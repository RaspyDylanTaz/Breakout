package Game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import Util.Hiscores;

public class Main {
	private JFrame f;
	private Menu m;
	private Game g;
	
	private Hiscores hs;
	
	public Main() throws NumberFormatException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		UIManager UI = new UIManager();
		UIManager.put("RootPane.dialogBorder", new LineBorder(Color.BLACK));    
		UI.put("OptionPane.background", Color.BLACK);
		UI.put("Panel.background", Color.BLACK);
		setHs(new Hiscores()); 
		f = new JFrame();
		int width = 1000, height = 500;
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(width, height);
		// centers window
		f.setLocationRelativeTo(null);
		f.setTitle("Breakout");
		f.setResizable(false);
		f.setVisible(true);

		/*
		 * create new instance of the menu and then pass the menu an instance of the
		 * main game so that from the menu different game states can be selected.
		 */
		this.m = new Menu(this);
		f.add(m);
	}

	/**
	 * use this to open the hiscores menu, creates new JPanel and adds it to the frame
	 */
	public void viewHiscores() {
		f.getContentPane().removeAll();
		int width = 1000, height = 500;
		HiscoresWindow hiscoresWindow = new HiscoresWindow(this, width, height, this.getHs());
		hiscoresWindow.setVisible(true);
		f.add(hiscoresWindow);
		f.getContentPane().invalidate();
		f.getContentPane().validate();
		hiscoresWindow.setFocusable(true);
		hiscoresWindow.requestFocusInWindow();
	}

	/**
	 * Use this to start/restart the game start, is accessible from the in game game
	 * restart or from the menu selection of a new game
	 */
	public void startGame() {
		f.getContentPane().removeAll();

		int width = 1000, height = 500;
		Game game = new Game(width, height, this);
		game.setVisible(true);
		game.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					game.pause();
				}
			}
		});
		f.add(game);
		f.getContentPane().invalidate();
		f.getContentPane().validate();
		game.setFocusable(true);
		game.requestFocusInWindow();
	}
	
	/**
	 * opens the menu panel
	 */
	public void openMenu() {
		f.getContentPane().removeAll();
		Menu m = new Menu(this);
		f.add(m);
		f.getContentPane().invalidate();
		f.getContentPane().validate();

	}

	public static void main(String[] args) throws FileNotFoundException {
		try {
			Main m = new Main();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Hiscores getHs() {
		return this.hs;
	}

	public void setHs(Hiscores hs) {
		this.hs = hs;
	}
}
