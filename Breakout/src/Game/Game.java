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

	Timer t;

	public Game(int width, int height, Main m) {
		this.main = m;
		this.repaint();
		this.height = height;
		this.width = width;
		plat = new Platform(width, height);
		ball = new Ball(width, height);

		createBlocks();
		addMouseMotionListener(this);

		this.t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				update();
			}
		}, 0, 5);
	}

	public void update() {
		// update the position of the platform on the game screen
		this.plat.setX(mouseX);
		this.ball.update(this.plat, this.blocks);
		this.outOfBounds();// check if the ball has gone out of bounds, if so a life is lost

		this.repaint();

	}

	public void outOfBounds() {
		if (this.ball.outOfBounds()) {
			this.lives--;
			UIManager UI = new UIManager();
			UI.put("OptionPane.background", Color.BLACK);
			UI.put("Panel.background", Color.BLACK);
			if (lives == 0) {
				t.cancel(); // if there is no more lifes left the game ends
				JFrame f = new JFrame();
				JOptionPane.showMessageDialog(f, "Oh no... you're out of lives. Maybe next time", "Alert",
						JOptionPane.WARNING_MESSAGE);
			} else {
				this.ball.lifeReset();
				JFrame f = new JFrame();
				JOptionPane.showMessageDialog(f, "Out of bounds! you only have " + this.lives + " lives left!", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}

		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		this.mouseX = arg0.getX();
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		drawBlocks(g);
		this.plat.draw(g);
		this.ball.draw(g);
		drawLives(g);
	}
	
	public void pause() {
		JFrame f = new JFrame();
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
}
