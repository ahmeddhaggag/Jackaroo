package view;

import model.Colour;
import model.player.Player;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HomeZoneView extends GridPane {

    public HomeZoneView(Player player) {
        setHgap(10); // horizontal space between cells
        setVgap(10); // vertical space between cells
        setAlignment(Pos.CENTER);

        int marbleIndex = 0;
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                StackPane cell = createCell(player.getColour());
                add(cell, col, row);
                marbleIndex++;
            }
        }

        setStyle("-fx-padding: 15; -fx-border-color: black;");
    }

    private StackPane createCell(Colour colour) {
        StackPane cell = new StackPane();
        cell.setPrefSize(60, 60); // cell size
        cell.setStyle("-fx-border-color: gray; -fx-border-width: 2;");

        Circle marble = new Circle(15); // radius
        cell.getChildren().add(marble);

        return cell;
    }
}
