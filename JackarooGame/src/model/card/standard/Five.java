package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.Colour;
import model.player.Marble;

public class Five extends Standard {

	public Five(String name, String description, Suit suit, BoardManager boardManager, GameManager
			 gameManager){
		super(name, description, 5, suit, boardManager, gameManager);
	}
	
	
	public boolean validateMarbleColours(ArrayList<Marble> marbles) {
	    return true;
	}
	@Override
	public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
		
		for(Marble marble  : marbles){
		boardManager.moveBy(marble, 5, false);
		}
		
	}
	
}
