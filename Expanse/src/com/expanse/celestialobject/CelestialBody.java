package com.expanse.celestialobject;


public class CelestialBody extends CelestialObject{
	public static String massUnit;
	public static String radiusUnit;

	protected float radius;
	/*
	0.05-220,000 Earth Radii total
	0.05 - 25 Earth Radii for planetary mass
	0.12 - 2000 Solar Radii for stellar mass
	*/
	protected float mass;
	/*
	x - y Earth Masses total
	0.0001 - 10000 Earth Masses for planetary mass
	About 0.0005 Earth Masses for our asteroid belt
	0.08 - 275 Solar Masses for stellar mass
	*/
	
}
