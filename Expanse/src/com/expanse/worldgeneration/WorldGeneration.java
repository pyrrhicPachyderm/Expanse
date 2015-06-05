package com.expanse.worldgeneration;

import com.expanse.celestialobject.CelestialNode;
import com.expanse.celestialobject.GasPlanetaryMassBody;
import com.expanse.celestialobject.StellarMassBody;
import com.expanse.celestialobject.TerrestrialPlanetaryMassBody;
import com.expanse.resources.Resources;

public class WorldGeneration {
	
	public static void generateWorld(){ //a placeholder right now
		//name, resources(, coreResources), orbitR, r, m(, breathP, atmoD, surfaceTemp)(, isWormhole)
		CelestialNode centralBody = new StellarMassBody("Sol", new Resources(), 0, 1, 1, false);
		centralBody.addOrbitingObject(new TerrestrialPlanetaryMassBody("Mercury", new Resources(), new Resources(100000, 0, 0, 0), 0.4, 0.383, 0.055, 0, 0, 180));
		centralBody.addOrbitingObject(new TerrestrialPlanetaryMassBody("Venus", new Resources(), new Resources(100000, 0, 0, 0), 0.7, 0.95, 0.815, 0.027, 92, 200));
		centralBody.addOrbitingObject(new TerrestrialPlanetaryMassBody("Earth", new Resources(), new Resources(100000, 0, 0, 0), 1, 1, 1, 1, 1, 298));
		centralBody.addOrbitingObject(new TerrestrialPlanetaryMassBody("Mars", new Resources(), new Resources(100000, 0, 0, 0), 1.5, 1, 1, 0, 0, 200));
		centralBody.addOrbitingObject(new GasPlanetaryMassBody("Jupiter", new Resources(), 50, 300, 300));
		centralBody.addOrbitingObject(new GasPlanetaryMassBody("Saturn", new Resources(), 100, 200, 100));
		centralBody.addOrbitingObject(new GasPlanetaryMassBody("Uranus", new Resources(), 150, 100, 75));
		centralBody.addOrbitingObject(new GasPlanetaryMassBody("Neptune", new Resources(), 200, 150, 50));
	}
}
