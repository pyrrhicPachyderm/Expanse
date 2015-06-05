package com.expanse.celestialobject;

import com.expanse.resources.Resources;

public class BeltObject extends CelestialObject{
	
	protected double orbitThickness;
	
	public BeltObject(String name, Resources remainingR, double orbitalR, double orbitalT){
		this.owningPlayer = -1;
		this.objectName = name;
		this.remainingResources = remainingR;
		this.orbitalRadius = orbitalR;
		this.orbitThickness = orbitalT;
	}
}
