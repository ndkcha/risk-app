package Test.Testing_Model;

import Game.Model.RollDice;
import java.util.Random;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

/**
 * This class test the rolling of dice for player attack
 * 
 * @author Jay
 */
public class TestRollDice {

	/**
	 * This method will test Roll dice model that generate random number.
	 */
	@Test
	public void testrolldice() {

		RollDice dice = new RollDice();
		Random random = new Random();

		int expected = random.nextInt(6) + 1;
		int testdice = dice.roll();
		assertNotEquals(testdice, expected);
	}

}
