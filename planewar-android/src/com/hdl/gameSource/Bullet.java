package com.hdl.gameSource;

import com.hdl.staticvalue.StaticValue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Bullet extends flyObject implements Runnable {

	public Bullet(Plane plane) {
		super();
//		this.r.left = plane.getX() + plane.getWidth() / 2;
//		this.r.top = plane.getY();
		setX(plane.getX() + plane.getWidth() / 2);
		setY(plane.getY());
		this.img = StaticValue.bulletimg;
		this.width = img.getWidth();
		this.height = img.getHeight();
		StaticValue.list.add(this);
		new Thread(this).start();
	}

	@Override
	public void run() {
		boolean flag = true;
		while (flag) {
			setY(this.r.top - 3);
			flag = getY() < 0 ? false : true;
			try {
				for (int i = 0; i < StaticValue.enems.size(); i++) {
					flyObject enmey = StaticValue.enems.get(i);
					if (isHit(enmey)) {
						StaticValue.number++;
						StaticValue.list.remove(enmey);
						StaticValue.enems.remove(enmey);
						StaticValue.list.add(new Bomb(enmey)); //添加爆炸效果
						flag = false;
						break;
					}
				}
				if (!flag)
					break;
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		StaticValue.list.remove(this);
	}

}
