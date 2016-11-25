package com.spring4.pure.annotation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring4.pure.annotation.hessian.TestHessianService;

@Configuration
@RequestMapping("/hessian")
public class HessianServerConfig {
	
	@Autowired
	private TestHessianService testHessianService;
	
	@Bean(name = "/testHessian")
	public HessianServiceExporter getHessianServiceExporter(){
		HessianServiceExporter hessian = new HessianServiceExporter();
		hessian.setService(testHessianService);
		hessian.setServiceInterface(TestHessianService.class);
		return hessian;
	}
}
