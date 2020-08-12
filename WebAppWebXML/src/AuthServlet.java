import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/serv", name="Servlet") // will be dominated by web.xml, /serv will be 404 (/auth will dominate)
public class AuthServlet extends HttpServlet {

	private static final long serialVersionUID = -4099191990249828904L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("In auth doGet");
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(req, res);
	}
	
}
