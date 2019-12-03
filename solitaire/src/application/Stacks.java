package application;

import java.util.*;

public class Stacks {

	// Each stack is made as a LinkedList
	LinkedList<Card>[] cache;
	LinkedList<Card>[] plateau;
	LinkedList<Card> deck = new LinkedList<Card>();
	LinkedList<Card> temp = new LinkedList<Card>();

	// Constructor takes a deck of cards to configure all the stacks
	public Stacks(DeckOfCards dc) {
		iniPlateau();
		iniCache();
		addCardToStacks(dc);
	}

	// Initialize Plateau
	@SuppressWarnings("unchecked")
	public void iniPlateau() {
		plateau = new LinkedList[7];
		for (int a = 0; a < 7; a++) {
			Card placeHolder = new Card();
			plateau[a] = new LinkedList<Card>();
			plateau[a].add(placeHolder);
		}
	}

	// Initialize Cache
	@SuppressWarnings("unchecked")
	public void iniCache() {
		cache = new LinkedList[4];
		for (int a = 0; a < 4; a++) {
			cache[a] = new LinkedList<Card>();
			Card placeHolder = new Card();
			placeHolder.isCache = true;
			cache[a].add(placeHolder);
		}
	}

	// Adds cards to all the different stacks
	public void addCardToStacks(DeckOfCards dc) {
		int increment = 0;
		// Add the correct amount of cards to each of the stacks that make up the
		// plateau
		for (int a = 0; a < 7; a++)
			for (int b = 1; b <= a + 1; b++) {
				plateau[a].add(dc.Cards[increment]);
				increment++;
			}
		// Adds 24 cards to the deck
		for (int a = 28; a < 52; a++) {
			dc.Cards[a].isDeck = true;
			deck.add(dc.Cards[a]);
		}
	}
}