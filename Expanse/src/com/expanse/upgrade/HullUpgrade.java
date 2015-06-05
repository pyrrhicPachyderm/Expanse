package com.expanse.upgrade;

public class HullUpgrade {

	public float multiplierIncrease = 1;//Stacks additively. Applies after flat.
	public int flatHIIncrease = 0;
	
	//Stored in an ArrayList in the Ship
	public HullUpgrade(float pcntIncrease, int flatIncrease){
			this.multiplierIncrease = pcntIncrease;
			this.flatHIIncrease = flatIncrease;
	}
	
	public static HullUpgrade chromeRims = new HullUpgrade(0, 420);
	public static HullUpgrade purpleChromePaint = new HullUpgrade(0.042f, 0);
	
}
