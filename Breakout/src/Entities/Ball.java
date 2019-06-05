package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import Game.Game;
import Util.Hiscores;
/**
 * Represents a ball object for the game
 * Contains all relevant  methods such as collision detection with
 * bounds, platform and blocks. As well as storing private fields such as location and dimensions
 * 
 * @author DaddyDyls
 *
 */
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

	/**
	 * updates the position and direction of the ball, also checks for collisions on each update.
	 * @param plat : The game platform(paddle)
	 * @param blocks
	 */
	public void update(Platform plat, ArrayList<Block> blocks) {

		
		//first check bounding walls for out of bounds
		if(x+width>screenWidth) {
			 this.xVelocity = -1;
		}
		if(x<0) {
			 this.xVelocity = 1;
		}
		// if the ball goes below the screen handle for life loss
		if(y+height>screenHeight) {
			plat.lifeReset();
			lifeReset();
		}
		if(y<0) {
			 this.yVelocity = 1;
		}
		//check collision
		checkPlatformCollision(plat);
		checkBlockCollision(blocks);
		//update position
		this.x = this.x + this.xVelocity;
		this.y = this.y + this.yVelocity;
		
		
	}
	
	/**
	 * reset the position of the ball to the starting position
	 */
	public void lifeReset() {
		this.x = screenWidth/2-this.width/2;
		this.y = screenHeight -100- this.height;
		this.yVelocity = -1;
		this.xVelocity = 1;
	}
	
	
	/**
	 * checks for collision between the ball and the platform, if there is collision update relevant parameters else do nothing
	 * 
	 * @param plat
	 */
	private void checkPlatformCollision(Platform plat){
		//secondly check collision with platform
				//check to see if y value  is less than or equal to platform
				//then secondly check to see if 
				if( this.y + this.height > plat.getY() && this.y+this.height < plat.getY()+plat.getHeight()) { 
					
					if((this.x+this.width/2 > plat.getX()-plat.getWidth()/2) && (this.x < plat.getX())) {
						
						if(xVelocity == 1 || xVelocity == 0) this.xVelocity=-1;
						this.yVelocity = -1;

						
					}else if((this.x >= plat.getX()) && (this.x-this.width/2 < plat.getX()+plat.getWidth()/2)) {
						
						if(xVelocity == -1 || xVelocity == 0) this.xVelocity=1;
						this.yVelocity = -1;

					}
					
				}
	}
	
	/**
	 * checks for collision between the ball and one of the current alive blocks
	 * When a collision occurs the block will be removed from the game
	 * @param blocks
	 */
	private void checkBlockCollision(ArrayList<Block> blocks){
		//iterate over every block and check for collision 
		for(int i = 0; i < blocks.size(); i++) {
			Block b = blocks.get(i);
			int xblock = b.xPos;
			int yblock = b.yPos;
			
			int widthblock = b.width;
			int heightblock = b.height;
			// using simple collision between two rectangles,case: if the ball hits a block from the side 
			if(this.x> xblock && this.x < xblock + widthblock) {
				if((this.y - this.height/2) < yblock + heightblock && (this.y - this.height/2) > yblock){
					this.yVelocity *=-1;
					b.HP -= 20;
				}else if((this.y + this.height/2) < yblock + heightblock && (this.y + this.height/2) > yblock){
					this.yVelocity *=-1;
					b.HP -= 20;
				}
			}
			//case: if the ball hits a block from below or above
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
	

	/**
	 * when the ball goes out of bounds a dialog will be displayed alerting the user they have died, and then notify the user of how many lives they have left.
	 * if the user runs out of lives then a separate dialog will appear allowing the user to enter their score into the hiscores.
	 * @throws IOException
	 */
	public void outOfBounds() throws IOException {
		if (this.y >= this.screenHeight-this.height) {
			this.game.loseALife();
			if (this.game.getLives() == 0) {
				//if no more lives remain show hiscores entry dialog
				this.game.cancelTimer(); 
				
				double score = this.game.calcScore();
				JFrame f = new JFrame();
				String s = JOptionPane.showInputDialog(f, "<HTML><font color=White> Oh no... you're out of lives.</font><br>"
						+ "<HTML><font color=White> Maybe next time.</font><br>"
						+ "<HTML><font color=White> You managed to score a total of </font>"
						+ " <HTML><font color=White> "+score+"</font><br>"
						+ " <HTML><font color=White> Please enter your name for the hiscores </font>", "Alert",
						JOptionPane.WARNING_MESSAGE);
				game.submitScore(s,score);
				game.goToMenu();
			} else {
				
				//if the user still has lives left alert them and continue game
				lifeReset();
				JFrame f = new JFrame();
				 			JOptionPane.showMessageDialog(f, "<HTML><font color=White> Out of bounds! you only have</font> <HTML><font color=White>" + this.game.getLives() + "</font><HTML><font color=White> lives left!</font> ", "Alert",
						JOptionPane.WARNING_MESSAGE);
				 

			}

		}
	}
	
	public void draw(Graphics g) {
		g.fillOval(x-width/2, y-height/2, width, height);
	}
}
