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
