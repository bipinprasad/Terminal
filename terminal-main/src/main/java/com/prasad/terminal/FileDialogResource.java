
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator


package com.prasad.terminal;

import com.prasad.util.ClassPathResourceUtils;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;

/**
 * Dialog to show a resource directory listing and allow the user to select
 * a resource file.
 **/
class FileDialogResource extends Dialog implements ItemListener, ActionListener {
    Label dirLabel;
    List fileList;
    TextField nameField;
    Button okButton,
        cancelButton;
    Label messageLabel;
    boolean debug;

    /**
     * Create a new File dialog
     *
     * @param parent the parent frame
     * @param modal  whether this dialog should be modal
     **/
    public FileDialogResource(Frame parent, boolean modal) {
        super(parent, modal);

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

        fileList = new List();
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
     * Read a directory in the resource path and find resource files.
     *
     * @param dir the directory path to list
     * @param filter the filter to use on file names. Only files
     *        matching this pattern will be displayed.
     * @return true if the directory read was successful, false otherwise
     **/
    public boolean readDirectory(String dir, String filter) {
        if (dir == null) {
            return false;
        }

        // Set label at top of dialog
        dirLabel.setText("Resource Directory: " + dir);

        // Remove current file list
        fileList.removeAll();
        nameField.setText("");

        try {
            Collection<String> fileNames = ClassPathResourceUtils.getResourceFilesInPath(dir, true, PalTerm.class);
            fileNames.forEach(x -> fileList.addItem(x));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Cannot obtain resource file list for resource directory " + dir);
            return false;
        }
        return true;
    }

    /**
     * Handle item events from file listbox
     **/
    @Override
    public void itemStateChanged(ItemEvent event) {
        try {
            if (event.getStateChange() == ItemEvent.SELECTED && fileList.getSelectedIndex() >= 0) {
                nameField.setText(fileList.getSelectedItem());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ignoring error when item state changed");
        }
    }

    /**
     * Handle action events from buttons
     **/
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("Cancel")) {
            nameField.setText("");
        }
        setVisible(false);
    }

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean flag) {
        debug = flag;
    }
}

