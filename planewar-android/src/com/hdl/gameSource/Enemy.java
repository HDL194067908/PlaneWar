package com.hdl.gameSource;


import com.hdl.staticvalue.StaticValue;

//敌机
public class Enemy extends flyObject implements Runnable{
	
	public Enemy() {
		super();
		img=StaticValue.enemyimg;
		width=img.getWidth();
		height=img.getHeight();
		//r.left=(float)( Math.random()*(StaticValue.width-width));
		setX((float)(Math.random()*(StaticValue.width-width)));
		setY((float)(-Math.random()));
		StaticValue.list.add(this);
		StaticValue.enems.add(this);
		new Thread(this).start();
	}

	@Override
	public void run() {
		boolean flag=true;
		while(flag) {
			setY(this.r.top+2);
			flag=getY()>StaticValue.height?false:true;
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		StaticValue.enems.remove(this);
	}

	

}
