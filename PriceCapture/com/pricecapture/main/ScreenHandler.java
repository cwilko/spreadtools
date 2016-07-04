/*
 * Created on 28 Nov 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.pricecapture.main;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

public class ScreenHandler {

    public static Color getPixelColor(Point p){
        return getPixelColor(p.x, p.y);
    }

    public static BufferedImage getScreen(Point hg, Point bd){
        checkNano();
        return nano.createScreenCapture(new Rectangle(hg, new Dimension(bd.x-hg.x, bd.y-hg.y)));
    }
    
    public static boolean areImagesEqual(BufferedImage img1, BufferedImage img2){
        int[] timg1 = getPixels(img1);
        int[] timg2 = getPixels(img2);
        for(int i = 0 ; i < timg1.length; i++){
            if(timg1[i]!=timg2[i]){
                return false;
            }
        }
        return true;
    }
    
    public static Color analyse(Point depart, int deviation, Color fond){
        for(int i= depart.x; i < depart.x+deviation; i++){
            Color col = ScreenHandler.getPixelColor(i, depart.y);
            if(!col.equals(fond))
                return col;
        }
        //IOHandler.abort("[ScreenHandler.analyse] : Aucune couleur de jeu trouvée");
        return null;
    }
///////////////////////////////////////////////////////////////////////////////////
    private static Robot nano;
    
    private static Color getPixelColor(int x, int y){
        checkNano();
        return nano.getPixelColor(x, y);
    }
    
    private static int[] getPixels(BufferedImage img){
        return img.getRaster().getPixels(img.getRaster().getMinX(), img.getRaster().getMinY(),  img.getRaster().getWidth(), img.getRaster().getHeight(), new int[ img.getRaster().getWidth()*img.getRaster().getHeight()*10]);
    }
    
    private static void checkNano(){
        if(nano == null)
            try {
                nano = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
    }
}