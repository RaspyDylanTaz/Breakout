package Entities;

import java.awt.Graphics;

public class Ball {

	private int x;
	private int y;
	private int screenHeight;
	private int screenWidth;
	
	private int height = 25;
	private int width = 25;
	private int xVelocity = 1;
	private int yVelocity = 1;

	public Ball(int width, int height) {
		this.screenHeight = height;
		this.screenWidth = width;
		this.x = screenWidth/2-this.width/2;
		this.y = screenHeight - 150;
	}

	public void update(Platform plat) {

		
		//first check bounding walls for out fo bounds
		if(x+width>screenWidth) {
			 this.xVelocity = -1;
		}
		if(x<0) {
			 this.xVelocity = 1;
		}
		if(y+height>screenHeight) {
			 this.yVelocity = -1;
			//do nothing yet remove life and reset
		}
		if(y<0) {
			 this.yVelocity = 1;
		}
		
		//secondly check collision with platform
		//check to see if y value  is less than or equal to platform
		//then secondly check to see if 
		if( this.y + this.height > plat.getY()) { 
			
			if((this.x+this.width/2 > plat.getX()-plat.getWidth()/2) && (this.x+width/2 < plat.getX())) {
				
				if(xVelocity == 1) this.xVelocity=-1;
				this.yVelocity = -1;

				
			}else if((this.x+this.width/2 >= plat.getX()) && (this.x+this.width/2 < plat.getX()+plat.getWidth()/2)) {
				
				if(xVelocity == -1) this.xVelocity=1;
				this.yVelocity = -1;

			}
			
		}
		
		this.x = this.x + this.xVelocity;
		this.y = this.y + this.yVelocity;
		
		
	}
	
	public void draw(Graphics g) {
		g.fillOval(x-width/2, y-height/2, width, height);
	}
}
