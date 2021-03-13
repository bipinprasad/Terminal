package pvTreeJ;

// Imports
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import java.beans.*;
import java.io.*;
import java.io.Serializable;
import java.net.*;
import java.awt.event.ComponentEvent;

/**
	The ProtoView Tree control.
	This class creates a component which can be used to display and navigate through
	a hierarchical list of items.  Items in the tree are referred to as nodes.
	The PVNode class which accompanies the PVTree is used to refer to individual
	nodes of the tree.
	Many of the node specific methods of the PVTree class are duplications of
	equivalent methods implemented on the PVNode class.  They are provided at the PVTree
	level simply for programming convenience.  For instance the PVTree method
	getNextSibling(PVNode) is the same as the method getNextSibling() method of
	the PVNode class.
  */


public class PVTree extends Panel implements Serializable, ImageObserver {

  private transient Vector PVTreeListeners;
//  private transient PVTreeActionListener  actionListener = null;
  private boolean                   hasFocus;
  private boolean                   down;
  private boolean                   sticky;

  // Constructors

	transient PVNode	m_RootNode = null;
	
	transient PVNode	m_SelectedNode = null;
	transient PVNode	m_FirstVisible = null;
	transient PVNode	m_LastVisible = null;
	transient int		m_VisItemCount = 0;
	transient int		m_Count = 0;
	transient boolean   m_Added			= false;

    transient   int     m_sync          = 0;
	transient   Vector	m_ImageList;
	transient   Image	m_PlusImage     = null;
	transient   Image	m_MinusImage    = null;
	transient   MediaTracker m_MediaTracker;

	transient FontMetrics   m_fm;
	transient PVBorder	    m_Border;
	transient boolean		m_Redraw;
	transient PVNode		m_editing;

    PVTextField	    m_edit;
	Scrollbar	    m_vscroll;
	Scrollbar	    m_hscroll;
	PVTreeCanvas    m_canvas;

	boolean m_Sorted = false;
    boolean m_caseSensitive;

	// Tree Metrics
	int		m_Indentation	= 15;
	int		m_ButtonWidth	= 16;
	int		m_ImageWidth	= 16;
	int		m_BorderWidth	= 2;
	int		m_BorderStyle	= 1;
	int		m_LineHeight	= 16;
	int		m_LinesPerPage	= 0;
	int		m_Width			= 0;
	int		m_MaxLineLength	= 0;
	int		m_HScrollOffset = 0;
	int		m_VScrollOffset = 0;
	boolean m_LinesAtRoot	= true;

	boolean	m_ShowLines		= true;
	boolean m_ShowImages	= true;
	boolean m_ShowButtons	= true;
    boolean m_vscrollshown	= false;
    boolean m_hscrollshown	= false;
	Color	m_BorderColor	= Color.gray;
	Color	m_LineColor		= Color.lightGray;
	Color	m_SelTextColor	= Color.white;
	Color	m_SelBackColor	= new Color(0x000077);
	Font	m_BranchFont    = null;
	Font	m_Font          = null;

    boolean     m_canEdit = true;
   	boolean     m_editsw = false;
	
	public PVTree() {

        super();
			m_canvas = new PVTreeCanvas(this);
		    m_vscroll = new Scrollbar(Scrollbar.VERTICAL);
		    m_hscroll = new Scrollbar(Scrollbar.HORIZONTAL);

			m_edit = new PVTextField(this);
//		    setBackground(Color.white);
        init();

	}

    private void readObject(ObjectInputStream stream) throws IOException {
        try {
            stream.defaultReadObject();
		}
        catch (ClassNotFoundException ex) {
		}
        init();
    }

    private boolean init() {
        // Enable event processing

        enableEvents(AWTEvent.FOCUS_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK |
          AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.KEY_EVENT_MASK);
  	    	m_MediaTracker = new MediaTracker(this);

        PVTreeListeners = new Vector();
        m_RootNode		= new PVNode(null, 0, 0, null, -1);
		m_RootNode.m_Root = true;
		m_RootNode.m_Expanded = true;
		m_FirstVisible	= null;
		m_ImageList		= new Vector(5);
		m_Border		= new PVBorder();

        m_canvas.setLayout(null);
		m_canvas.add(m_edit);

        setLayout(new BorderLayout());
		add("Center", m_canvas);
        add("East", m_vscroll);
        add("South", m_hscroll);

		m_edit.setVisible(false);

	//By default, the scrollbar is set to invisible. Changed by Ling
//		m_vscroll.setVisible(true);
//		m_hscroll.setVisible(true);
		m_vscroll.setVisible(false);
		m_hscroll.setVisible(false);
    	m_vscroll.addAdjustmentListener(new ScrollAdjustmentListener());
    	m_hscroll.addAdjustmentListener(new ScrollAdjustmentListener());
		  m_Redraw = true;

		addComponentListener(new ComponentAdjustmentListener());
		return true;
		//{{INIT_CONTROLS
		//}}
}
    /**
     *  Removes the selected node from the tree
     */
    public void removeSelected()
    {
        if (m_SelectedNode != null)
        {
            removeNode(m_SelectedNode);
        }
    }

    /**
     *
     * print each node of TreeView beginning with node.  The printing is sent to the system out
     * window.  This function is for debugging purposes mainly.
     */
    public void printTree(PVNode node)
    {
        if (node == null)
        {
            return;
        }

        System.out.println(node.m_Text);
        printTree(node.m_FirstChild);
		printTree(node.m_Next);
    }

