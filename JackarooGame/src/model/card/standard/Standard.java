
package model.card.standard;

import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.card.Card;
import model.player.Marble;

public class Standard extends Card {
      
	private final int rank;
	private final Suit suit;
	public Standard(String name, String description, int rank, Suit suit, BoardManager boardManager,
			 GameManager gameManager){
		super(name, description, boardManager, gameManager);
		this.rank = rank;
		this.suit = suit;
	}
	public String getName(){
		return super.getName();
	}
	
	public String getDescription(){
		return super.getDescription();
	}
	public int getRank() {
		return this.rank;
	}
	public Suit getSuit() {
		return this.suit;
	}
	@Override
	public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
		
		for(Marble marble  : marbles){
		boardManager.moveBy(marble, rank, false);
		}
		
	}
	
}
