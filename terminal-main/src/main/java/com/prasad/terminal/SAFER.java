package com.prasad.terminal;
/*
 * $Id: SAFER.java,v 1.1 1999/10/19 18:27:22 bipin Exp $
 *
 * Copyright (c) 1997 Systemics Ltd
 * on behalf of the Cryptix Development Team.  All rights reserved.
 */

/**
 * A subclass of Cipher to implement the SAFER algorithm in Java.
 * <p>
 * SAFER (Secure And Fast Encryption Routine) is a block-cipher algorithm
 * developed by Prof. J.L. Massey at the Swiss Federal Institute of Technology.
 * SAFER is usable in four versions (referred to in this implementation as
 * VARIANTS): SAFER K-64, SAFER K-128, SAFER SK-64 and SAFER SK-128. The numerals
 * 64 and 128 stand for the length of the user-selected key, 'K' stands for the
 * original key schedule and 'SK' stands for the strengthened key schedule.
 * <p>
 * <b>References:</b>
 * <ol>
 *   <li> Massey, J.L.,
 *        "SAFER K-64: A Byte-Oriented Block Ciphering Algorithm", pp. 1-17 in
 *        Fast Software Encryption (Ed. R. Anderson),
 *        Proceedings of the Cambridge Security Workshop, Cambridge, U.K.,
 *        December 9-11, 1993,<br>
 *        <cite>Lecture Notes in Computer Science No. 809</cite>.
 *        Heidelberg and New York: Springer, 1994.
 * <p>
 *   <li> Massey, J.L.,
 *        "SAFER K-64: One Year Later",
 *        preliminary manuscript of a paper presented at the K. U. Leuven
 *        Workshop on Cryptographic Algorithms, December 14-16, 1994.<br>
 *        To be published in the Proceedings of this workshop by Springer.
 * <p>
 *   <li> Massey, J.L.,
 *        "Announcement of a Strengthened Key Schedule for the Cipher SAFER",
 *        Sept. 9, 1995, (see file 'SAFER_SK.TXT' included in the toolkit).
 * <p>
 *   <li> Richard De Moliner &lt;demoliner@isi.ee.ethz.ch&gt;
 *        <a href="ftp://ftp.isi.ee.ethz.ch/pub/simpl/safer.V1.2.tar.Z">
 *        SAFER toolkit V1.2</a>
 *        includes C implementation, additional notes, test data, test program.
 * </ol>
 * <p>
 * Ported to Java from public domain 'C' code latest revised on September
 * 9, 1995 by:
 * <blockquote>
 *     Richard De Moliner (demoliner@isi.ee.ethz.ch)<br>
 *     Signal and Information Processing Laboratory<br>
 *     Swiss Federal Institute of Technology<br>
 *     CH-8092 Z&uuml;rich, Switzerland.
 * </blockquote>
 * <p>
 * <b>Copyright</b> &copy; 1997
 * <a href="http://www.systemics.com/">Systemics Ltd</a> on behalf of the
 * <a href="http://www.systemics.com/docs/cryptix/">Cryptix Development Team</a>.
 * <br>All rights reserved.
 * <p>
 * <b>$Revision: 1.1 $</b>
 *
 * @author Raif S. Naffah
 * @author David Hopwood
 * @since Cryptix 2.2.2
 */
public final class SAFER // must be final for security reasons
{
    // SAFER variables and constants
    //...........................................................................

    private static final int
        SK128_VARIANT = 0,            // use as default
        SK64_VARIANT = 1,
        K128_VARIANT = 2,
        K64_VARIANT = 3;

    public static final int BLOCK_SIZE = 8;           // SAFER block size in bytes

    private static final int
        K64_DEFAULT_NOF_ROUNDS = 6,
        K128_DEFAULT_NOF_ROUNDS = 10,
        SK64_DEFAULT_NOF_ROUNDS = 8,
        SK128_DEFAULT_NOF_ROUNDS = 10,
        MAX_NOF_ROUNDS = 13,
        KEY_LENGTH = (1 + BLOCK_SIZE * (1 + 2 * MAX_NOF_ROUNDS)),
        TAB_LEN = 256;

