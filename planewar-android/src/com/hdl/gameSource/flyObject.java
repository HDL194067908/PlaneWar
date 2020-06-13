package com.hdl.gameSource;

import com.hdl.staticvalue.StaticValue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class flyObject {
	public RectF r = new RectF();// 矩形类，这个是用来确定位置的x,y
	// public float x, y;
	public Bitmap img;
	public float width, height;

	public float getX() {
		return r.left;
	}

	public void setX(float x) {
		// this.x = x;
		r.left = x;
		r.right = x + width;
	}

	public float getY() {
		return r.top;
	}

	public void setY(float y) {
		// this.y = y;
		r.top = y;
		r.bottom = y + height;
	}

	public Bitmap getImg() {
		return img;
	}

	public void setImg(Bitmap img) {
		this.img = img;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void drawImage(Canvas canvas, Paint p) {
		canvas.drawBitmap(img, r.left, r.top, p);

	}

	public boolean isHit(flyObject obj) {
		if (r.left  - obj.r.left <= obj.width && obj.r.left - this.r.left  <= this.width )
			if (r.top  - obj.r.top <= obj.height && obj.r.top - r.top <= height) {
				return true;
			}
		return false;

	}

}
