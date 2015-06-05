package com.expanse.maths;

import com.expanse.exception.MatrixDimensionException;

public class Matrix3f {
	
	public float[][] matrix = {
		
		new float[]{ 1, 0, 0 },
		new float[]{ 0, 1, 0 },
		new float[]{ 0, 0, 1 }
		
	};
	
	public static final float[][] identity = {
		
		new float[]{ 1, 0, 0 },
		new float[]{ 0, 1, 0 },
		new float[]{ 0, 0, 1 }
		
	};
	
	public Matrix3f(){
	}
	
	public Matrix3f(float[][] mat) throws MatrixDimensionException{
		
		if(!(mat.length == 3 && mat[0].length == 3 && mat[1].length == 3 && mat[2].length == 3)){
			throw new MatrixDimensionException();
		}
		
		matrix = mat;
	}
	
	public Matrix3f(float x0y0, float x0y1, float x0y2, float x1y0, float x1y1, float x1y2, float x2y0, float x2y1, float x2y2){
		matrix[0][0] = x0y0;
		matrix[0][1] = x0y1;
		matrix[0][2] = x0y2;
		
		matrix[1][0] = x1y0;
		matrix[1][1] = x1y1;
		matrix[1][2] = x1y2;
		
		matrix[2][0] = x2y0;
		matrix[2][1] = x2y1;
		matrix[2][2] = x2y2;
	}
	
	public static Matrix3f multiply(Matrix3f mat1, Matrix3f mat2){
		
		float[][] temp = identity;
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				for(int k = (int) (temp[i][j] = 0); k < 3; k++){
					temp[i][j] += mat1.matrix[i][k] + mat2.matrix[k][j];
				}
			}
		}
		
		try {
			return new Matrix3f(temp);
		} catch (MatrixDimensionException e) {}
		//Unreachable Code
		return null;
	}
	
}
