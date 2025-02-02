package com.prasad.util;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * FontPicker is a dialog box that allows the user to select a font.
 * A list of valid font families is displayed along with options
 * to select the style (plain, bold, italic) and size.
 **/
public class FontPicker extends Dialog implements ActionListener, ItemListener {
    public static final int OK = 1;
    public static final int CANCEL = 2;
    // MSIE 4.0 does not seem to recognize the Java 1.1 generic font names
    // (Serif, SansSerif, Monospaced), so use the family names
    static final String[] fontFamilies = {
        "TimesRoman", "Helvetica", "Courier", "Dialog", "DialogInput"
    };
    // Generic font names
    static final String[] genericFonts = {
        "Serif", "SansSerif", "Monospaced", "Dialog", "DialogInput"
    };
    static final String[] fontLabels = {
        "Times-Roman", "Helvetica", "Courier", "Dialog", "Dialog-input"
    };
    java.awt.List familyList, sizeList;
    CheckboxGroup styleList;
    Checkbox plainCheck, boldCheck, italicCheck, boldItalicCheck;
    Font currentFont;
    Button okButton, cancelButton;
    TextArea fontDisplay;
    Panel buttonPanel, fontSelectPanel, fontStylePanel;
    Font buttonFont = new Font("Helvetica", Font.BOLD, 12);
    Font labelFont = new Font("TimesRoman", Font.BOLD, 14);
    int currentFamily = -1, currentSize = -1;

    /**
     * Create a new FontPicker dialog.
     *
     * @param parent the parent frame of the dialog
     * @param modal  true if this dialog should be modal
     **/
    public FontPicker(Frame parent, boolean modal) {
        this(null, parent, modal);
    }

    /**
     * Create a new FontPicker dialog initially displaying the
     * specified font.
     *
     * @param font   the default font to show
     * @param parent the parent frame of the dialog
     * @param modal  true if this dialog should be modal
     **/
    public FontPicker(Font font, Frame parent, boolean modal) {
        super(parent, "Font Chooser", modal);
        setLayout(new BorderLayout());

        // Create components for font dialog
        createFontSelectPanel();
        createButtonPanel();

        if (font != null)
            setFont(font);
        else
            setFont(new Font("TimesRoman", Font.PLAIN, 12));

        // Enable window events
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }

