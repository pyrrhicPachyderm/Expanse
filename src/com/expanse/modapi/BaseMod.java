package com.expanse.modapi;

import java.util.ArrayList;

public interface BaseMod {
	
	void preInit();
	void init();
	void postInit();
	
	ArrayList<String> getDependencies();
	
}
