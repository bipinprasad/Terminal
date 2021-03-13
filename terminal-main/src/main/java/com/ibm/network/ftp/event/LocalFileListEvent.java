/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp.event;
import com.ibm.network.ftp.FileInfo;
/**
 * Contains information about files in the current working directory of local machine.
 * The current local working directory name and a vector of FileInfo is stored in it.
 * The Vector of FileInfo contains details of each file in current working directory
 * of local machine.
 * The protocol creates this event and throws it to the user interface.
 *
 * @see com.ibm.network.ftp.FileInfo 
 */
public class LocalFileListEvent extends java.util.EventObject {
	private static final long serialVersionUID = 6340061292359442395L;
	java.util.Vector<FileInfo> fileInfo;
	String currentLocalDir;
/**
 * Constructs an LocalFileListEvent object with specified source object.
 *
 * @param source the object where the event originated.
 */
public LocalFileListEvent(Object source) {
	super(source);
}
/**
 * Constructs a LocalFileListEvent with specified source object and initializes it.
 * This constructor creates the LocalFileListEvent and sets the value of
 * local current directory and the list of files in it.
 *
 * @param	source	the object where the event originated.				
 * @param	fileInfo  vector of FileInfo objects.
 * @param 	currentLocalDirectory  current working directory on local machine.	
 
 */
public LocalFileListEvent (Object source,java.util.Vector<FileInfo> fileInfo,String currentLocalDir ) {
	super(source);
	this.fileInfo=fileInfo;
	this.currentLocalDir=currentLocalDir;
	
}
/**
 * Returns current local working directory.
 *
 * @return current working directory on local machine.
 */
public String getLocalDir( ) {
	return this.currentLocalDir;
}
/**
 * Returns the list of files in current local working directory.
 * The list is returned as Vector of FileInfo class
 *
 * @return vector of FileInfo objects.
 * @see com.ibm.network.ftp.FileInfo
 */
public java.util.Vector<FileInfo> getLocalFileInfo ( ) {
	
	return this.fileInfo;
}
/**
 * Sets the local current working directory.
 *.
 * @param dirName current working directory on local machine.
 */
public void setCurrentLocalDir (String dirName ) {
	this.currentLocalDir=dirName;
	return;
}
/**
 * Sets the list of files in current local working directory.
 * The method takes Vector of FileInfo.
 *
 * @param fileInfo vector of FileInfo objects.
 * @see		com.ibm.network.ftp.FileInfo
 */
public void setLocalFileInfo( java.util.Vector<FileInfo> fileInfo) {
	this.fileInfo=fileInfo;
	return;
}
}
