package application;

import java.util.LinkedList;

//This class defines game constraints in the deck section of the game
public class DeckRules extends CardRules {
	
	//method assumes card is attempting to go into the deck, false will snap the card back to it's original position
	public boolean validMove(Card orig, Card top) {
		return false;
	}
	
	public LinkedList<Card> removeCard(Card card, LinkedList<Card> stack){
		return stack;	
	}

}