    /**
     * Returns the text value of the selected node.
     */
    public String getSelectedText()
    {
        if (m_SelectedNode==null)
        {
            return null;
        }

        return m_SelectedNode.getText();
    }

    /**
	 * Returns a boolean value indicating whether or not the specified node has
	 * any child nodes associated with it.
     */
	public boolean nodeHasChildren() {
		return false;
	}

   /**
	*   Returns the first child node of the specified node.  Null is returned if
	*   the node has no children
    */
	public PVNode getChildNode(PVNode node) {
		return node.m_FirstChild;
	}

/**
	Returns the next node following the specified node.  Null is returned if
	the node has no next node associated with it.
  */
	public PVNode getNextNode(PVNode node) {
		return node.m_Next;
	}

/**
	Sets the indentation amount for parent - child relationships in the tree
  */
	public void setIndentation(int Indent) {
		m_Indentation = Indent;
		m_canvas.repaint();
	}

/**
	Gets the indentation amount for parent - child relationships in the tree
  */
	public int getIndentation() {
		return m_Indentation;
	}

/**
	Gets the level of the specified node within the tree hierarchy.
  */
	public int getLevel(PVNode node) {
		return node.m_Level;
	}
/**
	Returns the total number of nodes in the tree
  */
	public int getCount() {
		return m_Count;
	}

/**
	Returns the next sibling node for the specified node. The
	value will be null if there is no next sibling.
  */
	public PVNode getNextSiblingNode(PVNode node) {
		return node.m_Next;
	}

/**
	Returns the previous sibling node for the specified node. The
	value will be null if there is no previous sibling.
  */
	public PVNode getPrevSiblingNode(PVNode node) {
		return node.m_Prev;
	}

/**
	Returns the parent of the specified node
  */
	public PVNode getParentNode(PVNode node) {
		return node.m_Parent;
	}

/**
	Returns the first visible node of the tree.  This is the first
	node that is visible if the tree is scrolled all the way to the top.
	This method will return null if there are no nodes in the tree. Visible nodes
	are nodes which are at the root level, or child nodes whose anscestors are all
	expanded.
  */
	public PVNode getFirstVisibleNode() {
		return m_RootNode.m_FirstChild;
	}

/**
	Returns the next visible node of the tree following the specified node.
	This method will return null if there are no further visible nodes. Visible nodes
	are nodes which are at the root level, or child nodes whose anscestors are all
	expanded.
  */
	public PVNode getNextVisibleNode(PVNode node) {
		return node.getNextVisible();
	}

/**
	Returns the previous visible node of the tree following the specified node.
	This method will return null if there are no previous visible nodes. Visible nodes
	are nodes which are at the root level, or child nodes whose anscestors are all
	expanded.
  */
	public PVNode getPrevVisibleNode(PVNode node) {
		return node.getPrevVisible();
	}

/**
	Returns the currently selected node in the PVTree component
  */
	public PVNode getSelectedNode() {
		return m_SelectedNode;
	}

/**
	Sets the first node that is visible at the top of the PVTree window to the
	specified node.  Here, visible is meant literally as the first line that the
	user can see at the top of the component.
  */
	public void setFirstVisible(PVNode node) {
		m_FirstVisible = node;
		repaint();
	}

/**
	Returns the root node of the PVTree.  There may be multiple nodes at the root level,
	in which case this method returns the first one.  The method will return null if
	the tree is empty.
  */
	public PVNode getRootNode() {
		return m_RootNode.m_FirstChild;
	}


/**
	Returns the text label attribute of the specified node.
  */
	public String getNodeText(PVNode node) {
		return node.m_Text;
	}

   /**
    * Sets the text value of node.
    * @param text the String value of the node text
    */
	public void setNodeText(PVNode node, String text) {
		node.m_Text = text;
		m_canvas.repaint();
	}

    /**
    *  Gets the data Object associated with node.  The Object is a user defined type that is associated
    *  with a node.
	*  @param node the PVNode object containing the user define data object
    */
	public Object getNodeData(PVNode node) {
		return node.m_Data;
	}

   /**
    *  Sets the data Object associated with node.  The Object is a user defined type that is associated
    *  with a node.
    *  @param node the PVNode object containing the user define data object
    *  @param data the Object to be stored with the node.
    */
	public void setNodeData(PVNode node, Object data) {
		node.m_Data = data;
	}

   /**
    *  Gets the string value associated with node.  The string is a user defined type that is associated
    *  with a node.  This string is not the same as the text value of the node
    *  @param node the PVNode object containing the user define string object
    */
	public Object getNodeString(PVNode node) {
		return node.m_String;
	}

   /**
    *  Sets the string value associated with node.  The string is a user defined type that is associated
    *  with a node.  This string is not the same as the text value of the node
	*  @param node the PVNode object containing the user define string object
    *  @param the String object to be associated with this node.
    */
	public void setNodeString(PVNode node, String string) {
		node.m_String = string;
	}

