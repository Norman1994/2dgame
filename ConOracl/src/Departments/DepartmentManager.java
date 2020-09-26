package Departments;

	import java.util.List;

	import javax.persistence.EntityManager;
	import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;


	public class DepartmentManager {

		@PersistenceUnit(name = "UsersDataBase")
	    public EntityManager em = Persistence.createEntityManagerFactory("UsersDataBase").createEntityManager();
		 
	    public Departments add(Departments dt){
	        em.getTransaction().begin();
	        Departments dFromDB = em.merge(dt);
	        em.getTransaction().commit();
	        return dFromDB;
	    }
	 
	    public void delete(int id){
	        em.getTransaction().begin();
	        em.remove(get(id));
	        em.getTransaction().commit();
	    }
	 
	    public Departments get(int id){
	        return em.find(Departments.class, id);
	    }
	 
	    public void update(Departments dt){
	        em.getTransaction().begin();
	        em.merge(dt);
	        em.getTransaction().commit();
	    }
	 
	    public List<Departments> getAll(){
	        TypedQuery<Departments> namedQuery = em.createNamedQuery("Departments.getAll", Departments.class);
	        return namedQuery.getResultList();
	    }
	 
	}
