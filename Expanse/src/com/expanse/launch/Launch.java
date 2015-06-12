package com.expanse.launch;

import java.io.File;

import com.expanse.exception.ModDependencyError;
import com.expanse.exception.ModUnsatisfiedDependencyException;
import com.expanse.graphics.GraphicsManager;
import com.expanse.modloader.ModLoader;

public class Launch {
	
	public static void main(String[] args){
		System.setProperty("org.lwjgl.librarypath", new File("lib/lwjgl/native").getAbsolutePath());
		
		GraphicsManager graphics = new GraphicsManager();
		graphics.run();
		try {
			try {
				ModLoader.loadMods("/Users/chrisjung/mods");
			} catch (ModDependencyError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			ModLoader.preInitMods();
			ModLoader.initMods();
			ModLoader.postInitMods();
		} catch (ModUnsatisfiedDependencyException e) {
			e.printStackTrace();
		}
	}
	
}
