package controller;

import engine.Game;
import engine.board.Cell;
import engine.board.SafeZone;
import exception.ActionException;
import exception.CannotDiscardException;
import exception.CannotFieldException;
import exception.GameException;
import exception.IllegalDestroyException;
import exception.IllegalMovementException;
import exception.IllegalSwapException;
import exception.InvalidMarbleException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import view.CardView;
import view.CellView;
import view.FirePitView;
import view.HomeZoneView;
import view.SafeZoneView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.card.Card;
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
	@FXML private Pane firePitArea;
	@FXML private GridPane PlayerHomeZone;
	@FXML private GridPane CPU1HomeZone;
	@FXML private GridPane CPU2HomeZone;
	@FXML private GridPane CPU3HomeZone;

	private List<Pane> trackPanes = new ArrayList<>();

	private List<Pane> safePanes1 = new ArrayList<>();
	private List<Pane> safePanes2 = new ArrayList<>();
	private List<Pane> safePanes3 = new ArrayList<>();
	private List<Pane> safePanes4 = new ArrayList<>();
	private List<Pane> HomeZonePanes1 = new ArrayList<>();
	private List<Pane> HomeZonePanes2 = new ArrayList<>();
	private List<Pane> HomeZonePanes3 = new ArrayList<>();
	private List<Pane> HomeZonePanes4 = new ArrayList<>();
	private static ArrayList<Marble> selectedmarbles= new ArrayList<>();

	private Game game;

	public void startGame(String username) throws IOException, GameException {
		game = new Game(username);

		displayName(nameLabel1, username);
		displayName(nameLabel2, "CPU1");
		displayName(nameLabel3, "CPU2");
		displayName(nameLabel4, "CPU3");

		//	PlayerPane1.setStyle("-fx-background-color: " + game.getPlayers().get(0).getColour().name().toLowerCase() +";");
		//PlayerPane2.setStyle("-fx-background-color: " + game.getPlayers().get(1).getColour().name().toLowerCase() +";");
		//PlayerPane3.setStyle("-fx-background-color: " + game.getPlayers().get(2).getColour().name().toLowerCase() +";");
		//PlayerPane4.setStyle("-fx-background-color: " + game.getPlayers().get(3).getColour().name().toLowerCase() +";");


		updateBoard();

		displayCards();
		displayFirePit(game.getPlayers().get(0).getHand().get(0));
		displayHomeZones();

		//		while(game.checkWin() == null){
		//		updateBoard();
		//		
		//		displayCards();
		//		
		//		if(game.canPlayTurn()){
		//			game.playPlayerTurn();
		//			game.endPlayerTurn();
		//		}
		//		}



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
		for (int i = 0; i < 4; i++) {
			Pane pane = (Pane) board.lookup("#safePane1_" + i);
			if (pane != null) {
				safePanes1.add(pane);
			} else {
				System.err.println("Missing: safePane1_" + i);
			}

		}

		for (int i = 0; i < 4; i++) {
			Pane pane = (Pane) board.lookup("#safePane2_" + i);
			if (pane != null) {
				safePanes2.add(pane);
			} else {
				System.err.println("Missing: safePane2_" + i);
			}


		}

		for (int i = 0; i < 4; i++) {
			Pane pane = (Pane) board.lookup("#safePane3_" + i);
			if (pane != null) {
				safePanes3.add(pane);
			} else {
				System.err.println("Missing: safePane3_" + i);
			}
		}
		
		for (int i = 0; i < 4; i++) {
			Pane pane = (Pane) board.lookup("#safePane4_" + i);
			if (pane != null) {
				safePanes4.add(pane);
			} else {
				System.err.println("Missing: safePane4_" + i);
			}
		}
		for (int i = 0; i < 4; i++) {
			Pane pane = (Pane) board.lookup("#HomeZonePane1_" + i);
			if (pane != null) {
				HomeZonePanes1.add(pane);
			} else {
				System.err.println("Missing: HomeZonePane1_" + i);
			}
		}
		for (int i = 0; i < 4; i++) {
			Pane pane = (Pane) board.lookup("#HomeZonePane2_" + i);
			if (pane != null) {
				HomeZonePanes2.add(pane);
			} else {
				System.err.println("Missing: HomeZonePane2_" + i);
			}
		}
		for (int i = 0; i < 4; i++) {
			Pane pane = (Pane) board.lookup("#HomeZonePane3_" + i);
			if (pane != null) {
				HomeZonePanes3.add(pane);
			} else {
				System.err.println("Missing: HomeZonePane3_" + i);
			}
		}
		for (int i = 0; i < 4; i++) {
			Pane pane = (Pane) board.lookup("#HomeZonePane4_" + i);
			if (pane != null) {
				HomeZonePanes4.add(pane);
			} else {
				System.err.println("Missing: HomeZonePane4_" + i);
			}
		}
	}

	public void updateBoard() {
		List<Cell> cells = game.getBoard().getTrack(); 
		List<SafeZone> safe = game.getBoard().getSafeZones();
		
		for (int i = 0; i < 100; i++) {
			CellView cellView = new CellView(cells.get(i));
			Pane cellPane = trackPanes.get(i);
			cellPane.getChildren().clear();
			cellPane.getChildren().add(cellView);
		}
		//		
		for (int i = 0; i < 4; i++) {
			SafeZoneView safezoneview = new SafeZoneView(safe.get(0));
			Pane cellPane = safePanes1.get(i);
			cellPane.getChildren().clear();
			cellPane.getChildren().add(safezoneview);
		}

		for (int i = 0; i < 4; i++) {
			SafeZoneView safezoneview = new SafeZoneView(safe.get(1));
			Pane cellPane = safePanes2.get(i);
			cellPane.getChildren().clear();
			cellPane.getChildren().add(safezoneview);
		}
		for (int i = 0; i < 4; i++) {
			SafeZoneView safezoneview = new SafeZoneView(safe.get(2));
			Pane cellPane = safePanes3.get(i);
			cellPane.getChildren().clear();
			cellPane.getChildren().add(safezoneview);
		}
		
		for (int i = 0; i < 4; i++) {
			SafeZoneView safezoneview = new SafeZoneView(safe.get(3));
			Pane cellPane = safePanes4.get(i);
			cellPane.getChildren().clear();
			cellPane.getChildren().add(safezoneview);
		}
		
/*		for (int i = 0; i < 4; i++) {
			CellView cellView = new CellView(cells.get(i));
			Pane cellPane = trackPanes.get(i);
			cellPane.getChildren().clear();
			cellPane.getChildren().add(cellView);
		}

		for (int i = 0; i < 4; i++) {
			CellView cellView = new CellView(cells.get(i));
			Pane cellPane = trackPanes.get(i);
			cellPane.getChildren().clear();
			cellPane.getChildren().add(cellView);
		}
		for (int i = 0; i < 4; i++) {
			CellView cellView = new CellView(cells.get(i));
			Pane cellPane = trackPanes.get(i);
			cellPane.getChildren().clear();
			cellPane.getChildren().add(cellView);
		}
		
		for (int i = 0; i < 4; i++) {
			CellView cellView = new CellView(cells.get(i));
			Pane cellPane = trackPanes.get(i);
			cellPane.getChildren().clear();
			cellPane.getChildren().add(cellView);
		}*/
	}
	public void displayHomeZones(){
		PlayerHomeZone=new HomeZoneView(game.getPlayers().get(0));
		CPU1HomeZone=new HomeZoneView(game.getPlayers().get(1));
		CPU2HomeZone=new HomeZoneView(game.getPlayers().get(2));
		CPU3HomeZone=new HomeZoneView(game.getPlayers().get(3));
	}

	public void displayCards(){


		for (Card card : this.game.getPlayers().get(0).getHand()) {
			CardView cardView = new CardView(card, true, this);
			cardView.setOnMouseClicked(event -> handleCardClick(card));
			playerBox.getChildren().add(cardView);
		}

		for (Card card : this.game.getPlayers().get(1).getHand()) {
			CardView cardView = new CardView(card, false, this);
			cardView.setRotate(270);
			cardView.setOnMouseClicked(event -> handleCardClick(card));
			CPU1Box.getChildren().add(cardView);
		}

		for (Card card : this.game.getPlayers().get(2).getHand()) {
			CardView cardView = new CardView(card, false, this);
			cardView.setRotate(180);
			cardView.setOnMouseClicked(event -> handleCardClick(card));
			CPU2Box.getChildren().add(cardView);
		}

		for (Card card : this.game.getPlayers().get(3).getHand()) {
			CardView cardView = new CardView(card, false, this);
			cardView.setRotate(90);
			cardView.setOnMouseClicked(event -> handleCardClick(card));
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
		FirePitView f=new FirePitView(card);
		this.firePitArea.getChildren().add(f);
	}
	public void cardAction(Card card){
		handleCardClick(card);
		displayFirePit(card);
	}
	public void handleCardClick(Card card) {
		System.out.println("Card clicked: " + card);
		// Add logic for handling the card click event
		try{
			card.act(selectedmarbles);}
		catch(InvalidMarbleException e){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Invalid Marble");
			alert.setHeaderText(null);
			alert.setContentText("cannot select this marble!");
			alert.showAndWait();
		}
		catch(IllegalMovementException e){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Invalid Action");
			alert.setHeaderText(null);
			alert.setContentText("Illegal Movement!");
			alert.showAndWait();
		}
		catch(IllegalSwapException e){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Invalid Action");
			alert.setHeaderText(null);
			alert.setContentText("Illegal Swap!");
			alert.showAndWait();
		}
		catch(IllegalDestroyException e){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Invalid Action");
			alert.setHeaderText(null);
			alert.setContentText("Illegal Destroy!");
			alert.showAndWait();
		}
		catch(CannotFieldException e){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Invalid Action");
			alert.setHeaderText(null);
			alert.setContentText("Cannot field!");
			alert.showAndWait();
		}
		catch(CannotDiscardException e){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Invalid Action");
			alert.setHeaderText(null);
			alert.setContentText("Cannot discard!");
			alert.showAndWait();
		}
		catch(ActionException e){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Invalid Action");
			alert.setHeaderText(null);
			alert.setContentText("Invalid action!");
			alert.showAndWait();
		}
	}


}