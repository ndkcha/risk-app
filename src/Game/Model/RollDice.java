package Game.Model;

import java.util.Observable;
import java.util.Random;

/**
 * This class is used for roll dice observable.
 * 
 * @author Jay
 * @version 1.2.0
 */
public class RollDice {

	/**
	 * Gives a random value of the dice. 
	 * nextInt(max - min)
	 * 
	 * @return dice Dive Value
	 */
	public int roll() {
		Random dice = new Random();
		return dice.nextInt(6) + 1;
	}

	public RollDice() { }

}
