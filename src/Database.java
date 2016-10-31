import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
	protected final String conn_string;
	/**
	 * This constructor constructs an object that establishes a connection to database and processes the request!
	 * @param host
	 * @param port
	 * @param database
	 * @param username
	 * @param password
	 */
	public Database(String host, int port, String database, String username, String password)
	{
		StringBuilder sb = new StringBuilder();
        sb.append("jdbc:mysql://").append(host.trim()).append(":").append(port).append("/").append(database.trim()).append("?user=").append(username.trim()).append("&password=").append(password.trim());
        conn_string = sb.toString();
	}
	/**
	 * This Method executes any standard read query and provides the result as an arrayList<HashMap<String,String>>
	 * @param query
	 * @return ArrayList Object
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> execute(String query) throws Exception
	{
		Connection connection = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(this.conn_string);
			ResultSet rs = connection.createStatement().executeQuery(query);
			ResultSetMetaData meta = rs.getMetaData();
			int col_count = meta.getColumnCount();
            ArrayList<String> column_names = new ArrayList<>();
            for (int i = 0; i < col_count ; i++) {
                column_names.add(meta.getColumnName((i+1)));
            }
            ArrayList <HashMap<String, String>> data = new ArrayList<>();
            while(rs.next())
            {
                HashMap<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < col_count; i++) {
                    map.put(column_names.get(i), rs.getString((i+1)));
                }

                data.add(map);
            }
            return data;
		}
		catch (SQLException | ClassNotFoundException ex)
		{
			throw ex;
		}
		finally
		{
			if(connection != null)
			{
				try 
				{
					connection.close();
				} catch (SQLException e) {
					
				}
			}
		}
	}
	/**
	 * This Method execute DML commands to manipulate the data. It takes 2 parameters one is the command that we need to process
	 * and second one is the number of expected changes.  
	 * @param query
	 * @param expected_change_number
	 * @return Boolean
	 * @throws Exception
	 */
	public boolean executeDML(String query, int expected_change_number) throws Exception
    {
        Connection con = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.conn_string);
            int i = con.prepareStatement(query).executeUpdate();
            return i == expected_change_number;
        }
        catch(ClassNotFoundException | SQLException ex)
        {
        	throw ex;
        }
        finally
        {
            if(con != null)
            {
                con.close();
            }
        }
    }
	
	
}
