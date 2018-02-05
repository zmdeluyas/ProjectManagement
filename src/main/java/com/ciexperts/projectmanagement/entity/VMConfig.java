package com.ciexperts.projectmanagement.entity;

public class VMConfig {

	private String operatingSys;
	private String middleWare;
	private String cpu;
	private String app;
	private String memory;
	public VMConfig(String operatingSys, String middleWare, String cpu, String app, String memory) {
		super();
		this.operatingSys = operatingSys;
		this.middleWare = middleWare;
		this.cpu = cpu;
		this.app = app;
		this.memory = memory;
	}
	public String getOperatingSys() {
		return operatingSys;
	}
	public void setOperatingSys(String operatingSys) {
		this.operatingSys = operatingSys;
	}
	public String getMiddleWare() {
		return middleWare;
	}
	public void setMiddleWare(String middleWare) {
		this.middleWare = middleWare;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	
}
