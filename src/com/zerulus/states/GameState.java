package com.zerulus.states;

import java.awt.Graphics2D;

public abstract class GameState {

	public GameState() {
		
	}
	
	public abstract void update();
	public abstract void render(Graphics2D g);
	public abstract void input();
	
}
