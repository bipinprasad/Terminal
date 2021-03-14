package com.prasad.terminal;

//Title:        Terminal Emulator
//Version:      
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator


/**
 * This class holds information about a directory on the local host and
 * a specified remote host that should be synchronized.
 * <p>
 * Automatic synchronization follows the following logic:
 * if the local file has changed since the last upload,
 * and the remote file has not changed since last upload
 * then upload the file.
 * if the remote file has changed since the last upload,
 * and the local file has not changed since last upload
 * then download the file.
 * if the pattern matches a local file that does not exist
 * on the server, then upload the file.
 * if the pattern matches a remote file that does not exist
 * locally, then download the file.
 * <p>
 * This information is always saved on the local host.
 */

import java.util.*;

public class DirSyncInfo {
    String remoteHost;
    String localDir;
    String remoteDir;
    Date lastSyncDateTimeLocal;
    Date lastSyncDateTimeRemote;
    TransferFileSpec[] transferFileSpecs;

    public DirSyncInfo() {
    }

    @Override
    public Object clone() {
        DirSyncInfo retVal = new DirSyncInfo();
        retVal.remoteHost = remoteHost;
        retVal.localDir = localDir;
        retVal.remoteDir = remoteDir;
        retVal.lastSyncDateTimeLocal = (lastSyncDateTimeLocal != null) ? new Date(lastSyncDateTimeLocal.getTime()) : null;
        retVal.lastSyncDateTimeRemote = (lastSyncDateTimeRemote != null) ? new Date(lastSyncDateTimeRemote.getTime()) : null;
        if (transferFileSpecs != null) {
            int iMax = transferFileSpecs.length;
            retVal.transferFileSpecs = new TransferFileSpec[iMax];
            for (int i = 0; i < iMax; i++) {
                if (transferFileSpecs[i] != null)
                    retVal.transferFileSpecs[i] = (TransferFileSpec) transferFileSpecs[i].clone();
            }
        }
        return retVal;
    }

    /** Adds the supplied fileSpec */
    public void addTransferFileSpec(TransferFileSpec fileSpec) {
        if (fileSpec == null)
            return;
        if (transferFileSpecs == null) {
            transferFileSpecs = new TransferFileSpec[10];
            transferFileSpecs[0] = fileSpec;
        } else {
            boolean nullFound = false;
            for (int i = 0; i < transferFileSpecs.length; i++) {
                if (transferFileSpecs[i] == null) {
                    transferFileSpecs[i] = fileSpec;
                    nullFound = true;
                    break;
                }
            }
            if (!nullFound) {
                //  increase size of the array
                TransferFileSpec[] newTransferFileSpecs = new TransferFileSpec[transferFileSpecs.length + 10];
                for (int i = 0; i < transferFileSpecs.length; i++)
                    newTransferFileSpecs[i] = transferFileSpecs[i];
                newTransferFileSpecs[transferFileSpecs.length] = fileSpec;
                transferFileSpecs = newTransferFileSpecs;
            }
        }
    }

    public Properties getAsProperties(Properties retVal, String keyPrefix) {
        if (retVal == null)
            retVal = new Properties();
        if (keyPrefix == null)
            keyPrefix = "";
        else if (!keyPrefix.endsWith("."))
            keyPrefix = keyPrefix + ".";

        retVal.put(keyPrefix + "remoteHost", remoteHost);
        retVal.put(keyPrefix + "localDir", localDir);
        retVal.put(keyPrefix + "remoteDir", remoteDir);
        retVal.put(keyPrefix + "lastSyncDateTimeLocal", lastSyncDateTimeLocal);
        retVal.put(keyPrefix + "lastSyncDateTimeRemote", lastSyncDateTimeRemote);
        if (transferFileSpecs != null) {
            for (int cnt = 0, i = 0; i < transferFileSpecs.length; i++) {
                if (transferFileSpecs[i] == null)
                    continue;

                String nextPrefix = keyPrefix + "transferFileSpecs." + cnt + ".";
                transferFileSpecs[i].getAsProperties(retVal, nextPrefix);
                cnt++;
            }
        }

        return retVal;
    }

    /** return false if it cannot be set, true if it can be set from
     *  the properties.
     */
    public boolean setFromProperties(Properties retVal, String keyPrefix) {
        if (retVal == null)
            return false;

        if (keyPrefix == null)
            keyPrefix = "";
        else if (!keyPrefix.endsWith("."))
            keyPrefix = keyPrefix + ".";

        if (retVal.get(keyPrefix + "remoteHost") == null)
            return false;

        try {
            remoteHost = (String) retVal.get(keyPrefix + "remoteHost");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            localDir = (String) retVal.get(keyPrefix + "localDir");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            remoteDir = (String) retVal.get(keyPrefix + "remoteDir");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            lastSyncDateTimeLocal = new Date((String) retVal.get(keyPrefix + "lastSyncDateTimeLocal"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            lastSyncDateTimeRemote = new Date((String) retVal.get(keyPrefix + "lastSyncDateTimeRemote"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Vector tmp = new Vector(10);
        for (int i = 0, cnt = 0; i < 1024; i++) {
            String nextPrefix = keyPrefix + "transferFileSpecs." + i + ".";
            TransferFileSpec transferFileSpec = new TransferFileSpec();
            if (transferFileSpec.setFromProperties(retVal, nextPrefix))
                tmp.addElement(transferFileSpec);
            else
                break;
        }
        int iMax = tmp.size();
        if (iMax > 0) {
            transferFileSpecs = new TransferFileSpec[iMax];
            for (int i = 0; i < iMax; i++)
                transferFileSpecs[i] = (TransferFileSpec) tmp.elementAt(i);
        }

        return true;
    }

    /** Return they key string used to uniquely identify this object */
    public String getKey() {
        String key = remoteHost + "." + remoteDir + "." + localDir;
        return key;
    }

}