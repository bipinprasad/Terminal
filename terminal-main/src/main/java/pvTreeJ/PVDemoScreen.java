//package pvCalJ;
package pvTreeJ;

import java.awt.*;

// NOTE: For not pvWinJ or pvCalJ package
// you should call your own function 
// to draw a border in the "paint()".
//
// Example of PVDemoScreen usage:
//===================================
//===================================
/*
	//adjust "package pvName"
	static int oneDemo = 0;
	public synchronized void paint(Graphics g)
	{
		//-----------------------------
		if(oneDemo < 1)
		{
			try
			{
				Component f = this;
				while(!(f == null || f instanceof Frame))
				f = f.getParent();
				if(f != null)
				{
					//----------------------------------
					// pass the string
					new PVDemoScreen((Frame)f, "MyString");
					//----------------------------------
					// or pass the "this"
					// example of conversion to string:
					// pvTreeJ.PVTreeView[xxxxx] ==> "Tree TreeView"
					//----------------------------------
					//new PVDemoScreen((Frame)f, this);
					oneDemo++;
				}
			} catch(Exception e) { return; }
		}
		//-----------------------------
		// continue painting codes
	}
*/
//===================================
//===================================

class PVDemoScreen extends Window implements Runnable
{
	private String title;
	Thread thread = null;
	Button ok = null;
	private int clr = 0;

	public PVDemoScreen(Frame f, Object This)//, String str) 
	{
		super(f);
		setBackground(Color.lightGray);
		show();
		Dimension screen = getToolkit().getScreenSize();
		reshape(screen.width / 2 - 230, screen.height / 2 - 200, 
			480, 380);
		setLayout(null);
		buildTitle(This.toString());
	}

	private void buildTitle(String str)
	{
		int iLen = str.length();
		title = str;
		if(iLen < 9)
			return;
		int i = 2;
		int iDot = 0;
		char ch;
		while(++i < iLen)
		{
			ch = str.charAt(i);
			if(iDot > 0)
			{
				if(!Character.isLetter(ch))
					iLen = i;
			}
			else
			{
				if(ch == '.')
					iDot = i;
			}
		}
		if(iDot < 3) return;
		title = str.substring(2, iDot);
		if(iDot + 3 < iLen)
			title += (" " + str.substring(iDot + 3, iLen));
	}
	@Override
    public void run()
	{
		remove(ok);
		synchronized(thread)
		{
			while(clr < 190)
			{
				try { Thread.sleep(110); }
				catch(InterruptedException e) {};
				clr += (220 - clr) / 7;
				if(clr > 192) clr = 192;
				paintIt(null);
				if(clr == 192)
				{
					try { Thread.sleep(700); }
					catch(InterruptedException e) {};
				}
			}
			//-----------------------
			// destroy window
			dispose();
		}
	}
	@Override
    public synchronized void paint(Graphics g)
	{ 
		paintIt(g); 
		if(ok == null)
		{
			ok = new Button("I Accept These Terms");
			ok.reshape(160, 315, 150, 30);
			add(ok);
			ok.requestFocus();
		}
	}

	private synchronized void paintIt(Graphics gr)
	{
		//--------------------------------
		// flash image on screen
		if(gr == null) gr = getGraphics();
		if(gr == null) return;

		int iRight = size().width;
		int iBottom = size().height;
		//--------------------------------
		// draw to the image in memory
		Image im = createImage(iRight, iBottom);
		if(im == null)
			return;
		//--------------------------------
		// buffer string
		String s;
		Graphics g = im.getGraphics();
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, iRight, iBottom);

		int i, j, iTop, iLeft;
		int iSet1 = -1, iSet2 = -1;

