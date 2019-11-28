package application;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Card extends Rectangle {
	// because card extends rectangle, getX and getY return the top left position of
	// the card
	String suit;
	String rank;
	// boolean value to determine if card is face up
	boolean isFaceUp;
	// boolean value to check if a card is a part of the deck(top left)
	boolean isDeck;
	// Coordinates for moving a card, first position in array is x second is y
	double[] translationOrigin = new double[2];
	double[] origin = new double[2];
	// Height and width of card
	final int WIDTH = 80;
	final int HEIGHT = 120;
	// If this card is near another card (For mouse event)
	boolean isNear = false;
	double TranslateX,TranslateY;

	// No argument constructor to get example Card
	public Card() {
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
	}

	// Constructor
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		attachBack();
	}

	// Attach Face Image to card
	public void attachFace() {
		File card = new File("src/cards/" + this.rank + this.suit + ".jpg");
		Image c = new Image(card.toURI().toString());
		ImagePattern i = new ImagePattern(c);
		this.setFill(i);
		isFaceUp = true;
	}

	// Change image of card to be gray
	public void attachBack() {
		File card = new File("src/cards/Gray_back.jpg");
		Image c = new Image(card.toURI().toString());
		ImagePattern i = new ImagePattern(c);
		this.setFill(i);
		isFaceUp = false;
	}
}
