/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp.ui;
/*
 * @(#)FtpUI.java	1.0 1997/10/23
 *
 * Copyright (c) 1997 IBM Corporation.
 * All Rights Reserved.
 *
 * Author: Balamurugadass Arumugam
 *
 * Last Modified: 	1997/10/23
 *
 * Purpose:
 *
 * This class is the FTP user interface bean component which can be used
 * with any FTP Protocol bean for performing the FTP opearations.
 *
 * Revision History:
 * ================
 *
 * Date			By									Description
 * ----			--									-----------
 * 1997/10/23	Balamurugadass Arumugam		Created
 * 1997/10/28	Balamurugadass Arumugam		Modified for including some more properties for the bean	
 */
 
 
import java.util.Vector;
import java.io.Serializable;
import com.ibm.network.ftp.FileInfo;
import com.ibm.network.ftp.event.*;
/**
 * This class provides user interface for the FTP protocol services.
 * All the frequently used ftp commands have been implemented by 
 * this class and provided in the form of APIs.
 * This class throws <a href="com.ibm.network.ftp.event.CommandEvent.html"> CommandEvent </a> which carries the present
 * command name,parameters and a parameter for local or 
 * remote oparation.The listener catches the <a href="com.ibm.network.ftp.event.CommandEvent.html"> CommandEvent </a>,sends the
 * <a href="com.ibm.network.ftp.event.CommandEvent.html"> CommandEvent </a> to the FTP server and returns the <a href="com.ibm.network.ftp.event.StatusEvent.html"> StatusEvent </a> for the 
 * protocol status or the <a href="com.ibm.network.ftp.event.LocalFileListEvent.html"> LocalFileListEvent </a> or the <a href="com.ibm.network.ftp.event.RemoteFileListEvent.html"> RemoteFileListEvent </a>.
 * It listens for <a href="com.ibm.network.ftp.event.StatusEvent.html"> StatusEvent </a>,for receiving the command status
 * by implementing <a href="com.ibm.network.ftp.event.StatusListener.html"> StatusListener </a> .
 * It listens for <a href="com.ibm.network.ftp.event.LocalFileListEvent.html"> LocalFileListEvent </a>for receiving the local files list
 * by implementing <a href="com.ibm.network.ftp.event.LocalFileListListener.html"> LocalFileListListener </a> .
 * It listens for <a href="com.ibm.network.ftp.event.RemoteFileListEvent.html"> RemoteFileListEvent </a>for receiving the remote files list
 * by implementing <a href="com.ibm.network.ftp.event.RemoteFileListListener.html"> RemoteFileListListener </a> .
 * It uses various dialogs for getting data from the user.
 * The details of local and remote files are stored in <a href="com.ibm.network.ftp.FileInfo.html"> FileInfo </a> 
 * class. 
 * 
 * @author 	Balamurugadass Arumugam
 * @version 1.0
 *
 * @see		com.ibm.network.ftp.FileInfo
 * @see		com.ibm.network.ftp.event.LocalFileListListener
 * @see		com.ibm.network.ftp.event.RemoteFileListListener 
 * @see		com.ibm.network.ftp.event.StatusListener
 * @see		com.ibm.network.ftp.event.CommandEvent
 */
public class FTPUI extends java.awt.Panel implements Serializable,LocalFileListListener, RemoteFileListListener, StatusListener {
	//For accessing the Status Area and status labels
	private BottomPanel ivjBottomPanel = null;
	
	//Layout of this class
	private java.awt.BorderLayout ivjFTPUIBeanBorderLayout = null;
	
	//For accessing the Directory Name and Listing
	private MiddlePanel ivjMiddlePanel = null;
	
	//For accessing the Toolbar
	private TopPanel ivjTopPanel = null;
	
	//For storing local file list
	private Vector<FileInfo> localFileList=new Vector<FileInfo>();
	
	//For storing local file list
	private Vector<FileInfo> remoteFileList=new Vector<FileInfo>();
	
	//For maintaining all the files with attributes
	private transient Vector<FileInfo> fileInfo=new Vector<FileInfo>();
	
	//Constant for storing Local window selected 
	//private static final boolean LOCAL=false;
	//Constant for storing Local window selected 	
	private static final boolean REMOTE=true;
	
	//For getting the window,which has current focus
	private boolean selected=REMOTE;
	
	
	
	//For storing all the Command Listeners
	private transient Vector commandListeners=new Vector();
	
	//For storing the entries, user entered in the EntryDialog box
	private String userEntry=null;
	
	//For storing the entries, user entered in the InfoDialog box
	private String response=null;
	
	//For passing a Frame object to the dialogs
	private transient java.awt.Frame frm;
	
	//For the protocol bean to use getter methods of
	//the Current CommandEvent object
	private CommandEvent currentCommand;
	
	//Property for providing the default Toolbar
	private boolean defaultToolbar=true;
	//File view size setting
	private boolean viewSize=false;
	
	//File view date setting
	private boolean viewDate=false;
	
	//File view time setting
	private boolean viewTime=false;
	
	//Local Window view setting
	private boolean viewLocal=true;
	
	//Remote Window view setting
	private boolean viewRemote=true;
	
	//Toolbar buttons' Font setting
	//initialized to Font - dialog,Style - PLAIN,size - 12
	private java.awt.Font toolbarFont=new java.awt.Font("dialog",java.awt.Font.PLAIN,12);
	//Toolbar buttons' Background setting
	//initialized to lightGray
	private java.awt.Color toolbarBackground=new java.awt.Color(192,192,192);
	//Toolbar buttons' Foreground setting
	//initialized to black
	private java.awt.Color toolbarForeground=new java.awt.Color(0,0,0);
	//FtpUI's labels Foreground setting
	//initialized to black
	private java.awt.Color labelsForeground=new java.awt.Color(0,0,0);
	//FtpUI's labels Font setting
	//initialized to Font - dialog,Style - PLAIN,size - 12
	private java.awt.Font labelsFont=new java.awt.Font("dialog",java.awt.Font.PLAIN,12);
	
	//File List Background setting
	//initialized to white	
	private java.awt.Color listsBackground=new java.awt.Color(255,255,255);
	//File List Foreground setting
	//initialized to black
	private java.awt.Color listsForeground=new java.awt.Color(0,0,0);
	//Status Text Area Font setting
	//initialized to Font - dialog,Style - PLAIN,size - 12
	private java.awt.Font statusFont=new java.awt.Font("dialog",java.awt.Font.PLAIN,12);
	//Status Text Area Background setting
	//initialized to white	
	private java.awt.Color statusBackground=new java.awt.Color(255,255,255);
	//Status Text Area Foreground setting
	//initialized to black
	private java.awt.Color statusForeground=new java.awt.Color(0,0,0);
	
	//Directory selected color setting
	//initialized to white	
	private java.awt.Color dirSelectedColor=new java.awt.Color(255,255,255);
	
	//Directory deselected color setting
	//initialized to lightGray
	private java.awt.Color dirDeselectedColor=new java.awt.Color(192,192,192);
	
	//Parameter for storing the connection status
	private boolean connected=false;	
/**
 * Creates an object of  FTPUI class
 */
public FTPUI() {
	super();
	initialize();
}
  /**
	* Aborts the last issued command.
	* This command will abort the previously issued command if it
	* is running.It constructs the command event and sends it
	* to the listeners.
	*
	*/
	
	public void abort()
	{
		//Create a vector for parameters
		Vector pmtrs=new Vector();
		//Construct the CommandEvent object'
		CommandEvent ce=new CommandEvent(this,"abort",pmtrs,true);
		
		//Assign the new CommandEvent object to the currentCommand object
		//so that the Protocol Bean can use the getter methods
		this.currentCommand=ce;
		
		//Send the CommandEvent object
		this.sendCommandEvent(ce);
		return;
	}	
	/**
	* Adds the specified CommandListener to receive CommandEvents from
	* this FTPUI class. 
	*
	* @param aListener CommandListener
	* @see	com.ibm.network.ftp.event.CommandListener	
	* @exception java.util.TooManyListenersException	When more than one listener are registered,this exception will be thrown.
	*/
	public synchronized void addCommandListener(CommandListener aListener) throws java.util.TooManyListenersException
	{
		commandListeners.addElement(aListener);
		
		//If the listeners are more than one
		//throw TooManyListenersException
   	if (commandListeners.size()>1)
		{
			//Construct the Exception object
			java.util.TooManyListenersException te=new java.util.TooManyListenersException("Already one listener has been connected to this FTP User Interface Bean");			
			
			//throw the Exception
			throw te;
		}	 
		return;
	}		
	/**
	* Changes the current working directory.
	* The user can enter, either the full pathname or the relative pathname
	* of the directory in the "Change Directory" dialog box.
	*
	*/
	public void changeDir()
	{
		//Set the parent frame
		this.frm=getParentFrame();
		
		//Initialize the user entry
		this.userEntry=null;
		EntryDialog ed=new EntryDialog(frm,"Change Dir Dialog",this,"Directory name");
		
		//Set the Background of the Dialog
		ed.setDialogBackground(super.getBackground());
		//Set the textfields' Background in the Dialog
		//ed.setTextBackground(this.dirSelectedColor);
		
		//Set the Dialog Buttons' Background,Foreground & Font
		ed.setButtonsBackground(this.toolbarBackground);
		ed.setButtonsForeground(this.toolbarForeground);
		
		//Set the Dialog Labels' Foreground & Font
		ed.setLabelsForeground(this.labelsForeground);
	
		//Set the location of the dialog for showing 
		//in the parent's center
		ed.setLocation(this.findCenterPoint(ed));
		
		ed.show();
		
		if (userEntry!=null && !(userEntry.equals("")))
		{
			//Create a vector for parameters
			Vector files=new Vector();
			//Add the new file name to the Vector
			files.addElement(userEntry);
			
			CommandEvent ce=new CommandEvent(this,"changeDir",files,selected);
			
			//Assign the new CommandEvent object to the currentCommand object
			//so that the Protocol Bean can use the getter methods
			this.currentCommand=ce;
			
			//Send the command
			this.sendCommandEvent(ce);
		}			
		return;
	}	
	/**
 	* Changes directory whose pathname is the selected item 
 	* in the filelist. Dialog box will not be invoked for this method.
	* 
	* @param dirName the new directory to change to.
	*/
	
