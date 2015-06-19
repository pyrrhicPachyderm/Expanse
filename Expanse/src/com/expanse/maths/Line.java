package com.expanse.maths;

public class Line {
	
	public Vector3f c = new Vector3f();
	public Vector3f t = new Vector3f();
	
	public Line(Vector3f r, Vector3f t){
		this.c = r;
		this.t = t;
	}
	
	public Line(float x, float tx, float y, float ty, float z, float tz){
		c = new Vector3f(x, y, z);
		t = new Vector3f(tx, ty, tz);
	}
	
	public Vector3f findPoint(float tval){
		return new Vector3f(c.x + t.x * tval, c.y + t.y * tval, c.z + t.z * tval);
	}
	
}
