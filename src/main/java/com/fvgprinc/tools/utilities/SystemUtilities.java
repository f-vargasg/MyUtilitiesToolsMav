/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.utilities;

import com.fvgprinc.tools.common.string.MyCommonString;
import java.io.File;

/**
 *
 * @author fvargas
 */
public class SystemUtilities {

    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {

        return (OS.indexOf("win") >= 0);

    }

    public static boolean isMac() {

        return (OS.indexOf("mac") >= 0);

    }

    public static boolean isUnix() {

        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

    }

    public static boolean isSolaris() {

        return (OS.indexOf("sunos") >= 0);

    }

    /**
     * Store location & size of UI
     *
     * @param pAppName
     * @param pAppVersion
     * @return
     */
    public static String getDataDirectory(String pAppName, String pAppVersion) {
        String res;

        if (SystemUtilities.isWindows()) {
            res = System.getenv("APPDATA") + File.separator;
        } else {
            res = System.getProperty("user.home") + File.separator
                    + ".";
        }
        res += (pAppName + (pAppVersion.length() > 0 ? File.separator : MyCommonString.EMPTYSTR) + pAppVersion);
        return res;
    }

    /**
     * Build a path given a parent path and a path route arrya ... String
     * []newRoute = {"route"; "route2" };
     *
     * buildPath (System.getProperty("user.home"),
     *
     * @param parentPath
     * @param route
     */
    public static void buildPath(String parentPath, String[] route) {
        String newPath = parentPath;
        for (String route1 : route) {
            newPath += (File.separator + route1);
            File theDir = new File(newPath);
            if (!theDir.exists()) {
                boolean result = theDir.mkdir();
                if (result) {
                    System.out.println("DIR " + newPath + " created");
                } else {
                    System.out.println("DIR " + newPath + "NOT created");
                }
            }
        }
    }

    public static void buildPath(String pPath) {
        File theDir = new File(pPath);
        if (!theDir.exists()) {
            boolean result = theDir.mkdirs();
            if (result) {
                System.out.println("DIR " + pPath + " created");
            } else {
                 System.out.println("DIR " + pPath + " NOT created");
            }
        }
    }

}
