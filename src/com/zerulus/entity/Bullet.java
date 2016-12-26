package com.zerulus.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import com.zerulus.util.Vector2f;

public class Bullet {
	
	private Vector2f vec;
	private Vector2f vec2;
	private int size = 16;
	private int life = 300;
	
	public Bullet(Vector2f vec, Vector2f vec2) {
		this.vec = vec;
		this.vec2 = vec2;
	}
	
	//if true than remove Bullet
	public boolean update() {
		if(vec.x > vec2.x + life || vec.y > vec2.y +life || vec.x < vec2.x - life || vec.y < vec2.y - life)
			return true;
		
		double dx = vec.x - vec2.x;
		double dy = vec.y - vec2.y;
		double rad = Math.atan2(dy, dx);
		
		vec.x += (Math.cos(rad) * 20);
		vec.y += (Math.sin(rad) * 20);
		return false;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.white);
        g.fill(new Ellipse2D.Float(vec.x, vec.y, size, size));
	}
	
}
