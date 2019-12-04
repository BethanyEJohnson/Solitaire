package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//This class defines a section of the solitaire board (deck,cache,plateau). This is the parent class of the strategy pattern.
public class Section {
	Pane game;
	Stack[] stacks;
	int numSlots;
	Rectangle[] slots = new Rectangle[numSlots];
	double[] startCoord = new double[2];
	
	public Section(Pane game) {
		this.game = game;
	}


	public Stack[] getStacks() {
		return stacks;
	}

	public void setStacks(Stack[] stacks) {
		this.stacks = stacks;
	}

	public int getNumSlots() {
		return numSlots;
	}

	public void setNumSlots(int numSlots) {
		this.numSlots = numSlots;
	}

	public Rectangle[] getSlots() {
		return slots;
	}


	public void setSlots(Rectangle[] slots) {
		this.slots = slots;
	}

	public Pane getGame() {
		return game;
	}

	public void setGame(Pane game) {
		this.game = game;
	}

	public double[] getStartCoord() {
		return startCoord;
	}

	public void setStartCoord(double[] startCoord) {
		this.startCoord = startCoord;
	}

	//makes the appropriate white boxes on the board, based on the section type
	public void makeSlots(Pane game) {
		for(int i = 0; i<numSlots; i++) {
			slots[i] = new Rectangle();
			slots[i].setX(startCoord[0] + i*100);
			slots[i].setY(startCoord[1]);
			slots[i].setWidth(80);
			slots[i].setHeight(120);
			slots[i].setStroke(Color.WHITE);
			slots[i].setArcHeight(10);
			slots[i].setArcWidth(10);
			game.getChildren().add(slots[i]);
		}
	}
}
