package pong;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class main {

	public static void main(String[] args) {
		final JFrame jFrame = new JFrame("Pong in java");
		final GamePanel gamePanel = new GamePanel();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setResizable(false);
		jFrame.add(gamePanel);
		gamePanel.setFocusable(true);
		gamePanel.startGameLoop();
		jFrame.pack();
	}

}
