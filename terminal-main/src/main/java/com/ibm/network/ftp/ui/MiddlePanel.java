/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp.ui;
import java.util.Vector;
import com.ibm.network.ftp.FileInfo;
/**
 * This class was generated by a SmartGuide.
 * 
 */
 class MiddlePanel extends java.awt.Panel implements java.awt.event.ActionListener, java.awt.event.ItemListener,java.awt.event.MouseListener {
	private static final long serialVersionUID = -4631882036744521239L;
	private java.awt.Label ivjlabCurrent = null;
    private java.awt.Label ivjlabRemote = null;
    private java.awt.List ivjlisLocal = null;
    private java.awt.List ivjlisRemote = null;
    private java.awt.Panel ivjpanContainer = null;
    private java.awt.Canvas ivjpanMiddle = null;
    private java.awt.TextField ivjtexLocal = null;
    private java.awt.TextField ivjtexRemote = null;
    
    private static final boolean ON=true;
    private static final boolean OFF=false;
    
    private static final int LOCAL=0;
    private static final int REMOTE=1;
    //  For setting the File attributes in File Viewing
    private boolean size=OFF;
    private boolean date=OFF;
    private boolean time=OFF;
    
    //  For setting the View attributes in Local & Remote Viewing
    private boolean viewLocal=ON;
    private boolean viewRemote=ON;  
    
    //  For Change focus
    private int selected=REMOTE;
    
    //Directory selected color setting
    //initialized to white  
    private java.awt.Color dirSelectedColor=new java.awt.Color(255,255,255);
    
    //Directory deselected color setting
    //initialized to lightGray
    private java.awt.Color dirDeselectedColor=new java.awt.Color(192,192,192);
    // for setting the max. size of file attributes in the lists
    private static final int FILE_LENGTH=25;
    private static final int SIZE_LENGTH=15;
    private static final int DATE_LENGTH=15;
    private static final int TIME_LENGTH=15;        
    
    //For calling parent methods for changeDir,get & put 
    FTPUI myParent;
    
    //For temporary file handling
    Vector<String> files=new Vector<String>();
    Vector<FileInfo> localFiles=new Vector<FileInfo>();
    Vector<FileInfo> remoteFiles=new Vector<FileInfo>();
    
public MiddlePanel() {
    super();
    initialize();
}
/**
 * Constructor
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
public MiddlePanel(FTPUI container) {
    super();
    this.myParent=container;
    initialize();
}
/**
 * MiddlePanel constructor comment.
 * @param layout java.awt.LayoutManager
 */
public MiddlePanel(java.awt.LayoutManager layout) {
    super(layout);
}
/**
 * actionPerformed method comment.
 */
@Override
public void actionPerformed(java.awt.event.ActionEvent e) {
    Object source=e.getSource();
    
    
    String listItem=null;
    String fileName=null;
    if (source.equals(ivjlisLocal))
    {
        String item=e.getActionCommand();
        
        //Get the file name only, from the list
        if (item.length()>FILE_LENGTH)
        {
            listItem=item.substring(0,FILE_LENGTH);
        }   
        //Remove the white spaces
        listItem=new String(listItem.trim());
        if (listItem.startsWith("[") && listItem.endsWith("]"))
        {
            //Remove the [] from file Name
            fileName=listItem.substring(1,listItem.length()-1);
            //Call the parent method
            this.myParent.changeDir(fileName);
        }
        //If it's a file
        else    
        {
            //Call the parent method
            this.myParent.upload(listItem);
        }
    }   
    else if (source.equals(ivjlisRemote))
    {
        String item=e.getActionCommand();
        
        //Get the file name only, from the list
        if (item.length()>FILE_LENGTH)
        {
            listItem=item.substring(0,FILE_LENGTH);
        }   
        //Remove the white spaces
        listItem=new String(listItem.trim());
            
        if (listItem.startsWith("[") && listItem.endsWith("]"))
        {
            //Remove the [] from file Name
            fileName=listItem.substring(1,listItem.length()-1);
            //Call the parent method
            this.myParent.changeDir(fileName);
        }
        //If it's a file
        else    
        {
            //Call the parent method
            this.myParent.download(listItem);
        }
    }   
}
/**
 * This method was created by a SmartGuide.
 */
public void changeFocus( ) {
    if (selected==LOCAL)
    {
        selected=REMOTE;
        //Change the remote dir text field Background color
        gettexRemote().setBackground(dirSelectedColor);
        
        //Change the local dir text field Background color
        gettexLocal().setBackground(dirDeselectedColor);
        getlisRemote().requestFocus();
        
        this.myParent.updateSelected(true); //Return to parent
                
        //Set the Local list elements as deselected
        int[] selIndex=getlisLocal().getSelectedIndexes();
        for (int i=0;i<selIndex.length;i++)
        {
            getlisLocal().deselect(selIndex[i]);
        }   
    }   
    else
    {
        selected=LOCAL;
        
        //Change the remote dir text field Background color
        gettexLocal().setBackground(dirSelectedColor);
        
        //Change the local dir text field Background color
        gettexRemote().setBackground(dirDeselectedColor);
        
        getlisLocal().requestFocus();
        this.myParent.updateSelected(false); //Return to parent
        
        //Set the remote list elements as deselected
        int[] selIndex=getlisRemote().getSelectedIndexes();
        for (int i=0;i<selIndex.length;i++)
        {
            getlisRemote().deselect(selIndex[i]);
        }   
    }   
    return;
}
/**
 * Return the labCurrent property value.
 * @return java.awt.Label
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Label getlabCurrent() {
    if (ivjlabCurrent == null) {
        try {
            ivjlabCurrent = new java.awt.Label();
            ivjlabCurrent.setName("labCurrent");
            ivjlabCurrent.setText("Local directory: ");
            // user code begin {1}
            // user code end
        } catch (java.lang.Throwable ivjExc) {
            // user code begin {2}
            // user code end
            handleException(ivjExc);
        }
    };
    return ivjlabCurrent;
}
/**
 * Return the labRemote property value.
 * @return java.awt.Label
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Label getlabRemote() {
    if (ivjlabRemote == null) {
        try {
            ivjlabRemote = new java.awt.Label();
            ivjlabRemote.setName("labRemote");
            ivjlabRemote.setAlignment(java.awt.Label.CENTER);
            ivjlabRemote.setText("Remote directory:");
            // user code begin {1}
            // user code end
        } catch (java.lang.Throwable ivjExc) {
            // user code begin {2}
            // user code end
            handleException(ivjExc);
        }
    };
    return ivjlabRemote;
}
/**
 * Return the lisLocal property value.
 * @return java.awt.List
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.List getlisLocal() {
    if (ivjlisLocal == null) {
        try {
            ivjlisLocal = new java.awt.List(10);
            ivjlisLocal.setName("lisLocal");
            ivjlisLocal.setBackground(java.awt.Color.white);
            ivjlisLocal.setMultipleMode(true);
            // user code begin {1}
            
            //Set the proportional font for file listing
            ivjlisLocal.setFont(new java.awt.Font("Courier",java.awt.Font.PLAIN,12));
            
            // user code end
        } catch (java.lang.Throwable ivjExc) {
            // user code begin {2}
            // user code end
            handleException(ivjExc);
        }
    };
    return ivjlisLocal;
}
/**
 * Return the lisRemote property value.
 * @return java.awt.List
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.List getlisRemote() {
    if (ivjlisRemote == null) {
        try {
            ivjlisRemote = new java.awt.List(10);
            ivjlisRemote.setName("lisRemote");
            ivjlisRemote.setBackground(java.awt.Color.white);
            ivjlisRemote.setMultipleMode(true);
            // user code begin {1}
            
            //Set the proportional font for file listing
            ivjlisRemote.setFont(new java.awt.Font("Courier",java.awt.Font.PLAIN,12));
            // user code end
        } catch (java.lang.Throwable ivjExc) {
            // user code begin {2}
            // user code end
            handleException(ivjExc);
        }
    };
    return ivjlisRemote;
}
/**
 * This method was created by a SmartGuide.
 * @return java.lang.String
 * @param fi FTPUI.BottomPanel
 */
 
 // This method is for getting each list element from
 //the FileInfo object and the FileView settings
 
public String getListElement(FileInfo fi) {
    
    int position=FILE_LENGTH;
    //Get the file name
    String file=fi.getName();
    
    //Create a StringBuffer of FILE_LENGTH to display in list
    StringBuffer sbFile=new StringBuffer("                         ");
    
    //If the file is a directory ,show it inside []
    
    if (fi.isFile()==false)//File is a directory
    {
        file="["+file+"]";
    }   
    
    sbFile.insert(0,file); //Append file name
    
    //Check the File View Settings
    if (this.size==ON)
    {
        //Create a StringBuffer of SIZE_LENGTH to display in list
        StringBuffer sbSize=new StringBuffer("               ");
        
        //Get the file size
        String size=fi.getSize(); //Changed from int to String
        
        sbSize.insert(0,size);
                        
        //Set the new length for the String buffer
        sbFile.setLength(position+SIZE_LENGTH);
        //Insert file size
        sbFile.insert(position,sbSize.toString()); //Append to Main String buffer
        
        //Set the length of the whole string
        sbFile.setLength(position+SIZE_LENGTH);
        //Set the position for further inserting
        position=position+SIZE_LENGTH;
    }   
    if (this.date==ON)
    {
        
        //Create a StringBuffer of DATE_LENGTH to display in list
        StringBuffer sbDate=new StringBuffer("               ");
        
        //Get the file size
        String date=fi.getDate();
        
        sbDate.insert(0,date);
                        
        //Set the new length for the String buffer
        sbFile.setLength(position+DATE_LENGTH);
        //Insert file date
        sbFile.insert(position,sbDate.toString()); //Append to Main String buffer
        //Set the length of the whole string
        sbFile.setLength(position+DATE_LENGTH);
        //Set the position for further inserting
        position=position+DATE_LENGTH;
    }   
    if (this.time==ON)
    {
        
        //Create a StringBuffer of TIME_LENGTH to display in list
        StringBuffer sbTime=new StringBuffer("               ");
        
        //Get the file size
        String time=fi.getTime(); //Changed from int to String
        
        sbTime.insert(0,time);
                        
        //Set the new length for the String buffer
        sbFile.setLength(position+TIME_LENGTH);
        //Insert file time
        sbFile.insert(position,sbTime.toString()); //Append to Main String buffer
        //Set the length of the whole string        
        sbFile.setLength(position+TIME_LENGTH);
        //Set the position for further inserting
        position=position+TIME_LENGTH;
    }   
        
    String result=new String(sbFile);
        
    return result;
}
/**
 * This method was created by a SmartGuide.
 * @return java.lang.String
 */
public String getLocalCurrentDir( ) {
    return gettexLocal().getText();
}
/**
 * This method was created by a SmartGuide.
 * @return Vector
 */
public Vector<String> getLocalSelectedFiles() {
    /*
    //Get selected items from Local list
    String items[]=getlisLocal().getSelectedItems();
    String fileName;
    
    //clear the files Vector
    files.removeAllElements();
    
    for (int i=0;i<items.length;i++)
    {
        //Get the file name only,max. length of 21 chars.
        fileName=items[i].substring(0,FILE_LENGTH).trim();
        //Add to the files Vector
        files.addElement(fileName);
    }   
    return files; //Vectot of file names
    */
    /*************Changed in 1.06*******************/
    
    //Array of integer to store the selected indexes
    int [] selectedIndexes=getlisLocal().getSelectedIndexes();
    //temp string to store the selected filename
    String fileName;
    //temp FileInfo;
    FileInfo fileInfo=null;
    //remove all the elements from the temp vector;
    files.removeAllElements();  
    for(int i=0;i<selectedIndexes.length;i++){
        fileInfo=(FileInfo)localFiles.elementAt(selectedIndexes[i]);
        fileName=fileInfo.getName();
        if(!fileInfo.isFile()){ //if the file is not regular file then add "[","]"
            fileName=new String("["+fileName+"]");
        }   
        
        files.addElement(fileName);
    }
    return files;  //return the vector of filenames 
}
/**
 * Return the panContainer property value.
 * @return java.awt.Panel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Panel getpanContainer() {
    java.awt.GridBagConstraints constraintspanMiddle = new java.awt.GridBagConstraints();
    java.awt.GridBagConstraints constraintslisLocal = new java.awt.GridBagConstraints();
    java.awt.GridBagConstraints constraintslisRemote = new java.awt.GridBagConstraints();
    if (ivjpanContainer == null) {
        try {
            ivjpanContainer = new java.awt.Panel();
            ivjpanContainer.setName("panContainer");
            ivjpanContainer.setLayout(new java.awt.GridBagLayout());
            ivjpanContainer.setBackground(java.awt.Color.lightGray);
            constraintspanMiddle.gridx = 50; constraintspanMiddle.gridy = 0;
            constraintspanMiddle.gridwidth = 1; constraintspanMiddle.gridheight = 1;
            constraintspanMiddle.fill = java.awt.GridBagConstraints.VERTICAL;
            constraintspanMiddle.anchor = java.awt.GridBagConstraints.NORTHWEST;
            constraintspanMiddle.weightx = 0.0;
            constraintspanMiddle.weighty = 0.0;
            constraintspanMiddle.insets = new java.awt.Insets(3,0,3, 0);
            ((java.awt.GridBagLayout) getpanContainer().getLayout()).setConstraints(getpanMiddle(), constraintspanMiddle);
            getpanContainer().add(getpanMiddle());
            constraintslisLocal.gridx = 0; constraintslisLocal.gridy = 0;
            constraintslisLocal.gridwidth = 50; constraintslisLocal.gridheight = 1;
            constraintslisLocal.fill = java.awt.GridBagConstraints.BOTH;
            constraintslisLocal.anchor = java.awt.GridBagConstraints.CENTER;
            constraintslisLocal.weightx = 1.0;
            constraintslisLocal.weighty = 1.0;
            constraintslisLocal.insets = new java.awt.Insets(3, 4, 3, 5);
            ((java.awt.GridBagLayout) getpanContainer().getLayout()).setConstraints(getlisLocal(), constraintslisLocal);
            getpanContainer().add(getlisLocal());
            constraintslisRemote.gridx = 51; constraintslisRemote.gridy = 0;
            constraintslisRemote.gridwidth = 50; constraintslisRemote.gridheight = 1;
            constraintslisRemote.fill = java.awt.GridBagConstraints.BOTH;
            constraintslisRemote.anchor = java.awt.GridBagConstraints.CENTER;
            constraintslisRemote.weightx = 1.0;
            constraintslisRemote.weighty = 1.0;
            constraintslisRemote.insets = new java.awt.Insets(3, 1, 3, 5);
            ((java.awt.GridBagLayout) getpanContainer().getLayout()).setConstraints(getlisRemote(), constraintslisRemote);
            getpanContainer().add(getlisRemote());
            // user code begin {1}
            // user code end
        } catch (java.lang.Throwable ivjExc) {
            // user code begin {2}
            // user code end
            handleException(ivjExc);
        }
    };
    return ivjpanContainer;
}
/**
 * Return the panMiddle property value.
 * @return java.awt.Panel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Canvas getpanMiddle() {
    if (ivjpanMiddle == null) {
        try {
            ivjpanMiddle = new java.awt.Canvas();
            ivjpanMiddle.setName("panMiddle");
//          ivjpanMiddle.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,2,2));
            ivjpanMiddle.setBackground(java.awt.Color.lightGray);
            // user code begin {1}
            // user code end
        } catch (java.lang.Throwable ivjExc) {
            // user code begin {2}
            // user code end
            handleException(ivjExc);
        }
    };
    return ivjpanMiddle;
}
/**
 * This method was created by a SmartGuide.
 * @return java.lang.String
 */
public String getRemoteCurrentDir() {
    return gettexRemote().getText();
}
/**
 * This method was created by a SmartGuide.
 * @return java.lang.String[]
 */
public Vector<String> getRemoteSelectedFiles() {
    //Get selected items from Remote list
    /*String items[]=getlisRemote().getSelectedItems();
    String fileName;
    //clear the files Vector
    files.removeAllElements();
    
    for (int i=0;i<items.length;i++)
    {
        //Get the file name only,max. length of 21 chars.
        fileName=items[i].substring(0,FILE_LENGTH).trim();
        //Add to the files Vector
        files.addElement(fileName);
    }   
    return files; //Vectot of file names*/
    /*********** Added in 1.06********************/
    //Array of integer to store the selected indexes
    int [] selectedIndexes=getlisRemote().getSelectedIndexes();
    //temp string to store the selected filename
    String fileName;
    //temp FileInfo;
    FileInfo fileInfo=null;
    //remove all the elements from the temp vector;
    files.removeAllElements();  
    for(int i=0;i<selectedIndexes.length;i++){
        fileInfo=(FileInfo)remoteFiles.elementAt(selectedIndexes[i]);
        fileName=fileInfo.getName();
        if(!fileInfo.isFile()){ //if the file is not regular file then add "[","]"
            fileName=new String("["+fileName+"]");
        }   
        
        files.addElement(fileName);
    }
    return files;  //return the vector of filenames 
}
/**
 * Return the texCurrent property value.
 * @return java.awt.TextField
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.TextField gettexLocal() {
    if (ivjtexLocal == null) {
        try {
            ivjtexLocal = new java.awt.TextField();
            ivjtexLocal.setName("texLocal");
            
            ivjtexLocal.setColumns(18);
            // user code begin {1}
            
            //Initialize the background color
            //since,local is deselected, set color to lightGray
            ivjtexLocal.setBackground(java.awt.Color.lightGray);
            
    
            // user code end
        } catch (java.lang.Throwable ivjExc) {
            // user code begin {2}
            // user code end
            handleException(ivjExc);
        }
    };
    return ivjtexLocal;
}
/**
 * Return the texRemote property value.
 * @return java.awt.TextField
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.TextField gettexRemote() {
    if (ivjtexRemote == null) {
        try {
            ivjtexRemote = new java.awt.TextField();
            ivjtexRemote.setName("texRemote");
    
            ivjtexRemote.setColumns(18);
            // user code begin {1}
            
            //Initialize the background color
            //since,remote is selected, set color to white
            ivjtexRemote.setBackground(java.awt.Color.white);
            
    
            // user code end
        } catch (java.lang.Throwable ivjExc) {
            // user code begin {2}
            // user code end
            handleException(ivjExc);
        }
    };
    return ivjtexRemote;
}
/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(Throwable exception) {
    /* Uncomment the following lines to print uncaught exceptions to stdout */
    // System.out.println("--------- UNCAUGHT EXCEPTION ---------");
    //exception.printStackTrace(System.out);
}
/**
 * Initialize class
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
    // user code begin {1}
    // user code end
    java.awt.GridBagConstraints constraintslabCurrent = new java.awt.GridBagConstraints();
    java.awt.GridBagConstraints constraintstexLocal = new java.awt.GridBagConstraints();
    java.awt.GridBagConstraints constraintslabRemote = new java.awt.GridBagConstraints();
    java.awt.GridBagConstraints constraintstexRemote = new java.awt.GridBagConstraints();
    java.awt.GridBagConstraints constraintspanContainer = new java.awt.GridBagConstraints();
    setName("MiddlePanel");
    
    setLayout(new java.awt.GridBagLayout());
    setSize(520, 214);
    constraintslabCurrent.gridx = 0; constraintslabCurrent.gridy = 0;
    constraintslabCurrent.gridwidth = 1; constraintslabCurrent.gridheight = 1;
    constraintslabCurrent.anchor = java.awt.GridBagConstraints.NORTHWEST;
    constraintslabCurrent.weightx = 0.0;
    constraintslabCurrent.weighty = 0.0;
    constraintslabCurrent.insets = new java.awt.Insets(0, 5, 0, 0);
    ((java.awt.GridBagLayout) this.getLayout()).setConstraints(getlabCurrent(), constraintslabCurrent);
    this.add(getlabCurrent());
    constraintstexLocal.gridx = 1; constraintstexLocal.gridy = 0;
    constraintstexLocal.gridwidth = 1; constraintstexLocal.gridheight = 1;
    constraintstexLocal.fill = java.awt.GridBagConstraints.HORIZONTAL;
    constraintstexLocal.anchor = java.awt.GridBagConstraints.NORTHWEST;
    constraintstexLocal.weightx = 1.0;
    constraintstexLocal.weighty = 0.0;
    constraintstexLocal.insets = new java.awt.Insets(3, 0, 0, 0);
    ((java.awt.GridBagLayout) this.getLayout()).setConstraints(gettexLocal(), constraintstexLocal);
    this.add(gettexLocal());
    constraintslabRemote.gridx = 2; constraintslabRemote.gridy = 0;
    constraintslabRemote.gridwidth = 1; constraintslabRemote.gridheight = 1;
    constraintslabRemote.anchor = java.awt.GridBagConstraints.NORTHWEST;
    constraintslabRemote.weightx = 0.0;
    constraintslabRemote.weighty = 0.0;
    ((java.awt.GridBagLayout) this.getLayout()).setConstraints(getlabRemote(), constraintslabRemote);
    this.add(getlabRemote());
    constraintstexRemote.gridx = 3; constraintstexRemote.gridy = 0;
    constraintstexRemote.gridwidth = 1; constraintstexRemote.gridheight = 1;
    constraintstexRemote.fill = java.awt.GridBagConstraints.HORIZONTAL;
    constraintstexRemote.anchor = java.awt.GridBagConstraints.CENTER;
    constraintstexRemote.weightx = 1.0;
    constraintstexRemote.weighty = 0.0;
    constraintstexRemote.insets = new java.awt.Insets(3, 0, 0, 6);
    ((java.awt.GridBagLayout) this.getLayout()).setConstraints(gettexRemote(), constraintstexRemote);
    this.add(gettexRemote());
    constraintspanContainer.gridx = 0; constraintspanContainer.gridy = 1;
    constraintspanContainer.gridwidth = 4; constraintspanContainer.gridheight = 1;
    constraintspanContainer.fill = java.awt.GridBagConstraints.BOTH;
    constraintspanContainer.anchor = java.awt.GridBagConstraints.CENTER;
    constraintspanContainer.weightx = 1.0;
    constraintspanContainer.weighty = 1.0;
    ((java.awt.GridBagLayout) this.getLayout()).setConstraints(getpanContainer(), constraintspanContainer);
    this.add(getpanContainer());
    // user code begin {2}
    
    //Make the local & remote dir text field as non editable
    
    gettexLocal().setEnabled(false);
    gettexRemote().setEnabled(false);
    
    //Set focus on list
    if (selected==LOCAL)
    {
        getlisLocal().requestFocus();
        
    }
    else
    {
        getlisRemote().requestFocus();
        
    }       
    
    
    
    //Add the ActionListeners for local & remote lists
    getlisLocal().addActionListener(this);
    getlisRemote().addActionListener(this);
    
    //Add the Item Listeners for local & remote lists
    getlisLocal().addItemListener(this);
    getlisRemote().addItemListener(this);
    
    //Add the Mouse Listeners for local & remote lists
    getlisLocal().addMouseListener(this);
    getlisRemote().addMouseListener(this);
    // user code end
}
/**
 * itemStateChanged method comment.
 */
