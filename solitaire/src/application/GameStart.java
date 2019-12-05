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
				// Puts deck back in original location, after being cycled through
				boxes[11].setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent t) {
						moveDeck();
					}
				});
				// Make a deck of cards and the stacks
				dc = DeckOfCards.getInstance();
				stacks = new Stacks(dc);
				// Set up cards on the board
				moveDeck();
				addStacks();
				moveCache();
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
				// Check if card is faceup
				if (c.isFaceUp) {
					// Pull card to front and get coordinates
					c.toFront();
					c.translationOrigin[0] = t.getSceneX();
					c.translationOrigin[1] = t.getSceneY();
					c.origin[0] = ((Card) (t.getSource())).getTranslateX();
					c.origin[1] = ((Card) (t.getSource())).getTranslateY();
					// Check if cards has cards underneath is in stack
					Card[] cardChildren = cardChildren(c);
					int a = 0;
					// Pull each card to front and get coordinates
					if (!c.isDeck && c.hasChildren)
						while (a < cardChildren.length && cardChildren[a] != null) {
							cardChildren[a].toFront();
							cardChildren[a].translationOrigin[0] = c.translationOrigin[0];
							cardChildren[a].translationOrigin[0] = c.translationOrigin[1];
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
				// Check if card is faceup
				if (c.isFaceUp) {
					// Moves the card across the screen
					((Card) (t.getSource())).setTranslateX(c.origin[0] + t.getSceneX() - c.translationOrigin[0]);
					((Card) (t.getSource())).setTranslateY(c.origin[1] + t.getSceneY() - c.translationOrigin[1]);
					c.center[0] = t.getSceneX();
					c.center[1] = t.getSceneY();
					// Check if cards has cards underneath is in stack
					Card[] cardChildren = cardChildren(c);
					int a = 0;
					// Move cards underneath card c across screen
					if (!c.isDeck && c.hasChildren)
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
				WinStatus win = new WinStatus(stacks);

				if (c.isFaceUp) {
					// Check if cards has cards underneath in stack
					Card[] cardChildren = cardChildren(c);
					int a = 0;
					// Move cards to new position
					if (checkBounds(c, cardChildren)) {
						((Card) (t.getSource())).setTranslateX(c.origin[0]);
						((Card) (t.getSource())).setTranslateY(c.origin[1]);
						if (!c.isDeck && c.hasChildren)
							while (a < cardChildren.length && cardChildren[a] != null) {
								cardChildren[a].setTranslateX(cardChildren[a].origin[0]);
								cardChildren[a].setTranslateY(cardChildren[a].origin[1]);
								a++;
							}
					}
					// Move cards to original position
					else {
						((Card) (t.getSource())).setTranslateX(c.origin[0]);
						((Card) (t.getSource())).setTranslateY(c.origin[1]);
						if (!c.isDeck && c.hasChildren)
							while (a < cardChildren.length && cardChildren[a] != null) {
								cardChildren[a].setTranslateX(cardChildren[a].origin[0]);
								cardChildren[a].setTranslateY(cardChildren[a].origin[1]);
								a++;
							}
					}
					c.hasChildren = false;
					win.update();
				}
			}
		});
	}

	// Get all "children" of card
	public Card[] cardChildren(Card c) {
		Card[] children;
		if (!c.isDeck)
			for (int i = 0; i < stacks.plateau.length; i++)
				for (int j = 1; j < stacks.plateau[i].size(); j++)
					if (c.equals(stacks.plateau[i].get(j))) {
						children = new Card[stacks.plateau[i].size() - j];
						j++;
						int a = 0;
						while (j < stacks.plateau[i].size()) {
							c.hasChildren = true;
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
		if (!c.isCache) {
			for (int i = 0; i < stacks.plateau.length; i++)
				for (int j = 1; j < stacks.plateau[i].size(); j++)
					if (c.equals(stacks.plateau[i].get(j))) {
						if (j != 1)
							stacks.plateau[i].get(j - 1).attachFace();
						stacks.plateau[i].remove(j);
						i = stacks.plateau.length;
						break;
					}
		}
		if (c.isCache) {
			for (int i = 0; i < stacks.cache.length; i++) {
				for (int j = 0; j < stacks.cache[i].size(); j++) {
					if (c.equals(stacks.cache[i].get(j))) {
						stacks.cache[i].remove(c);
						c.isCache = false;
					}
				}
			}
		}
	}

	// Add card to new plateau
	public void moveToNewStack(Card pos, Card add) {
		// Add card to stack in plateau
		if (!pos.isCache) {
			for (int i = 0; i < stacks.plateau.length; i++)
				for (int j = 0; j < stacks.plateau[i].size(); j++)
					if (pos.equals(stacks.plateau[i].get(j))) {
						stacks.plateau[i].add(add);
						resetPlateau(i);
						i = stacks.plateau.length;
						break;
					}
		}

		// Add card to cache stack
		if (pos.isCache) {
			for (int i = 0; i < stacks.cache.length; i++)
				for (int j = 0; j < stacks.cache[i].size(); j++)
					if (pos.equals(stacks.cache[i].get(j))) {
						stacks.cache[i].add(add);
						resetCache(i);
						i = stacks.cache.length;
						break;
					}
		}
	}

	// Checks for collision of cards
	public boolean checkBounds(Card c, Card[] cardChildren) {
		boolean collisionDetected = false;
		if (!c.isCache) {
			// King to Empty Stack
			if (c.rank == "K") {
				for (int i = 0; i < stacks.plateau.length; i++) {
					Card card = stacks.plateau[i].get(0);
					if (cIntersect(c, card) && stacks.plateau[i].size() == 1 && distanceCheck(c, card)) {
						collisionDetected = true;
						if (!c.isDeck) {
							removeCard(c);
						} else {
							c.isDeck = false;
							stacks.deck.remove(c);
						}
						moveToNewStack(card, c);
						int a = 0;
						if (c.hasChildren)
							while (a < cardChildren.length && cardChildren[a] != null) {
								removeCard(cardChildren[a]);
								moveToNewStack(c, cardChildren[a]);
								a++;
							}
					}
				}
			}

			// Plateau/deck ---> Plateau
			for (int i = 0; i < stacks.plateau.length; i++)
				for (int j = 1; j < stacks.plateau[i].size(); j++) {
					Card card = stacks.plateau[i].get(j);
					if (card != c && card.isFaceUp && !card.isDeck)
						if (cIntersect(c, card) && distanceCheck(c, card) && c != card) {
							if (c.rb.plateauRank(c, card) && c.sb.plateauSuit(c, card)) {
								collisionDetected = true;
								if (!c.isDeck) {
									removeCard(c);
								} else {
									c.isDeck = false;
									stacks.deck.remove(c);
								}
								moveToNewStack(card, c);
								int a = 0;
								if (c.hasChildren)
									while (a < cardChildren.length && cardChildren[a] != null) {
										removeCard(cardChildren[a]);
										moveToNewStack(c, cardChildren[a]);
										a++;
									}
							}
						}
				}

			// Plateau/Deck ---> cache
			for (int i = 0; i < stacks.cache.length; i++)
				for (int j = 0; j < stacks.cache[i].size(); j++) {
					Card card = stacks.cache[i].get(j);
					if (cIntersect(c, card) && distanceCheck(c, card) && !c.hasChildren && c != card) {
						System.out.println(c.rb);
						if (c.rb.cacheRank(c, card) && c.sb.cacheSuit(c, card)) {
							collisionDetected = true;
							if (c.isDeck) {
								c.isDeck = false;
								stacks.deck.remove(c);
							} else
								removeCard(c);
							moveToNewStack(card, c);
						}
					}
				}
		}

		// Cache ---> Plateau
		if (c.isCache) {
			for (int i = 0; i < stacks.plateau.length; i++)
				for (int j = 1; j < stacks.plateau[i].size(); j++) {
					Card card = stacks.plateau[i].get(j);
					if (card != c && card.isFaceUp && !card.isDeck)
						if (cIntersect(c, card) && distanceCheck(c, card) && c != card) {
							if (c.rb.plateauRank(c, card) && c.sb.plateauSuit(c, card)) {
								collisionDetected = true;
								removeCard(c);
								moveToNewStack(card, c);
							}
						}
				}
			return collisionDetected;
		}
		return collisionDetected;
	}

	// Checks if two cards intersect by their bounds
	public boolean cIntersect(Card c, Card card) {
		return c.getBoundsInParent().intersects(card.getBoundsInParent());
	}

	// Find distance between two cards
	public boolean distanceCheck(Card c, Card card) {
		if (Math.abs(c.center[0] - card.getX() - 40) < 45 && Math.abs(c.center[1] - card.getY() - 60) < 45)
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
			stacks.deck.get(i).toFront();
			stacks.deck.get(i).attachBack();
			stacks.deck.get(i).setX(20);
			stacks.deck.get(i).setY(25);
			stacks.deck.get(i).setArcWidth(10);
			stacks.deck.get(i).setArcHeight(10);
			addEvent(stacks.deck.get(i));
		}
	}

	// Initially set up the Plateau
	public void movePlateau() {
		for (int i = 0; i < stacks.plateau.length; i++) {
			stacks.plateau[i].get(0).setX(20 + HEIGHT * i);
			stacks.plateau[i].get(0).setY(200 + 20 * (0 * 1.5));
			stacks.plateau[i].get(0).setStroke(Color.BLACK);
			stacks.plateau[i].get(0).setFill(Color.GREEN);
			stacks.plateau[i].get(0).setArcWidth(10);
			stacks.plateau[i].get(0).setArcHeight(10);
			for (int j = 1; j < stacks.plateau[i].size(); j++) {
				stacks.plateau[i].get(j).toFront();
				stacks.plateau[i].get(j).setX(20 + HEIGHT * i);
				stacks.plateau[i].get(j).setY(200 + 20 * ((j - 1) * 1.5));
				stacks.plateau[i].get(j).setArcWidth(10);
				stacks.plateau[i].get(j).setArcHeight(10);
				stacks.plateau[i].get(j).attachBack();
				if (j == stacks.plateau[i].size() - 1)
					stacks.plateau[i].get(j).attachFace();
				addEvent(stacks.plateau[i].get(j));
			}
		}
	}

	// Restack cards in stack after one is added
	public void resetPlateau(int i) {
		for (int j = 1; j < stacks.plateau[i].size(); j++) {
			stacks.plateau[i].get(j).setX(20 + HEIGHT * i);
			stacks.plateau[i].get(j).setY(200 + 20 * ((j - 1) * 1.5));
		}
	}

	// Initalliy set up cache
	public void moveCache() {
		for (int i = 0; i < stacks.cache.length; i++) {
			board.getChildren().addAll(stacks.cache[i]);
			stacks.cache[i].get(0).setX(380 + 120 * i);
			stacks.cache[i].get(0).setY(25);
			stacks.cache[i].get(0).setArcWidth(10);
			stacks.cache[i].get(0).setArcHeight(10);
			stacks.cache[i].get(0).setStroke(Color.WHITESMOKE);
			stacks.cache[i].get(0).setFill(Color.GREEN);
		}
	}

	// Restack the cache after one is added
	public void resetCache(int i) {
		for (int j = 1; j < stacks.cache[i].size(); j++) {
			stacks.cache[i].get(j).toFront();
			stacks.cache[i].get(j).isCache = true;
			stacks.cache[i].get(j).setX(380 + 120 * i);
			stacks.cache[i].get(j).setY(25);
		}
	}

	// Initialize empty boxes on board
	public void initializeBox(Rectangle[] r) {
		for (int i = 0; i < r.length; i++) {
			r[i] = new Rectangle();
			if (i < 7) {
				r[i].setX(20 + 120 * (i));
				r[i].setY(200);
				r[i].setStroke(Color.BLACK);
			} else {
				r[i].setX(380 + 120 * (i - 7));
				r[i].setY(25);
				r[i].setStroke(Color.WHITESMOKE);
			}
			r[i].setWidth(WIDTH);
			r[i].setHeight(HEIGHT);
			r[i].setArcWidth(10);
			r[i].setArcHeight(10);
			r[i].setFill(Color.GREEN);
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
			for (int i = 1; i < stacks.plateau[a].size(); i++) {
				Card c = stacks.plateau[a].get(i);
				System.out.println("R: " + c.rank + "\tS: " + c.suit + "\tX:   " + c.getX() + "\tY: " + c.getY());
			}
		}
	}

	// Test method to look at cache card coordinates
	public void cachePrinter() {
		for (int a = 0; a < stacks.cache.length; a++) {
			System.out.println("Stack: " + a);
			for (int i = 0; i < stacks.cache[a].size(); i++) {
				Card c = stacks.cache[a].get(i);
				System.out.println("R: " + c.rank + "\tS: " + c.suit + "\tX:   " + c.getX() + "\tY: " + c.getY());
			}
		}
	}
}