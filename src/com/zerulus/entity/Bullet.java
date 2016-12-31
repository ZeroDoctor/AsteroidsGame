package com.zerulus.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import com.zerulus.main.GamePanel;
import com.zerulus.util.AABB;
import com.zerulus.util.Animation;
import com.zerulus.util.Sprite;
import com.zerulus.util.Vector2f; 

public class Bullet extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	private Vector2f vec;
	private float x2, y2;
	private Vector2f vec3;
	private float pxSpeed;
	private float pySpeed;
	
	private int w = 6;
	private int h = 16;
	//FYI 60 ticks is a second
	private float life = 30;
	private int tick;
	
	public AABB bulletBounds;
	
	private Sprite spr_bullet = new Sprite("/Game/bullet.png");
	private BufferedImage[] img_bullet = {spr_bullet.getSprite()};
	private Animation rotate = new Animation(img_bullet, 10);
	private AffineTransformOp op;

	public Bullet(Vector2f vec, Vector2f vec2, float pxSpeed, float pySpeed) {
		this.vec = vec;
		x2 = vec.x;
		y2 = vec.y;
		this.vec3 = vec2;
		
		this.pxSpeed = Math.abs(pxSpeed);
		this.pySpeed = Math.abs(pySpeed);
		
		setBounds((int) vec.x, (int) vec.y, w, h);
		bulletBounds = new AABB(vec, w, h);
	}
	
	//if true than remove Bullet
	public boolean update() {
		if(tick >= life)
			return true;
		
		double dx = vec3.x - x2;
		double dy = vec3.y - y2;
		double rad = Math.atan2(dy, dx);
		
		//Still working on bullet rotations
		op = rotate.rotate(-rad - Math.PI / 2, w, h);
		
		vec.x += (Math.cos(rad) * (20 + pxSpeed));
		vec.y += (Math.sin(rad) * (20 + pySpeed));
		
		if(vec.x > GamePanel.WIDTH) {
			vec.x = -w;
		}
		if(vec.x < -w) {
			vec.x = GamePanel.WIDTH;
		}
		if(vec.y > GamePanel.HEIGHT + h) {
			vec.y = -h;
		}
		if(vec.y < -h) {
			vec.y = GamePanel.HEIGHT + h;
		}
		
		bulletBounds.setBox(vec, w, h);
		setBounds((int) vec.x, (int) vec.y, w, h);
		
		tick++;
		return false;
	}
	
	
	public void render(Graphics2D g) {
		//if(op == null) System.out.println("OP is null");
		
		if(img_bullet[0] == null || op == null)
			g.drawImage(img_bullet[0], (int) vec.x, (int) vec.y, w, h, null);
		else
			g.drawImage(op.filter(img_bullet[0], null), (int) vec.x, (int) vec.y, null);
	}
	
}
