package com.expanse.modloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ModDiscovery {
	
	static ModDiscovery discovery = new ModDiscovery();
	
	@SuppressWarnings("rawtypes")
	public static List<Class> findClassPathMods(){
		
		ExecutorService threadPool = Executors.newFixedThreadPool(128);
		
		List<File> files = findClassesInDirectory(System.getProperty("user.dir"));
		List<Class> classes = new ArrayList<Class>();
		List<Future<Class>> futures = new ArrayList<Future<Class>>();
		for(int i = 0; i < files.size(); i++){
			
			CallableClassLoader loader = new CallableClassLoader(files.get(i), true);
			Future<Class> future = threadPool.submit(loader);
			
			futures.add(future);
			
		}
		
		for(int i = 0; i < futures.size(); i++){
			
			try {
				classes.add(futures.get(i).get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			
		}
		
		threadPool.shutdown();
		
		return classes;
		
	}
	
	@SuppressWarnings({ "rawtypes" })
	public static List<Class> findExternalMods(String path) throws IOException, ClassNotFoundException{

		List<String> jars = findJars(path);
		List<Class> classes = new ArrayList<Class>();
		
		for(int iterator = 0; iterator < jars.size(); iterator++){
			
			if(jars.get(iterator).endsWith(".jar") || jars.get(iterator).endsWith(".zip")){
				classes.addAll(loadClassesFromJar(jars.get(iterator)));
			}
			
		}
		
		return classes;
		
	}
	
	@SuppressWarnings({ "rawtypes", "resource" })
	private static List<Class> loadClassesFromJar(String path) throws IOException, ClassNotFoundException{
		
		List<Class> classes = new ArrayList<Class>();
		
		if( path != null && path.endsWith(".jar") || path.endsWith(".zip")) {

			File file = new File(path);
			JarFileLoader loader = new JarFileLoader(new URL[]{file.toURI().toURL()});
			loader.addURL(file.toURI().toURL());
			
		    URL jar = file.toURI().toURL();
		    ZipInputStream zip = new ZipInputStream( jar.openStream());
		    ZipEntry ze = null;

		    while( ( ze = zip.getNextEntry() ) != null ) {
		        String entryName = ze.getName();
		        if( entryName.endsWith(".class") ) {
		        	entryName = entryName.replace('/', '.');
		        	entryName = entryName.substring(0, entryName.length() - 6);
		        	
		    		classes.add(loader.loadClass(entryName));
		        }
		    }

		 }
		
		return classes;
		
	}
	
	private static List<String> findJars(String path) throws IOException{
		
		File directory = new File(path);

        List<String> resultList = new ArrayList<String>();
        File[] fList = null;
        
        if(directory.listFiles() != null){
        	fList = directory.listFiles();
        	
            for (int i = 0; i < fList.length; i++) {
                if (fList[i].isFile() && fList[i].getName().endsWith(".jar") || fList[i].getName().endsWith(".zip")) {
                	
                    resultList.add(fList[i].getAbsolutePath());
                    
                } else if (fList[i].isDirectory()) {
                    resultList.addAll(findJars(fList[i].getAbsolutePath()));
                }
            }
        	
        }
        
        return resultList;
		
	}
	
	private static List<File> findClassesInDirectory(String directoryName){
		
		File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();
        File[] fList = null;
        
        if(directory.listFiles() != null){
        	fList = directory.listFiles();
        	
            for (int i = 0; i < fList.length; i++) {
                if (fList[i].isFile() && fList[i].getName().endsWith(".class")) {
                    resultList.add(fList[i]);
                    
                } else if (fList[i].isDirectory()) {
                    resultList.addAll(findClassesInDirectory(fList[i].getAbsolutePath()));
                }
            }
        	
        }
        
        return resultList;
        
	}
	
}