   /**
    *   Sorts the children of the specified node. Nodes are sorted by text value in ascending order
    *   only.
    *   @param node the node to be sorted.
    *   @param bRecursive specifies whether to sort all descendant nodes of the specified node.
    *   @param bCaseSensitive determines if sorting is performe with regard to case.
    */
	public void sortNodes(PVNode node, boolean bRecursive, boolean bCaseSensitive ) {
	    if(node == null)
	        node = m_RootNode;

	    node.sortChildren(bRecursive, bCaseSensitive);

		node = m_FirstVisible;
   		m_LastVisible = node;

		int total = m_LinesPerPage + 1 ;
		int count = 1;
		while(node != null && count <= total) {
			node = node.getNextVisible();
    		if(node != null)
				m_LastVisible = node;
			count++;
		}
	    m_canvas.repaint();
	}

   /**
    *   Searches the children of the specified node. The search will be conducted using the first
    *   non-null parameter of the last three.  The most common is the text parameter which will
    *   search the node text for the specified value.  
    *   The object parameter can be used when an object has been associated with a node of the TreeView.
    *   The searchNodes method will search the nodes to locate the one which contains an identical
    *   object reference value to the one passed in the object parameter.
    *   The stringData parameter can be used when a String value has been associated with a node of the TreeView
    *   using the setString method.
    *   The searchNodes method will search the nodes to locate the one which contains an
    *   identical string value to the one passed in the stringData parameter.
    *   @param node the node to be searched.
    *   @param text the node text to search for
    *   @param object the node object to search for
    *   @param stringData the node string data to search for
    *   To search the entire tree from the beginning, pass null as the first parameter value.
    */
	public PVNode searchNodes(PVNode startNode, String text, Object object, String stringData) {
        PVNode node;
        if(startNode == null)
            node = m_RootNode;
        else
			node = startNode;
        return node.searchChildren(text, object, stringData, m_caseSensitive);
	}

   public void resetVScrollbar() {
        // Reset m_VScrollOffset accord to the value of first visible node
        PVNode node = this.getRootNode();
        if (node == null) return;
        int count = 0;
        while((node != null) && (node != m_FirstVisible)) {
            count++;
			node = node.getNextVisible();
        }
        if (node == m_FirstVisible ) {
            m_VScrollOffset = count;
            m_vscroll.setValue(m_VScrollOffset);
        }
   }

   /**
    *  Makes sure the the specified node is visible in the PVTree window.  If necessary, the tree
    *  is scrolled.
    *  @param node the PVNode object which is to be made visible.
    */
	public void ensureVisible(PVNode node ) {
	    if(node.lineFromNode(this) == -1)
	    {
    	    m_FirstVisible = node;
			//Added by Ling
            resetVScrollbar();
	        repaint();
	    }
		return;
	}

   /**
    *  Performs mouse position testing to determine where the mouse pointer is located with respect to the
    *   objects of the PVTree control
    */
	public int hitTest(int x, int y) {
		return 0;
	}

   /**
    * Sets the style of the PVTree border.  The border style can be one of these styles:
    * @param BorderStyle the style type that is to be used to paint the border.
    */
	public void setBorderStyle(int BorderStyle) {
		m_BorderStyle = BorderStyle;
		doLayout();
		//repaint();
		m_canvas.repaint();
	}

   /**
    * Gets the current border style for the PVTree control
	*/
	public int getBorderStyle() {
		return m_BorderStyle;
	}

   /**
	*  Sets the width of the PVTree border.
	*  @param BorderWidth the width of the border in pixels.
	*/
	public void setBorderWidth(int BorderWidth) {
		m_BorderWidth = BorderWidth;
		setMetrics();
		doLayout();
		//validate();
	}

   /**
    *  Gets the width of the PVTree border.
    */
	public int getBorderWidth() {
		return m_BorderWidth;
	}

   /**
    *  Sets the color of the lines in the PVTree control.  The lines are drawn
    *  to connect related nodes in the tree structure.
    * @param lineColor a Color object.
    */
	public void setLineColor(Color lineColor) {
		m_LineColor = lineColor;
		m_canvas.repaint();
	}

   /**
    * Gets the current color for painting lines in the PVTree control.
    */
	public Color getLineColor() {
		return m_LineColor;
	}

   /**
    *  Sets the background color for the PVTree control
    *  @param backColor a Color object for the background color
    */
	public void setBackground(Color backColor) {
	    super.setBackground(backColor);
		  m_canvas.setBackground(backColor);
		  m_canvas.repaint();
	}

   /**
    *  Gets the current background color value for the PVTree control
    */
	public Color getBackground() {
		return super.getBackground();
	}

   /**
    *  Sets the foreground color for the PVTree control
    *  @param foreColor a Color object for the foreground color
    */
	public void setForeground(Color color) {
		super.setForeground(color);
		m_canvas.repaint();
	}

   /**
    *  Sets the color of selected text in the PVTree control.  Selected text is drawn for the
    *  node that is currently selected in the PVTree control
    *  @param textColor a Color object.
    *
    */
	public void setSelectedTextColor(Color textColor) {
		m_SelTextColor = textColor;
		m_canvas.repaint();
	}

   /**
    * Gets the current color for painting selected text in the PVTree control.
    */
	public Color getSelectedTextColor() {
		return m_SelTextColor;
	}

   /**
    *  Sets the color of the selected backgroun in the PVTree control.  Selected background
    *  is drawn for the node that is currently selected in the PVTree control.
    *  @param backColor a Color object.
    */
	public void setSelectedBackColor(Color backColor) {
		m_SelBackColor = backColor;
		m_canvas.repaint();
	}

