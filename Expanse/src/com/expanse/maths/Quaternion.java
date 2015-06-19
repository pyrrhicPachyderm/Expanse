package com.expanse.maths;

public class Quaternion {
	
	public static final float r = 1;
	public static final float i = 2;
	public static final float j = 3;
	public static final float k = 4;
	
	public float w = 0;
	public float x = 0;
	public float y = 0;
	public float z = 0;
	
	float[][] multTable = {
			new float[]{r, i, j, k},
			new float[]{i, -r, k, -j},
			new float[]{j, -k, -r, i},
			new float[]{k, j, -i, -r}
	};
	
	public Quaternion(){}
	
	public Quaternion(Vector3f vec){
		x = vec.x;
		y = vec.y;
		z = vec.z;
	}
	
	public Quaternion(float w, float x, float y, float z){
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Quaternion(float radians, Vector3f vec){
		float m = (float) Math.sin(radians / 2);
		w = (float) Math.cos(radians / 2);
		x = m * vec.x;
		y = m * vec.y;
		z = m * vec.z;
	}
	
	public static Quaternion multiply(Quaternion quat1, Quaternion quat2){
		return new Quaternion((quat1.w * quat2.w) - (quat1.x * quat2.x) - (quat1.y * quat2.y) - (quat1.z * quat2.z),
							  (quat1.w * quat2.x) + (quat1.x * quat2.w) - (quat1.y * quat2.z) + (quat1.z * quat2.y),
							  (quat1.w * quat2.y) + (quat1.x * quat2.z) + (quat1.y * quat2.w) - (quat1.z * quat2.x),
							  (quat1.w * quat2.z) - (quat1.x * quat2.y) + (quat1.y * quat2.x) + (quat1.z * quat2.w));
	}
	
	public static Quaternion multiply(Quaternion quat, float scalar){
		return new Quaternion(quat.w * scalar, quat.x * scalar, quat.y * scalar, quat.z * scalar);
	}
	
	public static Quaternion divide(Quaternion quat, float scalar){
		return new Quaternion(quat.w / scalar, quat.x / scalar, quat.y / scalar, quat.z / scalar);
	}
	
	public static float magnitude(Quaternion quat){
		return (float) Math.sqrt(quat.w * quat.w + quat.x * quat.x + quat.y * quat.y + quat.z * quat.z);
	}
	
	public static Quaternion normalize(Quaternion quat){
		float mag = magnitude(quat);
		return new Quaternion(quat.w / mag, quat.x / mag, quat.y / mag, quat.z / mag);
	}
	
	public static Quaternion conjugate(Quaternion quat){
		return new Quaternion(quat.w, -quat.x, -quat.y, -quat.z);
	}
	
	public static Matrix4f rotationMatrix(Quaternion quat){
		Matrix4f res = new Matrix4f();
		float w = quat.w, x = quat.x, y = quat.y, z = quat.z;
		
		res.matrix[0][0] = 1 - (2 * y * y) - (2 * z * z);
		res.matrix[0][1] = (2 * x * y) - (2 * z * w);
		res.matrix[0][2] = (2 * x * z) + (2 * y * w);
		res.matrix[0][3] = 0;
		res.matrix[1][0] = (2 * x * y) + (2 * z * w);
		res.matrix[1][1] = 1 - (2 * x * x) - (2 * z * z);
		res.matrix[1][2] = (2 * y * z) - (2 * x * w);
		res.matrix[1][3] = 0;
		res.matrix[2][0] = (2 * x * z) - (2 * y * w);
		res.matrix[2][1] = (2 * y * z) + (2 * x * w);
		res.matrix[2][2] = 1 - (2 * x * x) - (2 * y * y);
		res.matrix[3][0] = 0;
		res.matrix[3][1] = 0;
		res.matrix[3][2] = 0;
		res.matrix[3][3] = 1;
		
		return res;
	}
	
}
