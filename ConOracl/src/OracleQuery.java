import java.sql.DriverManager; 
import java.sql.Connection; 
import java.sql.Statement; 
import java.sql.ResultSet; 
import java.sql.Date; 
import java.sql.SQLException; 

public class OracleQuery { 
	
    public Connection getConnection() { 

        Connection conn = null; 

        try { 

            //Шаг второй: Регистрация JDBC драйвера 
            String driver = "oracle.jdbc.driver.OracleDriver"; 
            Class.forName(driver); 

            //Шаг третий: Создание соединения 
            System.out.println("Connecting to database ..."); 
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/xe"; 
            String user = "server"; 
            String pass = "serv"; 
            conn = DriverManager.getConnection(jdbcUrl, user, pass); 

            //Шаг четвертый: Выполнение запроса 
           /* Statement st = conn.createStatement(); 
            String sql; 
            sql = "SELECT t.god, u.FIO, (regexp_substr (get_week_list3(t.kod_sotr,t.god), '[^;]*;') || regexp_substr (get_week_list3(t.kod_sotr,t.god), ' [^;]*;$')) as учебный_отпуск, (regexp_substr (get_week_list4(t.kod_sotr,t.god), '[^;]*;') ||  regexp_substr (get_week_list4(t.kod_sotr,t.god), ' [^;]*;$')) as за_свой_счёт, week_fun2(t.kod_sotr,t.god) as внеочередной FROM GRAFIK t, USERS u  where t.KOD_SOTR = u.KOD_SOTR"; 
            ResultSet rs = st.executeQuery(sql); 

            //Шаг пятый: Извлечение данных из "Результата запроса" 
           while(rs.next()) { 
                // 
                int god = rs.getInt(1); 
                String fio = rs.getString(2);
                String uch = rs.getString(3);
                String svoi = rs.getString(4);
                String vneoch = rs.getString(5);
            } */

         //Шаг седьмой: Обработка исключений
         }catch(SQLException se) { 
             //Обработка ошибок для JDBC 
              se.printStackTrace(); 
         }catch(Exception e) { 
             //Обработка ошибок для Class.forName 
             e.printStackTrace(); 
         }finally { 
             //finally block used to close resourses 
           /*  try{ 
                 if (conn!=null) 
                     conn.close(); 
             }catch(SQLException se) { 
                 se.printStackTrace(); */
            }//end finally try 
        //}//end try 
		return conn; 
    }//end main 
}//end FirstQuery 