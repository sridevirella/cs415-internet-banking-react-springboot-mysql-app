package netbanking;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;

public class BenficiaryDB {
MySqlConnection myDBConnection = new MySqlConnection();
	
	String filePath = "E:\\FALL-2019\\CS-415\\Final Project\\step3\\Internet_Banking\\CSV_files\\benficiary.csv";
	
	Random rand = new Random();
	int phone_min = 211111111, phone_max = 555555555;
	int ben_ac_min = 21356789, ben_ac_max = 41356789;
	int routing_min = 1100, routing_max = 3300;
	int[] v_account_no = new int[156];
	                     
	
	public BenficiaryDB() {};
	
	public void insertBenficiary() throws SQLException, ParseException
	{
	 try
	  {
		Connection myConnection = myDBConnection.createConnection();
		myConnection.setAutoCommit(false);
		
		PreparedStatement ps = myConnection.prepareStatement("INSERT INTO BENFICIARY(ben_name,ben_email,ben_phone_number,ben_account_no,routing_no,account_no )"+"VALUES(?,?,?,?,?,?)");
		Statement stmt = myConnection.createStatement();
		
		BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
        String lineText = null;

        int count = 1, i = 0, j = 0;
        int batchSize = 150;
        lineReader.readLine();
        
        ResultSet rs = stmt.executeQuery("select account_no from account");
		rs.beforeFirst();
        while(rs.next()) {
        	if( i < 155 ) {
			v_account_no[i++] = rs.getInt(1);
        	}
		}
        
        while ((lineText = lineReader.readLine()) != null) {
        	
            String[] data = lineText.split(",");
            
            String v_ben_name = data[0];
            String v_ben_email = data[1];
           
            System.out.println("v_ben_name::v_ben_email::v_account_no");
            System.out.println(v_ben_name+"::"+v_ben_email+"::"+v_account_no[j]);
            
		ps.setString(1, v_ben_name);                    
		ps.setString(2, v_ben_email);
		ps.setInt(3, rand.nextInt((phone_max - phone_min) + 1) + phone_min);
		ps.setInt(4, rand.nextInt((ben_ac_max - ben_ac_min) + 1) + ben_ac_min);
		ps.setInt(5, rand.nextInt((routing_max - routing_min) + 1) + routing_min);
		ps.setInt(6, v_account_no[j++]);
		
		System.out.println("count::"+count);
		ps.addBatch();
		
		
        if (count % batchSize == 0 ) {
        	ps.executeBatch();
        }
       }
        
        lineReader.close();
        ps.executeBatch();
        myConnection.commit();
        myConnection.close();
        
	} catch (IOException e) {
       System.err.println(e);
   } catch (SQLException ex) {
       ex.printStackTrace();
   }catch (NullPointerException npe) {
       npe.printStackTrace();
   }
	 
 }

}
