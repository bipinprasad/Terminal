/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp.ui;
/**
 * The bean information class for com.ibm.network.ftp.ui.FTPUI.
 * @see com.ibm.network.ftp.ui.FTPUI
 */
public class FTPUIBeanInfo extends java.beans.SimpleBeanInfo {
/**
 * Gets the abort() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor abortMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the abort() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("abort", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "abort", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("abort()"); */
		/* aDescriptor.setShortDescription("abort()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the addCommandListener(com.ibm.network.ftp.event.CommandListener) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor addCommandListener_comibmnetworkftpeventCommandListenerMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the addCommandListener(com.ibm.network.ftp.event.CommandListener) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.CommandListener.class
			};
			aMethod = getBeanClass().getMethod("addCommandListener", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "addCommandListener", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("aListener");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("addCommandListener(com"); */
		aDescriptor.setShortDescription("addCommandListener(com.ibm.intercept.ftp.event.CommandListener)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the background property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor backgroundPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the background property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getBackground", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getBackground", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.awt.Color.class
				};
				aSetMethod = getBeanClass().getMethod("setBackground", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setBackground", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("background"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("background"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("Background");
		aDescriptor.setShortDescription("Background");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the changeDir(java.lang.String) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor changeDir_javalangStringMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the changeDir(java.lang.String) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class
			};
			aMethod = getBeanClass().getMethod("changeDir", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "changeDir", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("dirName");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("changeDir(java"); */
		aDescriptor.setShortDescription("changeDir(java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the changeDir() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor changeDirMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the changeDir() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("changeDir", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "changeDir", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("changeDir()"); */
		/* aDescriptor.setShortDescription("changeDir()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the changeFocus() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor changeFocusMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the changeFocus() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("changeFocus", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "changeFocus", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("changeFocus()"); */
		/* aDescriptor.setShortDescription("changeFocus()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the checkStatus() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor checkStatusMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the checkStatus() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("checkStatus", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "checkStatus", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("checkStatus()"); */
		/* aDescriptor.setShortDescription("checkStatus()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the clearStatusWindow() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor clearStatusWindowMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the clearStatusWindow() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("clearStatusWindow", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "clearStatusWindow", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("clearStatusWindow()"); */
		/* aDescriptor.setShortDescription("clearStatusWindow()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the command.commandPerformed(com.ibm.network.ftp.event.CommandEvent) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor commandcommandPerformed_comibmnetworkftpeventCommandEventMethodEventDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the command.commandPerformed(com.ibm.network.ftp.event.CommandEvent) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.CommandEvent.class
			};
			aMethod = (com.ibm.network.ftp.event.CommandListener.class).getMethod("commandPerformed", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod((com.ibm.network.ftp.event.CommandListener.class), "commandPerformed", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("cevent");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		aDescriptor.setDisplayName("commandPerformed(com.ibm.network.ftp.event.CommandEvent)");
		aDescriptor.setShortDescription("commandPerformed(com.ibm.network.ftp.event.CommandEvent)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the command event set descriptor.
 * @return java.beans.EventSetDescriptor
 */
public java.beans.EventSetDescriptor commandEventSetDescriptor() {
	java.beans.EventSetDescriptor aDescriptor = null;
	try {
		try {
			/* Try using method descriptors to create the command event set descriptor. */
			java.beans.MethodDescriptor eventMethodDescriptors[] = {
				commandcommandPerformed_comibmnetworkftpeventCommandEventMethodEventDescriptor()			};
			java.lang.reflect.Method anAddMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class anAddMethodParameterTypes[] = {
					com.ibm.network.ftp.event.CommandListener.class
				};
				anAddMethod = getBeanClass().getMethod("addCommandListener", anAddMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				anAddMethod = findMethod(getBeanClass(), "addCommandListener", 1);
			};
			java.lang.reflect.Method aRemoveMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aRemoveMethodParameterTypes[] = {
					com.ibm.network.ftp.event.CommandListener.class
				};
				aRemoveMethod = getBeanClass().getMethod("removeCommandListener", aRemoveMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aRemoveMethod = findMethod(getBeanClass(), "removeCommandListener", 1);
			};
			aDescriptor = new java.beans.EventSetDescriptor(
						"command", 
						com.ibm.network.ftp.event.CommandListener.class, 
						eventMethodDescriptors, anAddMethod, aRemoveMethod);
		} catch (java.lang.Throwable exception) {
			/* Using method descriptors failed, try using the methods names. */
			handleException(exception);
			java.lang.String eventMethodNames[] = {
				"commandPerformed"			};
			aDescriptor = new java.beans.EventSetDescriptor(getBeanClass(), 
						"command", 
						com.ibm.network.ftp.event.CommandListener.class, 
						eventMethodNames, 
						"addCommandListener", 
						"removeCommandListener");
		};
		aDescriptor.setUnicast(true);
		/* aDescriptor.setDisplayName("command"); */
		/* aDescriptor.setShortDescription("command"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the configureSocks() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor configureSocksMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the configureSocks() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("configureSocks", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "configureSocks", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("configureSocks()"); */
		/* aDescriptor.setShortDescription("configureSocks()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the connect() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor connectMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the connect() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("connect", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "connect", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("connect()"); */
		/* aDescriptor.setShortDescription("connect()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the defaultToolbar property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor defaultToolbarPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the defaultToolbar property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("isDefaultToolbar", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "isDefaultToolbar", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					boolean.class
				};
				aSetMethod = getBeanClass().getMethod("setDefaultToolbar", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setDefaultToolbar", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("defaultToolbar"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("defaultToolbar"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("Default Toolbar");
		aDescriptor.setShortDescription("Default Toolbar");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the deleteFile() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor deleteFileMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the deleteFile() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("deleteFile", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "deleteFile", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("deleteFile()"); */
		/* aDescriptor.setShortDescription("deleteFile()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the dirDeselectedColor property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor dirDeselectedColorPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the dirDeselectedColor property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getDirDeselectedColor", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getDirDeselectedColor", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.awt.Color.class
				};
				aSetMethod = getBeanClass().getMethod("setDirDeselectedColor", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setDirDeselectedColor", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("dirDeselectedColor"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("dirDeselectedColor"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("Directory Deselected Color");
		aDescriptor.setShortDescription("Directory Deselected Color");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the dirSelectedColor property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor dirSelectedColorPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the dirSelectedColor property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getDirSelectedColor", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getDirSelectedColor", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.awt.Color.class
				};
				aSetMethod = getBeanClass().getMethod("setDirSelectedColor", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setDirSelectedColor", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("dirSelectedColor"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("dirSelectedColor"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("Directory Selected Color");
		aDescriptor.setShortDescription("Selected Directory Color");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the disconnect() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor disconnectMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the disconnect() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("disconnect", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "disconnect", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("disconnect()"); */
		/* aDescriptor.setShortDescription("disconnect()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the download(java.lang.String) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor download_javalangStringMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the download(java.lang.String) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class
			};
			aMethod = getBeanClass().getMethod("download", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "download", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("fileName");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("download(java"); */
		aDescriptor.setShortDescription("download(java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the download() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor downloadMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the download() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("download", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "download", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("download()"); */
		/* aDescriptor.setShortDescription("download()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Find the method by comparing (name & parameter size) against the methods in the class.
 * @return java.lang.reflect.Method
 * @param aClass java.lang.Class
 * @param methodName java.lang.String
 * @param parameterCount int
 */
public static java.lang.reflect.Method findMethod(java.lang.Class aClass, java.lang.String methodName, int parameterCount) {
	try {
		/* Since this method attempts to find a method by getting all methods from the class, */
		/* this method should only be called if getMethod can not find the method. */
		java.lang.reflect.Method methods[] = aClass.getMethods();
		for (int index = 0; index < methods.length; index++){
			java.lang.reflect.Method method = methods[index];
			if ((method.getParameterTypes().length == parameterCount) && (method.getName().equals(methodName))) {
				return method;
			};
		};
	} catch (java.lang.Throwable exception) {
		return null;
	};
	return null;
}
/**
 * Gets the getBackground() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getBackgroundMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getBackground() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getBackground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getBackground", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getBackground()"); */
		/* aDescriptor.setShortDescription("getBackground()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the bean class.
 * @return java.lang.Class
 */
public static java.lang.Class getBeanClass() {
	return com.ibm.network.ftp.ui.FTPUI.class;
}
/**
 * Gets the bean class name.
 * @return java.lang.String
 */
public static java.lang.String getBeanClassName() {
	return "com.ibm.network.ftp.ui.FTPUI";
}
/**
 * Gets the getCommandName() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getCommandNameMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getCommandName() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getCommandName", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getCommandName", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getCommandName()"); */
		/* aDescriptor.setShortDescription("getCommandName()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getCommandParameters() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getCommandParametersMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getCommandParameters() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getCommandParameters", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getCommandParameters", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getCommandParameters()"); */
		/* aDescriptor.setShortDescription("getCommandParameters()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getDirDeselectedColor() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getDirDeselectedColorMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getDirDeselectedColor() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getDirDeselectedColor", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getDirDeselectedColor", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getDirDeselectedColor()"); */
		/* aDescriptor.setShortDescription("getDirDeselectedColor()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getDirSelectedColor() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getDirSelectedColorMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getDirSelectedColor() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getDirSelectedColor", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getDirSelectedColor", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getDirSelectedColor()"); */
		/* aDescriptor.setShortDescription("getDirSelectedColor()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Return the event set descriptors for this bean.
 * @return java.beans.EventSetDescriptor[]
 */
@Override
public java.beans.EventSetDescriptor[] getEventSetDescriptors() {
	try {
		java.beans.EventSetDescriptor aDescriptorList[] = {
			commandEventSetDescriptor()
		};
		return aDescriptorList;
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return null;
}
@Override
public java.awt.Image getIcon(int iconKind) {
	java.awt.Image image = null;
	if (iconKind == java.beans.BeanInfo.ICON_COLOR_16x16) {
		image = loadImage("uic1616.gif");
	};
	if (iconKind == java.beans.BeanInfo.ICON_COLOR_32x32) {
		image = loadImage("uic3232.gif");
	};
	return image;
}
/**
 * Gets the getLabelsFont() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getLabelsFontMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getLabelsFont() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getLabelsFont", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getLabelsFont", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getLabelsFont()"); */
		/* aDescriptor.setShortDescription("getLabelsFont()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getLabelsForeground() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getLabelsForegroundMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getLabelsForeground() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getLabelsForeground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getLabelsForeground", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getLabelsForeground()"); */
		/* aDescriptor.setShortDescription("getLabelsForeground()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getListsBackground() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getListsBackgroundMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getListsBackground() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getListsBackground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getListsBackground", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getListsBackground()"); */
		/* aDescriptor.setShortDescription("getListsBackground()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getListsForeground() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getListsForegroundMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getListsForeground() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getListsForeground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getListsForeground", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getListsForeground()"); */
		/* aDescriptor.setShortDescription("getListsForeground()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Return the method descriptors for this bean.
 * @return java.beans.MethodDescriptor[]
 */
@Override
public java.beans.MethodDescriptor[] getMethodDescriptors() {
	try {
		java.beans.MethodDescriptor aDescriptorList[] = {
			abortMethodDescriptor()
			,addCommandListener_comibmnetworkftpeventCommandListenerMethodDescriptor()
			,changeDir_javalangStringMethodDescriptor()
			,changeDirMethodDescriptor()
			,changeFocusMethodDescriptor()
			,checkStatusMethodDescriptor()
			,clearStatusWindowMethodDescriptor()
			,configureSocksMethodDescriptor()
			,connectMethodDescriptor()
			,deleteFileMethodDescriptor()
			,disconnectMethodDescriptor()
			,download_javalangStringMethodDescriptor()
			,downloadMethodDescriptor()
			,getBackgroundMethodDescriptor()
			,getCommandNameMethodDescriptor()
			,getCommandParametersMethodDescriptor()
			,getDirDeselectedColorMethodDescriptor()
			,getDirSelectedColorMethodDescriptor()
			,getLabelsFontMethodDescriptor()
			,getLabelsForegroundMethodDescriptor()
			,getListsBackgroundMethodDescriptor()
			,getListsForegroundMethodDescriptor()
			,getStatusBackgroundMethodDescriptor()
			,getStatusFontMethodDescriptor()
			,getStatusForegroundMethodDescriptor()
			,getToolbarBackgroundMethodDescriptor()
			,getToolbarFontMethodDescriptor()
			,getToolbarForegroundMethodDescriptor()
			,isDefaultToolbarMethodDescriptor()
			,isRemoteSelectedMethodDescriptor()
			,isViewDateMethodDescriptor()
			,isViewLocalMethodDescriptor()
			,isViewRemoteMethodDescriptor()
			,isViewSizeMethodDescriptor()
			,isViewTimeMethodDescriptor()
			,localFileListReceived_comibmnetworkftpeventLocalFileListEventMethodDescriptor()
			,makeDirMethodDescriptor()
			,remoteFileListReceived_comibmnetworkftpeventRemoteFileListEventMethodDescriptor()
			,removeCommandListener_comibmnetworkftpeventCommandListenerMethodDescriptor()
			,removeDirMethodDescriptor()
			,renameMethodDescriptor()
			,setBackground_javaawtColorMethodDescriptor()
			,setDefaultToolbar_booleanMethodDescriptor()
			,setDirDeselectedColor_javaawtColorMethodDescriptor()
			,setDirSelectedColor_javaawtColorMethodDescriptor()
			,setLabelsFont_javaawtFontMethodDescriptor()
			,setLabelsForeground_javaawtColorMethodDescriptor()
			,setListsBackground_javaawtColorMethodDescriptor()
			,setListsForeground_javaawtColorMethodDescriptor()
			,setStatusBackground_javaawtColorMethodDescriptor()
			,setStatusFont_javaawtFontMethodDescriptor()
			,setStatusForeground_javaawtColorMethodDescriptor()
			,setToolbarBackground_javaawtColorMethodDescriptor()
			,setToolbarFont_javaawtFontMethodDescriptor()
			,setToolbarForeground_javaawtColorMethodDescriptor()
			,setType_javalangStringMethodDescriptor()
			,setViewDate_booleanMethodDescriptor()
			,setViewLocal_booleanMethodDescriptor()
			,setViewRemote_booleanMethodDescriptor()
			,setViewSize_booleanMethodDescriptor()
			,setViewTime_booleanMethodDescriptor()
			,statusReceived_comibmnetworkftpeventStatusEventMethodDescriptor()
			,updateLocalDir_javalangStringMethodDescriptor()
			,updateLocalList_javautilVectorMethodDescriptor()
			,updateRemoteDir_javalangStringMethodDescriptor()
			,updateRemoteList_javautilVectorMethodDescriptor()
			,updateStatus_javalangStringMethodDescriptor()
			,upload_javalangStringMethodDescriptor()
			,uploadMethodDescriptor()
		};
		return aDescriptorList;
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return null;
}
/**
 * Return the property descriptors for this bean.
 * @return java.beans.PropertyDescriptor[]
 */
@Override
public java.beans.PropertyDescriptor[] getPropertyDescriptors() {
	try {
		java.beans.PropertyDescriptor aDescriptorList[] = {
			backgroundPropertyDescriptor()
			,defaultToolbarPropertyDescriptor()
			,dirDeselectedColorPropertyDescriptor()
			,dirSelectedColorPropertyDescriptor()
			,labelsFontPropertyDescriptor()
			,labelsForegroundPropertyDescriptor()
			,listsBackgroundPropertyDescriptor()
			,listsForegroundPropertyDescriptor()
			,statusBackgroundPropertyDescriptor()
			,statusFontPropertyDescriptor()
			,statusForegroundPropertyDescriptor()
			,toolbarBackgroundPropertyDescriptor()
			,toolbarFontPropertyDescriptor()
			,toolbarForegroundPropertyDescriptor()
			,viewDatePropertyDescriptor()
			,viewLocalPropertyDescriptor()
			,viewRemotePropertyDescriptor()
			,viewSizePropertyDescriptor()
			,viewTimePropertyDescriptor()
		};
		return aDescriptorList;
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return null;
}
/**
 * Gets the getStatusBackground() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getStatusBackgroundMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getStatusBackground() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getStatusBackground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getStatusBackground", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getStatusBackground()"); */
		/* aDescriptor.setShortDescription("getStatusBackground()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getStatusFont() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getStatusFontMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getStatusFont() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getStatusFont", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getStatusFont", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getStatusFont()"); */
		/* aDescriptor.setShortDescription("getStatusFont()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getStatusForeground() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getStatusForegroundMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getStatusForeground() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getStatusForeground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getStatusForeground", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getStatusForeground()"); */
		/* aDescriptor.setShortDescription("getStatusForeground()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getToolbarBackground() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getToolbarBackgroundMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getToolbarBackground() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getToolbarBackground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getToolbarBackground", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getToolbarBackground()"); */
		/* aDescriptor.setShortDescription("getToolbarBackground()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getToolbarFont() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getToolbarFontMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getToolbarFont() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getToolbarFont", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getToolbarFont", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getToolbarFont()"); */
		/* aDescriptor.setShortDescription("getToolbarFont()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getToolbarForeground() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getToolbarForegroundMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getToolbarForeground() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getToolbarForeground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getToolbarForeground", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getToolbarForeground()"); */
		/* aDescriptor.setShortDescription("getToolbarForeground()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Called whenever the bean information class throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(java.lang.Throwable exception) {
	/* Uncomment the following lines to print uncaught exceptions to stdout */
	// System.out.println("--------- UNCAUGHT EXCEPTION ---------");
	// exception.printStackTrace(System.out);
}
/**
 * Gets the isDefaultToolbar() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor isDefaultToolbarMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the isDefaultToolbar() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("isDefaultToolbar", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "isDefaultToolbar", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("isDefaultToolbar()"); */
		/* aDescriptor.setShortDescription("isDefaultToolbar()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the isRemoteSelected() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor isRemoteSelectedMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the isRemoteSelected() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("isRemoteSelected", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "isRemoteSelected", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("isRemoteSelected()"); */
		/* aDescriptor.setShortDescription("isRemoteSelected()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the isViewDate() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor isViewDateMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the isViewDate() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("isViewDate", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "isViewDate", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("isViewDate()"); */
		/* aDescriptor.setShortDescription("isViewDate()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the isViewLocal() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor isViewLocalMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the isViewLocal() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("isViewLocal", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "isViewLocal", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("isViewLocal()"); */
		/* aDescriptor.setShortDescription("isViewLocal()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the isViewRemote() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor isViewRemoteMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the isViewRemote() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("isViewRemote", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "isViewRemote", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("isViewRemote()"); */
		/* aDescriptor.setShortDescription("isViewRemote()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the isViewSize() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor isViewSizeMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the isViewSize() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("isViewSize", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "isViewSize", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("isViewSize()"); */
		/* aDescriptor.setShortDescription("isViewSize()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the isViewTime() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor isViewTimeMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the isViewTime() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("isViewTime", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "isViewTime", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("isViewTime()"); */
		/* aDescriptor.setShortDescription("isViewTime()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the labelsFont property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor labelsFontPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the labelsFont property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getLabelsFont", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getLabelsFont", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.awt.Font.class
				};
				aSetMethod = getBeanClass().getMethod("setLabelsFont", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setLabelsFont", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("labelsFont"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("labelsFont"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("Labels Font");
		aDescriptor.setShortDescription("Labels Font");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the labelsForeground property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor labelsForegroundPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the labelsForeground property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getLabelsForeground", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getLabelsForeground", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.awt.Color.class
				};
				aSetMethod = getBeanClass().getMethod("setLabelsForeground", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setLabelsForeground", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("labelsForeground"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("labelsForeground"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("Labels Foreground");
		aDescriptor.setShortDescription("Labels Foreground");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the listsBackground property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor listsBackgroundPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the listsBackground property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getListsBackground", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getListsBackground", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.awt.Color.class
				};
				aSetMethod = getBeanClass().getMethod("setListsBackground", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setListsBackground", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("listsBackground"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("listsBackground"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("File Lists Background");
		aDescriptor.setShortDescription("File Lists Background");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the listsForeground property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor listsForegroundPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the listsForeground property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getListsForeground", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getListsForeground", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.awt.Color.class
				};
				aSetMethod = getBeanClass().getMethod("setListsForeground", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setListsForeground", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("listsForeground"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("listsForeground"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("File Lists Foreground");
		aDescriptor.setShortDescription("File Lists Foreground");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the localFileListReceived(com.ibm.network.ftp.event.LocalFileListEvent) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor localFileListReceived_comibmnetworkftpeventLocalFileListEventMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the localFileListReceived(com.ibm.network.ftp.event.LocalFileListEvent) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.LocalFileListEvent.class
			};
			aMethod = getBeanClass().getMethod("localFileListReceived", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "localFileListReceived", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("fle");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("localFileListReceived(com"); */
		aDescriptor.setShortDescription("localFileListReceived(com.ibm.network.ftp.event.LocalFileListEvent)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the makeDir() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor makeDirMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the makeDir() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("makeDir", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "makeDir", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("makeDir()"); */
		/* aDescriptor.setShortDescription("makeDir()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the remoteFileListReceived(com.ibm.network.ftp.event.RemoteFileListEvent) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor remoteFileListReceived_comibmnetworkftpeventRemoteFileListEventMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the remoteFileListReceived(com.ibm.network.ftp.event.RemoteFileListEvent) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.RemoteFileListEvent.class
			};
			aMethod = getBeanClass().getMethod("remoteFileListReceived", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "remoteFileListReceived", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("fle");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("remoteFileListReceived(com"); */
		aDescriptor.setShortDescription("remoteFileListReceived(com.ibm.network.ftp.event.RemoteFileListEvent)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the removeCommandListener(com.ibm.network.ftp.event.CommandListener) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor removeCommandListener_comibmnetworkftpeventCommandListenerMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the removeCommandListener(com.ibm.network.ftp.event.CommandListener) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.CommandListener.class
			};
			aMethod = getBeanClass().getMethod("removeCommandListener", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "removeCommandListener", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("aListener");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("removeCommandListener(com"); */
		aDescriptor.setShortDescription("removeCommandListener(com.ibm.network.ftp.event.CommandListener)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the removeDir() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor removeDirMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the removeDir() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("removeDir", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "removeDir", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("removeDir()"); */
		/* aDescriptor.setShortDescription("removeDir()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the rename() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor renameMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the rename() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("rename", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "rename", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("rename()"); */
		/* aDescriptor.setShortDescription("rename()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setBackground(java.awt.Color) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setBackground_javaawtColorMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setBackground(java.awt.Color) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.awt.Color.class
			};
			aMethod = getBeanClass().getMethod("setBackground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setBackground", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newColor");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setBackground(java"); */
		aDescriptor.setShortDescription("setBackground(java.awt.Color)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setDefaultToolbar(boolean) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setDefaultToolbar_booleanMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setDefaultToolbar(boolean) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				boolean.class
			};
			aMethod = getBeanClass().getMethod("setDefaultToolbar", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setDefaultToolbar", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("defToolbar");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setDefaultToolbar(boolean)"); */
		/* aDescriptor.setShortDescription("setDefaultToolbar(boolean)"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setDirDeselectedColor(java.awt.Color) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setDirDeselectedColor_javaawtColorMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setDirDeselectedColor(java.awt.Color) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.awt.Color.class
			};
			aMethod = getBeanClass().getMethod("setDirDeselectedColor", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setDirDeselectedColor", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newColor");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setDirDeselectedColor(java"); */
		aDescriptor.setShortDescription("setDirDeselectedColor(java.awt.Color)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setDirSelectedColor(java.awt.Color) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setDirSelectedColor_javaawtColorMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setDirSelectedColor(java.awt.Color) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.awt.Color.class
			};
			aMethod = getBeanClass().getMethod("setDirSelectedColor", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setDirSelectedColor", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newColor");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setDirSelectedColor(java"); */
		aDescriptor.setShortDescription("setDirSelectedColor(java.awt.Color)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setLabelsFont(java.awt.Font) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setLabelsFont_javaawtFontMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setLabelsFont(java.awt.Font) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.awt.Font.class
			};
			aMethod = getBeanClass().getMethod("setLabelsFont", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setLabelsFont", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newFont");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setLabelsFont(java"); */
		aDescriptor.setShortDescription("setLabelsFont(java.awt.Font)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setLabelsForeground(java.awt.Color) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setLabelsForeground_javaawtColorMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setLabelsForeground(java.awt.Color) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.awt.Color.class
			};
			aMethod = getBeanClass().getMethod("setLabelsForeground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setLabelsForeground", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newColor");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setLabelsForeground(java"); */
		aDescriptor.setShortDescription("setLabelsForeground(java.awt.Color)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setListsBackground(java.awt.Color) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setListsBackground_javaawtColorMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setListsBackground(java.awt.Color) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.awt.Color.class
			};
			aMethod = getBeanClass().getMethod("setListsBackground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setListsBackground", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newColor");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setListsBackground(java"); */
		aDescriptor.setShortDescription("setListsBackground(java.awt.Color)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setListsForeground(java.awt.Color) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setListsForeground_javaawtColorMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setListsForeground(java.awt.Color) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.awt.Color.class
			};
			aMethod = getBeanClass().getMethod("setListsForeground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setListsForeground", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newColor");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setListsForeground(java"); */
		aDescriptor.setShortDescription("setListsForeground(java.awt.Color)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setStatusBackground(java.awt.Color) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setStatusBackground_javaawtColorMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setStatusBackground(java.awt.Color) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.awt.Color.class
			};
			aMethod = getBeanClass().getMethod("setStatusBackground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setStatusBackground", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newColor");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setStatusBackground(java"); */
		aDescriptor.setShortDescription("setStatusBackground(java.awt.Color)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setStatusFont(java.awt.Font) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setStatusFont_javaawtFontMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setStatusFont(java.awt.Font) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.awt.Font.class
			};
			aMethod = getBeanClass().getMethod("setStatusFont", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setStatusFont", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newFont");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setStatusFont(java"); */
		aDescriptor.setShortDescription("setStatusFont(java.awt.Font)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setStatusForeground(java.awt.Color) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setStatusForeground_javaawtColorMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setStatusForeground(java.awt.Color) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.awt.Color.class
			};
			aMethod = getBeanClass().getMethod("setStatusForeground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setStatusForeground", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newColor");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setStatusForeground(java"); */
		aDescriptor.setShortDescription("setStatusForeground(java.awt.Color)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setToolbarBackground(java.awt.Color) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setToolbarBackground_javaawtColorMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setToolbarBackground(java.awt.Color) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.awt.Color.class
			};
			aMethod = getBeanClass().getMethod("setToolbarBackground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setToolbarBackground", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newColor");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setToolbarBackground(java"); */
		aDescriptor.setShortDescription("setToolbarBackground(java.awt.Color)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setToolbarFont(java.awt.Font) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setToolbarFont_javaawtFontMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setToolbarFont(java.awt.Font) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.awt.Font.class
			};
			aMethod = getBeanClass().getMethod("setToolbarFont", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setToolbarFont", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newFont");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setToolbarFont(java"); */
		aDescriptor.setShortDescription("setToolbarFont(java.awt.Font)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setToolbarForeground(java.awt.Color) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setToolbarForeground_javaawtColorMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setToolbarForeground(java.awt.Color) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.awt.Color.class
			};
			aMethod = getBeanClass().getMethod("setToolbarForeground", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setToolbarForeground", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newColor");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setToolbarForeground(java"); */
		aDescriptor.setShortDescription("setToolbarForeground(java.awt.Color)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setType(java.lang.String) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setType_javalangStringMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setType(java.lang.String) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class
			};
			aMethod = getBeanClass().getMethod("setType", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setType", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("mode");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setType(java"); */
		aDescriptor.setShortDescription("setType(java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setViewDate(boolean) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setViewDate_booleanMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setViewDate(boolean) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				boolean.class
			};
			aMethod = getBeanClass().getMethod("setViewDate", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setViewDate", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("date");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setViewDate(boolean)"); */
		/* aDescriptor.setShortDescription("setViewDate(boolean)"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setViewLocal(boolean) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setViewLocal_booleanMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setViewLocal(boolean) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				boolean.class
			};
			aMethod = getBeanClass().getMethod("setViewLocal", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setViewLocal", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("local");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setViewLocal(boolean)"); */
		/* aDescriptor.setShortDescription("setViewLocal(boolean)"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setViewRemote(boolean) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setViewRemote_booleanMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setViewRemote(boolean) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				boolean.class
			};
			aMethod = getBeanClass().getMethod("setViewRemote", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setViewRemote", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("remote");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setViewRemote(boolean)"); */
		/* aDescriptor.setShortDescription("setViewRemote(boolean)"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setViewSize(boolean) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setViewSize_booleanMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setViewSize(boolean) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				boolean.class
			};
			aMethod = getBeanClass().getMethod("setViewSize", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setViewSize", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("size");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setViewSize(boolean)"); */
		/* aDescriptor.setShortDescription("setViewSize(boolean)"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setViewTime(boolean) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setViewTime_booleanMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setViewTime(boolean) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				boolean.class
			};
			aMethod = getBeanClass().getMethod("setViewTime", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setViewTime", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("time");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setViewTime(boolean)"); */
		/* aDescriptor.setShortDescription("setViewTime(boolean)"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the statusBackground property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor statusBackgroundPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the statusBackground property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getStatusBackground", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getStatusBackground", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.awt.Color.class
				};
				aSetMethod = getBeanClass().getMethod("setStatusBackground", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setStatusBackground", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("statusBackground"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("statusBackground"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("Status Background");
		aDescriptor.setShortDescription("Status Background");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the statusFont property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor statusFontPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the statusFont property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getStatusFont", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getStatusFont", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.awt.Font.class
				};
				aSetMethod = getBeanClass().getMethod("setStatusFont", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setStatusFont", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("statusFont"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("statusFont"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("Status Font");
		aDescriptor.setShortDescription("Status Font");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the statusForeground property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor statusForegroundPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the statusForeground property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getStatusForeground", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getStatusForeground", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.awt.Color.class
				};
				aSetMethod = getBeanClass().getMethod("setStatusForeground", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setStatusForeground", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("statusForeground"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("statusForeground"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("Status Foreground");
		aDescriptor.setShortDescription("Status Foreground");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the statusReceived(com.ibm.network.ftp.event.StatusEvent) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor statusReceived_comibmnetworkftpeventStatusEventMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the statusReceived(com.ibm.network.ftp.event.StatusEvent) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.StatusEvent.class
			};
			aMethod = getBeanClass().getMethod("statusReceived", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "statusReceived", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("se");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("statusReceived(com"); */
		aDescriptor.setShortDescription("statusReceived(com.ibm.network.ftp.event.StatusEvent)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the toolbarBackground property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor toolbarBackgroundPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the toolbarBackground property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getToolbarBackground", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getToolbarBackground", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.awt.Color.class
				};
				aSetMethod = getBeanClass().getMethod("setToolbarBackground", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setToolbarBackground", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("toolbarBackground"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("toolbarBackground"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("ToolBar Buttons Background");
		aDescriptor.setShortDescription("Toolbar Background");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the toolbarFont property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor toolbarFontPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the toolbarFont property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getToolbarFont", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getToolbarFont", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.awt.Font.class
				};
				aSetMethod = getBeanClass().getMethod("setToolbarFont", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setToolbarFont", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("toolbarFont"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("toolbarFont"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("ToolBar Buttons Font");
		aDescriptor.setShortDescription("Toolbar Buttons Font");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the toolbarForeground property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor toolbarForegroundPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the toolbarForeground property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getToolbarForeground", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getToolbarForeground", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.awt.Color.class
				};
				aSetMethod = getBeanClass().getMethod("setToolbarForeground", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setToolbarForeground", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("toolbarForeground"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("toolbarForeground"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("Toolbar Buttons Foreground");
		aDescriptor.setShortDescription("Toolbar Foreground");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the updateLocalDir(java.lang.String) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor updateLocalDir_javalangStringMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the updateLocalDir(java.lang.String) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class
			};
			aMethod = getBeanClass().getMethod("updateLocalDir", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "updateLocalDir", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("dirName");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("updateLocalDir(java"); */
		aDescriptor.setShortDescription("updateLocalDir(java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the updateLocalList(java.util.Vector) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor updateLocalList_javautilVectorMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the updateLocalList(java.util.Vector) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.util.Vector.class
			};
			aMethod = getBeanClass().getMethod("updateLocalList", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "updateLocalList", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("list");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("updateLocalList(java"); */
		aDescriptor.setShortDescription("updateLocalList(java.util.Vector)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the updateRemoteDir(java.lang.String) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor updateRemoteDir_javalangStringMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the updateRemoteDir(java.lang.String) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class
			};
			aMethod = getBeanClass().getMethod("updateRemoteDir", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "updateRemoteDir", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("dirName");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("updateRemoteDir(java"); */
		aDescriptor.setShortDescription("updateRemoteDir(java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the updateRemoteList(java.util.Vector) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor updateRemoteList_javautilVectorMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the updateRemoteList(java.util.Vector) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.util.Vector.class
			};
			aMethod = getBeanClass().getMethod("updateRemoteList", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "updateRemoteList", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("list");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("updateRemoteList(java"); */
		aDescriptor.setShortDescription("updateRemoteList(java.util.Vector)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the updateStatus(java.lang.String) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor updateStatus_javalangStringMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the updateStatus(java.lang.String) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class
			};
			aMethod = getBeanClass().getMethod("updateStatus", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "updateStatus", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("status");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("updateStatus(java"); */
		aDescriptor.setShortDescription("updateStatus(java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the upload(java.lang.String) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor upload_javalangStringMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the upload(java.lang.String) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class
			};
			aMethod = getBeanClass().getMethod("upload", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "upload", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("fileName");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("upload(java"); */
		aDescriptor.setShortDescription("upload(java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the upload() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor uploadMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the upload() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("upload", aParameterTypes);
		} catch (java.lang.Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "upload", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (java.lang.Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("upload()"); */
		/* aDescriptor.setShortDescription("upload()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the viewDate property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor viewDatePropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the viewDate property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("isViewDate", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "isViewDate", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					boolean.class
				};
				aSetMethod = getBeanClass().getMethod("setViewDate", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setViewDate", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("viewDate"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("viewDate"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("File Date View");
		aDescriptor.setShortDescription("File Date View");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the viewLocal property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor viewLocalPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the viewLocal property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("isViewLocal", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "isViewLocal", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					boolean.class
				};
				aSetMethod = getBeanClass().getMethod("setViewLocal", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setViewLocal", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("viewLocal"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("viewLocal"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("Local Window View");
		aDescriptor.setShortDescription("Local Window View");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the viewRemote property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor viewRemotePropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the viewRemote property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("isViewRemote", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "isViewRemote", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					boolean.class
				};
				aSetMethod = getBeanClass().getMethod("setViewRemote", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setViewRemote", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("viewRemote"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("viewRemote"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("Remote Window View");
		aDescriptor.setShortDescription("Remote Window View");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the viewSize property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor viewSizePropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the viewSize property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("isViewSize", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "isViewSize", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					boolean.class
				};
				aSetMethod = getBeanClass().getMethod("setViewSize", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setViewSize", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("viewSize"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("viewSize"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("File Size View");
		aDescriptor.setShortDescription("File Size View");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the viewTime property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor viewTimePropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the viewTime property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("isViewTime", aGetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "isViewTime", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					boolean.class
				};
				aSetMethod = getBeanClass().getMethod("setViewTime", aSetMethodParameterTypes);
			} catch (java.lang.Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setViewTime", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("viewTime"
			, aGetMethod, aSetMethod);
		} catch (java.lang.Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("viewTime"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setDisplayName("File Time View");
		aDescriptor.setShortDescription("File Time View");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (java.lang.Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
}
