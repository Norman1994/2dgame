package Beans;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnector {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String name = "server";
    String password = "serv";
  
    public Connection con;
    public Connection getConnection()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver loading success!");
            
            try {
                con = DriverManager.getConnection(url, name, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	return con;
    }
}
