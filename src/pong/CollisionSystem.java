package pong;

public class CollisionSystem {
	EntityManager entityManager;
	CollisionSystem(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	void checkCollisions() {
		
		for (int i = 0; i < entityManager.getEntities().size(); i++) {
			PongComponent currentEntity = entityManager.getEntityByIndex(i);
			for (int j = i+1; j < entityManager.getEntities().size(); j++) {
				PongComponent checkEntity = entityManager.getEntityByIndex(j);

				double halfCurrentY = currentEntity.size.y / 2;
				double halfCurrentX = currentEntity.size.x / 2;
				double currentLeft = currentEntity.position.x - halfCurrentX;
				double currentRight = currentEntity.position.x + halfCurrentX;
				double currentTop = currentEntity.position.y - halfCurrentY;
				double currentBottom = currentEntity.position.y + halfCurrentY;

				double halfCheckY = checkEntity.size.y / 2;
				double halfCheckX = checkEntity.size.x / 2;
				double checkLeft = checkEntity.position.x - halfCheckX;
				double checkRight = checkEntity.position.x + halfCheckX;
				double checkTop = checkEntity.position.y - halfCheckY;
				double checkBottom = checkEntity.position.y + halfCheckY;

				double overlapLeft = currentRight - checkLeft;
				double overlapRight = checkRight - currentLeft;
				double overlapTop = currentBottom - checkTop;
				double overlapBottom = checkBottom - currentTop;
				
				if (currentRight >= checkLeft &&
						checkRight >= currentLeft &&
						currentBottom >= checkTop &&
						checkBottom >= currentTop) {
					double minOverlapX = Math.min(overlapLeft, overlapRight);
					double minOverlapY = Math.min(overlapTop, overlapBottom);

					
					if (minOverlapX < minOverlapY) {
						  if (overlapLeft < overlapRight) {
						    currentEntity.position.x -= overlapLeft;
						  } else {
						    currentEntity.position.x += overlapRight;
						  }
						  currentEntity.velocity.x = -currentEntity.velocity.x;
						} else {
						  if (overlapTop < overlapBottom) {
						    currentEntity.position.y -= overlapTop;
						  } else {
						    currentEntity.position.y += overlapBottom;
						  }
						  currentEntity.velocity.y = -currentEntity.velocity.y;
						}
				}

			}

		}
	}
	
	
}
