
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import com.prasad.util.GridBagConstraints2;

import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import java.util.Vector;

public class DirSyncFrame extends Frame {
    Panel panelCenter = new Panel();
    Panel panelBottom = new Panel();
    Panel panelTop = new Panel();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    Label lblRemoteDir = new Label();
    Label lblLocalDir = new Label();
    Label lblSyncDateLocal = new Label();
    TextField txtRemoteDir = new TextField();
    TextField txtLocalDir = new TextField();
    TextField txtSyncDateLocal = new TextField();
    ScrollPane scrollPane1 = new ScrollPane();
    Button btnOk = new Button();
    Button btnCancel = new Button();
    Button btnFirst = new Button();
    Button btnPrev = new Button();
    Button btnNext = new Button();
    Button btnLast = new Button();
    Button btnDelete = new Button();
    Button btnAdd = new Button();
    Button btnUpdate = new Button();
    Button btnFind = new Button();
    Label lblSyncDateRemote = new Label();
    TextField txtSyncDateRemote = new TextField();
    Button btnBrowseRemote = new Button();
    Button btnBrowseLocalDir = new Button();
    Button btnSyncNow = new Button();
    Panel panelFileSpecs = new Panel();
    GridBagLayout gridBagLayout2 = new GridBagLayout();
    TextField txtFileSpec = new TextField();
    Checkbox chkbxBinary = new Checkbox();
    Button btnSelectFile = new Button();
    Label lblFileSpec = new Label();
    Button btnRefreshFileSpecs = new Button();
    Label lblRemoteHost = new Label();
    TextField txtRemoteHost = new TextField();


