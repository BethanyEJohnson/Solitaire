package application;

import java.util.LinkedList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

public class GameStart extends Application {
	Pane board = new Pane();
	Card cardEx = new Card();
	final int WIDTH = cardEx.WIDTH;
	final int HEIGHT = cardEx.HEIGHT;

	public static void main(String[] args) {
		launch();
	}

	public void start(Stage primaryStage) throws Exception {
		board = new Pane();
		board.setStyle("-fx-background-color: green");

		DeckOfCards dc = new DeckOfCards();
		Stacks stacks = new Stacks(dc);

		// Empty boxes to indicate cache area
		Rectangle[] boxes = new Rectangle[4];
		initializeBox(boxes);

		Button startGame = new Button("Start Game");

		startGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startGame.setVisible(false);
				moveDeck(stacks.deck, board);
				movePlateau(stacks.plateau, board);
			}
		});

		// add to container
		board.getChildren().addAll(boxes[0], boxes[1], boxes[2], boxes[3], startGame);

		// create scene
		Scene mainScene = new Scene(board, 840, 900);

		// add to stage
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Solitaire");

		// show the stage
		primaryStage.show();
	}

	// put deck in correct spot
	public boolean moveDeck(LinkedList<Card> cards, Pane board) {
		board.getChildren().addAll(cards);
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).setX(20);
			cards.get(i).setY(25);
			cards.get(i).setArcWidth(10);
			cards.get(i).setArcHeight(10);
		}
		return true;
	}

	// Set up plateau
	public void movePlateau(LinkedList<Card>[] plateau, Pane board) {
		for (int a = 0; a < 7; a++) {
			board.getChildren().addAll(plateau[a]);
			for (int i = 0; i < plateau[a].size(); i++) {
				plateau[a].get(i).setX(20 + HEIGHT * a);
				plateau[a].get(i).setY(200 + 20 * i);
				plateau[a].get(i).setArcWidth(10);
				plateau[a].get(i).setArcHeight(10);
				if (i == plateau[a].size() - 1) {
					plateau[a].get(i).attachFace();
				}
			}
		}
	}

	// Initialize cache area empty white boxes
	public void initializeBox(Rectangle[] r) {
		for (int a = 0; a < 4; a++) {
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
	}
}