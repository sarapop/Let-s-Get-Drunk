package lib;

import java.util.ArrayList;
import java.util.List;

public class RenderableHolder {
	
	private static final RenderableHolder instance = new RenderableHolder();
	
	private List<IRenderableObject> entities;
	
	public RenderableHolder() {
		entities = new ArrayList<IRenderableObject>();
	}
	
	public static RenderableHolder getInstance() {
		return instance;
	}
	
	public void addAndSort(IRenderableObject entity) {
		add(entity);
	}
	
	public void add(IRenderableObject entity) {
		entities.add(entity);
	}
	
	public List<IRenderableObject> getEntities() {
		return entities;
	}
}