import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class Logic {
	
	protected Database database;
	
	/**
	 * Logic Class Constructor
	 */
	public Logic()
	{
		database = new Database("anirudh.ddns.net", 3306, "project_humber", "nirav", "nish8099");
	}
	/**
	 * This Method returns authentication result. It requires username and passowrd and tells if the user exists or not
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean authenticate(String username, String password) throws Exception
	{
		String uname = username.trim().toUpperCase();
		String pass = password.trim();
		
		try
		{
			String query = String.format(Query.AUTHENTICATE.toString(), uname, pass);
			
			ArrayList<HashMap<String,String>> data = database.execute(query);
			if(data.isEmpty())
			{
				throw new Exception("No username password combination found!");
			}
			else if(data.size() == 1)
			{
					return true;
			}
		}
		catch(Exception ex)
		{
			throw ex;
		}
		return false;
	}
	
	public HashMap<String,String> get_info(String username) throws Exception 
	{
		
		HashMap<String,String> data =new HashMap<>();
		
		String query = String.format(Query.SELECT_ALL_FROM.toString(), "USER",username.trim().toUpperCase());
		
		ArrayList<HashMap<String,String>> user = database.execute(query);
		
		query = String.format(Query.SELECT_ALL_FROM.toString(),user.get(0).get("ROLE").toUpperCase().trim(),username.trim().toUpperCase() );
		
		user.addAll(database.execute(query));
		
		if(user.isEmpty())
		{
			throw new Exception("No user found with that username!");
		}
		else 
		{
			data.put("ROLE", user.get(0).get("ROLE").toString());
			data.put("USERNAME", user.get(0).get("USERNAME").toString());
			data.put("PASSWORD", user.get(0).get("PASSWORD").toString());
			data.put("FIRSTNAME", user.get(1).get("FIRSTNAME").toString());
			data.put("LASTNAME", user.get(1).get("LASTNAME").toString());
			return data;			
		}
		
	}
	
	protected String get_student_exams(String username) throws Exception
	{
		String user = username.trim().toUpperCase();
		String query = String.format(Query.SELECT_SCHEDULED_EXAMS.toString(), user);
		ArrayList<HashMap<String,String>> data = database.execute(query);
		if(data.isEmpty())
		{
			return new Display(Display.Type.INFO).getHtml("No Exams Scheduled for Student Yet!");
		}
		else
		{
			StringBuilder sb = new StringBuilder();
			for(int i = 0 ; i < data.size() ; i++)
			{
				sb.append(String.format(
						"<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
						data.get(i).get("COURSE_CODE").toString(),
						data.get(i).get("ROOM_NO").toString(),
						data.get(i).get("EXAM_DATE").toString(),
						data.get(i).get("START_TIME").toString(),
						data.get(i).get("END_TIME").toString()
						));
			}
			return sb.toString();
		}
	}
	
	public void getStudentExam(HttpSession session)
	{
		try
		{
			session.setAttribute("result", new Logic().get_student_exams(session.getAttribute("username").toString()));
		}
		catch(Exception ex)
		{
			session.setAttribute("result", new Display(Display.Type.ERROR).getHtml(ex.getMessage()));
		}
	}
	
}