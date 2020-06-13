package game;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy {
	private int width;
	private int height;
	
	private int x;
	private int y;
	
	private ImageIcon image=new ImageIcon("img/enemy.png");
	
	public Enemy() {
		width=image.getIconHeight();
		height=image.getIconHeight();
		Random random=new Random();
		this.x=random.nextInt(Main.width-width);
		this.y=-random.nextInt(Main.height-height);
	}
	
	public void move() {
		this.y+=2;
	}

	public void drawImage(Graphics g) {
		g.drawImage(image.getImage(), x, y, null);
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
}
