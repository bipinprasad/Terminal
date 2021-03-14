
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator


package com.prasad.terminal;

import java.util.*;
import java.io.*;

public class FtpConnectInfo {
    private PalTerm palTerm;
    private String host;
    private int telnetPort;
    private String userId;
    private String passwd;
    private String ftpAcct;
    private String ftpProxyHost;
    private String ftpProxyPort;

    public FtpConnectInfo(PalTerm palTerm) {
        this(palTerm, "host", 23, "userid", "passwd", null, null, null);
    }

    public FtpConnectInfo(
        PalTerm palTerm,
        String host,
        int telnetPort,
        String userId,
        String passwd,
        String ftpAcct,
        String ftpProxyHost,
        String ftpProxyPort) {
        setPalTerm(palTerm);
        setHost(host);
        setTelnetPort(telnetPort);
        setUserId(userId);
        setPasswd(passwd);
        setFtpAcct(ftpAcct);
        setFtpProxyHost(ftpProxyHost);
        setFtpProxyPort(ftpProxyPort);
    }

    public PalTerm getPalTerm() {
        return palTerm;
    }

    public void setPalTerm(PalTerm val) {
        this.palTerm = val;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String val) {
        this.host = val;
    }

    public int getTelnetPort() {
        return telnetPort;
    }

    public void setTelnetPort(int val) {
        this.telnetPort = val;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String val) {
        this.userId = val;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String val) {
        this.passwd = val;
    }

    public String getFtpAcct() {
        return ftpAcct;
    }

    public void setFtpAcct(String val) {
        this.ftpAcct = val;
    }

    public String getFtpProxyHost() {
        return ftpProxyHost;
    }

    public void setFtpProxyHost(String val) {
        this.ftpProxyHost = val;
    }

    public String getFtpProxyPort() {
        return ftpProxyPort;
    }

    public void setFtpProxyPort(String val) {
        this.ftpProxyPort = val;
    }

    public Hashtable<String, FtpConnectInfo> readFtpConnectInfo() {
        Hashtable<String, FtpConnectInfo> retVal = new Hashtable<>();

        byte[] encrypted = UserInfo.getLocalFileContents(palTerm, palTerm.isStandalone, palTerm.localDirectory + palTerm.ftpConnectFile);
        if (encrypted == null)
            return retVal;
        byte[] decrypted = palTerm.encrypt(encrypted, -1, false, '\n');
        ByteArrayInputStream bin = new ByteArrayInputStream(decrypted);
        Properties props = new Properties();
        try {
            props.load(bin);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 1024; i++) {
            String nextPrefix = i + ".";
            FtpConnectInfo ftpConnectInfo = new FtpConnectInfo(palTerm);
            if (ftpConnectInfo.setFromProperties(props, nextPrefix))
                retVal.put(ftpConnectInfo.getHost(), ftpConnectInfo);
            else
                break;
        }
        return retVal;
    }

    void writeFtpConnectInfo(Hashtable ftpConnectInfos) {
        if (ftpConnectInfos != null) {
            Properties props = new Properties();
            Enumeration enumeration = ftpConnectInfos.elements();
            int i = 0;
            while (enumeration.hasMoreElements())
            {
                FtpConnectInfo ftpConnectInfo = (FtpConnectInfo) enumeration.nextElement();
                if (ftpConnectInfo != null
                    && ftpConnectInfo.getHost() != null) {
                    ftpConnectInfo.getAsProperties(props, "" + i);
                    i++;
                }
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            props.save(out, "Generated by Java Terminal - Written by Bipin Prasad. (c) Copyright 1997. All Rights Reserved");
            byte[] uncrypted = out.toByteArray();
            byte[] crypted = palTerm.encrypt(uncrypted, -1, true, '\n');

            UserInfo.setLocalFileContents(palTerm, palTerm.isStandalone, palTerm.localDirectory + palTerm.ftpConnectFile, crypted, -1);
            /** Write the crypted text to file */

        }

    }

    public Properties getAsProperties(Properties retVal, String keyPrefix) {
        if (retVal == null)
            retVal = new Properties();
        if (keyPrefix == null)
            keyPrefix = "";
        else if (!keyPrefix.endsWith("."))
            keyPrefix = keyPrefix + ".";

        if (host != null)
            retVal.put(keyPrefix + "h", host);
        if (telnetPort >= 0)
            retVal.put(keyPrefix + "p", "" + telnetPort);
        if (userId != null)
            retVal.put(keyPrefix + "u", userId);
        if (passwd != null)
            retVal.put(keyPrefix + "pw", passwd);
        if (ftpAcct != null)
            retVal.put(keyPrefix + "a", ftpAcct);
        if (ftpProxyHost != null)
            retVal.put(keyPrefix + "ph", ftpProxyHost);
        if (ftpProxyPort != null)
            retVal.put(keyPrefix + "pp", ftpProxyPort);

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

        if (retVal.get(keyPrefix + "h") == null)
            return false;

        try {
            host = (String) retVal.get(keyPrefix + "h");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            telnetPort = Integer.parseInt((String) retVal.get(keyPrefix + "p"));
        } catch (Exception e) {
            e.printStackTrace();
            telnetPort = -1;
        }

        try {
            userId = (String) retVal.get(keyPrefix + "u");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            passwd = (String) retVal.get(keyPrefix + "pw");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ftpAcct = (String) retVal.get(keyPrefix + "a");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ftpProxyHost = (String) retVal.get(keyPrefix + "ph");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ftpProxyPort = (String) retVal.get(keyPrefix + "pp");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
