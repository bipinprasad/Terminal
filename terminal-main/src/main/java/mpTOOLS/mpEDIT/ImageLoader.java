
package mpTOOLS.mpEDIT;

import java.awt.*;

public interface ImageLoader
{
  Image loadImage(String image_dir, String image_file, Component observer, boolean waitForImage);
}
