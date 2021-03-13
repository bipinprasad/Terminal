

package com.prasad.terminal;

import java.awt.*;
import java.util.Properties;

public class TextAttrib {
    Color fore;
    Color back;
    boolean underLine;
    /**
     * Whether or not to use the background color
     */
    boolean useBack;

    public TextAttrib() {
        this(Color.black, Color.white, false, true);
    }

    public TextAttrib(Color foreground, Color background, boolean underline, boolean useBackground) {
        this.fore = foreground;
        this.back = background;
        this.underLine = underline;
        this.useBack = useBackground;
    }

    public static TextAttrib[] getDefaults() {
        Color[] defaultColors =
            {
                new Color(255, 255, 255),
                new Color(192, 192, 192),
                new Color(0, 128, 0),
                new Color(0, 128, 128),
                new Color(0, 0, 0),
                new Color(128, 0, 0),
                new Color(128, 128, 0),
                new Color(0, 0, 0),
            };

        TextAttrib[] retVal =
            {
                new TextAttrib(defaultColors[7], defaultColors[1], false, true),
                new TextAttrib(defaultColors[4], defaultColors[1], false, true),
                new TextAttrib(defaultColors[5], defaultColors[1], true, true),
                new TextAttrib(defaultColors[6], defaultColors[1], true, true),
                new TextAttrib(defaultColors[7], defaultColors[3], false, true),
                new TextAttrib(defaultColors[4], defaultColors[3], false, true),
                new TextAttrib(defaultColors[5], defaultColors[3], true, true),
                new TextAttrib(defaultColors[6], defaultColors[3], true, true),
                new TextAttrib(defaultColors[1], defaultColors[7], false, true),
                new TextAttrib(defaultColors[1], defaultColors[4], false, true),
                new TextAttrib(defaultColors[1], defaultColors[5], true, true),
                new TextAttrib(defaultColors[1], defaultColors[6], true, true),
                new TextAttrib(defaultColors[3], defaultColors[7], false, true),
                new TextAttrib(defaultColors[3], defaultColors[4], false, true),
                new TextAttrib(defaultColors[3], defaultColors[5], true, true),
                new TextAttrib(defaultColors[3], defaultColors[6], true, true),
            };
        return retVal;
    }

    public Properties getAsProperties(Properties retVal, String keyPrefix) {
        if (retVal == null)
            retVal = new Properties();
        if (keyPrefix == null)
            keyPrefix = "";
        else if (!keyPrefix.endsWith("."))
            keyPrefix = keyPrefix + ".";

        retVal.put(keyPrefix + "fore", "" + fore.getRGB());
        retVal.put(keyPrefix + "back", "" + back.getRGB());
        retVal.put(keyPrefix + "underLine", underLine ? "true" : "false");
        retVal.put(keyPrefix + "useBack", useBack ? "true" : "false");

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
            fore = new Color(Integer.decode((String) retVal.get(keyPrefix + "fore")).intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            back = new Color(Integer.decode((String) retVal.get(keyPrefix + "back")).intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            underLine = Boolean.valueOf((String) retVal.get(keyPrefix + "underLine")).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            useBack = Boolean.valueOf((String) retVal.get(keyPrefix + "useBack")).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
