package application;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

//one card object
public class Card extends Rectangle {
	// because card extends rectangle, getx and gety return the top left position of
	// the card
	String suit;
	String rank;
	boolean isFaceUp;
	int[] origin;
	int[] destination;

	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
		attachBack();
	}

	// Attach Face Image to card
	public void attachFace() {
		File card = new File("src/cards/" + this.rank + this.suit + ".jpg");
		Image c = new Image(card.toURI().toString());
		ImagePattern i = new ImagePattern(c);
		this.setFill(i);
	}

	// Change image of card to be gray
	public void attachBack() {
		File card = new File("src/cards/Gray_back.jpg");
		Image c = new Image(card.toURI().toString());
		ImagePattern i = new ImagePattern(c);
		this.setFill(i);
	}

	// state is face up or face down
	public boolean getState() {
		return isFaceUp;
	}

	public void setState(boolean isFaceUp) {
		this.isFaceUp = isFaceUp;
	}

	// origin is the spot the card was in before being clicked on
	public int[] getOrigin() {
		return origin;
	}

	public void setOrigin(int[] origin) {
		this.origin = origin;
	}

	// destination is the spot the card is in after click/drag is over
	public int[] getDestination() {
		return destination;
	}

	public void setDestination(int[] destination) {
		this.destination = destination;
	}
}
