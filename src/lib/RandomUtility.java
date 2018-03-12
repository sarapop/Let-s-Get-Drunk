package lib;

public class RandomUtility {
	public static int random(int start, int end) {
		// TODO Auto-generated method stub
		return start + (int)(Math.random() * (end - start + 1));
	}

}
