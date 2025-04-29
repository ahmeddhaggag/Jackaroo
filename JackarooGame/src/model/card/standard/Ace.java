package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;

public class Ace extends Standard{
	
	public Ace(String name, String description, Suit suit, BoardManager boardManager, GameManager
			 gameManager){
		super(name, description, 1, suit, boardManager, gameManager);
	}
	
	@Override
	public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
		
		for(Marble marble  : marbles){
		gameManager.fieldMarble();
		}
		
	}
	

}
