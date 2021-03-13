package com.prasad.terminal;
/*
 * PalTerm
 * ----------------------------------------------------------------------
 */

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.*;
import java.io.*;
import java.util.*;

import mpTOOLS.mpEDIT.ImageLoader;

public class PalTerm extends Applet implements Parameters, ImageLoader {

    boolean debug = false;
    boolean isIE = false;
    boolean isNAV = false;

    Applet applet;                    // set only in case this is a real applet
    boolean isStandalone = false;
    static UserInfo userInfo = new UserInfo(); // to make sure that userInfo does not get garbage collected

    FrameTerminal frameTerminal;    // if standAlone or not embedded within web page
    PanelTerminal panelTerminal;    // if embedded within web page
    private Frame hiddenParentFrame;
    boolean noFrame;  // if this is set, then no frameTerminal is created.

    static public String buildTag = "";
    boolean initialized = false;

    /**
     * Whether the applet is in debug mode
     */
    public static final String PARAM_DEBUG = "debug";

    /**
     * Whether the applet is running in Microsoft Internet Explorer
     */
    public static final String PARAM_ISIE = "isIE";

    /**
     * Whether the applet is running in Netscape Navigator
     */
    public static final String PARAM_ISNAV = "isNAV";

    /**
     * Whether the applet should create a frame. If this is set to true
     * then a frame is not created and the applet only appears in the
     * applet area on the HTML page.
     */
    public static final String PARAM_NOFRAME = "noFrame";

    /**
     * Name of the terminal emulator
     */
    public static final String PARAM_EMULATOR_NAME = "emulator";

    /**
     * Host to which the terminal should telnet to
     */
    public static final String PARAM_TELNETHOST = "telnetHost";

    /**
     * Port to which the terminal should telnet to
     */
    public static final String PARAM_TELNETPORT = "telnetPort";

    /**
     * Suffix to apply to cgiPrograms - useful for NT
     */
    public static final String PARAM_CGIEXTENSION = "cgiExtension";

    /**
     * Protocol used. Normally this should not
     * be set. The applet can determine the protocol used ("http" or "https").
     * Setting protocol will redirect all the URL calls made from the applet.
     */
    public static final String PARAM_PROTOCOL = "protocol";

    /**
     * Host from which this applet is running. Normally this should not
     * be set. The applet can determine the host. Setting serverHost will
     * redirect all the URL calls made from the applet.
     */
    public static final String PARAM_SERVERHOST = "serverHost";

    /**
     * Port from which this applet is running. Normally this should not
     * be set. The applet can determine the port. Setting serverPort will
     * redirect all the URL calls made from the applet.
     */
    public static final String PARAM_SERVERPORT = "serverPort";

    /**
     * Directory to the document base directory. Normally this should not
     * be set. The applet can determine the document base. If docBaseDir is
     * set then it will be used to determine paths to files (e.g. options for the user).
     */
    public static final String PARAM_DOCBASEDIR = "docBaseDir";

    /**
     * Directory where CGI programs are stored. Normally this should not
     * be set. The default value of this is "/cgi-bin".
     */
    public static final String PARAM_CGIDIR = "cgiDir";

    /**
     * Web User Id of the user. Normally this should not
     * be set for an applet.
     */
    public static final String PARAM_USERNAME = "userName";

    /**
     * Web User Password for the user. Normally this should not
     * be set for an applet.
     */
    public static final String PARAM_USERPASSWD = "userPasswd";

    /**
     * Name of the DME program. Normally this should not
     * be set. The default value of this is "dme".
     */
    public static final String PARAM_DME = "dme";

    /**
     * Name of the login program. Normally this should not
     * be set. The default value of this is "login".
     */
    public static final String PARAM_LOGIN = "login";

    /**
     * Name of the preferences save program. Normally this should not
     * be set. The default value of this is "prefsave".
     */
    public static final String PARAM_PREFSAVE = "prefsave";

