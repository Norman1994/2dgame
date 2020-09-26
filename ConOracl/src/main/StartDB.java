package main;

import Positions.PositionService;

public class StartDB {
	
public static PositionService dep;
	
	public static void main(String[] args){
	dep = new PositionService();
	System.out.println(dep.get(25).getNamePos());
	}

}
