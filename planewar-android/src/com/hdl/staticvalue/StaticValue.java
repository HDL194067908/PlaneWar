package com.hdl.staticvalue;

import java.util.Vector;

import com.hdl.gameSource.Bullet;
import com.hdl.gameSource.Enemy;
import com.hdl.gameSource.flyObject;

import android.graphics.Bitmap;

public class StaticValue {
	public static int number=0;// 击杀数
	public static int width, height;// 屏幕的宽高
	public static float bili;// 比例，用于适应不同屏幕
	public static Vector<Bullet> bullets = new Vector<Bullet>(); // 子弹集
	public static Vector<Enemy> enems = new Vector<Enemy>(); // 敌机集
	public static Vector<flyObject> list = new Vector<flyObject>();
	public static Bitmap planeimg, enemyimg, bulletimg,bombimg,planebombimg;// 图片：我的灰机 敌人灰机 我的子弹 敌机爆炸图像

}
