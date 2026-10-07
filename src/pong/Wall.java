package pong;

public class Wall extends PongComponent{
	Wall(Vector2 size, Vector2 position, Vector2 initialVelocity) {
		super(size, position, initialVelocity);
		this.type = "rect";
	}
	
}