    private int[] sKey;           // only the lower byte in each int is used
    private int rounds = SK128_DEFAULT_NOF_ROUNDS;
    private int variant = SK128_VARIANT;

    private /*static*/ final int[] EXP = new int[TAB_LEN];        // blank finals
    private /*static*/ final int[] LOG = new int[TAB_LEN];


// Static code
//...........................................................................

  /*static
  {
	int exp = 1;
	for (int i = 0; i < TAB_LEN; i++)
	{
		EXP[i] = exp & 0xFF;
		LOG[EXP[i]] = i;
		exp = exp * 45 % 257;
	}
  }
  */


// Constructor, finalizer, and clone()
//...........................................................................

    /**
     * Calls the Cipher constructor with <code>implBuffering</code> false,
     * <code>implPadding</code> false and the provider set to "Cryptix".
     * <p>
     * Sets the variant of this cipher based on the currently set "variant"
     * property in the provider properties file. The current JCE syntax
     * for the SAFER algorithm variant property is:
     * <pre>
     *    Alg.variant.SAFER = ...
     * </pre>
     * <p>
     * Valid alternatives for variant are:
     * <ul>
     *   <li> SK-128, SK128, sk-128 and sk128: Strengthened key schedule of
     *        length 128 bits.
     *   <li> SK-64, SK64, sk-64 and sk64: Strengthened key schedule of
     *        length 64 bits.
     *   <li> K-128, K128, k-128 and k128: Non-strengthened key schedule of
     *        length 128 bits.
     *   <li> K-64, K64, k-64 and k64: Non-strengthened key schedule of length
     *        64 bits.
     * </ul>
     * <p>
     * Once the variant is set, a default value for the number of rounds
     * to use is also set as follows:
     * <pre>
     *    Variant   Number of rounds = current value
     *    -------   --------------------------------
     *    SK-128    SK128_DEFAULT_NOF_ROUNDS = 10
     *    SK-64     SK64_DEFAULT_NOF_ROUNDS  =  8
     *    K-128     K128_DEFAULT_NOF_ROUNDS  = 10
     *    K-64      K64_DEFAULT_NOF_ROUNDS   =  6
     * </pre>
     * <p>
     * If no variant property is found in the provider's properties file
     * a strengthened key schedule of 128 bits is used with 10 rounds.
     * <p>
     * This constructor also attempts to set the desired number of rounds
     * for this cipher object from a "rounds" property in the provider's
     * properties file. Acceptable values are non-zero integers between 1
     * and the MAX_NOF_ROUNDS constant; i.e. 13. If no such property is
     * found, or is found but deemed invalid, then the already set value
     * (depending on the variant property as determined above) remains
     * unaltered.
     */
    public SAFER() {
        int exp = 1;
        for (int i = 0; i < TAB_LEN; i++) {
            EXP[i] = exp & 0xFF;
            LOG[EXP[i]] = i;
            exp = exp * 45 % 257;
        }

        sKey = new int[KEY_LENGTH];
        switch (variant) {
            case SK128_VARIANT:
                rounds = SK128_DEFAULT_NOF_ROUNDS;
                break;
            case SK64_VARIANT:
                rounds = SK64_DEFAULT_NOF_ROUNDS;
                break;
            case K128_VARIANT:
                rounds = K128_DEFAULT_NOF_ROUNDS;
                break;
            case K64_VARIANT:
                rounds = K64_DEFAULT_NOF_ROUNDS;
                break;
        }
    }

    /**
     * Always throws a CloneNotSupportedException (cloning of ciphers is not
     * supported for security reasons).
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * <b>SPI</b>: Initializes this cipher for encryption, using the
     * specified key.
     *
     * @param key the key to use for encryption.
     */
    public void engineInitEncrypt(String key) {
        makeCryptKey(key);
        setEncrypting(true);
    }

    /**
     * <b>SPI</b>: Initializes this cipher for decryption, using the
     * specified key.
     *
     * @param key the key to use for decryption.
     */
    public void engineInitDecrypt(String key) {
        makeCryptKey(key);
        setEncrypting(false);
    }

