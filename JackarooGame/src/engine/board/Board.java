package engine.board;

public class Board {
	GameManager gameManager;
	ArrayList<Cell> track;
	ArrayList<SafeZone> safeZones;
	int splitDistance;
	public Board(ArrayList<Colour> colourOrder, GameManager gameManager){
		 
	}
	public int getSplitDistance() {
		return splitDistance;
	}
	public void setSplitDistance(int splitDistance) {
		this.splitDistance = splitDistance;
	}
	public ArrayList<Cell> getTrack() {
		return track;
	}
	public ArrayList<SafeZone> getSafeZones() {
		return safeZones;
	}
}
