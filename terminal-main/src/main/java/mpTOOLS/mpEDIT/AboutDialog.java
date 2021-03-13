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

import java.awt.*;
import java.awt.event.*;

public class AboutDialog extends Dialog implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 7853264561313829851L;
	Button ok;

	public AboutDialog(Frame textFrame, StringMan strings, String title)
	{
		super(textFrame,title,true);

		setLayout( new GridBagLayout() );

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(0,0,0,0);
		constraints.weighty = 1;
		constraints.anchor = GridBagConstraints.CENTER;
	
		String temp;
		int i;
	
		for (i=1;;i++) {
			temp = "TextAbout" + i;
			temp = strings.getOptionalString(temp); 
			
			if (temp == null)
				break;
			
        		constraints.gridy = i;
			add(new Label(temp), constraints);
		}


		ok = new Button(strings.getString("ButtonOk"));
		ok.addActionListener(this);
		constraints.gridy = i;
		add(ok, constraints);

		Dimension size = new Dimension(400,250);
		setSize(size);

		addWindowListener(this);
	}

	// dispose on 'ok'

    public void actionPerformed(ActionEvent event)
	{
        if (event.getSource() == ok)
			dispose();
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
