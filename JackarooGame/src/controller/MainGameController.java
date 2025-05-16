package controller;

import engine.Game;
import engine.board.Cell;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import view.CellView;

import java.io.IOException;
import java.util.List;

public class MainGameController {

    @FXML private Label nameLabel1;
    @FXML private Label nameLabel2;
    @FXML private Label nameLabel3;
    @FXML private Label nameLabel4;

    @FXML private Pane boardPane;

    // 10 manually defined cell panes (from FXML)
    @FXML private Pane cellPane_0;
    @FXML private Pane cellPane_1;
    @FXML private Pane cellPane_2;
    @FXML private Pane cellPane_3;
    @FXML private Pane cellPane_4;
    @FXML private Pane cellPane_5;
    @FXML private Pane cellPane_6;
    @FXML private Pane cellPane_7;
    @FXML private Pane cellPane_8;
    @FXML private Pane cellPane_9;

    private Game game;

    public void startGame(String username) throws IOException {
        game = new Game(username);

        displayName(nameLabel1, username);
        displayName(nameLabel2, "CPU1");
        displayName(nameLabel3, "CPU2");
        displayName(nameLabel4, "CPU3");

        initializeBoard();
    }

    private void displayName(Label label, String name) {
        label.setText(name);
    }

    private void initializeBoard() {
    	List<Pane> paneList = java.util.Arrays.asList(
    		    cellPane_0, cellPane_1, cellPane_2, cellPane_3, cellPane_4,
    		    cellPane_5, cellPane_6, cellPane_7, cellPane_8, cellPane_9
    		);

        for (int i = 0; i < paneList.size(); i++) {
            Cell modelCell = game.getBoard().getTrack().get(i); // adjust index logic if needed
            CellView cellView = new CellView(modelCell);
            paneList.get(i).getChildren().clear();
            paneList.get(i).getChildren().add(cellView);
        }
    }
}
