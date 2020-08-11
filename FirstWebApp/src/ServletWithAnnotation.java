
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class ServletWithAnnotation extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3051792230907267836L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("In doGet");
//		String name = req.getParameter("name");
//		String surname = req.getParameter("surname");
		
		res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // send HTML page to client
        out.println("<html>");
        out.println("<head><title>A Test Servlet</title></head>");
        out.println("<body>");
        out.println("<h1>Test</h1>");
        out.println("<p>Simple servlet for testing.</p>");
        out.println("</body></html>");
		
//		res.getWriter().println("Hello " + name + " " + surname);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("In doPost");
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		System.out.println(name+surname);
		
		StringBuilder reqPayload = new StringBuilder();
        String line;
        while ((line = req.getReader().readLine()) != null) {
            reqPayload.append(line);
        }

        res.getWriter().println(reqPayload);
		
//		String tmp = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//		System.out.println(tmp);
		
//		super.doPost(req, res);
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("In service");
		super.service(req, res);
	}

}
