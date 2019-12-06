package application;

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
				buttonVisibility();
				initializeBox(boxes);
				newGame.setVisible(true);
				board.getChildren().clear();
				board.getChildren().addAll(newGame, hit, one, two, three, stand, boxes[0], boxes[1]);

				// Make a deck of cards and the stacks
				dc = DeckOfCards.getInstance();

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
			r[i].setHeight(200);
			r[i].setArcWidth(10);
			r[i].setArcHeight(10);
			r[i].setFill(Color.GREEN);
		}
	}
}
