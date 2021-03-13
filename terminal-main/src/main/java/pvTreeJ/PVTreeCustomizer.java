package pvTreeJ;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;

public class PVTreeCustomizer extends Panel implements Customizer
{
	PropertyChangeSupport support = new PropertyChangeSupport(this);
	PVTree target;
	Label nodeIndentationLabel;
	Label borderStyleLabel;
	Label borderWidthLabel;
	TextField nodeIndentation;
	TextField borderWidth;
	Checkbox checkLinesAtRoot;
	Checkbox checkEditable;
	Checkbox checkSorted;
	Checkbox checkCaseSensitive;
	Checkbox checkLines;
	Checkbox checkButtons;
	Checkbox checkImages;
	Choice borderStyle;

	public PVTreeCustomizer()
	{
		setLayout(null);
		setSize(340, 240);
		this.setFont(new Font("Dialog", 0, 11));

	}
	@Override
	public void paint(Graphics g)
	{
		//-------------------------------------
		// draw title at the top
		String s = "ProtoView TreeViewJ";
		g.setFont(new Font("Ariel", Font.BOLD, 18));
		g.setColor(Color.white);
		g.drawString(s, 10, 14);
		g.setColor(Color.black);
		g.drawString(s, 11, 15);

		g.setFont(new Font("Courier", Font.BOLD, 13));
		g.drawString("www.protoview.com", 12, 29);
	}

	@Override
	public void setObject(Object obj)
	{
		target = (PVTree)obj;

		checkLinesAtRoot = new Checkbox("Lines At Root", target.getLinesAtRoot());
		checkEditable = new Checkbox("Node Editing", target.getNodeEditing());
		checkSorted = new Checkbox("Sorted", target.getSorted());
		checkCaseSensitive = new Checkbox("Case Sensitivity", target.getCaseSensitive());

		checkLines = new Checkbox("Show Lines", target.getLines());
		checkButtons = new Checkbox("Show Buttons", target.getButtons());
		checkImages = new Checkbox("Show Images", target.getImages());

		nodeIndentationLabel = new Label("Node Indentation");
		borderStyleLabel = new Label("Border Style");
		borderWidthLabel = new Label("Border Width");
		nodeIndentation  = new TextField(String.valueOf(target.getIndentation()), 20);
		borderWidth  = new TextField(String.valueOf(target.getBorderStyle()), 20);

		borderStyle = new Choice();
		borderStyle.addItem("Plain");
		borderStyle.addItem("Border Style 1");
		borderStyle.addItem("Border Style 2");
		borderStyle.addItem("Border Style 3");
		borderStyle.addItem("Border Style 4");
		borderStyle.addItem("Border Style 5");
		borderStyle.addItem("Border Style 6");
		borderStyle.addItem("Border Style 7");
		borderStyle.select(target.getBorderStyle());


		checkLines.addItemListener(new LinesItemListener());
		checkButtons.addItemListener(new ButtonsItemListener());
		checkImages.addItemListener(new ImagesItemListener());

		checkLinesAtRoot.addItemListener(new LinesAtRootItemListener());
		checkEditable.addItemListener(new EditableItemListener());
		checkSorted.addItemListener(new SortedItemListener());
		checkCaseSensitive.addItemListener(new CaseSensitiveItemListener());
		borderStyle.addItemListener(new BorderStyleItemListener());
		nodeIndentation.addTextListener(new NodeIndentationTextListener());
		borderWidth.addTextListener(new BorderWidthTextListener());

		add(nodeIndentationLabel);
		add(borderStyleLabel);
        add(nodeIndentation);
		add(checkLines);
		add(checkButtons);
		add(checkImages);
		add(checkLinesAtRoot);

		add(checkEditable);
		add(checkSorted);
		add(checkCaseSensitive);
		add(borderWidthLabel);
		add(borderWidth);
		add(borderStyle);

		checkEditable.setBounds(10, 50, 150, 20);
		checkSorted.setBounds(10, 70, 150, 20);
		checkCaseSensitive.setBounds(10, 90, 150, 20);

		checkLines.setBounds(200, 50, 100, 20);
		checkButtons.setBounds(200, 70, 100, 20);
		checkImages.setBounds(200, 90, 100, 20);
		checkLinesAtRoot.setBounds(200, 110, 100, 20);

		nodeIndentationLabel.setBounds(10, 110, 100, 15);
		nodeIndentation.setBounds(10, 130, 50, 20);

		borderWidthLabel.setBounds(10, 150, 150, 20);
		borderWidth.setBounds(10, 170, 50, 20);

		borderStyleLabel.setBounds(10, 190, 150, 20);
		borderStyle.setBounds(10, 210, 150, 20);
	}

	class LinesItemListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			target.setLines(checkLines.getState());
			support.firePropertyChange("", null, null);
		}
	}
	class ButtonsItemListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			target.setButtons(checkButtons.getState());
			support.firePropertyChange("", null, null);
		}
	}
	class ImagesItemListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			target.setImages(checkImages.getState());
			support.firePropertyChange("", null, null);
		}
	}
	class LinesAtRootItemListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			target.setLinesAtRoot(checkLinesAtRoot.getState());
			support.firePropertyChange("", null, null);
		}
	}
	class EditableItemListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			target.setNodeEditing(checkEditable.getState());
			support.firePropertyChange("", null, null);
		}
	}
	class SortedItemListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			target.setSorted(checkSorted.getState());
			support.firePropertyChange("", null, null);
		}
	}

	class CaseSensitiveItemListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			target.setCaseSensitive(checkCaseSensitive.getState());
			support.firePropertyChange("", null, null);
		}
	}

	class BorderStyleItemListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			target.setBorderStyle(borderStyle.getSelectedIndex());
			support.firePropertyChange("", null, null);
		}
	}

	class NodeIndentationTextListener implements TextListener
	{
		@Override
		public void textValueChanged(TextEvent e)
		{
		    String s = nodeIndentation.getText();
		    int i;
		    if(s == null || (s.length() == 0))
		        i = 0;
	        else {
	            try  {
    		        i = Integer.valueOf(s).intValue();
    		    }
    		    catch (Exception ex) {return;}
    		}
		    if(i >= 0 && i < 500) {
    			target.setIndentation(i);
	    		support.firePropertyChange("", null, null);
	    	}
		}
	}

	class BorderWidthTextListener implements TextListener
	{
		@Override
		public void textValueChanged(TextEvent e)
		{
		    String s = borderWidth.getText();
		    int i;
		    if(s == null || (s.length() == 0))
		        i = 0;
	        else {
	            try  {
    		        i = Integer.valueOf(s).intValue();
    		    }
    		    catch (Exception ex) {return;}
    		}
		    if(i >= 0 && i < 500) {
    			target.setBorderWidth(i);
	    		support.firePropertyChange("", null, null);
	    	}
		}
	}
	//----------------------------------------------------------------------
	@Override
	public void addPropertyChangeListener(PropertyChangeListener l)
	{ 
	    support.addPropertyChangeListener(l); 
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener l)
	{ support.removePropertyChangeListener(l); }

}
