package com.aurionpro.entity;

public class StudentErrorResponse {

	private int status;
	private String msg;
	private long timestamp;
	
	
	public StudentErrorResponse(int status, String msg, long timestamp) {
		this.status = status;
		this.msg = msg;
		this.timestamp = timestamp;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}


	public StudentErrorResponse() {
		super();
	}
	
	
	
	
}
