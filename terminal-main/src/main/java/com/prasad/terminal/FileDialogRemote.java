
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator


package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

/**
 * Dialog to show a directory listing and allow the user to select
 * a file.
 **/
class FileDialogRemote extends Dialog implements ItemListener, ActionListener {
    private PalTerm palTerm;
    Label dirLabel;
    java.awt.List fileList;
    TextField nameField;
    Button okButton,
        cancelButton;
    Label messageLabel;
    URL dirlistURL;
    boolean debug;

    /**
     * Create a new File dialog
     *
     * @param parent the parent frame
     * @param modal  whether this dialog should be modal
     * @param url    the URL of the dirlist CGI program to use to
     *               retrieve directory listings from the server
     **/
    public FileDialogRemote(PalTerm palTerm, Frame parent, boolean modal, URL url) {
        super(parent, modal);
        this.palTerm = palTerm;
        dirlistURL = url;

        setLayout(new GridBagLayout());
        GridBagConstraints constr = new GridBagConstraints();
        constr.gridwidth = 1;
        constr.gridheight = 1;
        constr.weighty = 0;
        constr.weighty = 0;
        constr.insets = new Insets(4, 4, 4, 4);

        dirLabel = new Label("");
        dirLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        constr.gridx = 0;
        constr.gridy = 0;
        constr.fill = GridBagConstraints.HORIZONTAL;
        add(dirLabel, constr);

        fileList = new java.awt.List();
        fileList.setMultipleMode(false);
        fileList.setBackground(Color.white);
        fileList.addItemListener(this);
        constr.gridy++;
        constr.fill = GridBagConstraints.BOTH;
        constr.weightx = 1;
        constr.weighty = 1;
        add(fileList, constr);

        nameField = new TextField();
        nameField.setBackground(Color.white);
        constr.gridy++;
        constr.weightx = 0;
        constr.weighty = 0;
        constr.fill = GridBagConstraints.HORIZONTAL;
        add(nameField, constr);

        Panel panel = new Panel();
        panel.setLayout(new GridLayout(2, 2, 10, 0));
        okButton = new Button("OK");
        okButton.addActionListener(this);
        //okButton.setFont(buttonFont);
        panel.add(okButton);

        cancelButton = new Button("Cancel");
        cancelButton.addActionListener(this);
        //cancelButton.setFont(buttonFont);
        panel.add(cancelButton);

        messageLabel = new Label("                              ");
        messageLabel.setForeground(Color.red);
        panel.add(messageLabel);

        constr.gridy++;
        constr.fill = GridBagConstraints.BOTH;
        add(panel, constr);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        pack();
    }

    /**
     * Return the selected file name
     **/
    public String getFileName() {
        return nameField.getText();
    }

    /**
     * Set the file name shown in the dialog
     *
     * @param name the file name
     **/
    public void setFileName(String name) {
        nameField.setText(name);
        if (fileList.getSelectedIndex() >= 0)
            fileList.deselect(fileList.getSelectedIndex());

        // Highlight matching name in list box, if any
        for (int i = 0; i < fileList.getItemCount(); i++) {
            if (fileList.getItem(i).equals(name)) {
                fileList.select(i);
                break;
            }
        }
    }

    /**
     * Check if a file is in the select list or not.
     *
     * @param file the file name to look up
     * @return true if the file is in the list
     **/
    public boolean fileInList(String file) {
        for (int i = 0; i < fileList.getItemCount(); i++) {
            if (fileList.getItem(i).equals(file))
                return true;
        }
        return false;
    }

