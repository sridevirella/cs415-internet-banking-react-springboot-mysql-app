package netbanking;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;



public class CustomerDB {
	
	MySqlConnection myDBConnection = new MySqlConnection();
	
	String filePath = "E:\\FALL-2019\\CS-415\\Final Project\\step3\\Internet_Banking\\CSV_files\\customer.csv";
	
	Random rand = new Random();
	int min = 4756060, max = 9999999;
	int v_customer_id = rand.nextInt((max - min) + 1) + min ;
	String[] v_date_of_birth = {"1987-01-12","1986-02-15","1992-10-19","2005-07-17","1990-01-12",
	                            "1859-04-12","1993-02-19","1990-03-21","1989-12-12","2001-11-08",
	                            "1983-05-24","2003-02-15","1982-04-12","2000-07-07","2001-09-16",
	                            "1890-01-12","1855-06-15","1866-07-22","2000-01-12","1877-08-16" 
	                           };
	                     
	
	public CustomerDB() {};
	
	public void insertCustomer() throws SQLException, ParseException
	{
	 try
	  {
		Connection myConnection = myDBConnection.createConnection();
		myConnection.setAutoCommit(false);
		
		PreparedStatement ps = myConnection.prepareStatement("INSERT INTO CUSTOMER(customer_id,customer_name,date_of_birth,gender,email )"+"VALUES(?,?,?,?,?)");
		
		BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
        String lineText = null;

        int count = 1;
        int batchSize = 300;
        lineReader.readLine();
        
        while ((lineText = lineReader.readLine()) != null) {
        	
            String[] data = lineText.split(",");
            
            String v_customer_name = data[0];
            String v_gender = data[1];
            String v_email = data[2];
            v_customer_id++;
                        
        
		ps.setInt(1, v_customer_id);                    // v_customer_id 7 digit range
		ps.setString(2, v_customer_name);
		ps.setString(3, v_date_of_birth[rand.nextInt(v_date_of_birth.length)]);
		ps.setString(4, v_gender);
		ps.setString(5, v_email);
		
		ps.addBatch();
		
        if (count % batchSize == 0 ) {
        	ps.executeBatch();
        }
       }
        
        lineReader.close();
        ps.executeBatch();
        myConnection.commit();
        myConnection.close();
        myConnection.setAutoCommit(true);
        
	} catch (IOException e) {
       System.err.println(e);
   } catch (SQLException ex) {
       ex.printStackTrace();
   }catch (NullPointerException npe) {
       npe.printStackTrace();
   }
	 
 }
}