    /**
     * <b>SPI</b>: This is the main engine method for updating data.
     * <p>
     * <i>in</i> and <i>out</i> may be the same array, and the input and output
     * regions may overlap.
     *
     * @param in        the input data.
     * @param inOffset  the offset into in specifying where the data starts.
     * @param inLen     the length of the subarray.
     * @param out       the output array.
     * @param outOffset the offset indicating where to start writing into
     *                  the out array.
     * @return the number of bytes written.
     * @throws CryptixException if the native library is being used, and it
     *                          reports an error.
     */
    protected int
    engineUpdate(byte[] in, int inOffset, int inLen, byte[] out, int outOffset) {
        if (inLen < 0)
            throw new IllegalArgumentException("inLen < 0");
        int blockCount = inLen / BLOCK_SIZE;
        inLen = blockCount * BLOCK_SIZE;

        boolean doEncrypt = isEncrypting();

        // Avoid overlapping input and output regions.
        if (in == out
            && (outOffset >= inOffset
            && outOffset < (long) inOffset + inLen
            || inOffset >= outOffset
            && inOffset < (long) outOffset + inLen)) {
            byte[] newin = new byte[inLen];
            System.arraycopy(in, inOffset, newin, 0, inLen);
            in = newin;
            inOffset = 0;
        }
        if (doEncrypt) {
            for (int i = 0; i < blockCount; i++) {
                blockEncrypt(in, inOffset, out, outOffset);
                inOffset += BLOCK_SIZE;
                outOffset += BLOCK_SIZE;
            }
        } else {
            for (int i = 0; i < blockCount; i++) {
                blockDecrypt(in, inOffset, out, outOffset);
                inOffset += BLOCK_SIZE;
                outOffset += BLOCK_SIZE;
            }
        }
        return inLen;
    }

    /**
     * Encryption algorithm.
     *
     * @param in     contains the plaintext block.
     * @param inOff  start index within input where data should be considered.
     * @param out    will contain the ciphertext block.
     * @param outOff index in out where ciphertext starts.
     */
    private void blockEncrypt(byte[] in, int inOff, byte[] out, int outOff) {
        int k = 0,
            round = sKey[k++];

        if (MAX_NOF_ROUNDS < round)
            round = MAX_NOF_ROUNDS;

        int t,
            a = in[inOff++],
            b = in[inOff++],
            c = in[inOff++],
            d = in[inOff++],
            e = in[inOff++],
            f = in[inOff++],
            g = in[inOff++],
            h = in[inOff++];
        for (int i = 0; i < round; i++) {
            a ^= sKey[k++];
            b += sKey[k++];
            c += sKey[k++];
            d ^= sKey[k++];
            e ^= sKey[k++];
            f += sKey[k++];
            g += sKey[k++];
            h ^= sKey[k++];

            a = EXP[a & 0xFF] + sKey[k++];
            b = LOG[b & 0xFF] ^ sKey[k++];
            c = LOG[c & 0xFF] ^ sKey[k++];
            d = EXP[d & 0xFF] + sKey[k++];
            e = EXP[e & 0xFF] + sKey[k++];
            f = LOG[f & 0xFF] ^ sKey[k++];
            g = LOG[g & 0xFF] ^ sKey[k++];
            h = EXP[h & 0xFF] + sKey[k++];

            b += a;
            a += b;            //    PHT(a, b);
            d += c;
            c += d;            //    PHT(c, d);
            f += e;
            e += f;            //    PHT(e, f);
            h += g;
            g += h;            //    PHT(g, h);
            c += a;
            a += c;            //    PHT(a, c);
            g += e;
            e += g;            //    PHT(e, g);
            d += b;
            b += d;            //    PHT(b, d);
            h += f;
            f += h;            //    PHT(f, h);
            e += a;
            a += e;            //    PHT(a, e);
            f += b;
            b += f;            //    PHT(b, f);
            g += c;
            c += g;            //    PHT(c, g);
            h += d;
            d += h;            //    PHT(d, h);

            t = b;
            b = e;
            e = c;
            c = t;
            t = d;
            d = f;
            f = g;
            g = t;
        }
        out[outOff++] = (byte) (a ^ sKey[k++]);
        out[outOff++] = (byte) (b + sKey[k++]);
        out[outOff++] = (byte) (c + sKey[k++]);
        out[outOff++] = (byte) (d ^ sKey[k++]);
        out[outOff++] = (byte) (e ^ sKey[k++]);
        out[outOff++] = (byte) (f + sKey[k++]);
        out[outOff++] = (byte) (g + sKey[k++]);
        out[outOff++] = (byte) (h ^ sKey[k++]);
    }

