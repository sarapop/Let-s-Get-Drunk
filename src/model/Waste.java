package model;

import javafx.scene.canvas.GraphicsContext;
import lib.AudioUtility;
import lib.DrawingUtility;
import lib.IRenderableObject;

public class Waste extends TargetObject implements IRenderableObject {
	
	public Waste(int position) {
		super();
		// TODO Auto-generated constructor stub
		this.width = 78;
		this.height = 25;
		this.position = position;
		initializeX(this.width);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtility.drawObject(gc, x, y, "waste");
	}
	
	public void crash(Drunkard player) {
		if (!exist)
			return;
		if (player.isSamePosition(this)) {
			AudioUtility.playSound("crash");
			exist = false;
			player.setExist(false);
			return;
		}
	}
	
}
