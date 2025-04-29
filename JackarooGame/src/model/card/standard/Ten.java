package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;

public class Ten extends Standard {
	public Ten(String name, String description, Suit suit, BoardManager boardManager, GameManager
			 gameManager){
		super(name, description, 10, suit, boardManager, gameManager);
	}
	
	
	
	
	public boolean validateMarbleSize(ArrayList<Marble> marbles) {
	    if(marbles.size() == 1 || marbles.size() == 0){
	    	return true;
	    }
	    return false;
	}
	
	@Override
	public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
		gameManager.discardCard(gameManager.getNextPlayerColour());
		
	}
	

}
