package Beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import User.User;
import Users.UserService;
import Users.Users;
import jsf.common.MainBean;

@ManagedBean (name="MainUser")
@RequestScoped
public class UserBean implements Serializable  {
	
	public OracleConnector oralce;

	public UserService serv;
	
	public Users user;
	
	//@ManagedProperty(value="#{Main.fio}")
	public String fio;
	
	public String getFio() {
		return fio;
	}
	
	public void setFio(String fio) {
		this.fio = fio;
	}
	
	public String department;
	public String position;
	
	//@ManagedProperty(value="#{Main.login}")
	private String login;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public Users getUser() {
		return user;
	}

	public void setUser() {
		this.user = serv.get(Integer.valueOf(login));
	}
	
	/*public List<Users> getUsers() throws ClassNotFoundException, SQLException {

		Connection connect = null;

		String url = "jdbc:oracle:thin:@localhost:1521/xe";
        String name = "server";
        String password = "serv";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connect = DriverManager.getConnection(url, name, password);
			// System.out.println("Connection established"+connect);

		} catch (SQLException ex) {
			System.out.println("in exec");
			System.out.println(ex.getMessage());
		}
		
		List<Users> users = new ArrayList<Users>();
		PreparedStatement pstmt = connect
				.prepareStatement("select kod_sotr, fio, position, department, b_of_work, salary, status from Users where kod_sotr =" + login);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			Users user = new Users();
			user.setKodSotr(rs.getInt("kod_sotr"));
			user.setFio(rs.getString("fio"));
			user.setPosition(rs.getString("department"));
			user.setDepartment(rs.getString("department"));
			user.setWork(rs.getDate("b_of_work"));
			user.setSalary(rs.getInt("salary"));
			user.setStatus(rs.getString("status"));
			users.add(user);
		}

		// close resources
		rs.close();
		pstmt.close();
		connect.close();

		return users;
	}*/
}
