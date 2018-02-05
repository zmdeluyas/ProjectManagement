package com.ciexperts.projectmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Project_History")
public class ProjectHistory {

	@Id @GeneratedValue
	@Column(name = "ph_no")
	private Integer phNo;
	
	@Column(name = "proj_no")
	private Integer projNo;
	
	@Column(name = "ps_no")
	private Integer psNo;
	
	@Column(name = "start_date")
	private String startDate;
	
	@Column(name = "end_date")
	private String endDate;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	public ProjectHistory() {
		super();
	}
	
	public ProjectHistory(Integer phNo, Integer projNo, Integer psNo,
			String startDate, String endDate, String status, String remarks,
			String lastUpdate) {
		super();
		this.phNo = phNo;
		this.projNo = projNo;
		this.psNo = psNo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.remarks = remarks;
		this.lastUpdate = lastUpdate;
	}

	public Integer getPhNo() {
		return phNo;
	}

	public void setPhNo(Integer phNo) {
		this.phNo = phNo;
	}

	public Integer getProjNo() {
		return projNo;
	}

	public void setProjNo(Integer projNo) {
		this.projNo = projNo;
	}

	public Integer getPsNo() {
		return psNo;
	}

	public void setPsNo(Integer psNo) {
		this.psNo = psNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
}
