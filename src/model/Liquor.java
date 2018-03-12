package model;

import javafx.scene.canvas.GraphicsContext;
import lib.AudioUtility;
import lib.DrawingUtility;
import lib.IRenderableObject;
import lib.TotalScore;

public class Liquor extends TargetObject implements IRenderableObject {

	public Liquor(int position) {
		super();
		// TODO Auto-generated constructor stub
		this.width = 57;
		this.height = 90;
		this.position = position;
		initializeX(this.width);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtility.drawObject(gc, x, y, "liquor");
	}

	public void collect(Drunkard player) {
		if (!exist)
			return;
		if (player.isSamePosition(this)) {
			AudioUtility.playSound("collect");
			exist = false;
			TotalScore.instance.increaseTotalScore(1);
			return;
		}
	}

}
