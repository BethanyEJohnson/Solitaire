//Initializes a deck of 52 unique card objects
public class DeckOfCards {
	
	Card[] Cards = new Card[52];
	String[] suit = {"Heart","Spade","Diamond","Club"};
	String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	
	//initializes a deck of 52 unique card objects
	public void newDeck() {
		for(int i = 0; i < suit.length; i++) {
			for(int j=0; j < rank.length; j++) {
				Card c = new Card(suit[i],rank[j]);
				Cards[j+13*i] = c;
			} 
		}
	}
}
