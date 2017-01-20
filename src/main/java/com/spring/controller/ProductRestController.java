package com.spring.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.model.Product;
import com.app.model.UserResponse;
import com.spring.security.PrincipalFactory;

/*
 * URL
 /dummy,
 /allproducts,/addproduct,product-{id},/update-product-{id},delete-product-{id}
 * 
 */
@Controller
@PreAuthorize("hasRole('ADMIN')")
public class ProductRestController {

	private static Logger logger = Logger.getLogger(ProductRestController.class
			.getName());
	Map<String, Product> productData;

	public ProductRestController() {
		super();
		productData = new LinkedHashMap<String, Product>();

		String[] arrLiquior = new String[] { "Johnnie Walker", "Crown Royal",
				"Bulleit, Seagram's", "George Dickel", "Caol Ila", "Talisker",
				"Lagavulin", "Oban", "J&B", "Bell's", "Buchanan's", "Cardhu" };
		Arrays.sort(arrLiquior);
		for (int i = 0; i < arrLiquior.length; i++) {
			Product p = new Product(String.valueOf(new Random().hashCode()),
					arrLiquior[i], 9875.00F);
			productData.put(p.getProductSlnum(), p);
		}
		// TODO Auto-generated constructor stub
	}

	// sample json response test

	@RequestMapping(method = RequestMethod.GET, value = "dummy.do", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody
	Product getDummyProduct(@RequestHeader HttpHeaders headers) {
		return new Product("X23456", "SAMSUNG S6", 35000.78F);
	}

	// get all products
	@RequestMapping(method = RequestMethod.GET, value = "admin-allproducts.do", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public ResponseEntity<UserResponse> getAllProducts() {

		if (PrincipalFactory.getPrincipal() == null)
			System.out.println("session invalid");
		UserResponse response = new UserResponse(
				PrincipalFactory.getPrincipal()[0], productData);
		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
	}

	// add a product
	@RequestMapping(value = "admin-add-product.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public ResponseEntity<Void> addProduct(@RequestBody Product product,
			UriComponentsBuilder builder) {
		HttpHeaders headers = new HttpHeaders();
		int prodId = product.hashCode();
		Product p = new Product(String.valueOf(prodId),
				product.getProductName(), product.getProductPrice());// create/save
		System.out.println(p.getProductSlnum());
		productData.put(p.getProductSlnum(), p);
		headers.setLocation(builder.path("product-{id}")
				.buildAndExpand(p.getProductSlnum()).toUri());
		// logger.info("new product added");
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// add 5 dummy product
	@RequestMapping(value = "admin-add-five-product.do", method = RequestMethod.GET)
	public ResponseEntity<Void> addFiveProduct() {
		HttpHeaders headers = new HttpHeaders();
		String[] arrLiquior = new String[] { "Johnnie Walker", "Crown Royal",
				"Bulleit, Seagram's", "George Dickel", "Caol Ila", "Talisker",
				"Lagavulin", "Oban", "J&B", "Bell's", "Buchanan's", "Cardhu" };
		Arrays.sort(arrLiquior);
		for (int i = 0; i < arrLiquior.length; i++) {
			Product p = new Product(String.valueOf(new Random().hashCode()),
					arrLiquior[i], 9875.00F);
			productData.put(p.getProductSlnum(), p);
		}

		logger.info("new product added");
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// show a product
	@RequestMapping(value = "admin-product-{id}-show.do", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
		if (productData.containsKey(id) == false)
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Product>(productData.get(id), HttpStatus.OK);// retrieve
	}

	// update a product
	@RequestMapping(value = "admin-update-{id}-product.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public ResponseEntity<Product> updateProductById(
			@PathVariable("id") String id, @RequestBody Product product) {
		if (productData.containsKey(id) == false)
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		productData.put(product.getProductSlnum(), product);// update
		logger.info("updated"+product.getProductSlnum());
		return new ResponseEntity<Product>(productData.get(id), HttpStatus.OK);
	}

	// delete a product
	@RequestMapping(value = "admin-delete-{id}-product.do", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> deleteProductById(
			@PathVariable("id") String id) {
		if (productData.containsKey(id) == false)
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		productData.remove(id);// delete
		return new ResponseEntity<Product>(productData.get(id),
				HttpStatus.NO_CONTENT);
	}
}
