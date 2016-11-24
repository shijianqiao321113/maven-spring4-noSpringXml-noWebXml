package com.spring4.pure.annotation.webxmlconfig;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/WebService/*")
public class WebServiceCxfServlet extends org.apache.cxf.transport.servlet.CXFServlet{

	private static final long serialVersionUID = 1L;

}
