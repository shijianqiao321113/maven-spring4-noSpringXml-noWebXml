package com.spring4.pure.annotation.webxmlconfig;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(filterName="FilterSession",urlPatterns={"/*"},  
		initParams={  
	    @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源  
	})
public class DruidWebFilter extends com.alibaba.druid.support.http.WebStatFilter{

}
