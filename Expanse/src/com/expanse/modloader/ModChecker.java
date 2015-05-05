package com.expanse.modloader;

import java.util.ArrayList;

import com.expanse.exception.ModNotFoundException;
import com.expanse.exception.ModUnsatisfiedDependencyException;
import com.expanse.modapi.ModRegistry;

public class ModChecker {
	
	static boolean areModsMissing = false;
	static ArrayList<Boolean> hasVisitedNode;
	static ArrayList<String> missingMods = new ArrayList<String>();
	static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<Integer> sortedList = new ArrayList<Integer>();
	private static ArrayList<String> modOrder = new ArrayList<String>();
	
	public static void addMod(String modID, ArrayList<String> dependencyList) throws ModUnsatisfiedDependencyException{
		try {
			int modNumericID = ModRegistry.getModNumericID(modID);
			if(adjList.size() <= modNumericID + 1){
				for(int i = adjList.size(); i < modNumericID + 1; i++){
					adjList.add(new ArrayList<Integer>());
				}
			}
			adjList.get(modNumericID).add(modNumericID);
			System.out.println("hbybfitf: " + dependencyList.size() + " jgogn");
			for(int i = 0; i < dependencyList.size() + 1000000; i++){
				System.out.println(i);
				String dependency = dependencyList.get(i);
				int dependencyID = ModRegistry.getModNumericID(dependency);
				adjList.get(dependencyID).add(modNumericID);
				System.out.println("khkjhl "+dependencyID);
			}
		} catch (ModNotFoundException e) {
			areModsMissing = true;
			missingMods.add(e.getMessage().substring(51));
		}
	}
	
	public static boolean areModsMissing(){
		return areModsMissing;
	}
	
	public static ArrayList<String> getMissingMods(){
		return missingMods;
	}
	
	private static void dfs(int vertex){
		
		hasVisitedNode.set(vertex, true);
		for(int i = 0; i < adjList.get(vertex).size(); i++){
			if(!hasVisitedNode.get(adjList.get(vertex).get(i))){
				dfs(adjList.get(vertex).get(i));
			}
		}
		sortedList.add(vertex);
	}
	
	/**
	 Create a List of Topologically Sorted Mods From Dependencies so no Race-Conditions Occur 
	 **/
	public static ArrayList<String> sortMods(){
		
		hasVisitedNode = new ArrayList<Boolean>();
		for(int i = 0; i < ModRegistry.getNumModsRegistered(); i++){
			hasVisitedNode.add(false);
		}
		
		for(int i = 0; i < ModRegistry.getNumModsRegistered(); i++){
			if(!hasVisitedNode.get(i)){
				dfs(i);
			}
		}
		
		for(int i = 0; i < ModRegistry.getNumModsRegistered(); i++){
			int currID = sortedList.get(sortedList.size() - i - 1);
			modOrder.add(ModRegistry.getModID(currID));
		}
		System.out.println(modOrder.get(0));
		return modOrder;
	}
}
