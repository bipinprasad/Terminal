
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Get the the Unix Host's environment variables, user id, and current working directory.
 * Information about the host will also be gathered. This host info will be used in
 * tailoring Unix command help, or other idiosyncracies that are Unix system dependent.
 * <p>
 * When OK is clicked on this form, then it will send commands thru the owning PanelTerminal.
 */
public class FrameGetUnixEnv extends Frame implements TerminalVputListener {
    Panel panel1 = new Panel();
    Panel panel2 = new Panel();
    Panel panelBtns = new Panel();
    Panel panelAdvanced = new Panel();
    BorderLayout borderLayout1 = new BorderLayout();
    Button btnClose = new Button();
    Button btnGetEnv = new Button();
    GridLayout gridLayout1 = new GridLayout();
    Label lblLogName = new Label();
    TextField txtLogName = new TextField();
    Label lblEnvCmd = new Label();
    TextField txtEnvCmd = new TextField();
    Label lblPwdCmd = new Label();
    TextField txtPwdCmd = new TextField();
    Label lblSysNameCmd = new Label();
    TextField txtSysNameCmd = new TextField();
    Label lblSysRelCmd = new Label();
    TextField txtSysRelCmd = new TextField();
    Label lblTimeout = new Label();
    TextField txtTimeout = new TextField();
    TextArea textAreaEnv = new TextArea();
    Button btnAdvanced = new Button();
    BorderLayout borderLayout2 = new BorderLayout();

    TerminalSender terminalSender;

