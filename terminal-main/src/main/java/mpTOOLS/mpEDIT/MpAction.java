/*
 * Copyright (c) 1997, 1998, 1999 John Jensen. All rights reserved.
 *
 * This software is FREE FOR COMMERCIAL AND NON-COMMERCIAL USE,
 * provided the following condition is met.
 *
 * Permission to use, copy, modify, and distribute this software and
 * its documentation for any purpose and without fee is hereby granted,
 * provided that any copy or derivative of this software or documentation
 * retaining the name "John Jensen" also retains this condition and the
 * following disclaimer.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * CopyrightVersion 1.0
 */

package mpTOOLS.mpEDIT;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

/**
 * MpEDIT own swing-like action implementation
 * Can be later changed to swing one, or extended for free-builder
 * @see AbstractMpAction
 * @author Artur Biesiadowski
 * @version 1.0	Sun 1997.12.07
 */

public interface MpAction extends ActionListener
{
	
	/*
	 * Inherited from ActionListener
	 * Main method, called from various places to execute action
	 * @param evt event which caused the action
	 */
	//public void actionPerformed(ActionEvent evt);

	/**
	 * ID for action name changes
	 */
	public static final String NAME = "Name";

	/**
	 * ID for action 'enabled' changes
	 */
	public static final String ENABLED = "enabled";

	/**
	 * @return emacs-like name identifier for action
	 */
	public String getIdString();

	/**
	 * Is action enabled for its component
	 */
	public boolean isEnabled();

	/**
	 * Enable/disable action for given component
	 */
	public void setEnabled( boolean b );

	/**
	 * Add object to be notified of change in any of the values
	 * (mainly enabled state)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Add object to be notified of change in any of action's values 
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener);

}
