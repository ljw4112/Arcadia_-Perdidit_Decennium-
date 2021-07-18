package SinglePlay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

/*
 * 마을에서 물약을 살 수 있는 상점을 나타내는 클래스
 */
@SuppressWarnings("serial")
public class Market extends JLabel implements ActionListener, Runnable {
	private JButton sell, buy;
	private JButton RedPotion;
	private JButton BluePotion;
	private JButton UserRed, UserBlue;
	private JButton Exit;
	private JLabel Argent;
	private JLabel RedNumber, BlueNumber;
	private JTextField UserText, VendeurText;

	private String id; // 아이디를 저장
	private int money; // 가지고 있는 화폐
	private int RedNum; // 레드포션 갯수
	private int BlueNum; // 블루포션 갯수
	private boolean isRedClick, isBlueClick; // 아이템을 클릭 했는지 안했는지
	private boolean UserRedClick, UserBlueClick;
	private int isRedEconomic = 0, isBlueEconomic = 0; // 물약을 샀는지 안샀는지 판단하여 스레드를 돌려
													// 항상 물약갯수를 업데이트 해주는 변수
	// 더블클릭의 여부를 판단하는 변수
	private int Rclick;
	private int Bclick;
	private int UserR, UserB;

	private Login l;
	private mySQL my;
	private Thread t;

	Market() throws Exception {
		my = Login.getMySQL();
		id = my.getStringID();

		UserRedClick = false;
		UserBlueClick = false;

		money = my.getArgent(id);
		RedNum = my.getRedPosion(id);
		BlueNum = my.getBluePosion(id);

		RedPotion = new JButton(new ImageIcon("resources/Pictures/RedPotionBtn.png"));
		BluePotion = new JButton(new ImageIcon("resources/Pictures/BluePotionBtn.png"));
		Exit = new JButton(new ImageIcon("resources/Pictures/XButton.png"));
		sell = new JButton(new ImageIcon("resources/Pictures/SellButton.png"));
		buy = new JButton(new ImageIcon("resources/Pictures/BuyButton.png"));
		UserRed = new JButton(new ImageIcon("resources/Pictures/SellRedBtn.png"));
		UserBlue = new JButton(new ImageIcon("resources/Pictures/SellBlueBtn.png"));
		UserText = new JTextField();
		VendeurText = new JTextField();
		Argent = new JLabel("" + money);
		RedNumber = new JLabel("" + RedNum);
		BlueNumber = new JLabel("" + BlueNum);

		setTransparent(RedPotion);
		setTransparent(BluePotion);
		setTransparent(Exit);
		setTransparent(sell);
		setTransparent(buy);
		setTransparent(UserRed);
		setTransparent(UserBlue);

		RedPotion.setBounds(40, 140, 407, 96);
		BluePotion.setBounds(40, 233, 407, 96);
		Exit.setBounds(710, 10, 80, 80);
		sell.setBounds(100, 530, 100, 30);
		buy.setBounds(260, 530, 100, 30);

		UserRed.setBounds(450, 350, 177, 40);
		UserBlue.setBounds(600, 350, 177, 40);
		Argent.setBounds(570, 502, 300, 100);
		Argent.setFont(new Font("Nexa Bold.otf", Font.BOLD, 24));
		Argent.setForeground(Color.BLACK);
		RedNumber.setBounds(712, 140, 100, 100);
		RedNumber.setForeground(Color.RED);
		RedNumber.setFont(new Font("Nexa Bold.otf", Font.BOLD, 30));

		BlueNumber.setBounds(712, 235, 100, 100);
		BlueNumber.setForeground(Color.BLUE);
		BlueNumber.setFont(new Font("Nexa Bold.otf", Font.BOLD, 30));

		UserText.setBounds(625, 490, 125, 20);
		VendeurText.setBounds(260, 490, 125, 20);
		setTextField(UserText);
		setTextField(VendeurText);

		isRedClick = false;
		isBlueClick = false;
		Rclick = 0;
		Bclick = 0;
		UserR = 0;
		UserB = 0;

		setIcon(new ImageIcon("resources/Pictures/MarketPanel.png"));
		setSize(800, 600);
		setLocation(300, 100);
		setVisible(true);

		RedPotion.addActionListener(this);
		BluePotion.addActionListener(this);
		Exit.addActionListener(this);
		buy.addActionListener(this);
		sell.addActionListener(this);
		UserRed.addActionListener(this);
		UserBlue.addActionListener(this);

		repaint();

		add(UserBlue);
		add(UserRed);
		add(VendeurText);
		add(UserText);
		add(RedNumber);
		add(BlueNumber);
		add(Argent);
		add(sell);
		add(buy);
		add(RedPotion);
		add(BluePotion);
		add(Exit);

		t = new Thread(this);
		t.start();
	}

