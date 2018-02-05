package com.ciexperts.projectmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Request_History")
public class RequestHistory {

	@Id @GeneratedValue
	@Column(name = "rh_no")
	private Integer rhNo;
	
	@Column(name = "req_no")
	private Integer reqNo;
	
	@Column(name = "rs_no")
	private Integer rsNo;
	
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
	
	public RequestHistory() {
		super();
	}
	
	public RequestHistory(Integer rhNo, Integer reqNo, Integer rsNo,
			String startDate, String endDate, String status, String remarks,
			String lastUpdate) {
		super();
		this.rhNo = rhNo;
		this.reqNo = reqNo;
		this.rsNo = rsNo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.remarks = remarks;
		this.lastUpdate = lastUpdate;
	}

	public Integer getRhNo() {
		return rhNo;
	}
	public void setRhNo(Integer rhNo) {
		this.rhNo = rhNo;
	}
	public Integer getReqNo() {
		return reqNo;
	}
	public void setReqNo(Integer reqNo) {
		this.reqNo = reqNo;
	}
	public Integer getRsNo() {
		return rsNo;
	}
	public void setRsNo(Integer rsNo) {
		this.rsNo = rsNo;
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
