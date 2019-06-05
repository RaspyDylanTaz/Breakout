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
/**
 * Creates and displays the menu, containing three buttons which will either, play game, view hiscores and exit the window.
 * @param m
 */
	public Menu(Main m) {
		this.setForeground(Color.DARK_GRAY);
		this.setBackground(Color.BLACK);

		this.setLayout(new GridLayout(3, 1));
		Border line = new LineBorder(Color.WHITE);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
			
		//button direction user to the hiscores menu
		JButton hiscores = new JButton("HISCORES", null);
		hiscores.setOpaque(false);
		hiscores.setContentAreaFilled(false);
		hiscores.setBorderPainted(false);
		hiscores.setForeground(Color.DARK_GRAY);
		hiscores.setBackground(Color.BLACK);
		hiscores.setBorder(null);
		hiscores.setFont(new Font("Arial", Font.PLAIN, 40));
		//button direction to exit game
		JButton exit = new JButton("EXIT", null);
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.setForeground(Color.DARK_GRAY);
		exit.setBackground(Color.BLACK);
		exit.setBorder(null);
		exit.setFont(new Font("Arial", Font.PLAIN, 40));
		//button directing to play game
		JButton play = new JButton("PLAY", null);
		play.setOpaque(false);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
		play.setForeground(Color.DARK_GRAY);
		play.setBackground(Color.BLACK);
		play.setBorder(null);
		play.setFont(new Font("Arial", Font.PLAIN, 40));

		
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m.startGame();
				//start game
			}
		});
		hiscores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m.viewHiscores();
				//view hiscores
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				//exit game
			}
		});
		this.add(play);
		this.add(hiscores);
		this.add(exit);
	}

}
