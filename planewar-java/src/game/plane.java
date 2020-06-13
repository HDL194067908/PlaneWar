package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.Enemy;

public class plane extends JPanel implements MouseMotionListener {
	ImageIcon planeImage = new ImageIcon("img/plane.png");
	int planeX = 300;
	int planeY = 500;
	int number = 0;
	PlaneBomb planeBomb;
	private List<Enemy> enems = new ArrayList<Enemy>();
	private List<Bullet> bullets = new ArrayList<Bullet>();
	private List<Bomb> bombs = new ArrayList<Bomb>();

	public plane() {
		for (int i = 0; i < 10; i++) {
			enems.add(new Enemy());
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);	
		if(planeBomb==null) {
			// 绘制得分
			g.setFont(new Font("", Color.RED.getRed(), 30));
			g.drawString("得分:" + number, 20, 30);
			// 绘制飞机
			g.drawImage(planeImage.getImage(), planeX, planeY, null);
			// 绘制敌机
			for (int i = 0; i < enems.size(); i++) {
				Enemy enemy = enems.get(i);
				enemy.drawImage(g);
				if (enemy.getY() > Main.height) {
					enems.remove(enemy);
					enems.add(new Enemy());
				}
			}
			// 绘制子弹
			for (int i = 0; i < bullets.size(); i++) {
				Bullet b = bullets.get(i);
				b.drawImage(g);
				if (b.getY() < 0) {
					bullets.remove(b);
				}
			}
			// 绘制爆炸图像
			for (int i = 0; i < bombs.size(); i++) {
				Bomb bomb = bombs.get(i);
				bomb.drawImage(g);
			}
		}else {
			// 绘制得分
			g.setFont(new Font("", Color.RED.getRed(), 50));
			g.drawString("得分:" + number,Main.width/2-60, Main.height/2 );
			planeBomb.drawImage(g);
		}

	}

	// 鼠标监听
	@Override
	public void mouseDragged(MouseEvent e) {
		if(planeBomb==null) {
			planeX = e.getX() - planeImage.getIconWidth() / 2;
			planeY = e.getY() - planeImage.getIconHeight() / 2;
			if ((e.getX() + planeImage.getIconWidth()) > Main.width) {
				planeX = Main.width - planeImage.getIconWidth();
			} else if ((e.getX() - planeImage.getIconWidth() / 2) < 0) {
				planeX = 0;
			}
			if ((e.getY() + planeImage.getIconHeight()) > Main.height + 55) {
				planeY = Main.height - planeImage.getIconHeight();
			} else if ((e.getY() - planeImage.getIconHeight() / 2) < 0) {
				planeY = 0;
			}
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(planeBomb==null) {
			planeX = e.getX() - planeImage.getIconWidth() / 2;
			planeY = e.getY() - planeImage.getIconHeight() / 2;
			if ((e.getX() + planeImage.getIconWidth()) > Main.width) {
				planeX = Main.width - planeImage.getIconWidth();
			} else if ((e.getX() - planeImage.getIconWidth() / 2) < 0) {
				planeX = 0;
			}
			if ((e.getY() + planeImage.getIconHeight()) > Main.height + 55) {
				planeY = Main.height - planeImage.getIconHeight();
			} else if ((e.getY() - planeImage.getIconHeight() / 2) < 0) {
				planeY = 0;
			}
			repaint();
		}
	}

	// 绘制敌机
	public void init() {
		int flag = 0;
		while (true) {
			// 绘制敌机
			for (int i = 0; i < enems.size(); i++) {
				Enemy enemy = enems.get(i);
				enemy.move();
			}
			// 绘制子弹
			flag++;
			if (flag % 20 == 0) {
				Bullet bullet = new Bullet(planeX + planeImage.getIconWidth() / 2, planeY);
				bullets.add(bullet);
			}
			for (int i = 0; i < bullets.size(); i++) {
				Bullet b = bullets.get(i);
				b.move();
			}
			// 检测子弹是否碰撞
			for (int i = 0; i < enems.size(); i++) {
				Enemy e = enems.get(i);
				for (int j = 0; j < bullets.size(); j++) {
					Bullet b = bullets.get(j);
					if (isHit(e, b)) {
						enems.remove(e);
						enems.add(new Enemy());
						bombs.add(new Bomb(e.getX(), e.getY()));
						number++;
					}
				}
			}
			// 删除碰撞图像
			for (int i = 0; i < bombs.size(); i++) {
				Bomb bomb = bombs.get(i);
				bomb.count();
				if (bomb.getCount() > 10) {
					bombs.remove(bomb);
				}
			}
			
			//检测敌机是否碰撞
			for(int i=0;i<enems.size();i++) {
				Enemy e = enems.get(i);
				if(isHitPlane(e)) {
					planeBomb=new PlaneBomb(planeX, planeY);
				}
			}
			repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	// 子弹碰撞方法
	public boolean isHit(Enemy e, Bullet b) {
		// 1.指定一个区域
		Rectangle rect = new Rectangle(e.getX(), e.getY(), e.getWidth(), e.getHeight());

		// 2.表示 (x,y) 坐标空间中的位置的点
		Point p = new Point(b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2);

		return rect.contains(p);
	}

	// 敌机碰撞方法
	public boolean isHitPlane(Enemy e) {
		// 1.指定一个区域
		Rectangle rect = new Rectangle(planeX,planeY, planeImage.getIconWidth(), planeImage.getIconHeight());

		// 2.表示 (x,y) 坐标空间中的位置的点
		Point poi = new Point(e.getX() + e.getWidth() / 2, e.getY() + e.getHeight() / 2);

		return rect.contains(poi);
	}
}
