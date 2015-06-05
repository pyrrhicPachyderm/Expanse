package com.expanse.ship;

public class Position {
	public int x, y;
	
	public double getDistTo(Position position){
		return Math.hypot(Math.abs(this.x - position.x), Math.abs(this.y - position.y)); 
	}
	
}