	public void changeDir(String dirName)
	{
		if (dirName!=null)
		{
			//Create a vector for parameters
			Vector files=new Vector();
		
			//Add the new file name to the Vector
			files.addElement(dirName);
		
			CommandEvent ce=new CommandEvent(this,"changeDir",files,selected);
			
			//Assign the new CommandEvent object to the currentCommand object
			//so that the Protocol Bean can use the getter methods
			this.currentCommand=ce;
			
			//Send the command
			this.sendCommandEvent(ce);
		}	
		return;
	}			
/**
 * This method was created by a SmartGuide.
 * @return java.lang.String
 * @param fileName java.lang.String
 */
private String changeDirToFileName(String fileName ) {
	// Remove the initial [ and ending ]
	String newName=fileName.substring(1,fileName.length()-1);
	//System.out.println(newName);
	return newName;
}
	/**
	* Changes the focus between local and remote file list window.
	* 
	*/
	
	public void changeFocus()
	{
		//Call the MiddlePanel's changeFocus
		getMiddlePanel().changeFocus();
		return;
	}	
	/**
	* Returns latest FTP connection status.
	* This method constructs a CommandEvent for getting the status
	* and sends it to the CommandListener.
	*
	*/
	public void checkStatus()
	{
		//Create a vector for parameters
		Vector files=new Vector();
		
		files=null;
		
		//Construct the CommandEvent object'
		CommandEvent ce=new CommandEvent(this,"getStatus",files,selected);
		
		//Assign the new CommandEvent object to the currentCommand object
		//so that the Protocol Bean can use the getter methods
		this.currentCommand=ce;
	
		//Send the CommandEvent object
		this.sendCommandEvent(ce);
		return;
	}	
	/**
	* Clears the already existing status messages in the status window.
	*
	*/
	public void clearStatusWindow()
	{
		//Call the BottomPanel's methos
		getBottomPanel().clearStatus();
		return;
	}	
	/**
	* Configures socksProxyHost and socksProxyPort.
	* Socks host and Socks port will be received through "Configure Socks" dialog box.
	* The socksProxyHost and socksProxyPort are set by this method.
	* Socks host and socks port will be used by FTPProtocol for connecting to servers
	* that are outside the firewall.
	*
	*/
	public void configureSocks()
	{
		//Set the parent frame
		this.frm=getParentFrame();
		
		//Show the SocksDialog for the configuring 
		//socks server and port
		SocksDialog sd=new SocksDialog(frm,this);
		
		//Set the Background of the SocksDialog
		sd.setDialogBackground(super.getBackground());
		
		//Set the textfields' Background in the Dialog
		//sd.setTextBackground(this.dirSelectedColor);
		
		//Set the Dialog Buttons' Background,Foreground & Font
		sd.setButtonsBackground(this.toolbarBackground);
		sd.setButtonsForeground(this.toolbarForeground);
		//sd.setButtonsFont(this.toolbarFont);	
		
		//Set the Dialog Labels' Foreground & Font
		sd.setLabelsForeground(this.labelsForeground);
		
		
		//Set the location of the dialog for showing 
		//in the parent's center
		sd.setLocation(this.findCenterPoint(sd));
		
		sd.show();	
		return;
	}	
	/**
	* Connects to a host.
	* Invokes a connection dialog for getting the FTP server ip address,
	* user name, password and account.
	*
	*/
	public void connect()
	{
		//Set the parent frame
		this.frm=getParentFrame();
				
		//Create a invisible ConnectDialog Obect
		ConnectDialog cd=new ConnectDialog(frm,this);
		
		//Set the Background of the ConnectDialog
		cd.setDialogBackground(super.getBackground());
		
		//Set the textfields' Background in the Dialog
		//cd.setTextBackground(this.dirSelectedColor);
		
		//Set the Dialog Buttons' Background,Foreground & Font
		cd.setButtonsBackground(this.toolbarBackground);
		cd.setButtonsForeground(this.toolbarForeground);
		
		
		//Set the Dialog Labels' Foreground & Font
		cd.setLabelsForeground(this.labelsForeground);
		
		
		//Set the location of the dialog for showing 
		//in the parent's center
		cd.setLocation(this.findCenterPoint(cd));
		
		//Make the Dialog object visible
		cd.show();
		
		return;
	}	
	/**
	* Deletes a file.
	* File name will be received through a "Delete File" dialog box or
	* the selected file names in the file list.
	*
	*/
	public void deleteFile()
	{
		//Set the parent frame
		this.frm=getParentFrame();
		//Create a vector for parameters
		Vector files=new Vector();
		
		//If files are already selected,get from the list
		
		//Get the remote files
		if (selected==true)
		{
			files=getMiddlePanel().getRemoteSelectedFiles();
		}	
		//Get the local files 
		else
		{
			files=getMiddlePanel().getLocalSelectedFiles();
		}
	
		//If files are not selected,Show the delete dialog		
		if (files.size()<1)
		{
			//Initialize the user entry
			this.userEntry=null;
			
			EntryDialog ed=new EntryDialog(frm,"Delete Dialog",this,"File name");
			
			//Set the Background of the Dialog
			ed.setDialogBackground(super.getBackground());
			
			//Set the textfields' Background in the Dialog
			//ed.setTextBackground(this.dirSelectedColor);
			
			//Set the Dialog Buttons' Background,Foreground & Font
			ed.setButtonsBackground(this.toolbarBackground);
			ed.setButtonsForeground(this.toolbarForeground);
				
			
			//Set the Dialog Labels' Foreground & Font
			ed.setLabelsForeground(this.labelsForeground);
			
		
			//Set the location of the dialog for showing 
			//in the parent's center
			ed.setLocation(this.findCenterPoint(ed));
		
			ed.show();
	
			//If file name is entered delete,else return
			if (userEntry!=null && !(userEntry.equals("")))
			{
				files.addElement(userEntry);
			
				//Initialize the user entry
				this.response=null;
				
				//Set the parent
				this.frm=getParentFrame();
				//Confirm the Deletion
				
				InfoDialog id=new InfoDialog(frm,"Deletion Confirm Dialog",this,"Do you really want to delete?",true);
				
				//Set the Background of the Dialog
				id.setDialogBackground(super.getBackground());
				
				//Set the Dialog Buttons' Background,Foreground & Font
				id.setButtonsBackground(this.toolbarBackground);
				id.setButtonsForeground(this.toolbarForeground);
				
				
				//Set the Dialog Labels' Foreground & Font
				id.setLabelsForeground(this.labelsForeground);
				
		
				//Set the location of the dialog for showing 
				//in the parent's center
				id.setLocation(this.findCenterPoint(id));
		
				id.show();	
				
				if (response.equals("true"))//User said OK
				{				
					CommandEvent ce=new CommandEvent(this,"deleteFile",files,selected);		
					
					//Assign the new CommandEvent object to the currentCommand object
					//so that the Protocol Bean can use the getter methods
					this.currentCommand=ce;
					this.sendCommandEvent(ce);
				}	
			}
			//Opearation over for dialog
		}
		//Files are selected in the list
		else
		{
			//Initialize the user entry
			this.response=null;
			
			//Set the parent
			this.frm=getParentFrame();
			
			//Confirm the Deletion
			InfoDialog id=new InfoDialog(frm,"Deletion Confirm Dialog",this,"Do you really want to delete?",true);
			
			//Set the Background of the Dialog
			id.setDialogBackground(super.getBackground());
						
			//Set the Dialog Buttons' Background,Foreground & Font
			id.setButtonsBackground(this.toolbarBackground);
			id.setButtonsForeground(this.toolbarForeground);
			
		
			//Set the Dialog Labels' Foreground & Font
			id.setLabelsForeground(this.labelsForeground);
			
			//Set the location of the dialog for showing 
			//in the parent's center
			id.setLocation(this.findCenterPoint(id));
		
			id.show();	
				
			if (response.equals("true"))//User said OK
			{				
				//Send a delete command for each file
				for (int i=0;i<files.size();i++)
				{
					String fName=(String)files.elementAt(i);
					//If file is not a directory, delete
					if (!(fName.startsWith("[") && fName.endsWith("]")))
					{
						//Create parameter for sending each file
						Vector pmtr=new Vector();
						pmtr.addElement(fName);
						
						//Create the CommandEvent object
						CommandEvent ce=new CommandEvent(this,"deleteFile",pmtr,selected);		
					
						//Assign the new CommandEvent object to the currentCommand object
						//so that the Protocol Bean can use the getter methods
						this.currentCommand=ce;
						this.sendCommandEvent(ce);
					}	
					else
					{
						//Create parameter for sending each directory
						Vector pmtr=new Vector();
						
						//Remove the [] 
						fName=new String(fName.substring(1,fName.length()-1));
						
						pmtr.addElement(fName);
						//Create the CommandEvent object
						CommandEvent ce=new CommandEvent(this,"removeDir",pmtr,selected);		
					
						//Assign the new CommandEvent object to the currentCommand object
						//so that the Protocol Bean can use the getter methods
						this.currentCommand=ce;
						this.sendCommandEvent(ce);
					}	
					
				}	
			}	
		}			
					
		return;
	}	
	/**
	* Terminate current session, but don't exit program.
	* It constructs CommandEvent for disconnecting and sends
	* it to the CommandListener (FTPProtocol).
	*
	*/
	
