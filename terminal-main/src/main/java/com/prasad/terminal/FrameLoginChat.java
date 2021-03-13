
//Title:        Terminal Emulator
//Version:      
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.awt.*;
import com.prasad.util.GridBagConstraints2;

public class FrameLoginChat extends Frame {
    Panel panelCenter = new Panel();
    Panel panelBottom = new Panel();
    Panel panelTop = new Panel();
    Button btnCancel = new Button();
    Button btnSave = new Button();
    ScrollPane scrollPane1 = new ScrollPane();
    ScrollPane scrollPane2 = new ScrollPane();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    Label lblLoginChat = new Label();
    pvTreeJ.PVTree treeChat = new pvTreeJ.PVTree();
    Button btnTest = new Button();


    public FrameLoginChat() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FrameLoginChat frameLoginChat1 = new FrameLoginChat();

    }

    private void jbInit() throws Exception {
        btnCancel.setLabel("Cancel");
        lblLoginChat.setText("Login Chat Definition");
        treeChat.setBounds(new Rectangle(29, 27, 160, 160));
        btnTest.setLabel("Test");
        panelCenter.setLayout(gridBagLayout1);
        btnSave.setLabel("Save");
        this.add(panelCenter, BorderLayout.CENTER);
        panelCenter.add(scrollPane1, new GridBagConstraints2(0, 0, 1, 1, 2.0, 1.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        scrollPane1.add(treeChat, null);
        panelCenter.add(scrollPane2, new GridBagConstraints2(1, 0, 1, 1, 3.0, 1.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(panelBottom, BorderLayout.SOUTH);
        panelBottom.add(btnSave, null);
        panelBottom.add(btnTest, null);
        panelBottom.add(btnCancel, null);
        this.add(panelTop, BorderLayout.NORTH);
        panelTop.add(lblLoginChat, null);
    }
}

                                     
