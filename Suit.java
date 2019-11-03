//Not complete, just some type of progress update

abstract class Suit {
	
	StackBehavior sb;
	CacheBehavior cb;
	
	public Suit() {
	}
	
	public void StackSuitAbove() {
		sb.cardSuitAbove();
	}
	
	public void StackSuitBelow() {
		sb.cardSuitBelow();
	}
	
	public void StackRankAbove() {
		sb.cardRankAbove();
	}
	
	public void StackRankBelow() {
		sb.cardRankBelow();
	}
	
	public void CacheSuit() {
		cb.cardSuit();
	}
	
	public void CacheRank() {
		cb.cardRank();
	}
}

class Heart extends Suit {
	public Heart() {
		sb = new SuitPosition();
	}
}

class Diamond extends Suit {
	
}

class Spade extends Suit {
	
}

class Club extends Suit {
	
}

//Behavior for the stack
abstract class StackBehavior {
	
	public void cardSuitAbove() {}
	public void cardSuitBelow() {}
	public void cardRankAbove() {}
	public void cardRankBelow() {}
}

class SuitPosition extends StackBehavior {

	public void cardSuitAbove() {
		
	}

	public void cardSuitBelow() {
		
	}
}

class RankPosition extends StackBehavior {

	public void cardRankAbove() {
		
	}

	public void cardRankBelow() {
		
	}
}

//Behavior for the cache
abstract class CacheBehavior {
	public void cardRank() {}
	public void cardSuit() {}
}

class CardSuit extends CacheBehavior {

	public void cardSuit() {
		
	}
	
}

class CardRank extends CacheBehavior {

	public void cardRank() {
		
	}
	
}
