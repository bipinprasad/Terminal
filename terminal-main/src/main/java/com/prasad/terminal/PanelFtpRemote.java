
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.*;
import java.io.*;

import pvTreeJ.*;
import com.prasad.util.GridBagConstraints2;
import com.ibm.network.ftp.FileInfo;
import com.ibm.network.ftp.event.*;

public class PanelFtpRemote extends Panel
    implements RemoteFileListListener,
    LocalFileListListener,
    StatusListener,
    SocksInterface {
    public static final String FAKENODE_STR = "Empty Directory";

    public static final int FILELISTRECEIVER_MODE_CHANGES_ONLY = 0;
    public static final int FILELISTRECEIVER_MODE_IGNORE = 1;
    public static final int FILELISTRECEIVER_MODE_REFRESH = 2;

    public static final int CONNECT_DLG_FLDNO_HOST = 0;
    public static final int CONNECT_DLG_FLDNO_USERID = 1;
    public static final int CONNECT_DLG_FLDNO_PASSWD = 2;
    public static final int CONNECT_DLG_FLDNO_PROXY_HOST = 3;
    public static final int CONNECT_DLG_FLDNO_PROXY_PORT = 4;
    public static final int CONNECT_DLG_FLDNO_ACCT = 5;


    int fileListReceiverModeRemote;
    int fileListReceiverModeLocal;
    FileLineInfo localFileLineInfo = new FileLineInfo();
    FileLineInfo remoteFileLineInfo = new FileLineInfo();

    private PalTerm palTerm;
    private boolean debug;

    private boolean showFileDetails;
    private boolean showDirs;
    private boolean showRemote;
    private boolean showLocal;

    Label lblDirList = new Label();
    pvTreeJ.PVTree dirTreeRemote = new pvTreeJ.PVTree();
    pvTreeJ.PVTree fileTreeRemote = new pvTreeJ.PVTree();
    pvTreeJ.PVTree dirTreeLocal = new pvTreeJ.PVTree();
    pvTreeJ.PVTree fileTreeLocal = new pvTreeJ.PVTree();
    Label lblFileList = new Label();
    Button btnSelect = new Button();
    Button cancel = new Button();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    private PVNode dirTreeRemoteRootNode;
    private PVNode fileTreeRemoteRootNode;
    private PVNode dirTreeLocalRootNode;
    private PVNode fileTreeLocalRootNode;
    private final Vector<PVNode> fileTreeRemoteSelectedNodes = new Vector<>();
    private final Vector<PVNode> fileTreeLocalSelectedNodes = new Vector<>();
    private final Vector<PVNode> dirTreeRemoteSelectedNodes = new Vector<>();
    private final Vector<PVNode> dirTreeLocalSelectedNodes = new Vector<>();
    private Color unselectedNodeBackground = Color.white;
    private Color unselectedNodeForeground = Color.black;
    private Color selectedNodeBackground = Color.black;
    private Color selectedNodeForeground = Color.white;

    DirSelectListener dirSelectListener;
    DirSelectListener fileSelectListener;
    TextArea txtAreaMessage = new TextArea();
    com.ibm.network.ftp.protocol.FTPProtocol ftpProtocol;
    private KeepAliveThread keepAliveThread;
    private final boolean isNAV;
    private final boolean isIE;


    public PanelFtpRemote(boolean isNAV, boolean isIE) {
        //boolean success = false;
        this.isNAV = isNAV;
        this.isIE = isIE;

        if (isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalFileAccess");// for property read of local "user.dir" in IBM's ftp classes
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalPropertyRead");// for property read of local "user.dir" in IBM's ftp classes
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalPropertyWrite");// for property read of local "user.dir" in IBM's ftp classes
                //success = true;
            } catch (Throwable e) {
                //success = false;
            }
        }

        if (isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                //if (debug)
                //    System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                //if (debug)
                //    System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.PROPERTY);
                //if (debug)
                //    System.out.println("Got PROPERTY permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                //success = false;
            }
        }
        ftpProtocol = new com.ibm.network.ftp.protocol.FTPProtocol();

        showDirs = true;
        showFileDetails = true;
        showRemote = true;
        showLocal = true;
        try {
            jbInit();
            dirTreeRemoteRootNode = dirTreeRemote.addRootNode("/", 0, 1);
            dirTreeRemote.addChildNode(dirTreeRemoteRootNode, FAKENODE_STR, 2, 2);
            dirTreeLocalRootNode = dirTreeLocal.addRootNode("/", 0, 1);
            dirTreeLocal.addChildNode(dirTreeLocalRootNode, FAKENODE_STR, 2, 2);
            ftpProtocol.addRemoteFileListListener(this);
            ftpProtocol.addLocalFileListListener(this);
            ftpProtocol.addStatusListener(this);
            this.add(popupMenuFtp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() {
        lblDirList.setText("Directories");
        dirTreeRemote.setNodeEditing(false);
        dirTreeRemote.setImages(false);
        dirTreeLocal.setNodeEditing(false);
        dirTreeLocal.setImages(false);
        lblFileList.setText("Files");
        fileTreeRemote.setNodeEditing(false);
        fileTreeRemote.setImages(false);
        fileTreeLocal.setNodeEditing(false);
        fileTreeLocal.setImages(false);
        btnSelect.setLabel("Select");
        btnSelect.addActionListener(new PanelFtpRemote_btnSelect_actionAdapter(this));
        cancel.setLabel("Cancel");
        cancel.addActionListener(new PanelFtpRemote_cancel_actionAdapter(this));
        this.setLayout(gridBagLayout1);
        this.add(lblDirList, new GridBagConstraints2(0, 0, 2, 1, 2.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(4, 12, 0, 0), 0, 0));
        this.add(lblFileList, new GridBagConstraints2(2, 0, 1, 1, 3.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(4, 16, 0, 12), 0, 0));
        this.add(dirTreeRemote, new GridBagConstraints2(0, 1, 2, 1, 2.0, 1.0
            , GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(fileTreeRemote, new GridBagConstraints2(2, 1, 6, 1, 6.0, 1.0
            , GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(dirTreeLocal, new GridBagConstraints2(0, 2, 2, 1, 2.0, 1.0
            , GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(fileTreeLocal, new GridBagConstraints2(2, 2, 6, 1, 6.0, 1.0
            , GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        this.add(txtAreaMessage, new GridBagConstraints2(0, 3, 8, 1, 8.0, 0.5
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, -96));
        this.add(btnSelect, new GridBagConstraints2(0, 4, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(9, 0, 3, 0), 0, 0));
        this.add(cancel, new GridBagConstraints2(1, 4, 1, 1, 0.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(9, 0, 3, 0), 0, 0));
        popupMenuFtp.add(checkboxMenuItemFollowInTerm);
        popupMenuFtp.add(menuItemRefresh);
        popupMenuFtp.add(menuItemChangeDir);
        popupMenuFtp.add(menuItemMakeDir);
        popupMenuFtp.add(menuItemDelete);
        popupMenuFtp.add(menuItemRename);
        popupMenuFtp.add(menuItemUploadText);
        popupMenuFtp.add(menuItemUploadBinary);
        popupMenuFtp.add(menuItemDownloadText);
        popupMenuFtp.add(menuItemDownloadBinary);
        popupMenuFtp.add(menuItemExpand);
        popupMenuFtp.add(menuItemCollapse);
        popupMenuFtp.add(menuItemView);
        popupMenuFtp.add(menuItemEdit);
        popupMenuFtp.add(menuItemRun);
        popupMenuFtp.add(menuItemProperties);
        popupMenuFtp.add(menuItemSort);
        //menuItemSort.add(menuItemSortNone);
        menuItemSort.add(menuItemSortByNameAsc);
        menuItemSort.add(menuItemSortByNameDes);
        menuItemSort.add(menuItemSortByExtAsc);
        menuItemSort.add(menuItemSortByExtDes);
        menuItemSort.add(menuItemSortBySizeAsc);
        menuItemSort.add(menuItemSortBySizeDes);
        menuItemSort.add(menuItemSortByDateAsc);
        menuItemSort.add(menuItemSortByDateDes);

        dirTreeRemote.addPVTreeActionListener(new PanelFtpRemote_dirTreeRemote_PVTreeActionAdapter(this));
        fileTreeRemote.addPVTreeActionListener(new PanelFtpRemote_fileTreeRemote_PVTreeActionAdapter(this));
        fileTreeLocal.addPVTreeActionListener(new PanelFtpRemote_fileTreeLocal_PVTreeActionAdapter(this));
        dirTreeLocal.addPVTreeActionListener(new PanelFtpRemote_dirTreeLocal_PVTreeActionAdapter(this));

        fileTreeLocal.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                fileTreeLocal_mousePressed(e);
            }
        });
        dirTreeLocal.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                dirTreeLocal_mousePressed(e);
            }
        });
        fileTreeRemote.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                fileTreeRemote_mousePressed(e);
            }
        });
        menuItemDownloadBinary.setLabel("Download Binary");
        menuItemDownloadBinary.addActionListener(this::menuItemDownloadBinary_actionPerformed);
        menuItemUploadBinary.setLabel("Upload Binary");
        menuItemUploadBinary.addActionListener(this::menuItemUploadBinary_actionPerformed);
        //menuItemSortNone.setLabel("None");
        //menuItemSortNone.addActionListener(new java.awt.event.ActionListener()
        //{
        //	public void actionPerformed(ActionEvent e)
        //	{
        //		int saveModeLocal	= fileListReceiverModeLocal;
        //		int saveModeRemote	= fileListReceiverModeRemote;
        //		setFileListReceiverMode(FILELISTRECEIVER_MODE_REFRESH, FILELISTRECEIVER_MODE_REFRESH);
        //		menuItemSortNone_actionPerformed(e);
        //		setFileListReceiverMode(saveModeLocal, saveModeRemote);
        //	}
        //});
        menuItemSortByDateAsc.setLabel("by Date Asc");
        menuItemSortByDateAsc.addActionListener(e -> {
            int saveModeLocal = fileListReceiverModeLocal;
            int saveModeRemote = fileListReceiverModeRemote;
            setFileListReceiverMode(FILELISTRECEIVER_MODE_REFRESH, FILELISTRECEIVER_MODE_REFRESH);
            menuItemSortByDateAsc_actionPerformed(e);
            setFileListReceiverMode(saveModeLocal, saveModeRemote);
        });
        menuItemSortByDateDes.setLabel("by Date Des");
        menuItemSortByDateDes.addActionListener(e -> {
            int saveModeLocal = fileListReceiverModeLocal;
            int saveModeRemote = fileListReceiverModeRemote;
            setFileListReceiverMode(FILELISTRECEIVER_MODE_REFRESH, FILELISTRECEIVER_MODE_REFRESH);
            menuItemSortByDateDes_actionPerformed(e);
            setFileListReceiverMode(saveModeLocal, saveModeRemote);
        });
        menuItemSortBySizeAsc.setLabel("by Size Asc");
        menuItemSortBySizeAsc.addActionListener(e -> {
            int saveModeLocal = fileListReceiverModeLocal;
            int saveModeRemote = fileListReceiverModeRemote;
            setFileListReceiverMode(FILELISTRECEIVER_MODE_REFRESH, FILELISTRECEIVER_MODE_REFRESH);
            menuItemSortBySizeAsc_actionPerformed(e);
            setFileListReceiverMode(saveModeLocal, saveModeRemote);
        });
        menuItemSortBySizeDes.setLabel("by Size Des");
        menuItemSortBySizeDes.addActionListener(e -> {
            int saveModeLocal = fileListReceiverModeLocal;
            int saveModeRemote = fileListReceiverModeRemote;
            setFileListReceiverMode(FILELISTRECEIVER_MODE_REFRESH, FILELISTRECEIVER_MODE_REFRESH);
            menuItemSortBySizeDes_actionPerformed(e);
            setFileListReceiverMode(saveModeLocal, saveModeRemote);
        });
        menuItemSortByNameAsc.setLabel("by Name Asc");
        menuItemSortByNameAsc.addActionListener(e -> {
            int saveModeLocal = fileListReceiverModeLocal;
            int saveModeRemote = fileListReceiverModeRemote;
            setFileListReceiverMode(FILELISTRECEIVER_MODE_REFRESH, FILELISTRECEIVER_MODE_REFRESH);
            menuItemSortByNameAsc_actionPerformed(e);
            setFileListReceiverMode(saveModeLocal, saveModeRemote);
        });
        menuItemSortByNameDes.setLabel("by Name Des");
        menuItemSortByNameDes.addActionListener(e -> {
            int saveModeLocal = fileListReceiverModeLocal;
            int saveModeRemote = fileListReceiverModeRemote;
            setFileListReceiverMode(FILELISTRECEIVER_MODE_REFRESH, FILELISTRECEIVER_MODE_REFRESH);
            menuItemSortByNameDes_actionPerformed(e);
            setFileListReceiverMode(saveModeLocal, saveModeRemote);
        });
        menuItemSortByExtAsc.setLabel("by Ext Asc");
        menuItemSortByExtAsc.addActionListener(e -> {
            int saveModeLocal = fileListReceiverModeLocal;
            int saveModeRemote = fileListReceiverModeRemote;
            setFileListReceiverMode(FILELISTRECEIVER_MODE_REFRESH, FILELISTRECEIVER_MODE_REFRESH);
            menuItemSortByExtAsc_actionPerformed(e);
            setFileListReceiverMode(saveModeLocal, saveModeRemote);
        });
        menuItemSortByExtDes.setLabel("by Ext Des");
        menuItemSortByExtDes.addActionListener(e -> {
            int saveModeLocal = fileListReceiverModeLocal;
            int saveModeRemote = fileListReceiverModeRemote;
            setFileListReceiverMode(FILELISTRECEIVER_MODE_REFRESH, FILELISTRECEIVER_MODE_REFRESH);
            menuItemSortByExtDes_actionPerformed(e);
            setFileListReceiverMode(saveModeLocal, saveModeRemote);
        });
        menuItemSort.setLabel("Sort");
        menuItemProperties.setLabel("Properties");
        menuItemProperties.addActionListener(this::menuItemProperties_actionPerformed);
        menuItemRun.setLabel("Run");
        menuItemRun.addActionListener(this::menuItemRun_actionPerformed);
        menuItemEdit.setLabel("Edit");
        menuItemEdit.addActionListener(this::menuItemEdit_actionPerformed);
        menuItemView.setLabel("View");
        menuItemView.addActionListener(this::menuItemView_actionPerformed);
        menuItemCollapse.setLabel("Collapse");
        menuItemCollapse.addActionListener(this::menuItemCollapse_actionPerformed);
        menuItemExpand.setLabel("Expand");
        menuItemExpand.addActionListener(this::menuItemExpand_actionPerformed);
        menuItemDownloadText.setLabel("Download Text");
        menuItemDownloadText.addActionListener(this::menuItemDownloadText_actionPerformed);
        menuItemUploadText.setLabel("Upload Text");
        menuItemUploadText.addActionListener(this::menuItemUploadText_actionPerformed);
        menuItemRename.setLabel("Rename");
        menuItemRename.addActionListener(this::menuItemRename_actionPerformed);
        menuItemDelete.setLabel("Delete");
        menuItemDelete.addActionListener(this::menuItemDelete_actionPerformed);
        menuItemMakeDir.setLabel("MakeDir");
        menuItemMakeDir.addActionListener(this::menuItemMakeDir_actionPerformed);
        menuItemRefresh.setLabel("Refresh");
        menuItemRefresh.addActionListener(this::menuItemRefresh_actionPerformed);
        menuItemChangeDir.setLabel("ChangeDir");
        menuItemChangeDir.addActionListener(this::menuItemChangeDir_actionPerformed);
        dirTreeRemote.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dirTreeRemote_mousePressed(e);
            }
        });
        Color c = new Color(255, 222, 189);
        checkboxMenuItemFollowInTerm.setLabel("Follow in Terminal");
        checkboxMenuItemFollowInTerm.addItemListener(this::checkboxMenuItemFollowInTerm_itemStateChanged);
        fileTreeLocal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                fileTreeLocal_keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                fileTreeLocal_keyReleased(e);
            }
        });
        fileTreeRemote.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                fileTreeRemote_keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                fileTreeRemote_keyReleased(e);
            }
        });
        dirTreeLocal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                dirTreeLocal_keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                dirTreeLocal_keyReleased(e);
            }
        });
        dirTreeRemote.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                dirTreeRemote_keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                dirTreeRemote_keyReleased(e);
            }
        });
        setBackground(c);
        this.setSize(new Dimension(461, 356));

    }

    @Override
    public void setBackground(Color c) {
        super.setBackground(c);
        dirTreeRemote.setBackground(c);
        fileTreeRemote.setBackground(c);
        dirTreeLocal.setBackground(c);
        fileTreeLocal.setBackground(c);
        unselectedNodeBackground = c;
        selectedNodeForeground = c;
    }

    @Override
    public void setForeground(Color c) {
        super.setForeground(c);
        dirTreeRemote.setForeground(c);
        fileTreeRemote.setForeground(c);
        dirTreeLocal.setForeground(c);
        fileTreeLocal.setForeground(c);

        dirTreeRemote.setLineColor(c);
        fileTreeRemote.setLineColor(c);
        dirTreeLocal.setLineColor(c);
        fileTreeLocal.setLineColor(c);

        unselectedNodeForeground = c;
        selectedNodeBackground = c;
    }

    void dirTreeRemote_actionPerformed(PVTreeActionEvent e) {
        int id = e.getID();
        PVNode n = e.getNode();
        PVNode n2;
        Vector<PVNode> selectedNodes = dirTreeRemoteSelectedNodes;

        switch (id) {
            case PVTreeActionEvent.NODE_EXPANDED: // Node is about to be expanded
                if (n.HasChildren()) {
                    n2 = n.getChild();
                    if (n2.getText().equals(FAKENODE_STR))
                        changeDir(n, true);
                }
                break;

            case PVTreeActionEvent.NODE_DOUBLE_CLICKED:
                changeDir(n, true);
                break;

            case PVTreeActionEvent.NODE_CLICKED:
                // select/deselect
                if (dirTreeRemoteCtrlPressed)
                    reverseNodeSelection(selectedNodes, n);
                else if (dirTreeRemoteShiftPressed) {
                    if (!selectedNodes.contains(n))
                        reverseNodeSelection(selectedNodes, n);
                    while ((n = n.getPrevVisible()) != null) {
                        if (selectedNodes.contains(n))
                            break;
                        else
                            reverseNodeSelection(selectedNodes, n);
                    }
                } else {
                    // if it is already selected or is the current - then do nothing
                    // otherwise, unselect all and select this one
                    if (!selectedNodes.contains(n)) {
                        int selCnt = selectedNodes.size();
                        for (int i = 0; i < selCnt; i++) {
                            PVNode pvNode1 = selectedNodes.elementAt(i);
                            pvNode1.setBackground(unselectedNodeBackground);
                            pvNode1.setForeground(unselectedNodeForeground);
                        }
                        selectedNodes.removeAllElements();
                        // add the current node
                        reverseNodeSelection(selectedNodes, n);

                        // change to current directory
                        changeDir(n, true);
                    }
                }
                break;

            default:
        }
    }

    private boolean cleanedup;

    public synchronized void cleanup() {
        //System.out.println("Cleanup called");
        if (!cleanedup) {
            cleanedup = true;
            try {
                if (ftpProtocol != null)
                    ftpProtocol.disconnect();
                //System.out.println("Cleanup: disconnected called");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (ftpProtocol != null)
                    ftpProtocol.removeRemoteFileListListener(this);
                //System.out.println("Cleanup: removed remote filelistener");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (ftpProtocol != null)
                    ftpProtocol.removeLocalFileListListener(this);
                //System.out.println("Cleanup: removed local filelistener");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (ftpProtocol != null)
                    ftpProtocol.removeStatusListener(this);
                //System.out.println("Cleanup: removed statuslistener");
            } catch (Exception e) {
                e.printStackTrace();
            }

            ftpProtocol = null;
            try {
                if (keepAliveThread != null)
                    keepAliveThread.stop();
                keepAliveThread = null;
                //System.out.println("Cleanup: removed statuslistener");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void finalize() {
        // only one instance of FTPProtocol can exist in the whole application/applet
        // so remove /destroy it
        cleanup();
    }

    public void setFileListFont(Font f) {
        fileTreeRemote.setFont(f);
        fileTreeLocal.setFont(f);
    }

    void btnSelect_actionPerformed(ActionEvent e) {
        PVNode pvNode;
        if (dirSelectListener != null) {
            if ((dirTreeRemote.isVisible() && (pvNode = dirTreeRemote.getSelectedNode()) != null)
                || (fileTreeRemote.isVisible() && (pvNode = fileTreeRemote.getSelectedNode()) != null)
                || (dirTreeLocal.isVisible() && (pvNode = dirTreeLocal.getSelectedNode()) != null)
                || (fileTreeLocal.isVisible() && (pvNode = fileTreeLocal.getSelectedNode()) != null)) {
                dirSelectListener.dirSelected(getParentFrame(), getNodePath(pvNode), pvNode.HasChildren());
            }
        }
        // fileSelectListener is interested in file
        if (fileSelectListener != null) {
            if ((fileTreeRemote.isVisible() && (pvNode = fileTreeRemote.getSelectedNode()) != null)
                || (dirTreeRemote.isVisible() && (pvNode = dirTreeRemote.getSelectedNode()) != null)
                || (fileTreeLocal.isVisible() && (pvNode = fileTreeLocal.getSelectedNode()) != null)
                || (dirTreeLocal.isVisible() && (pvNode = dirTreeLocal.getSelectedNode()) != null)) {
                fileSelectListener.dirSelected(getParentFrame(), getNodePath(pvNode), pvNode.HasChildren());
            }
        }

    }

    void cancel_actionPerformed(ActionEvent e) {
        Frame frame = getParentFrame();
        if (frame != null)
            frame.setVisible(false);
        if (dirSelectListener != null)
            dirSelectListener.dirSelected(getParentFrame(), null, false);
        if (fileSelectListener != null)
            fileSelectListener.dirSelected(getParentFrame(), null, false);
    }

    /**
     * Returns the node directory, terminated by a "/"
     */
    private String getNodeDir(PVNode pvNode) {
        if (pvNode == null)
            return "";

        PVNode pvParent = pvNode.getParent();
        StringBuilder sb = new StringBuilder();

        for (; pvParent != null && pvParent.getLevel() >= 0; pvParent = pvParent.getParent()) {
            if (pvParent == dirTreeRemoteRootNode
                || pvParent == dirTreeLocalRootNode) {
                sb.insert(0, '/');
                break;
            } else {
                sb.insert(0, '/');
                sb.insert(0, pvParent.getText());
            }
        }
        return sb.toString();
    }

    private String getNodePath(PVNode pvNode) {
        String nodeText;
        String dir;

        if (pvNode != null
            && (nodeText = getNodeFile(pvNode)) != null
            && (dir = getNodeDir(pvNode)) != null)
            return dir + nodeText;
        else
            return null;
    }

    private String getNodeFile(PVNode pvNode) {
        if (pvNode == null)
            return null;

        PVNode pvParent = pvNode.getParent();
        String nodeText = pvNode.getText();

        if (pvParent == fileTreeRemoteRootNode) {
            if (nodeText.length() > remoteFileLineInfo.FILE_LINE_FILENAME_LEN)
                nodeText = nodeText.substring(0, remoteFileLineInfo.FILE_LINE_FILENAME_LEN).trim();
        } else if (pvParent == fileTreeLocalRootNode) {
            if (nodeText.length() > localFileLineInfo.FILE_LINE_FILENAME_LEN)
                nodeText = nodeText.substring(0, localFileLineInfo.FILE_LINE_FILENAME_LEN).trim();
        }
        if (pvParent == dirTreeRemoteRootNode
            || pvParent == dirTreeLocalRootNode) {
            if (nodeText.endsWith(":"))
                nodeText = nodeText + '/';
        }
        return nodeText;
    }

    private void changeDir(PVNode pvNode, boolean remote) {
        if (pvNode == null)
            return;

        ftpcmd_changeDir(getNodePath(pvNode), remote);
    }

    public Applet getApplet() {
        return palTerm;
    }

    public void setApplet(PalTerm a) {
        palTerm = a;
        if (palTerm != null) {
            Image[] imgs = new Image[3];
            imgs[0] = palTerm.loadImage("termimgs", "closedir.gif", this, true);
            imgs[1] = palTerm.loadImage("termimgs", "opendir.gif", this, true);
            imgs[2] = palTerm.loadImage("termimgs", "file.gif", this, true);

            for (Image img : imgs) {
                dirTreeRemote.addImage(img);
                fileTreeRemote.addImage(img);
                dirTreeLocal.addImage(img);
                fileTreeLocal.addImage(img);
            }

            dirTreeRemote.setImages(true);
            fileTreeRemote.setImages(true);
            dirTreeLocal.setImages(true);
            fileTreeLocal.setImages(true);
        }
    }

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean b) {
        debug = b;
    }

    /**
     * Show status messages in a dialog box
     *
     * @param message the status message to show
     * @param error   set this flag to true if the message is an error
     **/
    void showMessage(String message, boolean error) {
        createMessageDialog();
        if (message == null)
            message = "";
        statusMessage.setText(message);
        if (error)
            messageDialog.setTitle("Error");
        else
            messageDialog.setTitle("Status");
        messageDialog.pack();

        // Center dialog over applet
        messageDialog.setLocation(getLocation().x +
                getSize().width / 2,
            getLocation().y +
                getSize().height / 2);
        messageDialog.setVisible(true);
    }

    private Dialog messageDialog;
    private Label statusMessage;

    private void createMessageDialog() {
        if (messageDialog == null) {
            // Create message dialog box
            messageDialog = new Dialog(getParentFrame(), true);
            messageDialog.setLayout(new BorderLayout());
            statusMessage = new Label("", Label.CENTER);
            statusMessage.setFont(new Font("Serif", Font.PLAIN, 14));
            messageDialog.add(statusMessage, BorderLayout.NORTH);
            Panel panel = new Panel();
            Button button = new Button("Continue");
            button.addActionListener(e -> messageDialog.setVisible(false));
            panel.add(button);
            messageDialog.add(panel, BorderLayout.SOUTH);
        }
    }

    private Frame hiddenParentFrame;

    /**
     * Called whenever the part throws an exception.
     *
     * @param exception java.lang.Throwable
     */
    private void handleException(Throwable exception) {
        /* Uncomment the following lines to print uncaught exceptions to stdout */
        // System.out.println("--------- UNCAUGHT EXCEPTION ---------");
        // exception.printStackTrace(System.out);
        //Show the error in InfoDialog

        //Initialize the user entry
        //set the parent
        exception.printStackTrace();
        txtAreaMessage.append(exception.toString());
        txtAreaMessage.append("\n");
    }

    /**
     * Calculates the center location of the dialog for showing
     * in the parent's region and returns the center location.
     *
     * @param        dial java.awt.Dialog
     * @return java.awt.Point
     */

    private java.awt.Point findCenterPoint(java.awt.Dialog dial) {
        //Get parent's bounds wrt the screen
        java.awt.Point parPoint = this.getLocationOnScreen();
        java.awt.Dimension parSize = this.getSize();

        //Calculate the center point
        int cx = parPoint.x;
        int cy = parPoint.y;

        cx = cx + (parSize.width - dial.getSize().width) / 2;
        cy = cy + (parSize.height - dial.getSize().height) / 2;

        //return the center point
        return (new java.awt.Point(cx, cy));
    }

    /**
     * Return the frame of this panel.
     */
    public Frame getParentFrame() {
        Container containerFrame = getParent();
        while (containerFrame != null) {
            if (containerFrame instanceof Frame)
                return (Frame) containerFrame;
            containerFrame = containerFrame.getParent();
        }
        // no frame in the hierarchy
        if (hiddenParentFrame == null)
            hiddenParentFrame = new Frame();
        hiddenParentFrame.setVisible(false);
        return hiddenParentFrame;
    }

    /**
     * Listener to call when the directory is selected
     */
    public void setDirSelectListener(DirSelectListener listener) {
        dirSelectListener = listener;
    }

    /**
     * Listener to call when the file is selected
     */
    public void setFileSelectListener(DirSelectListener listener) {
        fileSelectListener = listener;
    }

    public boolean getShowRemote() {
        return showRemote;
    }

    public void setShowRemote(boolean flag) {
        showRemote = flag;
        setVisibility();
    }

    public boolean getShowLocal() {
        return showLocal;
    }

    public void setShowLocal(boolean flag) {
        showLocal = flag;
        setVisibility();
    }

    public boolean getShowFileDetails() {
        return showFileDetails;
    }

    public void setShowFileDetails(boolean flag) {
        showFileDetails = flag;
        setVisibility();
    }

    public boolean getShowDirs() {
        return showDirs;
    }

    public void setShowDirs(boolean flag) {
        showDirs = flag;
        setVisibility();
    }

    private void setVisibility() {
        // make appropriate components visible
        lblDirList.setVisible(showDirs && (showRemote || showLocal));
        lblFileList.setVisible(showFileDetails && (showRemote || showLocal));
        dirTreeRemote.setVisible(showDirs && showRemote);
        fileTreeRemote.setVisible(showFileDetails && showRemote);

        dirTreeLocal.setVisible(showDirs && showLocal);
        fileTreeLocal.setVisible(showFileDetails && showLocal);

        // readjust size of trees to 10 so they expand about about the same
        dirTreeRemote.setSize(10, 10);
        fileTreeRemote.setSize(10, 10);
        dirTreeLocal.setSize(10, 10);
        fileTreeLocal.setSize(10, 10);

        invalidate();
        doLayout();
    }

    void fileTreeRemote_actionPerformed(PVTreeActionEvent e) {
        int id = e.getID();
        PVNode n = e.getNode();

        PVNode n2;
        String dir;
        Vector<PVNode> selectedNodes = fileTreeRemoteSelectedNodes;
        //PVNode  parentPVNode = n.getParent();

        switch (id) {
            case PVTreeActionEvent.NODE_EXPANDED: // Node is about to be expanded
                if (n.HasChildren()) {
                    n2 = n.getChild();
                    if (n2.getText().equals(FAKENODE_STR)) {
                        //dir = fileTreeRemoteRootNode.getText() + "/" + n.getText();
                        //idx = dir.indexOf("    ");
                        //if ( idx > 0)
                        //	dir = dir.substring(0,idx);
                        dir = getNodePath(n);
                        ftpcmd_changeDir(dir, true);
                    }
                }
                break;

            case PVTreeActionEvent.NODE_DOUBLE_CLICKED:
                dir = getNodePath(n);
                //if (n == fileTreeRemoteRootNode)
                //	dir = fileTreeRemoteRootNode.getText();
                //else
                //	dir = fileTreeRemoteRootNode.getText() + "/" + n.getText();
                //idx = dir.indexOf("    ");
                //if ( idx > 0)
                //	dir = dir.substring(0,idx);

                ftpcmd_changeDir(dir, true);

                // sync dirTree
                ensureVisibleDirTreeNode(dir, true);
                break;

            case PVTreeActionEvent.NODE_CLICKED:
                // select/deselect
                if (fileTreeRemoteCtrlPressed)
                    reverseNodeSelection(fileTreeRemoteSelectedNodes, n);
                else if (fileTreeRemoteShiftPressed) {
                    if (!selectedNodes.contains(n))
                        reverseNodeSelection(selectedNodes, n);
                    while ((n = n.getPrevVisible()) != null) {
                        if (selectedNodes.contains(n))
                            break;
                        else
                            reverseNodeSelection(selectedNodes, n);
                    }
                } else {
                    // if it is already selected or is the current - then do nothing
                    // otherwise, unselect all and select this one
                    if (!selectedNodes.contains(n)) {
                        int selCnt = selectedNodes.size();
                        for (int i = 0; i < selCnt; i++) {
                            PVNode pvNode1 = selectedNodes.elementAt(i);
                            pvNode1.setBackground(unselectedNodeBackground);
                            pvNode1.setForeground(unselectedNodeForeground);
                        }
                        selectedNodes.removeAllElements();
                        // add the current node
                        reverseNodeSelection(selectedNodes, n);
                    }
                }
                break;

            default:
        }

    }
  /*
  private Frame getContainerFrame()
  {
	Container containerFrame = this.getParent();
	while (containerFrame != null)
	{
		if (containerFrame instanceof Frame)
			break;
		containerFrame = containerFrame.getParent();
	}
	return (Frame)containerFrame;
  }
  */

    /**
     * Check for the existence of the supplied directory node
     * Add if necessary.
     * Return the node.
     */
    private PVNode addDirTreeNode(String dir, boolean remote) {
        if (dir == null)
            return null;

        PVTree pvDirTree;
        PVNode pvDirTreeRootNode;

        if (remote) {
            pvDirTree = dirTreeRemote;
            pvDirTreeRootNode = dirTreeRemoteRootNode;
        } else {
            pvDirTree = dirTreeLocal;
            pvDirTreeRootNode = dirTreeLocalRootNode;
        }

        StringTokenizer st = new StringTokenizer(dir, "/");
        PVNode pvNode = pvDirTreeRootNode;
        while (st.hasMoreTokens()) {
            String fileFragment = st.nextToken();
            if (fileFragment.equals("..")) {
                pvNode = pvNode.getParent();
                continue;
            } else if (fileFragment.equals(".")) {
                continue;
            }

            PVNode childNode = pvNode.searchChildren(fileFragment, null, null, true);
            //for ( ; childNode != null ; childNode = childNode.getNextSibling() )
            //{
            //	String nodeString = childNode.getText();
            //	if (nodeString != null
            //	&&	nodeString.equals(fileFragment))
            //		break;
            //}
            if (childNode == null) {
                // if (firstChild) is fake str, then remove that child
                PVNode fakeNode = pvNode.searchChildren(FAKENODE_STR, null, null, true);
                if (fakeNode != null)
                    pvDirTree.removeNode(fakeNode);
                childNode = pvDirTree.addChildNode(pvNode, fileFragment, 0, 1);
                if (!childNode.getExpanded())
                    childNode.expand(true);
            }
            pvNode = childNode;
        }
        if (!pvDirTreeRootNode.getExpanded())
            pvDirTreeRootNode.expand(true);
        return pvNode;
    }

    /**
     * Ensure that the tree node is visible
     */
    private void ensureVisibleDirTreeNode(String dir, boolean remote) {
        if (dir == null)
            return;

        PVNode pvRootNode;
        PVTree pvTree;
        if (remote) {
            pvRootNode = dirTreeRemoteRootNode;
            pvTree = dirTreeRemote;
        } else {
            pvRootNode = dirTreeLocalRootNode;
            pvTree = dirTreeLocal;
        }

        StringTokenizer st = new StringTokenizer(dir, "/");
        PVNode pvNode = pvRootNode;
        while (st.hasMoreTokens()) {
            String fileFragment = st.nextToken();
            if (fileFragment.equals("..")) {
                pvNode = pvNode.getParent();
                continue;
            } else if (fileFragment.equals(".")) {
                continue;
            }

            PVNode childNode = pvNode.searchChildren(fileFragment, null, null, true);
            if (childNode == null)
                return;

            pvNode = childNode;

            if (!pvNode.getExpanded())
                pvTree.setExpanded(pvNode, true);

        }
        if (!pvNode.getExpanded())
            pvTree.setExpanded(pvNode, true);
        pvTree.ensureVisible(pvNode);
        pvTree.setSelectedNode(pvNode);
        //getParentFrame().validate();
        //getParentFrame().doLayout();
    }

    public void fileList(boolean remote) {
        if (remote) {
            int saveMode = fileListReceiverModeRemote;
            fileListReceiverModeRemote = ((saveMode == FILELISTRECEIVER_MODE_IGNORE) ? FILELISTRECEIVER_MODE_CHANGES_ONLY : saveMode);
            ftpProtocol.fileList(true);
            fileListReceiverModeRemote = saveMode;
        } else {
            int saveMode = fileListReceiverModeLocal;
            fileListReceiverModeLocal = ((saveMode == FILELISTRECEIVER_MODE_IGNORE) ? FILELISTRECEIVER_MODE_CHANGES_ONLY : saveMode);
            ftpProtocol.fileList(false);
            fileListReceiverModeLocal = saveMode;
        }
    }

    public void remoteFileListReceived(RemoteFileListEvent event) {
        //TODO: implement this com.ibm.network.ftp.event.LocalFileListListener method;
        switch (fileListReceiverModeRemote) {
            case FILELISTRECEIVER_MODE_IGNORE:
                return;

            case FILELISTRECEIVER_MODE_CHANGES_ONLY:
            case FILELISTRECEIVER_MODE_REFRESH:
                String newDir = event.getRemoteDir();
                if (checkboxMenuItemFollowInTerm.getState()
                    && terminalSender != null
                    && newDir != null
                    && ftp_remoteDir != null
                    && !ftp_remoteDir.equals(newDir))
                    terminalSender.send("cd " + newDir + "\n");
                ftp_remoteDir = newDir;
                Vector<FileInfo> v = event.getRemoteFileInfo();
                fileListReceived(ftp_remoteDir, v, true, fileListReceiverModeRemote);
                break;
        }
    }

    public void localFileListReceived(LocalFileListEvent event) {
        //TODO: implement this com.ibm.network.ftp.event.LocalFileListListener method;
        switch (fileListReceiverModeLocal) {
            case FILELISTRECEIVER_MODE_IGNORE:
                return;

            case FILELISTRECEIVER_MODE_CHANGES_ONLY:
            case FILELISTRECEIVER_MODE_REFRESH:
                ftp_localDir = event.getLocalDir();
                Vector<FileInfo> v = event.getLocalFileInfo();
                if (File.separatorChar == '\\')
                    ftp_localDir = ftp_localDir.replace(File.separatorChar, '/');
                fileListReceived(ftp_localDir, v, false, fileListReceiverModeLocal);
                break;
        }
    }

    private void fileListReceived(String dir, Vector v, boolean remote, int fileListReceiverMode) {
        Hashtable<String, PVNode> currentFileNodes = null;
        Hashtable<String, FileInfo> newFileInfos = null;
        FileLineInfo fileLineInfo = (remote ? remoteFileLineInfo : localFileLineInfo);
        //TODO: implement this com.ibm.network.ftp.event.RemoteFileListListener method;

        Vector<PVNode> selectedNodes = (remote ? fileTreeRemoteSelectedNodes : fileTreeLocalSelectedNodes);

        PVNode pvNode = addDirTreeNode(dir, remote);
        if (pvNode == null)
            return;

        PVNode pvDirTreeRootNode;
        PVTree pvDirTree;
        PVNode pvFileTreeRootNode;
        PVTree pvFileTree;
        if (remote) {
            pvDirTreeRootNode = dirTreeRemoteRootNode;
            pvDirTree = dirTreeRemote;
            pvFileTreeRootNode = fileTreeRemoteRootNode;
            pvFileTree = fileTreeRemote;
        } else {
            pvDirTreeRootNode = dirTreeLocalRootNode;
            pvDirTree = dirTreeLocal;
            pvFileTreeRootNode = fileTreeLocalRootNode;
            pvFileTree = fileTreeLocal;
        }

        if (fileListReceiverMode == FILELISTRECEIVER_MODE_REFRESH) {
            int selCnt = (selectedNodes == null) ? 0 : selectedNodes.size();
            for (int i = 0; i < selCnt; i++) {
                PVNode pvNode1 = selectedNodes.elementAt(i);
                pvNode1.setBackground(unselectedNodeBackground);
                pvNode1.setForeground(unselectedNodeForeground);
            }
            if (selectedNodes != null)
                selectedNodes.removeAllElements();

            if (showFileDetails) {
                pvFileTree.removeAll();
                pvFileTreeRootNode = pvFileTree.addRootNode(dir, 0, 1);
                if (remote)
                    fileTreeRemoteRootNode = pvFileTreeRootNode;
                else
                    fileTreeLocalRootNode = pvFileTreeRootNode;
                pvFileTree.setExpanded(pvFileTreeRootNode, true);
            }

            // remove the existing child nodes under this node
            // in the directory tree

            PVNode dirTreeChildNode = pvNode.getChild();
            Vector<PVNode> nodeList = new Vector<>(20);
            while (dirTreeChildNode != null) {
                nodeList.addElement(dirTreeChildNode);
                dirTreeChildNode = dirTreeChildNode.getNextSibling();
            }
            int iCnt = nodeList.size();
            for (int i = 0; i < iCnt; i++) {
                PVNode tmpNode = nodeList.elementAt(i);
                pvDirTree.removeNode(tmpNode);
            }

        } else if (fileListReceiverMode == FILELISTRECEIVER_MODE_CHANGES_ONLY) {
            // create a list of current file nodes on the file tree
            currentFileNodes = new Hashtable<>();
            PVNode nodeTmp;
            if (pvFileTreeRootNode == null) {
                pvFileTree.removeAll();
                pvFileTreeRootNode = pvFileTree.addRootNode(dir, 0, 1);
                if (remote)
                    fileTreeRemoteRootNode = pvFileTreeRootNode;
                else
                    fileTreeLocalRootNode = pvFileTreeRootNode;
                pvFileTree.setExpanded(pvFileTreeRootNode, true);
            } else
                pvFileTreeRootNode.setText(dir);

            for (nodeTmp = pvFileTreeRootNode.getChild();
                 nodeTmp != null;
                 nodeTmp = nodeTmp.getNextSibling()) {
                FileInfo f = (FileInfo) nodeTmp.getData();
                if (f == null)
                    continue;
                currentFileNodes.put(f.getName(), nodeTmp);
            }
            // remove only deleted items from dir/file trees and selection vector
            // if the directory does not match the new directory then all the nodes
        }

        int iMax;
        if (v != null
            && (iMax = v.size()) > 0) {
            // now add the new nodes
            FileInfo[] fileInfos = new FileInfo[iMax];
            v.copyInto(fileInfos);
            // make sure FILE_LINE_FILENAME_LEN is 4 chars bigger than biggest file name
            fileLineInfo.FILE_LINE_FILENAME_LEN = 4;
            newFileInfos = new Hashtable<>(iMax);
            for (int i = 0; i < iMax; i++) {
                int nameLen = fileInfos[i].getName().length();
                if (fileLineInfo.FILE_LINE_FILENAME_LEN < nameLen + 4)
                    fileLineInfo.FILE_LINE_FILENAME_LEN = nameLen + 4;
                newFileInfos.put(fileInfos[i].getName(), fileInfos[i]);
            }
            // now sort the files (if specified)
            //if (sortType != SORTTYPE_NONE)
            fileInfos = sortFileInfos(remote, fileInfos);

            StringBuffer sb = new StringBuffer(100);

            for (int i = 0; i < iMax; i++) {
                FileInfo f = fileInfos[i];

                PVNode childNode;
                // file side

                String newFileName = f.getName();
                PVNode oldChildNode;
                if (f.isFile()) {
                    if (currentFileNodes != null
                        && (oldChildNode = currentFileNodes.get(newFileName)) != null) {
                        oldChildNode.setText(f.toFormattedString(sb, fileLineInfo.FILE_LINE_FILENAME_LEN, fileLineInfo.FILE_LINE_FILESIZE_LEN, fileLineInfo.FILE_LINE_FILEDATE_LEN, fileLineInfo.FILE_LINE_FILETIME_LEN));
                        oldChildNode.setData(f);
                        if (oldChildNode.HasChildren())
                            pvFileTree.removeNode(oldChildNode.getChild());
                    } else {
                        childNode = pvFileTree.addChildNode(pvFileTreeRootNode,
                            f.toFormattedString(sb, fileLineInfo.FILE_LINE_FILENAME_LEN, fileLineInfo.FILE_LINE_FILESIZE_LEN, fileLineInfo.FILE_LINE_FILEDATE_LEN, fileLineInfo.FILE_LINE_FILETIME_LEN),
                            2, 2);
                        childNode.setData(f);
                    }
                } else {
                    if (currentFileNodes != null
                        && (oldChildNode = currentFileNodes.get(newFileName)) != null) {
                        oldChildNode.setText(f.toFormattedString(sb, fileLineInfo.FILE_LINE_FILENAME_LEN, fileLineInfo.FILE_LINE_FILESIZE_LEN, fileLineInfo.FILE_LINE_FILEDATE_LEN, fileLineInfo.FILE_LINE_FILETIME_LEN));
                        oldChildNode.setData(f);
                        if (!oldChildNode.HasChildren()) {
                            pvFileTree.addChildNode(oldChildNode, FAKENODE_STR, 2, 2);
                            oldChildNode.setImages(0, 0);
                        }
                        if (oldChildNode.getExpanded())
                            oldChildNode.collapse(false);
                    } else {
                        childNode = pvFileTree.addChildNode(pvFileTreeRootNode,
                            f.toFormattedString(sb, fileLineInfo.FILE_LINE_FILENAME_LEN, fileLineInfo.FILE_LINE_FILESIZE_LEN, fileLineInfo.FILE_LINE_FILEDATE_LEN, fileLineInfo.FILE_LINE_FILETIME_LEN),
                            0, 0);
                        childNode.setData(f);
                        pvFileTree.addChildNode(childNode, FAKENODE_STR, 2, 2);
                    }
                    // directory side
                    PVNode pvNode1 = addDirTreeNode(dir + "/" + f.getName(), remote);
                    pvNode1.setData(f);
                    //if (!pvNode1.HasChildren())
                    //	pvDirTree.addChildNode(pvNode1, FAKENODE_STR, 2, 2);

                    //childNode = pvDirTree.addChildNode(	pvNode,
                    //									f.getName(),
                    //									0, 1);
                    //childNode.setData(f);
                    //pvDirTree.addChildNode(childNode, FAKENODE_STR, 2, 2);
                }
            }
            // delete files that no longer exist
            if (currentFileNodes != null
                && currentFileNodes.size() > 0) {
                PVNode nodeTmp;
                Enumeration<PVNode> enumeration = currentFileNodes.elements();
                if ( enumeration !=null)
                {
                    while (enumeration.hasMoreElements() && (nodeTmp = enumeration.nextElement()) != null) {
                        FileInfo f = (FileInfo) nodeTmp.getData();
                        if (f != null
                            && newFileInfos.get(f.getName()) == null) {
                            try {
                                pvFileTree.removeNode(nodeTmp);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                if (selectedNodes != null)
                                    selectedNodes.removeElement(nodeTmp);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } else {
            pvFileTree.removeAll();
            pvFileTreeRootNode = pvFileTree.addRootNode(dir, 0, 1);
            if (remote)
                fileTreeRemoteRootNode = pvFileTreeRootNode;
            else
                fileTreeLocalRootNode = pvFileTreeRootNode;
            pvFileTree.setExpanded(pvFileTreeRootNode, true);
            //try{
            //	pvFileTreeRootNode.removeAll();
            //	pvFileTreeRootNode.setText(FAKENODE_STR);
            //} catch (Exception e) {}
        }

        // if the expansion was done on a PopupNode in a FileTree. make sure that is
        // is collapsed (if it is still on the tree. e.g. ".." or "." directories.

        //try {
        //	if (fileListReceiverMode == FILELISTRECEIVER_MODE_CHANGES_ONLY
        //	&&	!changeDirPVNode != null
        //	&&	(remote && changeDirPVNode. != null
        //	&&	popupMenuPVNode.HasChildren())
        //		popupMenuPVNode.setExpanded(false);
        //} catch (Exception e){}
    }

    //public static final int SORTTYPE_NONE			= 0;
    public static final int SORTTYPE_NAME = 0;
    public static final int SORTTYPE_EXT = 1;
    public static final int SORTTYPE_SIZE = 2;
    public static final int SORTTYPE_DATE = 3;
    private int sortTypeLocal = SORTTYPE_NAME;
    private int sortTypeRemote = SORTTYPE_NAME;
    private boolean sortAscendingLocal = true;
    private boolean sortAscendingRemote = true;

  /*
  private String getFileInfoKey(FileInfo fileInfo, int sortType)
  {
	String keyStr = null;
	if (fileInfo == null)
		return null;

	switch (sortType)
	{
		case SORTTYPE_SIZE:
			try {
				keyStr = Long.toHexString( Long.parseLong(fileInfo.getSize().trim()) );
				System.out.println("KeyStr (size) [" + keyStr + "]");
			} catch (Exception e) { keyStr = null;}
			break;
		case SORTTYPE_DATE:
			keyStr = fileInfo.getDate();
			break;
		case SORTTYPE_TIME:
			keyStr = fileInfo.getTime();
			break;
		case SORTTYPE_NAME:
		default:
			keyStr = fileInfo.getName();
			break;
	}
	return keyStr;
  }
  */

    /**
     * Return 0 if equal, -1 if f1 is less than f2 and, +1 if f2 is greater
     */
    private int compareFileInfoKey(boolean remote, FileInfo f1, FileInfo f2) {
        int retVal = 0;
        int sortType = (remote ? sortTypeRemote : sortTypeLocal);
        boolean sortAscending = (remote ? sortAscendingRemote : sortAscendingLocal);

        if (f1 == null) {
            if (f2 != null)
                retVal = -1;
        } else if (f2 == null)
            retVal = 1;
        else {
            String str1;
            String str2;
            long num1;
            long num2;

            switch (sortType) {
                case SORTTYPE_SIZE:
                    try {
                        num1 = Long.parseLong(f1.getSize().trim());
                    } catch (Exception e) {
                        num1 = 0;
                    }
                    try {
                        num2 = Long.parseLong(f2.getSize().trim());
                    } catch (Exception e) {
                        num2 = 0;
                    }
                    retVal = Long.compare(num1, num2);
                    break;

                case SORTTYPE_DATE:
                    str1 = f1.getTime();
                    if (str1.length() == 4)    // this is year
                        num1 = (new Date(f1.getDate() + ", " + str1)).getTime();
                    else
                        num1 = (new Date(f1.getDate() + ", 1999, " + str1)).getTime();

                    str2 = f2.getTime();
                    if (str2.length() == 4)    // this is year
                        num2 = (new Date(f2.getDate() + ", " + str2)).getTime();
                    else
                        num2 = (new Date(f2.getDate() + ", 1999, " + str2)).getTime();
                    retVal = (num1 > num2) ? 1 : (num1 == num2) ? 0 : -1;
                    break;

                case SORTTYPE_EXT:
                    str1 = f1.getName();
                    str2 = f2.getName();
                    int idx1 = str1.indexOf(".");
                    int idx2 = str2.indexOf(".");
                    if (idx1 > 0)
                        str1 = str1.substring(idx1);
                    else
                        str1 = " ";
                    if (idx2 > 0)
                        str2 = str2.substring(idx2);
                    else
                        str2 = " ";
                    retVal = str1.toLowerCase().compareTo(str2.toLowerCase());
                    if (retVal == 0)
                        retVal = f1.getName().toLowerCase().compareTo(f2.getName().toLowerCase());
                    break;

                case SORTTYPE_NAME:
                default:
                    str1 = f1.getName();
                    str2 = f2.getName();
                    retVal = str1.toLowerCase().compareTo(str2.toLowerCase());
                    break;

            }
        }
        retVal *= (sortAscending ? 1 : -1);
        return retVal;
    }

    private FileInfo[] sortFileInfos(boolean remote, FileInfo[] elms) {
        if (elms == null
            || elms.length == 0)
            //||	sortType   	== SORTTYPE_NONE)
            return elms;

        int iMax = elms.length;

        for (int i = 1; i < iMax; i++) {
            FileInfo keyObj = elms[i];
            FileInfo keyObjPrev = elms[i - 1];

            if (compareFileInfoKey(remote, keyObj, keyObjPrev) >= 0)
                continue;

            int jMax = i - 1;
            // slide all the sorted up one if necessary
            // and insert the new element in the right spot
            // Do a binary search to find the insertion point "j"
            int j;
            if (jMax == 0) {
                elms[1] = elms[0];
                elms[0] = keyObj;
                continue;
            }
            for (int jMin = 0; ; ) {
                FileInfo keyObjMid;
                if (jMax <= jMin + 1) {
                    keyObjMid = elms[jMin];
                    if (compareFileInfoKey(remote, keyObjMid, keyObj) > 0)
                        j = jMin;
                    else
                        j = jMax;
                    break;
                }

                int mid = (jMin + jMax) / 2;

                keyObjMid = elms[mid];

                int iCompare = compareFileInfoKey(remote, keyObjMid, keyObj);
                if (iCompare > 0)
                    jMax = mid;
                else if (iCompare < 0)
                    jMin = mid;
                else {
                    j = mid;
                    break;
                }
            }

            if (i - j >= 0) System.arraycopy(elms, j, elms, j + 1, i - j);
            elms[j] = keyObj; // insert at j
        }

        FileInfo[] retVal = new FileInfo[elms.length];
        System.arraycopy(elms, 0, retVal, 0, elms.length);
        return retVal;
    }

    public void statusReceived(StatusEvent event) {
        //TODO: implement this com.ibm.network.ftp.event.StatusListener method;
        String s = event.getMessage();
        txtAreaMessage.append(s);
        resetKeepAliveCountDown();
    }

    /**
     * Aborts the last issued command.
     * This command will abort the previously issued command if it
     * is running.It constructs the command event and sends it
     * to the listeners.
     */
    CommandEvent currentCommand;
    PopupMenu popupMenuFtp = new PopupMenu();
    CheckboxMenuItem checkboxMenuItemFollowInTerm = new CheckboxMenuItem();
    MenuItem menuItemChangeDir = new MenuItem();
    MenuItem menuItemRefresh = new MenuItem();
    MenuItem menuItemMakeDir = new MenuItem();
    MenuItem menuItemDelete = new MenuItem();
    MenuItem menuItemRename = new MenuItem();
    MenuItem menuItemUploadText = new MenuItem();
    MenuItem menuItemDownloadText = new MenuItem();
    MenuItem menuItemExpand = new MenuItem();
    MenuItem menuItemCollapse = new MenuItem();
    MenuItem menuItemView = new MenuItem();
    MenuItem menuItemEdit = new MenuItem();
    MenuItem menuItemRun = new MenuItem();
    MenuItem menuItemProperties = new MenuItem();
    Menu menuItemSort = new Menu();
    //MenuItem menuItemSortNone = new MenuItem();
    MenuItem menuItemSortByNameAsc = new MenuItem();
    MenuItem menuItemSortByExtAsc = new MenuItem();
    MenuItem menuItemSortBySizeAsc = new MenuItem();
    MenuItem menuItemSortByDateAsc = new MenuItem();
    MenuItem menuItemSortByNameDes = new MenuItem();
    MenuItem menuItemSortByExtDes = new MenuItem();
    MenuItem menuItemSortBySizeDes = new MenuItem();
    MenuItem menuItemSortByDateDes = new MenuItem();
    MenuItem menuItemUploadBinary = new MenuItem();
    MenuItem menuItemDownloadBinary = new MenuItem();

    public void ftpcmd_abort(boolean remote) {
        //Create a vector for parameters
        Vector<String> pmtrs = new Vector<>();
        //Construct the CommandEvent object'
        CommandEvent ce = new CommandEvent(this, "abort", pmtrs, remote);

        //Assign the new CommandEvent object to the currentCommand object
        //so that the Protocol Bean can use the getter methods
        this.currentCommand = ce;

        //Send the CommandEvent object
        this.sendCommandEvent(ce);
    }

    /**
     * Current remote and local directories. Set when File List is received
     * This is used for other commands to determine is the directory should
     * be changed before executing certain FTP commands.
     */
    private String ftp_localDir;
    private String ftp_remoteDir;

    /**
     * Changes directory whose pathname is the selected item
     * in the filelist.
     *
     * @param dirName the new directory to change to.
     * @param remote true for remote directory false for local.
     */
    public void ftpcmd_changeDir(String dirName, boolean remote) {
        if (dirName != null) {
            //Create a vector for parameters
            Vector<String> files = new Vector<>();

            //Add the new file name to the Vector
            files.addElement(dirName);

            CommandEvent ce = new CommandEvent(this, "changeDir", files, remote);

            //Assign the new CommandEvent object to the currentCommand object
            //so that the Protocol Bean can use the getter methods
            this.currentCommand = ce;

            //Send the command
            this.sendCommandEvent(ce);
        }
    }

    public String ftpcmd_getStatus() {
        return ftpProtocol.getStatus();
    }

    /**
     * Returns latest FTP connection status.
     * This method constructs a CommandEvent for getting the status
     * and sends it to the CommandListener.
     */
    public void ftpcmd_checkStatus(boolean remote) {
        //Create a vector for parameters
        Vector<String> files = null;

        //Construct the CommandEvent object'
        CommandEvent ce = new CommandEvent(this, "getStatus", files, remote);

        //Assign the new CommandEvent object to the currentCommand object
        //so that the Protocol Bean can use the getter methods
        this.currentCommand = ce;

        //Send the CommandEvent object
        this.sendCommandEvent(ce);
    }

    /**
     * Clears the already existing status messages in the status window.
     */
    public void clearStatusWindow() {
        //Call the BottomPanel's method
        txtAreaMessage.setText("");
    }

    /**
     * Configures socksProxyHost and socksProxyPort.
     * Socks host and Socks port will be received through "Configure Socks" dialog box.
     * The socksProxyHost and socksProxyPort are set by this method.
     * Socks host and socks port will be used by FTPProtocol for connecting to servers
     * that are outside the firewall.
     */
    public void ftpcmd_configureSocks() {
        //Set the parent frame
        //this.frm = ;

        //Show the SocksDialog for the configuring
        //socks server and port
        SocksDialog sd = new SocksDialog(getParentFrame(), this);

        //Set the Background of the SocksDialog
        sd.setDialogBackground(super.getBackground());

        //Set the textfields' Background in the Dialog
        //sd.setTextBackground(this.dirSelectedColor);

        //Set the Dialog Buttons' Background,Foreground & Font
        //sd.setButtonsBackground(this.toolbarBackground);
        //sd.setButtonsForeground(this.toolbarForeground);
        //sd.setButtonsFont(this.toolbarFont);

        //Set the Dialog Labels' Foreground & Font
        //sd.setLabelsForeground(this.labelsForeground);


        //Set the location of the dialog for showing
        //in the parent's center
        sd.setLocation(this.findCenterPoint(sd));

        sd.show();
    }


    private boolean connected;

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean s) {
        connected = s;
        if (keepAliveThread == null)
            keepAliveThread = new KeepAliveThread();

        resetKeepAliveCountDown();

        if (s) {
            if (keepAliveThread.isAlive())
                keepAliveThread.resume();
            else
                keepAliveThread.start();
        } else {
            if (keepAliveThread.isAlive())
                keepAliveThread.suspend();
            else {
                keepAliveThread.stop();
                keepAliveThread = null;
            }
        }
    }

    private String remoteHost;

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String s) {
        remoteHost = s;
    }

    private String proxyHost;

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String s) {
        proxyHost = s;
    }

    private String proxyPort;

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String s) {
        proxyPort = s;
    }

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String s) {
        userId = s;
    }

    private String passwd;

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String s) {
        passwd = s;
    }

    private String acct;

    public String getAcct() {
        return acct;
    }

    public void setAcct(String s) {
        acct = s;
    }


    /**
     * This should be called to reestablish a connection that has timed out.
     * The connection is established and the current working directories
     * on the remote and local host are set to the values before disconnect
     */
    public void ftpcmd_reconnect() {
        setFileListReceiverMode(FILELISTRECEIVER_MODE_IGNORE, FILELISTRECEIVER_MODE_IGNORE);
        String currRemoteDir = ftp_remoteDir;
        String currLocalDir = ftp_localDir;
        ftpcmd_disconnect();
        ftpcmd_connect(getRemoteHost());
        ftpcmd_login(getUserId(), getPasswd(), getAcct());
        setConnected(true);
        ftpcmd_changeDir(currRemoteDir, true);
        ftpcmd_changeDir(currLocalDir, false);
        setFileListReceiverMode(FILELISTRECEIVER_MODE_CHANGES_ONLY, FILELISTRECEIVER_MODE_CHANGES_ONLY);
        ftpProtocol.getRemoteFileList();
        ftpProtocol.getLocalFileList();
    }

    public void setFileListReceiverMode(int localMode, int remoteMode) {
        switch (localMode) {
            case FILELISTRECEIVER_MODE_IGNORE:
            case FILELISTRECEIVER_MODE_REFRESH:
                fileListReceiverModeLocal = localMode;
                break;

            default:
                fileListReceiverModeLocal = FILELISTRECEIVER_MODE_CHANGES_ONLY;
                break;
        }

        switch (remoteMode) {
            case FILELISTRECEIVER_MODE_IGNORE:
            case FILELISTRECEIVER_MODE_REFRESH:
                fileListReceiverModeLocal = remoteMode;
                break;

            default:
                fileListReceiverModeLocal = FILELISTRECEIVER_MODE_CHANGES_ONLY;
                break;
        }
    }

    /*
     * Connect to the server, without logging in.
     * @param		hostName java.lang.String
     */
    public void ftpcmd_connect(String hostName) {
        setRemoteHost(hostName);

        Vector<String> pmtrs = new Vector<>();
        try {
            //Check the parameters
            if (hostName == null || hostName.length() == 0) {
                showMessage("Host name has to be entered", true);
                return;
            }

            //Add the parameters to the Vector
            pmtrs.addElement(hostName);

            //Construct CommandEvent object for connect
            CommandEvent ce1 = new CommandEvent(this, "connect", pmtrs, true);
            //Assign the new CommandEvent object to the currentCommand object
            //so that the Protocol Bean can use the getter methods
            this.currentCommand = ce1;

            //Send this connect CommandEvent object to the command listeners
            this.sendCommandEvent(ce1);
        } catch (Exception e) {
            this.handleException(e);
        }
    }

    /*
     * Get the parameters from the Connection dialog,
     * constructs the connection and login CommandEvents and
     * sends it to the listener.
     *
     * @param		hostName java.lang.String
     * @param		userName java.lang.String
     * @param		password java.lang.String
     * @param		account  java.lang.String
     */
    public void ftpcmd_connect(String hostName, String userName, String password, String account) {
        ftpcmd_connect(hostName);
        ftpcmd_login(userName, password, account);
        setConnected(true);
    }

    /**
     * Renames a file or directory in current working directory of the
     * local machine or FTP server depending on which one of them is selected.
     * Old file name will be received from the selected directory name in the list.
     * New file name will be received from a "Rename File" dialog box.
     * It constructs a CommandEvent and sends it to the listener.
     */
    public void ftpcmd_rename(String origName, String newName, boolean remote) {
        if (origName == null
            || newName == null
            || origName.length() == 0
            || newName.length() == 0)
            return;

        // as special consideration for local windows machine, make sure
        // that the current directory is specified and only
        // filename is sent as parameters to this command
        if (!remote
            && File.separatorChar == '\\') {
            // PVNode	fileTreeRootNode = fileTreeLocalRootNode;
            int idx = origName.lastIndexOf('/');
            if (idx > 0) {
                String dirName = origName.substring(0, idx);
                String origFileName = origName.substring(idx + 1);
                String newFileName = newName;

                if (dirName.compareTo(ftpProtocol.getCurrentDir(remote)) != 0)
                    ftpcmd_changeDir(dirName, remote);
                idx = newName.lastIndexOf('/');
                if (idx >= 0)
                    newFileName = newName.substring(idx + 1);
                ftpcmd_rename(origFileName, newFileName, remote);
                return;
            }
        }

        //Create a vector for parameters
        Vector<String> pmtr = new Vector<>();

        pmtr.addElement(origName);
        //Add the new file name to the Vector
        pmtr.addElement(newName);
        CommandEvent ce = new CommandEvent(this, "rename", pmtr, remote);
        //Assign the new CommandEvent object to the currentCommand object
        //so that the Protocol Bean can use the getter methods
        this.currentCommand = ce;
        this.sendCommandEvent(ce);
    }

    /**
     * This method sets the "TYPE"  to be used for data transfer on the
     * data connection. The String parameter can specify "ASCII" for
     * ascii data transfer and "BINARY" for binary data transfer.
     *
     * @param ascii if tue then data transfer type is "ascii" otherwise "binary" for the FTP connection.
     */
    public void ftpcmd_setType(boolean ascii) {
        //check that the type parameter is not null
        ftpProtocol.setType((ascii ? "ascii" : "binary"));
    }

    /*
     * Login to the current host
     *
     * @param		userName java.lang.String
     * @param		password java.lang.String
     * @param		account  java.lang.String
     */
    public void ftpcmd_login(String userName, String password, String account) {
        setUserId(userName);
        setPasswd(password);
        setAcct(account);

        Vector<String> pmtrs = new Vector<>();
        try {
            //Check the parameters
            if (userName == null || userName.equals("")) {
                showMessage("User name has to be entered", true);
                return;
            }
            if (password == null || password.equals("")) {
                showMessage("Password has to be entered", true);
                return;
            }

            pmtrs.addElement(userName);
            pmtrs.addElement(password);
            //Add account if it's given
            if (account != null && !account.isEmpty()) {
                pmtrs.addElement(account);
            }

            //Construct the CommandEvent Object
            CommandEvent ce2 = new CommandEvent(this, "login", pmtrs, true);

            //Assign the new CommandEvent object to the currentCommand object
            //so that the Protocol Bean can use the getter methods
            this.currentCommand = ce2;

            //Send this CommandEvent object to the command listeners
            this.sendCommandEvent(ce2);
        } catch (Exception e) {
            this.handleException(e);
        }
    }

    /**
     * If the path does not contain any pathSeparator, then the
     * directory is created in the current working directory of local machine or
     * FTP server depending on which one of them is selected.
     * <p>
     * If the path contains "/" or "\\", then each segment of the
     * path is made.
     */
    public void ftpcmd_makeDir(String dirName, boolean remote) {
        if (dirName == null
            || dirName.length() == 0)
            return;

        if (!remote) {
            if (File.separatorChar == '\\') {
                dirName = dirName.replace('\\', '/');
                if (dirName.charAt(1) == ':')
                    dirName = "/" + dirName;
            }
        }
        //Create a vector for parameters
        Vector<String> files = new Vector<>();

        StringTokenizer st = new StringTokenizer(dirName, "/");

        if (st.countTokens() == 1) {
            files.addElement(st.nextToken());
            CommandEvent ce = new CommandEvent(this, "makeDir", files, remote);
            this.currentCommand = ce;
            this.sendCommandEvent(ce);
        } else {
            StringBuilder dirParent = new StringBuilder("/");
            while (st.hasMoreTokens()) {
                String tmp = st.nextToken();
                if (tmp.endsWith(":")
                    && dirParent.length() <= 1) {
                    dirParent.append(tmp + '/');
                    continue; // cannot makeDir on Drives
                }
                ftpcmd_changeDir(dirParent.toString(), remote);
                ftpcmd_makeDir(tmp, remote);
                if (!dirParent.toString().endsWith("/"))
                    dirParent.append("/");
                dirParent.append(tmp);
            }
        }
    }

    /**
     * Deletes a file/directory.
     */
    public void ftpcmd_deleteFile(String fileName, boolean isDir, boolean remote) {
        if (fileName == null
            || fileName.equals(""))
            return;

        // for local command, ensure the current directory is valid
        if (!remote) {
            int idx = fileName.lastIndexOf('/');
            if (idx > 0) {
                String dirName = fileName.substring(0, idx);
                fileName = fileName.substring(idx + 1);
                if (File.separatorChar == '\\'
                    && dirName.charAt(0) == '/'
                    && dirName.charAt(2) == ':')
                    dirName = dirName.substring(1);
                ftpcmd_changeDir(dirName, remote);
            }
        }

        //Create parameter for sending each file
        Vector<String> pmtr = new Vector<>();
        pmtr.addElement(fileName);

        //Create the CommandEvent object
        CommandEvent ce = new CommandEvent(this, (isDir ? "removeDir" : "deleteFile"), pmtr, remote);

        //Assign the new CommandEvent object to the currentCommand object
        //so that the Protocol Bean can use the getter methods
        this.currentCommand = ce;
        this.sendCommandEvent(ce);
    }

    /**
     * Show a connect dialog and establish a connection
     */

    public void connect(boolean forceShowDialog) {
        String h = getRemoteHost();
        String u = null;
        String p = null;
        String a = null;
        String proxyHost = null;
        String proxyPort = null;

        FtpConnectInfo ftpConnectInfo = null;
        Hashtable<String, FtpConnectInfo> hashTable = palTerm.getFtpConnectInfoTable();

        boolean showDialog = true;

        if (h != null
            && hashTable != null)
            ftpConnectInfo = hashTable.get(h);


        if (ftpConnectInfo != null) {
            u = ftpConnectInfo.getUserId();
            p = ftpConnectInfo.getPasswd();
            a = ftpConnectInfo.getFtpAcct();
            proxyHost = ftpConnectInfo.getFtpProxyHost();
            proxyPort = ftpConnectInfo.getFtpProxyPort();

            if (u != null
                && p != null
                && u.length() > 0
                && p.length() > 0)
                showDialog = false;
        }

        if (showDialog
            || forceShowDialog) {
            ConnectDialogChangeListener listener = new ConnectDialogChangeListener(palTerm, this);

            PromptDialog dlg = new PromptDialog(getParentFrame(), "Connect to", 6, listener);
            listener.setConnectDialog(dlg);

            int fldNo = CONNECT_DLG_FLDNO_HOST;
            dlg.setLabel(fldNo, "Host:");
            dlg.setTextField(fldNo, (ftpConnectInfo != null) ? ftpConnectInfo.getHost() : getRemoteHost());
            dlg.setTextFieldEnabled(fldNo, true);

            fldNo = CONNECT_DLG_FLDNO_USERID;
            dlg.setLabel(fldNo, "Login Id:");
            dlg.setTextField(fldNo, (ftpConnectInfo != null) ? ftpConnectInfo.getUserId() : getUserId());
            dlg.setTextFieldEnabled(fldNo, true);

            fldNo = CONNECT_DLG_FLDNO_PASSWD;
            dlg.setLabel(fldNo, "Password:");
            dlg.setTextField(fldNo, (ftpConnectInfo != null) ? ftpConnectInfo.getPasswd() : getPasswd());
            dlg.setTextFieldEnabled(fldNo, true);
            dlg.setTextFieldEchoChar(fldNo, '*');

            fldNo = CONNECT_DLG_FLDNO_PROXY_HOST;
            dlg.setLabel(fldNo, "Proxy Host:");
            dlg.setTextField(fldNo, (ftpConnectInfo != null) ? ftpConnectInfo.getFtpProxyHost() : getProxyHost());
            dlg.setTextFieldEnabled(fldNo, true);

            fldNo = CONNECT_DLG_FLDNO_PROXY_PORT;
            dlg.setLabel(fldNo, "Proxy Port:");
            dlg.setTextField(fldNo, (ftpConnectInfo != null) ? ftpConnectInfo.getFtpProxyPort() : getProxyPort());
            dlg.setTextFieldEnabled(fldNo, true);

            fldNo = CONNECT_DLG_FLDNO_ACCT;
            dlg.setLabel(fldNo, "Acct:");
            dlg.setTextField(fldNo, (ftpConnectInfo != null) ? ftpConnectInfo.getFtpAcct() : getAcct());
            dlg.setTextFieldEnabled(fldNo, true);

            dlg.setEnterOK(true);
            dlg.setEscapeCancel(true);
            dlg.setShowSaveCheckBox(true);
            dlg.setSaveFlagText("Save Login Info");
            dlg.setSaveFlag(true);

            //dlg.setSize(this.getSize().width, dlg.getSize().height);
            dlg.pack();
            dlg.show();
            if (dlg.getResult().equals(PromptDialog.OK)) {
                proxyHost = dlg.getTextField(CONNECT_DLG_FLDNO_PROXY_HOST);
                proxyPort = dlg.getTextField(CONNECT_DLG_FLDNO_PROXY_PORT);
                h = dlg.getTextField(CONNECT_DLG_FLDNO_HOST);
                u = dlg.getTextField(CONNECT_DLG_FLDNO_USERID);
                p = dlg.getTextField(CONNECT_DLG_FLDNO_PASSWD);
                a = dlg.getTextField(CONNECT_DLG_FLDNO_ACCT);

                if (ftpConnectInfo != null) {
                    ftpConnectInfo.setHost(h);
                    ftpConnectInfo.setUserId(u);
                    ftpConnectInfo.setPasswd(p);
                    ftpConnectInfo.setFtpAcct(a);
                    ftpConnectInfo.setFtpProxyHost(proxyHost);
                    ftpConnectInfo.setFtpProxyPort(proxyPort);
                } else {
                    ftpConnectInfo = new FtpConnectInfo(palTerm, h, 23, u, p, a, proxyHost, proxyPort);
                }

                if (dlg.getSaveFlag()) {
                    hashTable.put(h, ftpConnectInfo);
                    ftpConnectInfo.writeFtpConnectInfo(hashTable);
                } else {
                    if (h != null
                        && h.length() > 0) {
                        FtpConnectInfo obj = hashTable.get(h);
                        if (obj != null)
                            hashTable.remove(h);
                    }
                }
            } else
                ftpConnectInfo = null;
        }
        if (ftpConnectInfo != null) {
            if (isConnected())
                ftpcmd_disconnect();
            if (proxyHost != null
                && proxyPort != null
                && proxyHost.trim().length() > 0
                && proxyPort.trim().length() > 0) {
                setSocksInfo(proxyHost.trim(), proxyPort.trim());
            }
            if (h != null
                && u != null
                && p != null
                && h.trim().length() > 0
                && u.trim().length() > 0)
                ftpcmd_connect(h.trim(), u.trim(), p.trim(), a);
        }
    }

    /**
     * Terminate current session, but don't exit program.
     * It constructs CommandEvent for disconnecting and sends
     * it to the CommandListener (FTPProtocol).
     */

    public void ftpcmd_disconnect() {
        setConnected(false);

        Vector<String> pmtrs = null;

        //Construct the CommandEvent Object
        CommandEvent ce = new CommandEvent(this, "disconnect", pmtrs, true);

        //Assign the new CommandEvent object to the currentCommand object
        //so that the Protocol Bean can use the getter methods
        this.currentCommand = ce;
        //Send this CommandEvent object to the command listeners
        this.sendCommandEvent(ce);
    }

    /**
     * Downloads a file from the remote server to the local machine.
     * Selected file name in the remote file list is the file to be downloaded
     * and is passed as a parameter to this method.
     *
     * @param    fileName the name of the file to be downloaded.
     */
    public void ftpcmd_download(String fileName, boolean remote, String localFileName) {
        //If nothing selected in the list
        if (fileName == null
            || fileName.equals(""))
            return;

        //Create a vector for parameters
        Vector<String> files = new Vector<>();

        //Get the selected files in the Remote list
        files.addElement(fileName);
        if (localFileName != null)
            files.addElement(localFileName);

        //Construct the CommandEvent Object
        CommandEvent ce = new CommandEvent(this, "getFile", files, remote);

        //Assign the new CommandEvent object to the currentCommand object
        //so that the Protocol Bean can use the getter methods
        this.currentCommand = ce;
        //Send this CommandEvent object to the command listeners
        this.sendCommandEvent(ce);
    }

    /**
     * Configures socksProxyHost and socksProxyPort.
     * The socksProxyHost and socksProxyPort are set by this method.
     * This host will be used by Protocol for connecting to servers
     * that are outside the firewall.
     *
     * @param socksHost java.lang.String
     * @param socksPort java.lang.String
     */
    public void setSocksInfo(String socksHost, String socksPort) {
        setProxyHost(socksHost);
        setProxyPort(socksPort);

        try {
            //Check the parameters
            if (socksHost.equals("") || socksHost == null || socksPort.equals("") || socksPort == null) {
                showMessage("Socks Host and Port have to be entered", true);
                return;
            }

            //Create a vector for parameters
            Vector<String> files = new Vector();

            //Add the parameters in the vector
            files.addElement(socksHost);

            //Cast the in to Integer object
            files.addElement(socksPort);

            //Construct the CommandEvent object'
            CommandEvent ce = new CommandEvent(this, "configureSocks", files, true);

            //Assign the new CommandEvent object to the currentCommand object
            //so that the Protocol Bean can use the getter methods
            this.currentCommand = ce;

            //Send the CommandEvent object
            this.sendCommandEvent(ce);
        } catch (Exception e) {
            this.handleException(e);
        }
    }

    /**
     * Uploads a file from the local machine to the FTP server.
     * Selected file name in the localfile list is the parameter to this method.
     *
     * @param    localFileName name of the file to be uploaded.
     */
    public void ftpcmd_upload(String localFileName, boolean remote, String remoteFileName) {
        // as special consideration for local windows machine, make sure
        // that the current directory is specified and only
        // filename is sent as parameters to this command
        if (!remote
            && File.separatorChar == '\\') {
            int idx = localFileName.lastIndexOf('/');
            if (idx > 0) {
                String dirName = localFileName.substring(0, idx);
                localFileName = localFileName.substring(idx + 1);

                if (dirName.compareTo(ftpProtocol.getCurrentDir(remote)) != 0) {
                    ftpcmd_changeDir(dirName, remote);
                }
                ftpcmd_upload(localFileName, remote, remoteFileName);
                return;
            }
        }


        //Create a vector for parameters
        Vector<String> files = new Vector<>();

        //Get the selected files in the Remote list
        files.addElement(localFileName);
        if (remoteFileName != null) {
            files.addElement(remoteFileName);
        }

        //Construct the CommandEvent Object
        CommandEvent ce = new CommandEvent(this, "putFile", files, remote);

        //Assign the new CommandEvent object to the currentCommand object
        //so that the Protocol Bean can use the getter methods
        this.currentCommand = ce;
        //Send this CommandEvent object to the command listeners
        this.sendCommandEvent(ce);
    }

    public static final int DEFAULT_KEEPALIVE_DURATION_MINS = 3;
    private int keepAliveCountDown = DEFAULT_KEEPALIVE_DURATION_MINS;

    /**
     * FTP will automatically timeout after a default number of seconds.
     * usually this time approximately 5 minutes. In order to keep
     * the connection alive, this panel creates a thread to ping the
     * server with no operation.
     * <p>
     * This method is called upon sending an FTP command. A separate thread
     * will decrement this counter and will issue the noop to the server
     * when this timeout goes below zero.
     */
    void resetKeepAliveCountDown() {
        keepAliveCountDown = DEFAULT_KEEPALIVE_DURATION_MINS;
    }

    /**
     * Receives the command event and send it to the registered listeners.
     *
     * @param ce com.ibm.network.ftp.event.CommandEvent
     */
    void sendCommandEvent(CommandEvent ce) {
        resetKeepAliveCountDown();

        // display message
        if (ce.getCommand().equals("connect")) {
            Vector<String> pmtrs = ce.getParameters();
            String host = pmtrs.elementAt(0);
            this.txtAreaMessage.append("Connecting to host " + host + ",please wait...\n");
        } else
            this.txtAreaMessage.append("Please wait....\n");

        //Call all FTP protocol's commandPerformed method
        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        boolean success = false;
        if (isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalFileAccess");// for property read of local "user.dir" in IBM's ftp classes
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalPropertyRead");// for property read of local "user.dir" in IBM's ftp classes
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalPropertyWrite");// for property read of local "user.dir" in IBM's ftp classes
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                //if (debug)
                //    System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                //if (debug)
                //    System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.PROPERTY);
                //if (debug)
                //    System.out.println("Got PROPERTY permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }
        ftpProtocol.commandPerformed(ce);
    }

    void dirTreeLocal_actionPerformed(PVTreeActionEvent e) {
        int id = e.getID();
        PVNode n = e.getNode();
        PVNode n2;
        Vector selectedNodes = dirTreeLocalSelectedNodes;

        switch (id) {
            case PVTreeActionEvent.NODE_EXPANDED: // Node is about to be expanded
                if (n.HasChildren()) {
                    n2 = n.getChild();
                    if (n2.getText().equals(FAKENODE_STR))
                        changeDir(n, false);
                }
                break;

            case PVTreeActionEvent.NODE_DOUBLE_CLICKED:
                changeDir(n, false);
                break;

            case PVTreeActionEvent.NODE_CLICKED:
                // select/deselect
                if (dirTreeLocalCtrlPressed)
                    reverseNodeSelection(selectedNodes, n);
                else if (dirTreeLocalShiftPressed) {
                    if (!selectedNodes.contains(n))
                        reverseNodeSelection(selectedNodes, n);
                    while ((n = n.getPrevVisible()) != null) {
                        if (selectedNodes.contains(n))
                            break;
                        else
                            reverseNodeSelection(selectedNodes, n);
                    }
                } else {
                    // if it is already selected or is the current - then do nothing
                    // otherwise, unselect all and select this one
                    if (!selectedNodes.contains(n)) {
                        int selCnt = selectedNodes.size();
                        for (int i = 0; i < selCnt; i++) {
                            PVNode pvNode1 = (PVNode) selectedNodes.elementAt(i);
                            pvNode1.setBackground(unselectedNodeBackground);
                            pvNode1.setForeground(unselectedNodeForeground);
                        }
                        selectedNodes.removeAllElements();
                        // add the current node
                        reverseNodeSelection(selectedNodes, n);

                        changeDir(n, false);
                    }
                }
                break;

            default:

        }
    }

    void fileTreeLocal_actionPerformed(PVTreeActionEvent e) {
        int id = e.getID();
        PVNode n = e.getNode();
        PVNode n2;
        String dir;
        Vector<PVNode> selectedNodes = fileTreeLocalSelectedNodes;
        //PVNode  parentPVNode = n.getParent();

        switch (id) {
            case PVTreeActionEvent.NODE_EXPANDED: // Node is about to be expanded
                if (n.HasChildren()) {
                    n2 = n.getChild();
                    if (n2.getText().equals(FAKENODE_STR)) {
                        dir = getNodePath(n);
                        //dir = fileTreeLocalRootNode.getText() + "/" + n.getText();
                        //idx = dir.indexOf("    ");
                        //if ( idx > 0)
                        //	dir = dir.substring(0,idx);
                        ftpcmd_changeDir(dir, false);
                    }
                }
                break;

            case PVTreeActionEvent.NODE_DOUBLE_CLICKED:
                //if (n == fileTreeLocalRootNode)
                //	dir = fileTreeLocalRootNode.getText();
                //else
                //	dir = fileTreeLocalRootNode.getText() + "/" + n.getText();
                //idx = dir.indexOf("    ");
                //if ( idx > 0)
                //	dir = dir.substring(0,idx);
                dir = getNodePath(n);
                ftpcmd_changeDir(dir, false);

                // sync dirTree
                ensureVisibleDirTreeNode(dir, false);
                break;

            case PVTreeActionEvent.NODE_CLICKED:
                if (fileTreeLocalCtrlPressed)
                    reverseNodeSelection(fileTreeLocalSelectedNodes, n);
                else if (fileTreeLocalShiftPressed) {
                    if (!selectedNodes.contains(n))
                        reverseNodeSelection(selectedNodes, n);
                    while ((n = n.getPrevVisible()) != null) {
                        if (selectedNodes.contains(n))
                            break;
                        else
                            reverseNodeSelection(selectedNodes, n);
                    }
                } else {
                    // if it is already selected or is the current - then do nothing
                    // otherwise, unselect all and select this one
                    if (!selectedNodes.contains(n)) {
                        int selCnt = selectedNodes.size();
                        for (int i = 0; i < selCnt; i++) {
                            PVNode pvNode1 = selectedNodes.elementAt(i);
                            pvNode1.setBackground(unselectedNodeBackground);
                            pvNode1.setForeground(unselectedNodeForeground);
                        }
                        selectedNodes.removeAllElements();
                        // add the current node
                        reverseNodeSelection(selectedNodes, n);
                    }
                }

                break;

            default:
        }
    }

    private void reverseNodeSelection(Vector<PVNode> v, PVNode n) {
        if (v.contains(n)) {    // deselect
            n.setBackground(unselectedNodeBackground);
            n.setForeground(unselectedNodeForeground);
            v.removeElement(n);
        } else {    // select
            n.setBackground(selectedNodeBackground);
            n.setForeground(selectedNodeForeground);
            v.addElement(n);
        }
    }

    private PVNode popupMenuPVNode = null;
    private PVTree popupMenuPVTree;
    private boolean popupMenuIsRemote;
    private boolean popupMenuIsDirTree;
    private int popupMenuX;
    private int popupMenuY;
    private Vector<PVNode> popupMenuSelectedNodes;

    private void showPopupMenuFtp() {
        // enable// disable menu items
        checkboxMenuItemFollowInTerm.setEnabled(terminalSender != null && popupMenuIsRemote && popupMenuPVNode.HasChildren());
        menuItemChangeDir.setEnabled(popupMenuPVNode.HasChildren());
        menuItemRefresh.setEnabled(true);
        menuItemMakeDir.setEnabled(popupMenuPVNode.HasChildren());
        menuItemDelete.setEnabled(true);
        menuItemRename.setEnabled(true);
        menuItemUploadText.setEnabled(showRemote && !popupMenuIsRemote && !popupMenuPVNode.HasChildren());
        menuItemUploadBinary.setEnabled(showRemote && !popupMenuIsRemote && !popupMenuPVNode.HasChildren());
        menuItemDownloadText.setEnabled(showLocal && popupMenuIsRemote && !popupMenuPVNode.HasChildren());
        menuItemDownloadBinary.setEnabled(showLocal && popupMenuIsRemote && !popupMenuPVNode.HasChildren());
        menuItemExpand.setEnabled(popupMenuPVNode.HasChildren() && !popupMenuPVNode.getExpanded());
        menuItemCollapse.setEnabled(popupMenuPVNode.HasChildren() && popupMenuPVNode.getExpanded());
        menuItemView.setEnabled(!popupMenuPVNode.HasChildren());
        menuItemEdit.setEnabled(!popupMenuPVNode.HasChildren());
        menuItemRun.setEnabled(!popupMenuPVNode.HasChildren() && ((terminalSender != null && popupMenuIsRemote) || (!popupMenuIsRemote)));
        menuItemProperties.setEnabled(true);
        menuItemSort.setEnabled(popupMenuPVNode.HasChildren());
        menuItemSortByNameAsc.setEnabled(popupMenuPVNode.HasChildren());
        menuItemSortByExtAsc.setEnabled(popupMenuPVNode.HasChildren());
        menuItemSortBySizeAsc.setEnabled(popupMenuPVNode.HasChildren());
        menuItemSortByDateAsc.setEnabled(popupMenuPVNode.HasChildren());
        //menuItemSortNone.setEnabled(popupMenuPVNode.HasChildren());

        popupMenuFtp.show(popupMenuPVTree, popupMenuX, popupMenuY);
    }

    void dirTreeRemote_mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()
            || e.isMetaDown()) {
            PVNode pvNode = dirTreeRemote.getSelectedNode();
            if (pvNode == null)
                return;

            popupMenuPVTree = dirTreeRemote;
            popupMenuPVNode = pvNode;
            popupMenuIsRemote = true;
            popupMenuIsDirTree = true;
            popupMenuX = e.getX();
            popupMenuY = e.getY();
            popupMenuSelectedNodes = dirTreeRemoteSelectedNodes;
            if (!popupMenuSelectedNodes.contains(pvNode))
                popupMenuSelectedNodes.addElement(pvNode);

            showPopupMenuFtp();
        }
    }

    void fileTreeRemote_mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()
            || e.isMetaDown()) {
            PVNode pvNode = fileTreeRemote.getSelectedNode();
            if (pvNode == null)
                return;

            popupMenuPVTree = fileTreeRemote;
            popupMenuPVNode = pvNode;
            popupMenuIsRemote = true;
            popupMenuIsDirTree = false;
            popupMenuX = e.getX();
            popupMenuY = e.getY();
            popupMenuSelectedNodes = fileTreeRemoteSelectedNodes;
            if (!popupMenuSelectedNodes.contains(pvNode))
                popupMenuSelectedNodes.addElement(pvNode);

            showPopupMenuFtp();
        }
    }

    void dirTreeLocal_mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()
            || e.isMetaDown()) {
            PVNode pvNode = dirTreeLocal.getSelectedNode();
            if (pvNode == null)
                return;

            popupMenuPVTree = dirTreeLocal;
            popupMenuPVNode = pvNode;
            popupMenuIsRemote = false;
            popupMenuIsDirTree = true;
            popupMenuX = e.getX();
            popupMenuY = e.getY();
            popupMenuSelectedNodes = dirTreeLocalSelectedNodes;
            if (!popupMenuSelectedNodes.contains(pvNode))
                popupMenuSelectedNodes.addElement(pvNode);

            showPopupMenuFtp();
        }
    }

    void fileTreeLocal_mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()
            || e.isMetaDown()) {
            PVNode pvNode = fileTreeLocal.getSelectedNode();
            if (pvNode == null)
                return;

            popupMenuPVTree = fileTreeLocal;
            popupMenuPVNode = pvNode;
            popupMenuIsRemote = false;
            popupMenuIsDirTree = false;
            popupMenuX = e.getX();
            popupMenuY = e.getY();
            popupMenuSelectedNodes = fileTreeLocalSelectedNodes;
            if (!popupMenuSelectedNodes.contains(pvNode))
                popupMenuSelectedNodes.addElement(pvNode);

            showPopupMenuFtp();
        }
    }

    void checkboxMenuItemFollowInTerm_itemStateChanged(ItemEvent e) {
        if (terminalSender != null
            && e.getStateChange() == ItemEvent.SELECTED
            && ftp_remoteDir != null)
            terminalSender.send("cd " + ftp_remoteDir + "\n");
    }

    void menuItemChangeDir_actionPerformed(ActionEvent e) {
        String s = getNodePath(popupMenuPVNode);
        if (s != null) {
            boolean isDir = popupMenuPVNode.HasChildren();
            String remoteOrLocal = (popupMenuIsRemote ? "Remote" : "Local");
            PromptDialog dlg;
            if (isDir) {
                dlg = new PromptDialog(getParentFrame(), "Change " + remoteOrLocal + " Directory ?", 1, null);
                dlg.setLabel(0, "Directory:");
            } else {
                dlg = new PromptDialog(getParentFrame(), "Change " + remoteOrLocal + " File ?", 1, null);
                dlg.setLabel(0, "File:");
            }
            dlg.setTextField(0, s);
            dlg.setTextFieldEnabled(0, true);
            dlg.setEnterOK(true);
            dlg.setEscapeCancel(true);
            //dlg.setSize(this.getSize().width, dlg.getSize().height);
            dlg.pack();
            dlg.show();
            if (dlg.getResult().equals(PromptDialog.OK)) {
                s = dlg.getTextField(0);
                ftpcmd_changeDir(s, popupMenuIsRemote);
            }
        }
    }

    void menuItemRefresh_actionPerformed(ActionEvent e) {
        String s = getNodePath(popupMenuPVNode);
        if (s != null)
            ftpcmd_changeDir(s, popupMenuIsRemote);
    }

    void menuItemMakeDir_actionPerformed(ActionEvent e) {
        String s = getNodePath(popupMenuPVNode);
        if (s != null) {
            String newName = "/newDir";
            PromptDialog dlg = new PromptDialog(getParentFrame(), "Create Directory", 2, null);
            dlg.setLabel(0, "Parent Directory:");
            dlg.setTextField(0, s);
            dlg.setTextFieldEnabled(0, true);
            dlg.setLabel(1, "New Directory:");
            dlg.setTextField(1, "newDir");
            dlg.setTextFieldEnabled(1, true);
            dlg.setEnterOK(true);
            dlg.setEscapeCancel(true);
            //dlg.setSize(this.getSize().width, dlg.getSize().height);
            dlg.pack();
            dlg.show();
            if (dlg.getResult().equals(PromptDialog.OK)) {
                s = dlg.getTextField(0);
                newName = dlg.getTextField(1);
            }

            if (newName != null
                && newName.trim().length() > 0) {
                ftpcmd_changeDir(s, popupMenuIsRemote);
                ftpcmd_makeDir(newName.trim(), popupMenuIsRemote);
            }
        }
    }

    void menuItemDelete_actionPerformed(ActionEvent e) {
        String s = getNodePath(popupMenuPVNode);
        if (s != null) {
            boolean isDir = popupMenuPVNode.HasChildren();
            String remoteOrLocal = (popupMenuIsRemote ? "Remote" : "Local");
            PromptDialog dlg;
            if (isDir) {
                dlg = new PromptDialog(getParentFrame(), "Delete " + remoteOrLocal + " Directory ?", 1, null);
                dlg.setLabel(0, "Directory:");
            } else {
                dlg = new PromptDialog(getParentFrame(), "Delete " + remoteOrLocal + " File ?", 1, null);
                dlg.setLabel(0, "File:");
            }
            dlg.setTextField(0, s);
            dlg.setTextFieldEnabled(0, true);
            dlg.setEnterOK(true);
            dlg.setEscapeCancel(true);
            //dlg.setSize(this.getSize().width, dlg.getSize().height);
            dlg.pack();
            dlg.show();
            if (dlg.getResult().equals(PromptDialog.OK)) {
                s = dlg.getTextField(0);
                ftpcmd_deleteFile(s, isDir, popupMenuIsRemote);
            }
        }
    }

    void menuItemRename_actionPerformed(ActionEvent e) {
        String s = getNodePath(popupMenuPVNode);

        if (s != null) {
            String newName = null;

            PromptDialog dlg = new PromptDialog(getParentFrame(), "Rename to", 2, null);
            dlg.setLabel(0, "Current Name:");
            dlg.setTextField(0, s);
            dlg.setTextFieldEnabled(0, true);
            dlg.setLabel(1, "New Name:");
            dlg.setTextField(1, s);
            dlg.setTextFieldEnabled(1, true);
            dlg.setEnterOK(true);
            dlg.setEscapeCancel(true);
            //dlg.setSize(this.getSize().width, dlg.getSize().height);
            dlg.pack();
            dlg.show();
            if (dlg.getResult().equals(PromptDialog.OK))
                newName = dlg.getTextField(1);

            if (newName != null
                && newName.trim().length() > 0) {
                ftpcmd_rename(s, newName.trim(), popupMenuIsRemote);
            }
        }
    }

  /*
  private void fileTransfer( String nm, boolean download, boolean ascii, boolean remote)
  {
	if (nm == null)
		return;

	if (!remote
	&&	File.separatorChar == '\\'
	&&	nm.charAt(2)		== ':'
	&&	nm.charAt(0)		== '/')
	{
		nm = nm.substring(1).replace('/', '\\');
	}

	ftpcmd_setType(ascii);
	if (download)
		ftpcmd_download(nm, remote, null);
	else
		ftpcmd_upload(nm, remote);
  }
  */

    void menuItemUploadText_actionPerformed(ActionEvent e) {
        Vector<PVNode> v = (Vector<PVNode>) popupMenuSelectedNodes.clone();
        int iMax = v.size();

        ftpProtocol.removeRemoteFileListListener(this);    // no updates to remote directory
        for (int i = 0; i < iMax; i++) {
            PVNode pvNode;
            try {
                pvNode = v.elementAt(i);
            } catch (Exception e2) {
                pvNode = null;
            }
            if (pvNode == null
                || pvNode.HasChildren())
                continue;

            String dir = getNodeDir(pvNode);
            String s = getNodeFile(pvNode);

            if (dir != null
                && s != null) {
                String curDir = ftpProtocol.getLocalDir().replace(File.separatorChar, '/');
                if (curDir == null
                    || (!dir.equals(curDir) && !dir.equals(curDir + "/")))
                    ftpcmd_changeDir(dir, false);

                ftpcmd_setType(true);
                ftpcmd_upload(s, false, null);
            }
        }
        v.removeAllElements();
        ftpProtocol.addRemoteFileListListener(this);
        ftpProtocol.fileList(true);
    }

    void menuItemUploadBinary_actionPerformed(ActionEvent e) {
        Vector<PVNode> v = (Vector<PVNode>)popupMenuSelectedNodes.clone();
        int iMax = v.size();

        ftpProtocol.removeRemoteFileListListener(this);    // no updates to remote directory
        for (int i = 0; i < iMax; i++) {
            PVNode pvNode;
            try {
                pvNode = v.elementAt(i);
            } catch (Exception e2) {
                pvNode = null;
            }
            if (pvNode == null
                || pvNode.HasChildren())
                continue;

            String dir = getNodeDir(pvNode);
            String s = getNodeFile(pvNode);

            if (dir != null
                && s != null) {
                String curDir = ftpProtocol.getLocalDir().replace(File.separatorChar, '/');
                if (curDir == null
                    || (!dir.equals(curDir) && !dir.equals(curDir + "/")))
                    ftpcmd_changeDir(dir, false);

                ftpcmd_setType(false);
                ftpcmd_upload(s, false, null);
            }
        }
        ftpProtocol.addRemoteFileListListener(this);
        ftpProtocol.fileList(true);
        v.removeAllElements();
    }

    void menuItemDownloadText_actionPerformed(ActionEvent e) {
        Vector<PVNode> v = (Vector<PVNode>) popupMenuSelectedNodes.clone();
        int iMax = v.size();

        ftpProtocol.removeLocalFileListListener(this);    // no updates to local directory
        for (int i = 0; i < iMax; i++) {
            PVNode pvNode;
            try {
                pvNode = v.elementAt(i);
            } catch (Exception e2) {
                pvNode = null;
            }
            if (pvNode == null
                || pvNode.HasChildren())
                continue;

            String dir = getNodeDir(pvNode);
            String s = getNodeFile(pvNode);
            if (dir != null
                && s != null) {
                String curDir = ftpProtocol.getRemoteDir();
                if (curDir == null
                    || (!dir.equals(curDir) && !dir.equals(curDir + "/")))
                    ftpcmd_changeDir(dir, true);

                ftpcmd_setType(true);
                ftpcmd_download(s, true, null);
            }
        }
        v.removeAllElements();
        ftpProtocol.addLocalFileListListener(this);
        ftpProtocol.fileList(false);
    }

    void menuItemDownloadBinary_actionPerformed(ActionEvent e) {
        Vector<PVNode> v = (Vector<PVNode>) popupMenuSelectedNodes.clone();
        int iMax = v.size();

        ftpProtocol.removeLocalFileListListener(this);    // no updates to local directory
        for (int i = 0; i < iMax; i++) {
            PVNode pvNode;
            try {
                pvNode = v.elementAt(i);
            } catch (Exception e2) {
                pvNode = null;
            }
            if (pvNode == null
                || pvNode.HasChildren())
                continue;

            String dir = getNodeDir(pvNode);
            String s = getNodeFile(pvNode);

            if (dir != null
                && s != null) {
                String curDir = ftpProtocol.getRemoteDir();
                if (curDir == null
                    || (!dir.equals(curDir) && !dir.equals(curDir + "/")))
                    ftpcmd_changeDir(dir, true);

                ftpcmd_setType(false);
                ftpcmd_download(s, true, null);
            }
        }
        v.removeAllElements();
        ftpProtocol.addLocalFileListListener(this);
        ftpProtocol.fileList(false);
    }

    void menuItemExpand_actionPerformed(ActionEvent e) {
        popupMenuPVNode.setExpanded(true);
    }

    void menuItemCollapse_actionPerformed(ActionEvent e) {
        popupMenuPVNode.setExpanded(false);
    }

    void menuItemView_actionPerformed(ActionEvent e) {
        boolean uploadAfterViewing = false;
        boolean deleteAfterViewing = popupMenuIsRemote;
        boolean viewOnly = true;

        menuItemEditOrView_actionPerformed(uploadAfterViewing, deleteAfterViewing, viewOnly);

	/*
	System.out.println("TODO: collapse \"VIEW\" menuitem to \"Edit\" with the optional flag");

	int			fileCnt				= 0;

	if (popupMenuSelectedNodes == null
	||	(fileCnt = popupMenuSelectedNodes.size()) == 0)
		return;

	boolean deleteAfterViewing	= false;
	FileTemp	fileTemp		= null;

	if (popupMenuIsRemote)
	{

		String dir	= getNodeDir(popupMenuPVNode);
		String s	= getNodeFile(popupMenuPVNode);

		if (dir != null
		&&	s   != null)
		{
			String curDir = ftpProtocol.getRemoteDir();
			if (curDir == null
			||	!dir.equals(curDir+"/"))
			{
				ftpcmd_changeDir(dir, true);
			}

			ftpcmd_setType(true);	//set to ascii type
			String fileSuffix = null;
			int		idxSuffix = s.lastIndexOf(".");
			if (idxSuffix >= 0)
				fileSuffix = s.substring(idxSuffix);

			fileTemp = new FileTemp(FileTemp.getTmpFileName(fileSuffix));
			ftpcmd_download(s, true, fileTemp.getPath());
			deleteAfterViewing	= true;
		}

	}
	else
	{
		String dir	= getNodeDir(popupMenuPVNode);
		String s	= getNodeFile(popupMenuPVNode);
		fileTemp = new FileTemp(dir + s);
	}

	if (fileTemp != null)
	{
		ViewDialog	viewDialog = new ViewDialog(getParentFrame(), "View File", true);
		viewDialog.setTextFile(deleteAfterViewing, fileTemp);
		viewDialog.setLocation(popupMenuX, popupMenuY);
		viewDialog.show();
	}
	*/
    }

    void menuItemEdit_actionPerformed(ActionEvent e) {
        boolean uploadAfterViewing = popupMenuIsRemote;
        boolean deleteAfterViewing = popupMenuIsRemote;
        boolean viewOnly = false;

        menuItemEditOrView_actionPerformed(uploadAfterViewing, deleteAfterViewing, viewOnly);
    }

    void menuItemEditOrView_actionPerformed(
        boolean uploadAfterViewing,
        boolean deleteAfterViewing,
        boolean viewOnly) {
        int fileCnt;

        if (popupMenuSelectedNodes == null
            || (fileCnt = popupMenuSelectedNodes.size()) == 0)
            return;

        FileTemp[] fileTemps = new FileTemp[fileCnt];
        String[] uploadFilePaths = new String[fileCnt];

        for (int i = 0; i < fileCnt; i++) {
            PVNode pvNode = popupMenuSelectedNodes.elementAt(i);
            if (pvNode == null
                || pvNode.HasChildren())
                continue;
            String dir = getNodeDir(pvNode);
            if (!dir.endsWith("/"))
                dir = dir + '/';
            String s = getNodeFile(pvNode);
            if (dir == null
                || s == null)
                continue;

            if (popupMenuIsRemote) {
                String curDir = ftpProtocol.getRemoteDir();
                if (curDir == null
                    || (!dir.equals(curDir) && !dir.equals(curDir + "/")))
                    ftpcmd_changeDir(dir, true);

                ftpcmd_setType(true);    //set to ascii type

                String fileSuffix = null;
                int idxSuffix = s.lastIndexOf(".");
                if (idxSuffix >= 0)
                    fileSuffix = s.substring(idxSuffix);

                fileTemps[i] = new FileTemp(FileTemp.getTmpFileName(fileSuffix,
                    isNAV,
                    isIE),
                    isNAV,
                    isIE);

                ftpcmd_download(s, true, fileTemps[i].getPath());
                uploadFilePaths[i] = dir + s;
            } else
                fileTemps[i] = new FileTemp(dir + s, isNAV, isIE);
        }

        EditDialog editDialog = new EditDialog(palTerm, viewOnly, getParentFrame(), (viewOnly ? "View File" : "Edit File"), false);
        //FtpDocOwnerInterface docOwnerInterface = new FtpDocOwnerInterface(this, uploadAfterViewing, fileTemps, uploadFileNames);
        editDialog.setTextFiles(this, uploadAfterViewing, deleteAfterViewing, fileTemps, uploadFilePaths);
        Point p = getLocation();
        editDialog.setLocation(p.x + popupMenuX, p.y + popupMenuY);
        editDialog.show();
    }

    void menuItemRun_actionPerformed(ActionEvent e) {
        String s = getNodePath(popupMenuPVNode);
        if (popupMenuIsRemote) {
            if (terminalSender != null)
                terminalSender.send(s + "\n");
            else
                System.out.println("terminalSender is not set. Cannot execute [" + s + "] on remote host");
        } else
            palTerm.exec(s);
    }

    void menuItemProperties_actionPerformed(ActionEvent e) {
        // not enabled yet
    }

    void menuItemSortByNameAsc_actionPerformed(ActionEvent e) {
        if (popupMenuIsRemote) {
            sortTypeRemote = SORTTYPE_NAME;
            sortAscendingRemote = true;
        } else {
            sortTypeLocal = SORTTYPE_NAME;
            sortAscendingLocal = true;
        }
        ftpcmd_changeDir(ftpProtocol.getCurrentDir(popupMenuIsRemote), popupMenuIsRemote);
    }

    void menuItemSortByExtAsc_actionPerformed(ActionEvent e) {
        if (popupMenuIsRemote) {
            sortTypeRemote = SORTTYPE_EXT;
            sortAscendingRemote = true;
        } else {
            sortTypeLocal = SORTTYPE_EXT;
            sortAscendingLocal = true;
        }
        ftpcmd_changeDir(ftpProtocol.getCurrentDir(popupMenuIsRemote), popupMenuIsRemote);
    }

    void menuItemSortBySizeAsc_actionPerformed(ActionEvent e) {
        if (popupMenuIsRemote) {
            sortTypeRemote = SORTTYPE_SIZE;
            sortAscendingRemote = true;
        } else {
            sortTypeLocal = SORTTYPE_SIZE;
            sortAscendingLocal = true;
        }
        ftpcmd_changeDir(ftpProtocol.getCurrentDir(popupMenuIsRemote), popupMenuIsRemote);
    }

    void menuItemSortByDateAsc_actionPerformed(ActionEvent e) {
        if (popupMenuIsRemote) {
            sortTypeRemote = SORTTYPE_DATE;
            sortAscendingRemote = true;
        } else {
            sortTypeLocal = SORTTYPE_DATE;
            sortAscendingLocal = true;
        }
        ftpcmd_changeDir(ftpProtocol.getCurrentDir(popupMenuIsRemote), popupMenuIsRemote);
    }

    //void menuItemSortNone_actionPerformed(ActionEvent e)
    //{
    //sortType = SORTTYPE_NONE;
    //sortAscending = true;
    //ftpcmd_changeDir( ftpProtocol.getCurrentDir(popupMenuIsRemote), popupMenuIsRemote);
    //}

    void menuItemSortByNameDes_actionPerformed(ActionEvent e) {
        if (popupMenuIsRemote) {
            sortTypeRemote = SORTTYPE_NAME;
            sortAscendingRemote = false;
        } else {
            sortTypeLocal = SORTTYPE_NAME;
            sortAscendingLocal = false;
        }
        ftpcmd_changeDir(ftpProtocol.getCurrentDir(popupMenuIsRemote), popupMenuIsRemote);
    }

    void menuItemSortByExtDes_actionPerformed(ActionEvent ignored) {
        if (popupMenuIsRemote) {
            sortTypeRemote = SORTTYPE_EXT;
            sortAscendingRemote = false;
        } else {
            sortTypeLocal = SORTTYPE_EXT;
            sortAscendingLocal = false;
        }
        ftpcmd_changeDir(ftpProtocol.getCurrentDir(popupMenuIsRemote), popupMenuIsRemote);
    }

    void menuItemSortBySizeDes_actionPerformed(ActionEvent ignored) {
        if (popupMenuIsRemote) {
            sortTypeRemote = SORTTYPE_SIZE;
            sortAscendingRemote = false;
        } else {
            sortTypeLocal = SORTTYPE_SIZE;
            sortAscendingLocal = false;
        }
        ftpcmd_changeDir(ftpProtocol.getCurrentDir(popupMenuIsRemote), popupMenuIsRemote);
    }

    void menuItemSortByDateDes_actionPerformed(ActionEvent ignored) {
        if (popupMenuIsRemote) {
            sortTypeRemote = SORTTYPE_DATE;
            sortAscendingRemote = false;
        } else {
            sortTypeLocal = SORTTYPE_DATE;
            sortAscendingLocal = false;
        }
        ftpcmd_changeDir(ftpProtocol.getCurrentDir(popupMenuIsRemote), popupMenuIsRemote);
    }

    private TerminalSender terminalSender;

    public void setTerminalSender(TerminalSender sender) {
        terminalSender = sender;
    }

    public TerminalSender getTerminalSender() {
        return terminalSender;
    }

    private boolean dirTreeRemoteCtrlPressed;
    private boolean dirTreeRemoteShiftPressed;
    private boolean dirTreeLocalCtrlPressed;
    private boolean dirTreeLocalShiftPressed;
    private boolean fileTreeRemoteCtrlPressed;
    private boolean fileTreeRemoteShiftPressed;
    private boolean fileTreeLocalCtrlPressed;
    private boolean fileTreeLocalShiftPressed;

    void dirTreeRemote_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
            dirTreeRemoteCtrlPressed = true;
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT)
            dirTreeRemoteShiftPressed = true;
    }

    void dirTreeRemote_keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
            dirTreeRemoteCtrlPressed = false;
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT)
            dirTreeRemoteShiftPressed = false;
    }

    void dirTreeLocal_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
            dirTreeLocalCtrlPressed = true;
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT)
            dirTreeLocalShiftPressed = true;
    }

    void dirTreeLocal_keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
            dirTreeLocalCtrlPressed = false;
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT)
            dirTreeLocalShiftPressed = false;
    }

    void fileTreeRemote_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
            fileTreeRemoteCtrlPressed = true;
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT)
            fileTreeRemoteShiftPressed = true;
    }

    void fileTreeRemote_keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
            fileTreeRemoteCtrlPressed = false;
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT)
            fileTreeRemoteShiftPressed = false;
    }

    void fileTreeLocal_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
            fileTreeLocalCtrlPressed = true;
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT)
            fileTreeLocalShiftPressed = true;
    }

    void fileTreeLocal_keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
            fileTreeLocalCtrlPressed = false;
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT)
            fileTreeLocalShiftPressed = false;
    }

    class KeepAliveThread extends Thread {
        KeepAliveThread() {
        }

        public void run() {
            for (; ; ) {
                if (keepAliveCountDown < 0) {
                    resetKeepAliveCountDown();
                    if (isConnected()
                        && ftpProtocol != null) {
                        ftpProtocol.noop();
                        //System.out.println("Sending NOOP");
                    }
                }
                keepAliveCountDown--;
                try {
                    sleep(60000);// one minute
                } catch (Exception e) {
                }
            }
        }
    }
}


