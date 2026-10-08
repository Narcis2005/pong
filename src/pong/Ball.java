package pong;

public class Ball extends PongComponent {
	
	Ball(Vector2 size, Vector2 position, Vector2 initialVelocity) {
		super(size, position, initialVelocity);
		this.type = "oval";
	}
	public void update(double dt) {
		previousPosition = position;
		position = position.add(velocity.multiply(dt));
	}
	public void collided(PongComponent collidedComponent) {
		double[] overlaps = CollisionSystem.getOverlaps(this, collidedComponent);

		double overlapLeft = overlaps[0];
		double overlapRight = overlaps[1];
		double overlapTop = overlaps[2];
		double overlapBottom = overlaps[3];

		
		if(collidedComponent instanceof Wall) {
			if (overlapTop < overlapBottom) {
			  position.y -= overlapTop;
			}
			else {
			  position.y += overlapBottom;
			}
			velocity.y = -velocity.y;
			
		}
		else if (collidedComponent instanceof Tile) {
			double minOverlapX = Math.min(overlapLeft, overlapRight);
			double minOverlapY = Math.min(overlapTop, overlapBottom);
	
			if (minOverlapX < minOverlapY) {
			    if (overlapLeft < overlapRight) {
			      position.x -= overlapLeft;
			    }
			    else {
			      position.x += overlapRight;
			    }
				double speed = velocity.speed();
				double maxAngle = Math.PI / 3;
				double normalizedOffset = (position.y - collidedComponent.position.y) / (collidedComponent.size.y /2);
			    double newVx = -velocity.x;
			    double maxVerticalPush = speed * Math.sin(maxAngle);
			    double newVy = velocity.y + (normalizedOffset * maxVerticalPush);
			    float currentLength = (float) Math.sqrt(newVx * newVx + newVy * newVy);
			    velocity.x = (newVx / currentLength) * speed;
			    velocity.y = (newVy / currentLength) * speed;
			}
			
			
			else {
			  if (overlapTop < overlapBottom) {
			    position.y -= overlapTop;
				  if (overlapLeft < overlapRight) {
					    position.x -= overlapLeft;
					  } else {
					    position.x += overlapRight;
					  }
			  } else {
			    position.y += overlapBottom;
				  if (overlapLeft < overlapRight) {
					    position.x -= overlapLeft;
					  } else {
					    position.x += overlapRight;
					  }
			  }
			  velocity.y = -velocity.y;
			  velocity.x = -velocity.x;
			}
		}
	}
}
