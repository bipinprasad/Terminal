// This snippet creates a new dialog box
// that prompts for a password

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

import com.prasad.util.GridBagConstraints2;

public class DialogHost extends Dialog {
    private boolean result = false;
    private String host = null;
    private int port = 23;
    private String userId = null;
    private String password = null;

    Panel panel1 = new Panel();
    Panel panel2 = new Panel();
    Panel dialogPanel = new Panel();
    //BevelPanel bevelPanel1 = new BevelPanel();
    Panel bevelPanel1 = new Panel();
    Button btnOk = new Button();
    Button btnCancel = new Button();
    Label labelPassword = new Label();
    TextField txtPassword = new TextField(24);
    BorderLayout borderLayout2 = new BorderLayout();
    GridLayout gridLayout1 = new GridLayout();
    FlowLayout flowLayout1 = new FlowLayout();
    Label labelUserId = new Label();
    TextField txtUserId = new TextField();
    Label labelHost = new Label();
    Label labelPort = new Label();
    TextField txtHost = new TextField();
    TextField txtPort = new TextField();
    GridBagLayout gridBagLayout1 = new GridBagLayout();

    public DialogHost(Frame frame, String title, boolean modal) {
        super(frame, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        add(dialogPanel, BorderLayout.CENTER);
        //pack();
        setSize(new Dimension(427, 144));
        txtHost.requestFocus();
    }

    public DialogHost(Frame frame, String title) {
        this(frame, title, true);
    }

    public DialogHost(Frame frame) {
        this(frame, "", true);
    }

    private void jbInit() {
        dialogPanel.setLayout(borderLayout2);
        bevelPanel1.setLayout(gridBagLayout1);
        btnOk.setLabel("OK");
        btnOk.addActionListener(new DialogHost_btnOk_actionAdapter(this));
        btnCancel.setLabel("Cancel");
        labelPassword.setVisible(false);
        labelPassword.setAlignment(Label.RIGHT);
        labelPassword.setText("Password:");
        txtPassword.setVisible(false);
        txtPassword.setEchoChar('#');
        labelUserId.setVisible(false);
        labelUserId.setAlignment(Label.RIGHT);
        labelUserId.setText("User ID:");
        txtUserId.setVisible(false);
        txtUserId.setColumns(16);
        labelHost.setAlignment(Label.RIGHT);
        labelHost.setText("Host:");
        txtHost.setColumns(16);
        txtPort.setText("23");
        labelPort.setAlignment(Label.RIGHT);
        labelPort.setText("Port:");
        gridLayout1.setVgap(4);
        gridLayout1.setHgap(6);
        btnCancel.addActionListener(new DialogHost_btnCancel_actionAdapter(this));
        this.addWindowListener(new DialogHost_this_windowAdapter(this));
        panel1.add(panel2);
        //bevelPanel1.setMargins(new Insets(4, 4, 4, 4));
        panel2.setLayout(gridLayout1);
        panel1.setLayout(flowLayout1);
        dialogPanel.setSize(new Dimension(427, 144));
        //bevelPanel1.setBevelInner(BevelPanel.RAISED);
        //bevelPanel1.setBevelOuter(BevelPanel.LOWERED);
        dialogPanel.add(panel1, BorderLayout.SOUTH);
        panel2.add(btnOk);
        panel2.add(btnCancel);
        dialogPanel.add(bevelPanel1, BorderLayout.CENTER);
        bevelPanel1.add(labelHost, new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));
        bevelPanel1.add(txtHost, new GridBagConstraints2(1, 0, 1, 1, 1.0, 1.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 181, 0));
        bevelPanel1.add(labelPort, new GridBagConstraints2(0, 1, 1, 1, 1.0, 1.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));
        bevelPanel1.add(txtPort, new GridBagConstraints2(1, 1, 1, 1, 1.0, 1.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 90, 0));
        bevelPanel1.add(labelUserId, new GridBagConstraints2(0, 2, 1, 1, 1.0, 1.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));
        bevelPanel1.add(txtUserId, new GridBagConstraints2(1, 2, 1, 1, 1.0, 1.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 180, 0));
        bevelPanel1.add(labelPassword, new GridBagConstraints2(0, 3, 1, 1, 1.0, 1.0
            , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));
        bevelPanel1.add(txtPassword, new GridBagConstraints2(1, 3, 1, 1, 1.0, 1.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        //pack();
    }

    public boolean getResult() {
        return result;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setHost(String s) {
        host = s;
        txtHost.setText(s);
    }

    public void setUserId(String s) {
        userId = s;
        txtUserId.setText(s);
    }

    public void setPassword(String s) {
        password = s;
        txtPassword.setText(s);
    }

    //OK
    void btnOk_actionPerformed(ActionEvent e) {
        result = true;
        host = txtHost.getText();
        try {
            port = Integer.parseInt(txtPort.getText());
        } catch (Exception e2) {
            port = 23;
        }
        userId = txtUserId.getText();
        password = txtPassword.getText();
        dispose();
    }

    //Cancel
    void btnCancel_actionPerformed(ActionEvent e) {
        dispose();
    }

    void this_windowClosing(WindowEvent e) {
        dispose();
    }
}

class DialogHost_btnOk_actionAdapter implements ActionListener {
    DialogHost adaptee;

    DialogHost_btnOk_actionAdapter(DialogHost adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.btnOk_actionPerformed(e);
    }
}

class DialogHost_btnCancel_actionAdapter implements ActionListener {
    DialogHost adaptee;

    DialogHost_btnCancel_actionAdapter(DialogHost adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.btnCancel_actionPerformed(e);
    }
}

class DialogHost_this_windowAdapter extends WindowAdapter {
    DialogHost adaptee;

    DialogHost_this_windowAdapter(DialogHost adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        adaptee.this_windowClosing(e);
    }
}
