package com.ciexperts.projectmanagement.web;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.ciexperts.projectmanagement.entity.Request;
import com.ciexperts.projectmanagement.entity.RequestHistory;
import com.ciexperts.projectmanagement.entity.RequestStatus;
import com.ciexperts.projectmanagement.service.AttachmentService;
import com.ciexperts.projectmanagement.service.RequestService;
import com.ciexperts.projectmanagement.tools.ResponseFormatter;
import com.google.gson.Gson;

@Controller
public class RequestController {
	private RequestService requestService = new RequestService();
	private Gson gson = new Gson();

	@Autowired
	AttachmentService attachmentService = new AttachmentService();

	@RequestMapping(value = "/request/page", method = RequestMethod.GET)
	public ModelAndView requestListPage() {
		ModelAndView model = new ModelAndView("requests/requestlist");
		model.addObject("requestList", gson.toJson(ResponseFormatter.escapeHTMLInList(requestService.listRequest(""))));
		return model;
	}

	@RequestMapping(value = "/request/info", method = RequestMethod.GET)
	public ModelAndView requestInfo() {
		ModelAndView model = new ModelAndView("requests/requestinfo");
/*<<<<<<< .mine
		// ARVIC
		model.addObject("devManagerList",
				gson.toJson(ResponseFormatter.escapeHTMLInList(requestService.listDevManager())));
		model.addObject("businessAnalystList",
				gson.toJson(ResponseFormatter.escapeHTMLInList(requestService.listBusinessAnalyst())));
		model.addObject("qaManagerList",
				gson.toJson(ResponseFormatter.escapeHTMLInList(requestService.listQAManger())));
=======*/
		//ARVIC
		model.addObject("devManagerList",gson.toJson(requestService.listDevManager()));
		model.addObject("businessAnalystList",gson.toJson(requestService.listBusinessAnalyst()));
		model.addObject("qaManagerList",gson.toJson(requestService.listQAManger()));
		model.addObject("OPManagerList",gson.toJson(requestService.listOPManager())); /*added by SHARIE MANIPON 11.20.2017*/
		return model;
	}

	@RequestMapping(value = "/request/status", method = RequestMethod.GET)
	public ModelAndView requestStatus() {
		ModelAndView model = new ModelAndView("requests/requeststatus");
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/request/save", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> insertRequest(Request request, HttpServletRequest req) {
		return (HashMap<String, Object>) ResponseFormatter.escapeHTMLInObject(requestService.saveRequest(request, req));
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/request/saveproj", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> insertProjRequest(Request request, HttpServletRequest req) {
		return (HashMap<String, Object>) ResponseFormatter
				.escapeHTMLInObject(requestService.saveProjectRequest(request, req));
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/request/updateReq", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> updateRequest(Request request) {
		return (HashMap<String, Object>) ResponseFormatter.escapeHTMLInObject(requestService.updateRequest(request));
	}

	@RequestMapping(value = "/request/approve", method = RequestMethod.POST)
	@ResponseBody
	public String approveRequest(Request request) {
		requestService.approveRequest(request);
		return "success";
	}

	/* added by SHARIE MANIPON 11.21.2017 */
	@RequestMapping(value = "/request/sendEmailReq", method = RequestMethod.POST)
	@ResponseBody
	public String sendEmailRequest(HttpServletRequest req) {
		requestService.sendEmailRequest(req);
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/request/infobyno", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> reqinfobyno(@RequestParam Integer reqNo, HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("reqNo", reqNo);
		//Request request = (Request)ResponseFormatter.escapeHTMLInObject(requestService.getRequestInfoByNo(reqNo));
		return (HashMap<String, Object>) ResponseFormatter.escapeHTMLInObject(requestService.getRequestInfoByNo(reqNo));
	}

	@RequestMapping(value = "/request/statusmain", method = RequestMethod.GET)
	public ModelAndView statusMain(@RequestParam Integer reqNo) {
		ModelAndView model = new ModelAndView("/requests/reqStatusMain");
		model.addObject("reqHist", gson.toJson(ResponseFormatter.escapeHTMLInList(requestService.listReqHist(reqNo))));
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/request/history/list", method = RequestMethod.GET)
	@ResponseBody
	public List<RequestHistory> reqHist(@RequestParam Integer reqNo) {
		return (List<RequestHistory>) ResponseFormatter.escapeHTMLInList(requestService.listReqHist(reqNo));
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/request/status/list", method = RequestMethod.GET)
	@ResponseBody
	public List<RequestStatus> reqStatus() {
		return (List<RequestStatus>) ResponseFormatter.escapeHTMLInList(requestService.reqStatus());
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/request/status/update", method = RequestMethod.POST)
	@ResponseBody
	public List<RequestHistory> updateReqHist(@RequestParam String param) {
		HashMap<String, Object> params = gson.fromJson(param, HashMap.class);
		Integer reqNo = new Integer(params.get("reqNo").toString());
		Integer rhNo = new Integer(params.get("rhNo").toString()); //reqHistory number
		Integer rsNoDone = new Integer(params.get("rsNoDone").toString());
		Integer rsNoStart = new Integer(params.get("rsNoStart").toString());
		String rsNoStartLastTag = (String) params.get("rsNoStartLastTag");
		requestService.saveReqHist(reqNo, rhNo, rsNoDone, rsNoStart, rsNoStartLastTag);
		return (List<RequestHistory>) ResponseFormatter.escapeHTMLInList(requestService.listReqHist(reqNo));
	}

	@RequestMapping(value = "/request/upload", method = RequestMethod.POST)
	public @ResponseBody String doUpload(@RequestParam Integer reqNo, MultipartHttpServletRequest mRequest,
			HttpServletRequest hRequest) throws Exception {
		attachmentService.uploadReqAttachments(reqNo, mRequest, hRequest);
		return "Sucess";
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/request/attachments")
	public String getReqAttachments(@RequestParam Integer reqNo) {
		List<String> fileNames = (List<String>) ResponseFormatter
				.escapeHTMLInList(attachmentService.getReqAttachmentListByReqNo(reqNo));
		return gson.toJson(fileNames).toString();
	}

}
