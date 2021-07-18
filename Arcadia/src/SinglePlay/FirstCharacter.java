package SinglePlay;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import javax.swing.ImageIcon;

/**
 * 첫프레임에서 유저 설정 클래스
 * @author Administrator
 *
 */

public class FirstCharacter {
	private boolean isnew = false;
	private boolean isDamaged;
	private boolean isMagic;
	private boolean isClear;

	private int HP;
	private int MP;
	private int EXP;

	private String Status;
	private JPanel StatusPanel;
	private JLabel CharacterLabel;
	private JProgressBar HPBar;
	private JProgressBar MPBar;
	private JProgressBar EXPBar;
	private JLabel Job;
	private JLabel Nickname;
	private Login l;
	private mySQL ms;

	@SuppressWarnings("static-access")
	FirstCharacter(JPanel p, JFrame frame) throws Exception {
		ImageIcon charImg = new ImageIcon("resources/Pictures/Character1.png");
		
		CharacterLabel = new JLabel(charImg);
		StatusPanel = new JPanel();
		HPBar = new JProgressBar();
		MPBar = new JProgressBar();
		EXPBar = new JProgressBar();

		Job = new JLabel();
		Nickname = new JLabel();

		ms = l.getMySQL();

		HPBar.setForeground(Color.RED);
		MPBar.setForeground(Color.BLUE);
		EXPBar.setForeground(Color.GREEN);

		p.setLayout(new GridLayout(1, 2));
		StatusPanel.setLayout(new GridLayout(5, 2));
		
		Nickname.setText(ms.getNickname(ms.getStringID()));
		Job.setText(ms.getJob(ms.getStringID()));

		StatusPanel.add(new JLabel("닉네임: "));
		StatusPanel.add(Nickname);
		StatusPanel.add(new JLabel("직업: "));
		StatusPanel.add(Job);
		StatusPanel.add(new JLabel("HP: "));
		StatusPanel.add(HPBar);
		StatusPanel.add(new JLabel("MP: "));
		StatusPanel.add(MPBar);
		StatusPanel.add(new JLabel("Exp: "));
		StatusPanel.add(EXPBar);

		HPBar.setStringPainted(true);
		MPBar.setStringPainted(true);
		EXPBar.setStringPainted(true);

		p.add(CharacterLabel);
		p.add(StatusPanel);
	}
}
