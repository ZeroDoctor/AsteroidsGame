package com.zerulus.states;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import com.zerulus.entity.Asteroids;
import com.zerulus.entity.Bullet;
import com.zerulus.entity.Player;
import com.zerulus.main.GamePanel;
import com.zerulus.util.AABB;
import com.zerulus.util.InputHandler;
import com.zerulus.util.MouseHandler;
import com.zerulus.util.Sprite;
import com.zerulus.util.Vector2f;

public class PlayState extends GameState {
	
	private Player p;
	private Random rand;
	private ArrayList<Bullet> bullets;
	private ArrayList<Asteroids> asteroids;
	private AABB check;
	private int mouseX;
	private int mouseY;
	
	private int bulletTick;
	private int tick;
	private int create = 5;
	private boolean canCreate = true;
	
	private Integer score = 0;
	private ArrayList<BufferedImage> numScore;
	private int sizeNum = 20;
	
	private BufferedImage[] img_score;
	private int sizeScore = 20;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		check = new AABB();
		p = new Player();
		asteroids = new ArrayList<Asteroids>();
		bullets = new ArrayList<Bullet>();
		rand = new Random();
		
		img_score = MenuState.spr_font.getFont("SCORE");
		numScore = MenuState.spr_font.getFont(score);
		
		addAsteroids(1, (float) -1, -1, 3);
	}
	
	public void createBullet() {
		if(canCreate) {
			canCreate = false;
			bullets.add(new Bullet(new Vector2f(p.vec.x + (p.size / 2), p.vec.y + (p.size / 2)), new Vector2f(mouseX, mouseY), p.getDx(), p.getDy()));
		} else {
			if(bulletTick >= create) {
				canCreate = true;
				bulletTick = 0;
			}
		}
	}
	
	public void addAsteroids(int id, float x1, float y1, int total) {
		float x2, y2;
		for(int i = 0; i < total; i++) {
			if(x1 == -1) {
				x2 = rand.nextInt(GamePanel.WIDTH);
			} else {
				x2 = x1;
			}
			if(y1 == -1) {
				y2 = rand.nextInt(GamePanel.HEIGHT);
			} else {
				y2 = y1;
			}
			asteroids.add(new Asteroids(id, new Vector2f(x2, y2), (double) rand.nextInt(360)));
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
		 * */
		for(int i = 0; i < bullets.size(); i++) {
			for(int j = 0; j < asteroids.size(); j++) {
				if(check.colCircletoBox(bullets.get(i).bulletBounds, asteroids.get(j).asterBounds)){
					int id = asteroids.get(j).getID();
					
					float x = asteroids.get(j).getX();
					float y = asteroids.get(j).getY();
					
					asteroids.remove(j);
					if(id == 1) {
						score += 5;
						addAsteroids(2, x, y, 2);
					} else if(id == 2) {
						score += 10;
						addAsteroids(3, x, y, 2);
					} else {
						score += 15;
					}
					bullets.remove(i);
					break;
				}
			}
		}
		
		//for every x / 60 seconds make a group asteroids
		// x := the amount of updates
		if(tick % 210 == 0 || asteroids.size() == 0) {
			addAsteroids(1, -1, -1, rand.nextInt(4 + ((int) (tick / 360))));
		}
		
		
		//the bad part about this is you keep on render numbers
		//that doesn't need to be rendered
		//ex. 3456 then to 3457
		//numbers 3, 4, and 5 doesn't need to render again
		numScore = MenuState.spr_font.getFont(score++);
		
		tick++;
		bulletTick++;
		
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
		
		Sprite.drawArray(g, img_score, new Vector2f((float)25, (float) 25), sizeScore, sizeScore, sizeScore, 0);
		Sprite.drawArray(g, numScore, new Vector2f(5*20 + 30, 24), sizeNum, sizeNum, sizeNum, 0);
		
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
