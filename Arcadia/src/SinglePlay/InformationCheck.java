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
 * ó��ȭ�鿡�� Start��ư�� ������ �� �г����� �����ϴ� �� ���ϴ��� �Ǻ��ϴ� Ŭ����
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
					Nickname = JOptionPane.showInputDialog(null, "�г����� �Է��� �ּ���.");
					if (m.haveNickname(Nickname)) {
						m.setNickname(Nickname);
						DuplicateName = false;
						newCharacter = false;
					} else
						JOptionPane.showMessageDialog(null, "�̹� ������� �г����Դϴ�.\n�ٽ� �Է����ּ���.");
				}
			}
		} else
			newCharacter = false;

		if (!newCharacter)
			JOptionPane.showMessageDialog(null, "Nickname : " + m.getNickname(m.getStringID()) + "���� ������ �����մϴ�.");
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
