

package com.prasad.terminal;

/**
 * Sends characters to the host. PanelTerminal implements this interface
 */
public interface TerminalSender {
    public void addTerminalVputListener(TerminalVputListener listener);

    public void removeTerminalVputListener(TerminalVputListener listener);

    public void send(char[] d, int off, int len);

    public void send(char c);

    public void send(String str);

    /**
     * virtual keys are defined in PanelTerminal VK_xxx_INDEX
     */
    public void sendKey(int virtualKeyIndex, boolean shift, boolean ctrl);

    /**
     * to be used in smart mouse code and other TerminalVPutListener code
     * that may want to turn on/off the code for repaint of screen.
     * Complete repaint of screen will be done when this method is
     * called with repaintScreen flag set to true.
     * Caret painting can be set to on or off, independently of screen repainting.
     */
    public void setOptimizeDisplay(boolean repaintScreen, boolean repaintCaret);


    public static final int VK_ENTER_INDEX = 0;
    public static final int VK_BACK_SPACE_INDEX = 1;
    public static final int VK_TAB_INDEX = 2;
    public static final int VK_ESCAPE_INDEX = 3;
    public static final int VK_SPACE_INDEX = 4;
    public static final int VK_PAGE_UP_INDEX = 5;
    public static final int VK_PAGE_DOWN_INDEX = 6;
    public static final int VK_END_INDEX = 7;
    public static final int VK_HOME_INDEX = 8;
    public static final int VK_LEFT_INDEX = 9;
    public static final int VK_UP_INDEX = 10;
    public static final int VK_RIGHT_INDEX = 11;
    public static final int VK_DOWN_INDEX = 12;

    public static final int VK_0_INDEX = 13;
    public static final int VK_1_INDEX = 14;
    public static final int VK_2_INDEX = 15;
    public static final int VK_3_INDEX = 16;
    public static final int VK_4_INDEX = 17;
    public static final int VK_5_INDEX = 18;
    public static final int VK_6_INDEX = 19;
    public static final int VK_7_INDEX = 20;
    public static final int VK_8_INDEX = 21;
    public static final int VK_9_INDEX = 22;

    public static final int VK_NUMPAD0_INDEX = 23;
    public static final int VK_NUMPAD1_INDEX = 24;
    public static final int VK_NUMPAD2_INDEX = 25;
    public static final int VK_NUMPAD3_INDEX = 26;
    public static final int VK_NUMPAD4_INDEX = 27;
    public static final int VK_NUMPAD5_INDEX = 28;
    public static final int VK_NUMPAD6_INDEX = 29;
    public static final int VK_NUMPAD7_INDEX = 30;
    public static final int VK_NUMPAD8_INDEX = 31;
    public static final int VK_NUMPAD9_INDEX = 32;
    public static final int VK_MULTIPLY_INDEX = 33;
    public static final int VK_ADD_INDEX = 34;
    public static final int VK_SEPARATER_INDEX = 35;
    public static final int VK_SUBTRACT_INDEX = 36;
    public static final int VK_DECIMAL_INDEX = 37;
    public static final int VK_DIVIDE_INDEX = 38;

    public static final int VK_F1_INDEX = 39;
    public static final int VK_F2_INDEX = 40;
    public static final int VK_F3_INDEX = 41;
    public static final int VK_F4_INDEX = 42;
    public static final int VK_F5_INDEX = 43;
    public static final int VK_F6_INDEX = 44;
    public static final int VK_F7_INDEX = 45;
    public static final int VK_F8_INDEX = 46;
    public static final int VK_F9_INDEX = 47;
    public static final int VK_F10_INDEX = 48;
    public static final int VK_F11_INDEX = 49;
    public static final int VK_F12_INDEX = 50;

    public static final int VK_DELETE_INDEX = 51;
    public static final int VK_PRINTSCREEN_INDEX = 52;
    public static final int VK_INSERT_INDEX = 53;
    public static final int VK_C_ALT_INDEX = 54;
    public static final int VK_H_ALT_INDEX = 55;
    public static final int VK_M_ALT_INDEX = 56;
    public static final int VK_NUMERIC_MINUS_INDEX = 57;    // with num-lock on
    public static final int VK_NUMERIC_PLUS_INDEX = 58;    // with num-lock on
    public static final int VK_MAX_INDEX = 59;

}
