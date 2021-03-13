/************************************************************************
  This software is subject to the terms of the IBM alphaBeans with Source 
  License Agreement available at 
  www.software.ibm.com/developer/alphabeans/source/license.html.
  
  Copyright (c) 1999 IBM Corporation and others. All rights reserved. 
  
  You must accept the terms of that agreement to use this software.
*************************************************************************/
  
package com.ibm.network.ftp.protocol;
/**
 * Gets the FTPProtocolBeanInfo bean descriptor.
 * @see com.ibm.network.ftp.protocol.FTPProtocol
 */
public class FTPProtocolBeanInfo extends java.beans.SimpleBeanInfo {
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
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "abort", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("abort()"); */
		/* aDescriptor.setShortDescription("abort()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the addLocalFileListListener(com.ibm.network.ftp.event.LocalFileListListener) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor addLocalFileListListener_comibmnetworkftpeventLocalFileListListenerMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the addLocalFileListListener(com.ibm.network.ftp.event.LocalFileListListener) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.LocalFileListListener.class
			};
			aMethod = getBeanClass().getMethod("addLocalFileListListener", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "addLocalFileListListener", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("listener");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("addLocalFileListListener(com"); */
		aDescriptor.setShortDescription("addLocalFileListListener(com.ibm.network.ftp.event.LocalFileListListener)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the addRemoteFileListListener(com.ibm.network.ftp.event.RemoteFileListListener) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor addRemoteFileListListener_comibmnetworkftpeventRemoteFileListListenerMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the addRemoteFileListListener(com.ibm.network.ftp.event.RemoteFileListListener) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.RemoteFileListListener.class
			};
			aMethod = getBeanClass().getMethod("addRemoteFileListListener", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "addRemoteFileListListener", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("listener");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("addRemoteFileListListener(com"); */
		aDescriptor.setShortDescription("addRemoteFileListListener(com.ibm.network.ftp.event.RemoteFileListListener)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the addStatusListener(com.ibm.network.ftp.event.StatusListener) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor addStatusListener_comibmnetworkftpeventStatusListenerMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the addStatusListener(com.ibm.network.ftp.event.StatusListener) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.StatusListener.class
			};
			aMethod = getBeanClass().getMethod("addStatusListener", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "addStatusListener", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("listener");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("addStatusListener(com"); */
		aDescriptor.setShortDescription("addStatusListener(com.ibm.network.ftp.event.StatusListener)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the changeDir(java.lang.String, boolean) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor changeDir_javalangString_booleanMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the changeDir(java.lang.String, boolean) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class,
				boolean.class
			};
			aMethod = getBeanClass().getMethod("changeDir", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "changeDir", 2);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newName");
			java.beans.ParameterDescriptor aParameterDescriptor2 = new java.beans.ParameterDescriptor();
			aParameterDescriptor2.setName("arg2");
			aParameterDescriptor2.setDisplayName("remote");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1,
				aParameterDescriptor2
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("changeDir(java"); */
		aDescriptor.setShortDescription("changeDir(java.lang.String, boolean)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the commandPerformed(com.ibm.network.ftp.event.CommandEvent) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor commandPerformed_comibmnetworkftpeventCommandEventMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the commandPerformed(com.ibm.network.ftp.event.CommandEvent) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.CommandEvent.class
			};
			aMethod = getBeanClass().getMethod("commandPerformed", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "commandPerformed", 1);
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
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("commandPerformed(com"); */
		aDescriptor.setShortDescription("commandPerformed(com.ibm.network.ftp.event.CommandEvent)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the configureSocks(java.lang.String, java.lang.String) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor configureSocks_javalangString_javalangStringMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the configureSocks(java.lang.String, java.lang.String) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class,
				java.lang.String.class
			};
			aMethod = getBeanClass().getMethod("configureSocks", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "configureSocks", 2);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("hostName");
			java.beans.ParameterDescriptor aParameterDescriptor2 = new java.beans.ParameterDescriptor();
			aParameterDescriptor2.setName("arg2");
			aParameterDescriptor2.setDisplayName("port");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1,
				aParameterDescriptor2
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("configureSocks(java"); */
		aDescriptor.setShortDescription("configureSocks(java.lang.String, java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the connect(java.lang.String) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor connect_javalangStringMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the connect(java.lang.String) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class
			};
			aMethod = getBeanClass().getMethod("connect", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "connect", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("hostName");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("connect(java"); */
		aDescriptor.setShortDescription("connect(java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the deleteFile(java.lang.String, boolean) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor deleteFile_javalangString_booleanMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the deleteFile(java.lang.String, boolean) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class,
				boolean.class
			};
			aMethod = getBeanClass().getMethod("deleteFile", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "deleteFile", 2);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("fileName");
			java.beans.ParameterDescriptor aParameterDescriptor2 = new java.beans.ParameterDescriptor();
			aParameterDescriptor2.setName("arg2");
			aParameterDescriptor2.setDisplayName("remote");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1,
				aParameterDescriptor2
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("deleteFile(java"); */
		aDescriptor.setShortDescription("deleteFile(java.lang.String, boolean)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
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
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "disconnect", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("disconnect()"); */
		/* aDescriptor.setShortDescription("disconnect()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the fileList(boolean) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor fileList_booleanMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the fileList(boolean) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				boolean.class
			};
			aMethod = getBeanClass().getMethod("fileList", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "fileList", 1);
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
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("fileList(boolean)"); */
		/* aDescriptor.setShortDescription("fileList(boolean)"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
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
 * Gets the bean class.
 * @return java.lang.Class
 */
public static java.lang.Class getBeanClass() {
	return com.ibm.network.ftp.protocol.FTPProtocol.class;
}
/**
 * Gets the bean class name.
 * @return java.lang.String
 */
public static java.lang.String getBeanClassName() {
	return "com.ibm.network.ftp.protocol.FTPProtocol";
}
public java.beans.BeanDescriptor getBeanDescriptor() {
	java.beans.BeanDescriptor aDescriptor = null;
	try {
		/* Create and return the FTPProtocolBeanInfo bean descriptor. */
		aDescriptor = new java.beans.BeanDescriptor(com.ibm.network.ftp.protocol.FTPProtocol.class);
		aDescriptor.setDisplayName("FTPProtocol");
		aDescriptor.setShortDescription("com.ibm.network.ftp.protocol.FTPProtocol");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
	};
	return aDescriptor;
}
/**
 * Gets the getCurrentDir(boolean) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getCurrentDir_booleanMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getCurrentDir(boolean) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				boolean.class
			};
			aMethod = getBeanClass().getMethod("getCurrentDir", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getCurrentDir", 1);
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
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getCurrentDir(boolean)"); */
		/* aDescriptor.setShortDescription("getCurrentDir(boolean)"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Return the event set descriptors for this bean.
 * @return java.beans.EventSetDescriptor[]
 */
public java.beans.EventSetDescriptor[] getEventSetDescriptors() {
	try {
		java.beans.EventSetDescriptor aDescriptorList[] = {
			localFileListEventSetDescriptor()
			,remoteFileListEventSetDescriptor()
			,statusEventSetDescriptor()
		};
		return aDescriptorList;
	} catch (Throwable exception) {
		handleException(exception);
	};
	return null;
}
/**
 * Gets the getFile(java.lang.String) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getFile_javalangStringMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getFile(java.lang.String) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class
			};
			aMethod = getBeanClass().getMethod("getFile", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getFile", 1);
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
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getFile(java"); */
		aDescriptor.setShortDescription("getFile(java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
public java.awt.Image getIcon(int iconKind) {
	java.awt.Image image = null;
	if (iconKind == java.beans.BeanInfo.ICON_COLOR_16x16) {
		image = loadImage("c1616.gif");
	};
	if (iconKind == java.beans.BeanInfo.ICON_COLOR_32x32) {
		image = loadImage("c3232.gif");
	};
	return image;
}
/**
 * Gets the getLocalDir() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getLocalDirMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getLocalDir() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getLocalDir", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getLocalDir", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getLocalDir()"); */
		/* aDescriptor.setShortDescription("getLocalDir()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getLocalFileList() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getLocalFileListMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getLocalFileList() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getLocalFileList", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getLocalFileList", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getLocalFileList()"); */
		/* aDescriptor.setShortDescription("getLocalFileList()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Return the method descriptors for this bean.
 * @return java.beans.MethodDescriptor[]
 */
public java.beans.MethodDescriptor[] getMethodDescriptors() {
	try {
		java.beans.MethodDescriptor aDescriptorList[] = {
			abortMethodDescriptor()
			,addLocalFileListListener_comibmnetworkftpeventLocalFileListListenerMethodDescriptor()
			,addRemoteFileListListener_comibmnetworkftpeventRemoteFileListListenerMethodDescriptor()
			,addStatusListener_comibmnetworkftpeventStatusListenerMethodDescriptor()
			,changeDir_javalangString_booleanMethodDescriptor()
			,commandPerformed_comibmnetworkftpeventCommandEventMethodDescriptor()
			,configureSocks_javalangString_javalangStringMethodDescriptor()
			,connect_javalangStringMethodDescriptor()
			,deleteFile_javalangString_booleanMethodDescriptor()
			,disconnectMethodDescriptor()
			,fileList_booleanMethodDescriptor()
			,getCurrentDir_booleanMethodDescriptor()
			,getFile_javalangStringMethodDescriptor()
			,getLocalDirMethodDescriptor()
			,getLocalFileListMethodDescriptor()
			,getRemoteDirMethodDescriptor()
			,getRemoteFileListMethodDescriptor()
			,getSocksProxyHostMethodDescriptor()
			,getSocksProxyPortMethodDescriptor()
			,getStatusMethodDescriptor()
			,getTypeMethodDescriptor()
			,login_javalangString_javalangStringMethodDescriptor()
			,makeDir_javalangString_booleanMethodDescriptor()
			,passiveServerMethodDescriptor()
			,putFile_javalangStringMethodDescriptor()
			,removeDir_javalangString_booleanMethodDescriptor()
			,removeLocalFileListListener_comibmnetworkftpeventLocalFileListListenerMethodDescriptor()
			,removeRemoteFileListListener_comibmnetworkftpeventRemoteFileListListenerMethodDescriptor()
			,removeStatusListener_comibmnetworkftpeventStatusListenerMethodDescriptor()
			,rename_javalangString_javalangString_booleanMethodDescriptor()
			,setSocksProxyHost_javalangStringMethodDescriptor()
			,setSocksProxyPort_javalangStringMethodDescriptor()
			,setType_javalangStringMethodDescriptor()
			,statusMethodDescriptor()
		};
		return aDescriptorList;
	} catch (Throwable exception) {
		handleException(exception);
	};
	return null;
}
/**
 * Return the property descriptors for this bean.
 * @return java.beans.PropertyDescriptor[]
 */
public java.beans.PropertyDescriptor[] getPropertyDescriptors() {
	try {
		java.beans.PropertyDescriptor aDescriptorList[] = {
			localDirPropertyDescriptor()
			,localFileListPropertyDescriptor()
			,remoteDirPropertyDescriptor()
			,remoteFileListPropertyDescriptor()
			,socksProxyHostPropertyDescriptor()
			,socksProxyPortPropertyDescriptor()
			,statusPropertyDescriptor()
			,typePropertyDescriptor()
		};
		return aDescriptorList;
	} catch (Throwable exception) {
		handleException(exception);
	};
	return null;
}
/**
 * Gets the getRemoteDir() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getRemoteDirMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getRemoteDir() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getRemoteDir", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getRemoteDir", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getRemoteDir()"); */
		/* aDescriptor.setShortDescription("getRemoteDir()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getRemoteFileList() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getRemoteFileListMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getRemoteFileList() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getRemoteFileList", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getRemoteFileList", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getRemoteFileList()"); */
		/* aDescriptor.setShortDescription("getRemoteFileList()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getSocksProxyHost() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getSocksProxyHostMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getSocksProxyHost() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getSocksProxyHost", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getSocksProxyHost", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getSocksProxyHost()"); */
		/* aDescriptor.setShortDescription("getSocksProxyHost()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getSocksProxyPort() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getSocksProxyPortMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getSocksProxyPort() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getSocksProxyPort", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getSocksProxyPort", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getSocksProxyPort()"); */
		/* aDescriptor.setShortDescription("getSocksProxyPort()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getStatus() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getStatusMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getStatus() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getStatus", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getStatus", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getStatus()"); */
		/* aDescriptor.setShortDescription("getStatus()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the getType() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor getTypeMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the getType() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("getType", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "getType", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("getType()"); */
		/* aDescriptor.setShortDescription("getType()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
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
 * Gets the localDir property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor localDirPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the localDir property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getLocalDir", aGetMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getLocalDir", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			aDescriptor = new java.beans.PropertyDescriptor("localDir"
			, aGetMethod, aSetMethod);
		} catch (Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("localDir"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		/* aDescriptor.setDisplayName("localDir"); */
		/* aDescriptor.setShortDescription("localDir"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the localFileList event set descriptor.
 * @return java.beans.EventSetDescriptor
 */
public java.beans.EventSetDescriptor localFileListEventSetDescriptor() {
	java.beans.EventSetDescriptor aDescriptor = null;
	try {
		try {
			/* Try using method descriptors to create the localFileList event set descriptor. */
			java.beans.MethodDescriptor eventMethodDescriptors[] = {
				localFileListlocalFileListReceived_comibmnetworkftpeventLocalFileListEventMethodEventDescriptor()			};
			java.lang.reflect.Method anAddMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class anAddMethodParameterTypes[] = {
					com.ibm.network.ftp.event.LocalFileListListener.class
				};
				anAddMethod = getBeanClass().getMethod("addLocalFileListListener", anAddMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				anAddMethod = findMethod(getBeanClass(), "addLocalFileListListener", 1);
			};
			java.lang.reflect.Method aRemoveMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aRemoveMethodParameterTypes[] = {
					com.ibm.network.ftp.event.LocalFileListListener.class
				};
				aRemoveMethod = getBeanClass().getMethod("removeLocalFileListListener", aRemoveMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aRemoveMethod = findMethod(getBeanClass(), "removeLocalFileListListener", 1);
			};
			aDescriptor = new java.beans.EventSetDescriptor(
						"localFileList", 
						com.ibm.network.ftp.event.LocalFileListListener.class, 
						eventMethodDescriptors, anAddMethod, aRemoveMethod);
		} catch (Throwable exception) {
			/* Using method descriptors failed, try using the methods names. */
			handleException(exception);
			java.lang.String eventMethodNames[] = {
				"localFileListReceived"			};
			aDescriptor = new java.beans.EventSetDescriptor(getBeanClass(), 
						"localFileList", 
						com.ibm.network.ftp.event.LocalFileListListener.class, 
						eventMethodNames, 
						"addLocalFileListListener", 
						"removeLocalFileListListener");
		};
		/* aDescriptor.setUnicast(false); */
		/* aDescriptor.setDisplayName("localFileList"); */
		/* aDescriptor.setShortDescription("localFileList"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the localFileList.localFileListReceived(com.ibm.network.ftp.event.LocalFileListEvent) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor localFileListlocalFileListReceived_comibmnetworkftpeventLocalFileListEventMethodEventDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the localFileList.localFileListReceived(com.ibm.network.ftp.event.LocalFileListEvent) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.LocalFileListEvent.class
			};
			aMethod = (com.ibm.network.ftp.event.LocalFileListListener.class).getMethod("localFileListReceived", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod((com.ibm.network.ftp.event.LocalFileListListener.class), "localFileListReceived", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("event");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		aDescriptor.setDisplayName("localFileListReceived(com.ibm.network.ftp.event.LocalFileListEvent)");
		aDescriptor.setShortDescription("localFileListReceived(com.ibm.network.ftp.event.LocalFileListEvent)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the localFileList property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor localFileListPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the localFileList property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getLocalFileList", aGetMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getLocalFileList", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			aDescriptor = new java.beans.PropertyDescriptor("localFileList"
			, aGetMethod, aSetMethod);
		} catch (Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("localFileList"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		/* aDescriptor.setDisplayName("localFileList"); */
		/* aDescriptor.setShortDescription("localFileList"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the login(java.lang.String, java.lang.String) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor login_javalangString_javalangStringMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the login(java.lang.String, java.lang.String) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class,
				java.lang.String.class
			};
			aMethod = getBeanClass().getMethod("login", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "login", 2);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("userName");
			java.beans.ParameterDescriptor aParameterDescriptor2 = new java.beans.ParameterDescriptor();
			aParameterDescriptor2.setName("arg2");
			aParameterDescriptor2.setDisplayName("passwd");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1,
				aParameterDescriptor2
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("login(java"); */
		aDescriptor.setShortDescription("login(java.lang.String, java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the makeDir(java.lang.String, boolean) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor makeDir_javalangString_booleanMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the makeDir(java.lang.String, boolean) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class,
				boolean.class
			};
			aMethod = getBeanClass().getMethod("makeDir", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "makeDir", 2);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("dirName");
			java.beans.ParameterDescriptor aParameterDescriptor2 = new java.beans.ParameterDescriptor();
			aParameterDescriptor2.setName("arg2");
			aParameterDescriptor2.setDisplayName("remote");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1,
				aParameterDescriptor2
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("makeDir(java"); */
		aDescriptor.setShortDescription("makeDir(java.lang.String, boolean)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the passiveServer() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor passiveServerMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the passiveServer() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("passiveServer", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "passiveServer", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("passiveServer()"); */
		/* aDescriptor.setShortDescription("passiveServer()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the putFile(java.lang.String) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor putFile_javalangStringMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the putFile(java.lang.String) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class
			};
			aMethod = getBeanClass().getMethod("putFile", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "putFile", 1);
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
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("putFile(java"); */
		aDescriptor.setShortDescription("putFile(java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the remoteDir property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor remoteDirPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the remoteDir property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getRemoteDir", aGetMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getRemoteDir", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			aDescriptor = new java.beans.PropertyDescriptor("remoteDir"
			, aGetMethod, aSetMethod);
		} catch (Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("remoteDir"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		/* aDescriptor.setDisplayName("remoteDir"); */
		/* aDescriptor.setShortDescription("remoteDir"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the remoteFileList event set descriptor.
 * @return java.beans.EventSetDescriptor
 */
public java.beans.EventSetDescriptor remoteFileListEventSetDescriptor() {
	java.beans.EventSetDescriptor aDescriptor = null;
	try {
		try {
			/* Try using method descriptors to create the remoteFileList event set descriptor. */
			java.beans.MethodDescriptor eventMethodDescriptors[] = {
				remoteFileListremoteFileListReceived_comibmnetworkftpeventRemoteFileListEventMethodEventDescriptor()			};
			java.lang.reflect.Method anAddMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class anAddMethodParameterTypes[] = {
					com.ibm.network.ftp.event.RemoteFileListListener.class
				};
				anAddMethod = getBeanClass().getMethod("addRemoteFileListListener", anAddMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				anAddMethod = findMethod(getBeanClass(), "addRemoteFileListListener", 1);
			};
			java.lang.reflect.Method aRemoveMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aRemoveMethodParameterTypes[] = {
					com.ibm.network.ftp.event.RemoteFileListListener.class
				};
				aRemoveMethod = getBeanClass().getMethod("removeRemoteFileListListener", aRemoveMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aRemoveMethod = findMethod(getBeanClass(), "removeRemoteFileListListener", 1);
			};
			aDescriptor = new java.beans.EventSetDescriptor(
						"remoteFileList", 
						com.ibm.network.ftp.event.RemoteFileListListener.class, 
						eventMethodDescriptors, anAddMethod, aRemoveMethod);
		} catch (Throwable exception) {
			/* Using method descriptors failed, try using the methods names. */
			handleException(exception);
			java.lang.String eventMethodNames[] = {
				"remoteFileListReceived"			};
			aDescriptor = new java.beans.EventSetDescriptor(getBeanClass(), 
						"remoteFileList", 
						com.ibm.network.ftp.event.RemoteFileListListener.class, 
						eventMethodNames, 
						"addRemoteFileListListener", 
						"removeRemoteFileListListener");
		};
		/* aDescriptor.setUnicast(false); */
		/* aDescriptor.setDisplayName("remoteFileList"); */
		/* aDescriptor.setShortDescription("remoteFileList"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the remoteFileList property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor remoteFileListPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the remoteFileList property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getRemoteFileList", aGetMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getRemoteFileList", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			aDescriptor = new java.beans.PropertyDescriptor("remoteFileList"
			, aGetMethod, aSetMethod);
		} catch (Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("remoteFileList"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		/* aDescriptor.setDisplayName("remoteFileList"); */
		/* aDescriptor.setShortDescription("remoteFileList"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the remoteFileList.remoteFileListReceived(com.ibm.network.ftp.event.RemoteFileListEvent) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor remoteFileListremoteFileListReceived_comibmnetworkftpeventRemoteFileListEventMethodEventDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the remoteFileList.remoteFileListReceived(com.ibm.network.ftp.event.RemoteFileListEvent) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.RemoteFileListEvent.class
			};
			aMethod = (com.ibm.network.ftp.event.RemoteFileListListener.class).getMethod("remoteFileListReceived", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod((com.ibm.network.ftp.event.RemoteFileListListener.class), "remoteFileListReceived", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("event");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		aDescriptor.setDisplayName("remoteFileListReceived(com.ibm.network.ftp.event.RemoteFileListEvent)");
		aDescriptor.setShortDescription("remoteFileListReceived(com.ibm.network.ftp.event.RemoteFileListEvent)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the removeDir(java.lang.String, boolean) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor removeDir_javalangString_booleanMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the removeDir(java.lang.String, boolean) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class,
				boolean.class
			};
			aMethod = getBeanClass().getMethod("removeDir", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "removeDir", 2);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("dirName");
			java.beans.ParameterDescriptor aParameterDescriptor2 = new java.beans.ParameterDescriptor();
			aParameterDescriptor2.setName("arg2");
			aParameterDescriptor2.setDisplayName("remote");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1,
				aParameterDescriptor2
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("removeDir(java"); */
		aDescriptor.setShortDescription("removeDir(java.lang.String, boolean)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the removeLocalFileListListener(com.ibm.network.ftp.event.LocalFileListListener) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor removeLocalFileListListener_comibmnetworkftpeventLocalFileListListenerMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the removeLocalFileListListener(com.ibm.network.ftp.event.LocalFileListListener) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.LocalFileListListener.class
			};
			aMethod = getBeanClass().getMethod("removeLocalFileListListener", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "removeLocalFileListListener", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("listener");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("removeLocalFileListListener(com"); */
		aDescriptor.setShortDescription("removeLocalFileListListener(com.ibm.network.ftp.event.LocalFileListListener)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the removeRemoteFileListListener(com.ibm.network.ftp.event.RemoteFileListListener) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor removeRemoteFileListListener_comibmnetworkftpeventRemoteFileListListenerMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the removeRemoteFileListListener(com.ibm.network.ftp.event.RemoteFileListListener) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.RemoteFileListListener.class
			};
			aMethod = getBeanClass().getMethod("removeRemoteFileListListener", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "removeRemoteFileListListener", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("listener");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("removeRemoteFileListListener(com"); */
		aDescriptor.setShortDescription("removeRemoteFileListListener(com.ibm.network.ftp.event.RemoteFileListListener)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the removeStatusListener(com.ibm.network.ftp.event.StatusListener) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor removeStatusListener_comibmnetworkftpeventStatusListenerMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the removeStatusListener(com.ibm.network.ftp.event.StatusListener) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.StatusListener.class
			};
			aMethod = getBeanClass().getMethod("removeStatusListener", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "removeStatusListener", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("listener");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("removeStatusListener(com"); */
		aDescriptor.setShortDescription("removeStatusListener(com.ibm.network.ftp.event.StatusListener)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the rename(java.lang.String, java.lang.String, boolean) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor rename_javalangString_javalangString_booleanMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the rename(java.lang.String, java.lang.String, boolean) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class,
				java.lang.String.class,
				boolean.class
			};
			aMethod = getBeanClass().getMethod("rename", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "rename", 3);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("oldName");
			java.beans.ParameterDescriptor aParameterDescriptor2 = new java.beans.ParameterDescriptor();
			aParameterDescriptor2.setName("arg2");
			aParameterDescriptor2.setDisplayName("newName");
			java.beans.ParameterDescriptor aParameterDescriptor3 = new java.beans.ParameterDescriptor();
			aParameterDescriptor3.setName("arg3");
			aParameterDescriptor3.setDisplayName("remote");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1,
				aParameterDescriptor2,
				aParameterDescriptor3
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("rename(java"); */
		aDescriptor.setShortDescription("rename(java.lang.String, java.lang.String, boolean)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setSocksProxyHost(java.lang.String) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setSocksProxyHost_javalangStringMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setSocksProxyHost(java.lang.String) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class
			};
			aMethod = getBeanClass().getMethod("setSocksProxyHost", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setSocksProxyHost", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("socksProxyHost");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setSocksProxyHost(java"); */
		aDescriptor.setShortDescription("setSocksProxyHost(java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the setSocksProxyPort(java.lang.String) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor setSocksProxyPort_javalangStringMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the setSocksProxyPort(java.lang.String) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				java.lang.String.class
			};
			aMethod = getBeanClass().getMethod("setSocksProxyPort", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setSocksProxyPort", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("socksProxyPort");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setSocksProxyPort(java"); */
		aDescriptor.setShortDescription("setSocksProxyPort(java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
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
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "setType", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("newType");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("setType(java"); */
		aDescriptor.setShortDescription("setType(java.lang.String)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the socksProxyHost property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor socksProxyHostPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the socksProxyHost property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getSocksProxyHost", aGetMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getSocksProxyHost", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.lang.String.class
				};
				aSetMethod = getBeanClass().getMethod("setSocksProxyHost", aSetMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setSocksProxyHost", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("socksProxyHost"
			, aGetMethod, aSetMethod);
		} catch (Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("socksProxyHost"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		/* aDescriptor.setDisplayName("socksProxyHost"); */
		/* aDescriptor.setShortDescription("socksProxyHost"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the socksProxyPort property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor socksProxyPortPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the socksProxyPort property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getSocksProxyPort", aGetMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getSocksProxyPort", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.lang.String.class
				};
				aSetMethod = getBeanClass().getMethod("setSocksProxyPort", aSetMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setSocksProxyPort", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("socksProxyPort"
			, aGetMethod, aSetMethod);
		} catch (Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("socksProxyPort"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		/* aDescriptor.setDisplayName("socksProxyPort"); */
		/* aDescriptor.setShortDescription("socksProxyPort"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the status event set descriptor.
 * @return java.beans.EventSetDescriptor
 */
public java.beans.EventSetDescriptor statusEventSetDescriptor() {
	java.beans.EventSetDescriptor aDescriptor = null;
	try {
		try {
			/* Try using method descriptors to create the status event set descriptor. */
			java.beans.MethodDescriptor eventMethodDescriptors[] = {
				statusstatusReceived_comibmnetworkftpeventStatusEventMethodEventDescriptor()			};
			java.lang.reflect.Method anAddMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class anAddMethodParameterTypes[] = {
					com.ibm.network.ftp.event.StatusListener.class
				};
				anAddMethod = getBeanClass().getMethod("addStatusListener", anAddMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				anAddMethod = findMethod(getBeanClass(), "addStatusListener", 1);
			};
			java.lang.reflect.Method aRemoveMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aRemoveMethodParameterTypes[] = {
					com.ibm.network.ftp.event.StatusListener.class
				};
				aRemoveMethod = getBeanClass().getMethod("removeStatusListener", aRemoveMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aRemoveMethod = findMethod(getBeanClass(), "removeStatusListener", 1);
			};
			aDescriptor = new java.beans.EventSetDescriptor(
						"status", 
						com.ibm.network.ftp.event.StatusListener.class, 
						eventMethodDescriptors, anAddMethod, aRemoveMethod);
		} catch (Throwable exception) {
			/* Using method descriptors failed, try using the methods names. */
			handleException(exception);
			java.lang.String eventMethodNames[] = {
				"statusReceived"			};
			aDescriptor = new java.beans.EventSetDescriptor(getBeanClass(), 
						"status", 
						com.ibm.network.ftp.event.StatusListener.class, 
						eventMethodNames, 
						"addStatusListener", 
						"removeStatusListener");
		};
		/* aDescriptor.setUnicast(false); */
		/* aDescriptor.setDisplayName("status"); */
		/* aDescriptor.setShortDescription("status"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the status() method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor statusMethodDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the status() method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {};
			aMethod = getBeanClass().getMethod("status", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod(getBeanClass(), "status", 0);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptors[] = {};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		/* aDescriptor.setDisplayName("status()"); */
		/* aDescriptor.setShortDescription("status()"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the status property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor statusPropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the status property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getStatus", aGetMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getStatus", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			aDescriptor = new java.beans.PropertyDescriptor("status"
			, aGetMethod, aSetMethod);
		} catch (Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("status"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		/* aDescriptor.setDisplayName("status"); */
		/* aDescriptor.setShortDescription("status"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the status.statusReceived(com.ibm.network.ftp.event.StatusEvent) method descriptor.
 * @return java.beans.MethodDescriptor
 */
public java.beans.MethodDescriptor statusstatusReceived_comibmnetworkftpeventStatusEventMethodEventDescriptor() {
	java.beans.MethodDescriptor aDescriptor = null;
	try {
		/* Create and return the status.statusReceived(com.ibm.network.ftp.event.StatusEvent) method descriptor. */
		java.lang.reflect.Method aMethod = null;
		try {
			/* Attempt to find the method using getMethod with parameter types. */
			java.lang.Class aParameterTypes[] = {
				com.ibm.network.ftp.event.StatusEvent.class
			};
			aMethod = (com.ibm.network.ftp.event.StatusListener.class).getMethod("statusReceived", aParameterTypes);
		} catch (Throwable exception) {
			/* Since getMethod failed, call findMethod. */
			handleException(exception);
			aMethod = findMethod((com.ibm.network.ftp.event.StatusListener.class), "statusReceived", 1);
		};
		try {
			/* Try creating the method descriptor with parameter descriptors. */
			java.beans.ParameterDescriptor aParameterDescriptor1 = new java.beans.ParameterDescriptor();
			aParameterDescriptor1.setName("arg1");
			aParameterDescriptor1.setDisplayName("event");
			java.beans.ParameterDescriptor aParameterDescriptors[] = {
				aParameterDescriptor1
			};
			aDescriptor = new java.beans.MethodDescriptor(aMethod, aParameterDescriptors);
		} catch (Throwable exception) {
			/* Try creating the method descriptor without parameter descriptors. */
			handleException(exception);
			aDescriptor = new java.beans.MethodDescriptor(aMethod);
		};
		aDescriptor.setDisplayName("statusReceived(com.ibm.network.ftp.event.StatusEvent)");
		aDescriptor.setShortDescription("statusReceived(com.ibm.network.ftp.event.StatusEvent)");
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
/**
 * Gets the type property descriptor.
 * @return java.beans.PropertyDescriptor
 */
public java.beans.PropertyDescriptor typePropertyDescriptor() {
	java.beans.PropertyDescriptor aDescriptor = null;
	try {
		try {
			/* Using methods via getMethod is the faster way to create the type property descriptor. */
			java.lang.reflect.Method aGetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aGetMethodParameterTypes[] = {};
				aGetMethod = getBeanClass().getMethod("getType", aGetMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aGetMethod = findMethod(getBeanClass(), "getType", 0);
			};
			java.lang.reflect.Method aSetMethod = null;
			try {
				/* Attempt to find the method using getMethod with parameter types. */
				java.lang.Class aSetMethodParameterTypes[] = {
					java.lang.String.class
				};
				aSetMethod = getBeanClass().getMethod("setType", aSetMethodParameterTypes);
			} catch (Throwable exception) {
				/* Since getMethod failed, call findMethod. */
				handleException(exception);
				aSetMethod = findMethod(getBeanClass(), "setType", 1);
			};
			aDescriptor = new java.beans.PropertyDescriptor("type"
			, aGetMethod, aSetMethod);
		} catch (Throwable exception) {
			/* Since we failed using methods, try creating a default property descriptor. */
			handleException(exception);
			aDescriptor = new java.beans.PropertyDescriptor("type"
			, getBeanClass());
		};
		/* aDescriptor.setBound(false); */
		/* aDescriptor.setConstrained(false); */
		aDescriptor.setPropertyEditorClass(java.lang.Class.forName("com.ibm.network.ftp.protocol.TypeEditor"));
		/* aDescriptor.setDisplayName("type"); */
		/* aDescriptor.setShortDescription("type"); */
		/* aDescriptor.setExpert(false); */
		/* aDescriptor.setHidden(false); */
	} catch (Throwable exception) {
		handleException(exception);
	};
	return aDescriptor;
}
}
