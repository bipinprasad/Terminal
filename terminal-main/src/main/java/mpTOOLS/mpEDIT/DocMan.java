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
import java.util.zip.*;
import java.net.*;

import java.awt.event.ActionEvent;

class DocMan implements DocInterface
{
	private mpEDIT		mpEdit;			// the boss
	private StringMan	strings;
	private Properties	props;			// the user preferences
	private Hilite		hilite;
	private Ruler		ruler;
	private LineMan		lines;			// the document data

	private DocOwnerInterface docOwner;

	private Vector			frames;		// the document frames
	private Vector			textMenus;	// attached edit menu handlers
	private File			file;		// the document file
	private JournalItem		undo_list;	// where we are in undo
	private JournalItem		redo_list;	// where we are in redo

	private	boolean dirty;
	private	boolean neverNamed;
	private	boolean privateProps;
	private boolean highlighting;
	private boolean readOnly;

	private String	lineSeparator;

	private final int REPLACE_LINE	= 1;
	private final int SPLIT_LINE	= 2;
	private final int JOIN_LINE		= 3;
	private final int INSERT		= 4;
	private final int DELETE		= 5;
	private final int SWAP_LINES	= 6;
	private final int DELETE_LINE	= 7;

	private ImageLoader	imageLoader;

	public DocMan(mpEDIT mpe, StringMan str, Properties pr, ImageLoader imgLoader)
	{
		lines = new LineMan();
		frames = new Vector(4,4);
		textMenus = new Vector(4,4);

		lines.addElement(new String(""));

		mpEdit = mpe;
		strings = str;
		props = pr;
		imageLoader = imgLoader;

		hilite = new Hilite(lines,0,true);
		ruler = new Ruler(this);

		lineSeparator = System.getProperty("line.separator");

		undo_list = redo_list = null;
	}

//
// methods implementing DocInterface
//

	//
	// A test interface for sending actions to the owner
	//
	public void sendDocAction( String action )
	{

		System.out.println("generic doc owner action = " + action);

		if (docOwner != null)
			docOwner.docAction(this, action);
	}
	
	public void sendOpenedView( ViewInterface view )
	{
		if (docOwner != null)
			docOwner.openedView(view);
	}
	
	public void sendClosingView( ViewInterface view )
	{
		if (docOwner != null)
			docOwner.closingView(view);
	}

	public void sendViewAction( String action )
	{

		System.out.println("generic doc owner action = " + action);

		if (docOwner != null)
			docOwner.docAction(this, action);
	}
	
	public void setOwner(DocOwnerInterface o)
	{
		docOwner = o;
	}
	
	public DocOwnerInterface getOwner()
	{
		return docOwner;
	}

	/**
	 * Closes this document.  If the "bail" flag is true changes will
	 * be discarded, otherwise the user will be queried.
     * @param	bail Exit immediately, discarding changes.
	 */
	@Override
	public void closeDoc(boolean bail)
	{
		int i;
		int max;
		TextFrame textFrame;

		i = frames.size();

		while (--i >= 0)
		{
			textFrame = (TextFrame)frames.elementAt(i);
			if (bail || (i > 1) || !dirty)
			{
				doCloseFrame(textFrame);
			}
			else
			{	// show dialog for last frame (if not 'bail', etc.)

				DirtyDialog ab = new DirtyDialog(this,
												 textFrame,
												 strings,
												 strings.getString("DialogDirty"),
												 file.getName());
				ab.show();
			}
		}
	}

		
	/**
	 * Writes out this document.
	 */
	@Override
	public void saveDoc()
	{
		fileSave(anyFrame());
	}
		
	/**
	 * Writes out this document.
     * @param	pathname New document name (full path).
	 */
	@Override
	public void saveAsDoc(String pathname)
	{
		file = new File(pathname);
		setTitles();
		neverNamed = false;
		write(anyFrame(),file);
		scan();
	}
		
	/**
	 * Returns the full path to this document.
     * @return	Document name (full path).
	 */
	@Override
	public String getPathname()
	{
		return file.getPath();
	}
		
	/**
	 * Returns the filename (no path) for this document.
     * @return	Document name (no path).
	 */
	@Override
	public String getFilename()
	{
		return file.getName();
	}
		
	/**
	 * Returns status of the document.
     * @return	True if the file has been changed since being written.
	 */
	@Override
	public boolean docChanged()
	{
		return dirty;
	}
			
