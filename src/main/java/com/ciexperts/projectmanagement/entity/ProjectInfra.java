package com.ciexperts.projectmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Project_Infra")
public class ProjectInfra {

	@Id @GeneratedValue
	@Column(name = "pi_no")
	private Integer piNo;
	
	@Column(name = "proj_no")
	private Integer projNo;
	
	@Column(name = "os")
	private String os;
	
	@Column(name = "middleware")
	private String middleware;
	
	@Column(name = "application")
	private String application;
	
	@Column(name = "cpu_memory")
	private String cpuMemory;
	
	public ProjectInfra() {
		super();
	}
	public ProjectInfra(Integer piNo, Integer projNo, String os,
			String middleware, String application, String cpuMemory) {
		super();
		this.piNo = piNo;
		this.projNo = projNo;
		this.os = os;
		this.middleware = middleware;
		this.application = application;
		this.cpuMemory = cpuMemory;
	}
	public Integer getPiNo() {
		return piNo;
	}
	public void setPiNo(Integer piNo) {
		this.piNo = piNo;
	}
	public Integer getProjNo() {
		return projNo;
	}
	public void setProjNo(Integer projNo) {
		this.projNo = projNo;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getMiddleware() {
		return middleware;
	}
	public void setMiddleware(String middleware) {
		this.middleware = middleware;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getCpuMemory() {
		return cpuMemory;
	}
	public void setCpuMemory(String cpuMemory) {
		this.cpuMemory = cpuMemory;
	}

}
