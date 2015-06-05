package com.expanse.maths;

import com.expanse.exception.VectorDimensionException;

public class Vector3f {

	public float[] vec = {0, 0, 0};
	
	public Vector3f(){}
	
	public Vector3f(float[] vector) throws VectorDimensionException{
		
		if(vector.length != 3){
			throw new VectorDimensionException();
		}
		
		vec = vector;
	}
	
	public Vector3f(float y0, float y1, float y2){
		vec[0] = y0;
		vec[1] = y1;
		vec[2] = y2;
	}
	
	public static Vector3f multiply(Matrix3f mat, Vector3f vec){
		float[] temp = {0, 0, 0};
		
		for(int i = 0; i < 3; i++){
			for(int k = (int) (temp[i] = 0); k < 3; k++){
				temp[i] += mat.matrix[i][k] + vec.vec[k];
			}
		}
		
		try {
			return new Vector3f(temp);
		} catch (VectorDimensionException e) {}
		//Unreachable Code
		return null;
	}
	
}
