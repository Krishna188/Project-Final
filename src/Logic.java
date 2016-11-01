import java.util.ArrayList;
import java.util.HashMap;

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
				throw new Exception("No user found with that username!");
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
	
	public HashMap<String,String> get_info(String username) throws Exception {
		
		HashMap<String,String> data =new HashMap<>();
		
		String query = String.format(Query.SELECT_ALL_FROM.toString(), "USER",username.trim().toUpperCase());
		
		ArrayList<HashMap<String,String>> user = database.execute(query);
		
		query = String.format(Query.SELECT_ALL_FROM.toString(),user.get(0).get("ROLE").toUpperCase().trim(),username.trim().toUpperCase() );
		
		user.addAll(database.execute(query));
		
		if(user.isEmpty())
		{
			throw new Exception("No user found with that username!");
		}
		else {
			data.put("ROLE", user.get(0).get("ROLE").toString());
			data.put("USERNAME", user.get(0).get("USERNAME").toString());
			data.put("PASSWORD", user.get(0).get("PASSWORD").toString());
			data.put("FIRSTNAME", user.get(1).get("FIRSTNAME").toString());
			data.put("LASTNAME", user.get(1).get("LASTNAME").toString());
			return data;
			
		}
		
	}
	
	public String get_teacher_list() throws Exception {

		String data = " ";
		try {
			String query = String.format(Query.SELECT_INFO_OF_TEACHERS.toString());
			ArrayList<HashMap<String,String>> teachers = database.execute(query);
			if(teachers.size() != 0)
			{
				data = "<table class=\"table table-striped table-bordered table-hover table-responsive \">"
						+ "<tr> <th>USERNAME</th> <th>FIRST NAME</th> <th>LAST NAME</th> </tr>";
			
				for(int i=0 ; i < teachers.size(); i++)
				{
					data += String.format("<tr> <td>%s</td> <td>%s</td> <td>%s</td> </tr>",
							teachers.get(i).get("USERNAME").toString(),teachers.get(i).get("FIRSTNAME").toString(),
							teachers.get(i).get("LASTNAME").toString());
					
				}
				data += "</table>";
			}
		} 
		catch (Exception e) {
			throw e;
		}
		if(data == " ")
		{
			data = new Display(Display.Type.INFO).getHtml("No Teachers added.");
		}
		return data;
	}
	
	public String get_student_list() throws Exception
	{
		
		String data = " ";
		try {
			String query = String.format(Query.SELECT_INFO_OF_STUDENTS.toString());
			ArrayList<HashMap<String,String>> students = database.execute(query);
			if(students.size() != 0)
			{
				data = "<table class=\"table table-striped table-bordered table-hover table-responsive \">"
						+ "<tr> <th>USERNAME</th> <th>FIRST NAME</th> <th>LAST NAME</th><th colspan=\"2\">Actions</th> </tr>";
				for(int i=0 ; i < students.size(); i++)
				{
					data += String.format("<tr> <td>%s</td> <td>%s</td> <td>%s</td><td>%s %s</td> </tr>",
							students.get(i).get("USERNAME").toString(),students.get(i).get("FIRSTNAME").toString(),
							students.get(i).get("LASTNAME").toString(),
							"<a href=\"Delete?username="+students.get(i).get("USERNAME").toString()+"\">Delete</a>",
							"<a href=\"Edit?username="+(students.get(i).get("USERNAME").toString())+"\">Edit</a>")
							;
					
				}
				data += "</table>";
			}
		} 
		catch (Exception e) {
			throw e;
		}
		if(data == " ")
		{
			data = new Display(Display.Type.INFO).getHtml("No Students added.");
		}
		return data;
	}
}