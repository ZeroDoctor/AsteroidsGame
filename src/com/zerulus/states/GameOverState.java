package com.zerulus.states;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.zerulus.main.GamePanel;
import com.zerulus.util.InputHandler;
import com.zerulus.util.MouseHandler;
import com.zerulus.util.Sprite;
import com.zerulus.util.Vector2f;

public class GameOverState extends GameState {
	
	
	private BufferedImage[] img_gameover;
	private Vector2f vecGameOver;
	private int sizeGameOver = (GamePanel.WIDTH / 10);
	
	private BufferedImage[] img_continue;
	private Vector2f vecCont;
	private int sizeCont = (GamePanel.WIDTH / ("PRESS ENTER TO CONTINUE".length() + 15));
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);
		img_gameover = MenuState.spr_font.getFont("GAMEOVER");
		vecGameOver = new Vector2f(GamePanel.WIDTH / 2 - ("GAMEOVER".length() * sizeGameOver) / 2, GamePanel.HEIGHT / 2 - sizeGameOver / 2 - sizeCont);
		
		img_continue = MenuState.spr_font.getFont("PRESS ENTER TO CONTINUE");
		vecCont = new Vector2f(GamePanel.WIDTH / 2 - ("Press Enter to Continue".length() * sizeCont) / 2, GamePanel.HEIGHT / 2 - sizeCont / 2 + sizeCont);
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public void render(Graphics2D g) {
		Sprite.drawArray(g, img_gameover, vecGameOver, sizeGameOver, sizeGameOver, sizeGameOver, 0);
		Sprite.drawArray(g, img_continue, vecCont, sizeCont, sizeCont, sizeCont, 0);
		
	}

	@Override
	public void input(InputHandler keys, MouseHandler mouse) {
		keys.tick();
		if(keys.enter.clicked) {
			gsm.setState(0, true);
		}
		
	}

}
