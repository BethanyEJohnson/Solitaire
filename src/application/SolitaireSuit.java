package application;

public class SolitaireSuit implements SuitBehavior {
	public boolean cacheSuit(Card orig, Card top) {
		if (orig.suit == top.suit || top.suit == null)
			return true;
		else
			return false;
	}
	
	public boolean plateauSuit(Card orig, Card top) {
		if ((orig.suit == "H" || orig.suit == "D") && (top.suit == "S" || top.suit == "C"))
			return true;
		if ((orig.suit == "S" || orig.suit == "C") && (top.suit == "H" || top.suit == "D"))
			return true;
		return false;
	}

}