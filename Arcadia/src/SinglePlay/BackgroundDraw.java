package SinglePlay;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.SQLException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * mainPanel과 관련된 모든 것을 제어하는 클래스
 * @author Jong-woo Lee
 * Copyleft
 */

@SuppressWarnings("serial")
class BackgroundDraw extends JLayeredPane implements ActionListener, Runnable, MouseListener, KeyEventDispatcher { // BackgroundMoveSource
	private int x, y;
	private float dx;
	private boolean isClick;

	private JFrame frame;
	private Random random;
	private mySQL m;
	
	private JLabel background;		//mainPanel의 백그라운드를 설정하는 변수
	private Timer time;				//더블버퍼링을 위한 스레드

	private String job;
	
	private Image img;				//mainPanel에 사용되는 모든 이미지를 ImageIcon
	private Image _i;
	private Image _i2;
	private Image Monster1;
	private ImageIcon i;
	private ImageIcon img1;
	private ImageIcon img2;
	private ImageIcon img3;
	private ImageIcon img4;
	private ImageIcon img5;
	private ImageIcon img6;
	private ImageIcon img7;
	private ImageIcon img8;
	private ImageIcon img9;
	private ImageIcon img10;
	private ImageIcon img11;
	private ImageIcon img12;
	private ImageIcon img13;
	private ImageIcon img14;
	private ImageIcon img15;
	
	private Image Portal;				//포탈 이미지		
	private Image backgroundImg;
	private Image Vendeur;				//상인 이미지
	private YellowMushroom[] ym;		//주황버섯 객체 배열
	private StoneGolem sg;				//스톤골렘 객체 배열
	private Guardian[] gd;				//신수 객체 배열
	private Empress em;					//여제 객체 배열
	private CrazyMushroom[] cm;			//미친 머쉬맘 객체 배열
	private Hunting h;					//Hunting 클래스 객체
	private JLabel ElderDialog;			//마을 장로 대화창
	private KeyboardFocusManager manager;		//키보드에서 입력받기 위한 keyboardFocusManager
	private JButton MonsterDamaged;		//JLabel이 먹히지 않아 선언한 버튼
	private JButton MonsterAttack;		//JLabel이 먹히지 않아 선언한 버튼
	private Rectangle r1, r2, r3;		//각각 몬스터와 유저의 충돌판정을 하기 위해 선언한 사각형
	private Market mar;					//상점 객체 JLabel
	private Thread t;						
	private JLabel Elder;				//마을장로
	private JLabel Shop;				//상점주인
	private JLabel HPBar, MPBar, EXPBar;		//MainGUI에서 선언한 HPBar,MPBar,EXPBar을 사용하기 위해 선언
	private CharacterSet cs;			//캐릭터의 정보를 저장하고 db에 바뀐 값들을 대입
	private Sound s1, s2, s3;			//사운드 출력 객체

	private final int MonsterNumber = 10;		//필드의 몬스터 갯수MAX (편의상 한마리로 만들었습니다
	private int MonsterCount;					//몬스터가 몇 마리 있는지
	private int west;							//객체가 서쪽으로 이동할 때
	private int east;							//객체가 동쪽으로 이동할 때
	private int direct;							//객체가 이동하는 방향
	private boolean isPutControl;				//ctrl키를 눌렀는지 안눌렀는지 다른 함수에서 판단하기 위한 변수
	private boolean collision;					//충돌 했는지 안했는지
	private int mapcode;						//MainMap에서 선택한 맵
	private int MonsterHP;						//몬스터의 체력
	private double MonsterDamage;				//몬스터가 주는 데미지	
	private int UserDamage;						//유저가 주는 데미지
	private int[] status;						//유저의 스테이터스를 DB로부터 받아옴
	private int Yellow, Stone, Empress, Guardian, Crazy;			//각 몬스터의 체력 저장

	private boolean isAttack;		//공격을 했는지 안했는지
	private boolean isNPC;			//mapCode가 마을이면 TRUE
	private boolean isGuardianClear;		//Guardian을 처치했을 때 EMPRESS을 소환하기 위한 변수.
	private boolean isCollision;			//충돌판정을 get하기 위한 변수
	public boolean isClear, isCrazyClear, isEmpressClear;		//각각의 몬스터들을 클리어 했을시에 Rectangle.setBounds를 초기화 하기 위한 변수.
	public int isClear2 = 0;

