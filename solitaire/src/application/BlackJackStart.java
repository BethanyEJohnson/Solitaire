package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BlackJackStart extends Application {
	Pane board = new Pane();
	Card cardEx = new Card();
	final int WIDTH = cardEx.WIDTH;
	final int HEIGHT = cardEx.HEIGHT;
	DeckOfCards dc;
	WinStatus win;

	public void startGame() {
		launch();
	}

	public void start(Stage primaryStage) throws Exception {
		board = new Pane();
		board.setStyle("-fx-background-color: green");
		int c = 0;
		Button newGame = new Button("New Game");
		Button hit = new Button("Hit");
		Button one = new Button("Bet $1");
		Button two = new Button("Bet $2");
		Button three = new Button("Bet $3");
		Button stand = new Button("Stand");
		Rectangle[] boxes = new Rectangle[2];
		hit.setLayoutX(10);
		hit.setLayoutY(250);
		one.setLayoutX(10);
		one.setLayoutY(300);
		two.setLayoutX(10);
		two.setLayoutY(350);
		three.setLayoutX(10);
		three.setLayoutY(400);
		stand.setLayoutX(10);
		stand.setLayoutY(450);
		// Button to start game
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
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
		primaryStage.setTitle("Solitaire");

		// show the stage
		primaryStage.show();
	}
	
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
