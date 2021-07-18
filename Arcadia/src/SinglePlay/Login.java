package SinglePlay;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * ���α׷��� ������ �� ���� ó�� �����ϴ� Ŭ����
 * �α����� �����Ѵ�.
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Login extends JFrame implements KeyListener {
	private static mySQL m = new mySQL();

	private ImageIcon img;
	private JTextField id;
	private JPasswordField pass;

	public static mySQL getMySQL() {
		return m;
	}

	Login() throws IOException {
		id = new JTextField();
		pass = new JPasswordField();

		img = new ImageIcon(ImageIO.read(new File("resources/Pictures/Login.png")));
		setContentPane(new JLabel(img));
		setUndecorated(true);
		setLayout(null);
		id.setFocusable(true);
		pass.addKeyListener(this);
		setSize(800, 600);
		setLocation(1920 / 2 - 800 / 2, 1080 / 2 - 600 / 2);

		makeUI();
		setVisible(true);
	}

	public void makeUI() {
		setTransparent(id);
		setTransparent(pass);

		id.setBounds(540, 355, 170, 20);
		pass.setBounds(540, 385, 170, 20);
		add(id);
		add(pass);
	}

	void setTransparent(JTextField t) { // ���� �����Լ�
		t.setOpaque(false);
		t.setBorder(null);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void keyPressed(KeyEvent arg0) {
		try {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				if (m.login(id.getText(),pass.getText())) {
					FirstGUI f = new FirstGUI();
					try {
						f.GUI();
						setVisible(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "�ùٸ��� ���� ������ �ԷµǾ����ϴ�", "����", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (arg0.getKeyCode() == KeyEvent.VK_ALT && arg0.getKeyCode() == KeyEvent.VK_J) {
			System.out.println("Hello");
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}