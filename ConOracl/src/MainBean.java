
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import Beans.OracleConnector;
import Book.Book;
import Book.BookService;
import Lib.Lib;
import Lib.LibService;
import Lib.LibWiewer;
import Users.UserService;
import Users.Users;

@ManagedBean (name="Main") 
@SessionScoped             
public class MainBean
{
	public ResultSet rs;
	
    private String login;
    private String password;
    
    public BookService bookServ;
    
    private int god;
    
    public int getGod() {
		return god;
	}

	public void setGod(int god) {
		this.god = god;
	}

	public LibService libserv;
    
    public List<LibWiewer> lib;

    public List<LibWiewer> getLib() {
		return lib;
	}

	public void setLib() {
		bookServ = new BookService();
		List<Book> b = new ArrayList<Book>();
		b = bookServ.getAll();
		libserv = new LibService();
		lib = new ArrayList<LibWiewer>();
		List<Lib> libCount = new ArrayList<Lib>();
		libCount = libserv.getAll();
		for (int i = 0; i < libCount.size(); i++)
		{
			if((libCount.get(i).getKodSotr())==Integer.valueOf((login))) 
			{
				String bookName = "";
				for (Book bb: b) { 
					if (bb.getBookId() == libCount.get(i).getBookId())
					{
					bookName = bb.getBookName();
					}
				}
				
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis((libCount.get(i).getBdate()*24*60*60*1000) + libCount.get(i).getDate());
				SimpleDateFormat d = new SimpleDateFormat("dd.MM.yyyy");
				String datestr = d.format(c.getTime());
				
				lib.add(new LibWiewer (
						libCount.get(i).getId(),
						libCount.get(i).getBdate(),
						bookName,
						datestr,
						login,
						libCount.get(i).getPinalty(),
						libCount.get(i).getComment()
						));
			}
		}
	}
	
	public String goLibrary()
	{
		setLib();
		return "mylibrary.xhtml";
	}

	public OracleQuery oracle;
    
    public ArrayList<Otpusk> otp;
    
	public ArrayList<Otpusk> getOtp() {
		return otp;
	}

	public String setOtp() throws SQLException {
		
		otp = new ArrayList<Otpusk>();
		oracle = new OracleQuery();
		Connection con = oracle.getConnection();
		Statement st = con.createStatement(); 
        String sql; 
        sql = "SELECT t.god, u.FIO, fun1(t.god, " + login + ") as Week1, fun2 (t.god, " + login + ") as Week2, fun(t.god, " + login + ") as Week3 FROM GRAFIK t, USERS u  where u.kod_sotr = "+ login +" and t.kod_sotr = u.KOD_SOTR and t.god = "+ god +" "; 
        rs = st.executeQuery(sql); 
        
        while(rs.next()) { 
            this.otp.add(new Otpusk(rs.getInt(1), 
            		rs.getString(2), 
            		rs.getString(3), 
            		rs.getString(4),
            		rs.getString(5)));
        }
        
        return "vacations.xhtml";
	}

	@ManagedProperty(value = "#{UserService}")
    public UserService serv;
	
	public Users user;
	
	public String fio;
	public String department;
	public String position;

	public UserService getServ() {
		return serv;
	}

	public void setServ(UserService serv) {
		this.serv = serv;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = user.getFio();
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = user.getDepartment();
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = user.getPosition();
	}

	public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
	}

	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}
 
    public String checkLogin() throws SQLException{

    	System.out.println("checking login ...");
    	this.user = serv.get(Integer.valueOf(login));
    	setOtp();
    	return "table.xhtml";
    	/*if(login.equalsIgnoreCase("109909")){
	        return "table?faces-redirect=true";
        } else {
	        return "table?faces-redirect=true";
	    }*/
    }
    
}

