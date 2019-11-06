import java.awt.*;
import javax.swing.JPanel;

public class GameScreen extends JPanel {

	public Deck deck;

	//Distance modifier for setting the stack distance apart on the x axis
	public static final int X_mod = 50;

	// Position of the tableau cards on the y axis
	public static final int TABLEAU_PILE_Y = 240;

	// Position of the Foundation cards on the y axis
	public static final int FOUNDATIONS_PILE_Y = 20;

	// X distance between each pile of cards
	public static int[] X_location;

	//Declaring Each Pile type
	public Pile[] tableauPiles, foundationPiles;
	public Pile deckPile, selectedPile;

	//Constructor does initialization, and sets up muse listeners
	public GameScreen() {
		IniXDistance();
		setBackground(new Color(0, 200, 0));
		PilesAndDeckIni();
		arrangeCards(deck);
		deckPile = new Pile(deck.getX() + Card.WIDTH + GameScreen.X_mod, deck.getY(), Pile.DECK_PILE);
		selectedPile = null;
		CardListener l = new CardListener(this);
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
		this.setFocusable(true);
	}

	//Initializes the x distances for card piles
	public void IniXDistance() {
		X_location = new int[7];
		for (int a = 0; a < X_location.length; a++) {
			X_location[a] = (X_mod * (a + 1)) + (Card.WIDTH * a);
		}
	}
	
	//Initialize Piles and deck
	public void PilesAndDeckIni() {
		deck = new Deck();
		tableauPiles = new Pile[7];
		foundationPiles = new Pile[4];
	}


	//----------------------------------------------------------------------
	/**
	 * Paints the screen on a graphics context
	 * 
	 * @param g the graphics context to paint on
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw all piles and the remaining cards left in the deck
		for (int i = 0; i < tableauPiles.length; i++) {
			tableauPiles[i].draw(g);
		}
		for (int i = 0; i < foundationPiles.length; i++) {
			foundationPiles[i].draw(g);
		}
		deckPile.draw(g);
		deck.draw(g);

		if (selectedPile != null) {
			selectedPile.draw(g);
		}
	}


	//This method arranges the cards in the window
	public void arrangeCards(Deck d) {
		int cardNum = 0;
		//Arranges the tableau
		for (int i = 0; i < tableauPiles.length; i++) {
			tableauPiles[i] = new Pile(X_location[i], TABLEAU_PILE_Y, Pile.MAIN_PILE);
			for (int j = 0; j <= i; j++) {
				tableauPiles[i].addToPile(d.getCardAt(cardNum));
				if (j == i)
					d.getCardAt(cardNum).faceDown = false;
				d.removeCardAt(cardNum);
			}
		}
		//Arranges the foundation piles
		for (int i = 0; i < 4; i++) {
			foundationPiles[i] = new Pile(X_location[i + 3], FOUNDATIONS_PILE_Y, Pile.SUIT_PILE);
		}
		
		//Puts the deck of cards in the desired location
		deck.setLocation(X_mod, FOUNDATIONS_PILE_Y);
	}
}