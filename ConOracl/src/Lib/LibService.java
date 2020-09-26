package Lib;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import Book.Book;
import Lib.Lib;
import Book.BookService;
import User.LibraryUserService;
import User.User;

public class LibService {
	
	//@PersistenceUnit(name = "Library")
	public EntityManager em = Persistence.createEntityManagerFactory("Library").createEntityManager();
    public User us;
    public LibraryUserService userv;
    public Book bk;
    public BookService bks;
    
    public LibService(){
    	us = new User();
    	userv = new LibraryUserService();
    	bk = new Book();
    	bks = new BookService();
    }
 
    public Lib add(Lib lb){
        em.getTransaction().begin();
        Lib b = em.merge(lb);
        em.getTransaction().commit();
        return b;
    }
 
    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }
 
    public Lib get(int id){
    	us = new User();
    	Lib lb;// = new Lib();
    	lb = em.find(Lib.class, id);
//    	us = userv.get(Integer.valueOf(lb.getKodSotr()));
//    	lb.setKodSotr(us.getFio());
//    	bk = bks.get(Integer.valueOf(lb.getBookId()));
//    	lb.setBookId(bk.getBookName());
        return lb;
    }
 
    public void update(Lib lb){
        em.getTransaction().begin();
        em.merge(lb);
        em.getTransaction().commit();
    }
 
    public List<Lib> getAll(){
        TypedQuery<Lib> namedQuery = em.createNamedQuery("Lib.findAll", Lib.class);
        return namedQuery.getResultList();
    }
}