	public void setTextField(JTextField t) {
		t.setOpaque(false);
		t.setBorder(null);
	}

	public void setTransparent(JButton b) {
		b.setOpaque(false);
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == RedPotion) {
			if (Rclick == 0) {
				RedPotion.setIcon(new ImageIcon("resources/Pictures/Sel_RedPotionBtn.png"));
				isRedClick = true;
				Rclick = 1;
			} else if (Rclick == 1) {
				RedPotion.setIcon(new ImageIcon("resources/Pictures/RedPotionBtn.png"));
				isRedClick = false;
				Rclick = 0;
			}
		}

		if (arg0.getSource() == BluePotion) {
			if (Bclick == 0) {
				BluePotion.setIcon(new ImageIcon("resources/Pictures/Sel_BluePotionBtn.png"));
				isBlueClick = true;
				Bclick = 1;
			} else if (Bclick == 1) {
				BluePotion.setIcon(new ImageIcon("resources/Pictures/BluePotionBtn.png"));
				isBlueClick = false;
				Bclick = 0;
			}
		}

		if (arg0.getSource() == Exit) {
			setVisible(false);
		}

		if (isRedClick && arg0.getSource() == buy) {
			if (!VendeurText.getText().isEmpty()) {
				RedPotion.setIcon(new ImageIcon("resources/Pictures/RedPotionBtn.png"));
				try {
					my.setRedPotion(Integer.parseInt(VendeurText.getText()), my.getStringID());
					isRedEconomic = 1;
					money -= 10 * Integer.parseInt(VendeurText.getText());
					my.setArgent(money, id);
					RedNum += Integer.parseInt(VendeurText.getText());
					VendeurText.setText("");
					RedNumber.setText("" + RedNum);
					RedNumber.repaint();
					isRedEconomic = 0;
					Argent.setText("" + money);
					Argent.repaint();
					repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (isBlueClick && arg0.getSource() == buy) {
			if (!VendeurText.getText().isEmpty()) {
				BluePotion.setIcon(new ImageIcon("resources/Pictures/BluePotionBtn.png"));
				try {
					my.setBluePotion(Integer.parseInt(VendeurText.getText()), my.getStringID());
					isBlueEconomic = 1;
					money -= 10 * Integer.parseInt(VendeurText.getText());
					my.setArgent(money, id);
					VendeurText.setText("");
					BlueNumber.setText("" + BlueNum);
					isBlueEconomic = 0;
					Argent.setText("" + money);
					repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (arg0.getSource() == UserRed) {
			if (UserR == 0) {
				UserRed.setIcon(new ImageIcon("resources/Pictures/Select_SellRedBtn.png"));
				UserRedClick = true;
				UserR = 1;
			} else if (UserR == 1) {
				UserRed.setIcon(new ImageIcon("resources/Pictures/SellRedBtn.png"));
				UserRedClick = false;
				UserR = 0;
			}
		}

		if (arg0.getSource() == UserBlue) {
			if (UserB == 0) {
				UserBlue.setIcon(new ImageIcon("resources/Pictures/Select_SellBlueBtn.png"));
				UserBlueClick = true;
				UserB = 1;
			} else if (UserB == 1) {
				UserBlue.setIcon(new ImageIcon("resources/Pictures/SellBlueBtn.png"));
				UserBlueClick = false;
				UserB = 0;
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			if (isRedEconomic == 1) {
				try {
					RedNum = my.getRedPosion(id);
					System.out.println(RedNum);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			} else if (isBlueEconomic == 1) {
				try {
					BlueNum = my.getBluePosion(id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				BlueNumber.setText("" + BlueNum);
				isBlueEconomic = 0;
				Argent.setText("" + money);
			}
		}
	}
}
