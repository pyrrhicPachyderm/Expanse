package com.expanse.modapi.hooks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RegisterMethod {
	@SuppressWarnings("rawtypes")
	Class classHooked();
	boolean shouldCancel();
}
