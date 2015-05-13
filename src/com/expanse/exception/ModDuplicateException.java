package com.expanse.exception;

public class ModDuplicateException extends Exception{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModDuplicateException() {}

    public ModDuplicateException(String message)
    {
       super("ModDuplicateException:" + message);
    }
	
}
