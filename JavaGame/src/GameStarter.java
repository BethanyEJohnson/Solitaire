//https://bicyclecards.com/how-to-play/solitaire/
import javax.swing.*;

public class GameStarter {
	private static JFrame jf;
	private static GameScreen game;

	public static void main(String[] args) {
		loadGame();
		game.repaint();
	}

	private static void loadGame() {
		jf = new JFrame("Solitaire");
		game = new GameScreen();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1000, 800);
		jf.setLocationRelativeTo(null);
		jf.add(game);
		jf.setVisible(true);
	}
}