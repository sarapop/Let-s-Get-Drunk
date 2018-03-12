package logic;

import java.util.ArrayList;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import lib.AudioUtility;
import lib.GameProperties;
import lib.GameLoopUtility;
import lib.RenderableHolder;
import lib.TotalScore;
import lib.IRenderableObject;
import lib.InputUtility;
import lib.RandomUtility;
import main.Main;
import model.TargetObject;
import model.Waste;
import model.Drunkard;
import model.Liquor;

public class MainLogic {

	private Drunkard player1;
	private Drunkard player2;
	private List<TargetObject> onScreenObject1 = new ArrayList<TargetObject>();
	private List<TargetObject> onScreenObject2 = new ArrayList<TargetObject>();
	private boolean game_end;
	private boolean readyToRender;
	private int nextObjectCreationDelay1;
	private int nextObjectCreationDelay2;

	public synchronized void onStart() {
		// TODO Auto-generated method stub
		player1 = new Drunkard(0,"drunkard1");
		player2 = new Drunkard(2,"drunkard2");
		nextObjectCreationDelay1 = RandomUtility.random(GameProperties.objectCreationMinDelay,
				GameProperties.objectCreationMaxDelay);
		nextObjectCreationDelay2 = RandomUtility.random(GameProperties.objectCreationMinDelay,
				GameProperties.objectCreationMaxDelay);
		game_end = false;
		readyToRender = true;
	}

	public void logicUpdate() {
		// TODO Auto-generated method stub
		if (game_end) {
			GameLoopUtility.animationTimer.stop();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("Go home, dude. You're drunk\nYour score is " + TotalScore.instance.getTotalScore());
			alert.setTitle("Game over");
			
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(ClassLoader.getSystemResource("img/icon.png").toString()));
			
			alert.setOnCloseRequest(new EventHandler(){

				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					Main.instance.changeSceneTo("overScene");
				}
				
			});
			alert.show();
			
		}

		boolean triggerKey = InputUtility.getKeyTriggered(KeyCode.SPACE);
		if (triggerKey) {
			player1.setPause(!player1.isPause());
			player2.setPause(!player2.isPause());
		}
		
		if (player1.isPause() || player2.isPause()) {
			return;
		}

		if (!player1.exist() || !player2.exist()) {
			game_end = true;
			return;
		}

		createTarget();

		boolean triggerKey1 = InputUtility.getKeyTriggered(KeyCode.F);
		if (triggerKey1) {
			if (player1.getPosition() == 0) {
				player1.setPosition(1);
			} else if (player1.getPosition() == 1) {
				player1.setPosition(0);
			}

			player1.setX(player1.getPosition());
			AudioUtility.playSound("move");
		}

		boolean triggerKey2 = InputUtility.getKeyTriggered(KeyCode.J);
		if (triggerKey2) {
			if (player2.getPosition() == 2) {
				player2.setPosition(3);
			} else if (player2.getPosition() == 3) {
				player2.setPosition(2);
			}

			player2.setX(player2.getPosition());
			AudioUtility.playSound("move");
		}

		TargetObject target1 = null;
		target1 = getObject1();
		if (target1 instanceof Liquor) {
			((Liquor) target1).collect(player1);
		} else if (target1 instanceof Waste) {
			((Waste) target1).crash(player1);
		}
		
		TargetObject target2 = null;
		target2 = getObject2();
		if (target2 instanceof Liquor) {
			((Liquor) target2).collect(player2);
		} else if (target2 instanceof Waste) {
			((Waste) target2).crash(player2);
		}

		for (int i = onScreenObject1.size() - 1; i >= 0; i--) {
			if (!onScreenObject1.get(i).exist()) {
				onScreenObject1.remove(i);
			}
		}
		
		for (int i = onScreenObject2.size() - 1; i >= 0; i--) {
			if (!onScreenObject2.get(i).exist()) {
				onScreenObject2.remove(i);
			}
		}

		for (TargetObject obj : onScreenObject1) {
			obj.move();
			if (obj instanceof Liquor) {
				obj.outOfReached(player1);
			}
		}
		
		for (TargetObject obj : onScreenObject2) {
			obj.move();
			if (obj instanceof Liquor) {
				obj.outOfReached(player2);
			}
		}

	}

	private TargetObject getObject1() {
		TargetObject obj1 = null;
		for (TargetObject target : onScreenObject1) {
			if (player1.isSamePosition(target)) {
				obj1 = target;
			}
		}
		
		return obj1;
	}
	
	private TargetObject getObject2() {
		TargetObject obj2 = null;
		for (TargetObject target : onScreenObject2) {
			if (player2.isSamePosition(target)) {
				obj2 = target;
			}
		}

		return obj2;
	}

	public synchronized void onExit() {
		// TODO Auto-generated method stub
		onScreenObject1.clear();
		onScreenObject2.clear();
		readyToRender = false;
	}

	private void createTarget() {
		if (nextObjectCreationDelay1 > 0) {
			nextObjectCreationDelay1 -= 1;
		} else {
			nextObjectCreationDelay1 = RandomUtility.random(GameProperties.objectCreationMinDelay,
					GameProperties.objectCreationMaxDelay);
			int targetType1 = RandomUtility.random(0, 1);
			int targetPosition1 = RandomUtility.random(0, 1);

			if (targetType1 == 0) {
				onScreenObject1.add(new Liquor(targetPosition1));
			} else {
				onScreenObject1.add(new Waste(targetPosition1));
			}
		}
		
		if (nextObjectCreationDelay2 > 0) {
			nextObjectCreationDelay2 -= 1;
		} else {
			nextObjectCreationDelay2 = RandomUtility.random(GameProperties.objectCreationMinDelay,
					GameProperties.objectCreationMaxDelay);
			int targetType2 = RandomUtility.random(0, 1);
			int targetPosition2 = RandomUtility.random(2, 3);

			if (targetType2 == 0) {
				onScreenObject2.add(new Liquor(targetPosition2));
			} else {
				onScreenObject2.add(new Waste(targetPosition2));
			}
		}
	}

	public synchronized void setIRenderableHolderList() {
		RenderableHolder.getInstance().getEntities().clear();
		if (readyToRender) {
			for (TargetObject object : onScreenObject1) {
				RenderableHolder.getInstance().add((IRenderableObject) object);
			}
			for (TargetObject object : onScreenObject2) {
				RenderableHolder.getInstance().add((IRenderableObject) object);
			}
			RenderableHolder.getInstance().add(player1);
			RenderableHolder.getInstance().add(player2);
		}
	}

	public boolean isReadyToRender() {
		return readyToRender;
	}
}
