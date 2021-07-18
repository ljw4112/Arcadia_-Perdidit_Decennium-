package SinglePlay;

import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * 스톤골렘을 등장시키고 관리하는 클래스
 * @author Administrator
 *
 */
class StoneGolem extends JLabel implements Runnable {
	private final int level = 55;
	private final int hp = 70000;
	private final int Attack;
	private int x, y, dx;
	private int moveTimer = 0;
	private int aniTimer = 0;
	private int aniNumber = 0;
	private int count, flag, n = 1;

	private Random random;
	private Login l;
	private mySQL my;

	StoneGolem() throws Exception {
		my = l.getMySQL();
		Attack = level * 80 / my.getLevel(my.getStringID());

		random = new Random();
		x = random.nextInt(1162 - 173) + 173;
		y = 460;

		setIcon(new ImageIcon("resources/Pictures/StoneGolem1.png"));
		setBounds(x, y, 200, 200);
		setVisible(true);

		flag = (int) (Math.random() * 3);

		Thread t = new Thread(this);
		t.start();
	}

	public void move() {
		this.setLocation(x, y);
		x += dx;
	}

	public int getHP() {
		return hp;
	}
	
	public int getAttack(){
		return Attack;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 175, 158);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			moveTimer++;
			aniTimer++;
			if (count == 50 * n) {
				flag = (int) ((Math.random() * 10000) % 3);
				n++;
			}

			if (moveTimer >= 6) {
				moveTimer = 0;
				if (flag == 0) {
					dx = -1;
					move();
				}
				if (flag == 1) {
					dx = 1;
					move();
				}
				if (flag == 2) {
					dx = 0;
					move();
				}
				if (x > 1162)
					flag = 0;
				if (x < -173)
					flag = 1;
				count++;
			}

			if (aniTimer >= 40) {
				aniTimer = 0;
				if (flag == 1) {
					if (aniNumber == 0) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/StoneGolem1.png"));
						aniNumber++;
					} else if (aniNumber == 1) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/StoneGolem2.png"));
						aniNumber++;
					} else if (aniNumber == 2) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/StoneGolem3.png"));
						aniNumber++;
					} else if (aniNumber == 3) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/StoneGolem4.png"));
						aniNumber = 0;
					}
				} else if (flag == 0) {
					if (aniNumber == 0) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/StoneGolem5.png"));
						aniNumber++;
					} else if (aniNumber == 1) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/StoneGolem6.png"));
						aniNumber++;
					} else if (aniNumber == 2) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/StoneGolem7.png"));
						aniNumber++;
					} else if (aniNumber == 3) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/StoneGolem8.png"));
						aniNumber = 0;
					}
				}
				// TODO
			}
		}

	}
}