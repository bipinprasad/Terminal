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

class GotoDialog extends Dialog implements WindowListener, ActionListener, KeyListener
{
	private Button gbutton,cbutton;
	private TextFrame textFrame;
	private TextField line;
	private StringMan strings;

	public GotoDialog(TextFrame tf, StringMan str, String title)
	{
		super(tf,title,true);

		setBackground(Color.lightGray);

		textFrame = tf;
		strings = str;
		
		Panel p1 = new Panel();
		p1.add(new Label(strings.getString("PromptGoto")));
		line = new TextField();
		line.setColumns(10);
		line.setText(String.valueOf(textFrame.getCanvas().getLine()));
		line.requestFocus();
		line.selectAll();
		p1.add(line);
		add("North", p1);

		Panel p2 = new Panel();
		gbutton = new Button(strings.getString("ButtonGoto"));
		gbutton.addActionListener(this);
		p2.add(gbutton);
		cbutton = new Button(strings.getString("ButtonClose"));
		cbutton.addActionListener(this);
		p2.add(cbutton);
		add("South",p2);

		pack();
		Dimension size = getPreferredSize();
		size.width += 20;
		setSize(size);
		setLocation(textFrame.getPlace(size));

		addWindowListener(this);
		line.addKeyListener(this);
	}

	private void executeGoto()
	{
		try
		{
			int l = Integer.valueOf(line.getText()).intValue();
			textFrame.getCanvas().Goto(l);
		}
		catch (NumberFormatException e) { /* ignore */ }
	}
	
    @Override
    public void windowDeiconified(WindowEvent event) {}
    @Override
    public void windowIconified(WindowEvent event) {}
    @Override
    public void windowActivated(WindowEvent event) {}
    @Override
    public void windowDeactivated(WindowEvent event) {}
    @Override
    public void windowOpened(WindowEvent event) {}
    @Override
    public void windowClosed(WindowEvent event) {}
    @Override
    public void windowClosing(WindowEvent event)
	{
			dispose();
    }

	@Override
    public void actionPerformed(ActionEvent evt)
	{
        if (evt.getSource() == gbutton)
		{
			executeGoto();
		}
		dispose();
	}

	@Override
    public void keyTyped(KeyEvent e ) {}
	@Override
    public void keyReleased(KeyEvent e ) {}
	@Override
    public void keyPressed(KeyEvent e )
	{
		if ( e.getKeyCode() == KeyEvent.VK_ENTER )
		{
			executeGoto();
			dispose();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ESCAPE )
		{
			dispose();
		}
	}

}
