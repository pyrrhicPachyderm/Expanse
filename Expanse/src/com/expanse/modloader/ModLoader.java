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
	
	@SuppressWarnings("rawtypes")
	public static void loadMods(String path) throws ModUnsatisfiedDependencyException{
		
		List<Class> classes = ModDiscovery.findClassPathMods();
		
		try {
			classes.addAll(ModDiscovery.findExternalMods(path));
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}

		for(int i = 0; i < classes.size(); i++){
			ModProcessor processor = new ModProcessor(classes.get(i));
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
		
		for(int iterator = 0; iterator < futureList.size(); iterator++){
			try {
				IMod currentMod = futureList.get(iterator).get();
				Class clazz = currentMod.getClass();
				Annotation[] annotations = clazz.getAnnotations();
				Mod modAnnotation = null;
				
				for(int it = 0; it < annotations.length; it++){
				    if(annotations[it] instanceof Mod){
				        modAnnotation = (Mod) annotations[it];
				    }
				}
				unsortedList.add(modAnnotation.modID());
				modList.put(modAnnotation.modID(), currentMod);
				ModChecker.addMod(modAnnotation.modID(), currentMod.getDependencies());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		sortedList = ModChecker.sortMods();
	}
	
	public static void preInitMods(){
		for(int iterator = 0; iterator < sortedList.size(); iterator++){
			modList.get(sortedList.get(iterator)).preInit();;
		}
	}
	
	public static void initMods(){
		for(int iterator = 0; iterator < modList.size(); iterator++){
			modList.get(sortedList.get(iterator)).init();;
		}
	}
	
	public static void postInitMods(){
		for(int iterator = 0; iterator < modList.size(); iterator++){
			modList.get(sortedList.get(iterator)).postInit();
		}
	}
	
	public static void addModMetadata(String metadata[]){
		ModRegistry.registerMod(metadata[0], metadata[1], metadata[2]);
	}
}
