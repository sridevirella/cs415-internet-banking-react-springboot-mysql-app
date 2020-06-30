package netbanking;

import java.sql.*;
import java.util.*;

public class Queries {
	
	MySqlConnection myDBConnection = new MySqlConnection();
	
    public Queries() {};
    
    public  ArrayList<Map<String, Object>> getQuery1() throws SQLException {

    	Connection myConnection = myDBConnection.createConnection();
    	Statement stmt = myConnection.createStatement();
    	
    	ResultSet rs = stmt.executeQuery("select c.customer_name,count(c.customer_id),c.date_of_birth,c.gender,c.email   \r\n" + 
    			                          "from customer AS c, FIXED_DEPOSIT AS fd \r\n" + 
    			                          "where fd.customer_id = c.customer_id and fd.liquidate_date < fd.fd_maturity_date\r\n" + 
    			                          "     and fd.liquidate_date > '2000-01-01' and fd.liquidate_date < '2019-01-01'\r\n" + 
    			                          "group by c.customer_id\r\n" + 
    			                          "having count(c.customer_id) > 3");    
        rs.beforeFirst();
        ArrayList<Map<String, Object>> query1Array = new ArrayList<Map<String, Object>>();
        

    	while(rs.next()) {
    	
    		
    		Map<String, Object> record = new HashMap<String, Object>();
    		record.put("Customer Name", rs.getString(1));
    		record.put("Liquidated", rs.getInt(2));
    		record.put("Date Of Birth", rs.getDate(3));
    		record.put("Gender", rs.getString(4));
    		record.put("E-mail", rs.getString(5));
    		query1Array.add(record);
    		
    	}
    	
    	myConnection.close();
    	return query1Array;

     }
    
    public  ArrayList<Map<String, Object>> getQuery2() throws SQLException {

    	Connection myConnection = myDBConnection.createConnection();
    	Statement stmt = myConnection.createStatement();
    	
    	ResultSet rs = stmt.executeQuery("select c.customer_id,c.customer_name,c.date_of_birth,c.gender,c.email\r\n" + 
    			                         "from customer As c, loan AS l\r\n" + 
    			                         "where c.customer_id = l.customer_id and l.loan_amount > 1500 \r\n" + 
    			                         "and c.customer_id  not in ( select c.customer_id\r\n" + 
    			                         " from   customer AS c, account As a\r\n" + 
                                         " where  c.customer_id = a.customer_id)");    
        rs.beforeFirst();
        ArrayList<Map<String, Object>> query2Array = new ArrayList<Map<String, Object>>();
        

    	while(rs.next()) {
    		
    		
    		Map<String, Object> record = new HashMap<String, Object>();
    		record.put("Customer ID", rs.getInt(1));
    		record.put("Customer Name", rs.getString(2));
    		record.put("Date Of Birth", rs.getDate(3));
    		record.put("Gender", rs.getString(4));
    		record.put("E-mail", rs.getString(5));
    		query2Array.add(record);
    		
    	}
    	
    	myConnection.close();
    	return query2Array;

     }
    
