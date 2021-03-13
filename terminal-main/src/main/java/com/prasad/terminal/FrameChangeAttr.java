
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;

import com.prasad.util.GridBagConstraints2;
import com.prasad.util.ColorPicker;

public class FrameChangeAttr extends Frame {
    TextAttrib textAttrib;
    Panel panelCenter = new Panel();
    Panel panelButton = new Panel();
    Button btnOk = new Button();
    Button btnCancel = new Button();

    Panel panelTop = new Panel();

    public FrameChangeAttr(TextAttrib a, Checkbox cbAttr) {
        try {
            savedTextAttrib = textAttrib = a;
            jbInit();
            setTextAttrib(a, cbAttr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        panelCenter.setLayout(gridBagLayout1);
        this.setSize(new Dimension(357, 248));
        this.setTitle("Change Text Attribute");
        btnOk.setLabel("OK");
        btnOk.addActionListener(new FrameChangeAttr_btnOk_actionAdapter(this));
        btnCancel.setLabel("Cancel");
        labelFore.setAlignment(2);
        labelFore.setText("Text Foreground");
        labelBack.setAlignment(2);
        labelBack.setText("Text Background");
        labelAttrName.setText("Attribute Name");
        cbUnderline.setLabel("Underline");
        panelBack.addMouseListener(new FrameChangeAttr_panelBack_mouseAdapter(this));
        panelFore.addMouseListener(new FrameChangeAttr_panelFore_mouseAdapter(this));
        btnCancel.addActionListener(new FrameChangeAttr_btnCancel_actionAdapter(this));

        this.add(panelCenter, BorderLayout.CENTER);
        panelCenter.add(labelAttrName, new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelCenter.add(labelFore, new GridBagConstraints2(0, 1, 1, 1, 1.0, 1.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelCenter.add(labelBack, new GridBagConstraints2(0, 2, 1, 1, 1.0, 1.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelCenter.add(cbUnderline, new GridBagConstraints2(0, 3, 1, 1, 1.0, 1.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelCenter.add(panelFore, new GridBagConstraints2(1, 1, 1, 1, 1.0, 1.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 40, 40));
        panelCenter.add(panelBack, new GridBagConstraints2(1, 2, 1, 1, 1.0, 1.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 40, 40));

        panelFore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        this.add(panelButton, BorderLayout.SOUTH);
        panelButton.add(btnOk, null);
        panelButton.add(btnCancel, null);
        this.add(panelTop, BorderLayout.NORTH);
    }

    private TextAttrib savedTextAttrib;
    private Checkbox cbAttr; // checkbox in the calling frame
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    Label labelFore = new Label();
    Label labelBack = new Label();
    Checkbox cbUnderline = new Checkbox();
    Panel panelFore = new Panel();
    Panel panelBack = new Panel();
    Label labelAttrName = new Label();


    public void setTextAttrib(TextAttrib a, Checkbox cbAttr) {
        savedTextAttrib = a;
        this.cbAttr = cbAttr;
        if (a == null)
            return;

        textAttrib.fore = a.fore;
        textAttrib.back = a.back;
        textAttrib.underLine = a.underLine;

        cbUnderline.setState(a.underLine);
        labelAttrName.setText(cbAttr.getLabel());
        panelFore.setBackground(a.fore);
        panelBack.setBackground(a.back);
    }

    void btnOk_actionPerformed(ActionEvent e) {
        savedTextAttrib.fore = textAttrib.fore;
        savedTextAttrib.back = textAttrib.back;
        savedTextAttrib.underLine = textAttrib.underLine;
        cbAttr.setForeground(textAttrib.fore);
        cbAttr.setBackground(textAttrib.back);
        dispose();
    }

    void btnCancel_actionPerformed(ActionEvent e) {
        dispose();
    }

    void panelFore_mouseClicked(MouseEvent e) {
        ColorPicker colorPicker = new ColorPicker(this, true);
        colorPicker.setColor(panelFore.getBackground());
        colorPicker.pack();
        colorPicker.setLocation(getLocation().x + e.getX() + 10, getLocation().y + e.getY() + 10);
        colorPicker.show();

        Color color = colorPicker.getColor();
        if (color != null) {
            panelFore.setBackground(color);
            textAttrib.fore = color;
        }
        colorPicker.dispose();
    }

    void panelBack_mouseClicked(MouseEvent e) {
        ColorPicker colorPicker = new ColorPicker(this, true);
        colorPicker.setColor(panelBack.getBackground());
        colorPicker.pack();
        colorPicker.setLocation(getLocation().x + e.getX() + 10, getLocation().y + e.getY() + 10);
        colorPicker.show();

        Color color = colorPicker.getColor();
        if (color != null) {
            panelBack.setBackground(color);
            textAttrib.back = color;
        }
        colorPicker.dispose();
    }
}

class FrameChangeAttr_btnOk_actionAdapter implements java.awt.event.ActionListener {
    FrameChangeAttr adaptee;


    FrameChangeAttr_btnOk_actionAdapter(FrameChangeAttr adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnOk_actionPerformed(e);
    }
}

class FrameChangeAttr_btnCancel_actionAdapter implements java.awt.event.ActionListener {
    FrameChangeAttr adaptee;


    FrameChangeAttr_btnCancel_actionAdapter(FrameChangeAttr adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnCancel_actionPerformed(e);
    }
}

class FrameChangeAttr_panelFore_mouseAdapter extends java.awt.event.MouseAdapter {
    FrameChangeAttr adaptee;


    FrameChangeAttr_panelFore_mouseAdapter(FrameChangeAttr adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.panelFore_mouseClicked(e);
    }
}

class FrameChangeAttr_panelBack_mouseAdapter extends java.awt.event.MouseAdapter {
    FrameChangeAttr adaptee;


    FrameChangeAttr_panelBack_mouseAdapter(FrameChangeAttr adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.panelBack_mouseClicked(e);
    }
}


