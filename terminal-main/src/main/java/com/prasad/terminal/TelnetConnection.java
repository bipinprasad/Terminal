package com.prasad.terminal;

import java.lang.reflect.*;
import java.applet.Applet;
import java.awt.*;
import java.io.*;
import java.net.*;

public class TelnetConnection implements Runnable {
  /* ------------------------------------------------------------
	 CONSTANT VALUES SECTION
	 ------------------------------------------------------------ */

  /* TELNET protocol commands.  A command consists of a 0xFF byte
	 followed by one of the following codes. */

    static final int IAC = 255;   /* Interpret next by as a command;
					 IAC IAC is a 255 data byte. */

    // The following five are followed by a byte indicating the option.

    static final int DONT = 254;   /* Ask the remote system to stop
					 performing an option, or confirm
					 we no longer expect it to perform
					 the option. */
    static final int DO = 253;   /* Ask the remote system to start
					 performing an option, or confirm
					 we now expect it to perform
					 the option. */
    static final int WONT = 252;   /* Refuse to perform or continue
					 performing an option. */
    static final int WILL = 251;   /* Indicate desire to begin performing
					 an option or confirm that it is
					 now being performed. */
    static final int SB = 250;   /* Perform subnegotiation of the option. */

    static final int GA = 249;   /* Go Ahead */
    static final int EL = 248;   /* Erase Line */
    static final int EC = 247;   /* Erase Character */
    static final int AYT = 246;   /* Are You There */
    static final int AO = 245;   /* Abort Output */
    static final int IP = 244;   /* Interrupt Process */
    static final int BREAK = 243;   /* Break */
    static final int DM = 242;   /* Data Mark:
				     Data stream portion of Synch. */
    static final int NOP = 241;   /* No Operation */
    static final int SE = 240;   /* End of subnegotiation */
    static final int EOR = 239;
    static final int ABORT = 238;
    static final int SUSP = 237;
    static final int xEOF = 236;

    static final int SYNC = 242;

    static final int TELCMD_FIRST = xEOF;
    static final int TELCMD_LAST = IAC;

    static final String[] telcmds = {
        "EOF", "SUSP", "ABORT", "EOR",
        "SE", "NOP", "DMARK", "BRK", "IP", "AO", "AYT", "EC",
        "EL", "GA", "SB", "WILL", "WONT", "DO", "DONT", "IAC",
        null
    };

  /* TELNET protocol options.  Most of these are not implementented; the
     commented ones are implemented.

     XXX We really need to be better about respecting things like BINARY,
     LINEMODE, etc. */

    static final int TELOPT_BINARY = 0;

    // We ask for the remote to do so.
    static final int TELOPT_ECHO = 1;

    static final int TELOPT_RCP = 2;

    // Suppress Go Ahead.  We ask for the remote to do so.
    static final int TELOPT_SGA = 3;

    static final int TELOPT_NAMS = 4;
    static final int TELOPT_STATUS = 5;
    static final int TELOPT_TM = 6;
    static final int TELOPT_RCTE = 7;
    static final int TELOPT_NAOL = 8;
    static final int TELOPT_NAOP = 9;
    static final int TELOPT_NAOCRD = 10;
    static final int TELOPT_NAOHTS = 11;
    static final int TELOPT_NAOHTD = 12;
    static final int TELOPT_NAOFFD = 13;
    static final int TELOPT_NAOVTS = 14;
    static final int TELOPT_NAOVTD = 15;
    static final int TELOPT_NAOLFD = 16;
    static final int TELOPT_XASCII = 17;
    static final int TELOPT_LOGOUT = 18;
    static final int TELOPT_BM = 19;
    static final int TELOPT_DET = 20;
    static final int TELOPT_SUPDUP = 21;
    static final int TELOPT_SUPDUPOUTPUT = 22;
    static final int TELOPT_SNDLOC = 23;

    // Negotiate terminal type.
    static final int TELOPT_TTYPE = 24;

