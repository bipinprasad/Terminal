

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;

public class PanelAttrs extends Canvas {
    private int MAXROWS;
    private int MAXCOLS;

    int magnification;
    int origCharWidth;
    int origCharHeight;
    int charWidth;
    int charHeight;
    int[][] attrs;

    public PanelAttrs(int chWidth, int chHeight, int[][] scrAttrs, int magn) {
        setAttrs(scrAttrs);
        origCharWidth = charWidth;
        origCharHeight = chHeight;
        magnification = magn;

        charWidth = (chWidth * magn) / 100;
        charHeight = (chHeight * magn) / 100;
        computeSize();
    }

    public void setAttrs(int[][] scrAttrs) {
        attrs = scrAttrs;
        MAXROWS = scrAttrs.length;
        MAXCOLS = scrAttrs[0].length;
        recomputeSizes = true;
    }

    private boolean recomputeSizes;
    private Dimension minDim;

    private void computeSize() {
        int w = charWidth * MAXCOLS + 100;
        int h = charHeight * (MAXROWS + 1);
        setSize(w, h);
        minDim = new Dimension(w, h);
        recomputeSizes = false;
    }

    public Dimension getPreferredSize() {
        return getMinimumSize();
    }

    public Dimension getMinimumSize() {
        if (recomputeSizes)
            computeSize();
        return minDim;
    }

    public void paint(Graphics g) {
        for (int j = 0; j < MAXCOLS; j++) {
            int xpos = j * charWidth + 100;
            g.drawString("" + (j % 10), xpos, charHeight);
        }
        for (int i = 0; i < MAXROWS; i++) {
            int ypos = (i + 2) * charHeight;
            g.drawString("" + i, 10, ypos);
        }
        for (int i = 0; i < MAXROWS; i++) {
            for (int j = 0; j < MAXCOLS; j++) {
                int attr = attrs[i][j];
                int xpos = j * charWidth + 100;
                int ypos = (i + 2) * charHeight;
                if (attr == 0)
                    g.drawString("-", xpos, ypos);
                else if (attr < 0)
                    g.drawString(".", xpos, ypos);
                else
                    g.drawString("" + attr, xpos, ypos);
            }
        }

    }

    public void setMagnification(int magn) {
        if (magnification == magn)
            return;
        magnification = magn;
        charWidth = (origCharWidth * magn) / 100;
        charHeight = (origCharHeight * magn) / 100;

        computeSize();
        repaint();
    }

    private Frame frame;

    public void showInFrame() {
        if (frame != null) {
            frame.setVisible(true);
            return;
        }

        frame = new Frame();
        Panel topPanel = new Panel();
        TextField fld = new TextField("100", 4);
        ScrollPane mainPanel = new ScrollPane();
        final PanelAttrs p2 = this;

        fld.addTextListener(new TextListener() {
            public void textValueChanged(TextEvent e) {
                String s = ((TextField) e.getSource()).getText();
                int magn = 100;
                try {
                    magn = Integer.parseInt(s);
                } catch (Exception e2) {
                    magn = 100;
                }
                p2.setMagnification(magn);
            }
        });
        topPanel.add(fld);
        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(p2);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ((Frame) e.getSource()).dispose();
            }
        });
        frame.setSize(getSize().width + 50, getSize().height + 70);
        frame.show();
    }

    protected void finalize() throws Throwable {
        if (frame != null) {
            frame.dispose();
            frame = null;
        }
        super.finalize();
    }

}
