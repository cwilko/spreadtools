package com.pricecapture.main;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class OCR {
    static public ArrayList<String> recognize(Point hg, Point bd, Color color, boolean isColorEcriture) throws Exception{
        ArrayList<String> res = null;
        //File fImg2 = new File("screenshot2.png");
        File fImg = new File("screenshot.png");
        BufferedImage img = ScreenHandler.getScreen(hg, bd);
        //img = ImageIO.read(fImg2);
        
            if(isColorEcriture)
                img = changeWithColorEcriture(img, color);
            else
                img = changeWithColorFond(img, color);
          try {
              ImageIO.write(img, "PNG", fImg);
              Process p = Runtime.getRuntime().exec("\"C:\\Documents and Settings\\IBM_User\\My Documents\\My Dropbox\\Stock\\java\\nconvert\\XnView\\nconvert\" -out ppm -o text.ppm screenshot.png");
              p.waitFor();
              p.destroy();
              Process p2 = Runtime.getRuntime().exec("C:/tools/gocr048 -i text.ppm -o file");
              p2.waitFor();
              res = IOHandler.getResponse(new FileInputStream("file"));
              p2.destroy();
          }catch (Exception e) {
              e.printStackTrace();
          }
        
//        if(fImg.exists())
//            fImg.delete();
//        File texte = new File("text.ppm");
//        if(texte.exists())
//            texte.delete();
        return res;
    }

    private static BufferedImage changeWithColorEcriture(BufferedImage bi, Color ecriture) {
        if (bi != null) {                       
            int w = bi.getWidth();
            int h = bi.getHeight();
            int pixel;
            BufferedImage bitmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            BufferedImage biOut = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    pixel = bi.getRGB(x, y);
                    if(pixel != ecriture.getRGB())
                        pixel = Color.BLUE.getRGB();
                    else
                        pixel = Color.BLACK.getRGB();
                    bitmp.setRGB(x, y, pixel); 
                }
            }

            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    pixel = bitmp.getRGB(x, y);
                    if(pixel == Color.BLUE.getRGB())
                        pixel = Color.WHITE.getRGB();
                    biOut.setRGB(x, y, pixel);
                }
            }

            return biOut;
        } else {
            return bi;
        }
    }
    
    private static BufferedImage changeWithColorFond(BufferedImage bi, Color fond) {
        if (bi != null) {                       
            int w = bi.getWidth();
            int h = bi.getHeight();
            int pixel;
            BufferedImage bitmp = new BufferedImage(w, h, bi.getType());
            BufferedImage biOut = new BufferedImage(w, h, bi.getType());

            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    pixel = bi.getRGB(x, y);
                    if(pixel == fond.getRGB())
                        pixel = Color.BLUE.getRGB();
                    else
                        pixel = Color.WHITE.getRGB();
                    bitmp.setRGB(x, y, pixel); 
                }
            }

            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    pixel = bitmp.getRGB(x, y);
                    if(pixel == Color.BLUE.getRGB())
                        pixel = Color.WHITE.getRGB();
                    biOut.setRGB(x, y, pixel);
                }
            }

            return biOut;
        } else {
            return bi;
        }
    }
}