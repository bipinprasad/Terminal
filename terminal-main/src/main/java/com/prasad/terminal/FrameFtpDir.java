
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class FrameFtpDir extends Frame {
    PanelFtpRemote panelFtpRemote;
    MenuBar menuBar = new MenuBar();
    Menu menuView = new Menu();
    CheckboxMenuItem cbMenuItemViewLocal = new CheckboxMenuItem();
    CheckboxMenuItem cbMenuItemViewRemote = new CheckboxMenuItem();
    CheckboxMenuItem cbMenuItemViewDirs = new CheckboxMenuItem();
    CheckboxMenuItem cbMenuItemViewFiles = new CheckboxMenuItem();
    Menu menuFile = new Menu();
    MenuItem menuItemConnect = new MenuItem();
    MenuItem menuItemDisconnect = new MenuItem();
    MenuItem menuItemReconnect = new MenuItem();

    public FrameFtpDir(boolean isNAV, boolean isIE) {
        panelFtpRemote = new PanelFtpRemote(isNAV, isIE);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FrameFtpDir frameFtpDir = new FrameFtpDir(false, false);
        frameFtpDir.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    System.exit(0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        frameFtpDir.show();
        frameFtpDir.setDebug(true);
        frameFtpDir.setFileListFont(new Font("Courier", 0, 12));
        frameFtpDir.ftpcmd_connect("spider", "bipin", "pass1234", null);
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(494, 364));
        menuView.setLabel("View");
        cbMenuItemViewLocal.setLabel("Show Local");
        cbMenuItemViewLocal.setState(true);
        cbMenuItemViewLocal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                cbMenuItemViewLocal_itemStateChanged(e);
            }
        });
        cbMenuItemViewRemote.setLabel("Show Remote");
        cbMenuItemViewRemote.setState(true);
        cbMenuItemViewRemote.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                cbMenuItemViewRemote_itemStateChanged(e);
            }
        });
        cbMenuItemViewDirs.setLabel("Show Directories");
        cbMenuItemViewDirs.setState(true);
        cbMenuItemViewDirs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                cbMenuItemViewDirs_itemStateChanged(e);
            }
        });
        cbMenuItemViewFiles.setLabel("Show Files");
        cbMenuItemViewFiles.setState(true);
        menuFile.setLabel("File");
        menuItemConnect.setLabel("Connect");
        menuItemConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuItemConnect_actionPerformed(e);
            }
        });
        menuItemDisconnect.setLabel("Disconnect");
        menuItemReconnect.setLabel("Reconnect");
        menuItemReconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuItemReconnect_actionPerformed(e);
            }
        });
        menuItemDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuItemDisconnect_actionPerformed(e);
            }
        });
        cbMenuItemViewFiles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                cbMenuItemViewFiles_itemStateChanged(e);
            }
        });
        this.add(panelFtpRemote, BorderLayout.CENTER);
        menuBar.add(menuFile);
        menuBar.add(menuView);
        menuView.add(cbMenuItemViewLocal);
        menuView.add(cbMenuItemViewRemote);
        menuView.add(cbMenuItemViewDirs);
        menuView.add(cbMenuItemViewFiles);
        menuFile.add(menuItemConnect);
        menuFile.add(menuItemDisconnect);
        menuFile.add(menuItemReconnect);
        this.setMenuBar(menuBar);
    }

    public void setBackground(Color c) {
        super.setBackground(c);
        panelFtpRemote.setBackground(c);
    }

    public void setForeground(Color c) {
        super.setForeground(c);
        panelFtpRemote.setForeground(c);
    }

    public boolean getDebug() {
        return panelFtpRemote.getDebug();
    }

    public void setDebug(boolean flag) {
        panelFtpRemote.setDebug(flag);
    }

    public void setFileListFont(Font f) {
        panelFtpRemote.setFileListFont(f);
    }

    public String getRemoteHost() {
        return panelFtpRemote.getRemoteHost();
    }

    public void setRemoteHost(String s) {
        panelFtpRemote.setRemoteHost(s);
    }

    public String getProxyHost() {
        return panelFtpRemote.getProxyHost();
    }

    public void setProxyHost(String s) {
        panelFtpRemote.setProxyHost(s);
    }

    public String getProxyPort() {
        return panelFtpRemote.getProxyPort();
    }

    public void setProxyPort(String s) {
        panelFtpRemote.setProxyPort(s);
    }

    public String getUserId() {
        return panelFtpRemote.getUserId();
    }

    public void setUserId(String s) {
        panelFtpRemote.setUserId(s);
    }

    public String getPasswd() {
        return panelFtpRemote.getPasswd();
    }

    public void setPasswd(String s) {
        panelFtpRemote.setPasswd(s);
    }

    public String getAcct() {
        return panelFtpRemote.getAcct();
    }

    public void setAcct(String s) {
        panelFtpRemote.setAcct(s);
    }

    public Applet getApplet() {
        return panelFtpRemote.getApplet();
    }

    public void setApplet(PalTerm a) {
        panelFtpRemote.setApplet(a);
    }

    public void setSocksInfo(String socksHost, String socksPort) {
        panelFtpRemote.setSocksInfo(socksHost, socksPort);
    }

    public void ftpcmd_connect(String host) {
        panelFtpRemote.ftpcmd_connect(host);
    }

    public void ftpcmd_connect(String host, String id, String passwd, String acct) {
        panelFtpRemote.ftpcmd_connect(host, id, passwd, acct);
    }

    public void ftpcmd_login(String id, String passwd, String acct) {
        panelFtpRemote.ftpcmd_login(id, passwd, acct);
    }

    public void ftpcmd_disconnect() {
        panelFtpRemote.ftpcmd_disconnect();
    }

    public void ftpcmd_changeDir(String dir, boolean remote) {
        panelFtpRemote.ftpcmd_changeDir(dir, remote);
    }

    public void ftpcmd_download(String remoteFile, boolean remote, String localFile) {
        panelFtpRemote.ftpcmd_download(remoteFile, remote, localFile);
    }

    public void ftpcmd_upload(String localFile, boolean remote, String remoteFile) {
        panelFtpRemote.ftpcmd_upload(localFile, remote, remoteFile);
    }

    public void ftpcmd_makeDir(String dir, boolean remote) {
        panelFtpRemote.ftpcmd_makeDir(dir, remote);
    }

    public void ftpcmd_deleteFile(String fileName, boolean isDir, boolean remote) {
        panelFtpRemote.ftpcmd_deleteFile(fileName, isDir, remote);
    }

    public void ftpcmd_setType(boolean isAscii) {
        panelFtpRemote.ftpcmd_setType(isAscii);
    }

    public String ftpcmd_getStatus() {
        return panelFtpRemote.ftpcmd_getStatus();
    }

    public void setShowFileDetails(boolean flag) {
        cbMenuItemViewFiles.setState(flag);
        cbMenuItemViewFiles_itemStateChanged(null);
    }

    public void setShowLocal(boolean flag) {
        cbMenuItemViewLocal.setState(flag);
        cbMenuItemViewLocal_itemStateChanged(null);
    }

    public void setShowRemote(boolean flag) {
        cbMenuItemViewRemote.setState(flag);
        cbMenuItemViewRemote_itemStateChanged(null);
    }

    public void setDirSelectListener(DirSelectListener listener) {
        panelFtpRemote.setDirSelectListener(listener);
    }

    public void setFileSelectListener(DirSelectListener listener) {
        panelFtpRemote.setFileSelectListener(listener);
    }

    public void setTerminalSender(TerminalSender sender) {
        panelFtpRemote.setTerminalSender(sender);
    }

    public TerminalSender getTerminalSender() {
        return panelFtpRemote.getTerminalSender();
    }

    public void cleanup() {
        panelFtpRemote.cleanup();
    }

    void cbMenuItemViewLocal_itemStateChanged(ItemEvent e) {
        boolean flag = cbMenuItemViewLocal.getState();
        if (panelFtpRemote.getShowLocal() != flag)
            panelFtpRemote.setShowLocal(flag);
    }

    void cbMenuItemViewRemote_itemStateChanged(ItemEvent e) {
        boolean flag = cbMenuItemViewRemote.getState();
        if (panelFtpRemote.getShowRemote() != flag)
            panelFtpRemote.setShowRemote(flag);
    }

    void cbMenuItemViewDirs_itemStateChanged(ItemEvent e) {
        boolean flag = cbMenuItemViewDirs.getState();
        if (panelFtpRemote.getShowDirs() != flag)
            panelFtpRemote.setShowDirs(flag);
    }

    void cbMenuItemViewFiles_itemStateChanged(ItemEvent e) {
        boolean flag = cbMenuItemViewFiles.getState();
        if (panelFtpRemote.getShowFileDetails() != flag)
            panelFtpRemote.setShowFileDetails(flag);
    }

    void menuItemConnect_actionPerformed(ActionEvent e) {
        connect(true, true);
    }

    void menuItemDisconnect_actionPerformed(ActionEvent e) {
        connect(false, true);
    }

    void menuItemReconnect_actionPerformed(ActionEvent e) {
        reconnect(true);
    }


    /**
     * connectAction is true to establish a connection, and
     * false to disconnect.
     */
    public void connect(boolean connectAction, boolean forceShowDialog) {
        if (connectAction)
            panelFtpRemote.connect(forceShowDialog);
        else
            panelFtpRemote.ftpcmd_disconnect();
        boolean connected = panelFtpRemote.isConnected();
        menuItemConnect.enable(!connected);
        menuItemDisconnect.enable(connected);
        menuItemReconnect.enable(connected);
    }

    public void reconnect(boolean connectAction) {
        panelFtpRemote.ftpcmd_reconnect();
        menuItemConnect.enable(false);
        menuItemDisconnect.enable(true);
        menuItemReconnect.enable(true);
    }
}


