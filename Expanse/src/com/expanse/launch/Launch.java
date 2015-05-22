package com.expanse.launch;

import java.io.File;

public class Launch {
	
	public static void main(String[] args){
		System.setProperty("org.lwjgl.librarypath", new File("lib/natives").getAbsolutePath());
		//ModLoader.initMods();
	}
	
}
