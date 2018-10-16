package Game.Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

import Game.Model.Player;
import Game.Risk.DataHolder;
import Game.View.*;

/**
 * MVC - Common Controller to control the interaction between models and views.
 * 
 * @author Jay
 * @version 1.0.0
 *
 */
public class Controller {
	
	// Game Driver: Hold all the data for gameplay.
	private DataHolder holder = DataHolder.getInstance();
	
	// Store object of GameSettingsView class.
	private GameSettingsView gameSettings;

	private Views playerInfoGUI;
	private StartupController startupController;

	/**
	 * Run the game by calling startGame functions.
	 */
	public void gameInitializer() {
		gameSettings = new GameSettingsView();
		gameSettings.startGame();
		startGameListener();
		mapEditorListener();
	}

	/**
	 * This method fill start the game after game settings and uploading map file.
	 * 
	 * @param map_file File object.
	 */
	public void gameStart(File map_file) {

		StartupController startupController = new StartupController(map_file);
		startupController.processFiles(); // Reads the Map file
		startupController.assignCountries(); // assign the contries
		startupController.assignArmies(); // assign armies

		RiskMainInterface.createInstance();
	}


	/**
	 * Listener for Start Game button.
	 */
	private void startGameListener() {
		ActionListener startGameListener = (ActionEvent e) -> {
			System.out.println("Start Game Button is clicked");
			gameSettings.gameSettings(); // Open game settings.
			gameSettings.chooseOptionFrame().dispose(); // close the previous window.
		};
		this.gameSettings.startGameAction(startGameListener);
	}

	/**
	 * Listener for Map Editor button.
	 */
	private void mapEditorListener() {
		ActionListener mapEditorListener = (ActionEvent e) -> {
			MapEditorController controller = new MapEditorController();
			controller.initAndDisplayView();
			System.out.println("Map Editor button is clicked");
		};
		this.gameSettings.mapEditorAction(mapEditorListener);
	}

}
