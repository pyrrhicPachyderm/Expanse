package com.expanse.modloader;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@SuppressWarnings("rawtypes")
public class ExternalClassLoader implements Callable<List<Class>>{

	String path;
	List<Class> classes = new ArrayList<Class>();
	
	public ExternalClassLoader(String path) {
		this.path = path;
	}
	
	@Override
	public List<Class> call() throws Exception {

		File file = new File(path);
		JarFileLoader loader = new JarFileLoader(new URL[]{file.toURI().toURL()});
		loader.addURL(file.toURI().toURL());
		
	    URL jar = file.toURI().toURL();
	    ZipInputStream zip = new ZipInputStream( jar.openStream());
	    ZipEntry ze = null;
	    
	    ExecutorService threadPool = Executors.newFixedThreadPool(128);
	    JarClassLoader classLoader = new JarClassLoader(ze, loader);
	    List<Future<List<Class>>> futures = new ArrayList<Future<List<Class>>>();

	    while( ( ze = zip.getNextEntry() ) != null ) {
	    	
	    	Future<List<Class>> future = threadPool.submit(classLoader);
	    	futures.add(future);
	    	
	    }
	    
	    for(int iterator = 0; iterator < futures.size(); iterator++){
	    	
	    	classes.addAll(futures.get(iterator).get());
	    	
	    }
	    
		return classes;
		
	}

}
