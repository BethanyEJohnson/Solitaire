package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenu extends Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane board = new Pane();
		Scene mainScene = new Scene(board, 300, 300);
		Button blackJack = new Button("Play BlackJack");
		blackJack.setLayoutX(100);
		blackJack.setLayoutY(100);
		Button solitaire = new Button("Play Solitaire");
		solitaire.setLayoutX(100);
		solitaire.setLayoutY(140);
		board.getChildren().addAll(blackJack, solitaire);
		// add to stage
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Main Menu");
		// The following two are button events that let you choose the game
		blackJack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				BlackJackStart b = new BlackJackStart();
				try {
					b.start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		solitaire.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SolitaireStart s = new SolitaireStart();
				try {
					s.start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// show the stage
		primaryStage.show();
		
	}
}