package application;

import java.util.*;

public class Stacks {
	LinkedList<Card> cache = new LinkedList<Card>();
	LinkedList<Card>[] plateau;
	LinkedList<Card> deck = new LinkedList<Card>();
	LinkedList<Card> temp = new LinkedList<Card>();

	public Stacks(DeckOfCards dc) {
		iniPlateau();
		addCardToStacks(dc);
	}

	// Initialize Plateau
	@SuppressWarnings("unchecked")
	public void iniPlateau() {
		plateau = new LinkedList[7];
		for (int a = 0; a < 7; a++) {
			plateau[a] = new LinkedList<Card>();
		}
	}

	// Adds cards to all the different stacks
	public void addCardToStacks(DeckOfCards dc) {
		int increment = 0;
		for (int a = 0; a < 7; a++)
			for (int b = 0; b <= a; b++) {
				plateau[a].add(dc.Cards[increment]);
				increment++;
			}
		for (int a = 28; a < 52; a++)
			deck.add(dc.Cards[a]);
	}
}