package User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import User.User;

import java.util.List;

public class LibraryUserService {
	
	public EntityManager em = Persistence.createEntityManagerFactory("Library").createEntityManager();
	 
    public User add(User us){
        em.getTransaction().begin();
        User usFromDB = em.merge(us);
        em.getTransaction().commit();
        return usFromDB;
    }
 
    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }
 
    public User get(int id){
        return em.find(User.class, id);
    }
 
    public void update(User us){
        em.getTransaction().begin();
        em.merge(us);
        em.getTransaction().commit();
    }
 
    public List<User> getAll(){
        TypedQuery<User> namedQuery = em.createNamedQuery("User.findAll", User.class);
        return namedQuery.getResultList();
    }

}
