package com.prasad.util;

import java.awt.*;
import java.awt.image.*;
import javax.swing.ImageIcon;

/**
 * Title:        Service Request Tracking
 * Description:
 * Copyright:    Copyright (c) Bipin Prasad
 * Company:      Chase
 *
 * @author Bipin Prasad
 * @version 1.0
 */

public interface ImageGetterInterface {
    public boolean isCachingImages();

    public void setCachingImages(boolean flag);
    //public boolean isCachingImageIcons();
    //public void setCachingImageIcons(boolean flag);

    public Image getImage(String imageRelativePath);

    //public ImageLoaderIcon getImageIcon(String imageIconRelativePath, Dimension scaledDimension);
    public String[] getDirectories(String parentDir);

    public String[] getFiles(String parentDir);
}