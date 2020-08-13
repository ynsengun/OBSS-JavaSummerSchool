import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorCatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 6490258197811690663L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Throwable exception = (Throwable) req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		int statusCode = (int) req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String servletName = (String) req.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
		
		if(exception != null) {
			resp.getWriter().println("exception");
		} else {
			resp.getWriter().println("error");
		}
		
		resp.getWriter().println("status code: " + statusCode);
		resp.getWriter().println("servlet name: " + servletName);
	}
}
