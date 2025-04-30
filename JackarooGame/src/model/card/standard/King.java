package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;

public class King extends Standard{

	public King(String name, String description, Suit suit, BoardManager boardManager, GameManager
			gameManager){
		super(name, description, 13, suit, boardManager, gameManager);
	}

	public boolean validateMarbleSize(ArrayList<Marble> marbles) {
		int size = marbles.size();
		if(size == 1 || size == 0){
			return true;
		}
		return false;
	}
	@Override
	public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
		int size = marbles.size();
		if(size == 0){
			gameManager.fieldMarble();
		}else if(size == 1){
			boardManager.moveBy(marbles.get(0), 13, true);
		}else{
			throw new InvalidMarbleException("Incorrect number of marbles");
		}


	}

}
