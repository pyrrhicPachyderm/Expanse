package com.expanse.celestialobject;

import com.expanse.resources.Resources;

public class BeltObject extends CelestialObject{
	
	protected float orbitThickness;
	
	public BeltObject(String name, Resources remainingR, float orbitalR, float orbitalT){
		this.owningPlayer = -1;
		this.objectName = name;
		this.remainingResources = remainingR;
		this.orbitalRadius = orbitalR;
		this.orbitThickness = orbitalT;
	}
}
