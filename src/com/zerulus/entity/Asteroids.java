package com.zerulus.entity;

import java.awt.Graphics2D;

import com.zerulus.util.Vector2f;

public class Asteroids {
	
	private int size;
	private double angle;
	private double speed;
	private Vector2f vec;
	
	public Asteroids(int id, Vector2f vec, double angle) {
		this.vec = vec;
		this.angle = angle;
		
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
	}
	
	public void update() {
		vec.x += Math.cos(Math.toRadians(angle)) * speed;
		vec.y += Math.sin(Math.toRadians(angle)) * speed;
	}
	
	public void render(Graphics2D g) {
		g.fillOval((int) vec.x, (int) vec.y, size, size);
	}
	
}
