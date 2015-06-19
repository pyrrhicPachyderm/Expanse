package com.expanse.graphics;

import com.expanse.maths.Vector3f;

public class Triangle {
	
	public Vector3f a;
	public Vector3f b;
	public Vector3f c;
	
	float vertDist;
	
	public Triangle(Vector3f vert1, Vector3f vert2, Vector3f vert3){
		this.a = vert1;
		this.b = vert2;
		this.c = vert3;
	}
}
