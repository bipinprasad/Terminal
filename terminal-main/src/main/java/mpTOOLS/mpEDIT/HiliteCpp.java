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
import java.util.*;
import java.io.*;

public class HiliteCpp extends Hilite
{
	static final String keywordStrings[] =
	{
		"#define",
		"#elif",
		"#else",
		"#endif",
		"#error",
		"#if",
		"#ifdef",
		"#ifndef",
		"#include",
		"#line",
		"#pragma",
		"#undef",
		// #warning ?
		"asm",
		"auto",
		"break",
		"case",
		"catch",
		"cdecl",
		"char",
		"class",
		"const",
		"continue",
		"default",
		"delete",
		"double",
		"do",
		"else",
		"enum",
		"extern",
		"far",
		"float",
		"for",
		"friend",
		"goto",
		"huge",
		"if",
		"inline",
		"int",
		"interrupt",
		"long",
		"near",
		"new",
		"operator",
		"pascal",
		"private",
		"protected",
		"public",
		"register",
		"return",
		"short",
		"signed",
		"sizeof",
		"static",
		"struct",
		"switch",
		"template",
		"this",
		"throw",
		"try",
		"typedef",
		"union",
		"unsigned",
		"virtual",
		"void",
		"volatile",
		"while",
		"yield",
	};

	static final char[][] keywordChars;

	static
	{
		int i,max;

		max = keywordStrings.length;

		keywordChars = new char[max][];

		for (i=0;i<max;i++)
			keywordChars[i] = keywordStrings[i].toCharArray();
			
	}


	public HiliteCpp(LineMan l, int t, boolean a)
	{
		super(l,t,a);
		keys = keywordChars;
	}

	@Override
    protected void scanLine(int i)
	{
		char	c,d,e;
		String	w;
		boolean	isword,inword,incharliteral;
		int		pos,start,max;
		
		max = fillBuffer(i);

		pos = -1;
		inword = incharliteral =false;
		start = 0;
		c = d = 0;
		keyCt = 0;

		if (inComment || inLiteral)
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

			if ((c == '/') && (d == '*') && (e != '/')  && inComment && !inLiteral)
			{
				keyEnds[keyCt] = pos+1;
				keyTypes[keyCt] = COMMENT;
				keyCt++;
				inComment = false;
				continue;
			}

			if ((c == '*') && (d == '/') && !(inComment || inLiteral))
			{
				keyStarts[keyCt] = pos-1;
				inComment = true;
				continue;
			}
			
			if (inComment)
			    continue;

			if ((c == '"') && (d != '\\'))
			{
				if (!inLiteral)
				{
					inLiteral = true;
					keyStarts[keyCt] = pos;
				}
				else
				{
					inLiteral = false;
					keyEnds[keyCt] = pos+1;
					keyTypes[keyCt] = QUOTE;
					keyCt++;
				}
				inword = false;
			}

			if (inLiteral)
			    continue;
			    
			if ((c =='\'') && ((d != '\\' ) || ((d == '\\' ) && (e == '\\' ))))
			{
				if(!incharliteral)
				{
					incharliteral = true;
					keyStarts[keyCt] = pos;
				}
				else
				{
					incharliteral = false;
					keyEnds[keyCt] = pos+1;
					keyTypes[keyCt] = QUOTE;
					keyCt++;
				}
				inword = false;
			}

			if (incharliteral)
				continue;	

			    
			if (((c >= 'a') && (c <= 'z')) || (c == '_') || (c == '#'))
			{
				if (!inword && ((d < 'A') || (d > 'Z')) && ((d < 'a') || (d > 'z')))
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
					if ((c < 'A') || (c > 'Z'))
					{
						if (matchKeys(start,pos))
						{
							keyEnds[keyCt] = pos;
							keyTypes[keyCt] = KEYWORD;
							keyCt++;
						}
					}
					inword = false;
					continue;
				}
			}
			
			if ((c == '/') && (d == '/'))
			{
				keyStarts[keyCt] = pos-1;
				keyEnds[keyCt] = max;
				keyTypes[keyCt] = COMMENT;
				keyCt++;
			}
    
		}

		if (inComment)
		{
			keyEnds[keyCt] = max;
			keyTypes[keyCt] = COMMENT;
			keyCt++;
		}

		if (inLiteral)
		{
			keyEnds[keyCt] = max;
			keyTypes[keyCt] = QUOTE;
			keyCt++;
		}

		LineInfo hi = lines.getLineInfo(i);

		hi.inComment = inComment;
		hi.inLiteral = inLiteral;
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