    public ArrayList<Map<String, Object>> getQuery3() throws SQLException {

    	Connection myConnection = myDBConnection.createConnection();
    	Statement stmt = myConnection.createStatement();
    	System.out.println("came inside query3");
    	ResultSet rs = stmt.executeQuery("select temp.people,temp.branch_name,temp.login_date from (select count(s.login_id) as people , b.branch_name, s.login_date  from  sign_in AS s,branch as b , account as a\r\n" + 
    			                         " where  b.branch_code = a.branch_code and s.login_id = a.customer_id\r\n" + 
    			                         " group by b.branch_code) as temp group by temp.login_date");    
        rs.beforeFirst();
        ArrayList<Map<String, Object>> query3Array = new ArrayList<Map<String, Object>>();
        

    	while(rs.next()) {
    		
    		Map<String, Object> record = new HashMap<String, Object>();
    		record.put("Number Of Customers", rs.getInt(1));
    		record.put("Branch Name", rs.getString(2));
    		record.put("Date", rs.getDate(3));
    		query3Array.add(record);
    		
    	}
    	
    	myConnection.close();
    	return query3Array;

     }
    
   
    public  ArrayList<Map<String, Object>> getQuery4() throws SQLException {

    	Connection myConnection = myDBConnection.createConnection();
    	Statement stmt = myConnection.createStatement();
    	
    	ResultSet rs = stmt.executeQuery("select c.customer_name,b.bill_id,b.bill_amt,b.description,cc.credit_card_no,b.transaction_date\r\n" + 
    			                         "from customer AS c, credit_card As cc,bill AS b\r\n" + 
    			                         "where b.from_acc = cc.credit_card_no and b.customer_id = c.customer_id");    
        rs.beforeFirst();
        ArrayList<Map<String, Object>> query4Array = new ArrayList<Map<String, Object>>();
        

    	while(rs.next()) {
    		
    		
    		Map<String, Object> record = new HashMap<String, Object>();
    		record.put("Customer Name", rs.getString(1));
    		record.put("Bill ID", rs.getString(2));
    		record.put("Bill Amount", rs.getInt(3));
    		record.put("Description", rs.getString(4));
    		record.put("Credit Card Number", rs.getInt(5));
    		record.put("Transaction Date", rs.getDate(6));
    		query4Array.add(record);
    		
    	}
    	
    	myConnection.close();
    	return query4Array;

     }
    


public  ArrayList<Map<String, Object>> getQuery5() throws SQLException {

	Connection myConnection = myDBConnection.createConnection();
	Statement stmt = myConnection.createStatement();
	
	ResultSet rs = stmt.executeQuery(" select sum(ft.trans_amt), b.branch_name\r\n" + 
			                         " from fund_transfer AS ft, account AS a, branch AS b\r\n" + 
			                         " where ft.from_account_no = a.account_no and a.branch_code = b.branch_code\r\n" + 
			                         " group by b.branch_code");
    rs.beforeFirst();
    ArrayList<Map<String, Object>> query5Array = new ArrayList<Map<String, Object>>();
    

	while(rs.next()) {
		
		
		Map<String, Object> record = new HashMap<String, Object>();
		record.put("Total Transaction Amount", rs.getInt(1));
		record.put("Branch Name", rs.getString(2));
		query5Array.add(record);
		
	}
	
	myConnection.close();
	return query5Array;

 }

public  ArrayList<Map<String, Object>> getQuery6() throws SQLException {

	Connection myConnection = myDBConnection.createConnection();
	Statement stmt = myConnection.createStatement();
	
	ResultSet rs = stmt.executeQuery("select count(l.customer_id)\r\n" + 
			                         " from   credit_card As cc,loan_payment AS lp, loan AS l\r\n" + 
			                         " where lp.from_account = cc.credit_card_no \r\n" + 
			                         " and l.loan_id = lp.loan_id\r\n" + 
			                         " and cc.customer_id = l.customer_id");    
    rs.beforeFirst();
    ArrayList<Map<String, Object>> query6Array = new ArrayList<Map<String, Object>>();
    

	while(rs.next()) {
		
		Map<String, Object> record = new HashMap<String, Object>();
		record.put("Total Number of Customers", rs.getInt(1));
		query6Array.add(record);
		
	}

	myConnection.close();
	return query6Array;

 }


public  ArrayList<Map<String, Object>> getQuery7() throws SQLException {

	Connection myConnection = myDBConnection.createConnection();
	Statement stmt = myConnection.createStatement();
	
	ResultSet rs = stmt.executeQuery("select count(b.bill_id),sum(b.bill_amt)\r\n" + 
			                         "from   account As a,bill AS b\r\n" + 
			                         "where b.from_acc = a.account_no and b.customer_id = a.customer_id");    
    rs.beforeFirst();
    ArrayList<Map<String, Object>> query7Array = new ArrayList<Map<String, Object>>();
    

	while(rs.next()) {
		
		Map<String, Object> record = new HashMap<String, Object>();
		record.put("Total Number Of Transactions", rs.getInt(1));
		record.put("Total Transaction Amount", rs.getInt(2));
		query7Array.add(record);
		
	}
	
	myConnection.close();
	return query7Array;

 }

public  ArrayList<Map<String, Object>> getQuery8() throws SQLException {

	Connection myConnection = myDBConnection.createConnection();
	Statement stmt = myConnection.createStatement();
	
	ResultSet rs = stmt.executeQuery(" select distinct c.customer_name, l.loan_id, cc.credit_card_no, a.account_no,a.account_type, c.date_of_birth, c.gender, c.email\r\n" + 
			                         " from loan AS l, fixed_deposit AS fd, account AS a, credit_card AS cc, customer AS c\r\n" + 
			                         " where c.customer_id = l.customer_id and c.customer_id = fd.customer_id\r\n" + 
			                         " and c.customer_id = a.customer_id and c.customer_id = cc.customer_id\r\n" + 
			                         " and a.account_type = 'S' OR a.account_type = 'C' and a.acc_status = 'O'");    
    rs.beforeFirst();
    ArrayList<Map<String, Object>> query8Array = new ArrayList<Map<String, Object>>();
    

	while(rs.next()) {
		
		
		Map<String, Object> record = new HashMap<String, Object>();
		record.put("Customer Name", rs.getString(1));
		record.put("Loan ID", rs.getString(2));
		record.put("Credit Card No", rs.getInt(3));
		record.put("Account Number", rs.getInt(4));
		record.put("Account Type", rs.getString(5));
		record.put("Date Of Birth", rs.getDate(6));
		record.put("Gender", rs.getString(7));
		record.put("E-mail", rs.getString(8));
		query8Array.add(record);
		
	}
	
	myConnection.close();
	return query8Array;

 }


public  ArrayList<Map<String, Object>> getQuery9() throws SQLException {

	Connection myConnection = myDBConnection.createConnection();
	Statement stmt = myConnection.createStatement();
	
	ResultSet rs = stmt.executeQuery("  select b.branch_name, count(a.account_no) \r\n" + 
			                         "  from account AS a, branch AS b\r\n" + 
			                         "  where a.branch_code = b.branch_code\r\n" + 
			                         "  and a.acc_status = 'C'\r\n" + 
			                         "  group by a.branch_code ");    
    rs.beforeFirst();
    ArrayList<Map<String, Object>> query9Array = new ArrayList<Map<String, Object>>();
    

	while(rs.next()) {
		
		
		Map<String, Object> record = new HashMap<String, Object>();
		record.put("Branch Name", rs.getString(1));
		record.put("Number Of Closed Accounts", rs.getInt(2));
		query9Array.add(record);
		
	}
	
	myConnection.close();
	return query9Array;

 }

public  ArrayList<Map<String, Object>> getQuery10() throws SQLException {

	Connection myConnection = myDBConnection.createConnection();
	Statement stmt = myConnection.createStatement();
	
	ResultSet rs = stmt.executeQuery("select c.customer_name, cc.credit_limit\r\n" + 
			"from credit_card As cc, customer AS c\r\n" + 
			"where cc.customer_id = c.customer_id\r\n" + 
			" and cc.customer_id in    \r\n" + 
			"(select cc.customer_id\r\n" + 
			"from   credit_card As cc,loan_payment AS lp, loan AS l\r\n" + 
			"where lp.from_account = cc.credit_card_no \r\n" + 
			"and l.loan_id = lp.loan_id\r\n" + 
			" and cc.customer_id = l.customer_id\r\n" + 
			"union\r\n" + 
			"select c.customer_id\r\n" + 
			"from customer AS c, credit_card As cc,bill AS b\r\n" + 
			"where b.from_acc = cc.credit_card_no and b.customer_id = c.customer_id)");    
    rs.beforeFirst();
    ArrayList<Map<String, Object>> query10Array = new ArrayList<Map<String, Object>>();
    

	while(rs.next()) {
		
		
		Map<String, Object> record = new HashMap<String, Object>();
		record.put("Customer Name", rs.getString(1));
		record.put("Credit Limit", rs.getInt(2));
		query10Array.add(record);
		
	}
	
	myConnection.close();
	return query10Array;

 }

}
