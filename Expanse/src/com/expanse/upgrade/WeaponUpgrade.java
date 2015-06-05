package com.expanse.upgrade;

public class WeaponUpgrade extends Upgrade{
	
	public float multiplierIncrease = 0;//Stacks additively. Applies after flat.
	public int flatDmgIncrease = 0;
	
	//Stored in an ArrayList in the WeaponGroup
	public WeaponUpgrade(float pcntIncrease, int flatIncrease){
		this.multiplierIncrease = pcntIncrease;
		this.flatDmgIncrease = flatIncrease;
	}
	
}
