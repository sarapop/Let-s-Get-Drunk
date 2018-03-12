package ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import lib.GameProperties;
import lib.DrawingUtility;
import main.Main;

public class GameOverScreen extends Pane {

private Canvas canvas;
private Button restart;
private Button back;
	
	public GameOverScreen (){
		super();
		this.canvas = new Canvas(GameProperties.screenWidth, GameProperties.screenHeight);
		this.getChildren().add(this.canvas);
		this.paintComponenet();   
		restart = new Button();
		restart.setStyle("-fx-background-image: url('img/restartbutton.png')");
		restart.setPrefSize(200, 63);
		back = new Button();
		back.setStyle("-fx-background-image: url('img/returnbutton.png')");
		back.setPrefSize(133, 63);
        

        this.getChildren().add(restart);
        this.getChildren().add(back);
        restart.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Main.instance.changeSceneTo("gameScene");
			}
		});
        restart.setLayoutX(GameProperties.screenWidth/2 - 200/2);
        restart.setLayoutY(420);
        
        back.setOnMouseClicked(new EventHandler<Event>() {

     			@Override
     			public void handle(Event event) {
     				// TODO Auto-generated method stub
     				Main.instance.changeSceneTo("startScene");
     			}
     		});
        back.setLayoutX(GameProperties.screenWidth/2 - 133/2);
        back.setLayoutY(505);
	}
	
	
	public void paintComponenet(){
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		DrawingUtility.drawObject(gc, 0, 0, "overbg");
	}

	
	public void applyResize(){
		this.canvas.setWidth(GameProperties.screenWidth);
		this.canvas.setHeight(GameProperties.screenHeight);
	}
}
