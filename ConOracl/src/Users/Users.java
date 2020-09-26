package Users;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="users")
@NamedQuery(name="Users.findAll", query="SELECT u FROM Users u")
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="kod_sotr")
	private int kodSotr;
	
	@Column(name="fio")
	private String fio;
	
	@Column(name="position")
	private String position;
	
	@Column(name="department")
	private String department;
	
	@Column(name="b_of_work")
	private Date work;
	
	@Column(name="salary")
	private int salary;
	
	@Column(name="status")
	private String status;
	
	public Users(){
		
	}

	public int getKodSotr() {
		return kodSotr;
	}

	public void setKodSotr(int kodSotr) {
		this.kodSotr = kodSotr;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String string) {
		this.department = string;
	}

	public Date getWork() {
		return work;
	}

	public void setWork(Date work) {
		this.work = work;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
