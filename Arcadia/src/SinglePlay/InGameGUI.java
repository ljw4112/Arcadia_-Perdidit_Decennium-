package SinglePlay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
/**
 * 직업 선택 프레임의 레이아웃을 관장하는 클래스
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class InGameGUI extends JPanel {
	private String BackgroundImage;
	private String MusicAddress;
	private boolean IsQuest;
	private int mapCode;

	private JFrame f;
	private Clip clip;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JButton next;
	private Font font;

	private Login l;
	private TypeAnimation ta;
	private mySQL m;
	private FirstCharacter cs;
	private Sound s;

	@SuppressWarnings("static-access")
	InGameGUI(JFrame frame, String M, String B) throws Exception {
		BackgroundImage = B;
		MusicAddress = M;
		f = frame;

		m = l.getMySQL();

		GridBagLayout gbl = new GridBagLayout();
		frame.setLayout(gbl);

		s = new Sound(MusicAddress);
		s.MusicStart();

		ImageIcon ButtonImg = new ImageIcon("resources/Pictures/ButtonIcon.png");

		ImageIcon image = new ImageIcon(BackgroundImage);
		p1 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(image.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		p2 = new JPanel();
		p3 = new JPanel();

		p1.setBackground(Color.WHITE);
		p2.setBackground(Color.WHITE);
		p3.setBackground(Color.WHITE);

		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		p2.setBorder(BorderFactory.createLineBorder(Color.black));
		p3.setBorder(BorderFactory.createLineBorder(Color.black));

		arrangeInGridBag(this, gbl, p1, 0, 0, 1, 2, 2, 1);
		arrangeInGridBag(this, gbl, p2, 0, 1, 0, 0, 1, 1);
		arrangeInGridBag(this, gbl, p3, 1, 1, 2, 1, 1, 1);

		p1.setLayout(null);
		p2.setLayout(null);
		p3.setLayout(null);

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.getAllFonts();

		font = new Font("NanumSquareB.ttf", Font.BOLD, 18);

		JButton b = new JButton("Option");
		b.setBounds(1225, 15, 80, 30);
		JTextArea t = new JTextArea("Quest");
		t.setBounds(50, 15, 100, 18);
		t.setBackground(Color.WHITE);
		t.setEditable(false);
		t.setBorder(BorderFactory.createLineBorder(Color.black));

		next = new JButton(ButtonImg);
		next.setBounds(530, 120, 100, 50);

		next.setOpaque(false);
		next.setContentAreaFilled(false);
		next.setBorderPainted(false);

		if (IsQuest == true) {
			t.setForeground(Color.BLACK);
			t.setText("");
			p1.add(t, SwingConstants.CENTER);
		}
		p1.add(b);
		p3.add(next);
		frame.add(p1);
		frame.add(p2);
		frame.add(p3);
		cs = new FirstCharacter(p2, frame);

		frame.setVisible(true);
		ScreenAction(b);
	}

	public Sound getSound(){
		return s;
	}
	
	void ScreenAction(JButton b) {
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame Option = new JFrame("Option");
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Dimension dim = toolkit.getScreenSize();
				Option.setPreferredSize(new Dimension(400, 100));
				Option.setLocation((int) (dim.getWidth() / 2 - 400 / 2), (int) (dim.getHeight() / 2 - 100 / 2));
				Option.setResizable(false);

				JPanel Option1 = new JPanel();
				JCheckBox NotVol = new JCheckBox("음소거");
				JButton Save = new JButton("저장 (웹)");
				JButton confirm = new JButton("저장 후 돌아가기");

				Save.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int i = JOptionPane.showConfirmDialog(null, "웹에 지금까지의 기록이 저장됩니다.", "저장",
								JOptionPane.YES_NO_CANCEL_OPTION);
						if (i == JOptionPane.YES_OPTION) {

							JOptionPane.showMessageDialog(null, "저장되었습니다.", "저장", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});

				confirm.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (NotVol.isSelected()) {
							clip.stop();
						} else
							clip.start();
						JOptionPane.showMessageDialog(null, "옵션이 변경되었습니다.", "저장", JOptionPane.INFORMATION_MESSAGE);
						Option.setVisible(false);
					}
				});

				Option1.add(NotVol);
				Option1.add(Save);
				Option1.add(confirm);
				Option.add(Option1);

				Option.pack();
				Option.setVisible(true);
			}
		});
	}

	void arrangeInGridBag(Container con, GridBagLayout gbl, Component obj, int x, int y, int wx, int wy, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = wx;
		gbc.weighty = wy;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(obj, gbc);
		con.add(obj);
	}

	public Font getFont() {
		return font;
	}

	public JPanel getPanel1() {
		return p1;
	}

	public JPanel getPanel2() {
		return p2;
	}

	public JPanel getPanel3() {
		return p3;
	}

	public JButton getNext() {
		return next;
	}

}
