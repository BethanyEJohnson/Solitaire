package application;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
//This class defines a card object and its attributes
public class Card extends Rectangle{
	String suit;
	String rank;
	Image front;
	Image back = new Image("src/cards/Gray_back.jpg");
	boolean isFaceUp;
	//section refers to cache, deck or plateau
	String section;
	double[] origin = new double[2];
	double[] destination = new double[2];
	
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
		this.setWidth(80);
		this.setHeight(120);
		this.front = new Image ("src/cards/" + this.rank + this.suit + ".jpg"); 
	}
}