@Override
public void itemStateChanged(java.awt.event.ItemEvent e) {
    Object source=e.getSource();
    
    //If local list,set the parent's selected as false
    if (source.equals(getlisLocal()))
    {
        //Change the remote dir text field Background color
        gettexLocal().setBackground(dirSelectedColor);
        
        //Change the local dir text field Background color
        gettexRemote().setBackground(dirDeselectedColor);
        //Change this class selected property
        selected=LOCAL;
        
        //Call parent's method
        this.myParent.updateSelected(false);
        
        
        //Set the remote list elements as deselected
        int[] selIndex=getlisRemote().getSelectedIndexes();
        for (int i=0;i<selIndex.length;i++)
        {
            getlisRemote().deselect(selIndex[i]);
        }   
    }
    //If remote list,set the parent's selected as true
    else if (source.equals(getlisRemote()))
    {
        //Change the remote dir text field Background color
        gettexRemote().setBackground(dirSelectedColor);
        
        //Change the local dir text field Background color
        gettexLocal().setBackground(dirDeselectedColor);
        //Change this class selected property
        selected=REMOTE;
        
        //Call parent's method
        this.myParent.updateSelected(true);
        
        //Set the Local list elements as deselected
        int[] selIndex=getlisLocal().getSelectedIndexes();
        for (int i=0;i<selIndex.length;i++)
        {
            getlisLocal().deselect(selIndex[i]);
        }   
    }           
    return;
}
/**
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
        com.ibm.network.ftp.ui.MiddlePanel aMiddlePanel = new com.ibm.network.ftp.ui.MiddlePanel();
        frame.add("Center", aMiddlePanel);
        frame.setSize(aMiddlePanel.getSize());
        frame.setVisible(true);
    } catch (Throwable exception) {
        System.err.println("Exception occurred in main() of java.awt.Panel");
    }
}
*/
/**
 * mouseClicked method comment.
 */
 //This method is for,doing change focus, when the 
 //2 lists are empty & mouse is clicked on the list
 
