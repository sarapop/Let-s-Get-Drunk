package ui;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import lib.GameProperties;
import lib.DrawingUtility;
import lib.RenderableHolder;
import lib.IRenderableObject;
import lib.InputUtility;
import logic.MainLogic;

public class GameScreen extends StackPane{
	private MainLogic mainLogic;
	private Canvas canvas;
	
	public GameScreen(MainLogic mainLogic){
		super();
		this.canvas = new Canvas(GameProperties.screenWidth, GameProperties.screenHeight);
		this.mainLogic = mainLogic;
		this.getChildren().add(this.canvas);
		this.paintComponenet();
		addListener();
	}
	
	public void paintComponenet(){
		((MainLogic) mainLogic).setIRenderableHolderList();
		
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		DrawingUtility.drawObject(gc, 0, 0, "bg");
		for(IRenderableObject renderable : RenderableHolder.getInstance().getEntities()){
			renderable.render(gc);
		}
	}
	
	public void requestFocusForCanvas(){
		this.requestFocus();
	}
	
	public void applyResize(){
		this.canvas.setWidth(GameProperties.screenWidth);
		this.canvas.setHeight(GameProperties.screenHeight);
	}

	private void addListener() {
		
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (!InputUtility.getKeyPressed(event.getCode()))
					InputUtility.setKeyTriggered(event.getCode(), true);
				InputUtility.setKeyPressed(event.getCode(), true);
			}
		});

		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				InputUtility.setKeyPressed(event.getCode(), false);
			}
		});
	}
}
