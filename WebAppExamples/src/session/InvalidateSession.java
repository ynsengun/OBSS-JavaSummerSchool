package session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/invalidate-session")
public class InvalidateSession extends HttpServlet {

	private static final long serialVersionUID = -6942985743364961144L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In invalidate-session doGet");
		
		HttpSession session = req.getSession(false);
		if(session != null)
			session.invalidate();
	}

}
