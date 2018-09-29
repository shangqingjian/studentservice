package com.huawei.StudentService.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

@WebFilter("/AccessLogFilter")
public class AccessLogFilter implements Filter {

	private static final Logger logger = Logger.getLogger(AccessLogFilter.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		StringBuilder msgSb = new StringBuilder();
		String ip = req.getRemoteAddr();
		String method = req.getMethod();
		String uri = req.getRequestURI().toString();
		msgSb.append("[" + ip + "]").append("[" + method + "]").append("[" + uri + "]");
		logger.info(msgSb.toString());
		chain.doFilter(request, response);
	}

	public void destroy() {

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
