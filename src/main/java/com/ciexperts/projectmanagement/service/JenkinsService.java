package com.ciexperts.projectmanagement.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.ciexperts.projectmanagement.entity.Deploy;
import com.ciexperts.projectmanagement.entity.JenkinsRepoParam;
import com.ciexperts.projectmanagement.entity.Project;
import com.ciexperts.projectmanagement.entity.ProjectInfra;
import com.ciexperts.projectmanagement.entity.Request;
import com.ciexperts.projectmanagement.entity.RequestHistory;
import com.ciexperts.projectmanagement.entity.RequestStatus;
import com.ciexperts.projectmanagement.entity.User;
import com.ciexperts.projectmanagement.entity.VMConfig;
import com.ciexperts.projectmanagement.tools.ExceptionResolver;

public class JenkinsService {

	private ProjectService projService = new ProjectService();
	private RequestService reqService = new RequestService();
	private UserService userService = new UserService();
	private ExceptionResolver exception;
	private WebTarget webTarget;
	private Client client = ClientBuilder.newClient();
	
	
	/*
	 * TRIGGER CREATE_VM_VAGRANT JOB IN JENKIS
	 * BY AALOVERIA
	 */
	public Response createVirtualMachine(VMConfig vmCon, JenkinsRepoParam repoParam, String reqNo, String url, String authPassword){
		
		webTarget = client.target(url).path("buildByToken").path("buildWithParameters")
				.queryParam("job", "CREATE_VM_VAGRANT").queryParam("token",authPassword);
		System.out.println("Inside create VM >>>>>>>>>>>> assignedOM " + repoParam.getAssignedOm());
		Form form = new Form();
		form.param("PROJECT_NAME", repoParam.getProjName());
		form.param("DEV_EMAIL", repoParam.getAssignedDev());
		form.param("BA_EMAIL", repoParam.getAssignedBa());
		form.param("QA_EMAIL", repoParam.getAssignedQa());
		form.param("PM_EMAIL", repoParam.getAssignedPm());
		form.param("OM_EMAIL", repoParam.getAssignedOm());
		form.param("PROJ_NUMBER", repoParam.getProjNo());
		form.param("REQ_NO", reqNo);
		form.param("OPERATING_SYS", vmCon.getOperatingSys().toLowerCase());
		form.param("MEMORY", vmCon.getMemory());
		form.param("CPU", vmCon.getCpu());
		form.param("APPLICATION", vmCon.getApp().toLowerCase());
		form.param("MIDDLE_WARE",vmCon.getMiddleWare().toLowerCase());
		
		return webTarget.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
	}
	
	/*
	 * TRIGGER CREATE_QA_ENVIRONMENT JOB IN JENKIS
	 * BY AALOVERIA
	 */
	public Response createQAEnvironment(Integer projNo, String url, String authPassword){
		
		Request reqInfo = reqService.getReqByProjNo(projNo);
		User qaInfo = userService.findUser(reqInfo.getAssignedQA());
		Project projInfo = projService.getProjInfoByNo(projNo);
		ProjectInfra projInfra = projService.getProjInfraByProjNo(projNo);
		
		webTarget = client.target(url).path("buildByToken").path("buildWithParameters")
				.queryParam("job", "CREATE_QA_ENVIRONMENT").queryParam("token",authPassword);
		
		Form form = new Form();
		form.param("PROJECT_NAME", projInfo.getName());
		form.param("OPERATING_SYS", projInfra.getOs().toLowerCase());
		form.param("RECIPIENTS", qaInfo.getEmail());
		form.param("CPU", projInfra.getCpuMemory().substring(0, projInfra.getCpuMemory().indexOf("CPU/s")));
		form.param("MEMORY", projInfra.getCpuMemory().substring(projInfra.getCpuMemory().indexOf("s,") + 3));
		form.param("APPLICATION", projInfra.getApplication().toLowerCase());
		form.param("MIDDLE_WARE", projInfra.getMiddleware().toLowerCase());
		
		
		return webTarget.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
	}
	
	/*
	 * Trigger Deploy JOB in JENKINS
	 * By AALOVERIA
	 */
	public Response triggerDeploy(Deploy deploy, String url, String authPassword) {
		
		webTarget = client.target(url).path("buildByToken").path("buildWithParameters")
				.queryParam("job", "DEPLOY").queryParam("token",authPassword);
		
		Form form = new Form();
		form.param("HOST_ADDRESS", deploy.getHost());
		form.param("HOST_PORT", deploy.getPort());
		form.param("USERNAME", deploy.getUsername());
		form.param("PASSWORD", deploy.getPassword());
		form.param("CONTEXT_PATH", deploy.getContextPath());
		form.param("PROJECT_NAME", deploy.getProjName());
		form.param("REQ_NO", deploy.getReqNo());
		form.param("VERSION", deploy.getVersion());
		form.param("OM_EMAIL", deploy.getOmEmail());

		return webTarget.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
	}
	
	public ExceptionResolver updateReqStatus(String paramReqNo, Integer currRsNo){
		exception = new ExceptionResolver();
		Integer reqNo = null;
		Integer nextRsNo = null;
		Integer prevRsNo = null;
		Integer rhNo = null;
		String nextRsLastTag = "";
		String prevReqStatus = "";
		Boolean isNextRsExist = false;
		
		try {
			reqNo = Integer.parseInt(paramReqNo);
			prevRsNo = currRsNo - 1;
			nextRsNo = currRsNo + 1;
			
			List<RequestHistory> reqHistList = reqService.listReqHist(reqNo);
			for (int i = 0; i < reqHistList.size(); i++) {
				if (reqHistList.get(i).getRsNo() == (prevRsNo)){
					prevReqStatus = reqHistList.get(i).getStatus();
				}
				
				if (reqHistList.get(i).getRsNo() == currRsNo) {
					rhNo = reqHistList.get(i).getRhNo();
				}
				
				if (reqHistList.get(i).getRsNo() == nextRsNo){
					isNextRsExist = true;
				}
	
			}
			
			List<RequestStatus> reqStatusList = reqService.reqStatus();
			for (int i = 0; i < reqStatusList.size(); i++) {
				if (reqStatusList.get(i).getRsNo() == nextRsNo) {
					nextRsLastTag = reqStatusList.get(i).getLastTag();
				}
			}
			
			if (!(prevReqStatus.equals("On-going"))) {
				reqService.saveReqHist(reqNo, rhNo, currRsNo, nextRsNo, nextRsLastTag, isNextRsExist);
				exception.setRespResult("success");
				exception.setRespMessage("Reqeuest status updated successfully ");
			} else {
				exception.setRespResult("failed");
				exception.setRespMessage("Cannot update the request status, previous process still On-going ");
			}
			
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			exception.setRespResult("failed");
			exception.setRespMessage("Number format exception - ");
		}
		return exception;
	}
}
