
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

/**
 * VersionDialog displays the current Java Terminal version information in a
 * separate dialog window. This class should be updated whenever a new version
 * of Java Terminal is released.
 **/

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class VersionDialog extends Dialog {
    // Do not edit the following two lines - they will be
    // updated using updTermVer shell script on the server
    public static final String VERSION = "1.11";
    public static final Date COMPILE_DATE = new Date("Sun Apr 02 17:36:47 CDT 2000");

    Panel panel1 = new Panel();
    Panel panel2 = new Panel();
    Panel panel3 = new Panel();
    GridLayout gridLayout = new GridLayout(3, 1);
    GridLayout gridLayout2 = new GridLayout(2, 1);
    GridLayout gridLayout3 = new GridLayout(3, 1);
    Button okButton = new Button();
    Label lblName = new Label();
    Label lblVersion = new Label();
    Label lblCompileDate = new Label();
    Label lblCopyRight = new Label();
    Panel buttonPanel = new Panel();
    LogoCanvas logoCanvas = new LogoCanvas();

    public VersionDialog(Frame frame, String title, boolean modal) {
        super(frame, title, modal);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
            add(panel1);
            pack();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Create a new version window
     *
     * @param parent the parent frame
     **/
    public VersionDialog(Frame frame) {
        this(frame, "", false);
    }

    public VersionDialog(Frame frame, boolean modal) {
        this(frame, "", modal);
    }

    public VersionDialog(Frame frame, String title) {
        this(frame, title, false);
    }

    void jbInit() throws Exception {
        panel1.setLayout(gridLayout);
        panel2.setLayout(gridLayout2);
        panel3.setLayout(gridLayout3);

        lblName.setText("Prasad Java Terminal");
        lblName.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblName.setAlignment(1);
        panel2.add(lblName);

        lblVersion.setText("VERSION: " + VERSION);
        lblVersion.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblVersion.setAlignment(1);
        panel2.add(lblVersion);

        panel1.add(panel2);

        lblCompileDate.setText("Compile Date: " + COMPILE_DATE);
        lblCompileDate.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblCompileDate.setAlignment(1);
        panel3.add(lblCompileDate);

        lblCopyRight.setText("Copyright 1998-1999 Prasad & Associates Ltd.");
        lblCopyRight.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblCopyRight.setAlignment(1);
        panel3.add(lblCopyRight);

        okButton.setLabel("Close");
        buttonPanel.add(okButton);
        okButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        okButton.addActionListener(new ActionListener() {
            // Handle OK button event. Pops down the dialog box.
            public void actionPerformed(ActionEvent evt) {
                setVisible(false);
            }
        });
        okButton.addKeyListener(new KeyAdapter() {
            // Handle return key event. Pops down the dialog box.
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER)
                    setVisible(false);
            }
        });
        panel3.add(buttonPanel);

        panel1.add(logoCanvas);

        panel1.add(panel3);
        pack();
    }

    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            cancel();
        }
        super.processWindowEvent(e);
    }

    void cancel() {
        dispose();
    }


    /**
     * Return the button foreground color
     **/
    public Color getButtonFgColor() {
        return okButton.getForeground();
    }

    /**
     * Set the button foreground color
     **/
    public void setButtonFgColor(Color c) {
        okButton.setForeground(c);
    }

    /**
     * Return the button background color
     **/
    public Color getButtonBgColor() {
        return okButton.getBackground();
    }

    /**
     * Set the button background color
     **/
    public void setButtonBgColor(Color c) {
        okButton.setBackground(c);
    }

    /**
     * Set the background color of this dialog
     *
     * @param c the background color
     **/
    public void setBackground(Color c) {
        if (c != null) {
            super.setBackground(c);
            buttonPanel.setBackground(c);
            okButton.setBackground(c);
            logoCanvas.setBackground(c);
            panel1.setBackground(c);
            panel2.setBackground(c);
            panel3.setBackground(c);
            lblName.setBackground(c);
            lblVersion.setBackground(c);
            lblCompileDate.setBackground(c);
            lblCopyRight.setBackground(c);
        }
    }

    public void setLogoImageSource(PalTerm laTerm) {
        logoCanvas.setLogoImageSource(laTerm);
    }

    class LogoCanvas extends Canvas {
        Image logoImage;

        public LogoCanvas() {
            super();
        }

        public void setLogoImageSource(PalTerm laTerm) {
            logoImage = laTerm.loadImage("termimgs", "logo1.gif", this, true);
        }

        public void paint(Graphics g) {
            if (logoImage != null) {
                Rectangle rect = getBounds();
                Dimension d = getPreferredSize();
                int x = (rect.width - d.width) / 2;
                int y = (rect.height - d.height) / 2;
                if (x < 0)
                    x = 0;
                if (y < 0)
                    y = 0;
                g.drawImage(logoImage, x, y, this);
            }
        }

        public Dimension getPreferredSize() {
            return getMinimumSize();
        }

        public Dimension getMinimumSize() {
            if (logoImage != null) {
                int width = logoImage.getWidth(this);
                int height = logoImage.getHeight(this);
                return new Dimension(width, height);
            } else
                return new Dimension(10, 10);
        }
    }
}

