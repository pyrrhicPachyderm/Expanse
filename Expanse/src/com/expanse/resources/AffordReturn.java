package com.expanse.resources;

public class AffordReturn {
	public boolean affordable;
	public String error;
	
	public AffordReturn(boolean b, String e){
		this.affordable = b;
		this.error = e;
	}
}
