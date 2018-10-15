package Test.Testing_Controller;

import Game.Controller.ReinforcementController;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Test_ReinforcementController {
	
	ReinforcementController rfc=new ReinforcementController();
	
	@Test public void testcalculateReinformentArmies()
	{
		int num_reinforcement_armies=rfc.calculateReinformentArmies(4);
		
		assertEquals(2,num_reinforcement_armies);
	}
}
