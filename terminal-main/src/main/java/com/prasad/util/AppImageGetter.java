package com.prasad.util;

/**
 * Image Getter for an Application (as opposed to an Applet)
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
import java.io.*;
import java.util.*;

public class AppImageGetter extends ImageGetterBase implements ImageGetterInterface {
    String baseDir;

    public AppImageGetter(String dir) {
        baseDir = dir;
    }

    public Image getImage(String imageRelPath) // image example "images/abc"
    {
        if (imageRelPath == null)
            return null;

        Image img = null;

        if (isCachingImages())
            img = getCachedImage(imageRelPath);

        if (img == null) {
            File f = new File(baseDir, imageRelPath.replace('/', File.separatorChar));
            img = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
            if (isCachingImages())
                setCachedImage(imageRelPath, img);
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

	imgIcon = new ImageLoaderIcon(this, imageIconRelPath, null);
	if (isCachingImageIcons())
		setCachedImageIcon(imageIconRelPath, imgIcon);
	//if (imgIcon == null)
	//{
	//	File f = new File(baseDir, imageIconRelPath.replace('/', File.separatorChar));
	//	imgIcon = new ImageIcon(f.getAbsolutePath());
	//	if (isCachingImageIcons())
	//		setCachedImageIcon(imageIconRelPath, imgIcon);
	//}
	imgIcon.setScaledDimension(scaledDimension);
	return imgIcon;
  }
  */

    public String[] getDirectories(String parentDir) {
        return getDirectories(baseDir, parentDir);
    }

    public static String[] getDirectories(String baseDir, String parentDir) {
        File f = new File(baseDir, parentDir);
        if (f.isDirectory()) {
            String[] retVal = f.list(new FilenameFilter() {
                public boolean accept(File f2, String child) {
                    File f3 = new File(f2, child);
                    return f3.isDirectory();
                }
            });
            if (retVal != null)
                Collections.sort(Arrays.asList(retVal));
            return retVal;
        } else {
            System.out.println("getDirectories(): WARNING: [" + f.getAbsolutePath() + "] is not a directory");
            return null;
        }
    }

    public String[] getFiles(String parentDir) {
        return getFiles(baseDir, parentDir);
    }

    public static String[] getFiles(String baseDir, String parentDir) {
        File f = new File(baseDir, parentDir);
        if (f.isDirectory()) {
            String[] retVal = f.list(new FilenameFilter() {
                public boolean accept(File f2, String child) {
                    File f3 = new File(f2, child);
                    return f3.isFile();
                }
            });
            if (retVal != null)
                Collections.sort(Arrays.asList(retVal));
            return retVal;
        } else {
            System.out.println("getFiles(): WARNING: [" + f.getAbsolutePath() + "] is not a directory");
            return null;
        }
    }
}