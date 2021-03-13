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
import java.awt.datatransfer.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/*
 * Every private keyword was changed to protected and class was made final
 * It could be changed when anonymous class bug in javac will be corrected
 * TO do that just replace all 'protected' with 'private'
 */

final class TextCanvas
extends		Canvas
implements	ViewInterface,
			MouseMotionListener,
			MouseListener,
			ComponentListener,
			AdjustmentListener,
			KeyListener,
			FocusListener,
			ClipboardOwner
{
	final protected int EDGE = 10;

	protected Scrollbar	horiz;				// control
	protected Scrollbar	vert;				// control

	protected boolean		eactive,hactive;	// is there currently a range?
	protected boolean		mouseDown;
	protected boolean		autoIndent = true;	// auto indentation after ENTER?
	protected boolean		doSeparator = false;// print separator bar?

	protected int			sx,sy,ny;			// current scroll positions

	protected int			line,column,pix;	// the current column
	protected int			eline,ecolumn,epix;	// the current end column
	protected int			oldlines;			// repaint to old line count (if longer)
	protected int			fontHeight;			// save time
	protected int			fontDescent;		// save time
	protected int			tabSize;			// preference

	protected Font			font;				// preference
	protected FontMetrics	fontMetrics;		// save time
	protected Ruler			ruler;				// measure strings in pixels
	protected TextFrame		textFrame;			// friendly object
	protected TextCursor	textCursor;			// friendly object
	protected TextMenu		textMenu;			// friendly object
	protected TextCanvas	textCanvas;			// pointer to self, used by inner classes
	protected Dimension		dimension;			// save time
	protected StringMan		strings;			// interface to ResourceBundle
	protected DocMan		docMan;				// the document
	protected mpEDIT		mpEdit;				// the big cheese

	//protected PatternCompiler compiler;
	//protected Perl5Matcher matcher;
	protected Matcher		matcher;

	protected Color textColor;
	protected Color textXColor;
	protected Color commentColor;
	protected Color commentXColor;
	protected Color keywordColor;
	protected Color keywordXColor;
	protected Color keyword2Color;		// handy for VRML
	protected Color keyword2XColor;		// handy for VRML
	protected Color quoteColor;
	protected Color quoteXColor;

	protected int linesEmpty = 0;	// how many succesive lines was empty at the moment
	protected int lastVerticalSize = 10; // vertical size of view in text lines

	protected TextScroller textScroller = null;

	protected Hashtable actionDictionary;

	protected Vector /*TagLine*/ anchors;
	protected int currentAnchor = -1;
	protected Vector /*MpAction*/ macro;
	protected boolean recordingMacro = false;

	protected Clipboard clipboard;

	final int LINE_MAX = 2000;

	public TextCanvas(mpEDIT mpe,
					  StringMan str,
					  DocMan doc,
					  TextFrame tf,
					  Scrollbar h,
					  Scrollbar v,
					  Ruler r)
	{
		super();

		mpEdit = mpe;
		strings = str;
		docMan = doc;
		ruler = r;

		textCanvas = this;
		textFrame = tf;
		clipboard = textFrame.getToolkit().getSystemClipboard();

		createActionDictionary();
		anchors = new Vector(5);

		setBackground(Color.white);
		setForeground(Color.black);

		horiz = h;
		horiz.addAdjustmentListener(this);
		horiz.addKeyListener(this);
		
		vert = v;
		vert.addAdjustmentListener(this);
		vert.addKeyListener(this);

		addComponentListener(this);
		addMouseListener(this);
		addKeyListener(this);
		addFocusListener(this);

		applyProperties();

		clear();
		validate();
		requestFocus();

		textCursor = new TextCursor(this);
		textCursor.start();

		docMan.sendOpenedView(this);
	}

	public void lostOwnership(Clipboard clipboard, Transferable contents)
	{
    }

	protected boolean gotFocus = false;

	public void focusGained(FocusEvent e)
	{
		gotFocus = true;
		release_cursor();
	}

	public void focusLost(FocusEvent e)
	{
		pause_cursor();
		gotFocus = false;
	}

	public void release_cursor()
	{
		if ((textCursor != null) && !eactive && gotFocus)
			textCursor.release_cursor(true);
	}

	public void pause_cursor()
	{
		if ((textCursor != null) && !eactive && gotFocus)
			textCursor.pause_cursor(true);
	}

	public void setTextMenu(TextMenu tm)
	{
		textMenu = tm;
	}

	public int getHighest()	// highest visible line
	{
		return sy + ny - 1;
	}

	public void clear()
	{
		clear_area(null);
		hactive = eactive = false;
		updateCopyActions(false);
		oldlines = line = column = sx = sy = 0;
		pix = EDGE;
		horiz.setValue(0);
		vert.setValue(0);
	}

	public void applyProperties()
	{

		font = docMan.getFont();
		tabSize = docMan.getTabSize();
		
		Graphics g = getGraphics();
		
		if (g != null)
		{
			clear_area(g);
			updateFonts(g);
			ruler.setTabSize(tabSize);
			resizeLines();
			redoCanvas();
			repaint();
			g.dispose();
		}
		else
			fontMetrics = null;
	}

	protected void clear_area(Graphics g)
	{
		boolean foobar = false;
		
		pause_cursor();
			
		if (g == null)
		{
			g = getGraphics();
			foobar = true;
		}
		
		if (g != null)
			g.clearRect(0,0,dimension.width,dimension.height);

		if (foobar && (g != null))
			g.dispose();
	}
			
	public void setPos(TextPosition tp)
	{
		hactive = eactive = false;
		line = eline = tp.line;
		column = ecolumn = tp.column;
		pix = epix = ruler.length(line,column) + EDGE;
		shiftVert(line);
		shiftHoriz(pix);
		repaint();
	}
	
	public void Goto(int y)
	{
		y--;    // line is zero based
		
		if ((y >= 0) && (y < docMan.getLineCount()))
		{
			boolean paint = hactive;
			hactive = eactive = false;
			
			// the first resize may not have be called
			if (ny == 0) 
				setNy();
			
			// set the reference line depending on the direction
			int shiftLine = ny / 2;
			if (y < line) 
				shiftLine = (y > (ny / 2)) ? y - (ny / 2) : 0;
			else {
				shiftLine = y + (ny / 2);
				shiftLine = (shiftLine < docMan.getLineCount()) ? shiftLine : docMan.getLineCount();
			}
			
			line = eline = y;
			column = ecolumn = 0;
			pix = epix = EDGE;
			
			if (shiftVert(shiftLine))
				paint = true;
			
			cursorAdjust();
			
			// if (shiftHoriz(pix)) -- this left it shifted to the right
			if (shiftHoriz(0))
				paint = true;
			
			if (paint)
				repaint();
			
		}
	}
	
    private void setNy() {
		updateFonts(null);
		dimension = getSize();
		ny = dimension.height / fontHeight;
	}
	
	public int getLine()
	{
		return line + 1;	// line is zero based
	}

	public void legalizeCursor()
	{
		String whole;
		boolean illegal = false;
		int max_line;
		int max_column;

		max_line = docMan.getLineCount();

		if (line >= max_line)
		{
			line = max_line - 1;
			illegal = true;
		}
		
		whole = docMan.getLine(line);
		max_column = whole.length();

		if (column > max_column)
		{
			column = max_column;
			illegal = true;
		}

		if (eactive && (eline >= max_line))
		{
			illegal = true;
		}

		if (eactive && !illegal)
		{
			whole = detabbed(docMan.getLine(eline));
			max_column = whole.length();

			if (ecolumn > max_column)
			{
				illegal = true;
			}
		}

		if (line < 0)
			line = 0;

		if (column < 0)
			column = 0;

		if (illegal)
		{
			hactive = eactive = false;
			pix = epix = ruler.length(line,column) + EDGE;
			shiftVert(line);
			shiftHoriz(pix);
			repaint();
		}
	}

	protected void updateFonts(Graphics g)
	{
		boolean foobar = false;
		
		if (g == null)
		{
			g = getGraphics();
			foobar = true;
		}
		
		if (g != null)
		{
			g.setFont(font);
			fontMetrics = g.getFontMetrics(font);
			fontHeight = fontMetrics.getHeight();
			fontDescent = fontMetrics.getDescent();

			ruler.setFontMetrics(fontMetrics);
			
			if (foobar)
				g.dispose();
		}
	}


	public void addSearchPattern( String patt )
	{
		mpEdit.addSearchPattern(patt);
	}

	public String[] getSearchPatterns()
	{
		return mpEdit.getSearchPatterns();
	}

	public String getLatestSearchPattern()
	{
		return mpEdit.getLatestSearchPattern();
	}

	public void addReplacePattern( String patt )
	{
		mpEdit.addReplacePattern(patt);
	}

	public String[] getReplacePatterns()
	{
		return mpEdit.getReplacePatterns();
	}

	public String getLatestReplacePattern()
	{
		return mpEdit.getLatestReplacePattern();
	}

	public void findAgain(int direction)
	{
		String lastFind = getLatestSearchPattern();

		if ( lastFind == null )
			release_cursor();
		else
			find(lastFind, direction, lastRegex, lastMatchCase);
	}

	boolean lastRegex = false;
	boolean lastMatchCase = false;
	
	public boolean getLastRegex()
	{
		return lastRegex;
	}

	public boolean getLastMatchCase()
	{
		return lastMatchCase;
	}

	public boolean find( String pattern, int direction, boolean regex, boolean matchCase )
	{
		int i,max,ct,start,end,from;
		String whole;

		pause_cursor();

		lastRegex = regex;
		lastMatchCase = matchCase;

		max = docMan.getLineCount();
		i = line;
		ct = 0;

		while (ct++ <= max)
		{
			whole = docMan.getLine(i);
			
			if (regex)
			{
				Pattern pat;
				//MatchResult result;

				from = 0;

				if (( i == line ) && ( ct == 1 ))
				{
					if (eactive)
					{
						if ((i == eline) && (direction > 0))
						{
							from = ecolumn;
						}
						else
						{
							from = column + direction;
							// this should be merged with code at end
							if ( from < 0 )
							{
								from = 0;

								if ( direction > 0 )
									i++;
								else
									i--;
								
								if (i >= max)
								{
									i = 0;
									textFrame.setMessage("Search wrapped");
								}
								else if ( i < 0 )
								{
									i = max - 1;
									textFrame.setMessage("Search wrapped");
								}
								continue;
							}
						}
					}
					else
						from = column;
					if (direction > 0)
						whole = whole.substring(from);
					else
						whole = whole.substring(0,from);
				}				

				// Create Perl5Compiler and Perl5Matcher instances 1st time only
				//if (compiler == null)
				//	compiler = new Perl5Compiler();
				//if (matcher == null)
				//	matcher  = new Perl5Matcher();
				
				// Attempt to compile the pattern.  If the pattern is not valid,
				// report the error and exit.
				if (matcher == null){
					try
					{
						pat = Pattern.compile(pattern);
						matcher = pat.matcher(whole);
					}
					catch(Exception e)
					{
						textFrame.setMessage("Bad regex pattern");
						release_cursor();
						return false;
					}
				}
				
				start = -1;
				end = 0;
				
				if (matcher.find()) {
					// Fetch match that was found.
					String result = matcher.group();  
					start = matcher.start();
					end = matcher.end();
					if ((direction > 0) && (from != 0))
					{
						start += from;
						end += from;
					}
				}
			}
			else
			{
				if (( i == line ) && ( ct == 1 ))
				{
					if (eactive)
						from = column + direction;
					else
						from = column;
				}
				else
					from = (direction > 0) ? 0 : whole.length();
				if (!matchCase)
				{
					whole = whole.toUpperCase();
					pattern = pattern.toUpperCase();
				}
				if ( direction > 0 )
					start = whole.indexOf(pattern,from);
				else
					start = whole.lastIndexOf(pattern,from);
				end = start + pattern.length();
			}
			
			if (start >= 0)
			{
				line = eline = i;
				column = start;
				pix = ruler.length(line,column) + EDGE;
				ecolumn = end;
				epix = ruler.length(eline,ecolumn) + EDGE;
				eactive = true;
				setup_h();
				save_h();
				updateCopyActions(eactive);
				shiftVert(line);
				shiftHoriz(pix);
				shiftHoriz(epix);
				repaint();
				return true;
			}
			
			if ( direction > 0 )
				i++;
			else
				i--;
			
			if (i >= max)
			{
				i = 0;
				textFrame.setMessage("Search wrapped");
			}
			else if ( i < 0 )
			{
				i = max - 1;
				textFrame.setMessage("Search wrapped");
			}
		}

		release_cursor();
		return false;
	}

	// Thanks to Ed McGlaughlin for cursor-select-* actions!
	// Ed's changes are tagged with //ecm ... the dead code
	// will probably be removed by v1.12

	public synchronized void mousePressed(MouseEvent e)
	{
	    // ecm
		// Graphics g;

		requestFocus();

		if (mouseDown)
			return;

		mouseDown = true;
		
		pause_cursor();
		// ecm
		// g = getGraphics();
		// updateFonts(g);

		clickPosition( e.getX(), e.getY() );

		if ((cline < 0) || (cline >= docMan.getLineCount()))
			return;

		// ecm -- split the function here
		startHighlight(e.getClickCount(), e.isShiftDown());
		
		// ecm -- had to pull this up from bottom half
		if (!e.isShiftDown())
    		addMouseMotionListener(this);
	}

	// ecm 
	// This is the bottom half of mousePressed
	protected void startHighlight(int clickCount, boolean isShiftDown)
	{
		// ecm -- these were copied to here from above
		Graphics g = getGraphics();
		updateFonts(g);

		eline = cline;
		ecolumn = ccolumn;
		epix = cpix;

		// ecm -- deleted int clickCount = e.getClickCount();

		if (clickCount == 3)
		{
			eline = line = cline;
			column = 0;
			pix = EDGE;
			String whole = docMan.getLine(cline);
			ecolumn = whole.length();
			epix = ruler.length(cline,10000000) + EDGE;
			eactive = true;
			setup_h();
			save_h();
			updateCopyActions(eactive);
			repaint();
		}
		else
		if (clickCount == 2)
		{
			char c;
			eline = line = cline;
			String whole = docMan.getLine(cline);
			column = ecolumn = ccolumn;
			while (true)
			{
				if (column == 0)
					break;

				if (!Character.isLetterOrDigit(whole.charAt(column-1)))
					break;

				column--;
			}
			pix = ruler.length(cline,column) + EDGE;
			int max = whole.length();
			while (true)
			{
				if (ecolumn >= max)
					break;

				if (!Character.isLetterOrDigit(whole.charAt(ecolumn)))
					break;

				ecolumn++;
			}
			epix = ruler.length(cline,ecolumn) + EDGE;
			eactive = true;
			setup_h();
			save_h();
			updateCopyActions(eactive);
			repaint();
		}
		else
		if (isShiftDown)	// ecm -- was e.isShiftDown()
		{
			eline = cline;
			ecolumn = ccolumn;
			epix = cpix;
			eactive = true;
			setup_h();
			save_h();
			updateCopyActions(eactive);
			repaint();
		}
		else
		{
			if (hactive)
				flip_h( g, oline, opix, oeline, oepix );
			eline = line = cline;
			ecolumn = column = ccolumn;
			epix = pix = cpix;
			hactive = false;
			eactive = true;
			updateCopyActions(eactive);
			// ecm -- move up to mousePressed
			// addMouseMotionListener(this);
		}
		// ecm -- add line number display
		textFrame.setLine(line);
		
		g.dispose();
	}
	
	protected int opix, oepix, oline, oeline;
	protected int hpix, hepix, hline, heline, hcolumn, hecolumn;
	protected int lastx,lasty;

	public synchronized void mouseDragged(MouseEvent e)
	{
		// ecm copy down below
		// Graphics g;
		
		// g = getGraphics();
		// updateFonts(g);
		
		if (e != null)
		{
			lastx = e.getX();
			lasty = e.getY();
		}

		clickPosition( lastx, lasty );

		// ecm -- split here
		moreHighlight();
	}


	// ecm -- split mouseDragged in half here
	protected void moreHighlight() {
		// ecm copied these down from above
		Graphics g = getGraphics();
		updateFonts(g);

		eline = cline;
		ecolumn = ccolumn;
		epix = cpix;

		eactive = ((eline != line) || (ecolumn != column));
		if (eactive || hactive)
		{
			setup_h();
			
			if ((eline < sy) || 
				(eline >= sy+ny) ||
				(epix < sx) ||
				(epix >= dimension.width))
			{
				if (textScroller == null)
				{
					textScroller = new TextScroller(this);
					textScroller.start();
				}
				shiftVert(eline);
				shiftHoriz(epix);
				setup_h();
				save_h();
				docMan.extendHilite(sy+ny-1);
				for (int i = 0; i < ny; i++)
					drawLine(g,sy+i);
			}
			else
			{
				if (textScroller != null)
				{
					textScroller.stop();
					textScroller = null;
				}
				if (hactive)
				{
					if ((hline < oline) || ((hline == oline) && (hpix < opix)))
						flip_h( g, hline, hpix, oline, opix );
					if ((hline > oline) || ((hline == oline) && (hpix > opix)))
						flip_h( g, oline, opix, hline, hpix );
					if ((heline < oeline) || ((heline == oeline) && (hepix < oepix)))
						flip_h( g, heline, hepix, oeline, oepix );
					if ((heline > oeline) || ((heline == oeline) && (hepix > oepix)))
						flip_h( g, oeline, oepix, heline, hepix );
				}
				else
					flip_h( g, hline, hpix, heline, hepix );
				
				hactive = eactive;
				save_h();
			}
		}	
		// ecm -- add line number display
		textFrame.setLine(line);
		g.dispose();
	}

	protected int cline, ccolumn, cpix;

	void clickPosition( int x, int y )
	{
		cline = (y / fontHeight) + sy;

		if (cline < 0)
		{
			cline = ccolumn = cpix = 0;
			return;
		}

		if (cline >= docMan.getLineCount())
		{
			cline = docMan.getLineCount() - 1;
			String whole = docMan.getLine(cline);
			ccolumn = whole.length();
			cpix = ruler.length(cline,10000000) + EDGE;
			return;
		}

		x = x + sx - EDGE;

		ccolumn = cpix = 0;

		if (x > 0)
		{
			TextPosition tp = ruler.position(cline,x);
			ccolumn = tp.getColumn();
			cpix = tp.getPix() + EDGE;
		}
	}

	protected void flip_h(Graphics g, int sline, int spix, int eline, int epix)
	{
		int i;
		int bx,ex,by;
		
		g.setColor(Color.black);
		g.setXORMode(Color.white);
		
		if (eline >= sy + ny)
		{
			eline = sy + ny - 1;
			epix = 5000;
		}
	
		for (i = sline; i <= eline; i++)
		{
			by = (i - sy) * fontHeight;
			bx = 0;
			ex = 5000;
			if (i == sline)
			{
				bx = spix - sx;
			}
			if (i == eline)
			{
				ex = epix - sx;
				if (sline == eline)
					ex -= bx;
			}
			g.fillRect(bx,by,ex,fontHeight);
		}
	}
		
	protected void setup_h()
	{
		if ((line < eline) || ((line == eline) && (column <= ecolumn)))
		{
			hline = line;
			hcolumn = column;
			hpix = pix;
			heline = eline;
			hecolumn = ecolumn;
			hepix = epix;
		}
		else
		{
			hline = eline;
			hcolumn = ecolumn;
			hpix = epix;
			heline = line;
			hecolumn = column;
			hepix = pix;
		}
	}

	protected void save_h()
	{
		opix = hpix;
		oepix = hepix;
		oline = hline;
		oeline = heline;
		hactive = eactive;
	}

	// it is still fooled by comments and literals because LineInfo
	// works with display columns and here we have logical columns
	// I can detab every line, but it is not worth it - I'll wait until
	// everything will move to virtual spaces
	public void findMatchingBrace( int direction )
	{
		String tmplineStr = docMan.getLine(line);
		int tmpcolumn = column;
		int tmpline = line;
		boolean bracesearch = true;

		if ( column  >= tmplineStr.length() )
			return;

		char atchar = tmplineStr.charAt(column);
		char nestchar = atchar;
		char opposite;
		switch (atchar)
		{
			case '[': opposite = ']'; break;
			case ']': opposite = '['; break;
			case '(': opposite = ')'; break;
			case ')': opposite = '('; break;
			case '{': opposite = '}'; break;
			case '}': opposite = '{'; break;
			default: 
				opposite = atchar;
				nestchar = 0;
				bracesearch = false;
				break;
		}

		// seek braces in correct direction. not in provided one
		if ( bracesearch )
		{
			if ( (nestchar == '(') || (nestchar == '{') || (nestchar == '[') )
				direction = 1;
			else
				direction = -1;
		}

		int nest = 1;

		if ( direction < 0 ) // left
		{
			while(true)
			{
				tmpcolumn--;
				if ( tmpcolumn < 0 )
				{
					tmpline--;
					if ( tmpline < 0 )
						return;

					tmplineStr = docMan.getLine(tmpline);
					tmpcolumn = tmplineStr.length() - 1;
					if (tmpcolumn < 0)
					{
						textFrame.setMessage(strings.getString("UnbalancedCharacter"));
 						return;
					}
				}

				atchar = tmplineStr.charAt(tmpcolumn);
				if ( atchar == opposite && 
					(!bracesearch || docMan.getLineInfo(tmpline).isFreeStanding(tmpcolumn)) )
				{
					nest--;
					if ( nest == 0 )
					{
						line = tmpline;
						column = tmpcolumn;
						cursorAdjust();
						return;
					}
				}
				else if ( atchar == nestchar && 
						 (docMan.getLineInfo(tmpline).isFreeStanding(tmpcolumn)) )
				{
					nest++;
				}
			}
		}
		else // right
		{
			while(true)
			{
				tmpcolumn++;
				if ( tmpcolumn >= tmplineStr.length() )
				{
					tmpline++;
					if ( tmpline >= docMan.getLineCount() )
					{
						textFrame.setMessage(strings.getString("UnbalancedCharacter"));
 						return;
					}

					tmplineStr = docMan.getLine(tmpline);
					if ( tmplineStr.length() == 0 )
						continue;
					tmpcolumn = 0;
				}

				atchar = tmplineStr.charAt(tmpcolumn);
				if ( atchar == opposite &&
					(!bracesearch || docMan.getLineInfo(tmpline).isFreeStanding(tmpcolumn)) )
				{
					nest--;
					if ( nest == 0 )
					{
						line = tmpline;
						column = tmpcolumn;
						cursorAdjust();
						return;
					}
				}
				else if ( atchar == nestchar &&
						 (docMan.getLineInfo(tmpline).isFreeStanding(tmpcolumn)) )
				{
					nest++;
				}
			}
		}

	}

	public synchronized void mouseReleased(MouseEvent e)
	{
		if (!mouseDown)
			return;

		mouseDown = false;
		
		if (textScroller != null)
		{
			textScroller.stop();
			textScroller = null;
		}
		removeMouseMotionListener(this);
		eactive = ((eline != line) || (ecolumn != column));
		release_cursor();
		updateCopyActions(eactive);
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
    public void keyPressed(KeyEvent e)
	{
		int		keyModifiers = e.getModifiers();
		int		keyCode = e.getKeyCode();
		char	keyChar = e.getKeyChar();
		int		max;
		String	s;

		if (!gotFocus)
			return;

		s = mpEdit.getKeyAction(keyModifiers,keyCode);

		if (s != null)
		{
			MpAction act = (MpAction)actionDictionary.get(s);

			if ( act != null )
			{
				if ( recordingMacro )
					macro.addElement(act);

				e.consume();
				if ( !act.isEnabled() )
					return;
				pause_cursor();

				if (s.indexOf("dialog") >= 0)	// use a thread for dialogs
				{
					ButtonPusher bp = new ButtonPusher(act);
					bp.start();
				}
				else
				{
					act.actionPerformed(null);
				}
				release_cursor();
        		// ecm -- add line number display
        		textFrame.setLine(line);
				return;
			}
			else
			{
				// action which has a keybinding but isn't defined for this view
				// silently ignore
				// System.out.println("Unimplemented keybind hit");
				return;
			}
		}

		if (e.isMetaDown() || e.isControlDown() || e.isAltDown() )
			return;
		
		if ( docMan.isReadOnly() )
			return;

		pause_cursor();

		// default character insert
		if (keyChar != KeyEvent.CHAR_UNDEFINED)
		{
			if (keyCode != KeyEvent.VK_ESCAPE &&
				keyCode != KeyEvent.VK_SHIFT &&
				keyChar != '\n' &&
				keyChar != '\b')
			{
				if (recordingMacro)
					macro.addElement(new CharInsertAction(strings,keyChar));

				e.consume();
				if (hactive)
					copy(true,false);
				column++;
				docMan.insert_char(line,column-1,keyChar);
				 // JJ 1998.01.03  - "pix" must be updated with "column"		
				pix = ruler.length(line,column) + EDGE;
				shiftHoriz(pix);
        		// ecm -- add line number display
        		textFrame.setLine(line);
				return;
			}
		}
		release_cursor();
	}
	
	public void paste(String s)
	{
		int oldline;
		int oldcolumn;
		TextPosition tp;
		boolean paint;
		
		pause_cursor();

		if (hactive)
			copy(true,false);

		oldline = line;
		oldcolumn = column;

		tp = docMan.insert_section(line,column,s,false);

		line = tp.line;
		column = tp.column;
		ruler.invalidate(oldline,line);
		pix = ruler.length(line,column) + EDGE;

		docMan.updateFrames(oldline,line);

		paint = oldline != line;

		paint = false;

		if (shiftVert(line))
			paint = true;

		if (shiftHoriz(pix))
			paint = true;

		if (paint)
			repaint();

	}

	public String copy(boolean cut, boolean visible)
	{
		String s;
		int oldline;
		int oldcolumn;
		boolean paint = false;

		if (!hactive)
			return null;

		if (hline == heline)
		{
			oldline = hline;
			oldcolumn = hcolumn;
		}
		else
			paint = true;
		
		s = docMan.delete_section(hline, hcolumn, heline, hecolumn, cut);

		if (visible)
			docMan.updateFrames(hline,heline);

		if (cut)
		{
			line = hline;
			column = hcolumn;
			hactive = eactive = false;

			if (shiftVert(line))
				paint = true;

			pix = ruler.length(line,column) + EDGE;
			if (shiftHoriz(pix))
				paint = true;

			if (paint)
			{
				repaint();
			}
			else
			{
				if (visible)
					linesChanged(line,line);
			}
		}

		return s;
	}
	
	protected void cursorAdjust()
	{
		cursorAdjust(false);
	}

	protected void cursorAdjust(boolean force_paint)
	{
		int min, max;
		boolean paint = force_paint;

		if (eactive)
		{
			paint = true;
			hactive = eactive = false;
			updateCopyActions(eactive);
		}

		// straighten up lines first

		if (line < 0)
			line = 0;
		else
		{
			max = docMan.getLineCount();

			if (line >= max )
				line = max - 1;
		}

		// straighten up columns

		if (column < 0)
			column = 0;
		else
		{
			max = (docMan.getLine(line)).length();
			if (column > max)
				column = max;
		}

		// off page?

		if (shiftVert(line))
			paint = true;

		// JJ 1998.01.03  - "pix" must be updated with "column"
		
		pix = ruler.length(line,column) + EDGE;
		if (shiftHoriz(pix))
			paint = true;

		if (paint)
			repaint();
		else
			release_cursor();
	}

	protected boolean shiftVert( int line )
	{
		if (line < sy)
		{
			sy = line;
			if (sy < 0)
				sy = 0;
			vert.setValue(sy);
			return true;
		}

		if (line >= sy+ny)
		{
			sy = line - ny + 1;
			vert.setValue(sy);
			docMan.extendHilite(sy+ny-1);
			return true;
		}

		return false;
	}

	protected boolean shiftHoriz( int x )
	{
		if (x <= sx)
		{
			sx = x - (dimension.width / 5);

			if (sx < 0)
				sx = 0;

			horiz.setValue(sx);
			return true;
		}

		if (x >= sx + dimension.width)
		{
			sx = x - dimension.width + (dimension.width / 5);
			horiz.setValue(sx);
			return true;
		}

		return false;
	}

	public void componentHidden(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {}

	public void componentResized(ComponentEvent e)
	{
		redoControls(horiz.getValue(), vert.getValue(),true);
	}

	public void redoCanvas()
	{
		redoControls(horiz.getValue(), vert.getValue(),false);
	}

	protected void redoControls(int h, int v, boolean clear)
	{
		int maxline, maxpix, bubble;

		Graphics g = getGraphics();

		if (g == null)
			return;

		updateFonts(g);

		if (g != null)
		{
			dimension = getSize();

			if (clear)
				clear_area(g);

			maxpix = 6000;
			bubble = dimension.width;

			if ( bubble > maxpix)
				maxpix = bubble;

			horiz.setValues(h, bubble, 0, maxpix);

			maxline = docMan.getLineCount();

			if ( maxline <= 0 )
				maxline = 10;

			bubble = ny = dimension.height / fontHeight;

			docMan.extendHilite(sy+ny-1);

			if (bubble > maxline)
				bubble = maxline;

			if (v > maxline)
				v = maxline;

			vert.setValues(v, bubble, 0, maxline);
			repaint();

			g.dispose();
		}
	}

	protected void resizeLines()
	{
		if ((hline >= 0) && (hline < docMan.getLineCount()))
			opix = hpix = ruler.length(hline,hcolumn) + EDGE;

		if ((heline >= 0) && (heline < docMan.getLineCount()))
			oepix = hepix = ruler.length(heline,hecolumn) + EDGE;	
	}

	protected String detabbed(String s)
	{
		if (s.indexOf('\t') < 0)
			return s;

		char c;
		String t = new String("");
		int j=0;
		int tabs;
		int max = s.length();

		for (int i=0; i<max; i++)
		{
			c = s.charAt(i);
			if (c == '\t')
			{
				tabs = tabSize - (j % tabSize);
				j += tabs;
				while (tabs-- > 0) t = t + ' ';
			}
			else
			{
				t = t + c;
				j++;
			}
		}

		return t;
	}

	char buffer[];

	protected int fillBuffer(LineInfo li)
	{
		char c;
		int i,j,tabs,max;
		char before[];

		before = li.data.toCharArray();

		max = before.length;
		for (j=i=0; i<max; i++)
			if (before[i] == '\t')
				j++;

		if (j == 0)
		{
			buffer = before;
			j = max;
		}
		else
		{
			buffer = new char[max + (j * (tabSize-1))];

			for (j=i=0; i<max; i++)
			{
				c = before[i];
				if (c == '\t')
				{
					tabs = tabSize - (j % tabSize);
					while (tabs-- > 0) buffer[j++] = ' ';
				}
				else
				{
					buffer[j++] = c;
				}
			}
		}

		return j;
	}

	protected int charsLength(int start, int length)
	{
		if (fontMetrics == null)
			return -1;

		return fontMetrics.charsWidth(buffer,start,length);
	}

	public void getCursorPos(Graphics g, Rectangle r)
	{
		updateFonts(g);
		r.width = 2;
		r.height = fontHeight;
		r.y = (line - sy) * r.height;

		if (column > 0)
		{
			r.x = ruler.length(line,column) + EDGE - sx;
		}
		else
			r.x = EDGE - sx;
	}

	public String getSelectionOrWordUnderCursor()
	{
		String str = copy(false,false);
		if ( str != null )
			return str;
		int leftBound = column;
		int rightBound = column;
		String currLine = docMan.getLine(line);
		int rightMax = currLine.length();

leftB:	while ( (--leftBound >= 0) )
		{
			switch( currLine.charAt(leftBound) )
			{
				case ' ':
				case '\t':
				case '.':
				case '(':
				case ')':
				case '[':
				case ']':
				case '{':
				case '}':
				case ';':
					leftBound++;
					break leftB;				
			}
		}
		if ( leftBound < 0 )
			leftBound = 0;

rightB:	while ( rightBound < rightMax )
		{
			switch( currLine.charAt(rightBound) )
			{
				case ' ':
				case '\t':
				case '.':
				case '(':
				case ')':
				case '[':
				case ']':
				case '{':
				case '}':
				case ';':
					break rightB;
				default:
					rightBound++;
				
			}
		}

		if ( rightBound == 0 )
			return "";
		return currLine.substring(leftBound, rightBound);
	}

	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		int temp;
		boolean paint = false;

		pause_cursor();
		if (e.getSource() == horiz)
		{
			temp = e.getValue();
			if (temp != sx)
			{
				horiz.setValue(sx = temp);
				paint = true;
			}
		}
		else
		if (e.getSource() == vert)
		{
			temp = e.getValue();
			if (temp != sy)
			{
				vert.setValue(sy = temp);
				docMan.extendHilite(sy+ny-1);
				paint = true;
			}
		}

		if (paint)
			repaint();
		else
			release_cursor();
	}

	public void linesChanged(int first, int last)
	{
		if ((first > sy+ny) || (last < sy))
			return;

		if (first == last)
		{
			Graphics g = getGraphics();
			updateFonts(g);
			drawLine(g, first);
			g.dispose();
			release_cursor();
		}
		else
			repaint();
	}


	public void updateUndoActions( boolean un, boolean re )
	{
		undoAction.setEnabled(un);
		redoAction.setEnabled(re);
	}

	public void updateCopyActions( boolean active )
	{
		cutAction.setEnabled(active);
		copyAction.setEnabled(active);
	}

	public void update(Graphics g)
	{ 
		paint(g);
	}

	protected void getColors()
	{
		textColor = docMan.getTextColor();
		textXColor = docMan.getTextXColor();
		commentColor = docMan.getCommentColor();
		commentXColor = docMan.getCommentXColor();
		keywordColor = docMan.getKeywordColor();
		keywordXColor = docMan.getKeywordXColor();
		keyword2Color = docMan.getKeyword2Color();		// handy for VRML
		keyword2XColor = docMan.getKeyword2XColor();	// handy for VRML
		quoteColor = docMan.getQuoteColor();
		quoteXColor = docMan.getQuoteXColor();

	}

	public void paint(Graphics g)
	{ 
		if ( !isShowing() )	// why java tries to repaint window during file load ?
			return;

		int i,j,t;
		Rectangle clip;
		Rectangle curs = new Rectangle();
		
		pause_cursor();

		getColors();

		g.setPaintMode();
		updateFonts(g);

		i = docMan.getLineCount();
		if (oldlines != i)
		{
			oldlines = i;
			redoControls(horiz.getValue(), vert.getValue(),true);
		}
		
		if (dimension == null)
			dimension = getSize();

		clip = g.getClipBounds();

		i = clip.y / fontHeight;
		t = clip.y + clip.height;
		j = t / fontHeight;
		if ((t % fontHeight) == 0) j--;
		if (j >= ny) j = ny-1;

		linesEmpty = 0;
		lastVerticalSize = j;

		for (i = 0; i <= j; i++)
			drawLine(g,sy+i);

		release_cursor();
		// ecm -- add line number display
		textFrame.setLine(line);
	}
	
	static Color separatorInsideColor = new Color( 160, 0 , 0 );

	// This shouldn't be such hardcoded, but without Swing I'm a bit
	// helpless - Artur
	protected void drawLineSeparator( Graphics g, int y )
	{
		int center = y - fontHeight - 2;
		g.setColor(separatorInsideColor);
		g.drawLine( 3, center, dimension.width -12, center );
			g.setColor(Color.gray);
		g.drawLine(3, center, 6, center + 1);
		g.drawLine(3, center, 6, center - 1 );
		g.drawLine(6, center +1, dimension.width - 15, center + 1);
		g.drawLine(6, center -1, dimension.width - 15, center - 1);
		g.drawLine(dimension.width - 15, center +1, dimension.width -12, center);
		g.drawLine(dimension.width - 15, center -1, dimension.width -12, center);
	}
 
	protected synchronized void drawLine(Graphics g, int i)
	{
		int x,y,m;
		int bx,ex;
		int a,b,c;
		int max,key;
		String s;
		LineInfo hi;
		Rectangle curs = null;
		boolean xor = false;
				
		x = EDGE-sx;
		y = (i - sy + 1) * fontHeight;
		m = fontHeight;

		g.setColor(Color.black);
		g.setPaintMode();

		if (hactive && (i > oline) && (i < oeline))
		{
			g.fillRect(0,y-fontHeight,dimension.width,m);
			xor = true;
		}
		else
		{
			g.clearRect(0,y-fontHeight,dimension.width,m);
		}
		
		if (i >= docMan.getLineCount())
			return;
			
		hi = docMan.getLineInfo(i);
		max = fillBuffer(hi);
		a = 0;
		key = 0;

		if (hi.tagColor != null)
		{
			if (hactive && (i > oline) && (i < oeline))
				g.setColor(new Color(hi.tagColor.getRGB() ^ 0xffffff));
			else
				g.setColor(hi.tagColor);
			g.fillRect(0,y-fontHeight,dimension.width,m);
		}

		while (a < max) {
			if (key < hi.keyCt)
			{
				b = hi.keyStarts[key];
				c = hi.keyEnds[key];
			}
			else
			{
				b = max;
				c = max;
			}
			if (b > a)
			{
				if (xor)
					g.setColor(textXColor);
				else
					g.setColor(textColor);
				g.drawChars(buffer, a, b-a, x, y-fontDescent);
				x += charsLength(a, b-a);
			}
			if (c > b)
			{
				switch (hi.keyTypes[key]) {
				case Hilite.COMMENT:
					if (xor)
						g.setColor(commentXColor);
					else
						g.setColor(commentColor);
					break;
				case Hilite.KEYWORD:
					if (xor)
						g.setColor(keywordXColor);
					else
						g.setColor(keywordColor);
					break;
				case Hilite.KEYWORD2:
					if (xor)
						g.setColor(keyword2XColor);
					else
						g.setColor(keyword2Color);
					break;
				case Hilite.QUOTE:
					if (xor)
						g.setColor(quoteXColor);
					else
						g.setColor(quoteColor);
					break;
				}
				g.drawChars(buffer, b, c-b, x, y-fontDescent);
				x += charsLength(b, c-b);
			}
			a = c;
			key++;
		}

		if (doSeparator)		// Test code from Artur Biesiadowski
		{						// we might want to do more with this later - JJ
			if ( max == 0 )
				linesEmpty++;
			else
				linesEmpty = 0;

			if ( linesEmpty == 2 )
				drawLineSeparator( g, y );
		}

		if (hactive && ((i == oline) || (i == oeline)))
		{
			bx = 0;
			ex = 5000;
			if (i == oline)
			{
				bx = opix - sx;
			}
			if (i == oeline)
			{
				ex = oepix - sx;
				if (oline == oeline)
					ex -= bx;
			}
			g.setColor(Color.black);
			g.setXORMode(Color.white);
			g.fillRect(bx,y-fontHeight,ex,m);
		}
	}

	protected void addToDict( MpAction mpa )
	{
		actionDictionary.put(mpa.getIdString(), mpa );
	}

	public MpAction getMpAction( String id )
	{
		if (id.startsWith("ViewOwner-"))		// create a generic action
			return new AbstractMpAction(id) {
				public void actionPerformed( ActionEvent e ) {
					sendViewAction(idString);
				}
			};
		else
		if (id.startsWith("DocOwner-"))			// create a generic action
			return new AbstractMpAction(id) {
				public void actionPerformed( ActionEvent e ) {
					docMan.sendDocAction(idString);
				}
			};
		else
		if (id.startsWith("EditOwner-"))		// create a generic action
			return new AbstractMpAction(id) {
				public void actionPerformed( ActionEvent e ) {
					mpEdit.sendEditAction(idString);
				}
			};
		else
			return (MpAction) actionDictionary.get(id);
	}

	protected void createActionDictionary()
	{
		actionDictionary = new Hashtable();
		
		addToDict( undoAction = new
			AbstractMpAction("undo") {
				public void actionPerformed( ActionEvent e ) {
					docMan.undo(textFrame);
				}
			}
		);

		addToDict( redoAction = new 
			AbstractMpAction("redo") {
				public void actionPerformed(ActionEvent e ) {
					docMan.redo(textFrame);
				}
			}
		);

		addToDict( new 
			AbstractMpAction("line-swap") {
				public void actionPerformed( ActionEvent e ) {
					if ( docMan.getLineCount() - 1 == line )
						return;
					docMan.swap_lines(line, line+1);
					cursorAdjust(true);
				}
			}
		);
		
		addToDict( new
			AbstractMpAction("cursor-up") {
				public void actionPerformed( ActionEvent e ) {
					line--;
					cursorAdjust();
				}
			}
		);
		
		addToDict( new
			AbstractMpAction("cursor-down") {
				public void actionPerformed( ActionEvent e ) {
					line++;
					cursorAdjust();
				}
		}
		);
		
		addToDict( new
			AbstractMpAction("cursor-forward") {
				public void actionPerformed( ActionEvent e ) {
					column++;
					cursorAdjust();
				}
			}
		);
		
		addToDict( new
			AbstractMpAction("cursor-backward") {
				public void actionPerformed( ActionEvent e ) {
					column--;
					cursorAdjust();
				}
		}
		);
		
		addToDict( new
			AbstractMpAction("cursor-word-forward") {
				public void actionPerformed( ActionEvent e ) {
					_wordForward();
					cursorAdjust();
				}
			}
		);
		
		addToDict( new
			AbstractMpAction("cursor-word-backward") {
				public void actionPerformed( ActionEvent e ) {
					_wordBackward();
					cursorAdjust();
				}
		}
		);
		
		addToDict( new
			AbstractMpAction("cursor-line-begin") {
				public void actionPerformed( ActionEvent e ) {
					column = 0;
					cursorAdjust();
				}
			}
		);
		
		addToDict( new
			AbstractMpAction("cursor-line-end") {
				public void actionPerformed( ActionEvent e ) {
					column = docMan.getLine(line).length();
					cursorAdjust();
				}
		}
		);
		
		addToDict( new
			AbstractMpAction("cursor-page-begin") {
				public void actionPerformed( ActionEvent e ) {
					line = sy;
					cursorAdjust();
				}
			}
		);
		
		addToDict( new
			AbstractMpAction("cursor-page-end") {
				public void actionPerformed( ActionEvent e ) {
					line = Math.min( docMan.getLineCount() -1, sy + ny -1 );
					cursorAdjust();
				}
		}
		);
		
		addToDict( new
			AbstractMpAction("cursor-document-begin") {
				public void actionPerformed( ActionEvent e ) {
					line = 0;
					cursorAdjust();
				}
			}
		);
		
		addToDict( new
			AbstractMpAction("cursor-document-end") {
				public void actionPerformed( ActionEvent e ) {
					line = docMan.getLineCount() - 1;
					cursorAdjust();
				}
			}
		);
		
		addToDict( new
			AbstractMpAction("page-up") {
				public void actionPerformed( ActionEvent e ) {
					int offset = line - sy;
					line -= lastVerticalSize;
					shiftVert(Math.max(line-offset,0));
					cursorAdjust(true);
				}
		}
		);
		
		addToDict( new
			AbstractMpAction("page-down") {
				public void actionPerformed( ActionEvent e ) {
					int offset = line - sy;
					line += lastVerticalSize;
					shiftVert(Math.min(line-offset+ny-1, docMan.getLineCount() -1 ));
					cursorAdjust(true);
				}
			}
		);
		
		addToDict( new
			AbstractMpAction("find-next-forward") {
				public void actionPerformed( ActionEvent e ) {
					findAgain(1);
				}
		}
		);
		
		addToDict( new
			AbstractMpAction("find-next-backward") {
				public void actionPerformed( ActionEvent e ) {
					findAgain(-1);
				}
			}
		);
		
		addToDict( new
			AbstractMpAction("brace-match-forward") {
				public void actionPerformed( ActionEvent e ) {
					findMatchingBrace(1);
				}
		}
		);
		
		addToDict( new
			AbstractMpAction("brace-match-backward") {
				public void actionPerformed( ActionEvent e ) {
					findMatchingBrace(-1);
				}
			}
		);
		
		addToDict( new
			AbstractMpAction("character-delete-forward") {
				public void actionPerformed( ActionEvent e ) {
					_pressedDelete();
				}
		}
		);
		
		addToDict( new
			AbstractMpAction("character-delete-backward") {
				public void actionPerformed( ActionEvent e ) {
					_pressedBackspace();
				}
			}
		);
		
		addToDict( new
			AbstractMpAction("line-break") {
				public void actionPerformed( ActionEvent e ) {
					_insertNewline();
				}
		}
		);
		
		addToDict( new
			AbstractMpAction("line-clone") {
				public void actionPerformed(ActionEvent e ) {
					docMan.insert_line( line, docMan.getLine(line));
					line++;
					cursorAdjust(true);
				}
			}
		);
		
		addToDict( new
			AbstractMpAction("line-delete") {
				public void actionPerformed(ActionEvent e ) {
					_deleteLine();
				}
		}
		);
		
		addToDict( new
			AbstractMpAction("document-save") {
				public void actionPerformed( ActionEvent e ) {
					docMan.fileSave(textFrame);
				}
			}
		);
		
		addToDict( new
			AbstractMpAction("frame-close") {
				public void actionPerformed( ActionEvent e ) {
					docMan.closeFrame(textFrame);
				}
		}
		);
		
		addToDict( new
			AbstractMpAction("mode-autoindent-toggle") {
				public void actionPerformed( ActionEvent e ) {
					autoIndent = !autoIndent;
					textFrame.setMessage("Autindentation turned " + (autoIndent ? "ON" : "OFF"));
				}
			}
		);

		addToDict( new
			AbstractMpAction("document-new") {
				public void actionPerformed( ActionEvent e ) {
					mpEdit.newDoc();
				}
			}
		);

		addToDict( new
			AbstractMpAction("document-open-dialog") {
				public void actionPerformed( ActionEvent e ) {
					mpEdit.openDocDialog(docMan,textFrame);
				}
			}
		);

		addToDict( new
			AbstractMpAction("document-save-as-dialog") {
				public void actionPerformed( ActionEvent e ) {
					docMan.fileSaveAs(textFrame);
				}
			}
		);

		addToDict( new
			AbstractMpAction("document-print-dialog") {
				public void actionPerformed( ActionEvent e ) {
					docMan.filePrint(textFrame);
				}
			}
		);

		addToDict( new
			AbstractMpAction("find-dialog") {
				public void actionPerformed( ActionEvent e ) {
					String search = getSelectionOrWordUnderCursor();
					if ( !search.equals("") )
						addSearchPattern(search);
					String[] str = getSearchPatterns();
// Test code
//					System.out.println("-----------");
//					for ( int i = 0; i < str.length; i++)
//						System.out.println(str[i]);
// Test code
					FindDialog findDialog;
					findDialog = new FindDialog(textFrame,
										textCanvas,
										strings,
										strings.getString("DialogFind"));
					findDialog.show();
				}
			}
		);

		addToDict( new
			AbstractMpAction("goto-dialog") {
				public void actionPerformed( ActionEvent e ) {
					GotoDialog gotoDialog;
					gotoDialog = new GotoDialog(textFrame,
										strings,
										strings.getString("DialogGoto"));
					gotoDialog.show();
				}
			}
		);

		addToDict( new
			AbstractMpAction("replace-dialog") {
				public void actionPerformed( ActionEvent e ) {
					ReplaceDialog replaceDialog;
					replaceDialog = new ReplaceDialog(textFrame,
											  textCanvas,
											  strings,
											  strings.getString("DialogReplace"));
					replaceDialog.show();
				}
			}
		);
	
		addToDict( copyAction = new
			AbstractMpAction("selection-copy") {
				public void actionPerformed( ActionEvent e ) {
					String srcData = copy(false,false);
					if (srcData != null)
					{
			            StringSelection contents = new StringSelection(srcData);
			            clipboard.setContents(contents, textCanvas);
					}
				}
			}
		);

		addToDict( cutAction = new
			AbstractMpAction("selection-cut") {
				public void actionPerformed( ActionEvent e ) {
					String srcData = copy(true,true);
					if (srcData != null)
					{
			            StringSelection contents = new StringSelection(srcData);
			            clipboard.setContents(contents, textCanvas);
					}
				}
			}
		);

		addToDict( new
			AbstractMpAction("buffer-paste") {
				public void actionPerformed( ActionEvent e ) {
					String dstData;
			        Transferable content = clipboard.getContents(textMenu);
			        if (content != null)
					{
			            try
						{
			                dstData = (String)content.getTransferData(DataFlavor.stringFlavor);
			            }
						catch (Exception exc)
						{
			                System.out.println("Could not read clipboard"); 
							return;
			            }
						paste(dstData);
			    	}
				}
			}
		);

		addToDict( new
			AbstractMpAction("properties-dialog") {
				public void actionPerformed( ActionEvent e ) {
					PropDialog pd = new PropDialog(textFrame,strings,docMan,strings.getString("DialogProps"));
					pd.show();
				}
			}
		);

		addToDict( new
			AbstractMpAction("frame-clone") {
				public void actionPerformed( ActionEvent e ) {
					Dimension size = mpEdit.getWindowSize();
					Point place = mpEdit.getPlace(size);
					TextFrame textFrame = docMan.newFrame(place,size);
					docMan.setTitles();
					textFrame.setVisible(true);
				}
			}
		);

		addToDict( new
			AbstractMpAction("help-about-dialog") {
				public void actionPerformed( ActionEvent e ) {
					AboutDialog ab = new AboutDialog(textFrame,strings,strings.getString("DialogAbout"));
					ab.show();
				}
			}
		);

		addToDict( new
			AbstractMpAction("mode-readonly-toggle") {
				public void actionPerformed( ActionEvent e ) {
					docMan.setReadOnly(!docMan.isReadOnly());
				}
			}
		);

		addToDict( new
			AbstractMpAction("selection-indent") {
				public void actionPerformed( ActionEvent e ) {
					// maybe allow something else instead of tab ?
					// this should have its own undo routine
					if ( !hactive )
						return;
					int max = heline;
					if (hecolumn == 0)	// full line selections include column 0 from next line
						max--;			// so adjust maximum
					for (int i = hline; i <= max; i++ )
					{
						docMan.insert_char(i, 0, '\t');
					}
				}
			}
		);
						
		addToDict( new
			AbstractMpAction("selection-unindent") {
				public void actionPerformed( ActionEvent e ) {
					if (!hactive)
						return;
					int max = heline;
					if (hecolumn == 0)	// full line selections include column 0 from next line
						max--;			// so adjust maximum
					for (int  i = hline; i <= max; i++ )
					{
						String line = docMan.getLine(i);
						if (line.length() > 0)
						{
							if (line.charAt(0) == '\t')		// bottom out, but no further
								docMan.delete_char(i, 0);
							else
							{
								int t = tabSize;
								int j = 0;
								// eat a tabs worth of spaces
								while ((line.charAt(j++) == ' ') && (t-- > 0))
									docMan.delete_char(i, 0);
							}
						}
					}
				}
			}
		);

		addToDict( new
			AbstractMpAction("anchor-drop") {
				public void actionPerformed( ActionEvent e ) {
					anchors.addElement(docMan.tagLine(line));
					currentAnchor = anchors.size()-1;
					textFrame.setMessage("Anchor #" + currentAnchor + " dropped");
				}
			}
		);

		addToDict( new
			AbstractMpAction("anchor-goto-last") {
				public void actionPerformed( ActionEvent e ) {
					int nanchor = anchors.size();
					if ( nanchor == 0 )
					{
						textFrame.setMessage(strings.getString("NoAnchors"));
						return;
					}
					TagLine tag;
					int tagline;
					if ( currentAnchor < nanchor )
					{
						tag = (TagLine)anchors.elementAt(currentAnchor);
						tagline = docMan.lineFromTag(tag);
						if ( tagline == line )
						{
							nanchor = currentAnchor;
						}
					}

					while ( true )
					{
						nanchor--;
						if ( nanchor < 0 )
						{
							textFrame.setMessage(strings.getString("NoPreviousAnchor"));
							return;
						}
						tag = (TagLine)anchors.elementAt(nanchor);
						tagline = docMan.lineFromTag(tag);
						if ( tagline >= 0 )
						{
							currentAnchor = nanchor;
							Goto(tagline+1);
							textFrame.setMessage("Jumped to anchor #" + currentAnchor);
							return;
						}

						textFrame.setMessage(strings.getString("InvalidAnchor"));
						anchors.removeElementAt(nanchor);
					}
				}
			}
		);
	

		// ==============================================================
		// ecm
		// --------------------------------------------------------------
		//	+	cursor-select-forward
		addToDict( new
			AbstractMpAction("cursor-select-forward") {
				public void actionPerformed( ActionEvent e ) {
					if (!eactive) {
						cline = line;
						ccolumn = column;
						cpix = ruler.length(cline, ccolumn) + EDGE;
						startHighlight(1, false);
					} 
					if (ecolumn < docMan.getLine(eline).length()) {
						ccolumn = ecolumn+1;
						cline = eline;
					} else {
						ccolumn = 0;
						int lastLine = docMan.getLineCount()-1;
						cline = (eline < lastLine) ? eline+1 : lastLine;
					}
					cpix = ruler.length(cline, ccolumn) + EDGE;
					moreHighlight();
				}
		}
		);
		// --------------------------------------------------------------
		//	+	cursor-select-backward
		addToDict( new
			AbstractMpAction("cursor-select-backward") {
				public void actionPerformed( ActionEvent e ) {
					if (!eactive) {
						cline = line;
						ccolumn = column;
						cpix = ruler.length(cline, ccolumn) + EDGE;
						startHighlight(1, false);
					} 
					if (ecolumn > 0) {
						ccolumn = ecolumn-1;
						cline = eline;
					} else {
						cline = (eline > 0) ? eline-1 : 0;
						ccolumn = docMan.getLine(cline).length();
					}
					cpix = ruler.length(cline, ccolumn) + EDGE;
					moreHighlight();
				}
		}
		);
		// --------------------------------------------------------------
		//	+	cursor-select-up
		addToDict( new
			AbstractMpAction("cursor-select-up") {
				public void actionPerformed( ActionEvent e ) {
					if (!eactive) {
						cline = line;
						ccolumn = column;
						cpix = ruler.length(cline, ccolumn) + EDGE;
						startHighlight(1, false);
					} 
					cline = (eline > 0) ? eline-1 : 0;
					ccolumn = ecolumn;
					cpix = ruler.length(cline, ccolumn) + EDGE;
					moreHighlight();
				}
		}
		);
		// --------------------------------------------------------------
		//	+	cursor-select-down
		addToDict( new
			AbstractMpAction("cursor-select-down") {
				public void actionPerformed( ActionEvent e ) {
					if (!eactive) {
						cline = line;
						ccolumn = column;
						cpix = ruler.length(cline, ccolumn) + EDGE;
						startHighlight(1, false);
					}
					int lastLine = docMan.getLineCount()-1;
					cline = (eline < lastLine) ? eline+1 : lastLine;
					ccolumn = ecolumn;
					cpix = ruler.length(cline, ccolumn) + EDGE;
					moreHighlight();
				}
		}
		);
		// --------------------------------------------------------------
		//	+	cursor-select-word-backward
		addToDict( new
			AbstractMpAction("cursor-select-word-backward") {
				public void actionPerformed( ActionEvent e ) {
        			String whole = null;
					if (!eactive) {
            			cline = line;
        			    ccolumn = column;
            			whole = docMan.getLine(cline);
            			int max = whole.length();
            			while (true)
            			{
            				if (column >= max)
            					break;

            				if (!Character.isLetterOrDigit(whole.charAt(column)))
            					break;

            				column++;
            			}
            			cpix = ruler.length(line, column) + EDGE;
                        startHighlight(1, false);
                    }


					if (ecolumn > 0) {
						ccolumn = ecolumn-1;
						cline = eline;
					} else {
						cline = (eline > 0) ? eline-1 : 0;
						ccolumn = docMan.getLine(cline).length();
					}
        			whole = docMan.getLine(cline);
        			while (true)
        			{
                        if (ccolumn == 0)
        					break;

        				if (!Character.isLetterOrDigit(whole.charAt(ccolumn-1)))
        					break;

        				ccolumn--;
        			}
        			cpix = ruler.length(cline, ccolumn) + EDGE;
        			moreHighlight();
				}
		}
		);
		// --------------------------------------------------------------
		//	+	cursor-select-word-forward
		addToDict( new
			AbstractMpAction("cursor-select-word-forward") {
				public void actionPerformed( ActionEvent e ) {
        			String whole = null;
					if (!eactive) {
            			cline = line;
        			    ccolumn = column;
            			whole = docMan.getLine(cline);
            			while (true)
            			{
            				if (column == 0)
            					break;

            				if (!Character.isLetterOrDigit(whole.charAt(column-1)))
            					break;

            				column--;
            			}
            			cpix = ruler.length(line, column) + EDGE;
                        startHighlight(1, false);
                    }

					if (ecolumn < docMan.getLine(eline).length()) {
						ccolumn = ecolumn+1;
						cline = eline;
					} else {
						ccolumn = 0;
						int lastLine = docMan.getLineCount()-1;
						cline = (eline < lastLine) ? eline+1 : lastLine;
					}
        			whole = docMan.getLine(cline);
        			int max = whole.length();
        			while (true)
        			{
        				if (ccolumn >= max)
        					break;

        				if (!Character.isLetterOrDigit(whole.charAt(ccolumn)))
        					break;

        				ccolumn++;
        			}
        			cpix = ruler.length(cline, ccolumn) + EDGE;
        			moreHighlight();
				}
		}
		);
		// --------------------------------------------------------------
		//	+	cursor-select-document-begin
		addToDict( new
			AbstractMpAction("cursor-select-document-begin") {
				public void actionPerformed( ActionEvent e ) {
					if (!eactive) {
						cline = line;
						ccolumn = column;
						cpix = ruler.length(cline, ccolumn) + EDGE;
						startHighlight(1, false);
					} 
					ccolumn = 0;
					cline = 0;
					cpix = ruler.length(cline, ccolumn) + EDGE;
					moreHighlight();
				}
		}
		);
		// --------------------------------------------------------------
		//	+	cursor-select-document-end
		addToDict( new
			AbstractMpAction("cursor-select-document-end") {
				public void actionPerformed( ActionEvent e ) {
					if (!eactive) {
						cline = line;
						ccolumn = column;
						cpix = ruler.length(cline, ccolumn) + EDGE;
						startHighlight(1, false);
					} 
                    cline = docMan.getLineCount()-1;
                    ccolumn = docMan.getLine(eline).length();
					cpix = ruler.length(cline, ccolumn) + EDGE;
					moreHighlight();
				}
		}
		);
		// --------------------------------------------------------------
		//	+	cursor-select-line-begin
		addToDict( new
			AbstractMpAction("cursor-select-line-begin") {
				public void actionPerformed( ActionEvent e ) {
					if (!eactive) {
						cline = line;
						ccolumn = column;
						cpix = ruler.length(cline, ccolumn) + EDGE;
						startHighlight(1, false);
					} 
                    cline = eline;
                    ccolumn = 0;
					cpix = ruler.length(cline, ccolumn) + EDGE;
					moreHighlight();
				}
		}
		);
		// --------------------------------------------------------------
		//	+	cursor-select-line-end
		addToDict( new
			AbstractMpAction("cursor-select-line-end") {
				public void actionPerformed( ActionEvent e ) {
					if (!eactive) {
						cline = line;
						ccolumn = column;
						cpix = ruler.length(cline, ccolumn) + EDGE;
						startHighlight(1, false);
					} 
                    cline = eline;
                    ccolumn = docMan.getLine(eline).length();
					cpix = ruler.length(cline, ccolumn) + EDGE;
					moreHighlight();
				}
		}
		);
		// end ecm
		// ==============================================================
		
		addToDict( new
			AbstractMpAction("macro-record-toggle") {
				public void actionPerformed( ActionEvent e ) {
					if (recordingMacro)
					{
						recordingMacro = false;
						setStatusMessage(strings.getString("RecordedMacro"));
						if ( macro.size() > 0 )
						{
							MpAction act = (MpAction)macro.lastElement();
							if (act == this)
							{
								macro.removeElementAt(macro.size()-1);
							}
						}
					}
					else
					{
						if ( macro == null )
						{
							macro = new Vector();
						}
						else
						{
							macro.removeAllElements();
						}
						recordingMacro = true;
						setStatusMessage(strings.getString("StartedRecordingMacro"));
					}
				}
			}
		);

		addToDict( new
			AbstractMpAction("macro-replay") {
				public void actionPerformed( ActionEvent e ) {
					if ( recordingMacro )
					{
						setStatusMessage(strings.getString("ReplayWhenRecording"));
						macro.removeElementAt(macro.size()-1);
					}
					else if ( macro == null )
					{
						setStatusMessage(strings.getString("NoMacro"));
					}
					else
					{
						pause_cursor();
						synchronized(macro)
						{
							int i;
							int size = macro.size();
							for ( i =0; i < size; i++ )
							{
								// this do not include all dialog stuff
								// and there is no easy way to solve that
								((MpAction)macro.elementAt(i)).actionPerformed(null);
							}
						}
						release_cursor();
					}
				}
			}
		);		
	}

/* Action template

		addToDict( new
			AbstractMpAction("") {
				public void actionPerformed( ActionEvent e ) {
				}
			}
		);
	
*/

	MpAction undoAction;
	MpAction redoAction;
	MpAction copyAction;
	MpAction cutAction;
	
	class CharInsertAction extends AbstractMpAction
	{
		char letter;

		public CharInsertAction(StringMan strings, char aLetter)
		{
			super("character-insert");
			letter = aLetter;
		}

		public void actionPerformed( ActionEvent evt )
		{
				if (hactive)
					copy(true,false);
				column++;
				docMan.insert_char(line,column-1,letter);
				// maybe we can omit following - it do not need to be done
				// for each letter from macro
				pix = ruler.length(line,column) + EDGE;
				shiftHoriz(pix);
        		textFrame.setLine(line);
		}

		public String getIdString()
		{
			return "character-insert-" + letter;
		}
	}


	void _deleteLine()
	{
		if ( docMan.getLineCount() == 1 )
		{
			docMan.clear_line(0);
			column = 0;
			pix = EDGE;
		}
		else if ( docMan.getLineCount()-1 == line )
		{
			docMan.clear_line(line);
			line--;
		}
		else
		{
			docMan.delete_line(line);
		}
		cursorAdjust(true);
	}
	
	
	void _wordForward()
	{
		boolean skippedWord = false;
		char [] buf = docMan.getLine(line).toCharArray();

		if (buf.length == 0)	// failed on empty lines
			return;

		while ( column < buf.length )
		{
			if ( (buf[column] != ' ') && (buf[column] != '\t') )
			{
				if ( skippedWord )
				{
					break;
				}
			}
			else
			{
				skippedWord = true;
			}
			column++;
		}
	}
	
	void _wordBackward()
	{
		boolean skippedSpace = false;
		char [] buf = docMan.getLine(line).toCharArray();

		if (buf.length == 0)	// failed on empty lines
			return;

		column = Math.min(column, buf.length-1);
		if ( (buf[column] != ' ') && (buf[column] != '\t') )
			column--;
		while ( column >= 0 )
		{
			if ( (buf[column] == ' ') || (buf[column] == '\t') )
			{
				if ( skippedSpace )
				{
					column++;
					break;
				}
			}
			else
			{
				skippedSpace = true;
			}
			column--;
		}
	}
	
	void _pressedDelete()
	{
		String s;
		int max;
		
		if (hactive)
		{
			copy(true,true);
			return;
		}
		s = docMan.getLine(line);
		max = s.length();
		if (column < max)
		{
			docMan.delete_char(line,column);
			return;
		}
		else
			if (line+1 < docMan.getLineCount())
		{
			docMan.join_line(line,column);
			repaint();
			return;
		}
	}
	
	void _pressedBackspace()
	{
		String s;
		int max;
		
		if (hactive)
		{
			copy(true,true);
			return;
		}
		if (column > 0)
		{
			column--;
			docMan.delete_char(line,column);
			 // JJ 1998.01.03  - "pix" must be updated with "column"		
			pix = ruler.length(line,column) + EDGE;
			shiftHoriz(pix);
			return;
		}
		else
			if (line > 0)
		{
			line--;
			s = docMan.getLine(line);
			column = s.length();
			shiftHoriz(ruler.length(line,10000000) + EDGE);
			docMan.join_line(line,column);
			shiftVert(line);
			repaint();
			return;
		}
	}
	
	void _insertNewline()
	{
		String s;
		int max;
		
		if (hactive)
			copy(true,false);
		docMan.split_line(line,column);
		shiftVert(++line);
		column = 0;
		if ( autoIndent )
		{
			int i = 0;
			String prevline = docMan.getLine(line-1);
			char ch;
			while ( (i < prevline.length()) && 
				( ((ch = prevline.charAt(i)) == ' ') ||
				(ch == '\t') ) )
			{
				docMan.insert_char(line,column,ch);
				column++;
				i++;
			}
			 // JJ 1998.01.03  - "pix" must be updated with "column"		
			pix = ruler.length(line,column) + EDGE;
			shiftHoriz(pix);
		}
		else
		{
			pix = EDGE;
			shiftHoriz(0);
		}
		repaint();
		return;
	}

	private String[] readonlyActions =
	{
		"line-swap",
		"line-delete",
		"line-clone",
		"character-delete-forward",
		"character-delete-backward",
		"replace-dialog",
		"selection-cut",
		"buffer-paste",
		"line-break",
		"document-save"
	};

	/**
	 * Disables/enables all actions which modify text
	 * This method does not check if readOnly status have changed - you
	 * should check this before and do not call this if not needed
	 */
	public void setReadOnly(boolean readOnly)
	{
		int max = readonlyActions.length;
		int i;
		for ( i=0; i < max; i++ )
		{
			getMpAction(readonlyActions[i]).setEnabled(!readOnly);
		}
	}


// --------------------- ViewInterface implementation -----------------

	public DocInterface getDocument()
	{
		return docMan;
	}

	public void setStatusMessage( String txt )
	{
		textFrame.setMessage(txt);
	}

	public void sendViewAction( String action )
	{

		System.out.println("generic view owner action = " + action);

		DocOwnerInterface docOwner = docMan.getOwner();

		if (docOwner != null)
			docOwner.viewAction(this,action);
	}
}

/*
	ACTIONS LIST (-unimplemented, +implemented)

	+	line-swap
	+	line-delete
	+	line-clone
	+	brace-match-forward
	+	brace-match-backward
	+	find-next-forward
	+	find-next-backward
	+	cursor-forward
	+	cursor-backward
	+	cursor-up
	+	cursor-down
	+	cursor-word-backward
	+	cursor-word-forward
	+	cursor-document-begin
	+	cursor-document-end
	+	cursor-page-begin
	+	cursor-page-end
	+	cursor-line-begin
	+	cursor-line-end
	+	page-up
	+	page-down
	+	character-delete-forward
	+	character-delete-backward
	+	document-new
	+	document-open-dialog
	+	document-save
	+	document-save-as-dialog
	+	document-print-dialog
	+	frame-close
	+	undo
	+	redo
	+	find-dialog
	+	goto-dialog
	+	replace-dialog
	+	selection-copy
	+	selection-cut
	+	buffer-paste
	+	properties-dialog
	+	frame-clone
	+	help-about-dialog
	+	selection-indent
	+	selection-unindent
	+	anchor-drop
	+	anchor-goto-last
	-	anchor-goto-dialog
	+	macro-record-toggle
	+	macro-replay

// ecm actions
	+	cursor-select-forward
	+	cursor-select-backward
	+	cursor-select-up
	+	cursor-select-down
	+	cursor-select-word-backward
	+	cursor-select-word-forward
	+	cursor-select-document-begin
	+	cursor-select-document-end
	+	cursor-select-line-begin
	+	cursor-select-line-end
// end ecm actions

// Temporary hooks
	+	keytable-save
	+	keytable-load
	+	mode-readonly-toggle

// Do we want fully flexible actions ?
?	+	line-break
?	-	line-break-noindent
?	-	line-break-indent

?	-	info-text-statistics
?	-	info-text-statistics-sline
?	-	info-text-statistics-dialog

// Do we want to support direct on/off set, or only switches with info in sline?
?	-	mode-separators-toggle
?	-	mode-separators-on
?	-	mode-separators-off
?	+	mode-autoindent-toggle
?	-	mode-autoindent-on
?	-	mode-autoindent-off
*/


