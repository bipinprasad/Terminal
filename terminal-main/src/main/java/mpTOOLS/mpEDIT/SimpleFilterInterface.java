package mpTOOLS.mpEDIT;

/**
 * <P>This is a simple interface for EditBeans extending the <b>mpTOOLS</b>
 * editors. I plan on supporting this interface as well as more elaborate
 * beans and interfaces in future text editors.</P>
 *
 * <P>Example beans and their source code may be found at the
 * <A HREF="http://members.tripod.com/~mpTOOLS">mpTOOLS</A> page.</P>
 */
 
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

public interface SimpleFilterInterface
{
	/**
	 * Returns a menu string to the editor application.
	 * This string is inserted into the "Options" menu.
	 *
	 * @return	The menu string.
	 */
	public String getMenuString();

	/**
	 * This function filters text from the editor application.  This "filtering"
	 * may be anything which produces new text, it may even ignore
	 * the passed selection (as in an "insert date" item).
	 *
     * @param	text The currently selected text from the editor.
	 *			Note that this may be an empty string, "".
	 *
	 * @return	This filter should return a new String if the
	 *			operation is successful and a null pointer when
	 *			it fails.
	 */

	public String filterText(String text);
}
