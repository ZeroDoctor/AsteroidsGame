package com.zerulus.util;

public class Vector2f {

	public float x;
	public float y;
	
	public static float worldX;
	public static float worldY;
	
	public Vector2f() {
		x = 0;
		y = 0;
	}
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void addX(float x) { this.x += x; }
	public void addY(float y) { this.y += y;}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setVector(Vector2f vec) {
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public void setVector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public static void setWorldVar(float x, float y) {
		worldX = x;
		worldY = y;
	}
	
}