    public void setMessage(String msg) {
        messageLabel.setText(msg);
    }
    /**
     * Read a directory on the web server and display the
     * contents in the file list box.
     * @param dir the directory path to list
     * @param filter the filter to use on file names. Only files
     *        matching this pattern will be displayed.
     * @return true if the directory read was successful, false otherwise
     **/
    /**
     * Read a directory on the web server and display the
     * contents in the file list box.
     *
     * @param dir    the directory path to list
     * @param filter the filter to use on file names. Only files
     *               matching this pattern will be displayed.
     * @return true if the directory read was successful, false otherwise
     **/
   /*
		public boolean readDirectory(String dir, String filter) {
			if (dir == null || dirlistURL == null)
				return false;

			// Set label at top of dialog
			dirLabel.setText("Directory: "+dir);

			// Remove current file list
			fileList.removeAll();
			nameField.setText("");

			// Build CGI parameter string
			StringBuffer path = new StringBuffer(dirlistURL.toString());
			path.append("?dir=");
			path.append(URLEncoder.encode(dir));
			if (filter != null) {
				path.append("&filter=");
				path.append(URLEncoder.encode(filter));
			}
			URL cgi_url = null;
			try {
				cgi_url = new URL(path.toString());
			} catch (MalformedURLException e) {
				System.out.println("Invalid dirlist URL: "+path);
				return false;
			}

			// Remove all files currently in list
			fileList.removeAll();

			// Read directory list from URL and display it in listbox
			BufferedReader in = null;
			try {
				String file;
				in = new BufferedReader(new InputStreamReader(cgi_url.openStream()));
				while ((file = in.readLine()) != null) {
					fileList.addItem(file);
				}
				in.close();
			} catch (IOException e) {
				System.out.println("Error reading from URL: "+cgi_url);
				return false;
			}
			return true;
		}
   */
    public boolean readDirectory(String dir, String filter) {
        //PalTerm		palTerm	= (PalTerm)applet;

        boolean retVal = false;

        if (dir == null || dirlistURL == null)
            return retVal;
        URL cgi_url = dirlistURL;

        // Set label at top of dialog
        dirLabel.setText("Directory: " + dir);

        // Remove current file list
        fileList.removeAll();
        nameField.setText("");

        // Create parameters for "dirlist" program
        StringBuffer encodedString = new StringBuffer();
        encodedString.append("dir=");
        encodedString.append(URLEncoder.encode(dir));
        if (filter != null) {
            encodedString.append("&filter=");
            encodedString.append(URLEncoder.encode(filter));
        }

        // Remove all files currently in list
        fileList.removeAll();

        // Read directory list from URL and display it in listbox

        ReadDirectoryProcessLine lineHandler = new ReadDirectoryProcessLine(messageLabel, fileList);
        lineHandler.setDebug(getDebug());
        UserInfo.callCGI(palTerm, null, palTerm.pgm_secdirlist, encodedString.toString(), lineHandler);
        retVal = true;

        return retVal;
    }

    /**
     * Handle item events from file listbox
     **/
    public void itemStateChanged(ItemEvent event) {
        try {
            if (event.getStateChange() == ItemEvent.SELECTED
                && fileList.getSelectedIndex() >= 0)
                nameField.setText(fileList.getSelectedItem());
        } catch (ArrayIndexOutOfBoundsException e) {
            // Catch out of bounds exceptions generated by IE 4.0
            // when removing all items
        }
    }

    /**
     * Handle action events from buttons
     **/
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("Cancel"))
            nameField.setText("");
        setVisible(false);
    }

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean flag) {
        debug = flag;
    }

    /**
     * Read the list files/directories in a directory
     **/
    class ReadDirectoryProcessLine implements HTTPProcessLineInterface {
        Label statusLabel;
        java.awt.List fileList;
        boolean debug;

        public ReadDirectoryProcessLine(Label statusLabel, java.awt.List fileList) {
            this.statusLabel = statusLabel;
            this.fileList = fileList;
        }

        public void processLine(String line) {
            if (line.startsWith("ERROR:"))
                statusLabel.setText(line.substring(6));
            else
                fileList.addItem(line);
        }

        public boolean getDebug() {
            return debug;
        }

        public void setDebug(boolean flag) {
            debug = flag;
        }

    } // ReadDirectoryProcessLine class
} // FileDialogRemote Class

