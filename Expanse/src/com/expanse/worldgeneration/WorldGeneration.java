package com.expanse.worldgeneration;

import com.expanse.celestialobject.CelestialNode;
import com.expanse.celestialobject.GasPlanetaryMassBody;
import com.expanse.celestialobject.StellarMassBody;
import com.expanse.celestialobject.TerrestrialPlanetaryMassBody;
import com.expanse.resources.Resources;

public class WorldGeneration {
	
	public static void generateWorld(){ //a placeholder right now
		CelestialNode centralBody = new StellarMassBody("Sol", new Resources(), 0, 1, 1, false);
		centralBody.addOrbitingObject(new TerrestrialPlanetaryMassBody("Mercury", new Resources(), new Resources(100000, 0, 0, 0), 1, 1, 1, 0, 0, -200));
		centralBody.addOrbitingObject(new TerrestrialPlanetaryMassBody("Venus", new Resources(), new Resources(100000, 0, 0, 0), 1, 1, 1, 0, 0, -200));
		centralBody.addOrbitingObject(new TerrestrialPlanetaryMassBody("Earth", new Resources(), new Resources(100000, 0, 0, 0), 1, 1, 1, 1, 1, 298));
		centralBody.addOrbitingObject(new TerrestrialPlanetaryMassBody("Mars", new Resources(), new Resources(100000, 0, 0, 0), 1, 1, 1, 0, 0, -200));
		centralBody.addOrbitingObject(new GasPlanetaryMassBody("Jupiter", new Resources(), 1, 1, 1));
		centralBody.addOrbitingObject(new GasPlanetaryMassBody("Saturn", new Resources(), 1, 1, 1));
		centralBody.addOrbitingObject(new GasPlanetaryMassBody("Uranus", new Resources(), 1, 1, 1));
		centralBody.addOrbitingObject(new GasPlanetaryMassBody("Neptune", new Resources(), 1, 1, 1));
	}
}
