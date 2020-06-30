package netbanking;

import java.sql.*;
import java.text.ParseException;
import java.util.Random;

public class LoanDB {

MySqlConnection myDBConnection = new MySqlConnection();
    
	public LoanDB() {};
	
	public void loanDetails() throws SQLException, ParseException
	{
		System.out.println("inside insert loanDetails method");
	 try
	  {
		Connection myConnection = myDBConnection.createConnection();
		myConnection.setAutoCommit(false);
		
		PreparedStatement ps = myConnection.prepareStatement("INSERT INTO LOAN(loan_id,customer_id,loan_open_date,loan_duration,interest_rate,loan_status,loan_amount,outstanding_amt)"+"VALUES(?,?,?,?,?,?,?,?)");
		Statement stmt = myConnection.createStatement();
		
		Random rand = new Random();
		int lid_min = 22222, lid_max = 99999;
		int v_lid = rand.nextInt((lid_max - lid_min) + 1) + lid_min;
		int[] v_customer_id = new int[301];
		
		String[] v_loan_open_date =   { "2019-01-12","2018-02-15","2016-10-19","2016-07-17","2018-01-12",
                                        "2017-04-12","2016-02-19","2014-03-21","2014-12-12","2011-11-08" 
                                      };
        int[] v_loan_duration = { 12, 24, 36, 48, 60 };
        double v_interest_rate = 8.5;
        String[] v_loan_status = {"O","C"};                             
		int amt_min = 2000, amt_max = 100000;
		
		
		int i = 0, j = 0;
        int count = 1;
        int batchSize = 199;
        
        ResultSet rs = stmt.executeQuery("select customer_id from customer");
		rs.beforeFirst();
        while(rs.next() && (i < 202) ) {
			v_customer_id[i++] = rs.getInt(1);
		}
        
     
        while ( j < 200 ) {
        	
        	v_lid++;
        	String v_loan_id = "LN"+v_lid;
        	
		ps.setString(1, v_loan_id);  
		ps.setInt(2, v_customer_id[j] );	
		ps.setString(3, v_loan_open_date[rand.nextInt(v_loan_open_date.length)]);
		int v_loan_duration1 = rand.nextInt(v_loan_duration.length);
		ps.setInt(4, v_loan_duration[v_loan_duration1]);
		ps.setDouble(5, v_interest_rate);
		ps.setString(6, v_loan_status[rand.nextInt(v_loan_status.length)]);
		
		int v_loan_amount = rand.nextInt((amt_max - amt_min) + 1) + amt_min;
		ps.setInt(7, v_loan_amount);
		ps.setDouble(8,  v_loan_amount + ( (v_loan_amount*(v_interest_rate/100)/ 12 )*v_loan_duration[v_loan_duration1] ) );
		
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
