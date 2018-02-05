
package com.ciexperts.projectmanagement.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.ciexperts.projectmanagement.dao.ProjectDao;
import com.ciexperts.projectmanagement.entity.Project;
import com.ciexperts.projectmanagement.entity.ProjectCost;
import com.ciexperts.projectmanagement.entity.ProjectHistory;
import com.ciexperts.projectmanagement.entity.ProjectInfra;
import com.ciexperts.projectmanagement.entity.ProjectPeriod;
import com.ciexperts.projectmanagement.entity.ProjectStatus;
import com.ciexperts.projectmanagement.tools.Util;

public class ProjectService {
	private ProjectDao projectDao = new ProjectDao();
	
	public List<HashMap<String, Object>> getProjList(String screen){
		List<HashMap<String, Object>> projList = new ArrayList<HashMap<String, Object>>();
		List<Project> projects = projectDao.listProjects();
		for (Project project : projects) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("projNo", String.format("%06d", project.getProjNo()));
			map.put("name", project.getName());
			if("list".equalsIgnoreCase(screen)){
				map.put("businessUnit", project.getBusinesUnit());
				map.put("desc", project.getDesc());
				map.put("status", project.getStatus());
				map.put("health", project.getHealth());
			}
			projList.add(map);
		}
		return projList;
	}
	
	/* ROCHELLE */
	public List<HashMap<String, Object>> getApprvProjList(){
		List<HashMap<String, Object>> projList = new ArrayList<HashMap<String, Object>>();
		List<Project> projects = projectDao.listApprvProjects();
		for (Project project : projects) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("projNo", String.format("%06d", project.getProjNo()));
			map.put("name", project.getName());
			//if("list".equalsIgnoreCase(screen)){
				map.put("businessUnit", project.getBusinesUnit());
				map.put("desc", project.getDesc());
				map.put("status", project.getStatus());
				map.put("health", project.getHealth());
			//}
			projList.add(map);
		}
		return projList;
	}
	
	public Project getProjInfoByNo(Integer projNo){
		Project project = projectDao.getProjInfoByNo(projNo);
		return project;
	}
	
	public ProjectPeriod getProjPeriodByProjNo(Integer projNo){
		ProjectPeriod projPeriod = projectDao.getProjPeriodByProjNo(projNo);
		projPeriod.setStartDate(Util.formatDate(projPeriod.getStartDate(), Util.dbDateFormat, "MMMM d, yyyy"));
		projPeriod.setFinishDate(Util.formatDate(projPeriod.getFinishDate(), Util.dbDateFormat, "MMMM d, yyyy"));
		projPeriod.setPlannedStartDate(Util.formatDate(projPeriod.getPlannedStartDate(), Util.dbDateFormat, "MMMM d, yyyy"));
		projPeriod.setPlannedFinishDate(Util.formatDate(projPeriod.getPlannedFinishDate(), Util.dbDateFormat, "MMMM d, yyyy"));
		return projPeriod;
	}
	
	public ProjectInfra getProjInfraByProjNo(Integer projNo){
		ProjectInfra projInfra = projectDao.getProjInfraByProjNo(projNo);
		return projInfra;
	}
	
	public ProjectCost getProjCostByProjNo(Integer projNo){
		ProjectCost projCost = projectDao.getProjCostByProjNo(projNo);
		return projCost;
	}
	
	public List<ProjectPeriod> listProjPeriod(Integer projNo){
		List<ProjectPeriod> projPeriod = projectDao.listProjPeriod(projNo);
		List<ProjectPeriod> newProjPeriod = new ArrayList<ProjectPeriod>();
		for (ProjectPeriod projectPeriod : projPeriod) {
			projectPeriod.setStartDate(Util.formatDate(projectPeriod.getStartDate(), Util.dbDateFormat, "MMMM d, yyyy"));
			projectPeriod.setFinishDate(Util.formatDate(projectPeriod.getFinishDate(), Util.dbDateFormat, "MMMM d, yyyy"));
			projectPeriod.setPlannedStartDate(Util.formatDate(projectPeriod.getPlannedStartDate(), Util.dbDateFormat, "MMMM d, yyyy"));
			projectPeriod.setPlannedFinishDate(Util.formatDate(projectPeriod.getPlannedFinishDate(), Util.dbDateFormat, "MMMM d, yyyy"));
			newProjPeriod.add(projectPeriod);
		}
		return newProjPeriod;
	}
	
	public List<ProjectInfra> listProjectInfra(Integer projNo){
		return projectDao.listProjInfra(projNo);
	}
	
	public Integer insertProj(Project project, ProjectPeriod projectPeriod, ProjectInfra projectInfra, ProjectCost projectCost){
		SimpleDateFormat sf = new SimpleDateFormat(Util.dbDateFormat);
		String dateNow = sf.format(new Date()); 
		project.setLastUpdate(dateNow);
		Integer projNo = projectDao.insertProject(project);
		projectPeriod.setProjNo(projNo);
		if(projectPeriod.getStartDate() != null){
			projectPeriod.setStartDate(Util.formatDate(projectPeriod.getStartDate(), "MMMM d, yyyy", Util.dbDateFormat));
		}
		if(projectPeriod.getFinishDate() != null){
			projectPeriod.setFinishDate(Util.formatDate(projectPeriod.getFinishDate(), "MMMM d, yyyy", Util.dbDateFormat));
		}
		projectPeriod.setPlannedStartDate(Util.formatDate(projectPeriod.getPlannedStartDate(), "MMMM d, yyyy", Util.dbDateFormat));
		projectPeriod.setPlannedFinishDate(Util.formatDate(projectPeriod.getPlannedFinishDate(), "MMMM d, yyyy", Util.dbDateFormat));
		projectDao.insertProjectPeriod(projectPeriod);
		projectInfra.setProjNo(projNo);
		projectDao.insertProjectInfra(projectInfra);
		projectCost.setProjNo(projNo);
		projectDao.insertProjectCost(projectCost);
		return projNo;
	}
	
	public String updateProj(Project project, ProjectPeriod projectPeriod, ProjectInfra projectInfra, ProjectCost projectCost){
		SimpleDateFormat sf = new SimpleDateFormat(Util.dbDateFormat);
		String dateNow = sf.format(new Date()); 
		project.setLastUpdate(dateNow);
		projectDao.updateProject(project);
		if(projectPeriod.getStartDate() != null){
			projectPeriod.setStartDate(Util.formatDate(projectPeriod.getStartDate(), "MMMM d, yyyy", Util.dbDateFormat));
		}
		if(projectPeriod.getFinishDate() != null){
			projectPeriod.setFinishDate(Util.formatDate(projectPeriod.getFinishDate(), "MMMM d, yyyy", Util.dbDateFormat));
		}
		projectPeriod.setPlannedStartDate(Util.formatDate(projectPeriod.getPlannedStartDate(), "MMMM d, yyyy", Util.dbDateFormat));
		projectPeriod.setPlannedFinishDate(Util.formatDate(projectPeriod.getPlannedFinishDate(), "MMMM d, yyyy", Util.dbDateFormat));
		projectDao.updateProjectPeriod(projectPeriod);
		projectDao.updateProjectInfra(projectInfra);
		projectDao.updateProjectCost(projectCost);
		return "success";
	}
	
	// Rochelle Villaruz
	
	private void initialProjHistory(Integer projNo){
		SimpleDateFormat sf = new SimpleDateFormat(Util.dbDateFormat);
		String dateNow = sf.format(new Date()); 
		ProjectHistory firstProjHist = new ProjectHistory();
		firstProjHist.setProjNo(projNo);
		firstProjHist.setPsNo(1);
		firstProjHist.setStartDate(dateNow);
		firstProjHist.setEndDate(dateNow);
		firstProjHist.setStatus("Completed");
		firstProjHist.setLastUpdate(dateNow);
		projectDao.insertProjHistory(firstProjHist);
		
		ProjectHistory secondProjHist = new ProjectHistory();
		secondProjHist.setProjNo(projNo);
		secondProjHist.setPsNo(2);
		secondProjHist.setStartDate(dateNow);
		secondProjHist.setStatus("On-going");
		secondProjHist.setLastUpdate(dateNow);
		projectDao.insertProjHistory(secondProjHist);
		
	}
