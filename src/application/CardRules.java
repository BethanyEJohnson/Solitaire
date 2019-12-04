package application;

public class CardRules {
	String[] suit = { "H", "S", "D", "C" };
	String[] rank = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	String black = "HD";
	String red = "SC";
	int intRank;
	//returns numerical rank of card for comparison purposes
	public int getIntegerRank(String r) {
		for (int i = 0; i < rank.length; i++) {
			if (r == rank[i]) {
				intRank = i;
			}
		}
		return intRank;
	}

	public String compareRank(Card orig, Card top) {
		if (getIntegerRank(orig.rank) < getIntegerRank(top.rank)
				&& getIntegerRank(orig.rank) == (getIntegerRank(top.rank) - 1)) {
			return " Next Rank Down ";
		} else if (getIntegerRank(orig.rank) > getIntegerRank(top.rank)
				&& getIntegerRank(orig.rank) == (getIntegerRank(top.rank) + 1)) {
			return " Next Rank Up ";
		} else {
			return " Not Next ";
		}
	}

	public String compareSuit(Card orig, Card top) {
		String black = "HD";
		String red = "SC";
		if (orig.suit == top.suit) {
			return "Same Suit ";
		} else if (red.contains(orig.suit) && black.contains(top.suit)) {
			return "Opposite Suit ";
		} else if (black.contains(orig.suit) && red.contains(top.suit)) {
			return "Opposite Suit ";
		} else {
			return "Not a match ";
		}
	}

	public String compareCards(Card orig, Card top) {
		String rankComp = compareRank(orig, top);
		String suitComp = compareSuit(orig, top);
		return rankComp + suitComp;
	}
	
	public boolean isNear(Card orig, Card top) {
		if() {
		}
		return false;
		
	}

	public void validMove() {
	}
	
	public void removeCard() {
	}
	
	public void addCard() {
	}

}
