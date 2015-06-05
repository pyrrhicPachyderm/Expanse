package com.expanse.celestialobject;

import com.expanse.resources.Resources;

public class TerrestrialPlanetaryMassBody extends PlanetaryMassBody{
	protected Resources coreResources;
	protected double breathableProportion; //0-1
	protected double atmosphericDensity; //Earth atmospheres
	protected double surfaceTemperature; //Kelvin, optimally 25oC, 298oK?
	//TO simplify things absolute zero is -273oC, ignoring the .15
	
	public TerrestrialPlanetaryMassBody(String name, Resources remainingR, Resources coreR, double orbitalR, double r, double m, double breathP, double atmoDensity, double surfaceTemp){
		this.owningPlayer = -1;
		this.objectName = name;
		this.remainingResources = remainingR;
		this.coreResources = coreR;
		this.orbitalRadius = orbitalR;
		this.radius = r;
		this.mass = m;
		this.breathableProportion = breathP;
		this.atmosphericDensity = atmoDensity;
		this.surfaceTemperature = surfaceTemp;
	}
}
