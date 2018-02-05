package com.ciexperts.projectmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Request")
public class Request {

	@Id @GeneratedValue
	@Column(name = "req_no")
	private Integer reqNo;
	
	@Column(name = "proj_no")
	private Integer projNo;
	
	@Column(name = "summary")
	private String summary;
	
	@Column(name = "date_submitted")
	private String dateSubmitted;
	
	@Column(name = "rav_by")
	private String ravBy;
	
	@Column(name = "assigned_dev")
	private String assignedDev;
	
	@Column(name = "desc")
	private String desc;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	@Column(name = "requestor")
	private String requestor;
	
	@Column(name = "request_type")
	private Integer requestType;
	
	@Column(name = "assigned_ba")
	private String assignedBA;
	
	@Column(name = "assigned_qa")
	private String assignedQA;
	
	/* added by SHARIE MANIPON 11.21.2017 */
	@Column(name = "assigned_ops")
	private String assignedOPs;
	
	/*@OneToMany(mappedBy="request", fetch=FetchType.LAZY)
	private Set<User> user;*/
	
	public Request() {
		super();
	}
	public Request(Integer reqNo, Integer projNo, String summary, String dateSubmitted, String ravBy,
			String assignedDev, String desc, String remarks, String lastUpdate, String requestor, Integer requestType,
			String assignedBA, String assignedQA, String assignedOPs) {
		super();
		this.reqNo = reqNo;
		this.projNo = projNo;
		this.summary = summary;
		this.dateSubmitted = dateSubmitted;
		this.ravBy = ravBy;
		this.assignedDev = assignedDev;
		this.desc = desc;
		this.remarks = remarks;
		this.lastUpdate = lastUpdate;
		this.requestor = requestor;
		this.requestType = requestType;
		this.assignedBA = assignedBA;
		this.assignedQA = assignedQA;
		this.assignedOPs = assignedOPs;
	}
	
	public Integer getReqNo() {
		return reqNo;
	}
	
	public void setReqNo(Integer reqNo) {
		this.reqNo = reqNo;
	}
	public Integer getProjNo() {
		return projNo;
	}
	public void setProjNo(Integer projNo) {
		this.projNo = projNo;
	}
	public String getRequestor() {
		return requestor;
	}
	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	public String getRavBy() {
		return ravBy;
	}
	public void setRavBy(String ravBy) {
		this.ravBy = ravBy;
	}
	public String getAssignedDev() {
		return assignedDev;
	}
	public void setAssignedDev(String assignedDev) {
		this.assignedDev = assignedDev;
	}
	/* added by SHARIE MANIPON 11.21.2017 */
	public String getAssignedOPs() {
		return assignedOPs;
	}
	public void setAssignedOPs(String assignedOPs) {
		this.assignedOPs = assignedOPs;
	}
	public String getAssignedBA() {
		return assignedBA;
	}
	public void setAssignedBA(String assignedBA) {
		this.assignedBA = assignedBA;
	}
	public String getAssignedQA() {
		return assignedQA;
	}
	public void setAssignedQA(String assignedQA) {
		this.assignedQA = assignedQA;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public Integer getRequestType() {
		return requestType;
	}
	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}
	
}
