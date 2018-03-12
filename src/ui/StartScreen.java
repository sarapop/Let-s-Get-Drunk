package ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lib.GameProperties;
import lib.DrawingUtility;
import main.Main;

public class StartScreen extends Pane {
	
	private Canvas canvas;
	private Button start;

	public StartScreen() {
		super();
		// TODO Auto-generated constructor stub
		this.canvas = new Canvas(GameProperties.screenWidth, GameProperties.screenHeight);
		this.getChildren().add(this.canvas);
		this.paintComponenet();   
		start = new Button();
		start.setStyle("-fx-background-image: url('img/startbutton.png')");
		start.setPrefSize(133, 63);
        

        this.getChildren().add(start);
        start.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Main.instance.changeSceneTo("tutorialScene");;
			}
		});
        start.setLayoutX(GameProperties.screenWidth/2 - 133/2);
        start.setLayoutY(425);
        
        Text developer = new Text("developed by PoP and McDonan");
        developer.setFont(Font.loadFont("file:res/font.ttf", 10));
        developer.setFill(Color.WHITE);
        developer.setLayoutX(GameProperties.screenWidth/2 - 90);
        developer.setLayoutY(525);
        this.getChildren().add(developer);
	}

	public void applyResize() {
		// TODO Auto-generated method stub
		this.canvas.setWidth(GameProperties.screenWidth);
		this.canvas.setHeight(GameProperties.screenHeight);
	}

	public void paintComponenet() {
		// TODO Auto-generated method stub
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		DrawingUtility.drawObject(gc, 0, 0, "startscreen");
	}

}
