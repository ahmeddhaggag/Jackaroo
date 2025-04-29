package model.card;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.Colour;
import model.card.standard.*;
import model.player.Marble;

public abstract class Card {

	private final String name;
	private final String description;
	protected BoardManager boardManager;
	protected GameManager gameManager;
	
	
	public Card(String name, String description, BoardManager boardManager, GameManager gameManager){
		this.name = name;
		this.description = description;
		this.boardManager = boardManager;
		this.gameManager = gameManager;
	}
	
	
	
	
	public boolean validateMarbleSize(ArrayList<Marble> marbles) {
	    int expectedSize = getExpectedMarbleCount(); 
	    return marbles.size() == expectedSize;
	}

	// helper 
	private int getExpectedMarbleCount() {
	    if (this instanceof Jack) return 2;
	    if (this instanceof Seven) return 2;
	    if (this instanceof Queen) return 0;
	    if (this instanceof Ten) return 0;
	    return 1; // Default 
	}
	
	
	public boolean validateMarbleColours(ArrayList<Marble> marbles) {
	    Colour playerColour = gameManager.getActivePlayerColour(); 
	    for (Marble marble : marbles) {
	        if (this instanceof Jack) {
	            continue; 
	        }
	        if (marble.getColour() != playerColour) {
	            return false;
	        }
	    }
	    return true;
	}
	
	
	public abstract void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException;





	public String getName() {
		return name;
	}


	public String getDescription() {
		return description;
	}
}
