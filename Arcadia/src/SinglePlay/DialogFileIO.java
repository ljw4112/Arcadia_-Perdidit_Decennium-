package SinglePlay;

import java.io.*;
import java.util.ArrayList;
/**
 * 대사 txt 파일을 출력해주는 함수
 * @author Administrator
 *
 */

public class DialogFileIO {
	private String Dialog;
	private File DFile1;
	private FileReader fr1;
	private BufferedReader br;
	private ArrayList<String> dialogString;
	private int cnt;

	DialogFileIO() throws IOException {
		DFile1 = new File("resources/Data/Announcement.txt");
		fr1 = new FileReader(DFile1);
		br = new BufferedReader(fr1);
		dialogString = new ArrayList<String>();
		String input;
		while ((input = br.readLine()) != null) {
			dialogString.add(input);
			input = new String();
		}
		cnt = 0;
		getDialog();
	}

	public String getDialog() throws IOException {
		return dialogString.get(cnt++);
	}
}
