package netbanking;

import java.sql.*;
import java.text.ParseException;
import java.util.Random;

public class SignInDB {
MySqlConnection myDBConnection = new MySqlConnection();
	
	public SignInDB() {}
	
	public void insertSigninDetails() throws SQLException, ParseException
	{
		
	 try
	  {
		Connection myConnection = myDBConnection.createConnection();
		myConnection.setAutoCommit(false);
		
		PreparedStatement ps = myConnection.prepareStatement("INSERT INTO SIGN_IN(login_id,password,login_date)"+"VALUES(?,?,?)");
		Statement stmt = myConnection.createStatement();
		
		int count = 1;
        int batchSize = 300;
		int i = 1, j = 1;
		int[] v_login_id = new int[301];
		Random rand = new Random();
		
		
		String[] v_login_date = {"2019-01-12","2019-02-15","2019-10-19","2019-07-17","2019-01-12",
                                 "2019-04-12","2019-02-19","2019-03-21","2019-12-12","2019-11-08" 
                                };
		
		String[] v_password = {"Sri@123","password@123","Unlock!1","password@14","pass#1234",
                               "neiu$1234","raindrop@12","hey!87","world432","rain#124",
                               "Tennee$66","raindrop@14","raindrop@777","raindrop@999"
                              };
		
		ResultSet rs = stmt.executeQuery("select customer_id from customer");
		rs.beforeFirst();
        while(rs.next()) {		
			v_login_id[i++] = rs.getInt(1);
		}
		 
        while( j != 300 ) {
        
		ps.setInt(1, v_login_id[j]);                    
		ps.setString(2, v_password[rand.nextInt(v_password.length)]);
		ps.setString(3, v_login_date[rand.nextInt(v_login_date.length)] );
		
		
          ps.addBatch();
          j++;
       
        if (count % batchSize == 0 ) {
        	ps.executeBatch();
        }
		
       }
        ps.executeBatch();
        myConnection.commit();
        myConnection.close();
        
	} catch (SQLException ex) {
       ex.printStackTrace();
    }catch (NullPointerException npe) {
       npe.printStackTrace();
   }
	 
 }


}
