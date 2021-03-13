/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp.event;
/**
 * Listener interface for RemoteFileListEvent.
 * 
 */
public interface RemoteFileListListener extends java.util.EventListener {
/**
 * Invoked when a RemoteFileListEvent occurs. 
 *
 * @param RemoteFileListEvent passed from the RemoteFileListEvent source to RemoteFileListListener.
 */
public void remoteFileListReceived(RemoteFileListEvent event );
}
