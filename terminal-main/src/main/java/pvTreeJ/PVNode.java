package pvTreeJ;

import java.awt.*;
import java.util.*;
import java.applet.*;
import java.net.*;
import java.io.Serializable;

/**

   The PVNode class is used to represent a single line item in the PVTree
   control.  Nodes are created through the addNode method of PVTree.
   They are destroyed through the removeNode method of PVTree. The
   attributes of the PVNode object include: text string, image, selected
   image, user define data value, user defined string value, next and
   previous nodes, parent node and first and last child nodes.
 */
public class PVNode {

	PVNode	m_Next = null;
	PVNode	m_Prev = null;
	PVNode	m_FirstChild = null;
	PVNode	m_LastChild  = null;
	PVNode	m_Parent	 = null;
	boolean m_Root		 = false;
	boolean m_removed    = false;

	public static final int LAST_SIBLING	= 2;
	public static final int FIRST_SIBLING	= 1;
	public static final int PARENT_NODE	= 0;

	int		m_Level;
	boolean	m_Expanded;

	String	m_Text;
	int		m_Image;
	int		m_SelImage;
	Object  m_Data;
	String	m_String;
	private NodeAttrib m_Attrib;

	PVNode(String Text, int Image, int SelectedImage, PVNode Parent, int Level) {
		m_Text		= Text;
		m_Image		= Image;
		m_SelImage	= SelectedImage;
		m_Parent	= Parent;
		m_Level		= Level;
	}
	PVNode() {
	}
    /**
	 * Sets the background color for this node
	 * @param color the new backbround color
     */
	public void setBackground(Color color) {
		if(m_Attrib == null)
			m_Attrib = new NodeAttrib();
		m_Attrib.m_bColor = color;
	}

    /**
	 * Gets the background color for this node.  If no background color has been set, the return
	 * value is null.
     */
	public Color getBackground() {
		if(m_Attrib != null)
			return m_Attrib.m_bColor;
		else
			return null;
	}

    /**
	 * Sets the background color for this node
	 * @param color the new backbround color
     */
	public void setForeground(Color color) {
		if(m_Attrib == null)
			m_Attrib = new NodeAttrib();
		m_Attrib.m_fColor = color;
	}

    /**
	 * Gets the foreground color for this node.  If no foreground color has been set, the return
	 * value is null.
     */
	public Color getForeground() {
		if(m_Attrib != null)
			return m_Attrib.m_fColor;
		else
			return null;
	}

	public void setImages(int Image, int SelectedImage)
	{
		m_Image		= Image;
		m_SelImage	= SelectedImage;
	}
	/**
	 * Returns the font associated with this node.  If no font has been specified for this node,
	 * this method returns null.
	 */
	public Font getFont() {
		if(m_Attrib != null)
			return m_Attrib.m_Font;
		else
			return null;
	}

	/**
	 * Sets the font for this node to the specified font object.
	 * @param font the font object to be used when displaying this node.
     */
	public void setFont(Font font) {
		if(m_Attrib == null)
			m_Attrib = new NodeAttrib();
		m_Attrib.m_Font = font;
	}

    /**
	 *  Collapses this node so that none of its children are visible.  If the bChildren parameter
	 *  is set, the descendants of this node are collapsed as well.
	 *  @param Children  determines if child items of the node should be collapsed as well.
     */
	public void collapse(boolean bChildren) {
		m_Expanded = false;

        if(bChildren) {
    		PVNode next = m_FirstChild;
	    	while(next != null) {
		    	next.collapse(bChildren);
			    next = next.m_Next;
			}
		}
	}

    /**
	 *  Toggles the expanded state of this node.  If the node is now collapsed, it will be expanded.
	 *  If it is now expanded, it will be collapsed. If the bChildren parameter
	 *  is set, the descendants of this node are toggled as well.
	 *  @param Children determines if child items of the node should be toggled as well.
     */
	public void toggle(boolean bChildren) {
		if(m_Expanded)
			m_Expanded = false;
		else
			m_Expanded = true;
		if(bChildren) {
    		PVNode next = m_FirstChild;
	    	while(next != null) {
		    	next.toggle(bChildren);
			    next = next.m_Next;
    		}
    	}
	}

    /**
	 *  Expands this node so that its children are visible.  If the bChildren parameter
	 *  is set, the descendants of this node are expanded as well.
	 *  @param Children determines if child items of the node should be expanded as well.
     */
    public void expand(boolean bChildren) {
    	m_Expanded = true;
	    if (bChildren) {
      	  PVNode next = m_FirstChild;
	    	while(next != null) {
		      next.expand(bChildren);
    		  next = next.m_Next;
    		}
		}
	}

