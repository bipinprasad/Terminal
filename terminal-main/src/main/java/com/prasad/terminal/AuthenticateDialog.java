package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;

/**
 * AuthenticateDialog is a modal dialog box that allows a user to type in
 * a user name and password. It also has an "OK" button to accept the input
 * and a "Cancel" button to cancel the dialog.
 **/
public class AuthenticateDialog extends Dialog
    implements ActionListener, KeyListener {
    //  Panel		buttonPanel;
    Panel center;
    TextField user,
        password;
    //TextBox 	user,
    //			password;
    Button okButton,
        cancelButton;
    Label userLabel,
        passwordLabel,
        message;
    int command;
    com.prasad.terminal.UserInfo tempUserInfo = new com.prasad.terminal.UserInfo();
    public static final int OK = 1, CANCEL = 2;

    /**
     * Create a new authentication dialog box
     *
     * @param parent the parent frame
     **/
    public AuthenticateDialog(Frame parent) {
        this(parent, null);
    }

    /**
     * Create a new authentication dialog box
     *
     * @param parent the parent frame
     * @param title the window title
     **/
    public AuthenticateDialog(Frame parent, String title) {
        super(parent, title, true);
        setLayout(new BorderLayout(5, 5));

        center = new Panel(new GridBagLayout());
        userLabel = new Label("User name:", Label.RIGHT);
        user = new TextField(40);
        passwordLabel = new Label("Password:", Label.RIGHT);
        password = new TextField(20);
        password.setEchoChar('*');
        okButton = new Button("OK");
        okButton.addActionListener(this);
        okButton.addKeyListener(this);
        cancelButton = new Button("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.addKeyListener(this);
        message = new Label();

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;

        c.anchor = GridBagConstraints.EAST;
        c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row
        center.add(userLabel, c);
        c.anchor = GridBagConstraints.WEST;
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        center.add(user, c);

        c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row
        c.anchor = GridBagConstraints.EAST;
        center.add(passwordLabel, c);
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        c.anchor = GridBagConstraints.WEST;
        center.add(password, c);

        c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row
        c.anchor = GridBagConstraints.EAST;
        center.add(okButton, c);
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        c.anchor = GridBagConstraints.WEST;
        center.add(cancelButton, c);

        add(center, BorderLayout.CENTER);
        add(message, BorderLayout.SOUTH);
        //pack();

        // Add handler for window events
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                // Set focus to user field
                user.requestFocus();
            }

            @Override
            public void windowActivated(WindowEvent e) {
                // Set focus to user field
                user.requestFocus();
            }

            @Override
            public void windowClosing(WindowEvent e) {
//                command = CANCEL;
//                setVisible(false);
            }
        });

        int x = parent.getLocation().x + (parent.getSize().width - getSize().width) / 2;
        int y = parent.getLocation().y + (parent.getSize().height - getSize().height) / 2;
        setLocation(x, y);
        setUser(UserInfo.getUserId());
        setPassword(UserInfo.getPassword());
        user.requestFocus();
        pack();

    }

    /**
     * Handle button actions. Set the authenticate command either OK or
     * CANCEL depending on the button pressed.
     **/
    @Override
    public void actionPerformed(ActionEvent evt) {
        okPressed(evt.getSource() != cancelButton);
    }

    /**
     * Handle key events. If user hits return on a button, treat it like
     * a button click.
     **/
    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            if (event.getSource() == cancelButton)
                okPressed(false);
            else if (event.getSource() == okButton)
                okPressed(true);
        }
    }

    private void okPressed(boolean btnOK) {
        if (btnOK) {
            command = OK;
            tempUserInfo.setUserId(getUser());
            tempUserInfo.setPassword(getPassword());
        } else
            command = CANCEL;
        setVisible(false);
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    /**
     * Return the user command. Either OK or CANCEL
     **/
    public int getCommand() {
        return command;
    }

    /**
     * Return the user name
     **/
    public String getUser() {
        return user.getText();
    }

    /**
     * Set the name in the user field
     **/
    public void setUser(String name) {
        user.setText(name);
    }

    /**
     * Set the background color of this component
     **/
    @Override
    public void setBackground(Color c) {
        super.setBackground(c);
        center.setBackground(c);
        userLabel.setBackground(c);
        passwordLabel.setBackground(c);
        message.setBackground(c);
    }

    /**
     * Return the user password
     **/
    public String getPassword() {
        return password.getText();
    }

    /**
     * Set the user password
     **/
    public void setPassword(String p) {
        password.setText(p);
    }

    /**
     * Return the background color for text fields
     **/
    public Color getTextBgColor() {
        return user.getBackground();
    }

    /**
     * Set the background color for text fields
     **/
    public void setTextBgColor(Color c) {
        user.setBackground(c);
        password.setBackground(c);
    }

    /**
     * Return the foreground color text fields
     **/
    public Color getTextFgColor() {
        return user.getForeground();
    }

    /**
     * Set the background color text fields
     **/
    public void setTextFgColor(Color c) {
        user.setForeground(c);
        password.setForeground(c);
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
        cancelButton.setForeground(c);
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
        cancelButton.setBackground(c);
    }

    /**
     * Return the status message
     **/
    public String getMessage() {
        return message.getText();
    }

    /**
     * Return the status message
     *
     * @param msg the text to display at the bottom of the dialog
     **/
    public void setMessage(String msg) {
        message.setText(msg);
        if (msg != null)
            add("South", message);
        else
            remove(message);
        pack();
    }

}
