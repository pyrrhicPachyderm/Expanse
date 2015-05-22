package com.expanse.building;

import com.expanse.resources.Resources;

public class Building {
	String name;
	String description;
	Resources cost; //cost to build
	Resources requires; //needs but doesn't consume, e.g. people manning
	Resources consumes; //per turn
	Resources produces; //per turn
	Resources storage; //can store
	//TODO: weapons
	//TODO: sensors
	//TODO: foundries
	
}