	/**
	 *  Get the line count for a document.
	 */
	@Override
	public int getLineCount()
	{
		return lines.size();
	}
		
	/**
	 * Get a TagLine, used to track lines even when lines have
	 * been inserted or deleted elsewhere in the document.
     * @return	The TagLine for a given line.
	 */

	int nextTag = 123;

	@Override
	public TagLine tagLine(int line)
	{
		LineInfo li = lines.getLineInfo(line);
		li.tagValue = nextTag++;
		TagLine tag = new TagLine();
		tag.tagValue = li.tagValue;
		tag.lastLine = line;
		return tag;
	}

	/**
	 * Set a TagLine (including a tag color), used to track lines even when lines have
	 * been inserted or deleted elsewhere in the document.
     * @param	color	The Color to display (pass null to clear).
     * @return	The TagLine for a given line.
	 */
	@Override
	public TagLine tagLine(int line, Color color)
	{
		LineInfo li = lines.getLineInfo(line);
		li.tagValue = nextTag++;
		li.tagColor = color;
		TagLine tag = new TagLine();
		tag.tagValue = li.tagValue;
		tag.lastLine = line;
		updateFrames(line,line);
		return tag;
	}


	/**
	 * Get the current line number for a TagLine.
     * @return	The TagLine for a given line.
	 */
	@Override
	public int lineFromTag(TagLine tag)
	{
		int i = tag.lastLine;

		LineInfo li = lines.getLineInfo(i);

		// first check the old location

		if (li.tagValue == tag.tagValue)
			return i;

		// get fancy later

		i = lines.size();

		while (--i >= 0)
		{
			li = lines.getLineInfo(i);
			if (li.tagValue == tag.tagValue)
			{
				tag.lastLine = i;
				return i;
			}
		}

		return -1;
	}

	/**
	 * Bring forward any view window and scroll to the desired line.
     * @param	tag	The line to display.
	 */
	@Override
	public boolean showLine(TagLine tag)
	{
		int line = lineFromTag(tag);

		if (line < 0)
			return false;

		TextFrame f = anyFrame();
		f.showLine(line);
		f.toFront();
		return true;
	}

	/**
	 * Get the text for a line.
     * @param	tag	The line to get.
     * @return	The text for the line (null if TagLine not found).
	 */
	@Override
	public String getLine(TagLine tag)
	{
		int line = lineFromTag(tag);

		if (line < 0)
			return null;

		return new String (lines.getString(line));
	}

	/**
	 * Set the text for a line.
     * @param	tag	The line to set.
     * @param	s	The text to set.
     * @return	Success.
	 */
	@Override
	public boolean setLine(TagLine tag, String s)
	{
		int line = lineFromTag(tag);

		if (line < 0)
			return false;

		// set the text

		lines.setString(s,line);
	
		// update

		updateFrames(line,line);
		dirty = true;

		return true;
	}
	
	/**
	 * Insert a line of text before the TagLine.
     * @param	tag	The line to set.
     * @param	s	The text to set.
     * @return	Success.
	 */
	@Override
	public boolean addLine(TagLine tag, String s)
	{
		int line = lineFromTag(tag);

		if (line < 0)
			return false;

		// set the text

		lines.insertElementAt(s,line);
	
		// update

		updateFrames(line,line+1);
		dirty = true;

		return true;
	}

	/**
	 * Delete a line.
     * @param	tag	The line to delete.
     * @return	Success.
	 */
	@Override
	public boolean deleteLine(TagLine tag)
	{
		int line = lineFromTag(tag);

		if (line < 0)
			return false;

		// set the text

		lines.removeElementAt(line);
	
		// update

		updateFrames(line,line+1);
		dirty = true;

		return true;
	}
	
    //
    // sl - A call to display a document in front of all others.    
    // 
    @Override
	public void docToTop()
    {
      TextFrame f = (TextFrame)frames.elementAt(0);
      f.clearCanvas();  
      f.redoCanvas();
      f.toFront();
      f.requestFocus();
    }
    
	//    
	// sl - reloadDoc - Used to reload an existing/open document back into mpEDIT
	//      after it has been altered by an external source.
	
	  @Override
	  public void reloadDoc(String filename){
	    TextFrame textFrame=anyFrame();
	    file = new File(filename);
	    if (file.isFile())
	      {
	        textFrame.clearCanvas();
	        read(textFrame,filename);
	        textFrame.redoCanvas();
	        scan();
	      }    
	  }
//
// utility functions, manage frames and canvases attached to this document
//

