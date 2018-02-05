package com.ciexperts.projectmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Project_Period")
public class ProjectPeriod {

	@Id @GeneratedValue
	@Column(name = "pp_no")
	private Integer ppNo;
	
	@Column(name = "proj_no")
	private Integer projNo;
	
	@Column(name = "start_date")
	private String startDate;
	
	@Column(name = "finish_date")
	private String finishDate;
	
	@Column(name = "region")
	private String region;
	
	@Column(name = "phase")
	private String phase;
	
	@Column(name = "planned_start_date")
	private String plannedStartDate;
	
	@Column(name = "planned_finish_date")
	private String plannedFinishDate;
	
	public ProjectPeriod() {
		super();
	}
	public ProjectPeriod(Integer ppNo, Integer projNo, String startDate,
			String finishDate, String region, String phase, String plannedStartDate,
			String plannedFinishDate) {
		super();
		this.ppNo = ppNo;
		this.projNo = projNo;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.region = region;
		this.phase = phase;
		this.plannedStartDate = plannedStartDate;
		this.plannedFinishDate = plannedFinishDate;
	}
	public Integer getPpNo() {
		return ppNo;
	}
	public void setPpNo(Integer ppNo) {
		this.ppNo = ppNo;
	}
	public Integer getProjNo() {
		return projNo;
	}
	public void setProjNo(Integer projNo) {
		this.projNo = projNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getPlannedStartDate() {
		return plannedStartDate;
	}
	public void setPlannedStartDate(String plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}
	public String getPlannedFinishDate() {
		return plannedFinishDate;
	}
	public void setPlannedFinishDate(String plannedFinishDate) {
		this.plannedFinishDate = plannedFinishDate;
	}
}
