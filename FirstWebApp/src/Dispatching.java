import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dispatch")
public class Dispatching extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7336109280456347430L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Dispatch - doGet");

//		res.sendRedirect("hello.jsp"); // yeni request olusturur, url degisir, istedigim yere redirect(google vs)
		
		RequestDispatcher rd = req.getRequestDispatcher("hello.jsp");
		rd.forward(req, res); // onlar olmaz
		

	}
}
