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
import java.io.*;
import java.awt.*;

import java.awt.event.ActionEvent;

public class mpEDIT implements EditorInterface
{
	//private StringMan	stringMan;
	private StringMan	strings;
	private PropMan		propMan;
	private Properties	props;
	private Vector		docs;
	private Vector		beans;
	private Hashtable	keyhash;

	private EditorOwnerInterface	editorOwner;
	private DocOwnerInterface		docOwner;		// assume one default owner for all docs
	private ImageLoader				imageLoader;

	private int			untitled_count;

	private Dimension	screenDim;
	private Dimension	windowSize;
	private int			windowOffset;
	private int			windowCount;
	private Point		lastPlace;

	public static final String stringsResourceName = "mpTOOLS.mpEDIT.strings";

	// constructor puts together UI

	public mpEDIT()
	{
		// initialize patterns
		searchPatterns.addElement("");
		replacePatterns.addElement("");

		propMan = new PropMan();
		props = propMan.getProperties();

		strings = new StringMan(stringsResourceName);

		FilterMan filterMan = new FilterMan();
		beans = filterMan.loadDir("jars");
		
		initKeyHash();

		screenDim = Toolkit.getDefaultToolkit().getScreenSize();

		windowSize = new Dimension(screenDim.width * 2/3,screenDim.height * 2/3);
		windowOffset = screenDim.height / 21;

		docs = new Vector(5,5);
	}

	public void initKeyHash()
	{
		keyhash = new Hashtable(); 

		KeyMap keyMap = new KeyMap();
		keyMap.initHash(keyhash);
	}

	public mpEDIT(EditorOwnerInterface o, ImageLoader imgLoader)
	{
		this();
		editorOwner = o;
		imageLoader = imgLoader;
	}

	public EditorOwnerInterface getOwner()
	{
		return editorOwner;
	}

	public int getUntitled()
	{
		return ++untitled_count;
	}

	//
	// A test interface for sending actions to the owner
	//
	public void sendEditAction( String action )
	{

		System.out.println("generic edit owner action = " + action);

		if (editorOwner != null)
			editorOwner.editAction(this,action);
	}
	
	/**
	 * Search this ResourceBundle before mpEDIT's own
     * @param	rb ResourceBundle
	 */
	@Override
	public void addResourceBundle(ResourceBundle rb)
	{
		strings.addResourceBundle(rb);
	}

	/**
	 * Opens a new, empty, document.
	 * @return	A DocInterface for the opened document.
	 */

	@Override
	public synchronized DocInterface newDoc(DocOwnerInterface o)
	{
		docOwner = o;	// jj 1998.02.17 - set default docOwner
		return (DocMan)newDoc();
	}

	public synchronized DocInterface newDoc()
	{
		DocMan doc = new DocMan(this,strings,props, imageLoader);
		if (doc != null)
		{
			doc.setOwner(docOwner);	// jj 1998.02.17 - apply default docOwner
			TextFrame textFrame = doc.newFrame(getPlace(windowSize),windowSize);
			doc.setUntitled(++untitled_count);
			textFrame.setVisible(true);
			docs.addElement(doc);
			// sl 1998.02.14  - notify EditorOwnerInterface		
            if (editorOwner != null)
            	editorOwner.openedDoc(doc);

  		}
		return doc;
	}

	/**
	 * Opens a file dialog, and then the specified file.  The current
	 * document is used if it is not busy, or else a new editor document
	 * is created.
     * @param	doc The current document.
     * @param	textFrame The current textFrame.
	 * @return	A DocInterface for the opened document.
	 */
	public synchronized void openDocDialog(DocMan doc, TextFrame textFrame)
	{
		boolean newdoc;
		String filename = getFileForOpen(textFrame);

		if (filename != null)
		{
			newdoc = doc.isBusy();

			if (newdoc)
			{
				doc = new DocMan(this,strings,props,imageLoader);
				doc.setOwner(docOwner);	// jj 1998.02.17 - apply default docOwner
				textFrame = doc.newFrame(getPlace(windowSize),windowSize);
			}
			else
			{
				if (editorOwner != null)
					editorOwner.closingDoc(doc);;
			}

			doc.openDoc(textFrame,filename);

			if (editorOwner != null)
				editorOwner.openedDoc(doc);		// notify EditorOwnerInterface

			if (newdoc)
			{
				textFrame.setVisible(true);
				docs.addElement(doc);
			}
		}
	}

	/**
	 * Opens a new editor window containing the specified file.
     * @param	filename A full path to a text file to be opened.
	 * @return	A DocInterface for the opened document.
	 */
	@Override
	public synchronized DocInterface openDoc(DocOwnerInterface o, String filename)
	{
		docOwner = o;	// jj 1998.02.17 - set default docOwner
		return  openDoc(filename);
	}

