package com.expanse.modloader;

import java.net.URL;
import java.net.URLClassLoader;

public class JarFileLoader extends URLClassLoader{
	
	public JarFileLoader (URL[] urls)  
    {  
        super (urls);  
    }  
  
    public void addURL(URL path)  
    {  
    	System.out.println(path);
        super.addURL(path);
    }  
    
}