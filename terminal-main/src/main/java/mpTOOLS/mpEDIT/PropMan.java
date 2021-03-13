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

import java.io.*;
import java.awt.*;
import java.util.*;

public class PropMan
{
	Properties properties = null;

	private String prop_name = new String("mpEdit.ini");

	private void setDefaults()
	{
		properties.put("mpEDIT.font.name","Courier");
		properties.put("mpEDIT.font.style",String.valueOf(Font.PLAIN));
		properties.put("mpEDIT.font.size","12");
		properties.put("mpEDIT.tab.size","4");
		properties.put("mpEDIT.hilite",String.valueOf(true));
	}

	public Properties getProperties()
	{
		if (properties == null)
		{
			properties = new Properties();
			setDefaults();

			try
			{
				FileInputStream sf = new FileInputStream(prop_name);
				properties.load( sf );
				sf.close();
				System.out.println("Using custom properties.");
			}

			catch (Exception e)
			{
				System.out.println("Using default properties.");
			}
		}

		return properties;
	}

	public void writeProperties(Properties p)
	{
		properties = p;

		try
		{
			FileOutputStream od = new FileOutputStream(prop_name);
			properties.save( od, "Settings for mpEdit" );
			od.close();
		}

		catch (Exception e)
		{
			System.out.println("Warning: properties could not be saved");
		}
	}
}
