package com.ciexperts.projectmanagement.entity;

public class Deploy {

	private String host;
	private String port;
	private String contextPath;
	private String username;
	private String password;
	private String projName;
	private String reqNo;
	private String version;
	private String omEmail;
	
	public Deploy() {}

	public Deploy(String host, String port, String contextPath, String username, String password, String projName, String reqNo, String version, String omEmail) {
		this.host = host;
		this.port = port;
		this.contextPath = contextPath;
		this.username = username;
		this.password = password;
		this.projName = projName;
		this.reqNo = reqNo;
		this.version = version;
		this.omEmail = omEmail;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getOmEmail() {
		return omEmail;
	}

	public void setOmEmail(String omEmail) {
		this.omEmail = omEmail;
	}
}
