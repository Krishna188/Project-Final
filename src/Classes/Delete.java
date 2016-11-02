package Classes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Delete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		HttpSession session = request.getSession();
		try {
			boolean result = new Logic().delete_user(username);
			if (result) {
				session.setAttribute("result", new Display(Display.Type.SUCCESS).getHtml("User Deleted"));
			} else {
				session.setAttribute("result", new Display(Display.Type.ERROR).getHtml("User could not be Deleted"));
			}
			response.sendRedirect("admin.jsp");
		} catch (Exception e) {
			// nothing to do
			session.setAttribute("result", new Display(Display.Type.ERROR).getHtml(e.getMessage()));
		}

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
