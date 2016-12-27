package com.zerulus.states;

import java.awt.Color;
import java.awt.Graphics2D;

import com.zerulus.entity.Asteroids;
import com.zerulus.entity.Player;
import com.zerulus.util.AABB;
import com.zerulus.util.InputHandler;
import com.zerulus.util.MouseHandler;

public class PlayState extends GameState {
	
	private Player p;
	private Asteroids a;
	private AABB check;
	private int mouseX;
	private int mouseY;
	
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		check = new AABB();
		p = new Player();
		a = new Asteroids();
	}

	@Override
	public void update() {
		p.update(mouseX, mouseY);
		a.update();
		
		if(check.colCircletoBox(p.playerBounds, a.asterBounds)) {
			System.out.println("OUCH!!!");
		}
	}

	@Override
	public void render(Graphics2D g) {
		p.render(g);
		a.render(g);
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
