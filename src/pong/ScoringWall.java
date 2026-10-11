package pong;

public class ScoringWall extends PongComponent{
	public String side;
	ScoringWall(Vector2 size, Vector2 position, Vector2 initialVelocity, String side) {
		super(size, position, initialVelocity);
		this.type = null;
		this.side = side;
	}
}
