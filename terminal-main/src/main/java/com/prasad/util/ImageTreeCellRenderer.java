package com.prasad.util;

/**
 * Title:        Service Request Tracking
 * Description:
 * Copyright:    Copyright (c) Bipin Prasad
 * Company:      Chase
 *
 * @author Bipin Prasad
 * @version 1.0
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

import com.prasad.util.*;

public class ImageTreeCellRenderer extends DefaultTreeCellRenderer implements TreeCellRenderer {
    JLabel cellLabel = new JLabel();
    ImageGetterInterface imageGetter;
    //Hashtable              h      = new Hashtable();


    public Component getTreeCellRendererComponent(
        JTree tree,
        Object value,
        boolean selected,
        boolean expanded,
        boolean leaf,
        int row,
        boolean hasFocus) {
        JComponent comp = null;

        if (value != null) {
            Object obj = null;

            try {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                if (imageGetter == null
                    || node.getAllowsChildren())
                    return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

                String imagePath = FrameSelectImage.getNodePath(node);
                ImageLoaderIcon icon = null;
                //icon      = (Icon)h.get(imagePath);
                //if (icon == null)
                {
                    try {
                        //Image img = imageGetter.getImage(imagePath).getScaledInstance(24,24,0);
                        //icon = new ImageIcon(img);
                        //if (icon != null)
                        //    h.put(imagePath, icon);
                        icon = new ImageLoaderIcon(imageGetter, imagePath, cellLabel);
                        icon.setScaledDimension(new Dimension(24, 24));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                cellLabel.setIcon(icon);
                cellLabel.setText(node.getUserObject().toString());
                cellLabel.setOpaque(selected);
                return cellLabel;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Class of Node value is [" + value.getClass().getName() + "]");
                if (obj != null)
                    System.out.println("Class of UserObject in the node is [" + obj.getClass().getName() + "]");
                else
                    System.out.println("UserObject in the node is null");
            }
        }
        return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
    }

    public void setImageGetter(ImageGetterInterface val) {
        imageGetter = val;
    }

    public ImageGetterInterface getImageGetter() {
        return imageGetter;
    }
}