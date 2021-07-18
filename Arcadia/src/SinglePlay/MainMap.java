package SinglePlay;

import MultiPlay.MultiPlayMain;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.*;
/**
 * 마을, 사냥터 를 선택할 수 있는 맵 클래스
 * @author Administrator
 *
 */
public class MainMap implements Runnable, KeyEventDispatcher {
	private JFrame frame;
	private ImageIcon mimg;
	private ImageIcon HuntMenu;

	private Sound s;

	private JLabel HuntingMenu;
	private JLabel LeftArrow1, LeftArrow2;
	private JLabel WhiteLeftArrow1, WhiteLeftArrow2;
	private JLabel RightArrow;
	private JLabel WhiteRightArrow;
	private JLabel TownBG, TownMenu;
	private JLabel PortBG;

	private JFrame hunt;
	private Thread th = new Thread(this);
	private Thread t = new Thread(this);
	private Thread town = new Thread(this);
	private Thread TowntoHunt = new Thread(this);
	private KeyboardFocusManager manager;

	private boolean f = false;

	private int temp = 1, tempStage = 1; // temp = 1 :사냥터 , temp = 2 :마을, temp =
											// 3 :항구
	private int level;
	private int init1, init3;
	private int init2;
	private int isLoading;

	MainMap() throws Exception {
		isLoading = 0;
		init1 = 1;
		init2 = 0;
		init3 = 0;

		if (temp == 1) {
			s = new Sound("resources/Music/HuntingMain.wav");
			s.MusicStart();
		}

		LeftArrow1 = new JLabel();
		LeftArrow1.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/LeftArrow.png"));
		LeftArrow1.setBounds(820, 130, 100, 100);
		WhiteLeftArrow1 = new JLabel();
		WhiteLeftArrow1.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/W_LeftArrow.png"));
		WhiteLeftArrow1.setBounds(820, 130, 100, 100);

		LeftArrow2 = new JLabel();
		LeftArrow2.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/LeftArrow.png"));
		LeftArrow2.setBounds(810, 520, 100, 100);
		WhiteLeftArrow2 = new JLabel();
		WhiteLeftArrow2.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/W_LeftArrow.png"));
		WhiteLeftArrow2.setBounds(810, 520, 100, 100);

		RightArrow = new JLabel();
		RightArrow.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/RightArrow.png"));
		RightArrow.setBounds(1250, 290, 100, 100);
		WhiteRightArrow = new JLabel();
		WhiteRightArrow.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/W_RightArrow.png"));
		WhiteRightArrow.setBounds(1250, 290, 100, 100);

		if (temp == 1) {
			HuntingMenu = new JLabel();
			HuntingMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/HuntingMapPanel.png"));
			HuntingMenu.setBounds(0, 0, 1366, 768);
		}

		TownBG = new JLabel();
		TownBG.setBounds(0, 0, 1366, 768);
		TownMenu = new JLabel();
		TownMenu.setBounds(0, 0, 1366, 768);
		
		PortBG = new JLabel();
		PortBG.setBounds(0, 0, 1366, 768);

		Init();

		t.start();

		frame.add(LeftArrow1);
		frame.add(LeftArrow2);
		frame.add(RightArrow);

		frame.add(WhiteLeftArrow1, -1);
		WhiteLeftArrow1.setVisible(false);
		LeftArrow2.setVisible(false);
		RightArrow.setVisible(false);

		frame.add(WhiteLeftArrow2, -1);
		frame.add(WhiteRightArrow, -1);

		manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(this);
	}

	void setTransparant(JButton b) { // 투명도 설정함수
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (init1 == 1) {
				tempStage = 0;
				HuntingMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/HuntingMapPanel0.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				HuntingMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/HuntingMapPanel10.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				HuntingMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/HuntingMapPanel20.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				HuntingMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/HuntingMapPanel30.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				HuntingMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/HuntingMapPanel40.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				HuntingMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/HuntingMapPanel50.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				HuntingMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/HuntingMapPanel60.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				HuntingMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/HuntingMapPanel70.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				HuntingMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/HuntingMapPanel80.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				HuntingMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/HuntingMapPanel90.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				HuntingMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/HuntingMapPanel100.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				init1 = 0;
			}

			if (init2 == 1) {
				frame.add(TownMenu, 2, -1);
				frame.add(TownBG);

				TownBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownMapBackground1.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownMapBackground2.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownMapBackground3.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownMapBackground4.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownMapBackground5.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();	
				}
				TownBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownMapBackground6.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownMapBackground7.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownMapBackground8.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				TownMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownPanel0.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownPanel10.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownPanel20.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownPanel30.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownPanel40.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownPanel50.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownPanel60.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownPanel70.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownPanel80.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownPanel90.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TownMenu.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownPanel100.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				frame.add(TownMenu, -1);
				TownMenu.setVisible(true);
				init2 = 0;
			}

			if (isLoading == 1) {
				if (LeftArrow1.isVisible()) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						Hunting h = new Hunting(3, hunt, "resources/Pictures/Hunting1.png");
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (RightArrow.isVisible()) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						System.out.println("Hello");
						Hunting h = new Hunting(4, hunt, "resources/Pictures/Hunting2.png");
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (LeftArrow2.isVisible()) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						Hunting h = new Hunting(5, hunt, "resources/Pictures/LastHunting.png");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				isLoading = 0;
			}
			
