package com.zerulus.util;

public class Vector2i {

	public int x;
	public int y;
	
	public static int worldX;
	public static int worldY;
	
	public Vector2i() {
		x = 0;
		y = 0;
	}
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static void setWorldVar(int x, int y) {
		worldX = x;
		worldY = y;
	}
	
}