	PVNode addChild(int Relationship, String Text, int Image, int SelectedImage, boolean sorted, boolean caseSensitive) {

		PVNode NewNode = new PVNode(Text, Image, SelectedImage, this, m_Level + 1);
		if(m_LastChild == null) {
			m_FirstChild = m_LastChild = NewNode;
		}
		else
        if(sorted) {
            addSorted(NewNode, caseSensitive);
        }
        else
		if((Relationship == PVNode.LAST_SIBLING) ||
		   (Relationship == PVNode.PARENT_NODE))
		{
			m_LastChild.m_Next = NewNode;
			NewNode.m_Prev = m_LastChild;
			m_LastChild = NewNode;
		}
		else
		if(Relationship == PVNode.FIRST_SIBLING)
		{
			m_FirstChild.m_Prev = NewNode;
			NewNode.m_Next = m_FirstChild;
			m_FirstChild = NewNode;
		}
		return NewNode;
	}

	void addSorted(PVNode node, boolean caseSensitive) {
        PVNode slotNode = null;
	    int result;
	    if(m_FirstChild == null)
	        return;
	    slotNode = m_FirstChild;
        result = 1;
        while((slotNode != null) && (result > 0)) {
           if(caseSensitive)
               result = node.m_Text.compareTo(slotNode.m_Text);
           else {
                String one = node.m_Text.toUpperCase();
                String two = slotNode.m_Text.toUpperCase();
                result = one.compareTo(two);
           }
           if(result <= 0) {
                // insert the current node before the slotNode
                if(slotNode.m_Prev == null) {  // updating the first child
                    slotNode.m_Prev = node;
                    node.m_Next = slotNode;
                    m_FirstChild = node;
                }
                else
                {   // inserting before slotnode
                    slotNode.m_Prev.m_Next = node;
                    node.m_Prev = slotNode.m_Prev;
                    node.m_Next = slotNode;
                    slotNode.m_Prev = node;
                }
           }
           slotNode = slotNode.m_Next;
        }
        if(result > 0) {  // insert as the lastChild
            m_LastChild.m_Next = node;
            node.m_Prev = m_LastChild;
            m_LastChild = node;
            node.m_Next = null;
        }
	}

	void remove(PVTree tree) {
	    // The node is in the middle of other nodes
		if((m_Next != null) && (m_Prev != null)) {
			m_Next.m_Prev = m_Prev;
			m_Prev.m_Next = m_Next;
		}
		else {  // The node is the first child of several
			if(m_Next != null) {
				m_Next.m_Prev = null;
        	    if(m_Parent != null) {
	                if(m_Parent.m_FirstChild == this)
    	                m_Parent.m_FirstChild = m_Next;
    	        }
        	}
			else // The node is the last child of several
			if(m_Prev != null) {
				m_Prev.m_Next = null;
        	    if(m_Parent != null) {
	                if(m_Parent.m_LastChild == this)
    	                m_Parent.m_LastChild = m_Prev;
    	        }
    	    }
    	    else // This is the only child
       	    if(m_Parent != null) {
                if(m_Parent.m_FirstChild == this)
   	                m_Parent.m_FirstChild = null;
                if(m_Parent.m_LastChild == this)
   	                m_Parent.m_LastChild = null;
   	        }
		}

		m_Prev = null;
		m_Next = null;

		if(m_FirstChild != null) {
			PVNode next = m_FirstChild;
			while(next != null) {
				PVNode remove = next;
				next = next.m_Next;
				remove.remove(tree);
			}
			m_FirstChild = null;
			m_LastChild = null;
		}

		m_Parent = null;
		m_removed = true;
		tree.m_Count--;
	}

