/**
 * @file Controllers for the whole Risk Game
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.StartUpPhase;

/**
 * MVC - Controller to control the interaction between models and views.
 * 
 * @author Jay
 *
 */
public class Controller {    
    // Store object of StartUpPhase class.
	private StartUpPhase start_up_process;
	
	//ActionListener for "Start Game" button.
	private ActionListener startGameListener;
	
	//ActionListener for "Start Game" button.
	private ActionListener selectMapListener;
	
	// Initialization of Game and listeners.
	public void initialize() {
		start_up_process = new StartUpPhase();
		start_up_process.startUpPhase();
		startGameListener();
		//selectMapListener();
	}
	
	//Sets listener for Play Game button.
	public void startGameListener() {
		startGameListener =  new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start_up_process.game_settings();
			}
		};
		this.start_up_process.startGameAction(startGameListener);
	}
	
//	public void selectMapListener() {
//		selectMapListener =  new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				start_up_process.map_selector("map");
//			}
//		};
//		this.start_up_process.selectMapAction(selectMapListener);
//	}
	
	
	/**
	 * 
	 */
	public Controller() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
