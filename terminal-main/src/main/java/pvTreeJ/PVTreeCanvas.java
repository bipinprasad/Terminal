package pvTreeJ;

// Imports
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import java.beans.*;
import java.io.Serializable;
import java.net.*;
import java.awt.event.ComponentEvent;

/*
 *
 * PVTreeCanvas
 *
 */
class PVTreeCanvas extends Panel implements Serializable
{
	PVTree m_tree;
 
    int         oldx;
    int         oldy;
	PVTreeCanvas(PVTree tree) {
        super();
        m_tree = tree;
        enableEvents(AWTEvent.FOCUS_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK |
          AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.KEY_EVENT_MASK);
	}

	protected void scrollLineUp(AWTEvent event) {
		Graphics g = getGraphics();
		Dimension d = getSize();
		PVNode firstnode, prevnode;

		m_tree.endLabelEdit(true);
		firstnode = m_tree.m_FirstVisible.getPrevVisible();
		if(firstnode != null)
		{
			m_tree.m_FirstVisible = firstnode;
			prevnode = m_tree.m_LastVisible.getPrevVisible();
			if(prevnode != null)
				m_tree.m_LastVisible = prevnode;
			if(firstnode != null) {
				g.copyArea(0, 0, d.width, d.height, 0, m_tree.m_LineHeight);
				firstnode._paint(m_tree,
					g, 1);
//    		m_tree.m_vscroll.setValues(m_tree.m_vscroll.getValue() - 1, m_tree.m_LinesPerPage, 0, m_tree.m_VisItemCount);
//			int Pos = m_tree.m_vscroll.getValue();
            m_tree.m_VScrollOffset--;
			m_tree.m_vscroll.setValue(m_tree.m_VScrollOffset);
			}
		}
	}

	protected void scrollLineDown(AWTEvent event) {
		Graphics g = getGraphics();
		Dimension d = getSize();
		PVNode firstnode, nextnode;

		m_tree.endLabelEdit(true);
		nextnode = m_tree.m_LastVisible.getNextVisible();
		if(nextnode != null)
			m_tree.m_LastVisible = nextnode;
		if(nextnode != null) {
			firstnode = m_tree.m_FirstVisible.getNextVisible();
			if(firstnode != null)
				m_tree.m_FirstVisible = firstnode;

			g.copyArea(0, m_tree.m_LineHeight, d.width, d.height - m_tree.m_LineHeight, 0, -m_tree.m_LineHeight);
			nextnode._paint(m_tree,
				g,
				m_tree.m_LinesPerPage);
			nextnode = nextnode.getNextVisible();
			if(nextnode != null)
				nextnode._paint(m_tree,
					g,
					m_tree.m_LinesPerPage + 1);
			else
				blankLine(g, m_tree.m_LinesPerPage);
		}
//		m_tree.m_vscroll.setValues(m_tree.m_vscroll.getValue() + 1, m_tree.m_LinesPerPage, 0, m_tree.m_VisItemCount);
//		int Pos = m_tree.m_vscroll.getValue();
        m_tree.m_VScrollOffset++;
    	m_tree.m_vscroll.setValue(m_tree.m_VScrollOffset);
}

	protected void blankLine(Graphics g, int line) {
    Dimension dim=m_tree.getSize();
		g.setColor(getBackground()); //m_tree.m_BackColor);
		g.fillRect(0, line * m_tree.m_LineHeight, dim.width, m_tree.m_LineHeight);

	}

	protected void scrollPageUp(AWTEvent event) {
		Graphics g = getGraphics();
		Dimension d = getSize();
		int i;
		PVNode node = null;

		m_tree.endLabelEdit(true);
		for(i = 1; i <= m_tree.m_LinesPerPage; i++) {
			node = m_tree.m_FirstVisible.getPrevVisible();
			if(node != null) {
				m_tree.m_FirstVisible = node;
				node = m_tree.m_LastVisible.getPrevVisible();
				if(node != null)
					m_tree.m_LastVisible = node;
			}
			else
				if(i == 1)
					return;
				else
					i = m_tree.m_LinesPerPage + 1;
		}
		//int Pos = m_tree.m_vscroll.getValue();
		m_tree.m_VScrollOffset -= m_tree.m_LinesPerPage;
		m_tree.m_vscroll.setValue(m_tree.m_VScrollOffset);
		paintnodes(g);
	}

