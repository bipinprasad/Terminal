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
import java.net.*;
import java.beans.*;

public class ToolButton extends Component implements PropertyChangeListener
{
	final int HALF_EXTRA = 3;
	final int EXTRA_SIZE = 6;

	final boolean RAISED = true;	// set to true to create raise buttons

	private int xSize = 26;			// default size
	private int ySize = 26;

	private boolean pressed;
	private boolean entered;

	private Image img0,img1,img2;

	private ToolButtonOwner toolButtonOwner;
	private ActionListener listener;

	public ToolButton(Toolkit tk, MpAction action, String basename, ToolButtonOwner tbo, ImageLoader imageLoader)
	{
		toolButtonOwner = tbo;

		if (basename == null)	// "space" or icon not found
		{
			setEnabled(false);
			return;
		}

		MediaTracker mediaTracker = new MediaTracker(this);

		if (imageLoader != null)
		{
			img0 = imageLoader.loadImage("mpTOOLS/mpEDIT/", basename + "0.gif", this, false);
			img1 = imageLoader.loadImage("mpTOOLS/mpEDIT/", basename + "1.gif", this, false);
			img2 = imageLoader.loadImage("mpTOOLS/mpEDIT/", basename + "2.gif", this, false);

			mediaTracker.addImage(img0, 0);
			mediaTracker.addImage(img1, 1);
			mediaTracker.addImage(img2, 2);
		}
		else
		{
			URL url = ClassLoader.getSystemResource("mpTOOLS/mpEDIT/" + basename + "0.gif");
			System.out.println("Loading url [" + url + "]");
			if (url != null) {
				img0 = tk.getImage(url);
				mediaTracker.addImage(img0, 0);
			}

			url = ClassLoader.getSystemResource("mpTOOLS/mpEDIT/" + basename + "1.gif");
			System.out.println("Loading url [" + url + "]");
			if (url != null) {
				img1 = tk.getImage(url);
				mediaTracker.addImage(img1, 1);
			}

			url = ClassLoader.getSystemResource("mpTOOLS/mpEDIT/" + basename + "2.gif");
			System.out.println("Loading url [" + url + "]");
			if (url != null) {
				img2 = tk.getImage(url);
				mediaTracker.addImage(img2, 2);
			}
		}

		try
		{
			mediaTracker.waitForAll();
			if (mediaTracker.isErrorAny())
				System.out.println(">>>>> Error loading images");
		}
		catch (Exception e)
		{
			System.out.println(">>>>> Exception while loading images");
		}

		int temp;

		if (img0 != null)
		{
			xSize = img0.getWidth(this);
			ySize = img0.getHeight(this);
		}

		if (img1 != null)
		{
			temp = img1.getWidth(this);
			if (temp > xSize) xSize = temp;
			temp = img1.getHeight(this);
			if (temp > ySize) ySize = temp;
		}

		if (img1 == null) img1 = img0;		// if no alternate image, use default
		if (img2 == null) img2 = img0;

		xSize += EXTRA_SIZE;
		ySize += EXTRA_SIZE;

		enableEvents(AWTEvent.MOUSE_EVENT_MASK);

		if ( action != null )
		{
			addActionListener(action);
			action.addPropertyChangeListener(this);
		}
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(xSize, ySize);
	}

	@Override
	public Dimension getMinimumSize()
	{
		return new Dimension(xSize, ySize);
	}

	@Override
	public void paint(Graphics g)
	{
		if (img0 == null)
			return;

		if (isEnabled() == false)
			g.drawImage(img2,HALF_EXTRA,HALF_EXTRA,this);
		else
			if (entered)
				g.drawImage(img1,HALF_EXTRA,HALF_EXTRA,this);
			else
				g.drawImage(img0,HALF_EXTRA,HALF_EXTRA,this);

		if (pressed)
			g.setColor(SystemColor.controlShadow);
		else
			if (RAISED)
				g.setColor(SystemColor.controlHighlight);
			else
				g.setColor(SystemColor.control);

		g.drawLine(0,0,0,ySize-1);
		g.drawLine(0,0,xSize-1,0);

		if (pressed)
			g.setColor(SystemColor.controlHighlight);
		else
			if (RAISED)
				g.setColor(SystemColor.controlShadow);
			else
				g.setColor(SystemColor.control);

		g.drawLine(0,ySize-1,xSize-1,ySize-1);
		g.drawLine(xSize-1,0,xSize-1,ySize-1);
	}

	@Override
	public void processMouseEvent(MouseEvent e)
	{
		if (isEnabled() == false)
			return;
	
		if (toolButtonOwner.toolButtonIgnore())
			return;

		switch(e.getID()) {
		case MouseEvent.MOUSE_PRESSED:
			if(!pressed && contains(e.getX(),e.getY())) {
				pressed = true;
				repaint();
			}
			break;
		case MouseEvent.MOUSE_RELEASED:
			if(pressed && contains(e.getX(),e.getY())) {
				if ( listener != null )
				{
					ButtonPusher bp = new ButtonPusher(listener);
					bp.start();
				}
				pressed = false;
				entered = false;
				repaint();
			}
			break;
		case MouseEvent.MOUSE_ENTERED:
			if(entered == false) {
				entered = true;
				if (img1 != img0)
					repaint();
			}
			break;
		case MouseEvent.MOUSE_EXITED:
			if(entered || pressed) {
				entered = false;
				pressed = false;
				if (img1 != img0)
					repaint();
			}
			break;
		}
		super.processMouseEvent(e);
	}

	@Override
	public boolean contains(int x, int y)
	{
		if ((x < 0) || (y < 0))
			return false;

		if ((x >= xSize) || (y >= ySize))
			return false;

		return true;
	}

	public void addActionListener( ActionListener aListener )
	{
		// only one supported for now
		listener = aListener;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt )
	{
		if ( evt.getPropertyName().equals(MpAction.ENABLED) )
		{
			setEnabled(((Boolean)evt.getNewValue()).booleanValue());
			repaint();
		}
	}

}
