/*
 * Copyright (c) 1997, 1998 John Jensen. All rights reserved.
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

import java.util.*;
import java.awt.*;
import java.awt.event.*;

class NotFoundDialog extends Dialog implements WindowListener, ActionListener, KeyListener
{
	private Button okButton;

	public NotFoundDialog(TextFrame textFrame, StringMan strings)
	{
		super(textFrame,"",true);

		Panel north = new Panel();
		north.add(new Label(strings.getString("DialogNotFound")));
		add("North", north);

		okButton = new Button(strings.getString("ButtonOk"));
		okButton.addActionListener(this);
		Panel south = new Panel();
		south.add(okButton);
		add("South", south);

		Dimension size = new Dimension(200,110);
		setSize(size);
		setLocation(textFrame.getPlace(size));

		addWindowListener(this);
		addKeyListener(this);
		okButton.addKeyListener(this);
	}

    public void windowDeiconified(WindowEvent event) {}
    public void windowIconified(WindowEvent event) {}
    public void windowActivated(WindowEvent event) {}
    public void windowDeactivated(WindowEvent event) {}
    public void windowOpened(WindowEvent event) {}
    public void windowClosed(WindowEvent event) {}
    public void windowClosing(WindowEvent event)
	{
			dispose();
    }

	public void actionPerformed(ActionEvent evt)
	{
        if (evt.getSource() == okButton)
			dispose();
	}

	public void keyTyped( KeyEvent e ) {}
	public void keyReleased( KeyEvent e ) {}
	public void keyPressed( KeyEvent e )
	{
		if ( (e.getKeyCode() == KeyEvent.VK_ENTER) || 
			(e.getKeyCode() == KeyEvent.VK_ESCAPE) )
		{
			e.consume();
			dispose();
			return;
		}
	}
}
