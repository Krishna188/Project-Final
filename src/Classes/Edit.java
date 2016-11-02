package Classes;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Edit() {
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

		HashMap<String, String> data = new HashMap<String, String>();

		try {
			data = new Logic().get_info(username);
			HttpSession session = request.getSession();
			session.setAttribute("USERNAME", data.get("USERNAME"));
			session.setAttribute("FIRSTNAME", data.get("FIRSTNAME"));
			session.setAttribute("LASTNAME", data.get("LASTNAME"));
			session.setAttribute("ROLE", data.get("ROLE"));
			session.setAttribute("PASSWORD", data.get("PASSWORD"));
			session.setAttribute("result", new Display(Display.Type.SUCCESS).getHtml("User Modified"));

			response.sendRedirect("modify_user.jsp");
		} catch (Exception e) {
			// nothing to do
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
