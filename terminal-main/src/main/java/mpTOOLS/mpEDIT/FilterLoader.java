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

public class FilterLoader extends java.lang.ClassLoader
{

private final int MAX_READS = 30;
private String archive = null;
private Hashtable classes = null;

public FilterLoader(String a)
{
	super();
	classes = new Hashtable();
	archive = new String(a);
}

public Class loadClass(String className, boolean resolveIt) throws ClassNotFoundException
{
	Class c;
	String beanName;

	beanName = className.replace('.','/') + ".class";

	if (classes.containsKey(className))
		return (Class)classes.get(className);

	try
	{
		byte classData[] = extractClass(beanName, archive);
		if ( classData != null )
		{
			c = defineClass(className, classData, 0, classData.length);
		}
		else
		{
			ClassLoader loader = getClass().getClassLoader();
			if(loader != null)
				c = loader.loadClass(className);
			else
				c = findSystemClass(className);
		}
		classes.put(className,c);
	}

	catch (ClassNotFoundException ze)
	{
		System.out.println("Error: ClassNotFoundException");
		throw new ClassNotFoundException();
	}

	catch (IOException ioe)
	{
		System.out.println("Error: IOException 1");
		throw new ClassNotFoundException();
	}

	if (resolveIt)
	{
		resolveClass((Class) classes.get(className));
	}

	return (Class) classes.get(className);
}

private byte[] extractClass(String className, String archive)
throws ClassNotFoundException, IOException
{
	byte theClass[] = null;
	int ct,total,limit,tries;
	
	tries = total = 0;

	try
	{
		ZipFile   zipFile  = new ZipFile(archive);
		ZipEntry  zipEntry = zipFile.getEntry(className);

		if (zipEntry == null)
			return null;
			
		byte bytes[] = new byte[(int) zipEntry.getSize()];
		InputStream inputStream = zipFile.getInputStream(zipEntry);
		
		limit = (int)zipEntry.getSize();

		while ((tries < MAX_READS) && (total < limit))
		{
		    ct = inputStream.read(bytes,total,limit-total);
		    total = total + ct;
		    if (ct == 0)
			tries++;
		}
	
		inputStream.close();

		if (tries < MAX_READS)
		    theClass = bytes;
		else
		    theClass = null;
	}

	catch(ZipException e)
	{
		// output error 
		System.out.println("Error: ZipException");
		throw new ClassNotFoundException();
	}

	catch(IOException e)
	{
		// output error 
		System.out.println("Error: IOException 2");
		throw new IOException();
	}

	return theClass;
}

}