    static final int TELOPT_EOR = 25;
    static final int TELOPT_TUID = 26;
    static final int TELOPT_OUTMRK = 27;
    static final int TELOPT_TTYLOC = 28;
    static final int TELOPT_3270REGIME = 29;
    static final int TELOPT_X3PAD = 30;

    // Negotiate about window size.
    static final int TELOPT_NAWS = 31;

    static final int TELOPT_TSPEED = 32;
    static final int TELOPT_LFLOW = 33;
    static final int TELOPT_LINEMODE = 34;
    static final int TELOPT_XDISPLOC = 35;
    static final int TELOPT_OLD_ENVIRON = 36;
    static final int TELOPT_AUTHENTICATION = 37;
    static final int TELOPT_ENCRYPT = 38;
    static final int TELOPT_NEW_ENVIRON = 39;
    static final int TELOPT_EXOPL = 255;

    // ASCII names of the telnet options.

    static final String[] telopts = {
        /*  1 */ "BINARY", "ECHO", "RCP", "SUPPRESS GO AHEAD", "NAME",
        /*  2 */ "STATUS", "TIMING MARK", "RCTE", "NAOL", "NAOP",
        /*  3 */ "NAOCRD", "NAOHTS", "NAOHTD", "NAOFFD", "NAOVTS",
        /*  4 */ "NAOVTD", "NAOLFD", "EXTEND ASCII", "LOGOUT", "BYTE MACRO",
        /*  5 */ "DATA ENTRY TERMINAL", "SUPDUP", "SUPDUP OUTPUT",
        /*  6 */ "SEND LOCATION", "TERMINAL TYPE", "END OF RECORD",
        /*  7 */ "TACACS UID", "OUTPUT MARKING", "TTYLOC",
        /*  8 */ "3270 REGIME", "X.3 PAD", "NAWS", "TSPEED", "LFLOW",
        /*  9 */ "LINEMODE", "XDISPLOC", "OLD-ENVIRON", "AUTHENTICATION",
        /* 10 */ "ENCRYPT", "NEW-ENVIRON",
        null
    };

    // The local options that we have implemented

    static final boolean[] locopts_impl = {
        /*  1 */ true, false, false, true, false,
        /*  2 */ false, false, false, false, false,
        /*  3 */ false, false, true, false, false,
        /*  4 */ false, false, false, false, false,
        /*  5 */ false, false, false,
        /*  6 */ false, true, false,
        /*  7 */ false, false, false,
        /*  8 */ false, false, true, true, false,
        /*  9 */ false, false, false, false,
        /* 10 */ false, false,
        false
    };

    // The remote options that we have implemented

    static final boolean[] remopts_impl = {
        /*  1 */ true, true, false, true, false,
        /*  2 */ false, false, false, false, false,
        /*  3 */ false, false, false, false, false,
        /*  4 */ false, false, false, false, false,
        /*  5 */ false, false, false,
        /*  6 */ false, false, false,
        /*  7 */ false, false, false,
        /*  8 */ false, false, false, false, false,
        /*  9 */ false, false, false, false,
        /* 10 */ false, false,
        false
    };

    static final int TELOPT_FIRST = TELOPT_BINARY;
    static final int TELOPT_LAST = TELOPT_NEW_ENVIRON;

  /* ------------------------------------------------------------
	 INSTANCE DATA SECTION
	 ------------------------------------------------------------ */

  /* Common instance data, used by all threads.  Methods that access
	 it must be protected with the synchronized keyword. */

    TelnetInterface emulator;           // Data send/receive emulator.
    boolean connected = false;  // Is there an active connection?
    Socket s;                  // Network socket.
    DataOutputStream out;                // Stream used to write socket.
    Thread thread;             // Thread for reading socket.
    boolean isTelnet = false;   // An actual TELNET session?

    int curWidth = 0,
        curHeight = 0;    // Current window dimensions.

    // Current local and remote option status.
    boolean[] loc_options = new boolean[TELOPT_LAST - TELOPT_FIRST + 1];
    boolean[] rem_options = new boolean[TELOPT_LAST - TELOPT_FIRST + 1];

