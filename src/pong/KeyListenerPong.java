package pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListenerPong extends KeyAdapter {
	EntityManager entityManager;
	static final double incrementValue = 300;
	KeyListenerPong(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
    @Override
    public void keyPressed(KeyEvent event) {
    	Tile rightTile = (Tile) entityManager.getEntity("rightTile");
        int keyCode = event.getKeyCode();
        // need to find a way to handle this movement in the CollisionSystem
        // try to keep it like this, and the collisionSystem to somehow prevent clipping
        // first the movement will run, then the collisionSystem will do it's magic and only after that the render will update
        if (keyCode == KeyEvent.VK_UP)
        {
        	rightTile.velocity = new Vector2(0, incrementValue * -1);

        }
        if (keyCode == KeyEvent.VK_DOWN)
        {
        	rightTile.velocity = new Vector2(0, incrementValue);

        	
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
    	Tile rightTile = (Tile) entityManager.getEntity("rightTile");

        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.VK_UP)
        {
        	rightTile.velocity = new Vector2(0, 0);

        }
        if (keyCode == KeyEvent.VK_DOWN)
        {
        	rightTile.velocity = new Vector2(0, 0 );

        }
    }
}
