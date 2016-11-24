package com.spring4.pure.annotation.webservicecxf;

import javax.jws.WebService;

@WebService
public interface TestWebService {

	public String getWebServiceDataInfo(String text);
	
}
