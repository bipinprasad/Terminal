
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

public class AsciiViewer extends Frame implements ClipboardOwner {
    TextArea textArea = new TextArea();
    BorderLayout borderLayout1 = new BorderLayout();
    Panel panel1 = new Panel();
    Button btnOk = new Button();
    Button btnCopy = new Button();
    Button btnFont = new Button();
    boolean isNAV;
    boolean isIE;

    public AsciiViewer(boolean isNAV, boolean isIE) {
        this.isNAV = isNAV;
        this.isIE = isIE;

        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() {
        this.setLayout(borderLayout1);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        textArea.setFont(new Font("Courier", Font.PLAIN, 12));
        textArea.setEditable(false);
        btnOk.setLabel("Ok");
        btnOk.addActionListener(this::btnOk_actionPerformed);
        btnCopy.setLabel("Copy To Clipboard");
        btnCopy.addActionListener(this::btnCopy_actionPerformed);
        btnFont.setLabel("Font");
        btnFont.addActionListener(this::btnFont_actionPerformed);
        this.add(textArea, BorderLayout.CENTER);
        this.add(panel1, BorderLayout.SOUTH);
        panel1.add(btnOk, null);
        panel1.add(btnCopy, null);
        panel1.add(btnFont, null);
    }

    void btnOk_actionPerformed(ActionEvent e) {
        dispose();
    }

    void this_windowClosing(WindowEvent e) {
        dispose();
    }

    void btnCopy_actionPerformed(ActionEvent e) {
        String s = textArea.getSelectedText();
        //boolean success = false;

        if (isNAV) {
            // try to get Netscape permission
            try {
                // for more targets goto:
                //http://developer.netscape.com/docs/manuals/signedobj/targets/index.htm
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalSystemClipboardAccess");
                //success = true;
            } catch (Throwable e2) {
                e2.printStackTrace();
            }
        }

        if (isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Internet Explorer permissions have been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                //if (debug)
                //	System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                //if (debug)
                //	System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.PROPERTY);
                //if (debug)
                //	System.out.println("Got PROPERTY permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e2) {
                e2.printStackTrace();
            }
        }

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        if (s != null)
            clipboard.setContents(new StringSelection(s), this);
    }

    public void setText(String s) {
        textArea.setText(s);
    }

    private boolean debug;

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean b) {
        debug = b;
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        //TODO: implement this java.awt.datatransfer.ClipboardOwner method;
    }

    private com.prasad.util.FontPicker fontPicker;

    void btnFont_actionPerformed(ActionEvent e) {
        if (fontPicker == null)
            fontPicker = new com.prasad.util.FontPicker(this, true);

        // Get the font name, style and size from the button action
        Font currentFont = textArea.getFont();
        fontPicker.setFont(currentFont);
        fontPicker.pack();
        fontPicker.setVisible(true);

        if ((currentFont = fontPicker.getFont()) != null)
            textArea.setFont(currentFont);
    }
}


