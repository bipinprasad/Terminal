package pvTreeJ;

import java.awt.*;
import java.util.*;
import java.io.Serializable;

	//--------------------------------------------
	// draw border starting from the center line and moving
	// first to the outside, after that go back to the
	// center and moving to the inside
	// g ==> graphics to draw
	// r ==> bound rectangle to draw a border,
	// after drawing it is adjusted to client area
	// iBorderStyle ==> 0..9: plane, raised, sunken, indent, outdent
	// every style is doubled: evens-simple, odds-advanced
	// iBorderWidth ==> in pixels
	// clr ==> average color of border

class PVBorder {
	public void Draw(Graphics g, Rectangle r,
		int iBorderStyle, int iBorderWidth, Color color, boolean bFill)
	{

		if(iBorderStyle > 8) iBorderStyle = 8;
		iBorderWidth = checkWidth(r, iBorderWidth);
		if(iBorderWidth == 0) return;
		int iWidth = r.width;
		int iHeight = r.height;
		int i = (iBorderStyle >= 6) ?
			(iBorderWidth + 2) >> 3 : iBorderWidth >> 1;
		int iStart = i;
		Color clr = new Color(
			190 + (color.getRed() - 190) / 3,
			190 + (color.getGreen() - 190) / 3,
			190 + (color.getBlue() - 190) / 3);
		//------------------------------------------
		boolean bRaised = true;
		boolean bDarker = true;
		boolean bShadow = (iBorderStyle & 1) == 1;
		//------------------------------------------
		switch((iBorderStyle >> 1) & 7)
		{
			case 0: // plane
			case 1: // raised
			case 4: // outdent
				bRaised = true; break;
			case 2: // sunken
				bDarker = false;
			case 3: // indent
				bRaised = false; break;
		}
		//------------------------------------------
		// draw lines from center of frame to outside
		g.setColor(clr);
		g.setPaintMode();
		//------------------------------------------
		for(i = iStart; i >= 0; i--)
		{
			if(iBorderStyle > 1)
				g.draw3DRect(r.x + i, r.y + i, iWidth - ((i << 1) + 1),
					iHeight - ((i << 1) + 1), bRaised);
			else
				g.drawRect(r.x + i, r.y + i, iWidth - ((i << 1) + 1),
					iHeight - ((i << 1) + 1));
			if(bShadow)
				if(bDarker)
					g.setColor(g.getColor().darker());
				else
					g.setColor(g.getColor().brighter());
		}
		//------------------------------------------
		switch((iBorderStyle >> 1) & 7)
		{
			case 1: // raised
				bDarker = false; break;
			case 2: // sunken
				bDarker = true;  break;
			case 3: // indent
				bRaised = true;  break;
			case 0: // plane
			case 4: // outdent
				bRaised = false; break;
		}
		//------------------------------------------
		// draw lines at the center of frame for in/outdent
		i = iStart + 1;
		if(iBorderStyle >= 6)
		{
			g.setColor(clr);
			if(iBorderWidth > 2)
			{
				iStart = iBorderWidth - (++iStart << 1);
				while(iStart-- > 0)
				{
					g.drawRect(r.x + i, r.y + i, iWidth - ((i << 1) + 1),
						iHeight - ((i << 1) + 1));
					i++;
				}
			}
		}
		//------------------------------------------
		// draw lines from center of frame to inside
		if(bShadow)
			if(bDarker)
				g.setColor(clr.darker());
			else
				g.setColor(clr.brighter());
		//------------------------------------------
		for(i = i; i < iBorderWidth; i++)
		{
			if(iBorderStyle > 1)
				g.draw3DRect(r.x + i, r.y + i, iWidth - ((i << 1) + 1),
					iHeight - ((i << 1) + 1), bRaised);
			else
				g.drawRect(r.x + i, r.y + i, iWidth - ((i << 1) + 1),
					iHeight - ((i << 1) + 1));
			if(bShadow)
				if(bDarker)
					g.setColor(g.getColor().darker());
				else
					g.setColor(g.getColor().brighter());
		}
		getClientRect(r, iBorderWidth);
		if(bFill) {
    		g.setColor(color);
	    	g.fillRect(r.x, r.y, r.width, r.height);
	    }
	}
	public void getClientRect(Rectangle r, int iBorderWidth)
	{
		iBorderWidth = checkWidth(r, iBorderWidth);
		r.x += iBorderWidth;
		r.y += iBorderWidth;
		r.width -= (iBorderWidth << 1);
		r.height -= (iBorderWidth << 1);
	}
	private int checkWidth(Rectangle r, int iWidth)
	{
		if(iWidth <= 0) return 0;
		if(iWidth >= (r.width >>> 1))
			iWidth = r.width >>> 1;
		if(iWidth >= (r.height >>> 1))
			iWidth = r.height >>> 1;
		return iWidth;
	}
}