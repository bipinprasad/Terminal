package com.prasad.util;

/**
 * Image Getter Base class
 * <p>
 * Title:        Service Request Tracking
 * Description:
 * Copyright:    Copyright (c) Bipin Prasad
 * Company:      Chase
 *
 * @author Bipin Prasad
 * @version 1.0
 */

import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.util.*;

public class ImageGetterBase {
    Hashtable imgsHash;
    Hashtable imgIconsHash;

    private boolean cachingImages = true;   // if set, then the images retrieved are saved in the hashtable
    // private boolean cachingImageIcons = true;    // if set, then the icons retrieved are saved in the hashtable

    public boolean isCachingImages() {
        return cachingImages;
    }

    public void setCachingImages(boolean flag) {
        cachingImages = flag;
    }
    //public boolean isCachingImageIcons(){ return cachingImageIcons;}
    //public void setCachingImageIcons(boolean flag){ cachingImageIcons = flag;}

    protected Image getCachedImage(String imageRelPath) // image example "images/abc"
    {
        imageRelPath = imageRelPath.replace('/', File.separatorChar);
        if (imgsHash != null)
            return (Image) imgsHash.get(imageRelPath);
        else
            return null;
    }

    protected void setCachedImage(String imageRelPath, Image img) // image example "images/abc"
    {
        if (imgsHash == null)
            imgsHash = new Hashtable();
        setCachedObject(imgsHash, imageRelPath, img);
    }

    /*
    protected ImageLoaderIcon getCachedImageIcon(String imageIconRelPath) // image example "images/abc"
    {
      imageIconRelPath = imageIconRelPath.replace('/', File.separatorChar);
      if (imgIconsHash != null)
          return (ImageLoaderIcon)imgIconsHash.get(imageIconRelPath);
      else
          return null;
    }

    protected void setCachedImageIcon(String imageIconRelPath, ImageLoaderIcon imgIcon) // image example "images/abc"
    {
      if (imgIconsHash == null)
          imgIconsHash = new Hashtable();
      setCachedObject(imgIconsHash, imageIconRelPath, imgIcon);
    }

    */
    private void setCachedObject(Hashtable hash, String relPath, Object obj) {
        if (hash == null
            || relPath == null)
            return;

        relPath = relPath.replace('/', File.separatorChar);

        if (obj != null)
            hash.put(relPath, obj);
        else {
            obj = hash.get(relPath);
            if (obj != null)
                hash.remove(obj);
        }
    }
}