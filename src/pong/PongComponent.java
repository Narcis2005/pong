package pong;

public abstract class PongComponent {
	public Vector2 size = new Vector2();
	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2();
	public Vector2 previousPosition = new Vector2();

	public String type;
	public String id;
	
	PongComponent(Vector2 size, Vector2 position, Vector2 initialVelocity) {
		this.size = size;
		this.position = position;
		this.velocity = initialVelocity;
	}
	public void update(double dt) {}
	public void collided(PongComponent collidedComponent) {}
}
