
package com.ciexperts.projectmanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ciexperts.projectmanagement.dao.ProjectDao;
import com.ciexperts.projectmanagement.dao.RequestDao;
import com.ciexperts.projectmanagement.entity.Project;
import com.ciexperts.projectmanagement.entity.Request;
import com.ciexperts.projectmanagement.entity.RequestHistory;
import com.ciexperts.projectmanagement.tools.Util;

public class DeploymentService {
	private ProjectDao projectDao = new ProjectDao();
	private RequestDao requestDao = new RequestDao();
	
	public List<HashMap<String, Object>> getListForDeployment(){
		List<HashMap<String, Object>> deploymentList = new ArrayList<HashMap<String, Object>>();
		RequestHistory reqHistoryParam = new RequestHistory();
		reqHistoryParam.setRsNo(9);
		reqHistoryParam.setStatus("On-going");
		List<RequestHistory> reqHistList = requestDao.listReqHistByParam(reqHistoryParam);
		for (RequestHistory reqHist : reqHistList) {
			Request request = requestDao.getReqInfoByNo(reqHist.getReqNo());
			HashMap<String, Object> map = new HashMap<String, Object>();
			Project project = projectDao.getProjInfoByNo(request.getProjNo());
			map.put("reqNo", "R-" + String.format("%08d", request.getReqNo()));
			map.put("summary", request.getSummary());
			map.put("requestor", request.getRequestor());
			map.put("projNoName", String.format("%06d", project.getProjNo()) + " - " + project.getName());
			map.put("dateSubmitted", Util.formatDate(request.getDateSubmitted(), Util.dbDateFormat, "MMMM d, yyyy"));
			map.put("status", requestDao.getReqStatus(request.getReqNo()));
			deploymentList.add(map);
		}
		System.err.println(deploymentList.toString() + "       DEPLOYMENTLIST");
		return deploymentList;
	
	}
}
