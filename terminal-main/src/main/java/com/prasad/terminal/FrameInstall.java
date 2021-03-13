
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.awt.*;
import com.prasad.util.GridBagConstraints2;

import java.awt.event.*;
import java.io.*;

public class FrameInstall extends Frame {
    PalTerm palTerm;

    Panel panelTop = new Panel();
    Panel panelMiddle = new Panel();
    Panel panelBottom = new Panel();
    Button btnInstall = new Button();
    Checkbox chkbxClientInstall = new Checkbox();
    Label lblDestDir = new Label();
    TextField txtSrcHost = new TextField();
    TextField txtSrcProxyHost = new TextField();
    TextField txtSrcProxyPort = new TextField();
    Button btnBrowseDestDir = new Button();
    Checkbox chkbxLawsonExt = new Checkbox();
    TextField txtDestDir = new TextField();
    Label lblSrcHost = new Label();
    Label lblSrcProxyHost = new Label();
    Label lblSrcProxyPort = new Label();
    Label lblSrcDir = new Label();
    TextField txtSrcDir = new TextField();
    Button btnBrowseSrcDir = new Button();
    Label lblDestUserId = new Label();
    TextField txtDestUserId = new TextField();
    Label lblDestPasswd = new Label();
    TextField txtDestPasswd = new TextField();
    Label lblDestAcct = new Label();
    TextField txtDestAcct = new TextField();
    Label lblDest = new Label();
    Label lblSrc = new Label();
    Label lblSrcUserId = new Label();
    Label lblSrcPasswd = new Label();
    Label lblSrcAcct = new Label();
    TextField txtSrcUserId = new TextField();
    TextField txtSrcPasswd = new TextField();
    TextField txtSrcAcct = new TextField();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    Label lblDestHost = new Label();
    Label lblDestProxyHost = new Label();
    Label lblDestProxyPort = new Label();
    TextField txtDestHost = new TextField();
    TextField txtDestProxyHost = new TextField();
    TextField txtDestProxyPort = new TextField();
    Label lblMessage = new Label();

    public FrameInstall() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    PalTerm getApplet() {
        return palTerm;
    }

    void setApplet(PalTerm applet) {
        palTerm = applet;
    }

