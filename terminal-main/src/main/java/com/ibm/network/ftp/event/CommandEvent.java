/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp.event;
import java.util.Vector;
/**
 * This class represents a command.
 * The command name, parameters and whether it is for local machine or
 * remote server are stored as the attributes of the command event.
 * The user interface creates this event and throws it to the protocol.
 *
 */
public class CommandEvent extends java.util.EventObject {
	private static final long serialVersionUID = -2432417427720675880L;

	/**
	* Command for connecting to the remote server.
	*/
	public static final String CONNECT="connect";
	
	/**
	* Command for login to the remote server.
	*/
	public static final String LOGIN="login";
	/**
	* Command for disconnecting from the remote server.
	*/
	public static final String DISCONNECT="disconnect";
	
	/**
	* Command for setting the transfer type. 
	*/
	public static final String SET_TYPE="setType";
	
	/**
	* Command for creating a new directory. 
	*/
	public static final String MAKE_DIR="makeDir";
	
	/**
	* Command for changing the working directory.
	*/
	public static final String CHANGE_DIR="changeDir";
	
	/**
	* Command for deleting a directory.
	*/
	public static final String REMOVE_DIR="removeDir";
	
	/**
	* Command for downloading a file from the Remote server
	* to the local machine.
	*/
	public static final String DOWNLOAD="getFile";
	
	/**
	* Command for uploading a file from the local machine
	* to the remote server.
	*/
	public static final String UPLOAD="putFile";
	
	/**
	* Command for deleting a file.
	*/
	public static final String DELETE_FILE="deleteFile";
	
	/**
	* Command for renaming a file or directory.
	*/
	public static final String RENAME="rename";
	
	/**
	* Command for getting the Server status.
	*/
	public static final String STATUS="getStatus";
	
	/**
	* Command for configuring the Socks Server.
	*/
	public static final String SOCKS="configureSocks";
	
	/**
	* Command for stopping an executing command.
	*/
	public static final String ABORT="abort";
	private String command=null;
	private Vector<String> parameters=null;
	//this boolean tells whether the command is for remote
	//or the local protocol.
	private boolean remote;
	
/**
 * Constructs a CommandEvent object with the specified source object.
 *
 * @param source java.lang.Object
 */
public CommandEvent(Object source) {
	super(source);
}
/**
 * Constructs a CommandEvent object and initializes it.
 * Sets the value of command name, parameters and whether the
 * command is for local machine or remote server.
 * 
 * @param source the object where the event originated.
 * @param command name of the command which this CommandEvent object represents.
 * @param parameters parameters of the command which this CommandEvent object represents.
 * @param remote whether the command is for local machine or FTP server.
 *				true: command for FTP server.
 *				false: command for local machine.	
 */
public CommandEvent (Object source,String command,java.util.Vector<String> parameters,boolean remote ) {
	super(source);
	this.command=command;
	this.parameters=parameters;
	this.remote=remote;
}
/**
 * Returns the command name.
 *
 * @return the command name.
 */
public String getCommand( ) {
	return this.command;
}
/**
 * Returns the command parameters.
 * The command parameters are returned as a Vector of String Objects.
 *
 * @return parameters of the command.
 */
public java.util.Vector<String> getParameters ( ) {
	return parameters;
}
/**
 * Returns whether the command is for FTP server or local machine. 
 *
 * @return true if the CommandEvent is for FTP server; false if it is for local machine.
 */
public boolean isRemote ( ) {
	return remote;
}
/**
 * Sets the command name.
 *
 * @param command the name of the command.
 */
public void setCommand( String command) {
	this.command=command;
}
/**
 * Sets the command parameters.
 * This method takes a Vector of Strings as parameter.
 *
 * @param parameters parameters for the command.
 */
public void setParameters(java.util.Vector<String> parameters ) {
	this.parameters=parameters;
}
/**
 * Sets whether command is for FTP server or local machine.
 *
 * @param remote whether the command is for local machine or FTP server.
 *			true: command for FTP server
 *			false: command for local machine
 */
public void setRemote(boolean remote ) {
	this.remote=remote;
}
}
