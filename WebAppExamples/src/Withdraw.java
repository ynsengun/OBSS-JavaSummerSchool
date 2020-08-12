import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Withdraw extends HttpServlet {

	private static final long serialVersionUID = -7380453233141177084L;
	private int balance;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In wd doGet");

		String balanceStr = req.getParameter("balance");
		this.balance = Integer.parseInt(balanceStr);

		System.out.println("Balance" + balance);

		// req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In wd doPost");

		String wdStr = req.getParameter("wd");
		int wd = Integer.parseInt(wdStr);

		synchronized (this) {
			if (this.balance >= wd) {
				try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				this.balance -= wd;
			}
		}

		System.out.println("Balance: " + this.balance);

	}
}
