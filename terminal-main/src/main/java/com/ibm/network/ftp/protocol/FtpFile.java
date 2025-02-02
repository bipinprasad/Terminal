/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp.protocol;
/**
 * This class was generated by a SmartGuide.
 * 
 */
class FtpFile {
	private String fileName;
	private Protocol protocol;
/**
 * This method was created by a SmartGuide.
 * @param fileName java.lang.String
 * @param protocol FTPProtocolBean.Protocol
 */
public FtpFile ( String fileName,Protocol protocol) {
	this.fileName=fileName;
	this.protocol=protocol;
}
/**
 * This method was created by a SmartGuide.
 */
public void deleteFile( ) {
	//check that both protocol and filename are not null
	if(protocol!=null && this.fileName!=null){
		protocol.deleteFile(this.fileName);
	}	
	return;
}
/**
 * This method was created by a SmartGuide.
 */
public void getFile ( ) {
	return;
}
/**
 * This method was created by a SmartGuide.
 */
public void putFile ( ) {
	return;
}
/**
 * This method was created by a SmartGuide.
 */
public void renameFile(String newName ) {
	//check that both protocol and dirname are not null
	if(protocol!=null && this.fileName!=null && newName!=null){
		protocol.rename(this.fileName,newName);
	}	
	return;
}
}
