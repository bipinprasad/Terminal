

package com.prasad.terminal;

public interface KeyStatusListener {
    public void setCapsLocked(boolean b);

    public void setNumLocked(boolean b);

    public void setShiftPressed(boolean b);

    public void setCtrlPressed(boolean b);
}
