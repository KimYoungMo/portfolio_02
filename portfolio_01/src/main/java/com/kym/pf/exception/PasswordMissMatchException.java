package com.kym.pf.exception;

public class PasswordMissMatchException extends Exception {
	
	private String message;
	
	public PasswordMissMatchException() {
		this.message = "비밀번호가 다릅니다.";
	}
	
	public PasswordMissMatchException(String msg) {
		this.message = msg;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param msg the message to set
	 */
	public void setMessage(String msg) {
		this.message = msg;
	}
}
