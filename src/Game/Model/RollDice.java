package Game.Model;

import java.util.Observable;
import java.util.Random;

/**
 * This class is used for roll dice observable.
 * @author Jay
 */
public class RollDice extends Observable {
	private int currentDiceRoll = 0;

	/**
	 * Gives a random value of the dice. 
	 * nextInt(max - min)
	 */
	public void roll() {
		Random dice = new Random();
		this.currentDiceRoll = dice.nextInt(6) + 1;
		this.notifyObservers("roll:new");
	}

	public int getCurrentDiceRoll() {
		return currentDiceRoll;
	}

	public RollDice() { }

}
