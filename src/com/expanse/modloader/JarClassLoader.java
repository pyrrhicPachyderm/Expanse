package com.expanse.modloader;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;

@SuppressWarnings("rawtypes")
public class JarClassLoader implements Callable<List<Class>> {

	ZipEntry zipEntry;
	List<Class> classes;
	JarFileLoader loader;

	public JarClassLoader(ZipEntry entry, JarFileLoader jarLoader) {

		zipEntry = entry;
		jarLoader = loader;

	}

	@Override
	public List<Class> call() throws Exception {
		String entryName = zipEntry.getName();
		entryName = entryName.replace('/', '.');
		entryName = entryName.substring(0, entryName.length() - 6);

		classes.add(loader.loadClass(entryName));

		return classes;
	}

}
