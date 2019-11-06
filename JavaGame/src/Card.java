import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Card {

	private String suit;
	private String face;

	private BufferedImage suitImg;

	private Color color;
	private Font font;

	private int cornerX, cornerY;
	private int rightX;
	private int bottomY;

	public static final int HEIGHT = 125, WIDTH = 85;

	/**
	 * All possible suits a card may have
	 */
	public static final String[] SUITS = { "S", "H", "D", "C" };

	/**
	 * All possible face values a card may have
	 */
	public static final String[] FACES = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	public boolean faceDown;

	public Card() {
		this("A", "S");
	}

	public Card(String suit, String face) {
		this(suit, face, 0, 0);
	}

	public Card(String face, String suit, int x, int y) {
		this.face = face;
		this.suit = suit;
		setLocation(x, y);
		if (!initImage()) {
			// TODO: quit the game here somehow
		}
		faceDown = true;
		font = new Font("Courier New", Font.BOLD, 20);
	}

	/**
	 * Draws the card to a graphics context
	 * 
	 * @param g the graphics context to draw the card on
	 */
	public void draw(Graphics g) {
		if (!faceDown) {
			g.setColor(Color.white);
			g.fillRoundRect(cornerX, cornerY, WIDTH, HEIGHT, 10, 10);
			g.setColor(color);
			g.setFont(font);
			g.drawString(face, cornerX + 3, cornerY + 20);
			g.drawImage(suitImg, cornerX + 3, cornerY + 25, null);
		} else { // draw the back of the card
			g.setColor(new Color(0, 125, 150));
			g.fillRoundRect(cornerX, cornerY, WIDTH, HEIGHT, 10, 10);
		}
		g.setColor(Color.black);
		g.drawRoundRect(cornerX, cornerY, WIDTH, HEIGHT, 10, 10);
	}

	/**
	 * Initializes the suit and face images of the card depending on what suit and
	 * face the card is
	 * 
	 * @return whether the image initialization was successful
	 */
	private boolean initImage() {
		boolean success = true;
		try {
			switch (suit) {
			case "S":
				suitImg = ImageIO.read(new File("images/spade.png"));
				color = Color.black;
				break;
			case "H":
				suitImg = ImageIO.read(new File("images/heart.png"));
				color = Color.red;
				break;
			case "D":
				suitImg = ImageIO.read(new File("images/diamond.png"));
				color = Color.red;
				break;
			case "C":
				suitImg = ImageIO.read(new File("images/club.png"));
				color = Color.black;
				break;
			default: // should be impossible
				success = false;
			}
		} catch (IOException ioex) {
			System.out.println("Error reading image.");
			success = false;
		}

		return success;
	}

	/**
	 * @return the card suit
	 */
	public String getSuit() {
		return suit;
	}

	/**
	 * @return the card face
	 */
	public String getFace() {
		return face;
	}

	/**
	 * Returns the index in the faces array of the passed in the string
	 * 
	 * @param s the string to check
	 * @return the index in the faces array of s or -1 if not found
	 */
	public static int getFaceIndex(String s) {
		for (int i = 0; i < FACES.length; i++)
			if (s.equals(FACES[i]))
				return i;

		return -1;
	}

	/**
	 * Returns the index in the suits array of the passed in the string
	 * 
	 * @param s the string to check
	 * @return the index in the suits array of s or -1 if not found
	 */
	public static int getSuitIndex(String s) {
		for (int i = 0; i < SUITS.length; i++)
			if (s.equals(SUITS[i]))
				return i;

		return -1;
	}

	/**
	 * @return the top left x coordinate of the card
	 */
	public int getX() {
		return cornerX;
	}

	/**
	 * @return the top left y coordinate of the card
	 */
	public int getY() {
		return cornerY;
	}

	/**
	 * @return the x coordinate of the right side of the card
	 */
	public int getRightX() {
		return rightX;
	}

	/**
	 * @return the y coordinate of the bottom of the card
	 */
	public int getBottomY() {
		return bottomY;
	}

	/**
	 * Sets the top left corner of the card to (x, y)
	 * 
	 * @param x the top-left x coordinate of the card
	 * @param y the top-left y coordinate of the card
	 */
	public void setLocation(int x, int y) {
		cornerX = x;
		cornerY = y;
		assignVertices();
	}

	/**
	 * Checks whether the suit and face of two cards match
	 * 
	 * @param c the card to check for equality
	 * @return whether this card and c have the same suit and face
	 */
	public boolean equals(Card c) {
		return c.getSuit().equals(this.suit) && c.getFace().equals(this.face);
	}

	/**
	 * Assigns the rightX and bottomY vertices based on the top left corner location
	 */
	private void assignVertices() {
		rightX = cornerX + WIDTH;
		bottomY = cornerY + HEIGHT;
	}

	/**
	 * @return the color of the card
	 */
	public Color getColor() {
		return color;
	}

}