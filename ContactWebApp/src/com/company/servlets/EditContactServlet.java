package com.company.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.Contact;
import com.company.ContactDBManager;

@WebServlet("/edit-contact-form")
public class EditContactServlet extends HttpServlet {

	private static final long serialVersionUID = -7479240461367423474L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/edit-contact.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		
		System.out.println("In edit doPost name: " + name + " phone: " + phone);
		
		ContactDBManager.updateDB(name, new Contact(name, phone));
		req.getRequestDispatcher("WEB-INF/operation-successful.html").forward(req, resp);
	}
}
