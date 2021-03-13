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

class PropDialog extends Dialog implements WindowListener, ActionListener
{
	DocMan docMan;

	Button ok,cancel;

	TextField tabField = new TextField(3);
	Choice fontChoice = new Choice();
	Choice styleChoice = new Choice();
	Choice sizeChoice = new Choice();
	CheckboxGroup globalGroup = new CheckboxGroup();
	Checkbox syntax;
	Checkbox global;

	public PropDialog(TextFrame textFrame, StringMan strings, DocMan dm, String title)
	{
		super(textFrame,title,true);

		docMan = dm;

		setLayout( new GridBagLayout() );

		Panel p = new Panel();
		p.add(new Label(strings.getString("PromptFont")));
        String[] fontNames = getToolkit().getFontList();
        for (int i = 0; i < fontNames.length; i++)
		{
			fontChoice.add(fontNames[i]);
		}
		fontChoice.select(docMan.getProperty("mpEDIT.font.name"));
		p.add(fontChoice);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(15,15,15,15);
		constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx = 1;
        constraints.gridy = 0;
        constraints.gridx = 0;  
        add(p, constraints);
		
		p = new Panel();
		p.add(new Label(strings.getString("PromptStyle")));
		styleChoice.add(strings.getString("ChoicePlain"));
		styleChoice.add(strings.getString("ChoiceBold"));
		styleChoice.add(strings.getString("ChoiceItalic"));
		styleChoice.add(strings.getString("ChoiceBoldItalic"));
		styleChoice.select(docMan.getFontStyle());
		p.add(styleChoice);
        constraints.gridx = 1;  
        add(p, constraints);
		
		p = new Panel();
		p.add(new Label(strings.getString("PromptSize")));
		sizeChoice.add("8");
		sizeChoice.add("9");
		sizeChoice.add("10");
		sizeChoice.add("11");
		sizeChoice.add("12");
		sizeChoice.add("14");
		sizeChoice.add("16");
		sizeChoice.add("18");
		sizeChoice.add("24");
		sizeChoice.add("36");
		sizeChoice.add("48");
		sizeChoice.select(docMan.getProperty("mpEDIT.font.size"));
		p.add(sizeChoice);
        constraints.gridx = 2;  
        add(p, constraints);
				
		p = new Panel();
		p.add(new Label(strings.getString("PromptTab")));
		tabField.setText(docMan.getProperty("mpEDIT.tab.size"));
		p.add(tabField);
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 3;
        add(p, constraints);
		
		p = new Panel();
		syntax = new Checkbox(strings.getString("PromptSyntax"));
		syntax.setState(Boolean.valueOf(docMan.getProperty("mpEDIT.hilite")).booleanValue());
		p.add(syntax);
        constraints.gridy = 2;
        add(p, constraints);

		p = new Panel();
		p.add(new Label(strings.getString("PromptLocal")));
		p.add(new Checkbox(strings.getString("CheckThisDoc"), globalGroup, true));
		p.add(global = new Checkbox(strings.getString("CheckAllDocs"), globalGroup, false));
        constraints.gridy = 3;
        add(p, constraints);

        constraints.gridy = 4;
        constraints.gridx = 1;
        constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		ok = new Button(strings.getString("ButtonOk"));
		ok.addActionListener(this);
		add(ok, constraints);

        constraints.gridx = 2;
		cancel = new Button(strings.getString("ButtonCancel"));
		cancel.addActionListener(this);
		add(cancel, constraints);

		pack();
		Dimension size = getPreferredSize();
		setSize(size);
		setLocation(textFrame.getPlace(size));

		addWindowListener(this);
	}

	// dispose on 'ok'

    @Override
	public void actionPerformed(ActionEvent event)
	{
		
        if (event.getSource() == ok)
		{
			boolean all = global.getState();

			if (!all)
				docMan.splitProperties();

			 docMan.setProperty("mpEDIT.font.name",fontChoice.getSelectedItem());
			 docMan.setFontStyle(styleChoice.getSelectedItem());
			 docMan.setProperty("mpEDIT.font.size",sizeChoice.getSelectedItem());
			 docMan.setProperty("mpEDIT.tab.size",tabField.getText());
			 docMan.setProperty("mpEDIT.hilite",String.valueOf(syntax.getState()));

			 docMan.updateProperties(all);
		}
	
		dispose();
	}

	// add the 1.1 WindowListener stuff

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
	public void windowClosing(WindowEvent event) {
			dispose();
    }

}
