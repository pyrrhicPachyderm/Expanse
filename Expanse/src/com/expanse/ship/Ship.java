package com.expanse.ship;

import java.util.ArrayList;
import java.util.Collections;

import com.expanse.module.weapon.RailgunGroup;
import com.expanse.module.weapon.WeaponGroup;
import com.expanse.upgrade.HullUpgrade;

public class Ship {
	
	public ArrayList<WeaponGroup> weapons = new ArrayList<WeaponGroup>();
	public ArrayList<HullUpgrade> hullUpgrades = new ArrayList<HullUpgrade>();

	public String typeName;
    protected String name;
    protected double acc = 0.7; //Hit chance
    protected int combatSpeed; //Movement distance per combat turn
    protected int maxHealth;
    protected int baseHealth;
    protected int currentHealth;
    Position position = new Position();
    public boolean isSquad = false;
	public int count = 1;//Only used for squads of ships like fighters etc.

    public void addWeaponGroup(WeaponGroup group){
        weapons.add(group);
    }
    
    public void addWeaponGroup(int count, int dmg){ //Temporary
    	RailgunGroup group = new RailgunGroup();
    	group.count = count;
    	group.setBaseDmg(dmg);
        weapons.add(group);
    }
    
    public void removeWeaponGroup(int index){
    	Collections.swap(weapons, index, weapons.size() - 1);
		weapons.remove(weapons.size() - 1);
    }
    
    public void setPosition(int x, int y){
        position.x = x;
        position.y = y;
    }
    
    public void decrHealth(int health){
        currentHealth -= health;
        if(currentHealth < 0){
        	currentHealth = 0;
        }
    }
    
    public void addHullUpgrade(HullUpgrade upgrade){
        hullUpgrades.add(upgrade);
    	//Refreshing stats
    	maxHealth = baseHealth;
		float multiplier = 1;
		for(int i = 0; i < hullUpgrades.size(); i++){
			multiplier += hullUpgrades.get(i).multiplierIncrease;
			maxHealth += hullUpgrades.get(i).flatHIIncrease;
		}
		float tempHI = maxHealth * multiplier;
		maxHealth = (int)tempHI;
		currentHealth = maxHealth;
    }
    
    
    public int getHealth(){
        return currentHealth;
    }
    
    public Position getPosition(){
        return position;
    }
    
    public int getSpeed(){
        return combatSpeed;
    }
    
    public String getNameAndType(){
        return name + " (" + typeName + ")";     
    }
    
    public double getAcc(){    	
        return acc;     
    }

	public boolean damageSubship(int dmg) {
		System.out.println("ERROR: Trying to damage single ship as group of ships");
		return false;		
	}
}


