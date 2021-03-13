package com.prasad.util;

/**
 * Image Getter for an Applet (as opposed to an Application)
 * <p>
 * Title:        Service Request Tracking
 * Description:
 * Copyright:    Copyright (c) Bipin Prasad
 * Company:      Chase
 *
 * @author Bipin Prasad
 * @version 1.0
 */

import java.awt.*;
import java.net.*;
import javax.swing.JApplet;
import java.util.*;

public class AppletImageGetter extends ImageGetterBase implements ImageGetterInterface {
    JApplet applet;

    public static final String PARAM_IMAGEGETTERSERVLET = "dirservlet";
    public static final String DIRGETTER_JSPPAGE = "dirgetter.jsp";
    public static final String PARAM_DIRFLAG = "dirflag";
    public static final String PARAM_PARENTDIR = "parentdir";

    public AppletImageGetter(JApplet imageApplet) {
        applet = imageApplet;
    }

    @Override
    public Image getImage(String imageRelPath) // image example "images/abc"
    {
        if (imageRelPath == null)
            return null;

        Image img = null;
        if (isCachingImages())
            img = getCachedImage(imageRelPath);

        if (img == null) {
            try {
                img = applet.getImage(new URL(applet.getDocumentBase(), imageRelPath));
                if (isCachingImages())
                    setCachedImage(imageRelPath, img);
            } catch (Exception e) {
                e.printStackTrace();
                img = null;
            }
        }

        return img;
    }
  /*
  public ImageLoaderIcon getImageIcon(String imageIconRelPath, Dimension scaledDimension) // image example "images/abc"
  {
	if (imageIconRelPath == null)
		return null;

	ImageLoaderIcon imgIcon = null;

	if (isCachingImageIcons())
		imgIcon = getCachedImageIcon(imageIconRelPath);

	if (imgIcon == null)
	{
		imgIcon = new ImageLoaderIcon(this, imageIconRelPath, null);
		if (isCachingImageIcons())
			setCachedImageIcon(imageIconRelPath, imgIcon);
		//Image img = getImage(imageIconRelPath);
		//if (img != null)
		//{
		//    imgIcon = new ImageLoaderIcon(img);
		//    if (isCachingImageIcons())
		//        setCachedImageIcon(imageIconRelPath, imgIcon);
		//}
	}
	imgIcon.setScaledDimension(scaledDimension);
	return imgIcon;
  }
  */

    private String servletUrlStr;

    private String getServletLocation() {
        if (servletUrlStr != null)
            return servletUrlStr;
        if (applet == null)
            return null;

        String urlStr = applet.getParameter(PARAM_IMAGEGETTERSERVLET);
        if (urlStr == null) {
            URL doc_base = applet.getDocumentBase();
            if (doc_base != null) {
                // Path relative to current directory
                String dir = doc_base.getFile();
                if (dir.lastIndexOf('/') > 0)
                    dir = dir.substring(0, dir.lastIndexOf('/'));
                else
                    dir = "";
                try {
                    URL url = new URL(doc_base.getProtocol(), doc_base.getHost(), doc_base.getPort(), dir + "/" + DIRGETTER_JSPPAGE);
                    urlStr = url.toString();
                } catch (MalformedURLException e) {
                    urlStr = DIRGETTER_JSPPAGE;
                }
            } else
                urlStr = DIRGETTER_JSPPAGE;
        }
        servletUrlStr = urlStr;
        return servletUrlStr;
    }


    @Override
    public String[] getDirectories(String parentDir) {
        return getChildrenFiles(parentDir, "1");
    }

    @Override
    public String[] getFiles(String parentDir) {
        return getChildrenFiles(parentDir, "0");
    }

    public String[] getChildrenFiles(String parentDir, String dirFlag) {

        AppletImageLineInterface lineInterface = new AppletImageLineInterface();
        HTTP http = new HTTP(null, null, true, true, getServletLocation(), lineInterface);
        //http.setDebug(true);

        http.addParam(PARAM_DIRFLAG, dirFlag);
        http.addParam(PARAM_PARENTDIR, parentDir);

        try {
            http.open();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            return null;
        }

        String[] retVal = lineInterface.getResult();
        return retVal;
    }

