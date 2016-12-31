//code used from http://gamedev.stackexchange.com/questions/53705/how-can-i-make-a-sprite-sheet-based-animation-system
package com.zerulus.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import sun.applet.Main;

public class Sprite {
	private BufferedImage spriteSheet;
	private final int TILE_SIZE = 32;
	private int w;
	private int h;
	
	public Sprite(String file) {
		spriteSheet = loadSprite(Main.class.getResourceAsStream(file));
		h = TILE_SIZE;
		w = TILE_SIZE;
	}
	
	public Sprite(String file, int w, int h) {
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
	
	/*######################## getSprites ######################*/
	public BufferedImage getSprite() {
		return spriteSheet;
	}
	
	public BufferedImage getSprite(int xGrid, int yGrid){
		if (spriteSheet == null) {
			spriteSheet = loadSprite(Main.class.getResourceAsStream("AnimationSpriteSheet"));
		}
		
		return spriteSheet.getSubimage(xGrid * w, yGrid * h, w, h);
	}
	
	/*######################## getFonts ########################*/
	public BufferedImage[] getFont(String word) {
		
		BufferedImage[] img_word = new BufferedImage[30];
		
		for(int i = 0; i < word.length(); i++) {
			int a = word.charAt(i);
			img_word[i] = spriteSheet.getSubimage(((a - 65) % 6) * 48, ((int) ((a - 65) / 6)) * 48, 48, 48);
		}
		
		return img_word;
		
	}
	
	public ArrayList<BufferedImage> getFont(Integer k){
		
		ArrayList<BufferedImage> num = new ArrayList<BufferedImage>();
		
		int y = 4;
		String word = k.toString();
		for(int i = 0; i < k.toString().length(); i++) {
			int x = word.charAt(i) - 48;
			if(x > 3) {
				y = 5;
			}
			num.add(spriteSheet.getSubimage(((2 + x) % 6) * 48, y * 48, 48, 48));
		}
		
		return num;
	}
	
	public static void drawArray(Graphics2D g, BufferedImage[] img, Vector2f vec, int width, int height, int xOffset, int yOffset) {
		float x = vec.x;
		float y = vec.y;
		for(int i = 0; i < img.length; i++) {
			g.drawImage(img[i], (int) x, (int) y, width, height, null);
			x += xOffset;
			y += yOffset;
		}
	}
	
	public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f vec, int width, int height, int xOffset, int yOffset) {
		float x = vec.x;
		float y = vec.y;
		for(int i = 0; i < img.size(); i++) {
			g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
			x += xOffset;
			y += yOffset;
		}
	}

}
