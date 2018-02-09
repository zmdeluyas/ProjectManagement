package com.ciexperts.projectmanagement.tools;

public class ExceptionResolver {

	private String respResult;
	private String respMessage;
	
	public ExceptionResolver() {
		super();
	}

	public ExceptionResolver(String respResult, String respMessage) {
		super();
		this.respResult = respResult;
		this.respMessage = respMessage;
	}

	public String getRespResult() {
		return respResult;
	}

	public void setRespResult(String respResult) {
		this.respResult = respResult;
	}

	public String getRespMessage() {
		return respMessage;
	}

	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}
	
}
