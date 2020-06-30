
package netbanking;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DisplayController {
	
	Queries QueryObj = new Queries();
	ArrayList<Map<String, Object>> QueryMap = new ArrayList<Map<String, Object>>();
	
	
    @RequestMapping("/query")
    public ArrayList<Map<String, Object>> Query(@RequestParam(value="id") String id) throws SQLException, ParseException {
    	
    	    if( id.contentEquals("query1") ) {
    
    	    QueryMap = QueryObj.getQuery1();
    	    System.out.println(QueryMap);
    	
    	    }
    	  
    	  if( id.contentEquals("query2") ) {
    		    
      	    QueryMap = QueryObj.getQuery2();
      	    System.out.println(QueryMap);
      	
      	    } 
    	  
    	  if( id.contentEquals("query3") ) {
    		    
      	    QueryMap = QueryObj.getQuery3();
      	    System.out.println(QueryMap);
      	
      	    }
    	  
    	  if( id.contentEquals("query4") ) {
    		    
      	    QueryMap = QueryObj.getQuery4();
      	    System.out.println(QueryMap);
      	
      	    }
    	  
    	  if( id.contentEquals("query5") ) {
    		    
      	    QueryMap = QueryObj.getQuery5();
      	    System.out.println(QueryMap);
      	
      	    }
    	  
    	  if( id.contentEquals("query6") ) {
    		    
      	    QueryMap = QueryObj.getQuery6();
      	    System.out.println(QueryMap);
      	
      	    }
    	  
    	  if( id.contentEquals("query7") ) {
    		    
      	    QueryMap = QueryObj.getQuery7();
      	    System.out.println(QueryMap);
      	
      	    }
    	  
    	  if( id.contentEquals("query8") ) {
    		    
      	    QueryMap = QueryObj.getQuery8();
      	    System.out.println(QueryMap);
      	
      	    }
    	  
    	  if( id.contentEquals("query9") ) {
    		    
      	    QueryMap = QueryObj.getQuery9();
      	    System.out.println(QueryMap);
      	
      	    }
    	  
    	  if( id.contentEquals("query10") ) {
    		    
      	    QueryMap = QueryObj.getQuery10();
      	    System.out.println(QueryMap);
      	
      	    }
    	
    	return QueryMap;
    }
}
