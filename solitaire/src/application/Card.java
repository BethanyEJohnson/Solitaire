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
	double[] origin = new double[2];
	double[] destination = new double[2];
	// Height and width of card
	final int WIDTH = 80;
	final int HEIGHT = 120;

	// No arg constructor to get example Card
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
		this.setOnMousePressed(cardMousePressMove);
		this.setOnMouseDragged(cardMouseDragMove);
	}

	// Change image of card to be gray
	public void attachBack() {
		File card = new File("src/cards/Gray_back.jpg");
		Image c = new Image(card.toURI().toString());
		ImagePattern i = new ImagePattern(c);
		this.setFill(i);
		isFaceUp = false;
		if (isDeck) {
			this.setOnMouseReleased(cardMousePressDeck);
		}
	}

	// Mouse event for when upper left deck is clicked to reveal next card
	EventHandler<MouseEvent> cardMousePressDeck = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			toFront();
			setX(HEIGHT + 20);
			attachFace();
		}
	};

	// The following two events enable a card to be dragged and dropped to desired
	// location
	EventHandler<MouseEvent> cardMousePressMove = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			toFront();
			origin[0] = t.getSceneX();
			origin[1] = t.getSceneY();
			destination[0] = ((Card) (t.getSource())).getTranslateX();
			destination[1] = ((Card) (t.getSource())).getTranslateY();
		}
	};

	EventHandler<MouseEvent> cardMouseDragMove = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			toFront();
			double offsetX = t.getSceneX() - origin[0];
			double offsetY = t.getSceneY() - origin[1];
			double newTranslateX = destination[0] + offsetX;
			double newTranslateY = destination[1] + offsetY;
			((Card) (t.getSource())).setTranslateX(newTranslateX);
			((Card) (t.getSource())).setTranslateY(newTranslateY);
		}
	};
}
