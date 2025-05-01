package engine;
import java.io.IOException;

import engine.board.Board;
import engine.board.BoardManager;

import java.util.*;

import model.card.*;
import model.player.*;
import model.Colour;
import exception.CannotDiscardException;
import exception.CannotFieldException;
import exception.GameException;
import exception.IllegalDestroyException;
import exception.InvalidCardException;
import exception.InvalidMarbleException;
import exception.SplitOutOfRangeException;
import exception.GameException.*;

public class Game implements GameManager{
	private final Board board;
	private final ArrayList<Player> players = new ArrayList<Player>();
	private final ArrayList<Card> firePit = new ArrayList<Card>();
	private int currentPlayerIndex;
	private int turn;
	public Game(String playerName) throws IOException{
		ArrayList<Colour> colourOrder=new ArrayList<>();
		colourOrder.add(Colour.RED);
		colourOrder.add(Colour.BLUE);
		colourOrder.add(Colour.GREEN);
		colourOrder.add(Colour.YELLOW);
		Collections.shuffle(colourOrder);
		this.board=new Board(colourOrder,this);
		Deck.loadCardPool(this.board,this);
		
		players.add(new Player(playerName,colourOrder.get(0)));
		for(int i=1;i<=3;i++)
		{
			String name="CPU "+i;
			players.add(new CPU(name,colourOrder.get(i),this.board));
		}
		for(int i =0; i<4; i++){
			players.get(i).setHand(Deck.drawCards());
		}
		
		this.currentPlayerIndex=0;
		this.turn=0;
		 

	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public Board getBoard() {
		return board;
	}
	public ArrayList<Card> getFirePit() {
		return firePit;
	}
	public void selectCard(Card card) throws InvalidCardException{//1
		this.players.get(currentPlayerIndex).selectCard(card);
	}
	public void selectMarble(Marble marble) throws InvalidMarbleException{//2
		this.players.get(currentPlayerIndex).selectMarble(marble);
	}
	public void deselectAll(){//3
		this.players.get(currentPlayerIndex).deselectAll();
	}
	public void editSplitDistance(int splitDistance) throws SplitOutOfRangeException{//4
		if(splitDistance>=1 && splitDistance<=6)
			this.board.setSplitDistance(splitDistance);
		else
			throw new SplitOutOfRangeException();
	}
	public boolean canPlayTurn(){//5
		return this.players.get(currentPlayerIndex).getHand().size()+turn == 4;
	}
	public  void playPlayerTurn() throws GameException{//6
		if (this.canPlayTurn())
			this.players.get(currentPlayerIndex).play();
	}
	public void endPlayerTurn(){//7
		if (canPlayTurn()){
		Card selected = this.players.get(currentPlayerIndex).getSelectedCard();
		this.players.get(currentPlayerIndex).getHand().remove(selected);
		this.firePit.add(selected);}
		this.deselectAll();
		currentPlayerIndex++;
		if(currentPlayerIndex==4){
			turn++;
			currentPlayerIndex=0;
		}
		if(turn==4){
			turn=0;
			for(int i=0;i<=3;i++){
				if(Deck.getPoolSize()<4){
					Deck.refillPool(firePit);
					firePit.clear();
				}
				this.players.get(i).setHand(Deck.drawCards());
			}
				
		}
	}
	public Colour checkWin(){//8
		Colour res=null;
		for(int i=0;i<=3;i++)
			if(this.board.getSafeZones().get(i).isFull()){
				res=this.board.getSafeZones().get(i).getColour();
				break;
			}
		return res;
	}
	
	
	public void sendHome(Marble marble){//9
		this.players.get(currentPlayerIndex).regainMarble(marble);
	}
	
	
	public void fieldMarble() throws CannotFieldException, IllegalDestroyException{//10
		Marble m=this.players.get(currentPlayerIndex).getOneMarble();
		this.board.sendToBase(m);
		this.players.get(currentPlayerIndex).getMarbles().remove(m);
	}
	
	
	public void discardCard(Colour colour) throws CannotDiscardException{//11
		Player p=null;
		for(int i=0;i<=3;i++)
			if(this.players.get(i).getColour()==colour)
				p=this.players.get(i);
		if(p.getHand().size()==0 || this.getActivePlayerColour()==colour)
			throw new CannotDiscardException();
		else{
		Random random=new Random();
		int x=random.nextInt(p.getHand().size());
		this.firePit.add(p.getHand().get(x));
		p.getHand().remove(x);
		}
	}
	public void discardCard() throws CannotDiscardException{//12
		ArrayList<Colour> c=new ArrayList<>();
		c.add(Colour.RED);
		c.add(Colour.BLUE);
		c.add(Colour.GREEN);
		c.add(Colour.YELLOW);
		Collections.shuffle(c);
		Colour c1=(c.get(0)==this.players.get(currentPlayerIndex).getColour())?c.get(1):c.get(0);
		discardCard(c1);
	}
	public Colour getActivePlayerColour(){
		return this.players.get(currentPlayerIndex).getColour();
	}
	public Colour getNextPlayerColour(){
		return (currentPlayerIndex == 3)?this.players.get(0).getColour():this.players.get(currentPlayerIndex+1).getColour();
	}

}

