package com.expanse.launch;

import java.io.File;

import com.expanse.exception.ModDependencyError;
import com.expanse.exception.ModUnsatisfiedDependencyException;
import com.expanse.modloader.ModLoader;

public class Launch {
	
	public static void main(String[] args){
		System.setProperty("org.lwjgl.librarypath", new File("lib/lwjgl/native").getAbsolutePath());
		
		Thread modloaderThread = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					ModLoader.loadMods("/Users/chrisjung/mods");
					ModLoader.preInitMods();
					ModLoader.initMods();
					ModLoader.postInitMods();
				} catch (ModUnsatisfiedDependencyException e) {
					e.printStackTrace();
				} catch (ModDependencyError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});

		modloaderThread.start();
	}
	
}