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

class Progress extends Dialog
{
	Panel center;

	public Progress(TextFrame textFrame, String s)
	{
		super(textFrame);

		Panel north = new Panel();
		north.add(new Label(s));
		add("North", north);

		center = new Panel();
		add("Center", center);

		Dimension size = new Dimension(300,100);
		setSize(size);
		setLocation(textFrame.getPlace(size));
	}

	public void update( int percent )
	{
		Dimension d = center.getSize();
		Graphics g = center.getGraphics();

		int fifth = d.width / 5;
		int third = d.height / 3;

		g.fillRect( fifth, third, (fifth * 3 * percent) / 100, third);
	}

}