	public synchronized DocInterface openDoc(String filename)
	{
		TextFrame frame;
		DocMan doc = null;
		File file;
		
		file = new File(filename);

		if (file.isFile())
		{
			doc = new DocMan(this,strings,props,imageLoader);
			if (doc != null)
			{
				doc.setOwner(docOwner);	// jj 1998.02.17 - apply default docOwner
				frame = doc.newFrame(getPlace(windowSize),windowSize);
				doc.openDoc(frame,filename);
				frame.setVisible(true);
				docs.addElement(doc);
				// jj 1999.02.17  - be consitent about notification	
				if (editorOwner != null)
					editorOwner.openedDoc(doc);
			}
		}
		else
			System.out.println("File not found: "+filename);

		return doc;
	}

	public synchronized void openOneDoc()
	{
		if (docs.size() == 0)
			newDoc();
	}

	public synchronized void closeDoc(DocMan doc)
	{
		int i,max;

		max = docs.size();

		for (i = 0; i < max; i++ )
		{
			if (doc == (DocMan)docs.elementAt(i))
			{
				docs.removeElementAt(i);
				max--;
				break;
			}
		}

		if (editorOwner != null)
			editorOwner.closingDoc(doc);;

		if (max == 0)
			if (editorOwner != null)
				editorOwner.lastFileClosed();	// notify DocOwnerInterface
			else
				System.exit(0);					// no owner, so exit
	}

	public Dimension getWindowSize()
	{
		return windowSize;
	}

	public synchronized Point getPlace(Dimension size)
	{
		Point place = null;

		if (lastPlace == null)
		{
			place = new Point(50,50);
		}
		else
		{
			place = new Point(lastPlace);

			place.y += windowOffset;
			place.x += windowOffset;

			if ((place.x + size.width) > screenDim.width)
				place.x = 0;

			if ((place.y + size.height) > screenDim.height)
				place.y = 0;
		}

		lastPlace = place;

		return place;
	}

	String  openDir = new String(".");

	private String getFileForOpen(TextFrame textFrame)
	{
		String prompt;
		String filename;
		String pathname;

		prompt = strings.getString("DialogOpen");

		FileDialog d = new FileDialog(textFrame, prompt, FileDialog.LOAD);

		d.setFile("*");
		d.setDirectory(openDir);
		d.setVisible(true);

		openDir = d.getDirectory();
		filename = d.getFile(); 
		pathname = openDir + filename; 

		d.dispose();

		if (filename != null)
			return pathname;
		else
			return null;
	}

	public String getKeyAction(int modifiers, int key)
	{
		return (String)keyhash.get(new KeyObject(modifiers, key));
	}

	// remember search and replace

	private Vector /*String*/ searchPatterns = new Vector();

	public void addSearchPattern(String patt)
	{
		int i = searchPatterns.indexOf(patt);

		// do not do anything if it patt is last element
		if ( i != searchPatterns.size() -1 )
		{
			// if already exists delete
			if ( i >= 0 ) 
				searchPatterns.removeElementAt(i);
			
			// add at end of a list
			searchPatterns.addElement(patt);
		}
	}

	public String[] getSearchPatterns()
	{
		String[] newArray = new String[searchPatterns.size()];
		searchPatterns.copyInto(newArray);
		return newArray;
	}

	public String getLatestSearchPattern()
	{
		return (String)searchPatterns.lastElement();
	}

	private Vector /*String*/ replacePatterns = new Vector();

	public void addReplacePattern(String patt)
	{
		int i = replacePatterns.indexOf(patt);

		// do not do anything if it patt is last element
		if ( i != replacePatterns.size() -1 )
		{
			// if already exists delete
			if ( i >= 0 ) 
				replacePatterns.removeElementAt(i);
			
			// add at end of a list
			replacePatterns.addElement(patt);
		}
	}

	public String[] getReplacePatterns()
	{
		String[] newArray = new String[replacePatterns.size()];
		replacePatterns.copyInto(newArray);
		return newArray;
	}

	public String getLatestReplacePattern()
	{
		return (String)replacePatterns.lastElement();
	}

	// remember where new files are being saved

	String saveDir = new String(".");

	public void setSaveDir(String dir)
	{
		saveDir = new String(dir);
	}

	public String getSaveDir()
	{
		return saveDir;
	}

	public void updateProperties(Properties p)
	{
		int i;
		DocMan doc;

		props = p;
		i = docs.size();

		while (i-- > 0)
		{
			doc = (DocMan)docs.elementAt(i);
			doc.setProperties(props);
			doc.applyProperties();
		}

		propMan.writeProperties(p);
	}

	public Vector getBeans()
	{
		return beans;
	}
	/**
	 * Closes all documents.  If the "bail" flag is true changes will
	 * be discarded, otherwise the user will be queried.
     * @param	bail Exit immediately, discarding changes.
	 */
	@Override
	public void closeDocs(boolean bail)
	{
		DocMan d;

		int i = docs.size();

		while (--i >= 0)
		{
			d = (DocMan)docs.elementAt(i);
			d.closeDoc(bail);
		}
	}

	public static void main(String[] args)
	{
		// start editor, but open no windows

		mpEDIT mpe = new mpEDIT();

		// try to open the arg documents

		for (int i=0; i < args.length; i++)
			mpe.openDoc(args[i]);

		// if no other windows have been opened, open a blank one

		mpe.openOneDoc();
	}
}

