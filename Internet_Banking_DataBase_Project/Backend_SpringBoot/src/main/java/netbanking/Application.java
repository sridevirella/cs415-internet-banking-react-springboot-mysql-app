package netbanking;

import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws SQLException, ParseException {
  
        SpringApplication.run(Application.class, args);
        
            //TableDataInsertion tdi = new TableDataInsertion();
           // tdi.tableInsertion();
        System.out.println("heyyy you did it!!!");
        
    }
}
