
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.awt.*;

import com.prasad.util.GridBagConstraints2;

import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.Hashtable;

public class FrameBackImg extends Frame {
    Panel panelMiddle = new Panel();
    Panel panelBottom = new Panel();
    Panel panelTop = new Panel();
    Button btnPreview = new Button();
    Button btnCancel = new Button();
    Button btnOk = new Button();
    FlowLayout flowLayout1 = new FlowLayout();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    List listCmds = new List();
    List listImgFiles = new List();
    Label lblCmd = new Label();
    Label lblImgFile = new Label();
    TextField txtCmd = new TextField();
    GridBagLayout gridBagLayout2 = new GridBagLayout();
    Label lblCmd2 = new Label();
    Label lblImgFile2 = new Label();
    TextField txtImgFile = new TextField();
    Button btnBrowseImg = new Button();

    private PalTerm palTerm;
    private Component previewPanel;
    private Frame previewFrame;
    private FileDialogRemote fileDialogRemote;
    private Hashtable<String, String> backgroundImageURLs;


    public FrameBackImg(PalTerm palTerm) {
        this.palTerm = palTerm;

        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPreviewPanel(Component comp) {
        previewPanel = comp;
    }

    public void setBackgroundImageURLs(Hashtable<String, String> h) {
        backgroundImageURLs = h;
        listCmds.removeAll();
        listImgFiles.removeAll();
        if (h == null) {
            return;
        }
        for (String key : backgroundImageURLs.keySet()) {
            String val = backgroundImageURLs.get(key);
            if (key != null
                && key.trim().length() > 0
                && val != null
                && val.trim().length() > 0) {
                listCmds.add(key, 0);
                listImgFiles.add(val, 0);
            }
        }
    }

    public static void main(String[] args) {
        PalTerm palTerm = new PalTerm();
        palTerm.useLocalImages = false;
        palTerm.isStandalone = true;
        palTerm.localDirectory = "E:\\temp\\testterm\\";
        palTerm.protocol = "http";
        palTerm.telnetHost = "prasad-assoc.com";
        palTerm.serverHost = "www.prasad-assoc.com";
        palTerm.serverPort = -1;
        palTerm.docBaseDir = "/com/prasad/terminal/";
        palTerm.cgiDir = "/cgi/";
        palTerm.cgiExtension = "";
        palTerm.pgm_secdirlist = "secdirlist";
        //palTerm.useLocalImages	= true;

        UserInfo tmpUserInfo = new UserInfo();
        tmpUserInfo.setUserId("demo1");
        tmpUserInfo.setPassword("password");


        FrameBackImg frame = new FrameBackImg(palTerm);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    private void jbInit() throws Exception {
        panelTop.setLayout(gridBagLayout2);
        this.setSize(new Dimension(457, 324));
        this.setTitle("Background Images");
        btnPreview.setLabel("Preview");
        btnPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPreview_actionPerformed(e);
            }
        });
        flowLayout1.setVgap(15);
        listImgFiles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                listImgFiles_keyPressed(e);
            }
        });
        listCmds.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                listCmds_keyPressed(e);
            }
        });
        listImgFiles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                listImgFiles_itemStateChanged(e);
            }
        });
        lblCmd.setText("Command");
        lblImgFile.setText("Image File");
        lblImgFile2.setAlignment(2);
        lblImgFile2.setText("Image File");
        txtImgFile.setColumns(32);
        txtImgFile.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(TextEvent e) {
                txtImgFile_textValueChanged(e);
            }
        });
        btnBrowseImg.setLabel("Browse");
        btnBrowseImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnBrowseImg_actionPerformed(e);
            }
        });
        btnDelete.setLabel("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDelete_actionPerformed(e);
            }
        });
        btnAdd.setLabel("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAdd_actionPerformed(e);
            }
        });
        txtImgFile.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(FocusEvent e) {
                txtImgFile_focusLost(e);
            }
        });
        lblCmd2.setAlignment(2);
        lblCmd2.setText("Command:");
        txtCmd.setColumns(32);
        txtCmd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(FocusEvent e) {
                txtCmd_focusLost(e);
            }
        });
        listCmds.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                listCmds_itemStateChanged(e);
            }
        });
        flowLayout1.setHgap(15);
        btnCancel.setLabel("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCancel_actionPerformed(e);
            }
        });
        panelBottom.setLayout(flowLayout1);
        panelMiddle.setLayout(gridBagLayout1);
        btnOk.setLabel("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnOk_actionPerformed(e);
            }
        });
        this.add(panelMiddle, BorderLayout.CENTER);
        panelMiddle.add(listCmds, new GridBagConstraints2(0, 1, 1, 1, 1.0, 1.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panelMiddle.add(listImgFiles, new GridBagConstraints2(1, 1, 1, 1, 4.0, 1.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        panelMiddle.add(lblCmd, new GridBagConstraints2(0, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelMiddle.add(lblImgFile, new GridBagConstraints2(1, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        this.add(panelBottom, BorderLayout.SOUTH);
        panelBottom.add(btnOk, null);
        panelBottom.add(btnCancel, null);
        this.add(panelTop, BorderLayout.NORTH);
        panelTop.add(txtCmd, new GridBagConstraints2(1, 0, 3, 1, 0.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));
        panelTop.add(lblCmd2, new GridBagConstraints2(0, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelTop.add(lblImgFile2, new GridBagConstraints2(0, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelTop.add(txtImgFile, new GridBagConstraints2(1, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panelTop.add(btnBrowseImg, new GridBagConstraints2(2, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 5, 0, 5), 0, 0));
        panelTop.add(btnDelete, new GridBagConstraints2(3, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 5, 0, 5), 0, 0));
        panelTop.add(btnAdd, new GridBagConstraints2(2, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 5, 0, 5), 0, 0));
        panelTop.add(btnPreview, new GridBagConstraints2(3, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }

    private int selectedIdx = -1;
    Button btnDelete = new Button();
    Button btnAdd = new Button();

    void listCmds_itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == e.SELECTED)
            listItemSelected(listCmds, listImgFiles);
        else
            listItemDeSelected(listCmds, listImgFiles);
    }

    void listImgFiles_itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == e.SELECTED)
            listItemSelected(listImgFiles, listCmds);
        else
            listItemDeSelected(listImgFiles, listCmds);
    }

    private void listItemSelected(List thisList, List otherList) {
        int otherIdx = otherList.getSelectedIndex();
        int idx = thisList.getSelectedIndex();

        if (idx >= 0) {
            selectedIdx = idx;
            if (otherIdx != selectedIdx) {
                otherList.select(selectedIdx);
            }
            txtCmd.setText(listCmds.getItem(selectedIdx));
            txtImgFile.setText(listImgFiles.getItem(selectedIdx));
        } else {
            int[] idxs = thisList.getSelectedIndexes();
            if (idxs != null) {
                int[] idxs2 = otherList.getSelectedIndexes();
                if (idxs2 != null) {
                    for (int i = 0; i < idxs.length; i++) {
                        boolean found = false;
                        for (int j = 0; j < idxs2.length; j++) {
                            if (idxs2[j] == idxs[i]) {
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                            otherList.select(idxs[i]);
                    }
                }
            }
        }

    }

    private void listItemDeSelected(List thisList, List otherList) {
        int thisIdx = thisList.getSelectedIndex();

        if (thisIdx >= 0) {
            listCmds.deselect(thisIdx);
            listImgFiles.deselect(thisIdx);
        } else {
            int[] idxs = thisList.getSelectedIndexes();
            if (idxs != null) {
                int[] idxs2 = otherList.getSelectedIndexes();
                if (idxs2 != null) {
                    for (int i = 0; i < idxs2.length; i++) {
                        boolean found = false;
                        for (int j = 0; j < idxs.length; j++) {
                            if (idxs[j] == idxs2[i]) {
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                            otherList.deselect(idxs2[i]);
                    }
                }
            }
        }
    }

    void listCmds_keyPressed(KeyEvent e) {
        // process delete and insert keys
        int keyCode = e.getKeyCode();
        int idx;
        int[] idxs;
        switch (keyCode) {
            case KeyEvent.VK_DELETE:
                idxs = listCmds.getSelectedIndexes();
                if (idxs != null) {
                    for (int i = idxs.length - 1; i >= 0; i--) {
                        if (idxs[i] >= 0) {
                            listCmds.delItem(idxs[i]);
                            listImgFiles.delItem(idxs[i]);
                        }
                    }
                }

                break;

            case KeyEvent.VK_INSERT:
                idx = selectedIdx;
                if (idx < 0)
                    idx = 0;
                listCmds.addItem("newCmd", idx);
                listImgFiles.addItem("imageFile.gif", idx);
                break;
        }
    }

    void listImgFiles_keyPressed(KeyEvent e) {
        // process delete and insert keys
        listCmds_keyPressed(e);
    }

    void txtCmd_focusLost(FocusEvent e) {
        // add the changes
        int idx = selectedIdx;
        if (idx < 0)
            return;
        String cmd = txtCmd.getText();
        String imgFile = txtImgFile.getText();
        if (cmd == null
            || cmd.trim().length() == 0)
            cmd = "newCmd";
        if (imgFile == null
            || imgFile.trim().length() == 0)
            imgFile = "imgFile.gif";


        if (listCmds.isSelected(idx))
            listCmds.deselect(idx);
        if (listImgFiles.isSelected(idx))
            listImgFiles.deselect(idx);
        if (listCmds.countItems() > idx)
            listCmds.replaceItem(cmd.trim(), idx);
        else
            listCmds.add(cmd.trim());
        if (listImgFiles.countItems() > idx)
            listImgFiles.replaceItem(imgFile.trim(), idx);
        else
            listImgFiles.add(imgFile.trim());
    }

    void txtImgFile_focusLost(FocusEvent e) {
        // add the changes
        txtCmd_focusLost(e);
    }

    void btnAdd_actionPerformed(ActionEvent e) {
        String cmd = txtCmd.getText();
        String imgFile = txtImgFile.getText();
        if (cmd == null
            || cmd.trim().length() == 0)
            cmd = "newCmd";
        if (imgFile == null
            || imgFile.trim().length() == 0)
            imgFile = "imgFile.gif";
        if (selectedIdx < 0)
            selectedIdx = 0;
        listCmds.add(cmd.trim(), selectedIdx);
        listImgFiles.add(imgFile.trim(), selectedIdx);
    }

    void btnDelete_actionPerformed(ActionEvent e) {
        if (selectedIdx >= 0
            && listCmds.countItems() > selectedIdx
            && listImgFiles.countItems() > selectedIdx) {
            listCmds.delItem(selectedIdx);
            listImgFiles.delItem(selectedIdx);
        }

    }

    void btnBrowseImg_actionPerformed(ActionEvent e) {
        // show either local or remote directory - depending on what is set
        if (palTerm.useLocalImages) {
            FileDialog fileDialogLocal = PanelTerminal.createFileDialogLocal(palTerm.isNAV, palTerm.isIE, this, false, "Select Image File", getDebug());
            if (fileDialogLocal == null)
                return;

            // Read directory to get updated file list
            String imgDir = palTerm.localDirectory + "backimgs";
            {
                File f = new File(imgDir);
                f.mkdirs();
            }
            fileDialogLocal.setDirectory(imgDir);

            String imgFile = txtImgFile.getText();
            if (imgFile != null
                && imgFile.trim().length() > 0)
                fileDialogLocal.setFile(imgFile.trim());
            fileDialogLocal.setTitle("Select Image File");
            fileDialogLocal.setLocation(getLocation().x +
                    getSize().width / 2,
                getLocation().y +
                    getSize().height / 2);
            fileDialogLocal.pack();
            fileDialogLocal.show();

            String file = fileDialogLocal.getFile();
            if (file != null && file.length() > 0) {
                txtImgFile.setText(file);
            }
            fileDialogLocal.dispose();
        } else {
            if (fileDialogRemote == null) {
                fileDialogRemote = PanelTerminal.createFileDialogRemote(palTerm, this, getDebug());
                fileDialogRemote.setTitle("Select Image File");
                fileDialogRemote.setLocation(getLocation().x +
                        getSize().width / 2,
                    getLocation().y +
                        getSize().height / 2);
                fileDialogRemote.pack();
            }
            // Read directory to get updated file list
            String imgDir = palTerm.docBaseDir + "backimgs";
            if (!fileDialogRemote.readDirectory(imgDir, null)) {
                System.out.println("Error reading images directory [" + imgDir + "].");
                return;
            }

            // Load a new options file from the server
            String imgFile = txtImgFile.getText();
            if (imgFile != null
                && imgFile.trim().length() > 0)
                fileDialogRemote.setFileName(imgFile);
            fileDialogRemote.show();

            String file = fileDialogRemote.getFileName();
            if (file != null && file.length() > 0) {
                txtImgFile.setText(file);
            }
        }
        // now return focus to the image field
        txtImgFile.requestFocus();
    }

    private boolean debug;

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean b) {
        debug = b;
    }

    void txtImgFile_textValueChanged(TextEvent e) {
        btnPreview_actionPerformed(null);
    }

    void btnPreview_actionPerformed(ActionEvent e) {
        String imgFile = txtImgFile.getText();
        if (imgFile == null
            || imgFile.trim().length() == 0)
            return;    // nothing to preview

        Image img = palTerm.loadImage("backimgs", imgFile, this, true);
        if (img == null)
            return;

        Image backgroundImage;

        if (previewPanel == null) {
            previewFrame = new Frame();
            previewFrame.setLayout(new BorderLayout());
            previewPanel = new PreviewPanel();
            previewFrame.add(previewPanel, BorderLayout.CENTER);
            previewFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    try {
                        Frame f = (Frame) e.getSource();
                        f.setVisible(false);
                    } catch (Exception e2) {
                    }
                }
            });
            Rectangle rect = getBounds();
            rect.x += rect.width;
            previewFrame.setBounds(rect);
        }
        if (previewFrame != null)
            previewFrame.setVisible(true);

        backgroundImage = createImage(previewPanel.getSize().width, previewPanel.getSize().height);
        Graphics g = backgroundImage.getGraphics();
        tileImage(g, img, previewPanel, previewPanel);
        g.dispose();

        if (previewPanel instanceof PanelTerminal) {
            PanelTerminal panelTerminal = (PanelTerminal) previewPanel;
            if (debug)
                System.out.println("Attempting to load background image [" + imgFile + "]");
            panelTerminal.backgroundImage = backgroundImage;
            panelTerminal.invalidate();
            panelTerminal.validate();
            panelTerminal.repaint();
        } else if (previewPanel instanceof PreviewPanel) {
            ((PreviewPanel) previewPanel).setUntiledImage(img);
            previewPanel.invalidate();
            previewPanel.validate();
            previewPanel.repaint();
            //g = previewPanel.getGraphics();
            //g.drawImage(backgroundImage, 0, 0, (Panel)previewPanel);
            //g.dispose();
        }
    }


    void btnOk_actionPerformed(ActionEvent e) {
        String[] keys = listCmds.getItems();
        String[] vals = listImgFiles.getItems();

        if (keys != null
            && vals != null) {
            int cnt = Math.min(keys.length, vals.length);
            if (backgroundImageURLs != null) {
                backgroundImageURLs.clear();
                for (int i = 0; i < cnt; i++) {
                    if (keys[i] != null
                        && keys[i].trim().length() > 0
                        && vals[i] != null
                        && vals[i].trim().length() > 0)
                        backgroundImageURLs.put(keys[i].trim(), vals[i].trim());
                }
                if (previewPanel instanceof PanelTerminal) {
                    PanelTerminal panelTerminal = (PanelTerminal) previewPanel;
                    Frame f = panelTerminal.getParentFrame();
                    if (f != null
                        && f instanceof FrameTerminal) {
                        ((FrameTerminal) f).setOptionsDirty(true);
                    }
                }
            }
        }

        cleanup();
    }

    void btnCancel_actionPerformed(ActionEvent e) {
        cleanup();
    }

    private synchronized void cleanup() {
        if (fileDialogRemote != null)
            fileDialogRemote.dispose();
        if (previewFrame != null)
            previewFrame.dispose();
        setVisible(false);
    }

    /**
     * Fill the specified area with an image
     * <p>
     *
     * @param g      the graphics context
     * @param image  the image to tile
     * @param bounds area to fill
     **/
    static void tileImage(Graphics g, Image image, ImageObserver observer, Component comp) {
        Graphics backGC = g.create();
        int x = 0, y = 0;
        int width = comp.getSize().width, height = comp.getSize().height;

        if (image == null)
            return;

        backGC.clipRect(0, 0, width, height);
        while (y < height) {
            while (x < width) {
                backGC.drawImage(image, x, y, observer);
                int w = image.getWidth(observer);
                if (w > 0)
                    x += w;
                else
                    break;
            }
            x = 0;
            int h = image.getHeight(observer);
            if (h > 0)
                y += h;
            else
                break;
        }
        backGC.dispose();
    }

    class PreviewPanel extends Panel {
        public Image unTiledImage;

        public void setUntiledImage(Image img) {
            unTiledImage = img;
        }

        public void paint(Graphics g2) {
            super.paint(g2);
            if (unTiledImage != null) {
                Image backgroundImage = createImage(this.getSize().width, this.getSize().height);
                Graphics g = backgroundImage.getGraphics();
                tileImage(g, unTiledImage, this, this);
                g2.drawImage(backgroundImage, 0, 0, this);
                g.dispose();
            }
        }
    }


}


