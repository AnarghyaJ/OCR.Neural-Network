/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;


/**
 *
 * @author Anarghya
 */
public class Grayscale {
    
   BufferedImage  image;
   int width;
   int height;
   
   Grayscale(BufferedImage original) {
   
      try {
         
         image = original;
         width = image.getWidth();
         height = image.getHeight();
         
         for(int i=0; i<height; i++){
         
            for(int j=0; j<width; j++){
            
               Color c = new Color(image.getRGB(j, i));
               
               //The following conversion method is called Luminance preserving
               
               int red = (int)(c.getRed() * 0.2126);
               int green = (int)(c.getGreen() * 0.7152);
               int blue = (int)(c.getBlue() *0.0722);
               Color newColor = new Color(red+green+blue,red+green+blue,red+green+blue);
               
               image.setRGB(j,i,newColor.getRGB());
            }
         }
         
         File ouptut = new File("grayscale.jpg");
         ImageIO.write(image, "jpg", ouptut);
        
      } catch (Exception e) {}
   }
   
    
}
