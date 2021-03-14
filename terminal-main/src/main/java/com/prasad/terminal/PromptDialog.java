

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;


public class PromptDialog extends Dialog
    implements ActionListener, KeyListener {
    protected PromptPanel panel;
    public static final String OK = "ok";       //NORES: internal name
    public static final String CANCEL = "cancel";   //NORES: internal name
    Button okButton;
    Button cancelButton;
    Panel buttonHolder;
    String result;

    protected boolean centered = true;  // centered on screen
    protected boolean enterOK = true;   // allow the enter key to generate an OK command
    protected boolean escapeCancel = true;   // allow the escape key to generate a CANCEL command


    public PromptDialog(java.awt.Frame frame, String title, int iFields, ActionListener changeListener) {
        super(frame, title, true);
        panel = new PromptPanel(iFields, changeListener);
        setLayout(new BorderLayout());
        //setBackground(SystemColor.control);
        add(panel, BorderLayout.CENTER);
        // We want the ENTER key to return success from the dialog
        setEnterOK(true);
        okButton = new Button(OK);
        okButton.setActionCommand(OK);
        cancelButton = new Button(CANCEL);
        cancelButton.setActionCommand(CANCEL);
        buttonHolder = new Panel();
        buttonHolder.add(okButton);
        buttonHolder.add(cancelButton);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        okButton.addKeyListener(this);
        cancelButton.addKeyListener(this);
        add(buttonHolder, BorderLayout.SOUTH);
        int x = frame.getLocation().x + (frame.getSize().width - getSize().width) / 2;
        int y = frame.getLocation().y + (frame.getSize().height - getSize().height) / 2;
        setBackground(frame.getBackground());
        setForeground(frame.getForeground());
        setLocation(x, y);
        pack();
    }

    public void setLabel(int i, String text) {
        panel.setLabel(i, text);
    }

    public String getLabel(int i) {
        return panel.getLabel(i);
    }

    public void setTextField(int i, String text) {
        panel.setTextField(i, text);
    }

    public String getTextField(int i) {
        return panel.getTextField(i);
    }

    public void setTextFieldVisible(int i, boolean flag) {
        panel.setTextFieldVisible(i, flag);
    }

    public boolean getTextFieldVisible(int i) {
        return panel.getTextFieldVisible(i);
    }

    public void setTextFieldEnabled(int i, boolean flag) {
        panel.setTextFieldEnabled(i, flag);
    }

    public boolean getTextFieldEnabled(int i) {
        return panel.getTextFieldEnabled(i);
    }

    public void setTextFieldEchoChar(int i, char ch) {
        panel.setTextFieldEchoChar(i, ch);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        Insets i = getInsets();

        d.width += 60;
        d.height += 60;
        return d;
    }

    /**
     * Invokes the Prompt dialog
     *
     * @return True means OK was chosen, False means Cancel was chosen
     */
    static public boolean invoke(Frame frame, String title, int iFields, ActionListener changeListener) {
        boolean madeNewFrame = false;
        if (frame == null) {
            frame = new Frame();
            madeNewFrame = true;
        }

        if (title == null)
            title = "";

        PromptDialog dlg = new PromptDialog(frame, title, iFields, changeListener);
        dlg.setVisible(true);
        return (dlg.getResult() == OK);
    }

    public void setCentered(boolean c) {
        centered = c;
    }

    public boolean isCentered() {
        return centered;
    }

    public void setEnterOK(boolean e) {
        enterOK = e;
    }

    public boolean isEnterOK() {
        return enterOK;
    }

    public void setEscapeCancel(boolean c) {
        escapeCancel = c;
    }

    public boolean isEscapeCancel() {
        return escapeCancel;
    }

    /**
     * Process Button Event for Ok, Help
     **/

    @Override
    public void actionPerformed(ActionEvent e) {
        processActionEvent(e);
    }


    /**
     * Process standard dialog accelerators for help,ok,cancel
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && escapeCancel && (e.getModifiers() & (e.SHIFT_MASK | e.CTRL_MASK | e.ALT_MASK)) == 0)
            processActionEvent(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, CANCEL));
        else if (e.getKeyCode() == KeyEvent.VK_ENTER && (e.getModifiers() & (e.SHIFT_MASK | e.ALT_MASK)) == 0) {
            boolean isCtrl = (e.getModifiers() & e.CTRL_MASK) != 0;
            boolean needsCtrl = !enterOK || e.getSource() instanceof TextArea;
            System.out.println(isCtrl + ":" + needsCtrl);
            if (isCtrl == needsCtrl)
                processActionEvent(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, OK));
        }
//    else if (e.getKeyCode() == e.VK_F1 && (e.getModifiers() & (e.SHIFT_MASK | e.CTRL_MASK | e.ALT_MASK)) == 0)
//      processActionEvent(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, HELP_COMMAND));
    }

    /**
     *
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     *
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }


    protected void processActionEvent(ActionEvent e) {
        //System.err.println("ButtonDialog: processActionEvent " + e);
        Object src = e.getSource();

        if (src instanceof Button) {
            result = e.getActionCommand();
            setVisible(false);
            dispose();
        }
    }

    @Override
    public void setBackground(Color c) {
        super.setBackground(c);
        panel.setBackground(c);
        okButton.setBackground(c);
        cancelButton.setBackground(c);
    }

    @Override
    public void setForeground(Color c) {
        super.setForeground(c);
        panel.setForeground(c);
        okButton.setForeground(c);
        cancelButton.setForeground(c);
    }

    public String getResult() {
        return result;
    }

    public boolean getShowSaveCheckBox() {
        return panel.getShowSaveCheckBox();
    }

    public void setShowSaveCheckBox(boolean flag) {
        panel.setShowSaveCheckBox(flag);
    }

    public boolean getSaveFlag() {
        return panel.getSaveFlag();
    }

    public void setSaveFlag(boolean flag) {
        panel.setSaveFlag(flag);
    }

    public void setSaveFlagText(String text) {
        panel.setSaveFlagText(text);
    }
}
