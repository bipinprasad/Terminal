
//Title:        Terminal Emulator
//Version:      
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.awt.*;

import com.prasad.util.GridBagConstraints2;

public class Panel1 extends Panel {
    BorderLayout borderLayout1 = new BorderLayout();
    Panel panel1 = new Panel();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    Label label1 = new Label();
    Label label2 = new Label();
    TextField textField1 = new TextField();
    TextField textField2 = new TextField();


    public Panel1() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        panel1.setLayout(gridBagLayout1);
        label1.setText("label1");
        label2.setText("label2");
        textField1.setText("textField1");
        textField2.setText("textField2");
        this.add(panel1, BorderLayout.CENTER);
        panel1.add(label1, new GridBagConstraints2(0, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel1.add(label2, new GridBagConstraints2(0, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel1.add(textField1, new GridBagConstraints2(1, 0, 1, 1, 1.0, 1.0
            , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panel1.add(textField2, new GridBagConstraints2(1, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    }
}

        
