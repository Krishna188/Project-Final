package Classes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Add_room
 */
@WebServlet("/Add_room")
public class Add_room extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_room() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String room_no = request.getParameter("room_no");
		HttpSession session = request.getSession();
		String type =request.getParameter("type");
		if(new Logic().add_room(room_no,type)) {
			session.setAttribute("result", new Display(Display.Type.SUCCESS).getHtml("Room added successfully"));
		} else {
			session.setAttribute("result", new Display(Display.Type.ERROR).getHtml("Room could not added successfully"));
		}
		response.sendRedirect("admin.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
