package com.ciexperts.projectmanagement.web;

import java.util.HashMap;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ciexperts.projectmanagement.entity.Deploy;
import com.ciexperts.projectmanagement.entity.JenkinsRepoParam;
import com.ciexperts.projectmanagement.entity.ReqStatusParam;
import com.ciexperts.projectmanagement.entity.VMConfig;
import com.ciexperts.projectmanagement.service.JenkinsService;
import com.ciexperts.projectmanagement.service.RequestService;
import com.ciexperts.projectmanagement.tools.ExceptionResolver;
import com.google.gson.Gson;

@Controller
public class JenkinsController {

	private JenkinsService jenkinsService = new JenkinsService();
	private RequestService reqService = new RequestService();
	private Gson gson = new Gson();
	
	@Autowired
	private WebAPIClient webAPIClient;
	
	@RequestMapping(value = "/jenkins/vmcreation", method = RequestMethod.POST)
	@ResponseBody
	public String creatVirtualMachine(@RequestParam String vmConfig, @RequestParam String repoParam, @RequestParam String reqNo){
		String result = "failed";
		
		VMConfig vmCon = gson.fromJson(vmConfig, VMConfig.class);
		JenkinsRepoParam jenkinsRepoParam = gson.fromJson(repoParam, JenkinsRepoParam.class);
		//String result = jenkinsService.triggerVMCreation(vmCon,repParam,reqNo);
		Integer response = jenkinsService.createVirtualMachine(vmCon, jenkinsRepoParam, reqNo, webAPIClient.getUrl(), webAPIClient.getAuthPassword()).getStatus();
		
		if(response == 200 || response == 201){
			result = "success";
		}
		
		return result;
	}
	
	/*added by SHARIE MANIPON 12.12.17*/
	@RequestMapping(value = "/jenkins/update/reqstatus", method = RequestMethod.POST)
	@ResponseBody
	public String updateReqStatus(@RequestBody ReqStatusParam params){
		ExceptionResolver resp = jenkinsService.updateReqStatus(params.getReqNo(), params.getCurrRsNo());
		return resp.getRespMessage();
	}
	
	@RequestMapping(value = "/jenkins/deploy", method = RequestMethod.POST)
	@ResponseBody
	public String updatetatus(Deploy deploy){
		String result = "failed";
		Response response = jenkinsService.triggerDeploy(deploy, webAPIClient.getUrl(),webAPIClient.getAuthPassword());
		if (response.getStatus() == 201 || response.getStatus() == 200) {
			result = "success";
		}
		return result;
	}
	
	@RequestMapping(value = "/jenkins/create/qa", method = RequestMethod.GET)
	@ResponseBody 
	public String triggerCreateQAEnvironment(@RequestParam Integer projNo){
		String result="failed";
		
		Integer response = jenkinsService.createQAEnvironment(projNo, webAPIClient.getUrl(), webAPIClient.getAuthPassword()).getStatus();
		if(response == 200 || response == 201 ){
			result = "success";
		}
		
		return result;
	}
	
	@RequestMapping(value = "/jenkins/notifier", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> triggerEmailNotifier(@RequestParam String reqNo){
		HashMap<String, Object> reqInfo = reqService.getRequestInfoByNo(Integer.parseInt(reqNo));
		return reqInfo;
	}
	
}