    public static void main(String[] args) {
        FrameInstall frameInstall = new FrameInstall();
        frameInstall.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    System.exit(0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        frameInstall.show();
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(550, 414));
        this.setTitle("Install Terminal");
        panelMiddle.setLayout(gridBagLayout1);
        lblDestUserId.setAlignment(2);
        lblDestUserId.setText("User Id:");
        txtDestUserId.setColumns(16);
        lblDestPasswd.setAlignment(2);
        lblDestPasswd.setText("Password:");
        txtDestPasswd.setEchoChar('#');
        txtDestPasswd.setColumns(10);
        lblDestAcct.setAlignment(2);
        lblDestAcct.setText("Acct:");
        txtDestAcct.setColumns(10);
        lblDest.setAlignment(2);
        lblDest.setText("Destination:");
        lblSrc.setAlignment(2);
        lblSrc.setText("Source:");
        lblSrcUserId.setAlignment(2);
        lblSrcUserId.setText("User Id:");
        lblSrcPasswd.setAlignment(2);
        lblSrcPasswd.setText("Password:");
        txtSrcPasswd.setEchoChar('#');
        txtSrcPasswd.setColumns(10);
        txtSrcAcct.setColumns(10);
        lblSrcAcct.setAlignment(2);
        lblSrcAcct.setText("Acct:");
        txtSrcUserId.setColumns(16);
        lblDestHost.setAlignment(2);
        lblDestHost.setText("Destination Host:");
        lblDestProxyHost.setAlignment(2);
        lblDestProxyHost.setText("Proxy Host:");
        lblDestProxyPort.setAlignment(2);
        lblDestProxyPort.setText("Proxy Port:");
        txtDestHost.setColumns(24);
        txtDestProxyHost.setColumns(10);
        txtDestProxyPort.setColumns(8);
        lblMessage.setForeground(Color.red);
        btnInstall.setLabel("Install");
        btnInstall.addActionListener(new FrameInstall_btnInstall_actionAdapter(this));
        chkbxClientInstall.addItemListener(new FrameInstall_chkbxClientInstall_itemAdapter(this));
        lblDestDir.setAlignment(2);
        lblDestDir.setText("Install Directory:");
        txtSrcHost.setColumns(24);
        txtSrcProxyHost.setColumns(10);
        txtSrcProxyPort.setColumns(8);
        btnBrowseDestDir.setActionCommand("btnBrowseDestDir");
        btnBrowseDestDir.addActionListener(new FrameInstall_btnBrowseDestDir_actionAdapter(this));
        chkbxLawsonExt.setLabel("Install Lawson Extensions");
        txtDestDir.setColumns(32);
        lblSrcHost.setAlignment(2);
        lblSrcHost.setText("Source Host:");
        lblSrcProxyHost.setAlignment(2);
        lblSrcProxyHost.setText("Proxy Host:");
        lblSrcProxyPort.setAlignment(2);
        lblSrcProxyPort.setText("Proxy Port:");
        lblSrcDir.setAlignment(2);
        lblSrcDir.setText("Source Directory:");
        txtSrcDir.setColumns(32);
        btnBrowseSrcDir.setLabel("Browse");
        btnBrowseSrcDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnBrowseSrcDir_actionPerformed(e);
            }
        });
        btnBrowseDestDir.setLabel("Browse");
        chkbxClientInstall.setLabel("Client Installation");
        this.add(panelTop, BorderLayout.NORTH);
        this.add(panelMiddle, BorderLayout.CENTER);
        panelMiddle.add(chkbxClientInstall, new GridBagConstraints2(0, 0, 3, 1, 0.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), -2, 0));

        panelMiddle.add(lblDestHost, new GridBagConstraints2(0, 1, 2, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(11, 0, 0, 0), 0, 0));
        panelMiddle.add(txtDestHost, new GridBagConstraints2(3, 1, 1, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(9, 0, 0, 0), 0, 0));
        panelMiddle.add(lblDestProxyHost, new GridBagConstraints2(4, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(28, 7, 0, 0), 0, 0));
        panelMiddle.add(txtDestProxyHost, new GridBagConstraints2(5, 1, 1, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(25, 0, 0, 0), 0, 0));
        panelMiddle.add(lblDestProxyPort, new GridBagConstraints2(6, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(28, 7, 0, 0), -3, 0));
        panelMiddle.add(txtDestProxyPort, new GridBagConstraints2(7, 1, 1, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(25, 0, 0, 5), 0, 0));

        panelMiddle.add(lblSrcHost, new GridBagConstraints2(0, 2, 2, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelMiddle.add(txtSrcHost, new GridBagConstraints2(3, 2, 1, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panelMiddle.add(lblSrcProxyHost, new GridBagConstraints2(4, 2, 1, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), -3, 0));
        panelMiddle.add(txtSrcProxyHost, new GridBagConstraints2(5, 2, 1, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panelMiddle.add(lblSrcProxyPort, new GridBagConstraints2(6, 2, 1, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelMiddle.add(txtSrcProxyPort, new GridBagConstraints2(7, 2, 1, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 5), 0, 0));

        panelMiddle.add(btnBrowseDestDir, new GridBagConstraints2(5, 3, 2, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(9, 5, 0, 5), 2, 0));
        panelMiddle.add(chkbxLawsonExt, new GridBagConstraints2(3, 0, 3, 1, 0.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelMiddle.add(lblDestDir, new GridBagConstraints2(0, 3, 2, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(8, 0, 0, 0), 0, 0));
        panelMiddle.add(txtDestDir, new GridBagConstraints2(3, 3, 2, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(12, 0, 0, 0), 0, 0));
        panelMiddle.add(lblSrcDir, new GridBagConstraints2(0, 4, 2, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(8, 0, 0, 0), 0, 0));
        panelMiddle.add(txtSrcDir, new GridBagConstraints2(3, 4, 2, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(7, 0, 0, 0), 0, 0));
        panelMiddle.add(btnBrowseSrcDir, new GridBagConstraints2(5, 4, 2, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 5, 0, 5), -1, 0));

        panelMiddle.add(lblDest, new GridBagConstraints2(0, 6, 1, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));
        panelMiddle.add(lblDestUserId, new GridBagConstraints2(1, 6, 1, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(28, 0, 0, 0), 0, 0));
        panelMiddle.add(txtDestUserId, new GridBagConstraints2(3, 6, 1, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(28, 0, 0, 0), 0, 0));
        panelMiddle.add(lblDestPasswd, new GridBagConstraints2(4, 6, 1, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(28, 7, 0, 0), -3, 0));
        panelMiddle.add(txtDestPasswd, new GridBagConstraints2(5, 6, 1, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(25, 0, 0, 0), 0, 0));
        panelMiddle.add(lblDestAcct, new GridBagConstraints2(6, 6, 1, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(28, 7, 0, 0), -3, 0));
        panelMiddle.add(txtDestAcct, new GridBagConstraints2(7, 6, 1, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(25, 0, 0, 5), 0, 0));

        panelMiddle.add(lblSrc, new GridBagConstraints2(0, 7, 1, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(8, 0, 0, 0), 0, 0));
        panelMiddle.add(lblSrcUserId, new GridBagConstraints2(1, 7, 1, 4, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(8, 0, 66, 0), 0, 0));
        panelMiddle.add(txtSrcUserId, new GridBagConstraints2(3, 7, 1, 3, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(7, 0, 67, 0), 0, 0));
        panelMiddle.add(lblSrcPasswd, new GridBagConstraints2(4, 7, 1, 3, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(7, 7, 67, 0), -3, 0));
        panelMiddle.add(txtSrcPasswd, new GridBagConstraints2(5, 7, 1, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(7, 0, 0, 0), 0, 0));
        panelMiddle.add(lblSrcAcct, new GridBagConstraints2(6, 7, 1, 3, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(7, 7, 67, 0), -3, 0));
        panelMiddle.add(txtSrcAcct, new GridBagConstraints2(7, 7, 1, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(7, 0, 0, 5), 0, 0));

        panelMiddle.add(lblMessage, new GridBagConstraints2(0, 8, 9, 1, 0.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        this.add(panelBottom, BorderLayout.SOUTH);
        panelBottom.add(btnInstall, null);
    }

    void btnInstall_actionPerformed(ActionEvent e) {
        boolean clientInstall = chkbxClientInstall.getState();
        boolean installLawsonExt = chkbxLawsonExt.getState();

        String srcDir = txtSrcDir.getText();
        String srcServer = txtSrcHost.getText();
        String srcUserid = txtSrcUserId.getText();
        String srcPasswd = txtSrcPasswd.getText();
        String srcAcct = txtSrcAcct.getText();
        String srcProxyHost = txtSrcProxyHost.getText();
        String srcProxyPort = txtSrcProxyPort.getText();

        String destDir = txtDestDir.getText();
        String destServer = txtDestHost.getText();
        String destUserid = txtDestUserId.getText();
        String destPasswd = txtDestPasswd.getText();
        String destAcct = txtDestAcct.getText();
        String destProxyHost = txtDestProxyHost.getText();
        String destProxyPort = txtDestProxyPort.getText();

        String ftpStatus; // status of the last FTP command

        // validate the input
        if (destDir == null
            || destDir.trim().length() == 0) {
            setMessage("Please specify Destination directory for installation");
            txtDestDir.requestFocus();
        }
        destDir = destDir.trim();
        if (!clientInstall) {
            // server installation - validate destination params
            setMessage(null);
            if (destServer == null
                || destServer.trim().length() == 0) {
                setMessage("Please specify Destination host on which to install");
                txtDestHost.requestFocus();
                return;
            }
            destServer = destServer.trim();

            if (destUserid == null
                || destUserid.trim().length() == 0) {
                setMessage("Please specify Destination userid for host [" + destServer + "]");
                txtDestUserId.requestFocus();
                return;
            }
            destUserid = destUserid.trim();

            if (destPasswd == null
                || destPasswd.trim().length() == 0) {
                setMessage("Please specify Destination password for host [" + destServer + "], userid [" + destUserid + "]");
                txtDestPasswd.requestFocus();
                return;
            }
            destPasswd = destPasswd.trim();

            if (destAcct != null)
                destAcct = destAcct.trim();
            if (destProxyHost != null)
                destProxyHost = destProxyHost.trim();
            if (destProxyPort != null)
                destProxyPort = destProxyPort.trim();
        }

        // validate source params
        if (srcDir == null
            || srcDir.trim().length() == 0) {
            setMessage("Please specify Source directory to install from");
            txtSrcDir.requestFocus();
        }
        srcDir = srcDir.trim();

        if (srcServer == null
            || srcServer.trim().length() == 0) {
            setMessage("Please specify Source host to install from");
            txtSrcHost.requestFocus();
            return;
        }
        srcServer = srcServer.trim();

        if (srcUserid == null
            || srcUserid.trim().length() == 0) {
            setMessage("Please specify Source userid for host [" + srcServer + "]");
            txtSrcUserId.requestFocus();
            return;
        }
        srcUserid = srcUserid.trim();

        if (srcPasswd == null
            || srcPasswd.trim().length() == 0) {
            setMessage("Please specify Source password for host [" + srcServer + "], userid [" + srcUserid + "]");
            txtSrcPasswd.requestFocus();
            return;
        }
        srcPasswd = srcPasswd.trim();

        if (srcAcct != null)
            srcAcct = srcAcct.trim();
        if (srcProxyHost != null)
            srcProxyHost = srcProxyHost.trim();
        if (srcProxyPort != null)
            srcProxyPort = srcProxyPort.trim();

        // for a client install, use FrameFtpDir and download the files
        // for a server install, download the files to a local directory from the source
        //		and then upload to the destination directory

        FrameFtpDir frameFtpDir = new FrameFtpDir(palTerm.isNAV, palTerm.isIE);
        frameFtpDir.setApplet(palTerm);
        frameFtpDir.setDebug(getDebug());
        frameFtpDir.setBackground(getBackground());
        frameFtpDir.setForeground(getForeground());
        if (fixedFont != null)
            frameFtpDir.setFileListFont(fixedFont);
        frameFtpDir.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FrameFtpDir f = ((FrameFtpDir) e.getSource());
                f.cleanup();
                f.dispose();
            }
        });

        frameFtpDir.setBackground(getBackground());
        frameFtpDir.setForeground(getForeground());
        frameFtpDir.setShowFileDetails(true);
        frameFtpDir.setShowLocal(true);
        frameFtpDir.setShowRemote(true);
        //
        // set the initial directories
        //
        frameFtpDir.show();
        if (srcProxyHost != null
            && srcProxyPort != null
            && srcProxyHost.trim().length() > 0
            && srcProxyPort.trim().length() > 0)
            frameFtpDir.setSocksInfo(srcProxyHost, srcProxyPort);
        frameFtpDir.ftpcmd_connect(srcServer, srcUserid, srcPasswd, srcAcct);

        frameFtpDir.ftpcmd_changeDir(srcDir, true);

        ftpStatus = frameFtpDir.ftpcmd_getStatus();
        if (ftpStatus != null
            && ftpStatus.startsWith("550"))    // changeDir failed (this code does not work !)
        {
            setMessage("Error changing directory to [" + srcDir + "] on server [" + srcServer + "]");
            return;
        }

        String copyToDir = destDir;
        File tmpLocalDir = new File(System.getProperty("user.dir"), "tmp");
        if (clientInstall)
            copyToDir = destDir;
        else {
            tmpLocalDir.mkdirs();
            copyToDir = tmpLocalDir.getAbsolutePath();
        }

        frameFtpDir.ftpcmd_makeDir(copyToDir, false);
        frameFtpDir.ftpcmd_makeDir(copyToDir + "/termimgs", false);
        frameFtpDir.ftpcmd_makeDir(copyToDir + "/backimgs", false);
        frameFtpDir.ftpcmd_makeDir(copyToDir + "/terminfo", false);
        frameFtpDir.ftpcmd_changeDir(copyToDir, false);
        ftpStatus = frameFtpDir.ftpcmd_getStatus();
        if (ftpStatus != null
            && ftpStatus.startsWith("550"))    // changeDir failed (this code does not work !)
        {
            setMessage("Error changing directory to [" + copyToDir + "] on local client");
            return;
        }

        frameFtpDir.ftpcmd_setType(false);    // binary mode
        frameFtpDir.ftpcmd_download("terminal.jar", true, null);
        frameFtpDir.ftpcmd_download("terminal.cab", true, null);
        createIndexFile(copyToDir, "index.html",
            clientInstall, installLawsonExt,
            srcServer, srcDir);

        // for server, need to move it up
        if (!clientInstall) {
            frameFtpDir.ftpcmd_disconnect();
            if (destProxyHost != null
                && destProxyPort != null
                && destProxyHost.trim().length() > 0
                && destProxyPort.trim().length() > 0)
                frameFtpDir.setSocksInfo(destProxyHost, destProxyPort);
            frameFtpDir.ftpcmd_connect(destServer, destUserid, destPasswd, destAcct);
            frameFtpDir.ftpcmd_makeDir(destDir, true);
            frameFtpDir.ftpcmd_makeDir(destDir + "/termimgs", true);
            frameFtpDir.ftpcmd_makeDir(destDir + "/backimgs", true);
            frameFtpDir.ftpcmd_makeDir(destDir + "/terminfo", true);
            frameFtpDir.ftpcmd_changeDir(destDir, true);
            frameFtpDir.ftpcmd_setType(true);    // ascii mode
            frameFtpDir.ftpcmd_upload("index.html", false, null);
            frameFtpDir.ftpcmd_setType(false);    // binary mode
            frameFtpDir.ftpcmd_upload("terminal.jar", false, null);
            frameFtpDir.ftpcmd_upload("terminal.cab", false, null);
            // delete local file and temporary directory
            frameFtpDir.ftpcmd_deleteFile("terminal.jar", false, false);
            frameFtpDir.ftpcmd_deleteFile("terminal.cab", false, false);
            frameFtpDir.ftpcmd_deleteFile("index.html", false, false);
            frameFtpDir.ftpcmd_deleteFile(copyToDir, true, false);
        }
        // copy the default options directory

        //lblDest.setVisible(!clientInstall);
        //lblDestUserId.setVisible(!clientInstall);
        //txtDestUserId.setVisible(!clientInstall);
        //lblDestPasswd.setVisible(!clientInstall);
        //txtDestPasswd.setVisible(!clientInstall);
        //lblDestAcct.setVisible(!clientInstall);
        //txtDestAcct.setVisible(!clientInstall);
    }

    /**
     * Create the index file in the specified local directory
     */
    private void createIndexFile(
        String localDir,
        String fileName,
        boolean clientInstall,    // installation is being done on a client machine
        boolean installLawsonExt,
        String sourceHost,
        String sourceDir) {
        // need to get out of the sandbox when running from a jar file on local machine

        boolean success = false;
        if (palTerm.isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //if (debug)
                //    System.out.println("Got TerminalEmulator permission for Netscape Navigator");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            try {
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                //if (debug)
                //    System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        File f = new File(localDir, fileName);
        FileWriter fout = null;
        PrintWriter dout = null;

        try {
            fout = new FileWriter(f);
            dout = new PrintWriter(fout);

            dout.println("<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">");
            dout.println("<html><head>");
            dout.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
            dout.println("<meta name=\"Author\" content=\"Bipin Prasad\">");
            //dout.println("<meta name=\"GENERATOR\" content=\"Mozilla/4.61 [en] (Win95; I) [Netscape]\">");
            dout.println("<title>Terminal</title>");
            dout.println("<script LANGUAGE=javascript>");
            dout.println("");
            dout.println("//Title:        Java Terminal Emulator");
            dout.println("//Version: 1.0");
            dout.println("//Copyright:    Copyright (c) 1998. All Rights Reserved");
            dout.println("//Author:       Bipin Prasad");
            dout.println("//Company:      Prasad & Associates Ltd");
            dout.println("");
            dout.println("");
            dout.println("var browserName=navigator.appName; // \"Netscape\" or \"Microsoft Internet Explorer\"");
            dout.println("var browserVersion=navigator.appVersion; // \"4.5\"");
            dout.println("");
            dout.println("var isIE=false;");
            dout.println("var isNAV=false;");
            dout.println("if (document.all != null)");
            dout.println("	isIE=true;");
            dout.println("isNAV=!isIE;");
            dout.println("");
            dout.println("// PARAM: cgiExtension - Suffix to apply to cgiPrograms. This is useful");
            dout.println("//					if the server is Microsoft NT. All cgi program names are");
            dout.println("//					extended with this parameter. Default is a zero");
            dout.println("//					length string.");
            dout.println("// PARAM: protocol - Protocol used. Normally this should not");
            dout.println("//					be set. The applet can determine the protocol used");
            dout.println("//					(\"http\" or \"https\"). Setting protocol will redirect all");
            dout.println("//					the URL calls made from the applet. This is useful");
            dout.println("//					for local installs where the applet protocol is \"file\",");
            dout.println("//					however, the cgi-programs reside on a specified server.");
            dout.println("// PARAM: serverHost - Host from which this applet is running. Normally");
            dout.println("//					this should not be set. The applet can determine the host.");
            dout.println("//					Setting serverHost will redirect all the URL calls made");
            dout.println("//					from the applet. So this is useful to set in case of a");
            dout.println("//					local install that accesses a server.");
            dout.println("// PARAM: serverPort - Port from which this applet is running. Normally");
            dout.println("//					this should not be set. The applet can determine the port.");
            dout.println("//					Setting serverPort will redirect all the URL calls made");
            dout.println("//					from the applet. So this is useful to set in case of a");
            dout.println("//					local install that accesses a server.");
            dout.println("// PARAM: docBaseDir - Directory to the document base directory. Normally");
            dout.println("//					this should not be set. The applet can determine the");
            dout.println("//					document base. If docBaseDir is set then it will be");
            dout.println("//					used to determine paths to files (e.g. options for the user).");
            dout.println("// PARAM: cgiDir -  Directory where CGI programs are stored. Normally this");
            dout.println("//					should not be set. The default value of this is \"/cgi-bin\".");
            dout.println("// PARAM: userName - Web User Id of the user. Normally this should not");
            dout.println("//					be set for an applet. This will be determined by the login");
            dout.println("//					process.");
            dout.println("// PARAM: userPasswd - Web User Password for the user. Normally this should not");
            dout.println("//					be set for an applet.");
            dout.println("// PARAM: dme - 	Name of the DME program. Normally this should not");
            dout.println("//					be set. The default value of this is \"dme\".");
            dout.println("//					This is useful in a Lawson Environment.");
            dout.println("// PARAM: login -	Name of the login program. Normally this should not");
            dout.println("//					be set. The default value of this is \"login\".");
            dout.println("//					This is useful in a Lawson Environment.");
            dout.println("// PARAM: prefsave - Name of the preferences save program.");
            dout.println("//					The default value of this is \"prefsave\".");
            dout.println("//					This is useful in a Lawson Environment.");
            dout.println("// PARAM: secdirlist - Name of the program to list directory.");
            dout.println("//					The default value of this is \"secdirlist\".");
            dout.println("// PARAM: retrieveWebId  - retrieves the web user id. A cgi program called");
            dout.println("//					\"login\" (or one specified by PARAM_LOGIN) is called");
            dout.println("//					to determine the web user id.");
            dout.println("// PARAM: retrieveLoginForWeb  - retrieves the login id for corresponding to");
            dout.println("//					the current web user id. This is relevant for the");
            dout.println("//					Lawson environment. A DME CGI call is made to determine");
            dout.println("//					the id.");
            dout.println("// PARAM: useLocalOptions - Options are saved locally (and not on server).");
            dout.println("//					The \"localDirectory\" must be specified for this to work");
            dout.println("//					correctly. This option is useful for a local install.");
            dout.println("// PARAM: useLocalImages - Images are loaded locally (and not from server).");
            dout.println("//					The \"localDirectory\" must be specified for this to work");
            dout.println("//					correctly. This option is useful for a local install.");
            dout.println("// PARAM: useLocalLaTree - LaTree configation is saved locally (and not on server).");
            dout.println("//					The \"localDirectory\" must be specified for this to work");
            dout.println("//					correctly. This option is useful for a local install.");
            dout.println("// PARAM: useLocalTermInfo - Terminal Info is available locally (and not on server).");
            dout.println("//					The \"localDirectory\" must be specified for this to work");
            dout.println("//					correctly. This option is useful for a local install.");
            dout.println("// PARAM: localDirectory - Local directory under which options will be");
            dout.println("//					saved, if the useLocalOptions is set to true. This is");
            dout.println("//					useful for a local install.");
            dout.println("//					If \"useLocalOptions\" is set to true but \"localDirectory\"");
            dout.println("//					is not specified, then a default location of");
            dout.println("//					JAVA_HOME/prasad/terminal is used.");
            dout.println("// PARAM: useLaTree - Should LaTree be shown. This is useful for a Lawson");
            dout.println("//					environment. Typing LaTree at the command prompt will");
            dout.println("//					activate a tree view of all the Lawson Product Lines.");
            dout.println("//					This view can be customized by the user. Running");
            dout.println("//					LaTree requires access to lawson CGI programs that");
            dout.println("//					return information about nodes at each tree node.");
            dout.println("//					This programs are normally installed when Lawson's");
            dout.println("//					Network Enterprise Desktop is installed.");
            dout.println("// PARAM: install.enable : Enable the install menu option. This menu item");
            dout.println("//					will allow the user to install signed cab and jar files");
            dout.println("//					on to the current client. The installation can be");
            dout.println("//					specified as a server or a client install. Server installation");
            dout.println("//					is for access by multiple users on a single host.");
            dout.println("//					Client install is for a local install, usually for a single");
            dout.println("//					user on a single machine.");
            dout.println("// PARAM install.sourceHost : Host from which the CAB and JAR files are downloaded.");
            dout.println("// PARAM install.sourceDir  : Directory that contains CAB and JAR files on the host");
            dout.println("// PARAM install.version	: Version of the source code");
            dout.println("// PARAM install.compileDate: Date on which the code was compiled");
            dout.println("// PARAM smartMouse.firstChar.maxWaitTime: Maximum time in milliseconds to wait");
            dout.println("// 					for firstChar");
            dout.println("// PARAM smartMouse.interChar.waitTime: Time in milliseconds between characters");
            dout.println("// 					after the first one is received");
            dout.println("// PARAM: debug - enable applet debugging");
            dout.println("// PARAM: isIE - should be set to true/false. If true then it is assumed that");
            dout.println("//					applet is running under Microsoft Internet Explorer.");
            dout.println("// PARAM: isNAV - should be set to true/false. If true then it is assumed that");
            dout.println("//					applet is running under Netscape Navigator.");
            dout.println("//");
            dout.println("var c=\"\";");
            dout.println("var installVersion = \"" + VersionDialog.VERSION + "\";");
            dout.println("var installCompileDate = \"" + VersionDialog.COMPILE_DATE + "\";");
            dout.println("var params = \'\\");

            String codeBaseStr = " \\";
            /**
             try {
             if (palTerm.getCodeBase() != null)
             codeBaseStr = " CODEBASE=\"" + palTerm .getCodeBase() + "\" \\";
             } catch (Exception e){}
             **/

            if (palTerm != null) {
                if (clientInstall) {
                    codeBaseStr = " \\";
                    dout.println("		<!PARAM NAME=\"debug\" VALUE=\"false\"> \\");
                    dout.println("		<PARAM NAME=\"isIE\" VALUE=\"\' + isIE + \'\"> \\");
                    dout.println("		<PARAM NAME=\"isNAV\" VALUE=\"\' + isNAV + \'\"> \\");
                    if (palTerm.telnetHost != null) {
                        dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_TELNETHOST + "\" VALUE=\"" + palTerm.telnetHost + "\"> \\");
                        dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_TELNETPORT + "\" VALUE=\"" + palTerm.telnetPort + "\">  \\");
                    }
                    dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_PROTOCOL + "\" VALUE=\"http\"> \\");
                    dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_SERVERHOST + "\" VALUE=\"" + palTerm.serverHost + "\"> \\");
                    dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_SERVERPORT + "\" VALUE=\"" + palTerm.serverPort + "\"> \\");
                    dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_DOCBASEDIR + "\" VALUE=\"" + palTerm.docBaseDir + "\"> \\");
                    dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_USERNAME + "\" VALUE=\"" + UserInfo.getUserId() + "\"> \\");
                    dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_Install_SourceUserId + "\" VALUE=\"" + txtSrcUserId.getText() + "\">  \\");
                    dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_Install_SourcePasswd + "\" VALUE=\"" + txtSrcPasswd.getText() + "\">  \\");
                } else {
                    if (palTerm.telnetHost != null) {
                        dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_TELNETHOST + "\" VALUE=\"" + palTerm.telnetHost + "\"> \\");
                        dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_TELNETPORT + "\" VALUE=\"" + palTerm.telnetPort + "\">  \\");
                    }
                    dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_PROTOCOL + "\" VALUE=\"http\"> \\");
                    dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_SERVERHOST + "\" VALUE=\"myServerHost\"> \\");
                    dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_SERVERPORT + "\" VALUE=\"myServerPort\"> \\");
                    dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_DOCBASEDIR + "\" VALUE=\"myDocBase\"> \\");
                    dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_USERNAME + "\" VALUE=\"myUserName\"> \\");
                    dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_Install_SourceUserId + "\" VALUE=\"" + palTerm.install_sourceUserId + "\">  \\");
                    dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_Install_SourcePasswd + "\" VALUE=\"" + palTerm.install_sourcePasswd + "\">  \\");
                }
                dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_Install_Enable + "\" VALUE=\"true\">  \\");
                dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_EMULATOR_NAME + "\" VALUE=\"" + palTerm.emulatorName + "\">  \\");
                dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_CGIDIR + "\" VALUE=\"" + palTerm.cgiDir + "\"> \\");
                dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_CGIEXTENSION + "\" VALUE=\"" + palTerm.cgiExtension + "\"> \\");
                dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_DME + "\" VALUE=\"" + palTerm.pgm_dme + "\"> \\");
                dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_LOGIN + "\" VALUE=\"" + palTerm.pgm_login + "\"> \\");
                dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_PREFSAVE + "\" VALUE=\"" + palTerm.pgm_prefsave + "\"> \\");
                dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_SECDIRLIST + "\" VALUE=\"" + palTerm.pgm_secdirlist + "\"> \\");
                dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_UseLocalOptions + "\" VALUE=\"" + palTerm.useLocalOptions + "\">  \\");
                dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_UseLocalImages + "\" VALUE=\"" + palTerm.useLocalImages + "\">  \\");
                dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_UseLocalLaTree + "\" VALUE=\"" + palTerm.useLocalLaTree + "\">  \\");
                dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_UseLocalTermInfo + "\" VALUE=\"" + palTerm.useLocalTermInfo + "\">  \\");
            } else {
                dout.println("		<!PARAM NAME=\"host\" VALUE=\"spi\"> \\");
                dout.println("		<PARAM NAME=\"port\" VALUE=\"23\">  \\");
                dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_PROTOCOL + "\" VALUE=\"http\"> \\");
                dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_SERVERHOST + "\" VALUE=\"myServerHost\"> \\");
                dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_SERVERPORT + "\" VALUE=\"myServerPort\"> \\");
                dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_DOCBASEDIR + "\" VALUE=\"myDocBase\"> \\");
                dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_Install_Enable + "\" VALUE=\"true\">  \\");
                dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_USERNAME + "\" VALUE=\"myUserName\"> \\");
                dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_Install_SourceUserId + "\" VALUE=\"" + txtSrcUserId.getText() + "\">  \\");
                dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_Install_SourcePasswd + "\" VALUE=\"" + txtSrcPasswd.getText() + "\">  \\");
            }
            dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_Install_SourceHost + "\" VALUE=\"" + sourceHost + "\">  \\");
            dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_Install_SourceDir + "\" VALUE=\"" + sourceDir + "\">  \\");
            dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_USERPASSWD + "\" VALUE=\"" + "myUserPasswd" + "\"> \\");

            dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_RetrieveWebId + "\" VALUE=\"" + installLawsonExt + "\">  \\");
            dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_RetrieveLoginForWeb + "\" VALUE=\"" + installLawsonExt + "\">  \\");
            dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_UseLaTree + "\" VALUE=\"" + installLawsonExt + "\">  \\");

            dout.println("		<!PARAM NAME=\"" + PalTerm.PARAM_LocalDirectory + "\" VALUE=\"C:\\\\prasad\\\\terminal\">  \\");
            dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_Install_Version + "\" VALUE=\"' + installVersion + '\">  \\");
            dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_Install_CompileDate + "\" VALUE=\"' + installCompileDate + '\">  \\");
            dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_SmartMouse_FirstChar_MaxWaitTime + "\" VALUE=\"" + palTerm.smartMouse_FirstChar_MaxWaitTime + "\">  \\");
            dout.println("		<PARAM NAME=\"" + PalTerm.PARAM_SmartMouse_InterChar_WaitTime + "\" VALUE=\"" + palTerm.smartMouse_InterChar_WaitTime + "\">  \\");
            dout.println("		\';");
            dout.println("if (isIE)");
            dout.println("	c = \'<APPLET " + codeBaseStr);
            dout.println("		  CABBASE=terminal.cab \\");
            dout.println("		  CODE=\"prasad.terminal.PalTerm.class\" \\");
            dout.println("		  NAME=\"PalTermApplet\" \\");
            dout.println("		  WIDTH=100 \\");
            dout.println("		  HEIGHT=100 \\");
            dout.println("		  HSPACE=0 \\");
            dout.println("		  VSPACE=0 \\");
            dout.println("		  ALIGN=middle > \' + params +");
            dout.println("		\'</APPLET>\';");
            dout.println("else");
            dout.println("	c= \'<APPLET " + codeBaseStr);
            dout.println("		  ARCHIVE=terminal.jar  \\");
            dout.println("		  CODE=\"prasad.terminal.PalTerm.class\"  \\");
            dout.println("		  NAME=\"PalTermApplet\"  \\");
            dout.println("		  WIDTH=100  \\");
            dout.println("		  HEIGHT=100  \\");
            dout.println("		  HSPACE=0  \\");
            dout.println("		  VSPACE=0  \\");
            dout.println("		  ALIGN=middle > \' + params +");
            dout.println("		\'</APPLET>\';");
            dout.println("document.writeln(c);");
            dout.println("document.close;");
            dout.println("");
            dout.println("</script>");
            dout.println("</head>");
            dout.println("<body>&nbsp;");
            dout.println("Terminal will appear in a Java enabled browser.<BR>");
            dout.println("</body>");
            dout.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dout != null)
                dout.close();
        }


    }

    public boolean getClientInstall() {
        return chkbxClientInstall.getState();
    }

    public void setClientInstall(boolean flag) {
        chkbxClientInstall.setState(flag);
        chkbxClientInstall_itemStateChanged(null);
    }

    public boolean getInstallLawsonExt() {
        return chkbxLawsonExt.getState();
    }

    public void setInstallLawsonExt(boolean flag) {
        chkbxLawsonExt.setState(flag);
    }

    public String getSrcHost() {
        return txtSrcHost.getText();
    }

    public void setSrcHost(String s) {
        txtSrcHost.setText(s);
    }

    public String getSrcDir() {
        return txtSrcDir.getText();
    }

    public void setSrcDir(String s) {
        txtSrcDir.setText(s);
    }

    public String getSrcUserId() {
        return txtSrcUserId.getText();
    }

    public void setSrcUserId(String s) {
        txtSrcUserId.setText(s);
    }

    public String getSrcPasswd() {
        return txtSrcPasswd.getText();
    }

    public void setSrcPasswd(String s) {
        txtSrcPasswd.setText(s);
    }

    private boolean debug;

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean flag) {
        debug = flag;
    }

    public void setBackground(Color c) {
        super.setBackground(c);

        panelTop.setBackground(c);
        panelMiddle.setBackground(c);
        panelBottom.setBackground(c);
        btnInstall.setBackground(c);
        chkbxClientInstall.setBackground(c);
        lblDestDir.setBackground(c);
        txtSrcHost.setBackground(c);
        txtSrcProxyHost.setBackground(c);
        txtSrcProxyPort.setBackground(c);
        btnBrowseDestDir.setBackground(c);
        chkbxLawsonExt.setBackground(c);
        txtDestDir.setBackground(c);
        lblSrcHost.setBackground(c);
        lblSrcProxyHost.setBackground(c);
        lblSrcProxyPort.setBackground(c);
        lblSrcDir.setBackground(c);
        txtSrcDir.setBackground(c);
        btnBrowseSrcDir.setBackground(c);
        lblDestUserId.setBackground(c);
        txtDestUserId.setBackground(c);
        lblDestPasswd.setBackground(c);
        txtDestPasswd.setBackground(c);
        lblDestAcct.setBackground(c);
        txtDestAcct.setBackground(c);
        lblDest.setBackground(c);
        lblSrc.setBackground(c);
        lblSrcUserId.setBackground(c);
        lblSrcPasswd.setBackground(c);
        lblSrcAcct.setBackground(c);
        txtSrcUserId.setBackground(c);
        txtSrcPasswd.setBackground(c);
        txtSrcAcct.setBackground(c);
        lblDestHost.setBackground(c);
        lblDestProxyHost.setBackground(c);
        lblDestProxyPort.setBackground(c);
        txtDestHost.setBackground(c);
        txtDestProxyHost.setBackground(c);
        txtDestProxyPort.setBackground(c);
        lblMessage.setBackground(c);
    }

    public void setForeground(Color c) {
        super.setForeground(c);

        panelTop.setForeground(c);
        panelMiddle.setForeground(c);
        panelBottom.setForeground(c);
        btnInstall.setForeground(c);
        chkbxClientInstall.setForeground(c);
        lblDestDir.setForeground(c);
        txtSrcHost.setForeground(c);
        txtSrcProxyHost.setForeground(c);
        txtSrcProxyPort.setForeground(c);
        btnBrowseDestDir.setForeground(c);
        chkbxLawsonExt.setForeground(c);
        txtDestDir.setForeground(c);
        lblSrcHost.setForeground(c);
        lblSrcProxyHost.setForeground(c);
        lblSrcProxyPort.setForeground(c);
        lblSrcDir.setForeground(c);
        txtSrcDir.setForeground(c);
        btnBrowseSrcDir.setForeground(c);
        lblDestUserId.setForeground(c);
        txtDestUserId.setForeground(c);
        lblDestPasswd.setForeground(c);
        txtDestPasswd.setForeground(c);
        lblDestAcct.setForeground(c);
        txtDestAcct.setForeground(c);
        lblDest.setForeground(c);
        lblSrc.setForeground(c);
        lblSrcUserId.setForeground(c);
        lblSrcPasswd.setForeground(c);
        lblSrcAcct.setForeground(c);
        txtSrcUserId.setForeground(c);
        txtSrcPasswd.setForeground(c);
        txtSrcAcct.setForeground(c);
        lblDestHost.setForeground(c);
        lblDestProxyHost.setForeground(c);
        lblDestProxyPort.setForeground(c);
        txtDestHost.setForeground(c);
        txtDestProxyHost.setForeground(c);
        txtDestProxyPort.setForeground(c);
        lblMessage.setForeground(c);
    }

    private Font fixedFont;

    public Font getFixedFont() {
        return fixedFont;
    }

    public void setFixedFont(Font f) {
        fixedFont = f;
    }

    void chkbxClientInstall_itemStateChanged(ItemEvent e) {
        boolean clientInstall = chkbxClientInstall.getState();

        lblDestHost.setVisible(!clientInstall);
        txtDestHost.setVisible(!clientInstall);
        lblDestProxyHost.setVisible(!clientInstall);
        txtDestProxyHost.setVisible(!clientInstall);
        lblDestProxyPort.setVisible(!clientInstall);
        txtDestProxyPort.setVisible(!clientInstall);
        lblDest.setVisible(!clientInstall);
        lblDestUserId.setVisible(!clientInstall);
        txtDestUserId.setVisible(!clientInstall);
        lblDestPasswd.setVisible(!clientInstall);
        txtDestPasswd.setVisible(!clientInstall);
        lblDestAcct.setVisible(!clientInstall);
        txtDestAcct.setVisible(!clientInstall);

        panelMiddle.validate();
    }

    void btnBrowseDestDir_actionPerformed(ActionEvent e) {
        boolean clientInstall = chkbxClientInstall.getState();
        String server = null;
        String userid = null;
        String passwd = null;
        String acct = null;
        String proxyHost = null;
        String proxyPort = null;

        if (!clientInstall) {
            // server installation - browse destination directory using FTP
            setMessage(null);
            server = txtDestHost.getText();
            if (server == null
                || server.trim().length() == 0) {
                setMessage("Please specify Destination host on which to install");
                txtDestHost.requestFocus();
                return;
            }
            userid = txtDestUserId.getText();
            if (userid == null
                || userid.trim().length() == 0) {
                setMessage("Please specify Destination userid for host [" + server + "]");
                txtDestUserId.requestFocus();
                return;
            }
            passwd = txtDestPasswd.getText();
            if (passwd == null
                || passwd.trim().length() == 0) {
                setMessage("Please specify Destination password for host [" + server + "], userid [" + userid + "]");
                txtDestPasswd.requestFocus();
                return;
            }
            acct = txtDestAcct.getText();
            proxyHost = txtDestProxyHost.getText();
            proxyPort = txtDestProxyPort.getText();
        }

	/*
	if (clientInstall)
	{
		FileDialog fileDialog = new FileDialog(this, "Local Installation Directory", FileDialog.LOAD);
		fileDialog.setFilenameFilter(new FilenameFilter(){
				public boolean accept(File f, String nm)
				{
					File f2 = new File(f, nm);
					if (f2.exists()
					&&  f2.isDirectory())
						return true;
					return false;
				}
			});
		String d = txtDestDir.getText();
		if (d != null)
			fileDialog.setDirectory(d);
		fileDialog.show();
		String destDir = null;
		d = fileDialog.getDirectory();
		String f = fileDialog.getFile();
		if (f == null
		||	f.length() == 0)
			destDir = d;
		else
		{
			File f2 = new File(d,f);
			if (f2.exists())
			{
				if (f2.isDirectory())
					destDir = f2.getAbsolutePath();
				else
					destDir = f2.getParent();
			}
			else
			{
				f2.mkdirs();
				destDir = f2.getAbsolutePath();
			}
		}
		txtDestDir.setText(destDir);
		fileDialog.dispose();
		fileDialog = null;
	}
	*/

        // now activate the FTP UI to obtain the installation directory
        FrameFtpDir frameFtpDir = new FrameFtpDir(palTerm.isNAV, palTerm.isIE);
        frameFtpDir.setDebug(getDebug());
        frameFtpDir.setBackground(getBackground());
        frameFtpDir.setForeground(getForeground());
        if (fixedFont != null)
            frameFtpDir.setFileListFont(fixedFont);
        frameFtpDir.setShowRemote(!clientInstall);
        frameFtpDir.setShowLocal(clientInstall);
        frameFtpDir.setDirSelectListener(new DirSelectListener() {
            public void dirSelected(Frame frame, String dir, boolean isDir) {
                if (dir != null) {
                    if (isDir)
                        txtDestDir.setText(dir);
                    else {
                        int idx = dir.lastIndexOf('/');
                        if (idx > 0)
                            txtDestDir.setText(dir.substring(0, idx));
                    }
                }
                if (frame instanceof FrameFtpDir) {
                    FrameFtpDir f = (FrameFtpDir) frame;
                    f.cleanup();
                }
                frame.dispose();
            }
        });
        frameFtpDir.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FrameFtpDir f = ((FrameFtpDir) e.getSource());
                f.cleanup();
                f.dispose();
            }
        });
        frameFtpDir.setShowFileDetails(true);
        frameFtpDir.setShowLocal(clientInstall);
        frameFtpDir.setShowRemote(!clientInstall);
        //
        // set the initial directory if supplied
        //
        frameFtpDir.show();
        if (!clientInstall) {
            if (proxyHost != null
                && proxyPort != null
                && proxyHost.trim().length() > 0
                && proxyPort.trim().length() > 0)
                frameFtpDir.setSocksInfo(proxyHost, proxyPort);
            frameFtpDir.ftpcmd_connect(server, userid, passwd, acct);
        }
        String dir = txtDestDir.getText();
        if (dir != null
            && dir.trim().length() > 0)
            frameFtpDir.ftpcmd_changeDir(dir.trim(), !clientInstall);
    }

    void btnBrowseSrcDir_actionPerformed(ActionEvent e) {
        setMessage(null);
        String server = txtSrcHost.getText();
        if (server == null
            || server.trim().length() == 0) {
            setMessage("Please specify Source host to install from");
            txtSrcHost.requestFocus();
            return;
        }
        String userid = txtSrcUserId.getText();
        if (userid == null
            || userid.trim().length() == 0) {
            setMessage("Please specify Source userid for host [" + server + "]");
            txtSrcUserId.requestFocus();
            return;
        }
        String passwd = txtSrcPasswd.getText();
        if (passwd == null
            || passwd.trim().length() == 0) {
            setMessage("Please specify Source password for host [" + server + "], userid [" + userid + "]");
            txtSrcPasswd.requestFocus();
            return;
        }
        String acct = txtSrcAcct.getText();


        // now activate the FTP UI to obtain the installation directory
        FrameFtpDir frameFtpDir = new FrameFtpDir(palTerm.isNAV, palTerm.isIE);
        frameFtpDir.setDebug(getDebug());
        frameFtpDir.setBackground(getBackground());
        frameFtpDir.setForeground(getForeground());
        if (fixedFont != null)
            frameFtpDir.setFileListFont(fixedFont);
        frameFtpDir.setDirSelectListener(new DirSelectListener() {
            public void dirSelected(Frame frame, String dir, boolean isDir) {
                if (dir != null) {
                    if (isDir)
                        txtSrcDir.setText(dir);
                    else {
                        int idx = dir.lastIndexOf('/');
                        if (idx > 0)
                            txtSrcDir.setText(dir.substring(0, idx));
                    }
                }
                if (frame instanceof FrameFtpDir) {
                    FrameFtpDir f = (FrameFtpDir) frame;
                    f.cleanup();
                }
                frame.dispose();
            }
        });
        frameFtpDir.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FrameFtpDir f = ((FrameFtpDir) e.getSource());
                f.cleanup();
                f.dispose();
            }
        });
        frameFtpDir.setShowFileDetails(true);
        frameFtpDir.setShowLocal(false);
        frameFtpDir.setShowRemote(true);
        //
        // set the initial directory if supplied
        //
        frameFtpDir.show();

        String proxyHost = txtSrcProxyHost.getText();
        String proxyPort = txtSrcProxyPort.getText();
        if (proxyHost != null
            && proxyPort != null
            && proxyHost.trim().length() > 0
            && proxyPort.trim().length() > 0)
            frameFtpDir.setSocksInfo(proxyHost, proxyPort);

        frameFtpDir.ftpcmd_connect(server, userid, passwd, acct);
        String dir = txtSrcDir.getText();
        if (dir != null
            && dir.trim().length() > 0)
            frameFtpDir.ftpcmd_changeDir(dir.trim(), true);
    }

    public void setMessage(String m) {
        if (m == null)
            m = "";
        lblMessage.setText(m);
    }

}

class FrameInstall_btnInstall_actionAdapter implements java.awt.event.ActionListener {
    FrameInstall adaptee;


    FrameInstall_btnInstall_actionAdapter(FrameInstall adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnInstall_actionPerformed(e);
    }
}

class FrameInstall_chkbxClientInstall_itemAdapter implements java.awt.event.ItemListener {
    FrameInstall adaptee;


    FrameInstall_chkbxClientInstall_itemAdapter(FrameInstall adaptee) {
        this.adaptee = adaptee;
    }

    public void itemStateChanged(ItemEvent e) {
        adaptee.chkbxClientInstall_itemStateChanged(e);
    }
}

class FrameInstall_btnBrowseDestDir_actionAdapter implements java.awt.event.ActionListener {
    FrameInstall adaptee;


    FrameInstall_btnBrowseDestDir_actionAdapter(FrameInstall adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnBrowseDestDir_actionPerformed(e);
    }
}


