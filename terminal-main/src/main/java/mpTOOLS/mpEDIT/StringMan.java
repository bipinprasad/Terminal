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

public class StringMan
{
	ResourceBundle localBundle;
	ResourceBundle parentBundle;

	public StringMan(String resourceName)
	{
		localBundle = ResourceBundle.getBundle(resourceName, Locale.getDefault());
	}

	/**
	 * Search this resource bundle before using mpEDIT's own.
     * @param	rb ResourceBundle
	 */
	public void addResourceBundle(ResourceBundle rb)
	{
		parentBundle = rb;
	}

	//
	// Get a required string - it's an error when string not found
	//

	public String getString(String key)
	{
		String s = getParentString(key);

		if (s != null)
			return s;

		try {
			return localBundle.getString(key);
		}

		catch(MissingResourceException me) {
			System.out.println("String [" + key + "] is missing from resource file.");
			return "MISSING STRING";
		}
	}

	//
	// Get a optional string - it's NOT an error when string not found
	//

	public String getOptionalString(String key)
	{
		String s = getParentString(key);

		if (s != null)
			return s;

		try {
			return localBundle.getString(key);
		}

		catch(MissingResourceException me) 	{
			return null;
		}
	}
	//
	// Get a optional string - it's NOT an error when string not found
	//

	public String getParentString(String key)
	{
		if (parentBundle == null)
			return null;

		try {
			return parentBundle.getString(key);
		}

		catch(MissingResourceException me) {
			return null;
		}
	}
}
