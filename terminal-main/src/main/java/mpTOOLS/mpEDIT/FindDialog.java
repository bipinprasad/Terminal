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

class FindDialog extends Dialog implements WindowListener, ActionListener, KeyListener, ItemListener
{
	private Button fbutton,cbutton;
	private TextFrame textFrame;
	private TextField pattern;
	private StringMan strings;
	private String prevPatterns[];
	private int prevPatternIndex;
	private Checkbox forward;
	private Checkbox regex;
	private Checkbox matchCase;
	private TextCanvas textCanvas;

	public FindDialog(TextFrame tf, TextCanvas tc, StringMan str, String title)
	{
		super(tf,title,true);

		setBackground(Color.lightGray);

		textFrame = tf;
		textCanvas = tc;
		strings = str;
		
		Panel p1 = new Panel();
		p1.setLayout(new FlowLayout());
		p1.add(new Label(strings.getString("PromptFind")));
		prevPatterns = textCanvas.getSearchPatterns();
		prevPatternIndex = prevPatterns.length-1;
		pattern = new TextField(prevPatterns[prevPatternIndex],50);
		p1.add(pattern);
		p1.doLayout();
		add("North", p1);

		Panel p3 = new Panel();
		p3.setLayout(new FlowLayout());
		CheckboxGroup cbg = new CheckboxGroup();
		p3.add(forward = new Checkbox("Search forward", cbg, true));
		p3.add(new Checkbox("Search backward", cbg, false));
		boolean r = textCanvas.getLastRegex();
		p3.add(regex = new Checkbox("regex", r ));
		regex.addItemListener(this);
		p3.add(matchCase = new Checkbox("Match Case"));
		if (r)
		{
 			matchCase.setEnabled(false);
 			matchCase.setState(false);
 		}
 		else
 		{
 			matchCase.setState(textCanvas.getLastMatchCase());
 		}
		add("Center",p3);

		Panel p2 = new Panel();
		fbutton = new Button(strings.getString("ButtonFindNext"));
		fbutton.addActionListener(this);
		p2.add(fbutton);
		cbutton = new Button(strings.getString("ButtonClose"));
		cbutton.addActionListener(this);
		p2.add(cbutton);
		add("South",p2);

		Dimension size = new Dimension(500,140);
		setSize(size);
		setLocation(textFrame.getPlace(size));

		pattern.requestFocus();
		pattern.selectAll();
		pattern.addKeyListener(this);

		addWindowListener(this);
	}

	public void executeFind()
	{
		int direction = forward.getState() ? 1 : -1;
		
		if (!textCanvas.find(pattern.getText(),direction,regex.getState(),matchCase.getState()))
		{
			NotFoundDialog nf = new NotFoundDialog(textFrame,strings);
			nf.show();
		}
	}
	
 	@Override
    public void itemStateChanged(ItemEvent e)
 	{
 		if (e.getSource() == regex)
 		{
	 		if(e.getStateChange() == ItemEvent.SELECTED)
			{
	 			matchCase.setEnabled(false);
	 			matchCase.setState(false);
	 		}
	 		else
			{
	 			matchCase.setEnabled(true);
 				matchCase.setState(textCanvas.getLastMatchCase());
	 		}
 		}
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
			textFrame.CloseFindDialog(pattern.getText());
			dispose();
    }

	@Override
    public void actionPerformed(ActionEvent evt)
	{
        if (evt.getSource() == cbutton)
		{
			textFrame.CloseFindDialog(pattern.getText());
			dispose();
			return;
		}

        if (evt.getSource() == fbutton)
			executeFind();
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
			executeFind();
		}
		else if ( e.getKeyCode() == KeyEvent.VK_ESCAPE )
		{
			textFrame.CloseFindDialog(pattern.getText());
			dispose();
			return;
		}
		else if ( (e.getKeyCode() == KeyEvent.VK_UP) ||
			(e.getKeyCode() == KeyEvent.VK_DOWN) )
		{
			// this is part of editable-combobox functionality
			// if we ever move to swing we can replace it
			if ( e.getKeyCode() == KeyEvent.VK_UP )
			{
				if ( prevPatternIndex > 0 )
					prevPatternIndex--;
			}
			else
			{
				if ( prevPatternIndex < (prevPatterns.length-1) )
					prevPatternIndex++;
			}
			pattern.setText(prevPatterns[prevPatternIndex]);

			e.consume();
			return;
		}
	}

}
