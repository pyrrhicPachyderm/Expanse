package com.expanse.module;

import java.util.ArrayList;

import com.expanse.upgrade.Upgrade;

public class ModuleGroup {
	public String typeName;
	public String subTypeName;
	public int count;
	public int size;//One weapon/engine etc 
	public int totalSpace = size * count;
	
	public ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();

	
	public void addCount(int countToAdd) {
		count += countToAdd;
		updateStats();
	} 
	
	public void updateStats(){
		totalSpace = size * count;
		//TODO refreshing upgrades?
	}
	
	public int getSpaceUsage(){
		return totalSpace;
	}
}
