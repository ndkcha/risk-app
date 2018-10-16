package Test.Testing_Controller;

import Game.Controller.ReinforcementController;

import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

import Game.Controller.FortificationController;
import Game.Model.CountryData;
import Game.Risk.DataHolder;

public class Test_ReinforcementController {
	
	private ReinforcementController rfc=new ReinforcementController();
	
	@Test public void testcalculateReinformentArmies()
	{
		int num_reinforcement_armies=rfc.calculateReinformentArmies(4);
		
		assertEquals(2,num_reinforcement_armies);
	}
}
