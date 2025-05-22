package view;

import controller.MainGameController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
        this.setOnMouseClicked(new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				MainGameController.addmarble(marble);
			}
		});
    }

    private Color convertColour(Colour colour) {
    	Color color = Color.WHITE;
         switch (colour) {
            case RED : color =Color.RED;
            case BLUE :color = Color.BLUE;
            case GREEN : color= Color.GREEN;
            case YELLOW : color= Color.YELLOW;
        };
        return color;
    }

    public Marble getMarble() {
        return marble;
    }
}