package com.company;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/create-contact-form")
public class CreateContactServlet extends HttpServlet {

	private static final long serialVersionUID = 272255866103981842L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/create-contact.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In create doPost");
		
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		
		System.out.println("name: " + name + " phone: " + phone);
		
		Contact contactExist = DBManager.searchDB(name);
		if(contactExist == null) {
			Contact contact = new Contact(name, phone);
			DBManager.insertDB(contact);
			req.getRequestDispatcher("WEB-INF/operation-successful.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("WEB-INF/contact-exist.html").forward(req, resp);
		}
	}
}
