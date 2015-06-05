package com.expanse.ship;

public class Fighter extends ShipSquad{

	public Fighter(String name, int x, int y) {
		this.name = name;
		this.typeName = "Fighter";
		this.count = 8;
		this.healthPerShip = 600;
		for(int i = 0; i < count; i++){
			SubShip temp = new SubShip(this.healthPerShip);
			subships.add(temp);
		}
		this.isSquad = true;
		this.position.x = x;
	    this.position.y = y;
	}

}