	int _paint(PVTree Tree, Graphics g, int count) {
		int hOffset = 3 + (m_Level * Tree.m_Indentation) - Tree.m_HScrollOffset;
		int vOffset = count * Tree.m_LineHeight;
		int hStart =  7 - Tree.m_HScrollOffset;

		// Erase the background first
    Dimension dim = Tree.getSize();

/*  Remove by Ling , Here use the default background to paint the whole area
    Color b = getBackground();
    if (b != null)
        g.setColor(b);
    else
*/
        g.setColor(Tree.m_canvas.getBackground());
//       System.out.println(getText()+" 's background color is " +  Tree.m_canvas.getBackground());
    g.fillRect(0, vOffset - Tree.m_LineHeight, dim.width, Tree.m_LineHeight);


// the following is the origianl code
//    g.setColor(Tree.m_canvas.getBackground());
//		g.fillRect(0, vOffset - Tree.m_LineHeight, 400, Tree.m_LineHeight);

		if(Tree.m_ShowLines) {
			int i;
			int j;
			int x;
			int hLength;
			PVNode parent;
            if(m_removed)
                return 0;

			if(Tree.m_ShowButtons)
				x = 0; // additional offset for lines to meet when ShowButtons is false
			else
				x = 4;

    		if(Tree.m_ShowImages)
    		    hLength = 15;
   		    else
    		    hLength = 24;

			g.setColor(Tree.m_LineColor);
           // draw vertical lines
			for (i = 0; i <= m_Level; i++) {
				j = m_Level;
				parent = this;
				while(j > i) {
					parent = parent.m_Parent;
					j--;
				}

				if((i == 0) && !Tree.m_LinesAtRoot)
					continue;

				if((m_Level == 0) && (m_Prev == null)) {
					g.drawLine(hStart, vOffset,
					   hStart, vOffset - 6);
				}
				else
				if((parent.m_Next == null) && (i == m_Level))
					g.drawLine((i * Tree.m_Indentation) + hStart, vOffset - Tree.m_LineHeight - x,
					   (i * Tree.m_Indentation) + hStart, vOffset - 6);
				else
				if(parent.m_Next != null)
					g.drawLine((i * Tree.m_Indentation) + hStart, vOffset - Tree.m_LineHeight - x,
					   (i * Tree.m_Indentation) + hStart, vOffset);
			}
			// draw horizontal line
			if(!((m_Level == 0) && !Tree.m_LinesAtRoot))
				g.drawLine((m_Level * Tree.m_Indentation) + hStart, vOffset - (Tree.m_LineHeight / 2) + 2,
					   (m_Level * Tree.m_Indentation) + hStart + hLength, vOffset - (Tree.m_LineHeight / 2) + 2);

		}
        // paint the plus and minus buttons
		if(Tree.m_ShowButtons) {
			if(m_FirstChild != null) {
				if(m_Expanded) {
					g.drawImage(Tree.m_MinusImage, hOffset, vOffset - 11, Tree);
				}
				else {
					g.drawImage(Tree.m_PlusImage, hOffset, vOffset - 11 , Tree);
				}
			}
			int t = Tree.m_MinusImage.getWidth(Tree);
			hOffset += t;
			hOffset += 5;
		}
		else {
			hOffset += Tree.m_MinusImage.getWidth(Tree);
			hOffset += 5;
		}
		// paint the image
		if(Tree.m_ShowImages) {
			Image image = null;
			if(Tree.m_SelectedNode == this) {
			    if(m_SelImage < Tree.m_ImageList.size())
    				image = (Image)Tree.m_ImageList.elementAt(m_SelImage);
			}
			else {
			    if(m_Image < Tree.m_ImageList.size())
    				image = (Image)Tree.m_ImageList.elementAt(m_Image);
			}
			if(image != null) {
    			g.drawImage(image, hOffset, vOffset - 14, Tree);
	    		hOffset += 16; // image.getWidth(Tree);
		    	hOffset += 5;
		    }
		}
		else {
			if(Tree.m_ShowLines)
				hOffset += 21;
		}

		Font oldFont = g.getFont();

		Font font = getFont();
		if(font == null) {

			if((m_FirstChild != null) && (Tree.m_BranchFont != null)) {
				g.setFont(Tree.m_BranchFont);
			}
		}
		else
			g.setFont(font);

        // The following is added by Ling
        // Only paint the text area which doean't include the indentation and the lines
        Color b = getBackground();
        if (b != null) {
			FontMetrics fm = g.getFontMetrics();
			int w = fm.stringWidth(this.m_Text);
            g.setColor(b);
			g.fillRect(hOffset - 2, vOffset - Tree.m_LineHeight + 1, w + 4, Tree.m_LineHeight - 2);
       }
       
		if(Tree.m_SelectedNode == this) {
			FontMetrics fm = g.getFontMetrics();
			int w = fm.stringWidth(this.m_Text);

			g.setColor(Tree.m_SelBackColor);
			g.fillRect(hOffset - 2, vOffset - Tree.m_LineHeight + 1, w + 4, Tree.m_LineHeight - 2);
			g.setColor(Tree.m_SelTextColor);
            
			(new DotRect()).draw(g, Color.white, Color.black, new Rectangle(hOffset - 4, vOffset - Tree.m_LineHeight, w + 6, Tree.m_LineHeight-1), 0);
			g.drawString(m_Text, hOffset, vOffset - 3);
		}
		else {
			b = getBackground();
      FontMetrics fm = g.getFontMetrics();
      int w = fm.stringWidth(this.m_Text);
			if (b != null)
			{
          // refill the rectangle with background of this node
          g.setColor(b);
          g.fillRect(hOffset - 3, vOffset - Tree.m_LineHeight, w + 6, Tree.m_LineHeight);
      }

			Color f = getForeground();
			if(f != null)
				g.setColor(f);
			else
				g.setColor(Tree.getForeground());
			g.drawString(m_Text, hOffset, vOffset - 3);
		}

		g.setFont(oldFont);
		return count;
	}

