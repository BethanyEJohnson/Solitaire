package application;

public class WinStatus extends GameStart{
	Stacks stacks;
	int cardsInCache = 0;
	
	public WinStatus(Stacks stacks) {
		this.stacks = stacks;
	}
	
	//run after a card is placed to update cache
	public void update() {
		//int cardsInCache = 0;
		for (int a = 0; a < this.stacks.cache.length; a++)
			cardsInCache += this.stacks.cache[a].size();
		// Do Something if Player wins
		// 52 cards in Deck and 4 empty cards to hold place of cache
		if (cardsInCache == 0) {
			System.out.println("you win!");
		}
	}
	
	//to check class
	public void test() {
		System.out.println("you win!");
	}
}
