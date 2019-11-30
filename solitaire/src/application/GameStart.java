package application;

import javafx.geometry.Point2D;
import java.lang.Math;
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

	// Setup Game
	public void start(Stage primaryStage) throws Exception {
		board = new Pane();
		board.setStyle("-fx-background-color: green");

		// Empty boxes to indicate cache area
		Rectangle[] boxes = new Rectangle[12];
		initializeBox(boxes);

		Button newGame = new Button("New Game");

		// Button to start game
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				newGame.setVisible(true);
				board.getChildren().clear();
				addBox(boxes);
				board.getChildren().addAll(newGame);
				boxes[4].setOnMouseClicked(flipDeck);
				// Make a deck of cards and the stacks
				dc = DeckOfCards.getInstance();
				dc.getCards();
				stacks = new Stacks(dc);
				// Set up cards on the board
				addStacks();
				moveDeck();
				movePlateau();
			}
		});

		// add to container
		addBox(boxes);
		board.getChildren().addAll(newGame);

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
					Card[] cardChildren = cardChildren(c);
					int a = 0;
					while (a < cardChildren.length && cardChildren[a] != null) {
						cardChildren[a].toFront();
						cardChildren[a].translationOrigin[0] = c.translationOrigin[0];
						cardChildren[a].translationOrigin[0] = c.translationOrigin[1] ;
						cardChildren[a].origin[0] = c.origin[0];
						cardChildren[a].origin[1] = c.origin[1];
						a++;
					}
				}
			}
		});

		// Dragging cards around mouseEvent
		c.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (c.isFaceUp) {
					((Card) (t.getSource())).setTranslateX(c.origin[0] + t.getSceneX() - c.translationOrigin[0]);
					((Card) (t.getSource())).setTranslateY(c.origin[1] + t.getSceneY() - c.translationOrigin[1]);
					c.center[0] = t.getSceneX();
					c.center[1] = t.getSceneY();
					Card[] cardChildren = cardChildren(c);
					int a = 0;
					while (a < cardChildren.length && cardChildren[a] != null) {
						Point2D p = new Point2D(((Card) (t.getSource())).getTranslateX(),
								((Card) (t.getSource())).getTranslateY());
						cardChildren[a].setTranslateX(p.getX());
						cardChildren[a].setTranslateY(p.getY());
						a++;
					}
				}
			}
		});

		// Logic for moving cards around
		c.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (c.isFaceUp) {
					Card[] cardChildren = cardChildren(c);
					int a = 0;
					// Move cards to new position
					if (checkBounds(c, cardChildren)) {
						((Card) (t.getSource())).setTranslateX(c.origin[0]);
						((Card) (t.getSource())).setTranslateY(c.origin[1]);
						while (a < cardChildren.length && cardChildren[a] != null) {
							cardChildren[a].setTranslateX(cardChildren[a].origin[0]);
							cardChildren[a].setTranslateY(cardChildren[a].origin[1]);
							a++;
						}
						resetPlateau();
					}
					// Move cards to original position
					else {
						((Card) (t.getSource())).setTranslateX(c.origin[0]);
						((Card) (t.getSource())).setTranslateY(c.origin[1]);
						while (a < cardChildren.length && cardChildren[a] != null) {
							cardChildren[a].setTranslateX(cardChildren[a].origin[0]);
							cardChildren[a].setTranslateY(cardChildren[a].origin[1]);
							a++;
						}
					}
					//stackPrinter();
				}
			}
		});
	}

	// Get all "children" of card
	public Card[] cardChildren(Card c) {
		Card[] children;
		for (int i = 0; i < stacks.plateau.length; i++)
			for (int j = 0; j < stacks.plateau[i].size(); j++)
				if (c.equals(stacks.plateau[i].get(j))) {
					children = new Card[stacks.plateau[i].size() - j];
					j++;
					int a = 0;
					while (j < stacks.plateau[i].size()) {
						children[a] = stacks.plateau[i].get(j);
						a++;
						j++;
					}
					return children;
				}
		return null;
	}

	// remove card in its plateau
	public void removeCard(Card c) {
		for (int i = 0; i < stacks.plateau.length; i++) {
			for (int j = 0; j < stacks.plateau[i].size(); j++) {
				if (c.equals(stacks.plateau[i].get(j))) {
					if (j != 0)
						stacks.plateau[i].get(j - 1).attachFace();
					stacks.plateau[i].remove(j);
					i = stacks.plateau.length;
					break;
				}
			}
		}
	}

	// add card to new plateau
	public void moveToNewStack(Card pos, Card add) {
		for (int i = 0; i < stacks.plateau.length; i++)
			for (int j = 0; j < stacks.plateau[i].size(); j++)
				if (pos.equals(stacks.plateau[i].get(j))) {
					stacks.plateau[i].add(add);
					resetPlateau();
					i = stacks.plateau.length;
					break;
				}
	}

	// Checks for collision of cards
	public boolean checkBounds(Card c, Card[] cardChildren) {
		boolean collisionDetected = false;
		for (Card card : dc.Cards)
			if (card != c && card.isFaceUp && !card.isDeck)
				if (c.getBoundsInParent().intersects(card.getBoundsInParent()) && distanceCheck(c, card)) {
					// if (checkCard(c, card) && checkSuit(c, card)) {
					collisionDetected = true;
					removeCard(c);
					moveToNewStack(card, c);
					int a = 0;
					while (a < cardChildren.length && cardChildren[a] != null) {
						removeCard(cardChildren[a]);
						moveToNewStack(c, cardChildren[a]);
						a++;
					}
					// }
				}
		return collisionDetected;
	}

	// Find distance between two cards
	public boolean distanceCheck(Card c, Card card) {
		if (Math.abs(c.center[0] - card.getX() - 40) < 45 && Math.abs(c.center[1] - card.getY() - 60) < 45)
			return true;
		return false;
	}

	// Restack cards after one is moved
	public void resetPlateau() {
		for (int a = 0; a < stacks.plateau.length; a++) {
			for (int i = 0; i < stacks.plateau[a].size(); i++) {
				stacks.plateau[a].get(i).setX(20 + HEIGHT * a);
				stacks.plateau[a].get(i).setY(200 + 20 * (i * 1.5));
			}
		}
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
	public void addStacks() {
		board.getChildren().addAll(stacks.deck);
		for (int a = 0; a < 7; a++)
			board.getChildren().addAll(stacks.plateau[a]);
	}

	// put deck in correct spot
	public void moveDeck() {
		for (int i = 0; i < stacks.deck.size(); i++) {
			stacks.deck.get(i).attachBack();
			stacks.deck.get(i).setX(20);
			stacks.deck.get(i).setY(25);
			stacks.deck.get(i).setArcWidth(10);
			stacks.deck.get(i).setArcHeight(10);
			addEvent(stacks.deck.get(i));
		}
	}

	// Puts deck back in original location, after being cycled through
	EventHandler<MouseEvent> flipDeck = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			moveDeck();
		}
	};

	// Set up plateau
	public void movePlateau() {
		for (int a = 0; a < stacks.plateau.length; a++) {
			for (int i = 0; i < stacks.plateau[a].size(); i++) {
				stacks.plateau[a].get(i).setX(20 + HEIGHT * a);
				stacks.plateau[a].get(i).setY(200 + 20 * (i * 1.5));
				stacks.plateau[a].get(i).setArcWidth(10);
				stacks.plateau[a].get(i).setArcHeight(10);
				stacks.plateau[a].get(i).attachBack();
				if (i == stacks.plateau[a].size() - 1)
					stacks.plateau[a].get(i).attachFace();
				addEvent(stacks.plateau[a].get(i));
			}
		}
	}

	// Initialize empty boxes on board
	public void initializeBox(Rectangle[] r) {
		for (int a = 0; a < r.length; a++) {
			r[a] = new Rectangle();
			if (a < 7) {
				r[a].setX(20 + 120 * (a));
				r[a].setY(200);
				r[a].setStroke(Color.BLACK);
			} else {
				r[a].setX(380 + 120 * (a - 7));
				r[a].setY(25);
				r[a].setStroke(Color.WHITESMOKE);
			}
			r[a].setWidth(WIDTH);
			r[a].setHeight(HEIGHT);
			r[a].setArcWidth(10);
			r[a].setArcHeight(10);
			r[a].setFill(Color.GREEN);
		}
		// White box position for deck
		r[11].setX(20);
		r[11].setY(25);
	}

	// Add empty white boxes to board
	public void addBox(Rectangle[] r) {
		for (int a = 0; a < r.length; a++)
			board.getChildren().add(r[a]);
	}

	// Test method to look at card coordinates
	public void stackPrinter() {
		for (int a = 0; a < stacks.plateau.length; a++) {
			System.out.println("Stack: " + a);
			for (int i = 0; i < stacks.plateau[a].size(); i++) {
				Card c = stacks.plateau[a].get(i);
				System.out.println("R: " + c.rank + "\tS: " + c.suit + "\tX:   " + c.getX() + "\tY: " + c.getY());
			}
		}
	}
}