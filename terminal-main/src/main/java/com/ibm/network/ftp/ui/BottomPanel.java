/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp.ui;
/*
 * This class was generated by a SmartGuide.
 * 
 */
 class BottomPanel extends java.awt.Panel {
	private static final long serialVersionUID = -2609317870984477843L;
	private java.awt.Label ivjlabConStatus = null;
    private java.awt.Label ivjlabStatus = null;
    private java.awt.Panel ivjPanel1 = null;
    //private java.awt.GridLayout ivjPanel1GridLayout = null;
    private java.awt.TextArea ivjtarStatus = null;
/**
 * Constructor
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
public BottomPanel() {
    super();
    initialize();
}
/*
 * BottomPanel constructor comment.
 * @param layout java.awt.LayoutManager
 */
public BottomPanel(java.awt.LayoutManager layout) {
    super(layout);
    initialize();
}
/*
 * This method was created by a SmartGuide.
 */
public void clearStatus( ) {
    gettarStatus().setText("");
    return;
}
/**
 * Return the labConStatus property value.
 * @return java.awt.Label
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Label getlabConStatus() {
    if (ivjlabConStatus == null) {
        try {
            ivjlabConStatus = new java.awt.Label();
            ivjlabConStatus.setName("labConStatus");
            ivjlabConStatus.setAlignment(java.awt.Label.LEFT);
            ivjlabConStatus.setText("Data connection is idle.");
            // user code begin {1}
            // user code end
        } catch (java.lang.Throwable ivjExc) {
            // user code begin {2}
            // user code end
            handleException(ivjExc);
        }
    };
    return ivjlabConStatus;
}
/**
 * Return the labStatus property value.
 * @return java.awt.Label
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Label getlabStatus() {
    if (ivjlabStatus == null) {
        try {
            ivjlabStatus = new java.awt.Label();
            ivjlabStatus.setName("labStatus");
            ivjlabStatus.setText("Status:");
            // user code begin {1}
            // user code end
        } catch (java.lang.Throwable ivjExc) {
            // user code begin {2}
            // user code end
            handleException(ivjExc);
        }
    };
    return ivjlabStatus;
}
/**
 * Return the Panel1 property value.
 * @return java.awt.Panel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Panel getPanel1() {
    if (ivjPanel1 == null) {
        try {
            ivjPanel1 = new java.awt.Panel();
            ivjPanel1.setName("Panel1");
            ivjPanel1.setLayout(getPanel1GridLayout());
            ivjPanel1.setBackground(java.awt.Color.lightGray);
            ivjPanel1.add(getlabConStatus(), getlabConStatus().getName());
            // user code begin {1}
            // user code end
        } catch (java.lang.Throwable ivjExc) {
            // user code begin {2}
            // user code end
            handleException(ivjExc);
        }
    };
    return ivjPanel1;
}
/**
 * Return the Panel1GridLayout property value.
 * @return java.awt.GridLayout
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.GridLayout getPanel1GridLayout() {
    java.awt.GridLayout ivjPanel1GridLayout = null;
    try {
        /* Create part */
        ivjPanel1GridLayout = new java.awt.GridLayout();
        ivjPanel1GridLayout.setColumns(1);
    } catch (java.lang.Throwable ivjExc) {
        handleException(ivjExc);
    };
    return ivjPanel1GridLayout;
}
/**
 * Return the tarStatus property value.
 * @return java.awt.TextArea
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.TextArea gettarStatus() {
    if (ivjtarStatus == null) {
        try {
            ivjtarStatus = new java.awt.TextArea();
            ivjtarStatus.setName("tarStatus");
            ivjtarStatus.setRows(4);
            ivjtarStatus.setColumns(80);
                        
            // user code begin {1}
            ivjtarStatus.setBackground(java.awt.Color.white);
            
            //Set this status text area as not editable
            ivjtarStatus.setEditable(false);
            ivjtarStatus.setEnabled(true);
            // user code end
        } catch (java.lang.Throwable ivjExc) {
            // user code begin {2}
            // user code end
            handleException(ivjExc);
        }
    };
    return ivjtarStatus;
}
/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(Throwable exception) {
    /* Uncomment the following lines to print uncaught exceptions to stdout */
    // System.out.println("--------- UNCAUGHT EXCEPTION ---------");
    // exception.printStackTrace(System.out);
}
/**
 * Initialize class
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
    // user code begin {1}
    // user code end
    java.awt.GridBagConstraints constraintslabStatus = new java.awt.GridBagConstraints();
    java.awt.GridBagConstraints constraintstarStatus = new java.awt.GridBagConstraints();
    java.awt.GridBagConstraints constraintsPanel1 = new java.awt.GridBagConstraints();
    setName("BottomPanel");
    setName("BottomPanel");
    setLayout(new java.awt.GridBagLayout());
    setSize(512, 133);
    constraintslabStatus.gridx = 0; constraintslabStatus.gridy = 0;
    constraintslabStatus.gridwidth = 1; constraintslabStatus.gridheight = 1;
    constraintslabStatus.anchor = java.awt.GridBagConstraints.NORTHWEST;
    constraintslabStatus.weightx = 1.0;
    constraintslabStatus.weighty = 0.0;
    constraintslabStatus.insets = new java.awt.Insets(0, 5, 0, 0);
    ((java.awt.GridBagLayout) this.getLayout()).setConstraints(getlabStatus(), constraintslabStatus);
    this.add(getlabStatus());
    constraintstarStatus.gridx = 0; constraintstarStatus.gridy = 1;
    constraintstarStatus.gridwidth = 6; constraintstarStatus.gridheight = 1;
    constraintstarStatus.fill = java.awt.GridBagConstraints.HORIZONTAL;
    constraintstarStatus.anchor = java.awt.GridBagConstraints.CENTER;
    constraintstarStatus.weightx = 1.0;
    constraintstarStatus.weighty = 0.0;
    constraintstarStatus.insets = new java.awt.Insets(0, 5, 0, 5);
    ((java.awt.GridBagLayout) this.getLayout()).setConstraints(gettarStatus(), constraintstarStatus);
    this.add(gettarStatus());
    constraintsPanel1.gridx = 0; constraintsPanel1.gridy = 2;
    constraintsPanel1.gridwidth = 1; constraintsPanel1.gridheight = 1;
    constraintsPanel1.fill = java.awt.GridBagConstraints.HORIZONTAL;
    constraintsPanel1.anchor = java.awt.GridBagConstraints.NORTHWEST;
    constraintsPanel1.weightx = 0.0;
    constraintsPanel1.weighty = 0.0;
    constraintsPanel1.insets = new java.awt.Insets(0, 5, 2, 6);
    ((java.awt.GridBagLayout) this.getLayout()).setConstraints(getPanel1(), constraintsPanel1);
    this.add(getPanel1());
    // user code begin {2}
    
    // user code end
}
/*
 * main entrypoint - starts the part when it is run as an application
 * @param args java.lang.String[]
 */
