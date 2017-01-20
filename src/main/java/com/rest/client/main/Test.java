package com.rest.client.main;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.app.model.Product;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {
		// configuring client credentials
		ClientConfig clientConfig = new ClientConfig();
		//HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "admin123");
		//clientConfig.register(feature);
		Client client = ClientBuilder.newClient(clientConfig);
		
		// calling the rest api
		WebTarget webTarget = client.target(
				"http://jsonplaceholder.typicode.com/")
				.path("posts");
		Invocation.Builder invocationBuilder = webTarget
				.request(MediaType.APPLICATION_JSON);
		
		// getting response
		Response response = invocationBuilder.get();
		System.out.println(response.getStatus());
		System.out.println(response.getStatusInfo());
		
		//reading data as json and mapping json data to java object using object mapper
		String result = response.readEntity(String.class);
		//ObjectMapper objectMapper = new ObjectMapper();
		//Product p=objectMapper.readValue(result, Product.class);
		
		System.out.println(result);
	}
}
