//sorts out card positions for a new game
/*For Shingi: This class isn't complete just yet but you can either encapsulate this into singleton pattern somehow 
or use template pattern on these. Which ever you think is best. */
public class newGame {
	
	//52 unique cards
	DeckOfCards deckOfCards;
	
	//deck & discard piles in upper left
	Deck deck;
	
	//four cache slots in upper right
	Cache[] cache = new Cache[4];
	
	//seven bottom stacks
	Stack[] stack = new Stack[7];
	
	//shuffles cards
	public void ShuffleDeck() {
		for(int i = 0; i < deckOfCards.Cards.length; i++) {
			int index = (int)(Math.random() * deckOfCards.Cards.length);
			Card temp = deckOfCards.Cards[i];
			deckOfCards.Cards[i] = deckOfCards.Cards[index];
			deckOfCards.Cards[index] = temp;	
		}
	}
	
	//sets all cards' state to turned over
	public void TurnOver() {
		for(int i = 0; i < deckOfCards.Cards.length; i++) {
			deckOfCards.Cards[i].state = "facedown";	
		}
	}
	
	//assigns cards to initial deck and stack positions
	public void DealDeck(DeckOfCards d) {
		//24 cards go to the deck
		//1-7 cards to the 1st through 7th stacks
	}
}
