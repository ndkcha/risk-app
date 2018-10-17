package Test.Testing_Controller;

import Game.Controller.StartupController;
import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

/**
 * number of armies distributed at start of the game is tested object of class
 * StartupController is initialized
 * 
 * @author kunal ghai
 */
public class TestNumberofInitialArmies {

	StartupController sc = new StartupController(new File(""));

	/**
	 * This will test the Initial armies assign to user on initial startup
	 * phase.
	 */
	@Test
	public void testdetermineOfInitialArmy() {
		for (int i = 2; i <= 6; i++) {
			assertEquals((40 - ((i - 2) * 5)), sc.determineOfInitialArmy(i));
		}
	}

}
