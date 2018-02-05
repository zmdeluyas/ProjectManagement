package com.ciexperts.projectmanagement.entity;

public class AppUser {
	private String username;
	private String fullName;
	private String access;
	public AppUser() {
		super();
	}
	public AppUser(String username, String fullName, String access) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.access = access;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	
}
