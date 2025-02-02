package com.prasad.util;

import java.awt.*;
import java.awt.event.*;

/**
 * Class to show a solid color in a rectangle. (Normally, a Label or Button
 * component would be used, but Microsoft Internet Explorer always makes Label
 * backgrounds transparent.
 **/
public class ColorSwatch extends Canvas {
    Dimension prefSize = new Dimension(16, 16);
    boolean active = false;
    boolean pressed = false;
    ActionListener listeners;

    /**
     * Create a new color swatch
     *
     * @param active whether this component can be pushed or not
     **/
    public ColorSwatch(boolean active) {
        this.active = active;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    }

    /**
     * Set the foreground color
     **/
    @Override
    public void setForeground(Color c) {
        super.setForeground(c);
        repaint();
    }

    /**
     * Fill the component with the foreground color
     **/
    @Override
    public void paint(Graphics g) {
        g.setColor(getForeground());
        g.fillRect(0, 0, getSize().width, getSize().height);
        g.setColor(getBackground());
        if (active)
            g.draw3DRect(0, 0, getSize().width - 1, getSize().height - 1, !pressed);
        else
            g.draw3DRect(0, 0, getSize().width - 1, getSize().height - 1, false);
    }

    /**
     * Adds the specified action listener to receive action events
     * from the ColorSwatch component when the user clicks on it.
     *
     * @param listener the action listener
     */
    public void addActionListener(ActionListener listener) {
        listeners = AWTEventMulticaster.add(listeners, listener);
    }

    /**
     * Removes the specified action listener so it no longer receives
     * action events from this component.
     *
     * @param listener the action listener
     */
    public void removeActionListener(ActionListener listener) {
        listeners = AWTEventMulticaster.remove(listeners, listener);
    }

    /**
     * Handle mouse click events. Redraw the color button if
     * this component is active.
     **/
    @Override
    public void processMouseEvent(MouseEvent event) {
        if (event.getID() == MouseEvent.MOUSE_PRESSED) {
            if (active) {
                pressed = true;
                repaint();
            }
        } else if (event.getID() == MouseEvent.MOUSE_RELEASED) {
            if (active) {
                if (listeners != null) {
                    listeners.actionPerformed(new ActionEvent(this,
                        ActionEvent.ACTION_PERFORMED,
                        String.valueOf(getForeground().getRGB())));
                }
                pressed = false;
                repaint();
            }
        } else {
            super.processMouseEvent(event);
        }
    }

    /**
     * Set the size of this component
     *
     * @param width  the new width
     * @param height the new height
     **/
    @Override
    public void setSize(int width, int height) {
        prefSize.width = width;
        prefSize.height = height;
    }

    /**
     * Set the size of this component
     *
     * @param d the new dimensions
     **/
    @Override
    public void setSize(Dimension d) {
        setSize(d.width, d.height);
    }

    /**
     * Return the minimum size of this component
     **/
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    /**
     * Return the preferred size of this component
     **/
    @Override
    public Dimension getPreferredSize() {
        return prefSize;
    }
}