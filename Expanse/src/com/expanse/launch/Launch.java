package com.expanse.launch;

import java.io.File;

import com.expanse.exception.ModDependencyError;
import com.expanse.exception.ModUnsatisfiedDependencyException;
import com.expanse.graphics.GraphicsManager;
import com.expanse.maths.Matrix3f;
import com.expanse.maths.Matrix4f;
import com.expanse.modloader.ModLoader;

public class Launch {
	
	public static void main(String[] args){
		System.setProperty("org.lwjgl.librarypath", new File("lib/lwjgl/native").getAbsolutePath());
		
		Matrix3f mat = new Matrix3f(
					1, 2, 3,
					0, 4, 5,
					1, 0, 6
				);
		Matrix3f inv = Matrix3f.inverse(mat);
		Matrix3f ide = Matrix3f.multiply(mat, inv);
		
		Matrix4f mat2 = new Matrix4f(
				
						1, 3, -2, 1,
						5, 1, 0, -1,
						0, 1, 0, -2,
						2, -1, 0, 3
				
				);
		
		float det = Matrix4f.determinant(mat2);
		
		Matrix4f mat3 = Matrix4f.inverse(mat2);
		Matrix4f iden = Matrix4f.multiply(mat2, mat3);
		
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