/*
public static void main(java.lang.String[] args) {
    try {
        java.awt.Frame frame;
        try {
            Class aFrameClass = Class.forName("uvm.abt.edit.TestFrame");
            frame = (java.awt.Frame)aFrameClass.newInstance();
        } catch (java.lang.Throwable ivjExc) {
            frame = new java.awt.Frame();
        }
        com.ibm.network.ftp.ui.BottomPanel aBottomPanel = new com.ibm.network.ftp.ui.BottomPanel();
        frame.add("Center", aBottomPanel);
        frame.setSize(aBottomPanel.getSize());
        frame.setVisible(true);
    } catch (Throwable exception) {
        System.err.println("Exception occurred in main() of java.awt.Panel");
    }
}
*/
    //To set the background of this panel,
    //when parent's Background property changed
    public void setBackground(java.awt.Color newColor)
    {
        super.setBackground(newColor);
        //Change the Background of each contained classes
        getPanel1().setBackground(newColor);
        
        //Change the Background of each contained labels
        getlabStatus().setBackground(newColor);
        getlabConStatus().setBackground(newColor);
    }
    /*
    * Sets the Font of the Status & Connection Status labels.
    *
    * @param    newFont java.awt.Font
    */
    public void setLabelsFont(java.awt.Font newFont)
    {
        //Set the Status & Connection Status labels' Font
        
        this.getlabConStatus().setFont(newFont);
        this.getlabStatus().setFont(newFont);
        
        return;
    }       
    /*
    * Sets the Foreground color of the Status & Connection Status labels.
    *
    * @param    newColor java.awt.Color
    */
    public void setLabelsForeground(java.awt.Color newColor)
    {
        //Set the  Status & Connection Status labels' Foreground color
        
        this.getlabConStatus().setForeground(newColor);
        this.getlabStatus().setForeground(newColor);
        
        return;
    }       
    /*
    * Sets the Background color of the Status text area.
    *
    * @param    newColor java.awt.Color
    */
    public void setStatusBackground(java.awt.Color newColor)
    {
        //Set the Status Text Area Background color
        
        this.gettarStatus().setBackground(newColor);
        
        return;
    }       
    /*
    * Sets the Font of the Status text area.
    *
    * @param    newFont java.awt.Font
    */
    public void setStatusFont(java.awt.Font newFont)
    {
        //Set the Status Text Area Font
        
        this.gettarStatus().setFont(newFont);
        
        return;
    }       
    /*
    * Sets the Foreground color of the Status text area.
    *
    * @param    newColor java.awt.Color
    */
    public void setStatusForeground(java.awt.Color newColor)
    {
        //Set the Status Text Area Foreground color
        
        this.gettarStatus().setForeground(newColor);
        
        return;
    }       
/*
 * This method was created by a SmartGuide.
 * @param label java.lang.String
 */
public void updateStatus (String label ) {
    if (label!=null)
    {
        getlabConStatus().setText(label);
    }   
    return;
}
/*
 * This method was created by a SmartGuide.
 * @param text java.lang.String
 */
public void updateStatusWindow (String text ) {
    if (text!=null)
    {
        //Append Text in Text Area
        gettarStatus().append(text);
    }   
    return;
}
}