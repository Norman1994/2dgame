package Departments;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="Departments")
@NamedQuery(name="Departments.findAll", query="SELECT d FROM Departments d")
public class Departments implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="kod_dep")
	private int kodDep;
	
	@Column(name="name_dep")
	private String nameDep;
	
	public Departments() {
		
	}

	public int getKodDep() {
		return kodDep;
	}

	public void setKodDep(int kodDep) {
		this.kodDep = kodDep;
	}

	public String getNameDep() {
		return nameDep;
	}

	public void setNameDep(String nameDep) {
		this.nameDep = nameDep;
	}
	
	
}
