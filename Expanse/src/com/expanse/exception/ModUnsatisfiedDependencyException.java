package com.expanse.exception;

public class ModUnsatisfiedDependencyException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ModUnsatisfiedDependencyException(){};
	
	public ModUnsatisfiedDependencyException(String message){
		
		super("ModUnsatisfiedDependencyException: " + message);
		
	}
}