//	
	public void approveProject(Project project){
		Project proj = project;
		SimpleDateFormat sf = new SimpleDateFormat(Util.dbDateFormat);
		String dateNow = sf.format(new Date()); 
		
		proj.setApproved(1);
		proj.setLastUpdate(dateNow);
//		System.out.println(dateNow);
//		proj.setDateSubmitted(Util.formatDate(proj.getLastUpdate(), "MMMM d, yyyy h:mma", Util.dbDateFormat));
		projectDao.updateProject(proj);
		
		//initialProjHistory(proj.getProjNo());
		
	}
	
	public void submitProject(Project project){
		Project proj = project;
		SimpleDateFormat sf = new SimpleDateFormat(Util.dbDateFormat);
		String dateNow = sf.format(new Date()); 
		
		proj.setSubmitted(1);
		proj.setLastUpdate(dateNow);
//		System.out.println(dateNow);
//		proj.setDateSubmitted(Util.formatDate(proj.getLastUpdate(), "MMMM d, yyyy h:mma", Util.dbDateFormat));
//		projectDao.updateProject(proj);
		
//		projectDao.updateProject(project);
		
		projectDao.updateProjectSubmit(project);
		initialProjHistory(proj.getProjNo());
		
	}
//	
	public void saveProjHist(Integer projNo, Integer phNo, Integer psNoDone, Integer psNoStart, String psNoStartLastTag, Integer approval){
		SimpleDateFormat sf = new SimpleDateFormat(Util.dbDateFormat);
		String dateNow = sf.format(new Date()); 
		ProjectHistory firstProjHist = new ProjectHistory();
		String status;
		if (approval == 1) {
			status = "Completed";
		} else {
			status = "Failed";
		}
		firstProjHist.setPhNo(phNo);
		firstProjHist.setProjNo(projNo);
		firstProjHist.setPsNo(psNoDone);
		firstProjHist.setEndDate(dateNow);
		firstProjHist.setStatus("Completed");
		firstProjHist.setLastUpdate(dateNow);
		projectDao.updateProjHist(firstProjHist);
		
		ProjectHistory secondProjHist = new ProjectHistory();
		secondProjHist.setProjNo(projNo);
		secondProjHist.setPsNo(psNoStart);
		secondProjHist.setStartDate(dateNow);
		secondProjHist.setStatus("On-going");
		if("Y".equals(psNoStartLastTag)){
			firstProjHist.setEndDate(dateNow);
			secondProjHist.setStatus(status);
		}
		secondProjHist.setLastUpdate(dateNow);
		projectDao.insertProjHistory(secondProjHist);
	}
	
	public List<ProjectStatus> projStatus(){
		return projectDao.projStatus();
	}
	
	public List<ProjectHistory> listProjHist(Integer projNo){
		return projectDao.listProjHist(projNo);
	}

}
