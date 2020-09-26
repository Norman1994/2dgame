import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLConnector {

	private static final String url = "jdbc:mysql://localhost:3306/lib";
    private static final String user = "root";
    private static final String password = "DIMA";
	
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    
    public static void main(String args[]) {
        String query = "select kod_sotr, fio from users";
 
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
 
            // getting Statement object to execute query
            stmt = con.createStatement();
 
            // executing SELECT query
            rs = stmt.executeQuery(query);
 
            while (rs.next()) {
            	   int kod_sotr = rs.getInt(1);
            	   String fio = rs.getString(2);
            	   System.out.printf("Код сотрудника: %d, Фамилия: %s ;", kod_sotr, fio);
            	 }
 
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

}