	PVNode NodeAtLine(int Line)
	{
		int x;
		PVNode oldNode = null;
		PVNode next = this;
		x = 1;
		while((next != null) && x < Line)
		{
		    oldNode = next;
			next = next.getNextVisible();
			x++;
		}
		if(next == null)
		    return oldNode;
	    else
    		return next;
	}

	int lineFromNode(PVTree tree)
	{
		int x = 1;
		PVNode next = tree.m_FirstVisible;
		while((next != null) && x <= tree.m_LinesPerPage + 1)
		{
			if(next == this)
				return x;
			next = next.getNextVisible();
			x++;
		}
		return -1;
	}

    /**
	 *  Returns the parent node of this node
     */
	public PVNode getParent() {
		return m_Parent;
	}

/**
	Returns the next node that is visible.  The visible nodes are the nodes
	whose ancestors are all expanded.  Even if a node is scrolled out of view,
	it can still be considered visible.
  */
	public PVNode getNextVisible() {
		if((m_FirstChild != null) && (m_Expanded))
			return m_FirstChild;
		else
		if(m_Next != null)
			return m_Next;

		if(m_Parent != null)
			return m_Parent.getNextParent();
		else
			return null;
	}

/**
	Returns the next node following this node.  The next node may not be visible
	and it may not be either a child nor a sibling of this node.  This method is used for
	enumerating all nodes
  */
	public PVNode getNext() {
		if(m_FirstChild != null)
			return m_FirstChild;
		else
		if(m_Next != null)
			return m_Next;

		if(m_Parent != null)
			return m_Parent.getNextParent();
		else
			return null;
	}

/**
	Returns the previous node following this node.  The previous node may not
	be visibleand it may not be either a child nor a sibling of this node.
	This method is used for	enumerating all nodes in reverse
  */
	public PVNode getPrev() {
		if(m_Prev != null)
			return m_Prev.getPrevLast(this);
		else
			if(m_Parent.m_Root)
				return null;
			else
				return m_Parent;
	}

	PVNode getPrevLast(PVNode node) {
		if(m_LastChild != null)
			if(m_LastChild == node)
				return null;
			else
				return m_LastChild.getPrevLast(node);
		else
			return this;
	}

	PVNode getNextParent() {
		if(m_Next != null)
			return m_Next;
		if(m_Parent != null)
			return m_Parent.getNextParent();
		else
			return null;
	}

/**
	Returns the previous node that is visible.  The visible nodes are the nodes
	whose ancestors are all expanded.  Even if a node is scrolled out of view,
	it can still be considered visible.  */
	public PVNode getPrevVisible() {
		if(m_Prev != null)
			return m_Prev.getLastVisible(this);
		else
//		    if(m_Parent != null) {
    			if(m_Parent.m_Root)
	    			return null;
		    	else
			    	return m_Parent;
//			}
	//		else return null;
	}

	PVNode getLastVisible(PVNode node) {
		if(!m_Expanded)
			return this;
		else
			if(m_LastChild != null)
				if(m_LastChild == node)
					return null;
				else
					return m_LastChild.getLastVisible(node);
			else
				return this;
	}

/**
	Returns a boolean value indicating whether or not the node has
	any child nodes associated with it.
  */
	public boolean HasChildren() {
		if(m_FirstChild != null)
			return true;
		else
			return false;
	}

/**
	Returns the first child node of this node.  Null is returned if
	the node has no children
  */
	public PVNode getChild() {
		return m_FirstChild;
	}

/**
	Gets the level of this node within the tree hierarchy.
  */
	public int getLevel() {
		return m_Level;
	}

/**
	Returns the next sibling node for this node. The
	value will be null if there is no next sibling.
  */
	public PVNode getNextSibling() {
		return m_Next;
	}

/**
	Returns the previous sibling node for this node. The
	value will be null if there is no previous sibling.
  */
	public PVNode getPrevSibling() {
		return m_Prev;
	}

