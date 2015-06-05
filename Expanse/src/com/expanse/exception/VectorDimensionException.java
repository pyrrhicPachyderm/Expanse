package com.expanse.exception;

public class VectorDimensionException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VectorDimensionException() {}

    public VectorDimensionException(String message)
    {
       super("Vector Dimensions Incorrect" + message);
    }
}
