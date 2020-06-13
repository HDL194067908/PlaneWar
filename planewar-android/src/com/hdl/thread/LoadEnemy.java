package com.hdl.thread;

import com.hdl.gameSource.Enemy;

public class LoadEnemy extends Thread {
	private volatile boolean flag = true;

	public void shutdown() {
		this.flag = false;
		interrupt();
	}

	@Override
	public void run() {
		while (flag) {
			// 每300ms刷一个敌人
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new Enemy();
		}

	}

}
