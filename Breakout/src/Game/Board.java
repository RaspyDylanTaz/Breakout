package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Entities.Ball;
import Entities.Platform;

public class Board extends JPanel implements MouseMotionListener {

	private Platform plat;
	private Ball ball;
	
	private double mouseX;
	private int width;
	private int height;
	
	public Board(int width, int height) {
		this.height = height;
		this.width = width;
		plat = new Platform(width,height);
		ball = new Ball(width, height);
		addMouseMotionListener(this);
		Timer t = new Timer();
		t.schedule(
		new TimerTask() {
			   @Override
			   public void run() {
				   update();
			   }
			}, 0, 1);
	}
	
	public void update() {
		//update the position of the platform on the game screen
		this.plat.setX(mouseX);
		this.ball.update(this.plat);
		this.repaint();
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
		this.plat.draw(g);
		this.ball.draw(g);
	}
}
