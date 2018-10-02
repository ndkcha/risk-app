/**
 * @file Controllers for the whole Risk Game
 */
package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

import View.StartUpPhase;
import View.MapView;
//import risk.view.MapView;

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
	
	//Stores object of MapView class.
	private MapView mapGUI;
	
	/**
	 * BufferedReader Object to read the image file.
	 */
	private BufferedImage image;
	
	/**
	 * Boolean to check for .bmp file for the map.
	 */
	private boolean graphicalMap = false;
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 2353535256045293828L;
	
	// Initialization of Game and listeners.
	public void initialize() {
		start_up_process = new StartUpPhase();
		start_up_process.startUpPhase();
		startGameListener();
		//selectMapListener();
		//gameStart();
	}
	
	public void gameStart(String map_file, String bmp_file) {
		// TODO Auto-generated method stub
		System.out.println(map_file);
		if(bmp_file!=null) {
    		mapGUI = new MapView(bmp_file);
	    }else {
	    		mapGUI = new MapView();
	    }
	}

	//Sets listener for Play Game button.
	public void startGameListener() {
		startGameListener =  new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start_up_process.game_settings();
				start_up_process.chooseOptionFrame().dispose();
			}
		};
		this.start_up_process.startGameAction(startGameListener);
	}
	
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
