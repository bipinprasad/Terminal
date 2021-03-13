

package com.prasad.terminal;

import java.awt.*;

public class ScrData {
    public static final int LAPM_FUNC_CODE_WAIT_TIME = 200; // milliseconds
    public static final int LAPM_FUNC_CODE_WAIT_TIMEOUT = 400; // milliseconds
    public static final int LAPM_MAX_TAB_LABELS = 25; // maximum number of tab labels
    public static final int LAPM_MAX_INPUT_FIELDS = 50; // maximum number of input fields
    public static final int LAPM_MAX_INPUT_FIELD_SIZE = 50; // maximum number of input fields

    TerminalSender terminalSender;
    PalTerm palTerm;
    int FUNCTION_BTNS_ROW = 25;

    //int     		WindowNo;
    int lastChar;
    int[][] scrChars;
    int[][] scrAttrs;    //	Attribute as received - attributes are switched
    //	on at specific locations on screen and are
    //	active till the next such position on the same line.
    //	pScrAtr is attribute expanded to each character
    int col;                        // Cursor x position
    int row;                        // Cursor y position

    int caretCol;                    // caret Cursor x position after drawing caret
    int caretRow;                    // caret Cursor y position after drawing caret

    int cSet;
    int csiType;
    int maxCols;                        // Number of characters
    int maxRows;                        // Number of lines
    int scrollLines;                // Number of lines used to compute scroll
    boolean first;
    boolean lineWrap;
    boolean localEdit;
    int national;
    int ocSet;
    int pSub;
    int[] param = new int[20];
    int[] sgr = new int[10];
    int state;

    boolean overType;
    boolean curStatus;                    // Cursor ON/OFF Status
    int attrib;

    int noMinimized;

    boolean bFirstDisp;
    Font terminalFont;
    Font btnFont;

    int aveCharWidth;
    int maxCharWidth;
    int charHeight;
    int charDescent;

    //	Caret Attributes
    int cursorOffset;
    int caretWidth;
    Image bitmapIns;
    Image bitmapOver;

    //	Cursor Attributes
    CurAtt cursorAttrib;
    //int[]			fontCharDistance	= new int[132];

    // temporary character string for a line
    char[] tmpStr = new char[150];

    // needed for painting graphics characters
    int[] xPointsTriangle = new int[3];
    int[] yPointsTriangle = new int[3];

    FunctionKeys functionKeys;

    public void init(int iRows, int iCols, Font font) {
        Font logFontTmp;

        this.cursorAttrib = new CurAtt();
        this.col = 0;
        this.row = 0;
        this.cSet = PanelTerminal.G0;
        this.maxCols = iCols;
        this.maxRows = iRows;
        this.first = false;
        this.lineWrap = true;
        this.localEdit = false;
        this.national = 0;
        this.state = PanelTerminal.TERM_START;
        this.overType = false;
        this.curStatus = true;                     // CUR_ON
        this.attrib = 0;
        this.bFirstDisp = true;

        int oldRowCnt = ((this.scrChars != null) ? this.scrChars.length : 0);
        int oldColCnt = ((this.scrChars != null && this.scrChars.length > 0) ? this.scrChars[0].length : 0);

        int[][] newScrChars = new int[iRows + 1][iCols];
        int[][] newScrAttrs = new int[iRows + 1][iCols];

        for (int i = 0; i < iRows + 1; i++) {
            for (int j = 0; j < iCols; j++) {
                if (oldRowCnt > i
                    && oldColCnt > j) {
                    newScrChars[i][j] = this.scrChars[i][j];
                    newScrAttrs[i][j] = this.scrAttrs[i][j];
                } else {
                    //newScrChars[i][j] =
                    //newScrAttrs[i][j] = -1;
                    newScrChars[i][j] = ' ';
                    newScrAttrs[i][j] = 0;
                }
            }
        }
        this.scrChars = newScrChars;
        this.scrAttrs = newScrAttrs;

        if (tmpStr == null
            || tmpStr.length < (iCols - 30))
            tmpStr = new char[iCols + 30];

        //this.pUniCodeCh[n] = ' ';
        //this.pCharAttr[n]  = '\0';

        FUNCTION_BTNS_ROW = iRows - 2;
        functionKeys = new FunctionKeys(iCols > 80);
        // This will also set AveCharWidth, MaxCharWidth, CharHeight
        setFont(font);
    }

    //	-----------------------------------------------------------------------------------------------
    boolean setFont(Font font) {
        if (font == null)
            return false;

        FontMetrics tm = Toolkit.getDefaultToolkit().getFontMetrics(font);

        int[] widths = tm.getWidths();
        int maxWidth = 0;
        int totalWidth = 0;
        for (int i = 0; i < widths.length; i++) {
            totalWidth += widths[i];
            if (maxWidth < widths[i])
                maxWidth = widths[i];
        }

        this.aveCharWidth = totalWidth / widths.length;
        this.maxCharWidth = maxWidth;
        this.charHeight = tm.getAscent() + tm.getDescent();
        this.charDescent = tm.getDescent();

        //setFontCharDistance(this.maxCharWidth);

        //FontMetrics fm	= Toolkit.getDefaultToolkit().getFontMetrics(font);
        //layoutCharWidth	= (double)fm.stringWidth(metricsString) / metricsString.length();
        //if (debug)
        //{
        //	System.out.println("debug:"+getClass().getName() + ".setFont():layoutCharWidth=[" + layoutCharWidth +
        //		"], textFont=[" + font + "]" );
        //}
        //scrData.maxCharWidth	= (int)(layoutCharWidth + 0.5f);
        //scrData.charHeight		= fm.getCharHeight;

        // set the button font - one size smaller and Bold

        terminalFont = font;
        btnFont = new Font(font.getName(), font.getStyle() | font.BOLD, font.getSize() - 2);
        return true;
    }

    //private void setFontCharDistance(int Width)
    //{
    //int		i;
    //for (i = 0 ; i < 132 ; i++)
    //	this.fontCharDistance[i] = Width;
    //}

    //public static Font createFontIndirect(LogicalFont f)
    //{
    //return new Font(f.getName(), f.getStyle(), f.getSize());
    //}
    public ScrData(TerminalSender s) {
        setTerminalSender(s);
        caretCol =
            caretRow = -1;
    }