    /**
     * Name of the program to list directory. Normally this should not
     * be set. The default value of this is "secdirlist".
     */
    public static final String PARAM_SECDIRLIST = "secdirlist";
    /**
     * retrieves the web user id. A cgi program called
     * login" (or one specified by PARAM_LOGIN) is called
     * to determine the web user id.
     */
    public static final String PARAM_RetrieveWebId = "retrieveWebId";
    /**
     * retrieves the login id for corresponding to
     * the current web user id. This is relevant for the
     * Lawson environment. A DME CGI call is made to determine
     * the id.
     */
    public static final String PARAM_RetrieveLoginForWeb = "retrieveLoginForWeb";
    /**
     * Options are saved locally (and not on server).
     * The "localDirectory" must be specified for this to work
     * correctly. This option is useful for a local install.
     */
    public static final String PARAM_UseLocalOptions = "useLocalOptions";
    /**
     * Images are loaded locally (and not from server).
     * The "localDirectory" must be specified for this to work
     * correctly. This option is useful for a local install.
     */
    public static final String PARAM_UseLocalImages = "useLocalImages";
    /**
     * LaTree configuation is saved locally (and not on server).
     * The "localDirectory" must be specified for this to work
     * correctly. This option is useful for a local install.
     */
    public static final String PARAM_UseLocalLaTree = "useLocalLaTree";
    /**
     * Terminal Info is available locally (and not on server).
     * The "localDirectory" must be specified for this to work
     * correctly. This option is useful for a local install.
     **/
    public static final String PARAM_UseLocalTermInfo = "useLocalTermInfo";
    /**
     * Local directory under which options will be	saved, if the
     * useLocalOptions is set to true.
     * This directory is also used for loading images if useLocalImages is true.
     * This is	useful for a local install.
     */
    public static final String PARAM_LocalDirectory = "localDirectory";
    /**
     * Should LaTree be shown. This is useful for a Lawson
     * environment. Typing LaTree at the command prompt will
     * activate a tree view of all the Lawson Product Lines.
     * This view can be customized by the user. Running
     * LaTree requires access to lawson CGI programs that
     * return information about nodes at each tree node.
     * This programs are normally installed when Lawson's
     * Network Enterprise Desktop is installed.
     */
    public static final String PARAM_UseLaTree = "useLaTree";
    /**
     * Enable the install menu option. This menu item
     * will allow the user to install signed cab and jar files
     * on to the current client. The installation can be
     * specified as a server or a client install. Server installation
     * is for access by multiple users on a single host.
     * Client install is for a local install, usually for a single
     * user on a single machine.
     */
    public static final String PARAM_Install_Enable = "install.enable";
    /**
     * Host from which the CAB and JAR files are downloaded.
     */
    public static final String PARAM_Install_SourceHost = "install.sourceHost";
    /**
     * Source directory path on the host
     */
    public static final String PARAM_Install_SourceDir = "install.sourceDir";
    /**
     * UserId and Password used for installing from Source directory
     */
    public static final String PARAM_Install_SourceUserId = "install.sourceUserId";
    public static final String PARAM_Install_SourcePasswd = "install.sourcePasswd";
    /**
     * Version and Compile Date for the installed software
     */
    public static final String PARAM_Install_Version = "install.version";
    public static final String PARAM_Install_CompileDate = "install.compileDate";
    /**
     * Maximum time in milliseconds to wait for firstChar
     */
    public static final String PARAM_SmartMouse_FirstChar_MaxWaitTime = "smartMouse.firstChar.maxWaitTime";
    /**
     * Time in milliseconds between characters after the first one is received
     */
    public static final String PARAM_SmartMouse_InterChar_WaitTime = "smartMouse.interChar.waitTime";


    public String cgiExtension;
    public String protocol;
    public String telnetHost;
    public int telnetPort = 23;
    public String serverHost;
    public int serverPort = -1;
    public String docBaseDir;        // includes trailing slash
    public String cgiDir;            // includes trailing slash
    public boolean retrieveWebId;
    public boolean retrieveLoginForWeb;
    public boolean useLocalOptions;
    public boolean useLocalImages;
    public boolean useLocalLaTree;
    public boolean useLocalTermInfo;
    public String localDirectory;    // includes trailing slash
    public boolean useLaTree;
    public String emulatorName;    // name of the terminal emulator (e.g "pt80-e")
    public int smartMouse_FirstChar_MaxWaitTime = 200; // maximum number of milliseconds to wait for first character to arrive
    public int smartMouse_InterChar_WaitTime = 5; // number of milliseconds to wait between characters
    public final String ftpConnectFile = "logins";

    public boolean install_enabled;
    public String install_sourceHost;
    public String install_sourceDir;
    public String install_sourceUserId;
    public String install_sourcePasswd;
    public String install_version;
    public String install_compileDate;

    public String pgm_dme;
    public String pgm_login;
    public String pgm_prefsave;
    public String pgm_secdirlist;

    public PalTerm() {
    }

    @Override
    public String getAppletInfo() {
        return ("Prasad Java Terminal v" + VersionDialog.VERSION + "\n\n");
    }

  /*
  public String[][] getParameterInfo()
  {
	String[][]	telnet_info		= Telnet.getParameterInfo();
	String[][]	terminal_info	= Terminal.getParameterInfo();
	String[][]	applet_info		= {
				  { "host", "string", "initial name of telnet host" },
				  { "port", "integer", "initial port number (Telnet is 23)" },
				  { "emulator", "string", "initial emulator class" }
	};
	String[][]	info			=
				  new String[telnet_info.length + terminal_info.length +
						applet_info.length][];

	int i = 0;

	for ( int pos = 0 ; i < telnet_info.length ; i++, pos++ )
		info[i] = telnet_info[pos];

	for ( int pos = 0 ; i < terminal_info.length ; i++, pos++ )
		info[i] = terminal_info[pos];

	for ( int pos = 0 ; i < applet_info.length ; i++, pos++ )
		info[i] = applet_info[pos];


	return info;
  }
  */

