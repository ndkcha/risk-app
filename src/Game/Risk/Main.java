/**
 * @file Main file to run the Risk Game.
 */
package Game.Risk;

import Game.Controller.Controller;
import javax.swing.SwingUtilities;

/**
 * Main class to run the Risk game with Runnable.
 * 
 * @author Jay
 * @version 1.0.0
 */
public class Main {

	/**
	 * Main function to initialize and start the game.
	 * @param args Array of String args for main function.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SwingUtilities.invokeLater(new Runnable() {
          public void run() {
              Controller c = new Controller();
              c.gameInitializer();
          }
      });
	}

}
