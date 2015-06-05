package com.expanse.fleet;

import java.util.ArrayList;
import java.util.Collections;

import com.expanse.ship.Ship;

public class Fleet {

	public ArrayList<Ship> ships = new ArrayList<Ship>();
	//TODO ArrayList<Resources>
	
	public void addShip(Ship ship){
		ships.add(ship);
	}
	
	public void destroyShip(int index){ //TODO differentiate from removeShip
		Collections.swap(ships, index, ships.size() - 1);
		ships.remove(ships.size() - 1);
	}
	
	public void removeShip(int index){ //TODO differentiate from DestroyShip
		Collections.swap(ships, index, ships.size() - 1);
		ships.remove(ships.size() - 1);
	}
	
	public void changeResources(){
		//TODO
	}
	
	public void getResources(){
		//TODO
	}
	
	public void getResourceCap(){
		//TODO
	}
	
}
