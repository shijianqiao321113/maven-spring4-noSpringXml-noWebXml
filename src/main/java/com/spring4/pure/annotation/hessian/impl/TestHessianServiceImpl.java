package com.spring4.pure.annotation.hessian.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.spring4.pure.annotation.hessian.TestHessianService;

@Service
public class TestHessianServiceImpl implements TestHessianService {

	@Override
	public String getTestHessian() {
		System.out.println("=================hessian===========服务端===============");
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

}
