package com.expanse.module.weapon;

import java.util.ArrayList;

import com.expanse.module.ModuleGroup;
import com.expanse.upgrade.WeaponUpgrade;

public class WeaponGroup extends ModuleGroup{
	
	protected int baseDmg; //One weapon w/o multipliers
	protected int dmg; //One weapon w/ multipliers
	protected float multiplier = 1;
	protected int dmgType; 	/*0 = kinetic
							  1 = thermal
							  2 = explosive
							  3 = annihilation
	 						*/
	
	public ArrayList<WeaponUpgrade> upgrades = new ArrayList<WeaponUpgrade>();

	
	public void setBaseDmg(int newDmg){
		baseDmg = newDmg;
		updateStats();
	}
	
	public void updateStats(){
		dmg = baseDmg;
		multiplier = 1;
		for(int i = 0; i < upgrades.size(); i++){
			multiplier += upgrades.get(i).multiplierIncrease;
			dmg += upgrades.get(i).flatDmgIncrease;
			size += upgrades.get(i).sizeModifier;
			if(this.size <= 0){
				size = 1;
			}
		}
		totalSpace = size * count;
		float tempDmg = dmg * multiplier;
		dmg = (int)tempDmg;
	}
	
	public int getDamage(){
		return dmg;
	}
}