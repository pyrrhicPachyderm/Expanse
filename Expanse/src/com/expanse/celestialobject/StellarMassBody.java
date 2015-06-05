package com.expanse.celestialobject;

import com.expanse.resources.Resources;


public class StellarMassBody extends CelestialBody{
	public static final String massUnit = "Solar Masses";
	public static final String radiusUnit = "Solar Radii";
	
	public boolean isWormhole;
	
	public StellarMassBody(String name, Resources remainingR, double orbitalR, double r, double m, boolean isW){
		this.owningPlayer = -1;
		this.objectName = name;
		this.remainingResources = remainingR;
		this.orbitalRadius = orbitalR;
		this.radius = r;
		this.mass = m;
		this.isWormhole = isW;
	}
	
	public double getLuminosity(){ //in solar luminosities
		if(radius == 0){ //black hole
			return 0;
		}
		return mass * radius; //TODO: fix this
	}
}
