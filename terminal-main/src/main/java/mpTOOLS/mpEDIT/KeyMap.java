/*
 * Copyright (c) 1997, 1998, 1999 John Jensen. All rights reserved.
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
import java.util.*;

//
// If you want to change the key mapping, you are in the right place.
// Change the bindings below to match your preferences and recompile.
//

//
// The logic (?) here is to set up the Hashtable held by some other class
// and then allow this initialization object to be discarded and garbage
// collected.  This transient class could have been written to read and
// parse a configuration file.  I decided against that for the moment.
// As this is an open source project, this _is_ a configuration file,
// sort-of, and the runtime impact is a bit lower than opening and
// reading a file.
//

class KeyMap
{
	private Hashtable keyhash;

	private void setKeyAction(String action, int modifiers, int key)
	{
		keyhash.put(new KeyObject(modifiers ,key), action);
	}

	public void initHash(Hashtable kh)
	{
		keyhash = kh;

		setKeyAction("anchor-drop", Event.ALT_MASK, KeyEvent.VK_A);
		setKeyAction("anchor-goto-last", Event.ALT_MASK, KeyEvent.VK_J);
		setKeyAction("brace-match-backward", Event.ALT_MASK, 219); // '['
		setKeyAction("brace-match-forward", Event.ALT_MASK, 221); // ']'
		setKeyAction("buffer-paste", Event.CTRL_MASK, KeyEvent.VK_V);		
		setKeyAction("character-delete-backward", 0, KeyEvent.VK_BACK_SPACE);
		setKeyAction("character-delete-forward", 0, KeyEvent.VK_DELETE);
		setKeyAction("cursor-backward", 0, KeyEvent.VK_LEFT);
		setKeyAction("cursor-document-begin", KeyEvent.CTRL_MASK, KeyEvent.VK_PAGE_UP);
		setKeyAction("cursor-document-end", KeyEvent.CTRL_MASK, KeyEvent.VK_PAGE_DOWN);
		setKeyAction("cursor-down", 0, KeyEvent.VK_DOWN);
		setKeyAction("cursor-forward", 0, KeyEvent.VK_RIGHT);
		setKeyAction("cursor-line-begin", 0, KeyEvent.VK_HOME);
		setKeyAction("cursor-line-end", 0, KeyEvent.VK_END);
		setKeyAction("cursor-select-backward", Event.SHIFT_MASK, KeyEvent.VK_LEFT);
		setKeyAction("cursor-select-document-begin", Event.SHIFT_MASK | KeyEvent.CTRL_MASK, KeyEvent.VK_HOME);
		setKeyAction("cursor-select-document-end", Event.SHIFT_MASK | KeyEvent.CTRL_MASK, KeyEvent.VK_END);
		setKeyAction("cursor-select-down", Event.SHIFT_MASK | Event.SHIFT_MASK, KeyEvent.VK_DOWN);
		setKeyAction("cursor-select-forward", Event.SHIFT_MASK, KeyEvent.VK_RIGHT);
		setKeyAction("cursor-select-line-begin", Event.SHIFT_MASK, KeyEvent.VK_HOME);
		setKeyAction("cursor-select-line-end", Event.SHIFT_MASK, KeyEvent.VK_END);
		setKeyAction("cursor-select-up", Event.SHIFT_MASK, KeyEvent.VK_UP);
		setKeyAction("cursor-select-word-backward", Event.SHIFT_MASK | Event.CTRL_MASK, KeyEvent.VK_LEFT);
		setKeyAction("cursor-select-word-forward", Event.SHIFT_MASK | Event.CTRL_MASK, KeyEvent.VK_RIGHT);		
		setKeyAction("cursor-page-begin", KeyEvent.CTRL_MASK, KeyEvent.VK_HOME);
		setKeyAction("cursor-page-end", KeyEvent.CTRL_MASK, KeyEvent.VK_END); 
		setKeyAction("cursor-up", 0, KeyEvent.VK_UP);
		setKeyAction("cursor-word-backward", Event.CTRL_MASK, KeyEvent.VK_LEFT);
		setKeyAction("cursor-word-forward", Event.CTRL_MASK, KeyEvent.VK_RIGHT);
		setKeyAction("document-new", Event.CTRL_MASK, KeyEvent.VK_N);		
		setKeyAction("document-open-dialog", Event.CTRL_MASK, KeyEvent.VK_O);		
		setKeyAction("document-print-dialog", Event.ALT_MASK, KeyEvent.VK_P);
		setKeyAction("document-save", Event.ALT_MASK, KeyEvent.VK_S);
		setKeyAction("find-dialog", Event.CTRL_MASK, KeyEvent.VK_F);
		setKeyAction("find-next-backward", Event.ALT_MASK, KeyEvent.VK_UP);
		setKeyAction("find-next-forward", Event.ALT_MASK, KeyEvent.VK_DOWN);
		setKeyAction("frame-close", Event.CTRL_MASK, KeyEvent.VK_W);
		setKeyAction("goto-dialog", Event.CTRL_MASK, KeyEvent.VK_G);
		setKeyAction("line-break", 0, KeyEvent.VK_ENTER);
		setKeyAction("line-clone", Event.ALT_MASK, KeyEvent.VK_O);
		setKeyAction("line-delete", Event.ALT_MASK, KeyEvent.VK_D);
		setKeyAction("line-swap", Event.ALT_MASK, KeyEvent.VK_S);
		setKeyAction("mode-autoindent-switch", Event.ALT_MASK, KeyEvent.VK_F3);
		setKeyAction("page-down", 0, KeyEvent.VK_PAGE_DOWN);
		setKeyAction("page-up", 0, KeyEvent.VK_PAGE_UP);
		setKeyAction("redo", Event.CTRL_MASK, KeyEvent.VK_Y);
		setKeyAction("replace-dialog", Event.CTRL_MASK, KeyEvent.VK_H);
		setKeyAction("selection-copy", Event.CTRL_MASK, KeyEvent.VK_C);		
		setKeyAction("selection-cut", Event.CTRL_MASK, KeyEvent.VK_X);		
		setKeyAction("selection-indent", Event.CTRL_MASK, KeyEvent.VK_TAB );
		setKeyAction("selection-unindent", Event.SHIFT_MASK, KeyEvent.VK_TAB );
		setKeyAction("undo", Event.CTRL_MASK, KeyEvent.VK_Z);
		setKeyAction("macro-record-toggle", Event.CTRL_MASK, KeyEvent.VK_F8);
		setKeyAction("macro-replay", 0, KeyEvent.VK_F8);
		
		// temporary
  		setKeyAction("mode-readonly-switch", Event.ALT_MASK, KeyEvent.VK_R );
	}
}
