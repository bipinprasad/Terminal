

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

/**
 * Dialog to show a directory listing and allow the user to select
 * a file.
 **/
class UrlFileDialog extends Dialog implements ItemListener, ActionListener {
    Label dirLabel;
    java.awt.List fileList;
    TextField nameField;
    Button okButton,
        cancelButton;
    Label messageLabel;
    URL dirlistURL;
    boolean debug;

    Frame parentFrame;

    /**
     * Create a new File dialog
     *
     * @param parent the parent frame
     * @param modal  whether this dialog should be modal
     * @param url    the URL of the dirlist CGI program to use to
     *               retrieve directory listings from the server
     **/
    public UrlFileDialog(Frame parent, boolean modal, URL url) {
        super(parent, modal);
        parentFrame = parent;
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
            @Override
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
        if (dir == null || dirlistURL == null)
            return false;
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

        ReadDirectoryProcessLine lineHandler = new ReadDirectoryProcessLine(messageLabel, fileList);
        lineHandler.setDebug(getDebug());

        // Read directory list from URL and display it in listbox
        callCGI(cgi_url, encodedString.toString(), lineHandler);

        return true;
    }

    /**
     * Handle item events from file listbox
     **/
    @Override
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
    @Override
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

    void callCGI(URL cgi_url, String encodedString, ProcessLineInterface lineHandler) {
        boolean hasOutData = (encodedString != null && encodedString.length() > 0);
        BufferedReader in = null;
        PrintWriter out = null;


        URLConnection connection;
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            connection = cgi_url.openConnection();
            connection.setDoOutput(hasOutData);     // use POST method to pass data
            connection.setDoInput(true);      // process incoming CGI stream
            connection.setUseCaches(false);   // disable caching of documents
            connection.setAllowUserInteraction(true); // Allow user interaction

            String authCookie = UserInfo.getAuthCookie();
            if (authCookie != null)
                connection.setRequestProperty("Authorization", authCookie);
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-length", encodedString.length() + "");
            if (debug)
                System.out.println("CGI-REQUEST:" + encodedString);
            if (hasOutData) {
                out = new PrintWriter(connection.getOutputStream());
                out.print(encodedString);
                out.flush();
                out.close();
                out = null;
            }
            String line;
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = in.readLine()) != null) {
                lineHandler.processLine(line);
            }
            in.close();
            in = null;
        } catch (MalformedURLException e) {
            showMessage(parentFrame, "Invalid URL[" + cgi_url + "] for CGI program", true);
        } catch (IOException e) {
            return;
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e) {
            }
            setCursor(Cursor.getDefaultCursor());
        }
        return;
    }

    /**
     * Show status messages in a dialog box
     *
     * @param message the status message to show
     * @param error   set this flag to true if the message is an error
     **/
    void showMessage(Frame frame, String message, boolean error) {
        final Dialog messageDialog = new Dialog(frame, true);
        messageDialog.setLayout(new BorderLayout());
        Label statusMessage = new Label("", Label.CENTER);
        statusMessage.setFont(new Font("Serif", Font.PLAIN, 14));
        messageDialog.add("North", statusMessage);
        Panel panel = new Panel();
        Button button = new Button("Continue");
        button.addActionListener(e -> messageDialog.setVisible(false));
        panel.add(button);
        messageDialog.add("South", panel);

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
        messageDialog.dispose();
    }

    /**
     * Read the list files/directories in a directory
     **/
    class ReadDirectoryProcessLine implements ProcessLineInterface {
        Label statusLabel;
        java.awt.List fileList;
        boolean debug;

        public ReadDirectoryProcessLine(Label statusLabel, java.awt.List fileList) {
            this.statusLabel = statusLabel;
            this.fileList = fileList;
        }

        @Override
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

    }

    /**
     * Interface to process lines received from a CGI call
     */
    interface ProcessLineInterface {
        void processLine(String line);
    }
}
