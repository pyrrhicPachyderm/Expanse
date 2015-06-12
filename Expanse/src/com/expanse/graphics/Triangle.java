package com.expanse.graphics;

import com.expanse.utils.Vector2f;

public class Triangle {
	
	Vector2f A;
	Vector2f B;
	Vector2f C;
	
	float vertDist;
	
	public Triangle(Vector2f vert1, Vector2f vert2, Vector2f vert3){
		this.A = vert1;
		this.B = vert2;
		this.C = vert3;
	}
	
	public boolean isPointInside(Vector2f pnt){
		float area = 0.5f * (-B.y * C.x + A.y * (-B.x + C.x) + A.x * (B.y - C.y) + B.x * C.y);
		float sign;
		if(area < 0){
			sign = -1;
		}else{
			sign = 1;
		}
		float r = (A.y * C.x - A.x * C.y + (C.y - A.y) * pnt.x + (A.x - C.x) * pnt.y) * sign;
		float t = (A.x * B.y - A.y * B.x + (A.y - B.y) * pnt.x + (B.x - A.x) * pnt.y) * sign;
		
		if(r > 0 && t > 0 && (r + t) < 2 * area * sign){
			return true;
		}else{
			return false;
		}
	}
}
