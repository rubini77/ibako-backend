package com.example.demo.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;


@Component
@Configuration
public class CorsFilter implements Filter {
	
	public void init(FilterConfig filterconfig) throws ServletException{
		// No initialization required	
	}
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,FilterChain filterChain) throws IOException,ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,PATCH,UPDATE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		filterChain.doFilter(servletRequest,servletResponse);
		
	}
	public void destroy(){
		// No cleanup required
	}

}