	public void setUntitled(int count)
	{
		String filename = 
			new String(strings.getString("UntitledDoc")+
			(count==1?"":" "+count));
		file = new File(filename);
		setTitles();
		neverNamed = true;
	}
	
	public void setTitles()
	{
		int i,max;
		String name = file.toString();

		max = frames.size();

		for (i = 0; i < max; i++ )
		{
			if (max > 1)
				name = new String(file.toString() + ":" + (i+1));
				
			((TextFrame)frames.elementAt(i)).setTitle(name);
		}
	}

	public TextFrame newFrame(Point point, Dimension size)
	{
		TextFrame textFrame = new TextFrame(mpEdit, strings, props, this, ruler, imageLoader);
		textFrame.setLocation(point);
		textFrame.setSize(size);
		frames.addElement(textFrame);
		updateMenus();
		return textFrame;
	}

	public void closeFrame(TextFrame textFrame)
	{
		int max;
		
		max = frames.size();

		if ((max > 1) || !dirty)
		{
			doCloseFrame(textFrame);
		}
		else
		{
			DirtyDialog ab = new DirtyDialog(this,
											 textFrame,
											 strings,
											 strings.getString("DialogDirty"),
											 file.getName());
			ab.show();
		}
	}

	public void doCloseFrame(TextFrame textFrame)
	{
		int i;
		int max;
		
		max = frames.size();

		for (i = 0; i < max; i++ )
		{
			if (textFrame == (TextFrame)frames.elementAt(i))
			{
				sendClosingView(textFrame.getCanvas());
				frames.removeElementAt(i);
				textFrame.dispose();
				max--;
				break;
			}
		}

		if (max == 0)
			mpEdit.closeDoc(this);
		else
			setTitles();
	}

	public void newDoc()
	{
		int i,max;
		lines.removeAllElements();
		lines.addElement(new String(""));
		max = frames.size();
		for (i = 0; i < max; i++ )
		{
			TextFrame f = (TextFrame)frames.elementAt(i);
			f.clearCanvas();		
			f.redoCanvas();
		}
		setUntitled(mpEdit.getUntitled());
		clear_undo();
		updateMenus();
	}

	public boolean isBusy()
	{
		return dirty || !neverNamed;
	}

	public boolean isReadOnly()
	{
		return readOnly;
	}

	@Override
	public void setReadOnly(boolean aReadOnly )
	{
		if ( aReadOnly != readOnly )
		{
			readOnly = aReadOnly;
			int max = frames.size();
			int i;
			for ( i = 0; i < max; i++ )
			{
				((TextFrame)frames.elementAt(i)).setReadOnly(readOnly);
			}
			System.out.println("ReadOnly " + readOnly);
		}
	}

	

//
// utility functions, high-level routines for reading and writing documents
//

	public TextFrame anyFrame()
	{
		return (TextFrame)frames.firstElement();
	}

	public void openDoc(TextFrame textFrame, String filename)
	{
		int i,max;

		file = new File(filename);
		if (file.isFile())
		{
			neverNamed = false;
			max = frames.size();
			for (i = 0; i < max; i++ )
				((TextFrame)frames.elementAt(i)).clearCanvas();
			setTitles();
			ruler.invalidate(0,1000000);
			read(textFrame,filename);
			for (i = 0; i < max; i++ )
				((TextFrame)frames.elementAt(i)).redoCanvas();
			scan();
		}

		textFrame.getCanvas().requestFocus();
	}

	public void filePrint(TextFrame textFrame)
	{
		PrintMan pm = new PrintMan(textFrame, this, file.getName(), getFont(), getTabSize());

		if (pm != null)
			pm.start();
	}

	public boolean fileSave(TextFrame textFrame)
	{
		return doSaveFile(textFrame, false);
	}

	public void fileSaveAs(TextFrame textFrame)
	{
		doSaveFile(textFrame, true);
	}

	private boolean doSaveFile(TextFrame textFrame, boolean As)
	{
		boolean ok = true;
		String filename = null;

		if (neverNamed || As)
		{
			filename = getFileForSave(textFrame);
			if (filename == null)
			{
				ok = false;
			}
			else
			{
				file = new File(filename);
				setTitles();
				neverNamed = false;
			}
		}

		if (ok)
		{
			write(textFrame,file);
			scan();
			if (docOwner != null)
			{
				if (As)
					docOwner.savedAsDoc(this,filename);
				else
					docOwner.savedDoc(this);
			}
		}

		return ok;
	}

