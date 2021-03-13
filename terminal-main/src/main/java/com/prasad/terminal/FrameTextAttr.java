
//Title:        Terminal Emulator
//Version:      
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;

public class FrameTextAttr extends Frame {
    TextAttrib[] textAttribs;

    private static final int NORMAL_IDX = 0;
    private static final int DIM_IDX = 1;
    private static final int UNDERLINE_IDX = 2;
    private static final int DIMUNDERLINE_IDX = 3;
    private static final int BLINK_IDX = 4;
    private static final int DIMBLINK_IDX = 5;
    private static final int UNDERLINEBLINK_IDX = 6;
    private static final int DIMUNDERLINEBLINK_IDX = 7;

    private static final int INVERSE_IDX = 8;
    private static final int INVERSEDIM_IDX = 9;
    private static final int INVERSEUNDERLINE_IDX = 10;
    private static final int INVERSEDIMUNDERLINE_IDX = 11;
    private static final int INVERSEBLINK_IDX = 12;
    private static final int INVERSEDIMBLINK_IDX = 13;
    private static final int INVERSEUNDERLINEBLINK_IDX = 14;
    private static final int INVERSEDIMUNDERLINEBLINK_IDX = 15;

    Panel panelCenter = new Panel();
    Panel panelButton = new Panel();
    Button btnOk = new Button();
    Button btnModify = new Button();
    Button btnCancel = new Button();
    CheckboxGroup checkboxAttrsGroup = new CheckboxGroup();
    Checkbox[] cbAttrs = new Checkbox[16];

    GridLayout gridLayout1 = new GridLayout();
    Panel panelTop = new Panel();
    TextArea textAreaHelp = new TextArea();


    private Component parentComponent;

