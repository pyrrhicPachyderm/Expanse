package com.expanse.player;

import java.util.ArrayList;

import com.expanse.fleet.Fleet;

public class Player {
	
	public String username;
	//TODO Faction, colour?
	
	public ArrayList<Fleet> fleets = new ArrayList<Fleet>();
	//TODO Planets ArrayList
	
	public void addFleet(Fleet fleet){
		fleets.add(fleet);
	}
	
	public void removeFleet(int index){
		//Collections.swap(fleets, index, fleets.size() - 1); Not using swap & remove to preserve the order of fleets for the player.
		//fleets.remove(fleets.size() - 1);
		fleets.remove(index);
	}
}
