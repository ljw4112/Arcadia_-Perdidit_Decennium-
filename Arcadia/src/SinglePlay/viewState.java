package SinglePlay;

import java.awt.*;
import javax.swing.*;
/**
 * �ʱ�ȭ���� �ٸ��ִ� Ŭ����
 * @author Administrator
 *
 */
public class viewState {
	private JFrame StateViewer;
	private JLabel Nickname;
	private JLabel viewNickname;
	private JLabel Job;
	private JLabel viewJob;
	private JLabel Level;
	private JLabel viewLevel;
	private JLabel Str;
	private JLabel viewStr;
	private JLabel Dex;
	private JLabel viewDex;
	private JLabel Mac;
	private JLabel viewMac;
	private JLabel Luk;
	private JLabel viewLuk;
	
	private mySQL m;
	
	viewState(JFrame frame) throws Exception{
		StateViewer = new JFrame("Status Frame");
		Nickname = new JLabel("�г��� : ");
		viewNickname = new JLabel(m.getNickname(m.getStringID()));
		Job = new JLabel("���� : ");
		viewJob = new JLabel("����");
		Level = new JLabel("LV : ");
		viewLevel = new JLabel("1");
		Str = new JLabel("STR : ");
		viewStr = new JLabel();
		Dex = new JLabel("Dex : ");
		viewDex = new JLabel();
		Mac = new JLabel("Int : ");
		viewMac = new JLabel();
		Luk = new JLabel("Luk : ");
		viewLuk = new JLabel();
		
		StateViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StateViewer.setBounds((1920 / 2 - 300 / 2), (1080 / 2 - 600 / 2), 300, 600);
		StateViewer.setLayout(new GridLayout(5,2));
		StateViewer.setVisible(true);
	}
}
