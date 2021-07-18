package MultiPlay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MultiPlayMain {
	private ImageIcon BackImg = new ImageIcon("resources/Pictures/ServerMainBackground.png");

	private JFrame frame;

	public MultiPlayMain(JFrame f) {
		System.out.println("Hello World");
		frame = f;
		InitFrame();
	}

	public void InitFrame() {
		frame.setContentPane(new JLabel(BackImg));
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
	}
}
