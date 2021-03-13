package com.prasad.util;

/**
 * Base64 implements routines to encode and decode Base64 data
 **/
public class Base64 {
    static final int LINE_LENGTH = 76;
    static final char[] encodeChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
        'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
        'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
        'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
        '7', '8', '9', '+', '/'
    };
    static final char NOP = 255, EQL = 254;
    static final char[] decodeChars = {
        NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP,
        NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP,
        NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, 62, NOP, NOP, NOP, 63,
        52, 53, 54, 55, 56, 57, 58, 59, 60, 61, NOP, NOP, NOP, EQL, NOP, NOP,
        NOP, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, NOP, NOP, NOP, NOP, NOP,
        NOP, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
        41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, NOP, NOP, NOP, NOP, NOP,
        NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP,
        NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP,
        NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP,
        NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP,
        NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP,
        NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP,
        NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP,
        NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP, NOP
    };

    /**
     * Return a Base64 encoded string for the input string.
     *
     * @param input the string to encode
     **/
    public static String encode(String input) {
        return encode(input.getBytes());
    }

    /**
     * Return a Base64 encoded string for the input string.
     *
     * @param input the string to encode
     **/
    public static String encode(byte[] input) {
        StringBuffer output = new StringBuffer((int) (input.length * 1.333333));
        int bits, i = 0;
        if (input == null) return null;

        while (i < input.length) {
            bits = 0;
            try {
                bits = bits | (input[i] << 16);
                bits = bits | (input[i + 1] << 8);
                bits = bits | (input[i + 2]);
                output.append(encodeChars[(bits >> 18) & 0x3f]);
                output.append(encodeChars[(bits >> 12) & 0x3f]);
                output.append(encodeChars[(bits >> 6) & 0x3f]);
                output.append(encodeChars[bits & 0x3f]);
            } catch (ArrayIndexOutOfBoundsException e) {
                if (i == input.length - 2) {
                    // Encode last 2 bytes
                    output.append(encodeChars[(bits >> 18) & 0x3f]);
                    output.append(encodeChars[(bits >> 12) & 0x3f]);
                    output.append(encodeChars[(bits >> 6) & 0x3f]);
                    output.append('=');
                } else {
                    // Encode last byte
                    output.append(encodeChars[(bits >> 18) & 0x3f]);
                    output.append(encodeChars[(bits >> 12) & 0x3f]);
                    output.append('=');
                    output.append('=');
                }
            }
            i += 3;
            if (i >= LINE_LENGTH)
                output.append("\n");
        }
        return output.toString();
    }

    /**
     * Take a Base64 string and return the decoded information
     *
     * @param string the information to decode
     **/
    public static byte[] decode(String string) {
        if (string == null) return null;

        byte[] buffer = new byte[string.length()], input;
        char b1, b2, b3, b4;
        int bytes = 0, i = 0;

        input = string.getBytes();
        while (i < input.length) {
            try {
                while ((b1 = decodeChars[input[i++]]) == NOP)
                    ;
                while ((b2 = decodeChars[input[i++]]) == NOP)
                    ;
                while ((b3 = decodeChars[input[i++]]) == NOP)
                    ;
                while ((b4 = decodeChars[input[i++]]) == NOP)
                    ;
                if (b1 == EQL || b2 == EQL || (b3 == EQL && b4 != EQL)) {
                    System.err.println("Base-64 input was corrupt.");
                    return null;
                }
                if (b3 == EQL) {
                    buffer[bytes++] = (byte) ((b1 << 2) | (b2 >> 4));
                } else if (b4 == EQL) {
                    buffer[bytes++] = (byte) ((b1 << 2) | (b2 >> 4));
                    buffer[bytes++] = (byte) (((b2 & 0x0f) << 4) | (b3 >> 2));
                } else {
                    buffer[bytes++] = (byte) ((b1 << 2) | (b2 >> 4));
                    buffer[bytes++] = (byte) (((b2 & 0x0f) << 4) | (b3 >> 2));
                    buffer[bytes++] = (byte) (((b3 & 0x03) << 6) | b4);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Base-64 input was incomplete.");
                return null;
            }
        }
        byte[] output = new byte[bytes];
        System.arraycopy(buffer, 0, output, 0, bytes);
        return output;
    }
}