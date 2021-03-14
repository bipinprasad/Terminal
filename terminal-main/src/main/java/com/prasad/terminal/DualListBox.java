// This snippet creates a panel containing two list boxes, and buttons to
// copy elements from one list to the other.

//Title:
//Version:
//Copyright:
//Author:
//Company:
//Description:
//

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.reflect.*;

import pvTreeJ.*;
import com.prasad.util.GridBagConstraints2;

public class DualListBox extends Panel {
    public static final String FAKENODE_STR = "FakeNodeStr";

    private Applet applet;
    private boolean debug;

    private boolean designMode;
    Label lblSrcList = new Label();
    pvTreeJ.PVTree source = new pvTreeJ.PVTree();
    Label lblDestList = new Label();
    pvTreeJ.PVTree destination = new pvTreeJ.PVTree();
    Button addOne = new Button();
    Button addAll = new Button();
    Button removeOne = new Button();
    Button removeAll = new Button();
    Button ok = new Button();
    Button cancel = new Button();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    ShowLapmFormInterface showLapmFormInterface;

    public DualListBox() {
        designMode = true;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DualListBox(ShowLapmFormInterface showFormInterface) {
        this();
        setShowLapmFormInterface(showFormInterface);
    }

    private void jbInit() throws Exception {
        lblSrcList.setText("Source Forms List");
        source.setNodeEditing(false);
        source.setImages(false);
        source.addPVTreeActionListener(new DualListBox_source_PVTreeActionAdapter(this));
        lblDestList.setText("Display Forms List");
        destination.setNodeEditing(false);
        destination.setImages(false);
        destination.addPVTreeActionListener(new DualListBox_destination_PVTreeActionAdapter(this));
        addOne.setLabel(">");
        addOne.addActionListener(new DualListBox_addOne_actionAdapter(this));
        addAll.setLabel(">>");
        addAll.addActionListener(new DualListBox_addAll_actionAdapter(this));
        removeOne.setLabel("<");
        removeOne.addActionListener(new DualListBox_removeOne_actionAdapter(this));
        removeAll.setLabel("<<");
        removeAll.addActionListener(new DualListBox_removeAll_actionAdapter(this));
        ok.setLabel("OK");
        ok.addActionListener(new DualListBox_ok_actionAdapter(this));
        cancel.setLabel("Cancel");
        cancel.addActionListener(new DualListBox_cancel_actionAdapter(this));
        this.setLayout(gridBagLayout1);
        this.add(lblSrcList, new GridBagConstraints2(0, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(4, 12, 0, 0), 25, -9));
        this.add(source, new GridBagConstraints2(0, 1, 1, 5, 1.0, 1.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 12, 0, 0), 0, 0));
        this.add(lblDestList, new GridBagConstraints2(2, 0, 2, 1, 0.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(4, 16, 0, 12), 23, -9));
        this.add(destination, new GridBagConstraints2(2, 1, 2, 5, 1.0, 1.0
            , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 16, 0, 12), 0, 0));
        this.add(addOne, new GridBagConstraints2(1, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(17, 14, 0, 0), 0, 0));
        this.add(addAll, new GridBagConstraints2(1, 2, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 14, 0, 0), 0, 0));
        this.add(removeOne, new GridBagConstraints2(1, 3, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(48, 14, 0, 0), 0, 0));
        this.add(removeAll, new GridBagConstraints2(1, 4, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(9, 14, 0, 0), 0, 0));
        this.add(ok, new GridBagConstraints2(2, 6, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(9, 0, 3, 0), 41, -2));
        this.add(cancel, new GridBagConstraints2(3, 6, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(9, 13, 3, 11), 19, -2));

        Color c = new Color(255, 222, 189);

        setBackground(c);
        source.setBackground(c);
        destination.setBackground(c);

    }

    String[] getProdLines() {
        // make a cgi call and return all the productlines
        String cgiProgram = "dme";
        String cgiString = "prod=gen&file=prodline&field=productline&out=text&max=600";
        CgiProdLineProcessor cgiProdLineProcessor = new CgiProdLineProcessor();
        if (UserInfo.callCGI(applet, null, cgiProgram, cgiString, cgiProdLineProcessor))
            return cgiProdLineProcessor.getStrings();
        else
            return null;
    }

    String[] getSysCodes(String prodline) {
        // make a cgi call and return all the system codes
        if (prodline == null)
            return null;

        String cgiProgram = "dme";
        String cgiString = "OUT=csv&prod=gen&file=system&field=systemcode;sysname&key=" +
            prodline + "&noheader";
        CgiSysCodeProcessor cgiSysCodeProcessor = new CgiSysCodeProcessor();
        if (UserInfo.callCGI(applet, null, cgiProgram, cgiString, cgiSysCodeProcessor))
            return cgiSysCodeProcessor.getStrings();
        else
            return null;

    }

    String[] getTokens(String prodline, String syscode) {
        if (prodline == null
            || syscode == null)
            return null;

        int ndx = syscode.indexOf(' ');
        if (ndx > 0)
            syscode = syscode.substring(0, ndx);
        // make a cgi call and return all the tokens
        String cgiProgram = "gettokens";
        String cgiString = prodline + "&" + syscode + "&out=text&all";
        CgiTokenProcessor cgiTokenProcessor = new CgiTokenProcessor();
        if (UserInfo.callCGI(applet, null, cgiProgram, cgiString, cgiTokenProcessor))
            return cgiTokenProcessor.getStrings();
        else
            return null;
    }

    void addOne_actionPerformed(ActionEvent e) {
        PVNode destRoot;
        PVNode srcProdNode;
        PVNode srcSysNode;
        PVNode destProdNode;
        PVNode destSysNode;
        PVNode destTokenNode;
        String prodLine;
        String sysCode;
        String token;

        PVNode srcSelected = source.getSelectedNode();

        if (srcSelected != null) {
            destRoot = destination.getRootNode();
            int level = srcSelected.getLevel();
            switch (level) {
                case 1: //prodLine
                    prodLine = srcSelected.getText();
                    // if prodLine exists in the destination, then remove and re-add
                    destProdNode = destination.searchNodes(destRoot, prodLine, null, null);
                    if (destProdNode != null)
                        destination.removeNode(destProdNode);
                    destination.addChildNode(destRoot, prodLine, 0, 1);
                    if (!destRoot.getExpanded())
                        destRoot.setExpanded(true);
                    break;

                case 2: // system code
                    srcProdNode = srcSelected.getParent();
                    prodLine = srcProdNode.getText();
                    sysCode = srcSelected.getText();
                    // make sure that the product line exists in the tree
                    destProdNode = destination.searchNodes(destRoot, prodLine, null, null);
                    if (destProdNode == null)
                        destProdNode = destination.addChildNode(destRoot, prodLine, 0, 1);
                    // if the syscode exists in destination then remove and re-add
                    destSysNode = destination.searchNodes(destProdNode, sysCode, null, null);
                    if (destSysNode != null)
                        destination.removeNode(destSysNode);
                    destination.addChildNode(destProdNode, sysCode, 0, 1);
                    if (!destRoot.getExpanded())
                        destRoot.setExpanded(true);
                    if (!destProdNode.getExpanded())
                        destProdNode.setExpanded(true);
                    break;

                case 3: // token
                    srcSysNode = srcSelected.getParent();
                    srcProdNode = srcSysNode.getParent();
                    prodLine = srcProdNode.getText();
                    sysCode = srcSysNode.getText();
                    token = srcSelected.getText();
                    // make sure that the product line exists in the tree
                    destProdNode = destination.searchNodes(destRoot, prodLine, null, null);
                    if (destProdNode == null)
                        destProdNode = destination.addChildNode(destRoot, prodLine, 0, 1);
                    // make sure that the sys code exists in the tree
                    destSysNode = destination.searchNodes(destProdNode, sysCode, null, null);
                    if (destSysNode == null)
                        destSysNode = destination.addChildNode(destProdNode, sysCode, 0, 1);
                    // if the token exists in destination then do nothing, else re-add
                    destTokenNode = destination.searchNodes(destSysNode, token, null, null);
                    if (destTokenNode == null)
                        destination.addChildNode(destSysNode, token, 0, 1);
                    if (!destRoot.getExpanded())
                        destRoot.setExpanded(true);
                    if (!destProdNode.getExpanded())
                        destProdNode.setExpanded(true);
                    if (!destSysNode.getExpanded())
                        destSysNode.setExpanded(true);
                    break;

                default:
                    System.out.println("Error: Node found at level [" + level + "]");
            }
        }
    }

    void addAll_actionPerformed(ActionEvent e) {
        PVNode destRoot = destination.getRootNode();
        PVNode srcRoot = source.getRootNode();
        PVNode destProdNode;
        PVNode srcProdNode;

        // remove all destination product line nodes
        removeAll_actionPerformed(null);
        // add all the product lines from the source to the destination
        srcProdNode = source.getChildNode(srcRoot);
        while (srcProdNode != null) {
            String prodLine = srcProdNode.getText();
            destination.addChildNode(destRoot, prodLine, 0, 1);
            srcProdNode = srcProdNode.getNextSibling();
        }
        if (!destRoot.getExpanded())
            destRoot.setExpanded(true);
    }

    void removeOne_actionPerformed(ActionEvent e) {
        PVNode destSelected = destination.getSelectedNode();

        if (destSelected != null
            && destSelected != destination.getRootNode())
            destination.removeNode(destSelected);
    }

    void removeAll_actionPerformed(ActionEvent e) {
        PVNode destRoot = destination.getRootNode();
        PVNode destProdNode = destination.getChildNode(destRoot);
        while (destProdNode != null) {
            PVNode nextProdNode = destProdNode.getNextSibling();
            destination.removeNode(destProdNode);
            destProdNode = nextProdNode;
        }
    }

    void source_actionPerformed(PVTreeActionEvent e) {
        int id = e.getID();
        PVNode n = e.getNode();
        ;
        PVNode n2;
        //PVNode  parentPVNode = n.getParent();

        switch (id) {
            case PVTreeActionEvent.NODE_EXPANDING: // Node is about to be expanded
                if (n.HasChildren()) {
                    n2 = n.getChild();
                    if (n2.getText().equals(FAKENODE_STR))
                        doSourceExpansion(n);
                    else
                        source.setSelectedNode(n);
                }
                break;

            case PVTreeActionEvent.NODE_DOUBLE_CLICKED:
                addOne_actionPerformed(null);
                break;
            default:
        }

    }


    void ok_actionPerformed(ActionEvent e) {
        if (designMode) {
            // save the destination tree
            // create a string with one line for each node. The indentation level
            //	determines the tree node location. Tab is used for indentation.
            StringBuffer sb = new StringBuffer("");
            PVNode destRoot = destination.getRootNode();
            addNodeString(destRoot, sb);
            if (debug)
                System.out.println(sb.toString());
            saveDestinationTree(sb);
        }

        setDesignMode(!designMode);
    }

    private void addNodeString(PVNode pvNode, StringBuffer s) {
        PVNode child = pvNode.getChild();
        for (; child != null; child = child.getNextSibling()) {
            if (child.getText().equals(FAKENODE_STR))
                continue;
            int level = child.getLevel();
            for (int i = 0; i < level; i++) {
                s.append("\t");
            }
            s.append(child.getText());
            s.append("\n");
            if (child.HasChildren())
                addNodeString(child, s);
        }
    }

    /**
     * save the destination tree into the file it was read from. If the options
     * were not read from a file, then save it into default filename
     */

    public void saveDestinationTree(StringBuffer params) {
        PalTerm palTerm = (PalTerm) applet;
        if (palTerm.useLocalLaTree)
            saveDestinationTreeLocal(params);
        else
            saveDestinationTreeRemote(params);
    }

    private void saveDestinationTreeLocal(StringBuffer params) {
        PalTerm palTerm = (PalTerm) applet;

        String fileName = "default";

        String u = UserInfo.getUserId();

        boolean success = false;
        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
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
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                //if (debug)
                //    System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
            } catch (Throwable e) {
                e.printStackTrace();
                success = false;
            }
        }

        String fileDir = palTerm.localDirectory
            + "trees"
            + ((u != null && u.length() > 0) ? (File.separator + u) : "");
        File f = new File(fileDir);
        f.mkdirs();
        String filePath = fileDir + File.separator + fileName;

        // now save the properties p into the optionFilePath locally
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        File file = new File(filePath);
        if (file != null) {
            try {
                PrintWriter out = new PrintWriter(new FileOutputStream(file));
                out.print(params);
                out.flush();
                out.close();
            } catch (Exception e) {
                System.out.println("Error while writing Lawson Tree Initialization File [" + filePath + "]");
                e.printStackTrace();
            }
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

    }

    /**
     * save the destination tree into the file it was read from. If the options
     * were not read from a file, then save it into default filename
     */

    private void saveDestinationTreeRemote(StringBuffer params) {
        PalTerm palTerm = (PalTerm) applet;

        //String fileName	= optionFileName;
        //if (fileName == null)
        //	fileName = "default";
        String fileName = "default";

        String u = UserInfo.getUserId();
        if (u == null
            || u.length() == 0) {
            System.out.println("DualListBox.saveOptions(): Returning without action. Running as anonymous user.");
            return;    // running as anonymous user
        }

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
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                //if (debug)
                //    System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        String optionFilePath = palTerm.docBaseDir + "trees/" + u + "/" + fileName;

        // now save the properties p into the optionFilePath on server
        String prefsavePath = palTerm.cgiDir + palTerm.pgm_prefsave + palTerm.cgiExtension;
        URL prefsaveURL;
        try {
            prefsaveURL = new URL(palTerm.protocol, palTerm.serverHost,
                palTerm.serverPort, prefsavePath);
        } catch (MalformedURLException e) {
            System.out.println("Invalid prefsave path: " + prefsavePath);
            return;
        }
        URLConnection connection;
        BufferedReader in = null;
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Color c;

            // Create new URL to prefsave program with file name appended
            URL cgi_url = new URL(prefsaveURL.toString() + "?" + optionFilePath);

            connection = cgi_url.openConnection();
            connection.setDoOutput(true);     // use POST method to pass data
            connection.setDoInput(true);      // process incoming CGI stream
            connection.setUseCaches(false);   // disable caching of documents
            connection.setAllowUserInteraction(true); // Allow user interaction

            String authCookie = UserInfo.getAuthCookie();
            if (authCookie != null)
                connection.setRequestProperty("Authorization", authCookie);
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-length", params.length() + "");
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            if (debug)
                System.out.println("CGI-REQUEST:" + params);
            out.print(params);
            out.flush();
            out.close();
            out = null;

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String status = in.readLine();
            if (status.startsWith("ERROR"))
                showMessage(status, true);
        } catch (MalformedURLException e) {
            showMessage("Invalid URL for savepref CGI program", true);
        } catch (IOException e) {
            showMessage("I/O error writing LaTree initialization file to web server", true);
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
            }
            setCursor(Cursor.getDefaultCursor());
        }

    }


    void cancel_actionPerformed(ActionEvent e) {
        Frame frame = getParentFrame();
        if (frame != null)
            frame.setVisible(false);
    }

    void init() {
        String rootTitle = "Product Lines";
        destination.addRootNode(rootTitle, 0, 1);
        PVNode srcRoot = source.addRootNode(rootTitle, 0, 1);

        // add product lines to the root node
        String[] prodLines = getProdLines();
        if (prodLines != null) {
            for (int i = 0; i < prodLines.length; i++) {
                PVNode srcProdNode = source.addChildNode(srcRoot, prodLines[i], 0, 1);
                source.addChildNode(srcProdNode, FAKENODE_STR, 0, 1);
            }
            if (!srcRoot.getExpanded())
                srcRoot.setExpanded(true);
        }

        initDestinationTree();
    }

    void initDestinationTree() {
        PalTerm palTerm = (PalTerm) applet;
        if (palTerm.useLocalLaTree)
            initDestinationTreeLocal();
        else
            initDestinationTreeRemote();
    }


    private void initDestinationTreeLocal() {
        removeAll_actionPerformed(null);

        // read file from local client and add to the destination tree
        PalTerm palTerm = (PalTerm) applet;

        SavedTreeLineProcessor lineProcessor = new SavedTreeLineProcessor(destination);

        String fileName = "default";

        String u = UserInfo.getUserId();

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
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                //if (debug)
                //    System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        String fileDir = palTerm.localDirectory
            + "trees"
            + ((u != null && u.length() > 0) ? (File.separator + u) : "");
        File f = new File(fileDir);
        f.mkdirs();
        String filePath = fileDir + File.separator + fileName;

        // now read the properties p into the optionFilePath locally
        File file = new File(filePath);
        if (file == null) {
            System.out.println("Cannot create a new instance of File(\"" + filePath + "\").");
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BufferedReader in = null;
        try {
            // Read input stream line by line
            FileReader fin = new FileReader(file);
            in = new BufferedReader(fin);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (debug)
                    System.out.println("line = " + inputLine);
                lineProcessor.processLine(inputLine);
            }
        } catch (IOException e) {
            System.out.println("I/O error reading Lawson Tree Initialization File from [" + filePath + "]");
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
            }
        }
        setCursor(Cursor.getDefaultCursor());
    }

    private void initDestinationTreeRemote() {
        removeAll_actionPerformed(null);

        // read file from server and add to the destination tree
        PalTerm palTerm = (PalTerm) applet;

        SavedTreeLineProcessor lineProcessor = new SavedTreeLineProcessor(destination);

        String fileName = "default";

        String u = UserInfo.getUserId();
        if (u == null
            || u.length() == 0) {
            System.out.println("DualListBox.initDestinationTree(): Returning without action. Running as anonymous user.");
            return;    // running as anonymous user
        }

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
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                //if (debug)
                //    System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        String optionFilePath = palTerm.docBaseDir + "trees/" + u + "/" + fileName;
        // now read the properties p into the optionFilePath on server
        URL optionFileUrl = null;
        try {
            optionFileUrl = new URL(palTerm.protocol, palTerm.serverHost,
                palTerm.serverPort, optionFilePath);
        } catch (MalformedURLException e) {
            System.out.println("Invalid optionFileUrl path: " + optionFileUrl);
            return;
        }
        URLConnection connection;
        BufferedReader in = null;
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            connection = optionFileUrl.openConnection();
            connection.setDoOutput(false);     // use POST method to pass data
            connection.setDoInput(true);      // process incoming CGI stream
            connection.setUseCaches(false);   // disable caching of documents
            connection.setAllowUserInteraction(true); // Allow user interaction

            String authCookie = UserInfo.getAuthCookie();
            if (authCookie != null)
                connection.setRequestProperty("Authorization", authCookie);
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-length", "0");
            //PrintWriter out = new PrintWriter(connection.getOutputStream());
            //out.flush();
            //out.close();
            //out = null;

            // Read input stream line by line
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (debug)
                    System.out.println("line = " + inputLine);
                lineProcessor.processLine(inputLine);
            }
        } catch (MalformedURLException e) {
            showMessage("Invalid URL for saved option file", true);
        } catch (IOException e) {
            showMessage("I/O error reading options to web server", true);
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
            }
            setCursor(Cursor.getDefaultCursor());
        }

    }

    void doDestinationExpansion(PVNode n) {
        doTreeExpansion(destination, n);
    }

    void doSourceExpansion(PVNode n) {
        doTreeExpansion(source, n);
    }

    private void doTreeExpansion(PVTree tree, PVNode n) {
        PVNode prodNode;
        PVNode sysNode;
        PVNode tokenNode;
        String prodLine;
        String sysCode;
        String token;

        int level = n.getLevel();

        switch (level) {
            case 1: //prodLine
                prodLine = n.getText();
                String[] sysCodes = getSysCodes(prodLine);
                if (sysCodes != null) {
                    sysNode = tree.getChildNode(n);
                    while (sysNode != null) {
                        PVNode nextNode = sysNode.getNextSibling();
                        tree.removeNode(sysNode);
                        sysNode = nextNode;
                    }
                }
                for (int i = 0; i < sysCodes.length; i++) {
                    PVNode newNode = tree.addChildNode(n, sysCodes[i], 0, 1);
                    tree.addChildNode(newNode, FAKENODE_STR, 0, 1);
                }
                break;

            case 2: // system code
                prodNode = n.getParent();
                prodLine = prodNode.getText();
                sysCode = n.getText();
                String[] tokens = getTokens(prodLine, sysCode);

                if (tokens != null) {
                    tokenNode = tree.getChildNode(n);
                    while (tokenNode != null) {
                        PVNode nextNode = tokenNode.getNextSibling();
                        tree.removeNode(tokenNode);
                        tokenNode = nextNode;
                    }
                }
                for (int i = 0; i < tokens.length; i++) {
                    PVNode newNode = tree.addChildNode(n, tokens[i], 0, 1);
                }
                break;

            case 3: // token
            default:
                System.out.println("Error: Cannot expand node at level [" + level + "]");
        }
    }

    public Applet getApplet() {
        return applet;
    }

    public void setApplet(Applet a) {
        applet = a;
    }

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean b) {
        debug = b;
    }

    /**
     * Show status messages in a dialog box
     *
     * @param message the status message to show
     * @param error   set this flag to true if the message is an error
     **/
    void showMessage(String message, boolean error) {
        createMessageDialog();
        if (message == null)
            message = "";
        statusMessage.setText(message);
        if (error)
            messageDialog.setTitle("Error");
        else
            messageDialog.setTitle("Status");
        messageDialog.pack();

        // Center dialog over applet
        messageDialog.setLocation(getLocation().x +
                getSize().width / 2,
            getLocation().y +
                getSize().height / 2);
        messageDialog.show();
    }

    private Dialog messageDialog;
    private Label statusMessage;

    private void createMessageDialog() {
        if (messageDialog == null) {
            // Create message dialog box
            messageDialog = new Dialog(getParentFrame(), true);
            messageDialog.setLayout(new BorderLayout());
            statusMessage = new Label("", Label.CENTER);
            statusMessage.setFont(new Font("Serif", Font.PLAIN, 14));
            messageDialog.add(statusMessage, BorderLayout.NORTH);
            Panel panel = new Panel();
            Button button = new Button("Continue");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    messageDialog.setVisible(false);
                }
            });
            panel.add(button);
            messageDialog.add(panel, BorderLayout.SOUTH);
        }
    }

    private Frame hiddenParentFrame;

    /**
     * Return the frame of this panel.
     */
    public Frame getParentFrame() {
        Container containerFrame = getParent();
        while (containerFrame != null) {
            if (containerFrame instanceof Frame)
                return (Frame) containerFrame;
            containerFrame = containerFrame.getParent();
        }
        // no frame in the hierarchy
        if (hiddenParentFrame == null)
            hiddenParentFrame = new Frame();
        hiddenParentFrame.setVisible(false);
        return hiddenParentFrame;
    }

    class CgiProdLineProcessor implements HTTPProcessLineInterface {
        Vector v;

        CgiProdLineProcessor() {
            v = new Vector(100);
        }

        @Override
        public void processLine(String inputLine) {
            if (debug)
                System.out.println("CgiProdLineProcessor.processLine:  [" + inputLine + "]");
            if (inputLine.length() > 0) {
                int ind;
                if (inputLine.startsWith("C")) {
                    ind = inputLine.indexOf("=");
                    v.addElement(inputLine.substring(ind + 1));
                }
            }
        }

        String[] getStrings() {
            String[] retVal = null;
            if (v.size() > 0) {
                retVal = new String[v.size()];
                v.copyInto(retVal);
            }
            return retVal;

        }
    }

    class CgiSysCodeProcessor implements HTTPProcessLineInterface {
        Vector v;

        CgiSysCodeProcessor() {
            v = new Vector(100);
        }

        @Override
        public void processLine(String inputLine) {
            if (debug)
                System.out.println("CgiSysCodeProcessor.processLine:  [" + inputLine + "]");
            if (inputLine.length() == 0)
                return;

            StringTokenizer parse = new StringTokenizer(inputLine, ",");

            String code = "";
            int len;

            if (inputLine.startsWith("\"")) {
                code = parse.nextToken();
                if ((len = code.length()) > 2)
                    code = code.substring(1, len - 1);
                else
                    code = "";

                String system = parse.nextToken();
                if ((len = system.length()) > 2)
                    system = system.substring(1, len - 1);
                else
                    system = "";


                v.addElement(code + "  " + system);
            }
        }

        String[] getStrings() {
            String[] retVal = null;
            if (v.size() > 0) {
                retVal = new String[v.size()];
                v.copyInto(retVal);
            }
            return retVal;

        }
    }

    class CgiTokenProcessor implements HTTPProcessLineInterface {
        Vector v;

        CgiTokenProcessor() {
            v = new Vector(100);
        }

        @Override
        public void processLine(String inputLine) {
            if (debug)
                System.out.println("CgiTokenProcessor.processLine:  [" + inputLine + "]");
            if (inputLine.length() > 0) {
                if (inputLine.startsWith("O ")) {
                    v.addElement(inputLine.substring(2));
                }
            }
        }

        String[] getStrings() {
            String[] retVal = null;
            if (v.size() > 0) {
                retVal = new String[v.size()];
                v.copyInto(retVal);
            }
            return retVal;

        }
    }

    class SavedTreeLineProcessor implements HTTPProcessLineInterface {
        String prodLine;
        String sysCode;
        String token;
        PVTree destination;
        PVNode destRoot;

        SavedTreeLineProcessor(PVTree dest) {
            destination = dest;
            destRoot = dest.getRootNode();
        }

        @Override
        public void processLine(String inputLine) {
            PVNode destProdNode;
            PVNode destSysNode;
            PVNode destTokenNode;

            if (debug)
                System.out.println("SavedTreeLineProcessor.processLine:  [" + inputLine + "]");
            if (inputLine.length() > 0
                && inputLine.charAt(0) == '\t') {
                if (inputLine.charAt(1) == '\t') {
                    if (inputLine.charAt(2) == '\t') {
                        // token
                        token = inputLine.substring(3).trim();
                        if (token.length() == 0)
                            return;
                        // make sure that the product line exists in the tree
                        if (prodLine == null
                            || sysCode == null)
                            return;
                        destProdNode = destination.searchNodes(destRoot, prodLine, null, null);
                        if (destProdNode == null)
                            return;
                        // make sure that the sys code exists in the tree
                        destSysNode = destination.searchNodes(destProdNode, sysCode, null, null);
                        if (destSysNode == null)
                            return;
                        // if the token exists in destination then do nothing, else re-add
                        destTokenNode = destination.searchNodes(destSysNode, token, null, null);
                        if (destTokenNode == null) {
                            destination.addChildNode(destSysNode, token, 0, 1);
                            if (!destRoot.getExpanded())
                                destRoot.setExpanded(true);
                            if (!destProdNode.getExpanded())
                                destProdNode.setExpanded(true);
                            if (!destSysNode.getExpanded())
                                destSysNode.setExpanded(true);
                        }
                    } else {
                        // system code
                        sysCode = inputLine.substring(2).trim();
                        if (sysCode.length() == 0)
                            return;
                        // make sure that the product line exists in the tree
                        if (prodLine == null)
                            return;
                        destProdNode = destination.searchNodes(destRoot, prodLine, null, null);
                        if (destProdNode == null)
                            return;
                        // if the syscode exists in destination then remove and re-add
                        destSysNode = destination.searchNodes(destProdNode, sysCode, null, null);
                        if (destSysNode != null)
                            destination.removeNode(destSysNode);
                        destination.addChildNode(destProdNode, sysCode, 0, 1);
                        if (!destRoot.getExpanded())
                            destRoot.setExpanded(true);
                        if (!destProdNode.getExpanded())
                            destProdNode.setExpanded(true);
                    }
                } else {
                    //prodLine
                    prodLine = inputLine.substring(1).trim();
                    if (prodLine.length() == 0)
                        return;
                    // if prodLine exists in the destination, then remove and re-add
                    destProdNode = destination.searchNodes(destRoot, prodLine, null, null);
                    if (destProdNode != null)
                        destination.removeNode(destProdNode);
                    destination.addChildNode(destRoot, prodLine, 0, 1);
                    if (!destRoot.getExpanded())
                        destRoot.setExpanded(true);
                }
            }
        }
    }

    public void setDesignMode(boolean flag) {
        boolean oldDesignMode = designMode;
        designMode = flag;

        // make appropriate components visible
        lblSrcList.setVisible(designMode);
        source.setVisible(designMode);
        if (designMode)
            lblDestList.setText("Display Forms List");
        else
            lblDestList.setText("Forms List");
        addOne.setVisible(designMode);
        addAll.setVisible(designMode);
        removeOne.setVisible(designMode);
        removeAll.setVisible(designMode);
        if (designMode)
            ok.setLabel("Run");
        else
            ok.setLabel("Design");
        cancel.setVisible(designMode);

        // readjust size of trees to 10 so they expand about about the same
        source.setSize(10, 10);
        destination.setSize(10, 10);

        invalidate();
        doLayout();
        // in desigmode, it is ok to have productlines and systemcode
        // nodes without a FAKENODE, but not otherwise.
        initDestinationTree();
        if (!designMode) {
            PVNode destRoot = destination.getRootNode();
            setDestinationFakeNodes(destination, destRoot);
        }
    }

    private void setDestinationFakeNodes(PVTree dest, PVNode pvNode) {
        if (pvNode == null)
            return;

        PVNode child = pvNode.getChild();
        for (; child != null; child = child.getNextSibling()) {
            int level = child.getLevel();
            if (level == 1
                || level == 2) {
                PVNode grandChild;
                // show fake nodes in order to allow node expansion
                if (child.getText().equals(FAKENODE_STR))
                    continue;
                if (child.HasChildren())
                    setDestinationFakeNodes(dest, child);
                else
                    dest.addChildNode(child, FAKENODE_STR, 0, 1);
            }
        }
    }

    void destination_actionPerformed(PVTreeActionEvent e) {
        int id = e.getID();
        PVNode n = e.getNode();
        ;
        PVNode n2;
        //PVNode  parentPVNode = n.getParent();

        switch (id) {
            case PVTreeActionEvent.NODE_EXPANDING: // Node is about to be expanded
                if (n.HasChildren()) {
                    n2 = n.getChild();
                    if (n2.getText().equals(FAKENODE_STR))
                        doDestinationExpansion(n);
                    else
                        destination.setSelectedNode(n);
                }
                break;

            case PVTreeActionEvent.NODE_DOUBLE_CLICKED:
                if (!designMode) {
                    if (n.getLevel() == 3)
                        showForm(n);
                }
                break;
            default:
        }

    }

    /*
    private Frame getContainerFrame()
    {
      Container containerFrame = this.getParent();
      while (containerFrame != null)
      {
          if (containerFrame instanceof Frame)
              break;
          containerFrame = containerFrame.getParent();
      }
      return (Frame)containerFrame;
    }
    */
    void showForm(PVNode tokenNode) {
        if (showLapmFormInterface == null)
            return;

        PVNode sysNode = tokenNode.getParent();
        String token = tokenNode.getText();
        int idx = token.indexOf(' ');
        if (idx > 0)
            token = token.substring(0, idx);

        if (sysNode == null)
            return;
        PVNode prodNode = sysNode.getParent();
        if (prodNode == null)
            return;
        String prodLine = prodNode.getText();

        showLapmFormInterface.showLapmForm(prodLine, token);
    }

    public void setShowLapmFormInterface(ShowLapmFormInterface s) {
        showLapmFormInterface = s;
    }

    public ShowLapmFormInterface getShowLapmFormInterface() {
        return showLapmFormInterface;
    }
}

