package com.company.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.Contact;
import com.company.ContactDBManager;

@WebServlet("/search-contact-form")
public class SearchContactServlet extends HttpServlet {

	private static final long serialVersionUID = 4236679368150995048L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/search-contact.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		
		Contact contact = ContactDBManager.searchDB(name);
		if(contact == null) {
			req.getRequestDispatcher("/WEB-INF/searchNotFound.html").forward(req, resp);
			return;
		}
		
		req.setAttribute("name", contact.getName());
		req.setAttribute("phone", contact.getPhone());
		
		System.out.println("In search doPost  -  name: " + contact.getName() + " phone: " + contact.getPhone());
		
		req.getRequestDispatcher("/WEB-INF/edit-contact.jsp").forward(req, resp);
	}

}
