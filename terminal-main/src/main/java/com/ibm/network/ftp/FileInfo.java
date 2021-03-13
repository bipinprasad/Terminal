/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp;
import java.io.File;
import java.util.StringTokenizer;
/**
 * This class stores File information.
 * For each of the file, FTPProtocol class needs to store additional
 * information like size, date and time. This information has to be sent 
 * to any User Interface components registered with the FTPProtocol class.
 * This class has getter and setter methods for time, date,size,name and file type. 
 *
 * @author  Rajesh Singh
 * @version 
 * @see 	com.ibm.network.ftp.protocol.FTPProtocol
 * @see		com.ibm.network.ftp.ui.FTPUI
 */
public class FileInfo {
	
	/**
	 * Name of the file.
	 * Every FileInfo class represents a File. This variable 
	 * stores the name of the file.
	 */
	private String fileName=null;
	
	/**
	 * Size of the file
	 * This variable stores the size of the file represented 
	 * by this FileInfo class.
	 */
	private String size=null;
	
	/**
	 * Date of the file
	 * This variable stores the date of the file represented
	 * by this FileInfo class.
	 */
	private String date=null;
	
	/**
	 * Time of the file
	 * This variable stores the time of the file represented 
	 * by this FileInfo class.
	 */
	private String time=null;
	
	/**
	 * File or directory.
	 * This boolean variable decides whether the file represented 
	 * by this fileInfo class is a regular file or directory.
	 * If file is true then it is regular file else directory.
	 */
	private boolean file;
/**
 * Creates an object of FileInfo class.
 * The fields of the FileInfo object are initially null, they must be initialized
 * using appropriate setter methods.
 */
public FileInfo ( ) {
}
/**
 * Returns the last file modification date.
 * The date is returned as a String.
 *
 * @return the last modified date of the file represented by this FileInfo object.
 */
public java.lang.String getDate() {
	return (String)date;
}
/**
 * Returns the name of the file.
 * The name of the file that this FileInfo object represents is returned
 * by this method.
 *
 * @return the name of the file represented by this FileInfo object.
 */
public java.lang.String getName ( ) {
	return fileName;
}
/**
 * Returns the size of the file represented by this FileInfo object.
 * The size is returned as a String.
 * 
 * @return the size of the file represented by this FileInfo object.
 */
public java.lang.String getSize ( ) {
	return size;
}
/**
 * Returns the time the file was last modified.
 * The time is returned as a String.
 * 
 * @return the time at which the file represented by this FileObject was last modified.
 */
public java.lang.String getTime() {
	return (String)time;
}
/**
 * Returns whether a regular file or directory.
 * This method identifies the type ( regular file or directory) of the
 * file represented by this FileInfo object.
 *
 * @return whether the file represented by this FileInfo object is a simple file or directory.
 * 			true: if it is regular file.
 * 			false: if it is a directory.
 *	
 */
public boolean isFile ( ) {
	return file;
}
/**
 * Set the last modification date of the file.
 * This method will set the date of the file to the String parameter 
 * passed as the argument.
 *
 * @param date The last modified date of the file represented by this FileInfo object.
 */
public void setDate(java.lang.String date) {
	this.date=date;
	return;
}
/**
 * Sets the file type (regular file or directory).
 * This method will set the file type depending on the boolean
 * argument passed as parameter.
 * If the argument is true then the type is set to regular file.
 * If the argument is false then type is set to directory.
 *
 * @param file  whether the file represented by this FileInfo object is a simple file or directory.
 *				true: if it is regular file.
 * 				false: if it is a directory.
 */
public void  setFile (boolean file ) {
	this.file=file;
	return;
}
/**
 * This method will set the attributes for a local file.
 * Given the file name and path name as String parameters this method
 * will set all the attributes(name, size,time,date and file type)
 * of the named file.
 *
 * @param fileName name of the file whose description has to be set.
 * @param pathName path of the file whose description has to be set.
 */
public void setLocalDescription(String fileName,String pathName ) {
	//Create the file object with the given file name
	File file=new File(pathName,fileName);
	
	//set the name for the file
	this.setName(fileName);
	
	//Check whether the file is regular file or directory
	this.setFile(file.isFile());
	
	//Set the size of the file
	Long size=new Long(file.length());
	this.setSize(size.toString());
	
	//Set the date for the file
	java.util.Date date=new java.util.Date(file.lastModified());
	StringTokenizer stringToken=new StringTokenizer(date.toString());
	
	//neglect the first token which is day of the week
	stringToken.nextToken();
	
	//Next two token taken together represent the date
	this.setDate(stringToken.nextToken()+" "+stringToken.nextToken());
	
	//the next token is the time
	this.setTime(stringToken.nextToken());
	return;
}
/**
 * Sets the name of the file represented by this FileInfo object.
 *
 * @param fileName name of the file represented by this FileInfo object.
 */
public void setName(java.lang.String fileName) {
	this.fileName=fileName;
	return;
}
/**
 * This method will set attributes for a remote file.
 * Given a line of reply received as the result of "LIST" command,
 * this method will set all the attributes(name,size,time,date and file type)
 * of the named file. This method requires the reply to be in UNIX
 * (FTP server) format. For example,
 * " drwxr-xr-x	  2	 guest	other  1536  Jan 31 15:15  run.bat"
 *
 * @param reply reply of FTP server for "dir" command.
 */
public void setRemoteDescriptionUNIX(String reply ) {
	//reply represents each line read while listing.
	String tempString=null;
	String date=null;
	java.util.StringTokenizer strToken;
	
	if(reply==null){
	
		//send some message saying that reply is null.
		//hence nothing can be done.
	}//end of if
	else {
		
		//tokenize the reply using the space character. 
		strToken=new java.util.StringTokenizer(reply);
		//read first token which is access rights and whether
		//it is file or directory.
		tempString=strToken.nextToken();
		if(tempString.charAt(0)=='d'){
			this.setFile(false);
		}//end of if
		else if(tempString.charAt(0)=='-'){
			this.setFile(true);
		}//end of else if
		setPermissions(tempString.substring(1));
		
		//next token is not required
		strToken.nextToken();
		
		//next token is the owner
		setOwner(strToken.nextToken());


		//next token is the group owner that is not required
		setGroup(strToken.nextToken());
		
		//next token is the size which we need to set
		this.setSize(strToken.nextToken());
		
		//next to token represent the month and date which together 
		//will be represented as date
		date=new String(strToken.nextToken()+"  "+strToken.nextToken());
		this.setDate(date);
		
		//next token is either date or time
		this.setTime(strToken.nextToken());

		//next token is the file name
		this.setName(strToken.nextToken());
	}
}	
/**
 * This method will set attributes for a remote file.
 * Given a line of reply received as the result of "LIST" command,
 * this method will set all the attributes(name, size,time and date)
 * of the named file. This method requires the reply to be in  VM
 * (FTP server) format. For example,
 * " GATEWAY  RULES  V  76  86  1  1/17/98  22:30:59  ADISK"
 *
 * @param reply reply of FTP server for "dir" command.
 */
public void setRemoteDescriptionVM( String reply) {
	//reply represents each line read while listing.
	java.util.StringTokenizer strToken;

	if(reply==null){
	
		//send some message saying that reply is null.
		//hence nothing can be done.
	}//end of if
	else {
		
		//tokenize the reply using the space character. 
		strToken=new java.util.StringTokenizer(reply);
		
		//Set whether file or directory
		this.setFile(true);
		
		//First token is the file name and next is the extension append them
		
		this.setName(strToken.nextToken()+"."+strToken.nextToken());
		
		
		//next token is the owner
		setOwner(strToken.nextToken());

		//next token is the size which we need to set
		this.setSize(strToken.nextToken());

		//this token not required
		strToken.nextToken();

		//this token not required
		strToken.nextToken();


		//next to token represent the month and date which together
		//will be represented as date
		//date=new String(strToken.nextToken()+"  "+strToken.nextToken());
		this.setDate(strToken.nextToken());

		//next token is either date or time
		this.setTime(strToken.nextToken());


	}
	return;
}
/**
 * Sets the size of the file represented by this FileInfo object. 
 * This method sets the size of the file to the argument passed
 * to it as a String.
 *
 * @param size size of the file represented by this FileInfo object.
 */
public void setSize (java.lang.String size ) {
	this.size=size;
	return;
}
/**
 * Sets the time of the file represented by this FileInfo object.
 * It takes a string argument.
 *
 * @param time time at which  the file represented by this FileInfo object was last modified.
 */
public void setTime ( java.lang.String time) {
	this.time=time;
	return;
}
  private String owner;
  private String group;
  private String permissions;
  public void setOwner(String str) {owner = str;}
  public String getOwner() {return owner;}
  public void setGroup(String str) {group = str;}
  public String getGroup() {return group;}
  public void setPermissions(String str) {permissions = str;}
  public String getPermissions() {return permissions;}

