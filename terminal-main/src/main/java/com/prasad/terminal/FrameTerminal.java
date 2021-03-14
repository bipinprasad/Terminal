
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;


public class FrameTerminal extends Frame implements ConnectListener {
    UserInfo tmpUserInfo = new UserInfo();

    static Vector frameList = new Vector();

    private Applet applet;    // handle to LaTerm. test ((LaTerm)applet).isStandalone to determine if really an application
    PanelTerminal panelTerminal = new PanelTerminal();
    MenuBar mainMenu = new MenuBar();
    Menu menuFile = new Menu();
    Menu menuEdit = new Menu();
    Menu menuView = new Menu();
    Menu menuOptions = new Menu();
    Menu menuWindow = new Menu();
    Menu menuHelp = new Menu();
    MenuItem menuItemOpenInitFile = new MenuItem();
    MenuItem menuItemSaveInitFile = new MenuItem();
    MenuItem menuItemSaveInitFileAs = new MenuItem();
    MenuItem menuItemFileClose = new MenuItem();
    MenuItem menuItemFileExit = new MenuItem();
    MenuItem menuItemEditCopy = new MenuItem();
    MenuItem menuItemEditPaste = new MenuItem();
    CheckboxMenuItem chkbxMenuItemToolbar = new CheckboxMenuItem();
    CheckboxMenuItem chkbxMenuItemStatusBar = new CheckboxMenuItem();
    MenuItem menuItemSetupTermOptions = new MenuItem();
    MenuItem menuItemReadUnixEnv = new MenuItem();
    MenuItem menuItemNewWindow = new MenuItem();
    MenuItem menuItemHelpContents = new MenuItem();
    MenuItem menuItemKeyboardMap = new MenuItem();
    MenuItem menuItemHelpAbout = new MenuItem();
    MenuItem menuItemDisconnect = new MenuItem();
    MenuItem menuItemConnect = new MenuItem();
    //Panel panelTop = new Panel();
    Panel panelCenter = new Panel();
    //ScrollPane		panelCenter					= new ScrollPane();
    //Panel				panelBottom					= new Panel();
    CheckboxMenuItem chkbxMenuItemDebug = new CheckboxMenuItem();


