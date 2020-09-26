package Book;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="book")
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private int bookId;

	@Column(name="name_book")
	private String bookName;
	
	@Column(name="amount")
	private int amount;

	@Column(name="price")
	private int price;
	
	@Column(name="type")
	private int type;

	public Book() {
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getBookId() {
		return this.bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getOmount() {
		return this.amount;
	}

	public void setOmount(int omount) {
		this.amount = omount;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
