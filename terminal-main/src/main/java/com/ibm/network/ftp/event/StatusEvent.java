/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp.event;
/**
 * This class stores the status of FTP server connection.
 * The status information is stored as a message String.
 * The protocol creates this event and throws it to the user interface.
 *
 */
public class StatusEvent extends java.util.EventObject {
	private static final long serialVersionUID = 1502582850782489439L;
	private String message;
/**
 * Constructs StatusEvent with the specified source object.
 *
 * @param source the object where the event originated.
 */
public StatusEvent(Object source) {
	super(source);
}
/**
 * Constructs StatusEvent with the specified source object and initializes it.
 * This sets the status message for the  StatusEvent 
 *
 * @param source the object where the event originated.
 * @param message status of FTP connection.
 */
public StatusEvent ( Object source,String message) {
	super(source);
	this.message=message;
}
/**
 * Returns the status of FTP connection as a String.  
 *
 * @return the status of FTP connection.
 */
public java.lang.String getMessage( ) {
	return message;
}
/**
 * Sets the status of FTP connection for the StatusEvent. 
 *
 * @param message the status of FTP connection.
 */
public void setMessage(java.lang.String message ) {
	this.message=message;
}
}
