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

import java.util.*;
import java.awt.*;
import java.beans.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

// TextMenu is an old class which originally created menus and
// handled their events.  Currently it creates menus and toolbars
// using configuration values in the strings.properties file.  It
// also does event handling for EditBeans.

public class TextMenu extends MenuBar
implements ActionListener, ToolButtonOwner
{
	// remember who our friends are
	
	private mpEDIT mpEdit;
	private TextFrame textFrame;
	private TextCanvas textCanvas;
	private DocMan docMan;
	private ToolBar toolBar;
	private StringMan strings;
	private ImageLoader		imageLoader;
	
	// constructor puts together UI
	
	public TextMenu(mpEDIT mpe, StringMan str, DocMan doc, ToolBar b, TextFrame tf, ImageLoader imgLoader)
	{
		super();

		mpEdit = mpe;
		strings = str;
		docMan = doc;
		toolBar = b;
		textFrame = tf;
		imageLoader	= imgLoader;
		textCanvas = textFrame.getCanvas();
				
		createMenus();
		createToolBar();
		
		docMan.addTextMenu(this);
		textCanvas.setTextMenu(this);
	}
	
    protected void createMenus()
	{
		Vector menuKeys = tokenize(strings.getString("menubar"));
		int max = menuKeys.size();
		
		for (int i = 0; i < max; i++)
		{
			Menu m = createMenu((String)menuKeys.elementAt(i));
			if (m != null)
				add(m);
		}
    }
	
    protected Menu createMenu(String key)
	{
		String item;
		
		Menu menu = new Menu(strings.getString("menu." + key + ".name"));
		
		Vector itemKeys = tokenize(strings.getString("menu." + key));
		int max = itemKeys.size();
		
		for (int i = 0; i < max; i++)
		{
			item = (String)itemKeys.elementAt(i);
			
			if (item.equals("-"))
			{
				menu.addSeparator();
			}
			else
			if (item.equals("SpecialAddbeans"))
			{
				specialAddBeans(menu);
			}
			else 
			{
				createMenuItem(item,menu);
			}
		}
		
		return menu;
    }

    protected void createMenuItem(String cmd, Menu menu)
	{
		
		MpAction action = textCanvas.getMpAction(cmd);
		
		if (action != null)
		{
			MenuItem mi = new MenuItem(
			strings.getString("action." + action.getIdString() + ".name"));
			mi.addActionListener(action);
			action.addPropertyChangeListener(new MenuItemControl( mi ));
			menu.add(mi);
		}
    }
	
	protected void specialAddBeans(Menu menu)
	{
		MenuItem mi = null;
		beans = mpEdit.getBeans();
		
		try
		{
			sfi = Class.forName("mpTOOLS.mpEDIT.SimpleFilterInterface");
			tfi = Class.forName("mpTOOLS.mpEDIT.Test01FilterInterface");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Sorry, menu could not load filter interfaces.");
			beans = null;
		}
		if (beans != null)
		{
			
			Object o;
			String s;
			
			for (int j = 0; j < beans.size(); j++ )
			{
				s = null;
				o = beans.elementAt(j);
				if (sfi.isInstance(o))
					s = ((SimpleFilterInterface)o).getMenuString();
				else
					if (tfi.isInstance(o))
					s = ((Test01FilterInterface)o).getMenuString();
				if (s != null)
				{
					mi = new MenuItem(s);
					menu.add(mi);
					mi.addActionListener(this);	// for now
				}
			}
		}
	}
	
    public void createToolBar()
	{
		String name,icon;
		MpAction action;
		
		Toolkit tk = textFrame.getToolkit();
		Vector toolKeys = tokenize(strings.getString("toolbar"));
		int max = toolKeys.size();
		
		for (int i = 0; i < max; i++)
		{
			name = (String)toolKeys.elementAt(i);
			action = null;
			icon = null;
	
			if (!name.equals("-"))
			{
				action = textCanvas.getMpAction(name);
				
				if ( action == null )
					System.out.println("No action for toolbutton: " + name );
				else
					icon = strings.getOptionalString("action." + action.getIdString() + ".icon");	
			}

			toolBar.add(new ToolButton( tk, action, icon, this, imageLoader ));
		}
	}
	
    protected Vector tokenize(String input)
	{
		Vector v = new Vector();
		StringTokenizer t = new StringTokenizer(input);
		
		while (t.hasMoreTokens())
			v.addElement(t.nextToken());
		
		return v;
    }
	
	
	private class MenuItemControl implements PropertyChangeListener
	{
		MenuItem menuItem;
		
		public MenuItemControl( MenuItem mi )
		{
			menuItem = mi;
		}
		
		@Override
		public void propertyChange(PropertyChangeEvent evt )
		{
			if ( evt.getPropertyName().equals(MpAction.ENABLED) )
			{
				menuItem.setEnabled( ((Boolean) evt.getNewValue()).booleanValue());
			}
			else if ( evt.getPropertyName().equals(MpAction.NAME))
			{
				menuItem.setLabel((String) evt.getNewValue() );
			}
		}
	}
	
	// beans
	
	private Vector beans;
	private Class sfi;
	private Class tfi;
	
    @Override
	public boolean toolButtonIgnore()
	{
		// ignore buttons if frame has been active < 100 milliseconds
		
		long time = textFrame.getActiveMillis();
		
		if (time == 0)
			return true;
		
		return ((System.currentTimeMillis() - time) < 100);
	}
	
	
	// action handler, part of std java event handling
	
    @Override
	public void actionPerformed(ActionEvent evt)
	{
		int i;
		
		Object source = evt.getSource();
		String cmd = evt.getActionCommand();
		
		if (cmd == null)
			return;
		
		// the only actions now handled by this class are beans
		
		doBean(cmd);
	}
	
	private void doBean(String cmd)
	{
		int i = 0;
		boolean done = false;
		Object o;
		
		while (!done && (i < beans.size()))
		{
			o = beans.elementAt(i);
			if (sfi.isInstance(o))
				done = doSimple(cmd, (SimpleFilterInterface)o);
			else
				if (tfi.isInstance(o))
				done = doTest(cmd, (Test01FilterInterface)o);
			i++;
		}
	}
	
	private boolean doSimple(String cmd, SimpleFilterInterface fi)
	{
		boolean done = false;
		
		String s = fi.getMenuString();
		if (cmd.equals(s))
		{
			String data = textCanvas.copy(false,false);
			if (data == null)
				data = new String("");
			data = fi.filterText(data);
			if (data != null)
				textCanvas.paste(data);
			done = true;
		}
		
		return done;
	}
	
	private boolean doTest(String cmd, Test01FilterInterface fi)
	{
		boolean done = false;
		
		String s = fi.getMenuString();
		if (cmd.equals(s))
		{
			String data = textCanvas.copy(false,false);
			if (data == null)
				data = new String("");
			data = fi.filterText(textFrame,data);
			if (data != null)
				textCanvas.paste(data);
			done = true;
		}
		
		return done;
	}	
}
