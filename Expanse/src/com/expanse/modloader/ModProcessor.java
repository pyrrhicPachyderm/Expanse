package com.expanse.modloader;

import java.lang.annotation.Annotation;
import java.util.concurrent.Callable;

import com.expanse.exception.ModDuplicateException;
import com.expanse.exception.ModMissingAnnotationException;
import com.expanse.modapi.IMod;
import com.expanse.modapi.Mod;
import com.expanse.modapi.ModRegistry;

public class ModProcessor implements Callable<IMod>{

	@SuppressWarnings("rawtypes")
	Class classToBeLoaded;
	
	@SuppressWarnings("rawtypes")
	public ModProcessor(Class classToBeLoaded){
		this.classToBeLoaded = classToBeLoaded;
	}
	
	@Override
	public IMod call() throws ModDuplicateException, ModMissingAnnotationException{
		
		IMod result = null;
		Annotation[] annotations = this.classToBeLoaded.getAnnotations();
		Mod modAnnotation = null;
		
		for(int iterator = 0; iterator < annotations.length; iterator++){
		    if(annotations[iterator] instanceof Mod){
		        modAnnotation = (Mod) annotations[iterator];
		    }
		}
		
		if(!this.classToBeLoaded.isInterface() && modAnnotation != null){
			if(ModRegistry.doesModExist(modAnnotation.modID())){
				throw new ModDuplicateException("Duplicate Mod Detected! Remove One Version Of: \"" + modAnnotation.modName() + "\"");
			}
			
			ModLoader.addModMetadata(new String[]{
					modAnnotation.modID(),
					modAnnotation.modName(),
					modAnnotation.modVersion()
			});
			
			Object obj = null;
			try {
				obj = this.classToBeLoaded.newInstance();
				if(obj instanceof IMod){
					result = (IMod) obj;
				}
			} catch (InstantiationException e) {
				System.out.println("Skipping non-mod class");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}else{
			
			Object instance = null;
			try {
				instance = this.classToBeLoaded.newInstance();
			} catch (InstantiationException e) {
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if(instance != null){
				
				if(instance instanceof IMod){
					throw new ModMissingAnnotationException("Candidate Mod: " + classToBeLoaded.getName() + " Is Missing Necessary Annotation, skipping.");
				}
				
			}
			
		}
		
		return result;
	}

}
