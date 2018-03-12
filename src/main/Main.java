package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.GameOverScreen;
import ui.GameScreen;
import ui.StartScreen;
import ui.TutorialScreen;
import javafx.stage.WindowEvent;
import lib.AudioUtility;
import lib.GameLoopUtility;
import lib.TotalScore;
import logic.MainLogic;
import javafx.event.EventHandler;

public class Main extends Application {
	
	public static Main instance;
	private Stage primaryStage;
	private Scene startScene;
	private Scene tutorialScene;
	private Scene overScene;
	private Scene gameScene;
	private GameScreen gameScreen;
	private GameOverScreen overScreen;
	private TutorialScreen tutorialScreen;
	private StartScreen startScreen;
	private MainLogic gameLogic;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Let's Get Drunk");
		this.primaryStage.getIcons().add(new Image(ClassLoader.getSystemResource("img/icon.png").toString()));
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){

			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		
		this.gameLogic = new MainLogic();
		
		this.startScreen = new StartScreen();
		this.startScene = new Scene(startScreen);
		
		this.tutorialScreen = new TutorialScreen();
		this.tutorialScene = new Scene(tutorialScreen);
		
		this.gameScreen = new GameScreen(gameLogic);
		this.gameScene = new Scene(gameScreen);
		
		this.overScreen = new GameOverScreen();
		this.overScene = new Scene(overScreen);
		
		gameScreen.requestFocusForCanvas();
		this.primaryStage.setScene(this.startScene);
		this.resizeStage();
		this.primaryStage.show();
		AudioUtility.playSound("start");
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public synchronized void changeSceneTo(String scene) {
		if (scene.equals("gameScene")) {
			TotalScore.instance.resetTotalScore();
			this.gameLogic.onStart();
			GameLoopUtility.runGameLoop(gameLogic);
			this.primaryStage.setScene(gameScene);
			AudioUtility.stopSound("start");
			AudioUtility.playSound("game");
		} else if (scene.equals("overScene")) {
			this.gameLogic.onExit();
			this.primaryStage.setScene(overScene);
			AudioUtility.stopSound("game");
		} else if (scene.equals("startScene")) {
			this.primaryStage.setScene(startScene);
			AudioUtility.playSound("start");
		} else if (scene.equals("tutorialScene")) {
			this.primaryStage.setScene(tutorialScene);
		}
	}
	
	public void resizeStage() {
		this.startScreen.applyResize();
		this.gameScreen.applyResize();
		this.overScreen.applyResize();
		this.primaryStage.sizeToScene();
	}
	
	public void drawGameScreen() {
		this.gameScreen.paintComponenet();
	}
}

