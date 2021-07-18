package SinglePlay;

import javax.swing.JTextArea;
/**
 * Ÿ�� ġ�� �� ó�� ��Ÿ���ִ� Ŭ����
 * @author Administrator
 *
 */
public class TypeAnimation implements Runnable {
	private JTextArea ta;
	private String s;
	private int milliSecond;
	
	void setInitialize(JTextArea ta, String s, int milliSecond){
		this.ta = ta;
		this.s = s;
		this.milliSecond = milliSecond;
	}
	
	void TypeAnimated(JTextArea ta, String s, int milliSecond) {
		for (int i = 0; i < s.length(); i++) {
			ta.append("" + s.charAt(i));
			try {
				Thread.sleep(milliSecond);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run(){
		TypeAnimated(ta,s,milliSecond);
	}
}
