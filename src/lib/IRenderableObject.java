package lib;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderableObject {
	public void render(GraphicsContext gc);
	public boolean exist();
}
