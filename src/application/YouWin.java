package application;

import java.io.File;
import javafx.animation.PathTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.stage.*;

public class YouWin {

	public void start() throws Exception {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);

		Pane congrats = new Pane();
		congrats.setStyle("-fx-background-color: blue");
		congrats.setPrefSize(500, 500);

		File image = new File("congrats.png");
		Image myImage = new Image(image.toURI().toString());
		ImageView congratsImage = new ImageView(myImage);
		congratsImage.setFitHeight(150);
		congratsImage.setFitWidth(200);

		PathTransition pt = new PathTransition();
		pt.setNode(congratsImage);
		Polyline straightAway = new Polyline(10, 10, 250, 150);
		pt.setPath(straightAway);
		pt.setCycleCount(0);
		pt.setDuration(Duration.millis(8500));
		pt.play();

		Label message = new Label("CONGRATULATIONS PLAYER, YOU WIN THE GAME!!");
		message.setTextFill(Color.AQUAMARINE);
		message.setLayoutX(100);
		message.setLayoutY(50);

		congrats.getChildren().addAll(message, congratsImage);

		VBox window = new VBox();
		window.getChildren().addAll(congrats);

		Scene scene = new Scene(window, 500, 300);
		popupwindow.setTitle("Congratulations!!!!");
		popupwindow.setScene(scene);
		popupwindow.showAndWait();
	}
}