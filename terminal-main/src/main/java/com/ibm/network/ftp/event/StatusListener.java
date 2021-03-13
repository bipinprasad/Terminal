/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp.event;
/**
 * Listener interface for StatusEvent.
 * 
 */
public interface StatusListener extends java.util.EventListener {
/**
 * Invoked when a StatusEvent occurs. 
 *
 * @param StatusEvent passed from the StatusEvent source to the StatusListener.
 */
public void statusReceived(StatusEvent event );
}
