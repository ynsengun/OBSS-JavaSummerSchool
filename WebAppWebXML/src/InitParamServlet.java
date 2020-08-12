import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitParamServlet extends HttpServlet {

	private static final long serialVersionUID = -2005712636912738895L;
	
	private String method;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("In initparam doGet");
		
		// getServletConfig(); we can get config here as well, so we can get init parameters here as well.
		
		if( "forward".equals(method) ) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(req, res);
		} else {
			res.sendRedirect("/WEB-INF/index.jsp");
		}
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); // ilk satirda olmali
		System.out.println("In Init");
		
		System.out.println( "Context Param: " + config.getServletContext().getInitParameter("contextParam"));
		
		this.method = config.getInitParameter("method");
		System.out.println( "Method: " + this.method);
		
	}
	
}
