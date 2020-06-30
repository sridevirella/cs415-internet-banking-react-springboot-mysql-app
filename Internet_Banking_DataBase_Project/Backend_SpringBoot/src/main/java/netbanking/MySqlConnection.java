package netbanking;

import java.sql.*;



public class MySqlConnection {

	Connection con = null;

	public  Connection createConnection () throws SQLException {
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver"); 

		   con = DriverManager.getConnection(  
					         "jdbc:mysql://localhost:3306/mydb","root","Sri@1234");  
			
			System.out.println("MYSQL Connection established");


		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
		
		return con;
  }
	public MySqlConnection() {} // default constructor
	
}
