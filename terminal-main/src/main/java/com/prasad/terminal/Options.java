

package com.prasad.terminal;

import java.awt.*;
import java.util.*;

public class Options {
    public static final int MAX_BACKGROUNDS = 30000;// only to prevent an infinite loop
    Font font;
    Color screenBackground;
    Color graphicsCharForeground;// color for graphics characters
    Hashtable backgroundImageURLs;
    // character attribute table
    TextAttrib[] textAttribs;

    private boolean dfltF12IsGo;
    private boolean altGo;
    private boolean dfltShowTools;
    private boolean dfltShowStatus;

    boolean dfltCommSettings;
    boolean dfltCommHostPgm;
    short mailRecipType;                    // OPT_MAIL_NORECIP, OPT_MAIL_RECIP, OPT_MAIL_RECIP_SHOWOPTIONS
    boolean dfltMailAttachmentLawfile;
    boolean bMsgBoxInteractiveMode;
    boolean bNKPEnterIsTab;

    private boolean dirty;    // true if this option needs to be saved

    public Options() {
        font = new Font("Courier", 0, 12);
        textAttribs = TextAttrib.getDefaults();
        dfltF12IsGo = false;
        altGo = false;
        dfltShowTools = true;
        dfltShowStatus = true;
        backgroundImageURLs = new Hashtable(100);
    }

    public boolean getDfltF12IsGo() {
        return dfltF12IsGo;
    }

    public void setDfltF12IsGo(boolean flag) {
        dfltF12IsGo = flag;
    }

    public boolean getAltGo() {
        return altGo;
    }

    public void setAltGo(boolean flag) {
        altGo = flag;
    }

    public boolean getDfltShowTools() {
        return dfltShowTools;
    }

    public void setDfltShowTools(boolean flag) {
        dfltShowTools = flag;
    }

    public boolean getDfltShowStatus() {
        return dfltShowStatus;
    }

    public void setDfltShowStatus(boolean flag) {
        dfltShowStatus = flag;
    }

    public String getAsPropertyStrings() {
        Properties p = getAsProperties(null, null);
        StringBuffer b = new StringBuffer(1024);
        Enumeration enumeration	=p.keys();
        if (enumeration != null)
        {
            while (enumeration.hasMoreElements())
            {
                String val;
                String key = (String) enumeration.nextElement();
                if (key != null) {
                    val = p.getProperty(key);
                    if (val != null)
                        b.append(key + "=" + val + "\n");
                }
            }
        }
        return b.toString();
    }

    public Properties getAsProperties(Properties retVal, String keyPrefix) {
        if (retVal == null)
            retVal = new Properties();
        if (keyPrefix == null)
            keyPrefix = "";
        else if (!keyPrefix.endsWith("."))
            keyPrefix = keyPrefix + ".";

        String fontStr = font.getName() + "-";
        int fontStyle = font.getStyle();
        if ((fontStyle & Font.BOLD) != 0) {
            if ((fontStyle & Font.ITALIC) != 0)
                fontStr += "bolditalic-";
            else
                fontStr += "bold-";
        } else if ((fontStyle & Font.ITALIC) != 0)
            fontStr += "italic-";

        fontStr = fontStr + font.getSize();

        retVal.put(keyPrefix + "font", fontStr);
        retVal.put(keyPrefix + "dfltShowTools", dfltShowTools ? "true" : "false");
        retVal.put(keyPrefix + "dfltShowStatus", dfltShowStatus ? "true" : "false");
        retVal.put(keyPrefix + "dfltF12IsGo", dfltF12IsGo ? "true" : "false");
        //retVal.put(keyPrefix + "altGo"			, altGo				?"true":"false");
        if (screenBackground != null)
            retVal.put(keyPrefix + "screenBackground", "" + screenBackground.getRGB());
        if (graphicsCharForeground != null)
            retVal.put(keyPrefix + "graphicsCharForeground", "" + graphicsCharForeground.getRGB());

        Enumeration enumeration = backgroundImageURLs.keys();
        if (enumeration !=null)
        {
            for (int imgCnt = 0; enumeration.hasMoreElements(); )
            {
                String key = (String) enumeration.nextElement();
                String val = (String) backgroundImageURLs.get(key);
                if (key != null
                    && key.trim().length() > 0
                    && val != null
                    && val.trim().length() > 0) {
                    String nextPrefix = keyPrefix + "backgroundImageURLs." + imgCnt + ".";
                    retVal.put(nextPrefix + "cmd", key);
                    retVal.put(nextPrefix + "url", val);
                    imgCnt++;
                }
            }
        }

        for (int i = 0; i < 16; i++) {
            String nextPrefix = keyPrefix + "textAttribs." + i;
            textAttribs[i].getAsProperties(retVal, nextPrefix);
        }

        return retVal;
    }

    public void setFromProperties(Properties retVal, String keyPrefix) {
        if (retVal == null)
            return;

        if (keyPrefix == null)
            keyPrefix = "";
        else if (!keyPrefix.endsWith("."))
            keyPrefix = keyPrefix + ".";

        try {
            font = Font.decode((String) retVal.get(keyPrefix + "font"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            dfltShowTools = Boolean.valueOf((String) retVal.get(keyPrefix + "dfltShowTools")).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dfltShowStatus = Boolean.valueOf((String) retVal.get(keyPrefix + "dfltShowStatus")).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dfltF12IsGo = Boolean.valueOf((String) retVal.get(keyPrefix + "dfltF12IsGo")).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //try {
        //altGo = Boolean.valueOf((String)retVal.get(keyPrefix + "altGo")).booleanValue();
        //} catch (Exception e) { e.printStackTrace();}

        screenBackground = null;
        try {
            screenBackground = new Color(Integer.decode((String) retVal.get(keyPrefix + "screenBackground")).intValue());
        } catch (Exception e) {
        }

        graphicsCharForeground = null;
        try {
            graphicsCharForeground = new Color(Integer.decode((String) retVal.get(keyPrefix + "graphicsCharForeground")).intValue());
        } catch (Exception e) {
        }

        for (int i = 0, imgCnt = 0; i < MAX_BACKGROUNDS; i++) {
            String cmd;
            String url;
            String nextPrefix = keyPrefix + "backgroundImageURLs." + i + ".";
            try {
                cmd = (String) retVal.get(nextPrefix + "cmd");
                url = (String) retVal.get(nextPrefix + "url");
                if (cmd != null
                    && cmd.trim().length() > 0
                    && url != null
                    && url.trim().length() > 0) {
                    if (backgroundImageURLs == null)
                        backgroundImageURLs = new Hashtable(MAX_BACKGROUNDS);
                    backgroundImageURLs.put(cmd.trim(), url.trim());
                } else
                    break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 16; i++) {
            String nextPrefix = keyPrefix + "textAttribs." + i;
            textAttribs[i].setFromProperties(retVal, nextPrefix);
        }

    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font f) {
        if (f != null) font = f;
    }

    public String getBackgroundImageURL(String imageKey) {
        try {
            return (String) backgroundImageURLs.get(imageKey);
        } catch (Exception e) {
        }
        return null;
    }

    public boolean getDirty() {
        return dirty;
    }

    public void setDirty(boolean flag) {
        dirty = flag;
    }
}