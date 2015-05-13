package com.expanse.modapi;

import java.util.TreeMap;

import com.expanse.exception.ModNotFoundException;

public class ModRegistry {
	
	static int numModsRegistered = 0;
	static TreeMap<String, Integer> modNumericIDs = new TreeMap<String, Integer>();
	static TreeMap<Integer, String> modIDs = new TreeMap<Integer, String>();
	static TreeMap<Integer, String> modNames = new TreeMap<Integer, String>();
	static TreeMap<String, String> versions = new TreeMap<String, String>();
	
	public static void registerMod(String modID, String version, String modName){
		modNumericIDs.put(modID, numModsRegistered);
		modIDs.put(numModsRegistered, modID);
		versions.put(modID, version);
		modNames.put(numModsRegistered, modName);
		numModsRegistered++;
	}
	
	public static boolean doesVersionMeetMin(String minimumVersion, String modID) throws ModNotFoundException{
		if(!(getModVersion(modID).compareTo(minimumVersion) < 0)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean doesModExist(String modID){
		return modNumericIDs.containsKey(modID);
	}
	
	public static String getModID(int numericID){
		return modIDs.get(numericID);
	}
	
	public static int getModNumericID(String modID) throws ModNotFoundException{
		if(!doesModExist(modID)){
			throw new ModNotFoundException("Mod Not Found: "+modID);
		}
		return modNumericIDs.get(modID);
	}
	
	public static String getModVersion(String modID) throws ModNotFoundException{
		if(!doesModExist(modID)){
			throw new ModNotFoundException("Mod Not Found: "+modID);
		}
		return versions.get(modID);
	}
	
	public static String getModName(String modID) throws ModNotFoundException{
		if(!doesModExist(modID)){
			throw new ModNotFoundException("Mod Not Found: "+modID);
		}
		return modNames.get(modNumericIDs.get(modID));
	}
	
	public static int getNumModsRegistered(){
		return numModsRegistered;
	}
}
