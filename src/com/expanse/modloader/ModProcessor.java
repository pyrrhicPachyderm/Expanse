package com.expanse.modloader;

import java.lang.annotation.Annotation;
import java.util.concurrent.Callable;

import com.expanse.exception.ModDuplicateException;
import com.expanse.exception.ModMissingAnnotationException;
import com.expanse.modapi.BaseMod;
import com.expanse.modapi.Mod;
import com.expanse.modapi.ModRegistry;

public class ModProcessor implements Callable<BaseMod>{

	@SuppressWarnings("rawtypes")
	Class classToBeLoaded;
	
	@SuppressWarnings("rawtypes")
	public ModProcessor(Class classToBeLoaded){
		this.classToBeLoaded = classToBeLoaded;
	}
	
	@Override
	public BaseMod call() throws ModDuplicateException, ModMissingAnnotationException{
		
		BaseMod result = null;
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
				if(obj instanceof BaseMod){
					result = (BaseMod) obj;
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
				
				if(instance instanceof BaseMod){
					throw new ModMissingAnnotationException("Candidate Mod: " + classToBeLoaded.getName() + " Is Missing Necessary Annotation, skipping.");
				}
				
			}
			
		}
		
		return result;
	}

}
