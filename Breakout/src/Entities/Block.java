package Entities;

import java.awt.Color;
import java.awt.Graphics;

public class Block {

	int HP;
	Color col;
	int xPos;
	int yPos;
	
	int width = 76;
	int height = 24;
	
	public Block(int xPosition, int yPosition, Color color, int health) {
		this.HP = health;
		this.col = color;
		this.xPos = xPosition +=2;
		this.yPos = yPosition +=2;
	}
	
	public void draw(Graphics g) {
		g.fillRect(this.xPos, this.yPos, width, height);
	}
}
