package com.spring.interceptors;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.spring.execptions.UserAuthenticationFailedException;
import com.spring.execptions.UserAuthorizationFailedException;
import com.spring.security.PrincipalFactory;

public class AuthInterceptor implements HandlerInterceptor {

	Logger logger = Logger.getLogger(getClass());

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		logger.info("entering afterCompletion() for " + this.getClass());
		logger.info("servlet path:" + path);
		logger.info("before rendering to view from controller");
		logger.info("exiting afterCompletion() for " + this.getClass());
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		logger.info("entering postHandle() for " + this.getClass());
		logger.info("servlet path:" + path);
		logger.info("after executing controller");
		logger.info("exiting postHandle() for " + this.getClass());
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub

		String path = request.getServletPath();
		logger.info("entering preHandle() for " + this.getClass());
		logger.info("servlet path:" + path);
		logger.info("after executing controller");
		logger.info("exiting preHandle() for " + this.getClass());
		String userInfo[] = PrincipalFactory.getPrincipal();
		System.out
				.println(userInfo[0]
						+ "**preHandle()-LoginInterceptor:before executing controller - irrespective of url patern");

		if (userInfo[0].equals("anonymousUser")) {

			System.out.println("authentication failed");
			throw new UserAuthenticationFailedException(
					"Authentication Failed for user and the interceptor"
							+ this.getClass() + " throwing exception");
		} else if (userInfo[1].equals("[ROLE_ADMIN]") == false) {
			System.out.println("authorization failed for: " + userInfo[0]
					+ " having role as " + userInfo[1]);
			throw new UserAuthorizationFailedException(
					"Authorization Failed for user and the interceptor "
							+ this.getClass() + " throwing exception");
		}
		return true;
	}

}
