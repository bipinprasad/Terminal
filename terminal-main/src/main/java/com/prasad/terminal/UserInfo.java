

package com.prasad.terminal;

import java.util.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.lang.reflect.*;
import java.applet.Applet;
import java.awt.event.*;

import com.prasad.util.Base64;

/**
 * Information about the user of Jed. This includes the userId and password and the
 * hashed info when both user id and password are supplied.
 * <p>
 * This class is intended to be used only for the global variables. However,
 * an instance of this class must be created in a PalTerm
 * to avoid being garbage collected and reinitialized upon next reference.
 */

public class UserInfo {
    private static String userid;
    private static String password;
    private static String loginUserId;
    private static String authCookie;
    private static boolean debug;

    /**
     * Web User Id
     **/
    public static String getUserId() {
        return userid;
    }

    public static void setUserId(String u) {
        userid = u;
        if (debug) {
            if (userid == null)
                System.out.println("UserInfo.setUserId(): user is null");
            else
                System.out.println("UserInfo.setUserId(): user is " + userid);
        }
        computeAuthCookie();
        // invalidate loginId and logintype
        setLoginUserId(null);
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String p) {
        password = p;
        if (debug) {
            if (password == null)
                System.out.println("UserInfo.setPassword(): password is null");
            else
                System.out.println("UserInfo.setPassword(): password is " + password);
        }
        computeAuthCookie();
    }

    private static void computeAuthCookie() {
        if (userid != null
            && password != null
            && userid.trim().length() > 0
            && password.trim().length() > 0)
            authCookie = "Basic " + Base64.encode(userid + ":" + password);
        else
            authCookie = null;
    }

    public static String getAuthCookie() {
        return authCookie;
    }

    public static boolean getDebug() {
        return debug;
    }

    public static void setDebug(boolean b) {
        debug = b;
    }

    /**
     * (Unix) Login User Id
     **/
    public static String getLoginUserId(Applet applet) {
        if (loginUserId == null
            && userid != null)
            retrieveExtraUserInfo(applet);
        return loginUserId;
    }

    public static void setLoginUserId(String u) {
        loginUserId = u;
    }

    /**
     * Retrieve more information for the web user.
     * 1. Unix User Id
     * 2. UserType (NOT)
     */
    static public void retrieveExtraUserInfo(Applet applet) {
        PalTerm palTerm = (PalTerm) applet;
        String urlStr;
        String cgiProgram = palTerm.cgiDir + palTerm.pgm_dme + palTerm.cgiExtension;
        String statusMessage;

        if (!palTerm.retrieveLoginForWeb) {
            if (debug)
                System.out.println("UserInfo.retrieveExtraUserInfo(): returning without determining the webuserid, flag retrieveLoginForWeb is [false]");
            return;
        }

        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        {
            boolean success = false;

            if (palTerm.isNAV) {
                // try to get Netscape permission
                try {
                    throw new IllegalArgumentException("Netscape Browser support has been removed");
                    //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                    //success = true;

                    //Class clazz = Class.forName("netscape.security.PrivilegeManager");
                    //if (clazz != null)
                    //{
                    //	Class[] parameterTypes = new Class[1];
                    //	parameterTypes[0] = " ".getClass();
                    //	Method mid = clazz.getMethod("enablePrivilege", parameterTypes);
                    //	Object[]  args = new Object[1];
                    //	args[0] = "TerminalEmulator";
                    //	mid.invoke(null, args);
                    //}
                } catch (Throwable e) {
                    success = false;
                }
            }

            if (palTerm.isIE) {
                // try Microsoft permission
                //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                try {
                    throw new IllegalArgumentException("Microsft Internet Explorer support has been removed");
                    //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                    //if (debug)
                    //    System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                    //success = true;
                    ////Class clazz = Class.forName("com.ms.security.PolicyEngine");
                    ////if (clazz != null)
                    ////{
                    ////	Class[] parameterTypes = new Class[1];
                    ////	parameterTypes[0] = Class.forName("com.ms.security.PermissionID");
                    ////	Method mid = clazz.getMethod("assertPermission", parameterTypes);
                    ////	Field	field = clazz.getField("NETIO");
                    ////	Object[]  args = new Object[1];
                    ////	args[0] = field.get(null);
                    ////	mid.invoke(null, args);
                    ////	if (debug)
                    ////		System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                    ////}
                    ////else
                    ////{
                    ////	System.out.println("Unknown browser: neither Netscape Navigator nor Microsoft Internet Explorer");
                    ////	System.out.println("    Cannot request permission to escape applet security for network I/O");
                    ////}
                } catch (Throwable e) {
                    success = false;
                }
            }
        }