	private String getFileForSave(TextFrame textFrame)
	{
		String prompt;
		String filename;
		String dirname;
		String pathname;

		prompt = strings.getString("DialogSaveAs");

		FileDialog d = new FileDialog(textFrame, prompt, FileDialog.SAVE);

		d.setFile(file.getName());

		// for a never-named file, pick up the last directory
		// a new file was saved to

		if (neverNamed)
			dirname = mpEdit.getSaveDir();
		else
			dirname = file.getParent();

		d.setDirectory(dirname);

		d.setVisible(true);

		filename = d.getFile();
		dirname = d.getDirectory();
		pathname = dirname + filename; 

		d.dispose();

		if (filename != null)
		{
			// remeber where a new file was saved to
			// or should it be where any file was saved ?

			if (neverNamed)
				mpEdit.setSaveDir(dirname);

			return pathname;
		}
		else
			return null;
	}

//
// utility functions, low-level routines for reading and writing documents
//

	private final int SLOW_READ  = 2000;
	private final int SLOW_WRITE = 10000;

	public void read(TextFrame textFrame, String filename)
	{
		Progress readProgress = null;
		boolean show_progress;
		long max,part,all,tens;
		String line;

		max = file.length();
		tens = max / 10;
		part = all = 0;
		show_progress = max > SLOW_READ;

		if (show_progress)
		{
			if (readProgress == null)
				readProgress = new Progress(textFrame, "Reading file ... ");

			readProgress.show();
		}

		lines.freshVectors((int)Math.max(max / 20,100), (int)Math.max(max / 100, 100));

		try
		{
			BufferedReader br = createBufferedReader( filename );

			while ( (line=br.readLine()) != null)
			{
				//System.out.println( "" + Runtime.getRuntime().freeMemory() + "  " + Runtime.getRuntime().totalMemory() );

				if (show_progress)
				{
					part += line.length();
					if (part > tens)
					{
						all += part;
						readProgress.update( (int)(100 * all / max) );
						part = 0;
					}
				}
				lines.addElement(line);
			}

			br.close();
		}

		catch (IOException e)
		{
			System.out.println("Error - could not read file");
		}
		if (readProgress != null)
			readProgress.dispose();
		if (lines.size() < 1)
			lines.addElement(new String(""));
	}

	/** create BufferedReader for String indicating any file or URL, gzipped or not */
	BufferedReader createBufferedReader( String filename ) throws FileNotFoundException, IOException
	{
	    BufferedReader br;
	    PushbackInputStream test;
		try 
		{
			URL url = new URL(filename);
			test = new PushbackInputStream( url.openStream() );
    	} 
		catch ( Exception e ) 
		{
		    File source = new File( filename );
		    test = new PushbackInputStream( new FileInputStream( source ));
		}
		int testchar = test.read();
		test.unread( testchar );
		if ( testchar == 0x1f ) 
		{
			GZIPInputStream gzi = new GZIPInputStream( test );
			br = new BufferedReader( new InputStreamReader( new BufferedInputStream( gzi )));
   		}
		else
		{
			br = new BufferedReader( new InputStreamReader( test ));
		}
		return( br );
	}
		
	public void write(TextFrame textFrame, File file)
	{
		Progress writeProgress = null;
		boolean show_progress;
		long max,part,all,tens;
		String line;

		max = lines.size();
		tens = max / 10;
		part = all = 0;
		show_progress = max > SLOW_WRITE;

		if (show_progress)
		{
			if (writeProgress == null)
				writeProgress = new Progress(textFrame, "Writing file ... ");

			writeProgress.show();
		}

		try
		{
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw=new BufferedWriter(fw);

			for (int i=0;i<max;i++)
			{
				line = lines.getString(i);
				bw.write(line,0,line.length());
				bw.newLine();
				if (show_progress)
				{
					part++;
					if (part > tens)
					{
						all += part;
						writeProgress.update( (int)(100 * all / max) );
						part = 0;
					}
				}
			}

			bw.close();
			fw.close();

			dirty = false;
		}

		catch (IOException e)
		{
			System.out.println("Error - could not write file");
		}
		if (writeProgress != null)
			writeProgress.dispose();
	}


	public void clear_undo()
	{
		undo_list = redo_list = null;			
	}

//
// utility functions, manage syntax highlighting
//

