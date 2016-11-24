package com.spring4.pure.annotation.webservicecxf.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.spring4.pure.annotation.webservicecxf.TestWebService;

@WebService(endpointInterface = "com.spring4.pure.annotation.webservicecxf.TestWebService",
		serviceName = "testWebServiceImpl")
public class TestWebServiceImpl implements TestWebService{

	@WebMethod
	public String getWebServiceDataInfo(String text) {
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
	}

}
