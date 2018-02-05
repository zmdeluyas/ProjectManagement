package com.ciexperts.projectmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Request_Status")
public class RequestStatus {
	
	@Id @GeneratedValue
	@Column(name = "rs_no")
	private Integer rsNo;
	
	@Column(name = "seq_no")
	private Integer seqNo;
	
	@Column(name = "status_name")
	private String statusName;
	
	@Column(name = "ongoing_desc")
	private String ongoingDesc;
	
	@Column(name = "failed_desc")
	private String failedDesc;
	
	@Column(name = "complete_desc")
	private String completeDesc;
	
	@Column(name = "last_tag")
	private String lastTag;
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	public RequestStatus() {
		super();
	}
	public RequestStatus(Integer rsNo, Integer seqNo, String statusName,
			String ongoingDesc, String failedDesc, String completeDesc,
			String lastTag, String lastUpdate) {
		super();
		this.rsNo = rsNo;
		this.seqNo = seqNo;
		this.statusName = statusName;
		this.ongoingDesc = ongoingDesc;
		this.failedDesc = failedDesc;
		this.completeDesc = completeDesc;
		this.lastTag = lastTag;
		this.lastUpdate = lastUpdate;
	}
	public Integer getRsNo() {
		return rsNo;
	}
	public void setRsNo(Integer rsNo) {
		this.rsNo = rsNo;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getOngoingDesc() {
		return ongoingDesc;
	}
	public void setOngoingDesc(String ongoingDesc) {
		this.ongoingDesc = ongoingDesc;
	}
	public String getFailedDesc() {
		return failedDesc;
	}
	public void setFailedDesc(String failedDesc) {
		this.failedDesc = failedDesc;
	}
	public String getCompleteDesc() {
		return completeDesc;
	}
	public void setCompleteDesc(String completeDesc) {
		this.completeDesc = completeDesc;
	}
	public String getLastTag() {
		return lastTag;
	}
	public void setLastTag(String lastTag) {
		this.lastTag = lastTag;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
