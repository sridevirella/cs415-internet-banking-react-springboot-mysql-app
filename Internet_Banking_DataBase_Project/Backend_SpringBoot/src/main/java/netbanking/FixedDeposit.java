package netbanking;

import java.sql.*;
import java.text.ParseException;
import java.util.Random;

public class FixedDeposit {
 
	MySqlConnection myDBConnection = new MySqlConnection();
    
	public FixedDeposit() {};
	
	public void insertFD() throws SQLException, ParseException
	{
		System.out.println("inside insert fixedDeposit method");
	 try
	  {
		Connection myConnection = myDBConnection.createConnection();
		myConnection.setAutoCommit(false);
		
		PreparedStatement ps = myConnection.prepareStatement("INSERT INTO FIXED_DEPOSIT(fd_id,customer_id,fd_term,fd_open_date,fd_maturity_date,fd_status,fd_amt,interest_rate,total_amt,liquidate_date)"+"VALUES(?,?,?,?,?,?,?,?,?,?)");
		Statement stmt = myConnection.createStatement();
		
		Random rand = new Random();
		int fd_min = 111, fd_max = 555;
		int fd1_min = 666, fd1_max = 999;
		int v_fd = rand.nextInt((fd_max - fd_min) + 1) + fd_min;
		int v_fd1 = rand.nextInt((fd1_max - fd1_min) + 1) + fd1_min;
		int[] v_customer_id = new int[301];
		int[] v_fd_term = {6, 12, 18, 24, 30, 36, 40, 46, 50, 56 };
		
		String[] v_fd_open_date = { "2019-01-12","2018-02-15","2016-10-19","2016-07-17","2018-01-12",
		                            "2013-04-12","2014-02-19","2013-03-21","2012-12-12","2011-11-08" 
		                           };
		String[] v_fd_maturity_date = { "2019-06-12","2019-02-15","2017-04-19","2018-07-17","2020-06-12",
                                        "2016-04-12","2017-08-19","2017-03-21","2016-06-12","2016-11-08" 
                                      };
		String[] v_fd_status = {"C","O"}; 
		int fb_amt_min = 1000, fd_amt_max = 100000;
		
		int v_interest_rate = 7;
		String[] v_liquidate_date = {  "2019-04-12","2018-10-15","2017-01-19","2017-07-17","2019-06-12",
                                        "2015-02-12","2016-08-19","2014-03-21","2015-06-12","2015-11-08" 
                                      };
		int i = 0, j = 0, k = 1;
        int count = 1;
        int batchSize = 199;
        
        ResultSet rs = stmt.executeQuery("select customer_id from customer");
		rs.beforeFirst();
        while(rs.next() && (i < 202) ) {
			v_customer_id[i++] = rs.getInt(1);
		}
        
      
        int c = 40;
        int y = 40;
        while ( y < 200 ) {
        	
        v_fd++;
        String v_fd_id = "FD"+ v_fd;
        
		ps.setString(1, v_fd_id);  
		ps.setInt(2, v_customer_id[c++] );
		
		
		int v_fd_term1 = rand.nextInt(v_fd_term.length);
		ps.setInt(3, v_fd_term[v_fd_term1]);
		ps.setString(4, v_fd_open_date[v_fd_term1]);
		ps.setString(5, v_fd_maturity_date[v_fd_term1]);
		ps.setString(6, v_fd_status[rand.nextInt(v_fd_status.length)]);
		int v_fb_amt = rand.nextInt((fd_amt_max - fb_amt_min) + 1) + fb_amt_min ;
		ps.setInt(7, v_fb_amt);
		ps.setInt(8, v_interest_rate);
		ps.setInt(9, v_fb_amt + ( (v_fb_amt*(v_interest_rate/100)/ 12 )*v_fd_term[v_fd_term1]) );
		ps.setString(10, v_fd_maturity_date[v_fd_term1]);   // liquidate date is maturity date
	
		ps.execute();
		ps.addBatch();
		y++;
		
         if (count % batchSize == 0 ) {
        	ps.executeBatch();
        }
       }
        
        
        while( k <= 4 )
        {
         j = 0;
         while ( j < 10 ) {
        	
            v_fd1++;
            String v_fd_id1 = "FD"+ v_fd1;
            
    		ps.setString(1, v_fd_id1);    
    		
    		ps.setInt(2, v_customer_id[j] );
    		int v_fd_term1 = rand.nextInt(v_fd_term.length);
    		
    		
    		ps.setInt(3, v_fd_term[v_fd_term1]);
    		ps.setString(4, v_fd_open_date[v_fd_term1]);
    		ps.setString(5, v_fd_maturity_date[v_fd_term1]);
    		ps.setString(6, v_fd_status[rand.nextInt(v_fd_status.length)]);
    		int v_fb_amt = rand.nextInt((fd_amt_max - fb_amt_min) + 1) + fb_amt_min ;
    		ps.setInt(7, v_fb_amt);
    		ps.setInt(8, v_interest_rate);
    		ps.setInt(9, v_fb_amt + ( (v_fb_amt*(v_interest_rate/100)/ 12 )*v_fd_term[v_fd_term1]));
    		ps.setString(10, v_liquidate_date[v_fd_term1]);
    		
    		ps.execute();
    		j++;
    		
           }
         k++;
        }
        myConnection.commit();
        myConnection.close();
        
	}catch (SQLException ex) {
       ex.printStackTrace();
   }catch (NullPointerException npe) {
       npe.printStackTrace();
   }
	 
 }

}
