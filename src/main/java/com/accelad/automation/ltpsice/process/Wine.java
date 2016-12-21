package com.accelad.automation.ltpsice.process;

import java.io.File;

import org.apache.commons.lang3.SystemUtils;

public class Wine {
	
	public static File getDriveCFolder(){
        File userHomePath = SystemUtils.getUserHome();
        File wineFolder = new File(userHomePath, ".wine");
        return new File(wineFolder, "drive_c");
	}

}
