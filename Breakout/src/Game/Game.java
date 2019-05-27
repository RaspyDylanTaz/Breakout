package Game;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Entities.Ball;
import Entities.Block;
import Entities.Platform;
import Util.JEnhancedOptionPane;

public class Game extends JPanel implements MouseMotionListener {

	private Platform plat;
	private Ball ball;
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private double mouseX;
	private int width;
	private int height;
	private Main main;
	private int lives = 3;
	
	private int numOfBlocks = 84;

	Timer t;
	private double timeTaken=0, start = System.currentTimeMillis();
	
	
	
	public Game(int width, int height, Main m) {
		this.main = m;
		this.repaint();
		addMouseMotionListener(this);
		// set up all entities for the game to begin
		this.height = height;
		this.width = width;
		plat = new Platform(width, height);
		ball = new Ball(width, height, this);

		
		createBlocks();

		//creates a new timer loop to start the game
		this.t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				update();
			}
		}, 0, 5);
	}

	/**
	 * Main running loop of the game, this will update all parts of the game and then redraw the game.
	 */
	public void update() {
		// update the position of the platform on the game screen
		this.plat.setX(this.mouseX);
		this.ball.update(this.plat, this.blocks);
		this.ball.outOfBounds();// check if the ball has gone out of bounds, if so a life is lost
		System.out.println(this.mouseX);
		this.repaint();

	}

	
	/**
	 * 
	 * @return number of lives remaining
	 */
	public int getLives() {
		return this.lives;
	}
	
	/**
	 * 
	 */
	public void cancelTimer() {
		this.t.cancel(); 
	}
	
	public void loseALife() {
		this.lives--;
	}
	
	
	/**
	 * creates a score based on time taken to complete the level, number of lives used, and number of blocks destroyed.
	 * @return a double of the players game score
	 */
	public double calcScore() {
		double multiplyer = 1.0;
		if(this.lives == 2) {
			multiplyer = 1.2;
		}else if (this.lives == 3) {
			multiplyer = 1.3;
		}
		this.timeTaken += (	System.currentTimeMillis()-this.start);
		double score = ((this.numOfBlocks - this.blocks.size()) * this.timeTaken * multiplyer)/1000;
		return score;
	}

	
	/**
	 * calls all of the paint methods for objects on the screen
	 */
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		drawBlocks(g);
		this.plat.draw(g);
		this.ball.draw(g);
		drawLives(g);
	}
	
	
	/**
	 * handles the pausing of the game, display JOptionPane with pause options
	 */
	public void pause() {
		JFrame f = new JFrame();
		
		this.timeTaken += (System.currentTimeMillis()-this.start);
		
		this.t.cancel();
		int choice = JOptionPane.showOptionDialog(null, 
		        "The Game is paused", 
		        "Feedback", 
		        JOptionPane.OK_CANCEL_OPTION, 
		        JOptionPane.INFORMATION_MESSAGE, 
		        null, 
		        new String[]{"Resume", "Exit"}, // this is the array
		        "default");
		
		if(choice == 0) {
			this.t = new Timer();
			this.start = System.currentTimeMillis();
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					
					update();
				}
			}, 0, 5);
		}else {
			this.main.openMenu();
		}
	}
	


	
	/**
	 * ---------------------------------------------------------------
	 * drawing methods below
	 */
	
	
	public void drawBlocks(Graphics g) {
		for (Block b : this.blocks) {
			b.draw(g);
		}
	}

	public void createBlocks() {
		for (int i = 0; i < 7; i++) {
			for (int r = 0; r < 12; r++) {
				this.blocks.add(new Block(15 + (80 * r), 15 + (30 * i), Color.WHITE, 20));
			}
		}

	}
	
	public void drawLives(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
		g.setColor(Color.RED);
		g.drawString("LIVES : "+this.lives, 10, this.height-50);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		this.mouseX = e.getX();
	}


}
