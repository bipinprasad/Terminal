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

class ReplaceDialog extends Dialog implements WindowListener, ActionListener, ItemListener
{
	private Button fbutton,rbutton,cbutton;
	private TextField		pattern;
	private TextField		replace;
	private TextFrame		textFrame;
	private TextCanvas		textCanvas;
	private StringMan		strings;
	private Checkbox		forward;
	private Checkbox 		regex;
	private Checkbox		matchCase;

	private boolean foundOnce = false;

	public ReplaceDialog(TextFrame tf, TextCanvas tc, StringMan str, String title)
	{
		super(tf,title,true);

		setBackground(Color.lightGray);

		textFrame = tf;
		textCanvas = tc;
		strings = str;
	
		Panel p1 = new Panel();
		
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        p1.setLayout(gridbag);

		Label flabel = new Label(strings.getString("PromptFind"));
        constraints.anchor = GridBagConstraints.NORTHWEST;
		gridbag.setConstraints(flabel, constraints);

		pattern = new TextField();
		pattern.setColumns(50);
		pattern.setText(textCanvas.getLatestSearchPattern());

		constraints.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(pattern, constraints);

        p1.add(flabel);
        p1.add(pattern);

		Label rlabel = new Label(strings.getString("PromptReplace"));
        constraints.anchor = GridBagConstraints.WEST;
		constraints.gridwidth = 1;
        gridbag.setConstraints(rlabel, constraints);

		replace = new TextField();
		replace.setColumns(50);
		replace.setText(textCanvas.getLatestReplacePattern());

		constraints.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(replace, constraints);

        p1.add(rlabel);
        p1.add(replace);

		add("North", p1);
		
		Panel p2 = new Panel();
		p2.setLayout(new FlowLayout());
		CheckboxGroup cbg = new CheckboxGroup();
		p2.add(forward = new Checkbox("Search forward", cbg, true));
		p2.add(new Checkbox("Search backward", cbg, false));
		boolean r = textCanvas.getLastRegex();
		p2.add(regex = new Checkbox("regex", r ));
		regex.addItemListener(this);
		p2.add(matchCase = new Checkbox("Match Case"));
		if (r)
		{
 			matchCase.setEnabled(false);
 			matchCase.setState(false);
 		}
 		else
 		{
 			matchCase.setState(textCanvas.getLastMatchCase());
 		}
		add("Center",p2);

		Panel p3 = new Panel();
		fbutton = new Button(strings.getString("ButtonFindNext"));
		fbutton.addActionListener(this);
		p3.add(fbutton);
		rbutton = new Button(strings.getString("ButtonReplace"));
		rbutton.addActionListener(this);
		p3.add(rbutton);
		cbutton = new Button(strings.getString("ButtonClose"));
		cbutton.addActionListener(this);
		p3.add(cbutton);
		add("South",p3);

		Dimension size = new Dimension(500,150);
		setSize(size);
		setLocation(textFrame.getPlace(size));

		addWindowListener(this);
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
			textFrame.CloseReplaceDialog(pattern.getText(),replace.getText());
			dispose();
    }

	@Override
    public void actionPerformed(ActionEvent evt)
	{
        if (evt.getSource() == cbutton)
		{
			textFrame.CloseReplaceDialog(pattern.getText(),replace.getText());
			dispose();
			return;
		}

        if (evt.getSource() == fbutton)
		{
			foundOnce = executeFind();
		}
		else
        if (evt.getSource() == rbutton)
		{
			if (!foundOnce)
			{
				String selection= textCanvas.copy(false,false);
				if (selection != null)
					foundOnce = selection.equals(pattern.getText());
			}

			if (foundOnce)
				textCanvas.paste(replace.getText());

			foundOnce = executeFind();
		}

		if (!foundOnce)
		{
			NotFoundDialog nf = new NotFoundDialog(textFrame,strings);
			nf.show();
		}
	}
	
	public boolean executeFind()
	{
		int direction = forward.getState() ? 1 : -1;
		return textFrame.getCanvas().find(pattern.getText(),direction,regex.getState(),matchCase.getState());
	}

}
