package application;

import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BlackJackStart implements Game {
	Pane board = new Pane();
	Card cardEx = new Card();
	Button hit, one, two, stand;
	final int WIDTH = cardEx.WIDTH;
	final int HEIGHT = cardEx.HEIGHT;
	DeckOfCards dc;
	int deckPos = 0;
	// Lists of cards for player and house
	LinkedList<Card> hHand;
	LinkedList<Card> pHand;
	// Value of all cards in player and house hand
	int pNumber, hNumber;
	// Text box for when user wins or loses
	Text t;

	public void start(Stage primaryStage) throws Exception {
		board = new Pane();
		board.setStyle("-fx-background-color: green");
		Button newGame = new Button("New Game");

		// Initialize pHand and hHand
		pHand = new LinkedList<Card>();
		hHand = new LinkedList<Card>();

		// Initialize numbers
		pNumber = 0;
		hNumber = 0;

		// The button to add a card to your stack
		hit = new Button("Hit");

		// Initializing buttons
		one = new Button("Bet $1");
		two = new Button("Bet $2");
		stand = new Button("Stand");

		// Adds events to betting buttons
		setUpBetButtons();

		// Adds events to game buttons
		setUpGameButtons();

		// Initializing empty boxes on board
		Rectangle[] boxes = new Rectangle[2];

		// Sets up the position of the buttons on the scren
		hit.setLayoutX(10);
		hit.setLayoutY(400);
		one.setLayoutX(10);
		one.setLayoutY(250);
		two.setLayoutX(10);
		two.setLayoutY(300);
		stand.setLayoutX(10);
		stand.setLayoutY(450);
		hit.setVisible(false);
		stand.setVisible(false);

		t = new Text();
		t.setText("test");
		t.setLayoutX(100);
		t.setLayoutX(100);
		t.setFont(new Font(20));
		t.setFill(Color.RED);
		t.toFront();
		// Button to start game
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Get one instance of deck of cards
				dc = DeckOfCards.getInstance();
				initializeBox(boxes);
				newGame.setVisible(true);
				board.getChildren().clear();
				// Adds all buttons, boxes, and cards to board
				board.getChildren().addAll(newGame, hit, one, two, stand, boxes[0], boxes[1], t);
				adjustVisibility();
			}
		});

		board.getChildren().add(newGame);
		Scene mainScene = new Scene(board, 640, 700);

		// add to stage
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Black Jack");

		// show the stage
		primaryStage.show();
	}

	// For setting up and resetting the cards on the board
	public void adjustVisibility() {
		board.getChildren().removeAll(dc.Cards);
		board.getChildren().addAll(dc.Cards);
		for (int a = 0; a < 52; a++)
			dc.Cards[a].setVisible(false);
	}

	// Event for the betting buttons one and two
	// wanted to implement a way to keep track of money //
	public void setUpBetButtons() {
		one.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				adjustVisibility();
				// Set hand values back to zero
				pNumber = 0;
				hNumber = 0;
				// take cards from both player and house on board
				pHand.clear();
				hHand.clear();
				// Shuffle Deck each hand
				deckPos = 0;
				dc.shuffle(dc.Cards);
				buttonVisibility();
				// deal card to player and to the house
				houseCards();
				PlayerCards();
			}
		});
		two.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				adjustVisibility();
				// Set hand values back to zero
				pNumber = 0;
				hNumber = 0;
				// take cards from both player and house on board
				pHand.clear();
				hHand.clear();
				// Shuffle Deck each hand
				deckPos = 0;
				dc.shuffle(dc.Cards);
				buttonVisibility();
				// deal card to player and to the house
				houseCards();
				PlayerCards();
			}
		});
	}

	// Setup buttons to add card to your stack and buttons to stand when you don't
	// want anymore cards
	public void setUpGameButtons() {
		hit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addCardToPlayer();
				if (pNumber > 21) {
					//// Lose message
					System.out.println("you lose");
					hHand.get(0).attachFace();
					buttonVisibility();
				} else if (pNumber == 21) {
					hHand.get(0).attachFace();
					addCardToHouse();
					buttonVisibility();
					if (hNumber < pNumber) {
						//// Win message
						System.out.println("you win");
					}
				}
			}
		});
		stand.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				hHand.get(0).attachFace();
				addCardToHouse();
				buttonVisibility();

				if (hNumber < pNumber) {
					//// Win message
					System.out.println("you win");
				}
			}
		});
	}

	// Adds cards to the house hand to try to beat what the player has
	public void addCardToHouse() {
		while (hNumber < pNumber && hNumber < 21 && pNumber < 22 && hNumber != pNumber) {
			hHand.add(dc.Cards[deckPos]);
			hNumber += rankToInt(hHand.getLast());
			hHand.getLast().setVisible(true);
			hHand.getLast().toFront();
			hHand.getLast().attachFace();
			hHand.getLast().setArcWidth(10);
			hHand.getLast().setArcHeight(10);
			hHand.getLast().setX(105 + 35 * (hHand.size() - 1));
			hHand.getLast().setY(105);
			deckPos++;
		}
	}

	// Add card to player hand
	public void addCardToPlayer() {
		pHand.add(dc.Cards[deckPos]);
		pNumber += rankToInt(pHand.getLast());
		pHand.getLast().setVisible(true);
		pHand.getLast().toFront();
		pHand.getLast().attachFace();
		pHand.getLast().setArcWidth(10);
		pHand.getLast().setArcHeight(10);
		pHand.getLast().setX(105 + 35 * (pHand.size() - 1));
		pHand.getLast().setY(405);
		deckPos++;
	}

	// Gets a numerical value corresponding to a card rank
	public int rankToInt(Card c) {
		if (c.rank.equals("A")) {
			if (pNumber < 11)
				return 11;
			if (hNumber < 11)
				return 11;
			else
				return 1;
		}
		if (c.rank.equals("2") || c.rank.equals("3") || c.rank.equals("4") || c.rank.equals("5") || c.rank.equals("6")
				|| c.rank.equals("7") || c.rank.equals("8") || c.rank.equals("9")) {
			return Integer.parseInt(c.rank);
		}
		if (c.rank.equals("10") || c.rank.equals("J") || c.rank.equals("Q") || c.rank.equals("K")) {
			return 10;
		} else
			return 0;
	}

	// This deals the two cards for the house
	public void houseCards() {
		for (int i = 0; i < 2; i++) {
			hHand.add(dc.Cards[51 - i]);
			hHand.get(i).setVisible(true);
			hHand.get(i).setArcWidth(10);
			hHand.get(i).setArcHeight(10);
			hHand.get(i).setX(105 + 35 * i);
			hHand.get(i).setY(105);
			hNumber += rankToInt(hHand.get(i));
		}
		hHand.get(0).attachBack();
		hHand.get(1).toFront();
		hHand.get(1).attachFace();
	}

	// This deals the two cards for the player
	public void PlayerCards() {
		for (int i = 0; i < 2; i++) {
			pHand.add(dc.Cards[49 - i]);
			pHand.get(i).setVisible(true);
			pHand.get(i).setArcWidth(10);
			pHand.get(i).setArcHeight(10);
			pHand.get(i).setX(105 + 35 * i);
			pHand.get(i).setY(405);
			pNumber += rankToInt(pHand.get(i));
		}
		pHand.get(0).attachFace();
		pHand.get(1).toFront();
		pHand.get(1).attachFace();
	}

	// Set button visibility
	public void buttonVisibility() {
		if (one.isVisible() && two.isVisible()) {
			hit.setVisible(true);
			stand.setVisible(true);
			one.setVisible(false);
			two.setVisible(false);
		} else if (hit.isVisible() && stand.isVisible()) {
			hit.setVisible(false);
			stand.setVisible(false);
			one.setVisible(true);
			two.setVisible(true);
		}
	}

	// Initialize dealers' box and players' box
	public void initializeBox(Rectangle[] r) {
		for (int i = 0; i < r.length; i++) {
			r[i] = new Rectangle();
			if (i < 1) {
				r[i].setX(100);
				r[i].setY(100);
				r[i].setStroke(Color.BLACK);
			} else {
				r[i].setX(100);
				r[i].setY(400);
				r[i].setStroke(Color.WHITESMOKE);
			}
			r[i].setWidth(400);
			r[i].setHeight(130);
			r[i].setArcWidth(10);
			r[i].setArcHeight(10);
			r[i].setFill(Color.GREEN);
		}
	}
}
