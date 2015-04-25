package com.expanse.modloader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.expanse.modapi.BaseMod;
import com.expanse.modapi.ModRegistry;

public class ModLoader {

	static ExecutorService threadPool = Executors.newFixedThreadPool(64);
	
	static List<Future<BaseMod>> futureList = new ArrayList<Future<BaseMod>>();
	static List<BaseMod> modList = new ArrayList<BaseMod>();
	
	@SuppressWarnings("rawtypes")
	public static void loadMods(String path){
		
		List<Class> classes = ModDiscovery.findClassPathMods();
		
		try {
			classes.addAll(ModDiscovery.findExternalMods(path));
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}

		for(int i = 0; i < classes.size(); i++){
			ModProcessor processor = new ModProcessor(classes.get(i));
			Future<BaseMod> future = threadPool.submit(processor);
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
				modList.add(futureList.get(iterator).get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void preInitMods(){
		for(int iterator = 0; iterator < modList.size(); iterator++){
			modList.get(iterator).preInit();
		}
	}
	
	public static void initMods(){
		for(int iterator = 0; iterator < modList.size(); iterator++){
			modList.get(iterator).init();
		}
	}
	
	public static void postInitMods(){
		for(int iterator = 0; iterator < modList.size(); iterator++){
			modList.get(iterator).postInit();
		}
	}
	
	public static void addModMetadata(String metadata[]){
		ModRegistry.registerMod(metadata[0], metadata[1], metadata[2]);
	}
}
