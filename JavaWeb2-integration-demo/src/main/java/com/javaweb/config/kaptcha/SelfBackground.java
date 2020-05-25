package com.javaweb.config.kaptcha;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.google.code.kaptcha.BackgroundProducer;
import com.google.code.kaptcha.util.Configurable;

public class SelfBackground extends Configurable implements BackgroundProducer {
	
	public BufferedImage addBackground(BufferedImage baseImage){
		int width = baseImage.getWidth();
		int height = baseImage.getHeight();
		BufferedImage imageWithBackground = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graph = (Graphics2D) imageWithBackground.getGraphics();
		graph.setColor(new Color(135,206,250));//背景颜色
		graph.fill(new Rectangle2D.Double(0, 0, width, height));
		graph.drawImage(baseImage, 0, 0, null);
		return imageWithBackground;
	}
	
}
