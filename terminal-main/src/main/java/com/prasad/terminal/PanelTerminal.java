//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator

package com.prasad.terminal;

import java.lang.reflect.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.io.*;

import com.prasad.util.ClassPathResourceUtils;
import com.prasad.util.ColorPicker;
import com.prasad.util.FontPicker;

import java.util.*;
import java.net.*;
import java.applet.Applet;

import com.prasad.terminal.ShowLapmFormInterface;
import com.prasad.terminal.TerminalVputListener;


public class PanelTerminal extends Canvas implements
    KeyListener,
    MouseListener,
    MouseMotionListener,
    FocusListener,
    TelnetInterface,
    ShowLapmFormInterface,
    TerminalSender,
    ClipboardOwner {
    public static final int TERMINAL_TYPE_PT80 = 0;
    public static final int TERMINAL_TYPE_VT220 = 1;

    private int terminalType = TERMINAL_TYPE_PT80;

    public static final int HOSTTYPE_UNIX = 0;
    public static final int HOSTTYPE_NT = 1;

    private int hostType = HOSTTYPE_UNIX;

    //static final int INNER_SIZE = 4;
    //static final int ACTIVE_SIZE = 2;

    //static final int BORDER_WIDTH = (INNER_SIZE+ACTIVE_SIZE);
    //static final int BORDER_HEIGHT = (INNER_SIZE+ACTIVE_SIZE);

    /* Output flags used with the terminalWrite() methods. */

    //public static final int OUTF_RAW     = 1<<0;  // Don't interpret \n \b etc.?
    //public static final int OUTF_PARTIAL = 1<<1;  // Don't update screen?

  /* Mode flags.  These are global operating modes of the terminal;
	 the initial state of all modes is off. */

    //public static final int MODE_SMOOTH = 1<<0;    // Smooth scroll display?
    //public static final int MODE_NEWLINE = 1<<1;   // Go to left column on NL?
    //public static final int MODE_NOWRAP = 1<<2;    // Wrap at right of screen?
    //public static final int MODE_INVERSE = 1<<3;   // Inverse video display?

  /* Style flags.  Use with setStyle() and getStyle(); each character can
	 have its own style, and that style is taken from the current style
	 setting when it was written to the terminal. */

//  public static final int STYLE_PLAIN      = 0;
//  public static final int STYLE_INVERSE    = 1<<0;
//  public static final int STYLE_BOLD       = 1<<1;
//  public static final int STYLE_ITALIC     = 1<<2;
//  public static final int STYLE_UNDERSCORE = 1<<3;
//  public static final int STYLE_MASK       = 0xff;

//  public static final int STYLE_COLOR_MASK = 0xff;
//  public static final int STYLE_FOREGROUND_POS = 8;
//  public static final int STYLE_BACKGROUND_POS = 16;

    /* Colors */

//  public static final int MAX_COLORS = 9;

    //private  boolean	outputecho = false;
    //int		curRow, curCol;          // Current cursor location.
//  int		curStyle = STYLE_PLAIN;  // Current character style.
    //boolean	cursorVisible = true;// Show the cursor?
//  int		curMode = 0;			// Global mode flags.

    //int		lastRow, lastCol;        // Last postion cursor was drawn at.
    //boolean	overChar = false;    // Flag if at far right of screen.
    //int		dirtyTop = -1, dirtyBottom = -1;
    //boolean[]	dirty;             // Rows which need to be redrawn.

    //int		regionTop, regionBottom, regionLeft, regionRight;

    //int		scrollCount = 0;         // Optimize scrolling (not implemented).

    /* History buffer information.  */

    //char[][]	history_char;         // Characters in history buffer
    //int[][]	history_style;         // Styles in history buffer
    //int		history_top, history_len;  // Top position and amount in history arrays
    //boolean	history_changed;       // Has position or data changed?
    //int		history_view = 0;          // Current display view.  0 => no hist visible
    //int		history_last_len = 0;      // Last length of history we displayed
    //int		history_last_view = 0;     // Last view on history we displayed

    /* EVENT_ACTION types that the terminal reports to its parent. */

//  public static final Integer ACTION_DISCONNECT	= new Integer(0);
//  public static final Integer ACTION_CONNECT	= new Integer(1);
//  public static final Integer ACTION_VIEW		= new Integer(2);


    KeyStatusListener keyStatusListener;
    ConnectListener connectListener;
    TelnetConnection telnetConnection;

    private boolean recomputeSizes = true;
    private String metricsString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private boolean in_focus = false;
    /** Location of the cursor where subsequent text will be printed **/
    //private Point			cursorPos		= new Point();
    /**
     * Whether a text area has been selected
     */
    private boolean selectionActive = false;
    /**
     * Whether a text area selection is being currently defined
     */
    private boolean selectionInProcess = false;
    /**
     * Starting index of selected text.
     **/
    private Point selectionStart = new Point();
    /**
     * Index of character after the last selected character.
     **/
    private Point selectionEnd = new Point();

    BorderLayout borderLayout1 = new BorderLayout();


    Panel panelTop = new Panel();
    Panel panelCenter = new Panel();
    Panel panelBottom = new Panel();
    TextField txtOpenClose = new TextField();
    TextField txtCtrlKey = new TextField();
    TextField txtShiftKey = new TextField();
    TextField txtNumLock = new TextField();
    TextField txtCapsLock = new TextField();
    TextField txtMsg = new TextField();

    private PalTerm palTerm;    // handle to PalTerm;
    private Options options;


    public PanelTerminal() {
        setAutodetectModeState(AUTODETECTMODE_STATE_PROMPT_START);
        options = new Options();

        setFont(options.getFont());
        initKeyTable();
        scrData = new ScrData(this);

        setTerminalType(TERMINAL_TYPE_PT80);

        try {
            jbInit();
            enableEvents(AWTEvent.MOUSE_EVENT_MASK |
                AWTEvent.MOUSE_MOTION_EVENT_MASK |
                AWTEvent.FOCUS_EVENT_MASK |
                AWTEvent.KEY_EVENT_MASK);
            this.addKeyListener(this);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setPalTerm(PalTerm t) {
        palTerm = t;
        scrData.palTerm = t;
    }

    public PalTerm getPalTerm() {
        return palTerm;
    }

    @Override
    public boolean isFocusTraversable() {
        return true;
    }

    void jbInit() throws Exception {
        this.setBackground(options.textAttribs[0].back);
        //this.setLayout(new BorderLayout());
        //this.add(panelTop, BorderLayout.NORTH);
        //this.add(panelCenter, BorderLayout.CENTER);
        //this.add(panelBottom, BorderLayout.SOUTH);
        txtOpenClose.setEnabled(false);
        txtOpenClose.setText("Closed");
        txtOpenClose.setEditable(false);
        txtCtrlKey.setEnabled(false);
        txtCtrlKey.setText("    ");
        txtCtrlKey.setEditable(false);
        txtShiftKey.setEnabled(false);
        txtShiftKey.setText("    ");
        txtShiftKey.setEditable(false);
        txtNumLock.setEnabled(false);
        txtNumLock.setText("NUM");
        txtNumLock.setEditable(false);
        txtCapsLock.setEnabled(false);
        txtCapsLock.setText("    ");
        txtCapsLock.setEditable(false);
        txtMsg.setEnabled(false);
        txtMsg.setEditable(false);
        txtMsg.setColumns(30);
        panelBottom.add(txtMsg, null);
        panelBottom.add(txtCapsLock, null);
        panelBottom.add(txtNumLock, null);
        panelBottom.add(txtShiftKey, null);
        panelBottom.add(txtCtrlKey, null);
        panelBottom.add(txtOpenClose, null);
    }

    public int getTerminalType() {
        return terminalType;
    }

    @Override
    public String getTerminalTypeName() {
        switch (terminalType) {
            case TERMINAL_TYPE_PT80:
                return "PT80-E";
            case TERMINAL_TYPE_VT220:
                return "VT220";
        }
        return "UNKNOWN";
    }

    public void setTerminalType(int t) {
        switch (t) {
            case TERMINAL_TYPE_PT80:
                terminalType = t;
                setNumCols(80);
                setNumRows(27);
                break;

            case TERMINAL_TYPE_VT220:
                terminalType = t;
                setNumCols(80);
                setNumRows(24);
                break;

            default:
                System.out.println("ERROR:" + getClass().getName() + ".setTerminalType(" + t + ") called with unknown terminal type");
                terminalType = TERMINAL_TYPE_PT80;
                setNumCols(80);
                setNumRows(27);
        }
        initScreen();
    }

    ScrData scrData;

    private String getF12Sequence() {
        if (options.getAltGo())
            return "\020";
        else
            return "\033";
    }

    private String getEnterSequence() {
        return keyTable[VK_ENTER_INDEX][0];
    }

    private void initScreen() {
        scrData.init(scrData.maxRows, scrData.maxCols, getFont());
    }

    /**
     * Invoked when a key has been typed.
     * This event occurs when a key press is followed by a key release.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // do not send any characters when the meta-key is down
        if (e.isMetaDown())
            return;

        int keyCode = e.getKeyCode();
        char keyChar = e.getKeyChar();

        switch (keyChar) {
            case 0x1B:
                keyChar = options.getAltGo() ? '\004' : '\033';  // finish/esc
                send("" + keyChar);
                if (debug)
                    System.out.println(getClass().getName() + ".keyTyped(): keyCode [" + keyCode + "] keyChar [0x" + Integer.toHexString(keyChar) + "], AltGo [" + options.getAltGo() + "] - sent");
                return;

            case '\t':    // KeyEvent.VK_TAB: handled in keyPressed
                if (debug)
                    System.out.println(getClass().getName() + ".keyTyped(): keyCode [" + keyCode + "] keyChar [tab] - not sent");
                return;

            case '+':    // KeyEvent.VK_ADD: handled in keyPressed
                if (debug)
                    System.out.println(getClass().getName() + ".keyTyped(): keyCode [" + keyCode + "] keyChar [+] - not sent");
                return;

            case '-':    // KeyEvent.VK_SUBTRACT: handled in keyPressed
                if (debug)
                    System.out.println(getClass().getName() + ".keyTyped(): keyCode [" + keyCode + "] keyChar [-] - not sent");
                return;

            case 'c':      // Copy
            case 'C':
            case 'h':      // Help
            case 'H':
            case 'm':      // Move
            case 'M':
                if (altPressed)
                    return;

            case '\r':    // KeyEvent.VK_ENTER: handled in keyPressed
            case '\n':    // KeyEvent.VK_ENTER: handled in keyPressed
                if (enterPressed)
                    return;

        }

        switch (keyCode) {
            case KeyEvent.VK_ENTER: // handled in keyPressed
                return;

            case KeyEvent.VK_SUBTRACT: // handled in keyPressed
                return;
        }

        if (debug)
            System.out.println(getClass().getName() + ".keyTyped(): keyCode [" + keyCode + "] keyChar [" + keyChar + "] - sent");
        send("" + keyChar);
    }

    private boolean capsLocked;
    private boolean numLocked;
    private boolean shiftPressed;
    private boolean ctrlPressed;
    private boolean altPressed;
    private boolean enterPressed;

    /**
     * Invoked when a key has been pressed.
     */

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int keyChar = e.getKeyChar();
        String keyString = null;
        int keyFlags = 0 + (e.isShiftDown() ? 1 : 0) + (e.isControlDown() ? 2 : 0);

        enterPressed = false;

        if (debug)
            System.out.println(getClass().getName() + ".keyPressed(): keyCode [" + keyCode + "] keyChar [" + keyChar + "] keyFlags [" + keyFlags + "]");

        altPressed = e.isAltDown();

        switch (keyCode) {
            case KeyEvent.VK_CAPS_LOCK:
                setCapsLocked(!capsLocked);
                return;
            case KeyEvent.VK_NUM_LOCK:
                setNumLocked(!numLocked);
                return;
            case KeyEvent.VK_SHIFT:
                setShiftPressed(true);
                return;
            case KeyEvent.VK_CONTROL:
                setCtrlPressed(true);
                return;

            case KeyEvent.VK_TAB:
                e.consume();
                keyString = keyTable[VK_TAB_INDEX][keyFlags];
                requestFocus();
                break;
            case KeyEvent.VK_PAGE_UP:
                keyString = keyTable[VK_PAGE_UP_INDEX][keyFlags];
                break;
            case KeyEvent.VK_PAGE_DOWN:
                keyString = keyTable[VK_PAGE_DOWN_INDEX][keyFlags];
                break;
            case KeyEvent.VK_END:
                keyString = keyTable[VK_END_INDEX][keyFlags];
                break;
            case KeyEvent.VK_HOME:
                keyString = keyTable[VK_HOME_INDEX][keyFlags];
                break;
            case KeyEvent.VK_LEFT:
                keyString = keyTable[VK_LEFT_INDEX][keyFlags];
                break;
            case KeyEvent.VK_UP:
                keyString = keyTable[VK_UP_INDEX][keyFlags];
                break;
            case KeyEvent.VK_RIGHT:
                keyString = keyTable[VK_RIGHT_INDEX][keyFlags];
                break;
            case KeyEvent.VK_DOWN:
                keyString = keyTable[VK_DOWN_INDEX][keyFlags];
                break;
            // VK_0 thru VK_9 are the same as ASCII '0' thru '9' (0x30 - 0x39)
            case KeyEvent.VK_0:
                keyString = keyTable[VK_0_INDEX][keyFlags];
                break;
            case KeyEvent.VK_1:
                keyString = keyTable[VK_1_INDEX][keyFlags];
                break;
            case KeyEvent.VK_2:
                keyString = keyTable[VK_2_INDEX][keyFlags];
                break;
            case KeyEvent.VK_3:
                keyString = keyTable[VK_3_INDEX][keyFlags];
                break;
            case KeyEvent.VK_4:
                keyString = keyTable[VK_4_INDEX][keyFlags];
                break;
            case KeyEvent.VK_5:
                keyString = keyTable[VK_5_INDEX][keyFlags];
                break;
            case KeyEvent.VK_6:
                keyString = keyTable[VK_6_INDEX][keyFlags];
                break;
            case KeyEvent.VK_7:
                keyString = keyTable[VK_7_INDEX][keyFlags];
                break;
            case KeyEvent.VK_8:
                keyString = keyTable[VK_8_INDEX][keyFlags];
                break;
            case KeyEvent.VK_9:
                keyString = keyTable[VK_9_INDEX][keyFlags];
                break;

            // VK_A thru VK_Z are the same as ASCII 'A' thru 'Z' (0x41 - 0x5A)
            case KeyEvent.VK_C:
                if (e.isAltDown())
                    keyString = keyTable[VK_C_ALT_INDEX][keyFlags];
                break;

            case KeyEvent.VK_H:
                if (e.isAltDown())
                    keyString = keyTable[VK_H_ALT_INDEX][keyFlags];
                break;

            case KeyEvent.VK_M:
                if (e.isAltDown())
                    keyString = keyTable[VK_M_ALT_INDEX][keyFlags];
                break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_B:
            case KeyEvent.VK_D:
            case KeyEvent.VK_E:
            case KeyEvent.VK_F:
            case KeyEvent.VK_G:
            case KeyEvent.VK_I:
            case KeyEvent.VK_J:
            case KeyEvent.VK_K:
            case KeyEvent.VK_L:
            case KeyEvent.VK_N:
            case KeyEvent.VK_O:
            case KeyEvent.VK_P:
            case KeyEvent.VK_Q:
            case KeyEvent.VK_R:
            case KeyEvent.VK_S:
            case KeyEvent.VK_T:
            case KeyEvent.VK_U:
            case KeyEvent.VK_V:
            case KeyEvent.VK_W:
            case KeyEvent.VK_X:
            case KeyEvent.VK_Y:
            case KeyEvent.VK_Z:
            case KeyEvent.VK_SPACE:

            case KeyEvent.VK_OPEN_BRACKET:
            case KeyEvent.VK_BACK_SLASH:
            case KeyEvent.VK_CLOSE_BRACKET:
            case KeyEvent.VK_SEMICOLON:
            case KeyEvent.VK_EQUALS:

            case KeyEvent.VK_COMMA:
            case KeyEvent.VK_PERIOD:
            case KeyEvent.VK_SLASH:
            case KeyEvent.VK_BACK_QUOTE:
            case KeyEvent.VK_QUOTE:
                // handled in keyTyped
                break;

            //case KeyEvent.VK_ESCAPE:
            //	keyString = keyTable[VK_ESCAPE_INDEX][keyFlags];
            //	break;
            case KeyEvent.VK_NUMPAD0:
                if (!numLocked)
                    keyString = keyTable[VK_NUMPAD0_INDEX][keyFlags];
                break;
            case KeyEvent.VK_NUMPAD1:
                if (!numLocked)
                    keyString = keyTable[VK_NUMPAD1_INDEX][keyFlags];
                break;
            case KeyEvent.VK_NUMPAD2:
                if (!numLocked)
                    keyString = keyTable[VK_NUMPAD2_INDEX][keyFlags];
                break;
            case KeyEvent.VK_NUMPAD3:
                if (!numLocked)
                    keyString = keyTable[VK_NUMPAD3_INDEX][keyFlags];
                break;
            case KeyEvent.VK_NUMPAD4:
                if (!numLocked)
                    keyString = keyTable[VK_NUMPAD4_INDEX][keyFlags];
                break;
            case KeyEvent.VK_NUMPAD5:
                if (!numLocked)
                    keyString = keyTable[VK_NUMPAD5_INDEX][keyFlags];
                break;
            case KeyEvent.VK_NUMPAD6:
                if (!numLocked)
                    keyString = keyTable[VK_NUMPAD6_INDEX][keyFlags];
                break;
            case KeyEvent.VK_NUMPAD7:
                if (!numLocked)
                    keyString = keyTable[VK_NUMPAD7_INDEX][keyFlags];
                break;
            case KeyEvent.VK_NUMPAD8:
                if (!numLocked)
                    keyString = keyTable[VK_NUMPAD8_INDEX][keyFlags];
                break;
            case KeyEvent.VK_NUMPAD9:
                if (!numLocked)
                    keyString = keyTable[VK_NUMPAD9_INDEX][keyFlags];
                break;
            case KeyEvent.VK_MULTIPLY:
                keyString = keyTable[VK_MULTIPLY_INDEX][keyFlags];
                break;
            case KeyEvent.VK_ADD:
                if (numLocked)
                    keyString = keyTable[VK_ADD_INDEX][0];
                else
                    keyString = keyTable[VK_ADD_INDEX][1];
                break;
            case KeyEvent.VK_SEPARATER:
                keyString = keyTable[VK_SEPARATER_INDEX][keyFlags];
                break;
            case KeyEvent.VK_SUBTRACT:
                if (numLocked)
                    keyString = "-";
                else
                    keyString = keyTable[VK_SUBTRACT_INDEX][0];
                break;
            case KeyEvent.VK_DECIMAL:
                if (!numLocked)
                    keyString = keyTable[VK_DECIMAL_INDEX][keyFlags];
                break;
            case KeyEvent.VK_DIVIDE:
                keyString = keyTable[VK_DIVIDE_INDEX][keyFlags];
                break;
            case KeyEvent.VK_F1:
                keyString = keyTable[VK_F1_INDEX][keyFlags];
                if (debug)
                    System.out.println("Debug: VK_F1 Released");
                break;
            case KeyEvent.VK_F2:
                keyString = keyTable[VK_F2_INDEX][keyFlags];
                if (debug)
                    System.out.println("Debug: VK_F2 Released");
                break;
            case KeyEvent.VK_F3:
                keyString = keyTable[VK_F3_INDEX][keyFlags];
                if (debug)
                    System.out.println("Debug: VK_F3 Released");
                break;
            case KeyEvent.VK_F4:
                keyString = keyTable[VK_F4_INDEX][keyFlags];
                if (debug)
                    System.out.println("Debug: VK_F4 Released");
                break;
            case KeyEvent.VK_F5:
                keyString = keyTable[VK_F5_INDEX][keyFlags];
                if (debug)
                    System.out.println("Debug: VK_F5 Released");
                break;
            case KeyEvent.VK_F6:
                keyString = keyTable[VK_F6_INDEX][keyFlags];
                if (debug)
                    System.out.println("Debug: VK_F6 Released");
                break;
            case KeyEvent.VK_F7:
                keyString = keyTable[VK_F7_INDEX][keyFlags];
                if (debug)
                    System.out.println("Debug: VK_F7 Released");
                break;
            case KeyEvent.VK_F8:
                keyString = keyTable[VK_F8_INDEX][keyFlags];
                if (debug)
                    System.out.println("Debug: VK_F8 Released");
                break;
            case KeyEvent.VK_F9:
                keyString = keyTable[VK_F9_INDEX][keyFlags];
                if (debug)
                    System.out.println("Debug: VK_F9 Released");
                break;
            case KeyEvent.VK_F10:
                e.consume();
                keyString = keyTable[VK_F10_INDEX][keyFlags];
                if (debug)
                    System.out.println("Debug: VK_F10 Released");
                requestFocus();
                break;
            case KeyEvent.VK_F11:
                keyString = keyTable[VK_F11_INDEX][keyFlags];
                if (debug)
                    System.out.println("Debug: VK_F11 Released");
                break;
            case KeyEvent.VK_F12:
                if (options.getAltGo()
                    && !options.getDfltF12IsGo())
                    keyString = getEnterSequence();
                else
                    keyString = getF12Sequence();
                if (debug)
                    System.out.println("Debug: VK_F12 Released");
                break;
            case KeyEvent.VK_ENTER:
                //		Enter may be same as F12 if AltGo is set
                // 			In such a case enter should transmit F12 sequence !
                enterPressed = true;
                if (options.getAltGo()
                    && !options.getDfltF12IsGo())
                    keyString = getF12Sequence();
                else
                    keyString = getEnterSequence();
                break;

            case KeyEvent.VK_DELETE:
                keyString = keyTable[VK_DELETE_INDEX][keyFlags];
                break;
            case KeyEvent.VK_PRINTSCREEN:
                keyString = keyTable[VK_PRINTSCREEN_INDEX][keyFlags];
                break;
            case KeyEvent.VK_INSERT:
                if (keyFlags == 0) {
                    keyString = scrData.overType ? ("\033[4l") : ("\033[4h");
                    scrData.overType = !scrData.overType;
                    vCur(scrData.overType);
                    moveCaret(scrData.caretRow, scrData.caretCol);
                } else {
                    keyString = keyTable[VK_INSERT_INDEX][keyFlags];
                    if (keyString == null) {
                        // do control-insert and shift-insert processing for copying and pasting
                        // highlighted text
                        if ((keyFlags & 1) > 0)    // shift-insert
                            pasteFromClipboard();
                        else if ((keyFlags & 2) > 0)    // control-insert
                            copyToClipboard();
                    }
                }

                break;

            default:
                // typed from the keyboard not numeric pad
                if (keyChar == '-')
                    keyString = "-";
                else if (keyChar == '+')
                    keyString = "+";
        }
        if (debug)
            System.out.println(getClass().getName() + ".keyPressed(): keyCode [" + keyCode + "] keyChar [" + keyChar + "] keyString [" + keyString + "]");
        if (keyString != null)
            send(keyString);
    }

    /**
     * Invoked when a key has been released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        enterPressed = false;
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_SHIFT:
                setShiftPressed(false);
                return;
            case KeyEvent.VK_CONTROL:
                setCtrlPressed(false);
                return;

            case KeyEvent.VK_TAB:
            case KeyEvent.VK_F10:
                e.consume();
                requestFocus();
                return;
        }
    }


    public KeyStatusListener getKeyStatusListener() {
        return keyStatusListener;
    }

    public void setKeyStatusListener(KeyStatusListener l) {
        keyStatusListener = l;
    }

    public ConnectListener getConnectListener() {
        return connectListener;
    }

    public void setConnectListener(ConnectListener l) {
        connectListener = l;
    }

    public boolean getCapsLocked() {
        return capsLocked;
    }

    public boolean getNumLocked() {
        return numLocked;
    }

    public boolean getShiftPressed() {
        return shiftPressed;
    }

    public boolean getCtrlPressed() {
        return ctrlPressed;
    }

    public void setCapsLocked(boolean b) {
        capsLocked = b;
        if (keyStatusListener != null)
            keyStatusListener.setCapsLocked(b);
        txtCapsLock.setText(b ? "CAPS" : "");
    }

    public void setNumLocked(boolean b) {
        numLocked = b;
        if (keyStatusListener != null)
            keyStatusListener.setNumLocked(b);
        txtNumLock.setText(b ? "NUM" : "");
    }

    public void setShiftPressed(boolean b) {
        shiftPressed = b;
        if (keyStatusListener != null)
            keyStatusListener.setShiftPressed(b);
        txtShiftKey.setText(b ? "SHFT" : "");
    }

    public void setCtrlPressed(boolean b) {
        ctrlPressed = b;
        if (keyStatusListener != null)
            keyStatusListener.setCtrlPressed(b);
        txtCtrlKey.setText(b ? "CTRL" : "");
    }

    //private int numCols	= 80;
    //private int numRows	= 27;

    @Override
    public int getNumCols() {
        return scrData.maxCols;
    }

    public void setNumCols(int i) {
        scrData.maxCols = i;
        recomputeSizes = true;
        invalidate();
    }

    @Override
    public int getTelnetNumRows() {
        return scrData.maxRows - 1;
    }

    public void setNumRows(int newMaxRows) {
        //if (numRows != newMaxRows)
        //	numRows = newMaxRows;

        scrData.scrollLines = newMaxRows - 1;
        scrData.maxRows = newMaxRows;
        recomputeSizes = true;
        invalidate();
    }

    private Dimension minDim = new Dimension();

    /**
     * Show a dialog box and select the font
     */
    private Frame hiddenParentFrame;
    private FontPicker fontPicker;

    public void selectFont(Frame f) {
        if (f == null)
            f = getParentFrame();

        if (fontPicker == null)
            fontPicker = new FontPicker(f, true);

        // Get the font name, style and size from the button action
        Font currentFont = getFont();
        fontPicker.setFont(currentFont);
        fontPicker.pack();
        fontPicker.show();

        if ((currentFont = fontPicker.getFont()) != null)
            setFont(currentFont);
    }

    @Override
    public void setFont(Font font) {
        if (font == null)
            return;

        super.setFont(font);
        options.setFont(font);
        if (scrData != null)
            scrData.setFont(font);    // for character spacing

        recomputeSizes = true;
        invalidate();
        repaint();
        //FontMetrics fm	= Toolkit.getDefaultToolkit().getFontMetrics(font);
        //layoutCharWidth	= (double)fm.stringWidth(metricsString) / metricsString.length();
        //if (debug)
        //{
        //	System.out.println("debug:"+getClass().getName() + ".setFont():layoutCharWidth=[" + layoutCharWidth +
        //		"], textFont=[" + font + "]" );
        //}
        //scrData.maxCharWidth	= (int)(layoutCharWidth + 0.5f);
        //scrData.charHeight		= fm.getCharHeight;
    }

    private void computeSize() {
        minDim = new Dimension(scrData.maxCharWidth * scrData.maxCols, scrData.charHeight * scrData.maxRows);
    }

    @Override
    public Dimension getPreferredSize() {
        return getMinimumSize();
    }

    @Override
    public Dimension getMinimumSize() {
        if (recomputeSizes)
            computeSize();
        return minDim;
    }

    private boolean isFunctionKeyActive(int iCol) {
        int i;
        if (options.getAltGo())
            return scrData.isFunctionKeyActive(iCol);
        return false;
    }

    private boolean hasFunctionKeys() {
        if (options.getAltGo())
            return scrData.hasFunctionKeys();
        return false;
    }

    private int getFunctionKey(int iCol) {
        int i;
        if (options.getAltGo())
            return scrData.getFunctionKey(iCol);
        return 0;
    }

    private void sendFunctionKey(int key) {
        switch (key) {
            case 1:
                send(keyTable[VK_F1_INDEX][0]);
                break;
            case 2:
                send(keyTable[VK_F2_INDEX][0]);
                break;
            case 3:
                send(keyTable[VK_F3_INDEX][0]);
                break;
            case 4:
                send(keyTable[VK_F4_INDEX][0]);
                break;
            case 5:
                send(keyTable[VK_F5_INDEX][0]);
                break;
            case 6:
                send(keyTable[VK_F6_INDEX][0]);
                break;
            case 7:
                send(keyTable[VK_F7_INDEX][0]);
                break;
            case 8:
                send(keyTable[VK_F8_INDEX][0]);
                break;
            case 9:
                send(keyTable[VK_F9_INDEX][0]);
                break;
            case 10:
                send(keyTable[VK_F10_INDEX][0]);
                break;
            case 11:
                send(keyTable[VK_F11_INDEX][0]);
                break;
            case 12:
                send(keyTable[VK_F12_INDEX][0]);
                break;
        }
    }

    private void sendSmartMouseMovement(Point p) {
        if (p == null)
            return;

        if (p.x == 0
            && p.y == 0)    // special kludge - this is not mouse movement but enter
        {
            send(getF12Sequence());
            return;
        }
        if (p.y > 0) {
            for (int i = 0; i < p.y; i++)
                send(keyTable[VK_DOWN_INDEX][0]);
        } else {
            for (int i = 0; i > p.y; i--)
                send(keyTable[VK_UP_INDEX][0]);
        }
        if (p.x > 0) {
            for (int i = 0; i < p.x; i++)
                send(keyTable[VK_RIGHT_INDEX][0]);
        } else {
            for (int i = 0; i > p.x; i--)
                send(keyTable[VK_LEFT_INDEX][0]);
        }
    }
    /**
     * Handle the mouseUp Event. Sets the cursor to the position clicked on.
     * @param event the event
     **/
	/*
  public void processMouseEvent(MouseEvent event)
  {
	int		eventId = event.getID();
	int		iRow	= event.getY() / scrData.charHeight;
	int		iCol	= event.getX() / scrData.maxCharWidth;

	switch(eventId)
	{
	case MouseEvent.MOUSE_CLICKED:
		if (iRow == scrData.FUNCTION_BTNS_ROW
		&&	hasFunctionKeys())
		{
			if (isFunctionKeyActive(iCol))
				sendFunctionKey( getFunctionKey(iCol) );
		}
		break;

	case MouseEvent.MOUSE_MOVED:
		if (iRow == scrData.FUNCTION_BTNS_ROW
		&&	hasFunctionKeys())
		{
			if (isFunctionKeyActive(iCol))
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			else
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		else
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			if (selectionActive)
			{
				selectionEnd.x		= iCol;
				selectionEnd.y		= iRow;
				repaint();
			}
		}
		break;

	case MouseEvent.MOUSE_PRESSED:
		if (iRow == scrData.FUNCTION_BTNS_ROW
		&&	hasFunctionKeys())
		{
		}
		else
		{
			if (selectionActive)
				repaint();
			selectionActive		= true;
			selectionStart.x	= iCol;
			selectionStart.y	= iRow;
			selectionEnd.x		= iCol;
			selectionEnd.y		= iRow;
		}
		break;

	case MouseEvent.MOUSE_RELEASED:
		if (iRow == scrData.FUNCTION_BTNS_ROW
		&&	hasFunctionKeys())
		{
		}
		else
		{
			selectionEnd.x	= iCol;
			selectionEnd.y	= iRow;
			if (selectionEnd.x == selectionStart.x
			&&	selectionEnd.y == selectionStart.y)
				selectionActive = false;
			else
			{
				selectionActive = true;
				// swap the points if the selectionStart row or col is higher
				if (selectionStart.y > selectionEnd.y)
				{
					int y				= selectionStart.y;
					selectionStart.y	= selectionEnd.y;
					selectionEnd.y		= y;
				}
				if (selectionStart.x > selectionEnd.x)
				{
					int x				= selectionStart.x;
					selectionStart.x	= selectionEnd.x;
					selectionEnd.x		= x;
				}
			}
			if (!in_focus)
				requestFocus();
			else
			if (selectionActive)
				repaint();
		}
		break;
	}
	super.processMouseEvent(event);

  }
  */

    /**
     * Handle focus event
     **/
    @Override
    public void processFocusEvent(FocusEvent event) {
        if (event.getID() == FocusEvent.FOCUS_GAINED) {
            in_focus = true;
        } else if (event.getID() == FocusEvent.FOCUS_LOST) {
            in_focus = false;
        }

        if (selectionActive) {
            //selectionActive = false;
            Graphics g = getGraphics();
            paint(g);
            g.dispose();
        }
        super.processFocusEvent(event);
    }

    /**
     * Draw the text caret at the specified row and column
     *
     * @param row the cursor row
     * @param col the cursor column
     **/
    synchronized void drawCaret(int row, int col) {
        if (!in_focus
            || !paintCaretFlag) {
            scrData.caretRow = row;
            scrData.caretCol = col;
            return;
        }

        Graphics g = getGraphics();
        if (g == null)
            return;

        // erase old caret
        if (scrData.caretRow >= 0
            && scrData.caretCol >= 0)
            TermPaintWholeScr(scrData, g, scrData.caretCol, scrData.caretRow, scrData.caretCol + 1, scrData.caretRow);

        scrData.caretRow = row;
        scrData.caretCol = col;

        int xpos = col * scrData.maxCharWidth;
        int ypos = row * scrData.charHeight;

        int attrib = scrData.scrAttrs[row][col];
        boolean inverse = inSelection(row, col);

        g.setColor((inverse) ? options.textAttribs[attrib].back : options.textAttribs[attrib].fore);

        g.drawLine(xpos + 2, ypos, xpos + 2, ypos + scrData.charHeight - 1);
        g.drawLine(xpos, ypos, xpos + 4, ypos);
        g.drawLine(xpos, ypos + scrData.charHeight - 1, xpos + 4, ypos + scrData.charHeight - 1);
        g.dispose();
    }

    synchronized public void disconnect() {
        if (telnetConnection != null) {
            telnetConnection.disconnect();
            telnetConnection = null;
        }
    }

    private String telnetHost;

    public String getTitle() {
        String s = "Disconnected";
        if (isConnected)
            s = telnetHost;

        if (optionFileName != null)
            s += " - " + optionFileName;
        else
            s += " - default";

        if (options.getDirty())
            s += " (modified)";

        return s;
    }

    public void connect(String host, int port, String userid, String password) {
        telnetHost = host;
        disconnect();

        boolean success = false;
        // need to get out of the sandbox
        if (palTerm.isNAV) {
            // try to get Netscape permission
            try {
                // for more targets goto:
                //http://developer.netscape.com/docs/manuals/signedobj/targets/index.htm
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                //if (debug)
                //    System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        telnetConnection = new TelnetConnection(this, host, port);
        // userid and password processing is not implemented yet
    }

    private boolean isConnected;

    @Override
    public void connected() {
        //TODO: implement this prasad.terminal.TelnetInterface method;
        isConnected = true;
        if (connectListener != null)
            connectListener.connected();
        txtOpenClose.setText("OPEN");
    }

    @Override
    public void disconnected() {
        isConnected = false;
        //TODO: implement this prasad.terminal.TelnetInterface method;
        if (connectListener != null)
            connectListener.disconnected();
        txtOpenClose.setText("CLOSED");
    }

    @Override
    public void receive(char[] d, int off, int len) {
        //TODO: implement this prasad.terminal.TelnetInterface method;
        int cnt = len;
        if (debug && cnt > 0) {
            System.out.println("PanelTerminal: Text: '" + sequenceToString(new String(d, off, cnt)) + "'");
        }
        terminalWrite(d, off, cnt/*,0*/);
        if (panelAttrs != null)
            panelAttrs.repaint();
    }

    @Override
    public void receive(String str) {
        //TODO: implement this prasad.terminal.TelnetInterface method;
        receive(str.toCharArray(), 0, str.length());
    }

    @Override
    public void receive(char c) {
        //TODO: implement this prasad.terminal.TelnetInterface method;
        if (debug)
            System.out.println("PalTerm: Text: [" + charToString(c) + "]");
        terminalWrite(c/*,0*/);
    }

    @Override
    public String sequenceToString(String seq) {
        //TODO: implement this prasad.terminal.TelnetInterface method;
        StringBuffer res = new StringBuffer(128);
        for (int i = 0; i < seq.length(); i++) {
            if (i > 0)
                res.append(' ');
            res.append(charToString(seq.charAt(i)));
        }
        return res.toString();
    }

    @Override
    public String charToString(char c) {
        //TODO: implement this prasad.terminal.TelnetInterface method;
        if (c <= (char) 0x20)
            return codes_00_20[(int) c];
        else if (c >= (char) 0x7f && c <= (char) 0x9f)
            return codes_7f_9f[(int) c - 0x7f];
        else if (c == (char) 0xff)
            return "$ff";
        else if (c >= (char) 0x100)
            return "$" + Integer.toString((int) c, 16);
        return String.valueOf(c);
    }

    static final String[] codes_00_20 = {
        "NUL", "SOH", "STX", "ETX", "EOT", "ENQ", "ACK", "BEL",
        "BS", "HT", "LF", "VT", "FF", "CR", "SO", "SI",
        "DLE", "DC1", "DC2", "DC3", "DC4", "NAK", "SYN", "ETB",
        "CAN", "EM", "SUB", "ESC", "FS", "GS", "RS", "US",
        "[]"
    };

    static final String[] codes_7f_9f =
        {
            "DEL",
            "$80", "$81", "$82", "$83", "IND", "NEL", "SSA", "ESA",
            "HTS", "HTJ", "VTS", "PLD", "PLU", "RI", "SS2", "SS3",
            "DCS", "PU1", "PU2", "STS", "CCH", "MW", "SPA", "EPA",
            "$98", "$99", "$9a", "CSI", "ST", "OSC", "PM", "APC"
        };

    @Override
    public void sendKey(int virtualKeyIndex, boolean shift, boolean ctrl) {
        if (virtualKeyIndex >= 0
            && virtualKeyIndex < keyTable.length) {
            int keyFlags = 0 + (shift ? 1 : 0) + (ctrl ? 2 : 0);
            send(keyTable[virtualKeyIndex][keyFlags]);
            flush();
        }
    }

    @Override
    public void send(char[] d, int off, int len) {
        // all send() methods should check the command state and adjust
        // based on what is lastHostPrompt was
        switch (autodetectModeState) {
            case AUTODETECTMODE_STATE_PROMPT_START:
                if (lastHostPrompt != null
                    && lastUserCommandLine != null
                    && ((hostPrompt != null && lastHostPrompt.endsWith(hostPrompt))
                    || (hostPrompt == null && (lastHostPrompt.endsWith("$ ")
                    || lastHostPrompt.endsWith("# ")
                    || lastHostPrompt.endsWith("> ")))))
                    setAutodetectModeState(AUTODETECTMODE_STATE_USERCMD_START);
                break;
        }

        if (telnetConnection != null) {
            byte[] b = new byte[len];
            int i = 0;
            while (len > 0) {
                b[i] = (byte) d[off];
                i++;
                off++;
                len--;
            }
            try {
                telnetConnection.write(b);
            } catch (IOException ex) {
                terminalWrite("\r\n" + ex.toString()/*,0*/);
            }
        }
    }

    @Override
    public void send(String str) {
        if (str == null)
            return;

        switch (autodetectModeState) {
            case AUTODETECTMODE_STATE_PROMPT_START:
                if (lastHostPrompt != null
                    && ((hostPrompt != null && lastHostPrompt.endsWith(hostPrompt))
                    || (hostPrompt == null && (lastHostPrompt.endsWith("$ ")
                    || lastHostPrompt.endsWith("# ")
                    || lastHostPrompt.endsWith("> ")))))
                    setAutodetectModeState(AUTODETECTMODE_STATE_USERCMD_START);
                break;
        }

        if (telnetConnection != null) {
            try {
                telnetConnection.write(str);
            } catch (IOException ex) {
                terminalWrite("\r\n" + ex.toString()/*,0*/);
            }
        }
    }

    @Override
    public void send(char c) {
        switch (autodetectModeState) {
            case AUTODETECTMODE_STATE_PROMPT_START:
                if (lastHostPrompt != null
                    && ((hostPrompt != null && lastHostPrompt.endsWith(hostPrompt))
                    || (hostPrompt == null && (lastHostPrompt.endsWith("$ ")
                    || lastHostPrompt.endsWith("# ")
                    || lastHostPrompt.endsWith("> ")))))
                    setAutodetectModeState(AUTODETECTMODE_STATE_USERCMD_START);
                break;
        }

        if (telnetConnection != null) {
            try {
                telnetConnection.write((byte) c);
            } catch (IOException ex) {
                terminalWrite("\r\n" + ex.toString()/*,0*/);
            }
        }
    }

    public void flush() {
        telnetConnection.flush();
    }

    /* Override this method to handle non-ASCII events -- e.g., cursor
	 keys, mouse buttons, etc. */
    @Override
    public synchronized boolean handleEvent(Event e) {
        return false;
    }

  /* ------------------------------------------------------------
	 CHARACTER-STREAM METHODS.
	 ------------------------------------------------------------ */

    public void terminalWrite(char[] d, int off, int len /*, int flags*/) {
        if (debug) {
            if (d == null)
                System.out.println("PalTerm: terminalWrite: [(null)] at row " + scrData.row + ", col " + scrData.col);
            else
                System.out.println("PalTerm: terminalWrite: [" + new String(d, off, len) + "] at row " + scrData.row + ", col " + scrData.col);
        }

        Graphics g = getGraphics();
        //if (len > 2400)
        //	fastScroll = true;
        for (int i = 0; i < len; i++) {
            vput(g, d[off + i]);
            //if ((i % 100) == 0)
            //	System.out.println("PanelTerminal.terminalWrite: called vput " + i + " times.");
        }
        //if (len > 2400)
        //{
        //	fastScroll = false;
        //	paint(g);
        //}
        g.dispose();
        drawCaret(scrData.row, scrData.col);
    }

    public void terminalWrite(String str/*, int flags*/) {
        terminalWrite(str.toCharArray(), 0, str.length()/*,flags*/);
    }

    public void terminalWrite(char c/*, int flags*/) {
        Graphics g = getGraphics();
        vput(g, c);
        g.dispose();
        drawCaret(scrData.row, scrData.col);
    }

    /*
  void screenDirty(int top, int bottom)
  {
	if (dirtyTop	< 0
	||	top			< dirtyTop )
		dirtyTop = top;
	if (bottom > dirtyBottom)
		dirtyBottom = bottom;
	for (int i=top; i<=bottom; i++ )
		dirty[i] = true;
  }
  */
    public synchronized void terminalInit() {
        screenInit();
        repaint();
    }

    public synchronized void screenInit() {
        Dimension d;
        int rows, cols;

        if (debug)
            System.out.println("PalTerm: Initializing screen...");

        //curCol		= 0;
        //curRow		= 0;
        //lastCol		= 0;
        //lastRow		= 0;
        //scrollCount = 0;
        //setRegion();
        d = getMinimumSize();

        if (debug)
            System.out.println("PalTerm: Width = " + d.width + ", Height = " + d.height);
        if (debug)
            System.out.println("PalTerm: Columns = " + scrData.maxCols + ", maxRows = " + scrData.maxRows);
    }

  /*
  final public synchronized void setTextBackground(Color bg, int num) {
	if( num >= 0 && num < MAX_COLORS ) {
	  textBackground[num] = bg;
	  if( num == 0 ) setBackground(bg);
	}
	screenDirty(0,numRows-1);
  }

  final public synchronized void setTextForeground(Color fg, int num) {
	if( num >= 0 && num < MAX_COLORS ) {
	  textForeground[num] = fg;
	  if( num == 0 ) setForeground(fg);
	}
	screenDirty(0,numRows-1);
  }

  final public synchronized Color getTextBackground(int num) {
	if( num >= 0 && num < MAX_COLORS ) return textBackground[num];
	return null;
  }

  final public synchronized Color getForeBackground(int num) {
	if( num >= 0 && num < MAX_COLORS ) return textForeground[num];
	return null;
  }
  */

    public void setHostType(int type) {
        hostType = type;
        //if (hostType == HOSTTYPE_NT)
        {
            keyTable[VK_ENTER_INDEX][0] = "\r";
            keyTable[VK_ENTER_INDEX][1] = "\r";
            keyTable[VK_ENTER_INDEX][2] = "\033OJ";
            keyTable[VK_ENTER_INDEX][3] = null;
        }
        //else
        //{
        //	keyTable[VK_ENTER_INDEX][0]		= "\n";
        //	keyTable[VK_ENTER_INDEX][1]		= "\n";
        //	keyTable[VK_ENTER_INDEX][2]		= "\033OJ";
        //	keyTable[VK_ENTER_INDEX][3]		= null;
        //}
    }

    public static final String[][] keyTable = new String[VK_MAX_INDEX][4];

    private void initKeyTable() {
        //if (hostType == HOSTTYPE_NT)
        {
            keyTable[VK_ENTER_INDEX][0] = "\r";
            keyTable[VK_ENTER_INDEX][1] = "\r";
            keyTable[VK_ENTER_INDEX][2] = "\033OJ";
            keyTable[VK_ENTER_INDEX][3] = null;
        }
        //else
        //{
        //	keyTable[VK_ENTER_INDEX][0]		= "\n";
        //	keyTable[VK_ENTER_INDEX][1]		= "\n";
        //	keyTable[VK_ENTER_INDEX][2]		= "\033OJ";
        //	keyTable[VK_ENTER_INDEX][3]		= null;
        //}

        keyTable[VK_BACK_SPACE_INDEX][0] = "\b";
        keyTable[VK_BACK_SPACE_INDEX][1] = "\177";
        keyTable[VK_BACK_SPACE_INDEX][2] = "\033OF";
        keyTable[VK_BACK_SPACE_INDEX][3] = null;

        keyTable[VK_TAB_INDEX][0] = "\t";
        keyTable[VK_TAB_INDEX][1] = "\033OE";
        keyTable[VK_TAB_INDEX][2] = "\033OE";
        keyTable[VK_TAB_INDEX][3] = null;

        keyTable[VK_ESCAPE_INDEX][0] = "\030";
        keyTable[VK_ESCAPE_INDEX][1] = "\033OL";
        keyTable[VK_ESCAPE_INDEX][2] = "\034";
        keyTable[VK_ESCAPE_INDEX][3] = "\036";

        keyTable[VK_PAGE_UP_INDEX][0] = "\033[V";
        keyTable[VK_PAGE_UP_INDEX][1] = "\033[T";
        keyTable[VK_PAGE_UP_INDEX][2] = "\033[2T";
        keyTable[VK_PAGE_UP_INDEX][3] = "\033[2V";

        keyTable[VK_PAGE_DOWN_INDEX][0] = "\033[U";
        keyTable[VK_PAGE_DOWN_INDEX][1] = "\033[S";
        keyTable[VK_PAGE_DOWN_INDEX][2] = "\033[2S";
        keyTable[VK_PAGE_DOWN_INDEX][3] = "\033[2U";

        keyTable[VK_END_INDEX][0] = "\033O^";    // used to be numeric 5
        keyTable[VK_END_INDEX][1] = "\033O~";
        keyTable[VK_END_INDEX][2] = "\033On";
        keyTable[VK_END_INDEX][3] = null;

//	keyTable[VK_END_INDEX][0]		= "\004";
//	keyTable[VK_END_INDEX][1]		= "\033OL";
//	keyTable[VK_END_INDEX][2]		= "\033OM";
//	keyTable[VK_END_INDEX][3]		= null;

        keyTable[VK_HOME_INDEX][0] = "\033O]";
        keyTable[VK_HOME_INDEX][1] = "\033O}";
        keyTable[VK_HOME_INDEX][2] = "\033Om";
        keyTable[VK_HOME_INDEX][3] = null;

        keyTable[VK_LEFT_INDEX][0] = "\033[D";
        keyTable[VK_LEFT_INDEX][1] = "\033[2D";
        keyTable[VK_LEFT_INDEX][2] = "\033[2d";
        keyTable[VK_LEFT_INDEX][3] = null;

        keyTable[VK_UP_INDEX][0] = "\033[A";
        keyTable[VK_UP_INDEX][1] = "\033[2A";
        keyTable[VK_UP_INDEX][2] = "\033[2a";
        keyTable[VK_UP_INDEX][3] = null;

        keyTable[VK_RIGHT_INDEX][0] = "\033[C";
        keyTable[VK_RIGHT_INDEX][1] = "\033[2C";
        keyTable[VK_RIGHT_INDEX][2] = "\033[2c";
        keyTable[VK_RIGHT_INDEX][3] = null;

        keyTable[VK_DOWN_INDEX][0] = "\033[B";
        keyTable[VK_DOWN_INDEX][1] = "\033[2B";
        keyTable[VK_DOWN_INDEX][2] = "\033[2b";
        keyTable[VK_DOWN_INDEX][3] = null;

        keyTable[VK_0_INDEX][0] = null;
        keyTable[VK_0_INDEX][1] = null;
        keyTable[VK_0_INDEX][2] = "\033[2p";
        keyTable[VK_0_INDEX][3] = null;

        keyTable[VK_1_INDEX][0] = null;
        keyTable[VK_1_INDEX][1] = null;
        keyTable[VK_1_INDEX][2] = "\033[2r";
        keyTable[VK_1_INDEX][3] = null;

        keyTable[VK_2_INDEX][0] = null;
        keyTable[VK_2_INDEX][1] = null;
        keyTable[VK_2_INDEX][2] = "\033[2s";
        keyTable[VK_2_INDEX][3] = null;

        keyTable[VK_3_INDEX][0] = null;
        keyTable[VK_3_INDEX][1] = null;
        keyTable[VK_3_INDEX][2] = "\033[2t";
        keyTable[VK_3_INDEX][3] = null;

        keyTable[VK_4_INDEX][0] = null;
        keyTable[VK_4_INDEX][1] = null;
        keyTable[VK_4_INDEX][2] = "\033[2u";
        keyTable[VK_4_INDEX][3] = null;

        keyTable[VK_5_INDEX][0] = null;
        keyTable[VK_5_INDEX][1] = null;
        keyTable[VK_5_INDEX][2] = "\033[2v";
        keyTable[VK_5_INDEX][3] = null;

        keyTable[VK_6_INDEX][0] = null;
        keyTable[VK_6_INDEX][1] = null;
        keyTable[VK_6_INDEX][2] = "\033[2w";
        keyTable[VK_6_INDEX][3] = "";

        keyTable[VK_7_INDEX][0] = null;
        keyTable[VK_7_INDEX][1] = null;
        keyTable[VK_7_INDEX][2] = "\033[2x";
        keyTable[VK_7_INDEX][3] = "";

        keyTable[VK_8_INDEX][0] = null;
        keyTable[VK_8_INDEX][1] = null;
        keyTable[VK_8_INDEX][2] = "\033[2y";
        keyTable[VK_8_INDEX][3] = null;

        keyTable[VK_9_INDEX][0] = null;
        keyTable[VK_9_INDEX][1] = null;
        keyTable[VK_9_INDEX][2] = "\033[2z";
        keyTable[VK_9_INDEX][3] = null;


        keyTable[VK_NUMPAD0_INDEX][0] = "\033[4h";
        keyTable[VK_NUMPAD0_INDEX][1] = "\033[4l";
        keyTable[VK_NUMPAD0_INDEX][2] = null;
        keyTable[VK_NUMPAD0_INDEX][3] = null;

        keyTable[VK_NUMPAD1_INDEX][0] = "\004";
        keyTable[VK_NUMPAD1_INDEX][1] = "\033OL";
        keyTable[VK_NUMPAD1_INDEX][2] = "\033OM";
        keyTable[VK_NUMPAD1_INDEX][3] = null;

        keyTable[VK_NUMPAD2_INDEX][0] = "\033[B";
        keyTable[VK_NUMPAD2_INDEX][1] = "\033[2B";
        keyTable[VK_NUMPAD2_INDEX][2] = "\033[2b";
        keyTable[VK_NUMPAD2_INDEX][3] = null;

        keyTable[VK_NUMPAD3_INDEX][0] = "\033[U";
        keyTable[VK_NUMPAD3_INDEX][1] = "\033[S";
        keyTable[VK_NUMPAD3_INDEX][2] = "\033[2S";
        keyTable[VK_NUMPAD3_INDEX][3] = "\033[2U";

        keyTable[VK_NUMPAD4_INDEX][0] = "\033[D";
        keyTable[VK_NUMPAD4_INDEX][1] = "\033[2D";
        keyTable[VK_NUMPAD4_INDEX][2] = "\033[2d";
        keyTable[VK_NUMPAD4_INDEX][3] = null;

        keyTable[VK_NUMPAD5_INDEX][0] = "\033O^";
        keyTable[VK_NUMPAD5_INDEX][1] = "\033O~";
        keyTable[VK_NUMPAD5_INDEX][2] = "\033On";
        keyTable[VK_NUMPAD5_INDEX][3] = null;

        keyTable[VK_NUMPAD6_INDEX][0] = "\033[C";
        keyTable[VK_NUMPAD6_INDEX][1] = "\033[2C";
        keyTable[VK_NUMPAD6_INDEX][2] = "\033[2c";
        keyTable[VK_NUMPAD6_INDEX][3] = null;

        keyTable[VK_NUMPAD7_INDEX][0] = "\033O]";
        keyTable[VK_NUMPAD7_INDEX][1] = "\033O}";
        keyTable[VK_NUMPAD7_INDEX][2] = "\033Om";
        keyTable[VK_NUMPAD7_INDEX][3] = null;

        keyTable[VK_NUMPAD8_INDEX][0] = "\033[A";
        keyTable[VK_NUMPAD8_INDEX][1] = "\033[2A";
        keyTable[VK_NUMPAD8_INDEX][2] = "\033[2a";
        keyTable[VK_NUMPAD8_INDEX][3] = null;

        keyTable[VK_NUMPAD9_INDEX][0] = "\033[V";
        keyTable[VK_NUMPAD9_INDEX][1] = "\033[T";
        keyTable[VK_NUMPAD9_INDEX][2] = "\033[2T";
        keyTable[VK_NUMPAD9_INDEX][3] = "\033[2V";

        keyTable[VK_MULTIPLY_INDEX][0] = "*";
        keyTable[VK_MULTIPLY_INDEX][1] = "*";
        keyTable[VK_MULTIPLY_INDEX][2] = "*";
        keyTable[VK_MULTIPLY_INDEX][3] = "*";

        keyTable[VK_ADD_INDEX][0] = "+";
        keyTable[VK_ADD_INDEX][1] = "\n";
        keyTable[VK_ADD_INDEX][2] = null;
        keyTable[VK_ADD_INDEX][3] = null;

        keyTable[VK_SEPARATER_INDEX][0] = " ";
        keyTable[VK_SEPARATER_INDEX][1] = " ";
        keyTable[VK_SEPARATER_INDEX][2] = " ";
        keyTable[VK_SEPARATER_INDEX][3] = " ";

        keyTable[VK_SUBTRACT_INDEX][0] = "\033Oo";
        keyTable[VK_SUBTRACT_INDEX][1] = null;
        keyTable[VK_SUBTRACT_INDEX][2] = null;
        keyTable[VK_SUBTRACT_INDEX][3] = null;
        //keyTable[VK_SUBTRACT_INDEX][0]		= "\020";
        //keyTable[VK_SUBTRACT_INDEX][1]		= "\033ON";
        //keyTable[VK_SUBTRACT_INDEX][2]		= "\033OO";
        //keyTable[VK_SUBTRACT_INDEX][3]		= null;
//				{"\033Oo",       "\033Oo",       "\033Oo",       null},	// 84

        keyTable[VK_DECIMAL_INDEX][0] = "\177";
        keyTable[VK_DECIMAL_INDEX][1] = "\037";
        keyTable[VK_DECIMAL_INDEX][2] = "\035";
        keyTable[VK_DECIMAL_INDEX][3] = null;

        keyTable[VK_DIVIDE_INDEX][0] = "/";
        keyTable[VK_DIVIDE_INDEX][1] = "/";
        keyTable[VK_DIVIDE_INDEX][2] = "/";
        keyTable[VK_DIVIDE_INDEX][3] = "/";

        keyTable[VK_F1_INDEX][0] = "\033OP";
        keyTable[VK_F1_INDEX][1] = "\033Op";
        keyTable[VK_F1_INDEX][2] = "\033O`";
        keyTable[VK_F1_INDEX][3] = null;

        keyTable[VK_F2_INDEX][0] = "\033OQ";
        keyTable[VK_F2_INDEX][1] = "\033Oq";
        keyTable[VK_F2_INDEX][2] = "\033Oa";
        keyTable[VK_F2_INDEX][3] = null;

        keyTable[VK_F3_INDEX][0] = "\033OR";
        keyTable[VK_F3_INDEX][1] = "\033Or";
        keyTable[VK_F3_INDEX][2] = "\033Ob";
        keyTable[VK_F3_INDEX][3] = null;

        keyTable[VK_F4_INDEX][0] = "\033OS";
        keyTable[VK_F4_INDEX][1] = "\033Os";
        keyTable[VK_F4_INDEX][2] = "\033Oc";
        keyTable[VK_F4_INDEX][3] = null;

        keyTable[VK_F5_INDEX][0] = "\033OT";
        keyTable[VK_F5_INDEX][1] = "\033Ot";
        keyTable[VK_F5_INDEX][2] = "\033Od";
        keyTable[VK_F5_INDEX][3] = null;

        keyTable[VK_F6_INDEX][0] = "\033OU";
        keyTable[VK_F6_INDEX][1] = "\033Ou";
        keyTable[VK_F6_INDEX][2] = "\033Oe";
        keyTable[VK_F6_INDEX][3] = null;

        keyTable[VK_F7_INDEX][0] = "\033OV";
        keyTable[VK_F7_INDEX][1] = "\033Ov";
        keyTable[VK_F7_INDEX][2] = "\033Of";
        keyTable[VK_F7_INDEX][3] = null;

        keyTable[VK_F8_INDEX][0] = "\033OW";
        keyTable[VK_F8_INDEX][1] = "\033Ow";
        keyTable[VK_F8_INDEX][2] = "\033Og";
        keyTable[VK_F8_INDEX][3] = null;

        keyTable[VK_F9_INDEX][0] = "\033OX";
        keyTable[VK_F9_INDEX][1] = "\033Ox";
        keyTable[VK_F9_INDEX][2] = "\033Oh";
        keyTable[VK_F9_INDEX][3] = null;

        keyTable[VK_F10_INDEX][0] = "\033OY";
        keyTable[VK_F10_INDEX][1] = "\033Oy";
        keyTable[VK_F10_INDEX][2] = "\033Oi";
        keyTable[VK_F10_INDEX][3] = null;

        keyTable[VK_F11_INDEX][0] = "\030";
        keyTable[VK_F11_INDEX][1] = "\033OK";
        keyTable[VK_F11_INDEX][2] = "\034";
        keyTable[VK_F11_INDEX][3] = null;

        keyTable[VK_F12_INDEX][0] = "\020";
        keyTable[VK_F12_INDEX][1] = "\033ON";
        keyTable[VK_F12_INDEX][2] = "\033OO";
        keyTable[VK_F12_INDEX][3] = null;

        keyTable[VK_DELETE_INDEX][0] = "\177";
        keyTable[VK_DELETE_INDEX][1] = null;
        keyTable[VK_DELETE_INDEX][2] = null;
        //keyTable[VK_DELETE_INDEX][1]		= "\037";
        //keyTable[VK_DELETE_INDEX][2]		= "\035";
        keyTable[VK_DELETE_INDEX][3] = null;

        keyTable[VK_PRINTSCREEN_INDEX][0] = "\375";
        keyTable[VK_PRINTSCREEN_INDEX][1] = "\375";
        keyTable[VK_PRINTSCREEN_INDEX][2] = "\375";
        keyTable[VK_PRINTSCREEN_INDEX][3] = "\375";

        keyTable[VK_INSERT_INDEX][0] = "\033[4h";
        //keyTable[VK_INSERT_INDEX][1]		= "\033[4l";
        keyTable[VK_INSERT_INDEX][1] = null;
        keyTable[VK_INSERT_INDEX][2] = null;
        keyTable[VK_INSERT_INDEX][3] = null;

        // ALT-C copy
        keyTable[VK_C_ALT_INDEX][0] = "\033O[";
        keyTable[VK_C_ALT_INDEX][1] = "\033O{";
        keyTable[VK_C_ALT_INDEX][2] = "\033Ok";
        keyTable[VK_C_ALT_INDEX][3] = null;

        // ALT-H help
        keyTable[VK_H_ALT_INDEX][0] = "\033O\\";
        keyTable[VK_H_ALT_INDEX][1] = "\033O|";
        keyTable[VK_H_ALT_INDEX][2] = "\033Ol";
        keyTable[VK_H_ALT_INDEX][3] = null;

        // ALT-M mark
        keyTable[VK_M_ALT_INDEX][0] = "\033OZ";
        keyTable[VK_M_ALT_INDEX][1] = "\033Oz";
        keyTable[VK_M_ALT_INDEX][2] = "\033Oj";
        keyTable[VK_M_ALT_INDEX][3] = null;

        // Numeric Minus (with Num-Lock on)
        keyTable[VK_NUMERIC_MINUS_INDEX][0] = "\033Oo";
        keyTable[VK_NUMERIC_MINUS_INDEX][1] = null;
        keyTable[VK_NUMERIC_MINUS_INDEX][2] = null;
        keyTable[VK_NUMERIC_MINUS_INDEX][3] = null;

        // Numeric Plus (with Num-Lock on)
        keyTable[VK_NUMERIC_PLUS_INDEX][0] = null;
        keyTable[VK_NUMERIC_PLUS_INDEX][1] = "\n";
        keyTable[VK_NUMERIC_PLUS_INDEX][2] = null;
        keyTable[VK_NUMERIC_PLUS_INDEX][3] = null;

    }

    // character set
    public static final int G0 = 0;
    public static final int G1 = 1;
    public static final int G2 = 2;
    public static final int G2_1 = 3;
    public static final int G3 = 4;

    // state machine
    public static final int TERM_START = 0;
    public static final int TERM_STATE_ESC = 1;
    public static final int TERM_CSI = 2;
    public static final int TERM_MCW = 3;
    public static final int STATE_ESC_G = 7;

    public static final int CSINORMAL = 0;
    public static final int CSIDEC = 1;
    public static final int CSIPRIVATE = 2;

    private char[] ScrBuffer = new char[132];

    private void vput(Graphics g, int ch) {
        boolean caretMoved = false;
        int From, To;
        int x, y;
        int i, j;
        int Count;
        int Pos1, Pos2;
        int Col;
        int Row;
        int MaxCols;
        int State;
        boolean bIgnoreClientPacketChars = false;
        boolean bTextChar;
        int Attrib = 0;
        int chTmp;

        for (i = 0; i < terminalVputListenerCnt; i++) {
            if (terminalVputListeners[i] != null)
                terminalVputListeners[i].vput(g, ch);
        }

        if (ch == '\021'
            || ch == '\023')   // ignore XON/XOFF for now
            return;

        if (selectionActive)
            selectionActive = !selectionActive;

        Col = scrData.col;
        Row = scrData.row;
        MaxCols = scrData.maxCols;
        State = scrData.state;

        if (State == TERM_START) {
            chTmp = ((scrData.cSet == G1) ?
                ScrData.G1Tab[ch] :
                ((scrData.cSet == G2 || scrData.cSet == G2_1) ?
                    ScrData.G2Tab[ch] :
                    ((scrData.cSet == G3) ?
                        ScrData.G3Tab[ch] :
                        ch
                    )
                )
            );
            switch (autodetectModeState) {
                case AUTODETECTMODE_STATE_PROMPT_START:
                    if (lawsonMode
                        || unixMode) {
                        switch (chTmp) {
                            case '\n':
                                lastHostPrompt = null;
                                break;

                            default:
                                if (lastHostPrompt == null)
                                    lastHostPrompt = "" + (char) chTmp;
                                else
                                    lastHostPrompt += (char) chTmp;
                                break;
                        }
                    }
                    break;

                case AUTODETECTMODE_STATE_USERCMD_START:
                    switch (chTmp) {
                        case '\n':
                            setAutodetectModeState(AUTODETECTMODE_STATE_PROMPT_START);
                            //
                            if (lastHostPrompt != null
                                && lastUserCommandLine != null
                                && ((hostPrompt != null && lastHostPrompt.endsWith(hostPrompt))
                                || (hostPrompt == null && (lastHostPrompt.endsWith("$ ")
                                || lastHostPrompt.endsWith("# ")
                                || lastHostPrompt.endsWith("> "))))) {
                                StringTokenizer st = new StringTokenizer(lastUserCommandLine);
                                if (st.countTokens() > 0)
                                    setLastUserCommandWord(st.nextToken());
                                else
                                    setLastUserCommandWord(UNKNOWNCMD_STR);
                            } else
                                setLastUserCommandWord(UNKNOWNCMD_STR);
                            break;

                        case '\r':
                            break;

                        default:
                            if (lastUserCommandLine == null)
                                lastUserCommandLine = "" + (char) chTmp;
                            else
                                lastUserCommandLine += (char) chTmp;
                            break;
                    }
                    break;

                case AUTODETECTMODE_STATE_PROCESSING:
                    if (lawsonMode
                        || unixMode) {
                        switch (ch) {
                            case '\n':      // LF
                                setAutodetectModeState(AUTODETECTMODE_STATE_PROMPT_START);
                                // store in command history later -
                                lastUserCommandLine = null;
                                break;
                        }
                        break;
                    }

            }
        }

        switch (State) {
            case TERM_START:
                switch (ch) {
                    case '\000':    // NUL
                        break;

                    case '\007':    // BEL
                        flashScreen();
                        drawCaret(Row, Col);
                        break;

                    case '\b':      // BS
                        if (--Col < 0)
                            Col = 0;
                        moveCaret(Row, Col);
                        break;

                    case '\t':      // HT
                        Col = Math.min((Col / 8 + 1) * 8,
                            MaxCols - 1);
                        moveCaret(Row, Col);
                        break;

                    case '\n':      // LF
                    case '\013':    // VT
                    case '\014':    // FF
                        Row++;
                        if (Row > scrData.scrollLines - 1) {
                            Row = scrData.scrollLines - 1;
                            vScroll(scrData, g, 0, scrData.scrollLines - 1, 1, MaxCols, true);
                            // erase the scrolled cursor image
                            TermPaintWholeScr(scrData, g, 0, Row - 1, 0, Row - 1);
                        }
                        moveCaret(Row, Col);
                        break;

                    case '\r':      // CR
                        Col = 0;
                        moveCaret(Row, Col);
                        break;

                    case '\016':    // SO
                        scrData.cSet = G1;
                        break;

                    case '\017':    // SI
                        scrData.cSet = G0;
                        break;

                    case '\033':    // ESC
                        State = TERM_STATE_ESC;
                        break;

                    case '\177':    // DEL
                        break;

                    case 999:
                    default:
                        Attrib = scrData.attrib;
                        bTextChar = (scrData.cSet == G0 || scrData.cSet == G3);
                        ch = ((scrData.cSet == G1) ?
                            ScrData.G1Tab[ch] :
                            ((scrData.cSet == G2 || scrData.cSet == G2_1) ?
                                ScrData.G2Tab[ch] :
                                ((scrData.cSet == G3) ?
                                    ScrData.G3Tab[ch] :
                                    ch
                                )
                            )
                        );
                        //hCurrFont	= getCurrentFont();
                        scrData.scrChars[Row][Col] = (bTextChar ? ch : ch + 256);
                        scrData.scrAttrs[Row][Col] = scrData.attrib;
                        //SETCHARINSAVEDARRAY(scrData, Row, Col, bTextChar, ch);
                        //SETATTRINSAVEDARRAY(scrData, Row, Col);


                        ScrBuffer[0] = (char) ch;
                        scrData.lastChar = ch + (bTextChar ? 0 : 256);

                        TermPaintWholeScr(scrData, g, Col, Row, Col + 1, Row);
					/*
					{
						g.setColor(options.textAttribs[Attrib].back);
						g.fillRect( Col * scrData.maxCharWidth, Row * scrData.charHeight, scrData.maxCharWidth, scrData.charHeight);
						g.setColor(options.textAttribs[Attrib].fore);
						if (bTextChar)
						{
							g.drawChars(ScrBuffer, 0, 1, Col * scrData.maxCharWidth, (Row+1) * scrData.charHeight - scrData.charDescent);
						}
						else
						{
							scrData.drawWinPTGraphicsChar(g, Row, Col, ch);
						}
						if (options.textAttribs[Attrib].underLine)
							g.drawLine( Col * scrData.maxCharWidth, (Row+1) * scrData.charHeight -1, (Col+1) * scrData.maxCharWidth, (Row+1) * scrData.charHeight -1);
					}
					*/

                        Col++;
                        if (Col >= MaxCols) {
                            if (scrData.lineWrap) {
                                Col = 0;
                                Row++;
                                if (Row > scrData.scrollLines - 1) {
                                    Row = scrData.scrollLines - 1;
                                    vScroll(scrData, g, 0, scrData.scrollLines - 1, 1, MaxCols, true);
                                }
                            } else
                                Col = MaxCols - 1;
                        }
                        if (scrData.cSet == G2_1)
                            scrData.cSet = scrData.ocSet;
                        moveCaret(Row, Col);
                        break;
                }
                break;
            case TERM_STATE_ESC:
                switch (ch) {
                    case '\033':    // ESC
                        State = TERM_STATE_ESC;
                        break;

                    case 'D':   // IND
                        Row++;
                        if (Row > scrData.scrollLines - 1) {
                            Row = scrData.scrollLines - 1;
                            vScroll(scrData, g, 0, scrData.scrollLines - 1, 1, MaxCols, true);
                        }
                        moveCaret(Row, Col);
                        State = TERM_START;
                        break;

                    case 'E':   // NEL
                        Col = 0;
                        Row++;
                        if (Row > scrData.scrollLines - 1) {
                            Row = scrData.scrollLines - 1;
                            vScroll(scrData, g, 0, scrData.scrollLines - 1, 1, MaxCols, true);
                        }
                        moveCaret(Row, Col);
                        State = TERM_START;
                        break;

                    case 'G':
                        State = STATE_ESC_G;
                        break;

                    case 'M':   // RI
                        --Row;
                        if (Row < 0) {
                            Row = 0;
                            vScroll(scrData, g, 0, scrData.scrollLines - 1, 1, MaxCols, false);
                        }
                        moveCaret(Row, Col);
                        State = TERM_START;
                        break;

                    case 'N':   // SS2
                        scrData.ocSet = scrData.cSet;
                        scrData.cSet = G2_1;
                        State = TERM_START;
                        break;

                    case '[':   // CSI
                        scrData.first = true;
                        State = TERM_CSI;
                        break;

                    case ']':
                        State = TERM_MCW;
                        break;

                    default:
                        State = TERM_START;
                        break;
                }
                break;

            case TERM_CSI:
                if (scrData.first) {
                    scrData.first = false;
                    scrData.pSub = 0;
                    for (i = 0; i < scrData.param.length; i++)
                        scrData.param[i] = 0;
                    if (ch == '=') {
                        scrData.csiType = CSIPRIVATE;
                        break;
                    } else if (ch == '?') {
                        scrData.csiType = CSIDEC;
                        break;
                    } else
                        scrData.csiType = CSINORMAL;
                }

                if (ch >= '0' && ch <= '9')
                    scrData.param[scrData.pSub] = scrData.param[scrData.pSub] * 10 + (ch - '0');
                else if (ch == ';')
                    scrData.pSub++;
                else if (scrData.csiType == CSIDEC) {
                    switch (ch) {
                        case '\033':    // ESC
                            State = TERM_STATE_ESC;
                            break;

                        case 'h':   // DECSM
                            for (i = 0; i <= scrData.pSub; i++) {
                                if (scrData.param[i] == 7)
                                    scrData.lineWrap = true;
// Change column to 132 columns    *****************************************
                                else if (scrData.param[i] == 3) {
                                    MaxCols = scrData.maxCols = 132;
                                    Row = scrData.row = 0;
                                    Col = scrData.col = 0;
                                    scrData.attrib = 0;
                                    if (debug)
                                        System.out.println("Set scrData.attrib = 0");
                                    scrData.init(scrData.maxRows, MaxCols, getFont());
                                    repaint();
                                    moveCaret(Row, Col);
                                    //PostMessage(pTermData->hWnd, WMU_PRIVATE_CALC_SIZE, (WPARAM)0, (LPARAM)0);
                                }
                            }
                            State = TERM_START;
                            break;

                        case 'l':   // DECRM
                            for (i = 0; i <= scrData.pSub; i++) {
                                if (scrData.param[i] == 7)
                                    scrData.lineWrap = false;
// Change column to 80 columns    *****************************************
                                else if (scrData.param[i] == 3) {
                                    MaxCols = scrData.maxCols = 80;
                                    Row = scrData.row = 0;
                                    Col = scrData.col = 0;
                                    scrData.attrib = 0;
                                    if (debug)
                                        System.out.println("Changed to 80 columns, set scrData.attrib = 0");
                                    scrData.init(scrData.maxRows, MaxCols, getFont());
                                    repaint();
                                    moveCaret(Row, Col);
                                    //PostMessage(pTermData->hWnd, WMU_PRIVATE_CALC_SIZE, (WPARAM)0, (LPARAM)0);
                                }
                            }
                            State = TERM_START;
                            break;

                        default:
                            State = TERM_START;
                            break;
                    }
                } else if (scrData.csiType == CSIPRIVATE) {
                    switch (ch) {
                        case '\033':    // ESC
                            State = TERM_STATE_ESC;
                            break;

                        case '@':   // CTSLP
                            if (scrData.param[0] == 0)    // message line
                            {
                                Row = 0;
                                Col = Math.min(Math.max(scrData.param[1] - 1, 0), MaxCols - 1);
                                moveCaret(Row, Col);
                            } else if (scrData.param[0] == 2)   // function line
                            {
                                Row = scrData.scrollLines;
                                Col = Math.min(Math.max(scrData.param[1] - 1, 0), MaxCols - 1);
                                moveCaret(Row, Col);
                            }
                            State = TERM_START;
                            break;

                        case 'C':   // CTVIS
                            if (scrData.param[0] == 0)    // normal
                                scrData.curStatus = true;//Set Cursor On
                            else if (scrData.param[0] == 1 || scrData.param[0] == 2)    // invisible
                                scrData.curStatus = false;//Set Cursor Off
                            State = TERM_START;
                            break;

                        case 'L':   // CTLW
                            MaxCols = Math.min(Math.max(scrData.param[0], 1), 132);
                            State = TERM_START;
                            break;

                        case 'N':   // CTSNF
                            if (scrData.param[0] < 8)
                                scrData.national = scrData.param[0];
                            State = TERM_START;
                            break;

                        case 'S':   // CTSU
                            vScroll(scrData, g, Math.min(Math.max(scrData.param[0] - 1, 0), scrData.scrollLines - 1), Math.min(Math.max(scrData.param[1] - 1, 0), scrData.scrollLines - 1),
                                Math.max(scrData.param[2], 1), MaxCols, true);
                            State = TERM_START;
                            break;

                        case 'T':   // CTSD
                            vScroll(scrData, g, Math.min(Math.max(scrData.param[0] - 1, 0), scrData.scrollLines - 1), Math.min(Math.max(scrData.param[1] - 1, 0), scrData.scrollLines - 1),
                                Math.max(scrData.param[2], 1), MaxCols, false);
                            State = TERM_START;
                            break;

                        case 'e':   // CTENQ
                            // CPut('\033');  CPut('[');  CPut('>');
                            // CPut(EnqTerm(ScrVars));
                            ScrBuffer[0] = '\033';
                            ScrBuffer[1] = '[';
                            ScrBuffer[2] = '>';
                            ScrBuffer[3] = enqTerm();
                            send(ScrBuffer, 0, 4);
                            State = TERM_START;
                            break;

                        case 'h':   // CTSM
                            for (i = 0; i <= scrData.pSub; i++)
                                if (scrData.param[i] == 0)
                                    setAltGo(true);
                                else if (scrData.param[i] == 1)
                                    vCur(scrData.overType = true);
                                else if (scrData.param[i] == 2)
                                    scrData.localEdit = true;
                            State = TERM_START;
                            break;

                        case 'l':   // CTRM
                            for (i = 0; i <= scrData.pSub; i++) {
                                if (scrData.param[i] == 0)
                                    setAltGo(false);
                                else if (scrData.param[i] == 1)
                                    vCur(scrData.overType = false);
                                else if (scrData.param[i] == 2)
                                    scrData.localEdit = false;
                            }
                            State = TERM_START;
                            break;

                        case 'm':   // CTPGR
                            State = TERM_START;
                            break;

                        // Rectangular duplicate characters. Also need to store attribute - 11/18/92 BKP
                        case 'p':   // CTPCH
                            ch = scrData.lastChar;
                            bTextChar = (ch < 256);
//	zzzzzz					i = min(Col + max(scrData.param[1], 1), MaxCols);
//	zzzzzz					j = min(Row + max(scrData.param[0], 1), scrData.scrollLines-1);
                            i = Math.min(Col + scrData.param[1], MaxCols);
                            j = Math.min(Row + scrData.param[0], scrData.scrollLines - 1);

                            /* Use TermPaintWholeScr -- instead */
                            for (y = Row; y < j; y++) {
                                Count = 0;
                                for (x = Col; x < i; x++) {
                                    scrData.scrChars[y][x] = ch;
                                    scrData.scrAttrs[y][x] = scrData.attrib;
                                    if (debug)
                                        System.out.println("Row " + y + ", Col " + x + " attribute set to " + scrData.attrib + " in ESC [ = p");
                                }
                            }
                            TermPaintWholeScr(scrData, g, Col, Row, i, j);
						/*
						g.setColor(options.textAttribs[Attrib].back);
						g.fillRect( Col * scrData.maxCharWidth, Row * scrData.charHeight, (i - Col + 1) * scrData.maxCharWidth, (j - Row ) * scrData.charHeight);
						g.setColor(options.textAttribs[Attrib].fore);

						for (y = Row; y < j; y++)
						{
							Count = 0;
							for (x = Col; x < i; x++)
							{
								ScrBuffer[Count++] = (char)ch;

								scrData.scrChars[y][x] = ch;
								scrData.scrAttrs[y][x] = scrData.attrib;
								if (debug)
									System.out.println("Row " + y + ", Col " + x + " attribute set to " + scrData.attrib + " in ESC [ = p");
							}
							//if (scrData.NoMinimized)
							//{
								if (bTextChar)
									g.drawChars(ScrBuffer, 0, Count, Col * scrData.maxCharWidth, (y+1) * scrData.charHeight - scrData.charDescent);
								else
									scrData.drawWinPTGraphicsChars(g, y, Col, ScrBuffer, Count);

								if (options.textAttribs[Attrib].underLine)
									g.drawLine( Col * scrData.maxCharWidth, (y+1) * scrData.charHeight -1, (Col+1) * scrData.maxCharWidth, (y+1) * scrData.charHeight -1);
							//}
						}
						*/

                            State = TERM_START;
                            break;

                        default:
                            State = TERM_START;
                            break;
                    }
                } else    // CSINORMAL
                {
                    switch (ch) {
                        case '\033':    // ESC
                            State = TERM_STATE_ESC;
                            break;

                        case '@':   // ICH

// Move block of data ******************************************************
                            j = Math.min(Math.max(scrData.param[0], 1), MaxCols - Col);
                            Pos1 = MaxCols - 1;
                            Pos2 = Pos1 - j;

                            for (i = 0; i < MaxCols - Col - j; i++) {
                                scrData.scrChars[Row][Pos1 - i] = scrData.scrChars[Row][Pos2 - i];
                                scrData.scrAttrs[Row][Pos1 - i] = scrData.scrAttrs[Row][Pos2 - i];
                            }

                            for (i = 0; i < j; i++) {
                                scrData.scrChars[Row][Col + i] = ' ';
                                scrData.scrAttrs[Row][Col + i] = 0;
                            }

                            TermPaintWholeScr(scrData, g, Col, Row, MaxCols - 1, Row);
                            State = TERM_START;
                            break;

                        case 'A':   // CUU
                            Row = Math.max(Row - Math.max(scrData.param[0], 1), 0);
                            moveCaret(Row, Col);
                            State = TERM_START;
                            break;

                        case 'B':   // CUD
                        case 'd':   // VPR
                            Row = Math.min(Row + Math.max(scrData.param[0], 1), scrData.scrollLines - 1);
                            moveCaret(Row, Col);
                            State = TERM_START;
                            break;

                        case 'C':   // CUF
                        case 'a':   // HPR
                            Col = Math.min(Col + Math.max(scrData.param[0], 1), MaxCols - 1);
                            moveCaret(Row, Col);
                            State = TERM_START;
                            break;

                        case 'D':   // CUB
                            Col = Math.max(Col - Math.max(scrData.param[0], 1), 0);
                            moveCaret(Row, Col);
                            State = TERM_START;
                            break;

                        case 'E':   // CNL
                            Col = 0;
                            Row = Math.min(Row + Math.max(scrData.param[0], 1), scrData.scrollLines - 1);
                            moveCaret(Row, Col);
                            State = TERM_START;
                            break;

                        case 'e':   // VPA
                            Row = Math.min(Math.max(scrData.param[0] - 1, 0), scrData.scrollLines - 1);
                            moveCaret(Row, Col);
                            State = TERM_START;
                            break;

                        case 'F':   // CPL
                            Col = 0;
                            Row = Math.max(Row - Math.max(scrData.param[0], 1), 0);
                            moveCaret(Row, Col);
                            State = TERM_START;
                            break;

                        case 'G':   // CHA
                        case '`':   // HPA
                            Col = Math.min(Math.max(scrData.param[0] - 1, 0), MaxCols - 1);
                            moveCaret(Row, Col);
                            State = TERM_START;
                            break;

                        case 'H':   // CUP
                        case 'f':   // HVP
                            Row = Math.min(Math.max(scrData.param[0] - 1, 0), scrData.scrollLines - 1);
                            Col = Math.min(Math.max(scrData.param[1] - 1, 0), MaxCols - 1);
                            moveCaret(Row, Col);
                            if (debug)
                                System.out.println("ESC [ H encountered. Moving caret to row " + Row + ", col " + Col);
                            State = TERM_START;
                            break;

                        case 'J':   // ED
                            if (scrData.param[0] == 0) {
// Clear (Paint) the block ................
                                if (Col == 0)
                                    vScroll(scrData, g, Row, scrData.scrollLines - 1, 0, MaxCols, true);
                                else {
                                    for (i = Col; i <= MaxCols - 1; i++) {
                                        scrData.scrChars[Row][i] = ' ';
                                        scrData.scrAttrs[Row][i] = 0;
                                    }
                                    scrData.lastChar = ' ';
                                    TermPaintWholeScr(scrData, g, Col, Row, MaxCols - 1, Row);
                                    if (Row < scrData.scrollLines - 1)
                                        vScroll(scrData, g, Row + 1, scrData.scrollLines - 1, 0, MaxCols, true);
                                }
                            } else if (scrData.param[0] == 1) {
                                if (Col == MaxCols - 1)
                                    vScroll(scrData, g, 0, Row, 0, MaxCols, true);
                                else {
// Paint the region ....................
                                    if (Row > 0)
                                        vScroll(scrData, g, 0, Row - 1, 0, MaxCols, true);

                                    for (i = 0; i <= Col; i++) {
                                        scrData.scrChars[Row][i] = ' ';
                                        scrData.scrAttrs[Row][i] = 0;
//									scrData.scrAttrs[Row *132 +i] = -1; // zzzzzz
                                    }
                                    scrData.lastChar = ' ';

                                    TermPaintWholeScr(scrData, g, 0, Row, Col, Row);
                                }
                            } else if (scrData.param[0] == 2)
                                vScroll(scrData, g, 0, scrData.scrollLines - 1, 0, MaxCols, true);

                            State = TERM_START;
                            break;

                        case 'K':   // EL
                            if (scrData.param[0] == 0) {
                                From = Col;
                                To = MaxCols - 1;
                            } else if (scrData.param[0] == 1) {
                                From = 0;
                                To = Col;
                            } else if (scrData.param[0] == 2) {
                                From = 0;
                                To = MaxCols - 1;
                            } else {
                                From = 0;
                                To = -1;
                            }

                            for (i = From; i <= To; i++) {
                                scrData.scrChars[Row][i] = ' ';
                                scrData.scrAttrs[Row][i] = 0;
//							scrData.scrAttrs[Row][i] = -1; // zzzzzz
                            }
                            scrData.lastChar = ' ';

// Clear (Paint) the line "From" to "To" ..........
                            if (To != -1)
                                TermPaintWholeScr(scrData, g, From, Row, To, Row);
                            State = TERM_START;
                            break;

                        case 'L':   // IL
                            vScroll(scrData, g, Row, scrData.scrollLines - 1, Math.max(scrData.param[0], 1), MaxCols, false);
                            State = TERM_START;
                            break;

                        case 'M':   // DL
                            vScroll(scrData, g, Row, scrData.scrollLines - 1, Math.max(scrData.param[0], 1), MaxCols, true);
                            State = TERM_START;
                            break;

                        case 'm':   // SGR
                            for (i = 0; i <= scrData.pSub; i++) {
                                if (scrData.param[i] == 0)
                                    for (j = 1; j < 10; j++)
                                        scrData.sgr[j] = 0;
                                else if (scrData.param[i] == 10)
                                    scrData.cSet = G0;
                                else if (scrData.param[i] == 11)
                                    scrData.cSet = G1;
                                else if (scrData.param[i] == 12)
                                    scrData.cSet = G2;
                                else if (scrData.param[i] == 13)
                                    scrData.cSet = G3;
                                else if (scrData.param[i] < 10)
                                    scrData.sgr[scrData.param[i]] = 1;
                            }
                            scrData.attrib = (scrData.sgr[2]) | (scrData.sgr[4] * 2) | (scrData.sgr[5] * 4)
                                | (scrData.sgr[6] * 4) | (scrData.sgr[7] * 8);
                            //scrData.attrib = (scrData.sgr[2] << 0) | (scrData.sgr[4] << 1) | (scrData.sgr[5] << 2)
                            //	   | (scrData.sgr[6] << 2) | (scrData.sgr[7] << 3);
                            if (debug) {
                                System.out.println("scrData.attrib set to " + scrData.attrib + " in ESC [ m");
                                System.out.println("Using formula for attribute as [(" + scrData.sgr[2] + "<< 0) | (" + scrData.sgr[4] + "<< 1) | (" + scrData.sgr[5] + "<< 2) | (" + scrData.sgr[6] + "<< 2) | (" + scrData.sgr[7] + "<< 3)");
                            }

                            //if (scrData.NoMinimized)
                            //{
                            if (scrData.attrib > -1) {
								/*
								g.setColor(options.textAttribs[Attrib].back);
								g.fillRect( Col * scrData.maxCharWidth, Row * scrData.charHeight, scrData.maxCharWidth, scrData.charHeight);
								g.setColor(options.textAttribs[Attrib].fore);
								g.drawChars(ScrBuffer, 0, 1, Col * scrData.maxCharWidth, (Row+1) * scrData.charHeight - scrData.charDescent);
								if (options.textAttribs[Attrib].underLine)
									g.drawLine( Col * scrData.maxCharWidth, (Row+1) * scrData.charHeight -1, (Col+1) * scrData.maxCharWidth, (Row+1) * scrData.charHeight -1);

								*/

                                //SET_ATTRIB(g, scrData.attrib);
                            }
                            //}
                            //scrAttrs[Row][Col] = scrData.attrib;
                            //if (debug)
                            //	System.out.println("row " + Row + ", col " + Col + " attribute set to " + scrData.attrib + " in ESC [ m");
                            State = TERM_START;
                            break;

                        case 'n':   // DSR
                            State = TERM_START;
                            break;

                        case 'P':   // DCH
// Move block of data .....................................................
// zzzzzz						j = min(max(scrData.param[0], 1), MaxCols - Col);
                            j = Math.min(scrData.param[0], MaxCols - Col);
                            if (j < 1)
                                j = 1;
                            Pos1 = Col;
                            Pos2 = Pos1 + j;

                            for (i = 0; i < MaxCols - Col - j; i++) {
                                scrData.scrChars[Row][Pos1 + i] = scrData.scrChars[Row][Pos2 + i];
                                scrData.scrAttrs[Row][Pos1 + i] = scrData.scrAttrs[Row][Pos2 + i];
//							RamTextOut(ScrVars, hDC, Pos1+i, Row);
                            }

                            Pos1 = MaxCols - j;
                            for (i = 0; i < j; i++) {
                                scrData.scrChars[Row][Pos1 + i] = ' ';
                                scrData.scrAttrs[Row][Pos1 + i] = 0;
//							scrData.scrAttrs[Row][Pos1+i] = -1;// zzzzzz
                            }
//	zzzzzz					TermPaintWholeScr(ScrVars, hDC, Pos1, Row, Pos1 + j -1, Row);
                            TermPaintWholeScr(scrData, g, Col, Row, MaxCols - 1, Row);
                            //
                            // j = min(max(scrData.param[0], 1), MaxCols - Col);
                            // Screen1 = ScreenBase[Row] + Col;
                            // Screen2 = Screen1 + j;
                            // for (i = 0; i < MaxCols - Col - j; i++)
                            //	*Screen1++ = *Screen2++;
                            // Screen1 = ScreenBase[Row] + MaxCols - j;
                            // for (i = 0; i < j; i++)
                            //	*Screen1++ = pAttribTab[0];


                            State = TERM_START;
                            break;

                        case 'q':   // CTLED
                            State = TERM_START;
                            break;

                        case 'S':   // SU
                            vScroll(scrData, g, 0, scrData.scrollLines - 1, Math.max(scrData.param[0], 1), MaxCols, true);
                            State = TERM_START;
                            break;

                        case 'T':   // SD
                            vScroll(scrData, g, 0, scrData.scrollLines - 1, Math.max(scrData.param[0], 1), MaxCols, false);
                            State = TERM_START;
                            break;

                        default:
                            State = TERM_START;
                            break;
                    }
                }
                break;

//			scrData.state = TERM_START; BKP 10/28/93 Unreachable. Commented out
//			return;

            default:    // bad state!
                State = TERM_START;
                break;
        }

        scrData.col = Col;
        scrData.row = Row;
        scrData.maxCols = MaxCols;
        scrData.state = State;
    }

    /**
     * This variable is used for fast scrolling.
     */
    private boolean fastScroll;
    // -------------------------------------------------------------------------
    //
    //  VScroll scrolls the screen. Row numbers begin with 0. The scrolling region
    //    does not include the last Row (Row #26 or LINE). This last row is for
    //  status and other messages.
    //
    //  Usage: VScroll(SCREEN_DATA * ScrVars, int FromRow, int ToRow, int Count, int CharWidth, boolean bUP);
    //
    //  where:
    //      ScrVars:   Pointer to SCREEN_DATA structure.
    //      FromRow:  is the beginning Row number of (and included in) the scrolling region..
    //      ToRow:    is the last Row number of (and included in) the scrolling region.
    //      Count:     number of Rows to scroll.
    //      CharWidth: is the number of character columns that will be scrolled.
    //      UP: 		direction can be true (for UP) or false for DOWN.
    //
    //  modified: 11/17/92 BKP. To make the behavior consistent and add comments.
    //

    private void vScroll(ScrData scrData, Graphics g, int From, int To, int Count, int Width, boolean bUP) {
        int i,
            j;
        int Bottom;
        int Right;

        if (debug)
            System.out.println("calling vScroll(), From=" + From + ",To=" + To + ",Count=" + Count + ",Width=" + Width + "Up=" + bUP);
        if (Count == 0) {
            for (i = From; i <= To; i++) {
                for (j = 0; j < scrData.maxCols; j++) {
                    scrData.scrChars[i][j] = ' ';
                    scrData.scrAttrs[i][j] = 0;
//				scrData.scrAttrs[i][j] = -1; // zzzzzz
                }
            }
            if (Width == scrData.maxCols
                && From <= 0
                && To >= scrData.scrollLines - 1) {
                paint(g);
            } else
                TermPaintWholeScr(scrData, g, 0, From, Width - 1, To); // BKP 11/14/94
        } else {
            if (bUP) {
                int limit = To + 1 - Count;
                for (i = From; i < limit; i++) {
                    for (j = 0; j < Width; j++) {
                        scrData.scrChars[i][j] = scrData.scrChars[i + Count][j];
                        scrData.scrAttrs[i][j] = scrData.scrAttrs[i + Count][j];
                    }
                }

                int startLine = To - Count + 1;
                int endLine = To;
                for (i = startLine; i <= endLine; i++) {
                    for (j = 0; j < Width; j++) {
                        scrData.scrChars[i][j] = ' ';
                        scrData.scrAttrs[i][j] = 0;
//					scrData.scrAttrs[i][j] = -1; // zzzzzz
                    }
                }
            } else {
                //for (i = 0 ; i < To-From-Count ; i++)
                //{
                //	for (j = 0 ; j < Width ; j++)
                //	{
                //		scrChars[To-i][j] = scrChars[To-Count-i][j];
                //		scrAttrs[To-i][j] = scrAttrs[To-Count-i][j];
                //	}
                //}

                for (i = To; i >= From + Count; i--) {
                    for (j = 0; j < Width; j++) {
                        scrData.scrChars[i][j] = scrData.scrChars[i - Count][j];
                        scrData.scrAttrs[i][j] = scrData.scrAttrs[i - Count][j];
                    }
                }

                //for (i = To-From+1; i > Count; --i)
                //{
                //	for (j = 0 ; j < Width ; j++)
                //	{            24-i                24-1    -i
                //		scrChars[To-i][j] = scrChars[To-Count-i][j];
                //		scrAttrs[To-i][j] = scrAttrs[To-Count-i][j];
                //	}
                //}

                for (i = 0; i < Count; i++) {
                    for (j = 0; j < Width; j++) {
                        scrData.scrChars[From + i][j] = ' ';
                        scrData.scrAttrs[From + i][j] = 0;
//					scrData.scrAttrs[From+i][j] = -1; // zzzzzz
                    }
                }
            }

            if (fastScroll)
                return;

            // if this is substantially the whole screen then repaint
            if (From < 2
                && To >= scrData.scrollLines - 1)
                paint(g);
            else {
                int scrollRgnX = 0;
                int scrollRgnY = From * scrData.charHeight;
                int scrollRgnWidth = Width * scrData.maxCharWidth;
                int scrollRgnHeight = (To - From + 1) * scrData.charHeight;

                int scrollYPixels = scrData.charHeight * Count;

                if (Count > To - From)
                    g.clearRect(scrollRgnX, scrollRgnY, scrollRgnWidth, scrollRgnHeight);
                else {
                    if (bUP) {
                        g.copyArea(0, scrollRgnY + scrollYPixels, scrollRgnWidth, scrollRgnHeight - scrollYPixels, 0, -scrollYPixels);
                        g.clearRect(0, scrollRgnY + scrollRgnHeight - scrollYPixels, scrollRgnWidth, scrollYPixels);
                        // paint background of cleared lines
                        TermPaintWholeScr(scrData, g, 0, To - Count, Width - 1, To);
                        // repaint character location with Cursor, that may have been copied
                        if (scrData.caretRow <= To
                            && scrData.caretRow > From)
                            TermPaintWholeScr(scrData, g, scrData.caretCol, scrData.caretRow - 1, scrData.caretCol, scrData.caretRow - 1);
                    } else {
                        g.copyArea(0, scrollRgnY, scrollRgnWidth, scrollRgnHeight - scrollYPixels, 0, scrollYPixels);
                        g.clearRect(0, scrollRgnY, scrollRgnWidth, scrollYPixels);
                        // paint background of cleared lines
                        TermPaintWholeScr(scrData, g, 0, From, Width - 1, From + Count);
                        // repaint character location with Cursor, that may have been copied
                        if (scrData.caretRow < To
                            && scrData.caretRow >= From)
                            TermPaintWholeScr(scrData, g, scrData.caretCol, scrData.caretRow + 1, scrData.caretCol, scrData.caretRow + 1);
                    }
                }
            }
        }
    }

    //	-------------------------------------------------------------------------
    //	TermPaintWholeScr - Repaint screen between the specified co-ordinates
    //
    void TermPaintWholeScr(ScrData scrData, Graphics g, int x1, int y1, int x2, int y2) {
        if (!paintScreenFlag)
            return;

        int i, j, nLim, nLen;
        int Attrib;
        char[] tmpStr = scrData.tmpStr;
        boolean bGraph;

        // are y co-ord within extended bounds
        y1 = y1 < 0 ? 0 : (y1 > scrData.maxRows ? scrData.maxRows : y1);
        y2 = y2 < 0 ? 0 : (y2 > scrData.maxRows ? scrData.maxRows : y2);
        // are x co-ord within bounds
        x1 = x1 < 0 ? 0 : (x1 >= scrData.maxCols ? scrData.maxCols - 1 : x1);
        x2 = x2 < 0 ? 0 : (x2 >= scrData.maxCols ? scrData.maxCols - 1 : x2);

        //if (backgroundImage != null)
        //{
        //	g.drawImage(backgroundImage, 0, 0, this);
        //	//System.out.println("TODO: Print the background image");
        //}

        if (lawsonMode) {
            TermPaintWholeScrLawsonMode(scrData, g, x1, y1, x2, y2);
            if (selectionActive
                && inSelection(x1, y1, x2, y2))
                highLightSelection(scrData, g);
            return;
        }

        for (i = y1; i <= y2; i++) // each affected line
        {
            for (j = x1; j <= x2; ) // for the affected columns
            {
                nLen = 1;
                tmpStr[0] = (char) (scrData.scrChars[i][j] % 256);
                Attrib = scrData.scrAttrs[i][j];
                bGraph = scrData.scrChars[i][j] >= 256;
                // find out the  length of text with same Attribute and font
                nLim = x2 - j + 1;
                for (nLen = 1; nLen < nLim; nLen++) {
                    int tmpAttrib;

                    tmpStr[nLen] = (char) (scrData.scrChars[i][j + nLen] % 256);
                    tmpAttrib = scrData.scrAttrs[i][j + nLen];
                    if (tmpAttrib != Attrib)
                        break;

                    boolean bTmpGraph = (scrData.scrChars[i][j + nLen] >= 256);
                    if (bGraph != bTmpGraph)
                        break;
                }
                tmpStr[nLen] = '\0';

                int Col = j;
                int Row = i;

                if (backgroundImage == null) {
                    g.setColor(options.textAttribs[Attrib].back);
                    g.fillRect(Col * scrData.maxCharWidth, Row * scrData.charHeight, nLen * scrData.maxCharWidth, scrData.charHeight);
                } else {
                    try {
                        g.drawImage(backgroundImage,
                            Col * scrData.maxCharWidth,
                            Row * scrData.charHeight,
                            (Col + nLen) * scrData.maxCharWidth,
                            (Row + 1) * scrData.charHeight,
                            Col * scrData.maxCharWidth,
                            Row * scrData.charHeight,
                            (Col + nLen) * scrData.maxCharWidth,
                            (Row + 1) * scrData.charHeight,
                            this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //if (backgroundImage != null)
                //{
                //	g.drawImage(backgroundImage, 0, 0, this);
                //	//System.out.println("TODO: Print the background image");
                //}
                if (bGraph) {
                    if (options.graphicsCharForeground != null)
                        g.setColor(options.graphicsCharForeground);
                    else
                        g.setColor(options.textAttribs[Attrib].fore);
                    scrData.drawWinPTGraphicsChars(g, Row, Col, tmpStr, nLen, lawsonMode, unixMode, cmdMode);
                } else {
                    g.setColor(options.textAttribs[Attrib].fore);
                    g.drawChars(tmpStr, 0, nLen, Col * scrData.maxCharWidth, (Row + 1) * scrData.charHeight - scrData.charDescent);
                }
                if (options.textAttribs[Attrib].underLine)
                    g.drawLine(Col * scrData.maxCharWidth, (Row + 1) * scrData.charHeight - 1, (Col + nLen) * scrData.maxCharWidth, (Row + 1) * scrData.charHeight - 1);

                j += nLen;
            }
        }
        if (selectionActive
            && inSelection(x1, y1, x2, y2))
            highLightSelection(scrData, g);
    }

    /**
     * Highlight text selected by mouse
     */
    private synchronized void highLightSelection(ScrData scrData, Graphics g) {
        if (!selectionActive)
            return;
        int x = selectionStart.x * scrData.maxCharWidth,
            y = selectionStart.y * scrData.charHeight,
            width = (selectionEnd.x - selectionStart.x + 1) * scrData.maxCharWidth,
            height = (selectionEnd.y - selectionStart.y + 1) * scrData.charHeight;

        //System.out.println("In hightlight: selectionStart[" + selectionStart + "] selectionEnd[" + selectionEnd + "]");
        g.setXORMode(options.textAttribs[0].back);
        g.fillRect(x, y, width, height);
        //g.setColor(options.textAttribs[0].fore);
        //g.drawRect( x+1,	y+1,	width-2,	height-2);
        g.setPaintMode();
    }

    /**
     * Paint the whole screen in Lawson Mode. This mode does the following:
     * 1. Uses one color -- default white -- for background
     * 2. Paints buttons for the function line
     * 3. Uses text foreground color to paint the text
     */
    void TermPaintWholeScrLawsonMode(ScrData scrData, Graphics g, int x1, int y1, int x2, int y2) {
        int i, j, n, nLim, nLen;
        int Attrib;
        char[] tmpStr = scrData.tmpStr;
        boolean bGraph;
        boolean flagPaintLapmScrFuncs = false;

        // paint the background
        //if (options.screenBackground != null)
        //	g.setColor(options.screenBackground);
        //else
        //	g.setColor(Color.white);
        //
        //g.fillRect( x1 * scrData.maxCharWidth, y1 * scrData.charHeight, (x2-x1+1) * scrData.maxCharWidth, (y2-y1+1) * scrData.charHeight);

        for (i = y1; i <= y2; i++) // each affected line
        {
            if (i == 1
                && cmdMode == CMDMODE_LAWSON_LAPM) {
                flagPaintLapmScrFuncs = true; // set flag to paint later
                continue;
            }
            if (i == scrData.FUNCTION_BTNS_ROW
                && hasFunctionKeys()) {
                if (backgroundImage == null) {
                    g.setColor(options.textAttribs[0].back);
                    g.fillRect(x1 * scrData.maxCharWidth, i * scrData.charHeight, (x2 - x1 + 2) * scrData.maxCharWidth, scrData.charHeight);
                } else {
                    try {
                        g.drawImage(backgroundImage,
                            x1 * scrData.maxCharWidth,
                            i * scrData.charHeight,
                            (x2 + 2) * scrData.maxCharWidth,
                            (i + 1) * scrData.charHeight,
                            x1 * scrData.maxCharWidth,
                            i * scrData.charHeight,
                            (x2 + 2) * scrData.maxCharWidth,
                            (i + 1) * scrData.charHeight,
                            this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // paint function buttons only for this row
                scrData.drawFunctionBtns(g);
                continue;
            }
            for (j = x1; j <= x2; ) // for the affected columns
            {
                nLen = 1;
                tmpStr[0] = (char) (scrData.scrChars[i][j] % 256);
                Attrib = scrData.scrAttrs[i][j];
                bGraph = scrData.scrChars[i][j] >= 256;
                // find out the  length of text with same Attribute and font
                for (nLen = 1, nLim = x2 - j + 1, n = 1; n < nLim; n++, nLen++) {
                    int tmpAttrib;

                    tmpStr[nLen] = (char) (scrData.scrChars[i][j + nLen] % 256);
                    tmpAttrib = scrData.scrAttrs[i][j + nLen];
                    if (tmpAttrib != Attrib)
                        break;

                    boolean bTmpGraph = (scrData.scrChars[i][j + nLen] >= 256);
                    if (bGraph != bTmpGraph)
                        break;
                }
                tmpStr[nLen] = '\0';

                int Col = j;
                int Row = i;

                if (backgroundImage == null) {
                    g.setColor(options.textAttribs[Attrib].back);
                    g.fillRect(Col * scrData.maxCharWidth, Row * scrData.charHeight, nLen * scrData.maxCharWidth, scrData.charHeight);
                } else {
                    try {
                        g.drawImage(backgroundImage,
                            Col * scrData.maxCharWidth,
                            Row * scrData.charHeight,
                            (Col + nLen) * scrData.maxCharWidth,
                            (Row + 1) * scrData.charHeight,
                            Col * scrData.maxCharWidth,
                            Row * scrData.charHeight,
                            (Col + nLen) * scrData.maxCharWidth,
                            (Row + 1) * scrData.charHeight,
                            this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (bGraph) {
                    if (options.graphicsCharForeground != null)
                        g.setColor(options.graphicsCharForeground);
                    else
                        g.setColor(options.textAttribs[Attrib].fore);
                    scrData.drawWinPTGraphicsChars(g, Row, Col, tmpStr, nLen, lawsonMode, unixMode, cmdMode);
                } else {
                    g.setColor(options.textAttribs[Attrib].fore);
                    g.drawChars(tmpStr, 0, nLen, Col * scrData.maxCharWidth, (Row + 1) * scrData.charHeight - scrData.charDescent);
                }
                if (options.textAttribs[Attrib].underLine)
                    g.drawLine(Col * scrData.maxCharWidth, (Row + 1) * scrData.charHeight - 1, (Col + nLen) * scrData.maxCharWidth, (Row + 1) * scrData.charHeight - 1);

                j += nLen;
            }
        }
        if (flagPaintLapmScrFuncs)
            scrData.paintLawsonLapmScrFuncs(this);
    }

    private char enqTerm() {
        if (scrData.maxCols == 132)
            return 'i';
        else
            return 'h';
    }

    public void flashScreen() {
        System.out.println("TODO:flashScreen(): Complete this function. DO a visual flash of the screen");
    }

    public void showToolBar(boolean flag) {
        options.setDfltShowTools(flag);
        if (frameOptions != null)
            frameOptions.setOptions(options);
    }

    public void showStatusBar(boolean flag) {
        options.setDfltShowStatus(flag);
        if (frameOptions != null)
            frameOptions.setOptions(options);
    }

    public void setAltGo(boolean flag) {
        options.setAltGo(flag);
        scrData.setAltGo(flag);
        if (frameOptions != null)
            frameOptions.setOptions(options);
    }

    public void setDfltF12IsGo(boolean flag) {
        options.setDfltF12IsGo(flag);
        if (frameOptions != null)
            frameOptions.setOptions(options);
    }

    private FrameOptions frameOptions;

    public void showOptions() {
        if (frameOptions == null)
            frameOptions = new FrameOptions();
        frameOptions.setOptions(options);
        frameOptions.show();
    }
    //	-------------------------------------------------------------------------
    //	VCur -

    void vCur(boolean overwrite) {
        if (overwrite)
            scrData.cursorOffset = scrData.cursorAttrib.overShape * scrData.charHeight / 2;
        else
            scrData.cursorOffset = scrData.cursorAttrib.insShape * scrData.charHeight / 2;
    }

    @Override
    public void paint(Graphics g) {
        if (scrData.maxCols > 1)
            TermPaintWholeScr(scrData, g, 0, 0, scrData.maxCols - 1, scrData.maxRows);
        drawCaret(scrData.row, scrData.col);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void moveCaret(int newRow, int newCol) {
        drawCaret(newRow, newCol);
    }

    public void viewGraphicsChars() {
        PanelGraphicsChars p = new PanelGraphicsChars(scrData.maxCharWidth, scrData.charHeight, 100, scrData.scrChars);
        p.showInFrame();
    }

    PanelAttrs panelAttrs;

    public void viewCharAttrs() {
        panelAttrs = new PanelAttrs(scrData.maxCharWidth, scrData.charHeight, scrData.scrAttrs, 100);
        panelAttrs.showInFrame();
    }

    public boolean getOptionsDirty() {
        return options.getDirty();
    }

    public void setOptionsDirty(boolean flag) {
        options.setDirty(flag);
    }

    private FrameTextAttr frameTextAttr;

    public void setupTextAttrs() {
        if (frameTextAttr == null)
            frameTextAttr = new FrameTextAttr(this);
        frameTextAttr.setTextAttribs(options.textAttribs);
        frameTextAttr.show();
    }

    /**
     * Return the frame of this panel.
     */
    public Frame getParentFrame() {
        Container containerFrame = getParent();
        while (containerFrame != null) {
            if (containerFrame instanceof Frame)
                return (Frame) containerFrame;
            containerFrame = containerFrame.getParent();
        }
        // no frame in the hierarchy
        if (hiddenParentFrame == null)
            hiddenParentFrame = new Frame();
        hiddenParentFrame.setVisible(false);
        return hiddenParentFrame;
    }

    /**
     * Is the specified row and column within the selected area
     */
    private boolean inSelection(int row, int col) {
        if (selectionActive
            && row >= selectionStart.y
            && row <= selectionEnd.y
            && col >= selectionStart.x
            && col <= selectionEnd.x)
            return true;
        else
            return false;
    }

    /**
     * Do the specified start and end co-ordinates overlap the selected area
     */
    private boolean inSelection(int x1, int y1, int x2, int y2) {
        if (selectionActive) {
            try {
                Rectangle rect1 = new Rectangle(x1, y1, x2 - x1 + 1, y2 - y1 + 1);
                Rectangle rect2 = new Rectangle(selectionStart.x, selectionStart.y, selectionEnd.x - selectionStart.x + 1, selectionEnd.y - selectionStart.y + 1);
                return rect1.intersects(rect2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

  /*
  public void setCapsLocked(boolean b)
  {
	// prasad.terminal.KeyStatusListener method;
	txtCapsLock.setText(b?"CAPS":"");
  }
  public void setNumLocked(boolean b)
  {
	// prasad.terminal.KeyStatusListener method;
	txtNumLock.setText(b?"NUM":"");
  }

  public void setShiftPressed(boolean b)
  {
	// prasad.terminal.KeyStatusListener method;
	txtShiftKey.setText(b?"SHFT":"");
  }

  public void setCtrlPressed(boolean b)
  {
	// prasad.terminal.KeyStatusListener method;
	txtCtrlKey.setText(b?"CTRL":"");
  }
  */

    private boolean debug;

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean b) {
        debug = b;
        if (telnetConnection != null)
            telnetConnection.setDebug(debug);
    }

    @Override
    public void focusGained(FocusEvent e) {
        //TODO: implement this java.awt.event.FocusListener method;
        if (scrData != null)
            drawCaret(scrData.caretRow, scrData.caretCol);
    }

    @Override
    public void focusLost(FocusEvent e) {
        //TODO: implement this java.awt.event.FocusListener method;
    }
/*
	VK_ENTER
	VK_BACK_SPACE
	VK_TAB
	VK_CANCEL
	VK_CLEAR
	VK_SHIFT
	VK_CONTROL
	VK_ALT
	VK_PAUSE
	VK_CAPS_LOCK
	VK_ESCAPE
	VK_SPACE
	VK_PAGE_UP
	VK_PAGE_DOWN
	VK_END
	VK_HOME
	VK_LEFT
	VK_UP
	VK_RIGHT
	VK_DOWN
	VK_COMMA
	VK_PERIOD
	VK_SLASH

	// VK_0 thru VK_9 are the same as ASCII '0' thru '9' (0x30 - 0x39)
	VK_0
	VK_1
	VK_2
	VK_3
	VK_4
	VK_5
	VK_6
	VK_7
	VK_8
	VK_9

	VK_SEMICOLON
	VK_EQUALS

	// VK_A thru VK_Z are the same as ASCII 'A' thru 'Z' (0x41 - 0x5A)
	VK_A
	VK_B
	VK_C
	VK_D
	VK_E
	VK_F
	VK_G
	VK_H
	VK_I
	VK_J
	VK_K
	VK_L
	VK_M
	VK_N
	VK_O
	VK_P
	VK_Q
	VK_R
	VK_S
	VK_T
	VK_U
	VK_V
	VK_W
	VK_X
	VK_Y
	VK_Z

	VK_OPEN_BRACKET
	VK_BACK_SLASH
	VK_CLOSE_BRACKET

	VK_NUMPAD0
	VK_NUMPAD1
	VK_NUMPAD2
	VK_NUMPAD3
	VK_NUMPAD4
	VK_NUMPAD5
	VK_NUMPAD6
	VK_NUMPAD7
	VK_NUMPAD8
	VK_NUMPAD9
	VK_MULTIPLY
	VK_ADD
	VK_SEPARATER
	VK_SUBTRACT
	VK_DECIMAL
	VK_DIVIDE
	VK_F1
	VK_F2
	VK_F3
	VK_F4
	VK_F5
	VK_F6
	VK_F7
	VK_F8
	VK_F9
	VK_F10
	VK_F11
	VK_F12
	VK_DELETE
	VK_NUM_LOCK
	VK_SCROLL_LOCK

	VK_PRINTSCREEN
	VK_INSERT
	VK_HELP
	VK_META

	VK_BACK_QUOTE
	VK_QUOTE

	// for Asian Keyboard
	VK_FINAL
	VK_CONVERT
	VK_NONCONVERT
	VK_ACCEPT
	VK_MODECHANGE
	VK_KANA
	VK_KANJI

	// KEY_TYPED events do not have a defined keyCode.

	VK_UNDEFINED

	// KEY_PRESSED and KEY_RELEASED events which do not map to a
	// valid Unicode character do not have a defined keyChar.

	CHAR_UNDEFINED

  */

    private String optionFileName;

    public void readDefaultOptions() {
        optionFileName = null;
        if (!readOptions())
            options = new Options();
        setFont(options.getFont());
        if (frameOptions != null)
            frameOptions.setOptions(options);
        Frame f = getParentFrame();
        if (f == hiddenParentFrame) {
            invalidate();
            repaint();
        } else {
            f.invalidate();
            f.repaint();
            f.pack();
        }

    }

    private FileDialogRemote fileDialogRemote;
    private FileDialogResource fileDialogResource;
    //  private java.awt.FileDialog 		fileDialogLocal ;
    private SaveDialog saveDialog;

    static FileDialog createFileDialogLocal(
        boolean isNAV,
        boolean isIE,
        Frame parentFrame,
        boolean bSave,
        String title,
        boolean debug) {
        FileDialog fileDialogLocal = null;

        //PalTerm palTerm = (PalTerm)applet;

        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        boolean success = false;
        if (isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                //if (debug)
                //    System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        // Create file dialog box
        fileDialogLocal = new FileDialog(parentFrame,
            title,
            (bSave ? FileDialog.SAVE : FileDialog.LOAD));
        return fileDialogLocal;
    }

    static FileDialogRemote createFileDialogRemote(PalTerm palTerm, Frame parentFrame, boolean debug) {
        //PalTerm palTerm = (PalTerm)applet;

        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        boolean success = false;
        if (palTerm.isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                //if (debug)
                //    System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        FileDialogRemote fileDialogRemote;

        // Path to the "dirlist" CGI program to get a directory listing
        String dirlistPath = palTerm.cgiDir + palTerm.pgm_secdirlist + palTerm.cgiExtension;
        URL dirlistURL;
        try {
            dirlistURL = new URL(palTerm.protocol, palTerm.serverHost, palTerm.serverPort, dirlistPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("PanelTerminal.createFileDialog(): Error while creating URL to");
            System.out.println("\tprotocol   [" + palTerm.protocol + "]");
            System.out.println("\tserverHost [" + palTerm.serverHost + "]");
            System.out.println("\tserverPort [" + palTerm.serverPort + "]");
            System.out.println("\tdirlistPath[" + dirlistPath + "]");
            return null;
        }
        // Create file dialog box
        fileDialogRemote = new FileDialogRemote(palTerm, parentFrame, true, dirlistURL);
        fileDialogRemote.setDebug(debug);
        fileDialogRemote.messageLabel.setText("");

        return fileDialogRemote;

    }

    static FileDialogResource createFileDialogResource(PalTerm palTerm, Frame parentFrame, boolean debug) {

        FileDialogResource fileDialogResource;

        // Path to the "dirlist" CGI program to get a directory listing
        String dirlistPath = palTerm.cgiDir + palTerm.pgm_secdirlist + palTerm.cgiExtension;
        // Create file dialog box
        fileDialogResource = new FileDialogResource(parentFrame, true);
        fileDialogResource.setDebug(debug);
        fileDialogResource.messageLabel.setText("");

        return fileDialogResource;

    }

    /**
     * Show a list of options files to choose from. Load the selected file
     */
    public boolean openOptions() {
        //PalTerm palTerm = (PalTerm)applet;
        if (palTerm.useLocalOptions)
            return openOptionsLocal();
        else
            return openOptionsRemote();
    }

    /**
     * Show a list of options files to choose from. Load the selected file
     */
    private boolean openOptionsLocal() {
        boolean retVal = false;
        //PalTerm palTerm = (PalTerm)applet;

        FileDialog fileDialogLocal = createFileDialogLocal(palTerm.isNAV, palTerm.isIE, getParentFrame(), false, "Read Options File", getDebug());
        if (fileDialogLocal == null)
            return retVal;

        // Read directory to get updated file list
        String u = UserInfo.getUserId();
        String prefDir = palTerm.localDirectory + "termopts"
            + ((u != null && u.length() > 0) ? File.separator + u : "");
        {
            File f = new File(prefDir);
            f.mkdirs();
        }
        fileDialogLocal.setDirectory(prefDir);

        // Load a new options file from the server
        fileDialogLocal.setFile("default");
        fileDialogLocal.setTitle("Load User Options File");
        fileDialogLocal.setLocation(getLocation().x +
                getSize().width / 2,
            getLocation().y +
                getSize().height / 2);
        fileDialogLocal.setMode(java.awt.FileDialog.LOAD);
        fileDialogLocal.pack();
        fileDialogLocal.show();

        String file = fileDialogLocal.getFile();
        if (file != null && file.length() > 0) {
            optionFileName = file;
            readOptionsLocal();
            setFont(options.getFont());
            if (frameOptions != null)
                frameOptions.setOptions(options);
            Frame f = getParentFrame();
            if (f == hiddenParentFrame) {
                invalidate();
                repaint();
            } else {
                f.invalidate();
                f.repaint();
            }
            retVal = true;
        } else
            retVal = false;

        fileDialogLocal.dispose();
        fileDialogLocal = null;
        return retVal;
    }

    /**
     * Show a list of options files to choose from. Load the selected file
     */
    private boolean openOptionsRemote() {
        String remoteDir = "termopts/";
        boolean retVal = false;
        //PalTerm palTerm = (PalTerm)applet;

        if (fileDialogRemote == null)
            fileDialogRemote = createFileDialogRemote(palTerm, getParentFrame(), getDebug());
        if (fileDialogRemote == null)
            return retVal;

        // Read directory to get updated file list
        String u = UserInfo.getUserId();
        if (u == null
            || u.length() == 0) {
            if (debug)
                System.out.println("PanelTerminal.openOptions(): Running as anonymous user. Use default options only");
            u = "default";
        }
        String prefDir = palTerm.docBaseDir + remoteDir + u;
        if (!fileDialogRemote.readDirectory(prefDir, null))
            showMessage("Error reading options directory.", true);

        // Load a new options file from the server
        fileDialogRemote.setFileName(null);
        fileDialogRemote.setTitle("Load User Options File");
        fileDialogRemote.setLocation(getLocation().x +
                getSize().width / 2,
            getLocation().y +
                getSize().height / 2);
        fileDialogRemote.pack();
        fileDialogRemote.show();

        String file = fileDialogRemote.getFileName();
        if (file != null && file.length() > 0) {
            optionFileName = file;
            readOptionsRemote();
            setFont(options.getFont());
            if (frameOptions != null)
                frameOptions.setOptions(options);
            Frame f = getParentFrame();
            if (f == hiddenParentFrame) {
                invalidate();
                repaint();
            } else {
                f.invalidate();
                f.repaint();
            }
            retVal = true;
        } else
            retVal = false;

        return retVal;
    }

    /**
     * Show a list of options files to choose from. Load the selected file
     */
    public TermInfo selectTerminal() {
        String remoteDir = "terminfo/";
        String file = null;
        TermInfo retVal = null;

        if (palTerm.useLocalTermInfo) {
            FileDialog fileDialogLocal = createFileDialogLocal(palTerm.isNAV, palTerm.isIE, getParentFrame(), false, "Select Terminal", getDebug());
            if (fileDialogLocal == null)
                return retVal;

            // Read directory to get updated file list
            String dir = palTerm.localDirectory + remoteDir;
            dir = dir.replace('/', File.separatorChar);
            File f = new File(dir);
            f.mkdirs();
            fileDialogLocal.setDirectory(dir);

            // Load a new options file from the server
            fileDialogLocal.setFile("default");
            fileDialogLocal.setTitle("Select Terminal");
            fileDialogLocal.setLocation(getLocation().x + getSize().width / 4,
                getLocation().y + 75);
            fileDialogLocal.pack();
            fileDialogLocal.setSize(fileDialogLocal.getSize().width, getSize().height);
            fileDialogLocal.setMode(java.awt.FileDialog.LOAD);
            fileDialogLocal.show();

            file = fileDialogLocal.getFile();
        } else {
            // Try resource directory first, if successful, then return
            if (fileDialogResource == null) {
                fileDialogResource = createFileDialogResource(palTerm, getParentFrame(), getDebug());
                fileDialogResource.pack();
                fileDialogResource.setSize(fileDialogResource.getSize().width, getSize().height);
            }
            // Read directory to get updated file list
            String dir = remoteDir;
            if (fileDialogResource.readDirectory(dir, null)) {
                // Load a new terminfo file from the server
                fileDialogResource.setFileName(null);
                fileDialogResource.setTitle("Select Terminal Resource");
                fileDialogResource.setLocation(getLocation().x + getSize().width / 4, getLocation().y + 75);
                fileDialogResource.show();
                file = fileDialogResource.getFileName();
                if (file != null && file.length() > 0) {
                    String content = ClassPathResourceUtils.getResourceContent(file, PalTerm.class);
                    if (content != null && !content.isEmpty()) {
                        retVal = new TermInfo(content);
                    }
                    Frame f = getParentFrame();
                    if (f == hiddenParentFrame) {
                        invalidate();
                        repaint();
                    } else {
                        f.invalidate();
                        f.repaint();
                    }
                }
                return retVal;
            } else {
                showMessage("Error reading terminfo resource directory.", true);
            }
        }

        if (file == null) {
            // read using CGI
            if (fileDialogRemote == null) {
                fileDialogRemote = createFileDialogRemote(palTerm, getParentFrame(), getDebug());
                fileDialogRemote.pack();
                fileDialogRemote.setSize(fileDialogRemote.getSize().width, getSize().height);
            }
            if (fileDialogRemote == null)
                return retVal;

            // Read directory to get updated file list
            String dir = palTerm.docBaseDir + remoteDir;
            if (!fileDialogRemote.readDirectory(dir, null))
                showMessage("Error reading terminfo directory.", true);

            // Load a new terminfo file from the server
            fileDialogRemote.setFileName(null);
            fileDialogRemote.setTitle("Select Terminal");
            fileDialogRemote.setLocation(getLocation().x + getSize().width / 4,
                getLocation().y + 75);
            fileDialogRemote.show();

            file = fileDialogRemote.getFileName();
        }

        if (file != null && file.length() > 0) {
            StringBuffer sb = new StringBuffer();
            boolean success = UserInfo.getFileContents(palTerm, this, palTerm.useLocalTermInfo, remoteDir + file, sb);
            if (success) {
                retVal = new TermInfo(sb.toString());
            }
            Frame f = getParentFrame();
            if (f == hiddenParentFrame) {
                invalidate();
                repaint();
            } else {
                f.invalidate();
                f.repaint();
            }
        }

        return retVal;
    }

    public boolean readOptions() {
        //PalTerm		palTerm	= (PalTerm)applet;
        if (palTerm.useLocalOptions)
            return readOptionsLocal();
        else
            return readOptionsRemote();
    }

    private boolean readOptionsLocal() {
        boolean retVal = false;
        //PalTerm		palTerm	= (PalTerm)applet;

        String fileName = optionFileName;
        if (fileName == null)
            fileName = "default";

        String u = UserInfo.getUserId();

        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        boolean success = false;
        if (palTerm.isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                //if (debug)
                //    System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        String optionDir = palTerm.localDirectory + "termopts"
            + ((u != null && u.length() > 0) ? File.separator + u : "");
        File f = new File(optionDir);
        f.mkdirs();
        String optionFilePath = optionDir + File.separator + fileName;

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Properties props = new Properties();
        try {
            // Read input stream line by line
            FileInputStream fin = new FileInputStream(optionFilePath);
            props.load(fin);
        } catch (IOException e) {
            e.printStackTrace();
            showMessage("I/O error reading options from [" + optionFilePath + "]", true);
        } finally {
            setCursor(Cursor.getDefaultCursor());
            options.setFromProperties(props, null);
            retVal = true;
        }
        setCursor(Cursor.getDefaultCursor());
        return retVal;
    }

    private boolean readOptionsRemote() {
        boolean retVal = false;
        //PalTerm		palTerm	= (PalTerm)applet;

        String fileName = optionFileName;
        if (fileName == null)
            fileName = "default";

        String u = UserInfo.getUserId();
        if (u == null
            || u.length() == 0) {
            if (debug)
                System.out.println("PanelTerminal.readOptions(): Returning without action. Running as anonymous user.");
            return retVal;    // running as anonymous user
        }

        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        boolean success = false;
        if (palTerm.isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                //if (debug)
                //    System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        String optionFilePath = palTerm.docBaseDir + "termopts/" + u + "/" + fileName;

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            URL url = new URL(palTerm.protocol, palTerm.serverHost, palTerm.serverPort, optionFilePath);
            Properties props = new Properties();
            if (this.palTerm != null) {
                props.load(url.openStream());
            } else {
                URLConnection connection;
                BufferedReader in = null;
                try {
                    connection = url.openConnection();
                    connection.setDoOutput(false);     // use POST method to pass data
                    connection.setDoInput(true);      // process incoming CGI stream
                    connection.setUseCaches(false);   // disable caching of documents
                    connection.setAllowUserInteraction(true); // Allow user interaction

                    String authCookie = UserInfo.getAuthCookie();
                    if (authCookie != null)
                        connection.setRequestProperty("Authorization", authCookie);
                    connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("Content-length", "0");

                    props.load(connection.getInputStream());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    showMessage("Invalid URL for loading options", true);
                } catch (IOException e) {
                    e.printStackTrace();
                    showMessage("I/O error reading options from web server", true);
                } finally {
                    try {
                        if (in != null) in.close();
                    } catch (IOException e) {
                    }
                    setCursor(Cursor.getDefaultCursor());
                }
            }
            options.setFromProperties(props, null);
            retVal = true;
        } catch (Exception e) {
            showMessage("Could not read options file: " + optionFilePath, true);
        }
        setCursor(Cursor.getDefaultCursor());
        return retVal;
    }

    public void saveOptionsAs() {
        //PalTerm	palTerm = (PalTerm)applet;
        if (palTerm.useLocalOptions)
            saveOptionsAsLocal();
        else
            saveOptionsAsRemote();
    }

    private void saveOptionsAsLocal() {
        //PalTerm	palTerm = (PalTerm)applet;

        FileDialog fileDialogLocal = createFileDialogLocal(palTerm.isNAV, palTerm.isIE, getParentFrame(), true, "Save Options File", getDebug());
        if (fileDialogLocal == null)
            return;

        String fileName = optionFileName;
        if (fileName == null)
            fileName = "default";

        String u = UserInfo.getUserId();
        String prefDir = palTerm.localDirectory + "termopts"
            + ((u != null && u.length() > 0) ? (File.separator + u) : "");
        File f = new File(prefDir);
        f.mkdirs();

        fileDialogLocal.setDirectory(prefDir);
        fileDialogLocal.setFile(fileName);
        fileDialogLocal.setTitle("Save Options File");
        fileDialogLocal.setMode(java.awt.FileDialog.SAVE);
        fileDialogLocal.setLocation(getLocation().x +
                getSize().width / 2,
            getLocation().y +
                getSize().height / 2);
        fileDialogLocal.show();

        String file = fileDialogLocal.getFile();
        if (file != null && file.length() > 0) {
            f = new File(fileDialogLocal.getDirectory(), fileDialogLocal.getFile());
            // If file name already exists, prompt the user to overwrite it
            if (f.exists()) {
                if (saveDialog == null) {
                    saveDialog = new SaveDialog(getParentFrame());
                    saveDialog.setDebug(getDebug());
                }
                saveDialog.pack();
                saveDialog.setLocation(getLocation().x +
                        getSize().width / 2,
                    getLocation().y +
                        getSize().height / 2);
                saveDialog.show();
                if (saveDialog.getOverwriteOK()) {
                    optionFileName = file;
                    saveOptions();
                }
            } else {
                optionFileName = file;
                saveOptions();
            }
        }
        fileDialogLocal.dispose();
        fileDialogLocal = null;
    }

    private void saveOptionsAsRemote() {
        //PalTerm	palTerm = (PalTerm)applet;
        String remoteDir = "termopts/";
        if (fileDialogRemote == null)
            fileDialogRemote = createFileDialogRemote(palTerm, getParentFrame(), getDebug());
        if (fileDialogRemote == null)
            return;

        fileDialogRemote.messageLabel.setText("");

        String fileName = optionFileName;
        if (fileName == null)
            fileName = "default";

        String u = UserInfo.getUserId();
        if (u == null
            || u.length() == 0) {
            if (debug)
                System.out.println("PanelTerminal.openOptions(): Running as anonymous user. Use default options only");
            u = "default";
        }
        String prefDir = palTerm.docBaseDir + remoteDir + u;

        // Read directory to get updated file list
        if (!fileDialogRemote.readDirectory(prefDir, null))
            showMessage("Error reading options directory.", true);

        // Save the user-selected options to a file on the server
        fileDialogRemote.setFileName(fileName);
        fileDialogRemote.setTitle("Save Options File");
        fileDialogRemote.pack();
        fileDialogRemote.setLocation(getLocation().x +
                getSize().width / 2,
            getLocation().y +
                getSize().height / 2);
        fileDialogRemote.show();

        String file = fileDialogRemote.getFileName();
        if (file != null && file.length() > 0) {
            // If file name already exists, prompt the user to overwrite it
            if (fileDialogRemote.fileInList(file)) {
                if (saveDialog == null) {
                    saveDialog = new SaveDialog(getParentFrame());
                    saveDialog.setDebug(getDebug());
                }
                saveDialog.pack();
                saveDialog.setLocation(getLocation().x +
                        getSize().width / 2,
                    getLocation().y +
                        getSize().height / 2);
                saveDialog.show();
                if (saveDialog.getOverwriteOK()) {
                    optionFileName = file;
                    saveOptions();
                }
            } else {
                optionFileName = file;
                saveOptions();
            }
        }
    }

    /**
     * save the options file into the file it was read from. If the options
     * were not read from a file, then save it into default filename
     */

    public void saveOptions() {
        //PalTerm	palTerm = (PalTerm)applet;
        if (palTerm.useLocalOptions)
            saveOptionsLocal();
        else
            saveOptionsRemote();
    }

    /**
     * save the options file into the file it was read from. If the options
     * were not read from a file, then save it into default filename
     */

    private void saveOptionsLocal() {
        //PalTerm	palTerm	= (PalTerm)applet;

        String fileName = optionFileName;
        if (fileName == null)
            fileName = "default";

        String u = UserInfo.getUserId();
        if (u == null
            || u.length() == 0) {
            u = "default";
        }

        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        boolean success = false;
        if (palTerm.isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                //if (debug)
                //    System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        String optionDir = palTerm.localDirectory + "termopts"
            + ((u != null && u.length() > 0) ? File.separator + u : "");
        File f = new File(optionDir);
        f.mkdirs();
        String optionFilePath = optionDir + File.separator + fileName;

        // now save the properties p into the optionFilePath locally

        String params = options.getAsPropertyStrings();
        FileOutputStream fin = null;
        PrintWriter out = null;
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Color c;

            // Create new URL to prefsave program with file name appended
            fin = new FileOutputStream(optionFilePath);
            out = new PrintWriter(fin);
            if (debug)
                System.out.println("CGI-REQUEST:" + params);
            out.print(params);
            out.flush();
            out.close();
            out = null;
        } catch (IOException e) {
            showMessage("I/O error writing options to file [" + optionFilePath + "]", true);
        } finally {
            setCursor(Cursor.getDefaultCursor());
            if (out != null)
                out.close();
            out = null;
        }

    }

    /**
     * save the options file into the file it was read from. If the options
     * were not read from a file, then save it into default filename
     */

    private void saveOptionsRemote() {
        //PalTerm	palTerm	= (PalTerm)applet;

        String fileName = optionFileName;
        if (fileName == null)
            fileName = "default";

        String u = UserInfo.getUserId();
        if (u == null
            || u.length() == 0) {
            if (debug)
                System.out.println("PanelTerminal.saveOptions(): Returning without action. Running as anonymous user.");
            return;    // running as anonymous user
        }

        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        boolean success = false;
        if (palTerm.isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                //if (debug)
                //    System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        String optionFilePath = palTerm.docBaseDir + "termopts/" + u + "/" + fileName;

        // now save the properties p into the optionFilePath on server
        String prefsavePath = palTerm.cgiDir + palTerm.pgm_prefsave + palTerm.cgiExtension;
        URL prefsaveURL;
        try {
            prefsaveURL = new URL(palTerm.protocol, palTerm.serverHost,
                palTerm.serverPort, prefsavePath);
        } catch (MalformedURLException e) {
            System.out.println("Invalid prefsave path: " + prefsavePath);
            return;
        }
        String params = options.getAsPropertyStrings();
        URLConnection connection;
        BufferedReader in = null;
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Color c;

            // Create new URL to prefsave program with file name appended
            URL cgi_url = new URL(prefsaveURL.toString() + "?" + optionFilePath);

            connection = cgi_url.openConnection();
            connection.setDoOutput(true);     // use POST method to pass data
            connection.setDoInput(true);      // process incoming CGI stream
            connection.setUseCaches(false);   // disable caching of documents
            connection.setAllowUserInteraction(true); // Allow user interaction

            String authCookie = UserInfo.getAuthCookie();
            if (authCookie != null)
                connection.setRequestProperty("Authorization", authCookie);
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-length", params.length() + "");
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            if (debug)
                System.out.println("CGI-REQUEST:" + params);
            out.print(params);
            out.flush();
            out.close();
            out = null;

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String status = in.readLine();
            if (status.startsWith("ERROR"))
                showMessage(status, true);
        } catch (MalformedURLException e) {
            showMessage("Invalid URL for savepref CGI program", true);
        } catch (IOException e) {
            showMessage("I/O error writing options to web server", true);
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
            }
            setCursor(Cursor.getDefaultCursor());
        }

    }

    private Dialog messageDialog;
    private Label statusMessage;

    private void createMessageDialog() {
        if (messageDialog == null) {
            // Create message dialog box
            messageDialog = new Dialog(getParentFrame(), true);
            messageDialog.setLayout(new BorderLayout());
            statusMessage = new Label("", Label.CENTER);
            statusMessage.setFont(new Font("Serif", Font.PLAIN, 14));
            messageDialog.add("North", statusMessage);
            Panel panel = new Panel();
            Button button = new Button("Continue");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    messageDialog.setVisible(false);
                }
            });
            panel.add(button);
            messageDialog.add("South", panel);
        }
    }

    void writeCopyright() {
        terminalWrite("\r\nPalTerm "/*,0*/);
        terminalWrite("v" + VersionDialog.VERSION + PalTerm.buildTag + "\r\n"/*,0*/);
        if (terminalType == TERMINAL_TYPE_PT80) {
            terminalWrite("PT80 terminal emulator \r\n"/*,0*/);
        } else if (terminalType == TERMINAL_TYPE_VT220) {
            terminalWrite("VT220 terminal emulator \r\n"/*,0*/);
        }
        terminalWrite("\r\nCopyright 1999,2021 by Prasad & Associates Ltd. All Rights Reserved.\r\n\r\n"/*,0*/);
    }

    /**
     * Show status messages in a dialog box
     *
     * @param message the status message to show
     * @param error   set this flag to true if the message is an error
     **/
    void showMessage(String message, boolean error) {
        createMessageDialog();
        if (message == null)
            message = "";
        statusMessage.setText(message);
        if (error)
            messageDialog.setTitle("Error");
        else
            messageDialog.setTitle("Status");
        messageDialog.pack();

        // Center dialog over applet
        messageDialog.setLocation(getLocation().x +
                getSize().width / 2,
            getLocation().y +
                getSize().height / 2);
        messageDialog.show();
    }

    /**
     * Dialog to prompt the user to overwrite a file or not
     **/
    class SaveDialog extends Dialog implements ActionListener {
        Button okButton, cancelButton;
        Label message;
        boolean overwriteOK = false;
        boolean debug;

        /**
         * Create a new dialog box to prompt the user whether to
         * overwrite an existing options file.
         **/
        public SaveDialog(Frame parent) {
            super(parent, "Overwrite file?", true);

            setLayout(new BorderLayout());
            message = new Label("Overwrite existing options file?");
            message.setFont(new Font("Serif", Font.PLAIN, 14));
            add("Center", message);

            Panel panel = new Panel();
            add("South", panel);
            panel.setLayout(new GridLayout(1, 2, 10, 0));
            Font font = new Font("SansSerif", Font.BOLD, 12);

            okButton = new Button("Overwrite");
            okButton.setFont(font);
            okButton.addActionListener(this);
            panel.add(okButton);

            cancelButton = new Button("Cancel");
            cancelButton.setFont(font);
            cancelButton.addActionListener(this);
            panel.add(cancelButton);
        }

        /**
         * Return true if the user pressed to "Overwrite" button, otherwise
         * returns false.
         **/
        public boolean getOverwriteOK() {
            return overwriteOK;
        }

        /**
         * Handle action events from dialog buttons
         **/
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == okButton)
                overwriteOK = true;
            else
                overwriteOK = false;
            setVisible(false);
        }

        public boolean getDebug() {
            return debug;
        }

        public void setDebug(boolean flag) {
            debug = flag;
        }
    } // End of SaveDialog Class

    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO: implement this java.awt.event.MouseListener method;
        int iRow = e.getY() / scrData.charHeight;
        int iCol = e.getX() / scrData.maxCharWidth;

        if (iRow == scrData.FUNCTION_BTNS_ROW
            && hasFunctionKeys()) {
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //TODO: implement this java.awt.event.MouseListener method;
        int x = e.getX();
        int y = e.getY();
        int iRow = y / scrData.charHeight;
        int iCol = x / scrData.maxCharWidth;

        if (iRow >= scrData.scrChars.length)
            iRow = scrData.scrChars.length - 1;
        if (iCol >= scrData.scrChars[iRow].length)
            iCol = scrData.scrChars[iRow].length - 1;


        if (cmdMode == CMDMODE_LAWSON_LAPM
            && iRow == 1) {
            int newBtn = scrData.getLapmScrFuncNumber(x, y);
            int oldBtn = scrData.getLapmScrPressedFunc();
            StringBuffer sb = new StringBuffer();
            if (newBtn >= 0) {
                if (!scrData.isCursorInLapmFuncCodeField())
                    sb.append(keyTable[VK_HOME_INDEX][0]);
                if (newBtn == oldBtn) {
                    sb.append(getF12Sequence());
                } else if (newBtn < oldBtn) {
                    for (int i = oldBtn; i > newBtn; i--)
                        sb.append(keyTable[VK_LEFT_INDEX][0]);
                } else {
                    if (oldBtn < 0)
                        oldBtn = 0;
                    for (int i = oldBtn; i < newBtn; i++)
                        sb.append(keyTable[VK_RIGHT_INDEX][0]);
                }
                send(sb.toString());
            }
        } else if (options.getAltGo()) {
            if (scrData.scrChars[iRow][iCol] == (0x96 + 256)
                || scrData.scrChars[iRow][iCol + 1] == (0x96 + 256))
                send("\004");
            else if (scrData.scrChars[iRow][iCol] == (0x18 + 256))
                send(keyTable[VK_PAGE_UP_INDEX][0]);
            else if (scrData.scrChars[iRow][iCol] == (0x19 + 256))
                send(keyTable[VK_PAGE_DOWN_INDEX][0]);
            else if (iRow == scrData.FUNCTION_BTNS_ROW
                && hasFunctionKeys()) {
                if (isFunctionKeyActive(iCol))
                    sendFunctionKey(getFunctionKey(iCol));
            } else if (iRow < scrData.FUNCTION_BTNS_ROW) {
                sendSmartMouseMovement(scrData.getSmartMouseMovement(iRow, iCol, lawsonMode, unixMode, cmdMode));
            }
        } else {
            selectionInProcess = true;
            if (selectionActive)
                repaint();
            selectionActive = true;
            selectionStart.x = iCol;
            selectionStart.y = iRow;
            selectionEnd.x = iCol;
            selectionEnd.y = iRow;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //TODO: implement this java.awt.event.MouseListener method;
        int iRow = e.getY() / scrData.charHeight;
        int iCol = e.getX() / scrData.maxCharWidth;

        if (iRow >= scrData.scrChars.length)
            iRow = scrData.scrChars.length - 1;
        if (iCol >= scrData.scrChars[iRow].length)
            iCol = scrData.scrChars[iRow].length - 1;

        selectionInProcess = false;

        if (options.getAltGo()) {
            if (iRow == scrData.FUNCTION_BTNS_ROW
                && hasFunctionKeys())
                selectionActive = false;
            if (scrData.scrChars[iRow][iCol] == (0x95 + 256)
                || scrData.scrChars[iRow][iCol] == (0x96 + 256)
                || scrData.scrChars[iRow][iCol] == (0x18 + 256)
                || scrData.scrChars[iRow][iCol] == (0x19 + 256))
                selectionActive = false;
        } else {
            selectionEnd.x = iCol;
            selectionEnd.y = iRow;
            if (selectionEnd.x == selectionStart.x
                && selectionEnd.y == selectionStart.y)
                selectionActive = false;
            else {
                selectionActive = true;
                // swap the points if the selectionStart row or col is higher
                if (selectionStart.y > selectionEnd.y) {
                    int y = selectionStart.y;
                    selectionStart.y = selectionEnd.y;
                    selectionEnd.y = y;
                }
                if (selectionStart.x > selectionEnd.x) {
                    int x = selectionStart.x;
                    selectionStart.x = selectionEnd.x;
                    selectionEnd.x = x;
                }
            }
            if (!in_focus)
                requestFocus();
            else if (selectionActive)
                repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //	//TODO: implement this java.awt.event.MouseListener method;
        //	int		iRow	= event.getY() / scrData.charHeight;
        //	int		iCol	= event.getX() / scrData.maxCharWidth;
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //	//TODO: implement this java.awt.event.MouseListener method;
        //int		iRow	= event.getY() / scrData.charHeight;
        //int		iCol	= event.getX() / scrData.maxCharWidth;
        //
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //TODO: implement this java.awt.event.MouseMotionListener method;
        int x = e.getX();
        int y = e.getY();
        int iRow = y / scrData.charHeight;
        int iCol = x / scrData.maxCharWidth;

        if (iRow >= scrData.scrChars.length)
            iRow = scrData.scrChars.length - 1;
        if (iCol >= scrData.scrChars[iRow].length)
            iCol = scrData.scrChars[iRow].length - 1;

        if (selectionInProcess) {
            if (iRow < scrData.maxRows
                && iCol < scrData.maxCols) {
                if (selectionEnd.x != iCol
                    || selectionEnd.y != iRow) {
                    Graphics g = getGraphics();
                    if (selectionStart.x != iCol
                        || selectionStart.y != iRow)
                        highLightSelection(scrData, g); // this will unhighlight old
                    selectionEnd.x = iCol;
                    selectionEnd.y = iRow;
                    highLightSelection(scrData, g); // this will highlight the new
                    g.dispose();
                    //repaint();
                }
            }
        }
    }

    /**
     * Copy the selected string to the clipboard
     */
    public void copyToClipboard() {
        clipboardIO(true);
    }

    /**
     * Paste string from the clipboard to the terminal
     */
    public void pasteFromClipboard() {
        clipboardIO(false);
    }

    private void clipboardIO(boolean copyTo) {
        StringBuffer sb = null;
        if (copyTo) {
            if (!selectionActive)
                return;

            sb = new StringBuffer(100);
            for (int iRow = selectionStart.y; iRow <= selectionEnd.y; iRow++) {
                for (int iCol = selectionStart.x; iCol <= selectionEnd.x; iCol++) {
                    sb.append((char) (scrData.scrChars[iRow][iCol] % 256));
                }
                if (iRow < selectionEnd.y) {
                    if (File.separatorChar == '\\')
                        sb.append("\r\n");
                    else
                        sb.append("\n");
                }
            }
        }

        boolean success = false;
        if (palTerm.isNAV) {
            // try to get Netscape permission
            try {
                // for more targets goto:
                //http://developer.netscape.com/docs/manuals/signedobj/targets/index.htm
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalSystemClipboardAccess");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                //if (debug)
                //    System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.FILEIO);
                //if (debug)
                //    System.out.println("Got FILEIO permission for Microsoft Internet Explorer");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.PROPERTY);
                //if (debug)
                //    System.out.println("Got PROPERTY permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        if (copyTo) {
            if (sb != null)
                clipboard.setContents(new StringSelection(sb.toString()), this);
        } else {
            String s = null;
            try {
                Transferable transferable = clipboard.getContents(this);
                s = (String) transferable.getTransferData(DataFlavor.stringFlavor);
            } catch (Exception e) {
                e.printStackTrace();
            }
            /* Enter this string as input */
            if (s != null
                && s.length() > 0) {
                send(s);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //TODO: implement this java.awt.event.MouseMotionListener method;
        int x = e.getX();
        int y = e.getY();
        int iRow = y / scrData.charHeight;
        int iCol = x / scrData.maxCharWidth;

        if (iRow >= scrData.scrChars.length)
            iRow = scrData.scrChars.length - 1;
        if (iCol >= scrData.scrChars[iRow].length)
            iCol = scrData.scrChars[iRow].length - 1;

        if (cmdMode == CMDMODE_LAWSON_LAPM
            && iRow == 1
            && iCol < scrData.maxCols) {
            if (scrData.isOnLapmLapmScrFunc(x, y))
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        } else if (options.getAltGo()
            && iRow < scrData.maxRows
            && iCol < scrData.maxCols - 1
            && (scrData.scrChars[iRow][iCol] == (0x96 + 256) || scrData.scrChars[iRow][iCol + 1] == (0x96 + 256))
            && scrData.isNearTitleCorner(iRow, iCol))
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else if (iRow == scrData.FUNCTION_BTNS_ROW
            && hasFunctionKeys()) {
            if (isFunctionKeyActive(iCol))
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        } else {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public String getTelnetHost() {
        return telnetHost;
    }

    public void setTelnetHost(String h) {
        telnetHost = h;
    }

    /**
     * This is the last command that was typed by the user. Command is terminated by
     * an enter command and should have been initiated after a host prompt.
     */
    String lastUserCommandLine;
    /**
     * This is the last command word that was typed by the user. Command Word is
     * the first word in a comand line.
     */
    String lastUserCommandWord;
    /* This is the last prompt that was sent by the host. A prompt is text immediately
     * following a newLine character.
     */
    String lastHostPrompt;
    /**
     * Host prompt is the string used by the host. Host prompt should end the
     * string sent by the host (lastHostPrompt) after a newLine character.
     * Some defaults that are automatically checked are:
     * "# " and "$ " and "> " as they are typical unix prompts
     */
    String hostPrompt;
    /**
     * This is the current mode of the emulation. This mode can be set directly
     * by the user, or autodetected by the emulator, depending on the
     * lastCommand typed by the user at the hostPrompt.
     */
    int cmdMode;
    boolean lawsonMode = true;    // detect lawson commands
    boolean unixMode = true;    // detect unix commands

    Image backgroundImage;    // this is set depending on the lastUserCommandWord
    /**
     * Background images hashed by command
     */
    Hashtable backgroundImages;
    /**
     * State of the parser that is detecting command mode. Note that there is
     * no specific parser, but that when characters are received from the
     * telnet connection and when the key is typed by the user, the State is
     * set. cmdMode is set when a supported user command is typed, otherwise
     * this state machine is set to IDLE state. In IDLE state this state machine
     * waits for the recognized hostPrompt to be sent by the host.
     */
    int autodetectModeState;

    public static final int AUTODETECTMODE_STATE_PROMPT_START = 0;
    public static final int AUTODETECTMODE_STATE_USERCMD_START = 1;
    public static final int AUTODETECTMODE_STATE_PROCESSING = 2;

    public static final int CMDMODE_NONE = 0;
    // Special Modes for Lawson Software
    public static final int CMDMODE_LAWSON_LAPM = 1;
    public static final int CMDMODE_LAWSON_LANGDEF = 2;
    public static final int CMDMODE_LAWSON_LAUA = 3;
    public static final int CMDMODE_LAWSON_DBDEF = 4;
    public static final int CMDMODE_LAWSON_LX = 5;
    public static final int CMDMODE_LAWSON_LATREE = 6;
    public static final int CMDMODE_LAWSON_RESERVED_3 = 7;
    public static final int CMDMODE_LAWSON_RESERVED_4 = 8;
    public static final int CMDMODE_LAWSON_RESERVED_5 = 9;
    public static final int CMDMODE_LAWSON_RESERVED_6 = 10;
    public static final int CMDMODE_LAWSON_RESERVED_7 = 11;
    // General Unix Modes.
    public static final int CMDMODE_UNIX_CMD = 21;   // catch-all for unix commands
    public static final int CMDMODE_UNIX_VI = 22;    // vi command mode
    public static final int CMDMODE_UNIX_EMACS = 23;
    //
    public static final String UNKNOWNCMD_STR = "UNKNOWNCMD";

    void setHostPrompt(String s) {
        hostPrompt = s;
        if (debug)
            System.out.println(hostPrompt);
    }

    private void setAutodetectModeState(int state) {
        autodetectModeState = state;
        if (debug) {
            System.out.println(getAutodetectModeStateString());
            switch (autodetectModeState) {
                //case AUTODETECTMODE_STATE_PROMPT_START 	:
                case AUTODETECTMODE_STATE_USERCMD_START:
                    System.out.println("lastHostPrompt [" + lastHostPrompt + "] hostPrompt [" + hostPrompt + "]");
                    break;
                case AUTODETECTMODE_STATE_PROCESSING:
                    System.out.println("lastUserCommandLine [" + lastUserCommandLine + "]");
                    break;
            }
        }
    }

    private String getAutodetectModeStateString() {
        switch (autodetectModeState) {
            case AUTODETECTMODE_STATE_PROMPT_START:
                return "AUTODETECTMODE_STATE_PROMPT_START 	";
            case AUTODETECTMODE_STATE_USERCMD_START:
                return "AUTODETECTMODE_STATE_USERCMD_START	";
            case AUTODETECTMODE_STATE_PROCESSING:
                return "AUTODETECTMODE_STATE_PROCESSING		";
            default:
                return "AUTODETECTMODE_STATE " + autodetectModeState;
        }
    }

    private void setCmdMode(int iMode) {
        cmdMode = iMode;
        if (iMode != CMDMODE_NONE)
            setAutodetectModeState(AUTODETECTMODE_STATE_PROCESSING);
        if (debug)
            System.out.println(getCmdModeString());
    }

    String getCmdModeString() {
        switch (cmdMode) {
            case CMDMODE_NONE:
                return "CMDMODE_NONE";
            // Special Modes for Lawson Software
            case CMDMODE_LAWSON_LAPM:
                return "CMDMODE_LAWSON_LAPM";
            case CMDMODE_LAWSON_LANGDEF:
                return "CMDMODE_LAWSON_LANGDEF";
            case CMDMODE_LAWSON_LAUA:
                return "CMDMODE_LAWSON_LAUA";
            case CMDMODE_LAWSON_DBDEF:
                return "CMDMODE_LAWSON_DBDEF";
            case CMDMODE_LAWSON_LX:
                return "CMDMODE_LAWSON_LX";
            case CMDMODE_LAWSON_RESERVED_3:
                return "CMDMODE_LAWSON_RESERVED_3";
            case CMDMODE_LAWSON_RESERVED_4:
                return "CMDMODE_LAWSON_RESERVED_4";
            case CMDMODE_LAWSON_RESERVED_5:
                return "CMDMODE_LAWSON_RESERVED_5";
            case CMDMODE_LAWSON_RESERVED_6:
                return "CMDMODE_LAWSON_RESERVED_6";
            case CMDMODE_LAWSON_RESERVED_7:
                return "CMDMODE_LAWSON_RESERVED_7";
            // General Unix Modes.
            case CMDMODE_UNIX_CMD:
                return "CMDMODE_UNIX_CMD";
            case CMDMODE_UNIX_VI:
                return "CMDMODE_UNIX_VI";
            case CMDMODE_UNIX_EMACS:
                return "CMDMODE_UNIX_EMACS";

            default:
                return "CMDMODE is " + cmdMode;
        }
    }

    private void setLastUserCommandWord(String word) {
        lastUserCommandWord = word;
        if (word == null
            || word.equals(UNKNOWNCMD_STR)) {
            backgroundImage = null;
            setCmdMode(CMDMODE_NONE);
        } else {
            // set the command mode
            boolean lawsonModeSet = false;
            if (lawsonMode) {
                lawsonModeSet = true;
                if (lastUserCommandWord.equals("lapm"))
                    setCmdMode(CMDMODE_LAWSON_LAPM);
                else if (lastUserCommandWord.equals("latree")) {
                    if (palTerm.useLaTree) {
                        setCmdMode(CMDMODE_LAWSON_LATREE);
                        showLaTree();
                    }
                } else if (lastUserCommandWord.equals("langdef"))
                    setCmdMode(CMDMODE_LAWSON_LANGDEF);
                else if (lastUserCommandWord.equals("laua"))
                    setCmdMode(CMDMODE_LAWSON_LAUA);
                else if (lastUserCommandWord.equals("dbdef"))
                    setCmdMode(CMDMODE_LAWSON_DBDEF);
                else if (lastUserCommandWord.equals("lx"))
                    setCmdMode(CMDMODE_LAWSON_LX);
                else
                    lawsonModeSet = false;
            }
            // note there is no else here: lawsonMode and unixMode can be set separately or together
            if (unixMode
                && !lawsonModeSet) {
                if (lastUserCommandWord.equals("vi"))
                    setCmdMode(CMDMODE_UNIX_VI);
                else if (lastUserCommandWord.equals("emacs"))
                    setCmdMode(CMDMODE_UNIX_EMACS);
                else
                    setCmdMode(CMDMODE_UNIX_CMD);
            }
            // now set the background Image if available
            String image_file = options.getBackgroundImageURL(lastUserCommandWord);
            if (image_file != null) {
                if (backgroundImages == null)
                    backgroundImages = new Hashtable(32);
                if ((backgroundImage = (Image) backgroundImages.get(word)) == null) {
                    if (debug)
                        System.out.println("Attempting to load background image [" + image_file + "]");
                    Image img = palTerm.loadImage("backimgs", image_file, this, true);
                    if (img != null) {
                        backgroundImage = createImage(getSize().width, getSize().height);
                        Graphics g = backgroundImage.getGraphics();
                        tileImage(g, img);
                        g.dispose();
                        backgroundImages.put(word, backgroundImage);
                    }
                }
            }
        }
    }

    /**
     * Fill the specified area with an image
     * <p>
     *
     * @param g      the graphics context
     * @param image  the image to tile
     * @param g      area to fill
     **/
    protected void tileImage(Graphics g, Image image) {
        Graphics backGC = g.create();
        int x = 0, y = 0;
        int width = getSize().width, height = getSize().height;

        if (image == null)
            return;

        backGC.clipRect(0, 0, width, height);
        while (y < height) {
            while (x < width) {
                backGC.drawImage(image, x, y, this);
                int w = image.getWidth(this);
                if (w > 0)
                    x += w;
                else
                    break;
            }
            x = 0;
            int h = image.getHeight(this);
            if (h > 0)
                y += h;
            else
                break;
        }
        backGC.dispose();
    }

    private Frame laTreeFrame;
    private DualListBox laTreePanel;

    public void showLaTree() {
        //PalTerm	palTerm = (PalTerm)applet;

        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        boolean success = false;
        if (palTerm.isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                //if (debug)
                //    System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (laTreeFrame != null) {
            Frame f = getParentFrame();
            if (f != null) {
                // DOCK THIS ON THE RIGHT of current frame
                Rectangle r1 = f.getBounds();
                Rectangle r2 = laTreeFrame.getBounds();
                r2.x = r1.x + r1.width;
                r2.y = r1.y;
                r2.height = r1.height;
                laTreeFrame.setBounds(r2);
            }

            laTreeFrame.setVisible(true);
        } else {
            laTreeFrame = new Frame();

            laTreePanel = new DualListBox(this);
            laTreeFrame.setLayout(new BorderLayout());
            laTreeFrame.add(laTreePanel, BorderLayout.CENTER);

            Frame f = getParentFrame();
            if (f != null) {
                // DOCK THIS ON THE RIGHT of current frame
                Rectangle r = f.getBounds();
                r.x += r.width;
                r.width = 200;
                laTreeFrame.setBounds(r);
            } else
                laTreeFrame.setSize(400, 320);

            laTreeFrame.show();
            laTreePanel.setApplet(palTerm);
            laTreePanel.init();

            laTreeFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        Frame f2 = (Frame) e.getSource();
                        f2.setVisible(false);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }
        if (laTreeFrame != null) {
            if (laTreePanel != null) {
                laTreePanel.setDebug(debug);
                laTreePanel.setDesignMode(false);
                //callObjectBooleanMethod(laTreePanelObject, "setDebug", debug);
                //callObjectBooleanMethod(laTreePanelObject, "setDesignMode", false);
            }
            laTreeFrame.setVisible(true);
        }
    }

    FrameBackImg frameBackImg;

    public void setBackgroundImages() {
        if (frameBackImg == null) {
            frameBackImg = new FrameBackImg(palTerm);
            frameBackImg.setPreviewPanel(this);
            frameBackImg.pack();
        }
        if (options.backgroundImageURLs == null)
            options.backgroundImageURLs = new Hashtable();
        frameBackImg.setBackgroundImageURLs(options.backgroundImageURLs);
        frameBackImg.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ((Frame) e.getSource()).setVisible(false);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        frameBackImg.setVisible(true);
    }

    @Override
    public void showLapmForm(String prodLine, String token) {
        //TODO: implement this prasad.terminal.ShowLapmFormInterface method;
        if (cmdMode == CMDMODE_LAWSON_LATREE
            || cmdMode == CMDMODE_NONE)
//	||	cmdMode == CMDMODE_UNIX_CMD)
        {
            send("lapm " + prodLine + " " + token + "\n");
        } else if (cmdMode == CMDMODE_LAWSON_LAPM) {
            StringBuffer sb = new StringBuffer();
            sb.append(keyTable[VK_F8_INDEX][0]);
            sb.append(keyTable[VK_F8_INDEX][0]);
            sb.append(token);
            sb.append("\t");
            sb.append(prodLine);
            sb.append(getF12Sequence());
            send(sb.toString());
        } else {
            showMessage("Exit application [\"" + lastUserCommandWord + "\" - " + getCmdModeString() + "] in terminal window before running this form again", false);
            System.out.println("Error: Cannot show form [" + prodLine + token + "] in cmdMode [" + getCmdModeString() + "]");
        }
    }

    private Frame frameGetUnixEnv;

    /**
     * Create the frame after loading the class. This should avoid downloading the
     * class ahead of time.
     */
    public void readUnixEnv() {
        //PalTerm	palTerm	= (PalTerm)applet;

        // need to get out of the sandbox when running from a jar file on local machine
        // note: this code is repeated in every method that uses it !
        boolean success = false;
        if (palTerm.isNAV) {
            // try to get Netscape permission
            try {
                throw new IllegalArgumentException("Netscape Browser support has been removed");
                //netscape.security.PrivilegeManager.enablePrivilege("TerminalEmulator");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (palTerm.isIE) {
            // try Microsoft permission
            //		com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
            try {
                throw new IllegalArgumentException("Microsoft Internet Explorer support has been removed");
                //com.ms.security.PolicyEngine.assertPermission(com.ms.security.PermissionID.NETIO);
                //if (debug)
                //    System.out.println("Got NETIO permission for Microsoft Internet Explorer");
                //success = true;
            } catch (Throwable e) {
                success = false;
            }
        }

        if (frameGetUnixEnv == null) {
            frameGetUnixEnv = new FrameGetUnixEnv(this);
            frameGetUnixEnv.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    e.getWindow().setVisible(false);
                }
            });
        }
        if (frameGetUnixEnv != null) {
            frameGetUnixEnv.setVisible(true);
            frameGetUnixEnv.setBackground(options.textAttribs[0].back);
            frameGetUnixEnv.setForeground(options.textAttribs[0].fore);
            frameGetUnixEnv.setTitle("Environment for Host " + telnetHost);
        }
    }

    Color getTextBackground() {
        return options.textAttribs[0].back;
    }

    Color getTextForeground() {
        return options.textAttribs[0].fore;
    }

    Color getTextFont() {
        return options.textAttribs[0].fore;
    }

    TerminalVputListener[] terminalVputListeners;
    private int terminalVputListenerCnt;

    @Override
    public synchronized void addTerminalVputListener(TerminalVputListener listener) {
        //TODO: implement this prasad.terminal.TerminalSender method;
        if (listener != null) {
            if (terminalVputListeners == null)
                terminalVputListeners = new TerminalVputListener[10];
            for (int i = 0; i < terminalVputListeners.length; i++) {
                if (terminalVputListeners[i] == listener)
                    return;
            }
            for (int i = 0; i < terminalVputListeners.length; i++) {
                if (terminalVputListeners[i] == null) {
                    terminalVputListeners[i] = listener;
                    updateTerminalVputListenerCnt();
                    return;
                }
            }
            // expand array
            int iSize = terminalVputListeners.length;
            TerminalVputListener[] newArray = new TerminalVputListener[iSize + 10];
            for (int i = 0; i < iSize; i++)
                newArray[i] = terminalVputListeners[i];
            newArray[iSize] = listener;
            terminalVputListeners = newArray;
            updateTerminalVputListenerCnt();
        }
    }

    @Override
    public synchronized void removeTerminalVputListener(TerminalVputListener listener) {
        //TODO: implement this prasad.terminal.TerminalSender method;
        if (listener != null
            && terminalVputListeners != null) {
            for (int i = 0; i < terminalVputListeners.length; i++) {
                if (terminalVputListeners[i] == listener) {
                    terminalVputListeners[i] = null;
                    return;
                }
            }
        }
    }

    private synchronized void updateTerminalVputListenerCnt() {
        terminalVputListenerCnt = 0;
        if (terminalVputListeners != null) {
            for (int i = terminalVputListeners.length; i > 0; i--) {
                if (terminalVputListeners[i - 1] != null) {
                    terminalVputListenerCnt = i;
                    break;
                }
            }
        }
    }

    private boolean paintScreenFlag = true;
    private boolean paintCaretFlag = true;

    @Override
    public void setOptimizeDisplay(boolean repaintScreen, boolean repaintCaret) {
        //TODO: implement this prasad.terminal.TerminalSender method;
        boolean doRepaintScreen = false;
        if (repaintScreen
            && !paintScreenFlag)
            doRepaintScreen = true;
        paintScreenFlag = repaintScreen;
        paintCaretFlag = repaintCaret;
        if (doRepaintScreen)
            repaint();
    }

    public void configureSmartMouse() {
        //PalTerm palTerm	= (PalTerm)applet;
        Frame f = getParentFrame();
        palTerm.configureSmartMouse(f);
    }

    public void configureGraphicsCharForeground() {
        Frame f = getParentFrame();
        ColorPicker colorPicker = new ColorPicker(f, true);
        if (options.graphicsCharForeground != null)
            colorPicker.setColor(options.graphicsCharForeground);
        colorPicker.pack();
        Rectangle r1 = f.getBounds();
        Dimension d2 = colorPicker.getSize();
        colorPicker.setLocation(r1.x + r1.width / 2 - d2.width, r1.y + r1.height / 2 - d2.height);
        colorPicker.show();

        Color color = colorPicker.getColor();
        if (color != null) {
            options.graphicsCharForeground = color;
            invalidate();
            validate();
            repaint();
        }
        colorPicker.dispose();
    }
  /*
  public static void callObjectBooleanMethod(Object obj, String methodName, boolean param)
  {
	try {
		Class clazz = obj.getClass();
		Class[]	parameterTypes = new Class[1];
		parameterTypes[0]		= Boolean.TYPE;
		Method mid	= clazz.getDeclaredMethod(methodName, parameterTypes);
		Object[]	params		= new Object[1];
		params[0]				= new Boolean(param);
		mid.invoke(obj, params);
	}
	catch (Exception e)
	{
		System.err.println("ERROR: failed to call method [" + methodName + "]");
		e.printStackTrace();
	}
  }
  public static void callObjectMethod(Object obj, String methodName, Object param, Class paramClass)
  {
	try {
		Class clazz = obj.getClass();
		Class[]	parameterTypes = new Class[1];
		parameterTypes[0]		= paramClass;
		Method mid	= clazz.getDeclaredMethod(methodName, parameterTypes);
		Object[]	params		= new Object[1];
		params[0]				= param;
		mid.invoke(obj, params);
	}
	catch (Exception e)
	{
		System.err.println("ERROR: failed to call method [" + methodName + "]");
		e.printStackTrace();
	}
  }
  public static void callObjectMethod(Object obj, String methodName)
  {
	try {
		Class clazz = obj.getClass();
		Class[]	parameterTypes = new Class[0];
		Method mid	= clazz.getDeclaredMethod(methodName, parameterTypes);
		Object[]	params		= new Object[0];
		mid.invoke(obj, params);
	}
	catch (Throwable e)
	{
		System.err.println("ERROR: failed to call method [" + methodName + "]");
		e.printStackTrace();
	}
  }
  */

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        //TODO: implement this java.awt.datatransfer.ClipboardOwner method;
    }
}


