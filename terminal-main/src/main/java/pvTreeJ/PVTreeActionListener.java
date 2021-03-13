package pvTreeJ;

import java.util.EventListener;
import java.awt.event.*;

/**
 * The listener interface for receiving PVTreeJ action events.
 *
 * @version 1.6 07/28/97
 */
public interface PVTreeActionListener extends EventListener {

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(PVTreeActionEvent e);

}
