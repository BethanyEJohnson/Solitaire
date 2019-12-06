package application;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class DeckOfCards {

	private static DeckOfCards dc;
	Card[] Cards = new Card[52];
	String[] suit = { "H", "S", "D", "C" };
	String[] rank = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	// Constructor for DeckOfCards
	public DeckOfCards() {
		getCards();
	}
	
	public static DeckOfCards getInstance() {
		if(dc == null) {
			dc = new DeckOfCards();
		}
		cleanCards(dc.Cards);
		return dc;
	}
	
	// Reset boolean values on all cards
	public static void cleanCards(Card[] C) {
		for (int a = 0; a < 52; a++) {
			C[a].hasChildren = false;
			C[a].isFaceUp = false;
			C[a].isCache = false;
			C[a].isDeck = false;
		}
	}

	// initializes a deck of 52 unique card objects
	public void getCards() {
		for (int i = 0; i < suit.length; i++) {
			for (int j = 0; j < rank.length; j++) {
				Card c = new Card(suit[i], rank[j], new SolitaireRank(), new SolitaireSuit());
				Cards[j + 13 * i] = c;
			}
		}
	}

	// shuffles the deck of cards
	public Card[] shuffle(Card[] C) {
		List<Card> list = Arrays.asList(C);
		Collections.shuffle(list);
		return (Card[]) list.toArray();
	}
}
