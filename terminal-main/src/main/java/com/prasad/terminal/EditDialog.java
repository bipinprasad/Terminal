
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

public class EditDialog extends Dialog {
    Panel panel1 = new Panel();
    BorderLayout borderLayout1 = new BorderLayout();
    CheckboxGroup checkboxGroup1 = new CheckboxGroup();
    Panel panel2 = new Panel();
    Checkbox chkbxAssocProg = new Checkbox();
    Checkbox chkbxInternalAsciiEditor = new Checkbox();
    Checkbox chkbxAnyEditor = new Checkbox();
    GridLayout gridLayout1 = new GridLayout();
    Panel panel3 = new Panel();
    Button btnDone = new Button();
    Button btnEdit = new Button();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    Panel panel4 = new Panel();
    TextField txtEditor = new TextField();
    Button btnBrowse = new Button();
    BorderLayout borderLayout2 = new BorderLayout();
    private final boolean viewOnly;
    private final PalTerm palTerm;


    public EditDialog(PalTerm term, boolean view, Frame frame, String title, boolean modal) {
        super(frame, title, modal);
        this.palTerm = term;
        this.viewOnly = view;

        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
            add(panel1);
            pack();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public EditDialog(PalTerm term, boolean view, Frame frame) {
        this(term, view, frame, "", false);
    }


    public EditDialog(PalTerm term, boolean view, Frame frame, boolean modal) {
        this(term, view, frame, "", modal);
    }


    public EditDialog(PalTerm term, boolean view, Frame frame, String title) {
        this(term, view, frame, title, false);
    }

    void jbInit() throws Exception {
        chkbxAssocProg.setCheckboxGroup(checkboxGroup1);
        chkbxInternalAsciiEditor.setCheckboxGroup(checkboxGroup1);
        chkbxAnyEditor.setCheckboxGroup(checkboxGroup1);
        gridLayout1.setColumns(1);
        panel3.setLayout(gridBagLayout1);
        btnDone.setLabel("Done");
        txtEditor.setColumns(40);
        btnDone.addActionListener(e -> btnDone_actionPerformed(e));
        panel4.setLayout(borderLayout2);
        btnBrowse.setLabel("...");
        btnBrowse.addActionListener(e -> btnBrowse_actionPerformed(e));
        if (viewOnly)
            btnEdit.setLabel("  View  ");
        else
            btnEdit.setLabel("  Edit  ");
        btnEdit.addActionListener(e -> btnEdit_actionPerformed(e));
        gridLayout1.setRows(5);
        chkbxInternalAsciiEditor.setLabel("mpEdit ASCII Editor");
        chkbxAssocProg.setVisible(false);
        chkbxAssocProg.setLabel("Associated Program");
        panel1.setLayout(borderLayout1);
        panel2.setLayout(gridLayout1);
        panel1.setSize(new Dimension(469, 130));
        checkboxGroup1.setSelectedCheckbox(chkbxInternalAsciiEditor);
        chkbxAnyEditor.setLabel("Editor:");
        panel1.add(panel2, BorderLayout.CENTER);
        panel2.add(chkbxAssocProg, null);
        panel2.add(chkbxInternalAsciiEditor, null);
        panel2.add(chkbxAnyEditor, null);
        panel2.add(panel4, null);
        panel4.add(txtEditor, BorderLayout.CENTER);
        panel4.add(btnBrowse, BorderLayout.EAST);
        panel2.add(lblMessage, null);
        panel1.add(panel3, BorderLayout.EAST);
        panel3.add(btnEdit, new GridBagConstraints2(0, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
        panel3.add(btnDone, new GridBagConstraints2(0, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    }

    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            cancel();
        }
        super.processWindowEvent(e);
    }

    void cancel() {
        if (chkbxAnyEditor.getState())    // need to explicitly save
        {
            for (int i = 0; i < ftpDocOwners.length; i++) {
                if (ftpDocOwners[i] != null) {
                    ftpDocOwners[i].savedDoc(null);
                    ftpDocOwners[i].close();
                }
            }
        }
        dispose();
    }

    /**
     * Select a program to edit the file
     */
    void btnBrowse_actionPerformed(ActionEvent e) {
        checkboxGroup1.setSelectedCheckbox(chkbxAnyEditor);
        FileDialog fileDialogLocal = null;
        if (viewOnly) {
            fileDialogLocal = PanelTerminal.createFileDialogLocal(palTerm.isNAV, palTerm.isIE, (Frame) getParent(), false, "Select Viewer Program", getDebug());
            fileDialogLocal.setTitle("Select Viewer Program");
        } else {
            fileDialogLocal = PanelTerminal.createFileDialogLocal(palTerm.isNAV, palTerm.isIE, (Frame) getParent(), false, "Select Editor Program", getDebug());
            fileDialogLocal.setTitle("Select Editor Program");
        }
        fileDialogLocal.setLocation(
            getLocation().x + getSize().width / 2,
            getLocation().y + getSize().height / 2);
        fileDialogLocal.setMode(java.awt.FileDialog.LOAD);
        fileDialogLocal.pack();
        fileDialogLocal.show();

        String dir = fileDialogLocal.getDirectory();
        String file = fileDialogLocal.getFile();
        if (file != null && file.length() > 0) {
            if (!dir.endsWith(File.separator))
                dir = dir + File.separator;
            txtEditor.setText(dir + file);
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
     * Information about the files about to be edited
     */
    private boolean uploadAfterEditing;
    private PanelFtpRemote panelFtpRemote;
    private FileTemp[] editFiles;
    private String[] uploadPaths;
    private FtpDocOwner[] ftpDocOwners;
    Label lblMessage = new Label();

    public void setTextFiles(PanelFtpRemote panel, boolean upload, boolean deleteAfterUse, FileTemp[] localFiles, String[] remotePaths) {
        panelFtpRemote = panel;
        uploadAfterEditing = upload;
        editFiles = localFiles;
        ftpDocOwners = new FtpDocOwner[editFiles.length];
        for (int i = 0; i < editFiles.length; i++) {
            if (localFiles[i] != null)
                ftpDocOwners[i] = new FtpDocOwner(panel, upload, deleteAfterUse, localFiles[i].getPath(), (upload ? remotePaths[i] : null));
        }
        uploadPaths = remotePaths;
    }

    void btnEdit_actionPerformed(ActionEvent e) {
        if (chkbxInternalAsciiEditor.getState()) {
            // need to get out of the sandbox when running from a jar file on local machine
            // note: this code is repeated in every method that uses it !
            boolean success = false;
            if (palTerm.isNAV) {
                // try to get Netscape permission
                try {
                    throw new IllegalArgumentException("Netscape Browser support has been removed");
                    //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                    //netscape.security.PrivilegeManager.enablePrivilege("UniversalFileAccess");
                    //success = true;
                } catch (Throwable e2) {
                    success = false;
                }
            }

            if (palTerm.isIE) {
                // try Microsoft permission
                //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                try {
                    throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                    //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                    //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                    //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.EXEC);
                    //success = true;
                } catch (Throwable e2) {
                    success = false;
                    System.out.println("PalTerm.exec: Failed to get permissions for Microsoft Internet Explorer");
                }
            }

            try {
                Object textEditor = null;
                if (palTerm != null)
                    textEditor = palTerm.getTextEditor();
                mpTOOLS.mpEDIT.mpEDIT mpEdit = null;

                if (textEditor == null) {
                    mpEdit = new mpTOOLS.mpEDIT.mpEDIT(new mpTOOLS.mpEDIT.EditorOwnerInterface() {
                        public void openedDoc(mpTOOLS.mpEDIT.DocInterface doc) {
                        }

                        public void closingDoc(mpTOOLS.mpEDIT.DocInterface doc) {
                        }

                        public void lastFileClosed() {
                        }

                        public void editAction(mpTOOLS.mpEDIT.EditorInterface editor, String action) {
                        }
                    }, palTerm);
                    if (palTerm != null)
                        palTerm.setTextEditor(mpEdit);
                } else
                    mpEdit = (mpTOOLS.mpEDIT.mpEDIT) textEditor;

                for (int i = 0; i < editFiles.length; i++) {
                    if (editFiles[i] != null) {
                        mpTOOLS.mpEDIT.DocInterface doc = mpEdit.openDoc(ftpDocOwners[i], editFiles[i].getPath());
                        doc.setReadOnly(viewOnly);
                    }
                }
                dispose();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (chkbxAnyEditor.getState()) {
            // use the txtEditor.getText() as the editor
            String editor = txtEditor.getText().trim();
            String filePaths = "";
            for (int i = 0; i < editFiles.length; i++) {
                if (editFiles[i] != null)
                    filePaths += " " + editFiles[i].getPath().replace('/', File.separatorChar).replace('\\', File.separatorChar);
            }
            palTerm.exec(editor + " " + filePaths);
        }
        if (viewOnly)
            lblMessage.setText("Click on \"Done\" after viewing files");
        else
            lblMessage.setText("Click on \"Done\" after editing files");
    }

    void btnDone_actionPerformed(ActionEvent e) {
        cancel();
    }
}



