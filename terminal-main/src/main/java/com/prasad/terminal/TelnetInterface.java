

package com.prasad.terminal;

public interface TelnetInterface {
    public int getNumCols();

    public int getTelnetNumRows();

    public String getTerminalTypeName();

    public void receive(String s);

    public void receive(char b);

    public void receive(char[] b, int offset, int len);

    //
    public void connected(); // after connection is established

    public void disconnected();    // aftered disconnection

    // ?
    public String sequenceToString(String str);

    public String charToString(char b);

}
