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
	
	public static float distVectorEnds(Vector2f vec1, Vector2f vec2){
		return (float) (Math.sqrt((vec1.x - vec2.x) * (vec1.x - vec2.x) + (vec1.y - vec2.y) * (vec1.y - vec2.y)));
	}
	
	public static float norm(int type, Vector2f vec){
		if(type == 1){
			return vec.x + vec.y;
		}
		return (float) Math.pow(Math.pow(vec.x, type) + Math.pow(vec.y, type), 1 / type);
	}
	
	public static float infNorm(Vector2f vec){
		return Math.max(vec.x, vec.y);
	}
	
	/*
	 * Create vector from Coordinate co1 to co2
	 */
	public static Vector2f vectorFromCoords(Vector2f co1, Vector2f co2){
		return new Vector2f(co2.x - co1.x, co2.y - co1.y);
	}
	
}
