package netbanking;

import java.sql.*;
import java.text.ParseException;
import java.util.Random;

public class CustomerAddressDB {
	
	MySqlConnection myDBConnection = new MySqlConnection();
	
	public CustomerAddressDB() {}
	
	public void insertCustomerAddress() throws SQLException, ParseException
	{
	 try
	  {
		Connection myConnection = myDBConnection.createConnection();
		myConnection.setAutoCommit(false);
		
		PreparedStatement ps = myConnection.prepareStatement("INSERT INTO CUSTOMER_ADDRESS(customer_id,street,city,zipcode)"+"VALUES(?,?,?,?)");
		Statement stmt = myConnection.createStatement();
		
		int count = 1;
        int batchSize = 300;
		int i = 1, j = 1;
		int[] v_customer_id = new int[301];
		Random rand = new Random();
		int min = 25000, max = 90000;
		int v_zipcode = rand.nextInt((max - min) + 1) + min ;
		
		String[] v_street = {"Repton Ride","Home Farm Circle","Carey View","Rickaby Close","Mount Pleasant Court",
                           "Hadrian Village","Cedar Loke","Leslie View","Leslie Drift","Cumberland End",
                           "Pollard Street","Cranmer Road","Salcombe Reach","Chesterton Ridgeway","Sorrel Place",
                           "Vaughan Mill","Dovecote Cloisters","Colmer Close","Repton Meadows","Pelham West",
                           "Dove Loan","Urswick Close","Limes Vale","Severn Hill","Bowland Common"
                          };
		
		String[] v_city = {"Florida","Arizona","Texas","Wisconsin","District of Columbia",
                           "California","Minnesota","Pennsylvania","Indiana","New York",
                           "Tennessee","Illinois","Nebraska","Alaska"
                          };
		
		ResultSet rs = stmt.executeQuery("select customer_id from customer");
		rs.beforeFirst();
        while(rs.next()) {
			
			v_customer_id[i++] = rs.getInt(1);

		}
		 
        while( j != 300 ) {
        
		ps.setInt(1, v_customer_id[j]);                    
		ps.setString(2, v_street[rand.nextInt(v_street.length)]);
		ps.setString(3, v_city[rand.nextInt(v_city.length)]);
		v_zipcode++;
		ps.setInt(4, v_zipcode);
		
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
