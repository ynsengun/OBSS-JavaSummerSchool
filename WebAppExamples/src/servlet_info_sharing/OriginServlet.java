package servlet_info_sharing;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OriginServlet extends HttpServlet {

	private static final long serialVersionUID = -6773118932294182226L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In origin doGet");
		
		ServletContext context = getServletContext();
		context.setAttribute("attr1", "a");

		req.setAttribute("attr2", "b");
		
		req.getRequestDispatcher("destination").forward(req, resp);
		
//		resp.sendRedirect("destination");
	}

}
