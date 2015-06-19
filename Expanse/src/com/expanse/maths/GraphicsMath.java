package com.expanse.maths;

import com.expanse.graphics.Triangle;

public class GraphicsMath {

	public static Plane findTranglePlane(Triangle t){
		Vector3f vec1 = Vector3f.subtract(t.b, t.a);
		Vector3f vec2  = Vector3f.subtract(t.c, t.a);
		
		Vector3f cross = Vector3f.cross(vec1, vec2);
		
		Plane equation = new Plane();
		
		equation.a = cross.x;
		equation.b = cross.y;
		equation.c = cross.z;
		
		equation.d = ((equation.a * vec1.x) + (equation.b * vec1.y) + (equation.c * vec1.z));
		
		return equation;
	}
	
	public static Vector3f findLinePlaneIntersection(Plane p, Line l){
		float t = (p.d - (p.a * l.c.x) - (p.b * l.c.y) - (p.c * l.c.z)) / (p.a * l.t.x + p.b * l.t.y + p.c * l.t.z);
		
		return l.findPoint(t);
	}
	
	public static boolean isPointInTriangle(Triangle triangle, Vector3f pnt){
		Vector3f u = Vector3f.subtract(triangle.b, triangle.a);
		Vector3f v = Vector3f.subtract(triangle.c, triangle.a);
		Vector3f w = Vector3f.subtract(pnt, triangle.a);
		
		Vector3f vw = Vector3f.cross(v, w);
		Vector3f vu = Vector3f.cross(v, u);
		
		if(Vector3f.dot(vw, vu) < 0){
			return false;
		}
		
		Vector3f uw = Vector3f.cross(u, w);
		Vector3f uv = Vector3f.cross(u, v);
		
		if(Vector3f.dot(uw, uv) < 0){
			return false;
		}
		
		float d = Vector3f.magnitude(uv);
		float r = Vector3f.magnitude(vw) / d;
		float t = Vector3f.magnitude(uw) / d;
		
		if(r + t <= 1){
			return true;
		}
		return false;
	}
	
}
