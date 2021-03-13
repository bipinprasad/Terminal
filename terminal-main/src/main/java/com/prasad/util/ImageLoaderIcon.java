package com.prasad.util;

/**
 * Title:        Service Request Tracking
 * Description:
 * Copyright:    Copyright (c) Bipin Prasad
 * Company:      Chase
 *
 * @author Bipin Prasad
 * @version 1.0
 */

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class ImageLoaderIcon implements Icon {
    ImageGetterInterface imageGetter;
    String imageRelativePath;
    Component imageObserver;
    Dimension scaledDimension;

    Image image;
    int height = -1;
    int width = -1;
    MediaTracker tracker;

    private int loadStatus;

    public ImageLoaderIcon(ImageGetterInterface getter, String imgRelativePath, Component component) {
        imageGetter = getter;
        imageRelativePath = imgRelativePath;
        imageObserver = component;
    }

    private Image getImage() {
        if (image == null
            && imageGetter != null
            && imageObserver != null) {
            image = imageGetter.getImage(imageRelativePath);
            if (scaledDimension != null)
                image = image.getScaledInstance((int) scaledDimension.getWidth(), (int) scaledDimension.getHeight(), 0);
            tracker = new MediaTracker(imageObserver);
            loadImage(image);
        }
        return image;
    }

    @Override
    public int getIconHeight() {
        if (scaledDimension != null)
            return (int) scaledDimension.getHeight();
        else if (height >= 0)
            return height;
        else {
            Image img = getImage();

            if (img != null
                && imageObserver != null)
                height = img.getHeight(imageObserver);

            if (height >= 0)
                return height;
            return 0;
        }
    }

    @Override
    public int getIconWidth() {
        if (scaledDimension != null)
            return (int) scaledDimension.getWidth();
        else if (width >= 0)
            return width;
        else {
            Image img = getImage();

            if (img != null
                && imageObserver != null)
                width = img.getWidth(imageObserver);

            if (width >= 0)
                return width;
            return 0;
        }
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Image img = getImage();
        if (img != null
            && c != null) {
            if (imageObserver == null)
                g.drawImage(image, x, y, c);
            else
                g.drawImage(image, x, y, imageObserver);
            //g.setColor(Color.red);
            //g.fill3DRect(x,y,width,height,true);
            //g.drawImage(img, x, y, c.getBackground(), c);
        }
    }

    /**
     * Loads the image, returning only when the image is loaded.
     * @param image the image
     */
    protected void loadImage(Image image) {
        synchronized (tracker) {
            tracker.addImage(image, 0);
            try {
                tracker.waitForID(0, 0);
            } catch (InterruptedException e) {
                System.out.println("INTERRUPTED while loading Image");
            }
            loadStatus = tracker.statusID(0, false);
            tracker.removeImage(image, 0);

            width = image.getWidth(imageObserver);
            height = image.getHeight(imageObserver);
        }
    }


    public ImageObserver getImageObserver() {
        return imageObserver;
    }

    public void setImageObserver(Component observer) {
        if (imageObserver != observer) {
            if (observer != null) {
                // this will force a new image to be created
                height = -1;
                width = -1;
            }
            imageObserver = observer;
        }
    }

    public Dimension getScaledDimension() {
        return scaledDimension;
    }

    public void setScaledDimension(Dimension d) {
        if (d != null)
            scaledDimension = new Dimension(d);
        else
            scaledDimension = null;
    }

}