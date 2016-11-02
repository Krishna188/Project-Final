package Classes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Schedule_Exam
 */
@WebServlet("/Schedule_Exam")
public class Schedule_Exam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Schedule_Exam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String course = request.getParameter("course");
		String room = request.getParameter("room_no");
		String date = request.getParameter("date");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		
		HttpSession session = request.getSession();
		
		boolean scheduled_done = new Logic().schedule_exam(course, room, date, start_time, end_time);
		
		if(scheduled_done) {
			session.setAttribute("result",
					new Display(Display.Type.SUCCESS).getHtml("Exam scheduled successfully"));

		} else {
			session.setAttribute("result",
					new Display(Display.Type.ERROR).getHtml("Exam did not schedule successfully"));
		}
		response.sendRedirect("teacher.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
