package com.company;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show-contact-form")
public class ShowContactsServlet extends HttpServlet {

	private static final long serialVersionUID = -7479240461367423474L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In show doGet");
		
		ArrayList<Contact> contacts = DBManager.getAll();
		req.setAttribute("contacts", contacts);
		
		req.getRequestDispatcher("/WEB-INF/show-contacts.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		
		System.out.println("In show doPost  -  name: " + name + " phone: " + phone);
		
		req.setAttribute("name", name);
		req.setAttribute("phone", phone);
		
		req.getRequestDispatcher("/WEB-INF/edit-contact.jsp").forward(req, resp);
	}

}
