package com.prasad.terminal;
/*
 * LaTerm
 * Parameters interface: used to communicate program parameters between
 * different classes.
 */

public interface Parameters {
    public String getParameter(String name, String def);

    public boolean isApplet();
};
