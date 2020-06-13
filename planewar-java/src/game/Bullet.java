package game;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Bullet {
	private int x;
	private int y;
	private int width;
	private int height;
	private ImageIcon image= new ImageIcon("img/bullet.png");
	
	
	public Bullet(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.width=image.getIconWidth();
		this.height=image.getIconHeight();
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
	public void move() {
		this.y-=5;
		
	}
	public void drawImage(Graphics g) {
		g.drawImage(image.getImage(),x,y,null);
		
	}
	
	

}
