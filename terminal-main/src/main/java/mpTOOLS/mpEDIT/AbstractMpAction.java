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

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.Locale;
import java.util.MissingResourceException;

/**
 * This class will be changed to inherit from swing AbstractAction
 * @version 1.0 Sun 1997.12.07
 * @author Artur Biesiadowski
 */

public abstract class AbstractMpAction implements MpAction
{
	private boolean enabled = true;
	private PropertyChangeSupport propertyChangeSupport;

	protected String idString;

	public AbstractMpAction(String aIdString)
	{
		idString = aIdString;
	}
	
	public abstract void actionPerformed( ActionEvent e );

	public String getIdString()
	{
		return idString;
	}

	private void ensureChangeSupport()
	{
		if (propertyChangeSupport == null)
			propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled( boolean b )
	{
		ensureChangeSupport();
		propertyChangeSupport.firePropertyChange( ENABLED, new Boolean(enabled), new Boolean(b) );
		enabled = b;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		ensureChangeSupport();
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		if (propertyChangeSupport != null)
			propertyChangeSupport.removePropertyChangeListener(listener);
	}
}
