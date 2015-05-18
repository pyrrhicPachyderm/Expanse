package com.expanse.modloader;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.expanse.exception.ModDependencyError;
import com.expanse.exception.ModUnsatisfiedDependencyException;
import com.expanse.modapi.IMod;
import com.expanse.modapi.Mod;
import com.expanse.modapi.ModRegistry;

public class ModLoader {

	static ExecutorService threadPool = Executors.newFixedThreadPool(64);
	
	static List<Future<IMod>> futureList = new ArrayList<Future<IMod>>();
	static ArrayList<String> sortedList;
	static ArrayList<String> unsortedList = new ArrayList<String>();
	static TreeMap<String, IMod> modList = new TreeMap<String, IMod>();
	static ArrayList<IMod> sortedModList = new ArrayList<IMod>();
	
	@SuppressWarnings("rawtypes")
	public static void loadMods(String path) throws ModUnsatisfiedDependencyException, ModDependencyError{
		
		List<Class> classes = ModDiscovery.findClassPathMods();
		
		try {
			classes.addAll(ModDiscovery.findExternalMods(path));
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		
		for(Class currentClass : classes){
			ModProcessor processor = new ModProcessor(currentClass);
			Future<IMod> future = threadPool.submit(processor);
			try {
				if(future.get() != null){
					futureList.add(future);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}	
		}
		
		ModChecker.init();
		
		for(Future<IMod> currentFuture : futureList){
			IMod currentMod;
			try {
				currentMod = currentFuture.get();
				Class clazz = currentMod.getClass();
				Annotation[] annotations = clazz.getAnnotations();
				Mod modAnnotation = null;
				
				for(Annotation currentAnnotation : annotations){
					if(currentAnnotation instanceof Mod){
						modAnnotation = (Mod) currentAnnotation;
					}
				}
				unsortedList.add(modAnnotation.modID());
				modList.put(modAnnotation.modID(), currentMod);
				ModChecker.addMod(modAnnotation.modID(), currentMod.getDependencies());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		threadPool.shutdown();
		if(ModChecker.areModsMissing){
			System.out.println("Missing Mods Were Found:\n");
			String missingModsList = "";
			boolean firstTry = true;
			for(String mod : ModChecker.getMissingMods()){
				if(firstTry){
					System.out.println("	" + mod);
					missingModsList.concat(mod);
					continue;
				}
				System.out.println("	" + mod);
				missingModsList.concat(", " + mod);
			}
			throw new ModUnsatisfiedDependencyException(missingModsList);
		}
		sortedList = ModChecker.sortMods();
		for(String currentID : sortedList){
			sortedModList.add(modList.get(currentID));
		}
	}
	
	public static void preInitMods(){
		for(IMod currentMod : sortedModList){
			currentMod.preInit();
		}
	}
	
	public static void initMods(){
		for(IMod currentMod : sortedModList){
			currentMod.init();
		}
	}
	
	public static void postInitMods(){
		for(IMod currentMod : sortedModList){
			currentMod.postInit();
		}
	}
	
	public static void addModMetadata(String metadata[]){
		ModRegistry.registerMod(metadata[0], metadata[1], metadata[2]);
	}
}
