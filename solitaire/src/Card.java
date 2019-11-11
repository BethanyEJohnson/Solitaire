//one card object
public class Card {
	String suit;
	String rank;
	String state;
	int[] origin;
	int[] destination;
	
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	//state is face up or face down
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	//origin is the spot the card was in before being clicked on
	public int[] getOrigin() {
		return origin;
	}
	public void setOrigin(int[] origin) {
		this.origin = origin;
	}
	
	//destination is the spot the card is in after click/drag is over
	public int[] getDestination() {
		return destination;
	}
	public void setDestination(int[] destination) {
		this.destination = destination;
	}
}
