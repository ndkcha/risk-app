package Test.Testing_Controller;

import Game.Controller.ReinforcementController;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestReinforcementController {

	ReinforcementController rfc = new ReinforcementController();

	/**
	 * This method will test the reinforcement armies to assign.
	 */
	@Test
	public void testcalculateReinformentArmies() {
		int num_reinforcement_armies = rfc.calculateReinformentArmies(4);

		assertEquals(2, num_reinforcement_armies);
	}
}
