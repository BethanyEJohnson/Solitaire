package application;

public class CacheSuit implements Check{
	public boolean check(Card orig, Card top) {
		if(orig.suit == top.suit || top.suit == null) return true;
		else return false;
	}
}
