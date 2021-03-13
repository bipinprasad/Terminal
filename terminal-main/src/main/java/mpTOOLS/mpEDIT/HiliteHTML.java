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

public class HiliteHTML extends Hilite
{
	public HiliteHTML(LineMan l, int t, boolean a)
	{
		super(l,t,a);

		raise = true;
		initialComment = true;	// backwards for html

		textColor = new Color(0xC00000);
		textXColor = new Color(0xC00000 ^ 0xffffff);
		commentColor = new Color(0x000000);
		commentXColor = new Color(0xffffff);
		keywordColor = new Color(0x0000b0);
		keywordXColor = new Color(0x0000b0 ^ 0xffffff);

		String temp[] = new String[] 
		{
			"A",
			"ACTION",
			"ADDRESS",
			"ALIGN",
			"ALINK",
			"ALT",
			"APPLET",
			"AREA",
			"B",
			"BACKGROUND",
			"BASE",
			"BASEFONT",
			"BGCOLOR",
			"BIG",
			"BLOCKQUOTE",
			"BODY",
			"BOLD",
			"BORDER",
			"BR",
			"CAPTION",
			"CELLPADDING",
			"CELLSPACING",
			"CENTER",
			"CHECKED",
			"CITE",
			"CLEAR",
			"CODE",
			"CODE",
			"CODEBASE",
			"COLS",
			"COLSPAN",
			"COMPACT",
			"CONTENT",
			"COORDS",
			"DD",
			"DFN",
			"DIR",
			"DIV",
			"DL",
			"DOCTYPE",
			"DT",
			"EM",
			"ENCTYPE",
			"FONT",
			"FORM",
			"H1",
			"H2",
			"H3",
			"H4",
			"H5",
			"H6",
			"HEAD",
			"HEIGHT",
			"HR",
			"HREF",
			"HSPACE",
			"HTML",
			"HTTP-EQUIV",
			"I",
			"IMG",
			"INPUT",
			"ISINDEX",
			"ISMAP",
			"KBD",
			"LI",
			"LINK",
			"LINK",
			"MAP",
			"MAXLENGTH",
			"MENU",
			"META",
			"METHOD",
			"MULTIPLE",
			"NAME",
			"NOSHADE",
			"NOWRAP",
			"OL",
			"OPTION",
			"P",
			"PARAM",
			"PRE",
			"PROMPT",
			"PUBLIC",
			"REL",
			"REV",
			"ROWS",
			"ROWSPAN",
			"SAMP",
			"SCRIPT",
			"SELECT",
			"SELECTED",
			"SHAPE",
			"SIZE",
			"SMALL",
			"SRC",
			"START",
			"STRIKE",
			"STRONG",
			"STYLE",
			"SUB",
			"SUP",
			"TABLE",
			"TD",
			"TEXT",
			"TEXTAREA",
			"TH",
			"TITLE",
			"TITLE",
			"TR",
			"TT",
			"TYPE",
			"U",
			"UL",
			"USEMAP",
			"VALIGN",
			"VALUE",
			"VAR",
			"VLINK",
			"VSPACE",
			"WIDTH"
		};

		int i,max;

		max = temp.length;

		keys = new char[max][];

		for (i=0;i<max;i++)
			keys[i] = temp[i].toCharArray();
	}

	@Override
    protected void scanLine(int i)
	{
		char	c,d,e;
		String	w;
		boolean	isword,inword,inquote;
		int		pos,start,max;
		
		max = fillBuffer(i);

		pos = -1;
		inword = inquote = false;
		start = 0;
		c = d = 0;
		keyCt = 0;

		if (inComment)
		{
			keyStarts[keyCt] = 0;
		}

		while (++pos <= max)
		{
			e = d;
			d = c;

			if (pos < max)
				c = buffer[pos];
			else
				c = 0;

			if ((c == '<') && (d != '\\')  && inComment)
			{
				keyEnds[keyCt] = pos;
				keyTypes[keyCt] = COMMENT;
				keyCt++;
				inword = inComment = false;
				continue;
			}

			if (inComment)
			    continue;

			if ((c == '"') && (d != '\\'))
			{
				inquote = !inquote;
				inword = false;
			}

			if (inquote)
			    continue;
			    
			if (((c >= 'A') && (c <= 'Z')) ||
				((c >= 'a') && (c <= 'z')) || 
				((c >= '0') && (c <= '9')))
			{
				if (!inword)
				{
					keyStarts[keyCt] = start = pos;
					inword = true;
				}
				continue;
			}
			else
			{
				if (inword)
				{
					if (matchKeys(start,pos))
					{
						keyEnds[keyCt] = pos;
						keyTypes[keyCt] = KEYWORD;
						keyCt++;
					}
					inword = false;
				}
			}
			    
			if ((c == '>') && (d != '\\') && !(inComment || inquote))
			{
				keyStarts[keyCt] = pos+1;
				inComment = true;
			}			
		}

		if (inComment)
		{
			keyEnds[keyCt] = max;
			keyTypes[keyCt] = COMMENT;
			keyCt++;
		}

		LineInfo hi = lines.getLineInfo(i);

		hi.inComment = inComment;
		hi.keyCt = keyCt;
		hi.keyStarts = new int[keyCt];
		hi.keyEnds = new int[keyCt];
		hi.keyTypes = new byte[keyCt];

		for (int j=0; j<keyCt; j++)
		{
			hi.keyStarts[j] = keyStarts[j];
			hi.keyEnds[j] = keyEnds[j];
			hi.keyTypes[j] = keyTypes[j];
		}
	}
}
