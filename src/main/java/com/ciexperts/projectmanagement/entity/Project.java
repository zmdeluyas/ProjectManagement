package com.ciexperts.projectmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Project")
public class Project {

	@Id @GeneratedValue
	@Column(name = "proj_no")
	private Integer projNo;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "business_unit")
	private String businesUnit;
	
	@Column(name = "desc")
	private String desc;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "proj_manager")
	private String projManager;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "health")
	private String health;
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "submitted")
	private Integer submitted;
	
	@Column(name = "approved")
	private Integer approved;

	public Project(){
	}

	public Project(Integer projNo, String name, String businesUnit,
			String desc, String createdBy, String projManager, String status,
			String health, String lastUpdate, String remarks) {
		super();
		this.projNo = projNo;
		this.name = name;
		this.businesUnit = businesUnit;
		this.desc = desc;
		this.createdBy = createdBy;
		this.projManager = projManager;
		this.status = status;
		this.health = health;
		this.lastUpdate = lastUpdate;
		this.remarks = remarks;
	}

	public Integer getProjNo() {
		return projNo;
	}

	public void setProjNo(Integer projNo) {
		this.projNo = projNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinesUnit() {
		return businesUnit;
	}

	public void setBusinesUnit(String businesUnit) {
		this.businesUnit = businesUnit;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getProjManager() {
		return projManager;
	}

	public void setProjManager(String projManager) {
		this.projManager = projManager;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Integer submitted) {
		this.submitted = submitted;
	}

	public Integer getApproved() {
		return approved;
	}

	public void setApproved(Integer approved) {
		this.approved = approved;
	}
	
	
	
}
