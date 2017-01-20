package com.app.main.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.app.model.IApp;

@Component
//@DependsOn("appImpl")
public class Test {

	@Autowired
	 IApp app;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Test().m2();
	}

	private void m2()
	{
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource(
				"app-context.xml"));

		app=factory.getBean(AppImpl.class);
		app.m1();
		System.out.println("end");
	}
}
