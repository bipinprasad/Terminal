package pvTreeJ;

import java.applet.*;
import java.awt.*;

//--------------------------------------------------
// Draw dotted edge rectangle around specified r.
// Call this function twice to erase first rectangle.
//--------------------------------------------------
// To get the most control on what colors are
// drawing,- it is better to use
// draw(g, clr1, clr2, r) or
// draw(g, clr1, clr2, left, top, width, height)
// (the last function is called anyway)
// though any of draw(...) can be used.
//--------------------------------------------------
// draw(This, g, r) will do the best in case when
// painting is done mostly on the areas with
// default back/foreground colors.
//--------------------------------------------------
// you may call:
// (new DotRect()).draw(g, this, yoursRect);
class DotRect
{

	public void draw(Graphics g, Color clrFore,
		Color clrBack, Rectangle r, int iStyle)
	{
		draw(g, clrFore, clrBack, r.x, r.y, r.width, r.height, iStyle);
	}

	public void draw(Graphics g, Color clrFore,
		Color clrBack, int x, int y, int width, int height, int iStyle)
	{
		Color clrOld = g.getColor();
		g.setColor(clrFore);
		g.setXORMode(clrBack);
//		x--;
//		y--;
//		width += 1;
//		height += 1;
//		System.out.println("Draw: x:" + x + " y:" + y + " width:" + width + " height:" + height);
		int i;
		if(iStyle > 0)
		{
			for(i=0; i < iStyle; i++)
			{
				g.drawRect(x++, y++, width--, height--);
				width--; height--;
			}
		}
		else
		{
			if(iStyle < -1) iStyle = -1;
			i = x + 1;
			int iEnd = i + width + iStyle;
			int iBuf = y + height;
			while(i < iEnd)
			{
				g.drawLine(i, y,  i - iStyle, y);
				g.drawLine(i, iBuf, i - iStyle, iBuf);
				i += (2 - iStyle);
			}
			iBuf = i - 1;
			i = y + 1;
			iEnd = i + height + iStyle;
			while(i < iEnd)
			{
				g.drawLine(x,  i, x,  i - iStyle);
				g.drawLine(iBuf, i, iBuf, i - iStyle);
				i += (2 - iStyle);
			}
		}
		g.setPaintMode();
		g.setColor(clrOld);
	}
}
