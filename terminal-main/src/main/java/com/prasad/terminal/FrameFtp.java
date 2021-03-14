
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;

import com.ibm.network.ftp.protocol.*;
import com.ibm.network.ftp.event.*;

public class FrameFtp extends Frame // implements CommandListener, LocalFileListListener, RemoteFileListListener, StatusListener
{
    //com.ibm.network.ftp.ui.FTPUI ftpUI = new com.ibm.network.ftp.ui.FTPUI();
    //com.ibm.network.ftp.protocol.FTPProtocol ftpProtocol = new com.ibm.network.ftp.protocol.FTPProtocol();
    PanelFtpRemote panelFtpRemote;


    public FrameFtp(boolean isNAV, boolean isIE) {
        panelFtpRemote = new PanelFtpRemote(isNAV, isIE);

        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FrameFtp frameFtp = new FrameFtp(false, false);
        frameFtp.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    System.exit(0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        frameFtp.show();
        frameFtp.panelFtpRemote.setDebug(true);
        frameFtp.panelFtpRemote.setFileListFont(new Font("Courier", 0, 12));
        frameFtp.panelFtpRemote.ftpcmd_connect("www.prasad-assoc.com", "bipin", "password", null);
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(494, 364));
        this.add(panelFtpRemote, BorderLayout.CENTER);
        //this.add(ftpUI, BorderLayout.CENTER);

        //ftpProtocol.addStatusListener(ftpUI);
        //ftpProtocol.addRemoteFileListListener(ftpUI);
        //ftpProtocol.addLocalFileListListener(ftpUI);
        //ftpUI.addCommandListener(ftpProtocol);
    }
}


