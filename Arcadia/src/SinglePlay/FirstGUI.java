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
 * Frame�� �� �ʿ��� gui�� �� ó���� ����Ǵ� Ŭ����
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

	public void mustGUI() throws Exception { // ���ӽ��۰� ���ÿ� �����Ӱ� ������� ����
		JFrame frame = new JFrame("Arcadia -Perdidit Decennium-");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1366, 768));
		frame.setResizable(false);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("resources/Pictures/icon.png");
		frame.setIconImage(img);

		setFrameDimension(frame, toolkit, 1366, 768);

		JMenuBar menu = new JMenuBar();

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
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "����ȭ������ ���ư��ϴ�.", "Ȯ��", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);

				if (i == JOptionPane.YES_OPTION) {
					int k = JOptionPane.showConfirmDialog(null, "����?", "����", JOptionPane.YES_NO_OPTION);
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
				String wid = JOptionPane.showInputDialog("���� ���̸� �Է��ϼ���." + "\n\t������ 1366*768�� ȯ�濡�� �����Ͻñ� �����մϴ�.");
				String hei = JOptionPane.showInputDialog("���� ���̸� �Է��ϼ���." + "\n\t������ 1366*768�� ȯ�濡�� �����Ͻñ� �����մϴ�.");

				int width = Integer.parseInt(wid);
				int height = Integer.parseInt(hei);

				int j = JOptionPane.showConfirmDialog(null, "â�� ũ�Ⱑ ������ ���� �����˴ϴ�.\n" + width + " * " + height, "Ȯ��",
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

	public void _mustGUI() throws IOException { // MainScene() ȣ�� x
		JFrame frame = new JFrame("Arcadia -Perdidit Decennium-");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1366, 768));
		frame.setResizable(false);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("resources/Pictures/icon.png");
		frame.setIconImage(img);

		setFrameDimension(frame, toolkit, 1366, 768);

		JMenuBar menu = new JMenuBar();

		JMenu fmenu = new JMenu("����"); // �����Ȳ �ҷ����� ,������ �����ϴ� �޴�
		JMenuItem sfile = new JMenuItem("�����ϱ�");
		JMenuItem lfile = new JMenuItem("�ҷ�����");
		JMenuItem nfile = new JMenuItem("�ʱ�ȭ");
		JMenuItem quitwin = new JMenuItem("����");
		JMenuItem mainsce = new JMenuItem("����ȭ��");

		JMenu smenu = new JMenu("������"); // â�� ũ��,��üȭ���� �����ϴ� �޴�
		JMenuItem dimwin = new JMenuItem("â ũ�� ����");
		JMenuItem fullwin = new JMenuItem("��üȭ��");

		JMenu qmenu = new JMenu("����");
		JMenuItem ques = new JMenuItem("����");

		ques.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Noryeok = "����������� �����Ͻó׿�!" + "\n" + "���ӿ� ���� ������ �����Ͻ� ���Դϴ�.";
				JOptionPane.showMessageDialog(null, Noryeok, "����", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mainsce.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "����ȭ������ ���ư��ϴ�.", "Ȯ��", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);

				if (i == JOptionPane.YES_OPTION) {
					int k = JOptionPane.showConfirmDialog(null, "����?", "����", JOptionPane.YES_NO_OPTION);
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
				String wid = JOptionPane.showInputDialog("���� ���̸� �Է��ϼ���." + "\n\t������ 1366*768�� ȯ�濡�� �����Ͻñ� �����մϴ�.");
				String hei = JOptionPane.showInputDialog("���� ���̸� �Է��ϼ���." + "\n\t������ 1366*768�� ȯ�濡�� �����Ͻñ� �����մϴ�.");

				int width = Integer.parseInt(wid);
				int height = Integer.parseInt(hei);

				int j = JOptionPane.showConfirmDialog(null, "â�� ũ�Ⱑ ������ ���� �����˴ϴ�.\n" + width + " * " + height, "Ȯ��",
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

	static void setTransparant(JButton b) { // ���� �����Լ�
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
		// �̹����� â�� ũ�⿡ ����ϵ��� �����ϴ� �Լ�
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