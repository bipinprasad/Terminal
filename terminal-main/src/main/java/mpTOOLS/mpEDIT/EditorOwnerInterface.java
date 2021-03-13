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

import java.awt.event.ActionEvent;

/**
 * <P>This interface defines the services to be provided by a host
 * application for the mpEDIT text editor.  Using this interface, 
 * mpEDIT would notify its parent of opened or closed documents.</P>
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
public interface EditorOwnerInterface
{	
	/**
	 * Called when the user has opened a new document.
	 * @param	doc	A DocInterface for the opened document.
	 */
	public void openedDoc(DocInterface doc);

	/**
	 * Called when the user is closing a document.
	 * @param	doc	A DocInterface for the closing document.
	 */
	public void closingDoc(DocInterface doc);

	/**
	 * Called when the user has closed the last document.
	 */
	public void lastFileClosed();

	/**
	 * A very simple way to supply actions
	 * to an owner.  This will probably change as I move closer
	 * to Swing and have access to Swing's Action classes.
     * @param	editor The editor firing the action.
     * @param	action The action id.
	 */
	public void editAction(EditorInterface editor, String action);
}
