package servlet_info_sharing;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DestinationServlet extends HttpServlet {

	private static final long serialVersionUID = 2737485464135910799L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In destination doGet");
		
		ServletContext context = getServletContext();
		String attr1 = (String)context.getAttribute("attr1");
		System.out.println(attr1);
		
		String attr2 = (String)req.getAttribute("attr2");
		System.out.println(attr2);
	}

}
