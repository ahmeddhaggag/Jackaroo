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
	@Override
	public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
		
		for(Marble marble  : marbles){
		gameManager.fieldMarble();
		}
		
	}
	
}
