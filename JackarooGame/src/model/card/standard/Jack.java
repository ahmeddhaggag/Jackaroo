package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.Colour;
import model.player.Marble;

public class Jack extends Standard{

	public Jack(String name, String description, Suit suit, BoardManager boardManager, GameManager
			gameManager){
		super(name, description, 11, suit, boardManager, gameManager);
	}


	public boolean validateMarbleSize(ArrayList<Marble> marbles) {
		int size = marbles.size();
		if(size ==1 || size ==2 ){
			return true;
		}
		return false;
	}


	public boolean validateMarbleColours(ArrayList<Marble> marbles) { 
		int size = marbles.size();

		if(size == 2 && marbles.get(0).getColour() != marbles.get(1).getColour()){
			return true;
		}
		else if(size ==1 && marbles.get(0).getColour() == gameManager.getActivePlayerColour()){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
		int size = marbles.size();
		if(size ==2){
			boardManager.swap(marbles.get(0), marbles.get(1));
		}else if( size == 1){
			boardManager.moveBy(marbles.get(0), 11, false);
		}else{
			throw new InvalidMarbleException("Incorrect number of marbles");
		}

	}

}
