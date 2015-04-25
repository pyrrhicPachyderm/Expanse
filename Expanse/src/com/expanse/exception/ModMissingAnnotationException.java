package com.expanse.exception;

public class ModMissingAnnotationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ModMissingAnnotationException(){};
	
	public ModMissingAnnotationException(String message){
		
		super("ModMissingAnnotationException: " + message);
		
	}

}
