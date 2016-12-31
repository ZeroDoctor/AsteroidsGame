package com.zerulus.entity;

import java.awt.Graphics2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import com.zerulus.main.GamePanel;
import com.zerulus.util.AABB;
import com.zerulus.util.Animation;
import com.zerulus.util.Sprite;
import com.zerulus.util.Vector2f;

public class Player 
{
	private Sprite spr_ship = new Sprite("/Game/RocketShip.png");
	
	private BufferedImage[] flyingUp = {spr_ship.getSprite(0, 2), spr_ship.getSprite(1,  2), spr_ship.getSprite(2, 2)};
	private BufferedImage[] staying = {spr_ship.getSprite(0, 0)};
	private BufferedImage[] startingUp = {spr_ship.getSprite(1, 0), spr_ship.getSprite(2, 0), spr_ship.getSprite(0, 1), spr_ship.getSprite(1, 1), spr_ship.getSprite(2, 1)};
	
	public BufferedImage img_player;
	
	private Animation flyUp = new Animation(flyingUp, 10);
	private Animation stay = new Animation(staying, 10);
	private Animation startUP = new Animation(startingUp, 3);
	private Animation animation = stay;
	
	public Vector2f vec;
	private float dx;
	private float dy;
	public int size = 32;
	
	private float maxSpeed = 20;
	private float acc = 1f;
	private float deacc = 0.2f;
	
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	public AABB playerBounds;
	
	private AffineTransformOp op;
	
	public double rotation;
	
	public Player() {
		vec = new Vector2f(GamePanel.WIDTH / 2 - size / 2, GamePanel.HEIGHT / 2 - size / 2);
		playerBounds = new AABB(vec, size, size);
	}
	
	public float getDx() { return dx; }
	public float getDy() { return dy; }
	
	public void move() {
		if(up) {
			dy -= acc;
			if(dy < -maxSpeed) {
				dy = -maxSpeed;
			}
		} else {
			if(dy < 0) {
				dy += deacc;
				if(dy > 0) {
					dy = 0;
				}
			}
		}
		if (down) {
			dy += acc;
			if(dy > maxSpeed) {
				dy = maxSpeed;
			}
		} else {
			if(dy > 0) {
				dy -= deacc;
				if(dy < 0) {
					dy = 0;
				}
			}
		}
		if(left) {
			dx -= acc;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else {
			if(dx < 0) {
				dx += deacc;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		if(right) {
			dx += acc;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if(dx > 0) {
				dx -= deacc;
				if(dx < 0) {
					dx = 0;
				}
			}
		}
	}
	
	//this need more work
	public void animations() {
		if(dx == 0 && dy == 0) {
			animation.stop();
			animation.reset();
			animation = stay;
		} else if(!startUP.lastFrame()) {
			animation = startUP;
			animation.start();
		} else {
			animation = flyUp;
			animation.start();
		}
	}
	
	public void update(int mouseX, int mouseY) {
		
		img_player = stay.getSprite();
		stay.start();
		
		animations();
		
		animation.update();
		
		move();
		
		op = stay.rotate(Math.atan2(vec.x - mouseX, vec.y - mouseY), size, size);
		
		vec.x += dx;
		vec.y += dy;
		
		/**
		 * We could split the image when the player goes above or below
		 * the "play area", so it can create the illusion of looping. 
		 * This will do for now.
		 * */
		
		if(vec.y  > GamePanel.HEIGHT) {
			vec.y = 0;
		}
		if(vec.y < 0) {
			vec.y = GamePanel.HEIGHT;
		}
		
		if(vec.x > GamePanel.WIDTH) {
			vec.x = 0;
		}
		if(vec.x < 0) {
			vec.x = GamePanel.WIDTH;
		}
		
		playerBounds.setBox(vec, size, size);

		
	}
	
	public void render(Graphics2D g) {
		if(op == null) System.out.println("OP is null");
		
		if(img_player == null) 
			g.drawImage(animation.getSprite(), (int) vec.x, (int) vec.y, size, size, null);
		else
			g.drawImage(op.filter(animation.getSprite(), null), (int) vec.x, (int) vec.y, null);
	}
}

/* STILL NEEDS
 This is the actual animation
private Animation animation = standing;

In your update or tick method you will have:

animation.update();

In your render or draw method you will have:

g.drawImage(animation.getSprite(), x, y, null);

Lets say you have implemented MouseListener

public void mousePressed(MouseEvent e) {
    animation = walkLeft;
    animation.start();
}

public void mouseReleased(MouseEvent e) {
    animation.stop();
    animation.reset();
    animation = standing;
}
 */