
public class SolitaireTester {
	
	public static void main(String[] args) {
		DeckOfCards myDeck = new DeckOfCards();
		myDeck.newDeck();
		//myDeck.ShuffleDeck();
		
		for(int i = 0; i < myDeck.Cards.length; i++) {
			System.out.println(myDeck.Cards[i].rank + ""+ myDeck.Cards[i].suit + " ");	
		}

	}

}
