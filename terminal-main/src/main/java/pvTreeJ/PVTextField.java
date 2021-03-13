package pvTreeJ;

// Imports
import java.awt.*;
import java.util.*;
import java.awt.event.*;

class PVTextField extends TextField {
	PVTree m_tree;
	FontMetrics m_fm;
	int		m_minWidth;
	Font	m_Font;

	PVTextField(PVTree tree) {
		super();
        enableEvents(AWTEvent.FOCUS_EVENT_MASK | 
        //AWTEvent.MOUSE_EVENT_MASK |
        //AWTEvent.MOUSE_MOTION_EVENT_MASK | 
        AWTEvent.KEY_EVENT_MASK);
		m_tree	= tree;
	}

	public void setFontMetrics(FontMetrics fm) {
		m_fm = fm;
	}

///	public boolean lostFocus(Event evt, Object arg){
///		m_tree.endLabelEdit(true);
///		return true;
///	}

	public void setMinWidth(int minWidth) {
		m_minWidth = minWidth;
	}

	@Override
    protected void processKeyEvent(java.awt.event.KeyEvent event) {
	    
	    if(event.getID() != KeyEvent.KEY_PRESSED)
            return;
        requestFocus();
        int Key = event.getKeyCode();
		switch(Key) {
		case KeyEvent.VK_ENTER :
    		m_tree.endLabelEdit(true);
			return;
		case KeyEvent.VK_ESCAPE :
			m_tree.endLabelEdit(false);
			return;
		default :
	//		autoSize();
			return;
		}
	}

    int autoSize() {
		String text = getText();
		int w = m_fm.stringWidth(text);
		Rectangle r = getBounds();
		if(w + 25 > m_minWidth) {
///			w = Math.min(m_tree.m_Width - r.x, w + 25);
			setSize(new Dimension(w, r.height));
			return w;
		}
		else
		{
			setSize(new Dimension(m_minWidth, r.height));
			return m_minWidth;
		}
	}

}

