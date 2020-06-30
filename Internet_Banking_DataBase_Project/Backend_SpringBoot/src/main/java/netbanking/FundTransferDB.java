package netbanking;

import java.sql.*;
import java.text.*;
import java.util.*;

public class FundTransferDB {

MySqlConnection myDBConnection = new MySqlConnection();
	
	Random rand = new Random();
	int min = 111, max = 999;
	int v_transaction_id = rand.nextInt((max - min) + 1) + min;
	String[] v_trans_description = {"normal pay","personnel reason","chiti money","do festival"};
	int trn_min = 100, trn_max = 10000;
	String[] v_transaction_date = {"2019-01-12","2019-02-15","2019-10-19","2019-07-17","2019-01-12",
                                   "2019-04-12","2019-02-19","2019-03-21","2019-12-01","2019-11-08",
                                   "2019-05-24","2017-02-15","2019-04-12","2019-07-07","2019-09-16" 
                                  };
	String[] v_ben_name = new String[200];
	String[] v_ben_email = new  String[200];
	int[] v_from_account_no = new int[156];
	                     
	
	public FundTransferDB() {};
	
	public void insertBenficiary() throws SQLException, ParseException
	{
	 try
	  {
		Connection myConnection = myDBConnection.createConnection();
		myConnection.setAutoCommit(false);
		
		PreparedStatement ps = myConnection.prepareStatement("INSERT INTO FUND_TRANSFER(transaction_id,trans_description,trans_amt,trans_date,ben_name,ben_email,from_account_no )"+"VALUES(?,?,?,?,?,?,?)");
		Statement stmt = myConnection.createStatement();
		
		
        int count = 1, i = 0, j = 0;
        int batchSize = 149;
        
        ResultSet rs = stmt.executeQuery("select account_no from BENFICIARY");
       
        
        rs.beforeFirst();
        while(rs.next() ) {
        	if( i < 150 ) {
        		v_from_account_no[i++] = rs.getInt(1);
        	}
		}
        i = 0;
        Statement stmt1 = myConnection.createStatement();
        ResultSet rst = stmt1.executeQuery("select ben_name from BENFICIARY");
        rst.beforeFirst();
        while( rst.next() ) {
        	if( i < 150 ) {
        		System.out.println("rst.getString(1) ::"+rst.getString(1));
			v_ben_name[i++] = rst.getString(1);
        	}
		}
        
        i = 0;
        Statement stmt2 = myConnection.createStatement();
        ResultSet rst1 = stmt2.executeQuery("select ben_email from BENFICIARY"); 
        rst1.beforeFirst();
        while( rst1.next() ) {
        	if( i < 150 ) {
        		System.out.println("rst1.getString(1) ::"+rst1.getString(1));
  			v_ben_email[i++] = rst1.getString(1);
        	}
		}
        
        
        
        while ( j < 150 ) {
        	v_transaction_id++;
            String v_transaction_id1 = "FT"+ v_transaction_id;	
        
		ps.setString(1, v_transaction_id1);                    
		ps.setString(2, v_trans_description[rand.nextInt(v_trans_description.length)]);
		ps.setInt(3, rand.nextInt((trn_max - trn_min) + 1) + trn_min);
		ps.setString(4, v_transaction_date[rand.nextInt(v_transaction_date.length)]);
		ps.setString(5, v_ben_name[j]);
		ps.setString(6, v_ben_email[j]);
		ps.setInt(7, v_from_account_no[j]);
		
		System.out.println("v_ben_name::v_ben_email::"+v_ben_name[j]+"::"+v_ben_email[j]);
		ps.addBatch();
		j++;
		
		
        if (count % batchSize == 0 ) {
          ps.executeBatch();
         }
       }
        
         ps.executeBatch();
        myConnection.commit();
        myConnection.close();
        
	}  catch (SQLException ex) {
       ex.printStackTrace();
   }catch (NullPointerException npe) {
       npe.printStackTrace();
   }
	 
 }
}
