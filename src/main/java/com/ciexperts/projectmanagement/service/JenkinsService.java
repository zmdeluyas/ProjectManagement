package com.ciexperts.projectmanagement.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.bind.DatatypeConverter;
import com.ciexperts.projectmanagement.entity.JenkinsRepoParam;
import com.ciexperts.projectmanagement.entity.Project;
import com.ciexperts.projectmanagement.entity.ProjectInfra;
import com.ciexperts.projectmanagement.entity.Request;
import com.ciexperts.projectmanagement.entity.User;
import com.ciexperts.projectmanagement.entity.VMConfig;

public class JenkinsService {

	private ProjectService projService = new ProjectService();
	private RequestService reqService = new RequestService();
	private UserService userService = new UserService();
	private static String baseUrl = "http://192.10.10.221:8080";
	
	public String triggerVMCreation(VMConfig vmCon, JenkinsRepoParam repoParam, String reqNo){
		String result = null;
		try {
			System.out.println(">>>>> INISIDE VM CREATION<<<<<<<<<<");
			URL url = new URL(baseUrl+"/job/CREATE_VM_VAGRANT/buildWithParameters");
			String user = "admin"; // username
			String pass = "fbb6b381ef4e8897f2e0341234f38800"; // password or API
																// token
			String authStr = user + ":" + pass;
			String encoding = DatatypeConverter.printBase64Binary(authStr.getBytes("utf-8"));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			
			//String urlParams = "PROJECT_NAME="+repoParam.getProjName()+"&DEV_EMAIL="+repoParam.getAssignedDev()+"&BA_EMAIL="+repoParam.getAssignedBa()+"&QA_EMAIL="+repoParam.getAssignedQa()+"&PM_EMAIL="+repoParam.getAssignedPm()+"&OPERATING_SYS="+vmCon.getOperatingSys().toLowerCase()+"&RECIPIENTS="+repoParam.getAssignedDev()+","+repoParam.getAssignedBa()+","+repoParam.getAssignedQa()+","+repoParam.getAssignedPm()+"&MEMORY="+vmCon.getMemory()+"&CPU="+vmCon.getCpu()+"&APPLICATION="+vmCon.getApp().toLowerCase()+"&MIDDLE_WARE="+vmCon.getMiddleWare().toLowerCase()+"&PROJECT_LINK="+repoParam.getProjLink()+"&PACKAGE_NAME="+repoParam.getProjName().toLowerCase();
			String urlParams = "PROJECT_NAME="+repoParam.getProjName()+"&DEV_EMAIL="+repoParam.getAssignedDev()+"&BA_EMAIL="+repoParam.getAssignedBa()+"&QA_EMAIL="+repoParam.getAssignedQa()+"&PM_EMAIL="+repoParam.getAssignedPm()+"&PROJ_NUMBER="+repoParam.getProjNo()+"&REQ_NO="+reqNo+"&OPERATING_SYS="+vmCon.getOperatingSys().toLowerCase()+"&MEMORY="+vmCon.getMemory()+"&CPU="+vmCon.getCpu()+"&APPLICATION="+vmCon.getApp().toLowerCase()+"&MIDDLE_WARE="+vmCon.getMiddleWare().toLowerCase();
			byte[] postData = urlParams.getBytes("utf-8");
			try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
				wr.write(postData);
			}

			InputStream content = connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			
			result = "success";
		} catch (Exception e) {
			result = "failed";
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String triggerCreateQAEnvironment(Integer projNo){
		Request reqInfo = reqService.getReqByProjNo(projNo);
		User qaInfo = userService.findUser(reqInfo.getAssignedQA());
		Project projInfo = projService.getProjInfoByNo(projNo);
		ProjectInfra projInfra = projService.getProjInfraByProjNo(projNo);
		String result = null;
		try {
			System.out.println(">>>>> INISIDE QA VM CREATION<<<<<<<<<<");
			URL url = new URL(baseUrl+"/job/CREATE_QA_ENVIRONMENT/buildWithParameters");
			String user = "admin"; // username
			String pass = "fbb6b381ef4e8897f2e0341234f38800"; // password or API token
																
			String authStr = user + ":" + pass;
			String encoding = DatatypeConverter.printBase64Binary(authStr.getBytes("utf-8"));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			
			//String urlParams = "PROJECT_NAME="+repoParam.getProjName()+"&DEV_EMAIL="+repoParam.getAssignedDev()+"&BA_EMAIL="+repoParam.getAssignedBa()+"&QA_EMAIL="+repoParam.getAssignedQa()+"&PM_EMAIL="+repoParam.getAssignedPm()+"&OPERATING_SYS="+vmCon.getOperatingSys().toLowerCase()+"&RECIPIENTS="+repoParam.getAssignedDev()+","+repoParam.getAssignedBa()+","+repoParam.getAssignedQa()+","+repoParam.getAssignedPm()+"&MEMORY="+vmCon.getMemory()+"&CPU="+vmCon.getCpu()+"&APPLICATION="+vmCon.getApp().toLowerCase()+"&MIDDLE_WARE="+vmCon.getMiddleWare().toLowerCase()+"&PROJECT_LINK="+repoParam.getProjLink()+"&PACKAGE_NAME="+repoParam.getProjName().toLowerCase();
			String urlParams = "PROJECT_NAME="+projInfo.getName()+"&OPERATING_SYS="+projInfra.getOs().toLowerCase()+"&RECIPIENTS="+qaInfo.getEmail()+"&CPU="+projInfra.getCpuMemory().substring(0, projInfra.getCpuMemory().indexOf("CPU/s"))+"&MEMORY="+projInfra.getCpuMemory().substring(projInfra.getCpuMemory().indexOf("s,") + 3)+"&APPLICATION="+projInfra.getApplication().toLowerCase()+"&MIDDLE_WARE="+projInfra.getMiddleware().toLowerCase();
			byte[] postData = urlParams.getBytes("utf-8");
			try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
				wr.write(postData);
			}

			InputStream content = connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			result = "success";
		} catch (Exception e) {
			result = "failed";
			e.printStackTrace();
		}
		
		return result;
	}

}
