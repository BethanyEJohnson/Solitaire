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
		setOnMousePressed(cardMousePressMove);
		setOnMouseDragged(cardMouseDragMove);
		setOnMouseReleased(cardMouseDragRelease);
	}

	// Change image of card to be gray
	public void attachBack() {
		File card = new File("src/cards/Gray_back.jpg");
		Image c = new Image(card.toURI().toString());
		ImagePattern i = new ImagePattern(c);
		this.setFill(i);
		if (isDeck)
			setOnMouseClicked(cardMousePressDeck);
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

	// Get origin and destination of card for the movement
	EventHandler<MouseEvent> cardMousePressMove = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			toFront();
			translationOrigin[0] = t.getSceneX();
			translationOrigin[1] = t.getSceneY();
			origin[0] = ((Card) (t.getSource())).getTranslateX();
			origin[1] = ((Card) (t.getSource())).getTranslateY();
		}
	};

	// Dragging cards around mouseEvent
	EventHandler<MouseEvent> cardMouseDragMove = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			double TranslateX = origin[0] + t.getSceneX() - translationOrigin[0];
			double TranslateY = origin[1] + t.getSceneY() - translationOrigin[1];
			((Card) (t.getSource())).setTranslateX(TranslateX);
			((Card) (t.getSource())).setTranslateY(TranslateY);

		}
	};

	// The following event checks whether a card can be set at a location
	// Logic needs to be added here for movement ///////////////////////////////////////// or move this event to different class
	EventHandler<MouseEvent> cardMouseDragRelease = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			if (1 == 1) {
				((Card) (t.getSource())).setTranslateX(origin[0]);
				((Card) (t.getSource())).setTranslateY(origin[1]);
			}
		}
	};
}