   /**
    * Gets the current selected background color for painting selected text background in the PVTree control.
    */
	public Color getSelectedBackColor() {
		return m_SelBackColor;
	}

   /**
    *  Sets the border color for the PVTree control
    *  @param BorderColor a Color object for the border color
    */
	public void setBorderColor(Color BorderColor) {
		m_BorderColor = BorderColor;
		repaint();

	}

   /**
    *  Gets the current border color value for the PVTree control
    */
	public Color getBorderColor() {
		return m_BorderColor;
	}

    /**
	Sets a boolean value indicating whether or not Line display is enabled
    *  @param Value indicates whether node connecting lines should be displayed or not.
      */
	public void setLines(boolean newValue) {
		m_ShowLines = newValue;
		m_canvas.repaint();
	}

    /**
	Returns a boolean value indicating if Line display is enabled or not
      */
	public boolean getLines() {
		return m_ShowLines;
	}

/**
	Sets a boolean value indicating whether or not Plus and Minus button
	display is enabled
    @param Value indicates whether plus and minus buttons should be displayed or not.
  */
	public void setButtons(boolean newValue) {
		m_ShowButtons = newValue;
		m_canvas.repaint();
	}

    /**
	Returns a boolean value indicating if Plus and Minus button display
	is enabled or not
      */
	public boolean getButtons() {
		return m_ShowButtons;
	}

   /**
	Sets a boolean value indicating whether or not Image display is enabled
    *  @param Value indicates whether images should be displayed or not.
   */
	public void setImages(boolean newValue) {
		m_ShowImages = newValue;
		m_canvas.repaint();
	}

    /**
	Returns a boolean value indicating if Image display is enabled or not
      */
	public boolean getImages() {
		return m_ShowImages;
	}

    /**
    * Gets the sort flag for the Tree control.  This flag determines whether nodes will be added
    * in sorted order or not.
    */
	public boolean getSorted() {
		return m_Sorted;
	}

    /**
    * Sets the sort flag for the Tree control.  This flag determines whether nodes will be added
    * in sorted order or not.
    * @param sort turns sorting on or off with true or false.
    */
	public void setSorted(boolean sort) {
		m_Sorted = sort;
	}

    /**
	    Sets the editing capability on or off
      */
	public void setNodeEditing(boolean canEdit) {
		m_canEdit = canEdit;
	}

    /**
	    Gets the node editing capability for the TreeViewJ
      */
	public boolean getNodeEditing() {
		return m_canEdit;
	}

    /**
    * Gets the case sensitivity flag for the Tree control.  This flag determines whether nodes
    * will be sorted with regard to case sensitivity or not.
    */
	public boolean getCaseSensitive() {
		return m_caseSensitive;
	}

    /**
    * Sets the case sensitivity flag for the Tree control.  This flag determines whether nodes
    * will be sorted with regard to case sensitivity or not.
    * @param caseSensitive turns case sensitivity on or off with true or false.
    */
	public void setCaseSensitive(boolean caseSensitive) {
		m_caseSensitive = caseSensitive;
	}

/**
	Returns a boolean value indicating if lines are drawn between root level nodes
	of PVTree.
	*/
	public boolean getLinesAtRoot() {
		return m_LinesAtRoot;
	}

/**
	Sets a boolean value indicating whether or not lines are to be drawn between
	root level nodes of PVTree.
  */
	public void setLinesAtRoot(boolean lines) {
		m_LinesAtRoot = lines;
		m_canvas.repaint();
	}

    /**
	* Returns the current redraw flag.
    */
	public boolean getRedraw() {
		return m_Redraw;
	}

   /**
    *  Refreshes the PVTree control so that all visible nodes are repainted.
    */
//	public void repaint() {
//	    super.repaint();
//		m_canvas.repaint();
//	}

   /**
	* Sets the redraw flag to the given boolean value.  If the value is false,
	* drawing will be disabled until such time as the Redraw switch is set
	* back to true.  This property can be used when performing a large number
	* of insertions or deletions of nodes.
    */
	public void setRedraw(boolean Redraw) {
		if((Redraw == true) && (m_Redraw == false)) {
			m_Redraw = Redraw;
			setMetrics();
			m_canvas.repaint();
		}
		else
			m_Redraw = Redraw;
	}

   /**
	* Returns the number of items that are expanded and visible when scrolling
	* through PVTree.  It is not just the number of nodes visible on the screen
	* at the current moment.
    */
	public int getVisibleCount() {
		int count = 0;
		PVNode node = m_RootNode;
		if(node.m_FirstChild != null) {
			// count++;
			node = node.m_FirstChild;
			while(node != null) {
				node = node.getNextVisible();
				count++;
			}
		}
		return count;
	}

	protected boolean setVScrollbar() {

		m_VisItemCount = getVisibleCount();

        if(m_VisItemCount > m_LinesPerPage) {
			if(!m_vscrollshown) {
				m_vscroll.setVisible(true);
				validate();
				m_vscrollshown = true;
			}
		}
		else {
			if(m_vscrollshown) {
			    m_FirstVisible = m_RootNode.m_FirstChild;
				m_vscroll.setVisible(false);
				validate();
				m_vscrollshown = false;
			}
		}

    		//m_VScrollOffset = 1;
    		m_vscroll.setValues(m_VScrollOffset, m_LinesPerPage, 0, m_VisItemCount);

		return true;
	}

