package application;

import java.util.LinkedList;

//This class defines rules for moving cards within the plateau section of the game
public class PlateauRules extends CardRules{
	
	//method assumes card is attempting to go into a stack in the plateau, false will snap the card back to it's original position
	public boolean validMove(Card orig, Card top) {
		if (compareCards(orig, top) == "Opposite Suit Next Rank Down") {
			return true;
		} else if (orig.rank.equals("K") && top == null) {
			return true;
		} else 
			return false;
	}
	
	public LinkedList<Card> addCard(Card card, LinkedList<Card> stack){
		stack.add(card);
		card.section = "plateau";
		return stack;	
	} 
	
	public LinkedList<Card> removeCard(Card card, LinkedList<Card> stack){
		stack.remove(card);
		return stack;	
	} 
}
