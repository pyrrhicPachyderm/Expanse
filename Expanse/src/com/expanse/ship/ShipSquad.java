package com.expanse.ship;

import java.util.ArrayList;

public class ShipSquad extends Ship{
	
	int healthPerShip;
    public ArrayList<SubShip> subships = new ArrayList<SubShip>();
	
	public void decrHealth(int health){
		System.out.println("ERROR: Trying to decrease health of fighter squadron");
    }
	
	public boolean damageSubship(int dmg){
		if(count > 0){
			int targetSubship = (int)(Math.random() * subships.size());
		    subships.get(targetSubship).health -= dmg;
		    if(subships.get(targetSubship).health <= 0){
		    	subships.remove(targetSubship);
		    	count --;
		    	return true;
		    }
		}
	    return false;
	}
	
	public String getNameAndType(){
        return name + " (" + typeName + " [" + count + "])";     
    }
    
}
