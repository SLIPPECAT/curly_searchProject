package com.curly.search.practice;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.hibernate.Interceptor;

public interface Filter {

	public default void init(FilterConfig filterConfig) throws ServletException{}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException;

	public default void destroy() {}

}

