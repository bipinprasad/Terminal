package com.prasad.util;

import java.net.*;
import java.io.*;

public class HTTP {

    String user;
    String password;
    boolean doOutput;
    boolean doInput = true;
    String httpURLStr;
    HTTPProcessLineInterface hTTPProcessLineInterface;

    public HTTP() {
    }

    public HTTP(
        String user,
        String password,
        boolean doOutput,
        boolean doInput,
        String httpURL, // adde parameters using the addParam() method
        HTTPProcessLineInterface hTTPProcessLineInterface) {
        this();
        setUser(user);
        setPassword(password);
        setDoInput(doInput);
        setDoOutput(doOutput);
        setHttpURLStr(httpURL);
        setHTTPProcessLineInterface(hTTPProcessLineInterface);
    }

    public HTTPProcessLineInterface getHTTPProcessLineInterface() {
        return hTTPProcessLineInterface;
    }

    public void setHTTPProcessLineInterface(HTTPProcessLineInterface hTTPProcessLineInterface) {
        this.hTTPProcessLineInterface = hTTPProcessLineInterface;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthCookie() {
        if (user == null
            || password == null)
            return null;
        else
            return "Basic " + Base64.encode(user + ":" + password);
    }

    public boolean isDoInput() {
        return doInput;
    }

    public void setDoInput(boolean doInput) {
        this.doInput = doInput;
    }

    public boolean isDoOutput() {
        return doOutput;
    }

    public void setDoOutput(boolean doOutput) {
        this.doOutput = doOutput;
    }

    public String getHttpURLStr() {
        return httpURLStr;
    }

    public void setHttpURLStr(String httpURL) {
        this.httpURLStr = httpURL;
    }

    private URL getHttpURL() {
        if (httpURLStr != null) {
            try {
                return new URL(httpURLStr);
            } catch (MalformedURLException e) {
                String str = "Error: (" + getClass().getName() + ") Malformed URL [" + httpURLStr + "]";
                System.out.println(str);
            } catch (Exception e) {
                e.printStackTrace();
                String str = "Error: (" + getClass().getName() + ") with URL [" + httpURLStr + "]";
                System.out.println(str);
            }
        }
        return null;
    }

    StringBuffer params;

    public StringBuffer getParams() {
        return params;
    }

    public void addParam(String key, String value) {
        if (key == null)
            return;
        if (params == null)
            params = new StringBuffer();
        if (params.length() > 0)
            params.append('&');
        params.append(key + "=" + ((value != null) ? URLEncoder.encode(value) : ""));
    }

    public void open() throws MalformedURLException, IOException {
        if (hTTPProcessLineInterface != null)
            hTTPProcessLineInterface.setDebug(getDebug());

        URL baseURL = null;
        // Build the URL to get the form definition for the given product
        // line and token
        StringBuffer params = getParams();
        if (debug) {
            if (params == null)
                System.out.println("params is [" + "(null)" + "]");
            else
                System.out.println("params is [" + params + "]");
        }

        baseURL = getHttpURL();

        if (baseURL == null) {
            String str = "Error: (" + getClass().getName() + ") URL is null";
            System.out.println(str);
            return;
        }
        if (debug)
            System.out.println("baseURL is [" + baseURL + "]");

        URLConnection connection = null;
        String line;

        try {
            connection = baseURL.openConnection();
        } catch (IOException e) {
            System.out.println(getClass().getName() + ": IO exception:" + e);
            e.printStackTrace();
            throw (e);
        }

        connection.setDoOutput(doOutput);        // if true: use POST method to pass data
        connection.setDoInput(doInput);            // if true: process incoming CGI stream
        connection.setUseCaches(false);            // disable caching of documents
        connection.setAllowUserInteraction(true); // Allow user interaction

        String authCookie = getAuthCookie();
        if (authCookie != null)
            connection.setRequestProperty("Authorization", authCookie);
        connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-length", "" + (params == null ? 0 : params.length()));

        if (isDoOutput()) {
            PrintWriter out = null;
            out = new PrintWriter(connection.getOutputStream());
            if (params != null) {
                out.print(params.toString());
                if (debug)
                    System.out.println("Sending params [" + params + "]");
            }
            out.flush();
            out.close();
            out = null;
        }

        if (isDoInput()) {
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                if (connection.getHeaderField("WWW-authenticate") != null) {
                    if (debug)
                        System.out.println(connection.getHeaderField("WWW-authenticate"));
                    return;
                }
                while ((line = in.readLine()) != null) {
                    if (line.length() == 0)
                        continue;
                    if (line.charAt(0) == '#')
                        continue;
                    if (debug)
                        System.out.println("Processing line [" + line + "]");
                    if (hTTPProcessLineInterface != null)
                        hTTPProcessLineInterface.processLine(line);
                }
            } catch (IOException e) {
                System.out.println("IO exception:" + e);
                if (connection.getHeaderField("WWW-authenticate") != null)
                    System.out.println("User Authentication failed");
                else {
                    e.printStackTrace();
                    throw (e);
                }
            } finally {
                try {
                    if (in != null)
                        in.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private boolean debug;

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean flag) {
        debug = flag;
    }
}