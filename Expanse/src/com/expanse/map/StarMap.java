package com.expanse.map;

import java.util.ArrayList;
import java.util.Collections;

public class StarMap {
	protected ArrayList<StarSystem> systems = new ArrayList<StarSystem>();
	
	public void addSystem(StarSystem newSystem){
		this.systems.add(newSystem);
	}
	
	public void removeSystem(int systemIndex){
		Collections.swap(this.systems, systemIndex, this.systems.size()-1);
		this.systems.remove(this.systems.size()-1);
	}
}
