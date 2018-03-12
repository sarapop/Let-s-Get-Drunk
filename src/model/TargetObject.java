package model;

import lib.AudioUtility;
import lib.GameProperties;
import lib.IRenderableObject;

public abstract class TargetObject implements IRenderableObject {

	protected boolean exist;
	protected int x;
	protected int y;
	protected int height;
	protected int width;
	protected int position;

	public TargetObject() {
		y = 0;
		exist = true;
	}
	
	public void initializeX(int width) {
		switch (this.position) {
			case (0) : this.x = GameProperties.screenWidth/8 - this.width/2;
				break;
			case (1) : this.x = GameProperties.screenWidth/8 - this.width/2 + GameProperties.screenWidth/4;
				break;
			case (2) : this.x = GameProperties.screenWidth/8 - this.width/2 + GameProperties.screenWidth/2;
				break;
			case (3) : this.x = GameProperties.screenWidth/8 - this.width/2 + GameProperties.screenWidth/4 + GameProperties.screenWidth/2;
				break;
		}
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}

	public void move() {
		// TODO Auto-generated method stub
		if (!exist)
			return;
		if (y > GameProperties.screenHeight) {
			exist = false;
			return;
		} else {
			y += 3;
		}
	}
	
	public void outOfReached(Drunkard player) {
		if (!exist)
			return;
		if (this.y + this.height == GameProperties.screenHeight) {
			AudioUtility.playSound("no");
			exist = false;
			player.setExist(false);
			return;
		}
	}
	
	public boolean exist() {
		return exist;
	}

}
