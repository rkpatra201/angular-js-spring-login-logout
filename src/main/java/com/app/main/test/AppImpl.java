package com.app.main.test;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.app.model.IApp;



@Repository("appImpl")
public class AppImpl implements IApp {

	
	public AppImpl() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("AppImpl()");
	}

	@Override
	public void m1() {
		// TODO Auto-generated method stub
		System.out.println("hello");
	}

}