    /**
     * Decryption algorithm.
     *
     * @param in     contains the ciphertext block.
     * @param inOff  index within input where cipher data should be considered
     * @param out    will contain the plaintext block.
     * @param outOff index in out where plaintext starts.
     */
    private void blockDecrypt(byte[] in, int inOff, byte[] out, int outOff) {
        int round = sKey[0];
        if (MAX_NOF_ROUNDS < round)
            round = MAX_NOF_ROUNDS;

        int t,
            a = in[inOff++],
            b = in[inOff++],
            c = in[inOff++],
            d = in[inOff++],
            e = in[inOff++],
            f = in[inOff++],
            g = in[inOff++],
            h = in[inOff++];

        int k = BLOCK_SIZE * (1 + 2 * round);

        h ^= sKey[k];
        g -= sKey[--k];
        f -= sKey[--k];
        e ^= sKey[--k];
        d ^= sKey[--k];
        c -= sKey[--k];
        b -= sKey[--k];
        a ^= sKey[--k];

        for (int i = 0; i < round; i++) {
            t = e;
            e = b;
            b = c;
            c = t;
            t = f;
            f = d;
            d = g;
            g = t;

            a -= e;
            e -= a;            //    IPHT(a, e);
            b -= f;
            f -= b;            //    IPHT(b, f);
            c -= g;
            g -= c;            //    IPHT(c, g);
            d -= h;
            h -= d;            //    IPHT(d, h);
            a -= c;
            c -= a;            //    IPHT(a, c);
            e -= g;
            g -= e;            //    IPHT(e, g);
            b -= d;
            d -= b;            //    IPHT(b, d);
            f -= h;
            h -= f;            //    IPHT(f, h);
            a -= b;
            b -= a;            //    IPHT(a, b);
            c -= d;
            d -= c;            //    IPHT(c, d);
            e -= f;
            f -= e;            //    IPHT(e, f);
            g -= h;
            h -= g;            //    IPHT(g, h);

            h -= sKey[--k];
            g ^= sKey[--k];
            f ^= sKey[--k];
            e -= sKey[--k];
            d -= sKey[--k];
            c ^= sKey[--k];
            b ^= sKey[--k];
            a -= sKey[--k];

            h = LOG[h & 0xFF] ^ sKey[--k];
            g = EXP[g & 0xFF] - sKey[--k];
            f = EXP[f & 0xFF] - sKey[--k];
            e = LOG[e & 0xFF] ^ sKey[--k];
            d = LOG[d & 0xFF] ^ sKey[--k];
            c = EXP[c & 0xFF] - sKey[--k];
            b = EXP[b & 0xFF] - sKey[--k];
            a = LOG[a & 0xFF] ^ sKey[--k];
        }
        out[outOff++] = (byte) a;
        out[outOff++] = (byte) b;
        out[outOff++] = (byte) c;
        out[outOff++] = (byte) d;
        out[outOff++] = (byte) e;
        out[outOff++] = (byte) f;
        out[outOff++] = (byte) g;
        out[outOff++] = (byte) h;
    }

