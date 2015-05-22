package com.expanse.building;

import java.util.ArrayList;

import com.expanse.resources.Resources;

public class BuildingGroup { //currently unused
	protected ArrayList<Building> buildings = new ArrayList<Building>();
	
	Resources totalCost; //cost to build
	Resources totalRequires; //needs but doesn't consume, e.g. people manning
	Resources totalConsumes; //per turn
	Resources totalProduces; //per turn
	Resources totalStorage; //can store
	//TODO: weapons
	//TODO: sensors
	//TODO: foundries
}
