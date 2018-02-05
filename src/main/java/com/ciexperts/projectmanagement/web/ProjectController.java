package com.ciexperts.projectmanagement.web;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.ciexperts.projectmanagement.entity.Project;
import com.ciexperts.projectmanagement.entity.ProjectCost;
import com.ciexperts.projectmanagement.entity.ProjectHistory;
import com.ciexperts.projectmanagement.entity.ProjectInfra;
import com.ciexperts.projectmanagement.entity.ProjectPeriod;
import com.ciexperts.projectmanagement.entity.ProjectStatus;
import com.ciexperts.projectmanagement.service.AttachmentService;
import com.ciexperts.projectmanagement.service.ProjectService;
import com.ciexperts.projectmanagement.tools.ResponseFormatter;
import com.google.gson.Gson;

@Controller
public class ProjectController {

	private ProjectService projectService = new ProjectService();
	private Gson gson = new Gson();
	
	@Autowired
	AttachmentService attachmentService = new AttachmentService();
	
	@RequestMapping(value = "/project/page", method = RequestMethod.GET)
	public ModelAndView projectListPage(){
		ModelAndView model = new ModelAndView("projects/projectlist");
		// model.addObject("projectList", gson.toJson(ResponseFormatter.escapeHTMLInList(projectService.getProjList("list"))));
		//	EDIT: RJVILLARUZ
		model.addObject("projectList", gson.toJson(ResponseFormatter.escapeHTMLInList(projectService.getApprvProjList())));
		return model;
	}
	
	@RequestMapping(value = "/project/info", method = RequestMethod.GET)
	public ModelAndView projectInfo(@RequestParam Integer projNo, @RequestParam String page){
		ModelAndView model = new ModelAndView("projects/projectinfo");
		model.addObject("projNo", projNo);
		model.addObject("page", page);
		if(projNo == null && "createReq".equals(page)){
			model.addObject("projListPopup", gson.toJson(ResponseFormatter.escapeHTMLInList(projectService.getApprvProjList())));
		}
		return model;
	}
	
	@RequestMapping(value = "/project/adtlinfo", method = RequestMethod.GET)
	public ModelAndView projAdtlInfo(@RequestParam Integer projNo){
		ModelAndView model = new ModelAndView("projects/projadtlinfo");
		model.addObject("projNo", projNo);
		if(projNo != 0){
			model.addObject("projPeriod", gson.toJson(ResponseFormatter.escapeHTMLInObject(projectService.getProjPeriodByProjNo(projNo))));
			model.addObject("projInfra", gson.toJson(ResponseFormatter.escapeHTMLInObject(projectService.getProjInfraByProjNo(projNo))));
			model.addObject("projCost", gson.toJson(ResponseFormatter.escapeHTMLInObject(projectService.getProjCostByProjNo(projNo))));
		}
		return model;
	}
	
	@RequestMapping(value = "/project/planperiod", method = RequestMethod.GET)
	public ModelAndView projPlanPeriod(){
		ModelAndView model = new ModelAndView("projects/projplanperiod");
		return model;
	}
	
