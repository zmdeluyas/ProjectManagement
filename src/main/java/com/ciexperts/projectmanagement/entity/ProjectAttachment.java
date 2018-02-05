package com.ciexperts.projectmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Project_Attachments")
public class ProjectAttachment {
	@Id @GeneratedValue
	@Column(name = "item_no")
	private Integer itemNo;
	
	@Column(name = "proj_no")
	private Integer projNo;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_ext")
	private String fileExt;
	
	@Column(name = "last_user")
	private String lastUser;
	
	public ProjectAttachment() {
		super();
	}
	public ProjectAttachment(Integer itemNo, Integer projNo, String fileName, String fileExt,
			String lastUser) {
		super();
		this.itemNo = itemNo;
		this.projNo = projNo;
		this.fileName = fileName;
		this.fileExt = fileExt;
		this.lastUser = lastUser;
	}
	
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public Integer getProjNo() {
		return projNo;
	}
	public void setProjNo(Integer projNo) {
		this.projNo = projNo;
	}
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
