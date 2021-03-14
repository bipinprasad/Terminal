

package com.prasad.terminal;

public class LoginChat {
    String hostName;                // name/IP of the host - host identifier

    ExpectSend idExpectSend;
    ExpectSend passwordExpectSend;
    ExpectSend[] otherExpectSends;

    public LoginChat(String host) {
        hostName = host;
    }

    public ExpectSend getIdExpectSend() {
        return idExpectSend;
    }

    public void setIdExpectSend(ExpectSend es) {
        idExpectSend = es;
    }

    public ExpectSend getPasswordExpectSend() {
        return passwordExpectSend;
    }

    public void setLoginExpectSend(ExpectSend es) {
        passwordExpectSend = es;
    }

    public ExpectSend[] getOtherExpectSends() {
        return otherExpectSends;
    }

    public void setOtherExpectSends(ExpectSend[] es) {
        otherExpectSends = es;
    }

    public void addOtherExpectSend(ExpectSend es) {
        if (es == null)
            return;

        if (otherExpectSends == null) {
            otherExpectSends = new ExpectSend[1];
            otherExpectSends[0] = es;
        } else {
            int oldSize = otherExpectSends.length;
            ExpectSend[] newArray = new ExpectSend[oldSize + 1];
            for (int i = 0; i < oldSize; i++)
                newArray[i] = otherExpectSends[i];
            newArray[oldSize] = es;
            otherExpectSends = newArray;
        }
    }
}
