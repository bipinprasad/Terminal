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

import java.util.ResourceBundle;

/**
 * <P>This interface defines the services provided by the mpEDIT
 * text editor.  Using this interface, a parent application should be
 * able to create an mpEDIT object and then use that object to manage
 * one or more text editing windows.</P>
 *
 * <P>This interface is offered for public review and comment.  If
 * you have an application requiring text editing services, please
 * review this document and send me your comments.</P>
 *
 * <P>I would like to start with a simple framework and build
 * out as required.</P>
 *
 * <P>More information on the mpEDIT project may be found on the
 * <A HREF="http://members.tripod.com/~mpTOOLS">mpTOOLS</A> page.</P>
 *
 * <B>Revisions</B><BR>
 * Dec. 07, 1997 - Initial specification<BR>
 * Dec. 17, 1997 - General redesign<BR>
 * Dec. 21, 1997 - First Implementation<BR>
 */
public interface EditorInterface
{	
	/**
	 * Search this ResourceBundle before mpEDIT's own
     * @param	rb ResourceBundle
	 */
	public void addResourceBundle(ResourceBundle rb);
		
	/**
	 * Closes all documents.  If the "bail" flag is true changes will
	 * be discarded, otherwise the user will be queried.
     * @param	bail Exit immediately, discarding changes.
	 */
	public void closeDocs(boolean bail);
		
	/**
	 * Opens a new editor window containing the specified file.
     * @param	owner The owner for this document.
     * @param	filename A full path to a text file to be opened.
	 * @return	A DocInterface for the opened document.
	 */
	public DocInterface openDoc(DocOwnerInterface owner, String filename);
	
	/**
	 * Opens a new, empty, document.
     * @param	owner The owner for this document.
	 * @return	A DocInterface for the opened document.
	 */
	public DocInterface newDoc(DocOwnerInterface owner);
}
