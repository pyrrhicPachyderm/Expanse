package com.expanse.maths;

import com.expanse.exception.MatrixDimensionException;

public class Matrix3f {
	
	public float[][] matrix = {
		
		new float[]{ 1, 0, 0 },
		new float[]{ 0, 1, 0 },
		new float[]{ 0, 0, 1 }
		
	};
	
	public static final float[][] identity = {
		
		new float[]{ 1, 0, 0 },
		new float[]{ 0, 1, 0 },
		new float[]{ 0, 0, 1 }
		
	};
	
	public Matrix3f(){}
	
	public Matrix3f(float[][] mat) throws MatrixDimensionException{
		
		if(!(mat.length == 3 && mat[0].length == 3 && mat[1].length == 3 && mat[2].length == 3)){
			throw new MatrixDimensionException();
		}
		
		matrix = mat;
	}
	
	public Matrix3f(float m00, float m10, float m20, float m01, float m11, float m21, float m02, float m12, float m22){
		matrix[0][0] = m00;
		matrix[1][0] = m10;
		matrix[2][0] = m20;
		
		matrix[0][1] = m01;
		matrix[1][1] = m11;
		matrix[2][1] = m21;

		matrix[0][2] = m02;
		matrix[1][2] = m12;
		matrix[2][2] = m22;
	}
	
	public static Matrix3f multiply(Matrix3f mat1, Matrix3f mat2){
		
		float[][] temp = identity;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				temp[i][j] = 0;
				for(int k = 0; k < 3; k++){
					temp[i][j] += mat1.matrix[i][k] * mat2.matrix[k][j];
				}
			}
		}
		try {
			return new Matrix3f(temp);
		} catch (MatrixDimensionException e) {}
		//Unreachable Code
		return null;
	}
	
	public static Matrix3f multiply(Matrix3f mat, float scalar){
		
		Matrix3f res = new Matrix3f();
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				res.matrix[i][j] = mat.matrix[i][j] * scalar;
			}
		}
		return res;
	}
	
	public static Matrix3f add(Matrix3f mat1, Matrix3f mat2){
		Matrix3f res = new Matrix3f();
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				res.matrix[i][j] = mat1.matrix[i][j] + mat2.matrix[i][j];
			}
		}
		
		return res;
		
	}
	
	public static Matrix3f subtract(Matrix3f mat1, Matrix3f mat2){
		Matrix3f res = new Matrix3f();
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				res.matrix[i][j] = mat1.matrix[i][j] - mat2.matrix[i][j];
			}
		}
		
		return res;
	}
	
	public static Matrix3f transpose(Matrix3f mat){
		Matrix3f res = new Matrix3f();
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				res.matrix[j][i] = mat.matrix[i][j];
			}
		}
		return res;
	}
	
	public static Matrix3f inverse(Matrix3f mat){
		Matrix3f inv = new Matrix3f();
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				inv.matrix[i][j] = Matrix3f.cofactor(mat, i, j);
			}
		}
		inv = Matrix3f.transpose(inv);
		inv = Matrix3f.multiply(inv, 1 / Matrix3f.determinant(mat));
		return inv;
	}
	
	public static float cofactor(Matrix3f mat, int y, int x){
		
		Matrix2f co = new Matrix2f();
		
		for(int i = 0; i < 3; i++){
			if(i == y){
				continue;
			}
			for(int j = 0; j < 3; j++){
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
		
		return (float) (Math.pow(-1, x + y) * Matrix2f.determinant(co));
		
	}
	
	public static float determinant(Matrix3f mat){
		return (mat.matrix[2][2] * mat.matrix[1][1] * mat.matrix[0][0] + mat.matrix[2][1] * mat.matrix[1][0] * mat.matrix[2][0] + mat.matrix[2][0] * mat.matrix[1][2] * mat.matrix[0][1]) - (mat.matrix[2][0] * mat.matrix[1][1] * mat.matrix[0][2] + mat.matrix[2][1] * mat.matrix[1][2] * mat.matrix[0][0] + mat.matrix[2][2] * mat.matrix[1][0] * mat.matrix[0][1]);
	}
	
}
