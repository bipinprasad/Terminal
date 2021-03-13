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
 * Contains information about the files in the current working directory of FTP
 * server. The current remote working directory name and a vector of FileInfo is
 * stored in it. The Vector of FileInfo contains details of each file in current
 * working directory of the FTP server. The protocol creates this event and
 * throws it to the user interface.
 * 
 * @see com.ibm.network.ftp.FileInfo
 */
public class RemoteFileListEvent extends java.util.EventObject {
	private static final long serialVersionUID = -2292928739078532159L;
	private java.util.Vector<FileInfo> fileInfo;
	private String currentRemoteDir;

	/**
	 * Constructs RemoteFileListEvent with the specified source object..
	 * 
	 * @param source
	 *            the object where the event originated.
	 */
	public RemoteFileListEvent(Object source) {
		super(source);
	}

	/**
	 * Constructs RemoteFileListEvent with specified source object and
	 * initializes it. This constructor creates the RemoteFileListEvent and sets
	 * the value of remote current directory and the list of files in it.
	 * 
	 * @param source
	 *            the object where the event originated.
	 * @param fileInfo
	 *            vector of FileInfo objects.
	 * @param currentDir
	 *            current working directory on FTP server.
	 */
	public RemoteFileListEvent(Object source,
			java.util.Vector<FileInfo> fileInfo, String currentDir) {
		super(source);
		this.fileInfo = fileInfo;
		this.currentRemoteDir = currentDir;

	}

	/**
	 * Returns current remote (FTP server) working directory.
	 * 
	 * @return current working directory on FTP server.
	 */
	public java.lang.String getRemoteDir() {
		return this.currentRemoteDir;
	}

	/**
	 * Returns list of files in current remote (FTP server) working directory.
	 * The list is returned as Vector of FileInfo class
	 * 
	 * @return vector of FileInfo objects.
	 * @see com.ibm.network.ftp.FileInfo
	 */
	public java.util.Vector<FileInfo> getRemoteFileInfo() {
		return this.fileInfo;
	}

	/**
	 * Sets the remote (FTP server) current working directory.
	 * 
	 * @param currentDir
	 *            current working directory on FTP server.
	 */
	public void setRemoteCurrentDir(java.lang.String currentDir) {
		this.currentRemoteDir = currentDir;
		return;
	}

	/**
	 * Sets the list of files in current remote(FTP server) working directory.
	 * The method takes a Vector of FileInfo class.
	 * 
	 * @param fileInfo
	 *            vector of FileInfo objects.
	 * @see com.ibm.network.ftp.FileInfo
	 */
	public void setRemoteFileInfo(java.util.Vector<FileInfo> fileInfo) {
		this.fileInfo = fileInfo;
		return;
	}
}
