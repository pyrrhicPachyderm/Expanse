package com.expanse.exception;

public class ModNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ModNotFoundException(){};
	
	public ModNotFoundException(String message){
		
		super("ModNotFoundException: " + message);
		
	}
}
