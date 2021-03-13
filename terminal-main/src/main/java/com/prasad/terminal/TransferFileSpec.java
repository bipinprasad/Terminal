
//Title:        Terminal Emulator
//Version:      
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator


package com.prasad.terminal;

import java.util.*;

public class TransferFileSpec {
    String pattern;
    boolean binary;
//  boolean	isDir;

    public TransferFileSpec() {
        this("*", true);
    }

    public TransferFileSpec(String fileSpec, boolean binaryFile /*, boolean isDirectory*/) {
        pattern = fileSpec;
        binary = binaryFile;
        //isDir	= isDirectory;
    }

    @Override
    public Object clone() {
        return new TransferFileSpec(pattern, binary);
    }

    public Properties getAsProperties(Properties retVal, String keyPrefix) {
        if (retVal == null)
            retVal = new Properties();
        if (keyPrefix == null)
            keyPrefix = "";
        else if (!keyPrefix.endsWith("."))
            keyPrefix = keyPrefix + ".";

        retVal.put(keyPrefix + "pattern", pattern);
        retVal.put(keyPrefix + "binary", binary ? "true" : "false");
        //retVal.put(keyPrefix + "isDir"				, isDir?"true":"false");

        return retVal;
    }

    /**
     * return false if it cannot be set, true if it can be set from
     * the properties.
     */
    public boolean setFromProperties(Properties retVal, String keyPrefix) {
        if (retVal == null)
            return false;

        if (keyPrefix == null)
            keyPrefix = "";
        else if (!keyPrefix.endsWith("."))
            keyPrefix = keyPrefix + ".";

        if (retVal.get(keyPrefix + "pattern") == null)
            return false;

        try {
            pattern = (String) retVal.get(keyPrefix + "pattern");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            binary = Boolean.valueOf((String) retVal.get(keyPrefix + "binary")).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //try {
        //isDir = Boolean.valueOf((String)retVal.get(keyPrefix + "isDir")).booleanValue();
        //} catch (Exception e) { e.printStackTrace();}

        return true;
    }
}
