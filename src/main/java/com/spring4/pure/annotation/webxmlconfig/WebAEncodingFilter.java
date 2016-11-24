package com.spring4.pure.annotation.webxmlconfig;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.springframework.util.StringUtils;

@WebFilter(filterName="FilterEnCoding",urlPatterns="/*",initParams={@WebInitParam(name = "encoding", value = "UTF-8")})
public class WebAEncodingFilter implements Filter{

	private String encoding;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		if(!StringUtils.isEmpty(this.encoding)){
			request.setCharacterEncoding(this.encoding);
			response.setCharacterEncoding(this.encoding);
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
	}
	
	@Override
	public void destroy() { }
}