        URL baseUrl = null;
        try {
            baseUrl = new URL("http", palTerm.serverHost, palTerm.serverPort, cgiProgram);
        } catch (Exception e) {
            statusMessage = "Malformed URL for host [" + palTerm.serverHost + "] port [" + palTerm.serverPort + "], cgi [" + cgiProgram + "]";
            System.out.println(statusMessage);
            //statusBar.setText(statusMessage);
            return;
        }


        StringBuffer encodedString = new StringBuffer("OUT=TEXT&INDEX=wurset1&PROD=logan&FILE=webuser&FIELD=Web-User;Login;ALLOWJOBQUEUE&KEY=" + getUserId() + "&NOHEADER&MAX=300");
        URLConnection connection = null;
        BufferedReader in = null;
        PrintWriter out = null;
        String inputLine;

        boolean found = false;
        try {
            connection = baseUrl.openConnection();
            connection.setDoOutput(true);     // DONT use POST method to pass data
            connection.setDoInput(true);      // process incoming CGI stream
            connection.setUseCaches(false);   // disable caching of documents
            connection.setAllowUserInteraction(true);

            String authCookie = UserInfo.getAuthCookie();
            if (authCookie != null)
                connection.setRequestProperty("Authorization", authCookie);
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-length", encodedString.length() + "");
            out = new PrintWriter(connection.getOutputStream());
            out.print(encodedString);
            out.flush();
            out.close();
            out = null;

            // Read entire input stream into a string buffer
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith("C:0")) {
                    if (inputLine.startsWith("C:0:1=")) {
                        found = true;
                        setLoginUserId(inputLine.substring(6));
                    }
                    //else
                    //if (inputLine.startsWith("C:0:2="))
                    //{
                    //	found = true;
                    //	setLoginUserType(inputLine.substring(6));
                    //}
                }
            }
        } catch (MalformedURLException e) {
            statusMessage = "Error getting extra information for user [" + getUserId() + "]";
            System.out.println(statusMessage);
            return;
        } catch (IOException e) {
            if (connection.getHeaderField("WWW-authenticate") != null) {
                String cgiUser = getUserId();
                String cgiPassword = getPassword();

                statusMessage =
                    "Authorization failed for user[" +
                        ((cgiUser != null) ? cgiUser : "(null)") +
                        "] password [" +
                        ((cgiPassword != null) ? cgiPassword : "(null)") +
                        "]";
                System.out.println(statusMessage);
                return;
            }
            statusMessage = "I/O Error reading form" + e;
            System.out.println(statusMessage);
            return;
        } finally {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
            } catch (IOException e) {
            }
        }
        if (!found) {
            statusMessage = "Error getting extra information for user [" + getUserId() + "]";
            System.out.println(statusMessage);
        }
    }

    static public String statusMessage;

    /**
     * synchronized static public Vector callCGI(Applet applet, String url,String cgiProgram, String query)
     * {
     * PalTerm		palTerm = (PalTerm)applet;
     * String		urlStr = null;
     * Vector lines = new Vector();
     * statusMessage = null;
     * <p>
     * if (url != null)
     * urlStr = url + palTerm.cgiDir + cgiProgram + palTerm.cgiExtension;
     * else
     * urlStr = palTerm.protocol + "://" + palTerm.serverHost + ":" + palTerm.serverPort + palTerm.cgiDir + cgiProgram + palTerm.cgiExtension;
     * <p>
     * URL		baseUrl = null;
     * try{	baseUrl = new URL(urlStr);
     * }
     * catch (Exception e)
     * {
     * statusMessage = "Malformed URL [" + urlStr + "]";
     * return null;
     * }
     * <p>
     * StringBuffer encodedString= new StringBuffer(query);
     * URLConnection	connection	= null;
     * BufferedReader	in			= null;
     * PrintWriter		out			= null;
     * String			inputLine	;
     * <p>
     * try {
     * connection = baseUrl.openConnection();
     * connection.setDoOutput(true);     // use POST method to pass data
     * connection.setDoInput(true);      // process incoming CGI stream
     * connection.setUseCaches(false);   // disable caching of documents
     * connection.setAllowUserInteraction(true);
     * <p>
     * String authCookie	= UserInfo.getAuthCookie();
     * if (authCookie != null)
     * connection.setRequestProperty("Authorization", authCookie);
     * connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
     * connection.setRequestProperty("Content-length", encodedString.length()+"");
     * //		if (debug)
     * System.out.println("CGI-REQUEST:" + encodedString);
     * out = new PrintWriter(connection.getOutputStream());
     * out.print(encodedString);
     * out.flush();
     * out.close();
     * out = null;
     * <p>
     * // Read entire input stream into a string buffer
     * in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
     * <p>
     * while ( (inputLine = in.readLine()) != null) {
     * System.out.println("line = "+inputLine);
     * lines.addElement(inputLine);
     * }
     * <p>
     * }
     * catch (MalformedURLException e)
     * {
     * statusMessage = "Error: The URL is not correct "+baseUrl+"?"+query;
     * return null;
     * }
     * catch (IOException e)
     * {
     * if (connection.getHeaderField("WWW-authenticate") != null)
     * {
     * //showAuthFailedMessage();
     * statusMessage = "Authorization Failed";
     * return null;
     * }
     * statusMessage = "I/O Error reading form " + e;
     * return null;
     * }
     * finally
     * {
     * try
     * {
     * if (out != null) out.close();
     * if (in != null) in.close();
     * }
     * catch (IOException e) { }
     * }
     * return lines;
     * }
     **/

    synchronized static public boolean callCGI(Applet applet, String url, String cgiProgram, String query, HTTPProcessLineInterface lineProcessor) {
        PalTerm palTerm = (PalTerm) applet;
        String urlStr = null;
        statusMessage = null;

        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        {
            boolean success = false;

            if (palTerm.isNAV) {
                // try to get Netscape permission
                try {
                    throw new IllegalArgumentException("Netscape Browser support has been removed");
                    //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                    //success = true;

                    //Class clazz = Class.forName("netscape.security.PrivilegeManager");
                    //if (clazz != null)
                    //{
                    //	Class[] parameterTypes = new Class[1];
                    //	parameterTypes[0] = " ".getClass();
                    //	Method mid = clazz.getMethod("enablePrivilege", parameterTypes);
                    //	Object[]  args = new Object[1];
                    //	args[0] = "TerminalEmulator";
                    //	mid.invoke(null, args);
                    //}
                } catch (Throwable e) {
                    success = false;
                }
            }

            if (palTerm.isIE) {
                // try Microsoft permission
                //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                try {
                    throw new IllegalArgumentException("Microsft Internet Explorer support has been removed");
                    //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                    //if (debug)
                    //    System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                    //success = true;
                    ////Class clazz = Class.forName("com.ms.security.PolicyEngine");
                    ////if (clazz != null)
                    ////{
                    ////	Class[] parameterTypes = new Class[1];
                    ////	parameterTypes[0] = Class.forName("com.ms.security.PermissionID");
                    ////	Method mid = clazz.getMethod("assertPermission", parameterTypes);
                    ////	Field	field = clazz.getField("NETIO");
                    ////	Object[]  args = new Object[1];
                    ////	args[0] = field.get(null);
                    ////	mid.invoke(null, args);
                    ////	if (debug)
                    ////		System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                    ////}
                    ////else
                    ////{
                    ////	System.out.println("Unknown browser: neither Netscape Navigator nor Microsoft Internet Explorer");
                    ////	System.out.println("    Cannot request permission to escape applet security for network I/O");
                    ////}
                } catch (Throwable e) {
                    success = false;
                }
            }
        }

        if (url != null)
            urlStr = url + palTerm.cgiDir + cgiProgram + palTerm.cgiExtension;
        else
            urlStr = palTerm.protocol + "://" + palTerm.serverHost + ":" + palTerm.serverPort + palTerm.cgiDir + cgiProgram + palTerm.cgiExtension;

        URL baseUrl = null;
        try {
            baseUrl = new URL(urlStr);
        } catch (Exception e) {
            statusMessage = "Malformed URL [" + urlStr + "]";
            return false;
        }

        StringBuffer encodedString = new StringBuffer(query);
        URLConnection connection = null;
        BufferedReader in = null;
        PrintWriter out = null;
        String inputLine;

        try {
            connection = baseUrl.openConnection();
            connection.setDoOutput(true);     // use POST method to pass data
            connection.setDoInput(true);      // process incoming CGI stream
            connection.setUseCaches(false);   // disable caching of documents
            connection.setAllowUserInteraction(true);

            String authCookie = UserInfo.getAuthCookie();
            if (authCookie != null)
                connection.setRequestProperty("Authorization", authCookie);
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-length", encodedString.length() + "");
//		if (debug)
            System.out.println("CGI-REQUEST:" + encodedString);
            out = new PrintWriter(connection.getOutputStream());
            out.print(encodedString);
            out.flush();
            out.close();
            out = null;

            // Read entire input stream into a string buffer
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                if (debug)
                    System.out.println("line = " + inputLine);
                lineProcessor.processLine(inputLine);
            }

        } catch (MalformedURLException e) {
            statusMessage = "Error: The URL is not correct " + baseUrl + "?" + query;
            return false;
        } catch (IOException e) {
            if (connection.getHeaderField("WWW-authenticate") != null) {
                //showAuthFailedMessage();
                statusMessage = "Authorization Failed";
                return false;
            }
            statusMessage = "I/O Error reading form " + e;
            return false;
        } finally {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
            } catch (IOException e) {
            }
        }
        return true;
    }

    synchronized static public boolean getFileContents(Applet applet, Component comp, boolean local, String relativePath, StringBuffer contents) {
        PalTerm palTerm = (PalTerm) applet;
        String urlStr = null;
        String inputLine;

        boolean retVal = false;

        //if (!palTerm.isStandalone)
        {
            // need to get out of the sandbox when running from a jar file on local machine
            // note: this code is repeated in every method that uses it !
            boolean success = false;

            if (palTerm.isNAV) {
                // try to get Netscape permission
                try {
                    throw new IllegalArgumentException("Netscape Browser support has been removed");
                    //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                    //success = true;
                } catch (Throwable e) {
                    success = false;
                }
            }

            if (palTerm.isIE) {
                // try Microsoft permission
                //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                try {
                    throw new IllegalArgumentException("Microsft Internet Explorer support has been removed");
                    //if (local) {
                    //    com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                    //    if (debug)
                    //        System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                    //} else {
                    //    com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                    //    if (debug)
                    //        System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                    //}
                    //success = true;
                } catch (Throwable e) {
                    success = false;
                }
            }
        }

        comp.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        if (local) {
            String filePath = palTerm.localDirectory + relativePath;
            filePath = filePath.replace('/', File.separatorChar);

            try {
                // Read input stream line by line
                FileReader fin = new FileReader(filePath);
                BufferedReader br = new BufferedReader(fin);
                while ((inputLine = br.readLine()) != null) {
                    contents.append(inputLine);
                    contents.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("I/O error reading options from [" + filePath + "]");
            } finally {
                retVal = true;
            }
        } else {
            // need to get out of the sandbox when running from a jar file on local machine
            // note: this code is repeated in every method that uses it !

            String filePath = palTerm.docBaseDir + relativePath;

            try {
                URL url = new URL(palTerm.protocol, palTerm.serverHost, palTerm.serverPort, filePath);
                URLConnection connection;
                BufferedReader in = null;
                try {
                    connection = url.openConnection();
                    connection.setDoOutput(false);     // use POST method to pass data
                    connection.setDoInput(true);      // process incoming CGI stream
                    connection.setUseCaches(false);   // disable caching of documents
                    connection.setAllowUserInteraction(true); // Allow user interaction

                    String authCookie = UserInfo.getAuthCookie();
                    if (authCookie != null)
                        connection.setRequestProperty("Authorization", authCookie);
                    connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("Content-length", "0");

                    in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    while ((inputLine = in.readLine()) != null) {
                        if (debug)
                            System.out.println("line = " + inputLine);
                        contents.append(inputLine);
                        contents.append("\n");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    System.out.println("Invalid URL for loading file [" + url + "]");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("I/O error reading file from web server [" + url + "]");
                } finally {
                    try {
                        if (in != null) in.close();
                    } catch (IOException e) {
                    }
                }
                retVal = true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Could not read file from web server");
            }
        }
        comp.setCursor(Cursor.getDefaultCursor());
        return retVal;
    }

    synchronized static public boolean getLocalFileContents(Applet applet, boolean isStandalone, String filePath, StringBuffer contents) {
        PalTerm palTerm = (PalTerm) applet;
        String inputLine;

        boolean retVal = false;

        //if (!isStandalone)
        //{
        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        boolean success = false;

        if (palTerm.isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalFileAccess");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                //if (debug)
                //    System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }
        //}

        filePath = filePath.replace('/', File.separatorChar);

        try {
            // Read input stream line by line
            FileReader fin = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fin);
            while ((inputLine = br.readLine()) != null) {
                contents.append(inputLine);
                contents.append("\n");
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("I/O error reading options from [" + filePath + "]");
        } finally {
            retVal = true;
        }
        return retVal;
    }

    synchronized static public byte[] getLocalFileContents(Applet applet, boolean isStandalone, String filePath) {
        PalTerm palTerm = (PalTerm) applet;
        byte[] retVal = null;

        boolean success = false;

        if (palTerm.isNAV) {
            // need to get out of the sandbox when running from a jar file on local machine
            // note: this code is repeated in every method that uses it !
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalFileAccess");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                //if (debug)
                //    System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        filePath = filePath.replace('/', File.separatorChar);

        FileInputStream fin = null;
        ByteArrayOutputStream bin = null;

        try {
            // Read input stream as a byte array
            File f = new File(filePath);
            byte[] contents = new byte[512];

            fin = new FileInputStream(filePath);
            bin = new ByteArrayOutputStream();
            int readCnt = 0;
            int sleepTime = 0;
            while (sleepTime < 10000) {
                readCnt = fin.read(contents, 0, contents.length);
                if (readCnt > 0)
                    bin.write(contents, 0, readCnt);
                else if (readCnt < 0)
                    break;
                else {
                    sleepTime += 100;
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                }
            }
            retVal = bin.toByteArray();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("I/O error reading from [" + filePath + "]");
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                    fin = null;
                }
            } catch (Exception e) {
                fin = null;
            }

            try {
                if (bin != null) {
                    bin.close();
                    bin = null;
                }
            } catch (Exception e) {
                bin = null;
            }
        }
        return retVal;
    }

    synchronized static public void setLocalFileContents(Applet applet, boolean isStandalone, String filePath, byte[] contents, int contentLength) {
        PalTerm palTerm = (PalTerm) applet;
        boolean success = false;

        if (palTerm.isNAV) {
            // need to get out of the sandbox when running from a jar file on local machine
            // note: this code is repeated in every method that uses it !
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalFileAccess");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                //if (debug)
                //    System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        filePath = filePath.replace('/', File.separatorChar);

        try {
            File tmpFile1 = new File(filePath);
            File tmpFile2 = new File(tmpFile1.getParent());
            if (!tmpFile2.exists())
                tmpFile2.mkdirs();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        FileOutputStream fout = null;

        try {
            if (contentLength <= 0)
                contentLength = contents.length;
            fout = new FileOutputStream(filePath);
            fout.write(contents, 0, contentLength);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I/O error writing to [" + filePath + "]");
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                    fout = null;
                }
            } catch (Exception e) {
                fout = null;
            }
        }
    }

    public String getHomeDirectory(Applet applet) {
        PalTerm palTerm = (PalTerm) applet;
        String homeDir = null;

        boolean success = false;

        if (palTerm.isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //// http://developer.netscape.com/docs/manuals/signedobj/targets/index.htm
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            try {
                // http://www.microsoft.com/java/sdk/32/start.htm?http://www.microsoft.com/Java/sdk/32/packages/ref_security_package_81.htm
                throw new IllegalArgumentException("Microsft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.PROPERTY);
                //if (debug)
                //    System.out.println("Got PROPERTY permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        homeDir = System.getProperty("user.home");
        return homeDir;
    }

    /**
     * Login to web server
     *
     * @param parentFrame owner of dialog that may have to be created
     **/
    static boolean userLogin(Applet applet, Frame parentFrame) {
        PalTerm palTerm = (PalTerm) applet;
        String loginScript = palTerm.cgiDir + palTerm.pgm_login + palTerm.cgiExtension;
        URL loginURL;
        URLConnection connection = null;
        BufferedReader in = null;
        PrintWriter out = null;
        AuthenticateDialog authDialog = null;
        String authCookie = null;
        String line;
        boolean cancel = false;
        int tryCount = 0;

        if (!palTerm.retrieveWebId) {
            if (debug)
                System.out.println("UserInfo.userLogin(): returning without determining the webuserid, flag retrieveWebId is [false]");
            return false;
        }

        boolean success = false;
        // need to get out of the sandbox when running from a jar file on local machine
        if (palTerm.isNAV) {
            // try to get Netscape permission
            try {
                //PrivilegeManager.enablePrivilege("TerminalEmulator");
                // for more targets goto:
                //http://developer.netscape.com/docs/manuals/signedobj/targets/index.htm
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalPrintJobAccess");

                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //success = true;

                //Class clazz = Class.forName("netscape.security.PrivilegeManager");
                //if (clazz != null)
                //{
                //	Class[] parameterTypes = new Class[1];
                //	parameterTypes[0] = " ".getClass();
                //	Method mid = clazz.getMethod("enablePrivilege", parameterTypes);
                //	Object[]  args = new Object[1];
                //	args[0] = "TerminalEmulator";
                //	mid.invoke(null, args);
                //}
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                //if (debug)
                //    System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                //success = true;
                ////Class clazz = Class.forName("com.ms.security.PolicyEngine");
                ////if (clazz != null)
                ////{
                ////	Class[] parameterTypes = new Class[1];
                ////	parameterTypes[0] = Class.forName("com.ms.security.PermissionID");
                ////	Method mid = clazz.getMethod("assertPermission", parameterTypes);
                ////	Field	field = clazz.getField("NETIO");
                ////	Object[]  args = new Object[1];
                ////	args[0] = field.get(null);
                ////	mid.invoke(null, args);
                ////	if (debug)
                ////		System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                ////}
                ////else
                ////{
                ////	System.out.println("Unknown browser: neither Netscape Navigator nor Microsoft Internet Explorer");
                ////	System.out.println("    Cannot request permission to escape applet security for network I/O");
                ////}
            } catch (Throwable e) {
                success = false;
            }
        }

        try {
            loginURL = new URL("http", palTerm.serverHost, palTerm.serverPort, loginScript);
        } catch (MalformedURLException e) {
            System.err.println("Could not login user. Bad URL for login CGI program: http://" + palTerm.serverHost + ":" + palTerm.serverPort + loginScript);
            return false;
        }

        boolean createdNewFrame = false;
        if (parentFrame == null) {
            createdNewFrame = true;
            parentFrame = new Frame();
            parentFrame.setSize(30, 30);
        }

        while (!cancel) {
            tryCount++;
            try {
                connection = loginURL.openConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection = loginURL.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setUseCaches(false);
                connection.setAllowUserInteraction(true); // Allow user interaction
                authCookie = getAuthCookie();
                if (authCookie != null)
                    connection.setRequestProperty("Authorization", authCookie);
                connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-length", "0");
                out = new PrintWriter(connection.getOutputStream());
                out.print("");
                out.flush();
                out.close();
                out = null;

                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                if (connection.getHeaderField("WWW-authenticate") != null) {
                    System.out.println(connection.getHeaderField("WWW-authenticate"));
                    throw new Exception();
                }
                while ((line = in.readLine()) != null) {
                    String searchString = "REMOTE_USER is ";
                    int idx = line.indexOf(searchString);
                    if (idx > 0) {
                        String user = line.substring(idx + searchString.length());
                        if (debug)
                            System.out.println("REMOTE USER is " + user);
                        setUserId(user);
                        break;
                    }
                }
                if (in != null) {
                    in.close();
                    in = null;
                }
                break;
            } catch (Exception e) {
                // debug info - sometimes the dialog does not complete
                if (tryCount > 1) {
                    e.printStackTrace();
                    System.out.println("loginURL is [" + loginURL + "]");
                    if (authCookie != null)
                        System.out.println("authCookie is not null");
                    else
                        System.out.println("authCookie is null");
                    System.out.println("User Id [" + getUserId() + "] password [" + getPassword() + "]");
                }

                //if (connection.getHeaderField("WWW-authenticate") != null)
                if (tryCount > 1) {
                    if (authDialog == null) {
                        authDialog = new AuthenticateDialog(parentFrame, "PalTerm: Authentication");
                        authDialog.setBackground(parentFrame.getBackground());
                        //if (FrameTerminal.currentPrefs != null)
                        //{
                        //	authDialog.setTextFgColor(JedFrame.currentPrefs.inputFgColor);
                        //	authDialog.setTextBgColor(JedFrame.currentPrefs.inputBgColor);
                        //}
                    } else {
                        authDialog.setMessage("Login failed. Please try again.");
                        if (debug) {
                            System.out.println("Login failed. for userId[" + userid + "] password [" + password + "]");
                            System.out.println("              to URL [" + loginURL + "]");
                        }
                    }
                    authDialog.show();
                    if (authDialog.getCommand() == AuthenticateDialog.CANCEL)
                        cancel = true;
                }
            } finally {
                try {
                    if (in != null) {
                        in.close();
                        in = null;
                    }
                } catch (IOException e) {
                }
            }
        }
        if (createdNewFrame) {
            parentFrame.dispose();
            parentFrame = null;
        }

        if (cancel)
            return false;
        return true;
    }


}
