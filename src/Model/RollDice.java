/**
 * 
 */
package Model;

import java.util.Random;

/**
 * @author Jay
 *
 */
public class RollDice {


	/**
	 * Gives a random value of the dice. 
	 * nextInt(max - min)
	 * 
	 * @return integer number that represents the value on the dice.
	 */
	public int roll() {
		Random dice = new Random();
		return dice.nextInt(6) + 1;
	}
	
	/**
	 * 
	 */
	public RollDice() {
		// TODO Auto-generated constructor stub
	}

}
