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
}
