package application;

public class WinStatus {
	Stacks stacks;
	int cardsInCache = 0;

	public WinStatus(Stacks stacks) {
		this.stacks = stacks;
	}

	// run after a card is placed to update cache
	public void update(Stacks stacks) {
		this.stacks = stacks;
	}

	// check win for solitaire
	public void displaySolitaire() {
		cardsInCache = 0;
		// int cardsInCache = 0;
		for (int a = 0; a < this.stacks.cache.length; a++)
			cardsInCache += this.stacks.cache[a].size();
		// 52 cards in Deck and 4 empty cards to hold place of cache
		if (cardsInCache == 56) {
			System.out.println("you win!");
		}
		else {
			System.out.println("Current cards in stack: " + (cardsInCache - 4));
		}
	}
}
