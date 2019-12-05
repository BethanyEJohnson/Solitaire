package application;

public class CacheSuit implements SuitBehavior {
	public boolean suitRule(Card orig, Card top) {
		if (orig.suit == top.suit || top.suit == null)
			return true;
		else
			return false;
	}
}
