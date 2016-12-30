package com.zerulus.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.zerulus.main.GamePanel;
import com.zerulus.util.AABB;
import com.zerulus.util.Vector2f;

public class Asteroids {
	
	private int size;
	private double angle;
	private double speed;
	private Vector2f vec;
	private int id;
	
	public AABB asterBounds;
	
	public Asteroids() {
		vec = new Vector2f(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2);
		size = 64;
		speed = 0;
		angle = 0;
		id = 1;
		asterBounds = new AABB(vec, size);
	}
	
	public Asteroids(int id, Vector2f vec, double angle) {
		this.vec = vec;
		this.angle = angle;
		this.id = id;
		
		if(id == 1) {
			size = 64;
			speed = 2;
		}
		else if(id == 2) {
			size = 32;
			speed = 4;
		}
		else {
			size = 16;
			speed = 8;
		}
		
		asterBounds = new AABB(vec, size);
	}
	
	public int getID() { return id; }
	public Vector2f getPos() { return vec; }
	
	public void update() {
		vec.x += Math.cos(Math.toRadians(angle)) * speed;
		vec.y += Math.sin(Math.toRadians(angle)) * speed;
		
		if(vec.y  > GamePanel.HEIGHT) {
			vec.y = -size;
		}
		if(vec.y < -size) {
			vec.y = GamePanel.HEIGHT;
		}
		
		if(vec.x > GamePanel.WIDTH) {
			vec.x = -size;
		}
		if(vec.x < -size) {
			vec.x = GamePanel.WIDTH;
		}
		
		asterBounds.setCircle(vec, size);
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.white);
		g.drawOval((int) vec.x, (int) vec.y, size, size);
	}
	
}
