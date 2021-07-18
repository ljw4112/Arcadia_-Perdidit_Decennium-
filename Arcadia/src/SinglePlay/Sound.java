package SinglePlay;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * ���� ��ü�� SOUND�� �����ϴ� Ŭ����
 * @author Administrator
 *
 */
public class Sound {
	private Clip clip;
	private String MusicAddress;
	
	Sound(String s) throws Exception {
		MusicAddress = s;
		String firstSound = MusicAddress;
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(firstSound).getAbsoluteFile());
		clip = AudioSystem.getClip();
		clip.open(audioInputStream);
	}
	
	void MusicStart(){
		clip.start();
		clip.loop(50000000);
	}
	
	void MusicStop(){
		clip.stop();
	}
	
	public Clip getClip(){
		return clip;
	}
}
