package game;

import javax.sound.sampled.*;

public class Sound {
	
	private Clip clip;
	
	// Change file name to match yours, of course
	public static Sound backgroundmusic = new Sound("backgroundmusic.wav");
	public static Sound laser1 = new Sound("laser1.wav");
	//public static Sound sound3 = new Sound("sound3.wav");
	
	public Sound (String fileName) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource("res/sound/" + fileName));
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop(){
		if(clip == null) return;
		clip.stop();
	}
	
	public void loop() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isActive(){
		return clip.isActive();
	}
}