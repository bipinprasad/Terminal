
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.prasad.util.GridBagConstraints2;

public class ViewDialog extends Dialog {
    Panel panel1 = new Panel();
    BorderLayout borderLayout1 = new BorderLayout();
    CheckboxGroup checkboxGroup1 = new CheckboxGroup();
    Panel panel2 = new Panel();
    Checkbox chkbxAssocProg = new Checkbox();
    Checkbox chkbxInternalAsciiViewer = new Checkbox();
    Checkbox chkbxAnyViewer = new Checkbox();
    GridLayout gridLayout1 = new GridLayout();
    Panel panel3 = new Panel();
    Button btnCancel = new Button();
    Button btnView = new Button();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    Panel panel4 = new Panel();
    TextField txtViewer = new TextField();
    Button btnBrowse = new Button();
    BorderLayout borderLayout2 = new BorderLayout();
    PalTerm palTerm;


    public ViewDialog(PalTerm palTerm, Frame frame, String title, boolean modal) {
        super(frame, title, modal);
        this.palTerm = palTerm;
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
            add(panel1);
            pack();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public ViewDialog(PalTerm palTerm, Frame frame) {
        this(palTerm, frame, "", false);
    }


    //public ViewDialog(Frame frame, boolean modal)
    //{
    //	this(frame, "", modal);
    //}


    public ViewDialog(PalTerm palTerm, Frame frame, String title) {
        this(palTerm, frame, title, false);
    }

    void jbInit() throws Exception {
        chkbxAssocProg.setCheckboxGroup(checkboxGroup1);
        chkbxInternalAsciiViewer.setCheckboxGroup(checkboxGroup1);
        chkbxAnyViewer.setCheckboxGroup(checkboxGroup1);
        gridLayout1.setColumns(1);
        panel3.setLayout(gridBagLayout1);
        btnCancel.setLabel("Cancel");
        btnCancel.addActionListener(this::btnCancel_actionPerformed);
        panel4.setLayout(borderLayout2);
        btnBrowse.setLabel("...");
        btnBrowse.addActionListener(this::btnBrowse_actionPerformed);
        btnView.setLabel("  View  ");
        btnView.addActionListener(this::btnView_actionPerformed);
        gridLayout1.setRows(4);
        chkbxInternalAsciiViewer.setLabel("Internal ASCII Viewer");
        chkbxAssocProg.setVisible(false);
        chkbxAssocProg.setLabel("Associated Program");
        panel1.setLayout(borderLayout1);
        panel2.setLayout(gridLayout1);
        panel1.setSize(new Dimension(404, 126));
        checkboxGroup1.setSelectedCheckbox(chkbxInternalAsciiViewer);
        chkbxAnyViewer.setLabel("Viewer:");
        panel1.add(panel2, BorderLayout.CENTER);
        panel2.add(chkbxAssocProg, null);
        panel2.add(chkbxInternalAsciiViewer, null);
        panel2.add(chkbxAnyViewer, null);
        panel2.add(panel4, null);
        panel4.add(txtViewer, BorderLayout.CENTER);
        panel4.add(btnBrowse, BorderLayout.EAST);
        panel1.add(panel3, BorderLayout.EAST);
        panel3.add(btnView, new GridBagConstraints2(0, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
        panel3.add(btnCancel, new GridBagConstraints2(0, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            cancel();
        }
        super.processWindowEvent(e);
    }

    void cancel() {
        if (deleteAfterViewing
            && file != null)
            file.delete();

        dispose();
    }

    /**
     * Select a program to view the file
     */
    void btnBrowse_actionPerformed(ActionEvent e) {
        checkboxGroup1.setSelectedCheckbox(chkbxAnyViewer);
        FileDialog fileDialogLocal = PanelTerminal.createFileDialogLocal(palTerm.isNAV, palTerm.isIE, (Frame) getParent(), false, "Select Viewer Program", getDebug());
        fileDialogLocal.setTitle("Select Viewer Program");
        fileDialogLocal.setLocation(getLocation().x +
                getSize().width / 2,
            getLocation().y +
                getSize().height / 2);
        fileDialogLocal.setMode(java.awt.FileDialog.LOAD);
        fileDialogLocal.pack();
        fileDialogLocal.show();

        String dir = fileDialogLocal.getDirectory();
        String file = fileDialogLocal.getFile();
        if (file != null && file.length() > 0) {
            if (!dir.endsWith(File.separator))
                dir = dir + File.separator;
            txtViewer.setText(dir + file);
        }

        fileDialogLocal.dispose();
        fileDialogLocal = null;
    }

    private boolean debug;

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean b) {
        debug = b;
    }

    /**
     * Information about the file about to be viewed
     */
    private boolean deleteAfterViewing;
    private FileTemp file;

    public void setTextFile(boolean delete, FileTemp f) {
        deleteAfterViewing = delete;
        file = f;
    }

    void btnView_actionPerformed(ActionEvent e) {
        if (chkbxInternalAsciiViewer.getState()) {
            // use internal viewer
            AsciiViewer viewer = new AsciiViewer(palTerm.isNAV, palTerm.isIE);
            StringBuffer sb = new StringBuffer(1000);
            palTerm.userInfo.getLocalFileContents(palTerm, true, file.getPath(), sb);
            Point p = getLocation();
            viewer.setLocation(p.x + 10, p.y + 10);
            viewer.pack();
            viewer.show();
            viewer.setText(sb.toString());
        } else if (chkbxAnyViewer.getState()) {
            // use the txtViewer.getText() as the viewer
            String viewer = txtViewer.getText().trim();
            String filePath = file.getPath().replace('/', File.separatorChar).replace('\\', File.separatorChar);
            palTerm.exec(viewer + " " + filePath);
        }
    }

    void btnCancel_actionPerformed(ActionEvent e) {
        cancel();
    }
}


