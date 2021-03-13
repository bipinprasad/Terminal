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

/**
 * <P>This interface defines the services provided by an mpEDIT
 * document instance.  Using this interface, a parent application should be
 * able to manage a text document.</P>
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
public interface DocInterface
{	
	/**
	 * Closes this document.  If the "bail" flag is true changes will
	 * be discarded, otherwise the user will be queried.
     * @param	bail Exit immediately, discarding changes.
	 */
	public void closeDoc(boolean bail);
		
	/**
	 * Writes out this document.
	 */
	public void saveDoc();
		
	/**
	 * Writes out this document.
     * @param	pathname New document name (full path).
	 */
	public void saveAsDoc(String pathname);
		
	/**
	 * Returns the full path to this document.
     * @return	Document name (full path).
	 */
	public String getPathname();
		
	/**
	 * Returns the filename (no path) for this document.
     * @return	Document name (no path).
	 */
	public String getFilename();
		
	/**
	 * Returns status of the document.
     * @return	True if the file has been changed since being written.
	 */
	public boolean docChanged();
			
	/**
	 *  Get the line count for a document.
     * @return	The line count.
	 */
	public int getLineCount();
		
	/**
	 * Set a TagLine, used to track lines even when lines have
	 * been inserted or deleted elsewhere in the document.
     * @return	The TagLine for a given line.
	 */
	public TagLine tagLine(int lineNo);
		
	/**
	 * Set a TagLine (including a tag color), used to
	 * track lines even when lines have
	 * been inserted or deleted elsewhere in the document.
     * @param	color	The Color to display (pass null to clear).
     * @return	The TagLine for a given line.
	 */
	public TagLine tagLine(int lineNo, Color color);
			
	/**
	 * Get the current line number for a TagLine.
     * @return	The line for a given tag.
	 */
	public int lineFromTag(TagLine tag);
		
	/**
	 * Bring forward any view window and scroll to the desired line.
     * @param	tag	The line to display.
     * @return	Success.
	 */
	public boolean showLine(TagLine tag);
		
	/**
	 * Get the text for a line.
     * @param	tag	The line to get.
     * @return	The text for the line (null if TagLine not found).
	 */
	public String getLine(TagLine tag);
		
	/**
	 * Set the text for a line.
     * @param	tag	The line to set.
     * @param	text	The text to set.
     * @return	Success.
	 */
	public boolean setLine(TagLine tag, String text);
		
	/**
	 * Insert a line of text before the TagLine.
     * @param	tag	The line to set.
     * @param	text	The text to set.
     * @return	Success.
	 */
	public boolean addLine(TagLine tag, String text);
		
	/**
	 * Delete a line.
     * @param	tag	The line to delete.
     * @return	Success.
	 */
	public boolean deleteLine(TagLine tag);

	// jj - new method, feeback from the jipe project
	
	/**
	 * Reloads the existing document.
     * @param	filename of document to be reloaded (full path).
	 */
	public void reloadDoc(String filename);

    // sl - Call to bring a document to the top of a list and set in focus.
    
	/**
	 * Bring the document to the top of a list and set in focus.
	 */
	public void docToTop();
	/**
	 * Set the document as a readOnly document or editable
	 */
	public void setReadOnly( boolean aReadOnly );
}