@Override
public void mouseClicked(java.awt.event.MouseEvent e) {
    return;
}
/**
 * mouseEntered method comment.
 */
@Override
public void mouseEntered(java.awt.event.MouseEvent e) {
    
}
/**
 * mouseExited method comment.
 */
@Override
public void mouseExited(java.awt.event.MouseEvent e) {
}
/**
 * mousePressed method comment.
 */
@Override
public void mousePressed(java.awt.event.MouseEvent e) {
    Object source=e.getSource();
    //If local list,set the parent's selected as false
    if (source.equals(getlisLocal())) //&& (getlisLocal().getItemCount())<1)
    {
        //Change the remote dir text field Background color
        gettexLocal().setBackground(dirSelectedColor);
        
        //Change the local dir text field Background color
        gettexRemote().setBackground(dirDeselectedColor);
        //Change this class selected property
        selected=LOCAL;
        
        //Call parent's method
        this.myParent.updateSelected(false);
                
        //Set the remote list elements as deselected
        int[] selIndex=getlisRemote().getSelectedIndexes();
        for (int i=0;i<selIndex.length;i++)
        {
            getlisRemote().deselect(selIndex[i]);
        }   
        
    }
    //If remote list,set the parent's selected as true
    else if (source.equals(getlisRemote())) //&& (getlisRemote().getItemCount())<1)
    {
        //Change the remote dir text field Background color
        gettexRemote().setBackground(dirSelectedColor);
        
        //Change the local dir text field Background color
        gettexLocal().setBackground(dirDeselectedColor);
        //Change this class selected property
        selected=REMOTE;
        
        //Call parent's method
        this.myParent.updateSelected(true);
        
        //Set the Local list elements as deselected
        int[] selIndex=getlisLocal().getSelectedIndexes();
        for (int i=0;i<selIndex.length;i++)
        {
            getlisLocal().deselect(selIndex[i]);
        }   
    }           
    return;
}
/**
 * mouseReleased method comment.
 */
