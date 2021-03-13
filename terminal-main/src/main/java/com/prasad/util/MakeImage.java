package com.prasad.util;

/**
 * Source: Java Developer Connection Tech Tips: Aug 23, 2001 Update to Techtips
 * This servlet can be used to dynamically generate web page images.
 */

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;

import java.util.*;

public class MakeImage extends HttpServlet {

    public void doGet(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("image/jpeg");

        // Create image
        int width = 200, height = 200;
        BufferedImage image = new BufferedImage(
            width, height, BufferedImage.TYPE_INT_RGB);

        // Get drawing context
        Graphics g = image.getGraphics();

        // Fill background
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);

        // Create random polygon
        Polygon poly = new Polygon();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            poly.addPoint(random.nextInt(width),
                random.nextInt(height));
        }

        // Fill polygon
        g.setColor(Color.cyan);
        g.fillPolygon(poly);

        // Dispose context
        g.dispose();

        // Send back image
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "jpg", sos);
        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
        //encoder.encode(image);
    }
}

