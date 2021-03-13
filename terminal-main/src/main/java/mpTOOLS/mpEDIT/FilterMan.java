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
import java.util.*;
import java.util.zip.*;

public class FilterMan
{
Vector filters = new Vector();
FilterLoader fl = null;

private void loadBean(String beanName)
{
	Class c,sfi,tfi;
	Object o = null;

	String className = 	new String(beanName);

	if (className.endsWith(".class"))
		className = className.substring(0,className.length()-6);

	className = className.replace('/','.');

	try
	{
		sfi = Class.forName("mpTOOLS.mpEDIT.SimpleFilterInterface");
		tfi = Class.forName("mpTOOLS.mpEDIT.Test01FilterInterface");
	}
	catch (ClassNotFoundException e)
	{
		System.out.println("Sorry, could not load filter interfaces.");
		return;
	}
	
    try
    {
        c = fl.loadClass(className,true);
        o = c.newInstance();
	    if ( ! sfi.isInstance(o) && ! tfi.isInstance(o) )
		{
			System.out.println("Sorry, not an EditBean: "+className);
			o = null;
		}
    }

    catch (Exception e)
    {
        System.out.println("Error opening class: "+className);
		o = null;
    }

	if (o != null)
	{
        System.out.println("Added EditBean: "+className);
		filters.addElement(o);
	}
}

private Manifesto loadManifesto(String filename)
{
    Manifesto m = null;

    try
    {
        ZipFile zipFile = new ZipFile(filename);
        Enumeration enumeration = zipFile.entries();
        while((m == null) && enumeration.hasMoreElements())
        {
            ZipEntry ze = (ZipEntry)enumeration.nextElement();
            String name = ze.getName();
            if (name.charAt(0) == '/')
                name = name.substring(1, name.length());
            name = name.toUpperCase();
            if (name.equals("META-INF/MANIFEST.MF"))
            {
                InputStream inputStream = zipFile.getInputStream(ze);
                m = new Manifesto(inputStream);
            }

        }
        zipFile.close();
    }

    catch (Exception e)
    {
        System.out.println("Error opening manifest: "+e);
		m = null;
    }

	return m;
}

private void loadFile(String filename)
{
	ManifestItem mi;
	String value;
	Manifesto m;

	m  = loadManifesto(filename);
	if (m == null)
	{
		System.out.println("Error opening file: "+filename);
		return;
	}

	fl = new FilterLoader(filename);
	if (fl == null)
	{
		System.out.println("Error opening filter: "+filename);
		return;
	}

    Enumeration<ManifestItem> enumeration = m.entries();
    while(enumeration.hasMoreElements())
    {
        mi = enumeration.nextElement();
		value = mi.findValue("Java-Bean");
		if (value != null)
			if (value.equals("True"))
			{
				System.out.println("loading bean: "+mi.findValue("Name"));
				loadBean(mi.findValue("Name"));
			}
    }
}

public Vector loadDir(String dirname)
{
	File directory;
	String s[];
	String n;
	int i,len;

	directory = new File(dirname);

	s = directory.list();
	if (s == null)
		return null;

	len = s.length;

	for (i=0;i<len;i++)
		if (s[i].endsWith(".jar"))
		{
			n = dirname + File.separatorChar + s[i];
			System.out.println("scaning file: "+n);
			loadFile(n);
		}

	return filters;
}

}