  /* Input instance data.  This may only be accessed by the input
	 thread. */

    DataInputStream in;                // Stream used to read input.
    char[] parseBuff = null;    // Transfer data from main input loop
    int parsePos = 0;    // to TELNET command parser.

  /* ------------------------------------------------------------
	 CONTRUCTORS AND GLOBAL CONTROL METHODS
	 ------------------------------------------------------------ */

    public TelnetConnection(TelnetInterface emulator) {
        setEmulator(emulator);
    }

    public TelnetConnection(TelnetInterface emulator, String host, int port) {
        this(emulator);
        connect(host, port);
    }

    public synchronized void setEmulator(TelnetInterface emulator) {
        this.emulator = emulator;
        if (emulator != null) {
            curWidth = emulator.getNumCols();
            curHeight = emulator.getTelnetNumRows();
        } else {
            curWidth = 0;
            curHeight = 0;
        }
    }

    //public TelnetInterface getEmulator()
    //{
    //return emulator;
    //}

    public synchronized String getHost() {
        if (s != null) {
            InetAddress ia = s.getInetAddress();
            if (ia != null)
                return ia.getHostName();
        }
        return null;
    }

    public synchronized int getPort() {
        if (s != null) {
            return s.getPort();
        }
        return -1;
    }

    public synchronized boolean connect(String host, int port) {
        boolean handled_error = false;
        disconnect();
        isTelnet = false;

        if (emulator != null) {
            emulator.receive("\r\nOpening connection to " + host + "...\r\n");
        }
        if (debug)
            System.out.println("LaTerm: Opening socket at " + host + " / " + port);

        try {
            s = new Socket(host, port);

            if (debug)
                System.out.println("LaTerm: Getting input stream...");
            in = new DataInputStream(s.getInputStream());
            if (debug)
                System.out.println("LaTerm: Getting output stream...");
            out = new DataOutputStream(s.getOutputStream());
            connected = true;
            if (debug)
                System.out.println("LaTerm: Starting input thread...");
            thread = new Thread(this, "Telnet input monitor");
            if (debug)
                System.out.println("LaTerm: Thread: " + thread);
            // This is now an insecure function in Netscape 3.0!
            // thread.setDaemon(true);
            thread.start();
        } catch (UnknownHostException e) {
            if (emulator != null) {
                emulator.receive("\r\nUnable to connect to " + host +
                    ":\r\nHost unknown.  " +
                    "Make sure the host name is spelled correctly.\r\n");
                handled_error = true;
            }
        } catch (IOException e) {
            if (emulator != null) {
                emulator.receive("\r\n*** " + e.toString() + "\r\n");
            }
        } catch (Exception e) {
            if (emulator != null) {
                if (e.getClass().getName().equals("netscape.applet.33AppletSecurityException")) {
                    emulator.receive("\r\nUnable to connect to " + host +
                        ":\r\nSecurity violation.  " +
                        "Applets may only connect to their own host.\r\n");
                    handled_error = true;
                } else {
                    emulator.receive("\r\n*** " + e.toString() + "\r\n");
                }
            }
        } finally {
            if (!connected) {
                if (debug)
                    System.out.println("LaTerm: Oops, disconnecting.");
                disconnect();
                if (emulator != null && !handled_error) {
                    emulator.receive("\r\nUnable to connect to host: " + host + "\r\n");
                }
                return false;
            } else {
                if (emulator != null)
                    //emulator.connect(host,port);
                    emulator.connected();
            }
        }
        if (debug)
            System.out.println("LaTerm: Connection complete.");
        if (debug)
            System.out.println("LaTerm: Active threads: " + Thread.activeCount());
        return true;
    }