    public void setTerminalSender(TerminalSender s) {
        terminalSender = s;
        palTerm = null;
        try {
            palTerm = ((PanelTerminal) s).getPalTerm();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
    }

    public static char G1Tab[] =
        {
            //    0    1    2    3    4    5    6    7
            //	  8    9    A    B    C    D    E    F

            // loaded font
            15, 15, 15, 15, 15, 15, 15, 15, // 0
            15, 15, 15, 15, 15, 15, 15, 15,
            15, 15, 15, 15, 15, 15, 15, 15, // 1
            15, 15, 15, 15, 15, 15, 15, 15,
            0x20, 15, 15, 15, 15, 15, 15, 15, // 2
            15, 15, 15, 15, 15, 15, 15, 15,
            0x80, 0xBA, 0xB3, 0x81, 0x82, 0x83, 0x84, 0x85, // 3
            0x86, 0x87, 0x88, 0x89, 0x8A, 0x8B, 0x8C, 0x8D,
            0x8E, 0x8F, 0x90, 0x91, 0x92, 0xD7, 0xCE, 0xD1, // 4
            0xCF, 0xCD, 0xD8, 0xC4, 0xC5, 0xC2, 0xC1, 0xD0,
            0xD2, 0xC8, 0xC3, 0xB4, 0xC9, 0xC6, 0xB5, 0xBC,    // 5
            0xBB, 0x93, 0x94, 0x95, 0x96, 0xC0, 0xD9, 0xDA,
            0xBF, 0xCC, 0xCB, 0xB9, 0xCA, 0x97, 0x98, 0x99,    // 6
            0x9A, 0x9B, 0x9C, 0x9D, 0x9E, 15, 15, 15,
            0xDB, 15, 15, 15, 15, 15, 15, 15,    // 7
            15, 15, 15, 15, 15, 15, 15, 15
        };

    public static char G2Tab[] =
        {
            //    0    1    2    3    4    5    6    7
            //	  8    9    A    B    C    D    E    F

            // loaded font
            15, 15, 15, 15, 15, 15, 15, 15, // 0
            15, 15, 15, 15, 15, 15, 15, 15,
            15, 15, 15, 15, 15, 15, 15, 15, // 1
            15, 15, 15, 15, 15, 15, 15, 15,
            0x20, 15, 15, 15, 0x11, 15, 0x04, 15, // 2
            0xA0, 0x11, 15, 15, 0xA1, 0xA2, 0x09, 0x0A,
            0x1E, 0x1F, 0x20, 0x21, 0x22, 0x23, 0x24, 0x25, // 3
            0x26, 0x27, 15, 0x07, 0x18, 0x19, 0x1B, 0x1A,
            15, 15, 15, 15, 15, 15, 15, 15, // 4
            15, 15, 15, 15, 15, 15, 15, 15,
            15, 15, 15, 15, 15, 15, 15, 15, // 5
            15, 15, 0xB3, 0xFA, 0x10, 0xFA, 15, 15,
            15, 15, 15, 0xF6, 0xF1, 0xF2, 0xF3, 0xF7, // 6
            0xA3, 15, 0x14, 0x15, 0x13, 0x5B, 0x5D, 0x28,
            0x29, 0xF8, 15, 0x2F, 15, 15, 15, 15, // 7
            15, 15, 0xAB, 0xAC, 0x9B, 15, 15, 15
        };
    public static char G3Tab[] =
        {
            //    0    1    2    3    4    5    6    7   8    9    A    B    C    D    E    F

            // loaded font
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // 0
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // 1
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // 2
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // 3
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // 4
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // 5
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // 6
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // 7
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // 8
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // 9
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // A
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // B
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // C
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // D
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, // E
            15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15  // F
        };

    /**
     * This function draws several grapics character in the WinPT font set.
     */
    public void drawWinPTGraphicsChars(Graphics g, int row, int col, char[] chs, int count, boolean lawsonMode, boolean unixMode, int cmdMode) {
        for (int i = 0; i < count; i++) {
            drawWinPTGraphicsChar(g, row, col + i, chs[i], lawsonMode, unixMode, cmdMode);
        }
    }

    public void drawWinPTGraphicsChar(Graphics g, int row, int col, int ch, boolean lawsonMode, boolean unixMode, int cmdMode) {
        int pixelTopLeftX = col * this.maxCharWidth;
        int pixelTopLeftY = row * this.charHeight;
        boolean drawCloseWindow = (ch == 0x96
            && altGo
            && lawsonMode
            && isTitleCorner(row, col));
        drawWinPTGraphicsCharXY(g, pixelTopLeftX, pixelTopLeftY, ch, drawCloseWindow);
    }

    public boolean isNearTitleCorner(int row, int col) {
        return (isTitleCorner(row, col) || isTitleCorner(row, col + 1));
    }

    /**
     * Determines if this row,col is corner of a title window. Title window is one
     * That has a header area and a display list.
     * <p>
     * A   95 8e 8e 8e 8e 8e 96    D
     * 80                80
     * B   97 cd cd cd cd cd 99    E
     * 80                80
     * 80                80
     * 80                80
     * 80                80
     * C   93 8e 8e 8e 8e 8e 94    F
     */
    public boolean isTitleCorner(int row, int col) {
        // points are col, row (scrChars is [row][col]) - is this confusing enough ?
        Point pointA = null;
        Point pointB = null;
        //Point pointC = null;
        //Point pointD = null;
        //Point pointE = null;
        //Point pointF = null;

        if ((scrChars[row][col] % 256) != 0x96
            || col < 2)
            return false;

        // find point A from D
        for (int x = col - 1; x >= 0; x--) {
            int ch = scrChars[row][x] % 256;
            if (ch == 0x95) {
                pointA = new Point(x, row);
                break;
            }
            if (ch != 0x8e)
                return false;
        }
        if (pointA == null)
            return false;

        // find point B  from A
        for (int y = row + 1; y < maxRows; y++) {
            int ch = scrChars[y][pointA.x] % 256;
            if (ch == 0x97) {
                pointB = new Point(pointA.x, y);
                break;
            }
            if (ch != 0x80)
                return false;
        }
        if (pointB == null)
            return false;

        // find point E from B
        //         B   97 cd cd cd cd cd 99    E
        if ((scrChars[pointB.y][col] % 256) != 0x99)
            return false;
        for (int x = pointB.x + 1; x < col; x++) {
            int ch = scrChars[pointB.y][x] % 256;
            if (ch != 0xcd)
                return false;
        }

        // is E to A OK
        for (int y = pointB.y - 1; y > row; y--) {
            int ch = scrChars[y][col] % 256;
            if (ch != 0x80)
                return false;
        }

        return true;
    }

    /**
     * This function draws a grapics character in the WinPT font set.
     * The calling method should get the graphics context, set the
     * background and draw a filled rectangle to create the background.
     * It should also set the foreground color for the character to be drawn in.
     *
     * @param lawsonMode: Is lawson mode active
     * @param unixMode    : Is unix mode active
     * @param cmdMode     : the command mode for the terminal emulator
     */
    public void drawWinPTGraphicsCharXY(Graphics g, int pixelTopLeftX, int pixelTopLeftY, int ch, boolean drawCloseWindow) {
        if (g == null
            || this.maxCharWidth < 3
            || this.charHeight < 3)
            return;

        //
        int pointAx, pointAy;
        int pointBx, pointBy;
        int pointCx, pointCy;
        int pointDx, pointDy;
        int pointEx, pointEy;
        int pointFx, pointFy;
        int pointGx, pointGy;
        int pointHx, pointHy;
        int pointIx, pointIy;

        //   A----B----C
        //   |         |
        //   |         |
        //   |         |
        //   |         |
        //   H    I    D
        //   |         |
        //   |         |
        //   |         |
        //   |         |
        //   G____F____E
        //
        pointAx = pointHx = pointGx = pixelTopLeftX;
        pointBx = pointIx = pointFx = pixelTopLeftX + this.maxCharWidth / 2;
        pointCx = pointDx = pointEx = pixelTopLeftX + this.maxCharWidth;

        pointAy = pointBy = pointCy = pixelTopLeftY;
        pointHy = pointIy = pointDy = pixelTopLeftY + this.charHeight / 2;
        pointGy = pointFy = pointEy = pixelTopLeftY + this.charHeight;

        if (ch > 255)
            ch = (ch % 256);
        switch (ch) {
            case 0x18:    // up arrow
                xPointsTriangle[0] = pointBx;
                yPointsTriangle[0] = pointBy + 1;
                xPointsTriangle[1] = pointGx + 1;
                yPointsTriangle[1] = pointGy - 1;
                xPointsTriangle[2] = pointEx - 1;
                yPointsTriangle[2] = pointEy - 1;
                g.fillPolygon(xPointsTriangle, yPointsTriangle, 3);
                break;
            case 0x19:    // down arrow
                xPointsTriangle[0] = pointFx;
                yPointsTriangle[0] = pointFy - 1;
                xPointsTriangle[1] = pointAx + 1;
                yPointsTriangle[1] = pointAy + 1;
                xPointsTriangle[2] = pointCx - 1;
                yPointsTriangle[2] = pointCy + 1;
                g.fillPolygon(xPointsTriangle, yPointsTriangle, 3);
                break;
            case 0x80:    // vertical line left of middle, bold
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                break;
            case 0x81: // vertical line left of middle, bold ; horizontal line from center to left, above center line
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointIx, pointIy - 1, pointDx, pointDy - 1);
                break;
            case 0x82:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointIx, pointIy - 1, pointHx, pointHy - 1);
                break;
            case 0x83:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                break;
            case 0x84:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointHx, pointHy - 2, pointDx, pointDy - 2);
                break;
            case 0x85:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointIx, pointIy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx, pointIy - 2, pointDx, pointDy - 2);
                break;
            case 0x86:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointHx, pointHy - 2, pointDx, pointDy - 2);
                break;
            case 0x87:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointHx, pointHy - 2, pointDx, pointDy - 2);
                g.drawLine(pointHx, pointHy - 1, pointIx, pointIy - 1);
                break;
            case 0x88:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointHx, pointHy - 2, pointDx, pointDy - 2);
                g.drawLine(pointDx, pointDy - 1, pointIx, pointIy - 1);
                break;
            case 0x89:
                g.drawLine(pointHx, pointHy - 2, pointDx, pointDy - 2);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx - 2, pointIy, pointFx - 2, pointFy);
                break;
            case 0x8A:
                g.drawLine(pointHx, pointHy - 2, pointDx, pointDy - 2);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx - 2, pointIy, pointBx - 2, pointBy);
                break;
            case 0x8B:
                g.drawLine(pointHx, pointHy - 2, pointDx, pointDy - 2);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointFx - 2, pointFy, pointBx - 2, pointBy);
                break;
            case 0x8C:
                g.drawLine(pointHx, pointHy - 2, pointDx, pointDy - 2);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx - 2, pointIy, pointFx - 2, pointFy);
                g.drawLine(pointIx - 1, pointIy, pointFx - 1, pointFy);
                break;
            case 0x8D:
                g.drawLine(pointHx, pointHy - 2, pointDx, pointDy - 2);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx - 2, pointIy, pointFx - 2, pointFy);
                g.drawLine(pointIx - 1, pointIy, pointFx - 1, pointFy);
                break;
            case 0x8E:
                g.drawLine(pointHx, pointHy - 2, pointDx, pointDy - 2);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                break;
            case 0x8F:
                g.drawLine(pointHx, pointHy - 2, pointDx, pointDy - 2);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx - 1, pointIy, pointBx - 1, pointBy);
                g.drawLine(pointFx - 2, pointFy, pointBx - 2, pointBy);
                break;

            case 0x90:
                g.drawLine(pointHx, pointHy - 2, pointDx, pointDy - 2);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx - 1, pointIy, pointFx - 1, pointFy);
                g.drawLine(pointFx - 2, pointFy, pointBx - 2, pointBy);
                break;
            case 0x91:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointBx - 3, pointBy, pointFx - 3, pointFy);
                g.drawLine(pointBx - 4, pointBy, pointFx - 4, pointFy);
                g.drawLine(pointIx, pointIy - 1, pointDx, pointDy - 1);
                break;
            case 0x92:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointBx - 3, pointBy, pointFx - 3, pointFy);
                g.drawLine(pointBx - 4, pointBy, pointFx - 4, pointFy);
                g.drawLine(pointIx, pointIy - 1, pointHx, pointHy - 1);
                break;
            case 0x93:
                g.drawLine(pointBx, pointBy, pointIx, pointIy - 1);
                g.drawLine(pointBx - 1, pointBy, pointIx - 1, pointIy - 1);
                g.drawLine(pointIx, pointIy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx, pointIy - 2, pointDx, pointDy - 2);
                break;
            case 0x94:
                g.drawLine(pointBx, pointBy, pointIx, pointIy - 1);
                g.drawLine(pointBx - 1, pointBy, pointIx - 1, pointIy - 1);
                g.drawLine(pointIx, pointIy - 1, pointHx, pointHy - 1);
                g.drawLine(pointIx, pointIy - 2, pointHx, pointHy - 2);
                break;
            case 0x95:
                //if (altGo)
                //{
                //	g.drawLine(pointAx+1, pointAy+1, pointGx+1, pointGy-1);
                //	g.drawLine(pointAx+1, pointAy+1, pointCx-1, pointCy+1);
                //	g.drawLine(pointCx-1, pointCy+1, pointEx-1, pointEy-1);
                //	g.drawLine(pointGx+1, pointGy-1, pointEx-1, pointEy-1);
                //	g.drawLine(pointAx+1, pointAy+1, pointEx-1, pointEy-1);
                //	g.drawLine(pointGx+1, pointGy-1, pointCx-1, pointCy+1);
                //}
                //else
            {
                g.drawLine(pointFx, pointFy, pointIx, pointIy - 1);
                g.drawLine(pointFx - 1, pointFy, pointIx - 1, pointIy - 1);
                g.drawLine(pointIx, pointIy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx, pointIy - 2, pointDx, pointDy - 2);
            }
            break;
            case 0x96:
                g.drawLine(pointFx, pointFy, pointIx, pointIy - 1);
                g.drawLine(pointFx - 1, pointFy, pointIx - 1, pointIy - 1);
                g.drawLine(pointIx, pointIy - 1, pointHx, pointHy - 1);
                g.drawLine(pointIx, pointIy - 2, pointHx, pointHy - 2);

                if (drawCloseWindow) {
                    // to do: Check to see if this is a title bar then do the following:
                    int xAdjust = -maxCharWidth / 2 - 2;
                    int yAdjust = charHeight / 2;
                    int heightAdjust = -charHeight / 2;
                    drawButton(g, new Rectangle(pointAx + xAdjust, pointAy + yAdjust, maxCharWidth, charHeight + heightAdjust), 1, null, null, null, true);
                    g.drawLine(pointAx + xAdjust + 2, pointAy + yAdjust + 2, pointEx + xAdjust - 2, pointEy + yAdjust + heightAdjust - 2);
                    g.drawLine(pointCx + xAdjust - 2, pointCy + yAdjust + 2, pointGx + xAdjust + 2, pointGy + yAdjust + heightAdjust - 2);
                }
                break;
            case 0x97:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointIx, pointIy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx, pointIy + 1, pointDx, pointDy + 1);
                break;
            case 0x98:
                g.drawLine(pointHx, pointHy - 2, pointDx, pointDy - 2);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx - 3, pointIy, pointFx - 3, pointFy);
                g.drawLine(pointIx - 1, pointIy, pointFx - 1, pointFy);
                break;
            case 0x99:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointIx, pointIy - 1, pointHx, pointHy - 1);
                g.drawLine(pointIx, pointIy + 1, pointHx, pointHy + 1);
                break;
            case 0x9A:
                g.drawLine(pointHx, pointHy - 2, pointDx, pointDy - 2);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx - 2, pointIy, pointBx - 2, pointBy);
                g.drawLine(pointIx, pointIy, pointBx, pointBy);
                break;
            case 0x9B:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointBx - 3, pointBy, pointFx - 3, pointFy);
                g.drawLine(pointBx - 4, pointBy, pointFx - 4, pointFy);
                g.drawLine(pointIx, pointIy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx, pointIy - 2, pointDx, pointDy - 2);
                break;
            case 0x9C:
                g.drawLine(pointHx, pointHy - 3, pointDx, pointDy - 3);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx - 2, pointIy, pointFx - 2, pointFy);
                g.drawLine(pointIx - 1, pointIy, pointFx - 1, pointFy);
                break;
            case 0x9D:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointBx - 3, pointBy, pointFx - 3, pointFy);
                g.drawLine(pointBx - 4, pointBy, pointFx - 4, pointFy);
                g.drawLine(pointIx, pointIy - 1, pointHx, pointHy - 1);
                g.drawLine(pointIx, pointIy - 2, pointHx, pointHy - 2);
                break;
            case 0x9E:
                g.drawLine(pointHx, pointHy - 3, pointDx, pointDy - 3);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx - 2, pointIy - 3, pointBx - 2, pointBy);
                g.drawLine(pointIx - 1, pointIy - 3, pointBx - 1, pointBy);
                break;

            case 0xA0:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointHx, pointHy, pointIx, pointIy);
                xPointsTriangle[0] = pointIx;
                yPointsTriangle[0] = pointIy;
                xPointsTriangle[1] = pointIx - 4;
                yPointsTriangle[1] = pointIy - 3;
                xPointsTriangle[2] = pointIx - 4;
                yPointsTriangle[2] = pointIy + 3;
                g.fillPolygon(xPointsTriangle, yPointsTriangle, 3);
                break;
            case 0xA1:
                g.drawLine(pointBx, pointBy, pointIx, pointIy);
                g.drawLine(pointIx, pointIy, pointGx, pointGy);
                xPointsTriangle[0] = pointGx;
                yPointsTriangle[0] = pointGy;
                xPointsTriangle[1] = pointGx;
                yPointsTriangle[1] = pointGy - 3;
                xPointsTriangle[2] = pointGx + 3;
                yPointsTriangle[2] = pointGy;
                g.fillPolygon(xPointsTriangle, yPointsTriangle, 3);
                break;
            case 0xA2:
                g.drawLine(pointBx, pointBy, pointIx, pointIy);
                g.drawLine(pointIx, pointIy, pointEx, pointEy);
                xPointsTriangle[0] = pointEx;
                yPointsTriangle[0] = pointEy;
                xPointsTriangle[1] = pointEx;
                yPointsTriangle[1] = pointEy - 3;
                xPointsTriangle[2] = pointEx - 3;
                yPointsTriangle[2] = pointEy;
                g.fillPolygon(xPointsTriangle, yPointsTriangle, 3);
                break;
            case 0xA3:
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointHx, pointHy + 1, pointDx, pointDy + 1);
                g.drawLine(pointGx, pointGy - 3, pointCx, pointCy + 3);
                break;
            case 0xA6:
                g.drawLine(pointBx - 1, pointBy, pointIx - 1, pointIy - 1);
                g.drawLine(pointFx - 1, pointFy, pointIx - 1, pointIy + 1);
                break;

            case 0xB3:
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy - 1);
                break;
            case 0xB4:
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointIx, pointIy - 1, pointGx, pointGy - 1);
                break;
            case 0xB5:
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointIx, pointIy - 1, pointHx, pointHy - 1);
                g.drawLine(pointIx, pointIy + 1, pointHx, pointHy + 1);
                break;
            case 0xB6:
                g.drawLine(pointBx + 1, pointBy, pointFx + 1, pointFy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointIx, pointIy - 1, pointHx, pointHy - 1);
                g.drawLine(pointIx, pointIy + 1, pointHx, pointHy + 1);
                break;
            case 0xB9:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 2, pointBy, pointIx - 2, pointIy - 1);
                g.drawLine(pointIx - 2, pointIy + 1, pointFx - 2, pointFy);
                g.drawLine(pointIx - 2, pointIy - 1, pointHx - 2, pointHy - 1);
                g.drawLine(pointIx - 2, pointIy + 1, pointHx - 2, pointHy + 1);
                break;
            case 0xBA:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 2, pointBy, pointFx - 2, pointFy);
                break;
            case 0xBB:
                g.drawLine(pointFx, pointFy, pointIx, pointIy);
                g.drawLine(pointFx - 2, pointFy, pointIx - 2, pointIy + 2);
                g.drawLine(pointIx, pointIy, pointHx, pointHy);
                g.drawLine(pointIx - 2, pointIy + 2, pointHx, pointHy + 2);
                break;
            case 0xBC:
                g.drawLine(pointBx, pointBy, pointIx, pointIy + 2);
                g.drawLine(pointBx - 2, pointBy, pointIx - 2, pointIy);
                g.drawLine(pointIx - 2, pointIy, pointHx, pointHy);
                g.drawLine(pointIx, pointIy + 2, pointHx, pointHy + 2);
                break;
            case 0xBF:
                g.drawLine(pointFx, pointFy, pointIx, pointIy);
                g.drawLine(pointIx, pointIy, pointHx, pointHy);
                break;

            case 0xC0:
                g.drawLine(pointBx, pointBy, pointIx, pointIy);
                g.drawLine(pointIx, pointIy, pointDx, pointDy);
                break;
            case 0xC1:
                g.drawLine(pointBx - 1, pointBy, pointIx - 1, pointIy);
                g.drawLine(pointHx, pointHy, pointDx, pointDy);
                break;
            case 0xC2:
                g.drawLine(pointFx - 1, pointFy, pointIx - 1, pointIy);
                g.drawLine(pointHx, pointHy, pointDx, pointDy);
                break;
            case 0xC3:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointIx, pointIy, pointDx, pointDy);
                break;
            case 0xC4:
                g.drawLine(pointHx, pointHy, pointDx, pointDy);
                break;
            case 0xC5:
                g.drawLine(pointHx, pointHy, pointDx, pointDy);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                break;
            case 0xC6:
                g.drawLine(pointIx, pointIy - 1, pointDx, pointDy - 1);
                g.drawLine(pointIx, pointIy + 1, pointDx, pointDy + 1);
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                break;
            case 0xC7:
                g.drawLine(pointBx, pointBy, pointFx, pointFy);
                g.drawLine(pointBx - 2, pointBy, pointFx - 2, pointFy);
                g.drawLine(pointIx, pointIy, pointDx, pointDy);
                break;
            case 0xC8:
                g.drawLine(pointBx, pointBy, pointIx, pointIy);
                g.drawLine(pointIx, pointIy, pointDx, pointDy);
                g.drawLine(pointBx + 2, pointBy, pointIx + 2, pointIy - 2);
                g.drawLine(pointIx + 2, pointIy - 2, pointDx, pointDy - 2);
                break;
            case 0xC9:
                g.drawLine(pointFx, pointFy, pointIx, pointIy);
                g.drawLine(pointIx, pointIy, pointDx, pointDy);
                g.drawLine(pointFx + 2, pointFy, pointIx + 2, pointIy + 2);
                g.drawLine(pointIx + 2, pointIy + 2, pointDx, pointDy + 2);
                break;
            case 0xCA:
                g.drawLine(pointBx, pointBy, pointIx, pointIy - 1);
                g.drawLine(pointIx, pointIy - 1, pointDx, pointDy - 1);
                g.drawLine(pointBx - 2, pointBy, pointIx - 2, pointIy - 1);
                g.drawLine(pointIx - 2, pointIy - 1, pointHx, pointHy - 1);
                g.drawLine(pointHx, pointHy + 1, pointDx, pointDy + 1);
                break;
            case 0xCB:
                g.drawLine(pointFx, pointFy, pointIx, pointIy + 1);
                g.drawLine(pointIx, pointIy + 1, pointDx, pointDy + 1);
                g.drawLine(pointFx - 2, pointFy, pointIx - 2, pointIy + 1);
                g.drawLine(pointIx - 2, pointIy + 1, pointHx, pointHy + 1);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                break;
            case 0xCC:
                g.drawLine(pointFx, pointFy, pointIx, pointIy + 1);
                g.drawLine(pointIx, pointIy + 1, pointDx, pointDy + 1);
                g.drawLine(pointBx, pointBy, pointIx, pointIy - 1);
                g.drawLine(pointIx, pointIy - 1, pointDx, pointDy - 1);
                g.drawLine(pointBx - 2, pointBy, pointFx - 2, pointFy);
                break;
            case 0xCD:
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointHx, pointHy + 1, pointDx, pointDy + 1);
                break;
            case 0xCE:
                g.drawLine(pointBx, pointBy, pointIx, pointIy - 1);
                g.drawLine(pointIx, pointIy - 1, pointDx, pointDy - 1);
                g.drawLine(pointBx - 2, pointBy, pointIx - 2, pointIy - 1);
                g.drawLine(pointIx - 2, pointIy - 1, pointHx, pointHy - 1);
                g.drawLine(pointFx, pointFy, pointIx, pointIy + 1);
                g.drawLine(pointIx, pointIy + 1, pointDx, pointDy + 1);
                g.drawLine(pointFx - 2, pointFy, pointIx - 2, pointIy + 1);
                g.drawLine(pointIx - 2, pointIy + 1, pointHx, pointHy + 1);
                break;
            case 0xCF:
                g.drawLine(pointBx - 2, pointBy, pointIx - 2, pointIy - 1);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointHx, pointHy + 1, pointDx, pointDy + 1);
                break;

            case 0xD0:
                g.drawLine(pointHx, pointHy, pointDx, pointDy);
                g.drawLine(pointIx, pointIy, pointBx, pointBy);
                g.drawLine(pointIx - 2, pointIy, pointBx - 2, pointBy);
                break;
            case 0xD1:
                g.drawLine(pointFx - 2, pointFy, pointIx - 2, pointIy + 1);
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointHx, pointHy + 1, pointDx, pointDy + 1);
                break;
            case 0xD2:
                g.drawLine(pointHx, pointHy, pointDx, pointDy);
                g.drawLine(pointFx - 2, pointFy, pointIx - 2, pointIy);
                g.drawLine(pointFx, pointFy, pointIx, pointIy);
                break;
            case 0xD7:
                g.drawLine(pointBx - 1, pointBy, pointFx - 1, pointFy);
                g.drawLine(pointBx + 1, pointBy, pointFx + 1, pointFy);
                g.drawLine(pointHx, pointHy, pointDx, pointDy);
                break;
            case 0xD8:
                g.drawLine(pointHx, pointHy - 1, pointDx, pointDy - 1);
                g.drawLine(pointHx, pointHy + 1, pointDx, pointDy + 1);
                g.drawLine(pointBx - 2, pointBy, pointFx - 2, pointFy);
                break;
            case 0xD9:
                g.drawLine(pointBx - 2, pointBy, pointIx - 2, pointIy);
                g.drawLine(pointIx - 2, pointIy, pointHx, pointHy);
                break;
            case 0xDA:
                g.drawLine(pointFx, pointFy, pointIx, pointIy);
                g.drawLine(pointIx, pointIy, pointDx, pointDy);
                break;
            case 0xDB:
                g.fillRect(pointAx + 1, pointAy + 1, this.maxCharWidth - 2, this.charHeight - 2);
                break;

            case 0xF2:
                g.drawLine(pointGx + 1, pointGy - 1, pointEx - 1, pointEy - 1); // _
                g.drawLine(pointAx + 2, pointAy + 2, pointDx - 1, pointDy - 1); // \
                g.drawLine(pointGx + 2, pointGy - 4, pointDx - 1, pointDy - 1); // /
                break;
            case 0xF3:
                g.drawLine(pointGx + 1, pointGy - 1, pointEx - 1, pointEy - 1);// _
                g.drawLine(pointCx - 2, pointCy + 2, pointHx + 1, pointHy - 1);// /
                g.drawLine(pointEx - 2, pointEy - 4, pointHx + 1, pointHy - 1);// \
                break;
            case 0xFA:
                g.fillRect(pointIx - 1, pointIy - 1, 2, 2);
                break;
        }
    }

    boolean hasFunctionKeys() {
        if (functionKeys != null)
            return functionKeys.hasFunctionKeys(scrAttrs[FUNCTION_BTNS_ROW]);
        return false;
    }

    boolean isFunctionKeyActive(int iCol) {
        if (functionKeys != null)
            return functionKeys.isFunctionKeyActive(iCol, scrChars[FUNCTION_BTNS_ROW], scrAttrs[FUNCTION_BTNS_ROW]);
        return false;
    }

    int getFunctionKey(int iCol) {
        if (functionKeys != null)
            return functionKeys.getFunctionKey(iCol);
        return 0;
    }

    void drawFunctionBtns(Graphics g) {
        int chars[] = scrChars[FUNCTION_BTNS_ROW];
        for (int i = 0; i < 10; i++) {
            drawButton(g, functionKeys.getFunctionKeyBounds(i, FUNCTION_BTNS_ROW * charHeight), 3, null, null, functionKeys.getFunctionKeyLabel(i, chars), true);
        }
    }

    public void drawButton(Graphics g, Rectangle rect, int textOffset, Color back, Color fore, String text, boolean raised) {
        g.setFont(btnFont);
        if (back == null)
            back = Color.lightGray;
        g.setColor(back);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        g.fill3DRect(rect.x + 1, rect.y + 1, rect.width - 1, rect.height - 1, raised);
        //g.fill3DRect(rect.x,rect.y,rect.width,rect.height,true);
        if (fore == null)
            fore = Color.black;
        g.setColor(fore);
        if (text != null)
            g.drawString(text, rect.x + textOffset, rect.y + rect.height - 3);
        g.setFont(terminalFont);
    }

    Point getSmartMouseMovement(int iClickedRow, int iClickedCol, boolean lawsonMode, boolean unixMode, int cmdMode) {
        // if there are no graphics characters before caretCol on caretRow, then this is a plain
        // movement
        int[] caretRowChars = scrChars[caretRow];
        int[] caretRowAttrs = scrAttrs[caretRow];
        int blankRows = 0;    // number of rows that are blank. This number is subtracted from
        // the smart mouse movement in lawsonMode for certain applications.

        switch (cmdMode) {
            case PanelTerminal.CMDMODE_NONE:
                break;
            // Special Modes for Lawson Software
            case PanelTerminal.CMDMODE_LAWSON_LAPM:
                doSmartMouseMovementForLapm(iClickedRow, iClickedCol);
                return null;
            case PanelTerminal.CMDMODE_LAWSON_LANGDEF:
            case PanelTerminal.CMDMODE_LAWSON_LAUA:
            case PanelTerminal.CMDMODE_LAWSON_DBDEF:
                for (int i = caretCol - 1; i >= 0; i--) {
                    if (caretRowChars[i] == 256 + 0x80)    // graphics char
                    {
                        int startBoxCol = i;
                        // if this is a selection box, then determine the column spacing for each choice
                        for (int j = i + 1; j < maxCols; j++) {
                            if (caretRowChars[j] == 256 + 0x80) {
                                int endBoxCol = j;
                                int startBoxRow = -1;
                                for (int k = caretRow; k > 0; k--) {
                                    if (scrChars[k][caretCol] > 256)    // graphics char
                                    {
                                        startBoxRow = k + 1;
                                        break;
                                    }
                                }
                                if (startBoxRow < 0)
                                    return null;

                                int endBoxRow = -1;
                                for (int k = caretRow; k < FUNCTION_BTNS_ROW; k++) {
                                    if (scrChars[k][caretCol] >= 256)    // graphics char
                                    {
                                        endBoxRow = k - 1;
                                        break;
                                    }
                                }
                                if (endBoxRow < 0)
                                    return null;
                                if (iClickedRow >= startBoxRow
                                    && iClickedRow <= endBoxRow
                                    && iClickedCol >= startBoxCol
                                    && iClickedCol <= endBoxCol) {
                                    int choiceItemStartCol = -1;
                                    int choiceItemEndCol = -1;
                                    for (int k = caretCol; k >= startBoxCol; k--) {
                                        if (caretRowAttrs[k] != 0) {
                                            if (choiceItemEndCol < 0)
                                                choiceItemEndCol = k;
                                        } else {
                                            if (choiceItemEndCol >= 0) {
                                                choiceItemStartCol = k + 1;
                                                break;
                                            }
                                        }
                                    }
                                    if (choiceItemStartCol < 0)
                                        return null;
                                    else {
                                        int choiceItemSize = caretCol - choiceItemStartCol;
                                        int currentColumn = (caretCol - 1 - (startBoxCol + 2)) / choiceItemSize;
                                        int clickedColumn = (iClickedCol - (startBoxCol + 2)) / choiceItemSize;
                                        //if (debug)
                                        //{
                                        //	System.out.println("Smart Mouse Click: caretRow[" + caretRow + "] caretCol [" + caretCol + "]");
                                        //	System.out.println("\t iClickedRow[" + iClickedRow + "] iClickedCol [" + iClickedCol + "]");
                                        //	System.out.println("\t choiceItemStartCol [" + choiceItemStartCol + "] choiceItemSize [" + choiceItemSize + "]");
                                        //	System.out.println("\t currentColumn [" + currentColumn + "] clickedColumn [" + clickedColumn + "]");
                                        //}
                                        int rowAdjustment = 0;
                                        if (currentColumn == clickedColumn) {
                                            // reduce the iClicked row by the number of blank rows

                                            int loopStart, loopEnd;
                                            if (iClickedRow > caretRow) {
                                                loopStart = caretRow;
                                                loopEnd = iClickedRow;
                                            } else {
                                                loopStart = iClickedRow;
                                                loopEnd = caretRow;
                                            }
                                            for (int y = loopStart + 1; y <= loopEnd; y++) {
                                                boolean lineIsBlank = true;
                                                for (int x = startBoxCol + 1; x < endBoxCol; x++) {
                                                    if (scrChars[y][x] != ' ') {
                                                        lineIsBlank = false;
                                                        break;
                                                    }
                                                }
                                                if (lineIsBlank) {
                                                    if (iClickedRow > caretRow)
                                                        rowAdjustment++;
                                                    else
                                                        rowAdjustment--;
                                                }
                                            }
                                        }
                                        return new Point(clickedColumn - currentColumn, iClickedRow - caretRow - rowAdjustment);
                                    }
                                } else
                                    return null;
                            }
                        }
                        return null;
                    }
                }
                break;

            case PanelTerminal.CMDMODE_LAWSON_LX:
                return new Point(iClickedCol - caretCol, iClickedRow - caretRow);

            case PanelTerminal.CMDMODE_LAWSON_RESERVED_3:
                break;

            case PanelTerminal.CMDMODE_LAWSON_RESERVED_4:
                break;

            case PanelTerminal.CMDMODE_LAWSON_RESERVED_5:
                break;

            case PanelTerminal.CMDMODE_LAWSON_RESERVED_6:
                break;

            case PanelTerminal.CMDMODE_LAWSON_RESERVED_7:
                break;

            // General Unix Modes.
            case PanelTerminal.CMDMODE_UNIX_CMD:
                break;

            case PanelTerminal.CMDMODE_UNIX_VI:
                return new Point(iClickedCol - caretCol, iClickedRow - caretRow);

            case PanelTerminal.CMDMODE_UNIX_EMACS:
                return new Point(iClickedCol - caretCol, iClickedRow - caretRow);

        }
        return new Point(iClickedCol - caretCol, iClickedRow - caretRow);
    }

    /**
     * Send TAB keys (or SHIFT-TAB) until the clickedRow and caretRow match
     * Then send sufficient RIGHT/LEFT arrows for a match or overshoot
     * or caretCol as compared to clickedRow.
     * <p>
     * This method uses trial and error to position the
     */
    private void doSmartMouseMovementForLapm(int iClickedRow, int iClickedCol) {
        if (terminalSender == null) {
            System.out.println("ERROR: ScrData.doSmartMouseMovementForLapm() - terminalSender is null");
            return;
        }
        if (iClickedRow == 0
            && iClickedCol <= 6) {
            if (caretRow != 0
                || caretCol > 6) {
                // send HOME - to position to function code field (if not already there)
                // then send SHIFT-TAB
                if (caretRow != 0
                    && caretCol != 9)
                    terminalSender.sendKey(TerminalSender.VK_HOME_INDEX, false, false);
                terminalSender.sendKey(TerminalSender.VK_TAB_INDEX, true, false);
            }
            return;
        }
        if (iClickedRow == 0
            && iClickedCol <= 9) {
            // send HOME - to position to function code field (if not already there)
            // then send SHIFT-TAB
            if (caretRow != 0
                || caretCol != 9)
                terminalSender.sendKey(TerminalSender.VK_HOME_INDEX, false, false);
            return;
        }
        // Try to position to the clicked Point by using tab (or shift tab). If the
        // clicked position can be highlighted then we are done.
        // Otherwise, reverse the direction and try to use the arrow (left/right)
        // to position to the field.

        LapmPositionOnScreenThread t = new LapmPositionOnScreenThread(iClickedRow, iClickedCol,
            palTerm.smartMouse_FirstChar_MaxWaitTime,
            palTerm.smartMouse_InterChar_WaitTime);
        t.start();
    }

    class LapmPositionOnScreenThread extends Thread implements TerminalVputListener {
        int waitTime;
        int targetRow;
        int targetCol;
        boolean positionWithinField;
        int iCurrFldRow;
        int iCurrFldStartCol;
        int iCurrFldEndCol;
        Point tabLabelRows;
        String tabLabel;
        boolean receivedChar;
        int smartMouse_FirstChar_MaxWaitTime = 200;
        int smartMouse_InterChar_WaitTime = 5;

        LapmPositionOnScreenThread(int iTargetRow, int iTargetCol, int firstCharWait, int interCharWait) {
            targetRow = iTargetRow;
            targetCol = iTargetCol;
            positionWithinField = false;

            findCurrentField();
            // if the clicking was in the currentfield, then position within field
            // using left/right arrow.
            if (targetRow == caretRow
                && targetCol >= iCurrFldStartCol
                && targetCol <= iCurrFldEndCol) {
                positionWithinField = true;
            }
            tabLabelRows = getLapmTabLabelRows(targetRow, targetCol);
            if (terminalSender != null)
                terminalSender.addTerminalVputListener(this);

            if (firstCharWait < 1)
                firstCharWait = 1;
            if (interCharWait < 1)
                interCharWait = 1;
            smartMouse_FirstChar_MaxWaitTime = firstCharWait;
            smartMouse_InterChar_WaitTime = interCharWait;
        }

        @Override
        public void vput(Graphics g, int ch) {
            waitTime = 0;
            receivedChar = true;
        }

        /**
         * Get the tab label under the row and col
         */
        public String getLapmTabLabel(int iRow, int iCol) {
            if (iRow >= maxRows - 4)
                return null;    // too low for a tab label
            if (iRow < 1)
                return null;    // too high for a tab label

            int tabLabelStartCol;
            int tabLabelEndCol;

            for (tabLabelStartCol = iCol; tabLabelStartCol > 0; tabLabelStartCol--) {
                if (scrChars[iRow][tabLabelStartCol - 1] >= 256)
                    break;
            }
            for (tabLabelEndCol = iCol; tabLabelEndCol < maxCols - 1; tabLabelEndCol++) {
                if (scrChars[iRow][tabLabelEndCol + 1] >= 256)
                    break;
            }
            if (tabLabelEndCol <= tabLabelStartCol)
                return null;

            StringBuffer sb = new StringBuffer();
            for (int i = tabLabelStartCol; i <= tabLabelEndCol; i++)
                sb.append((char) scrChars[iRow][i]);

            return sb.toString().trim();
        }

        /**
         * Examine the row and column to determine if this is tab label.
         * If this is a tab label, then return a point with starting and
         * ending row numbers for this tab. If this is not a tab label
         * then return null.
         * <p>
         * Find the tab label text and its starting and ending columns.
         * If this is the currently selected tab label, then the
         * row above the label will have 0x8E and below will be spaces.
         * Otherwise, the top will have
         */
        public Point getLapmTabLabelRows(int iRow, int iCol) {
            if (iRow >= maxRows - 4)
                return null;    // too low for a tab label
            if (iRow < 1)
                return null;    // too high for a tab label

            Point retVal;

            int tabLabelStartCol;
            int tabLabelEndCol;

            for (tabLabelStartCol = iCol; tabLabelStartCol > 0; tabLabelStartCol--) {
                if (scrChars[iRow][tabLabelStartCol - 1] >= 256)
                    break;
            }
            for (tabLabelEndCol = iCol; tabLabelEndCol < maxCols - 1; tabLabelEndCol++) {
                if (scrChars[iRow][tabLabelEndCol + 1] >= 256)
                    break;
            }
            if (tabLabelEndCol <= tabLabelStartCol)
                return null;

            StringBuffer sb = new StringBuffer();
            for (int i = tabLabelStartCol; i <= tabLabelEndCol; i++)
                sb.append((char) scrChars[iRow][i]);

            tabLabel = sb.toString().trim(); // store this info for later match

            // character above should be 8e or c4 or c2 or 95/96
            for (int i = tabLabelStartCol; i <= tabLabelEndCol; i++) {
                if (iRow == 0)
                    return null;
                int ch = scrChars[iRow - 1][i] % 256;
                if (ch != 0xC4
                    && ch != 0xC2
                    && ch != 0x95
                    && ch != 0x96
                    && ch != 0x8E)
                    return null;
            }
            // character below should be 8e or c4 or c2 or 95 or 96 or ' '
            for (int i = tabLabelStartCol; i <= tabLabelEndCol; i++) {
                int ch = scrChars[iRow + 1][i] % 256;
                if (ch != 0xC4
                    && ch != 0xC2
                    && ch != 0x95
                    && ch != 0x96
                    && ch != 0x8E
                    && ch != ' ')
                    return null;
            }
            // now we are definitely in a tab label
            // find the Row range

            // is there a tab row above this label
            for (int i = tabLabelStartCol; i <= tabLabelEndCol; i++) {
                if (iRow < 3)
                    break;
                int ch = scrChars[iRow - 3][i] % 256;
                if (ch != 0xC4
                    && ch != 0xC2
                    && ch != 0x95
                    && ch != 0x96
                    && ch != 0x8E)
                    break;
                if (i == tabLabelEndCol) {
                    // there is a tab row before
                    return new Point(iRow - 2, iRow);
                }
            }
            // is there a tab row below this label
            for (int i = tabLabelStartCol; i <= tabLabelEndCol; i++) {
                int ch = scrChars[iRow + 3][i] % 256;
                if (ch != 0x8E
                    && ch != 0x93
                    && ch != 0x94
                    && ch != ' ') {
                    // there is no tab row below
                    return new Point(iRow, iRow);
                }
                if (i == tabLabelEndCol) {
                    // there is a tab row after
                    return new Point(iRow, iRow + 2);
                }
            }
            return new Point(iRow, iRow);
        }

        void findCurrentField() {
            iCurrFldRow = caretRow;
            iCurrFldStartCol = caretCol;
            iCurrFldEndCol = caretCol;
            try {
                for (int i = caretCol; i >= 0; i--) {
                    if (scrAttrs[iCurrFldRow][i] == 8)
                        iCurrFldStartCol = i;
                    else
                        break;
                }
                for (int i = caretCol; i < maxCols; i++) {
                    if (scrAttrs[iCurrFldRow][i] == 8)
                        iCurrFldEndCol = i;
                    else
                        break;
                }
            } catch (Exception e) {
            }
        }

        /**
         * Position caret to the row containing tab labels. Tab label is contained
         * within the tabLabelRows.x and tabLabelRows.y (inclusive)
         */
        private boolean runToTabRow() {
            boolean retVal = false;

            if (iCurrFldRow >= tabLabelRows.x
                && iCurrFldRow <= tabLabelRows.y)
                return true;

            boolean originalDirectionForward = (iCurrFldRow < tabLabelRows.x);
            boolean currentDirectionForward = originalDirectionForward;

            int overShootCount = 0;
            waitTime = 0;

            for (int loopCnt = 0; loopCnt < LAPM_MAX_INPUT_FIELDS; loopCnt++, waitTime = 0, receivedChar = false) {
                if (terminalSender == null)
                    break;
                try {
                    waitTime = 0;
                    receivedChar = false;

                    terminalSender.sendKey(TerminalSender.VK_TAB_INDEX, !currentDirectionForward, false);
                    while (!receivedChar
                        && waitTime < smartMouse_FirstChar_MaxWaitTime) {
                        waitTime += smartMouse_InterChar_WaitTime;
                        yield();
                        sleep(smartMouse_InterChar_WaitTime);
                    }

                    // now receive characters as long as available
                    if (receivedChar) {
                        waitTime = 0;
                        while (waitTime < smartMouse_InterChar_WaitTime * 3) {
                            waitTime += smartMouse_InterChar_WaitTime;
                            yield();
                            sleep(smartMouse_InterChar_WaitTime);
                        }
                    }

                    findCurrentField();
                    if (caretRow >= tabLabelRows.x
                        && caretRow <= tabLabelRows.y) {
                        retVal = true;
                        break;
                    }
                    if (currentDirectionForward) {
                        if (caretRow > tabLabelRows.y) {
                            overShootCount++;
                            currentDirectionForward = !currentDirectionForward;
                        }
                    } else {
                        if (caretRow < tabLabelRows.x) {
                            overShootCount++;
                            currentDirectionForward = !currentDirectionForward;
                        }
                    }
                    if (overShootCount > 1) {
                        break;    // cannot position to the required row
                    }
                } catch (InterruptedException e) {
                } catch (ThreadDeath e) {
                } catch (Exception e) {
                }
            }
            return retVal;
        }

        /**
         * This method should be called only after successful return from
         * runToTabRow() method.
         */
        private void runToTabCol() {
            if (tabLabelRows.y > tabLabelRows.x) {
                // this is multiline tab
                System.out.println("TODO: Speed up multi-line tab by using CTRL-DOWN arrow to position to the correct tab line");
            }

            if (iCurrFldRow >= tabLabelRows.x
                && iCurrFldRow <= tabLabelRows.y) {
                String lbl = getLapmTabLabel(iCurrFldRow, iCurrFldStartCol + 1);
                if (lbl != null
                    && lbl.equals(tabLabel))
                    return; // successful
            }

            waitTime = 0;
            int key = (targetCol > iCurrFldEndCol) ? TerminalSender.VK_RIGHT_INDEX : TerminalSender.VK_LEFT_INDEX;
            for (int loopCnt = 0; loopCnt < LAPM_MAX_TAB_LABELS; loopCnt++, waitTime = 0, receivedChar = false) {
                if (terminalSender == null)
                    break;
                try {
                    receivedChar = false;
                    terminalSender.sendKey(key, false, false);

                    while (!receivedChar
                        && waitTime < smartMouse_FirstChar_MaxWaitTime) {
                        waitTime += smartMouse_InterChar_WaitTime;
                        yield();
                        sleep(smartMouse_InterChar_WaitTime);
                    }

                    // now receive characters as long as available
                    if (receivedChar) {
                        waitTime = 0;
                        while (waitTime < smartMouse_InterChar_WaitTime * 3) {
                            waitTime += smartMouse_InterChar_WaitTime;
                            yield();
                            sleep(smartMouse_InterChar_WaitTime);
                        }
                    }

                    findCurrentField();
                    String lbl = getLapmTabLabel(iCurrFldRow, iCurrFldStartCol);
                    if (lbl != null
                        && lbl.equals(tabLabel))
                        return; // successful
                    if (iCurrFldRow < tabLabelRows.x
                        || iCurrFldRow > tabLabelRows.y)
                        break; // no longer in the tab
                } catch (InterruptedException e) {
                } catch (ThreadDeath e) {
                } catch (Exception e) {
                }
            }
            //System.out.println("ERROR: PaintLapmFuncThread: Run complete after " + cnt + " loops");
        }

        //System.out.println("ERROR: PaintLapmFuncThread: Run complete after " + cnt + " loops");
        @Override
        public void run() {
            if (terminalSender != null)
                terminalSender.setOptimizeDisplay(false, false);
            if (tabLabelRows != null) {
                if (runToTabRow()) {
                    runToTabCol();
                    if (terminalSender != null)
                        terminalSender.removeTerminalVputListener(this);
                }
                if (terminalSender != null)
                    terminalSender.setOptimizeDisplay(true, true);
                return;
            }

            boolean originalDirectionForward = (targetRow > iCurrFldRow || (targetRow == iCurrFldRow && targetCol > iCurrFldEndCol));
            boolean currentDirectionForward = originalDirectionForward;

            int overShootCount = 0;
            //int cnt = 0;
            waitTime = 0;
            int loopMax = (positionWithinField ? LAPM_MAX_INPUT_FIELD_SIZE : LAPM_MAX_INPUT_FIELDS);
            for (int loopCnt = 0; loopCnt < loopMax; loopCnt++, waitTime = 0, receivedChar = false) {
                if (terminalSender == null)
                    break;
                //cnt++;
                try {
                    receivedChar = false;
                    if (positionWithinField) {
                        if (targetCol == caretCol)
                            break;
                        else if (targetCol < caretCol)
                            terminalSender.sendKey(TerminalSender.VK_LEFT_INDEX, false, false);
                        else
                            terminalSender.sendKey(TerminalSender.VK_RIGHT_INDEX, false, false);
                    } else {
                        terminalSender.sendKey(TerminalSender.VK_TAB_INDEX, !currentDirectionForward, false);
                    }
                    //if (debug)
                    //	System.out.println("PaintLapmFuncThread: loop count " + cnt);
                    int totalWaitTime = 0;
                    yield();
                    while (!receivedChar
                        && waitTime < smartMouse_FirstChar_MaxWaitTime) {
                        waitTime += smartMouse_InterChar_WaitTime;
                        totalWaitTime += smartMouse_InterChar_WaitTime;
                        yield();
                        sleep(smartMouse_InterChar_WaitTime);
                    }
                    //System.err.println("first char waitTime is " + totalWaitTime);

                    // now receive characters as long as available
                    if (receivedChar) {
                        //System.err.println("Received char, now waiting for timeout");
                        waitTime = 0;
                        while (waitTime < smartMouse_InterChar_WaitTime * 3) {
                            //System.err.println("Received char, now waiting for timeout");
                            waitTime += smartMouse_InterChar_WaitTime;
                            totalWaitTime += smartMouse_InterChar_WaitTime;
                            yield();
                            sleep(smartMouse_InterChar_WaitTime);
                        }
                    }

                    //System.err.println("last char waitTime is " + totalWaitTime);

                    findCurrentField();

                    if (positionWithinField) {
                        if (targetRow == caretRow
                            && targetCol == caretCol)
                            break;        // positioning successful - break out
                        if (targetRow == caretRow
                            && targetCol >= iCurrFldStartCol
                            && targetCol <= iCurrFldEndCol) {
                            // continue trying
                        } else {
                            // exited field, return back to original field
                            if (caretRow > targetRow
                                || (caretRow == targetRow && caretCol > targetCol))
                                terminalSender.sendKey(TerminalSender.VK_LEFT_INDEX, false, false);
                            else
                                terminalSender.sendKey(TerminalSender.VK_RIGHT_INDEX, false, false);
                            break;
                        }
                    } else {
                        if (scrAttrs[targetRow][targetCol] == 8)
                            break;        // positioning successful - break out
                        // determine if the field has been overshot
                        if (currentDirectionForward) {
                            if (caretRow > targetRow
                                || (caretRow == targetRow && iCurrFldStartCol > targetCol)) {
                                overShootCount++;
                                currentDirectionForward = !currentDirectionForward;
                            }
                        } else {
                            if (caretRow < targetRow
                                || (caretRow == targetRow && iCurrFldEndCol < targetCol)) {
                                overShootCount++;
                                currentDirectionForward = !currentDirectionForward;
                            }
                        }
                        if (overShootCount > 1) {
                            // code to position between fields using arrows
                            break;
                        }
                        //System.err.println("LoopCnt = " + loopCnt);

                    }
                } catch (InterruptedException e) {
                } catch (ThreadDeath e) {
                } catch (Exception e) {
                }
            }
            if (terminalSender != null) {
                terminalSender.removeTerminalVputListener(this);
                terminalSender.setOptimizeDisplay(true, true);
            }
            //System.out.println("ERROR: PaintLapmFuncThread: Run complete after " + cnt + " loops");
        }
    }

    private boolean altGo;

    public void setAltGo(boolean flag) {
        altGo = flag;
    }

    class LapmScrFuncs {
        public final int LAPM_SCR_FUNCS_COUNT = 20;
        ColumnRange[] columnRanges;
        String[] labels;
        int btnCount;
        int btnSelected;

        LapmScrFuncs() {
            columnRanges = new ColumnRange[LAPM_SCR_FUNCS_COUNT];
            labels = new String[LAPM_SCR_FUNCS_COUNT];
            btnCount = 0;
            btnSelected = -1;
        }

        void init() {
            btnCount = 0;
            //btnSelected = -1;
        }

        int getButtonNumber(int col) {
            for (int i = 0; i < btnCount; i++) {
                if (col >= columnRanges[i].startCol
                    && col < columnRanges[i].endCol)
                    return i;
            }
            return -1;
        }

        void addBtn(int startCol, int endCol, String label, boolean selected) {
            if (columnRanges[btnCount] == null) {
                columnRanges[btnCount] = new ColumnRange(startCol, endCol);
            } else {
                columnRanges[btnCount].startCol = startCol;
                columnRanges[btnCount].endCol = endCol;
            }
            labels[btnCount] = label;
            if (selected)
                btnSelected = btnCount;
            btnCount++;
        }

        void paint(Graphics g) {
            g.setColor(Color.lightGray);
            g.fillRect(0, charHeight, maxCols * maxCharWidth, charHeight);
            Rectangle rect = new Rectangle();
            for (int i = 0; i < btnCount; i++) {
                rect.x = columnRanges[i].startCol * maxCharWidth;
                rect.y = charHeight;
                rect.width = (columnRanges[i].endCol - columnRanges[i].startCol) * maxCharWidth;
                rect.height = charHeight;
                drawButton(g, rect, 3, null, null, labels[i], (i != btnSelected));
            }
        }
    }

    private LapmScrFuncs lapmScrFuncs;
    /**
     * Paint the screen function code lines on an Lapm Screen (second line from top)
     * as a sequence of buttons. The current button has attributes of '8', the other
     * buttons have atributes of '9' and are separated by white spaces.
     * <p>
     * Painting is delayed by 200 milli-seconds everytime this function is called.
     */

    PaintLapmFuncThread paintLapmFuncThread;

    void paintLawsonLapmScrFuncs(Component component) {
        if (paintLapmFuncThread == null) {
            paintLapmFuncThread = new PaintLapmFuncThread();
            paintLapmFuncThread.start();
        }
        paintLapmFuncThread.repaint(component);
    }

    class PaintLapmFuncThread extends Thread {
        int waitTime;
        long waitTimeout = LAPM_FUNC_CODE_WAIT_TIMEOUT;
        boolean active;
        Component comp;

        public void repaint(Component component) {
            comp = component;
            waitTime = 0;
            active = true;
        }

        @Override
        public void run() {
            int cnt = 0;
            while (waitTime < waitTimeout) {
                cnt++;
                try {
                    //if (debug)
                    //	System.out.println("PaintLapmFuncThread: loop count " + cnt);
                    yield();
                    sleep(LAPM_FUNC_CODE_WAIT_TIME);
                    if (active) {
                        waitTime += LAPM_FUNC_CODE_WAIT_TIME;
                        if (waitTime >= waitTimeout) {
                            waitTime = 0;
                            active = false;
                            //if (debug)
                            //	System.out.println("PaintLapmFuncThread: painting row 2 buttons after " + waitTimeout + " milliseconds");
                            paintLawsonLapmScrFuncs();
                        }
                    }
                } catch (InterruptedException e) {
                } catch (ThreadDeath e) {
                } catch (Exception e) {
                }
            }
            System.out.println("ERROR: PaintLapmFuncThread: Run complete after " + cnt + " loops");
        }

        void paintLawsonLapmScrFuncs() {
            Graphics g = null;

            if (comp != null)
                g = comp.getGraphics();
            if (g == null) {
                System.out.println("paintLawsonLapmScrFuncs(): Graphics handle is null");
                return;
            }

            int[] chars = scrChars[1];
            int[] attrs = scrAttrs[1];
            StringBuffer sb = new StringBuffer();

            if (lapmScrFuncs == null)
                lapmScrFuncs = new LapmScrFuncs();
            lapmScrFuncs.init();

            // advance to the first space character beyond graphics character 0x18 + 256
            int btnStart = 0;
            for (btnStart = 0; btnStart < maxCols; btnStart++) {
                if (chars[btnStart] == 0x18 + 256)
                    break;
            }

            for (; btnStart < maxCols; ) {
                // advance btnStart to the first non-space character
                for (btnStart++; btnStart < maxCols - 1; btnStart++) {
                    if (chars[btnStart] < 256
                        && chars[btnStart] != ' ')
                        break;
                }
                if (btnStart >= maxCols - 1)
                    break;

                sb.setLength(0);
                // some function codes strings have spaces in them,
                // this code will not work there
                for (int btnEnd = btnStart; btnEnd < maxCols; btnEnd++) {
                    if (btnEnd >= maxCols - 1
                        || chars[btnEnd] == ' ') {
                        lapmScrFuncs.addBtn(btnStart, btnEnd, sb.toString(), attrs[btnStart] == 8);
                        btnStart = btnEnd;
                        break;
                    }
                    sb.append((char) chars[btnEnd]);
                }
            }
            // now draw the buttons
            lapmScrFuncs.paint(g);
            g.dispose();
        }
    }

    /**
     * Return true if the x,y co-ordinates correspond to a Lapm Function Button.
     * This function is used to determine whether to display a Hand-cursor
     */
    public boolean isOnLapmLapmScrFunc(int x, int y) {
        int btn = getLapmScrFuncNumber(x, y);
        return (btn >= 0);
    }

    /**
     * Return the button number if the x,y co-ordinates correspond to a Lapm Function Button.
     * This function is used to determine the sequence of key strokes to send
     * in order to activate the function button for Lawson's Lapm.
     *
     * @return Return the button number if on a button, otherwise return -1.
     */
    public int getLapmScrFuncNumber(int x, int y) {
        if (lapmScrFuncs == null)
            return -1;
        int col = x / maxCharWidth;
        int row = y / charHeight;
        if (row != 1)
            return -1;

        return lapmScrFuncs.getButtonNumber(col);
    }

    public int getLapmScrPressedFunc() {
        if (lapmScrFuncs == null)
            return -1;
        return lapmScrFuncs.btnSelected;
    }

    public boolean isCursorInLapmFuncCodeField() {
        return (maxRows > 0
            && maxCols > 9
            && scrAttrs[0][9] == 8);
    }

    class ColumnRange {
        int startCol;    // inclusive
        int endCol;        // exclusive

        ColumnRange(int iColStart, int iColEnd) {
            startCol = iColStart;
            endCol = iColEnd;
        }

    }

    class FunctionKeys {
        private ColumnRange[] functionKeys;
        private ColumnRange[] spaces;

        FunctionKeys(boolean wide)    // wide means 132 columns, otherwise based on 80 cols
        {
            int spaceWidth = (wide ? 6 : 3);
            int keyWidth = (wide ? 10 : 6);

            functionKeys = new ColumnRange[10];
            spaces = new ColumnRange[4];
            int i = 0;
            spaces[0] = new ColumnRange(i, i + spaceWidth);
            i += spaceWidth;
            for (int j = 0; j < 4; j++, i += keyWidth + 1) {
                functionKeys[j] = new ColumnRange(i, i + keyWidth);
            }
            i--;
            spaces[1] = new ColumnRange(i, i + spaceWidth);
            i += spaceWidth;
            for (int j = 0; j < 4; j++, i += keyWidth + 1) {
                functionKeys[4 + j] = new ColumnRange(i, i + keyWidth);
            }
            i--;
            spaces[2] = new ColumnRange(i, i + spaceWidth);
            i += spaceWidth;
            for (int j = 0; j < 2; j++, i += keyWidth + 1) {
                functionKeys[8 + j] = new ColumnRange(i, i + keyWidth);
            }
            i--;
            spaces[3] = new ColumnRange(i, i + spaceWidth);
            i += spaceWidth;
        }

        boolean hasFunctionKeys(int[] attrs) {
            // spaces should have no atributes set
            for (int i = 0; i < 4; i++) {
                int iStart = spaces[i].startCol;
                int iEnd = spaces[i].endCol;

                for (int j = iStart; j < iEnd; j++) {
                    if (attrs[j] != 0)
                        return false;
                }
            }
            // function keys should have attribute set
            for (int i = 0; i < 10; i++) {
                int iStart = functionKeys[i].startCol;
                int iEnd = functionKeys[i].endCol;

                for (int j = iStart; j < iEnd; j++) {
                    if (attrs[j] == 0)
                        return false;
                }
            }
            return true;
        }

        boolean isFunctionKey(int iCol) {
            // function keys should have attribute set
            for (int i = 0; i < 10; i++) {
                int iStart = functionKeys[i].startCol;
                int iEnd = functionKeys[i].endCol;

                if (iCol >= iStart
                    && iCol < iEnd)
                    return true;
            }
            return false;
        }

        boolean isFunctionKeyActive(int iCol, int[] chars, int[] attrs) {
            // function keys should have attribute set
            for (int i = 0; i < 10; i++) {
                int iStart = functionKeys[i].startCol;
                int iEnd = functionKeys[i].endCol;

                if (iCol >= iStart
                    && iCol < iEnd) {
                    for (int j = iStart; j < iEnd; j++) {
                        if (attrs[j] != 9)
                            return false;
                    }
                    boolean allSpaces = true;
                    for (int j = iStart; j < iEnd; j++) {
                        if (chars[j] != ' ')
                            allSpaces = false;
                    }
                    if (allSpaces)
                        return false;
                    return true;
                }
            }
            return false;
        }

        /**
         * Return the function key number. 0 means this column does not
         * correspond to a function key. 1 mean F1, 2 means F2 and so on
         */
        int getFunctionKey(int iCol) {
            // function keys should have attribute set
            for (int i = 0; i < 10; i++) {
                int iStart = functionKeys[i].startCol;
                int iEnd = functionKeys[i].endCol;

                if (iCol >= iStart
                    && iCol < iEnd)
                    return i + 1;
            }
            return 0;
        }

        /**
         * get the pixel bounds for the nth function key
         */
        Rectangle getFunctionKeyBounds(int keyNbr, int yOffset) {
            Rectangle rect = null;
            try {
                rect = new Rectangle(functionKeys[keyNbr].startCol * maxCharWidth,
                    yOffset,
                    (functionKeys[keyNbr].endCol - functionKeys[keyNbr].startCol) * maxCharWidth,
                    charHeight);
            } catch (Exception e) {
            }

            return rect;
        }

        /**
         * get the label for the nth function key
         */
        String getFunctionKeyLabel(int keyNbr, int[] chars) {
            int iStart = functionKeys[keyNbr].startCol;
            int iEnd = functionKeys[keyNbr].endCol;

            boolean allSpaces = true;
            String retVal = "";
            for (int j = iStart; j < iEnd; j++) {
                if (chars[j] != ' ')
                    allSpaces = false;
                retVal += (char) chars[j];
            }
            if (allSpaces)
                return null;
            return retVal;
        }

    }

}