    /**
     * Expands a userKey to a working SAFER key (sKey).
     * <p>
     * The key bytes are fist extracted from the user-key and formatted
     * into a 16-byte array (128 bits) which is then passed to the
     * Safer_Expand_Userkey() method. The length of the array is known
     * by the currently set variant of this object. If there isn't enough
     * bytes in the user key to make a valid SAFER user-key (64 or 128
     * bits), the user-key is either trunctated or copied appropriately
     * to obtain enough bytes for the Safer_Expand_Userkey() method. An
     * exception is thrown only if the user-key is null;
     */
    private synchronized void makeCryptKey(String key) {
        if (key == null)
            key = "bcwLJEJDSK";
        byte[] keyBytes = key.getBytes();

        byte[] userKey = new byte[2 * BLOCK_SIZE];
        int keyLen = keyBytes.length,
            len = 2 * BLOCK_SIZE,
            userKeyLenSoFar = 0;

        while (len >= keyLen) {
            System.arraycopy(keyBytes, 0, userKey, userKeyLenSoFar, keyLen);
            len -= keyLen;
            userKeyLenSoFar += keyLen;
        }
        System.arraycopy(keyBytes, 0, userKey, userKeyLenSoFar, len);

        byte[]
            key1 = new byte[BLOCK_SIZE],
            key2 = new byte[BLOCK_SIZE];

        System.arraycopy(userKey, 0, key1, 0, BLOCK_SIZE);
        System.arraycopy(userKey, BLOCK_SIZE, key2, 0, BLOCK_SIZE);
        Safer_Expand_Userkey(key1, key2);
    }

    /**
     * Expands a user-selected key of length 64 bits or 128 bits to the
     * encryption / decryption sKey.
     * <p>
     * Note: SAFER K-64 and SAFER SK-64 with a user-selected key 'z' of
     * length 64 bits are identical to SAFER K-128 and SAFER SK-128 with
     * a user-selected key 'z z' of length 128 bits, respectively.
     *
     * @param userkey_1 contains the first 64 bits of user key.
     * @param userkey_2 contains the second 64 bits of user key.
     */
    private void Safer_Expand_Userkey(byte[] userkey_1, byte[] userkey_2) {
        byte[]
            ka = new byte[BLOCK_SIZE + 1],
            kb = new byte[BLOCK_SIZE + 1];

        int k = 0;
        sKey[k++] = (byte) rounds;
        for (int j = 0; j < BLOCK_SIZE; j++) {
            ka[j] = (byte) (userkey_1[j] << 5 | (userkey_1[j] & 0xFF) >>> 3);
            ka[BLOCK_SIZE] ^= ka[j];
            sKey[k++] = userkey_2[j];
            kb[j] = userkey_2[j];
            kb[BLOCK_SIZE] ^= kb[j];
        }
        for (int i = 1; i <= rounds; i++) {
            for (int j = 0; j < BLOCK_SIZE + 1; j++) {
                ka[j] = (byte) (ka[j] << 6 | (ka[j] & 0xFF) >>> 2);
                kb[j] = (byte) (kb[j] << 6 | (kb[j] & 0xFF) >>> 2);
            }
            for (int j = 0; j < BLOCK_SIZE; j++) {
                if (isStrong())
                    sKey[k++] = (ka[(j + 2 * i - 1) % (BLOCK_SIZE + 1)]
                        + EXP[EXP[18 * i + j + 1]]) & 0xFF;
                else
                    sKey[k++] = (ka[j] + EXP[EXP[18 * i + j + 1]]) & 0xFF;
            }
            for (int j = 0; j < BLOCK_SIZE; j++) {
                if (isStrong())
                    sKey[k++] = (kb[(j + 2 * i) % (BLOCK_SIZE + 1)]
                        + EXP[EXP[18 * i + j + 10]]) & 0xFF;
                else
                    sKey[k++] = (kb[j] + EXP[EXP[18 * i + j + 10]]) & 0xFF;
            }
        }
    }

    /**
     * Returns true if this cipher should use a strengthened key schedule.
     */
    private boolean isStrong() {
        return (variant < K128_VARIANT);
    }

    private boolean encrypting;

    private void setEncrypting(boolean flag) {
        encrypting = flag;
    }

    private boolean isEncrypting() {
        return encrypting;
    }
}// end of class SAFER