    public synchronized void disconnect() {
        if (thread != null && thread != Thread.currentThread()) {
            if (debug)
                System.out.println("LaTerm: Stopping input thread.");
            thread.stop();
        }
        if (connected && emulator != null)
            //emulator.disconnect();
            emulator.disconnected();

        if (s != null) {
            if (emulator != null) {
                emulator.receive("\r\nConnection closed.\r\n");
            }
        }
        try {
            if (out != null)
                out.close();
            if (in != null)
                in.close();
            if (s != null)
                s.close();
        } catch (IOException e) {
            if (emulator != null) {
                emulator.receive("\r\n" + e.toString() + "\r\n");
            }
        } finally {
            thread = null;
            out = null;
            in = null;
            s = null;
            connected = false;
            isTelnet = false;
            for (int i = 0; i < loc_options.length; i++) {
                loc_options[i] = rem_options[i] = false;
            }
            if (debug)
                System.out.println("LaTerm: Active threads: " + Thread.activeCount());
        }
    }

  /* ------------------------------------------------------------
	 DATA SENDING METHODS
	 ------------------------------------------------------------ */

    public synchronized void setWindowSize(int width, int height) {
        curWidth = width;
        curHeight = height;
        try {
            send_naws(width, height);
        } catch (IOException e) {
            if (emulator != null) {
                emulator.receive("\r\n" + e.toString() + "\r\n");
            }
        }
    }

    public synchronized void write(byte b) throws IOException {
        if (out != null) {
            if (debug)
                System.out.println("LaTerm: Put: " + (char) b + " (" + b + ") hex [" + Integer.toHexString(b) + "]");
            out.write(b);
            if (!rem_options[TELOPT_ECHO] && emulator != null) {
                emulator.receive((char) b);
            }
        }
    }

    public synchronized void write(byte[] b) throws IOException {
        if (out != null) {
            if (debug) {
                String str = new String(b, 0);
                System.out.print("LaTerm: Put: " + str + " (" + b + ") hex");
                for (int i = 0; i < b.length; i++)
                    System.out.print(" " + Integer.toHexString(b[i]));
                System.out.println("");
            }
            out.write(b);
            if (!rem_options[TELOPT_ECHO] && emulator != null) {
                String str = new String(b, 0);
                emulator.receive(str);
            }
        }
    }

    public synchronized void write(String str) throws IOException {
        if (out != null) {
            byte[] b = new byte[str.length()];
            str.getBytes(0, str.length(), b, 0);
            if (debug) {
                System.out.print("LaTerm: Put: " + str + " (" + b + ") hex");
                for (int i = 0; i < b.length; i++)
                    System.out.print(" " + Integer.toHexString(b[i]));
                System.out.println("");
            }
            out.write(b);
            if (!rem_options[TELOPT_ECHO] && emulator != null) {
                emulator.receive(str);
            }
        }
    }

    public void flush() {
        try {
            if (out != null)
                out.flush();
        } catch (Exception e) {
        }
    }
  /* ------------------------------------------------------------
	 INPUT THREAD METHODS
	 ------------------------------------------------------------ */

  /* Entry point and main loop.
	 Note well: we must be careful that any methods that read
	 from the stream (either directly with in.read(), or indirectly
	 with next_char()) are not synchronized.  Otherwise, they may
	 block and leave other threads unable to access the class and
	 write more data to the socket. */

    private static final int MAX_INPUT_BUFLEN = 10240;
    byte[] b = new byte[MAX_INPUT_BUFLEN];
    char[] c = new char[MAX_INPUT_BUFLEN];

