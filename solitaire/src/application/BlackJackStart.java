package application;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BlackJackStart implements Game{
	Pane board = new Pane();
	Card cardEx = new Card();
	Button hit, one, two, three, stand;
	final int WIDTH = cardEx.WIDTH;
	final int HEIGHT = cardEx.HEIGHT;
	DeckOfCards dc;
	WinStatus win;
	// Lists of cards for player and house
	ArrayList<Card> hHand;
	ArrayList<Card> pHand;

	public void start(Stage primaryStage) throws Exception {
		board = new Pane();
		board.setStyle("-fx-background-color: green");
		Button newGame = new Button("New Game");
		// The button to add a card to your stack
		hit = new Button("Hit");
		// The button to bet one dollar
		one = new Button("Bet $1");
		two = new Button("Bet $2");
		three = new Button("Bet $3");
		stand = new Button("Stand");
		Rectangle[] boxes = new Rectangle[2];
		hit.setLayoutX(10);
		hit.setLayoutY(400);
		one.setLayoutX(10);
		one.setLayoutY(250);
		two.setLayoutX(10);
		two.setLayoutY(300);
		three.setLayoutX(10);
		three.setLayoutY(350);
		stand.setLayoutX(10);
		stand.setLayoutY(450);
		one.setVisible(false);
		two.setVisible(false);
		three.setVisible(false);
		
		// Button to start game
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Initialize pHand and hHand
				pHand = new ArrayList<Card>();
				hHand = new ArrayList<Card>();
				// Get one instance of deck of cards
				dc = DeckOfCards.getInstance();
				buttonVisibility();
				initializeBox(boxes);
				newGame.setVisible(true);
				board.getChildren().clear();
				board.getChildren().addAll(newGame, hit, one, two, three, stand, boxes[0], boxes[1]);
				// deal card to player and to the house
				houseCards();
				myCards();
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

	// This deals the two cards for the house
	public void houseCards() {
		board.getChildren().addAll(dc.Cards[51], dc.Cards[50]);
		hHand.add(dc.Cards[51]);
		hHand.add(dc.Cards[50]);
		hHand.get(0).attachBack();
		hHand.get(0).setArcWidth(10);
		hHand.get(0).setArcHeight(10);
		hHand.get(0).setX(105);
		hHand.get(0).setY(105);
		hHand.get(1).toFront();
		hHand.get(1).attachFace();
		hHand.get(1).setArcWidth(10);
		hHand.get(1).setArcHeight(10);
		hHand.get(1).setX(140);
		hHand.get(1).setY(105);
	}
	
	// This deals the two cards for the player
	public void myCards() {
		board.getChildren().addAll(dc.Cards[49], dc.Cards[48]);
		pHand.add(dc.Cards[49]);
		pHand.add(dc.Cards[48]);
		pHand.get(0).attachFace();
		pHand.get(0).setArcWidth(10);
		pHand.get(0).setArcHeight(10);
		pHand.get(0).setX(105);
		pHand.get(0).setY(405);
		pHand.get(1).toFront();
		pHand.get(1).attachFace();
		pHand.get(1).setArcWidth(10);
		pHand.get(1).setArcHeight(10);
		pHand.get(1).setX(140);
		pHand.get(1).setY(405);
	}
	
	// Set button visibility
	public void buttonVisibility() {
		if (!one.isVisible() && !two.isVisible() && !three.isVisible()) {
			hit.setVisible(true);
			stand.setVisible(true);
		} else {
			one.setVisible(true);
			two.setVisible(true);
			three.setVisible(true);
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
