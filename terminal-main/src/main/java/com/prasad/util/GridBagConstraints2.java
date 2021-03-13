package com.prasad.util;

import java.awt.*;


public class GridBagConstraints2 extends java.awt.GridBagConstraints {

    public GridBagConstraints2(int gridx,
                               int gridy,
                               int gridwidth,
                               int gridheight,
                               double weightx,
                               double weighty,
                               int anchor,
                               int fill,
                               java.awt.Insets insets,
                               int ipadx,
                               int ipady) {

        super();

        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        this.weightx = weightx;
        this.weighty = weighty;
        this.anchor = anchor;
        this.fill = fill;
        this.insets = insets;
        this.ipadx = ipadx;
        this.ipady = ipady;
    }
}