    //Get a parameter value
    @Override
    public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) :
            (getParameter(key) != null ? getParameter(key) : def);
    }

    /* Implement our parameter interface */
    @Override
    public String getParameter(String name) {
        String retVal = null;
        try {
            retVal = super.getParameter(name);
        } catch (Exception e) {
            retVal = null;
        }
        return retVal;
    }

    @Override
    public boolean isApplet() {
        return !isStandalone;
    }

    @Override
    public void init() {
        String val;

        val = getParameter(PARAM_DEBUG, null);
        if (val != null) {
            try {
                debug = Boolean.valueOf(val).booleanValue();
            } catch (Exception e) {
                noFrame = false;
            }
        }

        val = getParameter(PARAM_ISIE, null);
        if (val != null) {
            try {
                isIE = Boolean.valueOf(val).booleanValue();
            } catch (Exception e) {
                noFrame = false;
            }
        }

        val = getParameter(PARAM_ISNAV, null);
        if (val != null) {
            try {
                isNAV = Boolean.valueOf(val).booleanValue();
            } catch (Exception e) {
                noFrame = false;
            }
        }

        this.applet = this;

        if (!isStandalone) {
            val = getParameter(PARAM_NOFRAME, null);
            if (val != null) {
                try {
                    noFrame = Boolean.valueOf(val).booleanValue();
                } catch (Exception e) {
                    noFrame = false;
                }
            }
        }

        if (noFrame)
            hiddenParentFrame = new Frame();

        parseTerminalParameters((Parameters) this);

        Label lab;

        super.init();

        repaint();

        if (debug) {
            System.out.println("PalTerm: Requested serverHost: " + serverHost);
            System.out.println("PalTerm: Requested serverPort: " + serverPort);
        }


        userInfo.setDebug(debug);

        if (noFrame) {
            panelTerminal = new PanelTerminal();
            panelTerminal.setPalTerm(this);
            this.add(panelTerminal);
            panelTerminal.setDebug(debug);

            panelTerminal.terminalInit();
            if (!written_copyright) {
                written_copyright = true;
                panelTerminal.writeCopyright();
            }
            if (telnetHost != null)
                panelTerminal.connect(telnetHost, telnetPort, null, null);
            else if (serverHost != null)
                panelTerminal.connect(serverHost, telnetPort, null, null);
            panelTerminal.readDefaultOptions();
        } else {
            frameTerminal = new FrameTerminal();
            frameTerminal.setApplet(this);
            frameTerminal.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        FrameTerminal f = (FrameTerminal) e.getSource();
                        f.dispose();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
            frameTerminal.setDebug(debug);
            frameTerminal.enableInstall(install_enabled);
            frameTerminal.show();

            frameTerminal.terminalInit();
            if (!written_copyright) {
                written_copyright = true;
                frameTerminal.writeCopyright();
            }
            if (telnetHost == null
                || telnetHost.equalsIgnoreCase("unknown"))
                frameTerminal.menuItemConnect_actionPerformed(null);
            else
                frameTerminal.connect(telnetHost, telnetPort, null, null);

            frameTerminal.readDefaultOptions();
        }

        initialized = true;
    }

    @Override
    public void destroy() {
        frameTerminal.disconnect();
        frameTerminal.dispose();
    }

    boolean written_copyright = false;

    @Override
    public void start() {
        if (!written_copyright) {
            written_copyright = true;
            if (frameTerminal != null)
                frameTerminal.writeCopyright();
            else if (panelTerminal != null)
                panelTerminal.writeCopyright();

            // Special code for checking Unicode symbols...
            if (false) {
                int[] sections = {
                    0x0000, 0x0040, 0x0080, 0x00c0,
                    0x0100, 0x0140, 0x0180, 0x01c0,
                    0x2500, 0x2540, 0x2580, 0x25c0,
                    0x2600, 0x2640, 0x2680, 0x26c0
                };
                StringBuffer str = new StringBuffer(80);
                for (int i = 0; i < sections.length; i++) {
                    str.setLength(0);
                    String numstr = Integer.toString(sections[i], 16);
                    for (int j = 4; j > numstr.length(); j--)
                        str.append('0');
                    str.append(numstr);
                    str.append(": ");
                    for (int j = 0; j < 0x40; j++)
                        str.append((char) (sections[i] + j));
                    if (frameTerminal != null) {
                        frameTerminal.terminalWrite(str.toString()/*,PanelTerminal.OUTF_RAW*/);
                        frameTerminal.terminalWrite("\r\n"/*,0*/);
                    } else if (panelTerminal != null) {
                        panelTerminal.terminalWrite(str.toString()/*,PanelTerminal.OUTF_RAW*/);
                        panelTerminal.terminalWrite("\r\n"/*,0*/);
                    }
                }
            }
        }
        if (frameTerminal != null)
            frameTerminal.show();

        //BackgroundClassLoaderThread backgroundClassLoaderThread = new BackgroundClassLoaderThread();
        //backgroundClassLoaderThread.setDaemon(true);
        //backgroundClassLoaderThread.start();
    }

    @Override
    public void stop() {
        //	if (frameTerminal != null )
        //		frameTerminal.hide();
    }

    //Main method
    static public void main(String[] args) {
        Properties props = new Properties(System.getProperties());
        String propsfile = System.getProperty("user.home") + File.separator + "termrc";
        // Read configuration file: termrc
        try {
            FileInputStream fin = new FileInputStream(propsfile);
            BufferedInputStream bis = new BufferedInputStream(fin);
            props.load(bis);
            bis.close();
            System.setProperties(props);
            System.out.println("Read initialization file [" + propsfile + "]");
        } catch (FileNotFoundException e) {
            String error = "Could not open TERMINAL properties file: " + propsfile + "\n" +
                "Please create this file if it does not exist";
            System.out.println(error);
            //return;
        } catch (Exception e) {
            e.printStackTrace();
            String error = "Error while loading properties file [" + propsfile + "]";
            System.out.println(error);
            return;
        }

        PalTerm applet = new PalTerm();
        applet.isStandalone = true;
        applet.init();
        applet.start();
        applet.frameTerminal.pack();
    }

    private synchronized void parseTerminalParameters(Parameters p) {
        String val;
        int intVal;
        cgiExtension = p.getParameter(PARAM_CGIEXTENSION, "");

        serverHost = p.getParameter(PARAM_SERVERHOST, null);
        if (serverHost == null) {
            try {
                URL docBase = null;
                if (p.isApplet()
                    && (docBase = ((Applet) p).getDocumentBase()) != null)
                    serverHost = docBase.getHost();
                else
                    serverHost = "unknown";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protocol = p.getParameter(PARAM_PROTOCOL, null);
        if (protocol == null) {
            try {
                URL docBase = null;
                if (((Applet) p).isActive()
                    && (docBase = ((Applet) p).getDocumentBase()) != null)
                    protocol = docBase.getProtocol();
                else
                    protocol = "http";
            } catch (Exception e) {
                e.printStackTrace();
                protocol = "http";
            }
        } else {
            if (!protocol.equalsIgnoreCase("http")
                && !protocol.equalsIgnoreCase("https")) {
                System.out.println("Warning: Supplied protocol is neither \"http\" nor \"https\"");
            }
        }

        val = p.getParameter(PARAM_SERVERPORT, null);
        if (val == null) {
            try {
                URL docBase = null;
                if (((Applet) p).isActive()
                    && (docBase = ((Applet) p).getDocumentBase()) != null)
                    serverPort = docBase.getPort();
                else
                    serverPort = -1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                serverPort = Integer.parseInt(val);
            } catch (Exception e) {
                System.out.println("Parameter " + PARAM_SERVERPORT + " must specify a port number and not [" + val + "]");
                e.printStackTrace();
                serverPort = -1;
            }
        }

        telnetHost = p.getParameter(PARAM_TELNETHOST, null);
        telnetPort = 23;
        val = p.getParameter(PARAM_TELNETPORT, null);
        if (val != null) {
            try {
                telnetPort = Integer.parseInt(val);
            } catch (Exception e) {
                System.out.println("Parameter " + PARAM_SERVERPORT + " must specify a port number and not [" + val + "]");
                e.printStackTrace();
            }
        }

        // find the document base directory where the users options are kept
        // the actual location is not determined till later
        // since we don't want to make a URL call to determine the
        // user's webuserid yet.

        docBaseDir = p.getParameter(PARAM_DOCBASEDIR, null);
        if (docBaseDir == null) {
            try {
                URL docBase = null;
                if (((Applet) p).isActive()
                    && (docBase = ((Applet) p).getDocumentBase()) != null)
                    docBaseDir = docBase.getFile();
                else
                    docBaseDir = "/com/prasad/terminal/index.html";
                int idx = docBaseDir.lastIndexOf("/");
                if (idx >= 0)
                    docBaseDir = docBaseDir.substring(0, idx);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!docBaseDir.endsWith("/"))
            docBaseDir = docBaseDir + "/";

        cgiDir = p.getParameter(PARAM_CGIDIR, "/cgi-bin/");
        if (!cgiDir.endsWith("/"))
            cgiDir = cgiDir + "/";
        if (!cgiDir.startsWith("/"))
            cgiDir = "/" + cgiDir;

        pgm_dme = p.getParameter(PARAM_DME, "dme");
        pgm_login = p.getParameter(PARAM_LOGIN, "login");
        pgm_prefsave = p.getParameter(PARAM_PREFSAVE, "prefsave");
        pgm_secdirlist = p.getParameter(PARAM_SECDIRLIST, "secdirlist");


        val = p.getParameter(PARAM_USERNAME, null);
        if (val != null)
            userInfo.setUserId(val);
        val = p.getParameter(PARAM_USERPASSWD, null);
        if (val != null)
            userInfo.setPassword(val);

        val = getParameter(PARAM_RetrieveWebId, null);
        if (val != null) {
            try {
                retrieveWebId = Boolean.valueOf(val).booleanValue();
            } catch (Exception e) {
                retrieveWebId = false;
            }
        }

        val = getParameter(PARAM_RetrieveLoginForWeb, null);
        if (val != null) {
            try {
                retrieveLoginForWeb = Boolean.valueOf(val).booleanValue();
            } catch (Exception e) {
                retrieveLoginForWeb = false;
            }
        }

        val = getParameter(PARAM_UseLocalOptions, null);
        if (val != null) {
            try {
                useLocalOptions = Boolean.valueOf(val).booleanValue();
            } catch (Exception e) {
                useLocalOptions = false;
            }
        }

        val = getParameter(PARAM_UseLocalImages, null);
        if (val != null) {
            try {
                useLocalImages = Boolean.valueOf(val).booleanValue();
            } catch (Exception e) {
                useLocalImages = false;
            }
        }

        val = getParameter(PARAM_UseLocalLaTree, null);
        if (val != null) {
            try {
                useLocalLaTree = Boolean.valueOf(val).booleanValue();
            } catch (Exception e) {
                useLocalLaTree = false;
            }
        }

        val = getParameter(PARAM_UseLocalTermInfo, null);
        if (val != null) {
            try {
                useLocalTermInfo = Boolean.valueOf(val).booleanValue();
            } catch (Exception e) {
                useLocalTermInfo = false;
            }
        }

        val = getParameter(PARAM_UseLaTree, null);
        if (val != null) {
            try {
                useLaTree = Boolean.valueOf(val).booleanValue();
            } catch (Exception e) {
                useLaTree = false;
            }
        }

        localDirectory = getParameter(PARAM_LocalDirectory, null);
        if (localDirectory == null) {
            //if (useLocalOptions
            //||	useLocalImages
            //||	useLocalLaTree
            //||	useLocalTermInfo)
            {
                // set the local directory to something meaningful
                // If the applet is from the local file system, then set this directory to be the same
                // Otherwise, determine if the applet is running on Unix or Windows and use appropriate
                // defaults.
                if (((PalTerm) p).isStandalone
                    || !((Applet) p).isActive()) {
                    // application
                    localDirectory = userInfo.getHomeDirectory(this) + File.separator + "prasad" + File.separator + "terminal" + File.separator;
                    if (debug)
                        System.out.println("Standalone applications local directory is [" + localDirectory + "]");
                } else {
                    URL url = ((Applet) p).getDocumentBase();

                    String appletProtocol = url.getProtocol();
                    if (appletProtocol.equalsIgnoreCase("file")) {
                        // local applet
                        String appletBaseDir = url.getFile();
                        if (appletBaseDir.charAt(2) == ':'
                            && appletBaseDir.charAt(0) == '/')
                            appletBaseDir = appletBaseDir.substring(1);
                        int idx = appletBaseDir.lastIndexOf('/');
                        if (idx >= 0)
                            appletBaseDir = appletBaseDir.substring(0, idx);

                        appletBaseDir = appletBaseDir.replace('/', File.separatorChar);
                        localDirectory = appletBaseDir;
                        if (debug)
                            System.out.println("Local applet's local directory is [" + localDirectory + "]");
                    } else {
                        // remote applet
                        localDirectory = userInfo.getHomeDirectory(this) + File.separator + "prasad" + File.separator + "terminal" + File.separator;
                        if (debug)
                            System.out.println("Remote applet's local directory is [" + localDirectory + "]");
                    }
                }
            }
        }
        if (localDirectory != null) {
            if (!localDirectory.endsWith(File.separator))
                localDirectory = localDirectory + File.separator;
            if (debug)
                System.out.println("Local directory is [" + localDirectory + "]");
        }

	/*
	System.out.println("Start of -- Remove this code");
	try {
	String testFile = "logins";
	File f2 = new File(localDirectory + testFile);
	FileOutputStream fout = new FileOutputStream(f2);
		// encrypted string must be a multiple of BLOCK_SIZE (or 8)
	String password = "password2";
	byte[]	in		= password.getBytes();
	byte[] out 		= encrypt(in, -1, true, 0x20);
	fout.write(out);
	fout.close();
	FileInputStream fin = new FileInputStream(f2);
	in				= new byte[1024];
	int inLen 		= fin.read(in);
	out				= encrypt(in, inLen, false, 0x20);
	String password2 = new String(out, 0);
	System.out.println("Input password [" + password + "], out [" + password2 + "]");
	fin.close();
	} catch (Exception e) { e.printStackTrace();}
	System.out.println("End of --- Remove this code");
	*/

        emulatorName = getParameter(PARAM_EMULATOR_NAME, null);

        val = getParameter(PARAM_Install_Enable, null);
        if (val != null) {
            try {
                install_enabled = Boolean.valueOf(val).booleanValue();
            } catch (Exception e) {
                install_enabled = false;
            }
        }
        install_sourceHost = getParameter(PARAM_Install_SourceHost, null);
        install_sourceDir = getParameter(PARAM_Install_SourceDir, null);
        install_sourceUserId = getParameter(PARAM_Install_SourceUserId, null);
        install_sourcePasswd = getParameter(PARAM_Install_SourcePasswd, null);
        if (install_enabled) {
            if (install_sourceHost == null)
                install_sourceHost = serverHost;
            if (install_sourceDir == null)
                install_sourceDir = "/home/prasad-assoc/www/prasad/terminal";
        }

        install_version = getParameter(PARAM_Install_Version, null);
        install_compileDate = getParameter(PARAM_Install_CompileDate, null);

        try {
            intVal = Integer.parseInt(getParameter(PARAM_SmartMouse_FirstChar_MaxWaitTime, "200"));
            if (intVal < 1)
                intVal = 1;
        } catch (Exception e) {
            intVal = 200;
        }
        smartMouse_FirstChar_MaxWaitTime = intVal;

        try {
            intVal = Integer.parseInt(getParameter(PARAM_SmartMouse_InterChar_WaitTime, "5"));
            if (intVal < 1)
                intVal = 1;
        } catch (Exception e) {
            intVal = 5;
        }
        smartMouse_InterChar_WaitTime = intVal;

        // Now login and find out the login web user id
        Frame frame;
        if (frameTerminal != null)
            frame = frameTerminal;
        else
            frame = hiddenParentFrame;
        if (!userInfo.userLogin(this, frame))
            System.out.println("User login failed. Running as anonymous web user");
    }

    // allow functions to process the menu items in the Terminal Frame/Panel when running as
    // an applet

    public void connect() {
        if (frameTerminal != null)
            frameTerminal.menuItemConnect_actionPerformed(null);
        else if (panelTerminal != null) {
            if (telnetHost != null)
                panelTerminal.connect(telnetHost, telnetPort, null, null);
            else if (serverHost != null)
                panelTerminal.connect(serverHost, telnetPort, null, null);
        }
    }

    public void disconnect() {
        if (frameTerminal != null)
            frameTerminal.menuItemDisconnect_actionPerformed(null);
        else if (panelTerminal != null)
            panelTerminal.disconnect();
    }

    public void setupTextAttrs() {
        if (frameTerminal != null)
            frameTerminal.menuItemSetupTextAttrs_actionPerformed(null);
        else if (panelTerminal != null) {
            panelTerminal.setupTextAttrs();
            panelTerminal.setOptionsDirty(true);
        }
    }

    public void setupTextFont() {
        if (frameTerminal != null)
            frameTerminal.menuItemSetupTextFont_actionPerformed(null);
        else if (panelTerminal != null) {
            panelTerminal.selectFont(null);
            panelTerminal.setOptionsDirty(true);
        }
    }

    public void setupTermOptions() {
        if (frameTerminal != null)
            frameTerminal.menuItemSetupTermOptions_actionPerformed(null);
        else if (panelTerminal != null) {
            panelTerminal.showOptions();
            panelTerminal.setOptionsDirty(true);
        }
    }

    public void saveInitFile() {
        if (frameTerminal != null)
            frameTerminal.menuItemSaveInitFile_actionPerformed(null);
        else if (panelTerminal != null) {
            panelTerminal.saveOptions();
            panelTerminal.setOptionsDirty(false);
        }
    }

    public void openInitFile() {
        if (frameTerminal != null)
            frameTerminal.menuItemOpenInitFile_actionPerformed(null);
        else if (panelTerminal != null) {
            panelTerminal.openOptions();
            panelTerminal.setOptionsDirty(false);
        }
    }

    public void saveInitFileAs() {
        if (frameTerminal != null)
            frameTerminal.menuItemSaveInitFileAs_actionPerformed(null);
        else if (panelTerminal != null) {
            panelTerminal.saveOptionsAs();
            panelTerminal.setOptionsDirty(false);
        }
    }

    public void install(Color background, Color foreground, Font fixedFont) {
        FrameInstall frameInstall = new FrameInstall();
        frameInstall.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ((FrameInstall) e.getSource()).dispose();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        frameInstall.setApplet(this);
        frameInstall.setBackground(background);
        frameInstall.setForeground(foreground);
        frameInstall.setFixedFont(fixedFont);
        frameInstall.setSrcHost(install_sourceHost);
        frameInstall.setSrcDir(install_sourceDir);
        frameInstall.setSrcUserId(install_sourceUserId);
        frameInstall.setSrcPasswd(install_sourcePasswd);
        //frameInstall.pack();
        frameInstall.show();
        frameInstall.setClientInstall(true);
        frameInstall.setInstallLawsonExt(true);
    }

    /**
     * Configure the smart mouse parameters by asking the user.
     */
    public void configureSmartMouse(Frame f) {
        if (f == null) {
            if (frameTerminal != null)
                f = frameTerminal;
            else
                f = hiddenParentFrame;
        }
        PromptDialog dlg = new PromptDialog(f, "Smart Mouse Options", 3, null);

        dlg.setLabel(0, "");
        dlg.setTextField(0, "milliseconds");
        dlg.setTextFieldEnabled(0, false);

        dlg.setLabel(1, "First Character Timeout:");
        dlg.setTextField(1, "" + smartMouse_FirstChar_MaxWaitTime);

        dlg.setLabel(2, "Inter-Character Timeout:");
        dlg.setTextField(2, "" + smartMouse_InterChar_WaitTime);

        dlg.setEnterOK(true);
        dlg.setEscapeCancel(true);

        dlg.show();
        if (dlg.getResult().compareTo(dlg.OK) == 0) {
            int intVal;
            try {
                intVal = Integer.parseInt(dlg.getTextField(1));
                if (intVal < 1)
                    intVal = 1;
            } catch (Exception e) {
                intVal = 200;
            }
            smartMouse_FirstChar_MaxWaitTime = intVal;

            try {
                intVal = Integer.parseInt(dlg.getTextField(2));
                if (intVal < 1)
                    intVal = 1;
            } catch (Exception e) {
                intVal = 5;
            }
            smartMouse_InterChar_WaitTime = intVal;
        }
    }
    /** This class is used to load some of the classes in the background.
     *  PanelTerminal optimizes download time by using Class.forName() to
     *  dynamically load some of the classes. The download time becomes
     *  important at slow speeds. This thread will be started after
     *  PalTerm starts and will fetch in time for PanelTerminal to use.
     *  User can continue using the terminal while the download completes.
     */
    /**
     class BackgroundClassLoaderThread extends Thread
     {
     // this list should contain all the classNames loaded by class.forName() in
     // PanelTerminal and elsewhere
     String[]	classNames = {	"prasad.terminal.DualListBox",
     "prasad.terminal.FrameGetUnixEnv",
     "prasad.terminal.ShowLapmFormInterface",
     "prasad.terminal.TerminalSender",
     };
     public void run()
     {
     for (int i = 0 ; i < classNames.length; i++)
     {
     try {
     Class clazz = Class.forName(classNames[i]);
     if (!clazz.isInterface())
     {
     Object a = clazz.newInstance(); // this may fail for classes without a 0-arg constructor
     }
     if (debug)
     System.out.println("BackgroundClassLoaderThread: Loaded class [" + classNames[i] + "]");
     }
     catch (Exception e)
     {
     System.out.println("Error while loading class " + classNames[i]);
     e.printStackTrace();
     }
     }
     }
     }
     */

    /**
     * Load an image from the file system
     *
     * @param image_dir  the image directory
     * @param image_file the image file
     * @param observer   the image observer
     **/

    @Override
    public Image loadImage(String image_dir, String image_file, Component observer, boolean waitForImage) {

        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        //if (!isStandalone)
        {
            boolean success = false;
            // try to get Netscape permission
            if (isNAV) {
                try {
                    throw new IllegalArgumentException("Netscape Browser support has been removed");
                    //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                    //success = true;
                } catch (Throwable e) {
                    success = false;
                }
            }

            if (isIE) {
                // try Microsoft permission
                //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                try {
                    throw new IllegalArgumentException("Microsft Internet Explorer support has been removed");
                    //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                    //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                    //success = true;
                } catch (Throwable e) {
                    success = false;
                    System.out.println("PalTerm.loadImageLocal: Failed to get permissions for Microsoft Internet Explorer");
                }
            }
        }

        if (image_file == null)
            return null;

        if (image_dir != null) {
            char lastChar = image_dir.charAt(image_dir.length() - 1);
            if (lastChar == '/'
                || lastChar == '\\')
                image_file = image_dir + image_file;
            else
                image_file = image_dir + File.separator + image_file;
        }

        char firstChar = image_file.charAt(0);
        if (image_file.startsWith("/"))
            image_file = image_file.substring(1);

        Toolkit tk = Toolkit.getDefaultToolkit();

        Image new_image = null;
        String tmpImgPath = null;
        try {
            if (this.useLocalImages) {
                tmpImgPath = this.localDirectory + image_file;
                tmpImgPath = tmpImgPath.replace('/', File.separatorChar).replace('\\', File.separatorChar);
                new_image = tk.getImage(tmpImgPath);
            } else {
                tmpImgPath = image_file.replace('\\', '/');
                new_image = tk.getImage(new URL("http", this.serverHost, this.serverPort, this.docBaseDir + tmpImgPath));
            }
            observer.prepareImage(new_image, observer);

            // One second overhead per image is excessive - however 0 does not work  !

            if (!isStandalone
                && waitForImage) {
                for (int i = 0; i < 2; i++) {
                    if ((observer.checkImage(new_image, observer) & ImageObserver.ALLBITS) != 0)
                        break;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Bad image file: " + tmpImgPath);
        }

        if ((observer.checkImage(new_image, observer) & ImageObserver.ALLBITS) == 0) {
            System.err.println("Could not load image file completely: " + image_file);
            //new_image = null; some images are animated
        } else {
            //System.out.println("Loading image: " + image_file);
            System.out.println("Loading image: " + tmpImgPath);
        }

        return new_image;
    }

    /**
     * Editor for text files
     */
    private Object textEditor;

    public Object getTextEditor() {
        return textEditor;
    }

    public void setTextEditor(Object o) {
        textEditor = o;
    }

    public void exec(String s) {
        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        {
            boolean success = false;

            if (isNAV) {
                // try to get Netscape permission
                try {
                    throw new IllegalArgumentException("Netscape Browser support has been removed");
                    //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                    //netscape.security.PrivilegeManager.enablePrivilege("UniversalExecPermission");
                    //success = true;
                } catch (Throwable e) {
                    success = false;
                }
            }

            if (isIE) {
                // try Microsoft permission
                //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                try {
                    throw new IllegalArgumentException("Microsft Internet Explorer support has been removed");
                    //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                    //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                    //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.EXEC);
                    //success = true;
                } catch (Throwable e) {
                    success = false;
                    System.out.println("PalTerm.exec: Failed to get permissions for Microsoft Internet Explorer");
                }
            }
        }

        try {
            Runtime.getRuntime().exec(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SAFER crypter;

    /**
     * Encrypt or decrypt based on the encrypt flag.
     * "in" is the byte array to encrypt/decrypt and
     * "inLen" is the length. inLen can be -1 (meaning: use all bytes in in[])
     * trailing pad is the character that is added at the end to complete
     * a block size for the encrypting algorithm.
     * This character is removed from the end after decrypting as well.
     */
    byte[] encrypt(byte[] in, int inLen, boolean encryptFlag, int trailingPad) {
        byte[] retVal = null;

        if (in == null
            || in.length == 0)
            return retVal;

        if (inLen < 0
            || inLen > in.length)
            inLen = in.length;

        if (crypter == null)
            crypter = new SAFER();
        if (encryptFlag) {
            int padding = ((inLen % crypter.BLOCK_SIZE) == 0) ? 0 : (crypter.BLOCK_SIZE - (inLen % crypter.BLOCK_SIZE));
            if (padding > 0) {
                byte[] in2 = new byte[inLen + padding];
                System.arraycopy(in, 0, in2, 0, inLen);
                for (int i = 0; i < padding; i++)
                    in2[inLen + i] = (byte) trailingPad;
                in = in2;
                inLen += padding;
            }
            crypter.engineInitEncrypt(null);
        } else
            crypter.engineInitDecrypt(null);

        byte[] out = new byte[inLen * 2];
        int outLen = crypter.engineUpdate(in, 0, inLen, out, 0);

        if (!encryptFlag) {
            for (; outLen > 0; outLen--) {
                if (out[outLen - 1] != trailingPad)
                    break;
            }
        }

        if (outLen > 0) {
            retVal = new byte[outLen];
            System.arraycopy(out, 0, retVal, 0, outLen);
        }

        return retVal;
    }

    private Hashtable<String, FtpConnectInfo> ftpConnectInfoTable;

    Hashtable<String, FtpConnectInfo> getFtpConnectInfoTable() {
        if (ftpConnectInfoTable == null) {
            FtpConnectInfo info = new FtpConnectInfo(this);
            ftpConnectInfoTable = info.readFtpConnectInfo();
            if (ftpConnectInfoTable == null)
                ftpConnectInfoTable = new Hashtable();
        }
        return ftpConnectInfoTable;
    }
}