	BackgroundDraw(String address, JFrame f, boolean isNPC, int mapCode) throws Exception {
		frame = f;
		this.isNPC = isNPC;
		manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(this);
		t = new Thread(this);
		m = Login.getMySQL();

		ym = new YellowMushroom[5];
		sg = new StoneGolem();
		gd = new Guardian[5];
		cm = new CrazyMushroom[5];

		for (int i = 0; i < ym.length; i++) {
			ym[i] = new YellowMushroom();
			gd[i] = new Guardian();
			cm[i] = new CrazyMushroom();
		}
		em = new Empress();

		Yellow = ym[0].getHP();
		Stone = sg.getHP();
		Empress = em.getHP();
		Guardian = gd[0].getHP();
		Crazy = cm[0].getHP();

		Shop = new JLabel();
		mar = new Market();
		img15 = new ImageIcon("resources/Pictures/Portal.png");
		Portal = img15.getImage();

		collision = false;
		mapcode = mapCode;
		isGuardianClear = false;
		isCollision = false;
		isClear = false;
		isCrazyClear = false;
		isEmpressClear = false;

		status = new int[4];
		status = m.getUserStatus(m.getStringID());

		MonsterAttack = new JButton();

		if (mapCode == 3) {
			add(ym[0], 2, -1);
		} else if (mapCode == 4) {
			add(sg, 2, -1);
		} else if (mapCode == 5) {
			add(gd[0], 2, -1);
			add(em, 2, -1);
			add(cm[0], 2, -1);
		}
		
		backgroundImg = ImageIO.read(new File(address));
		background = new JLabel();
		background.setIcon(new ImageIcon(backgroundImg));
		background.setBounds(0, 0, 1366, 768);
		background.setVisible(true);
		add(background, 0, -1);

		isClick = true;
		i = new ImageIcon(address);
		img = i.getImage();

		time = new Timer(5, this);
		time.start();

		x = 10;
		y = -20;

		MonsterCount = 0;
		west = 0;
		east = 1;

		UserDamage = status[0] * m.getLevel(m.getStringID())
				+ (int) (Math.random() * (status[1] * m.getLevel(m.getStringID()) / 2));		//유저가 몬스터에게 입히는 데미지를 계산

		MonsterDamaged = new JButton("" + UserDamage);
		MonsterDamaged.setFont(new Font("Nexa Bold.otf", Font.BOLD, 50));
		MonsterDamaged.setForeground(Color.RED);
		MonsterDamaged.setBounds(ym[0].getX(), ym[0].getY() - 100, 500, 300);
		MonsterDamaged.setOpaque(false);
		MonsterDamaged.setContentAreaFilled(false);
		MonsterDamaged.setBorderPainted(false);
		MonsterDamaged.setVisible(false);
		add(MonsterDamaged, 2, -1);

		MonsterAttack.setText("" + MonsterDamage);
		MonsterAttack.setFont(new Font("Nexa Bold.otf", Font.BOLD, 50));
		MonsterAttack.setForeground(Color.BLUE);
		MonsterAttack.setBounds(x, y - 100, 200, 50);
		MonsterAttack.setOpaque(false);
		MonsterAttack.setContentAreaFilled(false);
		MonsterAttack.setBorderPainted(false);
		MonsterAttack.setVisible(false);
		add(MonsterAttack, 2, -2);

		this.addMouseListener(this);

		direct = 1;
		img7 = new ImageIcon("resources/Pictures/CharacterAnimation/TownElder.png");
		img8 = new ImageIcon("resources/Pictures/CharacterAnimation/ElderDialog.png");
		ElderDialog = new JLabel(new ImageIcon("resources/Pictures/CharacterAnimation/ElderDialog.png"));

		img14 = new ImageIcon("resources/Pictures/Vendeur.png");
		Vendeur = img14.getImage();

		job = m.getJob(m.getStringID());
		if (job.equals("Soldier")) {
			img1 = new ImageIcon("resources/Pictures/CharacterAnimation/LS_Soldier_Stand.png");
			img2 = new ImageIcon("resources/Pictures/CharacterAnimation/LS_Soldier_walk1.png");
			img3 = new ImageIcon("resources/Pictures/CharacterAnimation/LS_Soldier_walk2.png");
			img4 = new ImageIcon("resources/Pictures/CharacterAnimation/RS_Soldier_Stand.png");
			img5 = new ImageIcon("resources/Pictures/CharacterAnimation/RS_Soldier_walk1.png");
			img6 = new ImageIcon("resources/Pictures/CharacterAnimation/RS_Soldier_walk2.png");
			img10 = new ImageIcon("resources/Pictures/CharacterAnimation/LA_Soldier_walk1.png");
			img11 = new ImageIcon("resources/Pictures/CharacterAnimation/LA_Soldier_walk2.png");
			img12 = new ImageIcon("resources/Pictures/CharacterAnimation/RA_Soldier_walk1.png");
			img13 = new ImageIcon("resources/Pictures/CharacterAnimation/RA_Soldier_walk2.png");
		} else if (job.equals("Magician")) {

		}
		_i = img4.getImage();
		_i2 = img7.getImage();
		x = 10;
		y = 535;
		dx = 0;
		isAttack = false;

		Elder = new JLabel();
		Elder.setIcon(img7);
		Shop = new JLabel();
		Shop.setIcon(img14);
		Elder.setBounds(600, 480, 140, 130);
		Shop.setBounds(1000, 500, 200, 200);
		Elder.addMouseListener(this);
		Shop.addMouseListener(this);

		if (isNPC) {
			add(Elder, 2, -1);
			add(Shop, 2, -1);
		}

		setVisible(true);
	}

