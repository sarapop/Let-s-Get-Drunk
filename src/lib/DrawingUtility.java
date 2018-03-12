package lib;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DrawingUtility {

	protected static final Font font = Font.loadFont("file:res/font.ttf", 25);
	protected static final Image bg = getImage("img/bg.jpg");
	protected static final Image overbg = getImage("img/overbg.png");
	protected static final Image startscreen = getImage("img/startscreen.jpg");
	protected static final Image tutorialscreen = getImage("img/tutorialscreen.jpg");
	protected static final Image drunkard1 = getImage("img/drunkard1.gif");
	protected static final Image drunkard2 = getImage("img/drunkard2.gif");
	protected static final Image liquor = getImage("img/liquor.png");
	protected static final Image waste = getImage("img/waste.png");
	protected static final Image resume = getImage("img/resume.png");

	private static Image getImage(String directory) {
		try {
			return new Image(ClassLoader.getSystemResource(directory).toString());
		} catch (Exception e) {
			return null;
		}
	}

	public static void drawObject(GraphicsContext gc, double x, double y, String name) {
		switch (name) {
		case "drunkard1":
			gc.drawImage(drunkard1, x, y);
			break;
		case "drunkard2":
			gc.drawImage(drunkard2, x, y);
			break;
		case "liquor":
			gc.drawImage(liquor, x, y);
			break;
		case "waste":
			gc.drawImage(waste, x, y);
			break;
		case "startscreen":
			gc.drawImage(startscreen, x, y);
			break;
		case "bg":
			gc.drawImage(bg, x, y);
			break;
		case "overbg":
			gc.drawImage(overbg, x, y);
			break;
		case "tutorialscreen":
			gc.drawImage(tutorialscreen, x, y);
			break;
		}
	}

	public static void drawStatus(GraphicsContext gc, int score, boolean pause) {
		gc.setFont(font);
		gc.setFill(Color.WHITE);
		gc.fillText("" + score, GameProperties.screenWidth/8 + GameProperties.screenWidth/4 + GameProperties.screenWidth/2, font.getSize()*2.5);
		
		if (pause) {
			FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
			double font_width = fontLoader.computeStringWidth("spacebar to resume", gc.getFont());
			double font_height = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
			gc.setFill(Color.BLACK);
			gc.fillText("spacebar to resume", (GameProperties.screenWidth-font_width)/2 , (GameProperties.screenHeight-font_height)/2 + 25);	
		}
	}
}
