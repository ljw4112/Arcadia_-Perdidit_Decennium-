package SinglePlay;

import java.sql.*;
import javax.swing.*;
import java.util.Calendar;;
/**
 * DB와 통신하는 클래스
 * @author Administrator
 *
 */
public class mySQL {
	private String url = "jdbc:mysql://127.0.0.1:8080/login";
	private String user = "root";
	private String pass = "1234";
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;

	private boolean haveID;
	private int memberNumber;
	private String[] ID;
	private String nickname;
	private String password;
	private String Job;
	private String tid;
	private int[] status;
	private String[] statusName;
	private boolean login;
	private int MapCode;
	private int Argent;

	mySQL() {
		login = false;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, user, pass);
			JOptionPane.showMessageDialog(null, "Success to connect mySQL SERVER!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to connect mySQL SERVER!");
		}

		status = new int[4];
		statusName = new String[4];

		statusName[0] = "str";
		statusName[1] = "dex";
		statusName[2] = "mac";
		statusName[3] = "luk";
	}

	void join(String id, String pw) throws SQLException {
		try {
			st = con.createStatement();
			String sql = "insert into login values(\"" + id + "\",\"" + pw + "\",\"null\",1,\"null\",0,0,0,0,0,0,1);";
			int n = st.executeUpdate(sql);
			if (n > 0) {
				JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.", "가입완료", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "서버와의 통신에 실패하였습니다.", "통신실패", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	boolean login(String id, String pw) throws SQLException {
		String sql = "select password, Nickname from login where id='" + id + "'";
		st = con.createStatement();
		rs = st.executeQuery(sql);
		haveID = isLogin(id); // 받은 ID가 DB안에 존재하면 TRUE대입

		if (haveID && rs.first()) {
			password = rs.getString("password");
			nickname = rs.getString("Nickname");

			if (password.equals(pw))
				login = true;
		}
		return login;
	}

	boolean isLogin(String id) throws SQLException {
		tid = id;
		boolean ok;
		String sql = "select count(*) from login where id=\"" + id + "\";";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.first();
		int row = rs.getInt("COUNT(*)");

		if (row == 0) {
			ok = false;
		} else
			ok = true;

		return ok;
	}

		boolean haveNickname(String Nickname) throws SQLException {
			boolean ok;
			String sql = "select count(*) from login where Nickname=\"" + Nickname + "\";";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
	
			rs.first();
			int row = rs.getInt("COUNT(*)");
	
			if (row == 0) {
				ok = true;
			} else
				ok = false;
	
			return ok;
		}

	public void setLevel(int level, String id) throws SQLException{
		String sql = "update login set Level=" + level + " where id='" + id + ";";
		
		st = con.createStatement();
		int n = st.executeUpdate(sql);
		
		if(n>0)
			System.out.println("Success");
	}
	
	public int getLevel(String id) throws SQLException {
		int level;
		String sql = "select level from login where id='" + id + "';";

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		rs.first();
		level = rs.getInt("level");

		return level;
	}

	public void setmemberNumber() {
		try {
			String sql = "select count(*) as \'id\' from login;";
			st = con.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				memberNumber = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getmemberNumber() {
		setmemberNumber();
		return memberNumber;
	}

	public void setNickname(String n) throws SQLException {
		String sql = "update login set Nickname='" + n + "' where id='" + getStringID() + "';";
		st = con.createStatement();
		int m = st.executeUpdate(sql);
		if (m > 0) {
			System.out.println("Success to make Nickname");
		}
	}

	public String getNickname(String id) throws Exception {
		String sql = "select Nickname from login where id='" + id + "';";
		st = con.createStatement();
		rs = st.executeQuery(sql);

		while (rs.next()) {
			nickname = rs.getString("Nickname");
		}

		return nickname;
	}

	public void setID() {
		int i = 0;
		ID = new String[getmemberNumber()];
		try {
			String sql = "select id from login;";
			st = con.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				ID[i] = rs.getString("id");
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[] getID() {
		setID();
		return ID;
	}

	public String getStringID() {
		return tid;
	}

	public int getMapCode(String id) throws SQLException {
		String sql = "select MapCode from login where id='" + id + "';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			MapCode = rs.getInt("MapCode");
		}
		return MapCode;
	}

	public void setJob(int n) {
		if (n == 1)
			Job = "Soldier";
		else if (n == 2)
			Job = "Magician";
		try {
			st = con.createStatement();
			String sql = "update login set Job=\'" + Job + "\' where Nickname=\'" + getNickname(getStringID()) + "\'";
			int m = st.executeUpdate(sql);
			if (m > 0) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getJob(String id) throws SQLException {
		String temp = "", sql = "";
		sql = "select Job from login where id='" + id + "';";
		st = con.createStatement();
		rs = st.executeQuery(sql);

		while (rs.next()) {
			temp = rs.getString("Job");
		}
		return temp;
	}

	public void setMapCode(String id, int MapCode) {
		Calendar cal = Calendar.getInstance();
		int getMonth = cal.get(Calendar.MONTH) + 1;

		try {
			String sql = "update login set MapCode=" + MapCode + " where id= \"" + id + "\";";
			st = con.createStatement();
			int n = st.executeUpdate(sql);

			if (n > 0)
				JOptionPane.showMessageDialog(null, "SAVE SUCCESSFULLY!\nAT " + cal.get(Calendar.YEAR) + "." + getMonth
						+ "." + cal.get(Calendar.DATE));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isKatana(String id) throws SQLException {
		int n = 0;

		String sql = "Select Katana from login where id='" + id + "';";
		st = con.createStatement();
		rs = st.executeQuery(sql);

		while (rs.next()) {
			n = rs.getInt("Katana");
		}

		if (n == 1)
			return true;
		else
			return false;
	}

	public boolean isWarFrame(String id) throws SQLException {
		int n = 0;

		String sql = "Select WarFrame from login where id='" + id + "';";
		st = con.createStatement();
		rs = st.executeQuery(sql);

		while (rs.next()) {
			n = rs.getInt("WarFrame");
		}

		if (n == 1)
			return true;
		else
			return false;
	}

	public boolean isDagger(String id) throws SQLException {
		int n = 0;

		String sql = "Select Dagger from login where id='" + id + "';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			n = rs.getInt("Dagger");
		}

		if (n == 1)
			return true;
		else
			return false;
	}

	public boolean isSpear(String id) throws SQLException {
		int n = 0;

		String sql = "Select Spear from login where id='" + id + "';";
		st = con.createStatement();
		rs = st.executeQuery(sql);

		while (rs.next()) {
			n = rs.getInt("Spear");
		}

		if (n == 1)
			return true;
		else
			return false;
	}

	public int[] getUserStatus(String id) throws SQLException {
		String sql = "select str,dex,mac,luk from login where id='" + id + "';";
		st = con.createStatement();
		rs = st.executeQuery(sql);

		while (rs.next()) {
			for (int i = 0; i < status.length; i++) {
				status[i] = rs.getInt(statusName[i]);
			}
		}

		return status;
	}

	public int getArgent(String id) throws Exception {
		String sql = "select Argent from login where id='" + id + "';";
		st = con.createStatement();
		rs = st.executeQuery(sql);

		while (rs.next()) {
			Argent = rs.getInt("Argent");
		}
		return Argent;
	}

	public void setArgent(int money, String id) throws SQLException {
		String sql = "update login set Argent=" + money + " where id='" + id + "';";
		st = con.createStatement();
		int n = st.executeUpdate(sql);

		if (n > 0)
			System.out.println("Success");
	}

	public int getRedPosion(String id) throws SQLException {
		int RedPosion;
		String sql = "select RedPosion from login where id='" + id + "';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		rs.first();
		RedPosion = rs.getInt("RedPosion");

		return RedPosion;
	}

	public int getBluePosion(String id) throws SQLException {
		int BluePosion;
		String sql = "select BluePosion from login where id='" + id + "';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		rs.first();
		BluePosion = rs.getInt("BluePosion");

		return BluePosion;
	}

	public void setRedPotion(int n, String id) throws Exception {
		int potion = n + getRedPosion(id);

		String sql = "update login set RedPosion=" + potion + " where id='" + id + "';";
		st = con.createStatement();
		int temp = st.executeUpdate(sql);

		if (temp > 0)
			System.out.println("");

	}

	public void setBluePotion(int n, String id) throws Exception {
		int potion = n + getBluePosion(id);
		String sql = "update login set BluePosion=" + potion + " where id='" + id + "';";
		st = con.createStatement();
		int temp = st.executeUpdate(sql);

		if (temp > 0)
			System.out.println("");
	}

	public void setEXP(int n, String id) throws SQLException {
		String sql = "update login set EXP=" + n + " where id='" + id + "';";
		st = con.createStatement();
		int temp = st.executeUpdate(sql);

		if (temp > 0)
			System.out.println("");
	}
	
	public int getUserHP(String id) throws SQLException{
		String sql = "select HP from login where id='" + id + "';";
		st = con.createStatement();
		rs = st.executeQuery(sql);
		
		rs.first();
		return rs.getInt("HP");
	}
	
	public int getUserMP(String id) throws SQLException{
		String sql = "select MP from login where id='" + id + "';";
		st = con.createStatement();
		rs = st.executeQuery(sql);
		
		rs.first();
		return rs.getInt("MP");
	}
	
	public int getEXP(String id) throws SQLException{
		String sql = "select EXP from login where id='" + id + "';";
		st = con.createStatement();
		rs = st.executeQuery(sql);
		
		rs.first();
		return rs.getInt("EXP");
	}
}