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

// the Ruler is used to measure pixel lengths of strings

public class Ruler
{
	private DocMan		docMan;
	private FontMetrics fontMetrics;
	private int			tabSize = 4;

	private final int LINE_MAX = 2000;

	private char before[];
	private char after[];

	private boolean hasTabs;

	private int beforeMax,afterMax;
	private int lineLast;

	public Ruler(DocMan dm)
	{
		docMan = dm;
		lineLast = -1;
	}

	// public methods are synchronized to guard lineLast and the buffers

	public synchronized void setFontMetrics(FontMetrics fm)
	{
		if (fm == fontMetrics)
			return;

		fontMetrics = fm;
		lineLast = -1;
	}

	public synchronized void setTabSize(int ts)
	{
		if (ts == tabSize)
			return;

		tabSize = ts;
		lineLast = -1;
	}

	public synchronized void invalidate(int first, int last)
	{
		if ((lineLast >= first) && (lineLast <= last))
		lineLast = -1;
	}

	private void fillBuffers(int line_no)
	{
		char c;
		int i,j,tabs,max;

		before = docMan.getLine(line_no).toCharArray();

		max = before.length;
		for (j=i=0; i<max; i++)
			if (before[i] == '\t')
				j++;

		if (j == 0)
		{
		    hasTabs = false;
			after = before;
			j = max;
		}
		else
		{
		    hasTabs = true;
			after = new char[max + (j * (tabSize-1))];

			for (j=i=0; i<max; i++)
			{
				c = before[i];
				if (c == '\t')
				{
					tabs = tabSize - (j % tabSize);
					while (tabs-- > 0) after[j++] = ' ';
				}
				else
				{
					after[j++] = c;
				}
			}
		}

		lineLast = line_no;
		beforeMax = before.length;
		afterMax = j;

	}

	public synchronized int length(int line, int column)
	{
		int i,j;
		int uColumn,tabs,temp;
		char c;
		boolean readline;
		
		readline = line != lineLast;

		if (fontMetrics == null)
		    return -1;

		if (column == 0)
		    return 0;

		if (readline)
		    fillBuffers(line);

		if (hasTabs)
		{
			uColumn = 1000000;

			for (i=j=0; i<beforeMax; i++)
			{
				if (i == column)
				{
					uColumn = j;
					break;
				}
					
				c = before[i];
				if (c == '\t')
				{
					tabs = tabSize - (j % tabSize);
					while (tabs-- > 0) j++;
				}
				else
				{
					j++;
				}
			}

			afterMax = j;
			lineLast = line;
		}
		else
		{
			uColumn = column;
		}

		if (uColumn > afterMax)
			uColumn = afterMax;

		return fontMetrics.charsWidth(after,0,uColumn);
	}

	public synchronized TextPosition position(int line, int x)
	{
		int i,j;
		int pix,temp,diff,odiff;
		int tabs;
		char c;
		boolean readline,looking;

		readline = line != lineLast;

		if (fontMetrics == null)
		    return new TextPosition(0,0,0);

		if (readline)
		    fillBuffers(line);

		pix = temp = 0;
		diff = x;

		for (i=j=0; i<beforeMax; i++)
		{				
		    c = before[i];
		    if (c == '\t')
		    {
			    tabs = tabSize - (j % tabSize);
			    while (tabs-- > 0) j++;
		    }
		    else
		    {
			    j++;
		    }

			odiff = diff;
			temp = fontMetrics.charsWidth(after,0,j);
			diff = Math.abs(x - temp);

			if (diff >= odiff)
				break;

			pix = temp;
		}

		return new TextPosition(line,i,pix);
	}
}
