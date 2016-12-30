package com.zerulus.states;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import com.zerulus.entity.Asteroids;
import com.zerulus.entity.Bullet;
import com.zerulus.entity.Player;
import com.zerulus.main.GamePanel;
import com.zerulus.util.AABB;
import com.zerulus.util.InputHandler;
import com.zerulus.util.MouseHandler;
import com.zerulus.util.Vector2f;

public class PlayState extends GameState {
	
	private Player p;
	private Random rand;
	private ArrayList<Bullet> bullets;
	private ArrayList<Asteroids> asteroids;
	private AABB check;
	private int mouseX;
	private int mouseY;
	
	private int tick;
	private int create = 15;
	private boolean canCreate = true;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		check = new AABB();
		p = new Player();
		asteroids = new ArrayList<Asteroids>();
		bullets = new ArrayList<Bullet>();
		rand = new Random();
		
		
		for(int i = 0; i < 3; i++) {
			float x = (float) rand.nextInt(GamePanel.WIDTH);
			float y = (float) rand.nextInt(GamePanel.HEIGHT);
			asteroids.add(new Asteroids(1, new Vector2f(x, y), rand.nextInt(360)));
		}
	}
	
	public void createBullet() {
		if(canCreate) {
			canCreate = false;
			bullets.add(new Bullet(new Vector2f(p.vec.x + (p.size / 2), p.vec.y + (p.size / 2)), new Vector2f(mouseX, mouseY), p.getDx(), p.getDy()));
		} else {
			if(tick >= create) {
				canCreate = true;
				tick = 0;
			}
		}
	}

	@Override
	public void update() {
		p.update(mouseX, mouseY);
		
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).update();
			if(check.colCircletoBox(p.playerBounds, asteroids.get(i).asterBounds)) {
				System.out.println("OUCH!!!");
			}
		}
		
		for(int i = 0; i < bullets.size(); i++) {
			if(bullets.get(i).update()) {
				bullets.remove(i);
			}
		}
		
		
		/**
		 * I didn't think about having to worry about checking 
		 * collisions from two different ArrayList. We should
		 * think about coding AABB class a little bit differently.
		 * However, I'm just going with the flow right now.
		 * 
		 * */
		for(int i = 0; i < bullets.size(); i++) {
			for(int j = 0; j < asteroids.size(); j++) {
				if(check.colCircletoBox(bullets.get(i).bulletBounds, asteroids.get(j).asterBounds)){
					int id = asteroids.get(j).getID();
					Vector2f pos = asteroids.get(j).getPos();
					asteroids.remove(j);
					//still working on this
					if(id == 1) {
						asteroids.add(new Asteroids(2, pos, rand.nextInt(360)));
						asteroids.add(new Asteroids(2, pos, rand.nextInt(360)));
					}
					bullets.remove(i);
					break;
				}
			}
		}
		
		tick++;
		
	}

	@Override
	public void render(Graphics2D g) {
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
		
		p.render(g);
		
		for(int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).render(g);
		}
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
		
		if(mouse.getButton() == 1) {
			createBullet();
		}
		
	}

}
