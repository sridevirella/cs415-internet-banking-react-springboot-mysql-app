package netbanking;

import java.sql.*;
import java.text.ParseException;
import java.util.Random;

public class LoanPaymentDB {

MySqlConnection myDBConnection = new MySqlConnection();
    
	public LoanPaymentDB() {};
	
	public void loanPaymentDetails() throws SQLException, ParseException
	{
		System.out.println("inside insert loanPaymentDetails method");
	 try
	  {
		Connection myConnection = myDBConnection.createConnection();
		myConnection.setAutoCommit(false);
		
		PreparedStatement ps = myConnection.prepareStatement("INSERT INTO LOAN_PAYMENT(loan_payment_id,loan_id,from_account,loan_payment_amt,payment_date)"+"VALUES(?,?,?,?,?)");
		Statement stmt = myConnection.createStatement();
		Statement stmt1 = myConnection.createStatement();
		Statement stmt2 = myConnection.createStatement();
		
		Random rand = new Random();
		int lP_min = 4444, lp_max = 8888;
		int v_loan_payment_id = rand.nextInt((lp_max - lP_min) + 1) + lP_min;
		String[] v_loan_id = new String[301];
		int[] v_from_account = new int[301];
		int amt_min = 2000, amt_max = 100000;
		String[] v_payment_date =   { "2019-01-12","2018-02-15","2016-10-19","2016-07-17","2018-01-12",
                                      "2017-04-12","2016-02-19","2014-03-21","2014-12-12","2011-11-08" 
	                             	};
                                  
		
		int i = 0, j = 0, k = 0;
        int count = 1;
        int batchSize = 199;
        
        ResultSet rs = stmt.executeQuery("select credit_card_no from CREDIT_CARD");
		rs.beforeFirst();
        while(rs.next() && (i < 101) ) {
        	v_from_account[i++] = rs.getInt(1);
		}
        
 
        ResultSet rs1 = stmt1.executeQuery("select account_no from account");
        
        while(rs1.next() && (i < 201) ) {
        	v_from_account[i++] = rs1.getInt(1);
		}
        
        ResultSet rs2 = stmt2.executeQuery("select loan_id from loan");
        
        while(rs2.next() && (k < 201 ) ) {
        	v_loan_id[k++] = rs2.getString(1);
		}
        
     
        while ( j < 200 ) {
        	
		ps.setInt(1, v_loan_payment_id++);  
		ps.setString(2, v_loan_id[j] );	
		ps.setInt(3, v_from_account[j]);
		
		ps.setInt(4, rand.nextInt((amt_max - amt_min) + 1) + amt_min);
		ps.setString(5, v_payment_date[rand.nextInt(v_payment_date.length)]);
		
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