    protected boolean isNodeVisible(PVNode node) {
		PVNode oldnode = m_tree.m_FirstVisible;
		int i;
		if(node == null)
		    return false;
		for(i = 1; i <= m_tree.m_LinesPerPage; i++) {
			if(oldnode == node)
			    return true;
			if(oldnode == null)
			    return false;
			oldnode = oldnode.getNextVisible();
		}
		return false;
    }

	protected void scrollPageDown(AWTEvent event) {
		Graphics g = getGraphics();
		Dimension d = getSize();
		int i;
		int blanks = 0;
		PVNode node = null;
		PVNode oldnode = m_tree.m_FirstVisible;
		m_tree.endLabelEdit(true);
		for(i = 1; i <= m_tree.m_LinesPerPage; i++) {
			node = m_tree.m_LastVisible.getNextVisible();
			if(node != null) {
				m_tree.m_LastVisible = node;
				node = m_tree.m_FirstVisible.getNextVisible();
				if(node != null)
					m_tree.m_FirstVisible = node;
			}
			else
				i = m_tree.m_LinesPerPage + 1;

		}

		node = m_tree.m_FirstVisible;
		if(oldnode == node)
			return;

 		//int Pos = m_tree.m_vscroll.getValue();
 		m_tree.m_VScrollOffset += m_tree.m_LinesPerPage;
    	m_tree.m_vscroll.setValue(m_tree.m_VScrollOffset);
		paintnodes(g);
	}

	protected void scrollAbsolute(AWTEvent event) {
		m_tree.endLabelEdit(true);
		m_tree.m_VScrollOffset = m_tree.m_vscroll.getValue();
		int count = 0;
		PVNode node = m_tree.m_RootNode.m_FirstChild;
		while((node != null) && (count < m_tree.m_VScrollOffset)) {
			count++;
			node = node.getNextVisible();
		}
		if(node != null) {
			m_tree.m_FirstVisible = node;
			repaint();
			m_tree.m_vscroll.setValue(m_tree.m_VScrollOffset);
		}
	}

	protected void scrollLeft(AWTEvent event) {
		m_tree.endLabelEdit(true);
		Dimension dim = getSize();
		if(m_tree.m_hscrollshown && (m_tree.m_HScrollOffset > 0))
		{
			m_tree.m_HScrollOffset -= Math.min(10, m_tree.m_HScrollOffset);
			m_tree.m_hscroll.setValues(m_tree.m_HScrollOffset, 10, 0, m_tree.m_MaxLineLength - m_tree.m_Width);
			repaint();
		}
	}

	protected void scrollRight(AWTEvent event) {
		m_tree.endLabelEdit(true);
		Dimension dim = getSize();
		if(m_tree.m_hscrollshown &&
			(m_tree.m_HScrollOffset < (m_tree.m_MaxLineLength - m_tree.m_Width)))
		{
			m_tree.m_HScrollOffset += Math.min(10, m_tree.m_MaxLineLength - m_tree.m_Width);
			m_tree.m_hscroll.setValues(m_tree.m_HScrollOffset, dim.width, 0, m_tree.m_MaxLineLength - m_tree.m_Width);
			m_tree.m_hscroll.setValues(m_tree.m_HScrollOffset, 10, 0, m_tree.m_MaxLineLength - m_tree.m_Width);
			repaint();
		}
	}

	protected void hScrollPageUp(AWTEvent event) {
		m_tree.endLabelEdit(true);
		Dimension dim = getSize();
		if(m_tree.m_hscrollshown && (m_tree.m_HScrollOffset > 0))
		{
			m_tree.m_HScrollOffset -= Math.min(40, m_tree.m_HScrollOffset);
			m_tree.m_hscroll.setValues(m_tree.m_HScrollOffset, 10, 0, m_tree.m_MaxLineLength - m_tree.m_Width);
			repaint();
		}
	}

