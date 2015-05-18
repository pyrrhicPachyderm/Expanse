package com.expanse.exception;

public class ModDependencyError extends Exception{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModDependencyError() {}

    public ModDependencyError(String message)
    {
       super("Error, two or more mods are dependent on eachother" + message);
    }
	
}