	/**
	Returns the text label attribute of the this node.
  */
	public String getText() {
		return m_Text;
	}

/**
	Sets the text label attribute of the this node.

  */
	public void setText(String text) {
		m_Text = text;
	}

/**
	Returns a user defined Object reference that has been associated with this
	node with setData().
  */
	public Object getData() {
		return m_Data;
	}

/**
	Sets a user defined Object reference that can be associated with this
	node.
  */
	public void setData(Object data) {
		m_Data = data;
	}

/**
	Expands a node so that all of its children are visible.
  */
	public void setExpanded(boolean expanded) {
		m_Expanded = expanded;
	}

/**
	Returns the expanded state of the node.  If true, the node is expanded.
	If false the node is not expanded.
  */
	public boolean getExpanded() {
		return m_Expanded;
	}

/**
	Returns a user defined String reference that has been associated with this
	node through setString().
  */
	public String getString() {
		return m_String;
	}

/**
	Sets a user defined String reference that can be associated with this
	node.
  */
	public void setString(String string) {
		m_String = string;
	}

	boolean sortChildren( boolean bRecursive, boolean bCaseSensitive ) {
	    PVNode oldFirst;
	    PVNode oldLast;
	    PVNode current;
	    PVNode next;
        boolean didSwitch = true;
        int result;

        if(m_FirstChild == null)
	        return false;

	    if(m_FirstChild.m_Prev != null)
	        return false;

        oldLast = m_LastChild;

        while(didSwitch) {
    	    while(m_FirstChild.m_Prev != null)
    	        m_FirstChild = m_FirstChild.m_Prev;
    	    current = m_FirstChild;

	        next = current.m_Next;
            didSwitch = false;
            while(next != null) {
               if(bCaseSensitive)
    	           result = current.m_Text.compareTo(next.m_Text);
    	       else {
    	            String one = current.m_Text.toUpperCase();
    	            String two = next.m_Text.toUpperCase();
    	            result = one.compareTo(two);
    	       }
	           if(result > 0) {
	                // switch the nodes
	                switchNodes(current, next);
	                next = current;
	                didSwitch = true;
               }
               current = next;
               next = next.m_Next;
            }
        }

        // recursively sort the children of this node
        if(bRecursive) {
            current = m_FirstChild;
            while(current != null) {
                current.sortChildren(bRecursive, bCaseSensitive);
                current = current.m_Next;
            }
        }

        // reset the first and last child nodes of this node correctly
        while(oldLast != null) {
            m_LastChild = oldLast;
            oldLast = oldLast.m_Next;
        }

        return true;
    }

    void switchNodes(PVNode one, PVNode two) {
        PVNode tmpNext;
        PVNode tmpPrev;

        tmpNext = two.m_Next;
        tmpPrev = one.m_Prev;

        two.m_Next = one;
        two.m_Prev = tmpPrev;
        one.m_Prev = two;
        one.m_Next = tmpNext;

        // switch the neihgbor links
        if(tmpNext != null)
            tmpNext.m_Prev = one;
        if(tmpPrev != null)
            tmpPrev.m_Next = two;
    }

    public PVNode searchChildren(String text, Object data, String stringData, boolean caseSensitive) {
        if(m_FirstChild == null)
            return null;
        PVNode node = m_FirstChild;
        while(node != null) {
            if(text != null) {
                if(caseSensitive) {
                    if(node.m_Text.compareTo(text) == 0)
                        return node;
                }
                else {
                    String one = node.m_Text.toUpperCase();
                    String two = text.toUpperCase();
                    if(one.compareTo(two) == 0)
                        return node;
                }
            }
            else
            if(stringData != null) {
                if(caseSensitive) {
                    if(node.m_String.compareTo(stringData) == 0)
                        return node;
                }
                else {
                    String one = node.m_String.toUpperCase();
                    String two = stringData.toUpperCase();
                    if(one.compareTo(two) == 0)
                        return node;
                }
            }
            else
            if(data != null) {
                if(data == node.m_Data)
                   return node;
            }
            PVNode found = node.searchChildren(text, data, stringData, caseSensitive);
            if(found != null)
                return found;
            node = node.m_Next;
        }
        return null;
    }
}