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
		return dc;
	}

	// initializes a deck of 52 unique card objects
	public void getCards() {
		for (int i = 0; i < suit.length; i++) {
			for (int j = 0; j < rank.length; j++) {
				Card c = new Card(suit[i], rank[j]);
				Cards[j + 13 * i] = c;
			}
		}
		shuffle();
	}

	// shuffles the deck of cards
	public Card[] shuffle() {
		List<Card> list = Arrays.asList(Cards);
		Collections.shuffle(list);
		return (Card[]) list.toArray();
	}
}
