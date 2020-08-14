package com.company;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/private/*")
public class FilterPrivate implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("In filter");
        HttpSession session = ((HttpServletRequest)request).getSession();
        
        String isUserLoggedIn = (String)session.getAttribute("isUserLoggedIn");
        System.out.println(isUserLoggedIn);
        if(!"true".equals(isUserLoggedIn)) {
        	System.out.println("inside if");
        	((HttpServletResponse)response).sendRedirect("/LoginWebApp/login");
        	return;
        }
		
		chain.doFilter(request, response);
		
	}

}
