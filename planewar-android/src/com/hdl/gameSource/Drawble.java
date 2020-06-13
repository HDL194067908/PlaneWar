package com.hdl.gameSource;

import com.hdl.planeworld.R;
import com.hdl.staticvalue.StaticValue;
import com.hdl.thread.LoadEnemy;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Drawble extends View {

	private Paint p = new Paint();// 画笔
	private float x, y;// 按下屏幕时的坐标
	private Plane myplane;
	private F5 f5;
	private LoadEnemy loadEnemy;

	public Drawble(Context context) {
		super(context);

		// 加载图片
		StaticValue.planeimg = BitmapFactory.decodeResource(getResources(), R.drawable.plane);
		StaticValue.enemyimg = BitmapFactory.decodeResource(getResources(), R.drawable.enemy);
		StaticValue.bulletimg = BitmapFactory.decodeResource(getResources(), R.drawable.bullet);
		StaticValue.bombimg = BitmapFactory.decodeResource(getResources(), R.drawable.bomb);
		StaticValue.planebombimg = BitmapFactory.decodeResource(getResources(), R.drawable.plane_destory);
		//线程开启
		f5 = new F5();
		f5.start();
		loadEnemy = new LoadEnemy();
		loadEnemy.start();

	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		if (e.getAction() == MotionEvent.ACTION_MOVE) {
			x = e.getX();
			y = e.getY();
		}
		myplane.setX(x - myplane.getWidth() / 2);
		myplane.setY(y - myplane.getHeight() / 2);
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) { // 这个相当于swing的paint方法吧 用于绘制屏幕上的所有物体
		super.onDraw(canvas);
		if (myplane.isLive()) {
			// 绘制得分
			p.setColor(Color.BLACK);
			canvas.drawText("击杀敌机:" + StaticValue.number, 0, 20, p);
			// 绘制飞机
			myplane.PlanePaint(canvas, p);
			// 绘制飞行物（子弹/敌机）
			for (int i = 0; i < StaticValue.list.size(); i++) {
				flyObject object = StaticValue.list.get(i);
				object.drawImage(canvas, p);
			}
		} else {
			//关闭线程
			f5.shutdown();
			loadEnemy.shutdown();
			
			planeBomb planeBomb = new planeBomb(myplane);
			planeBomb.drawImage(canvas, p);
			// 绘制最终得分
			Paint p1 = new Paint();
			p1.setColor(Color.RED);
			p1.setTextSize(50);
			canvas.drawText("最终得分:" + StaticValue.number, StaticValue.width/4, StaticValue.height / 2, p1);
		}

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {// 这个方法用来获取屏幕宽高的
		super.onSizeChanged(w, h, oldw, oldh);
		StaticValue.width = w;// 获取宽
		StaticValue.height = h;// 高
		StaticValue.bili = (float) (Math.sqrt(StaticValue.width * StaticValue.height) / Math.sqrt(1920 * 1080));
		p.setTextSize(50 * StaticValue.bili);// 设置字体大小，“击杀”的大小
		p.setColor(Color.WHITE);// 设为白色
		// 好了 到这里游戏开始了
		myplane = new Plane();// 初始化 我的灰机
	}

	private class F5 extends Thread {
		private volatile boolean flag=true;
		public void shutdown() {
			this.flag=false;
			interrupt();
		}
		@Override
		public void run() {
			// 每10ms刷新一次界面
			while (flag) {
				postInvalidate();// 刷新画布
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

}
