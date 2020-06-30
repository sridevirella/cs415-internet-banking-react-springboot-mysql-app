package netbanking;

import java.sql.*;
import java.text.ParseException;

public class BranchDB {
	
    MySqlConnection myDBConnection = new MySqlConnection();
	
	public BranchDB() {}
	
	public void insertBranchDetails() throws SQLException, ParseException
	{
		
	 try
	  {
		Connection myConnection = myDBConnection.createConnection();
		myConnection.setAutoCommit(false);
		
		PreparedStatement ps = myConnection.prepareStatement("INSERT INTO BRANCH(branch_code,branch_name)"+"VALUES(?,?)");
				
		int count = 1;
        int batchSize = 10;
		int j = 0;
		
		
		int[] v_branch_code = {111,112,113,114,115,116,117,118,119,200};
		
		String[] v_branch_name = {"willowbrook","chicago","skokie","desplaines","devon",
                                  "aurora","edgewater","sauganesh","parkridge","oakbrook"
                                 };
				 
        while( j != 10 ) {
        
		ps.setInt(1, v_branch_code[j]);                    
		ps.setString(2, v_branch_name[j]);
		
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
