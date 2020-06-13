package game;


import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
     
	public final static int width=600;
	public final static int height=800;
	
	public static void main(String[] args) {
		JFrame gameMain=new JFrame();
		gameMain.setTitle("飞机大战");
		gameMain.setSize(width, height);
		//设置关闭按钮终止JVM
		gameMain.setDefaultCloseOperation(3);
		//设置居中
		gameMain.setLocationRelativeTo(null);
		//设置图标
		//gameMain.setIconImage(new ImageIcon("img/plane.png").getImage());
		
		plane p=new plane();
		gameMain.add(p);
		gameMain.addMouseMotionListener(p);
		gameMain.setVisible(true);
		p.init();
	}

}