class DualListBox_addOne_actionAdapter implements ActionListener {
    DualListBox adaptee;

    DualListBox_addOne_actionAdapter(DualListBox adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.addOne_actionPerformed(e);
    }
}

class DualListBox_addAll_actionAdapter implements ActionListener {
    DualListBox adaptee;

    DualListBox_addAll_actionAdapter(DualListBox adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.addAll_actionPerformed(e);
    }
}

class DualListBox_removeOne_actionAdapter implements ActionListener {
    DualListBox adaptee;

    DualListBox_removeOne_actionAdapter(DualListBox adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.removeOne_actionPerformed(e);
    }
}

class DualListBox_removeAll_actionAdapter implements ActionListener {
    DualListBox adaptee;

    DualListBox_removeAll_actionAdapter(DualListBox adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.removeAll_actionPerformed(e);
    }
}

class DualListBox_ok_actionAdapter implements ActionListener {
    DualListBox adaptee;

    DualListBox_ok_actionAdapter(DualListBox adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.ok_actionPerformed(e);
    }
}

class DualListBox_cancel_actionAdapter implements ActionListener {
    DualListBox adaptee;

    DualListBox_cancel_actionAdapter(DualListBox adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.cancel_actionPerformed(e);
    }
}

class DualListBox_source_PVTreeActionAdapter implements pvTreeJ.PVTreeActionListener {
    DualListBox adaptee;


    DualListBox_source_PVTreeActionAdapter(DualListBox adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(PVTreeActionEvent e) {
        adaptee.source_actionPerformed(e);
    }
}

class DualListBox_destination_PVTreeActionAdapter implements pvTreeJ.PVTreeActionListener {
    DualListBox adaptee;


    DualListBox_destination_PVTreeActionAdapter(DualListBox adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(PVTreeActionEvent e) {
        adaptee.destination_actionPerformed(e);
    }
}

