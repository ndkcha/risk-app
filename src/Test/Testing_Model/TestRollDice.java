package Test.Testing_Model;

import Game.Model.RollDice;

import static org.junit.Assert.*;

import java.util.Random;

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
		int testdice = dice.roll();
		
		Boolean dices = (testdice >= 1 && testdice <= 6);
		assertTrue(dices);
	}

}
