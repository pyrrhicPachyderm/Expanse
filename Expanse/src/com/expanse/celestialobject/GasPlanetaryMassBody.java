package com.expanse.celestialobject;

import com.expanse.resources.Resources;

public class GasPlanetaryMassBody extends PlanetaryMassBody{
	
	public GasPlanetaryMassBody(String name, Resources remainingR, float orbitalR, float r, float m){
		this.owningPlayer = -1;
		this.objectName = name;
		this.remainingResources = remainingR;
		this.orbitalRadius = orbitalR;
		this.radius = r;
		this.mass = m;
	}
}
