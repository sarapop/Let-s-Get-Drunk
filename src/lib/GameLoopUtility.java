package lib;

import javafx.animation.AnimationTimer;
import logic.MainLogic;
import main.Main;

public class GameLoopUtility {
	
	public static AnimationTimer animationTimer;

	public static void runGameLoop(MainLogic gameLogic) {
		// TODO Auto-generated method stub
		 
		 animationTimer = new AnimationTimer() {
			 public void handle(long now) {
				 try {
					 Main.instance.drawGameScreen();
					 if (gameLogic.isReadyToRender()) {
						 gameLogic.logicUpdate();
					 }
					 InputUtility.postUpdate();
				 } catch (Exception e) {
					 e.printStackTrace();
					 GameLoopUtility.animationTimer.stop();
				 }
		 	}
		 };
		 animationTimer.start();
	}

}
