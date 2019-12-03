package application;

public class PlateauSuit implements Check{
	public boolean check(Card orig, Card top) {
		if ((orig.suit == "H" || orig.suit == "D") && (top.suit == "S" || top.suit == "C"))
			return true;
		if ((orig.suit == "S" || orig.suit == "C") && (top.suit == "H" || top.suit == "D"))
			return true;
		return false;
		}
}