class PanelFtpRemote_btnSelect_actionAdapter implements ActionListener {
    PanelFtpRemote adaptee;

    PanelFtpRemote_btnSelect_actionAdapter(PanelFtpRemote adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.btnSelect_actionPerformed(e);
    }
}

class PanelFtpRemote_cancel_actionAdapter implements ActionListener {
    PanelFtpRemote adaptee;

    PanelFtpRemote_cancel_actionAdapter(PanelFtpRemote adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.cancel_actionPerformed(e);
    }
}

class PanelFtpRemote_dirTreeRemote_PVTreeActionAdapter implements pvTreeJ.PVTreeActionListener {
    PanelFtpRemote adaptee;


    PanelFtpRemote_dirTreeRemote_PVTreeActionAdapter(PanelFtpRemote adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(PVTreeActionEvent e) {
        adaptee.dirTreeRemote_actionPerformed(e);
    }
}

class PanelFtpRemote_fileTreeRemote_PVTreeActionAdapter implements pvTreeJ.PVTreeActionListener {
    PanelFtpRemote adaptee;


    PanelFtpRemote_fileTreeRemote_PVTreeActionAdapter(PanelFtpRemote adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(PVTreeActionEvent e) {
        adaptee.fileTreeRemote_actionPerformed(e);
    }
}

class PanelFtpRemote_dirTreeLocal_PVTreeActionAdapter implements pvTreeJ.PVTreeActionListener {
    PanelFtpRemote adaptee;