    class AppletImageLineInterface implements HTTPProcessLineInterface {
        boolean debug;
        int lineCnt;
        Vector result;
        StringBuffer error;
        boolean hasError;

        @Override
        public boolean getDebug() {
            return debug;
        }

        @Override
        public void setDebug(boolean flag) {
            debug = flag;
        }

        @Override
        public void processLine(String str) {
            if (debug) {
                lineCnt++;
                System.out.println("AppletImageLineInterface:" + lineCnt + ":[" + str + "]");
            }
            if (str == null
                || str.trim().length() == 0
                || str.charAt(0) == '#')
                return;

            if (hasError)
                appendError(str);
            else {
                char ch = str.charAt(0);
                if (ch == 'E')
                    appendError(str);
                else {
                    if (result == null)
                        result = new Vector();
                    result.addElement(str.trim());
                }
            }
        }

        public String[] getResult() {
            if (result == null
                || result.size() == 0)
                return null;
            int iMax = result.size();
            String[] retVal = new String[iMax];
            for (int i = 0; i < iMax; i++)
                retVal[i] = (String) result.elementAt(i);
            return retVal;
        }

        public String getError() {
            return (hasError ? error.toString() : null);
        }

        private void appendError(String s) {
            hasError = true;
            if (error == null)
                error = new StringBuffer();
            error.append(s + "\n");
        }
    }

//    /** This method is invoked and executed on the server in response
//     *  to a user request from an applet or a web page.
//     */
//    public static void processDirRequestJspPage(ServletRequest request, JspWriter out, String baseDir, boolean debug) {
//        String me = "AppletImageGetter.processDirRequestJspPage():";
//        String dirFlag = request.getParameter(PARAM_DIRFLAG);
//        String parentDir = request.getParameter(PARAM_PARENTDIR);
//
//        if (debug) {
//            System.out.println("DEBUG:" + me + "Following parameters were received:");
//            Enumeration enumeration = request.getParameterNames();
//            while (enumeration.hasMoreElements())
//            {
//                String varName = (String) enumeration.nextElement();
//                String value = request.getParameter(varName);
//                System.out.println("DEBUG:" + me + "Param name[" + varName + "] val [" + value + "]");
//            }
//            System.out.println(me + "DEBUG: END OF PARAMS");
//        }
//        if (parentDir == null) {
//            try {
//                out.println("ERROR: PARENT DIRECTORY PARAMETER [" + PARAM_PARENTDIR + "] IS MISSING IN THE REQUEST");
//            } catch (Exception e) {
//                System.out.println("ERROR:" + me + "Error while printing error");
//                e.printStackTrace();
//            }
//            return;
//        }
//
//        boolean showDirs = false;
//
//        if (dirFlag != null
//            && dirFlag.trim().length() > 0
//            && dirFlag.trim().equals("1"))
//            showDirs = true;
//
//        parentDir = parentDir.replace('\\', File.separatorChar).replace('/', File.separatorChar);
//        String[] retVal = null;
//        if (showDirs)
//            retVal = AppImageGetter.getDirectories(baseDir, parentDir);
//        else
//            retVal = AppImageGetter.getFiles(baseDir, parentDir);
//        int iMax = ((retVal != null) ? retVal.length : 0);
//        if (debug) {
//            System.out.println("DEBUG:" + me + "showDirs=" + showDirs + ", basedir[" + baseDir
//                + "], parentDir [" + parentDir + "], result count [" + iMax + "]");
//        }
//        for (int i = 0; i < iMax; i++) {
//            if (retVal[i] != null) {
//                try {
//                    out.println(" " + retVal[i]);
//                } catch (Exception e) {
//                    System.out.println("ERROR:" + me + "Error while file name to output stream");
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}