	@RequestMapping(value = "/project/infobyno", method = RequestMethod.GET)
	@ResponseBody
	public Project projinfobyno(@RequestParam Integer projNo){
		return (Project) ResponseFormatter.escapeHTMLInObject(projectService.getProjInfoByNo(projNo));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/project/periodlist", method = RequestMethod.GET)
	@ResponseBody
	public List<ProjectPeriod> projectPeriodList(@RequestParam Integer projNo){
		return (List<ProjectPeriod>) ResponseFormatter.escapeHTMLInList(projectService.listProjPeriod(projNo));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/project/infralist", method = RequestMethod.GET)
	@ResponseBody
	public List<ProjectInfra> projInfraList(@RequestParam Integer projNo){
		return (List<ProjectInfra>) ResponseFormatter.escapeHTMLInList(projectService.listProjectInfra(projNo));
	}
	
	@RequestMapping(value = "/project/insert", method = RequestMethod.POST)
	@ResponseBody
	public Integer insertProj(@RequestParam String projInfo, @RequestParam String projPeriod,
			@RequestParam String projInfra, @RequestParam String projCost){
		Project project = gson.fromJson(projInfo, Project.class);
		ProjectPeriod projectPeriod = gson.fromJson(projPeriod, ProjectPeriod.class);
		ProjectInfra projectInfra = gson.fromJson(projInfra, ProjectInfra.class);
		ProjectCost projectCost = gson.fromJson(projCost, ProjectCost.class);
		return projectService.insertProj(project, projectPeriod, projectInfra, projectCost);
	}
	
	@RequestMapping(value = "/project/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateProj(@RequestParam String projInfo, @RequestParam String projPeriod,
			@RequestParam String projInfra, @RequestParam String projCost){
		Project project = gson.fromJson(projInfo, Project.class);
		ProjectPeriod projectPeriod = gson.fromJson(projPeriod, ProjectPeriod.class);
		ProjectInfra projectInfra = gson.fromJson(projInfra, ProjectInfra.class);
		ProjectCost projectCost = gson.fromJson(projCost, ProjectCost.class);
		return projectService.updateProj(project, projectPeriod, projectInfra, projectCost);
	}
	
	
	// Rochelle Villaruz
	
	@RequestMapping(value = "/project/statusmain", method = RequestMethod.GET)
	public ModelAndView statusMain(@RequestParam Integer projNo){
		ModelAndView model = new ModelAndView("/projects/projStatusMain");
		model.addObject("projHist", gson.toJson(ResponseFormatter.escapeHTMLInList(projectService.listProjHist(projNo))));
		return model;
	}
	
	@RequestMapping(value = "/project/approve", method = RequestMethod.POST)
	@ResponseBody
	public String approveRequest(Project project){
		projectService.approveProject(project);
		return "success";
	}
	
	@RequestMapping(value = "/project/submit", method = RequestMethod.POST)
	@ResponseBody
	public String submitRequest(Project project){
		projectService.submitProject(project);
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/project/status/list", method = RequestMethod.GET)
	@ResponseBody
	public List<ProjectStatus> projStatus(){
		return (List<ProjectStatus>) ResponseFormatter.escapeHTMLInList(projectService.projStatus());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/project/status/update", method = RequestMethod.POST)
	@ResponseBody
	public List<ProjectHistory> updateProjHist(@RequestParam String param){
		HashMap<String, Object> params = gson.fromJson(param, HashMap.class);
		Integer projNo = new Integer(params.get("projNo").toString());
		Integer phNo = new Integer(params.get("phNo").toString());
		Integer psNoDone = new Integer(params.get("psNoDone").toString());
		Integer psNoStart = new Integer(params.get("psNoStart").toString());
		Integer approval = new Integer(params.get("approval").toString());
		String psNoStartLastTag = (String) params.get("psNoStartLastTag");
		projectService.saveProjHist(projNo, phNo, psNoDone, psNoStart, psNoStartLastTag, approval);
		return (List<ProjectHistory>) ResponseFormatter.escapeHTMLInList(projectService.listProjHist(projNo));
	}
	
	@RequestMapping(value = "/project/upload", method = RequestMethod.POST)
	public @ResponseBody String doUpload(@RequestParam Integer projNo,MultipartHttpServletRequest mRequest,HttpServletRequest hRequest) throws Exception {
			attachmentService.uploadProjAttachments(projNo, mRequest, hRequest);
		    return "Sucess";
}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping(value = "/project/attachments")
    public String getReqAttachments(@RequestParam Integer projNo) {
		List<String> fileNames =  (List<String>) ResponseFormatter.escapeHTMLInList(attachmentService.getProjectAttachmentListByProjNo(projNo));
        return gson.toJson(fileNames).toString();
    }
	
}
