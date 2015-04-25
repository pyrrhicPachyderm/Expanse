package com.expanse.modloader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.Callable;

@SuppressWarnings("rawtypes")
public class CallableClassLoader implements Callable<Class>{

	private Class clazz;
	private File file;
	private boolean isInClassPath;
  
	public CallableClassLoader(File filesToBeLoaded, boolean areFilesInClassPath){
		
		file = filesToBeLoaded;
		isInClassPath = areFilesInClassPath;
		
	}

	@Override
	public Class call() throws Exception {

			try {
				if(isInClassPath){
					
					String fileName = file.getAbsolutePath();
					fileName = fileName.substring((System.getProperty("user.dir") + "/bin").length() + 1, fileName.length() - 6);
			        fileName = fileName.replace('/', '.');
					clazz = loadClass(fileName, file.getAbsolutePath());
					
				}
				else{
					
					clazz = loadClass(file.getName(), file.getAbsolutePath());
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return clazz;
	}
	
	private Class loadClass(String className, String path) throws Exception{
		
		URLClassLoader loader = new URLClassLoader(new URL[] {
	            new URL("file://" + path)
	    });
	    Class loadedClass = loader.loadClass(className);
	    loader.close();
	    return loadedClass;
		
	}
	
	
}