	protected void hScrollPageDown(AWTEvent event) {
		m_tree.endLabelEdit(true);
		Dimension dim = getSize();
		if(m_tree.m_hscrollshown &&
			(m_tree.m_HScrollOffset < (m_tree.m_MaxLineLength - m_tree.m_Width)))
		{
			m_tree.m_HScrollOffset += 40;
			m_tree.m_HScrollOffset = Math.min(m_tree.m_HScrollOffset, m_tree.m_MaxLineLength - m_tree.m_Width);
			m_tree.m_hscroll.setValues(m_tree.m_HScrollOffset, 10, 0, m_tree.m_MaxLineLength - m_tree.m_Width);
			repaint();
		}
	}

	protected void hScrollAbsolute(AWTEvent event) {
		m_tree.endLabelEdit(true);
		int Pos = m_tree.m_hscroll.getValue();
		Dimension dim = getSize();
		if(m_tree.m_hscrollshown)
		{
			m_tree.m_HScrollOffset = Pos;
			m_tree.m_hscroll.setValues(m_tree.m_HScrollOffset, 10, 0, m_tree.m_MaxLineLength - m_tree.m_Width);
			repaint();
		}
	}

	@Override
	public void paint(Graphics g)
	{
		if(!m_tree.m_Redraw) {
			validate();
			return;
		}
		paintnodes(g);
	}

	void paintnodes(Graphics g) {
		PVNode node;
		node = m_tree.m_FirstVisible;

		Dimension dim = getSize();
		Image image = createImage(dim.width, dim.height);
		Graphics ig = image.getGraphics();

		int total = m_tree.m_LinesPerPage + 1 ;
		int count = 1;
		int blank = 0;
		while(node != null && count <= total) {
		    node._paint(m_tree, ig, count);
			if(count == total - 1)
    			m_tree.m_LastVisible = node;
			node = node.getNextVisible();
		    count++;
			blank++;
		}
		if(blank < m_tree.m_LinesPerPage + 1)
		{
			while(blank <= m_tree.m_LinesPerPage + 1)
			{
				blankLine(ig, blank);
				blank++;
			}
		}

		g.drawImage(image, 0, 0, this);
	}

	@Override
	protected void processKeyEvent(java.awt.event.KeyEvent event) {
	PVNode node;
	int Pos;
		//m_tree.processPVEvent(0, event.getID(), 0, 0, event.getKeyCode(), event.getModifiers(), null);
        int Key = event.getKeyCode();
		if(event.getID() != KeyEvent.KEY_PRESSED)
		{
			super.processKeyEvent(event);
			return;
		}

		switch(Key) {
//		case java.awt.event.KeyEvent.VK_TAB :
  //              transferFocus();
    //            return;
       case java.awt.event.KeyEvent.VK_ENTER :
            m_tree.processKeyEvent(event);
            return;
		case java.awt.event.KeyEvent.VK_RIGHT :
            if(event.getSource() == m_tree.m_edit)
                return;

			scrollRight(event);
			return;

		case java.awt.event.KeyEvent.VK_LEFT :
			if(event.getSource() == m_tree.m_edit)
                return;

			scrollLeft(event);
			return;

		case java.awt.event.KeyEvent.VK_DOWN :
			m_tree.endLabelEdit(true);
            if(!isNodeVisible(m_tree.m_SelectedNode)) {
                m_tree.setSelectedNode(m_tree.m_FirstVisible);
                return;
            }
			if(m_tree.m_SelectedNode == m_tree.m_LastVisible) {
				scrollLineDown(event);
				Pos = m_tree.m_vscroll.getValue();
				m_tree.m_vscroll.setValue(Pos + 1);
				node = m_tree.m_LastVisible;
			}
			else {
				node = m_tree.m_SelectedNode.getNextVisible();
			}
			if(node != null)
				m_tree.setSelectedNode(node);
			return;

		case java.awt.event.KeyEvent.VK_UP :
			m_tree.endLabelEdit(true);
            if(!isNodeVisible(m_tree.m_SelectedNode)) {
				m_tree.setSelectedNode(m_tree.m_LastVisible);
                return;
            }
			if(m_tree.m_SelectedNode == m_tree.m_FirstVisible) {
				scrollLineUp(event);
				Pos = m_tree.m_vscroll.getValue();
				m_tree.m_vscroll.setValue(Pos - 1);
			}
			node = m_tree.m_SelectedNode.getPrevVisible();
			if(node != null)
				m_tree.setSelectedNode(node);
			return;

		case java.awt.event.KeyEvent.VK_PAGE_DOWN :
			scrollPageDown(event);
			m_tree.setSelectedNode(m_tree.m_LastVisible);
    		Pos = m_tree.m_vscroll.getValue();
	    	m_tree.m_vscroll.setValue(Pos + m_tree.m_LinesPerPage);

			return;

		case java.awt.event.KeyEvent.VK_PAGE_UP :
			scrollPageUp(event);
			Pos = m_tree.m_vscroll.getValue();
			m_tree.m_vscroll.setValue(Pos - m_tree.m_LinesPerPage);
			m_tree.setSelectedNode(m_tree.m_FirstVisible);
			return;
		}
		super.processKeyEvent(event);
		return;
	}

