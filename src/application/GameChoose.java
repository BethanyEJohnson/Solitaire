package application;

public class GameChoose {
	Game g;

	public void soltaireGame() {
		g = new SolitaireStart();
	}
	
	public void blackJackGame() {
		g = new BlackJackStart();
	}
	
}