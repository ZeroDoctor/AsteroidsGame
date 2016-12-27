package com.zerulus.util;


//Sort of
public class AABB {
	
	private Vector2f pos;
	private float w;
	private float h;
	private float r;
	
	public AABB() {
		
	}
	
	public AABB(Vector2f pos, int w, int h) {
		//places x and y at the center
		this.pos = new Vector2f(pos.x + (w / 2), pos.y + (h / 2));
		this.w = w / 2;
		this.h = h / 2;
	}
	
	public AABB(Vector2f pos, int r) {
		this.pos = new Vector2f(pos.x + (r / 2), pos.y + (r / 2));
		this.r = (r / (float) Math.sqrt(2));
	}
	
	public void setBox(Vector2f pos, int w, int h) {
		this.pos = new Vector2f(pos.x + (w / 2), pos.y + (h / 2));
		this.w = w / 2;
		this.h = h / 2;
	}
	
	public void setCircle(Vector2f pos, int r) {
		this.pos = new Vector2f(pos.x + (r / (float) Math.sqrt(2)), pos.y + (r / (float) Math.sqrt(2)));
		this.r = (r / (float) Math.sqrt(2));
	}
	
	public boolean colCircletoBox(AABB aBox, AABB bCircle) {
		float xDelta = bCircle.pos.x - Math.max(aBox.pos.x, Math.min(bCircle.pos.x, aBox.pos.x + aBox.w));
		float yDelta = bCircle.pos.y - Math.max(aBox.pos.y, Math.min(bCircle.pos.y, aBox.pos.y + aBox.h));
		
		if((xDelta * xDelta + yDelta * yDelta) < (bCircle.r * bCircle.r)) {
			return true;
		}
		
		return false;
	}
	
	public boolean circlesCollides(AABB aCircle, AABB bCircle) {
		float xd = aCircle.pos.x - bCircle.pos.x;
		float yd = aCircle.pos.y - bCircle.pos.y;
		float disSq = xd * xd + yd * yd;
		
		if(disSq < (aCircle.r + bCircle.r) * (aCircle.r + bCircle.r)) {
			return true;
		}
		
		return false;
	}
	
	public boolean collides(AABB aBox, AABB bBox) {
		
		if(Math.abs(aBox.pos.x - bBox.pos.x) < aBox.w + bBox.w) {
			if(Math.abs(aBox.pos.y - bBox.pos.y) < aBox.h + bBox.h) {
				return true;
			}
		}
		
		return false;
	}
	
	
}
