package engine.board;

import java.util.ArrayList;

import exception.*;
import model.card.standard.Marble;

public interface BoardManager {
	int getSplitDistance();
	void moveBy(Marble marble, int steps, boolean destroy) throws IllegalMovementException, IllegalDestroyException;
	void swap(Marble marble_1, Marble marble_2) throws IllegalSwapException;
	void destroyMarble(Marble marble) throws IllegalDestroyException;
	void sendToBase(Marble marble) throws CannotFieldException, IllegalDestroyException;
	void sendToSafe(Marble marble) throws InvalidMarbleException;
	ArrayList<Marble> getActionableMarbles();

}
