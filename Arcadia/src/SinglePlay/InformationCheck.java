package SinglePlay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
/**
 * 처음화면에서 Start버튼을 눌렀을 때 닉네임이 존재하는 지 안하는지 판별하는 클래스
 * @author Administrator
 *
 */
public class InformationCheck {
	private JFrame f;
	private Login l;
	private mySQL m;

	private boolean DuplicateName;
	private boolean newCharacter;
	private String getNickname;
	private String Nickname;
	private int mapcode;

	@SuppressWarnings({ "static-access" })
	InformationCheck(JFrame frame) throws HeadlessException, Exception {
		f = frame;
		f.setContentPane(new JLabel(new ImageIcon("resources/Pictures/MainBackground.png")));
		m = l.getMySQL();
		getNickname = m.getNickname(m.getStringID());
		mapcode = m.getMapCode(m.getStringID());

		DuplicateName = true;

		if (getNickname.equals("null") || getNickname.equals("NULL")) {
			newCharacter = true;
			DuplicateName = true;
			while (DuplicateName) {
				if (newCharacter) {
					Nickname = JOptionPane.showInputDialog(null, "닉네임을 입력해 주세요.");
					if (m.haveNickname(Nickname)) {
						m.setNickname(Nickname);
						DuplicateName = false;
						newCharacter = false;
					} else
						JOptionPane.showMessageDialog(null, "이미 사용중인 닉네임입니다.\n다시 입력해주세요.");
				}
			}
		} else
			newCharacter = false;

		if (!newCharacter)
			JOptionPane.showMessageDialog(null, "Nickname : " + m.getNickname(m.getStringID()) + "으로 게임을 시작합니다.");
		switch (mapcode) {
		case 1:
			FirstField fi = new FirstField(f);
			break;
		case 2:
			f.setVisible(false);
			MainMap map = new MainMap();
			break;
		}
	}
}
