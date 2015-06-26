package com.expanse.maths;

import com.expanse.exception.MatrixDimensionException;

public class Matrix2f {
	
	public float[][] matrix = {
			new float[]{ 1, 0 },
			new float[]{ 0, 1 }
	};
	
	public static final float[][] identity = {
			new float[]{ 1, 0 },
			new float[]{ 0, 1 }
	};
	
	public Matrix2f(){};
	
	public Matrix2f(float[][] mat) throws MatrixDimensionException{
		if(!(mat.length == 2 && mat[0].length == 2 && mat[1].length == 2)){
			throw new MatrixDimensionException();
		}
		matrix = mat;
	}
	
	public Matrix2f(float x0y0, float x1y0, float x0y1, float x1y1){
		matrix[0][0] = x0y0;
		matrix[0][1] = x0y1;
		matrix[1][0] = x1y0;
		matrix[1][1] = x1y1;
	}
	
	public static float determinant(Matrix2f mat){
		return mat.matrix[0][0] * mat.matrix[1][1] - mat.matrix[0][1] * mat.matrix[1][0];
	}
	
}
