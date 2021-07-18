package SinglePlay;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.image.*;
/**
 * Frame에 꼭 필요한 gui나 맨 처음에 실행되는 클래스
 * @author Administrator
 *
 */
class FirstGUI {
	private static Sound s;
	
	FirstGUI() throws Exception{
		s = new Sound("resources/Music/MainMusic.wav");
	}
	
	public void GUI() throws Exception {
		mustGUI();
	}

	void setFrameDimension(JFrame f, Toolkit t, int w, int h) {
		Dimension dim = t.getScreenSize();
		f.setLocation((int) (dim.getWidth() / 2 - w / 2), (int) (dim.getHeight() / 2 - h / 2));
		f.setSize(w, h);
	}

	public void mustGUI() throws Exception { // 게임시작과 동시에 프레임과 구성요소 생성
		JFrame frame = new JFrame("Arcadia -Perdidit Decennium-");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1366, 768));
		frame.setResizable(false);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("resources/Pictures/icon.png");
		frame.setIconImage(img);

		setFrameDimension(frame, toolkit, 1366, 768);

		JMenuBar menu = new JMenuBar();

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
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "메인화면으로 돌아갑니다.", "확인", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);

				if (i == JOptionPane.YES_OPTION) {
					int k = JOptionPane.showConfirmDialog(null, "정말?", "정말", JOptionPane.YES_NO_OPTION);
					if (k == JOptionPane.YES_OPTION) {
						try {
							s.MusicStop();
							MainScene(frame);
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
		dimwin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String wid = JOptionPane.showInputDialog("가로 길이를 입력하세요." + "\n\t가능한 1366*768의 환경에서 구동하시길 권장합니다.");
				String hei = JOptionPane.showInputDialog("세로 길이를 입력하세요." + "\n\t가능한 1366*768의 환경에서 구동하시길 권장합니다.");

				int width = Integer.parseInt(wid);
				int height = Integer.parseInt(hei);

				int j = JOptionPane.showConfirmDialog(null, "창의 크기가 다음과 같이 설정됩니다.\n" + width + " * " + height, "확인",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (j == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, new String("Change Complete!"), "Success",
							JOptionPane.INFORMATION_MESSAGE);
					setFrameDimension(frame, toolkit, width, height);
					ImagePanel ip = new ImagePanel();
					ip.setBackground(img);
				}
			}
		});

		frame.setJMenuBar(menu);
		fmenu.add(nfile);
		fmenu.add(sfile);
		fmenu.add(lfile);
		smenu.add(dimwin);
		smenu.add(fullwin);
		fmenu.add(mainsce);
		fmenu.add(quitwin);
		qmenu.add(ques);
		menu.add(fmenu);
		menu.add(smenu);
		menu.add(qmenu);

		MainScene(frame);

		frame.pack();
		frame.setVisible(true);
	}

	public void _mustGUI() throws IOException { // MainScene() 호출 x
		JFrame frame = new JFrame("Arcadia -Perdidit Decennium-");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1366, 768));
		frame.setResizable(false);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("resources/Pictures/icon.png");
		frame.setIconImage(img);

		setFrameDimension(frame, toolkit, 1366, 768);

		JMenuBar menu = new JMenuBar();

		JMenu fmenu = new JMenu("파일"); // 진행상황 불러오기 ,저장을 관리하는 메뉴
		JMenuItem sfile = new JMenuItem("저장하기");
		JMenuItem lfile = new JMenuItem("불러오기");
		JMenuItem nfile = new JMenuItem("초기화");
		JMenuItem quitwin = new JMenuItem("종료");
		JMenuItem mainsce = new JMenuItem("메인화면");

		JMenu smenu = new JMenu("윈도우"); // 창의 크기,전체화면을 설정하는 메뉴
		JMenuItem dimwin = new JMenuItem("창 크기 조절");
		JMenuItem fullwin = new JMenuItem("전체화면");

		JMenu qmenu = new JMenu("도움말");
		JMenuItem ques = new JMenuItem("도움말");

		ques.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Noryeok = "노오오오력이 부족하시네요!" + "\n" + "게임에 대한 애정이 부족하신 분입니다.";
				JOptionPane.showMessageDialog(null, Noryeok, "도움말", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mainsce.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "메인화면으로 돌아갑니다.", "확인", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);

				if (i == JOptionPane.YES_OPTION) {
					int k = JOptionPane.showConfirmDialog(null, "정말?", "정말", JOptionPane.YES_NO_OPTION);
					if (k == JOptionPane.YES_OPTION) {
						try {
							
							MainScene(frame);
						} catch (Exception e1) {
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
		dimwin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String wid = JOptionPane.showInputDialog("가로 길이를 입력하세요." + "\n\t가능한 1366*768의 환경에서 구동하시길 권장합니다.");
				String hei = JOptionPane.showInputDialog("세로 길이를 입력하세요." + "\n\t가능한 1366*768의 환경에서 구동하시길 권장합니다.");

				int width = Integer.parseInt(wid);
				int height = Integer.parseInt(hei);

				int j = JOptionPane.showConfirmDialog(null, "창의 크기가 다음과 같이 설정됩니다.\n" + width + " * " + height, "확인",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (j == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, new String("Change Complete!"), "Success",
							JOptionPane.INFORMATION_MESSAGE);
					setFrameDimension(frame, toolkit, width, height);
					ImagePanel ip = new ImagePanel();
					ip.setBackground(img);
				}
			}
		});

		frame.setJMenuBar(menu);
		fmenu.add(nfile);
		fmenu.add(sfile);
		fmenu.add(lfile);
		smenu.add(dimwin);
		smenu.add(fullwin);
		fmenu.add(mainsce);
		fmenu.add(quitwin);
		qmenu.add(ques);
		menu.add(fmenu);
		menu.add(smenu);
		menu.add(qmenu);

		frame.pack();
		frame.setVisible(true);
	}

	static void MainScene(JFrame frame) throws Exception {
		
		s.MusicStart();

		ImageIcon img = new ImageIcon(ImageIO.read(new File("resources/Pictures/MainBackground.png")));
		frame.setContentPane(new JLabel(img));

		ImageIcon startimage = new ImageIcon("resources/Pictures/GameStartBtn.png");
		JButton startBtn = new JButton(startimage);
		startBtn.setPreferredSize(new Dimension(400, 100));
		setTransparant(startBtn);

		ImageIcon contimage = new ImageIcon("resources/Pictures/OptionBtn.png");
		JButton opBtn = new JButton(contimage);
		setTransparant(opBtn);

		ImageIcon exitimage = new ImageIcon("resources/Pictures/ExitBtn.png");
		JButton exitBtn = new JButton(exitimage);
		setTransparant(exitBtn);

		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameStart(startBtn, opBtn, exitBtn);
				JLabel lbl = new JLabel();
				lbl.setBackground(new Color(0, 0, 0));
				frame.setContentPane(lbl);
				try {
					InformationCheck start = new InformationCheck(frame);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				s.MusicStop();
			}
		});

		opBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame OptionFrame = new JFrame("Option Page");
				// OptionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				OptionFrame.setLocation(1920 / 2 - 500 / 2, 1080 / 2 - 300 / 2);
				OptionFrame.setSize(500, 300);
				OptionFrame.setLayout(new GridLayout(2, 1));

				JPanel Option = new JPanel();
				JPanel IsOnline = new JPanel();

				Option.setLayout(new GridLayout(2, 2));
				JLabel Sound = new JLabel("Sound", SwingConstants.CENTER);
				JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
				slider.setPaintLabels(true);
				slider.setPaintTrack(true);
				slider.setMajorTickSpacing(20);
				slider.setMinorTickSpacing(10);

				slider.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						FloatControl f = (FloatControl) (s.getClip()).getControl(FloatControl.Type.MASTER_GAIN);
						f.setValue((float) ((slider.getValue() - 100) / 20.0));
					}
				});

				JButton InitializeDB = new JButton("DB Initialize");
				JButton Credits = new JButton("Credit");

				OptionFrame.add(Option);
				OptionFrame.add(IsOnline);

				Option.add(Sound);
				Option.add(slider);
				Option.add(InitializeDB);
				Option.add(Credits);

				OptionFrame.setVisible(true);
			}
		});

		startBtn.setBounds(320, 300, 400, 156);
		opBtn.setBounds(320, 380, 400, 156);
		exitBtn.setBounds(320, 460, 400, 156);

		frame.add(startBtn);
		frame.add(opBtn);
		frame.add(exitBtn);
		frame.setVisible(true);
	}

	static void setTransparant(JButton b) { // 투명도 설정함수
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
	}

	static void setOff(JButton b1, JButton b2, JButton b3) {
		b1.setVisible(false);
		b2.setVisible(false);
		b3.setVisible(false);
	}

	static void gameStart(JButton b1, JButton b2, JButton b3) {
		setOff(b1, b2, b3);
	}

	void setSize(ImageIcon img) {
		// 이미지를 창의 크기에 비례하도록 설정하는 함수
	}
}

@SuppressWarnings("serial")
class ImagePanel extends JButton {
	Image image;

	public void setBackground(Image img) {
		this.image = img;
	}

	@Override
	public void paintComponent(Graphics G) {
		super.paintComponent(G);
		G.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}