	public void scan()
	{
		int i;
		TextFrame textFrame;
		TextCanvas textCanvas;

		highlighting = Boolean.valueOf(getProperty("mpEDIT.hilite")).booleanValue();

		if (highlighting)
		{
			String filename = file.toString();
			if (filename.endsWith(".java"))
				hilite = new HiliteJava(lines,getTabSize(),false);
			else
				if (filename.endsWith(".html") || filename.endsWith(".htm"))
				hilite = new HiliteHTML(lines,getTabSize(),false);
			else
				if (highlighting && (file.toString().endsWith(".cpp") ||
				file.toString().endsWith(".cc") ||
				file.toString().endsWith(".c") ))
				hilite = new HiliteCpp(lines, getTabSize(), false );
			else
				if (filename.endsWith(".js") ||
				filename.endsWith(".es") ||
				filename.endsWith(".esw"))
				hilite = new HiliteEcmaScript(lines,getTabSize(),false);
			else
				if (filename.endsWith(".wrl"))
				hilite = new HiliteVRML(lines,getTabSize(),false);
		}
		else
		{
			hilite = new Hilite(lines,0,true);
			highlighting = false;
		}

		hilite.scan(getHighest());

		i = frames.size();

		while (i-- > 0)
		{
			textFrame = (TextFrame)frames.elementAt(i);
			textCanvas = textFrame.getCanvas();
			textCanvas.repaint();
		}
	}

	public void extendHilite(int highest)
	{
		int temp;

		temp = lines.size() - 1;

		if (highest > temp)
			highest = temp;
			
		hilite.extendScan(highest);
	}

	public Color getTextColor()
	{
		return hilite.getTextColor();
	}

	public Color getTextXColor()
	{
		return hilite.getTextXColor();
	}

	public Color getCommentColor()
	{
		return hilite.getCommentColor();
	}

	public Color getCommentXColor()
	{
		return hilite.getCommentXColor();
	}

	public Color getKeywordColor()
	{
		return hilite.getKeywordColor();
	}

	public Color getKeywordXColor()
	{
		return hilite.getKeywordXColor();
	}

	public Color getKeyword2Color()		// handy for VRML
	{
		return hilite.getKeyword2Color();
	}

	public Color getKeyword2XColor()	// handy for VRML
	{
		return hilite.getKeyword2XColor();
	}

	public Color getQuoteColor()
	{
		return hilite.getQuoteColor();
	}

	public Color getQuoteXColor()
	{
		return hilite.getQuoteXColor();
	}

//
// utility functions, update canvases following changes to document
//

	public void updateFrames(int first, int last)
	{
		int i;
		TextFrame textFrame;
		TextCanvas textCanvas;

		ruler.invalidate(first,last);
		last = hilite.update(first,last,getHighest());
		
		i = frames.size();
		
		while (i>0)
		{
			i--;
			textFrame = (TextFrame)frames.elementAt(i);
			textCanvas = textFrame.getCanvas();
			textCanvas.legalizeCursor();
			textCanvas.linesChanged(first,last);
		}
	}

	private void legalizeCursors()
	{
		int i,max;

		max = frames.size();
		for (i = 0; i < max; i++ )
		{
			TextFrame f = (TextFrame)frames.elementAt(i);
			f.legalizeCursor();
		}
	}

//
// utility function, get highest visible line in any canvas
//

	private int getHighest()
	{
		int i,temp,highest;
		TextFrame textFrame;
		TextCanvas textCanvas;

		i = frames.size();
		highest = 0;

		while (i-->0)
		{
			textFrame = (TextFrame)frames.elementAt(i);
			textCanvas = textFrame.getCanvas();
			temp = textCanvas.getHighest();
			if (temp > highest)
				highest = temp;
		}

		temp = lines.size() - 1;

		if (highest > temp)
			highest = temp;

		return highest;
	}

//
// utility functions, access properties for this document
//

	public String getProperty(String p)
	{
		return props.getProperty(p);
	}

	public void setProperty(String p, String v)
	{
		props.put(p,v);
	}

	public void setProperties(Properties p)
	{
		props = p;
		privateProps = false;
	}

	public void splitProperties()
	{
		if (!privateProps)
		{
			props = new Properties(props);
			privateProps = true;
		}
	}

	public void updateProperties(boolean global)
	{
		if (global)
			mpEdit.updateProperties(props);
		else
			applyProperties();
	}

