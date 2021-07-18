package SinglePlay;

import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * 신수를 소환하고 신수에 대한 정보가 담겨있는 클래스
 * @author Administrator
 *
 */
class Guardian extends JLabel implements Runnable {
	private final int Level = 190;
	private final int HP = 550000;
	private int Attack = 120 + (int) (Math.random() * 10);
	private int MoveTimer = 0, aniTimer = 0;
	private int aniNumber;
	private int count = 0, n = 1;
	private int flag;
	private int x, dx, y, dy;
	private int skill = 0;
	private int exp = 100;

	Guardian() {
		skill = 0;
		flag = (int) (Math.random() * 3);
		exp = 10000;

		x = 750;
		y = 490;

		setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo1.png"));
		setBounds(x, y, 255, 174);

		Thread t = new Thread(this);
		t.start();
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 150, 174);
	}

	public int getHP() {
		return HP;
	}

	public double getAttack() {
		return (double)Attack;
	}
	
	public int getEXP(){
		return exp;
	}
	
	public void setSkill(int n){
		skill = n;
	}

	@Override
	public void run() {
		while (true) {
			try {
				java.lang.Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}

			MoveTimer++;
			aniTimer++;
			if (count == 50 * n) {
				flag = (int) (Math.random() * 6);
				n++;
			}

			if (skill == 0 && aniTimer >= 15) {
				aniTimer = 0;

				if (aniNumber == 0) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo1.png"));
					aniNumber++;
				} else if (aniNumber == 1) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo2.png"));
					aniNumber++;
				} else if (aniNumber == 2) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo3.png"));
					aniNumber++;
				} else if (aniNumber == 3) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo4.png"));
					aniNumber++;
				} else if (aniNumber == 4) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo5.png"));
					aniNumber++;
				} else if (aniNumber == 5) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo6.png"));
					aniNumber++;
				} else if (aniNumber == 6) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo7.png"));
					aniNumber++;
				} else if (aniNumber == 7) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo8.png"));
					aniNumber++;
				} else if (aniNumber == 8) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo9.png"));
					aniNumber++;
				} else if (aniNumber == 9) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo10.png"));
					aniNumber++;
				} else if (aniNumber == 10) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo11.png"));
					aniNumber++;
				} else if (aniNumber == 11) {
					setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo12.png"));
					aniNumber = 0;
				}
			}
			if (skill == 1) {
				if (aniTimer >= 15) {
					aniTimer = 0;

					if (aniNumber == 0) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo_Attack1.png"));
						aniNumber++;
					} else if (aniNumber == 1) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo_Attack2.png"));
						aniNumber++;
					} else if (aniNumber == 2) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo_Attack3.png"));
						aniNumber++;
					} else if (aniNumber == 3) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo_Attack4.png"));
						aniNumber++;
					} else if (aniNumber == 4) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo_Attack5.png"));
						aniNumber++;
					} else if (aniNumber == 5) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo_Attack6.png"));
						aniNumber++;
					} else if (aniNumber == 6) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo_Attack7.png"));
						aniNumber++;
					} else if (aniNumber == 7) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo_Attack8.png"));
						aniNumber++;
					} else if (aniNumber == 8) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo_Attack9.png"));
						aniNumber++;
					} else if (aniNumber == 9) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo10_Attack.png"));
						aniNumber++;
					} else if (aniNumber == 10) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo11_Attack.png"));
						aniNumber++;
					} else if (aniNumber == 11) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo12_Attack.png"));
						aniNumber++;
					}else if (aniNumber == 12) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo13_Attack.png"));
						aniNumber++;
					}else if (aniNumber == 13) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Shinsoo14_Attack.png"));
						aniNumber = 0;
					}
				}
			}

		}
	}
}
