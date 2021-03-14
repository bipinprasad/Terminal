
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator


package com.prasad.terminal;

import java.io.*;
import java.util.*;

/**
 * This class represents a temporary file. A static method in this class will
 * return a filename in the temporary directory. The file does not get
 * created but it does represent a file that does not exist. The returned
 * name is null if the file cannot be created.
 * <p>
 * The delete() method should be called to ensure the deletion of the file.
 * This delete method will check Netscape and IE permissions before
 * deleting.
 */
public class FileTemp extends File {
    String nm;
    boolean isNAV;
    boolean isIE;

    /**
     * Always instatiate in the following format
     * FileTemp f = new FileTemp(FileTemp.getTmpFileName());
     */
    public FileTemp(String s, boolean isNAV, boolean isIE) {
        super(s);
        nm = s;
        this.isNAV = isNAV;
        this.isIE = isIE;
    }

    @Override
    public boolean delete() {
        // get browser permission
        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        boolean success = false;
        if (isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalFileAccess");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (isIE) {
            // try Microsoft permission
            try {
                throw new IllegalArgumentException("Microsft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.SYSTEM);
                ////if (debug)
                ////	System.out.println("Got SYSTEM permission for Microsoft Internet Explorer");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                ////if (debug)
                ////	System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (exists()) {
            //System.out.println("File exists, trying to delete");
            return super.delete();
        } else {
            //System.out.println("File does not exist");
            return true;
        }
    }

    /**
     * Return a temporary filename in a temporary directory under the user's home
     * directory. Note that the file is actually not created.
     */
    public static String getTmpFileName(String suffix, boolean isNAV, boolean isIE) {
        if (suffix == null)
            suffix = ".tmp";
        // create a temporary file in a temporary directory in the user's home directory

        // get browser permission
        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        boolean success = false;

        if (isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalFileAccess");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (isIE) {
            // try Microsoft permission
            try {
                throw new IllegalArgumentException("Microsft Internet Explorer support has been removed");
                //
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.SYSTEM);
                ////if (debug)
                ////	System.out.println("Got SYSTEM permission for Microsoft Internet Explorer");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                ////if (debug)
                ////	System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        String userHome = System.getProperty("user.home");
        if (!userHome.endsWith(File.separator))
            userHome = userHome + File.separator;
        String tmpDir = userHome + "tmp";

        int tmpDirSuffix = 0;
        do {
            File tmp = new File(tmpDir);
            if (tmp.exists()) {
                if (tmp.isDirectory())
                    break;
                else {
                    if (tmpDirSuffix > 2000) {
                        System.out.println("Cannot create a temporary directory " + tmpDir);
                        return null;
                    }
                    tmpDir = tmpDir + tmpDirSuffix;
                }
            } else {
                tmp.mkdirs();
                break;
            }
        } while (true);

        // directory has been created
        String retVal = null;
        Date d = new Date();
        long millisecs = d.getTime();
        for (int i = 0; i < 10000; i++, millisecs++) {
            String fileBaseName = Long.toHexString(millisecs);
            String filePath = tmpDir + File.separator + fileBaseName + suffix;
            File f = new File(filePath);
            if (f.exists())
                continue;
            else {
                retVal = filePath;
                break;
            }
        }

        if (retVal == null)
            System.out.println("Cannot create a temporary file in directory " + tmpDir);
        return retVal;
    }

    public static void main(String[] args) {
        FileTemp f = new FileTemp(FileTemp.getTmpFileName(".tmp", false, false), false, false);
        System.out.println("The temporary file is [" + f.getPath() + "]");

        try {
            FileWriter fw = new FileWriter(f);
            fw.write("This is the date " + (new Date()).toString());
            fw.close();
            fw = null;
            System.out.println("The file [" + f.getPath() + "] has been created.");
            System.out.println("The file should be deleted after you enter q");
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                int i = System.in.read();
                if (i == 'q'
                    || i == 'Q')
                    break;
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        f.delete();
        System.out.println("Exiting program");
    }

}
