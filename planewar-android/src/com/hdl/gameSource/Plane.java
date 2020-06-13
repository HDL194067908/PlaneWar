package com.hdl.gameSource;

import com.hdl.staticvalue.StaticValue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

//我的灰机
public class Plane extends flyObject implements Runnable {
	private boolean isLive;

	public Plane() {
		super();
		isLive = true;
		this.img = StaticValue.planeimg;
		this.width = StaticValue.planeimg.getWidth();
		this.height = StaticValue.planeimg.getHeight();
//		this.r.left = StaticValue.width / 2 - StaticValue.planeimg.getWidth() / 2;		
//		this.r.top = StaticValue.height / 2 + StaticValue.planeimg.getHeight();
		setX(StaticValue.width / 2 - StaticValue.planeimg.getWidth() / 2);
		setY(StaticValue.height / 2 + StaticValue.planeimg.getHeight());
		new Thread(this).start();// 发射子弹的线程
	}
	
	
	public boolean isLive() {
		return isLive;
	}


	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}


	@Override
	public String toString() {
		return "Plane [planeX=" + this.r.left + ", planeY=" + this.r.top + ", img=" + img + "]";
	}

	public void PlanePaint(Canvas canvas, Paint p) {
		canvas.drawBitmap(img, this.r.left, this.r.top, p);
	}

	@Override
	public void run() {
		while (isLive) {
			// 90毫秒发射一发子弹
			new Bullet(this);
			try {
				for (int i = 0; i < StaticValue.enems.size(); i++) {
					flyObject enmey = StaticValue.enems.get(i);
					if (isHit(enmey)) {
						StaticValue.number++;
						StaticValue.list.remove(enmey);
						isLive = false;
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(90);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
