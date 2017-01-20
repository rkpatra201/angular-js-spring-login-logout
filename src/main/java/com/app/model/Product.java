package com.app.model;

public class Product {

	
	private String productSlnum;
	private String productName;
	private float productPrice;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String productSlnum, String productName, float productPrice) {
		super();
		this.productSlnum = productSlnum;
		this.productName = productName;
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [productSlnum=" + productSlnum + ", productName="
				+ productName + ", productPrice=" + productPrice + "]";
	}

	public String getProductSlnum() {
		return productSlnum;
	}

	public void setProductSlnum(String productSlnum) {
		this.productSlnum = productSlnum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

}
