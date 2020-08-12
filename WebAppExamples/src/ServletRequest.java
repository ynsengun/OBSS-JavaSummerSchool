import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletRequest extends HttpServlet {

	private static final long serialVersionUID = -6228607572509214374L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("----------------------------");
		System.out.println(req.getRequestURL());
		System.out.println(req.getRequestURI());
		System.out.println(req.getServletPath());
		System.out.println(req.getContextPath());
		System.out.println(req.getPathInfo());
		System.out.println(req.getQueryString());
		System.out.println("----------------------------");
		
		
		String url = "";
		if( req.getPathInfo() != null )
			url += req.getPathInfo();
		if( req.getQueryString() != null )
			url += req.getQueryString();
		
		if( url.contains("secured") ) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
		} else {
			req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
		}
	}
}
