package SinglePlay;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

//mapcode : 4
/**
 * MainMap에서 사냥터 중 아무거나 클릭 했을 때 맞는 프레임을 연결해주는 클래스
 * @author Administrator
 *
 */
public class Hunting implements KeyEventDispatcher {
	private MainGUI mg;
	private JFrame frame;
	private Sound s1;
	private Sound s2;
	private Sound s3;
	private KeyboardFocusManager manager;
	protected int cx, cy;

	private boolean NPCView;
	private int levelCode;

	Hunting(int code, JFrame f, String s) throws Exception {
		frame = f;
		makeGUI();
		NPCView = false;

		levelCode = code;

		manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(this);

		s1 = new Sound("resources/Music/Level1.wav");
		s2 = new Sound("resources/Music/Level2.wav");
		s3 = new Sound("resources/Music/Level3.wav");
		
		frame.setLocationRelativeTo(null);

		switch (code) {
		case 3:
			mg = new MainGUI(frame, s, NPCView, levelCode);
			mg.makeUI();
			s1.MusicStart();
			levelCode = 3;
			break;
		case 4:
			mg = new MainGUI(frame, s, NPCView, levelCode);
			mg.makeUI();
			s2.MusicStart();
			levelCode = 4;
			break;
		case 5:
			mg = new MainGUI(frame, s, NPCView, levelCode);
			mg.makeUI();
			s3.MusicStart();
			levelCode = 5;
			break;
		}
		mg.setSound(s1, s2, s3);
		
		cx = mg.getX();
		cy = mg.getY();
		
		frame.repaint();
		frame.revalidate();
	}
	
	public int getStage() {
		return levelCode;
	}

	void makeGUI() {
		frame.setPreferredSize(new Dimension(1366, 768));
		frame.setTitle("Arcadia -Perdidit Decennium-");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("resources/Pictures/icon.png");
		frame.setIconImage(img);
		JMenuBar menu = new JMenuBar();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenu fmenu = new JMenu("파일"); // 진행상황 불러오기 ,저장을 관리하는 메뉴
		JMenuItem sfile = new JMenuItem("저장하기");
		JMenuItem lfile = new JMenuItem("불러오기");
		JMenuItem nfile = new JMenuItem("초기화");
		JMenuItem quitwin = new JMenuItem("종료");
		// fileMenu.setMnemonic(KeyEvent.VK_F) //단축기 설정(ALT+F누를시에 실행)
		JMenuItem mainsce = new JMenuItem("메인화면");

		JMenu smenu = new JMenu("윈도우"); // 창의 크기,전체화면을 설정하는 메뉴
		JMenuItem dimwin = new JMenuItem("창 크기 조절");
		JMenuItem fullwin = new JMenuItem("전체화면");

		JMenu qmenu = new JMenu("도움말");
		JMenuItem ques = new JMenuItem("도움말");

		lfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("C:/Users/Administrator/workspace/Arcadia/Save");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				fc.addChoosableFileFilter(filter);
				int ret = fc.showDialog(null, "Open File");
				if (ret == JFileChooser.APPROVE_OPTION) {
					// When save File load, Start Game with saved data by File
					// IO.
				}
			}
		});

		ques.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Noryeok = "노오오오력이 부족하시네요!" + "\n" + "게임에 대한 애정이 부족하신 분입니다.";
				JOptionPane.showMessageDialog(null, Noryeok, "도움말", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mainsce.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "메인화면으로 돌아갑니다.", "확인", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);

				if (i == JOptionPane.YES_OPTION) {
					int k = JOptionPane.showConfirmDialog(null, "정말?", "정말", JOptionPane.YES_NO_OPTION);
					if (k == JOptionPane.YES_OPTION) {
						try {
							if(levelCode == 3){
								s1.MusicStop();
							} else if(levelCode == 4){
								s2.MusicStop();
							} else if (levelCode == 5){
								s3.MusicStop();
							}
							FirstGUI f = new FirstGUI();
							f.MainScene(frame);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		fullwin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
		});
		quitwin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		frame.setJMenuBar(menu);
		menu.add(fmenu);
		menu.add(smenu);
		menu.add(qmenu);
		menu.setVisible(true);
		fmenu.add(nfile);
		fmenu.add(sfile);
		fmenu.add(lfile);
		smenu.add(dimwin);
		smenu.add(fullwin);
		fmenu.add(mainsce);
		fmenu.add(quitwin);
		qmenu.add(ques);
		frame.repaint();
		frame.revalidate();
		menu.repaint();
		menu.revalidate();
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent arg0) {
		if (levelCode == 1) {
			if (arg0.getID() == KeyEvent.KEY_PRESSED) {
				if (arg0.getKeyCode() == KeyEvent.VK_UP) {
					if (mg.getX() > 930 && mg.getX() < 960) {
						mg.setX(240);
						mg.setY(210);
					}
					if (mg.getX() > 231 && mg.getX() < 271 && mg.getY() == 210) {
						mg.setX(945);
						mg.setY(530);
					}
				}
			}
		}
		return false;
	}
}