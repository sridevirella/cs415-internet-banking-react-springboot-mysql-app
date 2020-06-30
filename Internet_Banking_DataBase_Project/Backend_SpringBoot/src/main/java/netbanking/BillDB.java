package netbanking;

import java.sql.*;
import java.text.ParseException;
import java.util.Random;

public class BillDB {
	
MySqlConnection myDBConnection = new MySqlConnection();
	                     
	public BillDB() {};
	
	public void billDetails() throws SQLException, ParseException
	{
		System.out.println("inside insert account method");
	 try
	  {
		Connection myConnection = myDBConnection.createConnection();
		myConnection.setAutoCommit(false);
		
		PreparedStatement ps = myConnection.prepareStatement("INSERT INTO BILL(bill_id,bill_amt,transaction_date,from_acc,description,to_acc,customer_id )"+"VALUES(?,?,?,?,?,?,?)");
		Statement stmt = myConnection.createStatement();
		Statement stmt1 = myConnection.createStatement();
		
		Random rand = new Random();
		int min = 111, max = 999;
		int v_bill = rand.nextInt((max - min) + 1) + min;
		
		int amt_min = 100, amt_max = 10000;
		String[] v_transaction_date = {"2019-01-12","2018-02-15","2016-10-19","2016-07-17","2018-01-12",
		                            "2013-04-12","2014-02-19","2013-03-21","2012-12-12","2011-11-08",
		                            "2019-05-24","2017-02-15","2019-04-12","2019-07-07","2018-09-16",
		                            "2019-01-12","2018-06-15","2019-07-22","2016-01-12","2017-08-16" 
		                           };
		int[] v_from_acc = new int[301];
		String[] v_description = {"Electricity","Internet","Gas","Water","Mobile postpaid" };
		int to_acc_min = 111, to_acc_max = 999;
		int v_to_acc = rand.nextInt((to_acc_max - to_acc_min) + 1) + to_acc_min ;
		int[] v_customer_id = new int[301];
		
		int i = 0, j = 0;
        int count = 1;
        int batchSize = 199;
        
        ResultSet rs = stmt.executeQuery("select A.account_no,C.customer_id from account AS A,customer AS C WHERE A.customer_id = C.customer_id ");
		rs.beforeFirst();
        while(rs.next() && (i < 101) ) {
			v_from_acc[i] = rs.getInt(1);
			v_customer_id[i++] = rs.getInt(2);
		}
        
        ResultSet rs1 = stmt1.executeQuery("select CC.credit_card_no,C.customer_id from CREDIT_CARD AS CC,customer AS C WHERE CC.customer_id = C.customer_id");
		rs.beforeFirst();
        while(rs1.next() && (i < 201) ) {
        	v_from_acc[i] = rs1.getInt(1);
			v_customer_id[i++] = rs1.getInt(2);
		}
        
        
        while ( j < 200 ) {
        	
        v_bill++;
        String v_bill_id = "B"+ v_bill;
        
		ps.setString(1, v_bill_id);                    
		ps.setInt(2, rand.nextInt((amt_max - amt_min) + 1) + amt_min );
		ps.setString(3, v_transaction_date[rand.nextInt(v_transaction_date.length)]);
		ps.setInt(4, v_from_acc[j]);
		ps.setString(5, v_description[rand.nextInt(v_description.length)]);
		ps.setInt(6, v_to_acc++);
		ps.setInt(7, v_customer_id[j]);
		
	//	ps.execute();
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