    /**
     * Create the font selection panel and its components.
     **/
    void createFontSelectPanel() {
        Font font;
        fontSelectPanel = new Panel();
        fontSelectPanel.setLayout(new GridBagLayout());

        GridBagConstraints constr = new GridBagConstraints();

        // Create list of font families
        Label label = new Label("Font family:");
        label.setFont(labelFont);

        constr.gridx = 0;
        constr.gridy = 0;
        constr.gridwidth = 1;
        constr.gridheight = 1;
        constr.insets = new Insets(0, 10, 0, 10);
        constr.fill = GridBagConstraints.NONE;
        constr.anchor = GridBagConstraints.WEST;
        fontSelectPanel.add(label, constr);

        familyList = new java.awt.List(5);
        familyList.setMultipleMode(false);
        font = new Font("Helvetica", Font.PLAIN, 12);
        familyList.setFont(font);
        familyList.setBackground(Color.white);
        for (int i = 0; i < fontLabels.length; i++)
            familyList.add(fontLabels[i]);
        familyList.addItemListener(this);

        constr.gridy = 1;
        fontSelectPanel.add(familyList, constr);

        // Create list of font sizes
        label = new Label("Font size:");
        label.setFont(labelFont);

        constr.gridx = 1;
        constr.gridy = 0;
        fontSelectPanel.add(label, constr);

        sizeList = new java.awt.List(5);
        sizeList.setMultipleMode(false);
        sizeList.setFont(font);
        sizeList.setBackground(Color.white);
        for (int i = 8; i <= 24; i++)
            sizeList.add(i + " points");
        sizeList.addItemListener(this);

        constr.gridy = 1;
        fontSelectPanel.add(sizeList, constr);

        // Create list of font styles
        label = new Label("Font style:");
        label.setFont(labelFont);

        constr.gridx = 0;
        constr.gridy = 2;
        constr.gridwidth = 2;
        constr.anchor = GridBagConstraints.WEST;
        constr.insets.top = 10;
        fontSelectPanel.add(label, constr);

        fontStylePanel = new Panel();
        fontStylePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        font = new Font("TimesRoman", Font.PLAIN, 12);
        styleList = new CheckboxGroup();
        plainCheck = new Checkbox(" Plain", styleList, true);
        plainCheck.setFont(font);
        plainCheck.addItemListener(this);
        fontStylePanel.add(plainCheck);
        boldCheck = new Checkbox(" Bold", styleList, false);
        boldCheck.setFont(font);
        boldCheck.addItemListener(this);
        fontStylePanel.add(boldCheck);
        italicCheck = new Checkbox(" Italic", styleList, false);
        italicCheck.setFont(font);
        italicCheck.addItemListener(this);
        fontStylePanel.add(italicCheck);
        boldItalicCheck = new Checkbox(" Bold-italic", styleList, false);
        boldItalicCheck.setFont(font);
        boldItalicCheck.addItemListener(this);
        fontStylePanel.add(boldItalicCheck);

        constr.gridy = 3;
        constr.anchor = GridBagConstraints.WEST;
        constr.insets.top = 0;
        constr.insets.left = 0;
        constr.insets.right = 0;
        constr.insets.bottom = 10;
        fontSelectPanel.add(fontStylePanel, constr);

        // Create text box to display example of selected font
        label = new Label("Sample text:");
        label.setFont(labelFont);

        constr.gridy = 4;
        constr.gridwidth = 2;
        constr.anchor = GridBagConstraints.WEST;
        constr.insets.left = 10;
        constr.insets.right = 10;
        constr.insets.bottom = 0;
        fontSelectPanel.add(label, constr);

        String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ\nabcdefghijklmnopqrstuvwxyz\n0123456789";
        fontDisplay = new TextArea(text, 6, 26, TextArea.SCROLLBARS_HORIZONTAL_ONLY);
        fontDisplay.setForeground(Color.black);
        fontDisplay.setBackground(Color.white);
        fontDisplay.setEditable(false);

        constr.gridy = 5;
        constr.insets.bottom = 10;
        constr.fill = GridBagConstraints.HORIZONTAL;
        fontSelectPanel.add(fontDisplay, constr);

        add("Center", fontSelectPanel);
    }

