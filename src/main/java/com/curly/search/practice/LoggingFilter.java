package com.curly.search.practice;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		long startTime = System.currentTimeMillis();

		// Log request information
		String url = ((HttpServletRequest) request).getRequestURL().toString();
		String method = ((HttpServletRequest) request).getMethod();
		System.out.println("@@@@@@@@@@@@Request: " + method + " " + url);

		// Pass the request down the chain
		chain.doFilter(request, response);

		// Log response information
		int status = ((HttpServletResponse) request).getStatus();
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		System.out.println("@@@@@@@@@@@@@Response: " + status + " (Execution time: " + executionTime + "ms)");

	}
}
