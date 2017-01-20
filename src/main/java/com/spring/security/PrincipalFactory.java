package com.spring.security;

import java.util.ArrayList;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class PrincipalFactory {
	public static String[] getPrincipal() {
		System.out.println("control flow");
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		String[] userInfo = new String[3];
		if (principal instanceof UserDetails) {
			userInfo[0] = ((UserDetails) principal).getUsername();
			userInfo[1] = ((UserDetails) principal).getAuthorities().toString();

		} else {
			userInfo[0] = principal.toString();

		}

		return userInfo;
	}

}
