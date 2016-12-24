package com.zerulus.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.zerulus.main.GamePanel;
import com.zerulus.util.InputHandler;
import com.zerulus.util.MouseHandler;
import com.zerulus.util.Sprite;

import sun.applet.Main;

@SuppressWarnings("unused")
public class MenuState extends GameState {
	
	private final int PLAY = 0;
	private final int SCORE = 1;
	private final int QUIT = 2;
	private int options = PLAY;
	
	private int choiceX;
	private int choiceY;
	private int choiceWidth = 16;
	private int choiceHeight = 16;
	
	//images for main menu;
	private BufferedImage[] img_play;
	private BufferedImage[] img_highscore;
	private BufferedImage[] img_quit;
	private BufferedImage[] img_aster;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		
		Sprite spr_font = new Sprite("/Game/font.png");
		
		img_play = spr_font.getFont("PLAY");
		img_highscore = spr_font.getFont("HIGHSCORES");
		img_quit = spr_font.getFont("QUIT");
		img_aster = spr_font.getFont("ASTEROIDS");
		
	}
	
	public void drawArray(Graphics2D g, BufferedImage[] img, int x, int y, int width, int height, int xOffset, int yOffset) {
		for(int i = 0; i < img.length; i++) {
			g.drawImage(img[i], x, y, width, height, null);
			x += xOffset;
			y += yOffset;
		}
	}

	@Override
	public void update() {
		
		if(options == PLAY) {
			choiceX = GamePanel.WIDTH / 2 - (("PLAY".length() * 32) / 2) - 30;
			choiceY = GamePanel.HEIGHT - (GamePanel.HEIGHT / 2) + 5;
		}
		if(options == SCORE) {
			choiceX = GamePanel.WIDTH / 2 - (("HIGHSCORES".length() * 32) / 2) - 30;
			choiceY = GamePanel.HEIGHT - (GamePanel.HEIGHT / 3) - 32 + 5;
		}
		if(options == QUIT) {
			choiceX = GamePanel.WIDTH / 2 - (("QUIT".length() * 32) / 2) - 30;
			choiceY = GamePanel.HEIGHT - (GamePanel.HEIGHT / 4) + 5;
		}
		
	}

	@Override
	public void render(Graphics2D g) {
		drawArray(g, img_aster, GamePanel.WIDTH / 2 - (("ASTEROIDS".length() * 64) / 2), 100, 64, 64, 64, 0);
		drawArray(g, img_play, GamePanel.WIDTH / 2 - (("PLAY".length() * 32) / 2), GamePanel.HEIGHT - (GamePanel.HEIGHT / 2), 32, 32, 32, 0);
		drawArray(g, img_highscore, GamePanel.WIDTH / 2 - (("HIGHSCORES".length() * 32) / 2), GamePanel.HEIGHT - (GamePanel.HEIGHT / 3) - 32, 32, 32, 32, 0);
		drawArray(g, img_quit, GamePanel.WIDTH / 2 - (("QUIT".length() * 32) / 2), GamePanel.HEIGHT - (GamePanel.HEIGHT / 4), 32, 32, 32, 0);
		
		g.setColor(Color.white);
		g.fillOval(choiceX, choiceY, choiceWidth, choiceHeight);
	}

	@Override
	public void input(InputHandler keys, MouseHandler mouse) {
		if(keys.enter.down) {
			if(options == PLAY) {
				gsm.setState(GameStateManager.PLAY, true);
			}
			if(options == QUIT) {
				System.exit(0);
			}
		}
		
		keys.tick();
		if(keys.up.clicked) {
			if(options == PLAY) {
				options = QUIT;
			} else {
				options--;
			}
		}
		
		if(keys.down.clicked) {
			if(options == QUIT) {
				options = PLAY;
			} else {
				options++;
			}
		}
	}

}
