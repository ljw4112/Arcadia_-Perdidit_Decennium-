package SinglePlay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * 프로그램의 기본이 되는 레이아웃을 관리하는 클래스
 * @author Administrator
 *
 */
public class MainGUI implements KeyEventDispatcher, ActionListener, Runnable {
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel basePanel;

	private JLabel HPBar;
	private JLabel MPBar;
	private JLabel EXPBar;
	private JLabel Nickname;
	private JLabel Job;
	private JLabel Levellabel;
	private JLabel Level;
	private JButton Setbutton;
	private ImageIcon img;
	private ImageIcon baseImg;
	private JButton InventoryBtn;
	private JButton StatusBtn;
	private JLabel Damage;

	private Inventory in;
	private Thread t;
	private Thread h;
	private mySQL my;
	private Login l;
	private BackgroundDraw bd;
	private KeyboardFocusManager manager;
	private Sound s, s1, s2, s3;
	private CharacterSet cs;

	private boolean collision;
	private String address;
	private int code;
	private int count = 1;
	private int sound = 0;

	@SuppressWarnings({ "static-access", "serial" })
	MainGUI(JFrame f, String s, boolean isNPC, int mapCode) throws Exception {
		frame = f;
		code = mapCode;
		setBackground(s);
		baseImg = new ImageIcon("resources/Pictures/PanelBase.png");
		mainPanel = new JPanel();
		basePanel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(baseImg.getImage(), 0, 0, null);
				setOpaque(false);
			}
		};

		cs = new CharacterSet();

		if (mapCode == 0) {
			this.s = new Sound("resources/Music/Town.wav");
			this.s.MusicStart();
		}

		bd = new BackgroundDraw(address, frame, isNPC, mapCode);
		
		bd.setLayout(null);
		frame.setFocusable(true);
		Setbutton = new JButton(new ImageIcon("resources/Pictures/SettingButton.png"));
		StatusBtn = new JButton(new ImageIcon("resources/Pictures/Status.png"));
		my = l.getMySQL();
		Nickname = new JLabel();
		Job = new JLabel();
		manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(this);
		InventoryBtn = new JButton(new ImageIcon("resources/Pictures/InventoryBtn.png"));

		HPBar = new JLabel(new ImageIcon("resources/Pictures/HPBar.png"));
		HPBar.setText("HP");
		MPBar = new JLabel(new ImageIcon("resources/Pictures/MPBar.png"));
		EXPBar = new JLabel(new ImageIcon("resources/Pictures/EXPBar.png"));

		Setbutton.addActionListener(this);
		InventoryBtn.addActionListener(this);

		Level = new JLabel("" + my.getLevel(my.getStringID()));
		Levellabel = new JLabel(new ImageIcon("resources/Pictures/Levellabel.png"));
		Damage = new JLabel();

		basePanel.repaint();
		basePanel.revalidate();
	}

	public void setSound(Sound s1, Sound s2, Sound s3){
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
	}
	
	public JPanel getPanel() {
		return mainPanel;
	}

	void makeUI() throws Exception {
		mainPanel.setLayout(null);
		basePanel.setLayout(null);

		Nickname.setText("Nickname : " + my.getNickname(my.getStringID()));
		Job.setText("//	Job : " + my.getJob(my.getStringID()));
		Nickname.setFont(new Font("Nexa Bold.otf", Font.PLAIN, 15));
		Level.setFont(new Font("Nexa Bold.otf", Font.BOLD, 60));
		Level.setForeground(Color.WHITE);
		Job.setFont(new Font("Nexa Bold.otf", Font.PLAIN, 15));
		Nickname.setForeground(Color.WHITE);
		Job.setForeground(Color.WHITE);
		Damage.setForeground(Color.RED);
		Damage.setFont(new Font("Nexa Bold.otf", Font.BOLD, 60));
		Damage.setBounds(bd.monsterX(), bd.monsterY(), 100, 50);

		Levellabel.setBounds(-10, -10, 120, 120);
		Level.setBounds(100, 26, 100, 50);
		Nickname.setBounds(5, 47, 500, 100);
		Job.setBounds(170, 47, 500, 100);
		bd.setBounds(0, 200, 1366, 900);
		Setbutton.setBounds(710, 0, 300, 100);
		setTransparant(Setbutton);
		InventoryBtn.setBounds(950, -5, 300, 100);
		setTransparant(InventoryBtn);
		StatusBtn.setBounds(500, 20, 300, 100);
		setTransparant(StatusBtn);

		HPBar.setBounds(766, 75, 300, 30);
		MPBar.setBounds(1066, 75, 300, 30);
		EXPBar.setBounds(0, 105, (int) (1366 * cs.setEXP()), 10);

		h = new Thread(this);
		h.start();

		basePanel.add(StatusBtn);
		basePanel.add(InventoryBtn);
		basePanel.add(Level);
		basePanel.add(Levellabel);
		basePanel.add(Setbutton);
		basePanel.add(HPBar);
		basePanel.add(MPBar);
		basePanel.add(EXPBar);
		basePanel.add(Nickname);
		basePanel.add(Job);
		basePanel.setBounds(-2, 600, 1366, 200);
		mainPanel.setBounds(0, -300, 1366, 900);

		mainPanel.add(bd);

		frame.add(mainPanel);
		frame.add(basePanel);

		basePanel.setVisible(true);
		basePanel.repaint();
		basePanel.revalidate();

		bd.setHPBar(HPBar);
		bd.setMPBar(MPBar);
		bd.setEXPBar(EXPBar);
		bd.setCharacterSet(cs);
		
		bd.setSound1(s1);
		bd.setSound2(s2);
		bd.setSound3(s3);
		bd.getThread().start();
	}

	@Override
	public void run() {
		while (true) {
			if (s1 != null) {
				bd.setSound1(s1);
				bd.setSound2(s2);
				bd.setSound3(s3);
			}
			if (bd.isDamaged()) {
				Damage.setText("" + bd.setDamage());
				mainPanel.add(Damage);
				try {
					java.lang.Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Damage.setVisible(false);
			}
			if (bd.isCollider()) {
				try {
					HPBar.setBounds(766, 75, (int) ((int) 300 * cs.setHP(1)), 30);
					HPBar.repaint();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setCollision(boolean col) {
		collision = col;
	}

	void setTransparant(JButton b) { // 투명도 설정함수
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent arg0) {
		int code = arg0.getKeyCode();

		if (code == KeyEvent.VK_LEFT) {
			bd.setLeft();
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			bd.setRight();
		}

		if (arg0.getID() == KeyEvent.KEY_RELEASED) {
			bd.setNothing();
		}

		if (this.code == 0) {
			if (bd.egetX() >= 15 && bd.egetX() <= 94) {
				if (code == KeyEvent.VK_UP) {
					try {
						if (count < 2) {
							s.MusicStop();
							frame.setVisible(false);
							MainMap mm = new MainMap();
							count++;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}
		return false;
	}

	public int getX() {
		return bd.egetX();
	}

	public int getY() {
		return bd.egetY();
	}

	public void setX(int px) {
		bd.setX(px);
	}

	public void setY(int py) {
		bd.setY(py);
	}

	public void setBackground(String s) {
		address = s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Setbutton) {
			JFrame SettingFrame = new JFrame();
			try {
				img = new ImageIcon(ImageIO.read(new File("resources/Pictures/OptionBackground.png")));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SettingFrame.setContentPane(new JLabel(img));
			SettingFrame.setUndecorated(true);
			SettingFrame.setPreferredSize(new Dimension(400, 300));
			SettingFrame.setLayout(null);

			OptionFrameGUI(SettingFrame);
			SettingFrame.setLocation(1920 / 2 - 400 / 2, 1080 / 2 - 300 / 2);
			SettingFrame.pack();
			SettingFrame.setVisible(true);
		} else if (e.getSource() == InventoryBtn) {
			try {
				in = new Inventory();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public void OptionFrameGUI(JFrame f) {
		JButton exit = new JButton(new ImageIcon("resources/Pictures/Exit.png"));
		JButton SoundOn = new JButton(new ImageIcon("resources/Pictures/SoundOn.png"));
		JButton SoundOff = new JButton(new ImageIcon("resources/Pictures/SoundOff.png"));
		JLabel Save = new JLabel(new ImageIcon("resources/Pictures/AutoSave.png"));

		SoundOn.setBounds(140, 70, 120, 40);
		SoundOff.setBounds(140, 110, 120, 40);
		exit.setBounds(50, 200, 300, 100);
		Save.setBounds(50, 150, 300, 100);

		setTransparant(SoundOff);
		setTransparant(SoundOn);
		setTransparant(exit);

		f.add(Save);
		f.add(SoundOff);
		f.add(SoundOn);
		f.add(exit);

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
			}
		});
	}
}