

package com.prasad.util;

public interface Mailer {
    /* Mail Sender Interface Methods */
    public String getSubject();

    public void setSubject(String str);

    public String[] getBody();

    public void setBody(String[] a);

    public String[] getToRecipients();

    public void setToRecipients(String[] a);

    public String[] getCcRecipients();

    public void setCcRecipients(String[] a);

    public String[] getBccRecipients();

    public void setBccRecipients(String[] a);

    /**
     * Typical implementation of this interface will search up the
     * parent chain and call the first method if found. If no parent
     * has Mailer implemented, then it will perform its own action.
     * <PRE>
     * // Mailer methods
     * String	subject;
     * String[]  toRecipients;
     * String[]	ccRecipients;
     * String[]	bccRecipients;
     * String[]	body;
     * <p>
     * public String		getSubject()		{ return subject;}
     * public void		setSubject(String str)		{subject		= str ;};
     * public String[]	getBody()			{ return body; }
     * public void		setBody(String[] a)			{body			= a;}
     * public String[]	getToRecipients()	{ return toRecipients;}
     * public void		setToRecipients(String[] a)	{toRecipients	= a;}
     * public String[]	getCcRecipients()	{ return ccRecipients;}
     * public void		setCcRecipients(String[] a)	{ccRecipients	= a;}
     * public String[]	getBccRecipients()	{ return bccRecipients;}
     * public void		setBccRecipients(String[] a){bccRecipients	= a;}
     * public void sendMail(
     * String[] toRecip,
     * String[] ccRecip,
     * String[] bccRecip,
     * String   subject,
     * String[] body)
     * {
     * setToRecipients(toRecip);
     * setCcRecipients(ccRecip);
     * setBccRecipients(bccRecip);
     * setSubject(subject);
     * setBody(body);
     * <p>
     * Component component	= this.getParent();
     * while (
     * component != null && !(component instanceof Mailer))
     * component = component.getParent();
     * <p>
     * if (component != null
     * &&	component instanceof Mailer)
     * ((Mailer)component).sendMail(toRecip, ccRecip, bccRecip, subject, body);
     * else
     * {
     * sendMail();
     * }
     * }
     * <p>
     * private void sendMail()
     * {
     * if (this instanceof Applet)
     * {
     * String	url = "mailto:";
     * String toRecips[] = getToRecipients();
     * if (toRecips != null)
     * {
     * for (int i = 0 ; i < toRecips.length ; i++)
     * {
     * if (i == 0)
     * url += toRecips[i];
     * else
     * url += " " + toRecips[i];
     * }
     * }
     * url += "?";
     * String	subject = getSubject();
     * if (subject != null)
     * url += subject;
     * <p>
     * Applet applet = (Applet)this;
     * // Get a reference to the Navigator Window
     * try
     * {
     * JSObject win	= JSObject.getWindow(applet);
     * String[] params = new String[1];
     * params[0]		= url;
     * win.call("Mailer", params);
     * }
     * catch (Exception e)
     * {
     * // Don't throw exception information away, print it.
     * e.printStackTrace();
     * }
     * }
     * //    else
     * //    if (this instanceof Application)
     * //    {
     * //    }
     * }
     *
     * </PRE>
     */
    public void sendMail(
        String[] toRecip,
        String[] ccRecip,
        String[] bccRecip,
        String subject,
        String[] body);
    /* Mail Receiver Interface Methods - not defined yet */
//  public boolean	recvMail();
}
