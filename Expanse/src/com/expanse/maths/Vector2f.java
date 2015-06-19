package com.expanse.maths;

public class Vector2f {
	
	float x = 0;
	float y = 0;
	
	public Vector2f(){}
	
	public Vector2f(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public static Vector2f multiply(Matrix2f mat, Vector2f vec){
		return new Vector2f(
					(mat.matrix[0][0] * vec.x) + (mat.matrix[0][1] * vec.y),
					(mat.matrix[1][0] * vec.x) + (mat.matrix[1][2] * vec.y)
				);
	}
	
}
