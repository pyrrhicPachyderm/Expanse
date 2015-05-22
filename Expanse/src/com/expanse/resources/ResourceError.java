package com.expanse.resources;

public class ResourceError {
	/*
	 * 0: metal
	 * 1: food
	 * 2: water
	 * 3: fuel
	 * 4: people
	 * 5: not-thick people
	 */
	protected int errorCode;
	public ResourceError(int eCode){
		this.errorCode = eCode;
	}
	
}
