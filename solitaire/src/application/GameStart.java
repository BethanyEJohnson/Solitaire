package application;

import java.lang.Math;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class GameStart extends Application {
	Pane board = new Pane();
	Card cardEx = new Card();
	final int WIDTH = cardEx.WIDTH;
	final int HEIGHT = cardEx.HEIGHT;
	DeckOfCards dc;
	Stacks stacks;

	public static void main(String[] args) {
		launch();
	}

	public void start(Stage primaryStage) throws Exception {
		board = new Pane();
		board.setStyle("-fx-background-color: green");

		// Empty boxes to indicate cache area
		Rectangle[] boxes = new Rectangle[5];
		initializeBox(boxes);

		Button newGame = new Button("New Game");

		// Button to start game
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				newGame.setVisible(true);
				board.getChildren().clear();
				board.getChildren().addAll(boxes[0], boxes[1], boxes[2], boxes[3], boxes[4], newGame);
				boxes[4].setOnMouseClicked(flipDeck);
				// Make a deck of cards and the stacks
				dc = DeckOfCards.getInstance();
				dc.getCards();
				stacks = new Stacks(dc);
				// Set up cards on the board
				addStacks(stacks);
				moveDeck(stacks.deck);
				movePlateau(stacks.plateau);
			}
		});

		// add to container
		board.getChildren().addAll(boxes[0], boxes[1], boxes[2], boxes[3], boxes[4], newGame);

		// create scene
		Scene mainScene = new Scene(board, 840, 900);

		// add to stage
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Solitaire");

		// show the stage
		primaryStage.show();
	}

	// Add ability for cards to be clicked
	public void addEvent(Card c) {
		// Mouse event for when upper left deck is clicked to reveal next card
		c.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (c.isDeck) {
					c.toFront();
					c.setX(HEIGHT + 20);
					c.attachFace();
				}
			}
		});

		// Get origin and logic for movement of card
		c.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (c.isFaceUp) {
					c.toFront();
					c.translationOrigin[0] = t.getSceneX();
					c.translationOrigin[1] = t.getSceneY();
					c.origin[0] = ((Card) (t.getSource())).getTranslateX();
					c.origin[1] = ((Card) (t.getSource())).getTranslateY();
				}
			}
		});

		// Dragging cards around mouseEvent
		c.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (c.isFaceUp) {
					c.TranslateX = c.origin[0] + t.getSceneX() - c.translationOrigin[0];
					c.TranslateY = c.origin[1] + t.getSceneY() - c.translationOrigin[1];
					((Card) (t.getSource())).setTranslateX(c.TranslateX);
					((Card) (t.getSource())).setTranslateY(c.TranslateY);
					c.center[0] = t.getSceneX();
					c.center[1] = t.getSceneY();
				}
			}
		});

		// Logic for moving cards around
		c.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (c.isFaceUp) {
					if (checkBounds(c)) {
						((Card) (t.getSource())).setTranslateX(c.TranslateX);
						((Card) (t.getSource())).setTranslateY(c.TranslateY);
					} else {
						((Card) (t.getSource())).setTranslateX(c.origin[0]);
						((Card) (t.getSource())).setTranslateY(c.origin[1]);
					}
				}
			}
		});
	}

	// Checks for collision of cards
	public boolean checkBounds(Card c) {
		boolean collisionDetected = false;
		for (Card card : dc.Cards) {
			if (card != c && card.isFaceUp && !card.isDeck) {
				if (c.getBoundsInParent().intersects(card.getBoundsInParent()) && distanceCheck(c, card)) {
					if (checkCard(c, card) && checkSuit(c, card))
						collisionDetected = true;
				}
			}
		}
		System.out.println("");
		return collisionDetected;
	}

	public boolean distanceCheck(Card c, Card card) {
		if (Math.abs(c.center[0] - card.getX() - 40) < 40 && Math.abs(c.center[1] - card.getY() - 60) < 40)
			return true;
		return false;
	}

	// Comparison to see if above card is the right rank for movement
	public boolean checkCard(Card orig, Card top) {
		if (orig.rank == "A" && top.rank == "2")
			return true;
		if (orig.rank == "2" && top.rank == "3")
			return true;
		if (orig.rank == "3" && top.rank == "4")
			return true;
		if (orig.rank == "4" && top.rank == "5")
			return true;
		if (orig.rank == "5" && top.rank == "6")
			return true;
		if (orig.rank == "6" && top.rank == "7")
			return true;
		if (orig.rank == "7" && top.rank == "8")
			return true;
		if (orig.rank == "8" && top.rank == "9")
			return true;
		if (orig.rank == "9" && top.rank == "10")
			return true;
		if (orig.rank == "10" && top.rank == "J")
			return true;
		if (orig.rank == "J" && top.rank == "Q")
			return true;
		if (orig.rank == "Q" && top.rank == "K")
			return true;
		if (orig.rank == "K" && top.rank == null)
			return true;
		return false;
	}

	// Comparison to see if above card is the right suit for movement
	public boolean checkSuit(Card orig, Card top) {
		if ((orig.suit == "H" || orig.suit == "D") && (top.suit == "S" || top.suit == "C"))
			return true;
		if ((orig.suit == "S" || orig.suit == "C") && (top.suit == "H" || top.suit == "D"))
			return true;
		return false;
	}

	// add cards stacks entities to board
	public void addStacks(Stacks s) {
		board.getChildren().addAll(stacks.deck);
		for (int a = 0; a < 7; a++)
			board.getChildren().addAll(stacks.plateau[a]);
	}

	// put deck in correct spot
	public void moveDeck(LinkedList<Card> cards) {
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).attachBack();
			cards.get(i).setX(20);
			cards.get(i).setY(25);
			cards.get(i).setArcWidth(10);
			cards.get(i).setArcHeight(10);
			addEvent(cards.get(i));
		}
	}

	// Set up plateau
	public void movePlateau(LinkedList<Card>[] plateau) {
		for (int a = 0; a < plateau.length; a++) {
			for (int i = 0; i < plateau[a].size(); i++) {
				plateau[a].get(i).setX(20 + HEIGHT * a);
				plateau[a].get(i).setY(200 + 20 * i);
				plateau[a].get(i).setArcWidth(10);
				plateau[a].get(i).setArcHeight(10);
				if (i == plateau[a].size() - 1)
					plateau[a].get(i).attachFace();
				addEvent(plateau[a].get(i));

			}
		}
	}

	// Initialize cache area empty white boxes
	public void initializeBox(Rectangle[] r) {
		for (int a = 0; a < 5; a++) {
			r[a] = new Rectangle();
			r[a].setX(380 + 120 * a);
			r[a].setY(25);
			r[a].setWidth(WIDTH);
			r[a].setHeight(HEIGHT);
			r[a].setArcWidth(10);
			r[a].setArcHeight(10);
			r[a].setFill(Color.GREEN);
			r[a].setStroke(Color.WHITESMOKE);
		}
		// White box pos for deck
		r[4].setX(20);
		r[4].setY(25);
	}

	// Mouse event for putting deck back in original location, after being
	// cycled through
	EventHandler<MouseEvent> flipDeck = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			moveDeck(stacks.deck);
		}
	};
}