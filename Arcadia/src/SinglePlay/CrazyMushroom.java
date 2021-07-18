package SinglePlay;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 미친 머쉬맘을 관리하고 생성하는 클래스
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
class CrazyMushroom extends JLabel implements Runnable {
	private final int Level = 180;
	private final int HP = 200000;
	private int Attack = 80 + (int) (Math.random() * 10);
	private int x, y, flag, dx;
	private int moveTimer = 0, aniTimer = 0, aniNumber = 0;
	private int count = 0, n = 1;

	CrazyMushroom() {
		
		flag = (int) (Math.random() * 3);

		x = 580;
		y = 580;
		setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/CrazyMushroom1.png"));
		setBounds(x, y, 78, 68);

		Thread t = new Thread(this);
		t.start();
	}

	public void moving() {
		x += dx;
		setLocation(x, y);
	}
	
	public int getHP(){
		return HP;
	}
	
	public double getAttack(){
		return (double)Attack;
	}
	
	public int x(){
		return x;
	}
	
	public int y(){
		return y;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,78,68);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			moveTimer++;
			aniTimer++;
			if (count == 50 * n) {
				flag = (int) (Math.random() * 3);
				n++;
			}

			if (moveTimer >= 6) {
				moveTimer = 0;
				if (flag == 0) {
					dx = -2;
					moving();
				}
				if (flag == 1) {
					dx = 2;
					moving();
				}
				if (flag == 2) {
					dx = 0;
					moving();
				}
				if (x > 1162)
					flag = 1;
				if (x < -173)
					flag = 2;
				count++;
			}

			if (aniTimer >= 20) {
				aniTimer = 0;
				if (aniNumber == 0) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/CrazyMushroom1.png"));
					aniNumber++;
				} else if (aniNumber == 1) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/CrazyMushroom2.png"));
					aniNumber = 0;
				}
				// TODO
			}
		}
	}

}