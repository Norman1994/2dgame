package LIbraryCinchron;

import java.util.List;

import Lib.*;
import User.*;
import Users.*;

public class Synchron {

	private UserService users;
	private LibraryUserService libraryUsers;
	private LibService library;
	private List<Users> allUsers;
	private List<User> allLibraryUsers;
	private List<Lib> libraryData;
	
	public Synchron(){
		users = new UserService();
		libraryUsers = new LibraryUserService();
		library = new LibService();
		allUsers = users.getAll();
		allLibraryUsers = libraryUsers.getAll();
		libraryData = library.getAll();
		synchOnStart();
	}
	
	public void synchOnStart(){
		int counter = 0;
		
		//��������� �� ������ ������� ����������
		if(allLibraryUsers.size() != 0){
			for(Users ut: allUsers){
				for(User u: allLibraryUsers){
					if(ut.getKodSotr() == u.getKodSotr()){
						//��������� �� �������� � ��������� ���������
						synchUpdate(ut, u.getFio());
						// ��������� ������� �� ���������
						synchOnDelete(ut);
						counter++;
					}
				}
				// ���� ���������� �� ����, �� ��������� ������
				if(counter == 0){
					libraryUsers.add(new User(ut.getKodSotr(), ut.getFio()));
				}else{
					counter=0;
				}
			}
		}else{
			//���� ������� �����, �������� ���� �����������
			for(Users ut: allUsers){
				libraryUsers.add(new User(ut.getKodSotr(), ut.getFio()));
			}
		}
	}

	public void synchUpdate(Users user, String libraryUser){
		
		if(user.getFio() != libraryUser){
			libraryUsers.update(new User(user.getKodSotr(), user.getFio()));
		}
	}

	public void synchOnDelete(Users user){
		String status = "����";
		String userStatus = user.getStatus().toLowerCase().trim();
		//��������� ����� ����������
		if(userStatus.contains(status)){
			int counter = 0;
			//���� �������� ��� �����
			for	(Lib l: libraryData){
				if(user.getKodSotr() == l.getKodSotr()){
					counter++;
				}
			}
			//���� �������� ���� ���, �� ������� �� ����� ��
			if(counter==0){
				users.delete(user.getKodSotr());
				libraryUsers.delete(user.getKodSotr());
			}
			
		}
		
	}
}
