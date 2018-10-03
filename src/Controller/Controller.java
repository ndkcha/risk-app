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

import View.MapEditotView;
import View.RiskMainInterface;
import View.GameSettingsView;
//import View.MapView;
import View.Views;

/**
 * MVC - Controller to control the interaction between models and views.
 * 
 * @author Jay
 *
 */
public class Controller {  
	
    // Store object of GameSettingsView class.
	private GameSettingsView gameSettings;
	
	private MapEditotView map_editor;
	
	//ActionListener for "Start Game" button.
	private ActionListener startGameListener;
	
	//ActionListener for "Start Game" button.
	private ActionListener mapEditorListener;
	
	private Views mainGUI;
	private Views cardsGUI;
	private Views controlsGUI;
	private Views diceRollGUI;
	private Views playerInfoGUI;
	
	//Stores object of MapView class.
	//private MapView mapGUI;
	
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
		gameSettings = new GameSettingsView();
		gameSettings.startGame();
		map_editor = new MapEditotView();
		startGameListener();
		mapEditorListener();
	}
	
	public void gameStart(String map_file, String bmp_file) {
		// TODO Auto-generated method stub
		System.out.println(map_file);
		if(bmp_file!=null) {
    		//mapGUI = new MapView(bmp_file);
	    }else {
	    		//mapGUI = new MapView();
	    }
		mainGUI = new Views();
		playerInfoGUI = new Views();
		RiskMainInterface.createInstance(playerInfoGUI);
		setPlayerView(playerInfoGUI);
	}

	public void setPlayerView(Views newView) {
		this.playerInfoGUI = newView;
	}
	
	//Sets listener for Play Game button.
	public void startGameListener() {
		startGameListener =  new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameSettings.gameSettings();
				gameSettings.chooseOptionFrame().dispose();
			}
		};
		this.gameSettings.startGameAction(startGameListener);
	}
	
	public void mapEditorListener() {
		mapEditorListener =  new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				map_editor.mapEditorUI();
			}
		};
		this.gameSettings.mapEditorAction(mapEditorListener);
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
