package pong;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
    // panel setup
	final static int screenX = 800;
	final static int screenY = 600;
	final static Vector2 screenCenter = new Vector2(screenX/2, screenY/2);
	final static Vector2 unitOfSize = new Vector2(16, 16);
	final static int FPS = 60;

	// game loop setup
	Thread gameThread;
	long lastTime = System.nanoTime();
	
	// elements setup
	EntityManager entityManager = new EntityManager();
	
	CollisionSystem collisionSystem = new CollisionSystem(entityManager);

	Score score = new Score();
	
	Vector2 ballInitialVelocity = new Vector2(350, 0);

	GamePanel() {
		this.setPreferredSize(new Dimension(screenX, screenY));
		this.addKeyListener(new KeyListenerPong(entityManager));

		entityManager.add( new Ball(unitOfSize, screenCenter, new Vector2(ballInitialVelocity.x, ballInitialVelocity.y), "ball"));

		Vector2 tileSize = new Vector2(unitOfSize.x / 2, unitOfSize.y * 5);
		entityManager.add(new Tile(tileSize, new Vector2(unitOfSize.x, screenCenter.y), new Vector2(0, 0), "leftTile"));
		entityManager.add(new Tile(tileSize, new Vector2(screenX - unitOfSize.x, screenCenter.y), new Vector2(0, 0), "rightTile"));
		
		Vector2 wallSize = new Vector2(screenX, unitOfSize.y);
		entityManager.add(new Wall(wallSize, new Vector2(screenX /2, 0), new Vector2(0, 0)));
		entityManager.add(new Wall(wallSize, new Vector2(screenX /2, screenY - 1), new Vector2(0, 0)));
		
		entityManager.add(new ScoringWall(new Vector2(unitOfSize.x, screenY), new Vector2(-unitOfSize.x, screenY/2), new Vector2(0, 0), "left"));
		entityManager.add(new ScoringWall(new Vector2(unitOfSize.x, screenY), new Vector2(screenX + unitOfSize.x, screenY/2), new Vector2(0, 0), "right"));
	}

	@Override
	public void run() {
		while(gameThread != null) {
			long currentTime = System.nanoTime();

			if (currentTime - lastTime >= 1000000000 / FPS) {
				update((double) 1/FPS);
				repaint();
				lastTime = currentTime;	
			}

		}
	}
	public void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for(PongComponent entity : entityManager.getEntities()){
			if (entity.type == null) continue;
			if (entity.type.equals("oval")) {
				g2d.fillOval((int)entity.position.x - (int)entity.size.x / 2, (int) entity.position.y - (int) entity.size.y / 2, (int) entity.size.x, (int) entity.size.y);
			}
			else if(entity.type.equals("rect")) {
				g2d.fillRect((int)entity.position.x - (int)entity.size.x / 2, (int) entity.position.y - (int)entity.size.y / 2, (int)entity.size.x, (int)entity.size.y);
			}
		}

		Toolkit.getDefaultToolkit().sync();
	}
	public void update(double dt) {
		for(Events event : EventQueue.events ) {
			if (event == Events.PLAYER_LEFT_SCORE) {
				score.leftPlayerScore++;
			}
			else if (event == Events.PLAYER_RIGHT_SCORE) {
				score.rightPlayerScore++;
			}
			else if (event == Events.RESET_GAME) {
				Ball localBall = (Ball) entityManager.getEntity("ball");
				localBall.velocity = new Vector2(ballInitialVelocity.x, ballInitialVelocity.y);
				localBall.position = screenCenter;
			}
		}
		EventQueue.events.clear();

		for(PongComponent entity : entityManager.getEntities()){
			entity.update(dt);
		}
		
		collisionSystem.checkCollisions();
		//Handle collisions
		
	}
}
