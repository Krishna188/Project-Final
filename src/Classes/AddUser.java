package Classes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUser() {
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
		String username = request.getParameter("username").toUpperCase().trim();
		String password = request.getParameter("password").trim();
		String fname = request.getParameter("fname").toUpperCase().trim();
		String lname = request.getParameter("lname").toUpperCase().trim();
		String role = request.getParameter("role").toUpperCase().trim();

		HttpSession session = request.getSession();
		if (role.equals("ADMIN") || role.equals("STUDENT") || role.equals("TEACHER")) {

			if (username == null || password == null || fname == null || lname == null || password == " "
					|| fname == " " || lname == " " || username == " ") {
				session.setAttribute("add_result",
						new Display(Display.Type.ERROR).getHtml("All the fields are required to add user."));
			} else {
				try {
					if (new Logic().add_user(username, password, role, fname, lname)) {
						session.setAttribute("add_result",
								new Display(Display.Type.SUCCESS).getHtml("User has been added!"));
					} else {
						session.setAttribute("add_result",
								new Display(Display.Type.ERROR).getHtml("Failed to add user!"));
					}
				} catch (Exception ex) {
					session.setAttribute("add_result", new Display(Display.Type.ERROR).getHtml(ex.getMessage()));
				}
			}
		} else {
			session.setAttribute("add_result",
					new Display(Display.Type.ERROR).getHtml("Invalid request please try again!"));
		}
		response.sendRedirect(session.getAttribute("role").toString().toLowerCase().trim() + ".jsp");
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
