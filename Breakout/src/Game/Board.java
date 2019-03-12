package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Entities.Ball;
import Entities.Block;
import Entities.Platform;

public class Board extends JPanel implements MouseMotionListener {

	private Platform plat;
	private Ball ball;
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private double mouseX;
	private int width;
	private int height;
	
	
	private int lives = 3;
	
	Timer t;
	
	public Board(int width, int height) {
		this.height = height;
		this.width = width;
		plat = new Platform(width,height);
		ball = new Ball(width, height);
		
		createBlocks();
		addMouseMotionListener(this);
		
		
		this.t = new Timer();
		t.schedule(
		new TimerTask() {
			   @Override
			   public void run() {
				   update();
			   }
			}, 0, 5);
	}
	
	
	
	public void update() {
		//update the position of the platform on the game screen
		this.plat.setX(mouseX);
		this.ball.update(this.plat, this.blocks);
		this.repaint();
		if(this.lives == 0 )
			t.cancel();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		this.mouseX=arg0.getX();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0,0,width, height);
		g.setColor(Color.WHITE);
		drawBlocks(g);
		this.plat.draw(g);
		this.ball.draw(g);
	}
	
	public void drawBlocks(Graphics g) {
		for(Block b: this.blocks){
			b.draw(g);
		}
	}
	
	public void createBlocks() {
		for(int i = 0; i < 7; i++) {
			for(int r = 0; r < 12; r++) {
				this.blocks.add(new Block(15+(80*r), 15+(30*i), Color.WHITE, 20));
			}
		}

	}
}
