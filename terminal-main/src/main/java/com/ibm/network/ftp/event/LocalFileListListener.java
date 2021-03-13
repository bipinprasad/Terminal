/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp.event;
/**
 * Listener interface for LocalFileListEvent.
 * 
 */
public interface LocalFileListListener extends java.util.EventListener {
/**
 * Invoked when a LocalFileListEvent occurs. 
 *
 * @param LocalFileListEvent passed from the LocalFileListEvent source to the LocalFileListListener.
 */
public void localFileListReceived (LocalFileListEvent event );
}
