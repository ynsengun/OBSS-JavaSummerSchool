package session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/create-session")
public class CreateSession extends HttpServlet {

	private static final long serialVersionUID = 8579757467330351780L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In create-session doGet");
//		req.getSession(true);
		HttpSession session = req.getSession();
		
		session.setAttribute("attr", "attrVal");
		session.setAttribute("attr2", "attrVal2");
		
//		req.getRequestDispatcher("/list-session-attributes").forward(req, resp);
//		resp.sendRedirect("/WebAppExamples/list-session-attributes");
	}

}
