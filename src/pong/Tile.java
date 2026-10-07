package pong;

public class Tile extends PongComponent {
	public Vector2 velocity = new Vector2();

	Tile(Vector2 size, Vector2 position, Vector2 initialVelocity, String id) {
		super(size, position, initialVelocity);
		this.type = "rect";
		this.id = id;
	}
	public void update(double dt) {
		previousPosition = position;
		position = position.add(velocity.multiply(dt));
	}
	public void collided(PongComponent collidedComponent) {
		double[] overlaps = CollisionSystem.getOverlaps(this, collidedComponent);

		double overlapTop = overlaps[2];
		double overlapBottom = overlaps[3];

		if(collidedComponent instanceof Wall) {
			 if (overlapTop < overlapBottom) {
			   position.y -= overlapTop;
			 }
			 else {
			   position.y += overlapBottom;
			 }
			 velocity.y = 0;
		}
	}
}
