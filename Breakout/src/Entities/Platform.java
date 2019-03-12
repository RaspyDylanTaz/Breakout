package Entities;

import java.awt.Color;
import java.awt.Graphics;

public class Platform {

	private double x;
	private double y;
	private int width = 150;
	private int height = 10;
	private int screenWidth;
	private int screenHeight;
	
	public Platform(int width, int height) {
		this.screenHeight = height;
		this.screenWidth = width;
		this.x=width/2-this.width/2;
		this.y=height-100;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.x = y;
	}
	
	public int getWidth() {
		return this.width;
	}
	public void draw(Graphics g) {
		g.fillRect((int)this.x-width/2, (int)this.y-height/2, (int)width, (int)height);
	}
	
	public void lifeReset() {
		this.x=width/2-this.screenWidth/2;
		this.y=this.screenHeight-100;
	}
	
}
