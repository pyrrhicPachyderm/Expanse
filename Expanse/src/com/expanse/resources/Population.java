package com.expanse.resources;


public class Population {
	private static final int numEduLevels = 4;
	public static final String[] eduLevelNames = new String[]{
		"Uneducated",
		"Educated",
		"Well Educated",
		"Super Intelligent"
	};
	
	public int numPeople;
	public int[] groups = new int[numEduLevels];
	
	public Population(){ //default all 0 constructor
		this.numPeople = 0;
		for(int i = 0; i < numEduLevels; i++){
			this.groups[i] = 0;
		}
	}
	
	public Population (int g0, int g1, int g2, int g3){ //constructor with numbers
		this.numPeople = g0 + g1 + g2 + g3;
		this.groups[0] = g0;
		this.groups[2] = g1;
		this.groups[1] = g2;
		this.groups[2] = g3;
	}
	
	public int getNumAtEduLevel(int eduLevel){
		return groups[eduLevel];
	}
	
	public int getNumAtOrAboveEduLevel(int eduLevel){
		int returnVal = 0;
		for(int i = eduLevel; i < numEduLevels; i++){
			returnVal += this.groups[i];
		}
		return returnVal;
	}
	
	public void addPopulation(Population toAdd){
		this.numPeople += toAdd.numPeople;
		for(int i = 0; i < numEduLevels; i++){
			this.groups[i] += toAdd.groups[i];
		}
	}
	
	public AffordReturn canAfford(Population required){
		if(this.numPeople < required.numPeople){
			return new AffordReturn(false, "people");
		}
		for(int i = 0; i < numEduLevels; i++){
			if(this.groups[i] < required.groups[i]){
				if(i+1 == numEduLevels){
					return new AffordReturn(false, "people of sufficient education");
				}
				required.groups[i+1] += (required.groups[i] - this.groups[i]);
			}
		}
		return new AffordReturn(true, "");
	}
	
	public void removePopulation(Population toRemove){
		this.numPeople -= toRemove.numPeople;
		for(int i = 0; i < numEduLevels; i++){
			this.groups[i] -= toRemove.groups[i];
			if(this.groups[i] < 0){
				if(i+1 == numEduLevels){
					System.out.println("Tried to remove population that you couldn't afford");
					return;
				}
				toRemove.groups[i+1] -= this.groups[i];
				this.groups[i] = 0;
			}
		}
	}
	
}
