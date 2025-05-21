package controller;

import engine.Game;
import engine.board.Cell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import view.CardView;
import view.CellView;
import view.SideCardView;
import view.TopCardView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.card.Card;
import model.card.standard.Standard;
import model.card.standard.Suit;
import model.card.wild.Burner;
import model.card.wild.Saver;
import model.player.Marble;

public class MainGameController {

	@FXML private Label nameLabel1;
	@FXML private Label nameLabel2;
	@FXML private Label nameLabel3;
	@FXML private Label nameLabel4;
	
	@FXML private Pane PlayerPane1;
	@FXML private Pane PlayerPane2;
	@FXML private Pane PlayerPane3;
	@FXML private Pane PlayerPane4;

	@FXML private Pane boardPane;
	
	@FXML private GridPane board;

	@FXML private Pane boardTrackPane;
	@FXML private HBox playerBox;
	@FXML private VBox CPU1Box;
	@FXML private HBox CPU2Box;
	@FXML private VBox CPU3Box;

	private List<Pane> trackPanes = new ArrayList<>();

	
	private StackPane cards=new StackPane();

	private Game game;

	public void startGame(String username) throws IOException {
		game = new Game(username);

		displayName(nameLabel1, username);
		displayName(nameLabel2, "CPU1");
		displayName(nameLabel3, "CPU2");
		displayName(nameLabel4, "CPU3");
		
		PlayerPane1.setStyle("-fx-background-color: " + game.getPlayers().get(0).getColour().name().toLowerCase() +";");
		PlayerPane2.setStyle("-fx-background-color: " + game.getPlayers().get(1).getColour().name().toLowerCase() +";");
		PlayerPane3.setStyle("-fx-background-color: " + game.getPlayers().get(2).getColour().name().toLowerCase() +";");
		PlayerPane4.setStyle("-fx-background-color: " + game.getPlayers().get(3).getColour().name().toLowerCase() +";");


		initializeBoard();
		
		displayCards();

		
		
	}

	private void displayName(Label label, String name) {
		label.setText(name);
	}

	@FXML
	public void initialize() {
		for (int i = 0; i < 100; i++) {
			Pane pane = (Pane) board.lookup("#cellPane_" + i);
			if (pane != null) {
				trackPanes.add(pane);
			} else {
				System.err.println("Missing: cellPane_" + i);
			}
		}
	}

	public void initializeBoard() {
		List<Cell> cells = game.getBoard().getTrack(); // Assuming 100 cells

		for (int i = 0; i < 100; i++) {
			CellView cellView = new CellView(cells.get(i));
			Pane cellPane = trackPanes.get(i);
			cellPane.getChildren().clear();
			cellPane.getChildren().add(cellView);
		}
	}
	
