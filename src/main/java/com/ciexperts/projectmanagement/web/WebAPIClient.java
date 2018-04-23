package com.ciexperts.projectmanagement.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class WebAPIClient {
	
	@Value("${jenkinsapi.url}")
	private String url;
	@Value("${jenkinsapi.authid}")
	private String authId;
	@Value("${jenkinsapi.authpassword}")
	private String authPassword;	
	
	public WebAPIClient() {
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public String getAuthPassword() {
		return authPassword;
	}
	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}
	
}
