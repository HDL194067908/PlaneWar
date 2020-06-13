package com.hdl.gameSource;

import com.hdl.staticvalue.StaticValue;

public class Bomb extends flyObject implements Runnable{
	public Bomb(flyObject enemy) {
		super();
		setX(enemy.getX());
		setY(enemy.getY());
		img=StaticValue.bombimg;
		width=img.getWidth();
		height=img.getHeight();	
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		StaticValue.list.remove(this);
	}
}
