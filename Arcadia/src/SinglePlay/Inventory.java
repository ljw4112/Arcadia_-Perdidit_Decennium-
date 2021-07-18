package SinglePlay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * 인벤토리를 클릭했을 때 나타나는 프레임
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Inventory extends JFrame implements ActionListener, MouseListener {
	private ImageIcon Inventory;
	private ImageIcon x;
	private JButton xbtn;
	private String id;

	private JButton Dagger;
	private JButton Spear;
	private JButton Katana;
	private JButton WarFrame;

	private JLabel KatanaInfo;
	private JLabel DaggerInfo;
	private JLabel WarFrameInfo;
	private JLabel SpearInfo;

	private Login l;
	private mySQL m;

	@SuppressWarnings("static-access")
	Inventory() throws IOException, SQLException {
		Dagger = new JButton(new ImageIcon("resources/Pictures/Dagger.png"));
		Spear = new JButton(new ImageIcon("resources/Pictures/Spear.png"));
		Katana = new JButton(new ImageIcon("resources/Pictures/Katana.png"));
		WarFrame = new JButton(new ImageIcon("resources/Pictures/WarFrame.png"));

		KatanaInfo = new JLabel();
		DaggerInfo = new JLabel();
		SpearInfo = new JLabel();
		WarFrameInfo = new JLabel();

		KatanaInfo.setIcon(new ImageIcon("resources/Pictures/KatanaInfor.png"));
		DaggerInfo.setIcon(new ImageIcon("resources/Pictures/DaggerInfor.png"));
		SpearInfo.setIcon(new ImageIcon("resources/Pictures/SpearInfor.png"));
		WarFrameInfo.setIcon(new ImageIcon("resources/Pictures/WarFrameInfor.png"));

		Inventory = new ImageIcon("resources/Pictures/Inventory.png");
		x = new ImageIcon("resources/Pictures/XButton.png");
		m = l.getMySQL();
		id = m.getStringID();

		xbtn = new JButton(x);
		xbtn.setBounds(880, 20, 80, 80);

		xbtn.addActionListener(this);
		Katana.addActionListener(this);
		Spear.addActionListener(this);
		WarFrame.addActionListener(this);
		Dagger.addActionListener(this);

		Katana.addMouseListener(this);
		Spear.addMouseListener(this);
		WarFrame.addMouseListener(this);
		Dagger.addMouseListener(this);

		makeUI();

		setLayout(null);
		setContentPane(new JLabel(Inventory));
		setLocation(1920 / 2 - 1000 / 2, 1080 / 2 - 500 / 2);
		setUndecorated(true);
		setSize(1000, 500);
		setVisible(true);
		if (m.isKatana(id)) {
			Katana.setBounds(258, 264, 150, 150);
			add(Katana);
		}

		if (m.isDagger(id)) {
			Dagger.setBounds(417, 200, 150, 150);
			add(Dagger);
		}

		if (m.isSpear(id)) {
			Spear.setBounds(547, 93, 300, 300);
			add(Spear);
		}

		if (m.isWarFrame(id)) {
			WarFrame.setBounds(570, 388, 300, 300);
			add(WarFrame);
		}
		ButtonManage(Katana);
		ButtonManage(Spear);
		ButtonManage(Dagger);
		ButtonManage(WarFrame);
		ButtonManage(xbtn);

		KatanaInfo.setBounds(270, 10, 500, 500);
		// KatanaInfo.setVisible(false);

		add(xbtn);
	}

	public void makeUI() {
		xbtn.setOpaque(false);
		xbtn.setBorderPainted(false);
		xbtn.setContentAreaFilled(false);
	}

	public void ButtonManage(JButton b) {
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == xbtn) {
			setVisible(false);
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == Katana) {
			add(KatanaInfo, 2, -1);
			KatanaInfo.setVisible(true);
			KatanaInfo.repaint();
			repaint();

			Katana.setVisible(false);
			Dagger.setVisible(false);
			Spear.setVisible(false);
			WarFrame.setVisible(false);
		} else if (arg0.getSource() == Dagger) {
			add(DaggerInfo, 2, -1);
			DaggerInfo.setVisible(true);
			DaggerInfo.repaint();
			repaint();

			Katana.setVisible(false);
			Dagger.setVisible(false);
			Spear.setVisible(false);
			WarFrame.setVisible(false);
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (arg0.getSource() == Katana) {
			KatanaInfo.setVisible(false);

			Katana.setVisible(true);
			Dagger.setVisible(true);
		} else if (arg0.getSource() == Dagger) {
			DaggerInfo.setVisible(false);

			Katana.setVisible(true);
			Dagger.setVisible(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
