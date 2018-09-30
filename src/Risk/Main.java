/**
 * @file Main file to run the Risk Game.
 */
package Risk;

import Controller.Controller;

/**
 * Main class to run the Risk game with Runnable.
 * 
 * @author Jay
 *
 */
public class Main {

	/**
	 * 
	 */
	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
              Controller c = new Controller();
              c.initialize();;
          }
      });
	}

}