	public void applyProperties()
	{
		int i;
		TextFrame textFrame;
		TextCanvas textCanvas;

		scan();

		i = frames.size();

		while (i-- > 0)
		{
			textFrame = (TextFrame)frames.elementAt(i);
			textCanvas = textFrame.getCanvas();
			textCanvas.applyProperties();
		}
	}

	public String getFontStyle()
	{
		String style = props.getProperty("mpEDIT.font.style");

		switch (Integer.valueOf(style).intValue())
		{

		case Font.PLAIN:
			return strings.getString("ChoicePlain");
		case Font.BOLD:
			return strings.getString("ChoiceBold");
		case Font.ITALIC:
			return strings.getString("ChoiceItalic");
		case Font.BOLD | Font.ITALIC:
			return strings.getString("ChoiceBoldItalic");
		default:
			break;
		}

		return strings.getString("ChoicePlain");
	}

	public void setFontStyle(String style)
	{
		int s = Font.PLAIN;

		if (style.equals(strings.getString("ChoiceBold")))
			s = Font.BOLD;
		else
		if (style.equals(strings.getString("ChoiceItalic")))
			s = Font.ITALIC;
		else
		if (style.equals(strings.getString("ChoiceBoldItalic")))
			s = Font.BOLD | Font.ITALIC;

		props.put("mpEDIT.font.style",String.valueOf(s));
	}

	public Font getFont()
	{
		String name, style, size;
		int i;

		name  = props.getProperty("mpEDIT.font.name");     // add defaults
		style = props.getProperty("mpEDIT.font.style");
		size  = props.getProperty("mpEDIT.font.size");

		return new Font(name,
						Integer.valueOf(style).intValue(),
						Integer.valueOf(size).intValue());
	}

	public int getTabSize()
	{
		int tabs;
		
		try
		{
			tabs = Integer.valueOf(props.getProperty("mpEDIT.tab.size")).intValue();
		}

		catch (NumberFormatException e)
		{
			props.put("mpEDIT.tab.size","4");
			tabs = 4;
		}

		return tabs;
	}

//
// utility functions, access data in "lines" structure
//

	public String getLine(int i)
	{
		return lines.getString(i);
	}

	public LineInfo getLineInfo(int i)
	{
		return lines.getLineInfo(i);
	}

//
// utility functions, update menus to reflect undo/redo and copy/paste status
//

	public void addTextMenu(TextMenu e)
	{
		textMenus.addElement(e);
	}

	public void updateUndoItems(boolean un,boolean re)
	{
		int i,max;

		max = frames.size();
		for ( i=0; i < max; i++)
		{
			TextCanvas tc = ((TextFrame)frames.elementAt(i)).getCanvas();
			tc.updateUndoActions(un,re);
		}
	}

	private void updateMenus()
	{
		boolean got_undo;
		boolean got_redo;

		got_undo = undo_list != null;
		got_redo = redo_list != null;

		updateUndoItems(got_undo, got_redo);
	}

//
// The following routines implement high-level actions for undo, and redo
//

	public void undo(TextFrame textFrame)
	{
		int first,last;
		JournalItem temp;
		TextPosition tp;

		if (undo_list != null)
		{
			temp = undo_list;
			undo_list = temp.next;
			first = last = temp.line;

			switch (temp.action)
			{
			case REPLACE_LINE:
				redo_line(temp);
				break;
			case SPLIT_LINE:
				split_or_join(temp,true);
				last++;
				break;
			case JOIN_LINE:
				split_or_join(temp,false);
				break;
			case INSERT:
				copy_or_cut(temp,true);
				break;
			case DELETE:
				tp = insert(temp);
				last = tp.line;
				break;
			case SWAP_LINES:
				internal_swap_lines(temp.line, temp.eline);
				last++;
				break;
			case DELETE_LINE:
				insert(temp);
				last++;
				break;
			default:
				;
			}

			updateFrames(first,last);

			tp = new TextPosition(temp.line,temp.column);
			textFrame.setPos(tp);

			legalizeCursors();

			temp.next = redo_list;
			redo_list = temp;
		}

		updateMenus();
	}