	protected boolean setHScrollbar() {
		if(!m_Added)
			return false;
	    Dimension dim = getSize();
		m_MaxLineLength = getMaxLineLength();
		if(m_MaxLineLength > dim.width) {
			if(!m_hscrollshown) {
				m_hscroll.setVisible(true);
				validate();
				m_hscrollshown = true;
			}
		}
		else {
			if(m_hscrollshown) {
				m_hscroll.setVisible(false);
				validate();
				m_hscrollshown = false;
			}
		}
		m_hscroll.setValues(m_HScrollOffset, 10, 0, m_MaxLineLength);
	    return true;
	}

	public int getMaxLineLength() {
		int length = 0;
		PVNode node = m_RootNode;
		try {
    		Graphics g = getGraphics();
			if (g != null)
			{
				m_fm = g.getFontMetrics();
				if( node != null && node.m_FirstChild != null)
				{
					node = node.m_FirstChild;
					while(node != null)
					{
						int nodeLen= getNodeWidth(node);
						length = Math.max(length, nodeLen);
						node = node.getNextVisible();
					}
				}
				g.dispose();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
    	    return 20;
    	} 
		return length;
	}

    public boolean isFocusTraversable() {
        return true;   
    }
    
	private int getNodeWidth(PVNode node) {
		int length = 43 + (node.m_Level * m_Indentation);

		if(m_ShowButtons)
			length += m_MinusImage.getWidth(this);

		if(m_ShowImages)
			length += 16;

		String s = node.m_Text;
		if(s != null)
			length += m_fm.stringWidth(node.m_Text);
		return length;
	}

    /**
	 *  Begins label editing for the specified PVNode object
     */
	public void startLabelEdit(PVNode node) {
		int w;
		int margin;

		if(node == null)
			return;
		if(m_editing != null) {
			endLabelEdit(true);
		}
		
        PVTreeActionEvent e = createPVEvent(0, PVTreeActionEvent.BEGIN_LABEL_EDIT, 0, 0, 0, 0, node);
        processActionEvent(e);
        if(e.getCancel())
            return;
        
		Graphics g = getGraphics();
		FontMetrics fm = g.getFontMetrics();
		margin = Math.max(0, (node.m_Level * m_Indentation - m_HScrollOffset));
		int line = node.lineFromNode(this);
		line--;
		if(line == -1)
			return;
		w = fm.stringWidth(node.m_Text);
		m_edit.setText(node.m_Text);
		m_edit.select(0, 500);
		m_edit.setBounds(margin + 35, line * m_LineHeight - 1, w + 15, m_LineHeight + 4);
		//m_edit.setMinWidth(m_edit.autoSize());
		m_edit.setVisible(true);
		m_editing = null;
		m_editsw = true;
		m_edit.requestFocus();
		m_editing = node;
	}

	/**
	 * Ends label editing and closes the edit window
     */
	public void endLabelEdit(boolean Save) {

		if(m_editing != null) {
            PVTreeActionEvent e = createPVEvent(0, PVTreeActionEvent.END_LABEL_EDIT, 0, 0, 0, 0, null); //m_edit.getText());
            processActionEvent(e);
            if(e.getCancel())
                Save = false;

            if(Save)
			{
				m_editing.m_Text = m_edit.getText();
				if(m_Sorted)
    				sortNodes(m_editing.m_Parent, false, m_caseSensitive);
				setMetrics();
			}
			m_edit.setVisible(false);
			m_editing = null;
		}
	}

	public void addNotify() {
//		System.out.println("AddNotify called");
		super.addNotify();
		m_Added = true;
		if(m_MinusImage == null) {
    		m_MinusImage = createImage(9, 9);
    		m_PlusImage = createImage(9, 9);
			Graphics g = m_MinusImage.getGraphics();
    		g.setColor(Color.white);
    		g.fillRect(0, 0, 8, 8);
    		g.setColor(Color.black);
    		g.drawRect(0, 0, 8, 8);
    		g.drawLine(2, 4, 6, 4);
    		g = m_PlusImage.getGraphics();
    		g.setColor(Color.white);
    		g.fillRect(0, 0, 8, 8);
    		g.setColor(Color.black);
    		g.drawRect(0, 0, 8, 8);
    		g.drawLine(2, 4, 6, 4);
    		g.drawLine(4, 2, 4, 6);

    	}
		setMetrics();
		validate();
	}

    public void setBounds(int x, int y, int w, int h) {
		super.setBounds(x, y, w, h);
//      System.out.println("PVTree setbounds called");
//		String b = new String("bounds =");
//		b = b + String.valueOf(x) + ", " + String.valueOf(y) + ", " + String.valueOf(w) + ", " + String.valueOf(h);
//		System.out.println(b);
		setMetrics();
		if(m_Added) {
			validate();
		}
	}

	public void setSize(int w, int h) {
		super.setSize(w, h);
		setMetrics();
    validate();
//		String b = new String("SetSize size =");
//  	b = b + String.valueOf(w) + ", " + String.valueOf(h);
//		System.out.println(b);
//		if(!m_Added) {
//    		System.out.println("setSize do Layout called");
//			validate();
//		}
	}

   /**
    *  Sets the font object used for drawing branch nodes of the PVTree object
    *  A branch node is a node having one or more leaf nodes or child nodes.
    *  @param Font the Font used to draw branch nodes.
    */
	public void setBranchFont(Font font) {
		m_BranchFont = font;
		setMetrics();
		m_canvas.repaint();
	}

   /**
    *  Gets the font used for drawing nodes with children
    *  @return a Font object which represents the font used to draw branch nodes
    */
	public Font getBranchFont() {
	    if(m_BranchFont == null)
	        return getFont();
		return m_BranchFont;
	}

	public void setFont(Font font) {
		super.setFont(font);
		setMetrics();
		m_canvas.repaint();
	}

	private void setMetrics() {

		if(!m_Added)
			return;

        resetVScrollbar();

		setHScrollbar();
		Dimension dim = m_canvas.getSize();
		m_Width = dim.width;

		Graphics g = m_canvas.getGraphics();
		m_fm = g.getFontMetrics();
		m_edit.setFontMetrics(m_fm);

        int fh = m_fm.getHeight();
        if(fh < 16)
            fh = 16;
        int dh = dim.height;
        int lh = dh / fh;
        if(lh == 0)
            return;

//		m_LineHeight = Math.max(16, m_fm.getHeight());
//		m_LinesPerPage = dim.height / m_LineHeight ;
      m_LineHeight = fh;
		  m_LinesPerPage = lh ;

        if(m_FirstVisible != null)
    		m_LastVisible =  m_FirstVisible.NodeAtLine(m_LinesPerPage);

		if(m_LinesPerPage > 0) {
			setVScrollbar();
		}

	}


    /**
	 *  Sets the currently selected node to the given PVNode object
     */
	public void setSelectedNode(PVNode node) {
		if(node == m_SelectedNode)
			return;
		if((m_SelectedNode != null )&& m_SelectedNode.m_removed)
		    m_SelectedNode = null;

		PVNode old = m_SelectedNode;

        PVTreeActionEvent e = createPVEvent(0, PVTreeActionEvent.BEFORE_NODE_CHANGE_EVENT, 0, 0, 0, 0, old);
        processActionEvent(e);
        if(e.getCancel())
            return;

		int oldLine = -1;
		if(old != null) {
			if(node == old)
				return;
		   oldLine = old.lineFromNode(this);
		}
		int newLine = -1;
		if(node != null)
			newLine = node.lineFromNode(this);

		m_SelectedNode = node;
		Graphics g = m_canvas.getGraphics();
		if(oldLine != -1)
			old._paint(this, g, oldLine);
		if(newLine != -1)
			node._paint(this, g, newLine);
        e = createPVEvent(0, PVTreeActionEvent.AFTER_NODE_CHANGE_EVENT, 0, 0, 0, 0, node);
        processActionEvent(e);
        if(e.getCancel())
            return;
	}

/**
	Adds an image to the indexed list of images maintained for nodes.  The normal and
	selected images are specified for each node as an index and are drawn from this
	list.

  */
	public int addImage(Image newImage) {
		int size;
		if(newImage == null)
		    return 0;
		m_ImageList.addElement(newImage);
		size = m_ImageList.size();
		m_MediaTracker.addImage(newImage, size + 2);
		try { m_MediaTracker.waitForID(size + 2); }
		catch(InterruptedException e) { }
		return size;
	}

/**
	Adds a new node to the PVTree at the location specified by Relative and Relationship.
	The Relative parameter can be any other node that is already in the tree or it can
	be null, in which case the new node is added at the root level of the tree.  The relationship
	parameter can be one of PVNode.FIRST, PVNode.LAST or PVNode.PARENT.  PVNode.PARENT is the most
	commonly used value.
  */
	public PVNode addNode(PVNode Relative, int Relationship, String Text, int Image, int SelectedImage) {
		PVNode addedNode;
		boolean bChild = false;
		if((Relationship == PVNode.FIRST_SIBLING) || (Relationship == PVNode.LAST_SIBLING)) {
		    if((Relative == null) || (Relative.m_Parent == null))
                addedNode = m_RootNode.addChild(Relationship, Text, Image, SelectedImage, m_Sorted, m_caseSensitive) ;
            else
    			addedNode = Relative.m_Parent.addChild(Relationship, Text, Image, SelectedImage, m_Sorted, m_caseSensitive);
		}
		else
		if(Relative != null) {
		    bChild = true;
			addedNode = Relative.addChild(Relationship, Text, Image, SelectedImage, m_Sorted, m_caseSensitive);
		}
		else
			addedNode = m_RootNode.addChild(Relationship, Text, Image, SelectedImage, m_Sorted, m_caseSensitive);

		m_Count++;
		if(m_FirstVisible == null)
			m_FirstVisible = addedNode;

		if(m_Added && m_Redraw) {
			setVScrollbar();
			m_LinesPerPage++;  // artificially increase to make sure the tree gets painted for the last line
            if(m_canvas.isNodeVisible(addedNode))
    			m_canvas.repaint();
            else
                if(bChild)
   			    m_canvas.repaint();
			m_LinesPerPage--;  // restore to original correct value
		}
		return addedNode;
	}

    /**
     *  Adds a node as a child of an existing node
     */
    public PVNode addChildNode(PVNode parentNode, String Text, int Image, int SelectedImage)
    {
        return addNode(parentNode, PVNode.PARENT_NODE, Text, Image, SelectedImage);
    }

    /**
     *
     */
    public PVNode addSiblingNode(PVNode siblingNode, String Text, int Image, int SelectedImage)
    {
        return addNode(siblingNode, PVNode.LAST_SIBLING, Text, Image, SelectedImage);
    }
    /**
     *  add new node to level 0
     */
    public PVNode addRootNode(String Text, int Image, int SelectedImage) {
        if (m_RootNode == null)
        {
            PVNode newNode = new PVNode(Text, Image, SelectedImage, m_RootNode, 0);
            m_RootNode = newNode;
            m_SelectedNode = m_RootNode;
            m_Count = 1;
            return newNode;
        }
        else
        {
            return addNode(m_RootNode, PVNode.LAST_SIBLING, Text, Image, SelectedImage);
        }
    }

/**
	Removes the specifed node from its position in the PVTree object
    @param Node designates the node to be removed.

  */
	public void removeNode(PVNode node) {
		if(node == m_FirstVisible) {
			m_FirstVisible = m_FirstVisible.getNextParent();
		}
		if(node == m_LastVisible) {
		    PVNode prevnode = m_LastVisible.getPrevVisible();
			if(prevnode != null)
    			m_LastVisible = prevnode;
		}

		if(node != null)
			node.remove(this);

		if(m_Added && m_Redraw) {
			m_VisItemCount = getVisibleCount();
			if(m_VisItemCount <= m_LinesPerPage + 1) {
				m_vscroll.setVisible(false);
				m_vscrollshown = false;
			}
			m_canvas.repaint();
		}
		
		if(m_SelectedNode != null)
    		if(m_SelectedNode.m_removed)
	    	    m_SelectedNode = null;
        setVScrollbar();
	}

/**
	Removes all nodes from the PVTree object and sets it to empty
  */
	public void removeAll() {
		m_RootNode.remove(this);
		m_RootNode = null;
		m_RootNode = new PVNode(null, 0, 0, null, -1);
		m_RootNode.m_Root = true;
		m_RootNode.m_Expanded = true;
		m_FirstVisible	= null;
        m_Count = 0;
		setMetrics();
		m_canvas.repaint();
	}

    /**
	* Removes all nodes from the PVTree object and sets it to empty
    */
	public void reset() {
		removeAll();

        // the floowing is added by Ling
        m_HScrollOffset = 0;
	    m_VScrollOffset = 0;
	}

    /**
	 * Sets the expanded state of the specified node to true or false
	 * @param node the Node to be expanded or collapsed
	 * @param expanded a boolean value indicating whether to expand (true), or
	 * collapse (false) the specified node.
     */
	public void setExpanded(PVNode node, boolean expanded) {
        if(expanded) {
            PVTreeActionEvent e = createPVEvent(0, PVTreeActionEvent.NODE_EXPANDING, 0, 0, 0, 0, node);
            processActionEvent(e);
            if(e.getCancel())
                return;
        }
        else {
            PVTreeActionEvent e = createPVEvent(0, PVTreeActionEvent.NODE_COLLAPSING, 0, 0, 0, 0, node);
            processActionEvent(e);
            if(e.getCancel())
                return;
        }
		node.m_Expanded = expanded;
		setMetrics();
    m_canvas.repaint();
        if(expanded) {
            PVTreeActionEvent e = createPVEvent(0, PVTreeActionEvent.NODE_EXPANDED, 0, 0, 0, 0, node);
            processActionEvent(e);
            if(e.getCancel())
                return;
        }
        else {
            PVTreeActionEvent e = createPVEvent(0, PVTreeActionEvent.NODE_COLLAPSED, 0, 0, 0, 0, node);
            processActionEvent(e);
            if(e.getCancel())
                return;
        }

	}

    /**
	 * Returns the expanded state of the given node
	 * @param node the node whose expanded state is to be returned.
     */
	public boolean getExpanded(PVNode node) {
		return node.m_Expanded;
	}

    /**
     * Expands all branches of the PVTree to make all nodes visible
     */
	public void expandAll() {
		boolean oldRedraw = m_Redraw;
		m_Redraw = false;
		m_RootNode.expand(true);
		m_Redraw = oldRedraw;
		setMetrics();
		m_canvas.repaint();
	}

    /**
	Collapses all branches of the PVTree
    */
	public void collapseAll() {
		boolean oldRedraw = m_Redraw;
		m_Redraw = false;
		m_RootNode.collapse(true);
		m_Redraw = oldRedraw;
		setMetrics();
		m_canvas.repaint();
	}

	/**
	paints the PVTree control
	  */
	public void update(Graphics g) {
		paint(g);
		m_canvas.repaint();
	}

	/**
	 * returns the preferred size of the PVTree control
	 */
	public Dimension getPreferredSize() {
		Dimension d = getSize();
		if(d.width == 0)
			d.width = 160;
		if(d.height == 0)
			d.height = 160;
		return d;
	}

	public Insets getInsets() {
		return new Insets(m_BorderWidth,
			m_BorderWidth,
			m_BorderWidth,
			m_BorderWidth);
	}

	static int oneDemo = 0;
	public void paint(Graphics g)
	{
		//-----------------------------
/*		if(oneDemo < 1)
		{
			try
			{
				Component f = this;
				while(!(f == null || f instanceof Frame))
				f = f.getParent();
				if(f != null)
					new PVDemoScreen((Frame)f, "TreeViewJ");
				oneDemo++;
			} catch(Exception e) { return; }
		}
	*/

//    super.paint(g);
		Dimension dim = getSize();
		Rectangle r = new Rectangle(0, 0, dim.width, dim.height);
		m_Border.Draw(g, r, m_BorderStyle, m_BorderWidth, m_BorderColor, false);
	}

	class ScrollAdjustmentListener implements AdjustmentListener
	{
		public void adjustmentValueChanged(AdjustmentEvent e)
		{
			processScrollEvent(e);
		}
	}

	class ComponentAdjustmentListener extends ComponentAdapter
	{
			public void componentShown(ComponentEvent evt)  {  setMetrics();    }
			public void componentResized(ComponentEvent evt) {  setMetrics();    }
			//public void componentMoved(ComponentEvent e) {  setMetrics();    }
			//public void componentHidden(ComponentEvent e){  setMetrics();    }
	}

	public void processScrollEvent(AdjustmentEvent event) {
//        if(m_sync)
//            return;
        m_sync += 1;
        if(event.getSource() == m_vscroll) {
		    int id = event.getID();
		    int type = event.getAdjustmentType();
			switch(type) {
			case AdjustmentEvent.UNIT_DECREMENT :
			    m_canvas.scrollLineUp(event);
				break;
			case AdjustmentEvent.UNIT_INCREMENT :
			    m_canvas.scrollLineDown(event);
				break;
			case AdjustmentEvent.BLOCK_DECREMENT :
			    m_canvas.scrollPageUp(event);
				break;
			case AdjustmentEvent.BLOCK_INCREMENT :
			    m_canvas.scrollPageDown(event);
				break;
			case AdjustmentEvent.TRACK :
			    m_canvas.scrollAbsolute(event);
				break;
			}
		}
		else
		if(event.getSource() == m_hscroll) {
		    int id = event.getID();
		    int type = event.getAdjustmentType();
			switch(type) {
			case AdjustmentEvent.UNIT_DECREMENT :
				m_canvas.scrollLeft(event);
				break;
			case AdjustmentEvent.UNIT_INCREMENT :
				m_canvas.scrollRight(event);
				break;
			case AdjustmentEvent.BLOCK_DECREMENT :
			    m_canvas.hScrollPageUp(event);
				break;
			case AdjustmentEvent.BLOCK_INCREMENT :
			    m_canvas.hScrollPageDown(event);
			    break;
			case AdjustmentEvent.TRACK :
			    m_canvas.hScrollAbsolute(event);
			    break;
			}
		}
//        m_sync = false;
	}

	public void adjustmentValueChanged(Event e) {

	}

  PVTreeActionEvent createPVEvent(long when, int id, int x, int y, int key, int modifiers, PVNode arg) {

      PVTreeActionEvent e = new PVTreeActionEvent(this, id, modifiers, arg);
      return e;
 }


  public synchronized void addKeyListener(KeyListener l)
  {
	//super.addKeyListener(l);
	m_canvas.addKeyListener(l);
  }
  public synchronized void removeKeyListener(KeyListener l)
  {
	//super.removeKeyListener(l);
	m_canvas.removeKeyListener(l);
  }
  // Event processing methods
  public synchronized void addPVTreeActionListener(PVTreeActionListener l) {
     PVTreeListeners.addElement(l);
  }

  public synchronized void removePVTreeActionListener(PVTreeActionListener l) {
     PVTreeListeners.removeElement(l);
  }

  protected void processActionEvent(PVTreeActionEvent e) {
//    System.out.println("processActionEvent called");
    // Deliver the event to all registered action event listeners
    for (int i = 0; i < PVTreeListeners.size(); i++) {
        PVTreeActionListener al = (PVTreeActionListener)PVTreeListeners.elementAt(i);
        al.actionPerformed(e);
    }
    dispatchEvent(e);
  }

  protected void processFocusEvent(FocusEvent e) {
    // Get the new focus state and repaint
    switch(e.getID()) {
      case FocusEvent.FOCUS_GAINED:
        hasFocus = true;
        m_canvas.requestFocus();
        //repaint();
        break;

      case FocusEvent.FOCUS_LOST:
        hasFocus = false;
        //repaint();
        break;
    }

    // Let the superclass continue delivery
    super.processFocusEvent(e);
  }

  protected void processMouseEvent(MouseEvent e) {
    // Track mouse presses/releases
    switch(e.getID()) {
      case MouseEvent.MOUSE_PRESSED:
        down = !down;
        repaint();
        break;

      case MouseEvent.MOUSE_RELEASED:
        if (down && !sticky) {
          down = false;
          repaint();
        }
        break;
	}
	super.processMouseEvent(e);
  }

  protected void processMouseMotionEvent(MouseEvent e) {
    // Track mouse drags
  }

  protected void processKeyEvent(KeyEvent e) {
	// Simulate a mouse click for certain keys
	if (e.getKeyCode() == KeyEvent.VK_ENTER ||
	  e.getKeyChar() == KeyEvent.VK_SPACE) {
		   super.processKeyEvent(e);
	}
  }
	public void addPropertyChangeListener(PropertyChangeListener l)
	{ 
	}

	public void removePropertyChangeListener(PropertyChangeListener l)
	{
	}

	//{{DECLARE_CONTROLS
	//}}
}