  public String toFormattedString(
		StringBuffer			sb,	// to reduce object creation: This is used if supplied
		int fileNameFormatLength,
		int fileSizeFormatLength,
		int fileDateFormatLength,
		int fileTimeFormatLength)
  {
	int		tmpLen;
	
	if (sb == null)
		sb = new StringBuffer(100);
	else
		sb.setLength(0);

	if (fileNameFormatLength > 0)
	{
		tmpLen = fileName.length();
		int numSpaces = fileNameFormatLength - tmpLen;
		if (numSpaces < 0)
			sb.append(fileName.substring(0,fileNameFormatLength));
		else
		{
			sb.append(fileName);
			for (int i = 0 ; i < numSpaces ; i++)
				sb.append(' ');
		}
	}

	if (fileSizeFormatLength > 0)
	{
		tmpLen = size.length();
		int numSpaces = fileSizeFormatLength - tmpLen;
		if (numSpaces < 0)
			sb.append(size.substring(0,fileSizeFormatLength));
		else
		{
			sb.append(size);
			for (int i = 0 ; i < numSpaces ; i++)
				sb.append(' ');
		}
	}

	if (fileDateFormatLength > 0)
	{
		tmpLen = date.length();
		int numSpaces = fileDateFormatLength - tmpLen;
		if (numSpaces < 0)
			sb.append(date.substring(0,fileDateFormatLength));
		else
		{
			sb.append(date);
			for (int i = 0 ; i < numSpaces ; i++)
				sb.append(' ');
		}
	}

	if (fileTimeFormatLength > 0)
	{
		tmpLen = time.length();
		int numSpaces = fileTimeFormatLength - tmpLen;
		if (numSpaces < 0)
			sb.append(time.substring(0,fileTimeFormatLength));
		else
		{
			sb.append(time);
			for (int i = 0 ; i < numSpaces ; i++)
				sb.append(' ');
		}
	}
	return sb.toString();
  }
}
