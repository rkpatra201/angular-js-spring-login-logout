package com.spring.execptions;

import java.io.IOException;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class AppExceptionHandler {

	private Logger logger = Logger.getLogger(getClass());

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UserAuthenticationFailedException.class)
	public void DeviceNotFoundHandlerMethod(
			UserAuthenticationFailedException ex, Writer writer)
			throws IOException {
		ErrorInfo eInfo = new ErrorInfo(401, "AUTH_FAILED",
				"Authentication failed", "google.co.in");

		System.out.println("Error object created for: " + ex.getMessage());
		/*
		 * ModelAndView model = new ModelAndView(); model.addObject("error",
		 * eInfo); model.setViewName("error"); return model;
		 */
		ObjectMapper objectMapper = new ObjectMapper();
		String errorDataAsJson = objectMapper.writeValueAsString(eInfo);
		// return eInfo;
		writer.write(errorDataAsJson.toCharArray());
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(UserAuthorizationFailedException.class)
	public void userAuthorizationFailedHandler(
			UserAuthorizationFailedException ex, Writer writer)
			throws IOException {
		ErrorInfo eInfo = new ErrorInfo(403, "AUTHRIZATION_FAILED",
				"Authorization failed", "google.co.in");

		System.out.println("Error object created for: " + ex.getMessage());
		/*
		 * ModelAndView model = new ModelAndView(); model.addObject("error",
		 * eInfo); model.setViewName("error"); return model;
		 */
		ObjectMapper objectMapper = new ObjectMapper();
		String errorDataAsJson = objectMapper.writeValueAsString(eInfo);
		// return eInfo;
		writer.write(errorDataAsJson.toCharArray());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Throwable.class)
	public void defaultAppExceptionHandlerMethod(Writer writer, Throwable ex) {
		ErrorInfo eInfo = new ErrorInfo(
				500,
				"GENERIC_EXCEPTION",
				"Some Unknown Error Occured While Processin Your Request! Please Contact Support. 1800 102 444",
				"google.co.in");

		logger.info("Exception class: " + ex.getClass() + " message: "
				+ ex.getMessage());
		ObjectMapper objectMapper = new ObjectMapper();
		String errorDataAsJson = null;
		try {
			errorDataAsJson = objectMapper.writeValueAsString(eInfo);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return eInfo;
		try {
			writer.write(errorDataAsJson.toCharArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