    @Override
    public void run() {
        boolean client_kill = false;
        if (debug)
            System.out.println("LaTerm: Telnet input thread has started.");
        if (debug)
            System.out.println("LaTerm: Telnet thread: " + Thread.currentThread());
        try {
            for (; ; ) {
                if (debug)
                    System.out.println("Telnet.run(): At the top of for loop");
                int len = in.available();
                if (debug)
                    System.out.println("Telnet.run(): in.available() = " + len);
                if (len > 1) {
                    if (len > MAX_INPUT_BUFLEN)
                        len = MAX_INPUT_BUFLEN;
                    if (debug)
                        System.out.println("LaTerm: Ready to read " + len + " bytes.");
                    len = in.read(b, 0, len);
                    if (debug)
                        System.out.println("LaTerm: Actually read " + len + " bytes.");
                    if (len < 0)
                        break;

				/*
				String	str = new String(b,0,len);
				char[]	c	= str.toCharArray();
				len = c.length;
				*/
                    for (int i = 0; i < len; i++) {
                        int tmpByte = b[i];
                        if (tmpByte < 0)
                            tmpByte += 256;
                        c[i] = (char) tmpByte;
                    }

                    if (debug) {
                        System.out.println("LaTerm: Processing: " + emulator.sequenceToString(new String(b, 0, len)));
                    }
                    for (int i = 0; i < len; ) {
                        int j = i;
                        while (j < len && c[j] != IAC)
                            j++;
                        if (j > i)
                            process_data(c, i, j - i);
                        parseBuff = c;
                        parsePos = j + 1;
                        if (j < len)
                            process_cmd(c[j]);
                        i = parsePos;
                    }
                    parseBuff = null;
                    if (debug)
                        System.out.println("Telnet.run(): Processed multiple characters");
                } else {
                    int b;
                    if (debug)
                        System.out.println("LaTerm: Ready to read a character.");
                    b = in.read();
                    parseBuff = null;
                    if (b < 0)
                        break;
                    if (b == IAC)
                        process_cmd(b);
                    else
                        process_data((char) b);
                    if (debug)
                        System.out.println("Telnet.run(): Processed single character");
                }
                if (debug)
                    System.out.println("Telnet.run(): At the bottom of for loop");
            }
        } catch (IOException e) {
            if (emulator != null) {
                emulator.receive("\r\n" + e.toString() + "\r\n");
                e.printStackTrace();
            }
        } finally {
            if (in != null
                && !client_kill
                && emulator != null) {
                //emulator.receive("\r\nConnection closed by server.\r\n");
                disconnect();
            }
            try {
                if (in != null) in.close();
                in = null;
            } catch (IOException e) {
            }
        }
        if (debug)
            System.out.println("LaTerm: Input thread is terminating.");
        //System.exit(0);
    }

    /* Methods for handling standard data.  */

    void process_data(char b) throws IOException {
        if (debug)
            System.out.println("LaTerm: Got: " + emulator.charToString(b));
        if (emulator != null) {
            emulator.receive(b);
        }
    }

    void process_data(char[] b, int off, int len) throws IOException {
        if (debug)
            System.out.println("LaTerm: Got: " + emulator.sequenceToString(new String(b, off, len)));

        if (emulator != null)
            emulator.receive(b, off, len);
    }

    /* Retrieve command's next character */

    char next_char() throws IOException {
        if (parseBuff != null && parsePos < parseBuff.length) {
            parsePos++;
            return parseBuff[parsePos - 1];
        }
        parseBuff = null;
        parsePos = 0;
        return (char) in.read();
    }

    /* Parse a TELNET command character. */

    void process_cmd(int cmd) throws IOException {
        print_cmd("Server", cmd);
        cmd = (int) next_char();
        print_cmd("Server", cmd);

        switch (cmd) {
            case IAC:
                process_data((char) cmd);
                break;
            case DONT:
            case DO:
            case WONT:
            case WILL: {
                int opt = (int) next_char();
                print_opt("Server Opt", opt);
                process_opt(cmd, opt);
            }
            break;
            case SB: {
                int opt = (int) next_char();
                print_opt("Server Sub", opt);
                process_sb(opt);
            }
            break;
            default:
                return;
        }

        if (!isTelnet) {
            isTelnet = true;
            send_opt(WILL, TELOPT_TSPEED, false);
            send_opt(WILL, TELOPT_TTYPE, false);
            send_opt(WILL, TELOPT_NAWS, false);
            send_opt(WILL, TELOPT_NAOHTD, false);
            send_opt(DO, TELOPT_ECHO, false);
            send_opt(DO, TELOPT_SGA, false);
        }
    }

