package com.app.model;

import java.util.Map;

public class UserResponse {

	private String userName;
	private Map<String, Product> productResponse;

	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserResponse(String userName, Map<String, Product> productResponse) {
		//super();
		this.userName = userName;
		this.productResponse = productResponse;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<String, Product> getProductResponse() {
		return productResponse;
	}

	public void setProductResponse(Map<String, Product> productResponse) {
		this.productResponse = productResponse;
	}
	

}