@Override
public void mouseReleased(java.awt.event.MouseEvent e) {
}   
/**
 * This method was created by a SmartGuide.
 */
public void refresh() {
    getpanContainer().invalidate();//To be laid out
    
    //Remove the Components
    getpanContainer().remove(getlisLocal());
    getpanContainer().remove(getpanMiddle());
    getpanContainer().remove(getlisRemote());   
    
    //If the whole window is not resizable,it is applicable
    if (viewLocal==ON)
    {
        //Copy the local files list
        Vector<FileInfo> tempFiles= new Vector<FileInfo>(localFiles);
        
        //Update local file list
        setLocalFileList(tempFiles);
                
        //Add the local list
        getpanContainer().add(getlisLocal());   
    }   
    if (viewRemote==ON)
    {
        if (viewLocal==ON)
        {
            //Add the middle panel
            getpanContainer().add(getpanMiddle());
        }       
        //Copy the remote files list
        Vector<FileInfo> tempFiles = new Vector<FileInfo>(remoteFiles);
        
        //Update remote file list
        setRemoteFileList(tempFiles);
        
        //Add the remote list
        getpanContainer().add(getlisRemote());  
    }
    getpanContainer().validate();
    return;
}
    //To set the background of this panel,
    //when parent's Background property changed
    
    @Override
    public void setBackground(java.awt.Color newColor)
    {
        super.setBackground(newColor);
        //Change the Background of each contained classes
        getpanContainer().setBackground(newColor);
        
        //Change the Background of each contained labels
        
    
        getlabCurrent().setBackground(newColor);
        getlabRemote().setBackground(newColor);
    }
    /**
    * Sets the Deselected directory, ie., either Local / Remote
    * textfield's Background.
    *
    * @param    newColor java.awt.Color
    */
    public void setDirDeselectedColor(java.awt.Color newColor)
    {
        //Set the Local dirDeselectedColor property
        
        this.dirDeselectedColor=newColor;
        
        //Change the Deselected directory's Background color
        if (this.selected==REMOTE)
        {
            //Local is Deselected
            this.gettexLocal().setBackground(newColor);
        }   
        else
        {
            //Remote is Deselected          
            this.gettexRemote().setBackground(newColor);
        }
    
        return;
    }           
    /**
    * Sets the Selected directory, ie., either Local / Remote
    * textfield's Background.
    *
    * @param    newColor java.awt.Color
    */
    public void setDirSelectedColor(java.awt.Color newColor)
    {
        //Set the Local dirSelectedColor property
        
        this.dirSelectedColor=newColor;
        
        //Change the selected directory's Background color
        if (this.selected==REMOTE)
        {
            this.gettexRemote().setBackground(newColor);
        }   
        else
        {
            this.gettexLocal().setBackground(newColor);
        }
            
        return;
    }           
    /**
    * Sets the Font of the Local & Remote directory labels.
    *
    * @param    newFont java.awt.Font
    */
    public void setLabelsFont(java.awt.Font newFont)
    {
        //Set the Local & Remote directory labels' Font
        
        this.getlabCurrent().setFont(newFont);
        this.getlabRemote().setFont(newFont);
        
        return;
    }           
    /**
    * Sets the Foreground of the Local & Remote directory labels.
    *
    * @param    newColor java.awt.Color
    */
    public void setLabelsForeground(java.awt.Color newColor)
    {
        //Set the Local & Remote directory labels' Foreground color
        
        this.getlabCurrent().setForeground(newColor);
        this.getlabRemote().setForeground(newColor);
        
        return;
    }           
    /**
    * Sets the Background of the Local & Remote lists.
    *
    * @param    newColor java.awt.Color
    */
    public void setListsBackground(java.awt.Color newColor)
    {
        //Set the Local & Remote lists' Background color
                
        this.getlisLocal().setBackground(newColor);
        this.getlisRemote().setBackground(newColor);
                        
        return;
    }           
    /**
    * Sets the Foreground of the Local & Remote lists.
    *
    * @param    newColor java.awt.Color
    */
    public void setListsForeground(java.awt.Color newColor)
    {
        //Set the Local & Remote lists' Foreground color
        
        this.getlisLocal().setForeground(newColor);
        this.getlisRemote().setForeground(newColor);
        
        return;
    }           