    /* Eat up data until the end of the subnegotiation request. */

    void clean_sb()
        throws IOException {
        for (; ; ) {
            int d = (int) next_char();
            if (d == IAC) {
                d = (int) next_char();
                if (d == SE) return;
            }
        }
    }

  /* Process a subnegotiation sequence.  Note that this CAN NOT be
	 synchronized, because it may block while reading the subnegotiation
	 data! */

    void process_sb(int opt)
        throws IOException {
        switch (opt) {
            case TELOPT_TTYPE: {
                int d = (int) next_char();
                clean_sb();
                if (d != 1)
                    break;
                send_ttype();
            }
            break;
            case TELOPT_TSPEED: {
                int d = (int) next_char();
                clean_sb();
                if (d != 1)
                    break;
                send_tspeed();
            }
            break;
            default:
                if (debug)
                    System.out.println("LaTerm: Skipping subnegotiation...");
                clean_sb();
                break;
        }
    }

  /* ------------------------------------------------------------
	 TELNET OPTION CONTROL METHODS
	 ------------------------------------------------------------ */

    /* Send an option command to the remote server. */

    synchronized void send_opt(int cmd, int opt, boolean force) throws IOException {

	/* Send this command if we are being forced to,
	   OR if it is a change of state of the server options,
	   OR if it is a change in the state of our local options. */
        if (force
            || (remopt_ok(opt) && (cmd == DONT && rem_options[opt - TELOPT_FIRST])
            || (cmd == DO && !rem_options[opt - TELOPT_FIRST]))
            || (locopt_ok(opt) && (cmd == WONT && loc_options[opt - TELOPT_FIRST])
            || (cmd == WILL && !loc_options[opt - TELOPT_FIRST]))) {
            byte[] reply = new byte[3];
            reply[0] = (byte) IAC;
            reply[1] = (byte) cmd;
            reply[2] = (byte) opt;
            print_cmd("Client", IAC);
            print_cmd("Client", cmd);
            print_opt("Client", opt);
            out.write(reply);
        }

	/* Change our options state.  We really shouldn't be turning
	   options on until we get a reply, but this isn't
	   a problem yet for the options that are currently implemented... */
        if (cmd == WILL) {
            if (locopt_ok(opt))
                loc_options[opt - TELOPT_FIRST] = true;
        } else if (cmd == WONT) {
            if (locopt_ok(opt))
                loc_options[opt - TELOPT_FIRST] = false;
        } else if (cmd == DO) {
            if (remopt_ok(opt))
                rem_options[opt - TELOPT_FIRST] = true;
        } else if (cmd == DONT) {
            if (remopt_ok(opt))
                rem_options[opt - TELOPT_FIRST] = false;
        }
    }

    /* Take action on a process comment received from the server. */

    synchronized void process_opt(int cmd, int opt) throws IOException {

	/* If this is a local option we don't understand or have not implemented,
	   refuse any 'DO' request. */
        if (cmd == DO && !locopt_ok(opt)) {
            send_opt(WONT, opt, true);

	/* If this is a server option we don't understand or have not implemented,
	   refuse any 'DO' request. */
        } else if (cmd == WILL && !remopt_ok(opt)) {
            send_opt(DONT, opt, true);

            /* If this is a DONT request, (possibly) send a reply and turn off the option. */
        } else if (cmd == DONT) {
            send_opt(WONT, opt, false);

            /* If this is a WONT request, (possibly) send a reply and turn off the option. */
        } else if (cmd == WONT) {
            send_opt(DONT, opt, false);

            /* If this is a DO request, (possibly) send a reply and turn on the option. */
        } else if (cmd == DO) {
            send_opt(WILL, opt, false);
            if (opt == TELOPT_NAWS)
                send_naws(curWidth, curHeight);
                //else
                //if (opt == TELOPT_TTYPE )
                //	send_ttype();
            else if (opt == TELOPT_NAOHTD)
                send_naohtd();

            /* If this is a WILL request, (possibly) send a reply and turn on the option. */
        } else if (cmd == WILL) {
            send_opt(DO, opt, false);
        }
    }

