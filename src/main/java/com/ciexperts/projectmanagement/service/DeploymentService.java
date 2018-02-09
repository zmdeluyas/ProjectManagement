
package com.ciexperts.projectmanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ciexperts.projectmanagement.dao.ProjectDao;
import com.ciexperts.projectmanagement.dao.RequestDao;
import com.ciexperts.projectmanagement.entity.Project;
import com.ciexperts.projectmanagement.entity.Request;
import com.ciexperts.projectmanagement.entity.RequestHistory;

public class DeploymentService {
	private ProjectDao projectDao = new ProjectDao();
	private RequestDao requestDao = new RequestDao();
	
	public List<HashMap<String, Object>> getListForDeployment(){
		List<HashMap<String, Object>> deploymentList = new ArrayList<HashMap<String, Object>>();
		//List<Project> projectList = new ArrayList<Project>();
		RequestHistory reqHistoryParam = new RequestHistory();
		reqHistoryParam.setRsNo(9);
		reqHistoryParam.setStatus("On-going");
		List<RequestHistory> reqHistList = requestDao.listReqHistByParam(reqHistoryParam);
		for (RequestHistory reqHist : reqHistList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Request request = requestDao.getReqInfoByNo(reqHist.getReqNo());
			Project project = projectDao.getProjInfoByNo(request.getProjNo());
			map.put("projNo", String.format("%06d", project.getProjNo()));
			map.put("name", project.getName());
			map.put("businessUnit", project.getBusinesUnit());
			map.put("desc", project.getDesc());
			map.put("status", project.getStatus());
			map.put("health", project.getHealth());
			map.put("reqNo", "R-" + String.format("%08d", request.getReqNo()));
			deploymentList.add(map);
		}
		System.err.println(deploymentList.toString() + "       DEPLOYMENTLIST");
		return deploymentList;
	
	}
}
