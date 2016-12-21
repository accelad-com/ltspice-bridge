package com.accelad.automation.ltpsice.process;

import java.io.File;

public class CircuitFile {
	
	private final File file;
	private final String ltSpiceFolder;
	
	public CircuitFile(File file, String ltSpiceFolder) {
		this.file = file;
		this.ltSpiceFolder = ltSpiceFolder;
	}

	public File getFile() {
		return file;
	}

	public String getLtSpiceFolder() {
		return ltSpiceFolder;
	}

	public boolean delete() {
		return file.delete();
	}
	
	public String getAbsolutePath(){
		return file.getAbsolutePath();
	}
	

}
