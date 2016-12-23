//code used from http://gamedev.stackexchange.com/questions/53705/how-can-i-make-a-sprite-sheet-based-animation-system
package com.zerulus.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import sun.applet.Main;

public class Sprite {
	private BufferedImage spriteSheet;
	private final int TILE_SIZE = 32;
	
	public Sprite(String file) {
		spriteSheet = loadSprite(Main.class.getResourceAsStream(file));
	}
	
	public BufferedImage loadSprite(InputStream file) {
		BufferedImage sprite = null;
		
		try {
			sprite = ImageIO.read(file);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return sprite;
	}
	
	public BufferedImage[] getFont(String word) {
		
		BufferedImage[] img_word = new BufferedImage[30];
		
		for(int i = 0; i < word.length(); i++) {
			int a = word.charAt(i);
			img_word[i] = spriteSheet.getSubimage(((a - 65) % 6) * 48, ((int) ((a - 65) / 6)) * 48, 48, 48);
		}
		
		return img_word;
		
	}
	
	public BufferedImage getSprite(int xGrid, int yGrid){
		if (spriteSheet == null) {
			spriteSheet = loadSprite(Main.class.getResourceAsStream("AnimationSpriteSheet"));
		}
		
		return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	}

}
