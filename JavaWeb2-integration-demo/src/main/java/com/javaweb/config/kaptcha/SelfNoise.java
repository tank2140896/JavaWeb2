package com.javaweb.config.kaptcha;

import com.google.code.kaptcha.NoiseProducer;
import com.google.code.kaptcha.util.Configurable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.CubicCurve2D.Float;
import java.awt.image.BufferedImage;
import java.util.Random;

//参考：https://blog.csdn.net/qq_34248594/article/details/69262650
public class SelfNoise extends Configurable implements NoiseProducer {
	
    public SelfNoise() {
    	
    }

    public void makeNoise(BufferedImage image, float factorOne, float factorTwo, float factorThree, float factorFour) {
    	Color color = this.getConfig().getNoiseColor();
        int width = image.getWidth();
        int height = image.getHeight();
        Point2D[] pts = null;
        Random rand = new Random();
        CubicCurve2D cc = new Float((float)width * factorOne, (float)height * rand.nextFloat(), (float)width * factorTwo, (float)height * rand.nextFloat(), (float)width * factorThree, (float)height * rand.nextFloat(), (float)width * factorFour, (float)height * rand.nextFloat());
        PathIterator pi = cc.getPathIterator((AffineTransform)null, 2.0D);
        Point2D[] tmp = new Point2D[200];
        int i = 0;
        while(!pi.isDone()) {
            float[] coords = new float[6];
            switch(pi.currentSegment(coords)) {
	            case 0:
	            case 1:
	                tmp[i] = new java.awt.geom.Point2D.Float(coords[0], coords[1]);
	            default:
	                ++i;
	                pi.next();
	            //case PathIterator.SEG_MOVETO:
				//case PathIterator.SEG_LINETO:
				//case PathIterator.SEG_CUBICTO:
				//case PathIterator.SEG_QUADTO:
				//tmp[i] = new Point2D.Float(coords[0], coords[1]);
            }
            //++i;
            //pi.next();
        }
        pts = new Point2D[i];
        System.arraycopy(tmp, 0, pts, 0, i);
        Graphics2D graph = (Graphics2D)image.getGraphics();
        graph.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        graph.setColor(color);
        for(i = 0; i < pts.length - 1; ++i) {//for(i=0;i<10;i++) {
        	if (i < 3) {
                graph.setStroke(new BasicStroke(2.6F * (float)(4 - i)));
            }
            graph.drawLine((int)pts[i].getX(), (int)pts[i].getY(), (int)pts[i + 1].getX(), (int)pts[i + 1].getY());
        	//int xs = rand.nextInt(width);  
			//int ys = rand.nextInt(height);  
			//int xe = xs+rand.nextInt(width/8);  
			//int ye = ys+rand.nextInt(height/8);  
			//graph.setColor(color);  
			//graph.setColor(getColor());
			//graph.drawLine(xs, ys, xe, ye); 
        }
        graph.dispose();
    }
    
    public Color getColor(){
		Random random = new Random();
		int red = random.nextInt(255);  
		int green = random.nextInt(255);  
		int blue = random.nextInt(255);  
		return new Color(red, green, blue);  
	}
    
}
