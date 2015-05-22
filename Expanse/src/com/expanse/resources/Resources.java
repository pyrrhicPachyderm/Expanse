package com.expanse.resources;

public class Resources {
	protected int metal;
	protected int food;
	protected int water;
	protected int fuel;
	protected Population people;
	
	public Resources(int m, int fd, int w, int fl, Population pop){ //constructor with population
		this.metal = m;
		this.food = fd;
		this.water = w;
		this.fuel = fl;
		this.people = pop;
	}
	
	public Resources(int m, int fd, int w, int fl){ //constructor without population
		this.metal = m;
		this.food = fd;
		this.water = w;
		this.fuel = fl;
		this.people = new Population(); //Population = 0
	}
	
	public Resources(){ //default all 0 constructor
		this.metal = 0;
		this.food = 0;
		this.water = 0;
		this.fuel = 0;
		this.people = new Population(); //Population = 0
	}
	
	public void addResources(Resources toAdd){
		this.metal += toAdd.metal;
		this.food += toAdd.food;
		this.water += toAdd.water;
		this.fuel += toAdd.fuel;
		this.people.addPopulation(toAdd.people);
	}
	
	public AffordReturn canAfford(Resources required){
		if(this.metal < required.metal){
			return new AffordReturn(false, "metal");
		}
		if(this.food < required.food){
			return new AffordReturn(false, "food");
		}
		if(this.water < required.water){
			return new AffordReturn(false, "water");
		}
		if(this.fuel < required.fuel){
			return new AffordReturn(false, "fuel");
		}
		return this.people.canAfford(required.people);
	}
	
	public void removeResources(Resources toRemove){
		this.metal += toRemove.metal;
		this.food += toRemove.food;
		this.water += toRemove.water;
		this.fuel += toRemove.fuel;
		this.people.removePopulation(toRemove.people);
	}
}
