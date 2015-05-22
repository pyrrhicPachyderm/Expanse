package com.expanse.celestialobject;

import java.util.ArrayList;

import com.expanse.building.Building;
import com.expanse.resources.Resources;

public class CelestialObject extends CelestialNode{
	protected int owningPlayer; //-1 for unclaimed
	protected String objectName;
	protected Resources remainingResources;
	
	//public int parentIndex;
	
	protected ArrayList<Building> buildings = new ArrayList<Building>();
	/*protected Resources storageCap;
	protected Resources stored;*/
	
}