/**
 * This method was created by a SmartGuide.
 * @param currentDir java.lang.String
 */
public void setLocalCurrentDir (String currentDir) {
    if (currentDir!=null)
    {
        gettexLocal().setText(currentDir);
    }   
    return;
}
/**
 * This method was created by a SmartGuide.
 * @param fileList java.util.Vector
 */
public void setLocalFileList (Vector<FileInfo> fileList ) {
    
    if (fileList!=null)
    {
        //For avoiding flickering
        //java.awt.Color fore=getlisLocal().getForeground();
        //getlisLocal().setForeground(getlisLocal().getBackground());
                
        //getlisLocal().transferFocus();
        
        getlisLocal().setVisible(false);
        
        getlisLocal().removeAll();
            
        //Clear the localFiles Vector
        localFiles.removeAllElements();
    
        //Copy the fileList Vector elements for further Refresh
        localFiles=new Vector<FileInfo>(fileList);
    
        for (int i=0;i<localFiles.size();i++)
        {
            //Get each FileInfo object
            FileInfo fi=(FileInfo)localFiles.elementAt(i);
        
            String result=getListElement(fi);
            //Add the file info to the list
            getlisLocal().add(result);
        }   
        //getlisLocal().setForeground(fore); 
        getlisLocal().setVisible(true);
    }   
    return;
}
/**
 * This method was created by a SmartGuide.
 * @param currentDir java.lang.String
 */
