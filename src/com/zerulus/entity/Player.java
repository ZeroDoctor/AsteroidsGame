package com.zerulus.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
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
	
	private BufferedImage img_player;
	
	private Animation flyUp = new Animation(flyingUp, 10);
	private Animation stay = new Animation(staying, 10);
	private Animation startUP = new Animation(startingUp, 10);
	
	private Vector2f vec;
	private float dx;
	private float dy;
	private int size = 32;
	
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	private float maxSpeed = 20;
	private float acc = 1.5f;
	private float deacc = 0.5f;
	
	public AABB playerBounds;
	
	private AffineTransformOp op;
	private AffineTransform tx;
	
	public Player() {
		vec = new Vector2f(GamePanel.WIDTH / 2 - size / 2, GamePanel.HEIGHT / 2 - size / 2);
		playerBounds = new AABB(vec, size, size);
		rotate(0);
	}
	
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
	
	public void rotate(double angle) {
		double rotation = angle;
		tx = AffineTransform.getRotateInstance(-rotation, size / 2, size / 2);
		if(tx == null)
			System.out.println("Nah");
		
		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	}
	
	public void update(int mouseX, int mouseY) {
		
		move();
		
		rotate(Math.atan2(vec.x - mouseX, vec.y - mouseY));
		
		vec.x += dx;
		vec.y += dy;
		
		playerBounds.setBox(vec, size, size);

		img_player = stay.getSprite();
		stay.start();
	}
	
	public void render(Graphics2D g) {
		
		if(img_player != null) {
			g.drawImage(op.filter(img_player, null), (int) vec.x, (int) vec.y, null);
		} else {
			g.drawImage(img_player, (int) vec.x, (int) vec.y, size, size, null);
		}		
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