    public DirSyncFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DirSyncFrame dirSyncFrame1 = new DirSyncFrame();
        dirSyncFrame1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
        dirSyncFrame1.setVisible(true);
        for (int i = 1; i < 9; i++) {
            dirSyncFrame1.txtRemoteHost.setText("" + i);
            dirSyncFrame1.txtRemoteDir.setText("" + i);
            dirSyncFrame1.txtLocalDir.setText("" + i);
            dirSyncFrame1.btnAdd_actionPerformed(null);
        }
    }

    private void jbInit() {
        this.setSize(new Dimension(523, 345));
        this.setTitle("Directory Synchronization Info");
        lblRemoteDir.setAlignment(Label.RIGHT);
        lblRemoteDir.setText("*Remote Directory");
        lblLocalDir.setAlignment(Label.RIGHT);
        lblLocalDir.setText("*Local Directory");
        lblSyncDateLocal.setAlignment(Label.RIGHT);
        lblSyncDateLocal.setText("Sync Date Local");
        btnOk.setLabel("OK");
        btnOk.addActionListener(this::btnOk_actionPerformed);
        btnCancel.setLabel("Cancel");
        btnCancel.addActionListener(this::btnCancel_actionPerformed);
        btnBrowseRemote.setLabel("...");
        btnBrowseLocalDir.setLabel("...");
        btnSyncNow.setLabel("Sync Now");
        btnSyncNow.addActionListener(this::btnSyncNow_actionPerformed);
        chkbxBinary.setLabel("Binary");
        btnSelectFile.setLabel("...");
        btnRefreshFileSpecs.setLabel("Refresh Files");
        btnRefreshFileSpecs.addActionListener(this::btnRefreshFileSpecs_actionPerformed);
        lblRemoteHost.setAlignment(Label.RIGHT);
        lblRemoteHost.setText("*Remote Host");
        btnFind.setLabel("Find");
        btnFind.addActionListener(this::btnFind_actionPerformed);
        lblFileSpec.setText("Add files to be synced (wild cards are preferred)");
        panelFileSpecs.setLayout(gridBagLayout2);
        btnFirst.setLabel("First");
        btnFirst.addActionListener(this::btnFirst_actionPerformed);
        btnPrev.setLabel("Previous");
        btnPrev.addActionListener(this::btnPrev_actionPerformed);
        btnNext.setLabel("Next");
        btnNext.addActionListener(this::btnNext_actionPerformed);
        btnLast.setLabel("Last");
        btnLast.addActionListener(this::btnLast_actionPerformed);
        btnDelete.setLabel("Delete");
        btnDelete.addActionListener(this::btnDelete_actionPerformed);
        btnAdd.setLabel("Add");
        btnAdd.addActionListener(this::btnAdd_actionPerformed);
        btnUpdate.setLabel("Update");
        btnUpdate.addActionListener(this::btnUpdate_actionPerformed);
        lblSyncDateRemote.setAlignment(Label.RIGHT);
        lblSyncDateRemote.setText("Sync Date Remote");
        panelCenter.setLayout(gridBagLayout1);
        this.add(panelCenter, BorderLayout.CENTER);
        panelCenter.add(lblRemoteDir, new GridBagConstraints2(0, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelCenter.add(lblLocalDir, new GridBagConstraints2(0, 2, 1, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelCenter.add(lblSyncDateLocal, new GridBagConstraints2(0, 3, 1, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelCenter.add(txtRemoteDir, new GridBagConstraints2(1, 1, 1, 1, 1.0, 1.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 0), 0, 0));
        panelCenter.add(txtLocalDir, new GridBagConstraints2(1, 2, 1, 1, 1.0, 1.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 0), 0, 0));
        panelCenter.add(txtSyncDateLocal, new GridBagConstraints2(1, 3, 1, 1, 1.0, 1.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 0), 0, 0));
        panelCenter.add(scrollPane1, new GridBagConstraints2(0, 6, 3, 1, 1.0, 1.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        scrollPane1.add(panelFileSpecs, null);
        panelFileSpecs.add(txtFileSpec, new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0
            , GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        panelFileSpecs.add(chkbxBinary, new GridBagConstraints2(2, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
        panelFileSpecs.add(btnSelectFile, new GridBagConstraints2(1, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelCenter.add(lblSyncDateRemote, new GridBagConstraints2(0, 4, 1, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelCenter.add(txtSyncDateRemote, new GridBagConstraints2(1, 4, 1, 1, 1.0, 1.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 0), 0, 0));
        panelCenter.add(btnBrowseRemote, new GridBagConstraints2(2, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelCenter.add(btnBrowseLocalDir, new GridBagConstraints2(2, 2, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelCenter.add(lblFileSpec, new GridBagConstraints2(1, 5, 2, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelCenter.add(btnRefreshFileSpecs, new GridBagConstraints2(0, 5, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(4, 4, 4, 4), 0, 0));
        panelCenter.add(lblRemoteHost, new GridBagConstraints2(0, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelCenter.add(txtRemoteHost, new GridBagConstraints2(1, 0, 1, 1, 1.0, 1.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 0), 0, 0));
        this.add(panelBottom, BorderLayout.SOUTH);
        panelBottom.add(btnOk, null);
        panelBottom.add(btnCancel, null);
        this.add(panelTop, BorderLayout.NORTH);
        panelTop.add(btnFirst, null);
        panelTop.add(btnPrev, null);
        panelTop.add(btnNext, null);
        panelTop.add(btnLast, null);
        panelTop.add(btnFind, null);
        panelTop.add(btnDelete, null);
        panelTop.add(btnAdd, null);
        panelTop.add(btnUpdate, null);
        panelTop.add(btnSyncNow, null);
    }

    private TextField[] txtFileSpecs;
    private Checkbox[] chkbxBinarys;
    private Button[] btnSelectFiles;

    private void setTransferFileSpecs(TransferFileSpec[] specs) {
        transferFileSpecs = specs;
        if (txtFileSpecs != null) {
            for (TextField fileSpec : txtFileSpecs) panelFileSpecs.remove(fileSpec);
        }
        if (chkbxBinarys != null) {
            for (Checkbox binary : chkbxBinarys) panelFileSpecs.remove(binary);
        }
        if (btnSelectFiles != null) {
            for (Button selectFile : btnSelectFiles) panelFileSpecs.remove(selectFile);
        }

        int iMax;
        if (transferFileSpecs != null
            && (iMax = transferFileSpecs.length) > 0) {
            txtFileSpecs = new TextField[iMax];
            chkbxBinarys = new Checkbox[iMax];
            btnSelectFiles = new Button[iMax];

            for (int i = 0; i < iMax; i++) {
                txtFileSpecs[i] = new TextField(transferFileSpecs[i].pattern);
                chkbxBinarys[i] = new Checkbox("Binary", transferFileSpecs[i].binary);
                btnSelectFiles[i] = new Button("..");

                panelFileSpecs.add(txtFileSpecs[i], new GridBagConstraints2(0, i + 1, 1, 1, 1.0, 1.0
                    , GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
                panelFileSpecs.add(chkbxBinarys[i], new GridBagConstraints2(2, i + 1, 1, 1, 0.0, 0.0
                    , GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
                panelFileSpecs.add(btnSelectFiles[i], new GridBagConstraints2(1, i + 1, 1, 1, 0.0, 0.0
                    , GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
            }
        }
    }

    private MyHashtable<String, DirSyncInfo> savedDirSyncInfos;
    private Vector<String> keysVector;
    private MyHashtable<String, DirSyncInfo> dirSyncInfos;
    private int dirSyncIdx;
    private TransferFileSpec[] transferFileSpecs;
    private TransferFileSpec transferFileSpec;

    public MyHashtable<String, DirSyncInfo> getDirSyncInfos() {
        return dirSyncInfos;
    }

    public void setDirSyncInfos(MyHashtable<String, DirSyncInfo> infos) {
        savedDirSyncInfos = infos;
        if (infos != null
            && infos.size() > 0) {
            keysVector = new Vector<>();
            dirSyncInfos = new MyHashtable<>();
            Enumeration<String> enumeration = infos.keys();
            if (enumeration != null) {
                while (enumeration.hasMoreElements()) {
                    String key = enumeration.nextElement();
                    keysVector.addElement(key);
                    DirSyncInfo tmp = infos.get(key);
                    if (tmp != null)
                        dirSyncInfos.put(key, (DirSyncInfo) tmp.clone());
                }
            }
            dirSyncIdx = 0;
            if (keysVector != null
                && keysVector.size() > 0) {
                String key = keysVector.elementAt(0);
                setDirSyncInfo(dirSyncInfos.get(key));
            }
        } else {
            dirSyncInfos = null;
            setDirSyncInfo(null);
        }
        dirSyncIdx = 0;
    }

    private void setDirSyncInfo(DirSyncInfo info) {

        txtRemoteHost.setText("");
        txtRemoteDir.setText("");
        txtLocalDir.setText("");
        txtSyncDateLocal.setText("");
        txtSyncDateRemote.setText("");

        if (info != null) {
            if (info.remoteHost != null)
                txtRemoteHost.setText(info.remoteHost);

            if (info.remoteDir != null)
                txtRemoteDir.setText(info.remoteDir);

            if (info.localDir != null)
                txtLocalDir.setText(info.localDir);

            if (info.lastSyncDateTimeLocal != null)
                txtSyncDateLocal.setText("" + info.lastSyncDateTimeLocal);

            if (info.lastSyncDateTimeRemote != null)
                txtSyncDateRemote.setText("" + info.lastSyncDateTimeRemote);

            setTransferFileSpecs(info.transferFileSpecs);
        } else
            setTransferFileSpecs(null);
    }

    void btnFirst_actionPerformed(ActionEvent e) {
        DirSyncInfo info = dirSyncInfos.first();
        if (info != null)
            setDirSyncInfo(info);
    }

    void btnPrev_actionPerformed(ActionEvent e) {
        DirSyncInfo info = dirSyncInfos.previous();
        if (info != null)
            setDirSyncInfo(info);
    }

    void btnNext_actionPerformed(ActionEvent e) {
        DirSyncInfo info = dirSyncInfos.next();
        if (info != null)
            setDirSyncInfo(info);
    }

    void btnLast_actionPerformed(ActionEvent e) {
        DirSyncInfo info = dirSyncInfos.last();
        if (info != null)
            setDirSyncInfo(info);
    }

    void btnFind_actionPerformed(ActionEvent e) {
        String key = txtRemoteHost.getText().trim() + "." + txtRemoteDir.getText() + "." + txtLocalDir.getText();
        DirSyncInfo info = dirSyncInfos.get(key);
        if (info != null)
            setDirSyncInfo(info);
    }

    void btnDelete_actionPerformed(ActionEvent e) {
        String key = txtRemoteHost.getText().trim() + "." + txtRemoteDir.getText() + "." + txtLocalDir.getText();
        dirSyncInfos.remove(key);
        DirSyncInfo info = dirSyncInfos.current();
        if (info != null)
            setDirSyncInfo(info);
    }

    void btnAdd_actionPerformed(ActionEvent e) {
        DirSyncInfo info = new DirSyncInfo();
        info.remoteHost = txtRemoteHost.getText().trim();
        info.remoteDir = txtRemoteDir.getText().trim();
        info.localDir = txtLocalDir.getText().trim();
        if (transferFileSpecs != null) {
            int iMax = transferFileSpecs.length;
            info.transferFileSpecs = new TransferFileSpec[iMax];
            for (int i = 0; i < iMax; i++) {
                if (transferFileSpecs[i] != null)
                    info.transferFileSpecs[i] = (TransferFileSpec) transferFileSpecs[i].clone();
            }
        }
        if (dirSyncInfos == null)
            dirSyncInfos = new MyHashtable<>();
        dirSyncInfos.put(info.getKey(), info);
    }

    void btnUpdate_actionPerformed(ActionEvent e) {
    }

    void btnSyncNow_actionPerformed(ActionEvent e) {
    }

    void btnRefreshFileSpecs_actionPerformed(ActionEvent e) {
    }

    void btnOk_actionPerformed(ActionEvent e) {
    }

    void btnCancel_actionPerformed(ActionEvent e) {
    }
}
