

package com.prasad.terminal;

import java.awt.*;
import java.awt.event.*;

public class PanelGraphicsChars extends Canvas {
    private static final int MAXCOLS = 400;
    private static final int MAXROWS = 60;

    ScrData scrData;
    int magnification;
    int origCharWidth;
    int origCharHeight;
    int[][] chars;

    public PanelGraphicsChars() {
        this(10, 14, 100, null);
    }

    public PanelGraphicsChars(int charWidth, int charHeight, int magn, int[][] scrChars) {
        origCharWidth = charWidth;
        origCharHeight = charHeight;
        magnification = magn;
        chars = scrChars;

        recomputeSizes = true;

        scrData = new ScrData(null);
        scrData.maxCharWidth = (charWidth * magn) / 100;
        scrData.charHeight = (charHeight * magn) / 100;
        computeSize();
    }

    private boolean recomputeSizes;
    private Dimension minDim;

    private void computeSize() {
        int w = (scrData.maxCharWidth + 2) * MAXCOLS;
        int h = (scrData.charHeight + 2) * MAXROWS;
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
        g.drawString("Graphics Chars:", 0, scrData.charHeight);
        for (int k = 0; k < 8; k++)
            g.drawString(Integer.toHexString(k), (22 + k) * (scrData.maxCharWidth + 2), scrData.charHeight);
        for (int k = 8; k < 16; k++)
            g.drawString(Integer.toHexString(k), (24 + k) * (scrData.maxCharWidth + 2), scrData.charHeight);

        char[] chs = new char[16];

        for (int i = 8; i < 16; i++) {
            for (int j = 0; j < 16; j++)
                chs[j] = (char) (i * 16 + j);
            String s = Integer.toHexString(i);
            String s2 = "   0x" + s + "0 - 0x" + s + "F:";
            g.drawString(s2, 3 * (scrData.maxCharWidth + 2), (i - 6) * (scrData.charHeight + 2));
            for (int k = 0; k < 8; k++)
                scrData.drawWinPTGraphicsCharXY(g, (22 + k) * (scrData.maxCharWidth + 2), (i - 7) * (scrData.charHeight + 2), chs[k], false);
            for (int k = 8; k < 16; k++)
                scrData.drawWinPTGraphicsCharXY(g, (24 + k) * (scrData.maxCharWidth + 2), (i - 7) * (scrData.charHeight + 2), chs[k], false);
        }

        int maxRows = (chars != null) ? chars.length : 0;
        int maxCols = (chars != null && chars[0] != null) ? chars[0].length : 0;
        int startY = 10 * (scrData.charHeight + 2);
        for (int i = 0; i < maxCols; i++) {
            g.drawString("" + i, 2 * (i + 3) * (scrData.maxCharWidth + 2), startY);
            for (int j = 0; j < maxRows; j++) {
                if (chars[j][i] >= 256) {
                    g.drawString(Integer.toHexString(chars[j][i] % 256),
                        2 * (i + 3) * (scrData.maxCharWidth + 2),
                        startY + (j + 1) * (scrData.charHeight + 2));
                }
            }
        }
    }

    public void setMagnification(int magn) {
        if (magnification == magn)
            return;
        magnification = magn;
        scrData.maxCharWidth = (origCharWidth * magn) / 100;
        scrData.charHeight = (origCharHeight * magn) / 100;

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
        final PanelGraphicsChars p2 = this;

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
        frame.pack();
        frame.setSize(scrData.maxCharWidth * MAXCOLS + 30, scrData.charHeight * MAXROWS + 40);
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
