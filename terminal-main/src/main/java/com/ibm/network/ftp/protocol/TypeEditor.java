/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp.protocol;
import java.beans.*;
public class TypeEditor extends PropertyEditorSupport {
		 public String getAsText() {
				// Should localize this.
				if (((String)getValue()).equals("ASCII")) {
						return ("ASCII");
				} else {
						return ("BINARY");
				}
		}
		public String[] getTags() {
				String result[] = { "ASCII", "BINARY" };
				return result;
		}
		public void setAsText(String text) throws IllegalArgumentException {
				if (text.equals("ASCII")) {
						setValue("ASCII");
				} else if (text.equals("BINARY")) {
						setValue("BINARY");
				} else {
						throw new java.lang.IllegalArgumentException(text);
				}
		}
}
