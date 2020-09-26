package User;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="kod_sotr")
	private int kodSotr;

	@Column(name="fio")
	private String fio;
	
	
	public User() {
		
	}

	public User(int kodSotr, String fio) {
		this.kodSotr = kodSotr;
		this.fio = fio;
	}

	public int getKodSotr() {
		return this.kodSotr;
	}

	public void setKodSotr(int kodSotr) {
		this.kodSotr = kodSotr;
	}

	public String getFio() {
		return this.fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

}