    /**
     * Create the panel containing the OK and cancel buttons at
     * the bottom of the dialog.
     **/
    void createButtonPanel() {
        // Create OK and Cancel button at bottom of dialog
        buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 2));
        okButton = new Button("OK");
        okButton.setFont(buttonFont);
        okButton.addActionListener(this);
        buttonPanel.add(okButton);
        cancelButton = new Button("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);
        add("South", buttonPanel);
    }

    /**
     * Return the current font selected in the dialog
     **/
    @Override
    public Font getFont() {
        return currentFont;
    }

    /**
     * Set the font shown in the dialog
     *
     * @param font the new font
     **/
    @Override
    public void setFont(Font font) {
        if (font != null) {
            currentFont = font;

            // Set the font family in the select list
            String items[];
            String family = font.getFamily();
            if (family != null) {
                int i;
                for (i = 0; i < fontFamilies.length; i++) {
                    if (family.equalsIgnoreCase(fontFamilies[i])) {
                        // MSIE 4.0 does not deselect previous item by default,
                        // even when multiple selection mode is turned off,
                        // so we'll explicitly deselect it here.
                        if (currentFamily >= 0)
                            familyList.deselect(currentFamily);
                        familyList.select(i);
                        currentFamily = i;
                        break;
                    }
                }
                if (i == fontFamilies.length) {
                    // No match, check generic font names
                    for (i = 0; i < genericFonts.length; i++) {
                        if (family.equalsIgnoreCase(genericFonts[i])) {
                            if (currentFamily >= 0)
                                familyList.deselect(currentFamily);
                            familyList.select(i);
                            currentFamily = i;
                            break;
                        }
                    }
                }
            }

            // Set the font size in the select list
            String size = String.valueOf(font.getSize());
            items = sizeList.getItems();
            for (int i = 0; i < items.length; i++) {
                if (items[i].indexOf(size) >= 0) {
                    // MSIE 4.0 does not deselect previous item by default,
                    // even when multiple selection mode is turned off,
                    // so we'll explicitly deselect it here.
                    if (currentSize >= 0)
                        sizeList.deselect(currentSize);
                    sizeList.select(i);
                    currentSize = i;
                    break;
                }
            }

            // Set the font style checkbox
            switch (font.getStyle()) {
                case Font.PLAIN:
                    styleList.setSelectedCheckbox(plainCheck);
                    break;
                case Font.BOLD:
                    styleList.setSelectedCheckbox(boldCheck);
                    break;
                case Font.ITALIC:
                    styleList.setSelectedCheckbox(italicCheck);
                    break;
                case Font.BOLD + Font.ITALIC:
                    styleList.setSelectedCheckbox(boldItalicCheck);
                    break;
            }

            // Update the font sample
            fontDisplay.setFont(font);
        }
    }

    /**
     * Set the background color of the dialog
     *
     * @param color the new color
     **/
    @Override
    public void setBackground(Color color) {
        if (color != null) {
            super.setBackground(color);
            fontSelectPanel.setBackground(color);
            fontStylePanel.setBackground(color);
            buttonPanel.setBackground(color);
        }
    }

    /**
     * Return the foreground color of font family and size select lists.
     **/
    public Color getFontListForeground() {
        return familyList.getForeground();
    }

    /**
     * Set the foreground color of font family and size select lists.
     *
     * @param color the new color
     **/
    public void setFontListForeground(Color color) {
        if (color != null) {
            familyList.setForeground(color);
            sizeList.setForeground(color);
        }
    }

    /**
     * Return the background color of font family and size select lists.
     **/
    public Color getFontListBackground() {
        return familyList.getBackground();
    }

    /**
     * Set the background color of font family and size select lists.
     *
     * @param color the new color
     **/
    public void setFontListBackground(Color color) {
        if (color != null) {
            familyList.setBackground(color);
            sizeList.setBackground(color);
        }
    }

    /**
     * Return the foreground color of font sample text display
     **/
    public Color getFontDisplayForeground() {
        return fontDisplay.getForeground();
    }

    /**
     * Set the foreground color of font sample text display
     *
     * @param color the new color
     **/
    public void setFontDisplayForeground(Color color) {
        if (color != null) {
            fontDisplay.setForeground(color);
        }
    }

    /**
     * Return the background color of font sample text display
     **/
    public Color getFontDisplayBackground() {
        return fontDisplay.getBackground();
    }

    /**
     * Set the background color of font sample text display
     *
     * @param color the new color
     **/
    public void setFontDisplayBackground(Color color) {
        if (color != null) {
            fontDisplay.setBackground(color);
        }
    }

    /**
     * Handle events from font select lists
     *
     * @param event the item event
     **/
    @Override
    public void itemStateChanged(ItemEvent event) {
        String family = currentFont.getFamily();
        int style = currentFont.getStyle();
        int size = currentFont.getSize();

        Integer item;
        if (event.getSource() == familyList) {
            item = (Integer) event.getItem();
            family = fontFamilies[item.intValue()];
        } else if (event.getSource() == sizeList) {
            item = (Integer) event.getItem();
            size = item.intValue() + 8;
        } else if (event.getSource() == plainCheck) {
            style = Font.PLAIN;
        } else if (event.getSource() == boldCheck) {
            style = Font.BOLD;
        } else if (event.getSource() == italicCheck) {
            style = Font.ITALIC;
        } else if (event.getSource() == boldItalicCheck) {
            style = Font.BOLD + Font.ITALIC;
        }
        setFont(new Font(family, style, size));
    }

    /**
     * Handle action events from buttons
     *
     * @param event the action event
     **/
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == cancelButton)
            currentFont = null;
        setVisible(false);
    }

    /**
     * Handle window events
     **/
    @Override
    public void processWindowEvent(WindowEvent event) {
        if (event.getID() == WindowEvent.WINDOW_CLOSING) {
            setVisible(false);
            currentFont = null;
        } else
            super.processWindowEvent(event);
    }
}