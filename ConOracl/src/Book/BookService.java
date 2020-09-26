package Book;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Book.Book;

public class BookService {
	
	 public EntityManager em = Persistence.createEntityManagerFactory("Library").createEntityManager();
	 
	    public Book add(Book bk){
	        em.getTransaction().begin();
	        Book bFromDB = em.merge(bk);
	        em.getTransaction().commit();
	        return bFromDB;
	    }
	 
	    public void delete(int id){
	        em.getTransaction().begin();
	        em.remove(get(id));
	        em.getTransaction().commit();
	    }
	 
	    public Book get(int id){
	        return em.find(Book.class, id);
	    }
	 
	    public void update(Book bk){
	        em.getTransaction().begin();
	        em.merge(bk);
	        em.getTransaction().commit();
	    }
	 
	    public List<Book> getAll(){
	        TypedQuery<Book> namedQuery = em.createNamedQuery("Book.findAll", Book.class);
	        return namedQuery.getResultList();
	    }

}
