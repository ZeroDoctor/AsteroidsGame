package com.zerulus.states;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.zerulus.util.InputHandler;
import com.zerulus.util.MouseHandler;

@SuppressWarnings("unused")
public class MenuState extends GameState {
	
	private final int PLAY = 0;
	private final int ABOUT = 1;
	private final int QUIT = 2;
	private int options = PLAY;
	
	//images for main menu;
	private BufferedImage img_play;
	private BufferedImage img_about;
	private BufferedImage img_quit;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics2D g) {
		
	}

	@Override
	public void input(InputHandler keys, MouseHandler mouse) {
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
