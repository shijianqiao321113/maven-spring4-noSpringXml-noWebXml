package com.spring4.pure.annotation.webxmlconfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.spring4.pure.annotation.config.ViewConfig;

public class WebSpringDispatcherInit implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(ViewConfig.class);
		ctx.setServletContext(servletContext);
		Dynamic servlet = servletContext.addServlet("dispatcher",new DispatcherServlet(ctx));
		servlet.addMapping("*.json");
//		servlet.addMapping("*.xhtml");
		servlet.setLoadOnStartup(1);
	}
}