		// draw PV or text in order that depends on clr
		// if clr < 150 then draw PV first, otherwise draw text first
		while(iSet1 + iSet2 < 0)
		{
			if(iSet1 < 0 && (clr < 165 || iSet2 == 0))
			{
				iSet1 = 0;
				iTop = iBottom / 6;
				i = iTop * 2;
				iLeft = (iRight - iTop * 4) / 2;
				j = ((192 + clr / 5) << 16) | ((192 + clr / 5) << 8) | (192 + clr / 5);
				g.setColor(new Color(j));
				g.fillRect(iLeft, iTop, i, i);

				g.setColor(new Color(((192 - clr / 4) << 16) | ((192 - clr / 4) << 8) | (192 - clr / 4)));
				g.fillRect(iLeft + iTop, iTop + iTop, i, i);
				g.setColor(new Color(j));
				g.fillRect(iLeft + i , iTop + i, i, i);
			}
			if(iSet2 < 0)
			{
				iSet2 = 0;
				//--------------------------------
				// build white highlight color
				i = 255 - clr / 3;
				if(i < 192) i = 192;
				g.setColor(new Color((i << 16) | (i << 8) | i));
				//--------------------------------
				// draw title highlight
				g.setFont(new Font("Dialog", Font.BOLD, 13));
				s = "ProtoView " + title + " Demonstration Version";
				i = (iRight - 10 - g.getFontMetrics().stringWidth(s)) / 2;
				g.drawString(s, i, 40);
				//--------------------------------
				// build main black color
				g.setColor(new Color((clr << 16) | (clr << 8) | clr));
				//--------------------------------
				// draw main title
				g.drawString(s, i + 1, 41);
				//--------------------------------
				// draw minor stuff
				i = 55;
				g.setFont(new Font("Courier", 0, 12));
				s = "Thank you for trying the ProtoView " + title + ".";
				g.drawString(s, 60, i += 16);
				s = "This is a fully functional unlicensed copy of the";
				g.drawString(s, 60, i += 16);
				s = title  + " for evaluation use only.";
				g.drawString(s, 60, i += 16);
				s = "I understand that I may use it only for evaluation";
				g.drawString(s, 60, i += 26);
				s = "and any other use requires purchase of a license.";
				g.drawString(s, 60, i += 16);
				s = "To order a registered version of " + title + ":";
				g.drawString(s, 60, i += 26);
				s = "Phone:  800-231-8588 or 609-655-5000";
				g.drawString(s, 60, i += 16);
				s = "Fax:    609-655-5353";
				g.drawString(s, 60, i += 16);
				s = "Internet: http://www.protoview.com";
				g.drawString(s, 60, i += 16);
				s = "info@protoview.com";
				g.drawString(s, 60, i += 16);
				s = "In England:";
				g.drawString(s, 60, i += 16);
				s = "Phone:  01903538058  Fax: 01903538068";
				g.drawString(s, 60, i += 16);
			}
		}
		//----------------------------------
		// draw shadow
		iRight -= 10;
		iBottom -= 10;
		g.setColor(new Color(((128 + clr / 3) << 16) | ((128 + clr / 3) << 8) | (128 + clr / 3)));
		g.fillRect(iRight, 10, 10, iBottom);
		g.fillRect(10, iBottom, iRight, 10);
		//----------------------------------
		// draw border
		iLeft = 1;
		iTop = 0;
		int col[] = new int[6];
		col[0] = ((96 + clr / 2) << 16) | (clr << 8) | clr;
		col[1] = ((128 + clr / 3) << 16) | ((96 + clr / 2) << 8) | clr;
		col[2] = ((160 + clr / 6) << 16) | ((144 + clr / 4) << 8) | clr;
		col[3] = ((192) << 16) | ((176 + clr / 10) << 8) | clr;
		col[4] = ((240 - clr / 4) << 16) | ((224 - clr / 6) << 8) | clr;
		col[5] = ((255 - clr / 3) << 16) | ((255 - clr / 3) << 8) | (255 - clr / 3);
//		col[0] = 0x00600000;
//		col[1] = 0x00806000;
//		col[2] = 0x00a09000;
//		col[3] = 0x00c0b000;
//		col[4] = 0x00f0e000;
//		col[5] = 0x00ffffff;
		//----------------------------------
		// draw border rectangles starting from the 
		// most outter and moving to the inside
		iSet1 = 00045543100;
		iSet2 = 00012345530;
		for(j=0; j < 2; j++)
		{
			for(i=0; i < 7; i++)
			{
				g.setColor(new Color(col[(byte)((iSet2 >>= 3) & 7)]));
				g.drawLine(iLeft, ++iTop, iLeft, --iBottom);
				g.drawLine(iLeft, iTop, --iRight, iTop);
				//--------------------------------------
				// draw bottom-right lines
				g.setColor(new Color(col[(byte)((iSet1 >>= 3) & 7)]));
				g.drawLine(iLeft++, iBottom, iRight, iBottom);
				g.drawLine(iRight, iTop, iRight, iBottom);
			}
			iSet1 = 00034510230;
			iSet2 = 00001352010;
		}
		gr.drawImage(im, 0, 0, this);
	}

	@Override
    public boolean handleEvent(Event evt)
	{
		if(evt.id == Event.ACTION_EVENT) 
		{
			//--------------------------------
			// start thread for fading paint
			thread = new Thread(this);
			thread.start();
			return true;
		}
		return super.handleEvent(evt);
	}
}
