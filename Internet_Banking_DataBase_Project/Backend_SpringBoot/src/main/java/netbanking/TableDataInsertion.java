package netbanking;

import java.sql.SQLException;
import java.text.ParseException;

public class TableDataInsertion {
	
	public void tableInsertion() throws SQLException, ParseException {
		    	 
    	     	 
    	 CustomerDB custObj = new CustomerDB();
    	 custObj.insertCustomer();
    	 System.out.println("insertion of Customer details successful");
    	
    	 CustomerAddressDB custAddressObj = new CustomerAddressDB();
    	 custAddressObj.insertCustomerAddress();
    	 System.out.println("insertion of Customer Address details successful");
    	
    	  SignInDB signinObj = new SignInDB();
    	  signinObj.insertSigninDetails();
    	  System.out.println("insertion of Customer signin details successful");
    	  
    	  BranchDB brnObj = new BranchDB();
    	  brnObj.insertBranchDetails();
    	  System.out.println("insertion of branch details successful");
    	 
    	   AccountDB accObj = new AccountDB();
    	   accObj.insertAccount();
    	   System.out.println("insertion of account details successful");
		
		    BillDB  billObj = new BillDB();
            billObj.billDetails();
    	    System.out.println("insertion of Bill details successful");
    	
    	    BenficiaryDB benObj = new BenficiaryDB();
    	    benObj.insertBenficiary();
    	    System.out.println("insertion of Beneficiary details successful");
    	
    	    FundTransferDB ftObj = new FundTransferDB();
    	    ftObj.insertBenficiary();
    	    System.out.println("insertion of fund transfer details successful"); 
    	    
    	   FixedDeposit fdObj = new FixedDeposit();
 	       fdObj.insertFD();
 	       System.out.println("insertion of fixed deposit details successful");
    	     
    	 
    	   CreditCardDB ccObj = new CreditCardDB();
    	   ccObj.CCDetails();
    	   System.out.println("insertion of credit card details successful"); 
    	   
    	 
    	   LoanDB loanObj = new LoanDB();
    	   loanObj.loanDetails();
    	   System.out.println("insertion of loan details successful");
    	 
    	   LoanPaymentDB loanPayObj = new LoanPaymentDB();
    	   loanPayObj.loanPaymentDetails();
    	   System.out.println("insertion of loan payment details successful");
    }
}
