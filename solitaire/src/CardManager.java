package application;

import java.io.File;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class CardManager extends Application{
	
	public static void main(String[] args) {
		launch();
		
		
	}
	
	public void start(Stage primaryStage) throws Exception {
		Pane board = new Pane();
		board.setStyle("-fx-background-color: green");
		
		Rectangle boxOne = new Rectangle();
		boxOne.setX(380);
		boxOne.setY(25);
		boxOne.setWidth(100);
		boxOne.setHeight(130);
		boxOne.setArcWidth(10);
		boxOne.setArcHeight(10);
		boxOne.setFill(Color.GREEN);
		boxOne.setStroke(Color.WHITESMOKE);
		
		Rectangle boxTwo = new Rectangle();
		boxTwo.setX(500);
		boxTwo.setY(25);
		boxTwo.setWidth(100);
		boxTwo.setHeight(130);
		boxTwo.setArcWidth(10);
		boxTwo.setArcHeight(10);
		boxTwo.setFill(Color.GREEN);
		boxTwo.setStroke(Color.WHITESMOKE);
		
		Rectangle boxThree = new Rectangle();
		boxThree.setX(620);
		boxThree.setY(25);
		boxThree.setWidth(100);
		boxThree.setHeight(130);
		boxThree.setArcWidth(10);
		boxThree.setArcHeight(10);
		boxThree.setFill(Color.GREEN);
		boxThree.setStroke(Color.WHITESMOKE);
		
		Rectangle boxFour = new Rectangle();
		boxFour.setX(740);
		boxFour.setY(25);
		boxFour.setWidth(100);
		boxFour.setHeight(130);
		boxFour.setArcWidth(10);
		boxFour.setArcHeight(10);
		boxFour.setFill(Color.GREEN);
		boxFour.setStroke(Color.WHITESMOKE);
		
		
		
	    File AceCfile = new File("src/cards/AC.jpg");
	    Image AceCimage = new Image(AceCfile.toURI().toString(), 100, 130, false, false);
	    ImageView AceClubs = new ImageView(AceCimage);
		
		File TwoCfile = new File("src/cards/2C.jpg");
	    Image TwoCimage = new Image(TwoCfile.toURI().toString(), 100, 130, false, false);
	    ImageView TwoClubs = new ImageView(TwoCimage);

	    File ThreeCfile = new File("src/cards/3C.jpg");
	    Image ThreeCimage = new Image(ThreeCfile.toURI().toString(), 100, 130, false, false);
	    ImageView ThreeClubs = new ImageView(ThreeCimage);
	    
	    File FourCfile = new File("src/cards/4C.jpg");
	    Image FourCimage = new Image(FourCfile.toURI().toString(), 100, 130, false, false);
	    ImageView FourClubs = new ImageView(FourCimage);
	    
	    File FiveCfile = new File("src/cards/5C.jpg");
	    Image FiveCimage = new Image(FiveCfile.toURI().toString(), 100, 130, false, false);
	    ImageView FiveClubs = new ImageView(FiveCimage);
	    
	    File SixCfile = new File("src/cards/6C.jpg");
	    Image SixCimage = new Image(SixCfile.toURI().toString(), 100, 130, false, false);
	    ImageView SixClubs = new ImageView(SixCimage);
	    
	    File SevenCfile = new File("src/cards/7C.jpg");
	    Image SevenCimage = new Image(SevenCfile.toURI().toString(), 100, 130, false, false);
	    ImageView SevenClubs = new ImageView(SevenCimage);
	    
	    File EightCfile = new File("src/cards/8C.jpg");
	    Image EightCimage = new Image(EightCfile.toURI().toString(), 100, 130, false, false);
	    ImageView EightClubs = new ImageView(EightCimage);
	    
	    File NineCfile = new File("src/cards/9C.jpg");
	    Image NineCimage = new Image(NineCfile.toURI().toString(), 100, 130, false, false);
	    ImageView NineClubs = new ImageView(NineCimage);
	    
	    File TenCfile = new File("src/cards/9C.jpg");
	    Image TenCimage = new Image(TenCfile.toURI().toString(), 100, 130, false, false);
	    ImageView TenClubs = new ImageView(TenCimage);
	    
	    File JackCfile = new File("src/cards/JC.jpg");
	    Image JackCimage = new Image(JackCfile.toURI().toString(), 100, 130, false, false);
	    ImageView JackClubs = new ImageView(JackCimage);
		
	    File QueenCfile = new File("src/cards/QC.jpg");
	    Image QueenCimage = new Image(QueenCfile.toURI().toString(), 100, 130, false, false);
	    ImageView QueenClubs = new ImageView(QueenCimage);

	    File KingCfile = new File("src/cards/KC.jpg");
	    Image KingCimage = new Image(KingCfile.toURI().toString(), 100, 130, false, false);
	    ImageView KingClubs = new ImageView(KingCimage);
	    
	    File AceSfile = new File("src/cards/AS.jpg");
	    Image AceSimage = new Image(AceSfile.toURI().toString(), 100, 130, false, false);
	    ImageView AceSpades = new ImageView(AceSimage);
	    
	    File TwoSfile = new File("src/cards/2S.jpg");
	    Image TwoSimage = new Image(TwoSfile.toURI().toString(), 100, 130, false, false);
	    ImageView TwoSpades = new ImageView(TwoSimage);

	    File ThreeSfile = new File("src/cards/3S.jpg");
	    Image ThreeSimage = new Image(ThreeSfile.toURI().toString(), 100, 130, false, false);
	    ImageView ThreeSpades = new ImageView(ThreeSimage);
	    
	    File FourSfile = new File("src/cards/4S.jpg");
	    Image FourSimage = new Image(FourSfile.toURI().toString(), 100, 130, false, false);
	    ImageView FourSpades = new ImageView(FourSimage);
	    
	    File FiveSfile = new File("src/cards/5S.jpg");
	    Image FiveSimage = new Image(FiveSfile.toURI().toString(), 100, 130, false, false);
	    ImageView FiveSpades = new ImageView(FiveSimage);
	    
	    File SixSfile = new File("src/cards/6S.jpg");
	    Image SixSimage = new Image(SixSfile.toURI().toString(), 100, 130, false, false);
	    ImageView SixSpades = new ImageView(SixSimage);
	    
	    File SevenSfile = new File("src/cards/7S.jpg");
	    Image SevenSimage = new Image(SevenSfile.toURI().toString(), 100, 130, false, false);
	    ImageView SevenSpades = new ImageView(SevenSimage);
	    
	    File EightSfile = new File("src/cards/8S.jpg");
	    Image EightSimage = new Image(EightSfile.toURI().toString(), 100, 130, false, false);
	    ImageView EightSpades = new ImageView(EightSimage);
	    
	    File NineSfile = new File("src/cards/9S.jpg");
	    Image NineSimage = new Image(NineSfile.toURI().toString(), 100, 130, false, false);
	    ImageView NineSpades = new ImageView(NineSimage);
	    
	    File TenSfile = new File("src/cards/9S.jpg");
	    Image TenSimage = new Image(TenSfile.toURI().toString(), 100, 130, false, false);
	    ImageView TenSpades = new ImageView(TenSimage);
	    
	    File JackSfile = new File("src/cards/JS.jpg");
	    Image JackSimage = new Image(JackSfile.toURI().toString(), 100, 130, false, false);
	    ImageView JackSpades = new ImageView(JackSimage);
		
	    File QueenSfile = new File("src/cards/QS.jpg");
	    Image QueenSimage = new Image(QueenSfile.toURI().toString(), 100, 130, false, false);
	    ImageView QueenSpades = new ImageView(QueenSimage);

	    File KingSfile = new File("src/cards/KS.jpg");
	    Image KingSimage = new Image(KingSfile.toURI().toString(), 100, 130, false, false);
	    ImageView KingSpades = new ImageView(KingSimage);
	    
	    File AceDfile = new File("src/cards/AD.jpg");
	    Image AceDimage = new Image(AceDfile.toURI().toString(), 100, 130, false, false);
	    ImageView AceDiamonds = new ImageView(AceDimage);
	    
	    File TwoDfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/2D.jpg");
	    Image TwoDimage = new Image(TwoDfile.toURI().toString(), 100, 130, false, false);
	    ImageView TwoDiamonds = new ImageView(TwoDimage);

	    File ThreeDfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/3D.jpg");
	    Image ThreeDimage = new Image(ThreeDfile.toURI().toString(), 100, 130, false, false);
	    ImageView ThreeDiamonds = new ImageView(ThreeDimage);
	    
	    File FourDfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/4D.jpg");
	    Image FourDimage = new Image(FourDfile.toURI().toString(), 100, 130, false, false);
	    ImageView FourDiamonds = new ImageView(FourDimage);
	    
	    File FiveDfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/5D.jpg");
	    Image FiveDimage = new Image(FiveDfile.toURI().toString(), 100, 130, false, false);
	    ImageView FiveDiamonds = new ImageView(FiveDimage);
	    
	    File SixDfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/6D.jpg");
	    Image SixDimage = new Image(SixDfile.toURI().toString(), 100, 130, false, false);
	    ImageView SixDiamonds = new ImageView(SixDimage);
	    
	    File SevenDfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/7D.jpg");
	    Image SevenDimage = new Image(SevenDfile.toURI().toString(), 100, 130, false, false);
	    ImageView SevenDiamonds = new ImageView(SevenDimage);
	    
	    File EightDfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/8D.jpg");
	    Image EightDimage = new Image(EightDfile.toURI().toString(), 100, 130, false, false);
	    ImageView EightDiamonds = new ImageView(EightDimage);
	    
	    File NineDfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/9D.jpg");
	    Image NineDimage = new Image(NineDfile.toURI().toString(), 100, 130, false, false);
	    ImageView NineDiamonds = new ImageView(NineDimage);
	    
	    File TenDfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/9D.jpg");
	    Image TenDimage = new Image(TenDfile.toURI().toString(), 100, 130, false, false);
	    ImageView TenDiamonds = new ImageView(TenDimage);
	    
	    File JackDfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/JD.jpg");
	    Image JackDimage = new Image(JackDfile.toURI().toString(), 100, 130, false, false);
	    ImageView JackDiamonds = new ImageView(JackDimage);
		
	    File QueenDfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/QD.jpg");
	    Image QueenDimage = new Image(QueenDfile.toURI().toString(), 100, 130, false, false);
	    ImageView QueenDiamonds = new ImageView(QueenDimage);

	    File KingDfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/KD.jpg");
	    Image KingDimage = new Image(KingDfile.toURI().toString(), 100, 130, false, false);
	    ImageView KingDiamonds = new ImageView(KingDimage);
	    
	    File AceHfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/AH.jpg");
	    Image AceHimage = new Image(AceHfile.toURI().toString(), 100, 130, false, false);
	    ImageView AceHearts = new ImageView(AceHimage);
	    
	    File TwoHfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/2H.jpg");
	    Image TwoHimage = new Image(TwoHfile.toURI().toString(), 100, 130, false, false);
	    ImageView TwoHearts = new ImageView(TwoHimage);

	    File ThreeHfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/3H.jpg");
	    Image ThreeHimage = new Image(ThreeHfile.toURI().toString(), 100, 130, false, false);
	    ImageView ThreeHearts = new ImageView(ThreeHimage);
	    
	    File FourHfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/4H.jpg");
	    Image FourHimage = new Image(FourHfile.toURI().toString(), 100, 130, false, false);
	    ImageView FourHearts = new ImageView(FourHimage);
	    
	    File FiveHfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/5H.jpg");
	    Image FiveHimage = new Image(FiveHfile.toURI().toString(), 100, 130, false, false);
	    ImageView FiveHearts = new ImageView(FiveHimage);
	    
	    File SixHfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/6H.jpg");
	    Image SixHimage = new Image(SixHfile.toURI().toString(), 100, 130, false, false);
	    ImageView SixHearts = new ImageView(SixHimage);
	    
	    File SevenHfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/7H.jpg");
	    Image SevenHimage = new Image(SevenHfile.toURI().toString(), 100, 130, false, false);
	    ImageView SevenHearts = new ImageView(SevenHimage);
	    
	    File EightHfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/8H.jpg");
	    Image EightHimage = new Image(EightHfile.toURI().toString(), 100, 130, false, false);
	    ImageView EightHearts = new ImageView(EightHimage);
	    
	    File NineHfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/9H.jpg");
	    Image NineHimage = new Image(NineHfile.toURI().toString(), 100, 130, false, false);
	    ImageView NineHearts = new ImageView(NineHimage);
	    
	    File TenHfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/10H.jpg");
	    Image TenHimage = new Image(TenHfile.toURI().toString(), 100, 130, false, false);
	    ImageView TenHearts = new ImageView(TenHimage);
	    
	    File JackHfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/JH.jpg");
	    Image JackHimage = new Image(JackHfile.toURI().toString(), 100, 130, false, false);
	    ImageView JackHearts = new ImageView(JackHimage);
		
	    File QueenHfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/QH.jpg");
	    Image QueenHimage = new Image(QueenHfile.toURI().toString(), 100, 130, false, false);
	    ImageView QueenHearts = new ImageView(QueenHimage);

	    File KingHfile = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/KH.jpg");
	    Image KingHimage = new Image(KingHfile.toURI().toString(), 100, 130, false, false);
	    ImageView KingHearts = new ImageView(KingHimage);
	    
	    File backCard = new File("C:/Users/Camm/eclipse-workspace/Solitaire/src/cards/Gray_back.jpg");
	    Image backCardImage = new Image(backCard.toURI().toString(), 100, 130, false, false);
	    ImageView backCardAll = new ImageView(backCardImage);

	   
		
		Button startGame = new Button("Start Game");



		//add to container
		board.getChildren().addAll(
			boxOne, boxTwo, boxThree, boxFour,
			AceDiamonds,TwoDiamonds,ThreeDiamonds,FourDiamonds,FiveDiamonds,
			SixDiamonds,SevenDiamonds,EightDiamonds,NineDiamonds,
			TenDiamonds,JackDiamonds,QueenDiamonds,KingDiamonds,
			AceHearts,TwoHearts,ThreeHearts,FourHearts,FiveHearts,
			SixHearts,SevenHearts,EightHearts,NineHearts,
			TenHearts,JackHearts,QueenHearts,KingHearts,
			AceSpades,TwoSpades,ThreeSpades,FourSpades,FiveSpades,
			SixSpades,SevenSpades,EightSpades,NineSpades,
			TenSpades,JackSpades,QueenSpades,KingSpades,
			AceClubs,TwoClubs,ThreeClubs,FourClubs,FiveClubs,
			SixClubs,SevenClubs,EightClubs,NineClubs,
			TenClubs,JackClubs,QueenClubs,KingClubs,
			backCardAll,
			startGame
		);
		
		ImageView [] cards = {
			AceDiamonds,TwoDiamonds,ThreeDiamonds,FourDiamonds,FiveDiamonds,
			SixDiamonds,SevenDiamonds,EightDiamonds,NineDiamonds,
			TenDiamonds,JackDiamonds,QueenDiamonds,KingDiamonds,
			AceHearts,TwoHearts,ThreeHearts,FourHearts,FiveHearts,
			SixHearts,SevenHearts,EightHearts,NineHearts,
			TenHearts,JackHearts,QueenHearts,KingHearts,
			AceSpades,TwoSpades,ThreeSpades,FourSpades,FiveSpades,
			SixSpades,SevenSpades,EightSpades,NineSpades,
			TenSpades,JackSpades,QueenSpades,KingSpades,
			AceClubs,TwoClubs,ThreeClubs,FourClubs,FiveClubs,
			SixClubs,SevenClubs,EightClubs,NineClubs,
			TenClubs,JackClubs,QueenClubs,KingClubs
		};
		
		ImageView [] cardBacks = new ImageView [52];
		for(int i = 0; i < 52; i++) {
			cardBacks[i] = backCardAll;
		}
		
		startGame.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				startGame.setVisible(false);
				distributeCards(cards, cardBacks);
			}
		});

		//create scene
		Scene mainScene = new Scene(board, 900, 800);
		
		//add to stage
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Solitaire");
		
		//show the stage
		primaryStage.show();
	}
	
	public void distributeCards(ImageView [] cards, ImageView [] cardBacks) {
		
		//reference array 
		Point2D r1p1 = new Point2D(70, 260);
		Point2D r2p1 = new Point2D(190, 260);
		Point2D r2p2 = new Point2D(190, 300);
		Point2D r3p1 = new Point2D(310, 260);
		Point2D r3p2 = new Point2D(310, 300);
		Point2D r3p3 = new Point2D(310, 340);
		Point2D r4p1 = new Point2D(430, 260);
		Point2D r4p2 = new Point2D(430, 300);
		Point2D r4p3 = new Point2D(430, 340);
		Point2D r4p4 = new Point2D(430, 380);
		Point2D r5p1 = new Point2D(550, 260);
		Point2D r5p2 = new Point2D(550, 300);
		Point2D r5p3 = new Point2D(550, 340);
		Point2D r5p4 = new Point2D(550, 380);
		Point2D r5p5 = new Point2D(550, 420);
		Point2D r6p1 = new Point2D(670, 260);
		Point2D r6p2 = new Point2D(670, 300);
		Point2D r6p3 = new Point2D(670, 340);
		Point2D r6p4 = new Point2D(670, 380);
		Point2D r6p5 = new Point2D(670, 420);
		Point2D r6p6 = new Point2D(670, 460);
		Point2D r7p1 = new Point2D(790, 260);
		Point2D r7p2 = new Point2D(790, 300);
		Point2D r7p3 = new Point2D(790, 340);
		Point2D r7p4 = new Point2D(790, 380);
		Point2D r7p5 = new Point2D(790, 420);
		Point2D r7p6 = new Point2D(790, 460);
		Point2D r7p7 = new Point2D(790, 500);

		Point2D [] startingPositions = new Point2D [] {
				r1p1,
				r2p1,
				r2p2,
				r3p1,
				r3p2,
				r3p3,
				r4p1,
				r4p2,
				r4p3,
				r4p4,
				r5p1,
				r5p2,
				r5p3,
				r5p4,
				r5p5,
				r6p1,
				r6p2,
				r6p3,
				r6p4,
				r6p5,
				r6p6,
				r7p1,
				r7p2,
				r7p3,
				r7p4,
				r7p5,
				r7p6,
				r7p7,
		};
		
		moveCards(cards, startingPositions, cardBacks);
		
		
	}
	public boolean moveCards(ImageView [] cards, Point2D [] startPoints, ImageView [] backCard) {

		for(int i = 0; i < 28; i++) {
			PathTransition pt = new PathTransition();
			pt.setNode(cards[i]);
			Line cardMovement = new Line();
			cardMovement.setStartX(0); 
			cardMovement.setStartY(0); 
			cardMovement.setEndX(startPoints[i].getX());
			cardMovement.setEndY(startPoints[i].getY());
			pt.setPath(cardMovement);
			pt.setDuration(Duration.millis(400));
			pt.play();
		}
		
		return true;
		
	}
}