package lib;

public class TotalScore {
	
	public static final TotalScore instance = new TotalScore();
	private int totalScore;
	
	public TotalScore() {
		this.totalScore = 0;
	}

	public int getTotalScore() {
		return instance.totalScore;
	}
	
	public void increaseTotalScore(int amount){
		instance.totalScore += amount;	
	}
	public void resetTotalScore(){
		instance.totalScore=0;
	}
}
