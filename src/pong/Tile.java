package pong;

public class Tile extends PongComponent {
//	double movement = 0;
	public Vector2 velocity = new Vector2();

	Tile(Vector2 size, Vector2 position, Vector2 initialVelocity, String id) {
		super(size, position, initialVelocity);
		this.type = "rect";
		this.id = id;
	}
	public void update(double dt) {
//		if ((position.y > size.y /2) && (position.y  + movement > size.y /2) &&
//			(position.y < GamePanel.screenY - size.y /2) && (position.y + movement < GamePanel.screenY - size.y /2)) {
//			this.position.y += movement;
//		}
		previousPosition = position;
		position = position.add(velocity.multiply(dt));
		
	}
}
