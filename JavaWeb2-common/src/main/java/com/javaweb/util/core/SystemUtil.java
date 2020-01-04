package com.javaweb.util.core;

public class SystemUtil {
    
    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }

}
