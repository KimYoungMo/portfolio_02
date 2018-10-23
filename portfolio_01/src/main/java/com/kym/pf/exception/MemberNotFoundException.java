package com.kym.pf.exception;

public class MemberNotFoundException extends Exception {
	
	private String message;
	
	public MemberNotFoundException() {
		this.message = "존재하지 않는 ID입니다.";
	}
	
	public MemberNotFoundException(String msg) {
		this.message = msg;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	} 
}
