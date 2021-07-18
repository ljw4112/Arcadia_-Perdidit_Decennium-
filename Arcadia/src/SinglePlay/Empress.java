package SinglePlay;

import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 여제를 소환하고 정보가 담긴 클래스
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
class Empress extends JLabel implements Runnable {
	private final int Level = 200;
	private int HP = 3000000;
	private int Attack = 500;
	private int x = 1200, y = 410, dy;
	private int aniTimer = 0, aniNumber = 0, moveTimer = 0, flag, skillFlag, skillTimer = 0;
	private int skillWaitTimer = 0;		//여러가지 스킬을 쓰기 위한 클래스, 그러나 이 게임에서는 렉이 너무 걸리는 관계로 스킬은 안넣었습니다.
	private int count = 0, n = 1;
	private int skill;

	private Random random;

	Empress() {
		random = new Random();
		flag = (int) ((Math.random() * 10000) % 3);

		setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress.png"));
		setBounds(x, y, 300, 300);

		Thread t = new Thread(this);
		t.start();
	}

	public void move() {
		// this.setLocation(x, y);
		// y += dy;
	}

	public int getAttack() {
		return Attack;
	}

	public int getHP() {
		return HP;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 300, 300);
	}

	public void setSkill(int n) {

	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			aniTimer++;
			moveTimer++;
			skillTimer++;
			if (skillWaitTimer > 0) {
				skillWaitTimer++;
			}
			if (count == 50 * n) {
				flag = (int) ((Math.random() * 10000) % 3);
				n++;
			}

			if (moveTimer >= 6) {
				moveTimer = 0;
				if (flag == 0) {
					dy = -1;
					move();
				}
				if (flag == 1) {
					dy = 1;
					move();
				}
				if (flag == 2) {
					dy = 0;
					move();
				}
				if (y > 600)
					flag = 0;
				if (y < 50)
					flag = 1;
				count++;
			}

			if (aniTimer >= 9) {
				aniTimer = 0;
				if (skillFlag == 0) {
					if (skillWaitTimer >= 600) {
						skillWaitTimer = 0;
						skillFlag = 1;
					}
					if (aniNumber == 0) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress1.png"));
						aniNumber++;
					} else if (aniNumber == 1) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2.png"));
						aniNumber++;
					} else if (aniNumber == 2) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress3.png"));
						aniNumber++;
					} else if (aniNumber == 3) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress4.png"));
						aniNumber++;
					} else if (aniNumber == 4) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress5.png"));
						aniNumber++;
					} else if (aniNumber == 5) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress6.png"));
						aniNumber++;
					} else if (aniNumber == 6) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress7.png"));
						aniNumber++;
					} else if (aniNumber == 7) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress8.png"));
						aniNumber = 0;
					}
				} else if (skill == 1 && skillFlag == 1 && skillWaitTimer == 0) {
					setLocation(800, 200);
					if (aniNumber == 0) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill1.png"));
						aniNumber++;
					} else if (aniNumber == 1) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill2.png"));
						aniNumber++;
					} else if (aniNumber == 2) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill3.png"));
						aniNumber++;
					} else if (aniNumber == 3) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill4.png"));
						aniNumber++;
					} else if (aniNumber == 4) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill5.png"));
						aniNumber++;
					} else if (aniNumber == 5) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill6.png"));
						aniNumber++;
					} else if (aniNumber == 6) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill7.png"));
						aniNumber++;
					} else if (aniNumber == 7) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill8.png"));
						aniNumber++;
					} else if (aniNumber == 8) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill9.png"));
						aniNumber++;
					} else if (aniNumber == 9) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill10.png"));
						aniNumber++;
					} else if (aniNumber == 10) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill11.png"));
						aniNumber++;
					} else if (aniNumber == 11) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill12.png"));
						aniNumber++;
					} else if (aniNumber == 12) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill13.png"));
						aniNumber++;
					} else if (aniNumber == 13) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill14.png"));
						aniNumber++;
					} else if (aniNumber == 14) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill15.png"));
						aniNumber++;
					} else if (aniNumber == 15) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill16.png"));
						aniNumber++;
					} else if (aniNumber == 16) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill17.png"));
						aniNumber++;
					} else if (aniNumber == 17) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill18.png"));
						aniNumber++;
					} else if (aniNumber == 18) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill19.png"));
						aniNumber++;
					} else if (aniNumber == 19) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill20.png"));
						aniNumber++;
					} else if (aniNumber == 20) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill21.png"));
						aniNumber++;
					} else if (aniNumber == 21) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill22.png"));
						aniNumber++;
					} else if (aniNumber == 22) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill23.png"));
						aniNumber++;
					} else if (aniNumber == 23) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill24.png"));
						aniNumber++;
					} else if (aniNumber == 24) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill25.png"));
						aniNumber++;
					} else if (aniNumber == 25) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress_Skill26.png"));
						aniNumber = 0;
						setLocation(1200, 300);
						skillFlag = 0;
						skillWaitTimer = 1;
					}
				} else if (skillFlag == 2) {
					if (aniNumber == 0) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill1.png"));
						aniNumber++;
					} else if (aniNumber == 1) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill2.png"));
						aniNumber++;
					} else if (aniNumber == 2) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill3.png"));
						aniNumber++;
					} else if (aniNumber == 3) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill4.png"));
						aniNumber++;
					} else if (aniNumber == 4) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill5.png"));
						aniNumber++;
					} else if (aniNumber == 5) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill6.png"));
						aniNumber++;
					} else if (aniNumber == 6) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill7.png"));
						aniNumber++;
					} else if (aniNumber == 7) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill8.png"));
						aniNumber++;
					} else if (aniNumber == 8) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill9.png"));
						aniNumber++;
					} else if (aniNumber == 9) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill10.png"));
						aniNumber++;
					} else if (aniNumber == 10) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill11.png"));
						aniNumber++;
					} else if (aniNumber == 11) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill12.png"));
						aniNumber++;
					} else if (aniNumber == 12) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill13.png"));
						aniNumber++;
					} else if (aniNumber == 13) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill14.png"));
						aniNumber++;
					} else if (aniNumber == 14) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill15.png"));
						aniNumber++;
					} else if (aniNumber == 15) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill16.png"));
						aniNumber++;
					} else if (aniNumber == 16) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill17.png"));
						aniNumber++;
					} else if (aniNumber == 17) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill18.png"));
						aniNumber++;
					} else if (aniNumber == 18) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill19.png"));
						aniNumber++;
					} else if (aniNumber == 19) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill20.png"));
						aniNumber++;
					} else if (aniNumber == 20) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill21.png"));
						aniNumber++;
					} else if (aniNumber == 21) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill22.png"));
						aniNumber++;
					} else if (aniNumber == 22) {
						setIcon(new ImageIcon("resources/Pictures/CharacterAnimation/Empress2_Skill23.png"));
						aniNumber = 0;
					}
				}
			}

			if (skillTimer >= 500) {
				if (HP == 3000000) {
					skillFlag = (int) ((Math.random() * 10000) % 2);
					skillTimer++;
				}
			}
		}
	}
}