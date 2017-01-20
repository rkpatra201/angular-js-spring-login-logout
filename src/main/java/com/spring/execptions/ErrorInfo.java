package com.spring.execptions;

public class ErrorInfo {

	// error code needs to be logged for technical review
	private int errorCode;
	// error name should be displayed to user
	private String errorName;
	// error message should be displayed to user for additional understanding
	private String errorMessage;
	// some html pages must be at the desired link that will help user to know more about the error
	private String errorLink;
	public int getErrorCode() {
		return errorCode;
	}
	
	
	public ErrorInfo(int errorCode, String errorName, String errorMessage,
			String errorLink) {
		super();
		this.errorCode = errorCode;
		this.errorName = errorName;
		this.errorMessage = errorMessage;
		this.errorLink = errorLink;
	}


	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorName() {
		return errorName;
	}
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorLink() {
		return errorLink;
	}
	public void setErrorLink(String errorLink) {
		this.errorLink = errorLink;
	}
	
	
}
