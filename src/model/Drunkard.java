package model;

import javafx.scene.canvas.GraphicsContext;
import lib.GameProperties;
import lib.DrawingUtility;
import lib.IRenderableObject;
import lib.TotalScore;

public class Drunkard implements IRenderableObject {

	private int x;
	private int width = 65;
	private int height = 175;
	private int y;
	private int position;
	private String charName;
	private boolean exist;
	private boolean pause;

	public Drunkard(int position,String charName) {
		// TODO Auto-generated constructor stub
		this.setX(position);
		this.charName = charName;
		this.y = GameProperties.screenHeight - this.height;
		this.setPosition(position);
		this.exist = true;
		this.pause = false;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtility.drawObject(gc, this.x, this.y, charName);
		DrawingUtility.drawStatus(gc, TotalScore.instance.getTotalScore(), pause);
	}

	@Override
	public boolean exist() {
		// TODO Auto-generated method stub
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public boolean isSamePosition(TargetObject other) {
		if (this.position == other.position && this.y <= other.y + other.height) {
			return true;
		} else {
			return false;
		}
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setX(int position) {
		switch (position) {
		case (0):
			this.x = GameProperties.screenWidth / 8 - this.width / 2;
			break;
		case (1):
			this.x = GameProperties.screenWidth / 8 - this.width / 2 + GameProperties.screenWidth / 4;
			break;
		case (2):
			this.x = GameProperties.screenWidth / 8 - this.width / 2 + GameProperties.screenWidth / 2;
			break;
		case (3):
			this.x = GameProperties.screenWidth / 8 - this.width / 2 + GameProperties.screenWidth / 4
					+ GameProperties.screenWidth / 2;
			break;
		}
	}

	public boolean isPause() {
		// TODO Auto-generated method stub
		return pause;
	}

	public void setPause(boolean pause) {
		// TODO Auto-generated method stub
		this.pause = pause;
	}
}
