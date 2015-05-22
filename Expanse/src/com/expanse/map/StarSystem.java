package com.expanse.map;

import com.expanse.celestialobject.CelestialNode;
import com.expanse.position.Position;

public class StarSystem {
	public Position position;
	public CelestialNode centralNode;
	public String name;
	
	
	public StarSystem(int x, int y, CelestialNode centre, String n){
		this.position = new Position(x, y);
		this.centralNode = centre;
	}
	
}