	public void displayCards(){


		for(Card card : this.game.getPlayers().get(0).getHand() ){
			CardView cardView = new CardView(card , true);
			playerBox.getChildren().add(cardView);
		}
		for(Card card : this.game.getPlayers().get(1).getHand() ){
			CardView cardView = new CardView(card , false);
			cardView.setRotate(270);
			CPU1Box.getChildren().add(cardView);
		}
		for(Card card : this.game.getPlayers().get(2).getHand() ){
			CardView cardView = new CardView(card, false);
			cardView.setRotate(180);
			CPU2Box.getChildren().add(cardView);
		}
		for(Card card : this.game.getPlayers().get(3).getHand() ){
			CardView cardView = new CardView(card, false);
			cardView.setRotate(90);
			CPU3Box.getChildren().add(cardView);
		}
		playerBox.setSpacing(10);
		CPU2Box.setSpacing(10);


		StackPane.setAlignment(playerBox, javafx.geometry.Pos.BOTTOM_CENTER);
		StackPane.setAlignment(CPU1Box, javafx.geometry.Pos.CENTER_RIGHT);
		StackPane.setAlignment(CPU2Box, javafx.geometry.Pos.TOP_CENTER);
		StackPane.setAlignment(CPU3Box, javafx.geometry.Pos.CENTER_LEFT);

	}
	public void displayFirePit(Card card){
		if(card != null){
			if(firePitArea.getChildren().size() != 0)
			firePitArea.getChildren().clear();
			FirePitView firepitview=new FirePitView(card);
			firePitArea.getChildren().add(firepitview);
			StackPane.setAlignment(firepitview, javafx.geometry.Pos.CENTER);

		}
		else{
			FirePitView firepitview=new FirePitView();
			firePitArea.getChildren().add(firepitview);
			StackPane.setAlignment(firepitview, javafx.geometry.Pos.CENTER);
		}
	}
	//The Actions
	public static VBox getCardFun(Card card){
		VBox func=new VBox();
		if (card instanceof Standard) {
			
			int rank = ((Standard) card).getRank();
			if(rank == 1 || rank== 7|| rank == 10 || rank == 11 ||
					rank == 12){
				Button b1=new Button("Move"+rank);
				b1.setOnAction(new EventHandler<ActionEvent>() {
				    @Override
				    public void handle(ActionEvent event) {
				        movemarble(rank);
				    }
				});
				func.getChildren().add(b1);
			}
			switch (rank) {
			case 13:
				Button b7=new Button("Move 13 steps and destroy all");
				b7.setOnAction(new EventHandler<ActionEvent>() {
				    @Override
				    public void handle(ActionEvent event) {
				        moveking();
				    }
				});
				func.getChildren().add(b7);
				//no break since it share field marble with ace
			case 1:
				Button b2=new Button("Field a marble");
				b2.setOnAction(new EventHandler<ActionEvent>() {
				    @Override
				    public void handle(ActionEvent event) {
				        fieldmarble();
				    }
				});
				func.getChildren().add(b2);
				break;
			case 7:
				Button b3=new Button("Move 2 marbles 7 steps");
				b3.setOnAction(new EventHandler<ActionEvent>() {
				    @Override
				    public void handle(ActionEvent event) {
				    	movetwomarbles();
				    }
				});
				func.getChildren().add(b3);
				break;
			case 10:
				Button b4=new Button("Discard a card from next player");
				b4.setOnAction(new EventHandler<ActionEvent>() {
				    @Override
				    public void handle(ActionEvent event) {
				    	discardcard(false);
				    }
				});
				func.getChildren().add(b4);
				break;
			case 11:
				Button b5=new Button("Swap");
				b5.setOnAction(new EventHandler<ActionEvent>() {
				    @Override
				    public void handle(ActionEvent event) {
				    	swaptwomarbles();
				    }
				});
				func.getChildren().add(b5);
				break;
			case 12:
				Button b6=new Button("Discard a card from random player");
				b6.setOnAction(new EventHandler<ActionEvent>() {
				    @Override
				    public void handle(ActionEvent event) {
				    	discardcard(true);
				    }
				});
				func.getChildren().add(b6);
				break;
			default:
				break;
			}
		}
		return func;
	}
	public static void movemarble(int moves){
		movemarble(marble,moves); //put the chosen marble by current player
	}
	public static void movemarble(Marble marble,int moves){
		
	}
	public static void fieldmarble(){
		
	}
	public static void movetwomarbles(){
		
	}
	public static void swaptwomarbles(){
		
	}
	public static void discardcard(boolean israndom){
		//the boolean show the type af discarding
	}
	public static void moveking(){
		
	}
	public static void movefour(){
		
	}
	public static void movefive(){
		
	}
	public static void burneraction(){
		
	}
	public static void saveraction(){
		
	}
	public static void cardaction(Card card){
		if (card instanceof Standard) {
			int rank = ((Standard) card).getRank();

			switch (rank) {
			case 1 :
			case 7 :
			case 10:
			case 11:
			case 12:
			case 13:
				VBox actions=getCardFun(card);
				break;
			case 2:
			case 3:
			case 6:
			case 8:
			case 9:
				movemarble(rank);
				break;
			case 4:
				movefour();
			case 5:
				movefive();
			default:
				break;
			}
		}
		else if(card instanceof Burner){
			burneraction();
		}
		else if(card instanceof Saver){
			saveraction();
		}
		else
			System.out.println("Null!!!");
	}
}