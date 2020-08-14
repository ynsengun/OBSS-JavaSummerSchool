package session;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/list-session-attributes")
public class ListSessionAttributes extends HttpServlet{

	private static final long serialVersionUID = 3394921143960282508L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In list-session-attr doGet");
		HttpSession session = req.getSession();
		
//		ArrayList<String> attrs = (ArrayList<String>) session.getAttributeNames();
//		for( String attr: attrs) {
//			String attrVal = (String)session.getAttribute(attr);
//			resp.getWriter().append(attr +  "  " + attrVal);
//		}
		

		String attrVal = (String)session.getAttribute("attr");
		resp.getWriter().append("attr" +  "  " + attrVal + "\n");
		
		attrVal = (String)session.getAttribute("attr2");
		resp.getWriter().append("attr2" +  "  " + attrVal + "\n");
	}

}
