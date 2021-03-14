

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PromptPanel extends Panel implements FocusListener {
    boolean firstFocus;
    Label[] labels;
    TextField[] textFields;
    Frame parent;
    ActionListener textFieldChangeListener;

    public PromptPanel(int iFields, ActionListener changeListener) {
        if (iFields < 1)
            iFields = 1;

        labels = new Label[iFields];
        textFields = new TextField[iFields];
        textFieldChangeListener = changeListener;

        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLabel(int i, String text) {
        if (i < labels.length
            && labels[i] != null)
            labels[i].setText(text);
    }

    public String getLabel(int i) {
        if (i < labels.length)
            return labels[i].getText();
        else
            return null;
    }

    public void setTextField(int i, String text) {
        if (text == null)
            text = "";
        if (i < textFields.length
            && textFields[i] != null)
            textFields[i].setText(text);
    }

    public String getTextField(int i) {
        if (i < textFields.length)
            return textFields[i].getText();
        else
            return null;
    }

    public void setTextFieldEnabled(int i, boolean flag) {
        if (i < textFields.length
            && textFields[i] != null)
            textFields[i].setEnabled(flag);
    }

    public boolean getTextFieldEnabled(int i) {
        if (i < textFields.length
            && textFields[i] != null)
            return textFields[i].isEnabled();
        else
            return false;
    }

    public void setTextFieldVisible(int i, boolean flag) {
        if (i < textFields.length
            && textFields[i] != null)
            textFields[i].setVisible(flag);
    }

    public boolean getTextFieldVisible(int i) {
        if (i < textFields.length
            && textFields[i] != null)
            return textFields[i].isVisible();
        else
            return false;
    }

    public void setTextFieldEchoChar(int i, char ch) {
        if (i < textFields.length
            && textFields[i] != null) {
            textFields[i].setEchoChar(ch);
        }
    }

    void init() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 140;
        gridBagConstraints.fill = GridBagConstraints.NONE;              // undo the custom settings from above

        for (int i = 0; i < labels.length; i++) {
            gridBagConstraints.insets.left = 0;
            gridBagConstraints.insets.right = 0;

            labels[i] = new Label("Field" + i);
            labels[i].setAlignment(0);
            add(labels[i]);

            gridBagConstraints.insets.left = 5;

            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2 * i + 1;
            gridBagConstraints.anchor = GridBagConstraints.EAST;       // anchor close to field below and left align
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.weightx = 0;
            gridBagConstraints.weighty = 1;
            gridBagConstraints.fill = gridBagConstraints.NONE;
            gridBagLayout.setConstraints(labels[i], gridBagConstraints);

            gridBagConstraints.insets.right = 5;

            textFields[i] = new TextField("Value " + i, 12);
            add(textFields[i]);
            //gridBagConstraints.gridx		= GridBagConstraints.RELATIVE;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 2 * i + 1;
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.weightx = 2;
            gridBagConstraints.weighty = 1;
            gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
            gridBagConstraints.anchor = GridBagConstraints.WEST;
            gridBagLayout.setConstraints(textFields[i], gridBagConstraints);

            textFields[i].setEnabled(true);

            firstFocus = false;
            textFields[i].addFocusListener(this);
        }

        add(saveCheckBox);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2 * labels.length + 1;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 2;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(saveCheckBox, gridBagConstraints);
        saveCheckBox.setVisible(showSaveCheckBox);
    }

    /**
     * Invoked when a component gains the keyboard focus.
     */
    @Override
    public void focusGained(FocusEvent e) {

        // The very first time we acquire focus, move into first field
        if (!firstFocus) {
            firstFocus = true;
            textFields[0].requestFocus();
        }
    }

    /**
     * Invoked when a component loses the keyboard focus.
     */
    @Override
    public void focusLost(FocusEvent e) {
        Object src = e.getSource();
        if (src instanceof TextField) {
            int iMax = textFields.length;
            for (int i = 0; i < iMax; i++) {
                if (src == textFields[i]) {
                    if (textFieldChangeListener != null)
                        textFieldChangeListener.actionPerformed(new ActionEvent(e, ActionEvent.ACTION_PERFORMED, textFields[i].getText(), i));
                }
            }
        }
    }

    @Override
    public void setBackground(Color c) {
        super.setBackground(c);
        for (int i = 0; i < labels.length; i++)
            labels[i].setBackground(c);
        for (int i = 0; i < textFields.length; i++)
            textFields[i].setBackground(c);
    }

    @Override
    public void setForeground(Color c) {
        super.setForeground(c);
        for (int i = 0; i < labels.length; i++)
            labels[i].setForeground(c);
        for (int i = 0; i < textFields.length; i++)
            textFields[i].setForeground(c);
    }

    private boolean showSaveCheckBox;
    private boolean saveFlag;
    private Checkbox saveCheckBox = new Checkbox("Save");

    public boolean getShowSaveCheckBox() {
        return showSaveCheckBox;
    }

    public void setShowSaveCheckBox(boolean flag) {
        showSaveCheckBox = flag;
        saveCheckBox.setVisible(flag);
        saveCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setSaveFlag(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
    }

    public boolean getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(boolean flag) {
        saveFlag = flag;
        if (saveCheckBox.getState() != flag)
            saveCheckBox.setState(flag);
    }

    public void setSaveFlagText(String text) {
        saveCheckBox.setLabel(text);
    }
}

