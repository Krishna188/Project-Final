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
		
		HashMap<String,String> data =null;
		
		String query = String.format(Query.SELECT_ALL_FROM.toString(), "USER",username.trim().toUpperCase());
		
		ArrayList<HashMap<String,String>> user = database.execute(query);
		
		
		if(user.isEmpty())
		{
			throw new Exception("No user found with that username!");
		}
		else {
			
		}
		
		return data;
	}
	
	
}