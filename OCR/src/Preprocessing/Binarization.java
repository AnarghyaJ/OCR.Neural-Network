/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.*;

import javax.imageio.ImageIO;



/**
 *
 * @author Anarghya
 */
public class Binarization {
    
    static File originalFile;
    static BufferedImage original,grayscale,binary;
    File graySavedFile,BinarizedImage;
    
    public void Binarization(File f) throws IOException
    {
    originalFile=f;
    original = ImageIO.read(originalFile);
    Grayscale g=new Grayscale(original);
    graySavedFile=new File("grayscale.jpg");
    grayscale=ImageIO.read(graySavedFile);
    binary=binarize(grayscale);
      BinarizedImage = new File("Binarized.jpg");
         ImageIO.write(binary, "jpg", BinarizedImage);
    
    }
    
    
    
    
    
    //Calculation of threshold value for a given image using Otsu's Theorem.
    private static int Treshold( ) 
{
 
    int[] histogram = imageHistogram(original);
    int total = original.getHeight() * original.getWidth();
 
    float sum = 0;
    for(int i=0; i<256; i++) sum += i * histogram[i];
 
    float sumB = 0;
    int wB = 0;
    int wF = 0;
 
    float varMax = 0;
    int threshold = 0;
 
    for(int i=0 ; i<256 ; i++)
    {
        wB += histogram[i];
        if(wB == 0) continue;
        wF = total - wB;
 
        if(wF == 0) break;
 
        sumB += (float) (i * histogram[i]);
        float mB = sumB / wB;
        float mF = (sum - sumB) / wF;
 
        float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);
 
        if(varBetween > varMax) 
        {
            varMax = varBetween;
            threshold = i;
        }
    }
 
    return threshold;
 
}
    
     public static int[] imageHistogram(BufferedImage input) 
     
    {
 
        int[] histogram = new int[256];
 
        for(int i=0; i<histogram.length; i++) histogram[i] = 0;
 
        for(int i=0; i<input.getWidth(); i++) {
            for(int j=0; j<input.getHeight(); j++) {
                int red = new Color(input.getRGB (i, j)).getRed();
                histogram[red]++;
            }
        }
 
        return histogram;
 
    }
     
     
     
     private static BufferedImage binarize(BufferedImage original/*Grayscaled*/) {
 
    int red;
    int newPixel;
 
    int threshold = Treshold( );
 
    BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
 
    for(int i=0; i<original.getWidth(); i++) {
        for(int j=0; j<original.getHeight(); j++) {
 
            // Get pixels
            red = new Color(original.getRGB(i, j)).getRed();
            int alpha = new Color(original.getRGB(i, j)).getAlpha();
            if(red > threshold) {
                newPixel = 255;
            }
            else {
                newPixel = 0;
            }
            newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
            binarized.setRGB(i, j, newPixel); 
 
        }
    }
 
    return binarized;
 
}
     
      private static int colorToRGB(int alpha, int red, int green, int blue) {
 
        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red; newPixel = newPixel << 8;
        newPixel += green; newPixel = newPixel << 8;
        newPixel += blue;
 
        return newPixel;
 
    }
     
    
}


