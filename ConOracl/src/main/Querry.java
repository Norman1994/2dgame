package main;

import java.util.ArrayList;
import java.util.List;

import Lib.Lib;
import Lib.LibService;
import User.LibraryUserService;

public class Querry {
	
	public static LibraryUserService book;

	public static void main(String[] args) {
		LibService libserv = new LibService();
		List<Lib> lib = new ArrayList<Lib>();
		lib = libserv.getAll();
		for (int i = 0; i < lib.size(); i++)
		{
			if(Integer.valueOf(lib.get(i).getKodSotr())!=109909) {
			System.out.println(lib.get(i).getKodSotr());
			}
		}
	}

}