			if(init3 == 1){
				frame.remove(TownBG);
				frame.remove(TownMenu);
				frame.add(PortBG);
				PortBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/PortMapBackground0.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				PortBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/PortMapBackground1.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				PortBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/PortMapBackground2.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				PortBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/PortMapBackground3.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				PortBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/PortMapBackground4.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				PortBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/PortMapBackgroun5.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				PortBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/PortMapBackground6.png"));
				try {	
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				PortBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/PortMapBackground7.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				PortBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/PortMapBackground100.png"));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				init3 = 0;
			}
			
			if(temp == 5){
				break;
			}
		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if (temp == 1) {
				tempStage++;
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (tempStage == 1) {
						LeftArrow2.setVisible(false);
						LeftArrow1.setVisible(true);
					} else if (tempStage == 2) {
						LeftArrow1.setVisible(false);
						WhiteLeftArrow1.setVisible(true);
						RightArrow.setVisible(true);
					} else if (tempStage == 3) {
						RightArrow.setVisible(false);
						LeftArrow2.setVisible(true);
						tempStage = 0;
					}
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (temp == 1) {
					temp = 2;
					init2 = 1;

					LeftArrow1.setVisible(false);
					LeftArrow2.setVisible(false);
					RightArrow.setVisible(false);
					WhiteLeftArrow1.setVisible(false);
					WhiteLeftArrow2.setVisible(false);
					WhiteRightArrow.setVisible(false);
					HuntingMenu.setVisible(false);
					HuntingMenu.setEnabled(false);

					try {
						s.MusicStop();
						s = new Sound("resources/Music/TownMain.wav");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					s.MusicStart();
				}
				
				else if(temp == 2){
					temp = 3;
					init3 = 1;
					
					try {
						s.MusicStop();
						s = new Sound("resources/Music/PortMain.wav");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					s.MusicStart();
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (temp == 2) {

					TownBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/HuntingMapBackground.png"));
					HuntingMenu.setVisible(true);
					HuntingMenu.setEnabled(true);

					temp = 1;
					init1 = 1;

					LeftArrow1.setVisible(true);
					LeftArrow2.setVisible(false);
					RightArrow.setVisible(false);
					WhiteLeftArrow1.setVisible(false);
					WhiteLeftArrow2.setVisible(true);
					WhiteRightArrow.setVisible(true);

					try {
						s.MusicStop();
						s = new Sound("resources/Music/HuntingMain.wav");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					s.MusicStart();
				}
				
				else if(temp == 3){
					temp = 2;
					init2 = 1;

					frame.remove(PortBG);
					
					TownBG.setIcon(new ImageIcon("resources/Pictures/MainMapPictures/TownMapBackground.png"));
					
					LeftArrow1.setVisible(false);
					LeftArrow2.setVisible(false);
					RightArrow.setVisible(false);
					WhiteLeftArrow1.setVisible(false);
					WhiteLeftArrow2.setVisible(false);
					WhiteRightArrow.setVisible(false);
					HuntingMenu.setVisible(false);
					HuntingMenu.setEnabled(false);

					try {
						s.MusicStop();
						s = new Sound("resources/Music/TownMain.wav");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					s.MusicStart();
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (temp == 1) {
					isLoading = 1;
					hunt = new JFrame();
					hunt.setSize(1366, 768);
					hunt.setLocation(1920 / 2 - 1366 / 2, 1080 / 2 - 768 / 2);
					hunt.setResizable(false);
					hunt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					s.MusicStop();
					frame.setVisible(false);
					hunt.setVisible(true);
					temp = 5;

					if (LeftArrow1.isVisible()) {
						try {
							hunt.setContentPane(new JLabel(new ImageIcon("resources/Pictures/FirstHunting.png")));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else if (RightArrow.isVisible()) {
						hunt.setContentPane(new JLabel(new ImageIcon("resources/Pictures/SecondHunting.png")));
						try {
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else if (LeftArrow2.isVisible()) {
						hunt.setContentPane(new JLabel(new ImageIcon("resources/Pictures/LastStage.png")));
						try {
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

				}
				if (temp == 2) {
					try {
						temp = 5;
						frame.setContentPane(new JLabel());
						Town t = new Town(frame);
						s.MusicStop();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
				if(temp == 3){
					frame.setContentPane(new JLabel());
					MultiPlayMain mp = new MultiPlayMain(frame);
					s.MusicStop();
					//temp = 5;
				}
			}
		}

		return false;
	}

	public void Init() {
		frame = new JFrame("Arcadia -Perdidit Decennium-");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1366, 768));
		frame.setResizable(false);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("resources/Pictures/icon.png");
		frame.setIconImage(img);
		frame.setLocation(1920 / 2 - 1366 / 2, 1080 / 2 - 768 / 2);
		frame.setSize(1366, 768);

		mimg = new ImageIcon("resources/Pictures/MainMapPictures/HuntingMapBackground.png");
		frame.setContentPane(new JLabel(mimg));

		frame.add(HuntingMenu);
		frame.setVisible(true);
	}
}