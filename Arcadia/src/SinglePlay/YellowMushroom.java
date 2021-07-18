package SinglePlay;

import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * 주황버섯 객체
 * @author Administrator
 *
 */
class YellowMushroom extends JLabel implements Runnable {
	private int flag; // flag = 1 : 왼쪽이동 , flag = 2 : 오른쪽 이동 , flag = 3 : 정지
	private int x, hp, Attack;
	private int y;
	private int moveTimer = 0;		//움직임을 제어하는 타이머
	private int aniTimer = 0;		//애니메이션을 제어하는 타이머
	private int aniNumber = 0;		//애니메이션 이미지를 제어하는 변수
	private int dx;
	private int count;
	private int n = 1;
	private int exp = 10;

	private Random random;

	YellowMushroom() {
		MakeMushroom();
		random = new Random();

		x = random.nextInt(1162 - 173) + 173;
		hp = 10;
		dx = 0;
		y = 510;
		Attack = 10;

		flag = (int) (Math.random() * 3);

		Thread t = new Thread(this);
		t.start();
	}
	
	public int getExp(){
		return exp;
	}

	public int getAttack() {
		return Attack;
	}

	public int getHP() {
		return hp;
	}

	public void MakeMushroom() {
		setIcon(new ImageIcon("resources/Pictures/Monster1.png"));
		setBounds(x, y, 200, 200);
		setVisible(true);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void moving() {
		x += dx;
	}
/**
 * 유저와의 충돌 판정을 위해 투명한 사각형을 설정
 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, 78, 68);
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
			if (count == 50 * n) {				//count가 50의 배수일 때마다 flag변경 ,count는 0.01초마다 1 증가
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
					flag = 0;
				if (x < -173)
					flag = 1;
				count++;
			}

			if (aniTimer >= 20) {
				aniTimer = 0;
				if (aniNumber == 0) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Mushroom1.png"));
					aniNumber++;
				} else if (aniNumber == 1) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Mushroom2.png"));
					aniNumber = 0;
				}
				// TODO
			}
		}
	}
}