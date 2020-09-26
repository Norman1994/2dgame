package Lib;



public class LibWiewer {
	
	private int id;

	private int bdate;

	private String bookId;

	private String date;

	private String kodSotr;

	private double pinalty;
	
	private String comment;
	

	public LibWiewer(int id, int bdate, String bookId, String date, String kodSotr, double pinalty, String comment) {
		super();
		this.id = id;
		this.bdate = bdate;
		this.bookId = bookId;
		this.date = date;
		this.kodSotr = kodSotr;
		this.pinalty = pinalty;
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public String getBookId() {
		return this.bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getKodSotr() {
		return this.kodSotr;
	}

	public void setKodSotr(String kodSotr) {
		this.kodSotr = kodSotr;
	}

	public double getPinalty() {
		return this.pinalty;
	}

	public void setPinalty(double pinalty) {
		this.pinalty = pinalty;
	}

}