    PanelFtpRemote_dirTreeLocal_PVTreeActionAdapter(PanelFtpRemote adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(PVTreeActionEvent e) {
        adaptee.dirTreeLocal_actionPerformed(e);
    }
}

class PanelFtpRemote_fileTreeLocal_PVTreeActionAdapter implements pvTreeJ.PVTreeActionListener {
    PanelFtpRemote adaptee;


    PanelFtpRemote_fileTreeLocal_PVTreeActionAdapter(PanelFtpRemote adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(PVTreeActionEvent e) {
        adaptee.fileTreeLocal_actionPerformed(e);
    }
}

class FileLineInfo {
    public int FILE_LINE_FILENAME_LEN = 32;
    public int FILE_LINE_FILESIZE_LEN = 10;
    public int FILE_LINE_FILEDATE_LEN = 14;
    public int FILE_LINE_FILETIME_LEN = 14;
}

class ConnectDialogChangeListener implements ActionListener {
    PromptDialog dlg;
    PalTerm palTerm;
    PanelFtpRemote panel;

    public ConnectDialogChangeListener(PalTerm applet, PanelFtpRemote panelFtpRemote) {
        palTerm = applet;
        panel = panelFtpRemote;
    }

    public void setConnectDialog(PromptDialog dialog) {
        dlg = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (palTerm != null
            && dlg != null
            && e.getModifiers() == 0)        // Modifier contains the field ID --- host id changed
        {
            FtpConnectInfo ftpConnectInfo = null;
            Hashtable hashTable = palTerm.getFtpConnectInfoTable();

            String h = e.getActionCommand();
            if (h != null
                && hashTable != null)
                ftpConnectInfo = (FtpConnectInfo) hashTable.get(h);

            if (ftpConnectInfo != null) {
                dlg.setTextField(PanelFtpRemote.CONNECT_DLG_FLDNO_HOST,
                    (ftpConnectInfo != null) ? ftpConnectInfo.getHost() : panel.getRemoteHost());
                dlg.setTextField(PanelFtpRemote.CONNECT_DLG_FLDNO_USERID,
                    (ftpConnectInfo != null) ? ftpConnectInfo.getUserId() : panel.getUserId());
                dlg.setTextField(PanelFtpRemote.CONNECT_DLG_FLDNO_PASSWD,
                    (ftpConnectInfo != null) ? ftpConnectInfo.getPasswd() : panel.getPasswd());
                dlg.setTextField(PanelFtpRemote.CONNECT_DLG_FLDNO_PROXY_HOST,
                    (ftpConnectInfo != null) ? ftpConnectInfo.getFtpProxyHost() : panel.getProxyHost());
                dlg.setTextField(PanelFtpRemote.CONNECT_DLG_FLDNO_PROXY_PORT,
                    (ftpConnectInfo != null) ? ftpConnectInfo.getFtpProxyPort() : panel.getProxyPort());
                dlg.setTextField(PanelFtpRemote.CONNECT_DLG_FLDNO_ACCT,
                    (ftpConnectInfo != null) ? ftpConnectInfo.getFtpAcct() : panel.getAcct());
            }
        }
    }

    public void setPromptDialog(PromptDialog promptDialog) {
        dlg = promptDialog;
    }
}
