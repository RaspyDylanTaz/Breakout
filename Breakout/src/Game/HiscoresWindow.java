package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import Entities.Ball;
import Entities.Platform;
import Util.Hiscores;
import Util.Hiscores.Score;

public class HiscoresWindow extends JPanel {
		
		private int height, width;
		private Main main;
		private ArrayList<Score> scores = new ArrayList<Score>();
		
	
		public HiscoresWindow(Main m, int width, int height, Hiscores hs) {
			this.scores = hs.getScores();
			this.main = m;
			this.repaint();
			JButton back = new JButton("Back", null);
			back.setForeground(Color.WHITE);
			back.setBackground(Color.BLACK);
			back.setBorder(null);
			back.setFont(new Font("Arial", Font.PLAIN, 40));
			// set up all entities for the game to begin
			this.height = height;
			this.width = width;

		}
		
		public void paint(Graphics g) {
			for(int i = 0; i < scores.size(); i ++) {
				g.drawString(i+". "+scores.get(i).name+" "+scores.get(i).score, 350, i*200);	
				
			}
		}

}