	public void setSound1(Sound so) {
		s1 = so;
	}

	public void setSound2(Sound sou) {
		s2 = sou;
	}

	public void setSound3(Sound soun) {
		s3 = soun;
	}

	public void setHPBar(JLabel h) {
		HPBar = h;
	}

	public void setMPBar(JLabel m) {
		MPBar = m;
	}

	public void setEXPBar(JLabel l) {
		EXPBar = l;
	}

	public void setCharacterSet(CharacterSet cs) {
		this.cs = cs;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			CheckCollision();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		move();
		repaint();
	}

	public void CheckCollision() throws Exception {
		boolean ok = false;

		switch (mapcode) {
		case 3:
			MonsterDamage = ym[0].getAttack() - m.getLevel(m.getStringID());
			if (MonsterDamage <= 0)
				MonsterDamage = 0;

			r1 = ym[0].getBounds();
			r2 = new Rectangle(x, y, 120, 120);

			if (r1.intersects(r2)) {
				MonsterAttack.setLocation(x - 30, y - 40);
				MonsterAttack.setVisible(true);
				isCollision = true;

				if (HPBar.getWidth() == 0) {
					s1.MusicStop();
					try {
						time.stop();
						frame.setVisible(false);
						MainMap mm = new MainMap();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (isPutControl) {
					MonsterDamaged.setLocation(ym[0].getX(), ym[0].getY());
					MonsterDamaged.setVisible(true);
					MonsterDamaged.repaint();
					MonsterDamaged.revalidate();
					Yellow -= UserDamage;

					HPBar.setSize((int) (HPBar.getWidth() - MonsterDamage), HPBar.getHeight());
					time.setDelay(1000);

					if (Yellow <= UserDamage) {
						MonsterDamaged.setVisible(false);
						ym[0].setVisible(false);
						ym[0].setEnabled(false);
						isClear = true;
						EXPBar.setSize((int) (EXPBar.getWidth() + EXPBar.getWidth() * 10 / cs.getMaxEXP()),
								EXPBar.getHeight());
						cs.setNewEXP(10);
					}
				}
				isPutControl = false;
			} else {
				MonsterDamaged.setLocation(0, 0);
				MonsterAttack.setVisible(false);
			}
			isCollision = false;

			if (isClear) {
				r1.setBounds(0, 0, 0, 0);

			}
			break;

		case 4:
			MonsterDamage = sg.getAttack() - m.getLevel(m.getStringID());
			if (MonsterDamage <= 0)
				MonsterDamage = 0;

			if (!isClear) {
				r1 = sg.getBounds();
				r2 = new Rectangle(x, y, 120, 120);
			}
			if (r1.intersects(r2)) {
				MonsterAttack.setLocation(x - 30, y - 40);
				MonsterAttack.setVisible(true);
				isCollision = true;

				HPBar.setSize(
						(int) (HPBar.getWidth() - (HPBar.getWidth() * (MonsterDamage / m.getUserHP(m.getStringID())))),
						HPBar.getHeight());

				if (HPBar.getWidth() == 0) {
					s2.MusicStop();
					try {
						time.stop();
						frame.setVisible(false);
						MainMap mm = new MainMap();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (isPutControl) {
					MonsterDamaged.setLocation(sg.getX() - 100, sg.getY() - 100);
					MonsterDamaged.setVisible(true);
					Stone -= UserDamage;
					if (Stone <= UserDamage) {
						MonsterDamaged.setVisible(false);
						sg.setVisible(false);
						sg.setEnabled(false);
						EXPBar.setSize((int) ((1366 * cs.setEXP()) + ((EXPBar.getWidth()) * 50.0 / cs.getMaxEXP())),
								EXPBar.getHeight());
						EXPBar.repaint();
						cs.setNewEXP(50);
						r1.setBounds(0, 0, 0, 0);
					}
				}
				isPutControl = false;
			} else {
				MonsterAttack.setVisible(false);
			}
			isCollision = false;
			break;

		case 5:
			MonsterDamage = gd[0].getAttack() - m.getLevel(m.getStringID());
			if (MonsterDamage <= 0)
				MonsterDamage = 0;

			r1 = gd[0].getBounds();
			r2 = new Rectangle(x, y, 120, 120);
			r3 = cm[0].getBounds();

			if (r1.intersects(r2)) {
				MonsterAttack.setText("" + MonsterDamage);
				MonsterAttack.setLocation(x - 30, y - 40);
				MonsterAttack.setVisible(true);

				if (HPBar.getWidth() == 0) {
					System.out.println(HPBar.getWidth() + " HPBar");
					System.out.println(s3 + " HPBar in s3");
					s3.MusicStop();
					try {
						time.stop();
						frame.setVisible(false);
						MainMap mm = new MainMap();
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				HPBar.setSize(
						(int) (HPBar.getWidth() - (HPBar.getWidth() * (MonsterDamage / m.getUserHP(m.getStringID())))),
						HPBar.getHeight());
				isCollision = true;

				if (isPutControl) {
					MonsterDamaged.setLocation(gd[0].getX() - 100, gd[0].getY() - 100);
					MonsterDamaged.setVisible(true);
					Guardian -= UserDamage;
					if (Guardian <= 0) {
						MonsterDamaged.setVisible(false);
						gd[0].setVisible(false);
						gd[0].setEnabled(false);

						isGuardianClear = true;

						r1.setBounds(0, 0, 0, 0);
						EXPBar.setSize((int) ((1366 * cs.setEXP()) + ((EXPBar.getWidth()) * 100 / cs.getMaxEXP())),
								EXPBar.getHeight());
						EXPBar.repaint();
						m.setEXP(100, m.getStringID());
					}
				}
				isPutControl = false;
			} else {
				MonsterAttack.setVisible(false);
			}
			isCollision = false;

			if (Guardian < 200000) {
				// gd[0].setSkill(1);
			}

			if (r2.intersects(r3)) {
				MonsterDamage = cm[0].getAttack() - m.getLevel(m.getStringID());
				if (MonsterDamage < 0)
					MonsterDamage = 0;

				HPBar.setSize(
						(int) (HPBar.getWidth() - (HPBar.getWidth() * (MonsterDamage / m.getUserHP(m.getStringID())))),
						HPBar.getHeight());

				MonsterAttack.setLocation(x - 30, y - 40);
				MonsterAttack.setVisible(true);

				if (isPutControl) {
					MonsterDamaged.setLocation(cm[0].x(), cm[0].y());
					MonsterDamaged.setVisible(false);
					Crazy -= UserDamage;
					if (Crazy <= 0) {
						isCrazyClear = true;
						MonsterDamaged.setVisible(false);
						cm[0].setVisible(false);
						cm[0].setEnabled(false);

						r2.setBounds(0, 0, 0, 0);
						EXPBar.setSize((int) ((1366 * cs.setEXP()) + ((EXPBar.getWidth()) * 80 / cs.getMaxEXP())),
								EXPBar.getHeight());
						EXPBar.repaint();
						m.setEXP(80, m.getStringID());
					}
				}
				isPutControl = false;
			}

			if (isGuardianClear) {			//신수를 쓰러트렸을 때 여제 소환
				MonsterDamage = em.getAttack() - m.getLevel(m.getStringID());
				HPBar.setSize(
						(int) (HPBar.getWidth() - (HPBar.getWidth() * (MonsterDamage / m.getUserHP(m.getStringID())))),
						HPBar.getHeight());

				r1 = em.getBounds();
				r2 = new Rectangle(x, y, 120, 120);
				em.setBounds(em.getX(), em.getY(), 300, 300);
				// em.setSkill(1);

				if (r1.intersects(r2)) {
					if (isPutControl) {
						add(MonsterDamaged, 2, -1);
						Empress -= UserDamage;
						if (Empress <= UserDamage) {
							isEmpressClear = true;
							em.setVisible(false);
							em.setEnabled(false);
						}
					}
				}
				isPutControl = false;
			}
			break;
		}
	}

	public int getYellowHP() {
		return Yellow;
	}

	public int getStoneHP() {
		return Stone;
	}

	public int getCrazyHP() {
		return Crazy;
	}

	public int getGuardianHP() {
		return Guardian;
	}

	public int getEmpressHP() {
		return Empress;
	}

	public boolean isCollider() {
		return isCollision;
	}

	public int setDamage() {
		return (int) MonsterDamage;
	}

	public boolean isDamaged() {
		return collision;
	}

	public boolean setClear() {
		return isClear;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		if (isNPC) {
			g2d.drawImage(Portal, 20, 600, null);
		}
		g2d.drawImage(_i, x, y, null);
	}

	public void move() {
		x += dx;
	}

	public int monsterX() {
		return ym[0].getX();
	}

	public int monsterY() {
		return ym[0].getY();
	}

	public void setLeft() {
		dx = -1;
		if (x < -173) {
			dx = 0;
		}
		if (x <= 93 && y == -355)
			dx = 0;

		direct = 0;
		// isWalk = true;
	}

	public void setRight() {
		dx = 1;
		if (x > 1162) {
			dx = 0;
		}
		if (x >= 853 && y == -355)
			dx = 0;
		direct = 1;
		// isWalk = true;
	}

	public void setNothing() {
		dx = 0;
	}

	public int egetX() {
		return x;
	}

	public int egetY() {
		return y;
	}

	@Override
	public void run() {			//유저의 움직임 및 여러가지 애니메이션 효과를 처리하는 스레드
		while (true) {
			if (y != 535)
				y = 535;
			if (direct == 0) {
				if (direct == 0) {
					_i = img2.getImage();
					this.repaint();
					try {
						java.lang.Thread.sleep(150);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				if (direct == 0) {
					_i = img3.getImage();
					this.repaint();
					try {
						java.lang.Thread.sleep(150);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			} else if (direct == 1) {
				if (direct == 1) {
					_i = img5.getImage();
					this.repaint();
					try {
						java.lang.Thread.sleep(200);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				if (direct == 1) {
					_i = img6.getImage();
					this.repaint();
					try {
						java.lang.Thread.sleep(200);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
			if (isPutControl) {
				if (direct == 0) {
					_i = img10.getImage();
					try {
						java.lang.Thread.sleep(150);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					_i = img11.getImage();
				} else if (direct == 1) {
					_i = img12.getImage();
					try {
						java.lang.Thread.sleep(150);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					_i = img13.getImage();
				}
				isPutControl = false;
			}
			if (isCollision) {
				try {
					java.lang.Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				MonsterAttack.setVisible(false);
				isCollision = false;
			}

			if (isAttack) {
				try {
					java.lang.Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				MonsterDamaged.setVisible(false);
				isAttack = false;
			}
		}
	}

	public Thread getThread() {
		return t;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == Elder) {			//장로Label을 클릭했을 때.
			JFrame Dialog = new JFrame();
			JButton button = new JButton(new ImageIcon("resources/Pictures/ButtonIcon.png"));
			JLabel dialogue = new JLabel("이랏샤이마세");

			Dialog.setContentPane(ElderDialog);
			Dialog.setLocation(1200, 600);
			Dialog.setUndecorated(true);
			Dialog.pack();
			Dialog.setVisible(true);

			button.setBounds(340, 75, 50, 50);
			button.setOpaque(false);
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			Dialog.add(button);

			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Dialog.setVisible(false);
				}
			});

			dialogue.setBounds(170, 80, 300, 20);
			Dialog.add(dialogue);
		}

		if (arg0.getSource() == Shop) {
			add(mar, 5, -1);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setX(int px) {
		x = px;
	}

	public void setY(int py) {
		y = py;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent arg0) {
		if (arg0.getID() == KeyEvent.KEY_PRESSED) {
			if (arg0.getKeyCode() == KeyEvent.VK_CONTROL) {
				isPutControl = true;
				isAttack = true;
				try {
					UserDamage = status[0] * m.getLevel(m.getStringID())
							+ (int) (Math.random() * (status[1] * m.getLevel(m.getStringID()) / 2));
					MonsterDamaged.setText("" + UserDamage);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}