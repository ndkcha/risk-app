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
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Game.Model.Player;
import Game.Risk.DataHolder;
import Game.View.*;

/**
 * MVC - Common Controller to control the interaction between models and views.
 * 
 * @author Jay
 * @version 1..0
 *
 */
public class Controller {
	private DataHolder holder = DataHolder.getInstance();
	// Store object of GameSettingsView class.
	private GameSettingsView gameSettings;

	/**
	 * Run the game by calling startGame functions.
	 */
	public void gameInitializer() {
		gameSettings = new GameSettingsView();
		gameSettings.startGame();
		startGameListener();
		mapEditorListener();
//		taurnamentListener();
		loadGameListener();
	}

	/**
	 * This method fill start the game after game settings and uploading map
	 * file.
	 * 
	 * @param map_file
	 *            File object.
	 */
	public void gameStart(File map_file) {

		StartupController startupController = new StartupController(map_file);
		startupController.processFiles(); // Reads the Map file
		startupController.assignCountries(); // assign the contries

		RiskMainInterface.createInstance();
	}

	/**
	 * Listener for Start Game button.
	 */
	private void startGameListener() {
		ActionListener startGameListener = (ActionEvent e) -> {
			System.out.println("Start Game Button is clicked");
			gameSettings.gameSettings(); // Open game settings.
			gameSettings.chooseOptionFrame().dispose(); // close the previous
														// window.
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

	/**
	 * Listener for load game button.
	 */
	private void loadGameListener() {
		ActionListener loadGameActionListener = (ActionEvent e) -> {
			File file = fileSelector();

			StartupController startupController = new StartupController();
			startupController.processGame(file);

			holder.bmpFile = new File(holder.mapData.imageFilePath);

			RiskMainInterface.createInstance();
		};
		this.gameSettings.addActionLoadGame(loadGameActionListener);
	}

	/**
	 * Open a game file
	 * @return the file object
	 */
	private File fileSelector() {
		System.out.println("Load game file selected");
		JFrame frame = new JFrame("Select Map File");

		// Upload map file.
		// https://coderanch.com/t/466536/java/closing-jFileChooser-window
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("./files/map"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Risk Game Files", "risk");
		fileChooser.setFileFilter(filter);

		int returnValue = fileChooser.showOpenDialog(frame);
		// Get the path of the file.
		if (returnValue == JFileChooser.APPROVE_OPTION)
			return fileChooser.getSelectedFile();
		return null;
	}
}
