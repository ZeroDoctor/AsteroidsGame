package com.zerulus.states;

import java.awt.Graphics2D;

import com.zerulus.entity.Player;
import com.zerulus.util.InputHandler;
import com.zerulus.util.MouseHandler;

public class PlayState extends GameState {
	
	private Player p;
	private int mouseX;
	private int mouseY;
	
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		p = new Player();
	}

	@Override
	public void update() {
		p.update(mouseX, mouseY);
		
	}

	@Override
	public void render(Graphics2D g) {
		p.render(g);
	}

	@Override
	public void input(InputHandler keys, MouseHandler mouse) {
		mouseX = mouse.getX();
		mouseY = mouse.getY();
		
		if(keys.up.down) {
			p.up = true;
		} else {
			p.up = false;
		}
		
		if(keys.down.down) {
			p.down = true;
		} else {
			p.down =  false;
		}
				
		if(keys.left.down) {
			p.left = true;
		} else {
			p.left = false;
		}
		
		if(keys.right.down) {
			p.right = true;
		} else {
			p.right = false;
		}
	}

}
