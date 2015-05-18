package com.expanse.modloader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import com.expanse.exception.ModDependencyError;
import com.expanse.exception.ModNotFoundException;
import com.expanse.exception.ModUnsatisfiedDependencyException;
import com.expanse.modapi.ModRegistry;

public class ModChecker {
	
	static boolean areModsMissing = false;
	static ArrayList<String> missingMods = new ArrayList<String>();
	static ArrayList<ArrayList<Integer>> adjList;
	private static ArrayList<String> modOrder = new ArrayList<String>();
	
	public static void init(){
		adjList = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < ModRegistry.getNumModsRegistered(); i++){
			adjList.add(new ArrayList<Integer>());
		}
	}
	
	public static void addMod(String modID, ArrayList<String> dependencyList) throws ModUnsatisfiedDependencyException{
		try {
			int modNumericID = ModRegistry.getModNumericID(modID);
			
			for(String dependency : dependencyList){
				int dependencyID = ModRegistry.getModNumericID(dependency);
				adjList.get(dependencyID).add(modNumericID);
			}
			
			for(int i = 0; i < dependencyList.size(); i++){
				String dependency = dependencyList.get(i);
				int dependencyID = ModRegistry.getModNumericID(dependency);
				adjList.get(dependencyID).add(modNumericID);
			}
		} catch (ModNotFoundException e) {
			areModsMissing = true;
			missingMods.add(e.getMessage().substring(37));
		}
	}
	
	public static boolean areModsMissing(){
		return areModsMissing;
	}
	
	public static ArrayList<String> getMissingMods(){
		return missingMods;
	}
	
	/**
	 Create a List of Topologically Sorted Mods From Dependencies so no Race-Conditions Occur 
	 * @throws ModDependencyError 
	 **/
	public static ArrayList<String> sortMods() throws ModDependencyError{
		ArrayList<Boolean> hasVisitedNode = new ArrayList<Boolean>();
		ArrayList<Integer> indegrees = new ArrayList<Integer>();
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		for(int i = 0; i < adjList.size(); i++){
			indegrees.add(0);
			hasVisitedNode.add(false);
		}
		
		for(int i = 0; i < ModRegistry.getNumModsRegistered(); i++){
			if(hasVisitedNode.get(i)){
				continue;
			}
			q.add(i);
			while(!q.isEmpty()){
				int currVertex = q.remove();
				for(int j = 0; j < adjList.get(currVertex).size(); j++){
					int vertex = adjList.get(currVertex).get(j);
					if(!hasVisitedNode.get(vertex)){
						indegrees.set(vertex, indegrees.get(vertex) + 1);
						hasVisitedNode.set(vertex, true);
						q.add(vertex);
					}
				}
			}
		}
		
		for(int i = 0; i < indegrees.size(); i++){
			hasVisitedNode.set(i, false);
			if(indegrees.get(i) == 0){
				q.add(i);
				hasVisitedNode.set(i, true);
			}
		}
		
		if(q.isEmpty() && ModRegistry.getNumModsRegistered() != 0){
			throw new ModDependencyError();
		}
		
		while(!q.isEmpty()){
			int currVertex = q.remove();
			modOrder.add(ModRegistry.getModID(currVertex));
			for(int i = 0; i < adjList.get(currVertex).size(); i++){
				int vertex = adjList.get(currVertex).get(i);
				if(!hasVisitedNode.get(vertex)){
					indegrees.set(vertex, indegrees.get(vertex) - 1);
					if(indegrees.get(adjList.get(currVertex).get(i)) == 0){
						q.add(adjList.get(currVertex).get(i));
					}
				}
			}
		}
		if(modOrder.size() != ModRegistry.getNumModsRegistered()){
			throw new ModDependencyError();
		}
		return modOrder;
	}
}
