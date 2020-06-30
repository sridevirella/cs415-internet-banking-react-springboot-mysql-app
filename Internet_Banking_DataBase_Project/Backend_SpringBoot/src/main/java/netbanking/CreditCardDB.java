package netbanking;

import java.sql.*;
import java.text.ParseException;
import java.util.Random;

public class CreditCardDB {
	 
	MySqlConnection myDBConnection = new MySqlConnection();
    
	public CreditCardDB() {};
	
	public void CCDetails() throws SQLException, ParseException
	{
		System.out.println("inside insert CCDetails method");
	 try
	  {
		Connection myConnection = myDBConnection.createConnection();
		myConnection.setAutoCommit(false);
		
		PreparedStatement ps = myConnection.prepareStatement("INSERT INTO CREDIT_CARD(credit_card_no,customer_id,issue_date,expiration_date,credit_limit,outstanding_amt,name_on_card)"+"VALUES(?,?,?,?,?,?,?)");
		Statement stmt = myConnection.createStatement();
		
		Random rand = new Random();
		int cc_min = 22222222, cc_max = 99999999;
		int v_credit_card_no = rand.nextInt((cc_max - cc_min) + 1) + cc_min;
		int[] v_customer_id = new int[301];
		String[] v_customer_name = new String[301];
		
		String[] v_issue_date =   { "2019-01-12","2018-02-15","2016-10-19","2016-07-17","2018-01-12",
                                     "2017-04-12","2016-02-19","2014-03-21","2014-12-12","2011-11-08" 
                                   };
        String[] v_expiration_date = { "2024-01-12","2023-02-15","2021-10-19","2021-07-17","2023-01-12",
                                       "2022-04-12","2020-02-19","2019-03-21","2019-12-12","2016-11-08" 
                                     };
		int cc_limit_min = 5000, cc_limit_max = 25000;
		
		int i = 0, j = 0;
        int count = 1;
        int batchSize = 199;
        
        ResultSet rs = stmt.executeQuery("select customer_id, customer_name from customer");
		rs.beforeFirst();
        while(rs.next() && (i < 202) ) {
			v_customer_id[i] = rs.getInt(1);
			v_customer_name[i] = rs.getString(2);
			i++;
		}
        
     
        while ( j < 200 ) {
        	
		ps.setInt(1, v_credit_card_no++);  
		ps.setInt(2, v_customer_id[j] );
		
		int v_issue_date1 = rand.nextInt(v_issue_date.length);
		ps.setString(3, v_issue_date[v_issue_date1]);
		ps.setString(4, v_expiration_date[v_issue_date1]);
		
		int v_cc_limit = rand.nextInt((cc_limit_max - cc_limit_min) + 1) + cc_limit_min;
		ps.setInt(5, v_cc_limit);
		ps.setInt(6, v_cc_limit-500);
		ps.setString(7, v_customer_name[j]);
		
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