    /* Used for constructing messages. */

    int put_byte(byte[] b, int pos, byte val) {
        b[pos++] = val;
        if (val == (byte) IAC)
            b[pos++] = val;
        return pos;
    }

    /* Send a window size negotiation. */

    synchronized void send_naws(int width, int height) throws IOException {
        if (loc_options[TELOPT_NAWS - TELOPT_FIRST]) {
            byte[] reply = new byte[14];
            int i = 0;
            reply[i++] = (byte) IAC;
            reply[i++] = (byte) SB;
            reply[i++] = (byte) TELOPT_NAWS;
            i = put_byte(reply, i, (byte) ((width >> 8) & 0xFF));
            i = put_byte(reply, i, (byte) (width & 0xFF));
            i = put_byte(reply, i, (byte) ((height >> 8) & 0xFF));
            i = put_byte(reply, i, (byte) (height & 0xFF));
            reply[i++] = (byte) IAC;
            reply[i++] = (byte) SE;
            print_cmd("Client", IAC);
            print_cmd("Client", SB);
            print_opt("Client " + width + " x " + height, TELOPT_NAWS);
            print_cmd("Client", IAC);
            print_cmd("Client", SE);
            if (debug)
                System.out.println("LaTerm: Client: len=" + i + ", dat=" + reply);
            out.write(reply, 0, i);
        }
    }

    /* Send a terminal type negotiation. */

    synchronized void send_ttype() throws IOException {
        if (emulator != null) {
            String nstr = emulator.getTerminalTypeName();
            if (nstr == null)
                nstr = "UNKNOWN";
            char[] name = nstr.toCharArray();
            byte reply[] = new byte[name.length + 6];
            int i = 0;
            reply[i++] = (byte) IAC;
            reply[i++] = (byte) SB;
            reply[i++] = (byte) TELOPT_TTYPE;
            reply[i++] = 0;
            for (int j = 0; j < name.length; j++) {
                reply[i++] = (byte) name[j];
            }
            reply[i++] = (byte) IAC;
            reply[i++] = (byte) SE;
            print_cmd("Client", IAC);
            print_cmd("Client", SB);
            print_opt("Client " + name, TELOPT_TTYPE);
            print_cmd("Client", IAC);
            print_cmd("Client", SE);
            out.write(reply);
        }
    }

    /* Negotiate output horizontal tab disposition: let us do it all. */

    synchronized void send_naohtd() throws IOException {
        if (loc_options[TELOPT_NAOHTD - TELOPT_FIRST]) {
            byte[] reply = new byte[14];
            int i = 0;
            reply[i++] = (byte) IAC;
            reply[i++] = (byte) SB;
            reply[i++] = (byte) TELOPT_NAOHTD;
            reply[i++] = (byte) 0;
            reply[i++] = (byte) 0;
            reply[i++] = (byte) IAC;
            reply[i++] = (byte) SE;
            print_cmd("Client", IAC);
            print_cmd("Client", SB);
            print_opt("Client 0 0", TELOPT_NAOHTD);
            print_cmd("Client", IAC);
            print_cmd("Client", SE);
            if (debug)
                System.out.println("LaTerm: Client: len=" + i + ", dat=" + reply);
            out.write(reply, 0, i);
        }
    }

    /* Negotiate about terminal speed: fast as possible! */

