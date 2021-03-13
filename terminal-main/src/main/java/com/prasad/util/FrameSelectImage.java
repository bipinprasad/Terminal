package com.prasad.util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import java.io.*;
import java.util.*;

import com.prasad.util.*;

/**
 * Title:        Service Request Tracking
 * Description:
 * Copyright:    Copyright (c) Bipin Prasad
 * Company:      Chase
 *
 * @author Bipin Prasad
 * @version 1.0
 */

public class FrameSelectImage extends JFrame {
    JSplitPane jSplitPane1 = new JSplitPane();
    JScrollPane jScrollPane2 = new JScrollPane();
    JScrollPane jScrollPane1 = new JScrollPane();
    JLabel jLblImage = new JLabel();
    JTree jTree1 = new JTree();
    ImageTreeCellRenderer imageTreeCellRenderer = new ImageTreeCellRenderer();
    String selectedImagePath = null;

    DefaultMutableTreeNode rootNode;
    //java.awt.MediaTracker mediaTracker;

    public FrameSelectImage() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        jLblImage.setBorder(BorderFactory.createLoweredBevelBorder());
        jLblImage.setOpaque(true);
        jLblImage.setHorizontalTextPosition(SwingConstants.CENTER);
        jLblImage.setVerticalTextPosition(SwingConstants.TOP);
        jTree1.setAutoscrolls(true);
        jScrollPane2.setMinimumSize(new Dimension(200, 24));
        jScrollPane2.setPreferredSize(new Dimension(200, 324));
        jBtnCancel.setEnabled(false);
        jBtnCancel.setText("Cancel");
        jBtnCancel.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jBtnCancel_actionPerformed(e);
            }
        });
        jBtnOk.setEnabled(false);
        jBtnOk.setText("OK");
        jBtnOk.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jBtnOk_actionPerformed(e);
            }
        });
        this.getContentPane().add(jSplitPane1, BorderLayout.CENTER);
        jSplitPane1.add(jScrollPane2, JSplitPane.LEFT);
        jScrollPane2.getViewport().add(jTree1, null);
        jSplitPane1.add(jScrollPane1, JSplitPane.RIGHT);
        this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
        jPanel1.add(jBtnOk, null);
        jPanel1.add(jBtnCancel, null);
        jScrollPane1.getViewport().add(jLblImage, null);
        jSplitPane1.setDividerLocation(230);
        jTree1.addTreeWillExpandListener(new javax.swing.event.TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent e) throws ExpandVetoException {
                jTree1_treeWillExpand(e);
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent e) {
            }
        });
        jTree1.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                ImageLoaderIcon icon = null;
                String imagePath = "";
                try {
                    if (imageGetter != null) {
                        TreePath path = e.getPath();
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        if (!node.getAllowsChildren()) {
                            imagePath = FrameSelectImage.getNodePath(node);
                            icon = new ImageLoaderIcon(imageGetter, imagePath, jLblImage);
                            if (icon != null) {
                                selectedImagePath = imagePath;
                                jLblImage.setText(imagePath);
                                //icon.setImageObserver(jLblImage);
                                //Icon oldIcon = jLblImage.getIcon();
                                //if (oldIcon != null
                                //&&  oldIcon instanceof ImageLoaderIcon)
                                //    ((ImageLoaderIcon)oldIcon).setImageObserver(null);
                                jLblImage.setIcon(icon);
                            } else {
                                jLblImage.setIcon(null);
                                jLblImage.setText("");
                            }
                            jBtnOk.setEnabled(icon != null);
                            jBtnCancel.setEnabled(icon != null);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        // set look & feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        }

        FrameSelectImage frame = new FrameSelectImage();
        //EXIT_ON_CLOSE == 3
        frame.setImageGetter(new AppImageGetter("C:\\bipin\\src"));
        frame.setDefaultCloseOperation(3);
        frame.setTitle("Select Image");
        frame.setSize(700, 700);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - frame.getSize().width) / 2;
        int y = (d.height - frame.getSize().height) / 2;
        if (x < 0)
            x = 0;
        if (y < 0)
            y = 0;

        frame.setLocation(x, y);
        frame.init("images");
        frame.setVisible(true);
        frame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mod = e.getModifiers();
                if (mod == 0)
                    System.out.println("Cancel Pressed");
                else
                    System.out.println(e.getActionCommand());
            }
        });
    }

    ImageGetterInterface imageGetter;
    JPanel jPanel1 = new JPanel();
    JButton jBtnCancel = new JButton();
    JButton jBtnOk = new JButton();

    public void setImageGetter(ImageGetterInterface val) {
        imageGetter = val;
        if (imageTreeCellRenderer != null)
            imageTreeCellRenderer.setImageGetter(val);
    }

    public ImageGetterInterface getImageGetter() {
        return imageGetter;
    }

    public void init(String startLocation) {
        rootNode = new DefaultMutableTreeNode(startLocation, true);
        DefaultTreeModel model = new DefaultTreeModel(rootNode);
        model.setAsksAllowsChildren(true);
        jTree1.putClientProperty("JTree.lineStyle", "Angled");
        jTree1.setModel(model);
        jTree1.setCellRenderer(imageTreeCellRenderer);
        expandNode(rootNode, model);
    }

    public void selectImage(String imgPath) {
        if (imgPath == null)
            return;

        StringTokenizer st = new StringTokenizer(imgPath, "/");
        int tknCnt = 0;
        DefaultTreeModel model = null;
        DefaultMutableTreeNode parentNode = null;
        //DefaultMutableTreeNode selectedNode = null;
        //DefaultMutableTreeNode childNode = null;

        for (tknCnt = 0; st.hasMoreTokens(); tknCnt++) {
            String token = st.nextToken();
            if (tknCnt == 0) {
                // set the root
                if (rootNode != null) {
                    String s = (String) rootNode.getUserObject();
                    if (!s.equals(token))
                        init(token);
                } else
                    init(token);
                parentNode = rootNode;
                continue;
            } else {
                // set subsequent nodes
                if (model == null)
                    model = (DefaultTreeModel) jTree1.getModel();
                if (!parentNode.getAllowsChildren()) {
                    jTree1.setSelectionPath(new TreePath(model.getPathToRoot(parentNode)));
                    return;
                }
                expandNode(parentNode, model);
                int childCnt = parentNode.getChildCount();
                boolean found = false;
                for (int i = 0; i < childCnt; i++) {
                    DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) parentNode.getChildAt(i);
                    String s = (String) childNode.getUserObject();
                    if (s != null
                        && s.equals(token)) {
                        found = true;
                        if (!childNode.getAllowsChildren()) {
                            jTree1.setSelectionPath(new TreePath(model.getPathToRoot(childNode)));
                            return;
                        }
                        parentNode = childNode;
                        break;
                    }
                }
                if (!found) {
                    jTree1.setSelectionPath(new TreePath(model.getPathToRoot(parentNode)));
                    return;
                }
            }
        }
    }

    public static String getNodePath(DefaultMutableTreeNode node) {
        Object[] objs = node.getUserObjectPath();
        if (objs == null)
            return "";
        StringBuffer path = new StringBuffer(128);
        int iMax = objs.length;
        for (int i = 0; i < iMax; i++) {
            if (i > 0)
                path.append('/');
            path.append(objs[i]);
        }
        return path.toString();
    }

    public void expandNode(DefaultMutableTreeNode node, DefaultTreeModel model) {
        if (imageGetter == null
            || model == null
            || node == null
            || !node.getAllowsChildren()
            || node.getChildCount() > 0)
            return;

        String path = getNodePath(node);
        String[] dirs = imageGetter.getDirectories(path);
        String[] files = imageGetter.getFiles(path);
        int iMax = 0;
        String[] arr = null;

        arr = files;
        iMax = (arr != null) ? arr.length : 0;
        for (int i = 0; i < iMax; i++)
            model.insertNodeInto(new DefaultMutableTreeNode(arr[i], false), node, i);

        arr = dirs;
        iMax = (arr != null) ? arr.length : 0;
        for (int i = 0; i < iMax; i++)
            model.insertNodeInto(new DefaultMutableTreeNode(arr[i], true), node, i);
    }

    void jTree1_treeWillExpand(TreeExpansionEvent e) throws ExpandVetoException {
        TreePath treePath = e.getPath();
        expandNode((DefaultMutableTreeNode) e.getPath().getLastPathComponent(), (DefaultTreeModel) jTree1.getModel());
    }

    Vector listeners;

    public void addActionListener(ActionListener l) {
        if (listeners == null)
            listeners = new Vector();
        if (l != null
            && !listeners.contains(l))
            listeners.add(l);
    }

    private void fireActionEvent(boolean flag) {
        int iMax;
        if (listeners != null
            && (iMax = listeners.size()) > 0) {
            ActionEvent e = new ActionEvent(this, 0, selectedImagePath, (flag ? 1 : 0));
            ActionListener[] objs = new ActionListener[iMax];
            listeners.copyInto(objs);
            for (int i = 0; i < iMax; i++) {
                objs[i].actionPerformed(e);
            }
        }
    }

    void jBtnOk_actionPerformed(ActionEvent e) {
        fireActionEvent(true);
    }

    void jBtnCancel_actionPerformed(ActionEvent e) {
        fireActionEvent(false);

    }

}