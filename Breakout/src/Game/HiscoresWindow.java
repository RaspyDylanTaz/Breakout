package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		
	/**
	 * panel to contain the hiscores, logic of the hiscores is kept in the hiscore file, this is used only to dispaly the data
	 * @param m
	 * @param width
	 * @param height
	 * @param hs
	 */
		public HiscoresWindow(Main m, int width, int height, Hiscores hs) {
			this.scores = hs.getScores();
			this.main = m;
			this.setForeground(Color.DARK_GRAY);
			this.setBackground(Color.BLACK);
			JButton back = new JButton("MENU", null);
			back.setOpaque(false);
			back.setContentAreaFilled(false);
			back.setBorderPainted(false);
			back.setForeground(Color.DARK_GRAY);
			back.setBackground(Color.BLACK);
			back.setBorder(null);
			back.setFont(new Font("Arial", Font.PLAIN, 40));
			add(back, BorderLayout.SOUTH);
			
			back.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					main.openMenu();

				}
			});
			// set up all entities for the game to begin
			this.height = height;
			this.width = width;

		}
		
		public void paint(Graphics g) {
			super.paint(g);
			g.setFont(new Font("Arial", Font.PLAIN, 40));
			g.setColor(Color.DARK_GRAY);
			System.out.println(scores.size());
			for(int i = 0; i < scores.size(); i ++) {
				g.drawString((i+1)+".  ", 400, i*30+100);
				
				g.drawString(scores.get(i).name+" "+scores.get(i).score, 500, i*30+100);	
				
			}
		}

}
