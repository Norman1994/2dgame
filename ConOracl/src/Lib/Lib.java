package Lib;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="lib")
@NamedQuery(name="Lib.findAll", query="SELECT l FROM Lib l")
public class Lib implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="bdate")
	private int bdate;

	@Column(name="book_id")
	private int bookId;

	@Column(name="date")
	private long date;

	@Column(name="kod_sotr")
	private int kodSotr;

	@Column(name="pinalty")
	private double pinalty;
	
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Lib() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public 	int getBdate() {
		return this.bdate;
	}

	public void setBdate(int bdate) {
		this.bdate = bdate;
	}

	public int getBookId() {
		return this.bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public long getDate() {
		return this.date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getKodSotr() {
		return this.kodSotr;
	}

	public void setKodSotr(int kodSotr) {
		this.kodSotr = kodSotr;
	}

	public double getPinalty() {
		return this.pinalty;
	}

	public void setPinalty(double pinalty) {
		this.pinalty = pinalty;
	}

}
