package SinglePlay;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;

/**
 * 유저 직업을 선택하는 클래스
 * @author Administrator
 *
 */

@SuppressWarnings("serial")
public class FirstField extends JPanel {
	private boolean buttonClick = false;

	private JButton Soldier;
	private JButton Magician;
	private InGameGUI In;
	private JButton b;
	private JTextArea dialogue;
	private String Dialog;

	private TypeAnimation ta;
	private Login l;
	private mySQL m;
	private MainMap map;

	private String BackgroundAddress;
	private String MusicAddress;

	@SuppressWarnings("static-access")
	FirstField(JFrame frame) throws Exception {
		BackgroundAddress = "C:/Users/Administrator/workspace/Arcadia/resources/Pictures/TrainStation.png";
		MusicAddress = "C:/Users/Administrator/workspace/Arcadia/resources/Music/Plain.wav";

		In = new InGameGUI(frame, MusicAddress, BackgroundAddress);
		b = In.getNext();
		m = l.getMySQL();

		Soldier = new JButton("전사");
		Magician = new JButton("마법사");

		b.addActionListener(new ActionListener() {
			DialogFileIO F = new DialogFileIO();
			boolean checkNew = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				Dialog = "";
				if (checkNew == false) {
					dialogue = new JTextArea();
					dialogue.setLineWrap(true);
					dialogue.setColumns(13);
					dialogue.setRows(20);
					dialogue.setFont(In.getFont());
					SwingUtilities.updateComponentTreeUI(frame);
					dialogue.setEditable(false);
					dialogue.setBounds(50, 120, 300, 100);
					In.getPanel3().add(dialogue);
					checkNew = true;
				}
				try {
					while ((Dialog = F.getDialog()) == null) {
						dialogue.setText(Dialog);
					}
					TypeAnimation();
				} catch (Exception e2) {
					dialogue.setVisible(false);
					b.setVisible(false);
					b.setEnabled(false);
					Soldier.setBounds(90, 120, 100, 50);
					Magician.setBounds(200, 120, 100, 50);
					In.getPanel3().add(Soldier);
					In.getPanel3().add(Magician);

					Soldier.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							b.setVisible(true);
							b.setEnabled(true);
							Soldier.setVisible(false);
							Magician.setVisible(false);
							dialogue.setVisible(true);
							dialogue.setText("전사 직업을 선택하였습니다.");
							TypeAnimation();
							m.setJob(1);

							b.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									JOptionPane.showMessageDialog(null, "자동저장 후 다음 스크린으로 넘어갑니다.");
									m.setMapCode(m.getStringID(), 2);
									try {
										map = new MainMap();
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									frame.setVisible(false);
								}
							});
						}
					});
					Magician.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							b.setVisible(true);
							b.setEnabled(true);
							Soldier.setVisible(false);
							Magician.setVisible(false);
							dialogue.setVisible(true);
							In.getPanel3().add(dialogue);
							dialogue.setText("마법사 직업을 선택하였습니다.");
							TypeAnimation();
							m.setJob(2);
							b.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									JOptionPane.showMessageDialog(null, "자동저장 후 다음 스크린으로 넘어갑니다.");
									m.setMapCode(m.getStringID(), 2);
									try {
										map = new MainMap();
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									Sound s = In.getSound();
									s.MusicStop();
									frame.setVisible(false);
								}
							});

						}
					});
				}
				buttonClick = true;
				if (buttonClick == true) {
					dialogue.setText("");
					buttonClick = false;
				}
			}
		});
	}

	void TypeAnimation() {
		ta = new TypeAnimation();
		ta.setInitialize(dialogue, Dialog, 10);
		Thread taThread = new Thread(ta);
		taThread.start();
	}
}

