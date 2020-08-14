package com.company;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/public")
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = -7848927407686466141L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in init doGet");
		req.getRequestDispatcher("/public/public.html").forward(req, resp);
	}

}