    public FrameTerminal() {
        try {
            jbInit();
            panelCenter.setLayout(new BorderLayout());
            panelCenter.add(panelTerminal, BorderLayout.CENTER);
            //panelCenter.add(panelTerminal);
            panelTerminal.setConnectListener(this);
            //this.addKeyListener(panelTerminal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        frameList.addElement(this);
        updateAllGoToWindowMenus();
        pack();
    }

    @Override
    public void dispose() {
        frameList.removeElement(this);
        if (frameList.size() == 0) {
            if (((PalTerm) this.applet).isStandalone)
                System.exit(0);
        }
        updateAllGoToWindowMenus();
        super.dispose();
    }

    private void jbInit() throws Exception {
        //this.setSize(new Dimension(566, 408));
        menuItemBackgroundImg.setLabel("Background Images");
        menuItemSelectEmulation.setLabel("Select Emulation...");
        menuItemSelectEmulation.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuItemSelectEmulation_actionPerformed(e);
            }
        });
        menuItemBackgroundImg.addActionListener(new FrameTerminal_menuItemBackgroundImg_actionAdapter(this));
        this.setMenuBar(mainMenu);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        mainMenu.setHelpMenu(menuHelp);
        menuFile.setLabel("File");
        menuEdit.setLabel("Edit");
        menuView.setLabel("View");
        menuOptions.setLabel("Options");
        menuWindow.setLabel("Window");
        menuItemOpenInitFile.setLabel("Open Options File...");
        menuItemOpenInitFile.addActionListener(new FrameTerminal_menuItemOpenInitFile_actionAdapter(this));
        menuItemSaveInitFile.setLabel("Save Options File");
        menuItemSaveInitFile.addActionListener(new FrameTerminal_menuItemSaveInitFile_actionAdapter(this));
        menuItemSaveInitFileAs.setLabel("Save Options File As...");
        menuItemSaveInitFileAs.addActionListener(new FrameTerminal_menuItemSaveInitFileAs_actionAdapter(this));
        menuItemFileClose.setLabel("Close");
        menuItemFileExit.setLabel("Exit");
        menuItemEditCopy.setLabel("Copy");
        menuItemEditCopy.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuItemEditCopy_actionPerformed(e);
            }
        });
        menuItemEditPaste.setLabel("Paste");
        menuItemEditPaste.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuItemEditPaste_actionPerformed(e);
            }
        });
        chkbxMenuItemToolbar.setLabel("Toolbar");
        chkbxMenuItemToolbar.setState(true);
        chkbxMenuItemToolbar.addItemListener(new FrameTerminal_chkbxMenuItemToolbar_itemAdapter(this));
        chkbxMenuItemStatusBar.setLabel("Status Bar");
        chkbxMenuItemStatusBar.setState(true);
        chkbxMenuItemStatusBar.addItemListener(new FrameTerminal_chkbxMenuItemStatusBar_itemAdapter(this));
        menuItemSetupTermOptions.setLabel("Terminal Options...");
        menuItemSetupTermOptions.setActionCommand("Terminal Options...");
        menuItemSetupTermOptions.addActionListener(new FrameTerminal_menuItemSetupTermOptions_actionAdapter(this));
        menuItemReadUnixEnv.setLabel("Read Unix Env...");
        menuItemReadUnixEnv.setActionCommand("Read Unix Env...");
        menuItemReadUnixEnv.addActionListener(new FrameTerminal_menuItemReadUnixEnv_actionAdapter(this));
        menuItemNewWindow.setLabel("New Window");
        menuItemNewWindow.addActionListener(new FrameTerminal_menuItemNewWindow_actionAdapter(this));
        menuItemHelpContents.setLabel("Contents");
        menuItemKeyboardMap.setLabel("Keyboard Map");
        menuItemHelpAbout.setLabel("About Java Terminal");
        menuItemHelpAbout.setActionCommand("About Java Terminal");
        menuItemHelpAbout.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuItemHelpAbout_actionPerformed(e);
            }
        });
        menuItemDisconnect.setEnabled(false);
        menuItemDisconnect.setLabel("Disconnect");
        menuItemDisconnect.addActionListener(new FrameTerminal_menuItemDisconnect_actionAdapter(this));
        chkbxMenuItemDebug.setLabel("Debug On Console");
        chkbxMenuItemDebug.addItemListener(new FrameTerminal_chkbxMenuItemDebug_itemAdapter(this));
        menuViewGrapicsChars.setLabel("Graphics Chars");
        menuViewCharAttrs.setLabel("Character Attributes");
        menuItemSetupTextFont.setLabel("Text Font...");
        menuItemSetupTextFont.addActionListener(new FrameTerminal_menuItemSetupTextFont_actionAdapter(this));
        menuItemSetupTextAttrs.setLabel("Text Attributes...");
        menuGoToWindow.setLabel("Goto Window");
        menuItemInstall.setEnabled(false);
        menuItemFTP.setLabel("FTP");
        menuItemSmartMouseOptions.setLabel("Smart Mouse Options...");
        menuItemGraphicsColor.setLabel("Graphics Color...");
        menuItemScreenTree.setLabel("Screen Tree");
        menuItemScreenTree.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuItemScreenTree_actionPerformed(e);
            }
        });
        menuItemGraphicsColor.addActionListener(new FrameTerminal_menuItemGraphicsColor_actionAdapter(this));
        menuItemSmartMouseOptions.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuItemSmartMouseOptions_actionPerformed(e);
            }
        });
        menuItemFTP.addActionListener(new FrameTerminal_menuItemFTP_actionAdapter(this));
        menuItemInstall.setLabel("Install");
        menuItemInstall.addActionListener(new FrameTerminal_menuItemInstall_actionAdapter(this));
        menuItemSetupTextAttrs.addActionListener(new FrameTerminal_menuItemSetupTextAttrs_actionAdapter(this));
        menuViewCharAttrs.addActionListener(new FrameTerminal_menuViewCharAttrs_actionAdapter(this));
        menuViewGrapicsChars.addActionListener(new FrameTerminal_menuViewGrapicsChars_actionAdapter(this));
        menuItemConnect.setLabel("Connect");
        menuItemConnect.addActionListener(new FrameTerminal_menuItemConnect_actionAdapter(this));
        menuHelp.setLabel("Help");
        mainMenu.add(menuFile);
        mainMenu.add(menuEdit);
        mainMenu.add(menuView);
        mainMenu.add(menuOptions);
        mainMenu.add(menuWindow);
        mainMenu.add(menuHelp);
        menuFile.add(menuItemOpenInitFile);
        menuFile.add(menuItemSaveInitFile);
        menuFile.add(menuItemSaveInitFileAs);
        menuFile.addSeparator();
        menuFile.add(menuItemConnect);
        menuFile.add(menuItemDisconnect);
        menuFile.addSeparator();
        menuFile.add(menuItemFileClose);
        menuFile.add(menuItemFileExit);
        menuFile.addSeparator();
        menuEdit.add(menuItemEditCopy);
        menuEdit.add(menuItemEditPaste);
        menuView.add(chkbxMenuItemToolbar);
        menuView.add(chkbxMenuItemStatusBar);
        menuView.add(chkbxMenuItemDebug);
        menuView.add(menuViewGrapicsChars);
        menuView.add(menuViewCharAttrs);
        menuOptions.add(menuItemSelectEmulation);
        menuOptions.add(menuItemSetupTextFont);
        menuOptions.add(menuItemSetupTextAttrs);
        menuOptions.add(menuItemGraphicsColor);
        menuOptions.add(menuItemBackgroundImg);
        menuOptions.add(menuItemSetupTermOptions);
        menuOptions.add(menuItemSmartMouseOptions);
        menuOptions.add(menuItemReadUnixEnv);
        menuWindow.add(menuItemFTP);
        menuWindow.addSeparator();
        menuWindow.add(menuItemScreenTree);
        menuWindow.addSeparator();
        menuWindow.add(menuItemNewWindow);
        menuHelp.add(menuItemInstall);
        menuHelp.add(menuItemHelpContents);
        menuHelp.addSeparator();
        menuHelp.add(menuItemKeyboardMap);
        menuHelp.addSeparator();
        menuHelp.add(menuItemHelpAbout);
        //this.add(panelTop, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        //this.add(panelBottom, BorderLayout.SOUTH);
        menuWindow.addSeparator();
        menuWindow.add(menuGoToWindow);
    }

    void menuItemConnect_actionPerformed(ActionEvent e) {
        DialogHost dlg = new DialogHost(this, "Connect To");
        Point p = getLocation();

        String telnetHost = getTelnetHost();
        if (telnetHost != null)
            dlg.setHost(telnetHost);
        dlg.setLocation(p.x + 50, p.y + 50);
        dlg.show();
        if (dlg.getResult()) {
            telnetHost = dlg.getHost();
            int port = dlg.getPort();
            String id = dlg.getUserId();
            String password = dlg.getPassword();
            if (debug)
                System.out.println("Will connect to host[" + telnetHost + "] using id [" + id + "], password [" + password + "]");
            panelTerminal.connect(telnetHost, port, id, password);
        }
    }

    void menuItemDisconnect_actionPerformed(ActionEvent e) {
        disconnect();
    }

    /**
     * Redirect methods to panelTerminal
     */
    public void disconnect() {
        panelTerminal.disconnect();
    }

    public synchronized void terminalWrite(String str/*, int flags*/) {
        panelTerminal.terminalWrite(str/*, flags*/);
    }

    //public synchronized void setForeColor(int color){panelTerminal.setForeColor(color);}
    //public synchronized void setBackColor(int color){panelTerminal.setBackColor(color);}
    public void connect(String host, int port, String userid, String password) {
        panelTerminal.connect(host, port, userid, password);
    }

    @Override
    public void connected() {
        //TODO: implement this prasad.terminal.ConnectListener method;
        menuItemConnect.enable(false);
        menuItemDisconnect.enable(true);
        updateTitle();
    }

    @Override
    public void disconnected() {
        //TODO: implement this prasad.terminal.ConnectListener method;
        menuItemConnect.enable(true);
        menuItemDisconnect.enable(false);
        updateTitle();
    }

    private void updateTitle() {
        setTitle(panelTerminal.getTitle());
        updateAllGoToWindowMenus();
    }

    public synchronized void terminalInit() {
        panelTerminal.terminalInit();
    }

    public void readDefaultOptions() {
        panelTerminal.readDefaultOptions();
    }

    void setApplet(Applet a) {
        applet = a;
        try {
            PalTerm palTerm = (PalTerm) applet;
            if (!palTerm.useLaTree
                && menuWindow != null
                && menuItemScreenTree != null)
                menuWindow.remove(menuItemScreenTree);
            panelTerminal.setPalTerm(palTerm);
            enableInstall(palTerm.install_enabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
    }

    Applet getApplet() {
        return applet;
    }

    public void writeCopyright() {
        panelTerminal.writeCopyright();
    }

    /**
     * Sets the host prompt that will be used to autodetect command mode
     */
    public void setHostPrompt(String s) {
        panelTerminal.setHostPrompt(s);
    }

    private boolean debug;
    MenuItem menuViewGrapicsChars = new MenuItem();
    MenuItem menuViewCharAttrs = new MenuItem();
    MenuItem menuItemSetupTextFont = new MenuItem();
    MenuItem menuItemSetupTextAttrs = new MenuItem();
    Menu menuGoToWindow = new Menu();
    MenuItem menuItemInstall = new MenuItem();
    MenuItem menuItemFTP = new MenuItem();

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean b) {
        debug = b;
        panelTerminal.setDebug(b);
    }

    void chkbxMenuItemDebug_itemStateChanged(ItemEvent e) {
        setDebug(e.getStateChange() == e.SELECTED);
    }

    void menuViewGrapicsChars_actionPerformed(ActionEvent e) {
        panelTerminal.viewGraphicsChars();
    }

    void menuViewCharAttrs_actionPerformed(ActionEvent e) {
        panelTerminal.viewCharAttrs();
    }

    void menuItemSetupTextAttrs_actionPerformed(ActionEvent e) {
        panelTerminal.setupTextAttrs();
        setOptionsDirty(true);
    }

    void menuItemGraphicsColor_actionPerformed(ActionEvent e) {
        panelTerminal.configureGraphicsCharForeground();
        setOptionsDirty(true);
    }

    void menuItemSetupTextFont_actionPerformed(ActionEvent e) {
        panelTerminal.selectFont(this);
        setOptionsDirty(true);
    }

    void chkbxMenuItemStatusBar_itemStateChanged(ItemEvent e) {
        panelTerminal.showStatusBar(((CheckboxMenuItem) e.getSource()).getState());
    }

    void chkbxMenuItemToolbar_itemStateChanged(ItemEvent e) {
        panelTerminal.showToolBar(((CheckboxMenuItem) e.getSource()).getState());
    }

    void menuItemSetupTermOptions_actionPerformed(ActionEvent e) {
        panelTerminal.showOptions();
        setOptionsDirty(true);
    }

    void menuItemReadUnixEnv_actionPerformed(ActionEvent e) {
        panelTerminal.readUnixEnv();
    }

    void menuItemSaveInitFile_actionPerformed(ActionEvent e) {
        panelTerminal.saveOptions();
        setOptionsDirty(false);
    }

    void menuItemOpenInitFile_actionPerformed(ActionEvent e) {
        panelTerminal.openOptions();
        setOptionsDirty(false);
    }

    void menuItemSaveInitFileAs_actionPerformed(ActionEvent e) {
        panelTerminal.saveOptionsAs();
        setOptionsDirty(false);
    }

    void menuItemNewWindow_actionPerformed(ActionEvent e) {
        FrameTerminal frameTerminal = new FrameTerminal();
        frameTerminal.setApplet(getApplet());
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
        frameTerminal.show();

        frameTerminal.terminalInit();
        frameTerminal.readDefaultOptions();
        frameTerminal.setTelnetHost(getTelnetHost());
        frameTerminal.menuItemConnect_actionPerformed(null);
        frameTerminal.setOffsetLocation(getLocation());

    }

    private void setOffsetLocation(Point d) {
        if (d.x > 400
            || d.y > 400)
            setLocation(25, 25);
        else
            setLocation(d.x + 25, d.y + 25);
    }

    static void updateAllGoToWindowMenus() {
        Enumeration enumeration = frameList.elements();
        if (enumeration != null)
        {
            while (enumeration.hasMoreElements())
            {
                FrameTerminal f = (FrameTerminal) enumeration.nextElement();
                if (f != null)
                    f.updateGoToWindowMenu();
            }
        }
    }

    /**
     * Update the submenu for "GoTo Window"
     */
    private void updateGoToWindowMenu() {
        menuGoToWindow.removeAll();
        Enumeration enumeration = frameList.elements();
        if (enumeration != null)
        {
            while (enumeration.hasMoreElements())
            {
                FrameTerminal f = (FrameTerminal) enumeration.nextElement();
                if (f == this)
                    continue;
                MenuItem tmpMenuItem = new MenuItem(f.getTitle());
                tmpMenuItem.addActionListener(new FrameTerminal_menuItemGoToWindow_actionAdapter(f));
                menuGoToWindow.add(tmpMenuItem);
            }
        }

    }

    public void setOptionsDirty(boolean flag) {
        panelTerminal.setOptionsDirty(flag);
        updateTitle();
    }

    String getTelnetHost() {
        return panelTerminal.getTelnetHost();
    }

    void setTelnetHost(String h) {
        panelTerminal.setTelnetHost(h);
    }

    void menuItemInstall_actionPerformed(ActionEvent e) {
        ((PalTerm) applet).install(getTextBackground(), getTextForeground(), getTextFont());
    }

    public void enableInstall(boolean flag) {
        menuItemInstall.setEnabled(flag);
    }

    Color getTextBackground() {
        return panelTerminal.getTextBackground();
    }

    Color getTextForeground() {
        return panelTerminal.getTextForeground();
    }

    Font getTextFont() {
        return panelTerminal.getFont();
    }

    void menuItemFTP_actionPerformed(ActionEvent e) {
        PalTerm palTerm = (PalTerm) this.applet;
        setCursor(Cursor.WAIT_CURSOR);
        FrameFtpDir frameFtpDir = new FrameFtpDir(palTerm.isNAV, palTerm.isIE);
        frameFtpDir.setApplet(palTerm);
        frameFtpDir.setDebug(getDebug());
        frameFtpDir.setBackground(getTextBackground());
        frameFtpDir.setForeground(getTextForeground());
        frameFtpDir.setFileListFont(getTextFont());
        frameFtpDir.setTerminalSender(panelTerminal);
        frameFtpDir.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    FrameFtpDir f = ((FrameFtpDir) e.getSource());
                    f.cleanup();
                    f.dispose();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });

        frameFtpDir.setShowFileDetails(true);
        frameFtpDir.setShowLocal(true);
        frameFtpDir.setShowRemote(true);
        //
        // set the initial directories
        //
        frameFtpDir.show();
        frameFtpDir.setRemoteHost(getTelnetHost());
        frameFtpDir.connect(true, false);
        setCursor(Cursor.DEFAULT_CURSOR);
    }

    void menuItemEditCopy_actionPerformed(ActionEvent e) {
        panelTerminal.copyToClipboard();
    }

    void menuItemEditPaste_actionPerformed(ActionEvent e) {
        panelTerminal.pasteFromClipboard();
    }

    void menuItemSmartMouseOptions_actionPerformed(ActionEvent e) {
        panelTerminal.configureSmartMouse();
    }

    void menuItemHelpAbout_actionPerformed(ActionEvent e) {
        displayVersion();
    }

    /**
     * Popup a dialog window that shows the version of NED
     **/
    private VersionDialog versionDialog;

    MenuItem menuItemSmartMouseOptions = new MenuItem();
    MenuItem menuItemGraphicsColor = new MenuItem();
    MenuItem menuItemScreenTree = new MenuItem();
    MenuItem menuItemBackgroundImg = new MenuItem();
    MenuItem menuItemSelectEmulation = new MenuItem();

    void displayVersion() {
        if (versionDialog == null) {
            versionDialog = new VersionDialog(this, "About Prasad Java Terminal");
            versionDialog.setLogoImageSource((PalTerm) applet);
            versionDialog.setForeground(getTextForeground());
            versionDialog.setBackground(getTextBackground());
            //versionDialog.setForeground(getForeground());
            //versionDialog.setBackground(getBackground());
            //versionDialog.setButtonFgColor(currentPrefs.buttonFgColor);
            //versionDialog.setButtonBgColor(currentPrefs.buttonBgColor);
        }
        versionDialog.setLocation(getLocation().x + (getSize().width -
                versionDialog.getSize().width) / 2,
            getLocation().y + (getSize().height -
                versionDialog.getSize().height) / 2);
        versionDialog.show();
    }

    void menuItemScreenTree_actionPerformed(ActionEvent e) {
        panelTerminal.showLaTree();
    }

    void menuItemBackgroundImg_actionPerformed(ActionEvent e) {
        panelTerminal.setBackgroundImages();
    }

    void this_windowClosing(WindowEvent e) {
        // if the options has been changed, then ask if it should be saved
        if (panelTerminal.getOptionsDirty()) {
            PromptDialog dlg = new PromptDialog(this, "Save Options ?", 2, null);
            dlg.setTextFieldVisible(0, false);
            dlg.setTextFieldVisible(1, false);
            dlg.setLabel(0, "Options have changed");
            dlg.setLabel(1, "Save Options ?");

            dlg.show();
            if (dlg.getResult().equals(dlg.OK))
                panelTerminal.saveOptions();
        }
    }

    void menuItemSelectEmulation_actionPerformed(ActionEvent e) {
        panelTerminal.selectTerminal();
    }

}

