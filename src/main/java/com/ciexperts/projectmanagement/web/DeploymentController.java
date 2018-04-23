package com.ciexperts.projectmanagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ciexperts.projectmanagement.service.AttachmentService;
import com.ciexperts.projectmanagement.service.DeploymentService;
import com.ciexperts.projectmanagement.tools.ResponseFormatter;
import com.google.gson.Gson;

@Controller
public class DeploymentController {

	private DeploymentService deploymentService = new DeploymentService();
	private Gson gson = new Gson();
	
	@Autowired
	AttachmentService attachmentService = new AttachmentService();
	
	@RequestMapping(value = "/deployment/page", method = RequestMethod.GET)
	public ModelAndView deploymentListPage(){
		ModelAndView model = new ModelAndView("deployments/deploymentlist");
		// model.addObject("projectList", gson.toJson(ResponseFormatter.escapeHTMLInList(projectService.getProjList("list"))));
		//	EDIT: RJVILLARUZ
		model.addObject("deploymentList", gson.toJson(ResponseFormatter.escapeHTMLInList(deploymentService.getListForDeployment())));
		
		return model;
	}
}
