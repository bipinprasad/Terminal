package pvTreeJ;

import java.util.*;
import java.awt.*;



public class PVTreeActionEvent extends AWTEvent {
	/**
	*   The base value for PVTree Events.
	*/
    public final static int PVTREE_EVENT = 50;
	/**
	*   Fired when the selected node is about to change.  The getNode() method contains the
	*   PVNode object that is currently selected.
	*/
    public final static int BEFORE_NODE_CHANGE_EVENT = PVTREE_EVENT;
	/**
	*   Fired when the selected node has changed.  The getNode() method contains the
	*   PVNode object that is newly selected.
	*/
    public final static int AFTER_NODE_CHANGE_EVENT = PVTREE_EVENT + 1;
	/**
	*   Fired when label editing is about to begin.  The getNode() method contains
	*   the PVNode object that is being edited.
	*/
    public final static int BEGIN_LABEL_EDIT = PVTREE_EVENT + 2;
	/**
	*   Fired when label editing has changed.  The arg parameter contains the
	*   String text of the new value for the node.
	*/
    public final static int END_LABEL_EDIT = PVTREE_EVENT + 3;
	/**
	*   Fired when a node is about to be expanded. The getNode() method  contains the
	*   PVNode object that being expanded.
	*/
    public final static int NODE_EXPANDING = PVTREE_EVENT + 4;
	/**
	*   Fired when a node has been expaned.  The getNode() method contains the
	*   PVNode object that has been expanded.
	*/
    public final static int NODE_EXPANDED = PVTREE_EVENT + 5;
	/**
	*   Fired when a node is about to be collapsed.  The getNode() method contains the
	*   PVNode object that being collapsed.
	*/
    public final static int NODE_COLLAPSING = PVTREE_EVENT + 6;
	/**
	*   Fired when a node has been collapsed.  The getNode() method contains the
	*   PVNode object that has been collapsed.
	*/
    public final static int NODE_COLLAPSED = PVTREE_EVENT + 7;
	/**
	*   Fired when a node has been clicked.  The getNode() method  contains the
	*   PVNode object that has been clicked.
	*/
    public final static int NODE_CLICKED = PVTREE_EVENT + 9;
    /* deprecated event id.  Use NODE_CLICKED     */
    public final static int NODE_CLICK = PVTREE_EVENT + 9;
	/**
	*   Fired when a node has been double clicked.  The getNode() method contains the
	*   PVNode object that has been doubled clicked
	*/
    public final static int NODE_DOUBLE_CLICKED = PVTREE_EVENT + 10;
    /* deprecated event id.  Use NODE_DOUBLE_CLICKED     */
    public final static int NODE_DOUBLE_CLICK = PVTREE_EVENT + 10;
    PVNode m_node;
    boolean m_cancel;
    PVTreeActionEvent(Component source, int id, int modifiers, PVNode o) {
        super(source, id);
        m_node = o;

    }
    

 	/**
	*   provides access to PVNode object involved with the event, if there is one
	*/
   public PVNode getNode() {
        return m_node;
    }
 	/**
	*   Obtains the action state of the event.  If the Cancel flag is true, the event will not be
	*   processed further by the PVTree object.
	*/
   public boolean getCancel() {
        return m_cancel;
    }
    
	/**
	*   Sets the action state of the event.  If the Cancel flag is set to true, the event will not be
	*   processed further by the PVTree object.
	*/
    public void setCancel(boolean cancel) {
        m_cancel = cancel;
    }

}