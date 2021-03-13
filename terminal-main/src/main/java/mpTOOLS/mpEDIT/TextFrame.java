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
import java.io.*;
import java.util.*;
import java.awt.event.*;

public class TextFrame extends Frame implements WindowListener
{
	// the parent

	mpEDIT		mpEdit;
	StringMan	strings;
	DocMan		docMan;

	// these three .. no four! .. four objects form the main window

	private Scrollbar	horiz;
	private Scrollbar	vert;
	private TextMenu	textMenu;
	private TextCanvas	textCanvas;
	private ToolBar		toolBar;
	private ToolBar		statusBar;
	private Label		messageLabel;
	private Label		lineLabel;
	private ImageLoader	imageLoader;

	public TextFrame(mpEDIT mpe, StringMan str, Properties pr, DocMan doc, Ruler ruler, ImageLoader imgLoader)
	{
		super.setTitle("mpEDIT");

		mpEdit = mpe;
		strings = str;
		docMan = doc;
		imageLoader = imgLoader;

        // ecm -- added StatusLine label to display the line number
		// jj  -- reorganized Semantic generated code -> hand coded GridBag

		setLayout( new GridBagLayout() );

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(0,0,0,0);
		constraints.anchor = GridBagConstraints.WEST;

		toolBar = new ToolBar();
		statusBar = new ToolBar();
		vert = new Scrollbar(Scrollbar.VERTICAL);
		horiz = new Scrollbar(Scrollbar.HORIZONTAL);
		textCanvas = new TextCanvas(mpe,strings,doc,this,horiz,vert,ruler);

        constraints.gridy = 0;
        constraints.gridx = 0;
		constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;  
		add(toolBar, constraints);

        constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.weightx = constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;  
		add(textCanvas, constraints);

        constraints.gridx = 1;
		constraints.weightx = constraints.weighty = 0;
        constraints.fill = GridBagConstraints.VERTICAL;  
		add(vert, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;  
		constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;  
		add(horiz, constraints);

		statusBar.setLayout(new BorderLayout());
		messageLabel = new Label("");
		messageLabel.setBackground(SystemColor.control);
		statusBar.add("Center", messageLabel );
		lineLabel = new Label("Line 000000");	// init with max width string
		lineLabel.setBackground(SystemColor.control);
		statusBar.add("East", lineLabel );

        constraints.gridy = 3;
		add(statusBar, constraints);

		textMenu = new TextMenu(mpEdit,strings,docMan,toolBar,this,imageLoader);
		setMenuBar(textMenu);

		addWindowListener(this);
	}

    // ecm -- added method to update the line number display
    private int line = -1;
    public void setLine(int line) {
        if (this.line != line) {
            this.line = line;
            lineLabel.setText("Line "+(line+1));
        }
    }

	// jj - tied message text to new status bar
	public void setMessage(String msg)
	{
		messageLabel.setText(msg);
	}

	public TextCanvas getCanvas()
	{
		return textCanvas;
	}

	public Point getPlace(Dimension size)
	{
		return mpEdit.getPlace(size);
	}
	
	public void clearCanvas()
	{
		 textCanvas.clear();
	}
	
	public void redoCanvas()
	{
		 textCanvas.redoCanvas();
		 textCanvas.repaint();
	}

	public void legalizeCursor()
	{
		 textCanvas.legalizeCursor();
	}

	public void release_cursor()
	{
		 textCanvas.release_cursor();
	}

	public void pause_cursor()
	{
		 textCanvas.pause_cursor();
	}
		
	public void setPos(TextPosition tp)
	{
		 textCanvas.setPos(tp);
	}

	public void showLine(int line)
	{
		 textCanvas.Goto(line+1); // showLine is 0-based, Goto is 1-based
	}

	public void setReadOnly( boolean readOnly )
	{
		// this would be enough for menus and toolbars in future, but
		// we may want to notify somebody else - some readOnly led
		// or something  - AB
		textCanvas.setReadOnly(readOnly);
	}

	// add current filename to window title

	public void setTitle(String name)
	{
		super.setTitle("mpEDIT - " + name);
	}
	
	// pass strings up to parent

	public void CloseFindDialog(String pat)
	{
		mpEdit.addSearchPattern(pat);
	}
	
	public void CloseReplaceDialog(String pat, String rep)
	{
		mpEdit.addSearchPattern(pat);
		mpEdit.addReplacePattern(rep);
	}
	
	// add the 1.1 WindowListener stuff

	long activeMillis = 0;

	public long getActiveMillis()
	{
		return activeMillis;
	}

    public void windowDeiconified(WindowEvent event) {}
    public void windowIconified(WindowEvent event) {}

    public void windowActivated(WindowEvent event) {
		activeMillis = System.currentTimeMillis();
    }

    public void windowDeactivated(WindowEvent event) {
		activeMillis = 0;
	}

    public void windowOpened(WindowEvent event) {}
    public void windowClosed(WindowEvent event) {}

    public void windowClosing(WindowEvent event) {
		docMan.closeFrame(this);
    }
}


