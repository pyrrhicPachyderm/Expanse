package com.expanse.exception;

public class MatrixDimensionException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MatrixDimensionException() {}

    public MatrixDimensionException(String message)
    {
       super("Matrix Dimensions Incorrect" + message);
    }
}
