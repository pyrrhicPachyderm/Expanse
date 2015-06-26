package com.expanse.maths;

import com.expanse.exception.MatrixDimensionException;

public class Matrix4f {
	
	public float[][] matrix = {
		new float[]{1, 0, 0, 0},
		new float[]{0, 1, 0, 0},
		new float[]{0, 0, 1, 0},
		new float[]{0, 0, 0, 1}
	};
	
	public static final float[][] identity = {
		new float[]{1, 0, 0, 0},
		new float[]{0, 1, 0, 0},
		new float[]{0, 0, 1, 0},
		new float[]{0, 0, 0, 1}
	};
	
	public Matrix4f(){}
	
	public Matrix4f(float[][] mat) throws MatrixDimensionException{
		
		if(!(mat.length == 4 && mat[0].length == 4 && mat[1].length == 4 && mat[2].length == 4 && mat[3].length == 4)){
			throw new MatrixDimensionException();
		}
		
		matrix = mat;
	}
	
	public Matrix4f(float x0y0, float x1y0, float x2y0, float x3y0, float x0y1, float x1y1, float x2y1, float x3y1, float x0y2, float x1y2, float x2y2, float x3y2, float x0y3, float x1y3, float x2y3, float x3y3){
		matrix[0][0] = x0y0;
		matrix[0][1] = x0y1;
		matrix[0][2] = x0y2;
		matrix[0][3] = x0y3;
		
		matrix[1][0] = x1y0;
		matrix[1][1] = x1y1;
		matrix[1][2] = x1y2;
		matrix[1][3] = x1y3;
		
		matrix[2][0] = x2y0;
		matrix[2][1] = x2y1;
		matrix[2][2] = x2y2;
		matrix[2][3] = x2y3;
		
		matrix[3][0] = x3y0;
		matrix[3][1] = x3y1;
		matrix[3][2] = x3y2;
		matrix[3][3] = x3y3;
	}
	
	public static Matrix4f multiply(Matrix4f mat1, Matrix4f mat2){
		float[][] temp = identity;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				temp[i][j] = 0;
				for(int k = 0; k < 4; k++){
					temp[i][j] += mat1.matrix[i][k] * mat2.matrix[k][j];
				}
			}
		}
		try {
			return new Matrix4f(temp);
		} catch (MatrixDimensionException e) {}
		//Unreachable Code
		return null;
	}
	
	public static Matrix4f add(Matrix4f mat1, Matrix4f mat2){
		Matrix4f res = new Matrix4f();
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				res.matrix[i][j] = mat1.matrix[i][j] + mat2.matrix[i][j];
			}
		}
		
		return res;
	}
	
	public static Matrix4f subtract(Matrix4f mat1, Matrix4f mat2){
		Matrix4f res = new Matrix4f();
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				res.matrix[i][j] = mat1.matrix[i][j] - mat2.matrix[i][j];
			}
		}
		return res;	
	}
	
	public static Matrix4f multiply(Matrix4f mat, float scalar){
		Matrix4f res = new Matrix4f();
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				res.matrix[i][j] = mat.matrix[i][j] * scalar;
			}
		}
		return res;
	}
	
	public static Matrix4f transpose(Matrix4f mat){
		Matrix4f res = new Matrix4f();
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				res.matrix[j][i] = mat.matrix[i][j];
			}
		}
		return res;
	}
	
	public static float cofactor(Matrix4f mat, int y, int x){
		
		Matrix3f co = new Matrix3f();
		
		for(int i = 0; i < 4; i++){
			if(i == y){
				continue;
			}
			for(int j = 0; j < 4; j++){
				if(j == x){
					continue;
				}
				if(j < x){
					if(i < y){
						co.matrix[i][j] = mat.matrix[i][j];
					}else{
						co.matrix[i - 1][j] = mat.matrix[i][j];
					}
				}else{
					if(i < y){
						co.matrix[i][j - 1] = mat.matrix[i][j];
					}else{
						co.matrix[i - 1][j - 1] = mat.matrix[i][j];
					}
				}
			}
		}
		
		return (float) (Math.pow(-1, x + y) * Matrix3f.determinant(co));
	}
	
	public static float determinant(Matrix4f mat){
		return ((cofactor(mat, 0, 0) * mat.matrix[0][0]) + (cofactor(mat, 0, 1) * mat.matrix[0][1]) + (cofactor(mat, 0, 2) * mat.matrix[0][2]) + (cofactor(mat, 0, 3) * mat.matrix[0][3]));
	}
	
	public static Matrix4f inverse(Matrix4f mat){
		
		Matrix4f inv = new Matrix4f();
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				inv.matrix[i][j] = Matrix4f.cofactor(mat, i, j);
			}
		}
		
		inv = Matrix4f.transpose(inv);
		inv = Matrix4f.multiply(inv, 1 / Matrix4f.determinant(mat));
		
		return inv;
	}
	
}
