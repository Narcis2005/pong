package pong;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
	private ArrayList<PongComponent> entities = new ArrayList<>();
	EntityManager() {}
	void add(PongComponent entity) {
		entities.add(entity);
	}
	List<PongComponent> getEntities() {
		return entities;
	}
	PongComponent getEntity(String id) {
		List<PongComponent> entityArray = entities.stream().filter((entity) -> entity.id == id).toList();
		if (entityArray.size() == 1) {
			return entityArray.get(0);
		}
		return null;
	}
	PongComponent getEntityByIndex(int id) {
		return entities.get(id);
	}

}