class FrameTerminal_menuItemConnect_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemConnect_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuItemConnect_actionPerformed(e);
    }

}

class FrameTerminal_chkbxMenuItemDebug_itemAdapter implements java.awt.event.ItemListener {
    FrameTerminal adaptee;


    FrameTerminal_chkbxMenuItemDebug_itemAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        adaptee.chkbxMenuItemDebug_itemStateChanged(e);
    }
}

class FrameTerminal_menuItemDisconnect_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemDisconnect_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuItemDisconnect_actionPerformed(e);
    }
}

class FrameTerminal_menuViewGrapicsChars_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuViewGrapicsChars_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuViewGrapicsChars_actionPerformed(e);
    }
}

class FrameTerminal_menuViewCharAttrs_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuViewCharAttrs_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuViewCharAttrs_actionPerformed(e);
    }
}

class FrameTerminal_menuItemSetupTextAttrs_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemSetupTextAttrs_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuItemSetupTextAttrs_actionPerformed(e);
    }
}

class FrameTerminal_menuItemSetupTextFont_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemSetupTextFont_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuItemSetupTextFont_actionPerformed(e);
    }
}

class FrameTerminal_chkbxMenuItemStatusBar_itemAdapter implements java.awt.event.ItemListener {
    FrameTerminal adaptee;


