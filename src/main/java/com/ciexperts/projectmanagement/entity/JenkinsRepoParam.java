package com.ciexperts.projectmanagement.entity;

public class JenkinsRepoParam {
	
	private String projName;
	private String assignedDev;
	private String assignedBa;
	private String assignedQa;
	private String assignedPm;
	private String projNo;

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getAssignedBa() {
		return assignedBa;
	}

	public void setAssignedBa(String assignedBa) {
		this.assignedBa = assignedBa;
	}

	public String getAssignedQa() {
		return assignedQa;
	}

	public void setAssignedQa(String assignedQa) {
		this.assignedQa = assignedQa;
	}

	public String getAssignedDev() {
		return assignedDev;
	}

	public void setAssignedDev(String assignedDev) {
		this.assignedDev = assignedDev;
	}

	public String getAssignedPm() {
		return assignedPm;
	}

	public void setAssignedPm(String assignedPm) {
		this.assignedPm = assignedPm;
	}

	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

}
