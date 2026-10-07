package pong;

public class CollisionSystem {
	EntityManager entityManager;
	CollisionSystem(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void checkCollisions() {
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
				
				if (currentRight >= checkLeft &&
						checkRight >= currentLeft &&
						currentBottom >= checkTop &&
						checkBottom >= currentTop) {
					currentEntity.collided(checkEntity);
					checkEntity.collided(currentEntity);
				}
			}
		}
	}

	static double[] getOverlaps(PongComponent componentA, PongComponent componentB) {
		double halfCurrentY = componentA.size.y / 2;
		double halfCurrentX = componentA.size.x / 2;
		double currentLeft = componentA.position.x - halfCurrentX;
		double currentRight = componentA.position.x + halfCurrentX;
		double currentTop = componentA.position.y - halfCurrentY;
		double currentBottom = componentA.position.y + halfCurrentY;

		double halfCheckY = componentB.size.y / 2;
		double halfCheckX = componentB.size.x / 2;
		double checkLeft = componentB.position.x - halfCheckX;
		double checkRight = componentB.position.x + halfCheckX;
		double checkTop = componentB.position.y - halfCheckY;
		double checkBottom = componentB.position.y + halfCheckY;

		double overlapLeft = currentRight - checkLeft;
		double overlapRight = checkRight - currentLeft;
		double overlapTop = currentBottom - checkTop;
		double overlapBottom = checkBottom - currentTop;
		
		return new double[] {overlapLeft, overlapRight, overlapTop, overlapBottom};
	}
	
}
