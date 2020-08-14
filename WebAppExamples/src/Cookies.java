import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test-cookies")
public class Cookies extends HttpServlet {

	private static final long serialVersionUID = -1882395937628488121L;
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public static String generateRandpmString(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("display-cookie") != null) {
			Cookie[] cookies = req.getCookies();
			if(cookies != null) {
				Writer writer = resp.getWriter();
				for( Cookie cookie: cookies ) {
					writer.append(cookie.getName() + "  " + cookie.getValue() + "\n");
				}
			}
		} else {
			String s1 = generateRandpmString(5);
			String s2 = generateRandpmString(5);
			System.out.println(s1 + "  " + s2);
			
			resp.addCookie(new Cookie(s1, s2));
			resp.getWriter().append("Cookie added");
		}
		
	}

}