    synchronized void send_tspeed() throws IOException {
        if (loc_options[TELOPT_TSPEED - TELOPT_FIRST]) {
            byte[] reply = new byte[20];
            int i = 0;
            reply[i++] = (byte) IAC;
            reply[i++] = (byte) SB;
            reply[i++] = (byte) TELOPT_TSPEED;
            reply[i++] = (byte) 0;
            reply[i++] = (byte) '5';
            reply[i++] = (byte) '2';
            reply[i++] = (byte) '0';
            reply[i++] = (byte) '0';
            reply[i++] = (byte) '0';
            reply[i++] = (byte) ',';
            reply[i++] = (byte) '5';
            reply[i++] = (byte) '2';
            reply[i++] = (byte) '0';
            reply[i++] = (byte) '0';
            reply[i++] = (byte) '0';
            reply[i++] = (byte) IAC;
            reply[i++] = (byte) SE;
            print_cmd("Client", IAC);
            print_cmd("Client", SB);
            print_opt("Client 0 52000,52000", TELOPT_TSPEED);
            print_cmd("Client", IAC);
            print_cmd("Client", SE);
            if (debug)
                System.out.println("LaTerm: Client: len=" + i + ", dat=" + reply);
            out.write(reply, 0, i);
        }
    }

  /* ------------------------------------------------------------
	 MISC SUPPORT METHODS
	 ------------------------------------------------------------ */

    /* Parse escapes in a parameter string. */

    String parseString(String in) {
        if (in == null)
            return null;
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) != '\\')
                out.append(in.charAt(i));
            else if (++i < in.length()) {
                switch (in.charAt(i)) {
                    case '\\':
                        out.append('\\');
                        break;
                    case 'n':
                        out.append('\n');
                        break;
                    case 'r':
                        out.append('\r');
                        break;
                    case 't':
                        out.append('\t');
                        break;
                    case 'f':
                        out.append('\f');
                        break;
                    case 'b':
                        out.append('\b');
                        break;
                    case 'u': {
                        int c = 0;
                        for (int j = 0; j < 4 && i < in.length(); j++, i++) {
                            c <<= 4;
                            c = c + (int) (in.charAt(i) - '0');
                        }
                        out.append((char) c);
                    }
                    break;
                    case 'x': {
                        int c = 0;
                        for (int j = 0; j < 2 && i < in.length(); j++, i++) {
                            c <<= 4;
                            c = c + (int) (in.charAt(i) - '0');
                        }
                        out.append((char) c);
                    }
                    break;
                    default:
                        if (in.charAt(i) >= '0' && in.charAt(i) <= '9') {
                            int c = 0;
                            for (int j = 0; j < 3 && i < in.length(); j++, i++) {
                                c <<= 3;
                                c = c + (int) (in.charAt(i) - '0');
                            }
                            out.append((char) c);
                        }
                }
            }
        }
        return out.toString();
    }

    void print_cmd(String label, int cmd) {
        if (debug) System.out.println(label + ": Cmd " + telcmd(cmd)
            + " (" + cmd + ")");
    }

    void print_opt(String label, int opt) {
        if (debug) {
            boolean flag = false;
            if (opt >= TELOPT_FIRST && opt <= TELOPT_LAST) {
                flag = locopts_impl[opt - TELOPT_FIRST];
            }
            System.out.println(label + ": Opt " + telopt(opt)
                + " (" + opt + ") impl=" + flag);
        }
    }

    boolean telcmd_ok(int cmd) {
        return (cmd <= TELCMD_LAST && cmd >= TELCMD_FIRST);
    }

    boolean locopt_ok(int opt) {
        return (opt <= TELOPT_LAST && opt >= TELOPT_FIRST
            && locopts_impl[opt - TELOPT_FIRST]);
    }

    boolean remopt_ok(int opt) {
        return (opt <= TELOPT_LAST && opt >= TELOPT_FIRST
            && remopts_impl[opt - TELOPT_FIRST]);
    }

    String telcmd(int cmd) {
        if (telcmd_ok(cmd)) {
            return telcmds[cmd - TELCMD_FIRST];
        }
        return "<?CMD?>";
    }

    String telopt(int opt) {
        if (opt >= TELOPT_FIRST && opt <= TELOPT_LAST) {
            return telopts[opt - TELOPT_FIRST];
        }
        return "<?OPT?>";
    }

    private boolean debug;

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean b) {
        debug = b;
    }
}

