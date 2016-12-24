package com.zerulus.states;

import java.awt.Graphics2D;
import java.util.Stack;

import com.zerulus.util.InputHandler;
import com.zerulus.util.MouseHandler;

public class GameStateManager extends GameState{

	private Stack<GameState> states;
	
	public static final int MENU = 0;
	public static final int PLAY = 1;
	public static final int EXIT = 2;
	public static final int GUIMENU = 3;
	public static final int GAMEOVER = 4;
	
	public GameStateManager() {
		super(gsm);
		states = new Stack<GameState>();
		states.push(new MenuState(this));
	}
	
	public void setState(int i, boolean pop) {
		// This is mainly for PlayState to GUIMenuState
		if(pop) {
			states.pop();
		}
		
		if(i == 0) states.push(new MenuState(this));
		if(i == 1) states.push(new PlayState(this));
		//if(i == 2) states.push(new ExitState());
		//if(i == 3) states.push(new GUIMenuState()); 
		//if(i == 4) states.push(new GameOverState());
		
	}
	
	public void update() {
		states.peek().update();
	}
	
	public void input(InputHandler keys, MouseHandler mouse) {
		states.peek().input(keys, mouse);
		
		if(keys.escape.down) {
			System.exit(0);
		}
	}
	
	public void render(Graphics2D g) {
		states.peek().render(g);
	}
	
	
}
