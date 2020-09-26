package Users;

	import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
	import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import Departments.DepartmentManager;
import Departments.Departments;
import Positions.PositionService;
import Positions.Positions;

import java.util.List;
	 

@ManagedBean(name="UserService")
@SessionScoped
public class UserService {
		
		public Departments dt;
		public DepartmentManager dtm;
		public Positions pt;
		public PositionService pts;
		
		//@PersistenceUnit(name = "UsersDataBase")
	    public EntityManager em = Persistence.createEntityManagerFactory("UsersDataBase").createEntityManager();
		
		public UserService(){
			dt = new Departments();
			dtm = new DepartmentManager();
			pt = new Positions();
			pts = new PositionService();
		}
	 
	    public Users add(Users us){
	        em.getTransaction().begin();
	        Users usFromDB = em.merge(us);
	        em.getTransaction().commit();
	        return usFromDB;
	    }
	 
	    public void delete(int id){
	        em.getTransaction().begin();
	        em.remove(get(id));
	        em.getTransaction().commit();
	    }
	 
	    public Users get(int id){
	    	Users aut = new Users();
	    	aut = em.find(Users.class, id);
	    	dt = dtm.get(Integer.valueOf(aut.getDepartment()));
	    	aut.setDepartment(dt.getNameDep());
	    	pt = pts.get(Integer.valueOf(aut.getPosition()));
	    	aut.setPosition(pt.getNamePos());
	        return aut;
	    }
	 
	    public void update(Users us){
	        em.getTransaction().begin();
	        em.merge(us);
	        em.getTransaction().commit();
	    }
	 
	    public List<Users> getAll(){
	        TypedQuery<Users> namedQuery = em.createNamedQuery("Users.findAll", Users.class);
	        return namedQuery.getResultList();
	    }
	 
	}