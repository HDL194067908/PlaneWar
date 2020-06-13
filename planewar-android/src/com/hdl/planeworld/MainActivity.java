package com.hdl.planeworld;

import com.hdl.gameSource.Drawble;
import com.hdl.gameSource.Plane;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	private long time;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    getActionBar().hide();//隐藏标题栏
	    Drawble drawble = new Drawble(this);
	    setContentView(drawble);//setContentView()跟swing的add()差不多吧，不过这里只能添加一个控件，默认铺满屏幕     
	}
	public boolean onKeyDown(int keyCode,KeyEvent event) { //返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            long t=System.currentTimeMillis();//获取系统时间
            if(t-time<=500){
                exit(); //如果500毫秒内按下两次返回键则退出游戏
            }else{
                time=t;
                Toast.makeText(getApplicationContext(),"再按一次退出游戏",Toast.LENGTH_SHORT).show();
            }
 
            return true;
        }
        return false;
 
    }
    public void exit(){
        MainActivity.this.finish();
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
                System.exit(0);
            }
        }).start();
    }

}
