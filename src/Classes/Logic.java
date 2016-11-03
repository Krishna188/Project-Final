package Classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Logic {

	protected Database database;

	/**
	 * Logic Class Constructor
	 */
	public Logic() {
		database = new Database("anirudh.ddns.net", 3306, "project_humber", "nirav", "nish8099");
	}

	/**
	 * This Method returns authentication result. It requires username and
	 * passowrd and tells if the user exists or not
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean authenticate(String username, String password) throws Exception {
		String uname = username.trim().toUpperCase();
		String pass = password.trim();

		try {
			String query = String.format(Query.AUTHENTICATE.toString(), uname, pass);

			ArrayList<HashMap<String, String>> data = database.execute(query);
			if (data.isEmpty()) {
				throw new Exception("No username password combination found!");
			} else if (data.size() == 1) {
				return true;
			}
		} catch (Exception ex) {
			throw ex;
		}
		return false;
	}

	public HashMap<String, String> get_info(String username) throws Exception {

		HashMap<String, String> data = new HashMap<>();
		String query = String.format(Query.SELECT_ALL_FROM.toString(), "USER", username.trim().toUpperCase());
		ArrayList<HashMap<String, String>> user = database.execute(query);
		query = String.format(Query.SELECT_ALL_FROM.toString(), user.get(0).get("ROLE").toUpperCase().trim(),
				username.trim().toUpperCase());

		user.addAll(database.execute(query));

		if (user.isEmpty()) {
			throw new Exception("No user found with that username!");
		} else {
			data.put("ROLE", user.get(0).get("ROLE").toString());
			data.put("USERNAME", user.get(0).get("USERNAME").toString());
			data.put("PASSWORD", user.get(0).get("PASSWORD").toString());
			data.put("FIRSTNAME", user.get(1).get("FIRSTNAME").toString());
			data.put("LASTNAME", user.get(1).get("LASTNAME").toString());
			return data;
		}

	}

	public String get_student_exams(String username) throws Exception {
		String user = username.trim().toUpperCase();
		String query = String.format(Query.SELECT_SCHEDULED_EXAMS.toString(), user);
		ArrayList<HashMap<String, String>> exams = database.execute(query);
		
		String data=" ";
		if (exams.isEmpty()) {
			return new Display(Display.Type.INFO).getHtml("No Exams Scheduled Yet!");
		} else {
			data = "<table class=\"table table-striped table-bordered table-hover table-responsive \">"
					+ "<tr> <th>COURSE</th> <th>ROOM</th> <th>DATE</th><th>START TIME</th><th>END TIME</th> </tr>";

			for (int i = 0; i < exams.size(); i++) {
				data += String.format("<tr> <td>%s</td> <td>%s</td> <td>%s</td><td>%s</td><td> %s</td> </tr>",
						exams.get(i).get("COURSE_CODE").toString(), exams.get(i).get("ROOM_NO").toString(),
						exams.get(i).get("EXAM_DATE").toString(),
						exams.get(i).get("START_TIME").toString(),
						exams.get(i).get("END_TIME").toString()
						);
			}
			data += "</table> ";
		}
		return data;
	}

	public String get_teacher_list() throws Exception {

		String data = " ";
		try {
			String query = String.format(Query.SELECT_INFO_OF_TEACHERS.toString());
			ArrayList<HashMap<String, String>> teachers = database.execute(query);
			if (teachers.size() != 0) {
				data = "<table class=\"table table-striped table-bordered table-hover table-responsive \">"
						+ "<tr> <th>USERNAME</th> <th>FIRST NAME</th> <th>LAST NAME</th><th>EDIT</th><th>DELETE</th> </tr>";

				for (int i = 0; i < teachers.size(); i++) {
					data += String.format("<tr> <td>%s</td> <td>%s</td> <td>%s</td><td>%s</td><td> %s</td> </tr>",
							teachers.get(i).get("USERNAME").toString(), teachers.get(i).get("FIRSTNAME").toString(),
							teachers.get(i).get("LASTNAME").toString(),
							"<a href=\"Edit?username=" + (teachers.get(i).get("USERNAME").toString())
									+ "\"><span class=\"glyphicon glyphicon-pencil\"></span></a>",
							"<a href=\"Delete?username=" + teachers.get(i).get("USERNAME").toString()
									+ "\"><span class=\"glyphicon glyphicon-remove\"></span></a>");
				}
				data += "</table>";
			}
		} catch (Exception e) {
			throw e;
		}
		if (data == " ") {
			data = new Display(Display.Type.INFO).getHtml("No Teachers added.");
		}
		return data;
	}

	public String get_student_list() throws Exception {

		String data = " ";
		try {
			String query = String.format(Query.SELECT_INFO_OF_STUDENTS.toString());
			ArrayList<HashMap<String, String>> students = database.execute(query);
			if (students.size() != 0) {
				data = "<table class=\"table table-striped table-bordered table-hover table-responsive \">"
						+ "<tr> <th>USERNAME</th> <th>FIRST NAME</th> <th>LAST NAME</th><th>EDIT</th> <th>DELETE</th> </tr>";
				for (int i = 0; i < students.size(); i++) {
					data += String.format("<tr> <td>%s</td> <td>%s</td> <td>%s</td><td>%s</td><td> %s</td> </tr>",
							students.get(i).get("USERNAME").toString(), students.get(i).get("FIRSTNAME").toString(),
							students.get(i).get("LASTNAME").toString(),
							"<a href=\"Edit?username=" + (students.get(i).get("USERNAME").toString())
									+ "\"><span class=\"glyphicon glyphicon-pencil\"></span></a>",
							"<a href=\"Delete?username=" + students.get(i).get("USERNAME").toString()
									+ "\"><span class=\"glyphicon glyphicon-remove\"></span></a>");

				}
				data += "</table>";
			}
		} catch (Exception e) {
			throw e;
		}
		if (data == " ") {
			data = new Display(Display.Type.INFO).getHtml("No Students added.");
		}
		return data;
	}

	public boolean add_user(String username, String password, String role, String firstname, String lastname)
			throws Exception {
		username = username.toUpperCase().trim();
		password = password.trim();
		role = role.toUpperCase().trim();
		firstname = firstname.toUpperCase().trim();
		lastname = lastname.toUpperCase().trim();
		String query = String.format(Query.INSERT_INTO_USER.toString(), username, password, role);
		if (database.executeDML(query, 1)) {
			// correctly added user into user table
			query = String.format(Query.INSERT_INTO_ROLE.toString(), role, username, firstname, lastname);
			if (database.executeDML(query, 1)) {
				return true;
			} else {
				database.execute(String.format(Query.DELETE_USER.toString(), username));
				return false;
			}
		} else {
			throw new Exception("Failed to add user to the users.");
		}
	}

	public boolean modify_user(String role, String username, String firstname, String lastname, String password)
			throws Exception {
		String query = String.format(Query.MODIFY_USER.toString(), role, firstname.toUpperCase().trim(),
				lastname.toUpperCase().trim(), username);
		boolean data = database.executeDML(query, 1);
		if (data) {
			query = String.format(Query.CHANGE_PASSWORD.toString(), password, username);
			data = database.executeDML(query, 1);

		} else {
			return false;
		}
		return data;
	}

	public boolean delete_user(String username) throws Exception {
		String query = String.format(Query.DELETE_USER.toString(), username);
		boolean data = database.executeDML(query, 1);

		return data;
	}

	public boolean change_name(String fname, String lname, String username, String role) throws Exception {
		String query = String.format(Query.MODIFY_USER.toString(), role, fname, lname, username);
		return database.executeDML(query, 1);
	}

	public boolean change_password(String password, String username) throws Exception {
		String query = String.format(Query.CHANGE_PASSWORD.toString(), password, username);
		return database.executeDML(query, 1);
	}

	public String get_all_courses(String username) throws Exception {
		String uname = username.toUpperCase().trim();
		String data = "";
		try {
			String query = String.format(Query.GET_COURSES.toString(), uname);
			ArrayList<HashMap<String, String>> courses = database.execute(query);
			if (courses.size() != 0) {
				data += "<div class=\"form-group\"><label class=\"label label-primary\" for=\"course\">Select Course</label>";
				data += "<select id=\"course\" name=\"course\" class=\"form-control animated bounceInRight\" required=\"required\">";
				for (int i = 0; i < courses.size(); i++) {
					data += "<option value=" + courses.get(i).get("COURSE_CODE") + ">"
							+ courses.get(i).get("COURSE_CODE") + "</option>";
				}
				data += "</select> </div>";
			}
		} catch (Exception e) {
			throw e;
		}
		if (data == " ") {
			data = new Display(Display.Type.INFO).getHtml("You have not any course.");
		}
		return data;
	}

	
	public String get_all_rooms() throws Exception {
		String data = "";
		try {
			String query = String.format(Query.GET_ROOMS.toString());
			ArrayList<HashMap<String, String>> rooms = database.execute(query);
			if (rooms.size() != 0) {
				data += "<div class=\"form-group\"><label class=\"label label-primary\" for=\"course\">Select Room</label>";
				data += "<select id=\"room\" name=\"room_no\" class=\"form-control animated bounceInRight\" required=\"required\">";
				for (int i = 0; i < rooms.size(); i++) {
					data += "<option value=" + rooms.get(i).get("ROOM_NO") + ">" + rooms.get(i).get("ROOM_NO") + " - "
							+ rooms.get(i).get("TYPE") + "</option>";
				}
				data += "</select> </div>";
			}
		} catch (Exception e) {
			throw e;
		}
		if (data == " ") {
			data = new Display(Display.Type.ERROR).getHtml("There is no room.");
		}
		return data;
	}

	public String get_rooms() throws Exception {
		String data = "";
		try {
			String query = String.format(Query.GET_ROOMS.toString());
			ArrayList<HashMap<String, String>> rooms = database.execute(query);
			if (rooms.size() != 0) {
				data = "<table class=\"table table-striped table-bordered table-hover table-responsive \">"
						+ "<tr> <th>ROOM NO</th> <th>TYPE</th><th>EDIT</th><th>DELETE</th> </tr>";

				for (int i = 0; i < rooms.size(); i++) {
					data += String.format("<tr> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> </tr>",
							rooms.get(i).get("ROOM_NO").toString(), 
							rooms.get(i).get("TYPE").toString(),
							"<a href=\"edit_room.jsp?room_no=" + (rooms.get(i).get("ROOM_NO").toString())
							+ "\"><span class=\"glyphicon glyphicon-pencil\"></span></a>",
							"<a href=\"DeleteRoom?room_no=" + rooms.get(i).get("ROOM_NO").toString()
							+ "\"><span class=\"glyphicon glyphicon-remove\"></span></a>");
				
				}
				data += "</table>";
			}
		} catch (Exception e) {
			throw e;
		}
		if (data == " ") {
			data = new Display(Display.Type.ERROR).getHtml("There is no room.");
		}
		return data;
	}

	public boolean schedule_exam(String course, String room, String date, String start_time, String end_time) {
		boolean result = false;
	
		ArrayList<HashMap<String, String>> scheduled_exams = new ArrayList<HashMap<String, String>>();
		String query = String.format(Query.GET_SCHEDULED_EXAM.toString(), room, date);

		if (validTime(start_time, end_time) && validDate(date)) {
			try {
				scheduled_exams = database.execute(query);

				if (scheduled_exams.size() == 0) {

					query = String.format(Query.SCHEDULE_EXAM.toString(), course, room, date, start_time, end_time);
					result = database.executeDML(query, 1);

				} 
				else {

					for (int i = 0; i < scheduled_exams.size(); i++) {
						if (i == scheduled_exams.size() - 1 && validTimeForExam(scheduled_exams.get(i).get("END_TIME").toString(),start_time)) {
							query = String.format(Query.SCHEDULE_EXAM.toString(), course, room, date, start_time,
									end_time);
							result = database.executeDML(query, 1);
						} else if (validTimeForExam(scheduled_exams.get(i).get("END_TIME").toString(),start_time)
								&& validTimeForExam(end_time , scheduled_exams.get(i + 1).get("START_TIME").toString())) {
							query = String.format(Query.SCHEDULE_EXAM.toString(), course, room, date, start_time,
									end_time);
							result = database.executeDML(query, 1);

						}
					}
				}
				return result;
			} catch (Exception e) {

			}
		} else {
			result = false;
		}
		return result;
	}


	public boolean validDate(String date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		// current date
		int mYear = calendar.get(Calendar.YEAR);
		int mMonth = calendar.get(Calendar.MONTH) + 1;
		int mDay = calendar.get(Calendar.DAY_OF_MONTH);
		// user provided date
		int year = Integer.parseInt(date.split("-")[0]);
		int month = Integer.parseInt(date.split("-")[1]);
		int day = Integer.parseInt(date.split("-")[2]);

		if (mYear == year) {
			if (mMonth < month) {
				return true;
			} else if (mMonth == month) {
				// check day
				if (mDay < day) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else if (mYear < year) {
			return true;
		} else {
			return false;
		}
	}

	public  boolean validTime(String start_time, String end_time) {
		// start time
		int start_hours = Integer.parseInt(start_time.split(":")[0]);
		int start_minutes = Integer.parseInt(start_time.split(":")[1]);
		// end time
		int end_hours = Integer.parseInt(end_time.split(":")[0]);
		int end_minutes = Integer.parseInt(end_time.split(":")[1]);

		if (start_hours < 0 || start_hours > 23 || end_hours < 0 || end_hours > 23 || start_minutes < 0
				|| end_minutes < 0 || start_minutes > 59 || end_minutes > 59) {
			return false;
		}

		if (start_hours < end_hours) {
			return true;
		} else if (start_hours == end_hours) {
			return start_minutes < end_minutes;
		} else {
			return false;
		}
	}

	
	public  boolean validTimeForExam(String start_time, String end_time) {
		// start time
		int start_hours = Integer.parseInt(start_time.split(":")[0]);
		int start_minutes = Integer.parseInt(start_time.split(":")[1]);
		// end time
		int end_hours = Integer.parseInt(end_time.split(":")[0]);
		int end_minutes = Integer.parseInt(end_time.split(":")[1]);

		if (start_hours < 0 || start_hours > 23 || end_hours < 0 || end_hours > 23 || start_minutes < 0
				|| end_minutes < 0 || start_minutes > 59 || end_minutes > 59) {
			return false;
		}

		if (start_hours < end_hours) {
			return true;
		} else if (start_hours == end_hours) {
			return start_minutes <= end_minutes;
		} else {
			return false;
		}
	}
	
	public boolean add_room(String room_no , String type ) {
		boolean success = false;
		String query = String.format(Query.ADD_ROOM.toString(), room_no.toUpperCase().trim(), type.trim().toUpperCase());
		
		try{
			success = database.executeDML(query, 1);
		} catch(Exception e) {
			
		}
		
		return success;
	}
	
	public boolean delte_room(String room_no ) {
		boolean success = false;
		String query = String.format(Query.DELETE_ROOM.toString(), room_no);
		
		try{
			success = database.executeDML(query, 1);
		} catch(Exception e) {
			
		}
		
		return success;
	}
	
	public boolean edit_room(String room_no , String type ) {
		boolean success = false;
		String query = String.format(Query.EDIT_ROOM.toString(), type.trim().toUpperCase() ,room_no.toUpperCase().trim());
		
		try{
			success = database.executeDML(query, 1);
		} catch(Exception e) {
			
		}
		
		return success;
	}
}