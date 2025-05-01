package model.player;

import java.util.ArrayList;

import exception.ActionException;
import exception.GameException;
import exception.InvalidCardException;
import exception.InvalidMarbleException;
import model.Colour;
import model.card.Card;

public class Player {
	
	private final String name;
	private final Colour colour;
	private ArrayList<Card> hand;
	private final ArrayList<Marble> marbles;
	private Card selectedCard;
	private final ArrayList<Marble> selectedMarbles;
	private ArrayList<Marble> homeMarbles;

	
	
	
	public Player(String name, Colour colour){
		this.name = name;
		this.colour = colour;
		hand = new ArrayList<Card>(4);
		selectedMarbles = new ArrayList<Marble>();
		this.homeMarbles = new ArrayList<>();
		marbles = new ArrayList<Marble>();
		for(int i =0; i<4; i++){
			marbles.add(new Marble(colour));	
		}
		selectedCard = null;
	}
	
	public String getName() {
		return name;
	}
	public Colour getColour() {
		return colour;
	}
	public ArrayList<Card> getHand() {
		return hand;
	}
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	public ArrayList<Marble> getMarbles() {
		return marbles;
	}
	
	public Card getSelectedCard() {
		return selectedCard;
	}
	

	
	
	
	
	
	
	 public void regainMarble(Marble marble) {
	        if (marble != null && marble.getColour() == this.colour) {
	            marbles.add(marble);
	        }
	    }

	 public void selectMarble(Marble marble) throws InvalidMarbleException {
		    if (selectedMarbles.size() >= 2) {
		        throw new InvalidMarbleException("Cannot select more than 2 marbles.");
		    }
		    if (selectedMarbles.contains(marble)) {
		        throw new InvalidMarbleException("This marble is already selected.");
		    }
		    selectedMarbles.add(marble);
		}
	
	 public void selectCard(Card card) throws InvalidCardException {
	        if (!hand.contains(card)) {
	            throw new InvalidCardException("Card not in player's hand.");
	        }
	        this.selectedCard = card;
	    }
	 
	public void deselectAll(){
		selectedCard =null;
		selectedMarbles.clear();
		
	}
	public void play() throws GameException {
    if (selectedCard == null) {
        throw new InvalidCardException("No card selected.");
    }

    if (!selectedCard.validateMarbleSize(selectedMarbles)) {
        throw new InvalidMarbleException("Incorrect number of marbles for this card.");
    }
    if (!selectedCard.validateMarbleColours(selectedMarbles)) {
        throw new InvalidMarbleException("Invalid marble colors for this card.");
    }

    try {
        selectedCard.act(selectedMarbles);
    } catch (ActionException | InvalidMarbleException e) {
        throw new InvalidCardException("Card action failed: " + e.getMessage());
    }

    
    deselectAll();
}


	public Marble getOneMarble() {
		if(homeMarbles.isEmpty()) return null;
		return homeMarbles.get(0);
	}

	public void setHomeMarble(ArrayList<Marble> homeMarbles) {
		this.homeMarbles = homeMarbles;
	}
	public void addCardToHand(Card card) {
        if (hand.size() < 4) {
            hand.add(card);
        }
    }
	 public void removeSelectedCard() {
	        if (selectedCard != null) {
	            hand.remove(selectedCard);
	            selectedCard = null;
	        }
	    }

}
