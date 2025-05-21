package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Colour;
import model.player.Marble;

public class MarbleView extends Circle {

    private final Marble marble;

    public MarbleView(Marble marble) {
        super(15); // radius of marble circle
        this.marble = marble;
        this.setFill(convertColour(marble.getColour()));
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(2);
    }

    private Color convertColour(Colour colour) {
        return switch (colour) {
            case RED : Color.RED;
            case BLUE : Color.BLUE;
            case GREEN : Color.GREEN;
            case YELLOW : Color.YELLOW;
        };
    }

    public Marble getMarble() {
        return marble;
    }
}