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

public class LineMan
{
	private Vector lines;

	public LineMan()
	{
		lines = new Vector(100,100);
	}

	public synchronized void freshVectors( int aSize, int aDelta )
	{
		lines = new Vector(aSize, aDelta);
	}

	public synchronized void addElement(String s)
	{
		lines.addElement(new LineInfo(s));
	}

	public synchronized void insertElementAt(String s, int i)
	{
		lines.insertElementAt(new LineInfo(s),i);
	}

	public synchronized void removeAllElements()
	{
		lines.removeAllElements();
	}

	public synchronized void removeElementAt(int i)
	{
		lines.removeElementAt(i);
	}

	public synchronized int size()
	{
		return lines.size();
	}

	public synchronized void setString(String s, int i)
	{
		LineInfo li = (LineInfo)lines.elementAt(i);
		li.data = s;
	}

	public synchronized String getString(int i)
	{
		LineInfo li = (LineInfo)lines.elementAt(i);
		return li.data;
	}

	public synchronized LineInfo getLineInfo(int i)
	{
		return (LineInfo)lines.elementAt(i);
	}

	public synchronized void setLineInfo(LineInfo li, int i)
	{
		lines.setElementAt(li,i);
	}
}
