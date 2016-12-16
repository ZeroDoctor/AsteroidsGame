package com.zerulus.states;

import java.awt.Graphics2D;

import com.zerulus.util.InputHandler;
import com.zerulus.util.MouseHandler;

public abstract class GameState {
	
	public static GameStateManager gsm;
	
	public GameState(GameStateManager gsm) {
		GameState.gsm = gsm;
	}
	
	public abstract void update();
	public abstract void render(Graphics2D g);
	public abstract void input(InputHandler keys, MouseHandler mouse);
	
}