	public void disconnect()
	{
		Vector pmtrs=null;
		
		//Construct the CommandEvent Object
		CommandEvent ce=new CommandEvent(this,"disconnect",pmtrs,true);
		
		//Assign the new CommandEvent object to the currentCommand object
		//so that the Protocol Bean can use the getter methods
		this.currentCommand=ce;
		//Send this CommandEvent object to the command listeners
		this.sendCommandEvent(ce);
		return;
	}	
	/**
	* Downloads a file from the remote server to the local machine.
	* Displays a dialog box for the user to enter the file name.
	*
	*/
	public void download()
	{
		//Create a vector for parameters
		Vector files=new Vector();
		
		//Get the selected files in the Remote list
		files=(Vector)getMiddlePanel().getRemoteSelectedFiles();
		
		//If nothing selected in the list
		if (files==null || files.isEmpty())
		{
			this.userEntry=null;
			
			EntryDialog ed=new EntryDialog(frm,"Download Dialog",this,"File name");
			
			//Set the Background of the Dialog
			ed.setDialogBackground(super.getBackground());
						
			//Set the textfields' Background in the Dialog
			//ed.setTextBackground(this.dirSelectedColor);
			
			//Set the Dialog Buttons' Background,Foreground & Font
			ed.setButtonsBackground(this.toolbarBackground);
			ed.setButtonsForeground(this.toolbarForeground);
			
		
			//Set the Dialog Labels' Foreground & Font
			ed.setLabelsForeground(this.labelsForeground);
			
		
			//Set the location of the dialog for showing 
			//in the parent's center
			ed.setLocation(this.findCenterPoint(ed));
		
			ed.show();
		
			if (userEntry!=null && !(userEntry.equals("")))
			{
				//Get the parameter for downloading
				files.addElement(userEntry);
			}
			else
			//Operation cancelled
				return;
		}	
		
		for (int i=0;i<files.size();i++)
		{
			Vector pmtr=new Vector();
			/************** Changed on 10/02/98 ***************/
			/*  This change was required because the directory name
			was going as a String in square brackets which needs
			to be removed*/
			String fileName=(String)files.elementAt(i);
			if(fileName.startsWith("[")){
				fileName=this.changeDirToFileName(fileName);
			}
			pmtr.addElement(fileName);
			/************** End of change **********************/	
			//pmtr.addElement((String)files.elementAt(i));
			
			//Construct the CommandEvent Object
			CommandEvent ce=new CommandEvent(this,"getFile",pmtr,selected);
		
			//Assign the new CommandEvent object to the currentCommand object
			//so that the Protocol Bean can use the getter methods
			this.currentCommand=ce;
	
			//Send this CommandEvent object to the command listeners
			this.sendCommandEvent(ce);
		}	
		return;
	}	
	/**
	* Downloads a file from the remote server to the local machine.
	* Selected file name in the remote file list is the file to be downloaded
	* and is passed as a parameter to this method.
	* Dialog box will not be invoked for this method.
	*
	* @param	fileName the name of the file to be downloaded.
	*/
	public void download(String fileName)
	{
		//If nothing selected in the list
		if (fileName==null || fileName.equals(""))
		{
			//Set the parent frame
			this.frm=getParentFrame();
			
			//Show the error in InfoDialog & return
			this.response=null;
				
			InfoDialog id=new InfoDialog(frm,"Download Error",this,"File Name is not valid",false);
			
			//Set the Background of the Dialog
			id.setDialogBackground(super.getBackground());
			
			//Set the Dialog Buttons' Background,Foreground & Font
			id.setButtonsBackground(this.toolbarBackground);
			id.setButtonsForeground(this.toolbarForeground);
			
		
			//Set the Dialog Labels' Foreground & Font
			id.setLabelsForeground(this.labelsForeground);
			
		
			//Set the location of the dialog for showing 
			//in the parent's center
			id.setLocation(this.findCenterPoint(id));
		
			id.show();
			return;
		}	
		
		//Create a vector for parameters
		Vector files=new Vector();
		
		//Get the selected files in the Remote list
		files.addElement(fileName);
		
		//Construct the CommandEvent Object
		CommandEvent ce=new CommandEvent(this,"getFile",files,selected);
		
		//Assign the new CommandEvent object to the currentCommand object
		//so that the Protocol Bean can use the getter methods
		this.currentCommand=ce;
		//Send this CommandEvent object to the command listeners
		this.sendCommandEvent(ce);
		return;
	}		
/**
 * Receives the ActionEvent from the Toolbar and sends 
 * it to the CommandInterpreter for calling the corresponding
 * command methods.
 *
 * @param event java.awt.event.ActionEvent
 */
void eventFromToolbar ( java.awt.event.ActionEvent event) {
	//Set the parent frame
	this.frm=getParentFrame();
	
	//Pass this ActionEvent to CommandInterpreter
	CommandInterpreter ci=new CommandInterpreter(this);
	ci.executeCommand(event);
	return;
}
	/**
	* Calculates the center location of the dialog for showing 
	* in the parent's region and returns the center location.
	*
	* @param		dial java.awt.Dialog
	* @return	java.awt.Point	
	*/
	
