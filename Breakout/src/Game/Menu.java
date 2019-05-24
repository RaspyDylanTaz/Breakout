package Game;

import Game.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Entities.Ball;
import Entities.Platform;

public class Menu extends JPanel {

	private int height, width;

	public Menu(Main m) {
		this.setLayout(new GridLayout(3, 1));
		Border line = new LineBorder(Color.WHITE);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
			
		JButton hiscores = new JButton("Hiscores", null);
		hiscores.setForeground(Color.WHITE);
		hiscores.setBackground(Color.BLACK);
		hiscores.setBorder(null);
		hiscores.setFont(new Font("Arial", Font.PLAIN, 40));

		JButton exit = new JButton("Exit", null);
		exit.setForeground(Color.WHITE);
		exit.setBackground(Color.BLACK);
		exit.setBorder(null);
		exit.setFont(new Font("Arial", Font.PLAIN, 40));

		JButton play = new JButton("Play", null);
		play.setForeground(Color.WHITE);
		play.setBackground(Color.BLACK);
		play.setBorder(null);
		play.setFont(new Font("Arial", Font.PLAIN, 40));

		
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m.startGame();

			}
		});
		hiscores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.add(play);
		this.add(hiscores);
		this.add(exit);
	}

}