	@Override
	protected void processMouseEvent(java.awt.event.MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		int id = e.getID();
		int clickCount = e.getClickCount();

		if(id == java.awt.event.MouseEvent.MOUSE_PRESSED) {
			int line = y / m_tree.m_LineHeight + 1;
			if(m_tree.m_FirstVisible == null)
				return;
			PVNode node = m_tree.m_FirstVisible.NodeAtLine(line);
			int margin;
			requestFocus();
			if(node != null) {
				int nodeline = node.lineFromNode(m_tree);
				if(line != nodeline) { // The click is not on a node.  Do'nt do anything
					return;//viktor// true;
				}
				margin = node.m_Level * m_tree.m_Indentation;
				margin -= m_tree.m_HScrollOffset;
				m_tree.endLabelEdit(true);
				if(m_tree.m_ShowButtons && ((x > (margin + 2)) && (x < (margin + 16)))) {

					if(node.m_FirstChild != null) {
						if(node.m_Expanded) {
							m_tree.setExpanded(node, false);
						}
						else {
							m_tree.setExpanded(node, true);
						}
				}
				}

				if((clickCount == 2)) { // && (x == oldx) && (y == oldy)) {
					PVTreeActionEvent ae = m_tree.createPVEvent(0, PVTreeActionEvent.NODE_DOUBLE_CLICK, x, y, 0, e.getModifiers(), node);
					m_tree.processActionEvent(ae);
					if(ae.getCancel())
						return;

					if(x > margin + 32 && m_tree.m_canEdit)
						m_tree.startLabelEdit(node);
					else
/*  double clicked will expand the node if add the following code.
					if (x > margin + 16) {
						if(node.m_Expanded) {
							m_tree.setExpanded(node, false);
						}
					else {
							m_tree.setExpanded(node, true);
						}
					}
*/
					oldx = x;
					oldy = y;

					return;
				}
		  //  the if condition is added by Ling
		  //  if clicked on the buttons (+,-), will not set selected node to this node
		  else if ( m_tree.m_ShowButtons && ((x > (margin + 2)) && (x < (margin + 16)))) {
			if (m_tree.m_SelectedNode != null) return;
		  }
		  else {
					if(m_tree.m_SelectedNode != node) {
						m_tree.setSelectedNode(node);
					}
				}
			}
		}
		else
		if(id == java.awt.event.MouseEvent.MOUSE_RELEASED) {
			int line = y / m_tree.m_LineHeight + 1;
			if(m_tree.m_FirstVisible == null)
				return;
			PVNode node = m_tree.m_FirstVisible.NodeAtLine(line);
			if((node != null) && (node == m_tree.m_SelectedNode)) {
				PVTreeActionEvent ae = m_tree.createPVEvent(0, PVTreeActionEvent.NODE_CLICK, x, y, 0, e.getModifiers(), node);
				m_tree.processActionEvent(ae);
			}
		}

		try {
			((PVTree)getParent()).processMouseEvent(e);
		} catch(Exception e2){e2.printStackTrace();}

	}

	@Override
	protected void processFocusEvent(FocusEvent e) {
		// Get the new focus state and repaint
		switch(e.getID()) {
		  case FocusEvent.FOCUS_GAINED:
			break;

		  case FocusEvent.FOCUS_LOST:
			break;
		}
    }

	@Override
	public void update(Graphics g) {
		paint(g);
	}

}

