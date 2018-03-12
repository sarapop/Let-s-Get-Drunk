package lib;

import java.util.ArrayList;
import javafx.scene.input.KeyCode;

public class InputUtility {
	
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
	private static ArrayList<KeyCode> keyTriggered = new ArrayList<>();

	public static boolean getKeyPressed(KeyCode keycode) {
		return InputUtility.keyPressed.contains(keycode);
	}

	public static void setKeyPressed(KeyCode keycode, boolean pressed) {
		if (pressed) {
			InputUtility.keyPressed.add(keycode);
		} else {
			InputUtility.keyPressed.remove(keycode);
		}
	}

	public static boolean getKeyTriggered(KeyCode keycode) {
		return InputUtility.keyTriggered.contains(keycode);
	}

	public static void setKeyTriggered(KeyCode keycode, boolean pressed) {
		if (pressed) {
			InputUtility.keyTriggered.add(keycode);
		} else {
			InputUtility.keyTriggered.remove(keycode);
		}
	}

	public static void postUpdate() {
		keyTriggered.clear();
		keyPressed.clear();
	}

}
