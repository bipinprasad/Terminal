package pvTreeJ;

// Imports
import java.beans.*;

public class PVTreeBeanInfo extends SimpleBeanInfo {

      private final static Class beanClass = PVTree.class;

      // Get the appropriate icon

      public java.awt.Image getIcon(int iconKind) {
        if (iconKind == BeanInfo.ICON_COLOR_16x16) {
          java.awt.Image img = loadImage("PVTree16.gif");
          return img;
        }
        if (iconKind == BeanInfo.ICON_COLOR_32x32) {
          java.awt.Image img = loadImage("PVTree32.gif");
          return img;
        }
        return null;
      }

      public PropertyDescriptor[] getPropertyDescriptors() {
     	try
		{
			PropertyDescriptor backgroundColor =
				new PropertyDescriptor("background", beanClass);
			PropertyDescriptor foreground =
				new PropertyDescriptor("foreground", beanClass);
			PropertyDescriptor lineColor =
				new PropertyDescriptor("lineColor", beanClass);
			PropertyDescriptor selectedTextColor =
				new PropertyDescriptor("selectedTextColor", beanClass);
			PropertyDescriptor selectedBackColor =
				new PropertyDescriptor("selectedBackColor", beanClass);
			PropertyDescriptor borderColor =
				new PropertyDescriptor("borderColor", beanClass);

			PropertyDescriptor font =
				new PropertyDescriptor("font", beanClass);
			PropertyDescriptor borderStyle =
				new PropertyDescriptor("borderStyle", beanClass);
			PropertyDescriptor borderWidth =
				new PropertyDescriptor("borderWidth", beanClass);

			PropertyDescriptor lines =
				new PropertyDescriptor("lines", beanClass);
			PropertyDescriptor images =
				new PropertyDescriptor("images", beanClass);
			PropertyDescriptor buttons =
				new PropertyDescriptor("buttons", beanClass);


			PropertyDescriptor indent =
				new PropertyDescriptor("indentation", beanClass);
			PropertyDescriptor sorted =
				new PropertyDescriptor("sorted", beanClass);
			PropertyDescriptor linesAtRoot =
				new PropertyDescriptor("linesAtRoot", beanClass);
			PropertyDescriptor nodeEditing =
				new PropertyDescriptor("nodeEditing", beanClass);
			PropertyDescriptor caseSensitive =
				new PropertyDescriptor("caseSensitive", beanClass);

 			backgroundColor.setBound(true);
			foreground.setBound(true);
			font.setBound(true);
			linesAtRoot.setBound(true);
			nodeEditing.setBound(true);

			lines.setBound(true);
			buttons.setBound(true);
			images.setBound(true);


			borderStyle.setBound(true);
			borderWidth.setBound(true);
			indent.setBound(true);

			sorted.setBound(true);
			caseSensitive.setBound(true);
			lineColor.setBound(true);
			selectedTextColor.setBound(true);
			selectedBackColor.setBound(true);
			borderColor.setBound(true);

			PropertyDescriptor rv[] =
			{
				backgroundColor,
				foreground,
				font, linesAtRoot, nodeEditing,
				lines, buttons, images,
				borderStyle, borderWidth, indent,
				sorted, caseSensitive, lineColor, selectedTextColor,
				selectedBackColor, borderColor
			};
			return rv;
          } catch (Exception ex) {
              System.err.println("TreeView Unexpected exeption: " + ex);
              return null;
          }
      }

  	public BeanDescriptor getBeanDescriptor()
	{
		return new BeanDescriptor(beanClass, PVTreeCustomizer.class);
	}
}
