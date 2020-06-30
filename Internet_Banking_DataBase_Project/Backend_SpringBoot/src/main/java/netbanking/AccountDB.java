package netbanking;

import java.sql.*;
import java.text.ParseException;
import java.util.Random;

public class AccountDB {
	
    MySqlConnection myDBConnection = new MySqlConnection();
	
	                     
	public AccountDB() {};
	
	public void insertAccount() throws SQLException, ParseException
	{
		System.out.println("inside insert account method");
	 try
	  {
		Connection myConnection = myDBConnection.createConnection();
		myConnection.setAutoCommit(false);
		
		Random rand = new Random();
		int min = 255555555, max = 777777777;
		int v_account_no = rand.nextInt((max - min) + 1) + min ;
		String[] v_account_type = {"S","C"};
		int bal_min = 1000, bal_max = 100000;
		int v_available_balance = rand.nextInt((bal_max - bal_min) + 1) + bal_min ;
		String[] v_acc_open_date = {"2000-01-12","2008-02-15","2008-10-19","2009-07-17","2010-01-12",
		                            "2015-04-12","2014-02-19","2013-03-21","2012-12-12","2011-11-08",
		                            "2016-05-24","2017-02-15","2019-04-12","2019-07-07","2018-09-16",
		                            "2018-01-12","2018-06-15","2017-07-22","2016-01-12","2017-08-16" 
		                           };
		String[] v_acc_status = {"A","C"};
		int[] branch_code = {111,112,113,114,115,116,117,118,119,200};
		int[] v_customer_id = new int[301];
		
		PreparedStatement ps = myConnection.prepareStatement("INSERT INTO ACCOUNT(account_no,account_type,available_balance,acc_open_date,acc_status,branch_code,customer_id)"+"VALUES(?,?,?,?,?,?,?)");
		Statement stmt = myConnection.createStatement();
		
		int j = 0, i = 0 ;
        int count = 1;
        int batchSize = 299;
        
        ResultSet rs = stmt.executeQuery("select * from customer ");
		rs.beforeFirst();
        while(rs.next() && (i < 300) ) {
			v_customer_id[i++] = rs.getInt(1);
		}
        
        while ( j < 300 ) {
		ps.setInt(1, v_account_no++);                    
		ps.setString(2, v_account_type[rand.nextInt(v_account_type.length)]);
		ps.setInt(3, v_available_balance++);
		ps.setString(4, v_acc_open_date[rand.nextInt(v_acc_open_date.length)]);
		ps.setString(5, v_acc_status[rand.nextInt(v_acc_status.length)]);
		ps.setInt(6, branch_code[rand.nextInt(branch_code.length)]);
		ps.setInt(7, v_customer_id[j]);
		
		ps.addBatch();
		j++;
		
        if (count % batchSize == 0 ) {
        	ps.executeBatch();
        }
       }
        
        ps.executeBatch();
        myConnection.commit();
        myConnection.close();
        
	}catch (SQLException ex) {
       ex.printStackTrace();
   }catch (NullPointerException npe) {
       npe.printStackTrace();
   }
	 
 }

}
