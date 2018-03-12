package lib;

import javafx.scene.media.AudioClip;

public class AudioUtility {
	
	private static final String no = "se/no.wav";
	private static final String crash = "se/crash.wav";
	private static final String collect = "se/collect.wav";
	private static final String move = "se/move.wav";
	private static final String start = "se/startsong.wav";
	private static final String game = "se/gamesong.wav";
	private static AudioClip sound_no;
	private static AudioClip sound_crash;
	private static AudioClip sound_collect;
	private static AudioClip sound_move;
	private static AudioClip music_start;
	private static AudioClip music_game;
	
	static {
		loadResource();
	}

	public static void loadResource() {
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		sound_no = new AudioClip(loader.getResource(no).toString());
		sound_crash = new AudioClip(loader.getResource(crash).toString());
		sound_collect = new AudioClip(loader.getResource(collect).toString());
		sound_move = new AudioClip(loader.getResource(move).toString());
		music_start = new AudioClip(loader.getResource(start).toString());
		music_game = new AudioClip(loader.getResource(game).toString());
		music_start.setCycleCount(AudioClip.INDEFINITE);
		music_game.setCycleCount(AudioClip.INDEFINITE);
	}

	public static void playSound(String identifier) {
		switch (identifier) {
			case "crash" : sound_crash.play();
				break;
			case "collect" : sound_collect.play();
				break;
			case "no" : sound_no.play();
				break;
			case "move" : sound_move.play();
				break;
			case "start" : music_start.play();
				break;
			case "game" : music_game.play();
				break;
		}
	}
	
	public static void stopSound(String identifier) {
		switch (identifier) {
			case "start" : music_start.stop();
				break;
			case "game" : music_game.stop();
				break;
		}
	}

}
