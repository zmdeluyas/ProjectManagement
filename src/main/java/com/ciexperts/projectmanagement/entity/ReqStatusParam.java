package com.ciexperts.projectmanagement.entity;

public class ReqStatusParam {

	private String reqNo;
	private Integer currRsNo;
	
	public ReqStatusParam(String reqNo, Integer currRsNo) {
		this.reqNo = reqNo;
		this.currRsNo = currRsNo;
	}
	public String getReqNo() {
		return reqNo.replace("REQ-", "");
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	public Integer getCurrRsNo() {
		return currRsNo;
	}
	public void setCurrRsNo(Integer currRsNo) {
		this.currRsNo = currRsNo;
	}
	
	
}
