
//Title:        Terminal Emulator
//Version:      
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;

public class FrameOptions extends Frame {
    Options options;

    Panel panelMiddle = new Panel();
    Panel panelBottom = new Panel();
    Checkbox chkbxShowNewToolbar = new Checkbox();
    Checkbox chkbxShowNewStatusBar = new Checkbox();
    Checkbox chkbxUseF12AsEnter = new Checkbox();
    Checkbox chkbxAltGo = new Checkbox();
    BorderLayout borderLayout1 = new BorderLayout();
    GridLayout gridLayout1 = new GridLayout();
    Button btnOk = new Button();
    Button btnCancel = new Button();


    public FrameOptions() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        chkbxShowNewToolbar.setLabel("Show Toolbar In New Terminal Window");
        chkbxShowNewStatusBar.setLabel("Show Status Bar In New Terminal Window");
        chkbxUseF12AsEnter.setLabel("Use F12 As Enter Key");
        chkbxAltGo.setLabel("Use Alternate Go Key");
        gridLayout1.setRows(4);
        gridLayout1.setColumns(1);
        btnOk.setLabel("OK");
        btnOk.addActionListener(new FrameOptions_btnOk_actionAdapter(this));
        btnCancel.setLabel("Cancel");
        btnCancel.addActionListener(new FrameOptions_btnCancel_actionAdapter(this));
        this.setSize(new Dimension(415, 178));
        this.setTitle("Terminal Options");
        panelMiddle.setLayout(gridLayout1);
        this.setLayout(borderLayout1);
        this.add(panelMiddle, BorderLayout.CENTER);
        panelMiddle.add(chkbxShowNewToolbar, null);
        panelMiddle.add(chkbxShowNewStatusBar, null);
        panelMiddle.add(chkbxUseF12AsEnter, null);
        panelMiddle.add(chkbxAltGo, null);
        this.add(panelBottom, BorderLayout.SOUTH);
        panelBottom.add(btnOk, null);
        panelBottom.add(btnCancel, null);
    }

    public void setOptions(Options opts) {
        options = opts;
        if (options != null) {
            chkbxAltGo.setState(options.getAltGo());
            chkbxUseF12AsEnter.setState(options.getDfltF12IsGo());
            chkbxShowNewStatusBar.setState(options.getDfltShowStatus());
            chkbxShowNewToolbar.setState(options.getDfltShowTools());
        }
    }

    void btnOk_actionPerformed(ActionEvent e) {
        if (options != null) {
            options.setAltGo(chkbxAltGo.getState());
            options.setDfltF12IsGo(chkbxUseF12AsEnter.getState());
            options.setDfltShowStatus(chkbxShowNewStatusBar.getState());
            options.setDfltShowTools(chkbxShowNewToolbar.getState());
        }
        setVisible(false);
    }

    void btnCancel_actionPerformed(ActionEvent e) {
        setVisible(false);
    }
}

class FrameOptions_btnOk_actionAdapter implements java.awt.event.ActionListener {
    FrameOptions adaptee;


    FrameOptions_btnOk_actionAdapter(FrameOptions adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnOk_actionPerformed(e);
    }
}

class FrameOptions_btnCancel_actionAdapter implements java.awt.event.ActionListener {
    FrameOptions adaptee;


    FrameOptions_btnCancel_actionAdapter(FrameOptions adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnCancel_actionPerformed(e);
    }
}