    public FrameTextAttr(Component p) {
        parentComponent = p;
        try {
            textAttribs = TextAttrib.getDefaults();
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        panelCenter.setLayout(gridLayout1);
        this.setSize(new Dimension(477, 441));
        this.setTitle("Text Attribute Choices");
        btnOk.setLabel("OK");
        btnOk.addActionListener(new FrameTextAttr_btnOk_actionAdapter(this));
        btnModify.setLabel("Modify");
        btnModify.addActionListener(new FrameTextAttr_btnModify_actionAdapter(this));
        btnCancel.setLabel("Cancel");
        btnCancel.addActionListener(new FrameTextAttr_btnCancel_actionAdapter(this));

        btnOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnModify.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        for (int i = 0; i < 16; i++) {
            cbAttrs[i] = new Checkbox();
            cbAttrs[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            cbAttrs[i].setCheckboxGroup(checkboxAttrsGroup);
            switch (i) {
                case NORMAL_IDX:
                    cbAttrs[i].setLabel("Normal Text");
                    break;
                case DIM_IDX:
                    cbAttrs[i].setLabel("Dim Text");
                    break;
                case UNDERLINE_IDX:
                    cbAttrs[i].setLabel("Underline Text");
                    break;
                case DIMUNDERLINE_IDX:
                    cbAttrs[i].setLabel("Dim Underline Text");
                    break;
                case BLINK_IDX:
                    cbAttrs[i].setLabel("Blink Text");
                    break;
                case DIMBLINK_IDX:
                    cbAttrs[i].setLabel("Dim Blink Text");
                    break;
                case UNDERLINEBLINK_IDX:
                    cbAttrs[i].setLabel("Underline Blink");
                    break;
                case DIMUNDERLINEBLINK_IDX:
                    cbAttrs[i].setLabel("Dim Underline Blink Text");
                    break;
                case INVERSE_IDX:
                    cbAttrs[i].setLabel("Inverse Text");
                    break;
                case INVERSEDIM_IDX:
                    cbAttrs[i].setLabel("Inverse Dim Text");
                    break;
                case INVERSEUNDERLINE_IDX:
                    cbAttrs[i].setLabel("Inverse Underline Text");
                    break;
                case INVERSEDIMUNDERLINE_IDX:
                    cbAttrs[i].setLabel("Inverse Dim Underline Text");
                    break;
                case INVERSEBLINK_IDX:
                    cbAttrs[i].setLabel("Inverse Blink Text");
                    break;
                case INVERSEDIMBLINK_IDX:
                    cbAttrs[i].setLabel("Inverse Dim Blink");
                    break;
                case INVERSEUNDERLINEBLINK_IDX:
                    cbAttrs[i].setLabel("Inverse Underline Blink");
                    break;
                case INVERSEDIMUNDERLINEBLINK_IDX:
                    cbAttrs[i].setLabel("Inverse Dim Underline Blink Text");
                    break;
            }
        }

        checkboxAttrsGroup.setSelectedCheckbox(cbAttrs[1]);
        checkboxAttrsGroup.setCurrent(cbAttrs[1]);
        textAreaHelp.setEnabled(false);
        textAreaHelp.setText(
            "This dialog box lets you select the text type that you want to modify:\n" +
                "normal, dim, underline, blink, inverse, or a combination of these.\n\n" +
                "You can modify the foreground and background color, \n" +
                "and add or subtract the underline attribute.\n\n" +
                "Choose the option button that describes the attribute.\n" +
                "Then choose Modify to change the text attributes.");
        textAreaHelp.setEditable(false);
        textAreaHelp.setRows(3);


        gridLayout1.setColumns(2);
        gridLayout1.setRows(8);
        this.add(panelCenter, BorderLayout.CENTER);

        panelCenter.add(cbAttrs[NORMAL_IDX], null);
        panelCenter.add(cbAttrs[INVERSE_IDX], null);
        panelCenter.add(cbAttrs[DIM_IDX], null);
        panelCenter.add(cbAttrs[INVERSEDIM_IDX], null);
        panelCenter.add(cbAttrs[INVERSEUNDERLINE_IDX], null);
        panelCenter.add(cbAttrs[UNDERLINE_IDX], null);
        panelCenter.add(cbAttrs[DIMUNDERLINE_IDX], null);
        panelCenter.add(cbAttrs[INVERSEDIMUNDERLINE_IDX], null);
        panelCenter.add(cbAttrs[BLINK_IDX], null);
        panelCenter.add(cbAttrs[INVERSEBLINK_IDX], null);
        panelCenter.add(cbAttrs[DIMBLINK_IDX], null);
        panelCenter.add(cbAttrs[INVERSEDIMBLINK_IDX], null);
        panelCenter.add(cbAttrs[UNDERLINEBLINK_IDX], null);
        panelCenter.add(cbAttrs[INVERSEUNDERLINEBLINK_IDX], null);
        panelCenter.add(cbAttrs[DIMUNDERLINEBLINK_IDX], null);
        panelCenter.add(cbAttrs[INVERSEDIMUNDERLINEBLINK_IDX], null);

        this.add(panelButton, BorderLayout.SOUTH);
        panelButton.add(btnOk, null);
        panelButton.add(btnModify, null);
        panelButton.add(btnCancel, null);
        this.add(panelTop, BorderLayout.NORTH);
        panelTop.add(textAreaHelp, null);
    }

    private TextAttrib[] savedTextAttribs;

    public void setTextAttribs(TextAttrib[] a) {
        savedTextAttribs = a;
        if (a == null)
            return;
        for (int i = 0; i < a.length; i++) {
            if (i < textAttribs.length) {
                textAttribs[i].fore = a[i].fore;
                textAttribs[i].back = a[i].back;
                textAttribs[i].underLine = a[i].underLine;
            } else
                break;
            if (i < 16) {
                cbAttrs[i].setBackground(textAttribs[i].back);
                cbAttrs[i].setForeground(textAttribs[i].fore);
            }
        }
    }

    void btnOk_actionPerformed(ActionEvent e) {
        for (int i = 0; i < savedTextAttribs.length && i < textAttribs.length; i++) {
            if (i == 0
                && savedTextAttribs[i].back != textAttribs[i].back)
                parentComponent.setBackground(textAttribs[0].back);

            savedTextAttribs[i] = textAttribs[i];
        }

        dispose();
    }

    void btnCancel_actionPerformed(ActionEvent e) {
        dispose();
    }

    void btnModify_actionPerformed(ActionEvent e) {
        Checkbox cb = checkboxAttrsGroup.getSelectedCheckbox();
        int idx = 0;
        for (int i = 0; i < cbAttrs.length; i++) {
            if (cbAttrs[i] == cb) {
                idx = i;
                break;
            }
        }

        FrameChangeAttr f = new FrameChangeAttr(textAttribs[idx], cbAttrs[idx]);
        f.setLocation(getLocation().x + 10, getLocation().y + 10);
        f.show();
    }
}

class FrameTextAttr_btnOk_actionAdapter implements java.awt.event.ActionListener {
    FrameTextAttr adaptee;


    FrameTextAttr_btnOk_actionAdapter(FrameTextAttr adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.btnOk_actionPerformed(e);
    }
}

class FrameTextAttr_btnCancel_actionAdapter implements java.awt.event.ActionListener {
    FrameTextAttr adaptee;


    FrameTextAttr_btnCancel_actionAdapter(FrameTextAttr adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.btnCancel_actionPerformed(e);
    }
}

class FrameTextAttr_btnModify_actionAdapter implements java.awt.event.ActionListener {
    FrameTextAttr adaptee;


    FrameTextAttr_btnModify_actionAdapter(FrameTextAttr adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.btnModify_actionPerformed(e);
    }
}


