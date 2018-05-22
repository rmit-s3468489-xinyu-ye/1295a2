package mininet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
/**
 * 
 * @author Yifan ZHANG s3615625
 *
 */
public class DBConnect 
{
	private Connection connection = null;

	/**
	 * Connect to the embedded SQLite database
	 * 
	 * @throws Exception
	 */
	public void connect() throws Exception
	{	
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:TestDB.db");	
	}
	
	/**
	 * Read the data of persons from the embedded database
	 * into the MiniNet
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List readPeopleFromDB() throws SQLException
	{
		List<Person> result = new ArrayList<Person>();
		
		Statement stmt = connection.createStatement(); 
		
		ResultSet rs = null;
		
		rs = stmt.executeQuery("select name,photo,status,gender,age,state from people;");
		
		while(rs.next())
		{
			if(rs.getInt("age") > FileOperation.AGE)
			{

				result.add(new Adult(rs.getString("name"), rs.getString("photo"), rs.getString("status"),
						rs.getString("gender").charAt(0), rs.getInt("age"), rs.getString("state")));                 
			}
			else
			{
				result.add(new Dependent(rs.getString("name"), rs.getString("photo"), rs.getString("status"),
						rs.getString("gender").charAt(0), rs.getInt("age"), rs.getString("state")));
			}
		}
		stmt.close(); 
		
		return result;
	}
	
	/**
	 * Write the data of persons into
	 * the embedded database
	 * 
	 * @param person
	 * @throws Exception
	 */
	public void writePeopleToDB(List<Person> person) throws Exception
	{
		try 
		{
			Statement stmt = connection.createStatement(); 

			stmt.executeUpdate("DROP TABLE IF EXISTS people"); 
			stmt.executeUpdate("create table people (name varchar(100) not null, photo varchar(100), status varchar(100)"
					+ ", gender varchar(1), age integer,state varchar(20));");

			String sql = "";

			for(int i = 0;i < person.size();i++)
			{
				Person p = (Person)person.get(i);
				sql = "insert into people"
						+ " values ('"+p.getName()+"', '"+p.getPhotoPath()+"', '"+p.getStatus()+"','"+p.getGender()+"',"+p.getAge()+",'"+p.getState()+"');\n";
				stmt.executeUpdate(sql);
			}
			stmt.close();  
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e); 
		}
	}
}