	public void redo(TextFrame textFrame)
	{
		int first,last;
		JournalItem temp;
		TextPosition tp;
		int line, column;

		if (redo_list != null)
		{
			temp = redo_list;
			redo_list = temp.next;
			first = last = line = temp.line;
			column = temp.column;

			switch (temp.action)
			{
			case REPLACE_LINE:
				column = temp.text.length();
				redo_line(temp);
				break;
			case SPLIT_LINE:
				split_or_join(temp,false);
				column = 0;
				break;
			case JOIN_LINE:
				split_or_join(temp,true);
				last++;
				break;
			case INSERT:
				tp = insert(temp);
				last = tp.line;
				line = temp.eline;
				column = temp.ecolumn;
				break;
			case DELETE:
				copy_or_cut(temp,true);
				break;
			case SWAP_LINES:
				internal_swap_lines(temp.line, temp.eline);
				last++;
				break;
			case DELETE_LINE:
				lines.removeElementAt(temp.line);
				break;
			default:
				;
			}
			
			updateFrames(first,last);

			tp = new TextPosition(line,column);
			textFrame.setPos(tp);

			legalizeCursors();

			temp.next = undo_list;
			undo_list = temp;
		}

		updateMenus();
	}

//
// The following routines implement low-level actions for do, undo, and redo
//

	public void insert_char(int line, int column, char c)
	{
		String  s;
		s = lines.getString(line);
		remember_line(line,column,s);
		s = s.substring(0, column) + c + s.substring(column, s.length());
		lines.setString(s,line);
		updateFrames(line,line);
		dirty = true;
	}

	public void delete_char(int line, int column)
	{
		String s;
		s = lines.getString(line);
		remember_line(line,column,s);
		s = s.substring(0, column) + s.substring(column+1, s.length());
		lines.setString(s,line);
		updateFrames(line,line);
		dirty = true;
	}

	private void remember_line(int line, int column, String s)
	{
		boolean new_line = true;

		if (undo_list != null)
			new_line = (undo_list.action != REPLACE_LINE) || (undo_list.line != line);

		if (new_line)
		{
			JournalItem new_journal = new JournalItem();
			new_journal.action = REPLACE_LINE;
			new_journal.line = line;
			new_journal.column = column;
			new_journal.text = new String(s);
			new_journal.next = undo_list;
			undo_list = new_journal;
		}

		redo_list = null;
		updateMenus();
	}

	private void redo_line(JournalItem i)
	{
		String s;
		int line = i.line;
		s = lines.getString(line);
		lines.setString(i.text,line);
		i.text = s;
	}

	public void split_line(int line, int column)
	{
		JournalItem new_journal = new JournalItem();
		new_journal.action = SPLIT_LINE;
		new_journal.line = line;
		new_journal.column = column;
		split_or_join(new_journal,false);
		new_journal.next = undo_list;
		undo_list = new_journal;
		redo_list = null;
		updateMenus();
		updateFrames(line,line+1);
		dirty = true;
	}

	public void join_line(int line, int column)
	{
		JournalItem new_journal = new JournalItem();
		new_journal.action = JOIN_LINE;
		new_journal.line = line;
		new_journal.column = column;
		split_or_join(new_journal,true);
		new_journal.next = undo_list;
		undo_list = new_journal;
		redo_list = null;
		updateMenus();
		updateFrames(line,line+1);
		dirty = true;
	}

	public void split_or_join(JournalItem i, boolean join)
	{
		String s;
		int line = i.line;
		int column = i.column;

		if (join)
		{
			s = lines.getString(line);
			s = s.concat(lines.getString(line+1));
			lines.setString(s,line);
			lines.removeElementAt(line+1);
			hilite.lineRemoved(line+1);
		}
		else
		{
			s = lines.getString(line);
			lines.setString(s.substring(0,column),line);
			lines.insertElementAt(s.substring(column,s.length()),++line);
		}
	}

	public TextPosition insert_section(int line, int column, String s, boolean update)
	{
		int charCt;
		int saveCt;
		int charMax;
		char c,c2;
		String s2 = null;
		TextPosition tp;

		// start creating journal item

		JournalItem new_journal = new JournalItem();
		new_journal.action = INSERT;
		new_journal.text = new String(s);
		new_journal.line = line;		// starting line and column
		new_journal.column = column;

		// insert the text

		tp = insert(new_journal);
	
		// finish creating journal entry

		new_journal.eline = tp.line;		// updated line and column
		new_journal.ecolumn = tp.column;
		new_journal.next = undo_list;
		undo_list = new_journal;
		redo_list = null;
		updateMenus();
		if (update)
			updateFrames(line,tp.line);
		dirty = true;

		return tp;
	}

