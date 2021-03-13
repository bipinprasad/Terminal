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

public class ManifestItem
{
private String keys[];
private String values[];
private int nkeys;

public String findValue(String k)
{
    for (int i=0 ; i < nkeys;  i++)
        if (k.equalsIgnoreCase(keys[i]))
        return values[i];

    return null;
}

private void grow()
{
    if (keys == null || nkeys >= keys.length)
    {
        String[] nk = new String[nkeys + 4];
        String[] nv = new String[nkeys + 4];
        if (keys != null)
            System.arraycopy(keys, 0, nk, 0, nkeys);
        if (values != null)
            System.arraycopy(values, 0, nv, 0, nkeys);
        keys = nk;
        values = nv;
    }
}

public void add(String k, String v)
{
    grow();
    keys[nkeys] = k;
    values[nkeys] = v;
    nkeys++;
}

public boolean read(InputStream is) throws java.io.IOException
{
    boolean eof = false;

    nkeys = 0;

    if (is == null)
        return false;

    char s[] = new char[10];
    int firstc = is.read();
    if (firstc < 0)
        eof = true;

    while (firstc != '\n' && firstc != '\r' && firstc >= 0)
    {
        int len = 0;
        int keyend = -1;
        int c;
        boolean inKey = firstc > ' ';
        s[len++] = (char) firstc;
    parseloop:
        {
            while ((c = is.read()) >= 0)
            {
                if (c < 0) eof = true;
                switch (c)
                {
                    case ':':
                        if (inKey && len > 0)
                            keyend = len;
                        inKey = false;
                        break;
                    case '\t':
                        c = ' ';
                    case ' ':
                        inKey = false;
                        break;
                    case '\r':
                    case '\n':
                        firstc = is.read();
                        if (firstc < 0) eof = true;
                        if (c == '\r' && firstc == '\n')
                        {
                            firstc = is.read();
                            if (firstc == '\r')
                                firstc = is.read();
                            if (firstc < 0) eof = true;
                        }
                        if (firstc == '\n' || firstc == '\r' || firstc > ' ')
                            break parseloop;
				/* continuation */
                        c = ' ';
                        break;
                }
                if (len >= s.length)
                {
                    char ns[] = new char[s.length * 2];
                    System.arraycopy(s, 0, ns, 0, len);
                    s = ns;
                }
                s[len++] = (char) c;
            }
            firstc = -1;
        }
        while (len > 0 && s[len - 1] <= ' ')
            len--;
        String k;
        if (keyend <= 0)
        {
            k = null;
            keyend = 0;
        }
        else
        {
            k = String.copyValueOf(s, 0, keyend);
            if (keyend < len && s[keyend] == ':')
                keyend++;
            while (keyend < len && s[keyend] <= ' ')
                keyend++;
        }
        String v;
        if (keyend >= len)
            v = new String();
        else
            v = String.copyValueOf(s, keyend, len - keyend);
        add(k, v);
    }

    return !eof;
}

@Override
public String toString()
{
    String result = super.toString();

    for (int i = 0; i < keys.length; i++)
        result += "{"+keys[i]+": "+values[i]+"}\n";

    return result;
}
}
