

package com.prasad.terminal;

/**
 * This class is used to define one instance of the expectsend sequence.
 */
public class ExpectSend {
    String prompt;                // expected prompt for ID
    String send;                // String to send
    boolean usePrompt;            // should the prompt be used
    int timeoutPrompt;        // how long to wait for the prompt to appear (millisecs)
    boolean active;                // activate this ExpectSend
    boolean promptAtRuntime;    // Prompt for this string at run time in a dialog
    boolean continueAtTimeout;    // if this is set, then the next expect send is used when
    //	timeout occurs. A sequence of ExpectSend can have all
    //	but the last one continueAtTimeout. If there is
    //	NO-timeout, then the execution continues from the
    //  ExpectSend following an ExpectSend with this flag
    //	set to false. Thus, a group of ExpectSend acts as one unit
    //	where the first unit starts with this flag set to TRUE
    //	and the last unit with this flag set to FALSE.

    public ExpectSend() {
        this(": ", null, true, 300, true, true, false);
    }

    public ExpectSend(
        String promptStr,
        String sendStr,
        boolean fUsePrompt,
        int timeOut,
        boolean fActive,
        boolean fPromptAtRuntime,
        boolean fContinueAtTimeout) {
        prompt = promptStr;
        send = sendStr;
        usePrompt = fUsePrompt;
        timeoutPrompt = timeOut;
        active = fActive;
        promptAtRuntime = fPromptAtRuntime;
        continueAtTimeout = fContinueAtTimeout;
    }

}