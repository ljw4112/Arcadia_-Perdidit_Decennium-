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
 * MainMap���� ����� �� �ƹ��ų� Ŭ�� ���� �� �´� �������� �������ִ� Ŭ����
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

		JMenu fmenu = new JMenu("����"); // �����Ȳ �ҷ����� ,������ �����ϴ� �޴�
		JMenuItem sfile = new JMenuItem("�����ϱ�");
		JMenuItem lfile = new JMenuItem("�ҷ�����");
		JMenuItem nfile = new JMenuItem("�ʱ�ȭ");
		JMenuItem quitwin = new JMenuItem("����");
		// fileMenu.setMnemonic(KeyEvent.VK_F) //����� ����(ALT+F�����ÿ� ����)
		JMenuItem mainsce = new JMenuItem("����ȭ��");

		JMenu smenu = new JMenu("������"); // â�� ũ��,��üȭ���� �����ϴ� �޴�
		JMenuItem dimwin = new JMenuItem("â ũ�� ����");
		JMenuItem fullwin = new JMenuItem("��üȭ��");

		JMenu qmenu = new JMenu("����");
		JMenuItem ques = new JMenuItem("����");

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
				String Noryeok = "����������� �����Ͻó׿�!" + "\n" + "���ӿ� ���� ������ �����Ͻ� ���Դϴ�.";
				JOptionPane.showMessageDialog(null, Noryeok, "����", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mainsce.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "����ȭ������ ���ư��ϴ�.", "Ȯ��", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);

				if (i == JOptionPane.YES_OPTION) {
					int k = JOptionPane.showConfirmDialog(null, "����?", "����", JOptionPane.YES_NO_OPTION);
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