public void setRemoteCurrentDir(String currentDir ) {
    if (currentDir!=null)
    {
        gettexRemote().setText(currentDir);
    }   
    return;
}
/**
 * This method was created by a SmartGuide.
 * @param fileList java.util.Vector
 */
public void setRemoteFileList (Vector<FileInfo> fileList ) {
    if (fileList!=null)
    {
        //For avoiding flickering
        /*java.awt.Color fore=getlisRemote().getForeground();
        getlisRemote().setForeground(getlisRemote().getBackground());
                
        getlisRemote().transferFocus(); */
        getlisRemote().setVisible(false);
        
        //Clear the Remotel files list
        getlisRemote().removeAll();
    
        //Clear the remoteFiles Vector
        remoteFiles.removeAllElements();
    
        //Copy the fileList Vector elements for further Refresh
        remoteFiles = new Vector<FileInfo>(fileList);
    
        for (FileInfo fInfo:remoteFiles){
        
            String result=getListElement(fInfo);
            //Add the file info to the list
            getlisRemote().add(result);
        }
        //getlisRemote().setForeground(fore);
        getlisRemote().setVisible(true);    
    }   
    return;
}
/**
 * This method was created by a SmartGuide.
 * @param size int
 * @param date int
 * @param time int
 */
public void setViewOptions(boolean size,boolean date,boolean time ) {
    this.size=size;
    this.date=date;
    this.time=time;
    
    //Refresh the  local & remote lists
    //refresh();
    if (localFiles!=null)
    {
        //Copy the local files list
        Vector<FileInfo> locFiles = new Vector<FileInfo>(localFiles);
        
        //Update local file list
        setLocalFileList(locFiles);
    }
    
    if (remoteFiles!=null)
    {
        //Copy the remote files list
        Vector<FileInfo> remFiles = new Vector<FileInfo>(remoteFiles);
        
        //Update remote file list
        setRemoteFileList(remFiles);
    }   
        
    return;
}
/**
 * This method was created by a SmartGuide.
 * @param local int
 * @param remote int
 */
public void setWindowOptions(boolean local,boolean remote) {
    viewLocal=local;
    viewRemote=remote;
    //Refresh the local & remote windows
    refresh();
    return;
}
}
