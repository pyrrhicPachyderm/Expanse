package com.expanse.utils;

public class VectorMath {
	
	public static Vector2f getNormalized(Vector2f vec){
		return divideByScalar(vec, getMagnitude(vec));
	}
	
	public static float getMagnitude(Vector2f vec){
		return (float) Math.sqrt(vec.x*vec.x+vec.y*vec.y);
	}
	
	public static Vector2f addVectors(Vector2f vec1, Vector2f vec2){
		return new Vector2f(vec1.x + vec2.x, vec1.y + vec2.y);
	}
	
	public static Vector2f subtractVectors(Vector2f vec1, Vector2f vec2){
		return new Vector2f(vec1.x - vec2.x, vec1.y - vec2.y);
	}
	
	public static Vector2f multiplyScalar(Vector2f vec, float scalar){
		return new Vector2f(vec.x * scalar, vec.x * scalar);
	}
	
	public static Vector2f divideByScalar(Vector2f vec, float scalar){
		return new Vector2f(vec.x / scalar, vec.y / scalar);
	}
	
	public static float dotProduct(Vector2f vec1, Vector2f vec2){
		return (vec1.x * vec2.x + vec1.y * vec2.y);
	}
	
}
