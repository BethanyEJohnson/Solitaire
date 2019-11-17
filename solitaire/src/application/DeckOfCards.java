package application;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {

	Card[] Cards = new Card[52];
	String[] suit = { "H", "S", "D", "C" };
	String[] rank = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	// Constructor for DeckOfCards
	public DeckOfCards() {
		newDeck();
		shuffle(Cards);
	}

	// initializes a deck of 52 unique card objects
	public void newDeck() {
		for (int i = 0; i < suit.length; i++) {
			for (int j = 0; j < rank.length; j++) {
				Card c = new Card(suit[i], rank[j]);
				Cards[j + 13 * i] = c;
			}
		}
		shuffle(Cards);
	}

	// shuffles the deck of cards
	public Card[] shuffle(Card[] cardDeck) {
		List<Card> list = Arrays.asList(cardDeck);
		Collections.shuffle(list);
		return (Card[]) list.toArray();
	}
}