    FrameTerminal_chkbxMenuItemStatusBar_itemAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        adaptee.chkbxMenuItemStatusBar_itemStateChanged(e);
    }
}

class FrameTerminal_chkbxMenuItemToolbar_itemAdapter implements java.awt.event.ItemListener {
    FrameTerminal adaptee;


    FrameTerminal_chkbxMenuItemToolbar_itemAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        adaptee.chkbxMenuItemToolbar_itemStateChanged(e);
    }
}

class FrameTerminal_menuItemSetupTermOptions_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemSetupTermOptions_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuItemSetupTermOptions_actionPerformed(e);
    }
}

class FrameTerminal_menuItemReadUnixEnv_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemReadUnixEnv_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuItemReadUnixEnv_actionPerformed(e);
    }
}

class FrameTerminal_menuItemSaveInitFile_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemSaveInitFile_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuItemSaveInitFile_actionPerformed(e);
    }
}

class FrameTerminal_menuItemOpenInitFile_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemOpenInitFile_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuItemOpenInitFile_actionPerformed(e);
    }
}

class FrameTerminal_menuItemSaveInitFileAs_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemSaveInitFileAs_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuItemSaveInitFileAs_actionPerformed(e);
    }
}

class FrameTerminal_menuItemNewWindow_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemNewWindow_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuItemNewWindow_actionPerformed(e);
    }
}

class FrameTerminal_menuItemGoToWindow_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemGoToWindow_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.requestFocus();
    }
}

class FrameTerminal_menuItemInstall_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemInstall_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuItemInstall_actionPerformed(e);
    }
}

class FrameTerminal_menuItemFTP_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemFTP_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuItemFTP_actionPerformed(e);
    }
}

class FrameTerminal_menuItemGraphicsColor_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemGraphicsColor_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuItemGraphicsColor_actionPerformed(e);
    }
}

class FrameTerminal_menuItemBackgroundImg_actionAdapter implements java.awt.event.ActionListener {
    FrameTerminal adaptee;


    FrameTerminal_menuItemBackgroundImg_actionAdapter(FrameTerminal adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.menuItemBackgroundImg_actionPerformed(e);
    }
}



