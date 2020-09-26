package Positions;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="POSITIONS")
@NamedQuery(name="Positions.findAll", query="SELECT p FROM Positions p")
public class Positions implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="position_kod")
	private int kodPos;
	
	@Column(name="position_name")
	private String namePos;
	
	public Positions(){
		
	}

	public int getKodPos() {
		return kodPos;
	}

	public void setKodPos(int kodPos) {
		this.kodPos = kodPos;
	}

	public String getNamePos() {
		return namePos;
	}

	public void setNamePos(String namePos) {
		this.namePos = namePos;
	}
	
	
}
