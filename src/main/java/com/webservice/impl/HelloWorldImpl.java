package com.webservice.impl;

import javax.jws.WebService;
import com.webservice.IHelloWorld;

@WebService(endpointInterface = "com.webservice.IHelloWorld")
public class HelloWorldImpl implements IHelloWorld {
	
	@Override
	public String sayHello(String text) {
		// TODO Auto-generated method stub
		return null;
	}
}
