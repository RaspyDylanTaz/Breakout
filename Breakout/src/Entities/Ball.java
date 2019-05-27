package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import Game.Game;

public class Ball {

	private int x;
	private int y;
	private int screenHeight;
	private int screenWidth;
	
	private int height = 15;
	private int width = 15;
	private int xVelocity = 1;
	private int yVelocity = -1;

	
	private Game game;
	
	public Ball(int width, int height, Game g) {
		this.game = g;
		this.screenHeight = height;
		this.screenWidth = width;
		this.x = screenWidth/2-this.width/2;
		this.y = screenHeight-100-this.height;
	}

	public void update(Platform plat, ArrayList<Block> blocks) {

		
		//first check bounding walls for out fo bounds
		if(x+width>screenWidth) {
			 this.xVelocity = -1;
		}
		if(x<0) {
			 this.xVelocity = 1;
		}
		if(y+height>screenHeight) {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			plat.lifeReset();
			lifeReset();
		}
		if(y<0) {
			 this.yVelocity = 1;
		}
		
		checkPlatformCollision(plat);
		checkBlockCollision(blocks);

		this.x = this.x + this.xVelocity;
		this.y = this.y + this.yVelocity;
		
		
	}
	
	public void lifeReset() {
		this.x = screenWidth/2-this.width/2;
		this.y = screenHeight -100- this.height;
		this.yVelocity = -1;
		
		this.xVelocity = 1;
	}
	
	private void checkPlatformCollision(Platform plat){
		//secondly check collision with platform
				//check to see if y value  is less than or equal to platform
				//then secondly check to see if 
				if( this.y + this.height > plat.getY()) { 
					
					if((this.x+this.width/2 > plat.getX()-plat.getWidth()/2) && (this.x < plat.getX())) {
						
						if(xVelocity == 1 || xVelocity == 0) this.xVelocity=-1;
						this.yVelocity = -1;

						
					}else if((this.x >= plat.getX()) && (this.x-this.width/2 < plat.getX()+plat.getWidth()/2)) {
						
						if(xVelocity == -1 || xVelocity == 0) this.xVelocity=1;
						this.yVelocity = -1;

					}
					
				}
	}
	
	private void checkBlockCollision(ArrayList<Block> blocks){
		for(int i = 0; i < blocks.size(); i++) {
			Block b = blocks.get(i);
			int xblock = b.xPos;
			int yblock = b.yPos;
			
			int widthblock = b.width;
			int heightblock = b.height;
			
			if(this.x> xblock && this.x < xblock + widthblock) {
				if((this.y - this.height/2) < yblock + heightblock && (this.y - this.height/2) > yblock){
					this.yVelocity *=-1;
					b.HP -= 20;
				}else if((this.y + this.height/2) < yblock + heightblock && (this.y + this.height/2) > yblock){
					this.yVelocity *=-1;
					b.HP -= 20;
				}
			}
			
			if(this.y > yblock && this.y < yblock + heightblock) {
				if(this.x + this.width/2 > xblock && this.x+width/2 < xblock + widthblock ) {
					this.xVelocity *=-1;
					b.HP -= 20;
				}else if(this.x - this.width/2 > xblock && this.x - width/2 < xblock + widthblock) {
					this.xVelocity *=-1;
					b.HP -= 20;
				}
			}
			if (b.HP <= 0) {
				blocks.remove(i);
				i-=1;
			}
		}
	}
	

	
	public void outOfBounds() {
		if (this.y >= this.screenHeight-this.height) {
			this.game.loseALife();
			UIManager UI = new UIManager();
			UI.put("OptionPane.background", Color.BLACK);
			UI.put("Panel.background", Color.BLACK);
			if (this.game.getLives() == 0) {
				this.game.cancelTimer(); // if there is no more lifes left the game ends.
				
				double score = this.game.calcScore();
				JFrame f = new JFrame();
				JOptionPane.showInputDialog(f, "Oh no... you're out of lives. Maybe next time.  You managed to score a total of "+score+ " Please enter your name for the hiscores", "Alert",
						JOptionPane.WARNING_MESSAGE);
			} else {
				lifeReset();
				JFrame f = new JFrame();
				JOptionPane.showMessageDialog(f, "Out of bounds! you only have " + this.game.getLives() + " lives left!", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}

		}
	}
	
	public void draw(Graphics g) {
		g.fillOval(x-width/2, y-height/2, width, height);
	}
}
