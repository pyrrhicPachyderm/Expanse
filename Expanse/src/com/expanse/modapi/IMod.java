
package com.expanse.modapi;

import java.util.ArrayList;

public interface IMod{
	
	void preInit();
	void init();
	void postInit();
	
	ArrayList<String> getDependencies();
	
}