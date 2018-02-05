package com.ciexperts.projectmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Request_Attachments")
public class RequestAttachment {
	@Id @GeneratedValue
	@Column(name = "item_no")
	private Integer itemNo;
	
	@Column(name = "req_no")
	private Integer reqNo;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_ext")
	private String fileExt;
	
	@Column(name = "last_user")
	private String lastUser;
	
/*	@Column(name = "time_stamp")
	private Date timesStamp;*/
	
	public RequestAttachment() {
		super();
	}
	
	public RequestAttachment(Integer itemNo, Integer reqNo, String fileName, String fileType, String fileExt,
			String lastUser/*, Date timesStamp*/) {
		super();
		this.itemNo = itemNo;
		this.reqNo = reqNo;
		this.fileName = fileName;
		this.fileExt = fileExt;
		this.lastUser = lastUser;
		//this.timesStamp = timesStamp;
	}


	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public Integer getReqNo() {
		return reqNo;
	}
	public void setReqNo(Integer reqNo) {
		this.reqNo = reqNo;
	}
/*	public Date getTimesStamp() {
		return timesStamp;
	}
	public void setTimesStamp(Date timesStamp) {
		this.timesStamp = timesStamp;
	}*/
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public String getLastUser() {
		return lastUser;
	}
	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}

}
