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

class DirtyDialog extends Dialog implements WindowListener, ActionListener
{
	private DocMan docMan;
	private TextFrame textFrame;
	private Button cancel;
	private Button save;
	private Button dontSave;

	public DirtyDialog(DocMan dm, TextFrame tf, StringMan strings, String title, String file)
	{
		super(tf,title,true);

		docMan = dm;
		textFrame = tf;

		Panel north = new Panel();
		north.add(new Label(strings.getString("TextDirty")));
        add("North", north);

		Panel center = new Panel();
        center.add(new Label(file));
        add("Center", center);

		Panel south = new Panel();
		save = new Button(strings.getString("ButtonSave"));
		save.addActionListener(this);
		south.add(save);
		dontSave = new Button(strings.getString("ButtonDontSave"));
		dontSave.addActionListener(this);
		south.add(dontSave);
		cancel = new Button(strings.getString("ButtonCancel"));
		cancel.addActionListener(this);
		south.add(cancel);
		add("South",south);

		Dimension size = new Dimension(250,150);
		setSize(size);
		setLocation(textFrame.getPlace(size));

		addWindowListener(this);
	}

	// dispose on 'ok'

    public void actionPerformed(ActionEvent event)
	{
        if (event.getSource() == cancel)
		{
			dispose();
		}
		else
        if (event.getSource() == dontSave)
		{
			docMan.doCloseFrame(textFrame);
			dispose();
		}
		else
        if (event.getSource() == save)
		{
			if (docMan.fileSave(textFrame))
			{
				docMan.doCloseFrame(textFrame);
			}
			dispose();
		}
	}

	// add the 1.1 WindowListener stuff

    public void windowDeiconified(WindowEvent event) {}
    public void windowIconified(WindowEvent event) {}
    public void windowActivated(WindowEvent event) {}
    public void windowDeactivated(WindowEvent event) {}
    public void windowOpened(WindowEvent event) {}
    public void windowClosed(WindowEvent event) {}

    public void windowClosing(WindowEvent event) {
			dispose();
    }

}
