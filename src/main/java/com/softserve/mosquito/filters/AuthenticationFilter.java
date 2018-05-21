package com.softserve.mosquito.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter("/*")
public class AuthenticationFilter implements Filter{
	private List<String> allowedUris;
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		allowedUris = new ArrayList<>();
		//TODO: Change url
		allowedUris.add("login.html");
		allowedUris.add("login");
		allowedUris.add("registration.html");
		allowedUris.add("registration");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
	    HttpServletResponse res = (HttpServletResponse)response;
	    
	    String uri = req.getRequestURI();
	    //if request is going to allowed uri - give access to them
	    for(String allowedUri: allowedUris) {
		    if(uri.endsWith(allowedUri)) {
		    	filterChain.doFilter(request, response);
		    	return;
		    }
	    }
	    
	    HttpSession session = req.getSession(false);
	    if(session != null && session.getAttribute("user_id") != null) {
	    	filterChain.doFilter(request, response);
	    }else {
	    	//TODO Change url
	    	req.getRequestDispatcher("/login").forward(req, res);
	    }	    
		
	}

	
	@Override
	public void destroy() {
	}

}
