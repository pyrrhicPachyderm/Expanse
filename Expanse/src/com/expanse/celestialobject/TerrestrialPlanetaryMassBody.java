package com.expanse.celestialobject;

import com.expanse.resources.Resources;

public class TerrestrialPlanetaryMassBody extends PlanetaryMassBody{
	protected Resources coreResources;
	protected float breathableProportion; //0-1
	protected float atmosphericDensity; //Earth atmospheres
	protected float surfaceTemperature; //Kelvin, optimally 25oC, 298oK?
	//TO simplify things absolute zero is -273oC, ignoring the .15
	
	public TerrestrialPlanetaryMassBody(String name, Resources remainingR, Resources coreR, float orbitalR, float r, float m, float breathP, float atmoDensity, float surfaceTemp){
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