	public TextPosition insert(JournalItem i)
	{
		int line = i.line;
		int column = i.column;
		String s = i.text;

		int charCt;
		int saveCt;
		int charMax;
		char c,c2;
		String s2 = null;

		// insert the text

		charMax = s.length();
		charCt = saveCt = 0;
		
		while (charCt < charMax)
		{
			c = s.charAt(charCt);
			charCt++;
			if ((c == '\r') || (c == '\n'))
			{
				s2 = lines.getString(line);
				lines.setString(s2.substring(0,column) + s.substring(saveCt,charCt-1),line);
				lines.insertElementAt(s2.substring(column,s2.length()),++line);
				column = 0;
				if (charCt < charMax)
				{
					c2 = s.charAt(charCt);
					if (((c == '\r') && (c2 == '\n')) || ((c2 == '\r') && (c == '\n')))
						charCt++;
				}
				saveCt = charCt;
			}
		}
		
		if (saveCt < charCt)
		{
			s2 = lines.getString(line);
			s = s.substring(saveCt,charCt);
			if (column == 0)
				s2 = s + s2;
			else
				s2 = s2.substring(0, column) + s + s2.substring(column, s2.length());
			column += s.length();
			lines.setString(s2,line);
		}
		return new TextPosition(line,column);
	}

	public String delete_section(int line, int column, int eline, int ecolumn, boolean cut)
	{
		String text;

		JournalItem new_journal = new JournalItem();
		new_journal.action = DELETE;
		new_journal.line = line;
		new_journal.column = column;
		new_journal.eline = eline;
		new_journal.ecolumn = ecolumn;

		text = copy_or_cut(new_journal,cut);

		if (cut)
		{
			new_journal.text = new String(text);
			new_journal.next = undo_list;
			undo_list = new_journal;
			redo_list = null;
			updateMenus();
			dirty = true;
		}

		return text;
	}

	private String copy_or_cut(JournalItem i, boolean cut)
	{
		String s,s2;
		String text = null;

		int line = i.line;
		int column = i.column;
		int eline = i.eline;
		int ecolumn = i.ecolumn;
	
		if (line == eline)
		{
			s = lines.getString(line);
			text = s.substring(column,ecolumn);
			if (cut)
			{
				s = s.substring(0, column) + s.substring(ecolumn, s.length());
				lines.setString(s,line);
			}
		}
		else
		{
			s = lines.getString(line);
			text = s.substring(column,s.length());
			s = s.substring(0, column);

			int diff = eline - line;
			int k = line + 1;

			for (int j = 1; j < diff; j++)
			{
				s2 = lines.getString(k);
				text = text + lineSeparator + s2;
				if (cut) {
					lines.removeElementAt(k);
					hilite.lineRemoved(k);
				}
				else
					k++;
			}

			if (k != lines.size())
    		{
				s2 = lines.getString(k);
				text = text + lineSeparator + s2.substring(0,ecolumn);
				s = s + s2.substring(ecolumn,s2.length());
				if (cut) {
					lines.removeElementAt(k);
					hilite.lineRemoved(k);
				}
			}
			if (cut)
				lines.setString(s,line);
		}
		return text;
	}

	public String delete_line( int line )
	{		
		JournalItem ji = new JournalItem();
		ji.action = DELETE_LINE;
		ji.line = line;
		ji.column = 0;
		ji.text = lines.getString(line);
		ji.ecolumn = ji.text.length();
		ji.text += '\n';
		ji.next = undo_list;
		undo_list = ji;
		redo_list = null;

		lines.removeElementAt(line);
		updateMenus();
		updateFrames(line, line);

		return ji.text;
	}

	public String clear_line( int line )
	{
		return delete_section(line, 0, line, lines.getString(line).length(), true);
	}

	public void insert_line( int after, String txt )
	{
		insert_section(after, 0, txt + '\n', true);
	}

	private void internal_swap_lines( int first, int second )
	{
		LineInfo li = lines.getLineInfo(first);

		lines.setLineInfo(lines.getLineInfo(second), first);
		lines.setLineInfo(li, second); 

		updateFrames(first,second);
	}

	public void swap_lines( int first, int second )
	{
		JournalItem ji = new JournalItem();
		ji.action = SWAP_LINES;
		ji.line = first;
		ji.eline = second;
		ji.next = undo_list;
		undo_list = ji;
		redo_list = null;

		internal_swap_lines( first, second );

		updateMenus();
		return;
	}

}	

//
// JournalItem is used in the redo/undo queues to track changes to a document
//

class JournalItem
{
	public JournalItem		next;
	public String			text;
	public int				action;
	public int				line;
	public int				column;
	public int				eline;
	public int				ecolumn;
}
