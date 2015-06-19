package com.expanse.maths;

import com.expanse.exception.VectorDimensionException;

public class Vector3f {

	public float x = 0;
	public float y = 0;
	public float z = 0;
	
	public Vector3f(){}
	
	public Vector3f(float y0, float y1, float y2){
		x = y0;
		y = y1;
		z = y2;
	}
	
	public float[] asArray(){
		return new float[]{x, y, z};
	}
	
	public static float magnitude(Vector3f vector){
		return (float) Math.sqrt((vector.x *vector.x) + (vector.y * vector.y) + (vector.z * vector.z));
	}
	
	public static Vector3f add(Vector3f vec1, Vector3f vec2){
		return new Vector3f(
					vec1.x + vec2.x,
					vec1.y + vec2.y,
					vec1.z + vec2.z
				);
	}
	
	public static Vector3f subtract(Vector3f vec1, Vector3f vec2){
		return new Vector3f(
					vec1.x - vec2.x,
					vec1.y - vec2.y,
					vec1.z - vec2.z
				);
	}

	public static Vector3f multiply(Matrix3f mat, Vector3f vec){
		float[] temp = {0, 0, 0};
		float[] t = {vec.x, vec.y, vec.z};
		
		for(int i = 0; i < 3; i++){
			for(int k = (int) (temp[i] = 0); k < 3; k++){
				temp[i] += mat.matrix[i][k] * t[k];
			}
			
			
			
		}

		return new Vector3f(temp[0], temp[1], temp[2]);
	}
	
	public static Vector3f multiply(float scalar, Vector3f vec){
		return new Vector3f(
					vec.x * scalar,
					vec.y * scalar,
					vec.z * scalar
				);
	}
	
	public static float dot(Vector3f vec1, Vector3f vec2){
		return vec1.x * vec2.x + vec1.y * vec2.y + vec1.z * vec2.z;
	}
	
	public static Vector3f cross(Vector3f vec1, Vector3f vec2){
		Vector3f res = new Vector3f(
					vec1.y * vec2.z - vec1.z * vec2.y,
					vec1.z * vec2.x - vec1.x * vec2.z,
					vec1.x * vec2.y - vec1.y * vec2.x
				);
		Vector3f up = new Vector3f(0, 1, 0);
		
		if(dot(res, up) < 0){
			return multiply(-1, res);
		}
		return res;
	}
	
}
