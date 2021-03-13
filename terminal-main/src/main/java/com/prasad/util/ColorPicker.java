package com.prasad.util;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * The ColorPicker class provides an interface to choose colors. It displays
 * a color wheel to pick hue and saturation values and a bar to choose the
 * brightness value.
 **/
public class ColorPicker extends Dialog implements ActionListener, FocusListener {
    public static final String CHANGE_ACTION = "Change";
    public static final String CANCEL_ACTION = "Cancel";
    ColorCanvas colorCanvas;
    TextField redInput, greenInput, blueInput;
    ColorSwatch oldSwatch, newSwatch;
    Color currentColor;
    Button okButton, cancelButton;
    ActionListener colorListeners;

    /**
     * Create a new color picker component
     **/
    public ColorPicker(Frame parent, boolean modal) {
        this(Color.white, parent, modal);
    }

    /**
     * Create a new color picker component
     *
     * @param color the initial color
     **/
    public ColorPicker(Color color, Frame parent, boolean modal) {
        super(parent, "Color chooser", modal);

        setLayout(new GridBagLayout());
        createDialogComponents(color);

        setColor(color);
        colorCanvas.setColor(color);
    }

    /**
     * Create the components in the color picker dialog
     **/
    void createDialogComponents(Color color) {
        GridBagConstraints constr = new GridBagConstraints();
        constr.gridwidth = 1;
        constr.gridheight = 4;
        constr.insets = new Insets(0, 0, 0, 10);
        constr.fill = GridBagConstraints.NONE;
        constr.anchor = GridBagConstraints.CENTER;

        constr.gridx = 0;
        constr.gridy = 0;

        // Create color wheel canvas
        colorCanvas = new ColorCanvas(50, color);
        add(colorCanvas, constr);

        // Create input boxes to enter values
        Font font = new Font("DialogInput", Font.PLAIN, 10);
        redInput = new TextField(3);
        redInput.addFocusListener(this);
        redInput.setFont(font);
        greenInput = new TextField(3);
        greenInput.addFocusListener(this);
        greenInput.setFont(font);
        blueInput = new TextField(3);
        blueInput.addFocusListener(this);
        blueInput.setFont(font);

        // Add the input boxes and labels to the component
        Label label;
        constr.gridheight = 1;
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.anchor = GridBagConstraints.SOUTH;
        constr.insets = new Insets(0, 0, 0, 0);
        constr.gridx = 1;
        constr.gridy = 0;
        label = new Label("Red:", Label.RIGHT);
        add(label, constr);
        constr.gridy = 1;
        constr.anchor = GridBagConstraints.CENTER;
        label = new Label("Green:", Label.RIGHT);
        add(label, constr);
        constr.gridy = 2;
        constr.anchor = GridBagConstraints.NORTH;
        label = new Label("Blue:", Label.RIGHT);
        add(label, constr);

        constr.gridheight = 1;
        constr.fill = GridBagConstraints.NONE;
        constr.anchor = GridBagConstraints.SOUTHWEST;
        constr.insets = new Insets(0, 0, 0, 10);
        constr.gridx = 2;
        constr.gridy = 0;
        add(redInput, constr);
        constr.gridy = 1;
        constr.anchor = GridBagConstraints.WEST;
        add(greenInput, constr);
        constr.gridy = 2;
        constr.anchor = GridBagConstraints.NORTHWEST;
        add(blueInput, constr);

        // Add color swatches
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 2, 4, 4));
        oldSwatch = new ColorSwatch(false);
        oldSwatch.setForeground(color);
        panel.add(oldSwatch);
        newSwatch = new ColorSwatch(false);
        newSwatch.setForeground(color);
        panel.add(newSwatch);

        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.anchor = GridBagConstraints.CENTER;
        constr.gridx = 1;
        constr.gridy = 3;
        constr.gridwidth = 2;
        add(panel, constr);

        // Add buttons
        panel = new Panel();
        panel.setLayout(new GridLayout(1, 2, 10, 2));
        Font buttonFont = new Font("SansSerif", Font.BOLD, 12);
        okButton = new Button("Ok");
        okButton.setFont(buttonFont);
        okButton.addActionListener(this);
        cancelButton = new Button("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setFont(buttonFont);
        panel.add(okButton);
        panel.add(cancelButton);

        constr.fill = GridBagConstraints.NONE;
        constr.anchor = GridBagConstraints.CENTER;
        constr.gridx = 0;
        constr.gridy = 4;
        constr.gridwidth = 3;
        add(panel, constr);
    }

    /**
     * Return the currently selected color
     **/
    public Color getColor() {
        return currentColor;
    }

    /**
     * Add a component to listen for color events. When the user presses
     * the "Change color" or "Cancel" button, an action event will be sent
     * to listeners with the action command string: CHANGE_ACTION or
     * CANCEL_ACTION. The getColor() method can then be used to get
     * the selected color;
     *
     * @param listener the object to send events to
     **/
    public void addActionListener(ActionListener listener) {
        colorListeners = AWTEventMulticaster.add(colorListeners, listener);
    }

    /**
     * Remove a color listener component
     *
     * @param listener the object to remove
     **/
    public void removeColorListener(ActionListener listener) {
        colorListeners = AWTEventMulticaster.remove(colorListeners, listener);
    }

    /**
     * Set the current color displayed in the color picker
     *
     * @param color the new color
     **/
    public void setColor(Color color) {
        changeColor(color);
        oldSwatch.setForeground(currentColor);
        newSwatch.setForeground(currentColor);
        colorCanvas.setColor(currentColor);
        repaint();
    }

    /**
     * Change the color shown in the dialog
     *
     * @param color the new color
     **/
    void changeColor(Color color) {
        currentColor = color;
        redInput.setText(String.valueOf(color.getRed()));
        greenInput.setText(String.valueOf(color.getGreen()));
        blueInput.setText(String.valueOf(color.getBlue()));
        newSwatch.setForeground(currentColor);
    }

    /**
     * Handle gained focus events. (Not used, but needed for FocusListener
     * interface)
     **/
    public void focusGained(FocusEvent event) {
    }

    /**
     * Handle lost focus events from the text input boxes. Updates
     * the color with the value from the box.
     *
     * @param event the focus event
     **/
    public void focusLost(FocusEvent event) {
        if (event.getSource() instanceof TextField) {
            int value;
            int red = currentColor.getRed();
            int green = currentColor.getGreen();
            int blue = currentColor.getBlue();
            try {
                value = Integer.parseInt(((TextField) event.getSource()).getText());
            } catch (Exception e) {
                value = -1;
            }
            if (event.getSource() == redInput) {
                if (value >= 0 && value < 256)
                    red = value;
                else
                    redInput.setText(String.valueOf(red));
            } else if (event.getSource() == greenInput) {
                if (value >= 0 && value < 256)
                    green = value;
                else
                    greenInput.setText(String.valueOf(green));
            } else if (event.getSource() == blueInput) {
                if (value >= 0 && value < 256)
                    blue = value;
                else
                    blueInput.setText(String.valueOf(blue));
            }
            currentColor = new Color(red, green, blue);
            newSwatch.setForeground(currentColor);
            colorCanvas.setColor(currentColor);
        }
    }

    /**
     * Handle action events from the color input boxes.
     *
     * @param event the action event
     **/
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == redInput ||
            event.getSource() == greenInput ||
            event.getSource() == blueInput) {
            int value;
            int red = currentColor.getRed();
            int green = currentColor.getGreen();
            int blue = currentColor.getBlue();
            try {
                value = Integer.parseInt((String) event.getActionCommand());
            } catch (Exception e) {
                value = -1;
            }
            if (event.getSource() == redInput) {
                if (value >= 0 && value < 256)
                    red = value;
                else
                    redInput.setText(String.valueOf(red));
            } else if (event.getSource() == greenInput) {
                if (value >= 0 && value < 256)
                    green = value;
                else
                    greenInput.setText(String.valueOf(green));
            } else if (event.getSource() == blueInput) {
                if (value >= 0 && value < 256)
                    blue = value;
                else
                    blueInput.setText(String.valueOf(blue));
            }
            currentColor = new Color(red, green, blue);
            newSwatch.setForeground(currentColor);
            colorCanvas.setColor(currentColor);
        } else if (event.getSource() == okButton) {
            // Send action event to all color listeners
            if (colorListeners != null) {
                ActionEvent new_event = new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED,
                    CHANGE_ACTION);
                colorListeners.actionPerformed(new_event);
            }
            setVisible(false);
        } else if (event.getSource() == cancelButton) {
            if (colorListeners != null) {
                ActionEvent new_event = new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED,
                    CANCEL_ACTION);
                colorListeners.actionPerformed(new_event);
            }
            currentColor = null;
            setVisible(false);
        }
    }

    /**
     * Sub-compnent of the ColorPicker class that displays a color
     * wheel in a canvas
     **/
    class ColorCanvas extends Canvas {
        // Radius of the color wheel image
        int radius = 50;
        // Diameter of the color wheel image
        int diameter = 100;
        // Width of the color value selector
        int colorWheelWidth = 0;
        // Width of the color value selector
        int valueBarWidth = 25;
        // Amount of space between the color wheel and value bar
        int spacing = 10;
        // Amount of space around the edges of the component
        int margins = 5;
        // The color wheel image
        Image colorWheel;
        // The color value bar image
        Image valueBar;
        // The mouse x and y coordinates in the color wheel
        int colorX, colorY;
        // X and Y offsets of color wheel and value bar positions
        int offsetX, offsetY;
        // Diameter of color selection circle
        int selectDiameter = 7;
        // Flag to redraw value bar
        boolean redrawValueBar;
        // Current color wheel color
        float[] currentHSBColor = new float[3];

        /**
         * Create a new ColorCanvas object
         *
         * @param r     the radius of the color wheel
         * @param color the initial color to display
         **/
        public ColorCanvas(int r, Color color) {
            radius = r;
            diameter = r * 2;
            colorWheelWidth = diameter + selectDiameter;
            setColor(color);
            enableEvents(AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
        }

        /**
         * Set the color shown in the color wheel
         *
         * @param color the color to show
         **/
        public void setColor(Color color) {
            Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(),
                currentHSBColor);
            Point p = getColorPosition(currentHSBColor);
            colorX = p.x;
            colorY = p.y;
            repaint();
        }

        public void update(Graphics g) {
            paint(g);
        }

        /**
         * Set the background color of the chart canvas
         *
         * @param c the new bakcground color
         **/
        public void setBackground(Color c) {
            super.setBackground(c);
            colorWheel = null;
            repaint();
        }

        /**
         * Create the color wheel in a new Image so it doesn't have to be
         * recalculated each time paint() is called.
         **/
        protected Image makeColorWheel() {
            int x, y;
            float[] hsb = new float[3];

            Image image = createImage(colorWheelWidth, colorWheelWidth);
            if (image == null)
                return null;

            Graphics g = image.getGraphics();

            g.setColor(getBackground());
            g.fillRect(0, 0, colorWheelWidth, colorWheelWidth);

            int offset = selectDiameter / 2;
            for (y = 0; y <= diameter; y++) {
                for (x = 0; x <= diameter; x++) {
                    if (getColorAt(hsb, x, y, true)) {
                        g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
                        g.drawLine(x + offset, y + offset, x + offset, y + offset);
                    }
                }
            }
            g.dispose();
            return image;
        }

        /**
         * Return the color in the color wheel at the given X and Y coordinates.
         * If the coordinates are outside the color wheel, the function returns
         * null. If the paint flag is true, the color returned will have a
         * brightness value of 1, otherwise the brightness value in the array
         * will be unchanged.
         *
         * @param hsbvalues an array to return the hue, saturation and brightness
         *                  values in
         * @param x         the x coordinate
         * @param y         the y coordinate
         * @param paint     whether to return the color to paint
         * @return true if a color value was found or false if the point is
         * outside the color wheel
         **/
        protected boolean getColorAt(float[] hsbvalues, int x, int y, boolean paint) {
            if (hsbvalues == null) return false;

            // The tangent function expects the point (0,0) to be in the center
            // of the circle, not the top left. Subtract off the radius from
            // the x and y coordinates to translate the origin.
            x = radius - x;
            y = y - radius;

            // Find the radius at this point
            int r = (int) Math.round(Math.sqrt(x * x + y * y));
            // Find the angle
            double angle = Math.PI - Math.atan2(x, y);
            // Make sure angle value is valid. Workaround for stupid virtual
            // machines that don't know how to do math (MSIE).
            if (angle < 0)
                angle = 0;
            if (angle > 2.0 * Math.PI)
                angle = 2.0 * Math.PI;
            // Translate angle value into a number between 0 and 1 to get
            // the hue value
            float hue = (float) (angle / (2 * Math.PI));

            if (paint && r == radius) {
                // If returning paint colors, also return the color of
                // the border around the wheel
                hsbvalues[0] = hue;
                if (angle > 3.0 * Math.PI / 4.0 && angle < 7.0 * Math.PI / 4.0) {
                    hsbvalues[1] = 0.2f;
                    hsbvalues[2] = 1.0f;
                } else {
                    hsbvalues[1] = 1.0f;
                    hsbvalues[2] = 0.8f;
                }
                return true;
            } else if (r < radius) {
                float sat = (float) r / (float) (radius - 1);
                hsbvalues[0] = hue;
                hsbvalues[1] = sat;
                if (paint)
                    hsbvalues[2] = 1.0f;
                return true;
            }
            return false;
        }

        /**
         * Return the X and Y coordinates of the given color
         **/
        protected Point getColorPosition(float[] hsbvals) {
            double angle = Math.PI / 2.0 - hsbvals[0] * 2.0 * Math.PI;
            int x = (int) Math.round(radius - radius * hsbvals[1] * Math.cos(angle));
            int y = (int) Math.round(radius - radius * hsbvals[1] * Math.sin(angle));
            return new Point(x, y);
        }

        /**
         * Create the color value bar in a new Image so it doesn't have to be
         * recalculated each time paint() is called.
         **/
        protected void drawValueBar(Image image) {
            if (image == null)
                return;

            Graphics g = image.getGraphics();

            g.setColor(getBackground());
            g.draw3DRect(0, 0, valueBarWidth, diameter, false);

            int height = diameter - 2;
            for (int y = 0; y <= height; y++) {
                Color color = Color.getHSBColor(currentHSBColor[0],
                    currentHSBColor[1],
                    (float) (height - y) / (float) height);
                g.setColor(color);
                g.drawLine(1, y + 1, valueBarWidth - 1, y + 1);
            }
            g.dispose();
        }

        /**
         * Draw the color wheel and value selection box
         *
         * @param g the graphics context
         **/
        public void paint(Graphics g) {
            if (colorWheel == null)
                colorWheel = makeColorWheel();
            if (valueBar == null) {
                valueBar = createImage(valueBarWidth + 3, diameter + 3);
                redrawValueBar = true;
            }
            if (redrawValueBar)
                drawValueBar(valueBar);

            // Draw the color wheel and value bar
            g.drawImage(colorWheel, offsetX, offsetY, this);

            int x = offsetX + colorWheelWidth + spacing;
            ;
            int y = offsetY + selectDiameter / 2;
            g.drawImage(valueBar, x, y, this);

            // Draw circle around current value in color wheel
            g.setColor(Color.black);
            g.drawOval(offsetX + colorX, offsetY + colorY,
                selectDiameter, selectDiameter);

            // Draw line at current color value
            g.setXORMode(Color.yellow);
            y += (diameter - diameter * currentHSBColor[2]);
            g.drawLine(x + 1, y + 1, x + valueBarWidth - 1, y + 1);
        }

        /**
         * Handle mouse click events
         *
         * @param event the mouse event
         **/
        public void processMouseEvent(MouseEvent event) {
            if (event.getID() == MouseEvent.MOUSE_PRESSED) {
                // Subtract off the offsets to make checking easier
                int mx = event.getX() - offsetX - selectDiameter / 2 - 1;
                int my = event.getY() - offsetY - selectDiameter / 2 - 1;

                // Check if outside color selection area
                if (mx < 0 || my < 0 || my > diameter)
                    return;
                if (mx > diameter + selectDiameter / 2 + spacing + valueBarWidth)
                    return;

                boolean new_color = false;
                if (mx < diameter) {
                    // In color wheel, set the hue and saturation
                    if (getColorAt(currentHSBColor, mx, my, false)) {
                        colorX = mx;
                        colorY = my;
                        redrawValueBar = true;
                        new_color = true;
                    }
                } else if (mx > diameter + selectDiameter / 2 + spacing && my < diameter - 2) {
                    // In value bar, set the brightness
                    currentHSBColor[2] = (float) (diameter - 2 - my) / (diameter - 2);
                    new_color = true;
                }
                if (new_color) {
                    Color color = Color.getHSBColor(currentHSBColor[0],
                        currentHSBColor[1],
                        currentHSBColor[2]);
                    // Call function in parent to update the color
                    ColorPicker.this.changeColor(color);
                    repaint();
                }
            } else
                super.processMouseEvent(event);
        }

        /**
         * Handle mouse move and drag events
         *
         * @param event the mouse event
         **/
        public void processMouseMotionEvent(MouseEvent event) {
            if (event.getID() == MouseEvent.MOUSE_DRAGGED) {
                // Subtract off the offsets to make checking easier
                int mx = event.getX() - offsetX - selectDiameter / 2 - 1;
                int my = event.getY() - offsetY - selectDiameter / 2 - 1;

                // Check if outside color selection area
                if (mx < 0 || my < 0 || my > diameter)
                    return;
                if (mx > diameter + selectDiameter / 2 + spacing + valueBarWidth)
                    return;

                boolean new_color = false;
                if (mx < diameter) {
                    // In color wheel, set the hue and saturation
                    if (getColorAt(currentHSBColor, mx, my, false)) {
                        colorX = mx;
                        colorY = my;
                        redrawValueBar = true;
                        new_color = true;
                    }
                } else if (mx > diameter + selectDiameter / 2 + spacing && my < diameter - 2) {
                    // In value bar, set the brightness
                    currentHSBColor[2] = (float) (diameter - 2 - my) / (diameter - 2);
                    new_color = true;
                }
                if (new_color) {
                    // Call function in parent to update the color
                    Color color = Color.getHSBColor(currentHSBColor[0],
                        currentHSBColor[1],
                        currentHSBColor[2]);
                    ColorPicker.this.changeColor(color);
                    repaint();
                }
            } else
                super.processMouseMotionEvent(event);
        }

        /**
         * Resize the canvas
         **/
        public void setSize(int width, int height) {
            super.setSize(width, height);
            // Compute the offsets to center the components in the canvas
            offsetX = margins + (width - colorWheelWidth - valueBarWidth -
                spacing - margins * 2) / 2;
            offsetY = margins + (height - colorWheelWidth - margins * 2) / 2;
        }

        /**
         * Resize the canvas
         **/
        public void setSize(Dimension d) {
            setSize(d.width, d.height);
        }

        /**
         * Reshape the canvas
         **/
        public void setBounds(int x, int y, int width, int height) {
            super.setBounds(x, y, width, height);
            // Compute the offsets to center the components in the canvas
            offsetX = margins + (width - colorWheelWidth - valueBarWidth -
                spacing - margins * 2) / 2;
            offsetY = margins + (height - colorWheelWidth - margins * 2) / 2;
        }

        /**
         * Reshape the canvas
         **/
        public void setBounds(Rectangle r) {
            setBounds(r.x, r.y, r.width, r.height);
        }

        /**
         * Return the minimum size of this component
         **/
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        /**
         * Return the preferred size of this component
         **/
        public Dimension getPreferredSize() {
            return new Dimension(colorWheelWidth + valueBarWidth + spacing + margins * 2,
                diameter + selectDiameter + margins * 2);
        }
    }
}