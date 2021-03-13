package com.prasad.util;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.applet.Applet;

public class TransparentImage extends Component {
    public TransparentImage() {
        setSize(16, 16);
    }

    public void setImageName(String path) throws IOException {
        if (path != null
            && !path.equals("")) {
            imageName = path;
            if (applet != null) {
                image = applet.getImage(applet.getCodeBase(), imageName);
                setupImage(image, imageName);
            }
        } else {
            imageName = null;
            image = null;
        }
    }

    public String getImageName() {
        return imageName;
    }

    public void setImage(Image image) throws IOException {
        setupImage(image, "");
    }

    public Image getImage() {
        return image;
    }

    public void setAlignment(int align) {
        alignment = align;
    }

    public int getAlignment() {
        return alignment;
    }

    protected void setupImage(Image im, String path) throws IOException {
        prepareImage(im, this);
        if ((checkImage(im, this) & ERROR) != 0)
            throw new IOException("Image file [" + path + "] not found");
        imageName = path;
        if (isVisible()) {
            Graphics g = getGraphics();
            if (g != null) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getSize().width, getSize().height);
                g.dispose();
            }
        }
        image = im;
        repaint(100);
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
        repaint(100);
    }

    public boolean isTransparent() {
        return transparent;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
        repaint(100);
    }

    public Color getEdgeColor() {
        return edgeColor;
    }

    public void setDrawEdge(boolean drawEdge) {
        this.drawEdge = drawEdge;
        repaint(100);
    }

    public boolean isDrawEdge() {
        return drawEdge;
    }

    public Dimension getPreferredSize() {
        return image != null ? new Dimension(image.getWidth(this), image.getHeight(this))
            : new Dimension(16, 16);
    }

    public void update(Graphics g) {
        paint(g);
    }

    private transient Image canvas;

    public void paint(Graphics pg) {
        Dimension size = getSize();
        Color old = pg.getColor();
        Graphics g = pg;
        if (!transparent) {
            if (canvas == null
                || canvas.getWidth(null) != size.width
                || canvas.getHeight(null) != size.height)
                canvas = createImage(size.width, size.height);
            g = canvas.getGraphics();
            g.setColor(getBackground());
            g.fillRect(0, 0, size.width, size.height);
        }

        if (image != null) {
            int imageWidth = image.getWidth(null);
            int imageHeight = image.getHeight(null);
            // Horizontal alignment
            int x = 0;
            int width;
            switch (alignment & HORIZONTAL) {
                default:
                case LEFT:
                    width = imageWidth;
                    break;
                case CENTER:
                    x = (size.width - imageWidth) / 2;
                    width = imageWidth;
                    break;
                case RIGHT:
                    x = size.width - imageWidth;
                    width = imageWidth;
                    break;
                case HSTRETCH:
                    width = size.width;
                    break;
            }

            // Vertical alignment
            int y = 0;
            int height;
            switch (alignment & VERTICAL) {
                default:
                case TOP:
                    height = imageHeight;
                    break;
                case MIDDLE:
                    y = (size.height - imageHeight) / 2;
                    height = imageHeight;
                    break;
                case BOTTOM:
                    y = size.height - imageHeight;
                    height = imageHeight;
                    break;
                case VSTRETCH:
                    height = size.height;
            }

            // paint the image first
            try {
                g.drawImage(image, x, y, width, height, this);
            } catch (java.lang.ArithmeticException e) {
            }

            if (!transparent) {
                pg.drawImage(canvas, 0, 0, null);
                g.dispose();
            }
        }

        // Draw the border
        if (drawEdge) {
            g.setColor(edgeColor);
            g.drawRect(0, 0, size.width - 1, size.height - 1);
        }

        g.setColor(old);
    }

    public Applet getApplet() {
        return applet;
    }

    public void setApplet(Applet a) {
        applet = a;
        if (a != null) {
            try {
                if (imageName != null)
                    setImageName(imageName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    protected transient Image image;
    protected String imageName;
    protected Color edgeColor = Color.black;
    protected boolean drawEdge = true;
    protected boolean transparent = true;
    protected int alignment = HSTRETCH | VSTRETCH;
    protected Applet applet;

    public static final int VERTICAL = 0xFF00;
    public static final int TOP = 0x0000;
    public static final int MIDDLE = 0x0100;
    public static final int BOTTOM = 0x0200;
    public static final int VSTRETCH = 0x0300;

    public static final int HORIZONTAL = 0x00FF;
    public static final int LEFT = 0x0000;
    public static final int CENTER = 0x0001;
    public static final int RIGHT = 0x0002;
    public static final int HSTRETCH = 0x0003;

//  public static final int UNDEFINED	;

}