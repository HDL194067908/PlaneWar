package com.hdl.gameSource;

import com.hdl.staticvalue.StaticValue;

public class planeBomb extends flyObject implements Runnable{
	public planeBomb(flyObject plane) {
		super();
		setX(plane.getX());
		setY(plane.getY());
		img=StaticValue.planebombimg;
		width=img.getWidth();
		height=img.getHeight();	
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
