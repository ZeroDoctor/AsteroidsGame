package com.zerulus.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.zerulus.main.GamePanel;
import com.zerulus.util.InputHandler;
import com.zerulus.util.MouseHandler;
import com.zerulus.util.Sprite;
import com.zerulus.util.Vector2f;

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
	
	public static Sprite spr_font;
	
	private Vector2f vecAster;
	private Vector2f vecPlay;
	private Vector2f vecQuit;
	private Vector2f vecHighscore;
	
	private int sizeAster = 64;
	private int sizeGen = 32;
	
	private boolean animate;
	private int tick;
	
	private final int FADE_OUT = 60;
	private int alpha;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		
		spr_font = new Sprite("/Game/font.png");
		
		vecAster = new Vector2f(GamePanel.WIDTH / 2 - (("ASTEROIDS".length() * 64) / 2), 100);
		vecPlay = new Vector2f(GamePanel.WIDTH / 2 - (("PLAY".length() * 32) / 2), GamePanel.HEIGHT - (GamePanel.HEIGHT / 2));
		vecHighscore = new Vector2f(GamePanel.WIDTH / 2 - (("HIGHSCORES".length() * 32) / 2), GamePanel.HEIGHT - (GamePanel.HEIGHT / 3) - 32);
		vecQuit = new Vector2f(GamePanel.WIDTH / 2 - (("QUIT".length() * 32) / 2), GamePanel.HEIGHT - (GamePanel.HEIGHT / 4));
		
		img_play = spr_font.getFont("PLAY");
		img_highscore = spr_font.getFont("HIGHSCORES");
		img_quit = spr_font.getFont("QUIT");
		img_aster = spr_font.getFont("ASTEROIDS");
		
	}
	
	
	
	public void animate() {
		//Ill come back to this is make it better
		int size = 100 - (GamePanel.WIDTH / 10);
		if(animate) {
			if(tick <= size) {
				sizeAster += 1;
				
				vecAster.x -= 5;
				vecAster.y += 5;
				
			}
			else if(tick >= size + 30) {
				if(tick <= size + 45) {
					vecAster.y -= 5;
				} else {
					//System.out.println(vecAster.y += 5);
					vecAster.y += 40;
				}
			}
			
			if(vecAster.y >= GamePanel.HEIGHT) {
				animate = false;
				gsm.setState(1, true);
			}
			
			alpha = (int) (255 * (1.0 * tick) / FADE_OUT);
			if(alpha > 255) alpha = 255;
			
			tick++;
		}
	}

	@Override
	public void update() {
		
		
		animate();
		
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
		
		Sprite.drawArray(g, img_play, vecPlay, sizeGen, sizeGen, sizeGen, 0);
		Sprite.drawArray(g, img_highscore, vecHighscore, sizeGen, sizeGen, sizeGen, 0);
		Sprite.drawArray(g, img_quit, vecQuit, sizeGen, sizeGen, sizeGen, 0);
		
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect((int) vecPlay.x, (int) vecPlay.y, "Play".length() * sizeGen, "Play".length() * sizeGen);
		g.fillRect((int) vecHighscore.x, (int) vecHighscore.y, "Highscores".length() * sizeGen, "Highscore".length() * sizeGen);
		g.fillRect((int) vecQuit.x, (int) vecQuit.y, "Quit".length() * sizeGen, "Quit".length() * sizeGen);
		
		
		Sprite.drawArray(g, img_aster, vecAster, sizeAster, sizeAster, sizeAster, 0);
		
		if(!animate) {
			g.setColor(Color.white);
			g.fillOval(choiceX, choiceY, choiceWidth, choiceHeight);
		}
		
	}

	@Override
	public void input(InputHandler keys, MouseHandler mouse) {
		keys.tick();
		
		if(keys.enter.clicked) {
			if(options == PLAY) {
				animate = true;
			}
			if(options == QUIT) {
				System.exit(0);
			}
		}
		
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
