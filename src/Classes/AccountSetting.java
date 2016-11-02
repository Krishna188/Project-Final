package Classes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AccountSetting
 */
@WebServlet("/AccountSetting")
public class AccountSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountSetting() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String fname = request.getParameter("fname").toString().toUpperCase().trim();
		String lname = request.getParameter("lname").toString().toUpperCase().trim();
		String role = session.getAttribute("role").toString().toUpperCase().trim();
		String password = request.getParameter("password").toString().trim();
		String c_password = request.getParameter("c_password").toString().trim();
		String username = session.getAttribute("username").toString().toUpperCase().trim();
		if (c_password == "" && password == "") {
			try {
				if (new Logic().change_name(fname, lname, username, role)) {
					session.setAttribute("result",
							new Display(Display.Type.SUCCESS).getHtml("Account Settings updated!"));
				} else {
					session.setAttribute("result",
							new Display(Display.Type.ERROR).getHtml("Failed to update your account!"));
				}
			} catch (Exception ex) {
				session.setAttribute("result", new Display(Display.Type.ERROR).getHtml(ex.getMessage()));
			}
		} else {
			if ((c_password.isEmpty() && !password.isEmpty()) || (!c_password.isEmpty() && password.isEmpty())) {
				session.setAttribute("result", new Display(Display.Type.WARNING)
						.getHtml("Both password fields are required to change password!"));
			} else if (c_password.equals(password)) {
				try {
					if (new Logic().change_name(fname, lname, username, role)) {
						// change password
						if (new Logic().change_password(password, username)) {
							session.setAttribute("result",
									new Display(Display.Type.SUCCESS).getHtml("Account Settings updated!"));
						} else {
							session.setAttribute("result",
									new Display(Display.Type.ERROR).getHtml("Failed to update your account!"));
						}
					} else {
						session.setAttribute("result",
								new Display(Display.Type.ERROR).getHtml("Failed to update your account!"));
					}
				} catch (Exception ex) {
					session.setAttribute("result", new Display(Display.Type.ERROR).getHtml(ex.getMessage()));
				}
			} else {
				session.setAttribute("result",
						new Display(Display.Type.WARNING).getHtml("Passwords don't match, Please try again!"));
			}
		}
		response.sendRedirect(role.toLowerCase().concat(".jsp"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
