package com.ciexperts.projectmanagement.entity;

public class ResultBuild {
	
	private String _class;
	private String result;

	public ResultBuild(String _class, String result) {
		//super();
		this._class = _class;
		this.result = result;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
