package application;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


//This class creates a full deck of 52 unique card objects
public class FullDeck {
	private static FullDeck fd;
	Card[] cards = new Card[52];
	String[] suit = { "H", "S", "D", "C" };
	String[] rank = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	
	
	// Constructor for FullDeck
		public FullDeck() {
			newDeck();
		}
		
		public static FullDeck getInstance() {
			if(fd == null) {
				fd = new FullDeck();
			}
			return fd;
		}
	
	// initialize a new deck
		public void newDeck() {
			for (int i = 0; i < suit.length; i++) {
				for (int j = 0; j < rank.length; j++) {
					Card c = new Card(suit[i], rank[j]);
					cards[j + 13 * i] = c;
				}
			}
			shuffleDeck();
		}

	//shuffle the deck of cards
		public Card[] shuffleDeck() {
			List<Card> list = Arrays.asList(cards);
			Collections.shuffle(list);
			return (Card[]) list.toArray();
		}
}
