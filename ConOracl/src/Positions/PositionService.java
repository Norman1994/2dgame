package Positions;

	import java.util.List;

	import javax.persistence.EntityManager;
	import javax.persistence.Persistence;
	import javax.persistence.PersistenceUnit;
	import javax.persistence.TypedQuery;


	public class PositionService {

		@PersistenceUnit(name = "UsersDataBase")
	    public EntityManager em = Persistence.createEntityManagerFactory("UsersDataBase").createEntityManager();
		 
	    public Positions add(Positions  pt){
	        em.getTransaction().begin();
	        Positions pFromDB = em.merge(pt);
	        em.getTransaction().commit();
	        return pFromDB;
	    }
	 
	    public void delete(int id){
	        em.getTransaction().begin();
	        em.remove(get(id));
	        em.getTransaction().commit();
	    }
	 
	    public Positions  get(int id){
	        return em.find(Positions .class, id);
	    }
	 
	    public void update(Positions pt){
	        em.getTransaction().begin();
	        em.merge(pt);
	        em.getTransaction().commit();
	    }
	 
	    public List<Positions> getAll(){
	        TypedQuery<Positions> namedQuery = em.createNamedQuery("PositionTable.getAll", Positions.class);
	        return namedQuery.getResultList();
	    }
	 
	}
