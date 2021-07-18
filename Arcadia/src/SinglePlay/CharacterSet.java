package SinglePlay;

import java.sql.SQLException;


/**
 * 캐릭터의 정보들을 관할하는 클래스
 * @author Administrator
 *
 */
public class CharacterSet {
	private Login l;
	private mySQL my;

	private int HP, MP, EXP;
	private double exp, Percent, Level;
	private final int[] Experience;				//각 레벨마다의 경험치를 설정

	@SuppressWarnings("static-access")
	CharacterSet() throws Exception {
		my = l.getMySQL();

		HP = 100;

		Experience = new int[99];
		InitEXP();
	}

	public void SaveUserInfo(int e) throws SQLException {
		my.setEXP(e, my.getStringID());
	}

	public double setEXP() throws Exception {
		Level = my.getLevel(my.getStringID());
		exp = my.getEXP(my.getStringID());
		Percent = 100 * (exp / Experience[(int) (Level - 1)]);

		if (exp >= Experience[(int) (Level - 1)]) {				//레벨업
			my.setLevel((int) Level + 1, my.getStringID());
			exp = 0;
		}

		return Percent / 100;
	}

	public void setNewEXP(int n) throws Exception {
		exp += n;
		my.setEXP((int) exp, my.getStringID());
	}

	public double setHP(double d) throws SQLException {
		Percent = HP - d / HP;

		return Percent;
	}

	public double getMaxEXP() {
		return Experience[(int) (Level - 1)];
	}

	public void setMP() {

	}

	public void InitEXP() {
		for (int i = 0; i < 99; i++) {
			if (i < 10) {
				Experience[i] = i * 10;
			} else if (i >= 10 && i < 20) {
				Experience[i] = i * 10 + 10;
			} else if (i >= 20 && i < 30) {
				Experience[i] = i * 10 + 30;
			} else if (i >= 30 && i < 40) {
				Experience[i] = i * 10 + 50;
			} else if (i >= 40 && i < 50) {
				Experience[i] = i * 10 + 70;
			} else if (i >= 50 && i < 60) {
				Experience[i] = i * 10 + 90;
			} else if (i >= 60 && i < 70) {
				Experience[i] = i * 10 + 110;
			} else if (i >= 70 && i < 80) {
				Experience[i] = i * 10 + 130;
			} else if (i >= 80 && i < 90) {
				Experience[i] = i * 10 + 150;
			} else if (i >= 90 && i < 99) {
				Experience[i] = i * 10 + 200;
			}
		}
	}
}
