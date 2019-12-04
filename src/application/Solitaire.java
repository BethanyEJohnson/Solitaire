package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Solitaire extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception{
		
		GridPane menu = new GridPane();
		menu.setStyle("-fx-background-color: black;");
		menu.setAlignment(Pos.CENTER);
		menu.setVgap(10.0);
		menu.setPrefSize(1000,50);
		
		Pane game = new Pane();
		game.setStyle("-fx-background-color: blue");
		game.setPrefSize(1000,950);
		
		VBox window = new VBox();
		window.getChildren().addAll(menu,game);
		
		Section cache = new Section(game);
		cache.makeSlots(game);
		
		Scene scene = new Scene(window, 1000, 1000);
		primaryStage.setTitle("OOD Solitaire");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
