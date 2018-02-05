package com.ciexperts.projectmanagement.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ciexperts.projectmanagement.dao.ProjectDao;
import com.ciexperts.projectmanagement.dao.RequestDao;
import com.ciexperts.projectmanagement.entity.AppUser;
import com.ciexperts.projectmanagement.entity.Project;
import com.ciexperts.projectmanagement.entity.Request;
import com.ciexperts.projectmanagement.entity.RequestHistory;
import com.ciexperts.projectmanagement.entity.RequestStatus;
import com.ciexperts.projectmanagement.entity.User;
import com.ciexperts.projectmanagement.tools.Mailer;
import com.ciexperts.projectmanagement.tools.Util;

public class RequestService {
	private RequestDao requestDao = new RequestDao();
	private ProjectDao projectDao = new ProjectDao();
	
	public List<HashMap<String, Object>> listRequest(String screen){
		List<HashMap<String, Object>> reqList = new ArrayList<HashMap<String,Object>>();
		List<Request> requests = requestDao.listRequest();
		for (Request request : requests) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Project project = projectDao.getProjInfoByNo(request.getProjNo());
			map.put("reqNo", "R-" + String.format("%08d", request.getReqNo()));
			map.put("summary", request.getSummary());
			map.put("requestor", request.getRequestor());
			map.put("projNoName", String.format("%06d", project.getProjNo()) + " - " + project.getName());
			map.put("dateSubmitted", Util.formatDate(request.getDateSubmitted(), Util.dbDateFormat, "MMMM d, yyyy"));
			map.put("status", requestDao.getReqStatus(request.getReqNo()));
			reqList.add(map);
		}
		return reqList;
	}
	
	public HashMap<String, Object> saveRequest(Request request, HttpServletRequest httpreq){
		HttpSession session = httpreq.getSession();
		Request req = request;
		HashMap<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat(Util.dbDateFormat);
		String dateNow = sf.format(new Date()); 
		AppUser user = (AppUser) session.getAttribute("appUser");
		req.setRequestor(user.getFullName());
		req.setLastUpdate(dateNow);
		if(req.getReqNo() == null){
			req.setDateSubmitted(dateNow);
			Integer reqNo = requestDao.insertRequest(req);
			initialReqHistory(reqNo);
			map.put("reqNo", reqNo);
			map.put("dateSubmitted", Util.formatDate(dateNow, Util.dbDateFormat, "MMMM d, yyyy h:mma"));
			map.put("requestor", user.getFullName());
			map.put("status", "SUCCESS");
		}
		return map;
	}
	
	public HashMap<String, Object> saveProjectRequest(Request request, HttpServletRequest httpreq){
		HttpSession session = httpreq.getSession();
		Request req = request;
		HashMap<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat(Util.dbDateFormat);
		String dateNow = sf.format(new Date()); 
		AppUser user = (AppUser) session.getAttribute("appUser");
		req.setRequestor(user.getFullName());
		req.setLastUpdate(dateNow);
		req.setRequestType(1);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>req_TYPE_1" + req.getRequestType());
		if(req.getReqNo() == null){
			req.setDateSubmitted(dateNow);
			Integer reqNo = requestDao.insertRequest(req);
			initialReqHistory(reqNo);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>req_TYPE_2" + req.getRequestType());
			map.put("reqNo", reqNo);
			map.put("dateSubmitted", Util.formatDate(dateNow, Util.dbDateFormat, "MMMM d, yyyy h:mma"));
			map.put("requestor", user.getFullName());
			map.put("status", "SUCCESS");
		}
		return map;
	}
	
	public HashMap<String, Object> updateRequest(Request request){
		Request req = request;
		HashMap<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat(Util.dbDateFormat);
		String dateNow = sf.format(new Date());
		req.setLastUpdate(dateNow);
		if(req.getReqNo() != null){
			req.setDateSubmitted(dateNow);
			requestDao.updateRequest(req);
			Integer reqNo = req.getReqNo();
			//initialReqHistory(reqNo);
			map.put("reqNo", reqNo);
			map.put("dateSubmitted", Util.formatDate(dateNow, Util.dbDateFormat, "MMMM d, yyyy h:mma"));
			map.put("status", "SUCCESS");
		}
		return map;
	}
	
	private void initialReqHistory(Integer reqNo){
		SimpleDateFormat sf = new SimpleDateFormat(Util.dbDateFormat);
		String dateNow = sf.format(new Date()); 
		RequestHistory firstReqHist = new RequestHistory();
		firstReqHist.setReqNo(reqNo);
		firstReqHist.setRsNo(1);
		firstReqHist.setStartDate(dateNow);
		firstReqHist.setEndDate(dateNow);
		firstReqHist.setStatus("Completed");
		firstReqHist.setLastUpdate(dateNow);
		requestDao.insertRequestHistory(firstReqHist);
		
		RequestHistory secondReqHist = new RequestHistory();
		secondReqHist.setReqNo(reqNo);
		secondReqHist.setRsNo(2);
		secondReqHist.setStartDate(dateNow);
		secondReqHist.setStatus("On-going");
		secondReqHist.setLastUpdate(dateNow);
		requestDao.insertRequestHistory(secondReqHist);
		
	}
	
	public void approveRequest(Request request){
		Request req = request;
		SimpleDateFormat sf = new SimpleDateFormat(Util.dbDateFormat);
		String dateNow = sf.format(new Date()); 
		
		req.setLastUpdate(dateNow);
		req.setDateSubmitted(Util.formatDate(req.getDateSubmitted(), "MMMM d, yyyy h:mma", Util.dbDateFormat));
		requestDao.updateRequest(req);
	}
	
	/* added by SHARIE MANIPON 11.21.2017 */
	public void sendEmailRequest(HttpServletRequest req) {
		Integer reqNo    = Integer.parseInt(req.getParameter("reqNo"));
		String recipient = requestDao.getReqEmail(reqNo);
		String subject   = req.getParameter("subject");
		String message   = req.getParameter("message");
		String sender    = req.getParameter("sender");
		String password  = req.getParameter("password");
		
		System.out.println("E-mail successful!");
		
		Mailer.send(recipient,subject,message,sender,password);
	}
	
	public void saveReqHist(Integer reqNo, Integer rhNo, Integer rsNoDone, Integer rsNoStart, String rsNoStartLastTag){
		SimpleDateFormat sf = new SimpleDateFormat(Util.dbDateFormat);
		String dateNow = sf.format(new Date()); 
		RequestHistory firstReqHist = new RequestHistory();
		firstReqHist.setRhNo(rhNo);
		firstReqHist.setReqNo(reqNo);
		firstReqHist.setRsNo(rsNoDone);
		firstReqHist.setEndDate(dateNow);
		firstReqHist.setStatus("Completed");
		firstReqHist.setLastUpdate(dateNow);
		requestDao.updateReqHist(firstReqHist);
		
		RequestHistory secondReqHist = new RequestHistory();
		secondReqHist.setReqNo(reqNo);
		secondReqHist.setRsNo(rsNoStart);
		secondReqHist.setStartDate(dateNow);
		secondReqHist.setStatus("On-going");
		if("Y".equals(rsNoStartLastTag)){
			firstReqHist.setEndDate(dateNow);
			secondReqHist.setStatus("Completed");
		}
		secondReqHist.setLastUpdate(dateNow);
		requestDao.insertRequestHistory(secondReqHist);
	}
	
	public HashMap<String, Object> getRequestInfoByNo(Integer reqNo){
		HashMap<String, Object> request = new HashMap<String,Object>();
		Request reqInfo = requestDao.getReqInfoByNo(reqNo);
		//ARVIC
		String devMail = null;
		String baMail = null;
		String qaMail = null;
		String opMail = null; /* added by SHARIE MANIPON 11.21.2017 */
		String assignedDev = null;
		String assignedBA = null;
		String assignedQA = null;
		String assignedOPs = null; /* added by SHARIE MANIPON 11.21.2017 */
		List<User> assignedUserList = requestDao.listUserById(reqInfo.getAssignedDev(),reqInfo.getAssignedBA(),reqInfo.getAssignedQA(),reqInfo.getAssignedOPs());
		
		for (User user : assignedUserList) {
			if(user.getUserId().equals(reqInfo.getAssignedDev())){
				assignedDev = user.getFirstName() +" "+ user.getLastName();
				devMail = user.getEmail();
			}
			if(user.getUserId().equals(reqInfo.getAssignedBA())){
				assignedBA = user.getFirstName() +" "+ user.getLastName();
				baMail = user.getEmail();
			}
			if(user.getUserId().equals(reqInfo.getAssignedQA())){
				assignedQA = user.getFirstName() +" "+ user.getLastName();
				qaMail = user.getEmail();
			}
			if(user.getUserId().equals(reqInfo.getAssignedOPs())){
				assignedOPs = user.getFirstName() +" "+ user.getLastName();
				opMail = user.getEmail();
			}
		}
		
		reqInfo.setDateSubmitted(Util.formatDate(reqInfo.getDateSubmitted(), Util.dbDateFormat, "MMMM d, yyyy h:mma"));
		request.put("devMail", devMail);
		request.put("baMail", baMail);
		request.put("qaMail",qaMail);
		request.put("opMail",opMail);
		request.put("devId", reqInfo.getAssignedDev());
		request.put("baId", reqInfo.getAssignedBA());
		request.put("qaId", reqInfo.getAssignedQA());
		request.put("opId", reqInfo.getAssignedOPs());
		request.put("assignedDev", assignedDev);
		request.put("assignedBA", assignedBA);
		request.put("assignedQA", assignedQA);
		request.put("assignedOPs", assignedOPs);
		request.put("requestType", reqInfo.getRequestType());
		request.put("reqNo", reqInfo.getReqNo());
		request.put("summary", reqInfo.getSummary());
		request.put("dateSubmitted", reqInfo.getDateSubmitted());
		request.put("ravBy", reqInfo.getRavBy());
		request.put("desc", reqInfo.getDesc());
		request.put("remarks", reqInfo.getRemarks());
		request.put("requestor", reqInfo.getRequestor());
		return request;
	}
	
	public List<RequestStatus> reqStatus(){
		return requestDao.reqStatus();
	}
	
	public List<RequestHistory> listReqHist(Integer reqNo){
		return requestDao.listReqHist(reqNo);
	}
	
	public List<User> listDevManager(){
		return requestDao.listDevManager();
	}
	
	public List<User> listBusinessAnalyst(){
		return requestDao.listBusinessAnalyst();
	}
	
	public List<User> listQAManger(){
		return requestDao.listQAManager();
	}
	
	public Request getReqByProjNo(Integer projNo){
		return this.requestDao.getReqInfoByProjNo(projNo);
	}
	
	/*added by SHARIE MANIPON 11.20.2017*/
	public List<User> listOPManager(){
		return requestDao.listOPManager();
	}
}
