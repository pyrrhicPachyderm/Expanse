package com.expanse.celestialobject;

import java.util.ArrayList;

public class CelestialNode {
	public static final String orbitalRadiusUnit = "AU";
	
	protected double orbitalRadius; //in AU
	
	protected ArrayList<CelestialObject> orbitingObjects = new ArrayList<CelestialObject>(); //This should be maintained in order of ascending orbitalRadius
	
	public void addOrbitingObject(CelestialBody newObject){
		for(int i = 0; i < orbitingObjects.size(); i++){
			if(orbitingObjects.get(i).orbitalRadius > newObject.orbitalRadius){
				orbitingObjects.add(i, newObject); //displace the first object with greater orbital radius
				return;
			}
		}
		orbitingObjects.add(newObject); //if no object has a larger orbital radius, add it to the end
	}
	
	public void removeOrbitingObject(int objectIndex){
		orbitingObjects.remove(objectIndex); //avoid swapping to maintain sorted order
	}
}