    public FrameGetUnixEnv() {
        try {
            jbInit();
            response = new StringBuffer(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FrameGetUnixEnv(TerminalSender sender) {
        this();
        setTerminalSender(sender);
    }

    private void jbInit() throws Exception {
        panel2.setLayout(borderLayout1);
        panelAdvanced.setLayout(gridLayout1);
        btnClose.setLabel("Close");
        btnClose.addActionListener(new FrameGetUnixEnv_btnClose_actionAdapter(this));
        textAreaEnv.setEditable(false);
        btnAdvanced.setLabel("Hide Details");
        btnAdvanced.addActionListener(new FrameGetUnixEnv_btnAdvanced_actionAdapter(this));
        btnGetEnv.setLabel("Get Environment");
        btnGetEnv.addActionListener(new FrameGetUnixEnv_btnGetEnv_actionAdapter(this));
        lblEnvCmd.setAlignment(2);
        lblEnvCmd.setText("Command to get environment ");
        lblPwdCmd.setAlignment(2);
        lblPwdCmd.setText("Command to get directory ");
        lblSysNameCmd.setAlignment(2);
        lblSysNameCmd.setText("Command to get System Name ");
        lblSysRelCmd.setAlignment(2);
        lblSysRelCmd.setText("Command to get System Version ");
        lblTimeout.setAlignment(2);
        lblTimeout.setText("Command Timeout (in milliseconds) ");
        this.setSize(new Dimension(438, 327));
        panel1.setLayout(borderLayout2);
        gridLayout1.setRows(6);
        gridLayout1.setColumns(2);
        txtLogName.setText("logname");
        lblLogName.setAlignment(2);
        lblLogName.setText("Command to get login id ");
        txtEnvCmd.setText("env");
        txtPwdCmd.setText("pwd");
        txtSysNameCmd.setText("uname -s");
        txtSysRelCmd.setText("uname -r");
        txtTimeout.setText("2000");
        this.add(panel1, BorderLayout.CENTER);
        panel1.add(textAreaEnv, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.SOUTH);
        panel2.add(panelAdvanced, BorderLayout.CENTER);
        panelAdvanced.add(lblEnvCmd, null);
        panelAdvanced.add(txtEnvCmd, null);
        panelAdvanced.add(lblPwdCmd, null);
        panelAdvanced.add(txtPwdCmd, null);
        panelAdvanced.add(lblSysNameCmd, null);
        panelAdvanced.add(txtSysNameCmd, null);
        panelAdvanced.add(lblSysRelCmd, null);
        panelAdvanced.add(txtSysRelCmd, null);
        panelAdvanced.add(lblLogName, null);
        panelAdvanced.add(txtLogName, null);
        panelAdvanced.add(lblTimeout, null);
        panelAdvanced.add(txtTimeout, null);
        panel2.add(panelBtns, BorderLayout.SOUTH);
        panelBtns.add(btnAdvanced, null);
        panelBtns.add(btnGetEnv, null);
        panelBtns.add(btnClose, null);
    }

    public TerminalSender getTerminalSender() {
        return terminalSender;
    }

    public void setTerminalSender(TerminalSender sender) {
        terminalSender = sender;
    }

    @Override
    public void setBackground(Color c) {
        super.setBackground(c);

        panel1.setBackground(c);
        panel2.setBackground(c);
        panelBtns.setBackground(c);
        panelAdvanced.setBackground(c);
        lblEnvCmd.setBackground(c);
        lblPwdCmd.setBackground(c);
        lblSysNameCmd.setBackground(c);
        lblSysRelCmd.setBackground(c);
        lblTimeout.setBackground(c);
        lblLogName.setBackground(c);
        txtLogName.setBackground(c);
        txtEnvCmd.setBackground(c);
        txtPwdCmd.setBackground(c);
        txtSysNameCmd.setBackground(c);
        txtSysRelCmd.setBackground(c);
        txtTimeout.setBackground(c);
        textAreaEnv.setBackground(c);
    }

    @Override
    public void setForeground(Color c) {
        super.setForeground(c);

        panel1.setForeground(c);
        panel2.setForeground(c);
        panelBtns.setForeground(c);
        panelAdvanced.setForeground(c);
        lblEnvCmd.setForeground(c);
        lblPwdCmd.setForeground(c);
        lblSysNameCmd.setForeground(c);
        lblSysRelCmd.setForeground(c);
        lblTimeout.setForeground(c);
        lblLogName.setForeground(c);
        txtLogName.setForeground(c);
        txtEnvCmd.setForeground(c);
        txtPwdCmd.setForeground(c);
        txtSysNameCmd.setForeground(c);
        txtSysRelCmd.setForeground(c);
        txtTimeout.setForeground(c);
        textAreaEnv.setForeground(c);
    }

    void btnAdvanced_actionPerformed(ActionEvent e) {
        String btnLbl = btnAdvanced.getLabel();
        if (btnLbl.startsWith("Show")) {
            panelAdvanced.setVisible(true);
            btnAdvanced.setLabel("Hide Details");
        } else if (btnLbl.startsWith("Hide")) {
            panelAdvanced.setVisible(false);
            btnAdvanced.setLabel("Show Details");
        }
        validate();
        repaint();
    }

    boolean active;
    int state;
    long waitTime = 0; // milliseconds
    long waitTimeout = 2000; // milliseconds
    String cmd;
    StringBuffer response;
    private static final int STATE_CMD_START = 1;
    private static final int STATE_RESPONSE_START = 2;
    private static final int STATE_RESPONSE_END = 3;


    @Override
    public void vput(Graphics g, int ch) {
        //TODO: implement this prasad.terminal.TerminalVputListener method;
        if (!active)
            return;

        if (ch != -1)
            waitTime = 0;

        switch (state) {
            case STATE_CMD_START:
                if (ch == '\n')
                    state = STATE_RESPONSE_START;
                break;

            case STATE_RESPONSE_START:
                if (ch != '\r')
                    response.append((char) ch);
                break;

            case STATE_RESPONSE_END: // this state is set by the ResponseTimeoutThread upon a timeout
                // remove last line
                String temp = response.toString();
                int idx = temp.lastIndexOf('\n');
                if (idx > 0)
                    temp = temp.substring(0, idx);

                if (cmd.equals(txtEnvCmd.getText())) {
                    setEnvironmentVariables(temp);
                    sendCommand(txtPwdCmd.getText());
                } else if (cmd.equals(txtPwdCmd.getText())) {
                    unixEnv.dir = temp;
                    sendCommand(txtSysNameCmd.getText());
                } else if (cmd.equals(txtSysNameCmd.getText())) {
                    unixEnv.sysName = temp;
                    sendCommand(txtSysRelCmd.getText());
                } else if (cmd.equals(txtSysRelCmd.getText())) {
                    unixEnv.sysRelease = temp;
                    sendCommand(txtLogName.getText());
                } else if (cmd.equals(txtLogName.getText())) {
                    setLogName(temp);
                    setActive(false);    // called for the last command
                }
                break;
        }
    }

    /**
     * Called to activate the first command with a TRUE FLAG.
     */
    void setActive(boolean flag) {
        active = flag;
        // reset other states
        if (active) {
            if (terminalSender != null)
                terminalSender.addTerminalVputListener(this);
            sendCommand(txtEnvCmd.getText());
        } else {
            if (terminalSender != null)
                terminalSender.removeTerminalVputListener(this);
            if (unixEnv != null)
                textAreaEnv.setText(unixEnv.toString());
        }
    }

    ResponseTimeoutThread responseTimeoutThread;

    private void sendCommand(String s) {
        cmd = s;
        response.setLength(0);
        state = STATE_CMD_START;

        // send command is called within the context of a previous thread.
        // Do not stop it. Let it die by itself.
        //if (responseTimeoutThread != null)
        //{
        //	try{
        //	responseTimeoutThread.stop();
        //	} catch(Exception e){}
        //	responseTimeoutThread = null;
        //}
        waitTime = 0;
        responseTimeoutThread = new ResponseTimeoutThread();
        if (debug)
            System.out.println("Sending command [" + cmd + "]");
        if (terminalSender != null
            && cmd != null
            && cmd.length() > 0) {
            terminalSender.send(cmd + "\n");
        }
        responseTimeoutThread.start();
    }

    private boolean debug;

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean b) {
        debug = b;
    }

    private UnixEnv unixEnv;

    void setEnvironmentVariables(String temp) {
        if (temp == null)
            return;

        if (unixEnv == null)
            unixEnv = new UnixEnv();

        unixEnv.addEnvNameValues(temp);
    }

    void setLogName(String temp) {
        if (temp == null)
            return;

        if (unixEnv == null)
            unixEnv = new UnixEnv();

        unixEnv.setLoginId(temp);
    }

    class UnixEnv {
        String loginId;
        String dir;        // current working directory
        String sysName;    // sysname name
        String sysRelease;    // system release
        Hashtable envValues;

        void init() {
            loginId = null;
            dir = null;
            sysName = null;
            sysRelease = null;
            initEnvValues();
        }

        void initEnvValues() {
            if (envValues == null)
                envValues = new Hashtable();
            else
                envValues.clear();
        }

        void setLoginId(String name) {
            loginId = name;
        }

        void addEnvNameValues(String allEnv) {
            initEnvValues();
            StringTokenizer st = new StringTokenizer(allEnv, "\n\r", false);
            while (st.hasMoreTokens()) {
                String oneVar = st.nextToken();
                int idx = oneVar.indexOf('=');
                if (idx > 0) {
                    if (debug)
                        System.out.println("Adding [" + oneVar.substring(0, idx) + "]=[" + oneVar.substring(idx + 1) + "]");

                    envValues.put(oneVar.substring(0, idx), oneVar.substring(idx + 1));
                }
            }
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            if (loginId != null)
                sb.append("Login Name: [" + loginId + "]\n");
            else
                sb.append("Login Name: [(null)]\n");
            if (dir != null)
                sb.append("Directory: [" + dir + "]\n");
            else
                sb.append("Directory: [(null)]\n");
            if (sysName != null)
                sb.append("System Name: [" + sysName + "]\n");
            else
                sb.append("System Name: [(null)]\n");
            if (sysRelease != null)
                sb.append("System Release: [" + sysRelease + "]\n");
            else
                sb.append("System Release: [(null)]\n");
            int iMax = envValues.size();
            if (iMax > 0) {
                sb.append("Environment Variables:\n");
                Enumeration enumeration =envValues.keys();
                if (enumeration != null)
                {
                    try {
                        while (enumeration.hasMoreElements())
                        {
                            String key = (String) enumeration.nextElement();
                            String val = (String) envValues.get(key);
                            sb.append("\t" + key + "=\"" + val + "\"\n");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else
                sb.append("Environment Variables are Undefined\n");
            return sb.toString();
        }
    }

    class ResponseTimeoutThread extends Thread {
        @Override
        public void run() {
            int cnt = 0;
            while (waitTime < waitTimeout) {
                cnt++;
                try {
                    if (debug)
                        System.out.println("ResponseTimeoutThread: loop count " + cnt);
                    sleep(100);
                    waitTime += 100;
                    if (waitTime >= waitTimeout) {
                        if (debug)
                            System.out.println("ResponseTimeoutThread: timed out after " + waitTimeout + " milliseconds");
                        state = STATE_RESPONSE_END;
                        vput(null, -1); // allow next command to be executed
                        break;
                    }
                } catch (InterruptedException e) {
                } catch (ThreadDeath e) {
                } catch (Exception e) {
                }
            }
            if (debug)
                System.out.println("ResponseTimeoutThread: Run complete after " + cnt + " loops");
        }
    }

    void btnGetEnv_actionPerformed(ActionEvent e) {
        String s = txtTimeout.getText();
        long t = -1;
        try {
            t = Long.parseLong(s);
        } catch (Exception e2) {
        }
        if (t < 200
            || t > 99000) {
            t = 2000;
            txtTimeout.setText("" + t);
        }
        waitTimeout = t;
        setActive(true);
    }

    void btnClose_actionPerformed(ActionEvent e) {
        setVisible(false);
    }
}

class FrameGetUnixEnv_btnAdvanced_actionAdapter implements java.awt.event.ActionListener {
    FrameGetUnixEnv adaptee;

    FrameGetUnixEnv_btnAdvanced_actionAdapter(FrameGetUnixEnv adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.btnAdvanced_actionPerformed(e);
    }
}

class FrameGetUnixEnv_btnGetEnv_actionAdapter implements java.awt.event.ActionListener {
    FrameGetUnixEnv adaptee;


    FrameGetUnixEnv_btnGetEnv_actionAdapter(FrameGetUnixEnv adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.btnGetEnv_actionPerformed(e);
    }
}

class FrameGetUnixEnv_btnClose_actionAdapter implements java.awt.event.ActionListener {
    FrameGetUnixEnv adaptee;


    FrameGetUnixEnv_btnClose_actionAdapter(FrameGetUnixEnv adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.btnClose_actionPerformed(e);
    }
}

