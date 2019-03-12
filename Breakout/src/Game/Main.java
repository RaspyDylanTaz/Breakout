package Game;

import javax.swing.JFrame;

public class Main extends JFrame{
	
	public Main() {
		
	    int width = 1000, height = 500;
	    //TO DO: make a panel in TITLE MODE
	    ///////////////////////////////////
	    //panel in GAME MODE.
	    add(new Board(width, height));
	    //set default close
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(width,height);
	    //centers window
	    setLocationRelativeTo(null);
	    setTitle("Breakout");
	    setResizable(false);
	    setVisible(true); 
	}
	
	public static void main(String[] args) {
		Main m = new Main();
	}
}