	private java.awt.Point findCenterPoint(java.awt.Dialog dial)
	{
		//Get parent's bounds wrt the screen
		java.awt.Point parPoint=this.getLocationOnScreen();
		java.awt.Dimension parSize=this.getSize();
		
		//Calculate the center point
		int cx=parPoint.x;
		int cy=parPoint.y;
		
		cx=cx+((int)((parSize.width-dial.getSize().width)/2));
		cy=cy+((int)((parSize.height-dial.getSize().height)/2));
		
		//return the center point 
		return (new java.awt.Point(cx,cy));
	}		
	/**
	* Returns the background color of this user interface window.
	*
	* @return  background color of the main user interface window.	
	*/
	public java.awt.Color getBackground()
	{
		return super.getBackground();
	}	
/**
 * Return the BottomPanel property value.
 *
 * @return com.ibm.network.ftp.ui.BottomPanel
 */
private BottomPanel getBottomPanel() {
	if (ivjBottomPanel == null) {
		try {
			ivjBottomPanel = new com.ibm.network.ftp.ui.BottomPanel();
			ivjBottomPanel.setName("BottomPanel");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	};
	return ivjBottomPanel;
}
	/**
	* Returns the command name of the current CommandEvent.
	*
	* @return  name of the command represented by CommandEvent.	
	*/
	public String getCommandName()
	{
		return currentCommand.getCommand();
	}
	/**
	* Returns the command parameters of the current CommandEvent.
	*
	* @return  parameters of the command represented by CommandEvent.
	*/
	public Vector getCommandParameters()
	{
		return currentCommand.getParameters();
	}	
	/**
	* Returns the background color of the currently
	* deselected directories,ie., either local or remote
	* directory field.
	*
	* @return the color of deselected directories.	
	*/
	public java.awt.Color getDirDeselectedColor()	
	{
		//return the local property
		return this.dirDeselectedColor;
	}
	/**
	* Returns the background color of the currently
	* selected directories,ie., either local or remote
	* directory field.
	*
	* @return  the color of all selected directories.	
	*/
	public java.awt.Color getDirSelectedColor()	
	{
		//return the local property
		return this.dirSelectedColor;
	}
/**
 * Return the FTPUIBeanBorderLayout property value.
 * @return java.awt.BorderLayout
 */
private java.awt.BorderLayout getFTPUIBeanBorderLayout() {
	java.awt.BorderLayout ivjFTPUIBeanBorderLayout = null;
	try {
		/* Create part */
		ivjFTPUIBeanBorderLayout = new java.awt.BorderLayout();
		ivjFTPUIBeanBorderLayout.setVgap(3);
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	};
	return ivjFTPUIBeanBorderLayout;
}
	/**
	* Returns the font of all the labels in this UI.
	*
	* @return the font of all labels in the user interface.	
	*/
	public java.awt.Font getLabelsFont()
	{
		//return the local property
		return this.labelsFont;
	}			
	/**
	* Returns the foreground color of all the labels in this UI.
	*
	* @return  the foreground color of all the labels in the user interface.	
	*/
	public java.awt.Color getLabelsForeground()
	{
		//return the local property
		return this.labelsForeground;
	}	
	/**
	* Returns the background color of the local and remote lists.
	*
	* @return	background color of local and remote file lists.
	*/
	public java.awt.Color getListsBackground()
	{
		//return the local property
		return this.listsBackground;
	}	
	/**
	* Returns the foreground color of the local and remote lists.
	*
	* @return  foreground color of local and remote file lists.
	*/
	public java.awt.Color getListsForeground()
	{
		//return the local property
		return this.listsForeground;
	}	
/**
 * Return the MiddlePanel property value.
 * @return com.ibm.intercept.ftp.userinterface..MiddlePanel
 */
private MiddlePanel getMiddlePanel() {
	if (ivjMiddlePanel == null) {
		try {
			ivjMiddlePanel = new com.ibm.network.ftp.ui.MiddlePanel(this);
			ivjMiddlePanel.setName("MiddlePanel");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	};
	return ivjMiddlePanel;
}
	/**
	* For getting the Frame parent of this FtpUI class panel.
	* The frame parent reference will be passed in
	* the dialog's contructor.
	*
	* @return  java.awt.Frame
	*/
	private java.awt.Frame getParentFrame()	
	{
		java.awt.Component container=this.getParent();
		
		while (!(container instanceof java.awt.Frame))
		{
			container=container.getParent();	
		}
		return (java.awt.Frame)container;	
	}	
	/**
	* Returns the background color of the status text area.
	*
	* @return  the background color of status window.
	*/
	public java.awt.Color getStatusBackground()	
	{
		//return the local property
		return this.statusBackground;
	}
	/**
	* Returns the font of the status text area.
	*
	* @return  the font used for the status window.	
	*/
	public java.awt.Font getStatusFont()	
	{
		//return the local property
		return this.statusFont;
	}	
	/**
	* Returns the foreground color of the status text area.
	*
	* @return  the foreground color of status window.
	*/
	public java.awt.Color getStatusForeground()	
	{
		//return the local property
		return this.statusForeground;
	}	
	/**
	* Returns the background color of all the buttons in the toolbar.
	*
	* @return  the background color of all buttons in the toolbar.
	*/
	public java.awt.Color getToolbarBackground()
	{
		//Return the local property
		return this.toolbarBackground;
	}			
	/**
	* Returns the font of all the buttons in the toolbar.
	*
	* @return  the font used for the toolbar.
	*/
	public java.awt.Font getToolbarFont()
	{
		//Return the local property
		return this.toolbarFont;
	}			
	/**
	* Returns the foreground color of all the buttons in the toolbar.
	*
	* @return  the foreground color of the toolbar.
	*/
	public java.awt.Color getToolbarForeground()
	{
		//Return the local property
		return this.toolbarForeground;
	}			
/**
 * Return the TopPanel property value.
 * @return com.ibm.intercept.ftp.userinterface.TopPanel
 */
private TopPanel getTopPanel() {
	if (ivjTopPanel == null) {
		try {
			
			//In constructor pass this as reference
			ivjTopPanel = new com.ibm.network.ftp.ui.TopPanel(this);
			ivjTopPanel.setName("TopPanel");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	};
	return ivjTopPanel;
}
/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(Throwable exception) {
	/* Uncomment the following lines to print uncaught exceptions to stdout */
	// System.out.println("--------- UNCAUGHT EXCEPTION ---------");
	// exception.printStackTrace(System.out);
	//Show the error in InfoDialog
			
	//Initialize the user entry
	//set the parent
	this.frm=getParentFrame();
			
	InfoDialog id=new InfoDialog(frm,"Error Message",this,"Exception occurred!",false);
	
	//Set the Background of the Dialog
	id.setDialogBackground(super.getBackground());
	
	//Set the Dialog Buttons' Background,Foreground & Font
	id.setButtonsBackground(this.toolbarBackground);
	id.setButtonsForeground(this.toolbarForeground);
	
	
	//Set the Dialog Labels' Foreground & Font
	id.setLabelsForeground(this.labelsForeground);
	
			
	//Set the location of the dialog for showing 
	//in the parent's center
	id.setLocation(this.findCenterPoint(id));
		
	id.show();	
}
/**
 * Initialize class.
 */
private void initialize() {
	// user code begin {1}
	// user code end
	setName("FTPUIBean");
	setLayout(getFTPUIBeanBorderLayout());
	setBackground(java.awt.Color.lightGray);
	setSize(512, 358);
	//If the default Toolbar is true add it to the UI
	if (this.defaultToolbar==true)
	{
		this.add("North", getTopPanel());
	}	
	this.add("Center", getMiddlePanel());
	this.add("South", getBottomPanel());
	// user code begin {2}
	getTopPanel().enableConnectButton(false);
	getTopPanel().enableRemoteButtons(false);
	getTopPanel().enableFileButtons(false);
	// user code end
}
	/**
	* Returns true if the default toolbar is selected,
	* else false.
	*
	* @return  true if the toolbar is being displayed else returns false.
	*/
	
	public boolean isDefaultToolbar()
	{
		return defaultToolbar;
	}	
	/**
	* Returns true if the current command is for FTP server,
	* else returns false.
	*
	* @return  true if the commands are for FTP server else returns false.
	*/
	public boolean isRemoteSelected()
	{
		return currentCommand.isRemote();
	}
  /**
	* Returns true if the view file modified date is selected,
	* else returns false.
	*
	* @return  true if file modification date is to be displayed.
	*/
  public boolean isViewDate()
  {
	  	return viewDate;
  }	                               
	/**
	* Returns true if the local file list window is selected,
	* else returns false.
	*
	* @return	true if local file list window is selected else returns false.
	*/
	public boolean isViewLocal()
  {
	  	return viewLocal;
  }	                                  
 /**
	* Returns true if the remote file list window is selected
	* else returns false.
	*
	* @return  true if the remote file list window is selected else returns false.	
	*/
  public boolean isViewRemote()
  {
	  	return viewRemote;
  }	                                   
  /**
	* Returns true if the view file size is selected
	* else returns false.
	*
	* @return  true if file size is being displayed else returns false.
	*/
  public boolean isViewSize()
  {
	  	return viewSize;
  }	                                 
  /**
	* Returns true if the view file modified time is selected
	* else  returns false.
	*
	* @return  true if file modification time is being displayed else returns false.
	*/
  public boolean isViewTime()
  {
	  	return viewTime;
  }	                               
 /**
 * Implements the localFileListReceived method defined in <a href="com.ibm.network.ftp.event.LocalFileListListener.html"> LocalFileListListener </a>.
 * This method receives the <a href="com.ibm.network.ftp.event.LocalFileListEvent.html"> LocalFileListEvent </a> from the sources and
 * sets the local directory and local file list.
 *
 * @param 	fle LocalFileListEvent
 * @see		com.ibm.network.ftp.event.LocalFileListListener
 */
public void localFileListReceived(LocalFileListEvent fle) {
	//Call MiddlePanel's methods to set
	//Local current dir & file list
	if (fle.getLocalDir()!=null)
	{
		getMiddlePanel().setLocalCurrentDir(fle.getLocalDir());
	}
	
	if (fle.getLocalFileInfo()!=null)
	{	
		/******************Changed in 1.06 **********/
		localFileList=fle.getLocalFileInfo();	
		/********************************************/
		getMiddlePanel().setLocalFileList(fle.getLocalFileInfo());
	}	
	//Set the BottomPanel's label
	this.getBottomPanel().updateStatus("Data connection is idle.");
	return;
}
	/**
	* Creates a new directory in current working directory of local machine or
	* FTP server depending on which one of them is selected.
	* Path name will be received through a "Make Dir" dialog box.
	*
	*/
	public void makeDir()
	{
		//Set the parent frame
		this.frm=getParentFrame();
		//Initialize the user entry
		this.userEntry=null;
		
		EntryDialog ed=new EntryDialog(frm,"Make Dir Dialog",this,"Directory name");
		
		//Set the Background of the Dialog
		ed.setDialogBackground(super.getBackground());
								
		//Set the textfields' Background in the Dialog
		//ed.setTextBackground(this.dirSelectedColor);
		
		//Set the Dialog Buttons' Background,Foreground & Font
		ed.setButtonsBackground(this.toolbarBackground);
		ed.setButtonsForeground(this.toolbarForeground);
		
		
		//Set the Dialog Labels' Foreground & Font
		ed.setLabelsForeground(this.labelsForeground);
		
		
		//Set the location of the dialog for showing 
		//in the parent's center
		ed.setLocation(this.findCenterPoint(ed));
		
		ed.show();
		
		if (userEntry!=null && !(userEntry.equals("")))
		{
			//Create a vector for parameters
			Vector<String> files=new Vector<String>();
						
			//Add the new file name to the Vector
			files.addElement(userEntry);
			
			CommandEvent ce=new CommandEvent(this,"makeDir",files,selected);
			
			//Assign the new CommandEvent object to the currentCommand object
			//so that the Protocol Bean can use the getter methods
			this.currentCommand=ce;
			this.sendCommandEvent(ce);
		}			
		return;
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
  void messageConnectDialog(String hostName, String userName, String password, String account) {
		
	Vector<String> pmtrs = new Vector<String>();
	try
	{
		//Check the parameters
		if (hostName.equals("") || hostName==null)
		{
			InfoDialog id=new InfoDialog(frm,"Connect Error",this,"Host name has to be entered",false);
		
			//Set the Background of the Dialog
			id.setDialogBackground(super.getBackground());
			
			//Set the Dialog Buttons' Background,Foreground & Font
			id.setButtonsBackground(this.toolbarBackground);
			id.setButtonsForeground(this.toolbarForeground);
			
			//Set the Dialog Labels' Foreground & Font
			id.setLabelsForeground(this.labelsForeground);
			
		
			//Set the location of the dialog for showing 
			//in the parent's center
			id.setLocation(this.findCenterPoint(id));
		
			id.setVisible(true);
			return;
		}	
		if (userName.equals("") || userName==null)
		{
			InfoDialog id=new InfoDialog(frm,"Connect Error",this,"User name has to be entered",false);
		
			//Set the Background of the Dialog
			id.setDialogBackground(super.getBackground());
			
			//Set the Dialog Buttons' Background,Foreground & Font
			id.setButtonsBackground(this.toolbarBackground);
			id.setButtonsForeground(this.toolbarForeground);
			
			//Set the Dialog Labels' Foreground & Font
			id.setLabelsForeground(this.labelsForeground);
			
		
			//Set the location of the dialog for showing 
			//in the parent's center
			id.setLocation(this.findCenterPoint(id));
		
			id.setVisible(true);
			return;
		}	
		if (password.equals("") || password==null)
		{
			InfoDialog id=new InfoDialog(frm,"Connect Error",this,"Password has to be entered",false);
		
			//Set the Background of the Dialog
			id.setDialogBackground(super.getBackground());
			
			//Set the Dialog Buttons' Background,Foreground & Font
			id.setButtonsBackground(this.toolbarBackground);
			id.setButtonsForeground(this.toolbarForeground);
			
			//Set the Dialog Labels' Foreground & Font
			id.setLabelsForeground(this.labelsForeground);
			
		
			//Set the location of the dialog for showing 
			//in the parent's center
			id.setLocation(this.findCenterPoint(id));
		
			id.setVisible(true);
			return;
		}	
	
		//Add the parameters to the Vector		
		pmtrs.addElement(hostName);
	
		//Construct CommandEvent object for connect
		CommandEvent ce1=new CommandEvent(this,"connect",pmtrs,selected);
		//Assign the new CommandEvent object to the currentCommand object
		//so that the Protocol Bean can use the getter methods
		this.currentCommand=ce1;
	
		//Send this connect CommandEvent object to the command listeners
		this.sendCommandEvent(ce1);
	
		//Add the parameters to the Vector	for login command	
		pmtrs.removeAllElements();
		
		pmtrs.addElement(userName);
		pmtrs.addElement(password);
		//Add account if it's given
		if (account!=null && !(account.equals("")))
		{
			pmtrs.addElement(account);			
		}	
		//Construct the CommandEvent Object
		CommandEvent ce2=new CommandEvent(this,"login",pmtrs,selected);
	
		//Assign the new CommandEvent object to the currentCommand object
		//so that the Protocol Bean can use the getter methods
		this.currentCommand=ce2;
	
		//Send this CommandEvent object to the command listeners
		this.sendCommandEvent(ce2);
	}	
	catch(Exception e)
	{
		this.handleException(e);
	}		
}
/**
 * Implements the remoteFileListReceived method defined in <a href="com.ibm.network.ftp.event.RemoteFileListListener.html"> RemoteFileListListener </a>
 * This method receives the <a href="com.ibm.network.ftp.event.RemoteFileListEvent.html"> RemoteFileListEvent </a> from the sources and
 * and sets the remote directory and remote file list.
 *
 * @param 	fle RemoteFileListEvent
 * @see		com.ibm.network.ftp.event.RemoteFileListListener
 */
public void remoteFileListReceived(RemoteFileListEvent fle) {
	//Call MiddlePanel's methods to set
	//Remote current dir & file list
	
	if (fle.getRemoteDir()!=null)
	{
		getMiddlePanel().setRemoteCurrentDir(fle.getRemoteDir());
	}
	if	(fle.getRemoteFileInfo()!=null)
	{
		/***************Changed in 1.06 ******/
		remoteFileList=fle.getRemoteFileInfo();
		/*************************************/
		
		getMiddlePanel().setRemoteFileList(fle.getRemoteFileInfo());
	}		
	//Set the BottomPanel's label
	this.getBottomPanel().updateStatus("Data connection is idle.");
	return;
}
 /**
 * Removes the specified command listener so that it no longer 
 * receives command event from this class.
 * Command event occurs whenever a new command has to be notified to the registered listeners. 
 *
 * @param 	aListener CommandListener
 * @see		com.ibm.network.ftp.event.CommandListener
 */
	public synchronized void removeCommandListener(CommandListener aListener)	{
		//If the commandListeners not null remove the specified listener
		if (commandListeners!=null && !(commandListeners.isEmpty()))
		{
				commandListeners.removeElement(aListener);
		}
		return;
	}		
	/**
	* Deletes a directory in current working directory of the local machine
	* or the FTP server depending on which one of them is selected.
	* Path name will be received through "Remove Directory" dialog box
	* or the selected directory name in the list.
	* It constructs a CommandEvent and sends it to the listener.
	*
	*/
	public void removeDir()
	{
		//Set the parent frame
		this.frm=getParentFrame();
		//Create a vector for parameters
		Vector<String> files=new Vector<String>();
		
		//If Directories are already selected,get from the list
		
		//Get the remote files
		if (selected)
		{
			files=getMiddlePanel().getRemoteSelectedFiles();
		}	
		//Get the local files 
		else
		{
			files=getMiddlePanel().getLocalSelectedFiles();
		}
		
		//If files are not selected,Show the delete dialog		
		if (files.size()<1)
		{
			//Initialize the user entry
			this.userEntry=null;
			
			EntryDialog ed=new EntryDialog(frm,"Delete Dialog",this,"Directory name");
			
			//Set the Background of the Dialog
			ed.setDialogBackground(super.getBackground());
									
			//Set the textfields' Background in the Dialog
			//ed.setTextBackground(this.dirSelectedColor);
		
			//Set the Dialog Buttons' Background,Foreground & Font
			ed.setButtonsBackground(this.toolbarBackground);
			ed.setButtonsForeground(this.toolbarForeground);
		
		
			//Set the Dialog Labels' Foreground & Font
			ed.setLabelsForeground(this.labelsForeground);
			
		
			//Set the location of the dialog for showing 
			//in the parent's center
			ed.setLocation(this.findCenterPoint(ed));
		
			ed.setVisible(true);
		
			//If file name is entered delete,else return		
			if (userEntry!=null && !(userEntry.equals("")))
			{
				files.addElement(userEntry);
			
				//Set the parent
				this.frm=getParentFrame();
				
				//Confirm the Deletion
				//Initialize the user entry
				this.response=null;
				
				InfoDialog id=new InfoDialog(frm,"Deletion Confirm Dialog",this,"Do you really want to delete?",true);
				
				//Set the Background of the Dialog
				id.setDialogBackground(super.getBackground());
				
				//Set the Dialog Buttons' Background,Foreground & Font
				id.setButtonsBackground(this.toolbarBackground);
				id.setButtonsForeground(this.toolbarForeground);
				
		
				//Set the Dialog Labels' Foreground & Font
				id.setLabelsForeground(this.labelsForeground);
				
			
				//Set the location of the dialog for showing 
				//in the parent's center
				id.setLocation(this.findCenterPoint(id));
		
				id.show();	
				
				if (response.equals("true"))//User said OK
				{				
					CommandEvent ce=new CommandEvent(this,"removeDir",files,selected);		
					
					//Assign the new CommandEvent object to the currentCommand object
					//so that the Protocol Bean can use the getter methods
					this.currentCommand=ce;
					this.sendCommandEvent(ce);
				}	
			}
			//Opearation over for dialog
		}
		//Files are selected in the list
		else
		{
			//Initialize the user entry
			this.response=null;
			
			//Set the parent
			this.frm=getParentFrame();
			
			//Confirm the Deletion
			InfoDialog id=new InfoDialog(frm,"Deletion Confirm Dialog",this,"Do you really want to delete?",true);
			
			//Set the Background of the Dialog
			id.setDialogBackground(super.getBackground());
			
			//Set the Dialog Buttons' Background,Foreground & Font
			id.setButtonsBackground(this.toolbarBackground);
			id.setButtonsForeground(this.toolbarForeground);
		
		
			//Set the Dialog Labels' Foreground & Font
			id.setLabelsForeground(this.labelsForeground);
			
		
			//Set the location of the dialog for showing 
			//in the parent's center
			id.setLocation(this.findCenterPoint(id));
		
			id.show();	
				
			if (response.equals("true"))//User said OK
			{				
				//Send a delete command for each file
				for (int i=0;i<files.size();i++)
				{
					String fName=(String)files.elementAt(i);
					//If file is a directory, delete
					if (fName.startsWith("[") && fName.endsWith("]"))
					{
						Vector pmtr=new Vector();
						
						//Remove the [] 
						fName=new String(fName.substring(1,fName.length()-1));
						
						pmtr.addElement(fName);
						
						//Create the CommandEvent object
						CommandEvent ce=new CommandEvent(this,"removeDir",pmtr,selected);		
					
						//Assign the new CommandEvent object to the currentCommand object
						//so that the Protocol Bean can use the getter methods
						this.currentCommand=ce;
						this.sendCommandEvent(ce);
					}	
				}	
			}	
		}			
					
		return;
	}		
	/**
	* Renames a file or directory in current working directory of the
	* local machine or FTP server depending on which one of them is selected.
	* Old file name will be received from the selected directory name in the list.
	* New file name will be received from a "Rename File" dialog box.
	* It constructs a CommandEvent and sends it to the listener.
	*
	*/
	public void rename()
	{
		//Set the parent frame
		this.frm=getParentFrame();
		
		//Initialize the user entry
		this.userEntry=null;
		
		String oldName;
		
	   EntryDialog ed=new EntryDialog(frm,"Rename Dialog",this,"New name");
		
		//Set the Background of the Dialog
		ed.setDialogBackground(super.getBackground());
								
		//Set the textfields' Background in the Dialog
		//ed.setTextBackground(this.dirSelectedColor);
		
		//Set the Dialog Buttons' Background,Foreground & Font
		ed.setButtonsBackground(this.toolbarBackground);
		ed.setButtonsForeground(this.toolbarForeground);
	
		
		//Set the Dialog Labels' Foreground & Font
		ed.setLabelsForeground(this.labelsForeground);
		
		
		//Set the location of the dialog for showing 
		//in the parent's center
		ed.setLocation(this.findCenterPoint(ed));
		
		ed.show();
		
		if (userEntry!=null && !(userEntry.equals("")))
		{
			//Create a vector for parameters
			Vector files=new Vector();
			
			//Get the remote files
			if (selected==true)
			{
				files=getMiddlePanel().getRemoteSelectedFiles();
			}	
			//Get the local files 
			else
			{
				files=getMiddlePanel().getLocalSelectedFiles();
			}
			//If more than one file is selected,Show the Error		
			if (files.size()!=1)
			{
				//Initialize the user entry
				this.response=null;
				
				InfoDialog id=new InfoDialog(frm,"Rename error",this,"Select one file",false);
				
				//Set the Background of the Dialog
				id.setDialogBackground(super.getBackground());
				
				//Set the Dialog Buttons' Background,Foreground & Font
				id.setButtonsBackground(this.toolbarBackground);
				id.setButtonsForeground(this.toolbarForeground);
				
		
				//Set the Dialog Labels' Foreground & Font
				id.setLabelsForeground(this.labelsForeground);
				
		
				//Set the location of the dialog for showing 
				//in the parent's center
				id.setLocation(this.findCenterPoint(id));
		
				id.setVisible(true);
				return;
			}
			else
			{
				//For removing the [] file name,if it's a directory
				String fName=(String)files.elementAt(0);
				if (fName.startsWith("[") && fName.endsWith("]"))
				{
						Vector<String> pmtr=new Vector<String>();
						
						fName=new String(fName.substring(1,fName.length()-1));
					
						files.removeAllElements();	
						files.addElement(fName);
				}	
						
				//Add the new file name to the Vector
				files.addElement(userEntry);
				CommandEvent ce=new CommandEvent(this,"rename",files,selected);
				//Assign the new CommandEvent object to the currentCommand object
				//so that the Protocol Bean can use the getter methods
				this.currentCommand=ce;
				this.sendCommandEvent(ce);
			}			
		}	
					
		return;
	}		
	/**
	* Receives the command event and send it to the registered listeners.
	*
	* @param ce com.ibm.network.ftp.event.CommandEvent
	*/
	void sendCommandEvent(CommandEvent ce)
	{
		Vector<CommandListener> cmdListeners;
		
		if (ce.getCommand().equals("connect"))
		{
			//Get the parameters
			java.util.Vector<String> pmtrs = ce.getParameters();
			//Get the host name
			String host = pmtrs.elementAt(0);
		
			//Set the BottomPanel's label for connection
			this.getBottomPanel().updateStatus("Connecting to host "+host+",please wait...");
		}	
		//For login command also show the connect status only
		else 
		{
			//Set the BottomPanel's label
			this.getBottomPanel().updateStatus("Please wait....");
		}	
		
		//Copy the command listeners
		synchronized (this)
		{
			cmdListeners = new Vector<CommandListener>(commandListeners);
		}	
		
		//Call all listeners' commandPerformed method
		for (CommandListener cmdListener: cmdListeners)
		{
			cmdListener.commandPerformed(ce);
		}	
				
		return;
	}	
	/**
	* Sets the background color of this user interface.
	*
	* @param	newColor background color of the user interface.
	* @exception java.lang.IllegalArgumentException	When the argument for this setter method is null,this exception will be thrown.
	*/
	public void setBackground(java.awt.Color newColor) throws IllegalArgumentException
	{
		if (newColor!=null)
		{	
			super.setBackground(newColor);
	
			//Change the Background of each contained classes
			getBottomPanel().setBackground(newColor);
			getMiddlePanel().setBackground(newColor);
			getTopPanel().setBackground(newColor);
		}
		else
		{
			throw new IllegalArgumentException("Background color is null"); 
		}		
		return;
	}
	/*
	* For getting user entry received from the EntryDialog.
	*
	* @param	entry java.lang.String
	*/
  	void setDataString(String entry) {
	try
	{
		if (entry.equals("") || entry==null)
		{
			InfoDialog id=new InfoDialog(frm,"File name error",this,"Enter a valid name",false);
		
			//Set the Background of the Dialog
			id.setDialogBackground(super.getBackground());
			
			//Set the Dialog Buttons' Background,Foreground & Font
			id.setButtonsBackground(this.toolbarBackground);
			id.setButtonsForeground(this.toolbarForeground);
			
		
			//Set the Dialog Labels' Foreground & Font
			id.setLabelsForeground(this.labelsForeground);
			
		
			//Set the location of the dialog for showing 
			//in the parent's center
			id.setLocation(this.findCenterPoint(id));
		
			id.setVisible(true);
		}	
		else
		{
			//Set the user entry
			userEntry=entry;
		}	
	}
	catch(Exception e)
	{
		this.handleException(e);
	}		
	return;
}
	/**
	* Displays toolbar if the boolean parameter is true or else hides
	* the toolbar.
	*
	* @param	defToolbar  this argument decides whether the toolbar has to be displayed or hidden. 
	*/
	public void setDefaultToolbar(boolean defToolbar)
	{
		this.defaultToolbar=defToolbar;
		//Set the Toolbar's visibility 
		if (this.defaultToolbar)
		{
			this.getTopPanel().setVisible(true);
		}
		else	
		{
			this.getTopPanel().setVisible(false);
		}
		validate();
	}	
	/**
	* Sets the background color of deselected directory.
	* When the local file list is clicked local file list will get selected
	* and the remote file list will get deselected.
	* When the remote file list is clicked,remote file list will get selected
	* and the Local list will get deselected. 
	*
	* @param	newColor the color for deselected directories.
	* @exception java.lang.IllegalArgumentException	When the argument for this setter method is null,this exception will be thrown.	
	*/
	public void setDirDeselectedColor(java.awt.Color newColor) throws IllegalArgumentException
	{
		if (newColor!=null) 
		{
			//Set the Local dirDeselectedColor property
			this.dirDeselectedColor=newColor;
		
			//Call MiddlePanel's method
			this.getMiddlePanel().setDirDeselectedColor(newColor);
		}	
		else
		{
			throw new IllegalArgumentException("Directory selected color is null"); 
		}		
		return;
	}			
	/**
	* Sets the background color of selected directory.
	* When the local file list is clicked local file list will get selected
	* and the remote file list will get deselected.
	* When the remote file list is clicked remote file list will get selected
	* and the local file list will get deselected.  
	*
	* @param	newColor the color for selected directories.
	* @exception java.lang.IllegalArgumentException	When the argument for this setter method is null this exception will be thrown.	
	*/
	public void setDirSelectedColor(java.awt.Color newColor) throws IllegalArgumentException
	{
		if (newColor!=null) 
		{
			//Set the Local dirSelectedColor property
			this.dirSelectedColor=newColor;
		
			//Call MiddlePanel's method
			this.getMiddlePanel().setDirSelectedColor(newColor);
		}	
		else
		{
			throw new IllegalArgumentException("Directory selected color is null"); 
		}		
		return;
	}			
	/*
	* For getting the user's response
	* for the FTP operations from the InfoDialog.
	*
	* @param infoResponse java.lang.String
	*
	*/
	
	void setInfoResponse(String infoResponse)
	{
		try
		{
			if (!(infoResponse.equals("")) && infoResponse!=null)
			{
				this.response=infoResponse;
			}	
		}
		catch(Exception e)
		{
			this.handleException(e);
		}			
		return;
	}	
	/**
	* Sets the font of all the labels in the main window of the user interface. 
	*
	* @param	newFont the font of the labels.
	* @exception java.lang.IllegalArgumentException	When the argument for this setter method is null this exception will be thrown.	
	*/
	public void setLabelsFont(java.awt.Font newFont) throws IllegalArgumentException
	{
		if (newFont!=null)
		{
			//Set the local property
			this.labelsFont=newFont;
		
			//Set the Local & Remote directory labels' Font
		
			this.getMiddlePanel().setLabelsFont(newFont);
			
			//Set the Status & Data Connection status labels' Font
		
			this.getBottomPanel().setLabelsFont(newFont);
		}
		else
		{
			throw new IllegalArgumentException("Labels font is null"); 
		}		
	
		return;
	}				
	/**
	* Sets the foreground color of all the labels in the main window 
	* of this user interface.
	*
	* @param	newFont foreground color of labels.
	* @exception java.lang.IllegalArgumentException	When the argument for this setter method is null this exception will be thrown.	
	*/
	public void setLabelsForeground(java.awt.Color newColor) throws IllegalArgumentException
	{
		if (newColor != null)
		{
			//Set the local property
			this.labelsForeground=newColor;
		
			//Set the Local & Remote directory labels' Foreground color
			this.getMiddlePanel().setLabelsForeground(newColor);
		
			//Set the Status & Data Connection status labels' Foreground color
			this.getBottomPanel().setLabelsForeground(newColor);
		}	
		else
		{
			throw new IllegalArgumentException("Labels Foreground color is null"); 
		}		
		return;
	}				
	/**
	* Sets the background color of the local and remote lists.
	*
	* @param	newColor background color of local and remote lists.
	* @exception java.lang.IllegalArgumentException	When the argument for this setter method is null this exception will be thrown.	
	*/
	public void setListsBackground(java.awt.Color newColor) throws IllegalArgumentException
	{
		if (newColor!=null)
		{
			//Set the local property
			this.listsBackground=newColor;
		
			//Set the Local & Remote lists' Background color
			this.getMiddlePanel().setListsBackground(newColor);
		}
		else
		{
			throw new IllegalArgumentException("Lists Background color is null"); 
		}		
		
		return;
	}	
	/**
	* Sets the foreground color of the local and remote lists.
	*
	* @param	newColor foreground color of local and remote file lists.
	* @exception java.lang.IllegalArgumentException	When the argument for this setter method is null this exception will be thrown.	
	*/
	public void setListsForeground(java.awt.Color newColor) throws IllegalArgumentException
	{
		if (newColor != null)
		{
			//Set the local property
			this.listsForeground=newColor;
		
			//Set the Local & Remote lists' Foreground color
			this.getMiddlePanel().setListsForeground(newColor);
		}	
		else
		{
			throw new IllegalArgumentException("Lists Foreground color is null"); 
		}		
		return;
	}			
/*
 * Configures socksProxyHost and socksProxyPort.
 * The socksProxyHost and socksProxyPort are set by this method.
 * This host will be used by Protocol for connecting to servers
 * that are outside the firewall.
 *
 * @param socksHost java.lang.String
 * @param socksPort java.lang.String
 */
	void setSocksInfo(String socksHost,String socksPort) {
	try
	{
		//Check the parameters
		if (socksHost.equals("") || socksHost==null || socksPort.equals("") || socksPort==null)
		{
			InfoDialog id=new InfoDialog(frm,"Connect Error",this,"Socks Host and Port have to be entered",false);
		
			//Set the Background of the Dialog
			id.setDialogBackground(super.getBackground());
			
			//Set the Dialog Buttons' Background,Foreground & Font
			id.setButtonsBackground(this.toolbarBackground);
			id.setButtonsForeground(this.toolbarForeground);
			
		
			//Set the Dialog Labels' Foreground & Font
			id.setLabelsForeground(this.labelsForeground);
			
		
			//Set the location of the dialog for showing 
			//in the parent's center
			id.setLocation(this.findCenterPoint(id));
		
			id.setVisible(true);
			return;
		}	
	
		//Create a vector for parameters
		Vector<String> parameters=new Vector<String>();
	
		//Add the parameters in the vector
		parameters.addElement(socksHost);
	
		//Cast the in to Integer object
		parameters.addElement(socksPort);
		
		//Construct the CommandEvent object'
		CommandEvent ce=new CommandEvent(this,"configureSocks",parameters,selected);
		
		//Assign the new CommandEvent object to the currentCommand object
		//so that the Protocol Bean can use the getter methods
		this.currentCommand=ce;
		
		//Send the CommandEvent object
		this.sendCommandEvent(ce);
	}
	catch(Exception e)
	{
		this.handleException(e);
	}			
	return;
}
	/**
	* Sets the background color of the status window.
	*
	* @param	newColor background color of status window.
	* @exception java.lang.IllegalArgumentException	When the argument for this setter method is null this exception will be thrown.	
	*/
	public void setStatusBackground(java.awt.Color newColor) throws IllegalArgumentException
	{
		if (newColor!=null)
		{
			//Set the local property
			this.statusBackground=newColor;	
		
			//Set the Status Text Area Background color
			this.getBottomPanel().setStatusBackground(newColor);
		}
		else
		{
			throw new IllegalArgumentException("Status Background color is null"); 
		}		
		return;
	}		
	/**
	* Sets the font of the status window.
	*
	* @param	newFont the font for the status window.
	* @exception java.lang.IllegalArgumentException	When the argument for this setter method is null this exception will be thrown.	
	*/
	public void setStatusFont(java.awt.Font newFont) throws IllegalArgumentException
	{
		if (newFont!=null)
		{
			//Set the local property
			this.statusFont=newFont;	
		
			//Set the Status Text Area Font
			this.getBottomPanel().setStatusFont(newFont);
		}
		else
		{
			throw new IllegalArgumentException("Status font is null"); 
		}		
		return;
	}		
	/**
	* Sets the foreground color of the status window.
	*
	* @param	newColor foreground color for status window.
	* @exception java.lang.IllegalArgumentException	When the argument for this setter method is null this exception will be thrown.	
	*/
	public void setStatusForeground(java.awt.Color newColor) throws IllegalArgumentException
	{
		if (newColor!=null)
		{
			//Set the local property
			this.statusForeground=newColor;	
		
			//Set the Status Text Area Foreground color
			this.getBottomPanel().setStatusForeground(newColor);
		}
		else
		{
			throw new IllegalArgumentException("Status Foreground color is null"); 
		}		
		return;
	}		
	/**
	* Sets the background color of all the buttons in the toolbar.
	*
	* @param	newColor the background color for toolbar.
	* @exception java.lang.IllegalArgumentException	When the argument for this setter method is null this exception will be thrown.	
	*/
	public void setToolbarBackground(java.awt.Color newColor) throws IllegalArgumentException
	{
		if (newColor!=null)
		{
			//Set the local property
			this.toolbarBackground=newColor;
			//Call the TopPanel's method
			getTopPanel().setToolbarBackground(newColor);
		}
		else
		{
			throw new IllegalArgumentException("Button Background color is null"); 
		}		
		return;
	}			
	/**
	* Sets the font of all the buttons in the toolbar.
	*
	* @param	newFont the font for the toolbar.
	* @exception java.lang.IllegalArgumentException	When the argument for this setter method is null this exception will be thrown.	
	*/
	public void setToolbarFont(java.awt.Font newFont) throws IllegalArgumentException
	{
		if (newFont!=null)
		{
			//Set the local property
			this.toolbarFont=newFont;
			//Call the TopPanel's method
			getTopPanel().setToolbarFont(newFont);
		}
		else
		{
			throw new IllegalArgumentException("Buttons Font is null"); 
		}		
		return;
	}			
	/**
	* Sets the foreground color of all the buttons in the toolbar.
	*
	* @param	newColor the foreground color for toolbar.
	* @exception java.lang.IllegalArgumentException	When the argument for this setter method is null this exception will be thrown.	
	*/
	public void setToolbarForeground(java.awt.Color newColor) throws IllegalArgumentException
	{
		if (newColor!=null)
		{
			//Set the local property
			this.toolbarForeground=newColor;
			//Call the TopPanel's method
			getTopPanel().setToolbarForeground(newColor);
		}
		else
		{
			throw new IllegalArgumentException("Buttons Foreground color is null"); 
		}		
		return;
	}			
	/**
	* Sets the data transfer type for the data transfer with the FTP server.
	* The transfer mode can be "ASCII" or "BINARY".
	*
	* @param	mode  the data transfer type.
	*/
	public void setType(String mode)
	{
		//Check the parameters
		if (!(mode.equalsIgnoreCase("ASCII")) && !(mode.equalsIgnoreCase("BINARY")))
		{
			//Set the parent
			this.frm=getParentFrame();
			
			InfoDialog id=new InfoDialog(frm,"Set type Error",this,"Enter mode as Ascii or Binary",false);
			
			//Set the textfields' Background in the Dialog
			id.setDialogBackground(super.getBackground());
		
			//Set the Dialog Buttons' Background,Foreground & Font
			id.setButtonsBackground(this.toolbarBackground);
			id.setButtonsForeground(this.toolbarForeground);
			
		
			//Set the Dialog Labels' Foreground & Font
			id.setLabelsForeground(this.labelsForeground);
			
		
			//Set the location of the dialog for showing 
			//in the parent's center
			id.setLocation(this.findCenterPoint(id));
		
			id.setVisible(true);
			return;
		}	
		//Create a vector for parameters
		Vector<String> pmtrs=new Vector<String>();
		
		//Make the parameters 
		pmtrs.addElement(mode);
		
		//Construct the CommandEvent object'
		CommandEvent ce=new CommandEvent(this,"setType",pmtrs,selected);
		
		//Assign the new CommandEvent object to the currentCommand object
		//so that the Protocol Bean can use the getter methods
		this.currentCommand=ce;
		//Send the CommandEvent object
		this.sendCommandEvent(ce);	
		return;
	}	
	/**
	* Sets the value of the view file modified date property.
	* If the boolean argument is true the date associated with file will be displayed.
	*
	* @param	date  decides whether file modification date has to be displayed.
	*/
	public void setViewDate(boolean date)
  {
	  	this.viewDate=date;
	  	this.getMiddlePanel().setViewOptions(viewSize,viewDate,viewTime);
	}	 
	/**
	* Sets the value of the view local file list window property.
	* If the parameter is true the local file list window will be 
	* displayed else it will be hidden.
	*
	* @param	local decides whether the local file list window has to be displayed.
	*/
	public void setViewLocal(boolean local)
  {
	  	this.viewLocal=local;
	  	this.getMiddlePanel().setWindowOptions(viewLocal,viewRemote);
	}	 
	/**
	* Sets the value of the view remote file list window property.
	* If the parameter is true remote file list window will be displayed 
	* else it will be hidden. 
	*
	* @param	remote decides whether the remote file list window has to be displayed.
	*/
	public void setViewRemote(boolean remote)
  {
	  	this.viewRemote=remote;
	  	this.getMiddlePanel().setWindowOptions(viewLocal,viewRemote);
	}	 
	/**
	* Sets the value of the view file size property.
	* If the argument is true the filesize  will be displayed in user interface.
	*
	* @param	size decides whether file size has to be displayed.
	*/
	public void setViewSize(boolean size)
  {
	  	this.viewSize=size;
	  	this.getMiddlePanel().setViewOptions(viewSize,viewDate,viewTime);
	}	 
	/**
	* Sets the value of the  view file modified time property.
	* If the argument is true the file modification time will be displayed.
	*
	* @param	time decides whether file modification time has to be displayed.
	*/
	public void setViewTime(boolean time)
  {
	  	this.viewTime=time;
	  	this.getMiddlePanel().setViewOptions(viewSize,viewDate,viewTime);
	}	 
/**
 * Implements the statusReceived method defined in <a href="com.ibm.network.ftp.event.StatusListener.html"> StatusListener </a>
 * This method receives the <a href="com.ibm.network.ftp.event.StatusEvent.html"> StatusEvent </a> from the sources and
 * and sets the status message in the status window.
 *
 * @param 	se StatusEvent
 * @see		com.ibm.network.ftp.event.StatusListener
 */
public void statusReceived(StatusEvent se) {
	if (se.getMessage()!=null)
	{
		//Get the status message
		String msg=se.getMessage();
		
		//Enable the buttons in the ToolBar
		//After the connection is made
		
		//Get the return code for connection
		
		if (msg.startsWith("220",0))
		{	
			getTopPanel().enableConnectButton(true);
			//getTopPanel().enableRemoteButtons(true);
			//getTopPanel().enableFileButtons(true);
			//this.connected=true;
		}
		if (msg.startsWith("230",0))
		{
			getTopPanel().enableRemoteButtons(true);
			getTopPanel().enableFileButtons(true);
			this.connected=true;
		}
		//Enable the buttons in the ToolBar
		//After Disconnect.	
		if ((msg.startsWith("221",0)) || (msg.startsWith("ERROR: Error reading from  the controlconnection")))
		{	
			getTopPanel().enableConnectButton(false);	
			getTopPanel().enableRemoteButtons(false);
			/*************** Changed on 11/02/98 *********************/
			/* This change was made beacuse while disconnect if local
			  is selected then file buttons should not be disabled */
			if(selected==REMOTE){
				getTopPanel().enableFileButtons(false);
			}	
			/************** End of the change ************************/
			/**************Changed in FTP1.06 dated 9/3/98************/
			/* Clear the remote file list*/
			getMiddlePanel().setRemoteCurrentDir("");
			getMiddlePanel().setRemoteFileList(new Vector());
			/***************End of Change ****************************/
			this.connected=false;			
		}	
		//Call BottomPanel's method
		getBottomPanel().updateStatusWindow(msg);
	
	}	
	//Set the BottomPanel's label
	this.getBottomPanel().updateStatus("Data connection is idle.");
}
	/**
	* Sets the local current working directory.
	* The parameter specifies the new name of the local directory.
	*
	* @param	dirName	the local directory name.
	*/
	public void updateLocalDir(String dirName)
	{
		if (dirName!=null)
		{
			//Call the MiddlePanel's method
			getMiddlePanel().setLocalCurrentDir(dirName);
		}	
		return;
	}	
	/**
	* Sets the local file list.
	* After a LocalFileListEvent is received this method is used to update
	* the local file list.
	*
	* @param	list  the list of local files in current working directory.
	*/
	public void updateLocalList(Vector list)
	{
		if (list!=null)
		{
			//Call the MiddlePanel's method
			getMiddlePanel().setLocalFileList(list);
		}	
		return;
	}	
	/**
	* Sets the remote current working directory.
	* The parameter specifies the new remote working directory.
	*
	* @param	dirName	 the FTP server directory name.
	*/
	public void updateRemoteDir(String dirName)
	{
		if (dirName!=null)
		{
			//Call the MiddlePanel's method
			getMiddlePanel().setRemoteCurrentDir(dirName);
		}	
		return;
	}	
	/**
	* Sets the remote file list.
	* Whenever a new RemoteFileListEvent is received this method is used
	* to update the remote file list.
	*
	* @param	list  the list of files in current working directory of FTP server.
	*/
	public void updateRemoteList(Vector list)
	{
		if (list!=null)
		{
			//Call the MiddlePanel's method
			getMiddlePanel().setRemoteFileList(list);
		}	
		return;
	}	
	/**
	* For updating the window selected property
	* This method will be called from MiddlePanel class
	*
	* @param	remoteSelected	boolean
	*/
	
	void updateSelected(boolean remoteSelected)
	{
		//Set as true if remote,else false
		this.selected=remoteSelected;
		
		//Enable and Disable the Toolbar buttons
		if (remoteSelected)
		{
			getTopPanel().enableFileButtons(this.connected);
		}
		else
		{
			//For Local file list
			getTopPanel().enableFileButtons(true);
		}		
	}	
	/**
	* Sets the status message.
	* Whenever a new StatusEvent is received this method is called to update
	* the status window.
	*
	* @param	status  the FTP connection status.
	*/
	public void updateStatus(String status)
	{
		if (status!=null)
		{
			//Call the BottomPanel's method
			getBottomPanel().updateStatusWindow(status);
		}	
		return;
	}	
	/**
	* Uploads a file from the local machine to the FTP server.
	* File name will be received through a "Put File" dialog box.
	*
	*/
	public void upload()
	{
		//Create a vector for parameters
		Vector files=new Vector();
			
		//Get the selected files in the Remote list
		files=(Vector)getMiddlePanel().getLocalSelectedFiles();
		
		//If nothing selected in the list
		if (files==null || files.isEmpty())
		{
			this.userEntry=null;
			
			//Set the parent
			this.frm=getParentFrame();
			
			EntryDialog ed=new EntryDialog(frm,"Upload Dialog",this,"File name");
			
			//Set the Background of the Dialog
			ed.setDialogBackground(super.getBackground());
									
			//Set the textfields' Background in the Dialog
			//ed.setTextBackground(this.dirSelectedColor);
			
			//Set the Dialog Buttons' Background,Foreground & Font
			ed.setButtonsBackground(this.toolbarBackground);
			ed.setButtonsForeground(this.toolbarForeground);
			
		
			//Set the Dialog Labels' Foreground & Font
			ed.setLabelsForeground(this.labelsForeground);
		
		
			//Set the location of the dialog for showing 
			//in the parent's center
			ed.setLocation(this.findCenterPoint(ed));
		
			ed.show();
		
			if (userEntry!=null && !(userEntry.equals("")))
			{
				//Get the parameter for downloading
				files.addElement(userEntry);
			}
			else	
			{			
				//File name not entered
				return;
			}	
		}	
		for (int i=0;i<files.size();i++)
		{
			Vector pmtr=new Vector();
			pmtr.addElement((String)files.elementAt(i));
			
			//Construct the CommandEvent Object
			CommandEvent ce=new CommandEvent(this,"putFile",pmtr,selected);
		
			//Assign the new CommandEvent object to the currentCommand object
			//so that the Protocol Bean can use the getter methods
			this.currentCommand=ce;
	
			//Send this CommandEvent object to the command listeners
			this.sendCommandEvent(ce);
		}	
		return;
	}	
	/**
	* Uploads a file from the local machine to the FTP server.
	* Selected file name in the localfile list is the parameter to this method.
	* Dialog box will not be invoked for this method.
	*
	* @param	fileName  name of the file to be uploaded.
	*/
	public void upload(String fileName)
	{
		
		//If nothing selected in the list
		if (fileName==null || fileName.equals(""))
		{
			//Set the parent frame
			this.frm=getParentFrame();
			
			//Show the error in InfoDialog & return
			InfoDialog id=new InfoDialog(frm,"Upload Error",this,"File Name is not valid",false);
			
			//Set the Background of the Dialog
			id.setDialogBackground(super.getBackground());
			
			//Set the Dialog Buttons' Background,Foreground & Font
			id.setButtonsBackground(this.toolbarBackground);
			id.setButtonsForeground(this.toolbarForeground);
			
			//Set the Dialog Labels' Foreground & Font
			id.setLabelsForeground(this.labelsForeground);
			
		
			//Set the location of the dialog for showing 
			//in the parent's center
			id.setLocation(this.findCenterPoint(id));
		
			id.show();
			return;
		}	
		
		//Create a vector for parameters
		Vector files=new Vector();
		
		//Get the selected files in the Remote list
		files.addElement(fileName);
		
		//Construct the CommandEvent Object
		CommandEvent ce=new CommandEvent(this,"putFile",files,selected);
		
		//Assign the new CommandEvent object to the currentCommand object
		//so that the Protocol Bean can use the getter methods
		this.currentCommand=ce;
		//Send this CommandEvent object to the command listeners
		this.sendCommandEvent(ce);
		return;
	}	
}
