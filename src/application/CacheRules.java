package application;

import java.util.LinkedList;

//This class defines rules for moving cards within the cache section of the game
public class CacheRules extends CardRules{
	
	//method assumes card is attempting to go into the cache, false will snap the card back to it's original position
		public boolean validMove(Card orig, Card top) {
			if (compareCards(orig, top) == "Same Suit Next Rank Up") {
				return true;
			} else if (orig.rank.equals("A") && top == null){
				return true;
			}else
				return false;	
		}
		
		public LinkedList<Card> addCard(Card card, LinkedList<Card> stack){
			stack.addLast(card);
			card.section = "cache";
			return stack;	
		} 
		
		public LinkedList<Card> removeCard(Card card, LinkedList<Card> stack){
			stack.remove(card);
			return stack;	
		}
	
}
