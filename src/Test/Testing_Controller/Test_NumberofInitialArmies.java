package Test.Testing_Controller;

import Game.Controller.StartupController;
import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;


/**number of armies distributed at start of the game is tested  
 * object of class StartupController is initialized
 * @param File is passed 
 * @author kunal ghai
 *
 */
public class Test_NumberofInitialArmies {
			
   StartupController sc=new StartupController(new File(""));
   
   /** 
	 * 
	 */
	@Test public void testdetermineOfInitialArmy() {
			 for(int i=2;i<=6;i++)
			 {
			  assertEquals((40-((i-2)*5)),sc.determineOfInitialArmy(i));
			 }
		}

	}
