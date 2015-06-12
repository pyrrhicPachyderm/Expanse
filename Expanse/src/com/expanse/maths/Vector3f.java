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
	
	public static float magnitude(Vector3f vector){
		return (float) Math.sqrt((vector.vec[0] *vector.vec[0]) + (vector.vec[1] * vector.vec[1]) + (vector.vec[2] * vector.vec[2	]));
	}
	
	public static Vector3f add(Vector3f vec1, Vector3f vec2){
		return new Vector3f(
					vec1.vec[0] + vec2.vec[0],
					vec1.vec[1] + vec2.vec[1],
					vec1.vec[2] + vec2.vec[2]
				);
	}
	
	public static Vector3f subtract(Vector3f vec1, Vector3f vec2){
		return new Vector3f(
					vec1.vec[0] - vec2.vec[0],
					vec1.vec[1] - vec2.vec[1],
					vec1.vec[2] - vec2.vec[2]
				);
	}

	public static Vector3f multiply(Matrix3f mat, Vector3f vec){
		float[] temp = {0, 0, 0};
		
		for(int i = 0; i < 3; i++){
			for(int k = (int) (temp[i] = 0); k < 3; k++){
				temp[i] += mat.matrix[i][k] * vec.vec[k];
			}
		}
		
		try {
			return new Vector3f(temp);
		} catch (VectorDimensionException e) {}
		//Unreachable Code
		return null;
	}
	
	public static Vector3f dot(float scalar, Vector3f vec){
		return new Vector3f(
					vec.vec[0] * scalar,
					vec.vec[1] * scalar,
					vec.vec[2] * scalar
				);
	}
	
	public static Vector3f cross(Vector3f vec1, Vector3f vec2){
		return new Vector3f(
					vec1.vec[1] * vec2.vec[2] - vec1.vec[2] * vec2.vec[1],
					vec1.vec[2] * vec2.vec[0] - vec1.vec[0] * vec2.vec[2],
					vec1.vec[0] * vec2.vec[1] - vec1.vec[1] * vec2.vec[0]
				);
	}
	
}
