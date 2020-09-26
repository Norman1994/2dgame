package Beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import Book.Book;
import Book.BookService;
import LIbraryCinchron.Synchron;
import Lib.Lib;
import Lib.LibService;
import Lib.LibWiewer;
import User.LibraryUserService;
import User.User;

@ManagedBean (name="libBean") 
@SessionScoped 
public class LibraryBean {

	 public LibService libserv;
	 public BookService bookServ;
	 public LibraryUserService userService;
	 
	 public static Synchron sync; 
	 
	 public int selectedLib;
	 public int selectedBooks;
	 
	 public int bDate;
	 public Date dateTime;
	 public String format;
	 
	 public String errorMessage;

	 	public List<User> user;
		public List<LibWiewer> lib;
		public List<Book> book;
		
		public Book books;
		public User users;

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public int getbDate() {
			return bDate;
		}

		public void setbDate(int bDate) {
			this.bDate = bDate;
		}

		public Date getDateTime() {
			return dateTime;
		}

		public void setDateTime(Date dateTime) {
			this.dateTime = dateTime;
		}

		public List<LibWiewer> getLib() {
			return lib;
		}

	    public List<Book> getBook() {
			return book;
		}

		public List<User> getUser() {
			return user;
		}

		public void setUser(List<User> user) {
			this.user = user;
		}

		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}

		public LibraryBean()
	    {
	    	getLibr();
	    	getBooks();
	    	getUsers();
	    	sync = new Synchron();
	    	
	    }
	    
	    public void getLibr()
	    {
	    	bookServ = new BookService();
	    	List<Book> b = new ArrayList<Book>();
	    	b = bookServ.getAll();		
	    	userService = new LibraryUserService();
	    	List<User> u = new ArrayList<User>();
	    	u = userService.getAll();
	    	libserv = new LibService();
	    	books = new Book();
	    	users = new User();
			lib = new ArrayList<LibWiewer>();
			List<Lib> libCount = new ArrayList<Lib>();
			libCount = libserv.getAll();
			for (int i = 0; i < libCount.size(); i++)
			{
				String userName = "";
				String bookName = "";
				for (Book bb: b) { 
					if (bb.getBookId() == libCount.get(i).getBookId())
					{
					bookName = bb.getBookName();
					}
				}
				for (User uu: u) { 
					if (uu.getKodSotr() == libCount.get(i).getKodSotr())
					{
					userName = uu.getFio();
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
						userName,
						libCount.get(i).getPinalty(),
						libCount.get(i).getComment()
						));
				System.out.println(userName + " " + bookName);
			}
	    }
	    
	    public void getBooks()
	    {
	    	bookServ = new BookService();
	    	book = new ArrayList<Book>();
	    	List<Book> bookCount = new ArrayList<Book>();
	    	bookCount = bookServ.getAll();
	    	for (int i = 0; i < bookCount.size(); i++)
			{
					book.add(bookCount.get(i));
			}
	    }
	    
	    public void getUsers()
	    {
	    	userService = new LibraryUserService();
	    	user = new ArrayList<User>();
	    	List<User>userCount = new ArrayList<User>();
	    	userCount = userService.getAll();
	    	for (int i = 0; i < userCount.size(); i++)
			{
					user.add(userCount.get(i));
			}
	    }
	    
	    public String addRec()
	    {
	    	bookServ = new BookService();
	    	int neededBooksNumber = bookServ.get(getSelectedBooks()).getOmount();
	    	List<Lib> libs = libserv.getAll();
	    	int takedBooks = 0;
	    	long millis = 0;
	    	for(Lib l:libs)
	    	{
	    		if (l.getBookId() == getSelectedBooks())
	    		{
	    			takedBooks++;
	    			millis = l.getDate() + l.getBdate()*24*60*60*1000;
	    		}
	    	}
	    	Calendar c = Calendar.getInstance();
			c.setTimeInMillis(millis);
			SimpleDateFormat d = new SimpleDateFormat("dd.MM.yyyy");
			String datestr = d.format(c.getTime());
			
	    	if (takedBooks < neededBooksNumber)
	    	{
	    		checkRareBooks();
	    	}
	    	else 
	    	{
	    		errorMessage = "Данной книги нет в наличии. Экземпляр книги "
	    				+ "примерно вернут" + datestr;
	    	}
	    	getLibr();
			return "sAdd.xhtml";
	    }
	    
	    public String deleteAction(LibWiewer lb)
	    {
	    	libserv.delete(lb.getId());
	    	getLibr();
	    	return null;
	    }
	    
	    public void checkRareBooks()
	    {
	    	bookServ = new BookService();
	    	double pt = 0.0;
	    	if (bookServ.get(getSelectedBooks()).getType() == 1)
	    	{
	    		pt = bookServ.get(getSelectedBooks()).getPrice() * 0.3;
	    		if (getbDate() <= 7)
	    		{
	    			addLibToDb(pt);
	    		}
	    		else 
	    		{
	    			errorMessage = "Редкие книги не могут быть выданы более чем на 7 дней!";
	    		}
	    	}
	    	else 
	    	{
	    		pt = 0.0;
	    		if(getbDate()!=0)
	    		{
	    			errorMessage = "Успешно!";
	    			addLibToDb(pt);
	    		}
	    		else
	    		{
	    			errorMessage = "Поле 'Кол-во дней' не может быть пустым!";
	    		}
	    	}
	    	
	    }
	    
	    public void addLibToDb(double pinalty)
	    {
	    	Lib lb = new Lib();
	    	lb.setBookId(getSelectedBooks());
	    	lb.setKodSotr(getSelectedLib());
	    	lb.setBdate(getbDate());
	    	lb.setDate(new java.util.Date().getTime());
	    	lb.setPinalty(pinalty);
	    	libserv.add(lb);  
	    }
	    
	    public void getCurrentDate()
	    {
	    	dateTime = new Date();
	    	format = "dd-MM-yyyy";
	    }
	    
	    public int getSelectedLib() {
			return selectedLib;
		}

		public void setSelectedLib(int selectedLib) {
			this.selectedLib = selectedLib;
		}

		public int getSelectedBooks() {
			return selectedBooks;
		}

		public void setSelectedBooks(int selectedBooks) {
			this.selectedBooks = selectedBooks;
		}
		
		 @PostConstruct
		    public void init(){
		       getCurrentDate();
		    }

}
