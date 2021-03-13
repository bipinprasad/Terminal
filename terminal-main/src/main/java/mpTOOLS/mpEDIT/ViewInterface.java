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

/**
 * This interface defines the services provided by an mpEDIT
 * document view.  Each document may have
 * one to many views, each with different selections, states, but sharing
 * exactly the same document (all changes are sent to all views of
 * a given document).</P>
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
 * Feb. 08, 1998 - Initial implementation by Artur Biesiadowski<BR>
 * Mar. 25, 1998 - removed addMpAction, pending Swing integration<BR>
 */
public interface ViewInterface
{
	/**
	 * Returns document to which given view is bound
	 */
	public DocInterface getDocument();
	
	public void setStatusMessage( String txt );

	// add a lot of view control methods here
	// ....

/*	public TagLine getTopLine();
	public TagLine getBottomLine();

	public int getLeftColumn();
	public int getRightColumn();

	public int getRowCount();

	public TagLine getLineAtRow( int row );

	public int getCursorRow();
	public void setCursorRow(int row);
	public TagLine getCursorLine();
	public int getCursorColumn();
	public void setCursorColumn(int);
*/
}
